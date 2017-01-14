/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model;

/**
 * @author andrewvojak
 *
 */
@SuppressWarnings("nls")
public enum WebServerOptions {

	/**
	 * Start the web server with the H2 Console.
	 */
	WEB("-web"),

	/**
	 * Allow other computers to connect.
	 */
	ALLOW_OTHERS("-webAllowOthers"),

	/**
	 * Use a daemon thread.
	 */
	DAEMON("-webDaemon"),

	/**
	 * The port (default: 8082).
	 */
	PORT("-webPort"),

	/**
	 * Use encrypted (HTTPS) connections.
	 */
	SSL("-webSSL"),

	/**
	 * Start a browser connecting to the web server.
	 */
	BROWSER("-browser");

	private final String param;

	private WebServerOptions(final String param) {
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
