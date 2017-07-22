package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.listener;

import org.eclipse.debug.core.ILaunch;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.thedesertmonk.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.thedesertmonk.plugin.hydrogen.core.logging.IHydrogenLogger;

/**
 * Implementation of {@link LaunchesAdapter} for the Hydrogen launch
 * configurations.
 *
 * @author Andrew Vojak
 */
public class HydrogenLaunchListener extends LaunchesAdapter {

	private static final IHydrogenLogger LOGGER = HydrogenLoggerFactory.getForClass(HydrogenLaunchListener.class);

	private final LaunchDelegatePortPool portPool;

	/**
	 * Constructor.
	 *
	 * @param portPool The {@link LaunchDelegatePortPool}. Cannot be null.
	 */
	public HydrogenLaunchListener(final LaunchDelegatePortPool portPool) {
		if (portPool == null) {
			throw new IllegalArgumentException("portPool cannot be null"); //$NON-NLS-1$
		}
		this.portPool = portPool;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launchesTerminated(final ILaunch[] launches) {
		// Return used ports to the pool so that they can be used in subsequent
		// launches
		// TODO: Fix this because if there are multiple launches, it will return
		// all ports - not just those from the specific launch
		portPool.returnDistributedPorts();

		LOGGER.info("Launch terminated"); //$NON-NLS-1$
	}

}
