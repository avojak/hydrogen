package com.avojak.plugin.hydrogen.core.wrapper;

import org.eclipse.debug.core.DebugPlugin;

/**
 * Wrapper class for {@link DebugPlugin} to allow facilitated mocking.
 * 
 * @author Andrew Vojak
 */
public class DebugPluginWrapper {

	/**
	 * Wrapper for {@link DebugPlugin#getDefault()}.
	 * 
	 * @return The {@link DebugPlugin}.
	 * 
	 * @see DebugPlugin#getDefault()
	 */
	public DebugPlugin getDefault() {
		return DebugPlugin.getDefault();
	}

}
