package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;

/**
 * Test class for {@link TcpServerArguments}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class TcpServerArgumentsTest {

	private final List<String> arguments = singletonList("arg");

	/**
	 * Tests that the constructor throws an exception when the {@link List} of
	 * arguments is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullArguments() {
		new TcpServerArguments((List<String>) null);
	}

	/**
	 * Tests that the constructor throws an exception when the {@link List} of
	 * arguments is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_EmptyArguments() {
		new TcpServerArguments(emptyList());
	}

	/**
	 * Tests {@link TcpServerArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		assertEquals(arguments, new TcpServerArguments(arguments).getArguments());
	}

	/**
	 * Tests {@link TcpServerArguments#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("TcpServerArguments [arguments=" + arguments.toString() + "]",
				new TcpServerArguments(arguments).toString());
	}

	/**
	 * Tests {@link TcpServerArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final TcpServerArguments arguments1 = new TcpServerArguments(arguments);
		final TcpServerArguments arguments2 = new TcpServerArguments(singletonList("arg2"));

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
	}

	/**
	 * Tests {@link TcpServerArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final TcpServerArguments arguments1 = new TcpServerArguments(arguments);
		final TcpServerArguments arguments2 = new TcpServerArguments(arguments);
		final TcpServerArguments arguments3 = new TcpServerArguments(singletonList("arg2"));

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertTrue(arguments1.equals(arguments2));
	}

}
