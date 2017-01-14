/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.PgServerOptions;

/**
 * @author andrewvojak
 *
 */
public class PgServerArgumentsBuilder {

	private final List<String> arguments;

	/**
	 * Constructor.
	 */
	public PgServerArgumentsBuilder() {
		arguments = new ArrayList<String>();
		arguments.add(PgServerOptions.PG.getParam());
	}

	/**
	 * Sets the {@link PgServerOptions#ALLOW_OTHERS} property.
	 *
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder allowOthers() {
		arguments.add(PgServerOptions.ALLOW_OTHERS.getParam());
		return this;
	}

	/**
	 * Sets the {@link PgServerOptions#DAEMON} property.
	 *
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder useDaemonThread() {
		arguments.add(PgServerOptions.DAEMON.getParam());
		return this;
	}

	/**
	 * Sets the {@link PgServerOptions#PORT} property.
	 *
	 * @param port The port number. Cannot be null or empty.
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder withPort(final String port) {
		if (port == null || port.trim().isEmpty()) {
			throw new IllegalArgumentException("port cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(PgServerOptions.PORT.getParam());
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
