package com.thedesertmonk.plugin.hydrogen.test.contributions.configuration.launch.tab;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab.GeneralLaunchConfigurationTab;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;

/**
 * Test class for {@link GeneralLaunchConfigurationTab}
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneralLaunchConfigurationTabTest { 

	@Mock
	private ILaunchConfigurationWorkingCopy configurationWorkingCopy;

	private Display display;
	private Shell shell;
	private Composite baseComposite;

	private GeneralLaunchConfigurationTab tab;

	@Before
	public void setup() { 
		display = new Display();
		shell = new Shell(display);
		baseComposite = new Composite(shell, SWT.NONE);

		tab = new GeneralLaunchConfigurationTab();
		tab.createControl(baseComposite);
	}

	@After
	public void tearDown() {
		display.dispose();
	}

	@Test
	public void testSetDefaults() {
		tab.setDefaults(configurationWorkingCopy);

		Mockito.verify(configurationWorkingCopy).setAttribute(LaunchConfigurationAttributes.BASE_DIRECTORY.getName(),
				LaunchConfigurationAttributes.BASE_DIRECTORY.getDefaultValue());
		Mockito.verify(configurationWorkingCopy).setAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(),
				LaunchConfigurationAttributes.IF_EXISTS.getDefaultValue());
		Mockito.verify(configurationWorkingCopy).setAttribute(LaunchConfigurationAttributes.START_WEB.getName(),
				LaunchConfigurationAttributes.START_WEB.getDefaultValue());
		Mockito.verify(configurationWorkingCopy).setAttribute(LaunchConfigurationAttributes.START_TCP.getName(),
				LaunchConfigurationAttributes.START_TCP.getDefaultValue());
		Mockito.verify(configurationWorkingCopy).setAttribute(LaunchConfigurationAttributes.START_PG.getName(),
				LaunchConfigurationAttributes.START_PG.getDefaultValue());
		Mockito.verify(configurationWorkingCopy).setAttribute(LaunchConfigurationAttributes.ENABLE_TRACING.getName(),
				LaunchConfigurationAttributes.ENABLE_TRACING.getDefaultValue());
	}

	@Test
	public void testInitializeForm() {

	}

	@Test
	public void testPerformApply() {

	}

	@Test
	public void testIsValid() {

	}

	@Test
	public void testCanSave() {

	}

	@Test
	public void testGetName() {

	}

	@Test
	public void testGetImage() {

	}

	// public class GeneralLaunchConfigurationTabHelper {
	//
	// public final Text baseDirectoryField;
	//
	// public GeneralLaunchConfigurationTabHelper(final Composite baseComposite)
	// {
	// }
	//
	// }

}
