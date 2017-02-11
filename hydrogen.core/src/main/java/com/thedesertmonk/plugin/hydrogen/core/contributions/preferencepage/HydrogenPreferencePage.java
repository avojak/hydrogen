package com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.thedesertmonk.plugin.hydrogen.core.HydrogenActivator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class HydrogenPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private FileFieldEditor executableField;

	/**
	 * Constructor
	 */
	public HydrogenPreferencePage() {
		super(GRID);
		setPreferenceStore(HydrogenActivator.getDefault().getPreferenceStore());
		setDescription("General Hydrogen settings:");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createFieldEditors() {
		executableField = new FileFieldEditor(PreferenceConstants.P_EXECUTABLE, "&Executable location:", true,
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
		final String executablePreference = executableField.getStringValue();

		if (executablePreference == null || executablePreference.trim().isEmpty()) {
			executableField.setErrorMessage("Location of the executable must be specified");
			return false;
		}

		final Path executablePath = FileSystems.getDefault().getPath(executablePreference);
		final boolean isDirectory = Files.isDirectory(executablePath);
		final boolean isExecutable = Files.isExecutable(executablePath);

		final boolean isValid = (!isDirectory && isExecutable);
		if (!isValid) {
			executableField.setErrorMessage("Specified location must be an executable file");
		}

		return isValid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final IWorkbench workbench) {
	}

}