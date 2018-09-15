package com.avojak.plugin.hydrogen.core.contributions.preferencepage;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class to wrap {@link Files} methods to allow for facilitated testing.
 *
 * @author Andrew Vojak
 */
public class FileValidator {

	/**
	 * Returns whether or not the given {@link Path} refers to a directory.
	 *
	 * @param path The {@link Path}. Cannot be null.
	 * @return {@code true} if the {@Path} refers to a directory, otherwise
	 *         {@code false}.
	 */
	public boolean isDirectory(final Path path) {
		if (path == null) {
			throw new IllegalArgumentException("path cannot be null"); //$NON-NLS-1$
		}
		return Files.isDirectory(path);
	}

	/**
	 * Returns whether or not the given {@link Path} refers to a file which is
	 * executable.
	 *
	 * @param path The {@link Path}. Cannot be null.
	 * @return {@code true} if the {@Path} refers to a file which is executable,
	 *         otherwise {@code false}.
	 */
	public boolean isExecutable(final Path path) {
		if (path == null) {
			throw new IllegalArgumentException("path cannot be null"); //$NON-NLS-1$
		}
		return Files.isExecutable(path);
	}

}
