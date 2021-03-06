package com.avojak.plugin.hydrogen.core.contributions.configuration.launch.listener;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchesListener2;

/**
 * Abstract adapter implementation of {@link ILaunchesListener2}.
 *
 * @author Andrew Vojak
 */
public abstract class LaunchesAdapter implements ILaunchesListener2 {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launchesRemoved(final ILaunch[] launches) {
		// Empty default implementation
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launchesAdded(final ILaunch[] launches) {
		// Empty default implementation
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launchesChanged(final ILaunch[] launches) {
		// Empty default implementation
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launchesTerminated(final ILaunch[] launches) {
		// Empty default implementation
	}

}
