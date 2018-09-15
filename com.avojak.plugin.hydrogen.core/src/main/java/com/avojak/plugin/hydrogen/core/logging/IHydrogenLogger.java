package com.avojak.plugin.hydrogen.core.logging;

/**
 * The Hydrogen logging interface.
 *
 * @author Andrew Vojak
 */
public interface IHydrogenLogger {

	/**
	 * Logs a debug message.
	 *
	 * @param message The human-readable message.
	 */
	public void debug(final String message);

	/**
	 * Logs an informational message.
	 *
	 * @param message The human-readable message.
	 */
	public void info(final String message);

	/**
	 * Logs a warning message.
	 *
	 * @param message The human-readable message.
	 */
	public void warn(final String message);

	/**
	 * Logs an error message.
	 *
	 * @param message The human-readable message.
	 */
	public void error(final String message);

	/**
	 * Logs an error message.
	 *
	 * @param message The human-readable message.
	 * @param exception The low-level exception which has caused the error.
	 *            Cannot be null.
	 */
	public void error(final String message, final Throwable exception);

	/**
	 * Logs a fatal message.
	 *
	 * @param message The human-readable message.
	 */
	public void fatal(final String message);

	/**
	 * Logs a fatal message.
	 *
	 * @param message The human-readable message.
	 * @param exception The low-level exception which has caused the fatality.
	 *            Cannot be null.
	 */
	public void fatal(final String message, final Throwable exception);

}
