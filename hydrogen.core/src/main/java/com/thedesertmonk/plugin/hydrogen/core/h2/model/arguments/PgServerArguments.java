package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * Models the PostgreSQL server arguments.
 * 
 * @author Andrew Vojak
 */
public class PgServerArguments {

	private final List<String> arguments;

	/**
	 * Constructor.
	 *
	 * @param arguments The {@link List} of argument {@code String}s. Cannot be
	 *            null or empty.
	 */
	public PgServerArguments(final List<String> arguments) {
		if (arguments == null) {
			throw new IllegalArgumentException("arguments cannot be null"); //$NON-NLS-1$
		}
		this.arguments = arguments;
	}

	/**
	 * Gets the {@link List} of arguments.
	 *
	 * @return The non-null, non-empty {@link List} of PostgreSQL server
	 *         arguments.
	 */
	public List<String> getArguments() {
		return new ArrayList<String>(arguments);
	}

}
