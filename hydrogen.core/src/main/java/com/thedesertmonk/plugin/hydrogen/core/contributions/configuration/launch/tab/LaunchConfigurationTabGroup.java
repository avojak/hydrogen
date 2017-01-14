/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

/**
 * @author andrewvojak
 *
 */
public class LaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
		//@formatter:off
		setTabs(new ILaunchConfigurationTab[] {
				new GeneralLaunchConfigurationTab(),
				new WebLaunchConfigurationTab(),
				new TcpLaunchConfigurationTab(),
				new PgLaunchConfigurationTab(),
				new CommonTab()
		});
		//@formatter:on
	}

}
