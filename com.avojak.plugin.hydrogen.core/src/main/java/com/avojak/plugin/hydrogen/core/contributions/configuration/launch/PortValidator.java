package com.avojak.plugin.hydrogen.core.contributions.configuration.launch;

/**
 * Class to validate port numbers.
 *
 * @author Andrew Vojak
 */
public class PortValidator {

	/**
	 * Returns whether or not the given port String is a valid port number.
	 *
	 * @param portString The port String.
	 * @return {@code true} if the given port String is valid, otherwise
	 *         {@code false}. A {@code null} or empty String will return
	 *         {@code false}.
	 */
	public static boolean isValid(final String portString) {
		if (portString == null || portString.trim().isEmpty()) {
			return false;
		}
		try {
			return isValid(Integer.valueOf(portString));
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Returns whether or not the given port is a valid port number.
	 *
	 * @param portNumber The port number.
	 * @return {@code true} if the given port is valid, otherwise {@code false}.
	 */
	public static boolean isValid(final int portNumber) {
		if (portNumber < 1024 || portNumber > 0xFFFF) {
			return false;
		}
		return true;
	}

}
