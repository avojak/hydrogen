package com.avojak.plugin.hydrogen.test.contributions.configuration.launch.listener;

import java.util.Collections;

import org.eclipse.core.runtime.ILog;
import org.eclipse.debug.core.ILaunch;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.listener.HydrogenLaunchListener;
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
	 * Tests {@link HydrogenLaunchListener#launchesTerminated(ILaunch[])}.
	 */
	@Test
	public void testLaunchesTerminated() {
		new HydrogenLaunchListener(portPool).launchesTerminated(new ILaunch[] {});
		Mockito.verify(portPool).returnPorts(Collections.emptyList());
	}

}
