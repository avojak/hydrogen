package com.avojak.plugin.hydrogen.core.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import com.avojak.plugin.hydrogen.core.factory.JarInputStreamFactory;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.avojak.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * Utility class to read the Manifest of a JAR file.
 * 
 * @author Andrew Vojak
 */
public class ManifestUtility {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory.getForClass(ManifestUtility.class);

	private static final String IMPLEMENTATION_VERSION = "Implementation-Version"; //$NON-NLS-1$

	private final JarInputStreamFactory jarInputStreamFactory;

	/**
	 * Constructor.
	 * 
	 * @param jarInputStreamFactory
	 *            The {@link JarInputStreamFactory}. Cannot be null.
	 */
	public ManifestUtility(final JarInputStreamFactory jarInputStreamFactory) {
		if (jarInputStreamFactory == null) {
			throw new IllegalArgumentException("jarInputStreamFactory cannot be null"); //$NON-NLS-1$
		}
		this.jarInputStreamFactory = jarInputStreamFactory;
	}

	/**
	 * Gets the version of the JAR at the specified location.
	 * 
	 * @param jarPath
	 *            The path to the JAR file. Cannot be null or empty.
	 * @return The Optional JAR version.
	 */
	public Optional<String> getVersion(final String jarPath) {
		if (jarPath == null || jarPath.trim().isEmpty()) {
			throw new IllegalArgumentException("jarPath cannot be null or empty"); //$NON-NLS-1$
		}
		try (final JarInputStream jarStream = jarInputStreamFactory.create(jarPath)) {
			final Manifest manifest = jarStream.getManifest();
			return Optional.ofNullable(manifest.getMainAttributes().getValue(IMPLEMENTATION_VERSION));
		} catch (final FileNotFoundException e) {
			LOGGER.error("JAR file [" + jarPath + "] not found", e); //$NON-NLS-1$ //$NON-NLS-2$
			return Optional.empty();
		} catch (final IOException e) {
			LOGGER.error("I/O error while creating stream for JAR file [" + jarPath + "]", e); //$NON-NLS-1$ //$NON-NLS-2$
			return Optional.empty();
		}
	}

}
