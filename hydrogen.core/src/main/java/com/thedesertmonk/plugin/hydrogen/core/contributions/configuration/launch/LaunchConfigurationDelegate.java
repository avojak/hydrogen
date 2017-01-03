/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

/**
 * @author andrewvojak
 *
 */
public class LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
	 *      eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 *      org.eclipse.debug.core.ILaunch,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
			final IProgressMonitor monitor) throws CoreException {
		// Only care about 'run' mode
		if (!mode.equals(ILaunchManager.RUN_MODE)) {
			return;
		}
	}

}
