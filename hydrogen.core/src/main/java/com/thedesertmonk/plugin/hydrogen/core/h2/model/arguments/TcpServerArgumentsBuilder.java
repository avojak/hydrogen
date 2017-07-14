package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Builder class to create instances of {@link TcpServerArguments}.
 *
 * @author Andrew Vojak
 */
public class TcpServerArgumentsBuilder {

	private String allowOthers;
	private String useDaemonThread;
	private String port;
	private String useSsl;
	private String shutdownUrl;
	private String shutdownPassword;
	private String forceShutdown;

	/**
	 * Constructs a new {@link TcpServerArgumentsBuilder}.
	 */
	public TcpServerArgumentsBuilder() {
	}

	/**
	 * Constructs a new {@link TcpServerArgumentsBuilder} from existing
	 * {@link TcpServerArguments}.
	 *
	 * @param oldArguments The existing {@link TcpServerArguments}. Cannot be
	 *            null.
	 */
	public TcpServerArgumentsBuilder(final TcpServerArguments oldArguments) {
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
		if (oldArguments.getShutdownUrl().isPresent()) {
			this.shutdownUrl = oldArguments.getShutdownUrl().get();
		}
		if (oldArguments.getShutdownPassword().isPresent()) {
			this.shutdownPassword = oldArguments.getShutdownPassword().get();
		}
		if (oldArguments.getForceShutdown().isPresent()) {
			this.forceShutdown = oldArguments.getForceShutdown().get();
		}
	}

	/**
	 * Sets the {@link ServerOption#TCP_ALLOW_OTHERS} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder allowOthers() {
		allowOthers = ServerOption.TCP_ALLOW_OTHERS.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_DAEMON} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder useDaemonThread() {
		useDaemonThread = ServerOption.TCP_DAEMON.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_PORT} property.
	 *
	 * @param portNumber The port number. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withPort(final String portNumber) {
		if (portNumber == null || portNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("portNumber cannot be null or empty"); //$NON-NLS-1$
		}
		this.port = portNumber;
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_SSL} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder useSsl() {
		useSsl = ServerOption.TCP_SSL.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_SHUTDOWN_URL} property.
	 *
	 * @param url The shutdown URL. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withShutdownUrl(final String url) {
		if (url == null || url.trim().isEmpty()) {
			throw new IllegalArgumentException("url cannot be null or empty"); //$NON-NLS-1$
		}
		this.shutdownUrl = url;
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_SHUTDOWN_PASSWORD} property.
	 *
	 * @param password The shutdown password. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withShutdownPassword(final String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("password cannot be null or empty"); //$NON-NLS-1$
		}
		this.shutdownPassword = password;
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_SHUTDOWN_FORCE} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder forceShutdown() {
		forceShutdown = ServerOption.TCP_SHUTDOWN_FORCE.getParam();
		return this;
	}

	/**
	 * Creates a new instance of {@link TcpServerArguments}.
	 *
	 * @return A new, non-null instance of {@link TcpServerArguments}.
	 */
	public TcpServerArguments build() {
		final String startTcp = ServerOption.START_TCP.getParam();
		return new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port, useSsl, shutdownUrl,
				shutdownPassword, forceShutdown);
	}

	/**
	 * Gets the {@link ServerOption#TCP_ALLOW_OTHERS} property if present.
	 *
	 * @return The {@link ServerOption#TCP_ALLOW_OTHERS} property if present,
	 *         otherwise {@code null}.
	 */
	public String getAllowOthers() {
		return allowOthers;
	}

	/**
	 * Gets the {@link ServerOption#TCP_DAEMON} property if present.
	 *
	 * @return The {@link ServerOption#TCP_DAEMON} property if present,
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
	 * Gets the {@link ServerOption#TCP_SSL} property if present.
	 *
	 * @return The {@link ServerOption#TCP_SSL} property if present, otherwise
	 *         {@code null}.
	 */
	public String getUseSsl() {
		return useSsl;
	}

	/**
	 * Gets the {@link ServerOption#TCP_SHUTDOWN_URL} property if present.
	 *
	 * @return The {@link ServerOption#TCP_SHUTDOWN_URL} property if present,
	 *         otherwise {@code null}.
	 */
	public String getShutdownUrl() {
		return shutdownUrl;
	}

	/**
	 * Gets the {@link ServerOption#TCP_SHUTDOWN_PASSWORD} property if present.
	 *
	 * @return The {@link ServerOption#TCP_SHUTDOWN_PASSWORD} property if
	 *         present, otherwise {@code null}.
	 */
	public String getShutdownPassword() {
		return shutdownPassword;
	}

	/**
	 * Gets the {@link ServerOption#TCP_SHUTDOWN_FORCE} property if present.
	 *
	 * @return The {@link ServerOption#TCP_SHUTDOWN_FORCE} property if present,
	 *         otherwise {@code null}.
	 */
	public String getForceShutdown() {
		return forceShutdown;
	}

}
