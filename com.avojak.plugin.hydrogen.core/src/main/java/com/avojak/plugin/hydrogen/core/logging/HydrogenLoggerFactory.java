package com.avojak.plugin.hydrogen.core.logging;

import org.eclipse.core.runtime.ILog;

import com.avojak.plugin.hydrogen.core.HydrogenActivator;

/**
 * Factory class to create instances of {@link IHydrogenLogger}.
 *
 * @author Andrew Vojak
 */
public abstract class HydrogenLoggerFactory {

	private static ILog logger;

	/**
	 * Initializes the factory.
	 *
	 * @param logger The instance of {@link ILog}. Cannot be null.
	 */
	public static void init(@SuppressWarnings("hiding") final ILog logger) {
		if (logger == null) {
			throw new IllegalArgumentException("logger cannot be null"); //$NON-NLS-1$
		}
		HydrogenLoggerFactory.logger = logger;
	}

	/**
	 * Creates a new instance of {@link IHydrogenLogger} for the given class.
	 *
	 * @param clazz The {@link Class}. Cannot be null.
	 * @return A new, non-null instance of {@link IHydrogenLogger} for the given
	 *         class.
	 */
	@SuppressWarnings("rawtypes")
	public static IHydrogenLogger getForClass(final Class clazz) {
		if (logger == null) {
			throw new IllegalStateException("HydrogenLoggerFactory not initialized"); //$NON-NLS-1$
		}
		if (clazz == null) {
			throw new IllegalArgumentException("clazz cannot be null"); //$NON-NLS-1$
		}
		return new EclipsePlatformLogger(logger, HydrogenActivator.PLUGIN_ID, clazz.getSimpleName());
	}

}
