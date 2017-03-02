package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Builder class to create instances of {@link PgServerArguments}.
 * 
 * @author Andrew Vojak
 */
public class PgServerArgumentsBuilder {

	private final List<String> arguments;

	/**
	 * Constructor.
	 */
	public PgServerArgumentsBuilder() {
		arguments = new ArrayList<String>();
		arguments.add(ServerOption.START_PG.getParam());
	}

	/**
	 * Sets the {@link ServerOption#PG_ALLOW_OTHERS} property.
	 *
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder allowOthers() {
		arguments.add(ServerOption.PG_ALLOW_OTHERS.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#PG_DAEMON} property.
	 *
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder useDaemonThread() {
		arguments.add(ServerOption.PG_DAEMON.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#PG_PORT} property.
	 *
	 * @param port The port number. Cannot be null or empty.
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder withPort(final String port) {
		if (port == null || port.trim().isEmpty()) {
			throw new IllegalArgumentException("port cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(ServerOption.PG_PORT.getParam());
		arguments.add(port);
		return this;
	}

	/**
	 * Creates a new instance of {@link PgServerArguments}.
	 *
	 * @return A new, non-null instance of {@link PgServerArguments}.
	 */
	public PgServerArguments build() {
		return new PgServerArguments(arguments);
	}

}
