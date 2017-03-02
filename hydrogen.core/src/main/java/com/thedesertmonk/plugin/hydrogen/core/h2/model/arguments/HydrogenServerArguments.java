package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * Models all server arguments to be provided at runtime.
 *
 * @author Andrew Vojak
 */
public class HydrogenServerArguments {

	private final List<String> arguments;

	/**
	 * Constructor.
	 * 
	 * @param arguments The {@link List} of arguments. Cannot be null.
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "HydrogenServerArguments [arguments=" + arguments + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
