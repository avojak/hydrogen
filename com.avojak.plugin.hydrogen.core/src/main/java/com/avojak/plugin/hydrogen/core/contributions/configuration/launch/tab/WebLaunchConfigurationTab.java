package com.avojak.plugin.hydrogen.core.contributions.configuration.launch.tab;

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

import com.avojak.plugin.hydrogen.core.HydrogenActivator;
import com.avojak.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.avojak.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * The launch configuration tab for the web server.
 *
 * @author Andrew Vojak
 */
public class WebLaunchConfigurationTab extends HydrogenLaunchConfigurationTab {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory.getForClass(WebLaunchConfigurationTab.class);

	private Composite baseComposite;
	private Button allowOthersButton;
	private Button useDaemonThreadButton;
	private Text portText;
	private Button useSslButton;
	private Button openBrowserButton;

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
		useDaemonThreadButton = createCheckButton(connectionSettingsGroup, "Use a &daemon thread"); //$NON-NLS-1$
		useDaemonThreadButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));
		portText = createField(connectionSettingsGroup, "Port"); //$NON-NLS-1$
		portText.addModifyListener(new HydrogenLaunchConfigurationTabChangeListener(this));
		useSslButton = createCheckButton(connectionSettingsGroup, "Use encrypted (HTTPS) connections"); //$NON-NLS-1$
		useSslButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));

		openBrowserButton = createCheckButton(baseComposite, "Open &browser"); //$NON-NLS-1$
		openBrowserButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));
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
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getName(), LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_DAEMON.getName(), LaunchConfigurationAttributes.WEB_DAEMON.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_PORT.getName(), LaunchConfigurationAttributes.WEB_PORT.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_SSL.getName(), LaunchConfigurationAttributes.WEB_SSL.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_BROWSER.getName(), LaunchConfigurationAttributes.WEB_BROWSER.getDefaultValue());
		//@formatter:on
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		boolean allowOthers = LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getDefaultValue();
		boolean useDaemonThread = LaunchConfigurationAttributes.WEB_DAEMON.getDefaultValue();
		String port = LaunchConfigurationAttributes.WEB_PORT.getDefaultValue();
		boolean useSsl = LaunchConfigurationAttributes.WEB_SSL.getDefaultValue();
		boolean openBrowser = LaunchConfigurationAttributes.WEB_BROWSER.getDefaultValue();

		try {
			//@formatter:off
			allowOthers = configuration.getAttribute(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getName(), LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getDefaultValue());
			useDaemonThread = configuration.getAttribute(LaunchConfigurationAttributes.WEB_DAEMON.getName(), LaunchConfigurationAttributes.WEB_DAEMON.getDefaultValue());
			port = configuration.getAttribute(LaunchConfigurationAttributes.WEB_PORT.getName(), LaunchConfigurationAttributes.WEB_PORT.getDefaultValue());
			useSsl = configuration.getAttribute(LaunchConfigurationAttributes.WEB_SSL.getName(), LaunchConfigurationAttributes.WEB_SSL.getDefaultValue());
			openBrowser = configuration.getAttribute(LaunchConfigurationAttributes.WEB_BROWSER.getName(), LaunchConfigurationAttributes.WEB_BROWSER.getDefaultValue());
			//@formatter:on
		} catch (final CoreException e) {
			LOGGER.error("Failed to initialize form", e); //$NON-NLS-1$
			return;
		}

		allowOthersButton.setSelection(allowOthers);
		useDaemonThreadButton.setSelection(useDaemonThread);
		portText.setText(port);
		useSslButton.setSelection(useSsl);
		openBrowserButton.setSelection(openBrowser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		//@formatter:off
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getName(), Boolean.valueOf(allowOthersButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_DAEMON.getName(), Boolean.valueOf(useDaemonThreadButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_PORT.getName(), portText.getText());
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_SSL.getName(), Boolean.valueOf(useSslButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.WEB_BROWSER.getName(), Boolean.valueOf(openBrowserButton.getSelection()));
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
		return "&Web"; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage() {
		return HydrogenActivator.getImageDescriptor("icons/globe.png").createImage(); //$NON-NLS-1$
	}

}
