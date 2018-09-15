package com.avojak.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Models the PostgreSQL server arguments.
 *
 * @author Andrew Vojak
 */
public class PgServerArguments {

	private final List<String> arguments;

	private final String startPg;
	private final Optional<String> allowOthers;
	private final Optional<String> useDaemonThread;
	private final Optional<String> port;

	/**
	 * Constructor.
	 *
	 * @param startPg The argument for starting the PostgreSQL server. Cannot be
	 *            null or empty.
	 * @param allowOthers The argument for allowing connections to the server
	 *            outside of the host machine.
	 * @param useDaemonThread The argument for running the server on a daemon
	 *            thread.
	 * @param port The argument for specifying a port number.
	 */
	public PgServerArguments(final String startPg, final String allowOthers, final String useDaemonThread,
			final String port) {
		if (startPg == null || startPg.trim().isEmpty()) {
			throw new IllegalArgumentException("startPg cannot be null or empty"); //$NON-NLS-1$
		}
		this.startPg = startPg;
		this.allowOthers = Optional.ofNullable(allowOthers);
		this.useDaemonThread = Optional.ofNullable(useDaemonThread);
		this.port = Optional.ofNullable(port);

		this.arguments = new ArrayList<String>();
		arguments.add(startPg);
		if (this.allowOthers.isPresent()) {
			arguments.add(this.allowOthers.get());
		}
		if (this.useDaemonThread.isPresent()) {
			arguments.add(this.useDaemonThread.get());
		}
		if (this.port.isPresent()) {
			arguments.add(ServerOption.PG_PORT.getParam());
			arguments.add(this.port.get());
		}
	}

	/**
	 * Gets the {@link List} of arguments.
	 *
	 * @return The non-null, non-empty {@link List} of PostgreSQL server
	 *         arguments.
	 */
	public List<String> getArguments() {
		return new ArrayList<String>(arguments);
	}

	/**
	 * Gets the "Start PostgreSQL" property.
	 *
	 * @return The non-null property.
	 */
	public String getStartPg() {
		return startPg;
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "PgServerArguments [arguments=" + arguments + ", startPg=" + startPg + ", allowOthers=" + allowOthers //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", useDaemonThread=" + useDaemonThread + ", port=" + port + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allowOthers.hashCode();
		result = prime * result + arguments.hashCode();
		result = prime * result + port.hashCode();
		result = prime * result + startPg.hashCode();
		result = prime * result + useDaemonThread.hashCode();
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
		final PgServerArguments other = (PgServerArguments) obj;
		if (!allowOthers.equals(other.allowOthers)) {
			return false;
		}
		if (!arguments.equals(other.arguments)) {
			return false;
		}
		if (!port.equals(other.port)) {
			return false;
		}
		if (!startPg.equals(other.startPg)) {
			return false;
		}
		if (!useDaemonThread.equals(other.useDaemonThread)) {
			return false;
		}
		return true;
	}

}
