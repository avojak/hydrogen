/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.server;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Optional;

import org.h2.tools.Server;

/**
 * @author andrewvojak
 *
 */
public class ServerDelegate {

	private Optional<Server> server;

	private final PrintStream printStream;

	/**
	 * Constructor
	 *
	 * @param printStream The {@link PrintStream}. Cannot be null.
	 */
	public ServerDelegate(final PrintStream printStream) {
		if (printStream == null) {
			throw new IllegalArgumentException("printStream cannot be null"); //$NON-NLS-1$
		}
		this.printStream = printStream;
		server = Optional.empty();
	}

	/**
	 * <pre>
	 * Summary
	 *
	 * TODO http://www.h2database.com/html/features.html#in_memory_databases
	 *
	 * This plug allows a persistent, in-memory H2 Server for the life of the
	 * JVM or until terminated. This is especially useful if multiple processes
	 * need to access the same database, or the database needs to be accessed
	 * from another computer.
	 *
	 * Might need to make a connection to the database to kick it off. This
	 * would also allow for doing initial database population.
	 *
	 * Might need to start the web server in addition to the TCP server so that
	 * it can also be managed from the browser? What is the PG server for?
	 *
	 * Look into compatibility modes! Would be a good preference page item.
	 * </pre>
	 */

	/**
	 * Starts the server.
	 */
	public void launchServer() {
		if (server.isPresent()) {
			if (server.get().isRunning(true)) {
				System.out.println("Server already running");
			}
			System.out.println("Server instance already present");
			return;
		}
		try {
			printStream.println("> Creating server...");
			server = Optional.of(Server.createWebServer("-trace"));
			server.get().setOut(printStream);
			printStream.println(server.get().getStatus());
			server.get().start();
			printStream.println(server.get().getStatus());
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the server.
	 */
	public void terminateServer() {
		if (!server.isPresent()) {
			System.out.println("Server instance not present");
			return;
		}
		if (!server.get().isRunning(true)) {
			System.out.println("Server already stopped");
			return;
		}
		printStream.println("> Stopping server...");
		server.get().stop();
		printStream.println(server.get().getStatus());
		server = Optional.empty();
	}

	/**
	 * Returns whether or not the server instance is running.
	 *
	 * @return {@code true} if the server instance is running, otherwise
	 *         {@code false}.
	 */
	public boolean isServerRunning() {
		return server.isPresent() && server.get().isRunning(true);
	}

}
