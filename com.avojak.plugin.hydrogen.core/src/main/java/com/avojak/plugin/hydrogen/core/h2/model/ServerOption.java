package com.avojak.plugin.hydrogen.core.h2.model;

/**
 * Models available server options.
 *
 * @author Andrew Vojak
 */
public enum ServerOption {

	/**
	 * Allows to map a database name to another.
	 */
	KEY("-key", Type.GENERAL), //$NON-NLS-1$

	/**
	 * Print additional trace information.
	 */
	ENABLE_TRACING("-trace", Type.GENERAL), //$NON-NLS-1$

	/**
	 * Only existing databases may be opened.
	 */
	IF_EXISTS("-ifExists", Type.GENERAL), //$NON-NLS-1$

	/**
	 * The base directory for H2 databases.
	 */
	BASE_DIRECTORY("-baseDir", Type.GENERAL), //$NON-NLS-1$

	/**
	 * Server properties.
	 */
	PROPERTIES("-properties", Type.GENERAL), //$NON-NLS-1$

	/**
	 * Start the web server with the H2 Console.
	 */
	START_WEB("-web", Type.GENERAL), //$NON-NLS-1$

	/**
	 * Start the TCP server.
	 */
	START_TCP("-tcp", Type.GENERAL), //$NON-NLS-1$

	/**
	 * Start the PG server.
	 */
	START_PG("-pg", Type.GENERAL), //$NON-NLS-1$

	/**
	 * Allow other computers to connect.
	 */
	WEB_ALLOW_OTHERS("-webAllowOthers", Type.WEB), //$NON-NLS-1$

	/**
	 * Use a daemon thread.
	 */
	WEB_DAEMON("-webDaemon", Type.WEB), //$NON-NLS-1$

	/**
	 * The port (default: 8082).
	 */
	WEB_PORT("-webPort", Type.WEB), //$NON-NLS-1$

	/**
	 * Use encrypted (HTTPS) connections.
	 */
	WEB_SSL("-webSSL", Type.WEB), //$NON-NLS-1$

	/**
	 * Start a browser connecting to the web server.
	 */
	WEB_BROWSER("-browser", Type.WEB), //$NON-NLS-1$

	/**
	 * The port (default: 9092).
	 */
	TCP_PORT("-tcpPort", Type.TCP), //$NON-NLS-1$

	/**
	 * Use encrypted (SSL) connections.
	 */
	TCP_SSL("-tcpSSL", Type.TCP), //$NON-NLS-1$

	/**
	 * Allow other computers to connect.
	 */
	TCP_ALLOW_OTHERS("-tcpAllowOthers", Type.TCP), //$NON-NLS-1$

	/**
	 * Use a daemon thread.
	 */
	TCP_DAEMON("-tcpDaemon", Type.TCP), //$NON-NLS-1$

	/**
	 * The password for shutting down a TCP server.
	 */
	TCP_SHUTDOWN_PASSWORD("-tcpPassword", Type.TCP), //$NON-NLS-1$

	/**
	 * Stop the TCP server; example: tcp://localhost.
	 */
	TCP_SHUTDOWN_URL("-tcpShutdown", Type.TCP), //$NON-NLS-1$

	/**
	 * Do not wait until all connections are closed.
	 */
	TCP_SHUTDOWN_FORCE("-tcpShutdownForce", Type.TCP), //$NON-NLS-1$

	/**
	 * Allow other computers to connect.
	 */
	PG_ALLOW_OTHERS("-pgAllowOthers", Type.PG), //$NON-NLS-1$

	/**
	 * Use a daemon thread.
	 */
	PG_DAEMON("-pgDaemon", Type.PG), //$NON-NLS-1$

	/**
	 * The port (default: 5435).
	 */
	PG_PORT("-pgPort", Type.PG); //$NON-NLS-1$

	/**
	 * Models the server option type.
	 *
	 * @author Andrew Vojak
	 */
	public enum Type {

		/**
		 * The GENERAL Type. Not specific to any server type.
		 */
		GENERAL,

		/**
		 * The WEB Type. Specific to web servers.
		 */
		WEB,

		/**
		 * The TCP Type. Specific to TCP servers.
		 */
		TCP,

		/**
		 * The PG Type. Specific to PostgreSQL servers.
		 */
		PG;
	}

	private final String param;
	private final Type type;

	private ServerOption(final String param, final Type type) {
		this.param = param;
		this.type = type;
	}

	/**
	 * Gets the command line parameter.
	 *
	 * @return The non-null, non-empty command line parameter {@code String}.
	 */
	public String getParam() {
		return param;
	}

	/**
	 * Gets the option {@link Type}.
	 *
	 * @return The non-null {@link Type}.
	 */
	public Type getType() {
		return type;
	}

}
