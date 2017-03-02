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

	private final ILaunchConfiguration configuration;
	private final HydrogenServerArguments arguments;

	/**
	 * Constructor.
	 *
	 * @param configuration The {@link ILaunchConfiguration}. Cannot be null.
	 */
	public ProgramArguments(final ILaunchConfiguration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("configuration cannot be null"); //$NON-NLS-1$
		}
		this.configuration = configuration;

		final HydrogenServerArgumentsBuilder hydrogenServerArgumentsBuilder = new HydrogenServerArgumentsBuilder();
		try {
			if (verifyBooleanAttribute(LaunchConfigurationAttributes.START_WEB)) {
				hydrogenServerArgumentsBuilder.withWebServer(buildWebServerArguments());
			}
			if (verifyBooleanAttribute(LaunchConfigurationAttributes.START_TCP)) {
				hydrogenServerArgumentsBuilder.withTcpServer(buildTcpServerArguments());
			}
			if (verifyBooleanAttribute(LaunchConfigurationAttributes.START_PG)) {
				hydrogenServerArgumentsBuilder.withPgServer(buildPgServerArguments());
			}
			if (verifyBooleanAttribute(LaunchConfigurationAttributes.ENABLE_TRACING)) {
				hydrogenServerArgumentsBuilder.enableTracing();
			}
			if (verifyBooleanAttribute(LaunchConfigurationAttributes.IF_EXISTS)) {
				hydrogenServerArgumentsBuilder.onlyOpenExistingDatabases();
			}
		} catch (final CoreException e) {
			throw new RuntimeException("Unable to retrieve attributes", e); //$NON-NLS-1$
		}

		arguments = hydrogenServerArgumentsBuilder.build();
	}

	private boolean verifyBooleanAttribute(final LaunchConfigurationAttribute<Boolean> attribute) throws CoreException {
		verifyAttribute(attribute);
		return (Boolean) configuration.getAttributes().get(attribute.getName());
	}

	private String verifyStringAttribute(final LaunchConfigurationAttribute<String> attribute) throws CoreException {
		verifyAttribute(attribute);
		return (String) configuration.getAttributes().get(attribute.getName());
	}

	private void verifyAttribute(final LaunchConfigurationAttribute<?> attribute) throws CoreException {
		if (!configuration.hasAttribute(attribute.getName())) {
			throw new IllegalStateException("Launch configuration attribute not found [" + attribute.getName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private WebServerArguments buildWebServerArguments() throws CoreException {
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder();
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS)) {
			builder.allowOthers();
		}
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.WEB_DAEMON)) {
			builder.useDaemonThread();
		}
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.WEB_SSL)) {
			builder.useSsl();
		}
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.WEB_BROWSER)) {
			builder.openBrowser();
		}
		builder.withPort(verifyStringAttribute(LaunchConfigurationAttributes.WEB_PORT));
		return builder.build();
	}

	private TcpServerArguments buildTcpServerArguments() throws CoreException {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder();
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_SSL)) {
			builder.useSsl();
		}
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_ALLOW_OTHERS)) {
			builder.allowOthers();
		}
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_DAEMON)) {
			builder.useDaemonThread();
		}
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE)) {
			builder.forceShutdown();
		}
		builder.withPort(verifyStringAttribute(LaunchConfigurationAttributes.TCP_PORT));
		return builder.build();
	}

	private PgServerArguments buildPgServerArguments() throws CoreException {
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder();
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS)) {
			builder.allowOthers();
		}
		if (verifyBooleanAttribute(LaunchConfigurationAttributes.PG_DAEMON)) {
			builder.useDaemonThread();
		}
		builder.withPort(verifyStringAttribute(LaunchConfigurationAttributes.PG_PORT));
		return builder.build();
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
		return "ProgramArguments [configuration=" + configuration + ", arguments=" + arguments + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
