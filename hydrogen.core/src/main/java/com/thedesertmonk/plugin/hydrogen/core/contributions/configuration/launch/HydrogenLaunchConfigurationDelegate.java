package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch;

import java.io.File;

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
import com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage.PreferenceConstants;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArgumentsFactory;

/**
 * The Hydrogen launch configuration delegate.
 *
 * @author Andrew Vojak
 */
public class HydrogenLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {

	private static final String MAIN_SERVER_CLASS = "org.h2.tools.Server"; //$NON-NLS-1$
	private static final String LAUNCH_HEADLESS = "-Djava.awt.headless=true"; //$NON-NLS-1$

	private final ProgramArgumentsFactory programArgumentsFactory;

	/**
	 * Constructor.
	 */
	public HydrogenLaunchConfigurationDelegate() {
		programArgumentsFactory = new ProgramArgumentsFactory();
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

		final IVMInstall vm = verifyVMInstall(configuration);
		final IVMRunner runner = vm.getVMRunner(mode);

		final File executablePreference = new File(
				HydrogenActivator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_EXECUTABLE));
		validateExecutablePermissions(executablePreference);
		final String workingDirectory = executablePreference.getParent();
		final String executableName = executablePreference.getName();

		// Create VM config
		final VMRunnerConfiguration runConfig = new VMRunnerConfiguration(MAIN_SERVER_CLASS,
				new String[] { executableName });
		runConfig.setWorkingDirectory(workingDirectory);
		final ProgramArguments programArguments = programArgumentsFactory.create(configuration);
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

}
