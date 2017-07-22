package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortAvailabilityChecker;
import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.exception.NoAvailablePortException;
import com.thedesertmonk.plugin.hydrogen.core.logging.HydrogenLoggerFactory;

/**
 * Test class for {@link LaunchDelegatePortPool}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class LaunchDelegatePortPoolTest {

	private static final int MIN_PORT_NUMBER = 49152;

	@Mock
	private LaunchDelegatePortAvailabilityChecker availabilityChecker;

	private LaunchDelegatePortPool pool;

	/**
	 * Setup static mocks.
	 */
	@BeforeClass
	public static void setupClass() {
		HydrogenLoggerFactory.init(Mockito.mock(ILog.class));
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		pool = new LaunchDelegatePortPool(availabilityChecker);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link LaunchDelegatePortAvailabilityChecker} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullAvailabilityChecker() {
		new LaunchDelegatePortPool((LaunchDelegatePortAvailabilityChecker) null);
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
	 */
	@Test
	public void testIsPortFree_AlreadyDistributed() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(true);
		final int port = pool.getFreePort();

		assertFalse(pool.isPortFree(port));
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * has already been discovered to be used by another service.
	 */
	@Test
	public void testIsPortFree_DiscoveredUsed() {
		final ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(false, true);
		pool.getFreePort();
		Mockito.verify(availabilityChecker, Mockito.times(2)).isPortFree(captor.capture());

		assertFalse(pool.isPortFree(captor.getAllValues().get(0)));
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * is not free.
	 */
	@Test
	public void testIsPortFree_PortNotFree() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(false);
		assertFalse(pool.isPortFree(8082));
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#isPortFree(int)} when the given port
	 * is free.
	 */
	@Test
	public void testIsPortFree_PortFree() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(true);
		assertTrue(pool.isPortFree(8082));
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#getFreePort()} when a port is found
	 * that is already in use.
	 */
	@Test
	public void testGetFreePort_FoundUsedPort() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(false, true);
		final int port = pool.getFreePort();

		assertEquals(MIN_PORT_NUMBER + 1, port);
		assertEquals(Collections.singleton(MIN_PORT_NUMBER + 1), pool.getDistributedPorts());
		assertEquals(Collections.singleton(MIN_PORT_NUMBER), pool.getDiscoveredUsedPorts());
	}

	/**
	 * Tests that {@link LaunchDelegatePortPool#getFreePort()} throws an
	 * exception when all valid ports are in use.
	 */
	@Test(expected = NoAvailablePortException.class)
	public void testGetFreePort_AllPortsUsed() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(false);
		pool.getFreePort();
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#getFreePort()}.
	 */
	@Test
	public void testGetFreePort() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(true);
		assertEquals(MIN_PORT_NUMBER, pool.getFreePort());
	}

	/**
	 * Tests {@link LaunchDelegatePortPool#returnAllDistributedPorts()}
	 */
	@Test
	public void testReturnDistributedPorts() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(true);
		pool.getFreePort();
		pool.getFreePort();

		final Set<Integer> expectedSet = new HashSet<Integer>();
		expectedSet.add(MIN_PORT_NUMBER);
		expectedSet.add(MIN_PORT_NUMBER + 1);

		assertEquals(expectedSet, pool.returnAllDistributedPorts());
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
	 */
	@Test
	public void testReturnPorts() {
		Mockito.when(availabilityChecker.isPortFree(Matchers.anyInt())).thenReturn(true);
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