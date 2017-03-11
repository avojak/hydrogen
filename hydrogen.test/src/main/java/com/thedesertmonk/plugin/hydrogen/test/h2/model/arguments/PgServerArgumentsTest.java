package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;

/**
 * Test class for {@link PgServerArguments}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class PgServerArgumentsTest {

	private final List<String> arguments = singletonList("arg");

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link List} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullArguments() {
		new PgServerArguments((List<String>) null);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link List} is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_EmptyArguments() {
		new PgServerArguments(emptyList());
	}

	/**
	 * Tests {@link PgServerArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		assertEquals(arguments, new PgServerArguments(arguments).getArguments());
	}

	/**
	 * Tests {@link PgServerArguments#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("PgServerArguments [arguments=" + arguments.toString() + "]",
				new PgServerArguments(arguments).toString());
	}

	/**
	 * Tests {@link PgServerArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final PgServerArguments arguments1 = new PgServerArguments(arguments);
		final PgServerArguments arguments2 = new PgServerArguments(singletonList("arg2"));

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
	}

	/**
	 * Tests {@link PgServerArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final PgServerArguments arguments1 = new PgServerArguments(arguments);
		final PgServerArguments arguments2 = new PgServerArguments(arguments);
		final PgServerArguments arguments3 = new PgServerArguments(singletonList("arg2"));

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertTrue(arguments1.equals(arguments2));
	}

}
