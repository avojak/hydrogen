/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * @author andrewvojak
 *
 */
public enum BooleanLaunchConfigurationAttribute {

	START_WEB(ServerOption.START_WEB, true),
	START_TCP(ServerOption.START_TCP, true),
	START_PG(ServerOption.START_PG, true),
	ENABLE_TRACING(ServerOption.ENABLE_TRACING, false),
	IF_EXISTS(ServerOption.IF_EXISTS, false),
	WEB_ALLOW_OTHERS(ServerOption.WEB_ALLOW_OTHERS, false),
	WEB_DAEMON(ServerOption.WEB_DAEMON, false),
	WEB_SSL(ServerOption.WEB_SSL, true),
	WEB_BROWSER(ServerOption.WEB_BROWSER, true),
	TCP_SSL(ServerOption.TCP_SSL, true),
	TCP_ALLOW_OTHERS(ServerOption.TCP_ALLOW_OTHERS, false),
	TCP_DAEMON(ServerOption.TCP_DAEMON, false),
	TCP_SHUTDOWN_FORCE(ServerOption.TCP_SHUTDOWN_FORCE, true),
	PG_ALLOW_OTHERS(ServerOption.PG_ALLOW_OTHERS, false),
	PG_DAEMON(ServerOption.PG_DAEMON, false);

	private final ServerOption serverOption;
	private final boolean defaultValue;

	private BooleanLaunchConfigurationAttribute(final ServerOption serverOption, final boolean defaultValue) {
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
	 * @return The default {@code boolean} value.
	 */
	public boolean getDefaultValue() {
		return defaultValue;
	}

}
