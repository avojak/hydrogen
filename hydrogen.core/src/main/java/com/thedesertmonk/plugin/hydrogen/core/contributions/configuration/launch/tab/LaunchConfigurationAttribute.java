/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

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
	 * @param serverOption
	 * @param defaultValue
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
	 * @return
	 */
	public String getName() {
		return getServerOption().name();
	}

}
