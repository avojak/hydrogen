/**
 * @author
 */
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

/**
 * @author andrewvojak
 *         http://www.eclipse.org/articles/Article-Launch-Framework/launch.html
 */
public class LaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {

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
		final String workingDirectory = executablePreference.getParent();
		final String executable = executablePreference.getName();

		System.out.println("> Working directory: " + workingDirectory);
		System.out.println("> Executable name: " + executable);

		// Create VM config
		final VMRunnerConfiguration runConfig = new VMRunnerConfiguration("org.h2.tools.Server",
				new String[] { executable });
		runConfig.setWorkingDirectory(workingDirectory);
		final ProgramArguments programArguments = new ProgramArguments(configuration);
		final String[] argArray = programArguments.getArguments().getArguments().toArray(new String[0]);
		runConfig.setProgramArguments(argArray);
		runConfig.setVMArguments(new String[] { "-Djava.awt.headless=true" });

		// Bootpath
		final String[] bootpath = getBootpath(configuration);
		// runConfig.setBootClassPath(bootpath);

		// Launch the configuration
		runner.run(runConfig, launch, monitor);
	}

}
