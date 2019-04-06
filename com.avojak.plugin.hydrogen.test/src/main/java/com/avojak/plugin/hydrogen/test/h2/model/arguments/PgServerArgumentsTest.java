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
import com.avojak.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;

/**
 * Test class for {@link PgServerArguments}.
 *
 * @author Andrew Vojak
 */
public class PgServerArgumentsTest {

	private final String startPg = "startPg";
	private final String allowOthers = "allowOthers";
	private final String useDaemonThread = "useDaemonThread";
	private final String port = "port";

	/**
	 * Tests that the constructor throws an exception when the given startPg
	 * argument is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullStartPg() {
		new PgServerArguments((String) null, allowOthers, useDaemonThread, port);
	}

	/**
	 * Tests {@link PgServerArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		final PgServerArguments arguments = new PgServerArguments(startPg, allowOthers, useDaemonThread, port);
		final List<String> expectedList = asList(startPg, allowOthers, useDaemonThread, ServerOption.PG_PORT.getParam(),
				port);
		assertEquals(expectedList, arguments.getArguments());
	}

	/**
	 * Tests the getter methods.
	 */
	@Test
	public void testGetters() {
		final PgServerArguments arguments = new PgServerArguments(startPg, allowOthers, useDaemonThread, port);
		assertEquals(startPg, arguments.getStartPg());
		assertEquals(of(allowOthers), arguments.getAllowOthers());
		assertEquals(of(useDaemonThread), arguments.getUseDaemonThread());
		assertEquals(of(port), arguments.getPort());
	}

	/**
	 * Tests {@link PgServerArguments#toString()}.
	 */
	@Test
	public void testToString() {
		final List<String> expectedList = asList(startPg, allowOthers, useDaemonThread, ServerOption.PG_PORT.getParam(),
				port);
		assertEquals(
				"PgServerArguments [arguments=" + expectedList + ", startPg=" + startPg + ", allowOthers=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						+ of(allowOthers) + ", useDaemonThread=" + of(useDaemonThread) + ", port=" + of(port) + "]",
				new PgServerArguments(startPg, allowOthers, useDaemonThread, port).toString());
	}

	/**
	 * Tests {@link PgServerArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final PgServerArguments arguments1 = new PgServerArguments(startPg, allowOthers, useDaemonThread, port);
		final PgServerArguments arguments2 = new PgServerArguments("other", allowOthers, useDaemonThread, port);
		final PgServerArguments arguments3 = new PgServerArguments(startPg, "other", useDaemonThread, port);
		final PgServerArguments arguments4 = new PgServerArguments(startPg, allowOthers, "other", port);
		final PgServerArguments arguments5 = new PgServerArguments(startPg, allowOthers, useDaemonThread, "other");

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments3.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments4.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments5.hashCode());
	}

	/**
	 * Tests {@link PgServerArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final PgServerArguments arguments1 = new PgServerArguments(startPg, allowOthers, useDaemonThread, port);
		final PgServerArguments arguments2 = new PgServerArguments(startPg, allowOthers, useDaemonThread, port);
		final PgServerArguments arguments3 = new PgServerArguments("other", allowOthers, useDaemonThread, port);
		final PgServerArguments arguments4 = new PgServerArguments(startPg, "other", useDaemonThread, port);
		final PgServerArguments arguments5 = new PgServerArguments(startPg, allowOthers, "other", port);
		final PgServerArguments arguments6 = new PgServerArguments(startPg, allowOthers, useDaemonThread, "other");

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertFalse(arguments1.equals(arguments4));
		assertFalse(arguments1.equals(arguments5));
		assertFalse(arguments1.equals(arguments6));
		assertTrue(arguments1.equals(arguments2));
	}

}
