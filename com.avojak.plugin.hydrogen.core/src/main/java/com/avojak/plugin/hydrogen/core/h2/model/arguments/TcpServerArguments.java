package com.avojak.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Models the set of TCP server arguments.
 *
 * @author Andrew Vojak
 */
public class TcpServerArguments {

	private final List<String> arguments;

	private final String startTcp;
	private final Optional<String> allowOthers;
	private final Optional<String> useDaemonThread;
	private final Optional<String> port;
	private final Optional<String> useSsl;
	private final Optional<String> shutdownUrl;
	private final Optional<String> shutdownPassword;
	private final Optional<String> forceShutdown;

	/**
	 * Constructor.
	 *
	 * @param startTcp
	 *            The argument for starting the TCP server. Cannot be null or empty.
	 * @param allowOthers
	 *            The argument for allowing connections to the server outside of the
	 *            host machine.
	 * @param useDaemonThread
	 *            The argument for running the server on a daemon thread.
	 * @param port
	 *            The argument for specifying a port number.
	 * @param useSsl
	 *            The argument for enabling SSL.
	 * @param shutdownUrl
	 *            The argument for specifying a shutdown URL.
	 * @param shutdownPassword
	 *            The argument for specifying a shutdown password.
	 * @param forceShutdown
	 *            The argument for allowing a forced shutdown.
	 */
	public TcpServerArguments(final String startTcp, final String allowOthers, final String useDaemonThread,
			final String port, final String useSsl, final String shutdownUrl, final String shutdownPassword,
			final String forceShutdown) {
		if (startTcp == null || startTcp.trim().isEmpty()) {
			throw new IllegalArgumentException("startTcp cannot be null or empty"); //$NON-NLS-1$
		}
		this.startTcp = startTcp;
		this.allowOthers = Optional.ofNullable(allowOthers);
		this.useDaemonThread = Optional.ofNullable(useDaemonThread);
		this.port = Optional.ofNullable(port);
		this.useSsl = Optional.ofNullable(useSsl);
		this.shutdownUrl = Optional.ofNullable(shutdownUrl);
		this.shutdownPassword = Optional.ofNullable(shutdownPassword);
		this.forceShutdown = Optional.ofNullable(forceShutdown);

		this.arguments = new ArrayList<String>();
		arguments.add(startTcp);
		if (this.allowOthers.isPresent()) {
			arguments.add(this.allowOthers.get());
		}
		if (this.useDaemonThread.isPresent()) {
			arguments.add(this.useDaemonThread.get());
		}
		if (this.port.isPresent()) {
			arguments.add(ServerOption.TCP_PORT.getParam());
			arguments.add(this.port.get());
		}
		if (this.useSsl.isPresent()) {
			arguments.add(this.useSsl.get());
		}
		if (this.shutdownUrl.isPresent()) {
			arguments.add(ServerOption.TCP_SHUTDOWN_URL.getParam());
			arguments.add(this.shutdownUrl.get());
		}
		if (this.shutdownPassword.isPresent()) {
			arguments.add(ServerOption.TCP_SHUTDOWN_PASSWORD.getParam());
			arguments.add(this.shutdownPassword.get());
		}
		if (this.forceShutdown.isPresent()) {
			arguments.add(this.forceShutdown.get());
		}
	}

	/**
	 * Gets the {@link List} of arguments.
	 *
	 * @return The non-null, non-empty {@link List} of TCP server arguments.
	 */
	public List<String> getArguments() {
		return new ArrayList<String>(arguments);
	}

	/**
	 * Gets the "Start TCP" property.
	 *
	 * @return The non-null property.
	 */
	public String getStartTcp() {
		return startTcp;
	}

	/**
	 * Gets the "Allow Others" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getAllowOthers() {
		return allowOthers;
	}

	/**
	 * Gets the "Use Daemon Thread" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getUseDaemonThread() {
		return useDaemonThread;
	}

	/**
	 * Gets the "Port Number" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getPort() {
		return port;
	}

	/**
	 * Gets the "Use SSL" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getUseSsl() {
		return useSsl;
	}

	/**
	 * Gets the "Shutdown URL" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getShutdownUrl() {
		return shutdownUrl;
	}

	/**
	 * Gets the "Shutdown Password" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getShutdownPassword() {
		return shutdownPassword;
	}

	/**
	 * Gets the "Force Shutdown" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getForceShutdown() {
		return forceShutdown;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "TcpServerArguments [arguments=" + arguments + ", startTcp=" + startTcp + ", allowOthers=" + allowOthers //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", useDaemonThread=" + useDaemonThread + ", port=" + port + ", useSsl=" + useSsl + ", shutdownUrl=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ shutdownUrl + ", shutdownPassword=" + shutdownPassword + ", forceShutdown=" + forceShutdown + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allowOthers.hashCode();
		result = prime * result + forceShutdown.hashCode();
		result = prime * result + port.hashCode();
		result = prime * result + shutdownPassword.hashCode();
		result = prime * result + shutdownUrl.hashCode();
		result = prime * result + startTcp.hashCode();
		result = prime * result + useDaemonThread.hashCode();
		result = prime * result + useSsl.hashCode();
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TcpServerArguments other = (TcpServerArguments) obj;
		if (!allowOthers.equals(other.allowOthers)) {
			return false;
		}
		if (!forceShutdown.equals(other.forceShutdown)) {
			return false;
		}
		if (!port.equals(other.port)) {
			return false;
		}
		if (!shutdownPassword.equals(other.shutdownPassword)) {
			return false;
		}
		if (!shutdownUrl.equals(other.shutdownUrl)) {
			return false;
		}
		if (!startTcp.equals(other.startTcp)) {
			return false;
		}
		if (!useDaemonThread.equals(other.useDaemonThread)) {
			return false;
		}
		if (!useSsl.equals(other.useSsl)) {
			return false;
		}
		return true;
	}

}
