/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model;

/**
 * @author andrewvojak
 *
 */
@SuppressWarnings("nls")
public enum PgServerOptions {

	/**
	 * Start the PG server.
	 */
	PG("-pg"),

	/**
	 * Allow other computers to connect.
	 */
	ALLOW_OTHERS("-pgAllowOthers"),

	/**
	 * Use a daemon thread.
	 */
	DAEMON("-pgDaemon"),

	/**
	 * The port (default: 5435).
	 */
	PORT("-pgPort");

	private final String param;

	private PgServerOptions(final String param) {
		this.param = param;
	}

	/**
	 * Gets the command line parameter.
	 *
	 * @return The non-null, non-empty command line parameter {@code String}.
	 */
	public String getParam() {
		return param;
	}

}
