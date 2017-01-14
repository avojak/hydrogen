/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrewvojak
 *
 */
public class HydrogenServerArguments {

	private final List<String> arguments;

	/**
	 * @param arguments
	 */
	public HydrogenServerArguments(final List<String> arguments) {
		if (arguments == null) {
			throw new IllegalArgumentException("arguments cannot be null"); //$NON-NLS-1$
		}
		this.arguments = arguments;
	}

	/**
	 * Gets the {@link List} of arguments.
	 *
	 * @return The non-null, non-empty {@link List} of server arguments.
	 */
	public List<String> getArguments() {
		return new ArrayList<String>(arguments);
	}

}
