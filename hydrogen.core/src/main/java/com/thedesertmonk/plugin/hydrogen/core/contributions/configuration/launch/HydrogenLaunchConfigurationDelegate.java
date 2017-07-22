package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.VMRunnerConfiguration;

import com.thedesertmonk.plugin.hydrogen.core.HydrogenActivator;
import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.listener.HydrogenLaunchListener;
import com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage.PreferenceConstants;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArgumentsFactory;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.thedesertmonk.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * The Hydrogen launch configuration delegate.
 *
 * @author Andrew Vojak
 */
public class HydrogenLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory
			.getForClass(HydrogenLaunchConfigurationDelegate.class);

	private static final String MAIN_SERVER_CLASS = "org.h2.tools.Server"; //$NON-NLS-1$
	private static final String LAUNCH_HEADLESS = "-Djava.awt.headless=true"; //$NON-NLS-1$

	private static final String UNSET_EXECUTABLE_ERROR = "The executable location has not yet been set. " //$NON-NLS-1$
			+ "Update the Hydrogen preferences and select the H2 executable before reattempting the launch."; //$NON-NLS-1$
	private static final String EXECUTABLE_NOT_FILE_ERROR = "The specified H2 executable is not a file. " //$NON-NLS-1$
			+ "Verify the Hydrogen preferences before reattempting the launch."; //$NON-NLS-1$
	private static final String INSUFFICIENT_PRIVS_ERROR = "The specified H2 executable cannot be executed. " //$NON-NLS-1$
			+ "Verify that the correct permissions are in place for Eclipse to launch the executable before " //$NON-NLS-1$
			+ "reattempting the launch."; //$NON-NLS-1$

	private final ProgramArgumentsFactory programArgumentsFactory;
	private final LaunchDelegatePortPool portPool;

	/**
	 * Constructor.
	 */
	public HydrogenLaunchConfigurationDelegate() {
		programArgumentsFactory = new ProgramArgumentsFactory();
		portPool = new LaunchDelegatePortPool(new LaunchDelegatePortAvailabilityChecker(new ServerSocketFactory()));
		getLaunchManager().addLaunchListener(new HydrogenLaunchListener(portPool));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
			final IProgressMonitor monitor) throws CoreException {
		// Only care about 'run' mode
		if (!mode.equals(ILaunchManager.RUN_MODE)) {
			LOGGER.error("Launch mode was " + mode + ". Expected " + ILaunchManager.RUN_MODE); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}

		LOGGER.info("Preparing to launch: " + configuration.getName()); //$NON-NLS-1$

		final IVMInstall vm = verifyVMInstall(configuration);
		final IVMRunner runner = vm.getVMRunner(mode);

		final String executablePreference = HydrogenActivator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.P_EXECUTABLE);
		validateExecutablePreference(executablePreference);
		final File executable = new File(executablePreference);
		validateExecutable(executable);

		final String workingDirectory = executable.getParent();
		LOGGER.debug("Working directory: " + workingDirectory); //$NON-NLS-1$
		final String executableName = executable.getName();
		LOGGER.debug("Executable name: " + executableName); //$NON-NLS-1$
		final String[] classPath = new String[] { executableName };
		LOGGER.debug("Classpath: " + Arrays.toString(classPath)); //$NON-NLS-1$
		final VMRunnerConfiguration runConfig = new VMRunnerConfiguration(MAIN_SERVER_CLASS, classPath);
		runConfig.setWorkingDirectory(workingDirectory);

		ProgramArguments programArguments = programArgumentsFactory.create(configuration);
		programArguments = validatePortNumbers(programArguments);
		setUsedPorts(launch, programArguments);
		final String[] argArray = programArguments.getArguments().getArguments().toArray(new String[0]);
		LOGGER.debug("Program arguments: " + Arrays.toString(argArray)); //$NON-NLS-1$
		runConfig.setProgramArguments(argArray);

		final String[] vmArgs = new String[] { LAUNCH_HEADLESS };
		LOGGER.debug("VM arguments: " + Arrays.toString(vmArgs)); //$NON-NLS-1$
		runConfig.setVMArguments(vmArgs);

		// Bootpath
		// final String[] bootpath = getBootpath(configuration);
		// runConfig.setBootClassPath(bootpath);

		LOGGER.info("Launch starting..."); //$NON-NLS-1$

		// Launch the configuration
		runner.run(runConfig, launch, monitor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void abort(final String message, final Throwable exception, final int code) throws CoreException {
		final String exceptionMessage = exception == null ? "[null]" : exception.getMessage(); //$NON-NLS-1$
		LOGGER.error("Launch aborted {message=" + message + ", exception=" + exceptionMessage + ", code=" + code); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		super.abort(message, exception, code);
	}

	/**
	 * Sets attributes on the {@link ILaunch} for which ports have been used.
	 *
	 * @param launch The {@link ILaunch} instance.
	 * @param programArguments The {@link ProgramArguments}.
	 */
	private void setUsedPorts(final ILaunch launch, final ProgramArguments programArguments) {
		final Optional<WebServerArguments> webServerArguments = programArguments.getWebServerArguments();
		if (webServerArguments.isPresent()) {
			launch.setAttribute(ServerOption.WEB_PORT.name(), webServerArguments.get().getPort().get());
		}
		final Optional<TcpServerArguments> tcpServerArguments = programArguments.getTcpServerArguments();
		if (tcpServerArguments.isPresent()) {
			launch.setAttribute(ServerOption.TCP_PORT.name(), tcpServerArguments.get().getPort().get());
		}
		final Optional<PgServerArguments> pgServerArguments = programArguments.getPgServerArguments();
		if (tcpServerArguments.isPresent()) {
			launch.setAttribute(ServerOption.PG_PORT.name(), pgServerArguments.get().getPort().get());
		}
	}

	private void validateExecutablePreference(final String executablePreference) throws CoreException {
		if (executablePreference == null || executablePreference.trim().isEmpty()) {
			abort(UNSET_EXECUTABLE_ERROR, null, 0);
		}
	}

	/**
	 * Validate that the given {@link File} exists, is a file, and able to be
	 * executed.
	 *
	 * @param executable The {@link File} to execute.
	 * @throws CoreException Thrown by
	 *             {@link HydrogenLaunchConfigurationDelegate#abort(String, Throwable, int)}.
	 */
	private void validateExecutable(final File executable) throws CoreException {
		if (!executable.isFile()) {
			abort(EXECUTABLE_NOT_FILE_ERROR, null, 0);
		}
		if (!executable.canExecute()) {
			abort(INSUFFICIENT_PRIVS_ERROR, null, 0);
		}
	}

	// TODO Lots of repetition in this method - need to refactor
	private ProgramArguments validatePortNumbers(final ProgramArguments programArguments) {
		WebServerArguments updatedWebServerArguments = null;
		TcpServerArguments updatedTcpServerArguments = null;
		PgServerArguments updatedPgServerArguments = null;

		// Check if the specified web server port is free. If not, find a new
		// one that is.
		final Optional<WebServerArguments> webServerArguments = programArguments.getWebServerArguments();
		if (webServerArguments.isPresent()) {
			final Optional<String> port = webServerArguments.get().getPort();
			if (port.isPresent()) {
				if (!portPool.isPortFree(Integer.valueOf(port.get()))) {
					LOGGER.debug(
							"Port " + port.get() + " already in use. A new port will be selected for the web server."); //$NON-NLS-1$ //$NON-NLS-2$
					updatedWebServerArguments = new WebServerArgumentsBuilder(webServerArguments.get())
							.withPort(String.valueOf(portPool.getFreePort())).build();
				}
			}
		}

		// Check if the specified TCP server port is free. If not, find a new
		// one that is.
		final Optional<TcpServerArguments> tcpServerArguments = programArguments.getTcpServerArguments();
		if (tcpServerArguments.isPresent()) {
			final Optional<String> port = tcpServerArguments.get().getPort();
			if (port.isPresent()) {
				if (!portPool.isPortFree(Integer.valueOf(port.get()))) {
					LOGGER.debug(
							"Port " + port.get() + " already in use. A new port will be selected for the TCP server."); //$NON-NLS-1$ //$NON-NLS-2$
					updatedTcpServerArguments = new TcpServerArgumentsBuilder(tcpServerArguments.get())
							.withPort(String.valueOf(portPool.getFreePort())).build();
				}
			}
		}

		// Check if the specified PostgreSQL server port is free. If not, find a
		// new one that is.
		final Optional<PgServerArguments> pgServerArguments = programArguments.getPgServerArguments();
		if (tcpServerArguments.isPresent()) {
			final Optional<String> port = pgServerArguments.get().getPort();
			if (port.isPresent()) {
				if (!portPool.isPortFree(Integer.valueOf(port.get()))) {
					LOGGER.debug("Port " + port.get() //$NON-NLS-1$
							+ " already in use. A new port will be selected for the PostgreSQL server."); //$NON-NLS-1$
					updatedPgServerArguments = new PgServerArgumentsBuilder(pgServerArguments.get())
							.withPort(String.valueOf(portPool.getFreePort())).build();
				}
			}
		}

		// One of the specified port numbers was invalid, so we need to recreate
		// the ProgramArguments
		if ((updatedWebServerArguments != null) || (updatedTcpServerArguments != null)
				|| (updatedPgServerArguments != null)) {
			return programArgumentsFactory.create(updatedWebServerArguments, updatedTcpServerArguments,
					updatedPgServerArguments);
		}

		return programArguments;
	}

}
