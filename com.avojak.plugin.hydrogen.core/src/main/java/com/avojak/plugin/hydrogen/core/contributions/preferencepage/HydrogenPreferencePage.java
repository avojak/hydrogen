package com.avojak.plugin.hydrogen.core.contributions.preferencepage;

import java.io.File;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.avojak.plugin.hydrogen.core.HydrogenActivator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 *
 * @author Andrew Vojak
 */

public class HydrogenPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private final HydrogenPreferencePageValidator validator;
	private FileFieldEditor executableField;

	/**
	 * Constructor.
	 */
	public HydrogenPreferencePage() {
		super(GRID);
		validator = new HydrogenPreferencePageValidatorFactory().create();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createFieldEditors() {
		executableField = new FileFieldEditor(PreferenceConstants.P_EXECUTABLE, "&Executable location:", true, //$NON-NLS-1$
				StringButtonFieldEditor.VALIDATE_ON_KEY_STROKE, getFieldEditorParent());
		executableField.setFilterPath(new File(System.getProperty("user.home"))); //$NON-NLS-1$
		executableField.setFileExtensions(new String[] { "*.jar" }); //$NON-NLS-1$
		executableField.setEmptyStringAllowed(false);
		addField(executableField);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid() {
		if (!validator.isValuePresent(executableField)) {
			executableField.setErrorMessage("Location of the executable must be specified"); //$NON-NLS-1$
			return false;
		}

		if (!validator.validateExecutableFile(executableField)) {
			executableField.setErrorMessage("Specified location must be an executable file"); //$NON-NLS-1$
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final IWorkbench workbench) {
		setPreferenceStore(HydrogenActivator.getDefault().getPreferenceStore());
		setDescription("General Hydrogen settings:"); //$NON-NLS-1$
	}

}
