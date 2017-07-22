package com.thedesertmonk.plugin.hydrogen.test.logging;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;

import java.text.MessageFormat;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.logging.EclipsePlatformLogger;

/**
 * Test class for {@link EclipsePlatformLogger}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
@RunWith(MockitoJUnitRunner.class)
public class EclipsePlatformLoggerTest {

	private static final String DEBUG_FORMAT = "[DEBUG] [{0}] {1}";
	private static final String INFO_FORMAT = "[INFO] [{0}] {1}";
	private static final String WARN_FORMAT = "[WARN] [{0}] {1}";
	private static final String ERROR_FORMAT = "[ERROR] [{0}] {1}";
	private static final String FATAL_FORMAT = "[FATAL] [{0}] {1}";

	private static final String PLUGIN_ID = "hydrogen.core";

	@Mock
	private ILog log;

	private final String message = "message";
	private final String className = getClass().getSimpleName();
	private final Throwable exception = new Throwable();

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link ILog} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullLog() {
		new EclipsePlatformLogger((ILog) null, PLUGIN_ID, className);
	}

	/**
	 * Tests that the constructor throws an exception when the given plugin ID
	 * is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullPluginId() {
		new EclipsePlatformLogger(log, (String) null, className);
	}

	/**
	 * Tests that the constructor throws an exception when the given plugin ID
	 * is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_EmptyPluginId() {
		new EclipsePlatformLogger(log, " ", className);
	}

	/**
	 * Tests that the constructor throws an exception when the given class name
	 * is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullClassName() {
		new EclipsePlatformLogger(log, PLUGIN_ID, (String) null);
	}

	/**
	 * Tests that the constructor throws an exception when the given class name
	 * is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_EmptyClassName() {
		new EclipsePlatformLogger(log, PLUGIN_ID, " ");
	}

	/**
	 * Tests {@link EclipsePlatformLogger#debug(String)}.
	 */
	@Test
	public void testDebug() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).debug(message);
		final String expectedMessage = MessageFormat.format(DEBUG_FORMAT, className, message);
		verify(log).log(refEq(new Status(IStatus.OK, PLUGIN_ID, expectedMessage)));
	}

	/**
	 * Tests {@link EclipsePlatformLogger#info(String)}.
	 */
	@Test
	public void testInfo() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).info(message);
		final String expectedMessage = MessageFormat.format(INFO_FORMAT, className, message);
		verify(log).log(refEq(new Status(IStatus.INFO, PLUGIN_ID, expectedMessage)));
	}

	/**
	 * Tests {@link EclipsePlatformLogger#warn(String)}.
	 */
	@Test
	public void testWarn() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).warn(message);
		final String expectedMessage = MessageFormat.format(WARN_FORMAT, className, message);
		verify(log).log(refEq(new Status(IStatus.WARNING, PLUGIN_ID, expectedMessage)));
	}

	/**
	 * Tests {@link EclipsePlatformLogger#error(String)}.
	 */
	@Test
	public void testError() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).error(message);
		final String expectedMessage = MessageFormat.format(ERROR_FORMAT, className, message);
		verify(log).log(refEq(new Status(IStatus.ERROR, PLUGIN_ID, expectedMessage)));
	}

	/**
	 * Tests {@link EclipsePlatformLogger#error(String, Throwable)} when the
	 * given {@link Throwable} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testError_NullException() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).error(message, (Throwable) null);
	}

	/**
	 * Tests {@link EclipsePlatformLogger#error(String, Throwable)}.
	 */
	@Test
	public void testErrorWithException() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).error(message, exception);
		final String expectedMessage = MessageFormat.format(ERROR_FORMAT, className, message);
		verify(log).log(refEq(new Status(IStatus.ERROR, PLUGIN_ID, expectedMessage, exception)));
	}

	/**
	 * Tests {@link EclipsePlatformLogger#fatal(String)}.
	 */
	@Test
	public void testFatal() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).fatal(message);
		final String expectedMessage = MessageFormat.format(FATAL_FORMAT, className, message);
		verify(log).log(refEq(new Status(IStatus.CANCEL, PLUGIN_ID, expectedMessage)));
	}

	/**
	 * Tests {@link EclipsePlatformLogger#fatal(String, Throwable)} when the
	 * given {@link Throwable} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFatal_NullException() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).fatal(message, (Throwable) null);
	}

	/**
	 * Tests {@link EclipsePlatformLogger#fatal(String, Throwable)}.
	 */
	@Test
	public void testFatalWithException() {
		new EclipsePlatformLogger(log, PLUGIN_ID, className).fatal(message, exception);
		final String expectedMessage = MessageFormat.format(FATAL_FORMAT, className, message);
		verify(log).log(refEq(new Status(IStatus.CANCEL, PLUGIN_ID, expectedMessage, exception)));
	}

}
