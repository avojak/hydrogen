package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.exception.NoAvailablePortException;

/**
 * Test class for {@link NoAvailablePortException}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class NoAvailablePortExceptionTest {

	private final String message = "message";
	private final Throwable cause = new Throwable();

	/**
	 * Tests {@link NoAvailablePortException#NoAvailablePortException()}.
	 */
	@Test
	public void testConstructor_NoArgs() {
		final NoAvailablePortException exception = new NoAvailablePortException();
		assertNull(exception.getCause());
		assertNull(exception.getMessage());
	}

	/**
	 * Tests {@link NoAvailablePortException#NoAvailablePortException(String)}.
	 */
	@Test
	public void testConstructor_Message() {
		final NoAvailablePortException exception = new NoAvailablePortException(message);
		assertNull(exception.getCause());
		assertEquals(message, exception.getMessage());
	}

	/**
	 * Tests
	 * {@link NoAvailablePortException#NoAvailablePortException(Throwable)}.
	 */
	@Test
	public void testConstructor_Throwable() {
		final NoAvailablePortException exception = new NoAvailablePortException(cause);
		assertEquals(cause, exception.getCause());
		assertEquals("java.lang.Throwable", exception.getMessage());
	}

	/**
	 * Tests
	 * {@link NoAvailablePortException#NoAvailablePortException(String, Throwable)}.
	 */
	@Test
	public void testConstructor_MessageAndThrowable() {
		final NoAvailablePortException exception = new NoAvailablePortException(message, cause);
		assertEquals(cause, exception.getCause());
		assertEquals(message, exception.getMessage());
	}

}
