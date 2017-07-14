package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch;

import java.io.File;
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
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArgumentsFactory;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArgumentsBuilder;

/**
 * The Hydrogen launch configuration delegate.
 *
 * @author Andrew Vojak
 */
public class HydrogenLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {

	private static final String MAIN_SERVER_CLASS = "org.h2.tools.Server"; //$NON-NLS-1$
	private static final String LAUNCH_HEADLESS = "-Djava.awt.headless=true"; //$NON-NLS-1$

	private final ProgramArgumentsFactory programArgumentsFactory;
	private final LaunchDelegatePortPool portPool;

	/**
	 * Constructor.
	 */
	public HydrogenLaunchConfigurationDelegate() {
		programArgumentsFactory = new ProgramArgumentsFactory();
		portPool = new LaunchDelegatePortPool(new LaunchDelegatePortAvailabilityChecker(new ServerSocketFactory()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
			final IProgressMonitor monitor) throws CoreException {
		// Only care about 'run' mode
		if (!mode.equals(ILaunchManager.RUN_MODE)) {
			return;
		}

		getLaunchManager().addLaunchListener(new HydrogenLaunchListener(portPool));

		final IVMInstall vm = verifyVMInstall(configuration);
		final IVMRunner runner = vm.getVMRunner(mode);

		// TODO Fix this so that if attempting to run after install (before
		// setting the executable location), the message prompts for it to be
		// set instead of saying it doesn't exist or isn't a file.
		final File executablePreference = new File(
				HydrogenActivator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_EXECUTABLE));
		validateExecutablePermissions(executablePreference);
		final String workingDirectory = executablePreference.getParent();
		final String executableName = executablePreference.getName();

		// Create VM config
		final VMRunnerConfiguration runConfig = new VMRunnerConfiguration(MAIN_SERVER_CLASS,
				new String[] { executableName });
		runConfig.setWorkingDirectory(workingDirectory);
		ProgramArguments programArguments = programArgumentsFactory.create(configuration);
		programArguments = validatePortNumbers(programArguments);
		final String[] argArray = programArguments.getArguments().getArguments().toArray(new String[0]);
		runConfig.setProgramArguments(argArray);
		runConfig.setVMArguments(new String[] { LAUNCH_HEADLESS });

		// Bootpath
		// final String[] bootpath = getBootpath(configuration);
		// runConfig.setBootClassPath(bootpath);

		// Launch the configuration
		runner.run(runConfig, launch, monitor);
	}

	private void validateExecutablePermissions(final File executable) throws CoreException {
		if (!executable.isFile()) {
			abort("Specified executable is not a file", null, 0); //$NON-NLS-1$
		}
		if (!executable.canExecute()) {
			abort("Specified executable cannot be executed", null, 0); //$NON-NLS-1$
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
					// TODO Log message that the specified port is already used
					// and a new one will be selected
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
					// TODO Log message that the specified port is already used
					// and a new one will be selected
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
					// TODO Log message that the specified port is already used
					// and a new one will be selected
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
