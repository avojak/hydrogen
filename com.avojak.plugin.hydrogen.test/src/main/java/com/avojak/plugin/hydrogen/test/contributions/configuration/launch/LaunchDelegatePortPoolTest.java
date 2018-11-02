package com.avojak.plugin.hydrogen.test.contributions.configuration.launch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.ILog;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortAvailabilityChecker;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.avojak.plugin.hydrogen.core.factory.ServerSocketFactory;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;

/**
 * Test class for {@link LaunchDelegatePortPool}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class LaunchDelegatePortPoolTest {

	@Mock
	private LaunchDelegatePortAvailabilityChecker availabilityChecker;

	@Mock
	private ServerSocketFactory serverSocketFactory;

	@Mock
	private ServerSocket socket;

	private LaunchDelegatePortPool pool;

	/**
	 * Setup static mocks.
	 */
	@BeforeClass
	public static void setupClass() {
		HydrogenLoggerFactory.init(mock(ILog.class));
	}

	/**
	 * Setup.
	 *
	 * @throws IOException Unexpected
	 */
	@Before
	public void setup() throws IOException {
		pool = new LaunchDelegatePortPool(availabilityChecker, serverSocketFactory);
		when(serverSocketFactory.create(0)).thenReturn(socket);
		when(socket.getLocalPort()).thenReturn(5000);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link LaunchDelegatePortAvailabilityChecker} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullAvailabilityChecker() {
		new LaunchDelegatePortPool((LaunchDelegatePortAvailabilityChecker) null, serverSocketFactory);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link ServerSocketFactory} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullServerSocketFactory() {
		new LaunchDelegatePortPool(availabilityChecker, (ServerSocketFactory) null);
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * number is invalid.
	 */
	@Test
	public void testIsPortFree_InvalidPort() {
		assertFalse(pool.isPortFree(-1));
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * has already been distributed.
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testIsPortFree_AlreadyDistributed() throws IOException {
		when(availabilityChecker.isPortFree(anyInt())).thenReturn(true);
		final int port = pool.getFreePort();

		assertFalse(pool.isPortFree(port));
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * has already been discovered to be used by another service.
	 */
	@Test
	public void testIsPortFree_DiscoveredUsed() {
		when(availabilityChecker.isPortFree(anyInt())).thenReturn(false);

		assertFalse(pool.isPortFree(6000));
		assertEquals(Collections.singleton(6000), pool.getDiscoveredUsedPorts());
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * is not free.
	 */
	@Test
	public void testIsPortFree_PortNotFree() {
		when(availabilityChecker.isPortFree(anyInt())).thenReturn(false);
		assertFalse(pool.isPortFree(8082));
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * is free.
	 */
	@Test
	public void testIsPortFree_PortFree() {
		when(availabilityChecker.isPortFree(anyInt())).thenReturn(true);
		assertTrue(pool.isPortFree(8082));
	}

	/**
	 * Tests that {@link LaunchDelegatePortPool#getFreePort()} throws an
	 * exception when there is an I/O exception.
	 *
	 * @throws IOException Expected.
	 */
	@Test(expected = IOException.class)
	public void testGetFreePort_IOException() throws IOException {
		when(serverSocketFactory.create(0)).thenThrow(new IOException());
		pool.getFreePort();
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#getFreePort()}.
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testGetFreePort() throws IOException {
		assertEquals(5000, pool.getFreePort());
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#returnAllDistributedPorts()}
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testReturnDistributedPorts() throws IOException {
		pool.getFreePort();
		assertEquals(Collections.singleton(5000), pool.returnAllDistributedPorts());
	}

	/**
	 * Tests that {@link LaunchDelegatePortPool#returnPorts(List)} throws an
	 * exception when the given {@link List} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testReturnPorts_NullList() {
		pool.returnPorts((List<Integer>) null);
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#returnPorts(List)}.
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testReturnPorts() throws IOException {
		when(availabilityChecker.isPortFree(anyInt())).thenReturn(true);
		final int port1 = pool.getFreePort();
		final int port2 = pool.getFreePort();

		final Set<Integer> expectedSet = new HashSet<Integer>();
		expectedSet.add(port1);
		expectedSet.add(port2);
		assertEquals(expectedSet, pool.getDistributedPorts());

		pool.returnPorts(Arrays.asList(port1, port2));

		assertEquals(Collections.EMPTY_SET, pool.getDiscoveredUsedPorts());
	}

}
