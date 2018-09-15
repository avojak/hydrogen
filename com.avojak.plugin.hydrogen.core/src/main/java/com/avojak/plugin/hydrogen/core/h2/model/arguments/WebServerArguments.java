package com.avojak.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Models the set of web server arguments.
 *
 * @author Andrew Vojak
 */
public class WebServerArguments {

	private final List<String> arguments;

	private final String startWeb;
	private final Optional<String> allowOthers;
	private final Optional<String> useDaemonThread;
	private final Optional<String> port;
	private final Optional<String> useSsl;
	private final Optional<String> openBrowser;

	/**
	 * Constructor.
	 *
	 * @param startWeb The argument for starting the web server. Cannot be null
	 *            or empty.
	 * @param allowOthers The argument for allowing connections to the server
	 *            outside of the host machine.
	 * @param useDaemonThread The argument for running the server on a daemon
	 *            thread.
	 * @param port The argument for specifying a port number.
	 * @param useSsl The argument for enabling SSL.
	 * @param openBrowser The argument for opening the web browser by default to
	 *            connect to the web interface.
	 */
	public WebServerArguments(final String startWeb, final String allowOthers, final String useDaemonThread,
			final String port, final String useSsl, final String openBrowser) {
		if (startWeb == null || startWeb.trim().isEmpty()) {
			throw new IllegalArgumentException("startWeb cannot be null or empty"); //$NON-NLS-1$
		}
		this.startWeb = startWeb;
		this.allowOthers = Optional.ofNullable(allowOthers);
		this.useDaemonThread = Optional.ofNullable(useDaemonThread);
		this.port = Optional.ofNullable(port);
		this.useSsl = Optional.ofNullable(useSsl);
		this.openBrowser = Optional.ofNullable(openBrowser);

		this.arguments = new ArrayList<String>();
		arguments.add(startWeb);
		if (this.allowOthers.isPresent()) {
			arguments.add(this.allowOthers.get());
		}
		if (this.useDaemonThread.isPresent()) {
			arguments.add(this.useDaemonThread.get());
		}
		if (this.port.isPresent()) {
			arguments.add(ServerOption.WEB_PORT.getParam());
			arguments.add(this.port.get());
		}
		if (this.useSsl.isPresent()) {
			arguments.add(this.useSsl.get());
		}
		if (this.openBrowser.isPresent()) {
			arguments.add(this.openBrowser.get());
		}
	}

	/**
	 * Gets the {@link List} of arguments.
	 *
	 * @return The non-null, non-empty {@link List} of web server arguments.
	 */
	public List<String> getArguments() {
		return new ArrayList<String>(arguments);
	}

	/**
	 * Gets the "Start Web" property.
	 *
	 * @return The non-null property.
	 */
	public String getStartWeb() {
		return startWeb;
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
	 * Gets the "Open Browser" property, if present.
	 *
	 * @return The property, if present, otherwise {@link Optional#empty()}.
	 */
	public Optional<String> getOpenBrowser() {
		return openBrowser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "WebServerArguments [arguments=" + arguments + ", startWeb=" + startWeb + ", allowOthers=" + allowOthers //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", useDaemonThread=" + useDaemonThread + ", port=" + port + ", useSsl=" + useSsl + ", openBrowser=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ openBrowser + "]"; //$NON-NLS-1$
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
		result = prime * result + openBrowser.hashCode();
		result = prime * result + port.hashCode();
		result = prime * result + startWeb.hashCode();
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
		final WebServerArguments other = (WebServerArguments) obj;
		if (!allowOthers.equals(other.allowOthers)) {
			return false;
		}
		if (!arguments.equals(other.arguments)) {
			return false;
		}
		if (!openBrowser.equals(other.openBrowser)) {
			return false;
		}
		if (!port.equals(other.port)) {
			return false;
		}
		if (!startWeb.equals(other.startWeb)) {
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
