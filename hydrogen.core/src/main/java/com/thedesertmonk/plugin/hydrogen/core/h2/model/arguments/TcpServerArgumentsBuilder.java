/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * @author andrewvojak
 *
 */
public class TcpServerArgumentsBuilder {

	private final List<String> arguments;

	/**
	 * Constructor.
	 */
	public TcpServerArgumentsBuilder() {
		arguments = new ArrayList<String>();
		arguments.add(ServerOption.START_TCP.getParam());
	}

	/**
	 * Sets the {@link ServerOption#TCP_ALLOW_OTHERS} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder allowOthers() {
		arguments.add(ServerOption.TCP_ALLOW_OTHERS.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_DAEMON} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder useDaemonThread() {
		arguments.add(ServerOption.TCP_DAEMON.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_PORT} property.
	 *
	 * @param port The port number. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withPort(final String port) {
		if (port == null || port.trim().isEmpty()) {
			throw new IllegalArgumentException("port cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(ServerOption.TCP_PORT.getParam());
		arguments.add(port);
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_SSL} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder useSsl() {
		arguments.add(ServerOption.TCP_SSL.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_SHUTDOWN_URL} property.
	 *
	 * @param url The shutdown URL. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withShutdownURL(final String url) {
		if (url == null || url.trim().isEmpty()) {
			throw new IllegalArgumentException("url cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(ServerOption.TCP_SHUTDOWN_URL.getParam());
		arguments.add(url);
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
		arguments.add(ServerOption.TCP_SHUTDOWN_PASSWORD.getParam());
		arguments.add(password);
		return this;
	}

	/**
	 * Sets the {@link ServerOption#TCP_SHUTDOWN_FORCE} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder forceShutdown() {
		arguments.add(ServerOption.TCP_SHUTDOWN_FORCE.getParam());
		return this;
	}

	/**
	 * Creates a new instance of {@link TcpServerArguments}.
	 *
	 * @return A new, non-null instance of {@link TcpServerArguments}.
	 */
	public TcpServerArguments build() {
		return new TcpServerArguments(arguments);
	}

}
