package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch.listener;

import org.eclipse.debug.core.ILaunch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.listener.HydrogenLaunchListener;

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
		Mockito.verify(portPool).returnDistributedPorts();
	}

}
