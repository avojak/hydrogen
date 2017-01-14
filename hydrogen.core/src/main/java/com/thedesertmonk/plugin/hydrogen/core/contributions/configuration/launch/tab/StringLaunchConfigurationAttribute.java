/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * @author andrewvojak
 *
 */
public enum StringLaunchConfigurationAttribute {

	BASE_DIRECTORY(ServerOption.BASE_DIRECTORY, ""),
	PROPERTIES(ServerOption.PROPERTIES, ""),
	WEB_PORT(ServerOption.WEB_PORT, "8082"),
	TCP_PORT(ServerOption.TCP_PORT, "9092"),
	TCP_SHUTDOWN_PASSWORD(ServerOption.TCP_SHUTDOWN_PASSWORD, ""),
	TCP_SHUTDOWN_URL(ServerOption.TCP_SHUTDOWN_URL, ""),
	PG_PORT(ServerOption.PG_PORT, "5435");

	private final ServerOption serverOption;
	private final String defaultValue;

	private StringLaunchConfigurationAttribute(final ServerOption serverOption, final String defaultValue) {
		this.serverOption = serverOption;
		this.defaultValue = defaultValue;
	}

	/**
	 * Gets the {@link ServerOption}.
	 *
	 * @return The non-null {@link ServerOption}.
	 */
	public ServerOption getServerOption() {
		return serverOption;
	}

	/**
	 * Gets the default value.
	 *
	 * @return The default {@code String} value.
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

}
