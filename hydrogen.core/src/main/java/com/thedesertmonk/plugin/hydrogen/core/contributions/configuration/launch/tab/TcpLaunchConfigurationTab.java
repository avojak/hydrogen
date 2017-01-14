/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

/**
 * @author andrewvojak
 *
 */
public class TcpLaunchConfigurationTab extends HydrogenLaunchConfigurationTab {

	private Composite baseComposite;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent) {
		baseComposite = new Composite(parent, SWT.NONE);
		baseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		baseComposite.setLayout(new GridLayout());

		final Group connectionSettingsGroup = new Group(baseComposite, SWT.NONE);
		connectionSettingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		connectionSettingsGroup.setLayout(new GridLayout());
		connectionSettingsGroup.setText("Connection Settings");

		createCheckButton(connectionSettingsGroup, "Allow other computers to connect");
		createCheckButton(connectionSettingsGroup, "Use a daemon thread");
		createField(connectionSettingsGroup, "Port");
		createCheckButton(connectionSettingsGroup, "Use encrypted (HTTPS) connections");

		final Group shutdownSettingsGroup = new Group(baseComposite, SWT.NONE);
		shutdownSettingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		shutdownSettingsGroup.setLayout(new GridLayout());
		shutdownSettingsGroup.setText("Shutdown Settings");

		createField(shutdownSettingsGroup, "Shutdown password");
		createField(shutdownSettingsGroup, "Shutdown URL");
		createCheckButton(shutdownSettingsGroup, "Force shutdown");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Control getControl() {
		return baseComposite;
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
	 */
	@Override
	public String getName() {
		return "TCP";
	}

}
