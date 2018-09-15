package com.avojak.plugin.hydrogen.core.h2.model.arguments;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Builder class to create instances of {@link WebServerArguments}.
 *
 * @author Andrew Vojak
 */
public class WebServerArgumentsBuilder {

	private String allowOthers;
	private String useDaemonThread;
	private String port;
	private String useSsl;
	private String openBrowser;

	/**
	 * Constructs a new {@link WebServerArgumentsBuilder}.
	 */
	public WebServerArgumentsBuilder() {
	}

	/**
	 * Constructs a new {@link WebServerArgumentsBuilder} from existing
	 * {@link WebServerArguments}.
	 *
	 * @param oldArguments The existing {@link WebServerArguments}. Cannot be
	 *            null.
	 */
	public WebServerArgumentsBuilder(final WebServerArguments oldArguments) {
		if (oldArguments == null) {
			throw new IllegalArgumentException("oldArguments cannot be null"); //$NON-NLS-1$
		}
		if (oldArguments.getAllowOthers().isPresent()) {
			this.allowOthers = oldArguments.getAllowOthers().get();
		}
		if (oldArguments.getUseDaemonThread().isPresent()) {
			this.useDaemonThread = oldArguments.getUseDaemonThread().get();
		}
		if (oldArguments.getPort().isPresent()) {
			this.port = oldArguments.getPort().get();
		}
		if (oldArguments.getUseSsl().isPresent()) {
			this.useSsl = oldArguments.getUseSsl().get();
		}
		if (oldArguments.getOpenBrowser().isPresent()) {
			this.openBrowser = oldArguments.getOpenBrowser().get();
		}
	}

	/**
	 * Sets the {@link ServerOption#WEB_ALLOW_OTHERS} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder allowOthers() {
		allowOthers = ServerOption.WEB_ALLOW_OTHERS.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_DAEMON} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder useDaemonThread() {
		useDaemonThread = ServerOption.WEB_DAEMON.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_PORT} property.
	 *
	 * @param portNumber The port number. Cannot be null or empty.
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder withPort(final String portNumber) {
		if (portNumber == null || portNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("portNumber cannot be null or empty"); //$NON-NLS-1$
		}
		this.port = portNumber;
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_SSL} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder useSsl() {
		useSsl = ServerOption.WEB_SSL.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_BROWSER} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder openBrowser() {
		openBrowser = ServerOption.WEB_BROWSER.getParam();
		return this;
	}

	/**
	 * Creates a new instance of {@link WebServerArguments}.
	 *
	 * @return A new, non-null instance of {@link WebServerArguments}.
	 */
	public WebServerArguments build() {
		final String startWeb = ServerOption.START_WEB.getParam();
		return new WebServerArguments(startWeb, allowOthers, useDaemonThread, port, useSsl, openBrowser);
	}

	/**
	 * Gets the {@link ServerOption#WEB_ALLOW_OTHERS} property if present.
	 *
	 * @return The {@link ServerOption#WEB_ALLOW_OTHERS} property if present,
	 *         otherwise {@code null}.
	 */
	public String getAllowOthers() {
		return allowOthers;
	}

	/**
	 * Gets the {@link ServerOption#WEB_DAEMON} property if present.
	 *
	 * @return The {@link ServerOption#WEB_DAEMON} property if present,
	 *         otherwise {@code null}.
	 */
	public String getUseDaemonThread() {
		return useDaemonThread;
	}

	/**
	 * Gets the port number if present.
	 *
	 * @return The port number if present, otherwise {@code null}.
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Gets the {@link ServerOption#WEB_SSL} property if present.
	 *
	 * @return The {@link ServerOption#WEB_SSL} property if present, otherwise
	 *         {@code null}.
	 */
	public String getUseSsl() {
		return useSsl;
	}

	/**
	 * Gets the {@link ServerOption#WEB_BROWSER} property if present.
	 *
	 * @return The {@link ServerOption#WEB_BROWSER} property if present,
	 *         otherwise {@code null}.
	 */
	public String getOpenBrowser() {
		return openBrowser;
	}

}
