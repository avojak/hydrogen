/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author andrewvojak
 *
 */
public class JDBCTest {

	private Connection connection;
	private Server server;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Register the driver
		try {
			Class.forName("org.h2.Driver");
		} catch (final ClassNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		// server = Server.createTcpServer().start();
		server = Server.createWebServer().start();
		System.out.println(server.getStatus());
		// Create the connection
		try {
			connection = DriverManager.getConnection("jdbc:h2:tcp://10.0.0.9:9092/~/test", "sa", "");
		} catch (final SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// Close the connection
		try {
			System.out.println("closing connection");
			connection.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		server.stop();
	}

	@Test
	public void test() {
		try {
			final Statement statement = connection.createStatement();
			final String sql = "SELECT * FROM MYLOVELYSTUDENTS";
			statement.execute(sql);
			// System.out.println(statement.getResultSet().getString(1));

			while (true) {
				/**
				 * Create the TCP server first, then the web server. Connect to
				 * the TCP server from the web console with the tcp:// address.
				 * Need to look into running multiple servers from the plugin so
				 * that they can all connect to the same database. Maybe also
				 * open a browser view/perspective with the web console in it?
				 * Or that could be a preference to auto-open the web console on
				 * server start.
				 *
				 * http://stackoverflow.com/questions/20768931/how-to-connect-to-a-webserver-mode-h2-database-in-console
				 */
			}
		} catch (final SQLException e1) {
			e1.printStackTrace();
		}
	}

}
