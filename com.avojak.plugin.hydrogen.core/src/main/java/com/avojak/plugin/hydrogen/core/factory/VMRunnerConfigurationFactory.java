package com.avojak.plugin.hydrogen.core.factory;

import org.eclipse.jdt.launching.VMRunnerConfiguration;

/**
 * Factory class to create instances of {@link VMRunnerConfiguration}.
 * 
 * @author Andrew Vojak
 */
public class VMRunnerConfigurationFactory {

	/**
	 * Creates a new {@link VMRunnerConfiguration}.
	 * 
	 * @param classToLaunch
	 *            The class to launch.
	 * @param classPath
	 *            The class path.
	 * @return The new {@link VMRunnerConfiguration}.
	 * 
	 * @see VMRunnerConfiguration#VMRunnerConfiguration(String, String[])
	 */
	public VMRunnerConfiguration create(final String classToLaunch, final String[] classPath) {
		return new VMRunnerConfiguration(classToLaunch, classPath);
	}

}
