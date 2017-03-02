package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.thedesertmonk.plugin.hydrogen.core.HydrogenActivator;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;

/**
 * The launch configuration tab for the PostgreSQL server.
 *
 * @author Andrew Vojak
 */
public class PgLaunchConfigurationTab extends HydrogenLaunchConfigurationTab {

	private Composite baseComposite;
	private Button allowOthersButton;
	private Button useDaemonThreadButton;
	private Text portText;

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
		connectionSettingsGroup.setText("Connection Settings"); //$NON-NLS-1$

		allowOthersButton = createCheckButton(connectionSettingsGroup,
				"Allow other computers to connect (Discouraged)"); //$NON-NLS-1$
		allowOthersButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));
		useDaemonThreadButton = createCheckButton(connectionSettingsGroup, "Use a daemon thread"); //$NON-NLS-1$
		useDaemonThreadButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));
		portText = createField(connectionSettingsGroup, "Port"); //$NON-NLS-1$
		portText.addModifyListener(new HydrogenLaunchConfigurationTabChangeListener(this));
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
		//@formatter:off
		configuration.setAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getName(), LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.PG_DAEMON.getName(), LaunchConfigurationAttributes.PG_DAEMON.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.PG_PORT.getName(), LaunchConfigurationAttributes.PG_PORT.getDefaultValue());
		//@formatter:on
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		boolean allowOthers = LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getDefaultValue();
		boolean useDaemonThread = LaunchConfigurationAttributes.PG_DAEMON.getDefaultValue();
		String port = LaunchConfigurationAttributes.PG_PORT.getDefaultValue();

		try {
			//@formatter:off
			allowOthers = configuration.getAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getName(), LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getDefaultValue());
			useDaemonThread = configuration.getAttribute(LaunchConfigurationAttributes.PG_DAEMON.getName(), LaunchConfigurationAttributes.PG_DAEMON.getDefaultValue());
			port = configuration.getAttribute(LaunchConfigurationAttributes.PG_PORT.getName(), LaunchConfigurationAttributes.PG_PORT.getDefaultValue());
			//@formatter:on
		} catch (final CoreException e) {
			e.printStackTrace();
			return;
		}

		allowOthersButton.setSelection(allowOthers);
		useDaemonThreadButton.setSelection(useDaemonThread);
		portText.setText(port);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		//@formatter:off
		configuration.setAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getName(), Boolean.valueOf(allowOthersButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.PG_DAEMON.getName(), Boolean.valueOf(useDaemonThreadButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.PG_PORT.getName(), portText.getText());
		//@formatter:on
		setDirty(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		if (allowOthersButton.getSelection()) {
			showAllowOthersWarning();
		} else {
			clearWarningMessage();
		}

		final boolean isPortValid = validatePortNumber(portText.getText());

		if (!isPortValid) {
			showInvalidPortNumberError();
		} else {
			clearErrorMessage();
		}

		return isPortValid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canSave() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "PostgreSQL"; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage() {
		return HydrogenActivator.getImageDescriptor("icons/pg.png").createImage(); //$NON-NLS-1$
	}

}
