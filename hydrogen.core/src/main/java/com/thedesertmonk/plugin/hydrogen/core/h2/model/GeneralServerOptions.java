/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model;

/**
 * @author andrewvojak
 *
 */
@SuppressWarnings("nls")
public enum GeneralServerOptions {

	/**
	 * Allows to map a database name to another.
	 */
	KEY("-key"),

	/**
	 * Print additional trace information.
	 */
	TRACE("-trace"),

	/**
	 * Only existing databases may be opened.
	 */
	IF_EXISTS("-ifExists"),

	/**
	 * The base directory for H2 databases.
	 */
	BASE_DIR("-baseDir"),

	/**
	 * Server properties.
	 */
	PROPERTIES("-properties");

	private final String param;

	private GeneralServerOptions(final String param) {
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
