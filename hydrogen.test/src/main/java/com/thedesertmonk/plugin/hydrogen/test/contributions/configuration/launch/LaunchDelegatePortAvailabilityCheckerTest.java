package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortAvailabilityChecker;
import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.ServerSocketFactory;

/**
 * Test class for {@link LaunchDelegatePortAvailabilityChecker}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class LaunchDelegatePortAvailabilityCheckerTest {

	private static final int PORT = 8082;

	@Mock
	private ServerSocketFactory serverSocketFactory;

	@Mock
	private ServerSocket socket;

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link ServerSocketFactory} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullFactory() {
		new LaunchDelegatePortAvailabilityChecker((ServerSocketFactory) null);
	}

	/**
	 * Tests {@link LaunchDelegatePortAvailabilityChecker#isPortFree(int)} when
	 * the specified port number is invalid.
	 */
	@Test
	public void testIsPortFree_InvalidPort() {
		assertFalse(new LaunchDelegatePortAvailabilityChecker(serverSocketFactory).isPortFree(-1));
	}

	/**
	 * Tests {@link LaunchDelegatePortAvailabilityChecker#isPortFree(int)} when
	 * there is a failure opening the socket.
	 *
	 * @throws IOException unexpected.
	 */
	@Test
	public void testIsPortFree_FailedToOpenSocket() throws IOException {
		Mockito.when(serverSocketFactory.create(PORT)).thenThrow(new IOException());
		assertFalse(new LaunchDelegatePortAvailabilityChecker(serverSocketFactory).isPortFree(PORT));
		Mockito.verify(socket, Mockito.never()).close();
	}

	/**
	 * Tests {@link LaunchDelegatePortAvailabilityChecker#isPortFree(int)}.
	 *
	 * @throws IOException unexpected.
	 */
	@Test
	public void testIsPortFree() throws IOException {
		Mockito.when(serverSocketFactory.create(PORT)).thenReturn(socket);
		assertTrue(new LaunchDelegatePortAvailabilityChecker(serverSocketFactory).isPortFree(PORT));
		Mockito.verify(socket).close();
	}

}
