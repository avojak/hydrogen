/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model;

/**
 * @author andrewvojak
 *
 */
@SuppressWarnings("nls")
public enum TcpServerOptions {

	/**
	 * Start the TCP server.
	 */
	TCP("-tcp"),

	/**
	 * The port (default: 9092).
	 */
	PORT("-tcpPort"),

	/**
	 * Use encrypted (SSL) connections.
	 */
	SSL("-tcpSSL"),

	/**
	 * Allow other computers to connect.
	 */
	ALLOW_OTHERS("-tcpAllowOthers"),

	/**
	 * Use a daemon thread.
	 */
	DAEMON("-tcpDaemon"),

	/**
	 * The password for shutting down a TCP server.
	 */
	PASSWORD("-tcpPassword"),

	/**
	 * Stop the TCP server; example: tcp://localhost.
	 */
	SHUTDOWN("-tcpShutdown"),

	/**
	 * Do not wait until all connections are closed.
	 */
	SHUTDOWN_FORCE("-tcpShutdownForce");

	private final String param;

	private TcpServerOptions(final String param) {
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
