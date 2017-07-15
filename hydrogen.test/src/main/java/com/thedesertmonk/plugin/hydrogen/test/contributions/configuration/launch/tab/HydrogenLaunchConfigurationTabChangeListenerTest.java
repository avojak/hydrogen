package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch.tab;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab.HydrogenLaunchConfigurationTab;
import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab.HydrogenLaunchConfigurationTabChangeListener;

/**
 * Test class for {@link HydrogenLaunchConfigurationTabChangeListener}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class HydrogenLaunchConfigurationTabChangeListenerTest {

	@Mock
	private HydrogenLaunchConfigurationTab tab;

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link HydrogenLaunchConfigurationTab} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullTab() {
		new HydrogenLaunchConfigurationTabChangeListener((HydrogenLaunchConfigurationTab) null);
	}

	/**
	 * Tests
	 * {@link HydrogenLaunchConfigurationTabChangeListener#widgetSelected(SelectionEvent)}.
	 */
	@Test
	public void testWidgetSelected() {
		new HydrogenLaunchConfigurationTabChangeListener(tab).widgetSelected(null);
		Mockito.verify(tab).onContentChanged();
	}

	/**
	 * Tests
	 * {@link HydrogenLaunchConfigurationTabChangeListener#modifyText(ModifyEvent)}.
	 */
	@Test
	public void testModifyText() {
		new HydrogenLaunchConfigurationTabChangeListener(tab).modifyText(null);
		Mockito.verify(tab).onContentChanged();
	}

}
