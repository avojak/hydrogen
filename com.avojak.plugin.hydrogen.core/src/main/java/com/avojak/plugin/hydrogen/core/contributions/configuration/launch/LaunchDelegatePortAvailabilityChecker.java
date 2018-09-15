package com.avojak.plugin.hydrogen.core.contributions.configuration.launch;

import java.io.IOException;
import java.net.ServerSocket;

import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.avojak.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * <p>
 * Class to handle last-resort checking of port availability by attempting to
 * create a socket bound to the port in question.
 * </p>
 * <p>
 * <em>Note: When distributing port numbers, it is recommended that those port
 * numbers be cached so that this process only needs to be run when
 * necessary.</em>
 * </p>
 *
 * @author Andrew Vojak
 */
public class LaunchDelegatePortAvailabilityChecker {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory
			.getForClass(LaunchDelegatePortAvailabilityChecker.class);

	private final ServerSocketFactory serverSocketFactory;

	/**
	 * Constructor.
	 *
	 * @param serverSocketFactory The {@link ServerSocketFactory}. Cannot be
	 *            null.
	 */
	public LaunchDelegatePortAvailabilityChecker(final ServerSocketFactory serverSocketFactory) {
		if (serverSocketFactory == null) {
			throw new IllegalArgumentException("serverSocketFactory cannot be null"); //$NON-NLS-1$
		}
		this.serverSocketFactory = serverSocketFactory;
	}

	/**
	 * Returns whether or not the given port number represents a valid, free,
	 * and unused port.
	 *
	 * @param port The port number.
	 * @return {@code true} if the port is free, otherwise {@code false}.
	 */
	public boolean isPortFree(final int port) {
		if (!PortValidator.isValid(port)) {
			return false;
		}
		ServerSocket serverSocket = null;
		try {
			serverSocket = serverSocketFactory.create(port);
			return true;
		} catch (final IOException e) {
			return false;
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (final IOException e) {
				LOGGER.debug("Failed to close socket during port availability test"); //$NON-NLS-1$
			}
		}
	}

}
