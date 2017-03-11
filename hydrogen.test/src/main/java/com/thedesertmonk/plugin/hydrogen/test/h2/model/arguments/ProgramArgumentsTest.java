package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;

/**
 * Test class for {@link ProgramArguments}
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
@RunWith(MockitoJUnitRunner.class)
public class ProgramArgumentsTest {

	@Mock
	private ILaunchConfiguration configuration;

	private Map<String, Object> attributes;

	/**
	 * Setup mocks.
	 *
	 * @throws CoreException unexpected.
	 */
	@Before
	public void setup() throws CoreException {
		attributes = new HashMap<String, Object>();
		// General attributes
		attributes.put(LaunchConfigurationAttributes.START_WEB.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.START_TCP.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.START_PG.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.ENABLE_TRACING.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.IF_EXISTS.getName(), Boolean.TRUE);
		// Web server attributes
		attributes.put(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.WEB_DAEMON.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.WEB_SSL.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.WEB_BROWSER.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.WEB_PORT.getName(), Boolean.TRUE);
		// TCP server attributes
		attributes.put(LaunchConfigurationAttributes.TCP_SSL.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.TCP_ALLOW_OTHERS.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.TCP_DAEMON.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.TCP_PORT.getName(), Boolean.TRUE);
		// PG server attributes
		attributes.put(LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.PG_DAEMON.getName(), Boolean.TRUE);
		attributes.put(LaunchConfigurationAttributes.PG_PORT.getName(), Boolean.TRUE);

		when(configuration.getAttributes()).thenReturn(attributes);
		when(configuration.hasAttribute(Matchers.anyString())).thenReturn(true);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link ILaunchConfiguration} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullConfiguration() {
		new ProgramArguments((ILaunchConfiguration) null);
	}

	@Test
	public void testUnableToRetrieveAttributes() {
		try {
			when(configuration.hasAttribute(Matchers.anyString())).thenThrow(new CoreException(null));
			new ProgramArguments(configuration);
		} catch (final CoreException e) {
			fail("CoreException not expected");
		} catch (final RuntimeException e) {
			assertEquals("Unable to retrieve attributes", e.getMessage());
			return;
		}
		fail("Expected RuntimeException");
	}

	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_START_WEB() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.START_WEB.getName())).thenReturn(false);
	}

}
