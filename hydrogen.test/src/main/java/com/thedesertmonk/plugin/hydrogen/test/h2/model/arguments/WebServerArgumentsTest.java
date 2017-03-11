package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;

/**
 * Test class for {@link WebServerArguments}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class WebServerArgumentsTest {

	private final List<String> arguments = singletonList("arg");

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link List} of arguments is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullArguments() {
		new WebServerArguments((List<String>) null);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link List} of arguments is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_EmptyArguments() {
		new WebServerArguments(emptyList());
	}

	/**
	 * Tests {@link WebServerArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		assertEquals(arguments, new WebServerArguments(arguments).getArguments());
	}

	/**
	 * Tests {@link WebServerArguments#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("WebServerArguments [arguments=" + arguments.toString() + "]",
				new WebServerArguments(arguments).toString());
	}

	/**
	 * Tests {@link WebServerArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final WebServerArguments arguments1 = new WebServerArguments(arguments);
		final WebServerArguments arguments2 = new WebServerArguments(singletonList("arg2"));

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
	}

	/**
	 * Tests {@link WebServerArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final WebServerArguments arguments1 = new WebServerArguments(arguments);
		final WebServerArguments arguments2 = new WebServerArguments(arguments);
		final WebServerArguments arguments3 = new WebServerArguments(singletonList("arg2"));

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertTrue(arguments1.equals(arguments2));
	}

}
