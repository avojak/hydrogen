package com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage;

import java.nio.file.FileSystem;
import java.nio.file.Path;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;

/**
 * Class to validate the field editors of a {@link HydrogenPreferencePage}.
 *
 * @author Andrew Vojak
 */
public class HydrogenPreferencePageValidator {

	private final FileSystem fileSystem;
	private final FileValidator fileValidator;

	/**
	 * Constructor.
	 *
	 * @param fileSystem The {@link FileSystem}. Cannot be null.
	 * @param fileValidator The {@link FileValidator}. Cannot be null.
	 */
	public HydrogenPreferencePageValidator(final FileSystem fileSystem, final FileValidator fileValidator) {
		if (fileSystem == null) {
			throw new IllegalArgumentException("fileSystem cannot be null"); //$NON-NLS-1$
		}
		if (fileValidator == null) {
			throw new IllegalArgumentException("fileValidator cannot be null"); //$NON-NLS-1$
		}
		this.fileSystem = fileSystem;
		this.fileValidator = fileValidator;
	}

	/**
	 * Returns whether or not the {@link StringFieldEditor} contains a non-null
	 * and non-empty {@code String} value.
	 *
	 * @param editor The {@link StringFieldEditor}. Cannot be null.
	 * @return {@code true} if the {@link StringFieldEditor} contains a
	 *         non-null, non-empty value, otherwise {@code false}.
	 */
	public boolean isValuePresent(final StringFieldEditor editor) {
		if (editor == null) {
			throw new IllegalArgumentException("editor cannot be null"); //$NON-NLS-1$
		}
		final String value = editor.getStringValue();
		return !(value == null || value.trim().isEmpty());
	}

	/**
	 * Returns whether or not the {@link FileFieldEditor} contains a value which
	 * refers to an executable file. If consumers wish to handle the case where
	 * the editor contains a {@code null} or empty value separately, then they
	 * should first invoke
	 * {@link HydrogenPreferencePageValidator#isValuePresent(StringFieldEditor)
	 * isValuePresent(FileFieldEditor)}.
	 *
	 * @param editor The {@link FileFieldEditor}. Cannot be null.
	 * @return {@code true} if the value of the {@link FileFieldEditor} refers
	 *         to a file which is both executable and not a directory, otherwise
	 *         {@code false}. Returns {@code false} if the
	 *         {@link FileFieldEditor} contains a {@code null} or empty value.
	 */
	public boolean validateExecutableFile(final FileFieldEditor editor) {
		if (editor == null) {
			throw new IllegalArgumentException("editor cannot be null"); //$NON-NLS-1$
		}

		final String value = editor.getStringValue();

		if (value == null || value.trim().isEmpty()) {
			return false;
		}

		final Path path = fileSystem.getPath(value);
		final boolean isDirectory = fileValidator.isDirectory(path);
		final boolean isExecutable = fileValidator.isExecutable(path);

		return (!isDirectory && isExecutable);
	}

}
