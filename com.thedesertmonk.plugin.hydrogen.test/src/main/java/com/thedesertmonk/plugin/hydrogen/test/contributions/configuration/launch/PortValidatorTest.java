package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.PortValidator;

/**
 * Test class for {@link PortValidator}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class PortValidatorTest {

	/**
	 * Tests that {@link PortValidator#isValid(String)} returns {@code false}
	 * when the given port String is {@code null}.
	 */
	@Test
	public void testIsValid_NullString() {
		assertFalse(PortValidator.isValid((String) null));
	}

	/**
	 * Tests that {@link PortValidator#isValid(String)} returns {@code false}
	 * when the given port String is empty.
	 */
	@Test
	public void testIsValid_EmptyString() {
		assertFalse(PortValidator.isValid(" "));
	}

	/**
	 * Tests that {@link PortValidator#isValid(String)} returns {@code false}
	 * when the given port String is not in valid {@link Integer} format.
	 */
	@Test
	public void testIsValid_BadFormat() {
		assertFalse(PortValidator.isValid("!@#$%&"));
	}

	/**
	 * Tests that {@link PortValidator#isValid(String)} returns {@code false}
	 * when the given port String is too low.
	 */
	@Test
	public void testIsValid_PortNumberTooLow() {
		assertFalse(PortValidator.isValid("1023"));
	}

	/**
	 * Tests that {@link PortValidator#isValid(String)} returns {@code true}
	 * when the given port String is the minimum acceptable port number.
	 */
	@Test
	public void testIsValid_MinPortNumber() {
		assertTrue(PortValidator.isValid("1024"));
	}

	/**
	 * Tests that {@link PortValidator#isValid(String)} returns {@code true}
	 * when the given port String is the maximum acceptable port number.
	 */
	@Test
	public void testIsValid_MaxPortNumber() {
		assertTrue(PortValidator.isValid("65535"));
	}

	/**
	 * Tests that {@link PortValidator#isValid(String)} returns {@code false}
	 * when the given port String is too high.
	 */
	@Test
	public void testIsValid_PortNumberTooHigh() {
		assertFalse(PortValidator.isValid("65536"));
	}

}
