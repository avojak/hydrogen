package com.avojak.plugin.hydrogen.test.contributions.configuration.launch.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.eclipse.core.runtime.ILog;
import org.eclipse.debug.core.ILaunch;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.listener.HydrogenLaunchListener;
import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;

/**
 * Test class for {@link HydrogenLaunchListener}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class HydrogenLaunchListenerTest {

	@Mock
	private LaunchDelegatePortPool portPool;

	@Mock
	private ILaunch launch;

	/**
	 * Setup static mocks.
	 */
	@BeforeClass
	public static void setupClass() {
		HydrogenLoggerFactory.init(Mockito.mock(ILog.class));
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link LaunchDelegatePortPool} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullPortPool() {
		new HydrogenLaunchListener((LaunchDelegatePortPool) null);
	}

	/**
	 * Tests {@link HydrogenLaunchListener#launchesTerminated(ILaunch[])} when there
	 * are no launches.
	 */
	@Test
	public void testLaunchesTerminated_NoLaunches() {
		new HydrogenLaunchListener(portPool).launchesTerminated(new ILaunch[] {});
		Mockito.verify(portPool).returnPorts(Collections.emptyList());
	}

	/**
	 * Tests {@link HydrogenLaunchListener#launchesTerminated(ILaunch[])} when all
	 * port attributes are null.
	 */
	@Test
	public void testLaunchesTerminated_NullAttributes() {
		Mockito.when(launch.getAttribute(ServerOption.WEB_PORT.name())).thenReturn(null);
		Mockito.when(launch.getAttribute(ServerOption.TCP_PORT.name())).thenReturn(null);
		Mockito.when(launch.getAttribute(ServerOption.PG_PORT.name())).thenReturn(null);

		new HydrogenLaunchListener(portPool).launchesTerminated(new ILaunch[] { launch });

		Mockito.verify(portPool).returnPorts(new ArrayList<Integer>());
	}

	/**
	 * Tests {@link HydrogenLaunchListener#launchesTerminated(ILaunch[])}.
	 */
	@Test
	public void testLaunchesTerminated() {
		Mockito.when(launch.getAttribute(ServerOption.WEB_PORT.name())).thenReturn("8082"); //$NON-NLS-1$
		Mockito.when(launch.getAttribute(ServerOption.TCP_PORT.name())).thenReturn("9092"); //$NON-NLS-1$
		Mockito.when(launch.getAttribute(ServerOption.PG_PORT.name())).thenReturn("5435"); //$NON-NLS-1$

		new HydrogenLaunchListener(portPool).launchesTerminated(new ILaunch[] { launch });

		Mockito.verify(portPool).returnPorts(Arrays.asList(8082, 9092, 5435));
	}

}
