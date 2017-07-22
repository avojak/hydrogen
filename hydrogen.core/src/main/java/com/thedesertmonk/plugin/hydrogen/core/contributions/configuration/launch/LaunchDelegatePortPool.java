package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.exception.NoAvailablePortException;
import com.thedesertmonk.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.thedesertmonk.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * Utility class for the launch delegate to distribute and maintain ports during
 * the launch.
 *
 * @author Andrew Vojak
 */
public class LaunchDelegatePortPool {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory.getForClass(LaunchDelegatePortPool.class);

	// An acceptable port number to return to the caller will be within the
	// range [49152-65535].
	private static final int MIN_PORT_NUMBER = 49152;
	private static final int MAX_PORT_NUMBER = 65535;

	private final LaunchDelegatePortAvailabilityChecker availabilityChecker;
	// A cache of the ports that we have distributed
	private final Set<Integer> distributedPorts;
	// A cache of the ports that we have discovered to already be in use
	private final Set<Integer> discoveredUsedPorts;

	/**
	 * Constructor.
	 *
	 * @param availabilityChecker The
	 *            {@link LaunchDelegatePortAvailabilityChecker}. Cannot be null.
	 */
	public LaunchDelegatePortPool(final LaunchDelegatePortAvailabilityChecker availabilityChecker) {
		if (availabilityChecker == null) {
			throw new IllegalArgumentException("availabilityChecker cannot be null"); //$NON-NLS-1$
		}
		this.availabilityChecker = availabilityChecker;
		distributedPorts = new HashSet<Integer>();
		discoveredUsedPorts = new HashSet<Integer>();
	}

	/**
	 * Returns whether or not the given port number represents a valid, free,
	 * and unused port.
	 *
	 * @param port The port number.
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
		return availabilityChecker.isPortFree(port);
	}

	/**
	 * Finds and returns the first free port within an acceptable range of
	 * non-'well-known' ports.
	 *
	 * @return The port number.
	 */
	public int getFreePort() {
		for (int port = MIN_PORT_NUMBER; port <= MAX_PORT_NUMBER; ++port) {
			if (isPortFree(port)) {
				// Cache the port number so we don't try to hand it out again
				distributedPorts.add(port);
				LOGGER.debug("Distributing unused port " + port); //$NON-NLS-1$
				return port;
			}
			// We found a used port that we did not distribute
			if (!distributedPorts.contains(port)) {
				discoveredUsedPorts.add(port);
			}
		}
		final NoAvailablePortException exception = new NoAvailablePortException(
				"No available port was found within the range [" + MIN_PORT_NUMBER + " - " + MAX_PORT_NUMBER + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		LOGGER.fatal("No available port could be found", exception); //$NON-NLS-1$
		throw exception;
	}

	/**
	 * Returns all ports which have been distributed by the pool.
	 *
	 * @return A non-null, possibly empty {@link Collection} containing the
	 *         ports numbers.
	 */
	public Collection<Integer> getDistributedPorts() {
		return new HashSet<Integer>(distributedPorts);
	}

	/**
	 * Returns all ports which have been discovered to be in use by other
	 * services.
	 *
	 * @return A non-null, possibly empty {@link Collection} containing the
	 *         ports numbers.
	 */
	public Collection<Integer> getDiscoveredUsedPorts() {
		return new HashSet<Integer>(discoveredUsedPorts);
	}

	/**
	 * Returns all distributed ports back to the pool for re-distribution.
	 *
	 * @return A non-null, possibly empty {@link Collection} containing the
	 *         ports which have been successfully added back to the pool.
	 */
	public Collection<Integer> returnDistributedPorts() {
		final Set<Integer> returnSet = new HashSet<Integer>(distributedPorts);
		distributedPorts.clear();
		return returnSet;
	}

}
