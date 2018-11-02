package com.avojak.plugin.hydrogen.core.factory;

import java.io.File;

/**
 * Factory class to create instances of {@link File}.
 */
public class FileFactory {

	/**
	 * Creates a new file for the given pathname.
	 *
	 * @param pathname
	 *            The pathname of the file to create.
	 *
	 * @return The new, non-{@code null} {@link File}.
	 */
	public File create(final String pathname) {
		return new File(pathname);
	}

}
