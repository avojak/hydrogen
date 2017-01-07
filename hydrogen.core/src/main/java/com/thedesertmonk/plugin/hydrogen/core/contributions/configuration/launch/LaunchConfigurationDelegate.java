/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.VMRunnerConfiguration;

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

		// Create VM config
		final VMRunnerConfiguration runConfig = new VMRunnerConfiguration("org.h2.tools.Server",
				new String[] { "h2*.jar" });
		runConfig.setWorkingDirectory("/Users/andrewvojak/Downloads/h2/bin");

		// Bootpath
		final String[] bootpath = getBootpath(configuration);
		runConfig.setBootClassPath(bootpath);

		// Launch the configuration
		runner.run(runConfig, launch, monitor);
	}

}
