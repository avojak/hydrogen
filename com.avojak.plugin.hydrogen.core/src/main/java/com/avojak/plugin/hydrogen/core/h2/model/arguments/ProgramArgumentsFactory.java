package com.avojak.plugin.hydrogen.core.h2.model.arguments;

import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * Factory class to create instances of {@link ProgramArguments}.
 *
 * @author Andrew Vojak
 */
public class ProgramArgumentsFactory {

	/**
	 * Creates and returns a new instance of {@link ProgramArguments}.
	 *
	 * @param configuration The {@link ILaunchConfiguration}. Cannot be null.
	 *
	 * @return The non-null {@link ProgramArguments}.
	 */
	public ProgramArguments create(final ILaunchConfiguration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("configuration cannot be null"); //$NON-NLS-1$
		}
		return new ProgramArguments(new HydrogenRuntimeArgumentsBuilder(), new WebServerArgumentsBuilder(),
				new TcpServerArgumentsBuilder(), new PgServerArgumentsBuilder(), configuration);
	}

	/**
	 * Creates and returns a new instance of {@link ProgramArguments}.
	 *
	 * @param webServerArguments The {@link WebServerArguments}.
	 * @param tcpServerArguments The {@lik TcpServerArguments}.
	 * @param pgServerArguments The {@link PgServerArguments}.
	 * @return The non-null {@link ProgramArguments}.
	 */
	public ProgramArguments create(final WebServerArguments webServerArguments,
			final TcpServerArguments tcpServerArguments, final PgServerArguments pgServerArguments) {
		return new ProgramArguments(new HydrogenRuntimeArgumentsBuilder(), webServerArguments, tcpServerArguments,
				pgServerArguments);
	}

}
