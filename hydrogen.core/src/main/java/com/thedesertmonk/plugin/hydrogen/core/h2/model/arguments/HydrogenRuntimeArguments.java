package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * Models all server arguments to be provided at runtime.
 *
 * @author Andrew Vojak
 */
public class HydrogenRuntimeArguments {

	private final List<String> arguments;

	/**
	 * Constructor.
	 *
	 * @param arguments The {@link List} of arguments. Cannot be null.
	 */
	public HydrogenRuntimeArguments(final List<String> arguments) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "HydrogenServerArguments [arguments=" + arguments + "]"; //$NON-NLS-1$ //$NON-NLS-2$
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
		final HydrogenRuntimeArguments other = (HydrogenRuntimeArguments) obj;
		if (!arguments.equals(other.arguments)) {
			return false;
		}
		return true;
	}

}
