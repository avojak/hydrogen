/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author andrewvojak
 *
 */
public class HydrogenTestClient {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// Register the driver
		try {
			Class.forName("org.h2.Driver");
		} catch (final ClassNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		// Create the connection
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		} catch (final SQLException e) {
			e.printStackTrace();
			return;
		}
		// Execute statements
		try {
			final Statement statement = connection.createStatement();
			final String sql = "CREATE TABLE MYLOVELYSTUDENTS " + "(studentid INTEGER not NULL, "
					+ " first VARCHAR(255), " + " last VARCHAR(255), " + " age INTEGER)";
			statement.executeUpdate(sql);

		} catch (final SQLException e1) {
			e1.printStackTrace();
		} finally {
			// Close the connection
			try {
				connection.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
