package com.avojak.plugin.hydrogen.test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author andrewvojak
 *
 */
public class H2Tester {

	private static final String H2_DRIVER_CLASS = "org.h2.Driver";
	private static final String JDBC_URL = "jdbc:h2:ssl://10.0.0.9:9092/~/test";
	private static final String USER = "sa";
	private static final String PASS = "";

	private static final String CREATE_TABLE;
	private static final String INSERT;

	static {
		//@formatter:off
		CREATE_TABLE = "CREATE TABLE CUSTOMERS("
						+ "ID   INT              NOT NULL,"
				   		+ "NAME VARCHAR (20)     NOT NULL,"
				   		+ "AGE  INT              NOT NULL,"
				   		+ "PRIMARY KEY (ID)"
				   		+ ");";
		INSERT = "INSERT INTO CUSTOMERS (ID,NAME,AGE)"
					+ "VALUES (1, 'Andrew', 32);";
		//@formatter:on
	}

	/**
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String... args) throws Exception {
		Class.forName(H2_DRIVER_CLASS);
		final Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
		connection.prepareStatement(CREATE_TABLE).execute();
		connection.prepareStatement(INSERT).execute();
		connection.close();
	}

}
