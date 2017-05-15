package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch.tab;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab.HydrogenLaunchConfigurationTab;
import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab.HydrogenLaunchConfigurationTabChangeListener;

/**
 * Test class for {@link HydrogenLaunchConfigurationTabChangeListener}
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class HydrogenLaunchConfigurationTabChangeListenerTest {

	@Mock
	private HydrogenLaunchConfigurationTab tab;

	private SelectionEvent selectionEvent;
	private ModifyEvent modifyEvent;

	/**
	 * Setup mocks.
	 */
	@Before
	public void setup() {
		final Event event = new Event();
		event.widget = Mockito.mock(Composite.class);
		selectionEvent = new SelectionEvent(event);
		modifyEvent = new ModifyEvent(event);
	}

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
		new HydrogenLaunchConfigurationTabChangeListener(tab).widgetSelected(selectionEvent);
		verify(tab).onContentChanged();
	}

	/**
	 * Tests
	 * {@link HydrogenLaunchConfigurationTabChangeListener#widgetDefaultSelected(SelectionEvent)}.
	 */
	@Test
	public void testWidgetDefaultSelected() {
		new HydrogenLaunchConfigurationTabChangeListener(tab).widgetDefaultSelected(selectionEvent);
		verifyZeroInteractions(tab);
	}

	/**
	 * Tests
	 * {@link HydrogenLaunchConfigurationTabChangeListener#modifyText(ModifyEvent)}.
	 */
	@Test
	public void testModifyText() {
		new HydrogenLaunchConfigurationTabChangeListener(tab).modifyText(modifyEvent);
		verify(tab).onContentChanged();
	}

}
