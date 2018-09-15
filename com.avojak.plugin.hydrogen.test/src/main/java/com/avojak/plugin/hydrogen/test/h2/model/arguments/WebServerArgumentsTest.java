package com.avojak.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;

/**
 * Test class for {@link WebServerArguments}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class WebServerArgumentsTest {

	private final String startWeb = "startWeb";
	private final String allowOthers = "allowOthers";
	private final String useDaemonThread = "useDaemonThread";
	private final String port = "port";
	private final String useSsl = "useSsl";
	private final String openBrowser = "openBrowser";

	/**
	 * Tests that the constructor throws an exception when the given startWeb
	 * {@link String} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullStartWeb() {
		new WebServerArguments((String) null, allowOthers, useDaemonThread, port, useSsl, openBrowser);
	}

	/**
	 * Tests {@link WebServerArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		final WebServerArguments arguments = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		final List<String> expectedList = asList(startWeb, allowOthers, useDaemonThread,
				ServerOption.WEB_PORT.getParam(), port, useSsl, openBrowser);
		assertEquals(expectedList, arguments.getArguments());
	}

	/**
	 * Tests the getter methods.
	 */
	@Test
	public void testGetters() {
		final WebServerArguments arguments = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		assertEquals(startWeb, arguments.getStartWeb());
		assertEquals(of(allowOthers), arguments.getAllowOthers());
		assertEquals(of(useDaemonThread), arguments.getUseDaemonThread());
		assertEquals(of(port), arguments.getPort());
		assertEquals(of(useSsl), arguments.getUseSsl());
		assertEquals(of(openBrowser), arguments.getOpenBrowser());
	}

	/**
	 * Tests {@link WebServerArguments#toString()}.
	 */
	@Test
	public void testToString() {
		final List<String> expectedList = asList(startWeb, allowOthers, useDaemonThread,
				ServerOption.WEB_PORT.getParam(), port, useSsl, openBrowser);
		assertEquals(
				"WebServerArguments [arguments=" + expectedList + ", startWeb=" + startWeb + ", allowOthers="
						+ of(allowOthers) + ", useDaemonThread=" + of(useDaemonThread) + ", port=" + of(port)
						+ ", useSsl=" + of(useSsl) + ", openBrowser=" + of(openBrowser) + "]",
				new WebServerArguments(startWeb, allowOthers, useDaemonThread, port, useSsl, openBrowser).toString());
	}

	/**
	 * Tests {@link WebServerArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final WebServerArguments arguments1 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		final WebServerArguments arguments2 = new WebServerArguments("other", allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		final WebServerArguments arguments3 = new WebServerArguments(startWeb, "other", useDaemonThread, port, useSsl,
				openBrowser);
		final WebServerArguments arguments4 = new WebServerArguments(startWeb, allowOthers, "other", port, useSsl,
				openBrowser);
		final WebServerArguments arguments5 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, "other",
				useSsl, openBrowser);
		final WebServerArguments arguments6 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				"other", openBrowser);
		final WebServerArguments arguments7 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, "other");

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments3.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments4.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments5.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments6.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments7.hashCode());
	}

	/**
	 * Tests {@link WebServerArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final WebServerArguments arguments1 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		final WebServerArguments arguments2 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		final WebServerArguments arguments3 = new WebServerArguments("other", allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		final WebServerArguments arguments4 = new WebServerArguments(startWeb, "other", useDaemonThread, port, useSsl,
				openBrowser);
		final WebServerArguments arguments5 = new WebServerArguments(startWeb, allowOthers, "other", port, useSsl,
				openBrowser);
		final WebServerArguments arguments6 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, "other",
				useSsl, openBrowser);
		final WebServerArguments arguments7 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				"other", openBrowser);
		final WebServerArguments arguments8 = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, "other");

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertFalse(arguments1.equals(arguments4));
		assertFalse(arguments1.equals(arguments5));
		assertFalse(arguments1.equals(arguments6));
		assertFalse(arguments1.equals(arguments7));
		assertFalse(arguments1.equals(arguments8));
		assertTrue(arguments1.equals(arguments2));
	}

}
