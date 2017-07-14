package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Builder class to create instances of {@link PgServerArguments}.
 *
 * @author Andrew Vojak
 */
public class PgServerArgumentsBuilder {

	private String allowOthers;
	private String useDaemonThread;
	private String port;

	/**
	 * Constructs a new {@link PgServerArgumentsBuilder}.
	 */
	public PgServerArgumentsBuilder() {
	}

	/**
	 * Constructs a new {@link PgServerArgumentsBuilder} from existing
	 * {@link PgServerArguments}.
	 *
	 * @param oldArguments The existing {@link PgServerArguments}. Cannot be
	 *            null.
	 */
	public PgServerArgumentsBuilder(final PgServerArguments oldArguments) {
		if (oldArguments == null) {
			throw new IllegalArgumentException("oldArguments cannot be null"); //$NON-NLS-1$
		}
		if (oldArguments.getAllowOthers().isPresent()) {
			this.allowOthers = oldArguments.getAllowOthers().get();
		}
		if (oldArguments.getUseDaemonThread().isPresent()) {
			this.useDaemonThread = oldArguments.getUseDaemonThread().get();
		}
		if (oldArguments.getPort().isPresent()) {
			this.port = oldArguments.getPort().get();
		}
	}

	/**
	 * Sets the {@link ServerOption#PG_ALLOW_OTHERS} property.
	 *
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder allowOthers() {
		allowOthers = ServerOption.PG_ALLOW_OTHERS.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#PG_DAEMON} property.
	 *
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder useDaemonThread() {
		useDaemonThread = ServerOption.PG_DAEMON.getParam();
		return this;
	}

	/**
	 * Sets the {@link ServerOption#PG_PORT} property.
	 *
	 * @param portNumber The port number. Cannot be null or empty.
	 * @return The current {@link PgServerArgumentsBuilder} instance.
	 */
	public PgServerArgumentsBuilder withPort(final String portNumber) {
		if (portNumber == null || portNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("portNumber cannot be null or empty"); //$NON-NLS-1$
		}
		this.port = portNumber;
		return this;
	}

	/**
	 * Creates a new instance of {@link PgServerArguments}.
	 *
	 * @return A new, non-null instance of {@link PgServerArguments}.
	 */
	public PgServerArguments build() {
		final String startPg = ServerOption.START_PG.getParam();
		return new PgServerArguments(startPg, allowOthers, useDaemonThread, port);
	}

	/**
	 * Gets the {@link ServerOption#PG_ALLOW_OTHERS} property if present.
	 *
	 * @return The {@link ServerOption#PG_ALLOW_OTHERS} property if present,
	 *         otherwise {@code null}.
	 */
	public String getAllowOthers() {
		return allowOthers;
	}

	/**
	 * Gets the {@link ServerOption#PG_DAEMON} property if present.
	 *
	 * @return The {@link ServerOption#PG_DAEMON} property if present, otherwise
	 *         {@code null}.
	 */
	public String getUseDaemonThread() {
		return useDaemonThread;
	}

	/**
	 * Gets the port number if present.
	 *
	 * @return The port number if present, otherwise {@code null}.
	 */
	public String getPort() {
		return port;
	}

}
