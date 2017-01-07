/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTabGroup;

/**
 * @author andrewvojak
 *
 */
public class LaunchConfigurationTabGroup implements ILaunchConfigurationTabGroup {

	private final List<ILaunchConfigurationTab> tabs;

	/**
	 *
	 */
	public LaunchConfigurationTabGroup() {
		tabs = new ArrayList<ILaunchConfigurationTab>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
		tabs.add(new LaunchConfigurationTab());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ILaunchConfigurationTab[] getTabs() {
		return tabs.toArray(new ILaunchConfigurationTab[tabs.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void launched(final ILaunch launch) {
		// TODO Auto-generated method stub

	}

}
