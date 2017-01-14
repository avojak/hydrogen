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
		configuration.setAttribute(StringLaunchConfigurationAttribute.BASE_DIRECTORY.name(), StringLaunchConfigurationAttribute.BASE_DIRECTORY.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(), LaunchConfigurationAttributes.IF_EXISTS.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.START_WEB.getName(), LaunchConfigurationAttributes.START_WEB.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.START_TCP.getName(), LaunchConfigurationAttributes.START_TCP.getDefaultValue());
		configuration.setAttribute(LaunchConfigurationAttributes.START_PG.getName(), LaunchConfigurationAttributes.START_PG.getDefaultValue());
		//@formatter:on
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		try {
			//@formatter:off
			configuration.getAttribute(StringLaunchConfigurationAttribute.BASE_DIRECTORY.name(), StringLaunchConfigurationAttribute.BASE_DIRECTORY.getDefaultValue());
			configuration.getAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(), LaunchConfigurationAttributes.IF_EXISTS.getDefaultValue());
			configuration.getAttribute(LaunchConfigurationAttributes.START_WEB.getName(), LaunchConfigurationAttributes.START_WEB.getDefaultValue());
			configuration.getAttribute(LaunchConfigurationAttributes.START_TCP.getName(), LaunchConfigurationAttributes.START_TCP.getDefaultValue());
			configuration.getAttribute(LaunchConfigurationAttributes.START_PG.getName(), LaunchConfigurationAttributes.START_PG.getDefaultValue());
			//@formatter:on
		} catch (final CoreException e) {
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub
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
