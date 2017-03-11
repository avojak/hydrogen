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
		if (arguments == null || arguments.isEmpty()) {
			throw new IllegalArgumentException("arguments cannot be null or empty"); //$NON-NLS-1$
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "PgServerArguments [arguments=" + arguments + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + arguments.hashCode();
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PgServerArguments other = (PgServerArguments) obj;
		if (!arguments.equals(other.arguments)) {
			return false;
		}
		return true;
	}

}
