/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Builder class to create instances of {@link HydrogenServerArguments}.
 *
 * @author andrewvojak
 */
public class HydrogenServerArgumentsBuilder {

	private final List<String> arguments;

	/**
	 * Constructor.
	 */
	public HydrogenServerArgumentsBuilder() {
		arguments = new ArrayList<String>();
	}

	/**
	 * Adds web server arguments.
	 *
	 * @param webServerArguments The {@link WebServerArguments}. Cannot be null.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withWebServer(final WebServerArguments webServerArguments) {
		if (webServerArguments == null) {
			throw new IllegalArgumentException("webServerArguments cannot be null"); //$NON-NLS-1$
		}
		arguments.addAll(webServerArguments.getArguments());
		return this;
	}

	/**
	 * Adds TCP server arguments.
	 *
	 * @param tcpServerArguments The {@link TcpServerArguments}. Cannot be null.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withTcpServer(final TcpServerArguments tcpServerArguments) {
		if (tcpServerArguments == null) {
			throw new IllegalArgumentException("tcpServerArguments cannot be null"); //$NON-NLS-1$
		}
		arguments.addAll(tcpServerArguments.getArguments());
		return this;
	}

	/**
	 * Adds PostgreSQL server arguments.
	 *
	 * @param pgServerArguments The {@link PgServerArguments}. Cannot be null.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withPgServer(final PgServerArguments pgServerArguments) {
		if (pgServerArguments == null) {
			throw new IllegalArgumentException("pgServerArguments cannot be null"); //$NON-NLS-1$
		}
		arguments.addAll(pgServerArguments.getArguments());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#PROPERTIES} property.
	 *
	 * @param propertiesDirectory The directory where the server properties are
	 *            located. Cannot be null or empty.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withProperties(final String propertiesDirectory) {
		if (propertiesDirectory == null || propertiesDirectory.trim().isEmpty()) {
			throw new IllegalArgumentException("propertiesDirectory cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(ServerOption.PROPERTIES.getParam());
		arguments.add(propertiesDirectory);
		return this;
	}

	/**
	 * Sets the {@link ServerOption#BASE_DIRECTORY} property.
	 *
	 * @param databaseDirectory The base directory for H2 databases. Cannot be
	 *            null or empty.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder setBaseDirectory(final String databaseDirectory) {
		if (databaseDirectory == null || databaseDirectory.trim().isEmpty()) {
			throw new IllegalArgumentException("databaseDirectory cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(ServerOption.BASE_DIRECTORY.getParam());
		arguments.add(databaseDirectory);
		return this;
	}

	/**
	 * Sets the {@link ServerOption#IF_EXISTS} property.
	 *
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder onlyOpenExistingDatabases() {
		arguments.add(ServerOption.IF_EXISTS.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#ENABLE_TRACING} property.
	 *
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder enableTracing() {
		arguments.add(ServerOption.ENABLE_TRACING.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#KEY} property.
	 *
	 * @param fromDatabaseName The name of the database to be mapped to a new
	 *            database. Cannot be null or empty.
	 * @param toDatabaseName The name of the database that will be mapped to.
	 *            Cannot be null or empty.
	 * @return The current instance of {@link HydrogenServerArgumentsBuilder}.
	 */
	public HydrogenServerArgumentsBuilder withDatabaseMapping(final String fromDatabaseName,
			final String toDatabaseName) {
		if (fromDatabaseName == null || fromDatabaseName.trim().isEmpty()) {
			throw new IllegalArgumentException("fromDatabaseName cannot be null or empty"); //$NON-NLS-1$
		}
		if (toDatabaseName == null || toDatabaseName.trim().isEmpty()) {
			throw new IllegalArgumentException("toDatabaseName cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(ServerOption.KEY.getParam());
		arguments.add(fromDatabaseName);
		arguments.add(toDatabaseName);
		return this;
	}

	/**
	 * Creates a new instance of {@link HydrogenServerArguments}.
	 *
	 * @return A new, non-null instance of {@link HydrogenServerArguments}.
	 */
	public HydrogenServerArguments build() {
		return new HydrogenServerArguments(arguments);
	}

}
