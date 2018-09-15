package com.avojak.plugin.hydrogen.core.contributions.configuration.launch.listener;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.ILaunch;

import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.avojak.plugin.hydrogen.core.logging.IHydrogenLogger;

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
		final List<Integer> portsToReturn = new ArrayList<Integer>();
		for (final ILaunch launch : launches) {
			final String webPort = launch.getAttribute(ServerOption.WEB_PORT.name());
			if (webPort != null) {
				portsToReturn.add(Integer.valueOf(webPort));
			}
			final String tcpPort = launch.getAttribute(ServerOption.TCP_PORT.name());
			if (tcpPort != null) {
				portsToReturn.add(Integer.valueOf(tcpPort));
			}
			final String pgPort = launch.getAttribute(ServerOption.PG_PORT.name());
			if (pgPort != null) {
				portsToReturn.add(Integer.valueOf(pgPort));
			}
		}
		portPool.returnPorts(portsToReturn);

		LOGGER.info("Launch terminated"); //$NON-NLS-1$
	}

}
