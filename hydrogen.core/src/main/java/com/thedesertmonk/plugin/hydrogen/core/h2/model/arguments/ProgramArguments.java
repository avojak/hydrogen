/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttribute;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;

/**
 * @author andrewvojak
 *
 */
public class ProgramArguments {

	private final ILaunchConfiguration configuration;

	/**
	 * @param configuration
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
			final boolean enableTracing = verifyBooleanAttribute(LaunchConfigurationAttributes.ENABLE_TRACING);
			final boolean ifExists = verifyBooleanAttribute(LaunchConfigurationAttributes.IF_EXISTS);

			final boolean tcpSsl = verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_SSL);
			final boolean tcpAllowOthers = verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_ALLOW_OTHERS);
			final boolean tcpDaemon = verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_DAEMON);
			final boolean tcpShutdownForce = verifyBooleanAttribute(LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE);
			final boolean pgAllowOthers = verifyBooleanAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS);
			final boolean pgDaemon = verifyBooleanAttribute(LaunchConfigurationAttributes.PG_DAEMON);
		} catch (final CoreException e) {
			throw new RuntimeException("Unable to retrieve attributes", e); //$NON-NLS-1$
		}

		// General settings
		// Web settings (if needed)
		// TCP settings (if needed)
		// PG settings (if needed)
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

	private TcpServerArguments buildTcpServerArguments() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder();
		// TODO
		return builder.build();
	}

	private PgServerArguments buildPgServerArguments() {
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder();
		// TODO
		return builder.build();
	}

}
