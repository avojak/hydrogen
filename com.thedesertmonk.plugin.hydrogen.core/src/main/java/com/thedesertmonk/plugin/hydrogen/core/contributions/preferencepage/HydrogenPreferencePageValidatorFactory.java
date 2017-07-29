package com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage;

import java.nio.file.FileSystems;

/**
 * Factory class to create instances of {@link HydrogenPreferencePageValidator}.
 *
 * @author Andrew Vojak
 */
public class HydrogenPreferencePageValidatorFactory {

	/**
	 * Creates a new instance of {@link HydrogenPreferencePageValidator}.
	 *
	 * @return The non-null {@link HydrogenPreferencePageValidator}.
	 */
	public HydrogenPreferencePageValidator create() {
		return new HydrogenPreferencePageValidator(FileSystems.getDefault(), new FileValidator());
	}

}
