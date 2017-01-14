/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * Models the set of web server arguments.
 *
 * @author andrewvojak
 */
public class WebServerArguments {

	private final List<String> arguments;

	/**
	 * Constructor.
	 *
	 * @param arguments The {@link List} of argument {@code String}s. Cannot be
	 *            null or empty.
	 */
	public WebServerArguments(final List<String> arguments) {
		if (arguments == null || arguments.isEmpty()) {
			throw new IllegalArgumentException("arguments cannot be null or empty"); //$NON-NLS-1$
		}
		this.arguments = arguments;
	}

	/**
	 * Gets the {@link List} of arguments.
	 *
	 * @return The non-null, non-empty {@link List} of web server arguments.
	 */
	public List<String> getArguments() {
		return new ArrayList<String>(arguments);
	}

}
