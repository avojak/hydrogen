package com.avojak.plugin.hydrogen.core.contributions.preferencepage;

import java.nio.file.FileSystem;
import java.nio.file.Path;

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
	 * @param fileSystem
	 *            The {@link FileSystem}. Cannot be null.
	 * @param fileValidator
	 *            The {@link FileValidator}. Cannot be null.
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
	 * Validates the executable file at the given location.
	 * 
	 * @param executableFileLocation
	 *            The executable location.
	 * @return Whether or not the specified file location refers to a valid
	 *         executable.
	 */
	public boolean validateExecutableFile(final String executableFileLocation) {
		if (executableFileLocation == null || executableFileLocation.trim().isEmpty()) {
			return false;
		}
		final Path path = fileSystem.getPath(executableFileLocation);
		final boolean isDirectory = fileValidator.isDirectory(path);
		final boolean isExecutable = fileValidator.isExecutable(path);

		return (!isDirectory && isExecutable);
	}

}
