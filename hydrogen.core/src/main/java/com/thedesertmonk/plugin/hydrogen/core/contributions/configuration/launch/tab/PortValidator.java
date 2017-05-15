package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

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
			final int portNumber = Integer.valueOf(portString);
			if (portNumber < 1024 || portNumber > 0xFFFF) {
				return false;
			}
		} catch (final NumberFormatException e) {
			return false;
		}
		return true;
	}

}
