package com.avojak.plugin.hydrogen.core.contributions.preferencepage;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.avojak.plugin.hydrogen.core.HydrogenActivator;
import com.avojak.plugin.hydrogen.core.factory.JarInputStreamFactory;
import com.avojak.plugin.hydrogen.core.util.EmbeddedExecutableLocator;
import com.avojak.plugin.hydrogen.core.util.ManifestUtility;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing PreferencePage, we can use the field
 * support built into JFace that allows us to create a page that is small and
 * knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 *
 * @author Andrew Vojak
 */

public class HydrogenPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private final HydrogenPreferencePageValidator validator;
	private final EmbeddedExecutableLocator embeddedExecutableLocator;
	private final ManifestUtility manifestUtility;

	private Button externalButton;
	private Button embeddedButton;
	private Text externalFileText;
	private Button browseButton;

	private Optional<String> embeddedFileVersion;
	private String externalFileLocation;
	private boolean runExternal;

	/**
	 * Constructor.
	 */
	public HydrogenPreferencePage() {
		super();
		validator = new HydrogenPreferencePageValidatorFactory().create();
		embeddedExecutableLocator = new EmbeddedExecutableLocator();
		manifestUtility = new ManifestUtility(new JarInputStreamFactory());
	}

	@Override
	public Control createContents(final Composite parent) {
		Composite composite = createComposite(parent);
		createExecutableSelectionGroup(composite);
		applyDialogFont(composite);
		return composite;
	}

	protected Composite createComposite(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		return composite;
	}

	protected void createExecutableSelectionGroup(final Composite parent) {
		final Group baseComposite = new Group(parent, SWT.LEFT);
		baseComposite.setLayout(new GridLayout());
		baseComposite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		baseComposite.setText("H2 Executable"); //$NON-NLS-1$

		embeddedButton = createRadioButton(baseComposite, "E&mbedded"); //$NON-NLS-1$
		embeddedButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void widgetSelected(final SelectionEvent e) {
				selectExternalMode(externalButton.getSelection());
			}
		});
		if (embeddedFileVersion.isPresent()) {
			embeddedButton.setText(embeddedButton.getText() + " (v" + embeddedFileVersion.get() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		embeddedButton.setSelection(!runExternal);

		externalButton = createRadioButton(baseComposite, "E&xternal"); //$NON-NLS-1$
		externalButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void widgetSelected(final SelectionEvent e) {
				selectExternalMode(externalButton.getSelection());
			}
		});
		externalButton.setSelection(runExternal);

		final Composite externalFileComposite = new Composite(baseComposite, SWT.NONE);
		final GridLayout externalFileLayout = new GridLayout(2, false);
		externalFileLayout.marginWidth = 0;
		externalFileLayout.marginHeight = 0;
		externalFileComposite.setLayout(externalFileLayout);
		final GridData externalFileData = new GridData(
				GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_HORIZONTAL);
		externalFileData.horizontalIndent = LayoutConstants.getIndent();
		externalFileComposite.setLayoutData(externalFileData);

		externalFileText = new Text(externalFileComposite, SWT.SINGLE | SWT.BORDER);
		externalFileText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		externalFileText.setText(externalFileLocation);
		externalFileText.setEnabled(runExternal);
		externalFileText.addModifyListener(new ModifyListener() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void modifyText(final ModifyEvent e) {
				externalFileLocation = externalFileText.getText();
				updateValidity();
			}
		});

		browseButton = new Button(externalFileComposite, SWT.PUSH);
		browseButton.setText("&Browse..."); //$NON-NLS-1$
		final GridData browseGridData = new GridData();
		browseGridData.widthHint = IDialogConstants.BUTTON_WIDTH;
		browseButton.setLayoutData(browseGridData);
		browseButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
				dialog.setText("Open"); //$NON-NLS-1$
				dialog.setFilterPath(System.getProperty("user.home")); //$NON-NLS-1$
				dialog.setFilterExtensions(new String[] { "*.jar" }); //$NON-NLS-1$
				final String path = dialog.open();
				if (path == null) {
					return;
				}
				externalFileText.setText(path);
				updateValidity();
			}
		});
		browseButton.setEnabled(runExternal);
	}

	private static Button createRadioButton(final Composite parent, final String label) {
		Button button = new Button(parent, SWT.RADIO | SWT.LEFT);
		button.setText(label);
		return button;
	}

	private void selectExternalMode(final boolean external) {
		runExternal = external;
		externalFileText.setEnabled(runExternal);
		browseButton.setEnabled(runExternal);
		updateValidity();
	}

	private void updateValidity() {
		if (runExternal) {
			if (externalFileLocation == null || externalFileLocation.trim().isEmpty()) {
				setValid(false);
				setErrorMessage("Location of the executable must be specified"); //$NON-NLS-1$
			} else if (!validator.validateExecutableFile(externalFileLocation)) {
				setValid(false);
				setErrorMessage("Specified location must be an executable file"); //$NON-NLS-1$
			} else {
				setValid(true);
				setErrorMessage(null);
			}
		} else {
			setValid(true);
			setErrorMessage(null);
		}
	}

	@Override
	protected void performDefaults() {
		final IPreferenceStore preferenceStore = getPreferenceStore();
		runExternal = preferenceStore.getDefaultBoolean(PreferenceConstants.P_RUN_EXTERNAL);
		externalFileLocation = preferenceStore.getDefaultString(PreferenceConstants.P_EXT_EXE_PATH);
		externalFileText.setEnabled(runExternal);
		browseButton.setEnabled(runExternal);
		embeddedButton.setSelection(!runExternal);
		externalButton.setSelection(runExternal);
		externalFileText.setText(externalFileLocation);

		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		final IPreferenceStore preferenceStore = getPreferenceStore();
		preferenceStore.setValue(PreferenceConstants.P_RUN_EXTERNAL, runExternal);
		preferenceStore.setValue(PreferenceConstants.P_EXT_EXE_PATH, externalFileLocation);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final IWorkbench workbench) {
		final IPreferenceStore preferenceStore = getPreferenceStore();
		runExternal = preferenceStore.getBoolean(PreferenceConstants.P_RUN_EXTERNAL);
		externalFileLocation = preferenceStore.getString(PreferenceConstants.P_EXT_EXE_PATH);
		try {
			embeddedFileVersion = manifestUtility.getVersion(embeddedExecutableLocator.locate());
		} catch (FileNotFoundException e) {
			embeddedFileVersion = Optional.empty();
		}
		setDescription("General Hydrogen settings:"); //$NON-NLS-1$
	}

	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return HydrogenActivator.getDefault().getPreferenceStore();
	}

}
