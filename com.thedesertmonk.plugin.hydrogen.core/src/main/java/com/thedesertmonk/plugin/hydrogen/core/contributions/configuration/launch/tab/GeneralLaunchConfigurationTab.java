package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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
import com.thedesertmonk.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.thedesertmonk.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * Launch configuration tab for general configuration settings.
 *
 * @author Andrew Vojak
 */
public class GeneralLaunchConfigurationTab extends HydrogenLaunchConfigurationTab {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory
			.getForClass(GeneralLaunchConfigurationTab.class);

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

		final Group databaseSettingsGroup = new Group(baseComposite, SWT.NONE);
		databaseSettingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		databaseSettingsGroup.setLayout(new GridLayout());
		databaseSettingsGroup.setText("Database Settings"); //$NON-NLS-1$

		baseDirectoryField = createDirectoryField(databaseSettingsGroup, "Database base directory"); //$NON-NLS-1$
		baseDirectoryField.addModifyListener(new HydrogenLaunchConfigurationTabChangeListener(this));
		existingDatabaseButton = createCheckButton(databaseSettingsGroup, "Only open existing databases"); //$NON-NLS-1$
		existingDatabaseButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));

		final Group serversToLaunchGroup = new Group(baseComposite, SWT.NONE);
		serversToLaunchGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		serversToLaunchGroup.setLayout(new GridLayout());
		serversToLaunchGroup.setText("Servers to launch"); //$NON-NLS-1$

		launchWebServerButton = createCheckButton(serversToLaunchGroup, "Web"); //$NON-NLS-1$
		launchWebServerButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));
		launchTcpServerButton = createCheckButton(serversToLaunchGroup, "TCP"); //$NON-NLS-1$
		launchTcpServerButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));
		launchPgServerButton = createCheckButton(serversToLaunchGroup, "PostgreSQL"); //$NON-NLS-1$
		launchPgServerButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));

		tracingButton = createCheckButton(baseComposite, "Enable tracing"); //$NON-NLS-1$
		tracingButton.addSelectionListener(new HydrogenLaunchConfigurationTabChangeListener(this));
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
			LOGGER.error("Failed to initialize form", e); //$NON-NLS-1$
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
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		//@formatter:off
		configuration.setAttribute(LaunchConfigurationAttributes.BASE_DIRECTORY.getName(), baseDirectoryField.getText());
		configuration.setAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(), Boolean.valueOf(existingDatabaseButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.START_WEB.getName(), Boolean.valueOf(launchWebServerButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.START_TCP.getName(), Boolean.valueOf(launchTcpServerButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.START_PG.getName(), Boolean.valueOf(launchPgServerButton.getSelection()));
		configuration.setAttribute(LaunchConfigurationAttributes.ENABLE_TRACING.getName(), Boolean.valueOf(tracingButton.getSelection()));
		//@formatter:on
		setDirty(false);
	}

	/**
	 * {@inheritDoc} TODO This is bad and needs fixing...
	 */
	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		String baseDirectory = null;
		try {
			baseDirectory = launchConfig.getAttribute(LaunchConfigurationAttributes.BASE_DIRECTORY.getName(),
					(String) null);
		} catch (final CoreException e) {
			LOGGER.error("Error retrieving BASE_DIRECTORY launch configuration attribute", e); //$NON-NLS-1$
		} finally {
			// If null, there was either an error reading the attribute, or no
			// attribute was specified in the config. Either way, this is an
			// invalid state.
			if (baseDirectory == null) {
				return false;
			}
		}

		// Verify that the specified base directory is in fact a directory and
		// that we will have permissions to read and write there.
		final Path baseDirectoryPath = FileSystems.getDefault().getPath(baseDirectory);
		final boolean isDirectory = Files.isDirectory(baseDirectoryPath);
		if (!isDirectory) {
			setErrorMessage("Database base directory must be a directory"); //$NON-NLS-1$
			return false;
		}
		final boolean isReadable = Files.isReadable(baseDirectoryPath);
		final boolean isWritable = Files.isWritable(baseDirectoryPath);
		if (!isReadable || !isWritable) {
			setErrorMessage("Verify directory privileges allow read and write access"); //$NON-NLS-1$
			return false;
		}

		final boolean isValid = isDirectory && isReadable && isWritable;

		// If valid, clear any error messages
		if (isValid) {
			setErrorMessage(null);
		}

		return isValid;
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
		return "General"; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage() {
		return HydrogenActivator.getImageDescriptor("icons/main_tab.png").createImage(); //$NON-NLS-1$
	}

}
