package com.avojak.plugin.hydrogen.core.h2.model.arguments;

import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.avojak.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttribute;
import com.avojak.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;

/**
 * Encapsulates the {@link HydrogenRuntimeArguments} as well as any other
 * arguments required from the configuration.
 *
 * @author Andrew Vojak
 */
public class ProgramArguments {

	private final WebServerArgumentsBuilder webServerArgumentsBuilder;
	private final TcpServerArgumentsBuilder tcpServerArgumentsBuilder;
	private final PgServerArgumentsBuilder pgServerArgumentsBuilder;

	private final Optional<WebServerArguments> webServerArguments;
	private final Optional<TcpServerArguments> tcpServerArguments;
	private final Optional<PgServerArguments> pgServerArguments;

	private final ILaunchConfiguration configuration;

	private final HydrogenRuntimeArguments arguments;

	/**
	 * Constructor.
	 *
	 * @param hydrogenRuntimeArgumentsBuilder The
	 *            {@link HydrogenRuntimeArgumentsBuilder}. Cannot be null.
	 * @param webServerArgumentsBuilder The {@link WebServerArgumentsBuilder}.
	 *            Cannot be null.
	 * @param tcpServerArgumentsBuilder The {@link TcpServerArgumentsBuilder}.
	 *            Cannot be null.
	 * @param pgServerArgumentsBuilder The {@link PgServerArgumentsBuilder}.
	 *            Cannot be null.
	 * @param configuration The {@link ILaunchConfiguration}. Cannot be null.
	 */
	public ProgramArguments(final HydrogenRuntimeArgumentsBuilder hydrogenRuntimeArgumentsBuilder,
			final WebServerArgumentsBuilder webServerArgumentsBuilder,
			final TcpServerArgumentsBuilder tcpServerArgumentsBuilder,
			final PgServerArgumentsBuilder pgServerArgumentsBuilder, final ILaunchConfiguration configuration) {
		if (hydrogenRuntimeArgumentsBuilder == null) {
			throw new IllegalArgumentException("hydrogenRuntimeArgumentsBuilder cannot be null"); //$NON-NLS-1$
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
				webServerArguments = Optional.of(buildWebServerArguments());
				hydrogenRuntimeArgumentsBuilder.withWebServer(webServerArguments.get());
			} else {
				webServerArguments = Optional.empty();
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.START_TCP)) {
				tcpServerArguments = Optional.of(buildTcpServerArguments());
				hydrogenRuntimeArgumentsBuilder.withTcpServer(tcpServerArguments.get());
			} else {
				tcpServerArguments = Optional.empty();
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.START_PG)) {
				pgServerArguments = Optional.of(buildPgServerArguments());
				hydrogenRuntimeArgumentsBuilder.withPgServer(pgServerArguments.get());
			} else {
				pgServerArguments = Optional.empty();
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.ENABLE_TRACING)) {
				hydrogenRuntimeArgumentsBuilder.enableTracing();
			}
			if (getBooleanAttribute(LaunchConfigurationAttributes.IF_EXISTS)) {
				hydrogenRuntimeArgumentsBuilder.onlyOpenExistingDatabases();
			}
		} catch (final CoreException e) {
			throw new RuntimeException("Unable to retrieve attributes", e); //$NON-NLS-1$
		}

		arguments = hydrogenRuntimeArgumentsBuilder.build();
	}

	/**
	 * Constructor.
	 *
	 * @param hydrogenRuntimeArgumentsBuilder The
	 *            {@link HydrogenRuntimeArgumentsBuilder}. Cannot be null.
	 * @param webServerArguments The existing {@link WebServerArguments}. May be
	 *            null.
	 * @param tcpServerArguments The existing {@link TcpServerArguments}. May be
	 *            null.
	 * @param pgServerArguments The existing {@link PgServerArguments}. May be
	 *            null.
	 */
	public ProgramArguments(final HydrogenRuntimeArgumentsBuilder hydrogenRuntimeArgumentsBuilder,
			final WebServerArguments webServerArguments, final TcpServerArguments tcpServerArguments,
			final PgServerArguments pgServerArguments) {
		if (hydrogenRuntimeArgumentsBuilder == null) {
			throw new IllegalArgumentException("hydrogenRuntimeArgumentsBuilder cannot be null"); //$NON-NLS-1$
		}
		this.webServerArgumentsBuilder = null;
		this.tcpServerArgumentsBuilder = null;
		this.pgServerArgumentsBuilder = null;
		this.configuration = null;
		this.webServerArguments = Optional.ofNullable(webServerArguments);
		this.tcpServerArguments = Optional.ofNullable(tcpServerArguments);
		this.pgServerArguments = Optional.ofNullable(pgServerArguments);
		if (this.webServerArguments.isPresent()) {
			hydrogenRuntimeArgumentsBuilder.withWebServer(webServerArguments);
		}
		if (this.tcpServerArguments.isPresent()) {
			hydrogenRuntimeArgumentsBuilder.withTcpServer(tcpServerArguments);
		}
		if (this.pgServerArguments.isPresent()) {
			hydrogenRuntimeArgumentsBuilder.withPgServer(pgServerArguments);
		}
		arguments = hydrogenRuntimeArgumentsBuilder.build();
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
	 * Gets the {@link HydrogenRuntimeArguments}.
	 *
	 * @return The non-null {@link HydrogenRuntimeArguments}.
	 */
	public HydrogenRuntimeArguments getArguments() {
		return arguments;
	}

	/**
	 * Gets the {@link WebServerArguments}.
	 *
	 * @return The {@link Optional} {@link WebServerArguments}.
	 */
	public Optional<WebServerArguments> getWebServerArguments() {
		return webServerArguments;
	}

	/**
	 * Gets the {@link TcpServerArguments}.
	 *
	 * @return The {@link Optional} {@link TcpServerArguments}.
	 */
	public Optional<TcpServerArguments> getTcpServerArguments() {
		return tcpServerArguments;
	}

	/**
	 * Gets the {@link PgServerArguments}.
	 *
	 * @return The {@link Optional} {@link PgServerArguments}.
	 */
	public Optional<PgServerArguments> getPgServerArguments() {
		return pgServerArguments;
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
