/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class to create instances of {@link HydrogenServerArguments}.
 *
 * @author andrewvojak
 */
public class HydrogenServerArgumentsBuilder {

	private final List<String> arguments;

	/**
	 * Constructor.
	 */
	public HydrogenServerArgumentsBuilder() {
		arguments = new ArrayList<String>();
	}

	/**
	 * Adds web server arguments.
	 *
	 * @param webServerArguments The {@link WebServerArguments}. Cannot be null.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withWebServer(final WebServerArguments webServerArguments) {
		if (webServerArguments == null) {
			throw new IllegalArgumentException("webServerArguments cannot be null"); //$NON-NLS-1$
		}
		arguments.addAll(webServerArguments.getArguments());
		return this;
	}

	/**
	 * Adds TCP server arguments.
	 *
	 * @param tcpServerArguments The {@link TcpServerArguments}. Cannot be null.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withTcpServer(final TcpServerArguments tcpServerArguments) {
		if (tcpServerArguments == null) {
			throw new IllegalArgumentException("tcpServerArguments cannot be null"); //$NON-NLS-1$
		}
		arguments.addAll(tcpServerArguments.getArguments());
		return this;
	}

	/**
	 * Adds PostgreSQL server arguments.
	 *
	 * @param pgServerArguments The {@link PgServerArguments}. Cannot be null.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withTcpServer(final PgServerArguments pgServerArguments) {
		if (pgServerArguments == null) {
			throw new IllegalArgumentException("pgServerArguments cannot be null"); //$NON-NLS-1$
		}
		arguments.addAll(pgServerArguments.getArguments());
		return this;
	}

	/**
	 * Creates a new instance of {@link HydrogenServerArguments}.
	 *
	 * @return A new, non-null instance of {@link HydrogenServerArguments}.
	 */
	public HydrogenServerArguments build() {
		return new HydrogenServerArguments(arguments);
	}

}
