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

		// IProgressMonitor _monitor = monitor;
		//
		// if (_monitor == null) {
		// _monitor = new NullProgressMonitor();
		// }
		//
		// _monitor.beginTask(NLS.bind("{0}...", new String[] {
		// configuration.getName() }), 3); //$NON-NLS-1$
		// // check for cancellation
		// if (_monitor.isCanceled()) {
		// return;
		// }
		// try {
		// _monitor.subTask(
		// LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Verifying_launch_attributes____1);
		//
		// final String mainTypeName = "org.h2.tools.Server"; //
		// verifyMainTypeName(configuration);
		// final IVMRunner runner = getVMRunner(configuration, mode);
		//
		// final File workingDir = new
		// File("/Users/andrewvojak/Downloads/h2/bin"); //
		// verifyWorkingDirectory(configuration);
		// String workingDirName = null;
		// if (workingDir != null) {
		// workingDirName = workingDir.getAbsolutePath();
		// }
		//
		// // Environment variables
		// final String[] envp = getEnvironment(configuration);
		//
		// // Program & VM arguments
		// final String pgmArgs = getProgramArguments(configuration);
		// final String vmArgs = getVMArguments(configuration);
		// final ExecutionArguments execArgs = new ExecutionArguments(vmArgs,
		// pgmArgs);
		//
		// // VM-specific attributes
		// final Map<String, Object> vmAttributesMap =
		// getVMSpecificAttributesMap(configuration);
		//
		// // Classpath
		// final String[] classpath = new String[] {
		// "/Users/andrewvojak/Downloads/h2/bin/h2-1.4.193.jar" }; //
		// getClasspath(configuration);
		//
		// // Create VM config
		// final VMRunnerConfiguration runConfig = new
		// VMRunnerConfiguration(mainTypeName, classpath);
		// runConfig.setProgramArguments(execArgs.getProgramArgumentsArray());
		// runConfig.setEnvironment(envp);
		// runConfig.setVMArguments(execArgs.getVMArgumentsArray());
		// runConfig.setWorkingDirectory(workingDirName);
		// runConfig.setVMSpecificAttributesMap(vmAttributesMap);
		//
		// // Bootpath
		// runConfig.setBootClassPath(getBootpath(configuration));
		//
		// // check for cancellation
		// if (_monitor.isCanceled()) {
		// return;
		// }
		//
		// // stop in main
		// prepareStopInMain(configuration);
		//
		// // done the verification phase
		// _monitor.worked(1);
		//
		// _monitor.subTask(
		// LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Creating_source_locator____2);
		// // set the default source locator if required
		// setDefaultSourceLocator(launch, configuration);
		// _monitor.worked(1);
		//
		// // Launch the configuration - 1 unit of work
		// runner.run(runConfig, launch, _monitor);
		//
		// // check for cancellation
		// if (_monitor.isCanceled()) {
		// return;
		// }
		// } finally {
		// _monitor.done();
		// }

		// final String mainTypeName = verifyMainTypeName(configuration);

		final IVMInstall vm = verifyVMInstall(configuration);
		final IVMRunner runner = vm.getVMRunner(mode);

		// Create VM config
		final VMRunnerConfiguration runConfig = new VMRunnerConfiguration("org.h2.tools.Server",
				new String[] { "h2-1.4.193.jar" });
		runConfig.setWorkingDirectory("/Users/andrewvojak/Downloads/h2/bin");

		// Bootpath
		final String[] bootpath = getBootpath(configuration);
		// runConfig.setBootClassPath(bootpath);

		// Launch the configuration
		runner.run(runConfig, launch, monitor);
	}

}
