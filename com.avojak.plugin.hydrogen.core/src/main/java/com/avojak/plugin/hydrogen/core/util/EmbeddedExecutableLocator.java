package com.avojak.plugin.hydrogen.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.avojak.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * Utility class to locate the embedded H2 executable.
 * 
 * @author Andrew Vojak
 */
public class EmbeddedExecutableLocator {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory.getForClass(EmbeddedExecutableLocator.class);

	private static final String LIB_BUNDLE = "com.avojak.plugin.hydrogen.lib"; //$NON-NLS-1$
	private static final String LIB_DIRECTORY_BUNDLE_ENTRY = "lib/"; //$NON-NLS-1$
	private static final String H2_EXECUTABLE_REGEX = "h2-*.jar"; //$NON-NLS-1$

	/**
	 * Locates the embedded H2 executable.
	 * 
	 * @return The absolute file path of the embedded H2 executable.
	 * @throws FileNotFoundException
	 *             If the embedded H2 executable could not be found.
	 */
	public String locate() throws FileNotFoundException {
		final Bundle bundle = Platform.getBundle(LIB_BUNDLE);
		final URL libDirectoryEntry = bundle.getEntry(LIB_DIRECTORY_BUNDLE_ENTRY);
		final String libDirectory;
		try {
			final URL libDirectoryUrl = FileLocator.toFileURL(libDirectoryEntry);
			final URI resolvedUri = new URI(libDirectoryUrl.getProtocol(), libDirectoryUrl.getPath(), null);
			libDirectory = new File(resolvedUri).getAbsolutePath();
			LOGGER.debug("Resolved Hydrogen libraries directory [" + libDirectory + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (final IOException | URISyntaxException e) {
			LOGGER.error("Failed to locate the Hydrogen libraries bundle", e); //$NON-NLS-1$
			throw new FileNotFoundException("Failed to locate the embedded H2 executable"); //$NON-NLS-1$
		}
		final List<Path> matches = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(libDirectory),
				H2_EXECUTABLE_REGEX)) {
			directoryStream.forEach(new Consumer<Path>() {
				@Override
				public void accept(final Path path) {
					matches.add(path);
				}
			});
		} catch (final IOException e) {
			LOGGER.error("Failed to locate the bundled H2 executable", e); //$NON-NLS-1$
			throw new FileNotFoundException("Failed to locate the embedded H2 executable"); //$NON-NLS-1$
		}
		if (matches.size() == 0) {
			LOGGER.error("No H2 executable found in Hydrogen libraries bundle"); //$NON-NLS-1$
			throw new FileNotFoundException("Failed to locate the embedded H2 executable"); //$NON-NLS-1$
		}
		if (matches.size() > 1) {
			LOGGER.warn("Multiple H2 executables found [" + matches + "] - using first [" + matches.get(0) + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return matches.get(0).toAbsolutePath().normalize().toString();
	}

}
