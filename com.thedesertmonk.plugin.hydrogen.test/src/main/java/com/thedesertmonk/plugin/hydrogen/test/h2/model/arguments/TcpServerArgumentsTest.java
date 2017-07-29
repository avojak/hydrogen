package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;

/**
 * Test class for {@link TcpServerArguments}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class TcpServerArgumentsTest {

	private final String startTcp = "startTcp";
	private final String allowOthers = "allowOthers";
	private final String useDaemonThread = "useDaemonThread";
	private final String port = "port";
	private final String useSsl = "useSsl";
	private final String shutdownUrl = "shutdownUrl";
	private final String shutdownPassword = "shutdownPassword";
	private final String forceShutdown = "forceShutdown";

	/**
	 * Tests that the constructor throws an exception when the startTcp argument
	 * is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullStartTcp() {
		new TcpServerArguments((String) null, allowOthers, useDaemonThread, port, useSsl, shutdownUrl, shutdownPassword,
				forceShutdown);
	}

	/**
	 * Tests {@link TcpServerArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		final TcpServerArguments arguments = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final List<String> expectedList = asList(startTcp, allowOthers, useDaemonThread,
				ServerOption.TCP_PORT.getParam(), port, useSsl, ServerOption.TCP_SHUTDOWN_URL.getParam(), shutdownUrl,
				ServerOption.TCP_SHUTDOWN_PASSWORD.getParam(), shutdownPassword, forceShutdown);
		assertEquals(expectedList, arguments.getArguments());
	}

	/**
	 * Tests the getter methods.
	 */
	@Test
	public void testGetters() {
		final TcpServerArguments arguments = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		assertEquals(startTcp, arguments.getStartTcp());
		assertEquals(of(allowOthers), arguments.getAllowOthers());
		assertEquals(of(useDaemonThread), arguments.getUseDaemonThread());
		assertEquals(of(port), arguments.getPort());
		assertEquals(of(useSsl), arguments.getUseSsl());
		assertEquals(of(shutdownUrl), arguments.getShutdownUrl());
		assertEquals(of(shutdownPassword), arguments.getShutdownPassword());
		assertEquals(of(forceShutdown), arguments.getForceShutdown());
	}

	/**
	 * Tests {@link TcpServerArguments#toString()}.
	 */
	@Test
	public void testToString() {
		final List<String> expectedList = asList(startTcp, allowOthers, useDaemonThread,
				ServerOption.TCP_PORT.getParam(), port, useSsl, ServerOption.TCP_SHUTDOWN_URL.getParam(), shutdownUrl,
				ServerOption.TCP_SHUTDOWN_PASSWORD.getParam(), shutdownPassword, forceShutdown);
		assertEquals(
				"TcpServerArguments [arguments=" + expectedList + ", startTcp=" + startTcp + ", allowOthers="
						+ of(allowOthers) + ", useDaemonThread=" + of(useDaemonThread) + ", port=" + of(port)
						+ ", useSsl=" + of(useSsl) + ", shutdownUrl=" + of(shutdownUrl) + ", shutdownPassword="
						+ of(shutdownPassword) + ", forceShutdown=" + of(forceShutdown) + "]",
				new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port, useSsl, shutdownUrl,
						shutdownPassword, forceShutdown).toString());
	}

	/**
	 * Tests {@link TcpServerArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final TcpServerArguments arguments1 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments2 = new TcpServerArguments("other", allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments3 = new TcpServerArguments(startTcp, "other", useDaemonThread, port, useSsl,
				shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments4 = new TcpServerArguments(startTcp, allowOthers, "other", port, useSsl,
				shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments5 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, "other",
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments6 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				"other", shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments7 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, "other", shutdownPassword, forceShutdown);
		final TcpServerArguments arguments8 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, "other", forceShutdown);
		final TcpServerArguments arguments9 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, "other");

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments3.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments4.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments5.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments6.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments7.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments8.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments9.hashCode());
	}

	/**
	 * Tests {@link TcpServerArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final TcpServerArguments arguments1 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments2 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments3 = new TcpServerArguments("other", allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments4 = new TcpServerArguments(startTcp, "other", useDaemonThread, port, useSsl,
				shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments5 = new TcpServerArguments(startTcp, allowOthers, "other", port, useSsl,
				shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments6 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, "other",
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments7 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				"other", shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArguments arguments8 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, "other", shutdownPassword, forceShutdown);
		final TcpServerArguments arguments9 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, "other", forceShutdown);
		final TcpServerArguments arguments10 = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, "other");

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertFalse(arguments1.equals(arguments4));
		assertFalse(arguments1.equals(arguments5));
		assertFalse(arguments1.equals(arguments6));
		assertFalse(arguments1.equals(arguments7));
		assertFalse(arguments1.equals(arguments8));
		assertFalse(arguments1.equals(arguments9));
		assertFalse(arguments1.equals(arguments10));
		assertTrue(arguments1.equals(arguments2));
	}

}
