/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * @author andrewvojak
 * @param <T>
 *
 */
public class LaunchConfigurationAttribute<T> {

	private final ServerOption serverOption;
	private final T defaultValue;

	/**
	 * Constructor.
	 *
	 * @param serverOption The corresponding {@link ServerOption}. Cannot be
	 *            null.
	 * @param defaultValue The default value. Cannot be null.
	 */
	public LaunchConfigurationAttribute(final ServerOption serverOption, final T defaultValue) {
		if (serverOption == null) {
			throw new IllegalArgumentException("serverOption cannot be null"); //$NON-NLS-1$
		}
		if (defaultValue == null) {
			throw new IllegalArgumentException("defaultValue cannot be null"); //$NON-NLS-1$
		}
		this.serverOption = serverOption;
		this.defaultValue = defaultValue;
	}

	/**
	 * Gets the {@link ServerOption}.
	 *
	 * @return The non-null {@link ServerOption}.
	 */
	public ServerOption getServerOption() {
		return serverOption;
	}

	/**
	 * Gets the default value.
	 *
	 * @return The non-null default value.
	 */
	public T getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Gets the name of this attribute.
	 * 
	 * @return The non-null, non-empty name of the attribute.
	 */
	public String getName() {
		return getServerOption().name();
	}

}
