package com.avojak.plugin.hydrogen.core.contributions.configuration.launch;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Factory class to create instances of {@link ServerSocket}.
 *
 * @author Andrew Vojak
 */
public class ServerSocketFactory {

	/**
	 * Creates and returns a new instance of {@link ServerSocket}.
	 *
	 * @param port The port number.
	 * @return The non-null {@link ServerSocket}.
	 * @throws IOException if an I/O error occurs when opening the socket.
	 */
	public ServerSocket create(final int port) throws IOException {
		return new ServerSocket(port);
	}

}
