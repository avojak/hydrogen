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
	 * Starts the server.
	 */
	public void startServer() {
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
	public void stopServer() {
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
