/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.TcpServerOptions;

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
		arguments.add(TcpServerOptions.TCP.getParam());
	}

	/**
	 * Sets the {@link TcpServerOptions#ALLOW_OTHERS} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder allowOthers() {
		arguments.add(TcpServerOptions.ALLOW_OTHERS.getParam());
		return this;
	}

	/**
	 * Sets the {@link TcpServerOptions#DAEMON} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder useDaemonThread() {
		arguments.add(TcpServerOptions.DAEMON.getParam());
		return this;
	}

	/**
	 * Sets the {@link TcpServerOptions#PORT} property.
	 *
	 * @param port The port number. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withPort(final String port) {
		if (port == null || port.trim().isEmpty()) {
			throw new IllegalArgumentException("port cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(TcpServerOptions.PORT.getParam());
		arguments.add(port);
		return this;
	}

	/**
	 * Sets the {@link TcpServerOptions#SSL} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder useSSL() {
		arguments.add(TcpServerOptions.SSL.getParam());
		return this;
	}

	/**
	 * Sets the {@link TcpServerOptions#SHUTDOWN} property.
	 *
	 * @param url The shutdown URL. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withShutdownURL(final String url) {
		if (url == null || url.trim().isEmpty()) {
			throw new IllegalArgumentException("url cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(TcpServerOptions.SHUTDOWN.getParam());
		arguments.add(url);
		return this;
	}

	/**
	 * Sets the {@link TcpServerOptions#PASSWORD} property.
	 *
	 * @param password The shutdown password. Cannot be null or empty.
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder withShutdownPassword(final String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("password cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(TcpServerOptions.PASSWORD.getParam());
		arguments.add(password);
		return this;
	}

	/**
	 * Sets the {@link TcpServerOptions#SHUTDOWN_FORCE} property.
	 *
	 * @return The current {@link TcpServerArgumentsBuilder} instance.
	 */
	public TcpServerArgumentsBuilder forceShutdown() {
		arguments.add(TcpServerOptions.SHUTDOWN_FORCE.getParam());
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
