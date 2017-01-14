/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * @author andrewvojak
 *
 */
public class GeneralLaunchConfigurationTab extends HydrogenLaunchConfigurationTab {

	private Composite baseComposite;
	private Text baseDirectoryField;
	private Button existingDatabaseButton;
	private Button launchWebServerButton;
	private Button launchTcpServerButton;
	private Button launchPgServerButton;
	private Button tracingButton;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent) {
		baseComposite = new Composite(parent, SWT.NONE);
		baseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		baseComposite.setLayout(new GridLayout());

		// General properties

		final Group databaseSettingsGroup = new Group(baseComposite, SWT.NONE);
		databaseSettingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		databaseSettingsGroup.setLayout(new GridLayout());
		databaseSettingsGroup.setText("Database Settings");

		baseDirectoryField = createDirectoryField(databaseSettingsGroup, "Database base directory");
		existingDatabaseButton = createCheckButton(databaseSettingsGroup, "Only open existing databases");

		final Group serversToLaunchGroup = new Group(baseComposite, SWT.NONE);
		serversToLaunchGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		serversToLaunchGroup.setLayout(new GridLayout());
		serversToLaunchGroup.setText("Servers to launch");

		launchWebServerButton = createCheckButton(serversToLaunchGroup, "Web");
		launchTcpServerButton = createCheckButton(serversToLaunchGroup, "TCP");
		launchPgServerButton = createCheckButton(serversToLaunchGroup, "PostgreSQL");

		tracingButton = createCheckButton(baseComposite, "Enable tracing");
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
		configuration.setAttribute(LaunchConfigurationAttributes.BASE_DIRECTORY.getName(), LaunchConfigurationAttributes.BASE_DIRECTORY.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(), LaunchConfigurationAttributes.IF_EXISTS.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.START_WEB.getName(), LaunchConfigurationAttributes.START_WEB.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.START_TCP.getName(), LaunchConfigurationAttributes.START_TCP.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.START_PG.getName(), LaunchConfigurationAttributes.START_PG.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.ENABLE_TRACING.getName(), LaunchConfigurationAttributes.ENABLE_TRACING.getDefaultValue());
		//@formatter:on
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		String baseDirectory = LaunchConfigurationAttributes.BASE_DIRECTORY.getDefaultValue();
		boolean ifExists = LaunchConfigurationAttributes.IF_EXISTS.getDefaultValue().booleanValue();
		boolean startWeb = LaunchConfigurationAttributes.START_WEB.getDefaultValue().booleanValue();
		boolean startTcp = LaunchConfigurationAttributes.START_TCP.getDefaultValue().booleanValue();
		boolean startPg = LaunchConfigurationAttributes.START_PG.getDefaultValue().booleanValue();
		boolean enableTracing = LaunchConfigurationAttributes.ENABLE_TRACING.getDefaultValue().booleanValue();

		try {
			//@formatter:off
			baseDirectory = configuration.getAttribute(LaunchConfigurationAttributes.BASE_DIRECTORY.getName(), LaunchConfigurationAttributes.BASE_DIRECTORY.getDefaultValue());
			ifExists = configuration.getAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(), LaunchConfigurationAttributes.IF_EXISTS.getDefaultValue());
			startWeb = configuration.getAttribute(LaunchConfigurationAttributes.START_WEB.getName(), LaunchConfigurationAttributes.START_WEB.getDefaultValue());
			startTcp = configuration.getAttribute(LaunchConfigurationAttributes.START_TCP.getName(), LaunchConfigurationAttributes.START_TCP.getDefaultValue());
			startPg = configuration.getAttribute(LaunchConfigurationAttributes.START_PG.getName(), LaunchConfigurationAttributes.START_PG.getDefaultValue());
			enableTracing = configuration.getAttribute(LaunchConfigurationAttributes.ENABLE_TRACING.getName(), LaunchConfigurationAttributes.ENABLE_TRACING.getDefaultValue());
			//@formatter:on
		} catch (final CoreException e) {
			e.printStackTrace();
			return;
		}

		baseDirectoryField.setText(baseDirectory);
		existingDatabaseButton.setSelection(ifExists);
		launchWebServerButton.setSelection(startWeb);
		launchTcpServerButton.setSelection(startTcp);
		launchPgServerButton.setSelection(startPg);
		tracingButton.setSelection(enableTracing);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		baseComposite.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		//@formatter:off
		configuration.setAttribute(LaunchConfigurationAttributes.BASE_DIRECTORY.getName(), baseDirectoryField.getText());
		configuration.setAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(), Boolean.valueOf(existingDatabaseButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.START_WEB.getName(), Boolean.valueOf(launchWebServerButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.START_TCP.getName(), Boolean.valueOf(launchTcpServerButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.START_PG.getName(), Boolean.valueOf(launchPgServerButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.ENABLE_TRACING.getName(), Boolean.valueOf(tracingButton.getSelection()));
		//@formatter:on
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLaunchConfigurationDialog(final ILaunchConfigurationDialog dialog) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "General";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activated(final ILaunchConfigurationWorkingCopy workingCopy) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivated(final ILaunchConfigurationWorkingCopy workingCopy) {
		// TODO Auto-generated method stub

	}

}
