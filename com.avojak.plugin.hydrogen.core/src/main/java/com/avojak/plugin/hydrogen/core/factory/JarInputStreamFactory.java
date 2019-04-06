package com.avojak.plugin.hydrogen.core.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.JarInputStream;

/**
 * Factory class to create instances of {@link JarInputStream} for facilitated
 * testing.
 * 
 * @author Andrew Vojak
 */
public class JarInputStreamFactory {

	/**
	 * Creates a new {@link JarInputStream}.
	 * 
	 * @param jarPath
	 *            The path to the JAR file.
	 * @return The non-null {@link JarInputStream}.
	 * @throws FileNotFoundException
	 *             If the given path refers to a non-existent file.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	public JarInputStream create(final String jarPath) throws FileNotFoundException, IOException {
		return new JarInputStream(new FileInputStream(jarPath));
	}

}
