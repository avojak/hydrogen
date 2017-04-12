package com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Models a launch configuration attribute, which consists of a
 * {@link ServerOption} and corresponding value.
 *
 * @author Andrew Vojak
 * @param <T> The type of the value.
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "LaunchConfigurationAttribute [serverOption=" + serverOption + ", defaultValue=" + defaultValue + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + defaultValue.hashCode();
		result = prime * result + serverOption.hashCode();
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final LaunchConfigurationAttribute<?> other = (LaunchConfigurationAttribute<?>) obj;
		if (!defaultValue.equals(other.defaultValue)) {
			return false;
		}
		if (serverOption != other.serverOption) {
			return false;
		}
		return true;
	}

}
