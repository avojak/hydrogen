/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model;

/**
 * @author andrewvojak
 *
 */
@SuppressWarnings("nls")
public enum TCPServerOptions {

	PORT("-tcpPort"),
	SSL("-tcpSSL"),
	PASSWORD("-tcpPassword"),
	ALLOW_OTHERS("tcpAllowOthers"),
	DAEMON("-tcpDaemon"),
	TRACE("-trace"),
	IF_EXISTS("-ifExists"),
	BASE_DIR("-baseDir"),
	KEY("-key");

	private final String param;

	private TCPServerOptions(final String param) {
		if (param == null || param.trim().isEmpty()) {
			throw new IllegalArgumentException("param cannot be null or empty");
		}
		this.param = param;
	}

}
