package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.HydrogenServerArguments;

/**
 * Test class for {@link HydrogenServerArguments}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class HydrogenServerArgumentsTest {

	private final List<String> argumentStrings = singletonList("arg");

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link List} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullArguments() {
		new HydrogenServerArguments((List<String>) null);
	}

	/**
	 * Tests {@link HydrogenServerArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		assertEquals(argumentStrings, new HydrogenServerArguments(argumentStrings).getArguments());
	}

	/**
	 * Tests {@link HydrogenServerArguments#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("HydrogenServerArguments [arguments=" + argumentStrings.toString() + "]",
				new HydrogenServerArguments(argumentStrings).toString());
	}

	/**
	 * Tests {@link HydrogenServerArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final HydrogenServerArguments arguments1 = new HydrogenServerArguments(argumentStrings);
		final HydrogenServerArguments arguments2 = new HydrogenServerArguments(emptyList());

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
	}

	/**
	 * Tests {@link HydrogenServerArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final HydrogenServerArguments arguments1 = new HydrogenServerArguments(argumentStrings);
		final HydrogenServerArguments arguments2 = new HydrogenServerArguments(argumentStrings);
		final HydrogenServerArguments arguments3 = new HydrogenServerArguments(emptyList());

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertTrue(arguments1.equals(arguments2));
	}

}
