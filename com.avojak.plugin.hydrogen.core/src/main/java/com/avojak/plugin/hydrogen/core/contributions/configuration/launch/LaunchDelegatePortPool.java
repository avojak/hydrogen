package com.avojak.plugin.hydrogen.core.contributions.configuration.launch;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.factory.ServerSocketFactory;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.avojak.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * Utility class for the launch delegate to distribute and maintain ports during
 * the launch.
 *
 * @author Andrew Vojak
 */
public class LaunchDelegatePortPool {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory.getForClass(LaunchDelegatePortPool.class);

	private final LaunchDelegatePortAvailabilityChecker availabilityChecker;
	private final ServerSocketFactory serverSocketFactory;
	// A cache of the ports that we have distributed
	private final Set<Integer> distributedPorts;
	// A cache of the ports that we have discovered to already be in use
	private final Set<Integer> discoveredUsedPorts;

	/**
	 * Constructor.
	 *
	 * @param availabilityChecker
	 *            The {@link LaunchDelegatePortAvailabilityChecker}. Cannot be null.
	 * @param serverSocketFactory
	 *            The {@link ServerSocketFactory}. Cannot be null.
	 */
	public LaunchDelegatePortPool(final LaunchDelegatePortAvailabilityChecker availabilityChecker,
			final ServerSocketFactory serverSocketFactory) {
		if (availabilityChecker == null) {
			throw new IllegalArgumentException("availabilityChecker cannot be null"); //$NON-NLS-1$
		}
		if (serverSocketFactory == null) {
			throw new IllegalArgumentException("serverSocketFactory cannot be null"); //$NON-NLS-1$
		}
		this.availabilityChecker = availabilityChecker;
		this.serverSocketFactory = serverSocketFactory;
		distributedPorts = new HashSet<Integer>();
		discoveredUsedPorts = new HashSet<Integer>();
	}

	/**
	 * Returns whether or not the given port number represents a valid, free, and
	 * unused port.
	 *
	 * @param port
	 *            The port number.
	 * @return {@code true} if the port is free, otherwise {@code false}.
	 */
	public boolean isPortFree(final int port) {
		// First check that the port is valid
		if (!PortValidator.isValid(port)) {
			LOGGER.debug(port + " is invalid"); //$NON-NLS-1$
			return false;
		}

		// Check if the port has already been distributed
		if (distributedPorts.contains(port)) {
			LOGGER.debug(port + " has already been distributed by the pool"); //$NON-NLS-1$
			return false;
		}

		// Check if the port is known to be used by another service
		// Note: This is a bit of a guess, because the port may no longer
		// actually be used. By skipping it we eliminate an attempt to bind to
		// the port which is costly.
		if (discoveredUsedPorts.contains(port)) {
			LOGGER.debug(port + " is already in use"); //$NON-NLS-1$
			return false;
		}

		// Attempt to bind to the port as a final check
		final boolean isPortFree = availabilityChecker.isPortFree(port);
		if (!isPortFree) {
			discoveredUsedPorts.add(port);
		}
		return isPortFree;
	}

	/**
	 * Finds and returns an unused port number.
	 *
	 * @return The port number.
	 * @throws IOException
	 *             If an I/O error occurs when checking the socket.
	 */
	public int getFreePort() throws IOException {
		try (final ServerSocket socket = serverSocketFactory.create(0)) {
			final int port = socket.getLocalPort();
			distributedPorts.add(port);
			return port;
		}
	}

	/**
	 * Returns all ports which have been distributed by the pool.
	 *
	 * @return A non-null, possibly empty {@link Collection} containing the ports
	 *         numbers.
	 */
	public Collection<Integer> getDistributedPorts() {
		return new HashSet<Integer>(distributedPorts);
	}

	/**
	 * Returns all ports which have been discovered to be in use by other services.
	 *
	 * @return A non-null, possibly empty {@link Collection} containing the ports
	 *         numbers.
	 */
	public Collection<Integer> getDiscoveredUsedPorts() {
		return new HashSet<Integer>(discoveredUsedPorts);
	}

	/**
	 * Returns all distributed ports back to the pool for re-distribution.
	 *
	 * @return A non-null, possibly empty {@link Collection} containing the ports
	 *         which have been successfully added back to the pool.
	 */
	public Collection<Integer> returnAllDistributedPorts() {
		final Set<Integer> returnSet = new HashSet<Integer>(distributedPorts);
		distributedPorts.clear();
		return returnSet;
	}

	/**
	 * Returns the specified ports back to the pool for re-distribution.
	 *
	 * @param ports
	 *            The {@link List} of port numbers to be returned to the pool.
	 *            Cannot be null.
	 */
	public void returnPorts(final List<Integer> ports) {
		if (ports == null) {
			throw new IllegalArgumentException("ports cannot be null"); //$NON-NLS-1$
		}
		for (final Integer port : ports) {
			distributedPorts.remove(port);
			discoveredUsedPorts.remove(port);
		}
	}

}
