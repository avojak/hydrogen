package com.avojak.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.avojak.plugin.hydrogen.core.h2.model.arguments.HydrogenRuntimeArguments;

/**
 * Test class for {@link HydrogenRuntimeArguments}.
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
		new HydrogenRuntimeArguments((List<String>) null);
	}

	/**
	 * Tests {@link HydrogenRuntimeArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		assertEquals(argumentStrings, new HydrogenRuntimeArguments(argumentStrings).getArguments());
	}

	/**
	 * Tests {@link HydrogenRuntimeArguments#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("HydrogenServerArguments [arguments=" + argumentStrings.toString() + "]",
				new HydrogenRuntimeArguments(argumentStrings).toString());
	}

	/**
	 * Tests {@link HydrogenRuntimeArguments#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final HydrogenRuntimeArguments arguments1 = new HydrogenRuntimeArguments(argumentStrings);
		final HydrogenRuntimeArguments arguments2 = new HydrogenRuntimeArguments(emptyList());

		assertEquals(arguments1.hashCode(), arguments1.hashCode());
		assertNotEquals(arguments1.hashCode(), arguments2.hashCode());
	}

	/**
	 * Tests {@link HydrogenRuntimeArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final HydrogenRuntimeArguments arguments1 = new HydrogenRuntimeArguments(argumentStrings);
		final HydrogenRuntimeArguments arguments2 = new HydrogenRuntimeArguments(argumentStrings);
		final HydrogenRuntimeArguments arguments3 = new HydrogenRuntimeArguments(emptyList());

		assertTrue(arguments1.equals(arguments1));
		assertFalse(arguments1.equals(null));
		assertFalse(arguments1.equals("String"));
		assertFalse(arguments1.equals(arguments3));
		assertTrue(arguments1.equals(arguments2));
	}

}
