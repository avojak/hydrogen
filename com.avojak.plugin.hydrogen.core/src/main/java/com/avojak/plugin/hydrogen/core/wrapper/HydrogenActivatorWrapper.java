package com.avojak.plugin.hydrogen.core.wrapper;

import com.avojak.plugin.hydrogen.core.HydrogenActivator;

/**
 * Wrapper class for {@link HydrogenActivatorWrapper} to allow facilitated
 * mocking.
 * 
 * @author Andrew Vojak
 */
public class HydrogenActivatorWrapper {

	/**
	 * Wraps {@link HydrogenActivator#getDefault()}.
	 * 
	 * @return The {@link HydrogenActivator}.
	 * 
	 * @see HydrogenActivator#getDefault()
	 */
	public HydrogenActivator getDefault() {
		return HydrogenActivator.getDefault();
	}

}
