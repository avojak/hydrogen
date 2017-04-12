package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttribute;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;

/**
 * Encapsulates the {@link HydrogenServerArguments} as well as any other
 * arguments required from the configuration.
 *
 * @author Andrew Vojak
 */
public class ProgramArguments {

	private final WebServerArgumentsBuilder webServerArgumentsBuilder;
	private final TcpServerArgumentsBuilder tcpServerArgumentsBuilder;
	private final PgServerArgumentsBuilder pgServerArgumentsBuilder;
	private final ILaunchConfiguration configuration;
	private final HydrogenServerArguments arguments;

	/**
	 * Constructor.
	 *
	 * @param hydrogenServerArgumentsBuilder
	 * @param webServerArgumentsBuilder
	 * @param tcpServerArgumentsBuilder
	 * @param pgServerArgumentsBuilder
	 *
	 * @param configuration The {@link ILaunchConfiguration}. Cannot be null.
	 */
	public ProgramArguments(final HydrogenServerArgumentsBuilder hydrogenServerArgumentsBuilder,
			final WebServerArgumentsBuilder webServerArgumentsBuilder,
			final TcpServerArgumentsBuilder tcpServerArgumentsBuilder,
			final PgServerArgumentsBuilder pgServerArgumentsBuilder, final ILaunchConfiguration configuration) {
		if (hydrogenServerArgumentsBuilder == null) {
			throw new IllegalArgumentException("hydrogenServerArgumentsBuilder cannot be null"); //$NON-NLS-1$
		}
		if (webServerArgumentsBuilder == null) {
			throw new IllegalArgumentException("webServerArgumentsBuilder cannot be null"); //$NON-NLS-1$
		}
		if (tcpServerArgumentsBuilder == null) {
			throw new IllegalArgumentException("tcpServerArgumentsBuilder cannot be null"); //$NON-NLS-1$
		}
		if (pgServerArgumentsBuilder == null) {
			throw new IllegalArgumentException("pgServerArgumentsBuilder cannot be null"); //$NON-NLS-1$
		}
		if (configuration == null) {
			throw new IllegalArgumentException("configuration cannot be null"); //$NON-NLS-1$
		}
		this.webServerArgumentsBuilder = webServerArgumentsBuilder;
		this.tcpServerArgumentsBuilder = tcpServerArgumentsBuilder;
		this.pgServerArgumentsBuilder = pgServerArgumentsBuilder;
		this.configuration = configuration;

		try {
			if (getBooleanAttribute(LaunchConfigurationAttributes.START_WEB)) {
				hydrogenServerArgumentsBuilder.withWebServer(buildWebServerArguments());
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.START_TCP)) {
				hydrogenServerArgumentsBuilder.withTcpServer(buildTcpServerArguments());
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.START_PG)) {
				hydrogenServerArgumentsBuilder.withPgServer(buildPgServerArguments());
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.ENABLE_TRACING)) {
				hydrogenServerArgumentsBuilder.enableTracing();
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.IF_EXISTS)) {
				hydrogenServerArgumentsBuilder.onlyOpenExistingDatabases();
			}
		} catch (final CoreException e) {
			throw new RuntimeException("Unable to retrieve attributes", e); //$NON-NLS-1$
		}

		arguments = hydrogenServerArgumentsBuilder.build();
	}

	private boolean getBooleanAttribute(final LaunchConfigurationAttribute<Boolean> attribute) throws CoreException {
		verifyAttribute(attribute);
		return configuration.getAttribute(attribute.getName(), attribute.getDefaultValue().booleanValue());
	}

	private String getStringAttribute(final LaunchConfigurationAttribute<String> attribute) throws CoreException {
		verifyAttribute(attribute);
		return configuration.getAttribute(attribute.getName(), attribute.getDefaultValue());
	}

	private void verifyAttribute(final LaunchConfigurationAttribute<?> attribute) throws CoreException {
		if (!configuration.hasAttribute(attribute.getName())) {
			throw new IllegalStateException("Launch configuration attribute not found [" + attribute.getName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private WebServerArguments buildWebServerArguments() throws CoreException {
		if (getBooleanAttribute(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS)) {
			webServerArgumentsBuilder.allowOthers();
		}
		if (getBooleanAttribute(LaunchConfigurationAttributes.WEB_DAEMON)) {
			webServerArgumentsBuilder.useDaemonThread();
		}
		if (getBooleanAttribute(LaunchConfigurationAttributes.WEB_SSL)) {
			webServerArgumentsBuilder.useSsl();
		}
		if (getBooleanAttribute(LaunchConfigurationAttributes.WEB_BROWSER)) {
			webServerArgumentsBuilder.openBrowser();
		}
		webServerArgumentsBuilder.withPort(getStringAttribute(LaunchConfigurationAttributes.WEB_PORT));
		return webServerArgumentsBuilder.build();
	}

	private TcpServerArguments buildTcpServerArguments() throws CoreException {
		if (getBooleanAttribute(LaunchConfigurationAttributes.TCP_SSL)) {
			tcpServerArgumentsBuilder.useSsl();
		}
		if (getBooleanAttribute(LaunchConfigurationAttributes.TCP_ALLOW_OTHERS)) {
			tcpServerArgumentsBuilder.allowOthers();
		}
		if (getBooleanAttribute(LaunchConfigurationAttributes.TCP_DAEMON)) {
			tcpServerArgumentsBuilder.useDaemonThread();
		}
		if (getBooleanAttribute(LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE)) {
			tcpServerArgumentsBuilder.forceShutdown();
		}
		tcpServerArgumentsBuilder.withPort(getStringAttribute(LaunchConfigurationAttributes.TCP_PORT));
		return tcpServerArgumentsBuilder.build();
	}

	private PgServerArguments buildPgServerArguments() throws CoreException {
		if (getBooleanAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS)) {
			pgServerArgumentsBuilder.allowOthers();
		}
		if (getBooleanAttribute(LaunchConfigurationAttributes.PG_DAEMON)) {
			pgServerArgumentsBuilder.useDaemonThread();
		}
		pgServerArgumentsBuilder.withPort(getStringAttribute(LaunchConfigurationAttributes.PG_PORT));
		return pgServerArgumentsBuilder.build();
	}

	/**
	 * Gets the {@link HydrogenServerArguments}.
	 *
	 * @return The non-null {@link HydrogenServerArguments}.
	 */
	public HydrogenServerArguments getArguments() {
		return arguments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ProgramArguments [arguments=" + arguments + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + arguments.hashCode();
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
		final ProgramArguments other = (ProgramArguments) obj;
		if (!arguments.equals(other.arguments)) {
			return false;
		}
		return true;
	}

}
