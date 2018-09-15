package com.avojak.plugin.hydrogen.test.h2.model.configuration.attributes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttribute;
import com.avojak.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttributes;

/**
 * Test class for {@link LaunchConfigurationAttributes}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class LaunchConfigurationAttributesTest {

	/**
	 * Verify the values.
	 */
	@Test
	public void test() {
		//@formatter:off
		assertTrue(LaunchConfigurationAttributes.START_WEB.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.START_WEB, Boolean.TRUE)));
		assertTrue(LaunchConfigurationAttributes.START_TCP.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.START_TCP, Boolean.TRUE)));
		assertTrue(LaunchConfigurationAttributes.START_PG.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.START_PG, Boolean.TRUE)));
		assertTrue(LaunchConfigurationAttributes.ENABLE_TRACING.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.ENABLE_TRACING, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.IF_EXISTS.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.IF_EXISTS, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_ALLOW_OTHERS, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.WEB_DAEMON.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_DAEMON, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.WEB_SSL.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_SSL, Boolean.TRUE)));
		assertTrue(LaunchConfigurationAttributes.WEB_BROWSER.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_BROWSER, Boolean.TRUE)));
		assertTrue(LaunchConfigurationAttributes.TCP_SSL.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_SSL, Boolean.TRUE)));
		assertTrue(LaunchConfigurationAttributes.TCP_ALLOW_OTHERS.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_ALLOW_OTHERS, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.TCP_DAEMON.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_DAEMON, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_SHUTDOWN_FORCE, Boolean.TRUE)));
		assertTrue(LaunchConfigurationAttributes.PG_ALLOW_OTHERS.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.PG_ALLOW_OTHERS, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.PG_DAEMON.equals(new LaunchConfigurationAttribute<Boolean>(ServerOption.PG_DAEMON, Boolean.FALSE)));
		assertTrue(LaunchConfigurationAttributes.BASE_DIRECTORY.equals(new LaunchConfigurationAttribute<String>(ServerOption.BASE_DIRECTORY, System.getProperty("user.home"))));
		assertTrue(LaunchConfigurationAttributes.PROPERTIES.equals(new LaunchConfigurationAttribute<String>(ServerOption.PROPERTIES, "")));
		assertTrue(LaunchConfigurationAttributes.WEB_PORT.equals(new LaunchConfigurationAttribute<String>(ServerOption.WEB_PORT, "8082")));
		assertTrue(LaunchConfigurationAttributes.TCP_PORT.equals(new LaunchConfigurationAttribute<String>(ServerOption.TCP_PORT, "9092")));
		assertTrue(LaunchConfigurationAttributes.PG_PORT.equals(new LaunchConfigurationAttribute<String>(ServerOption.PG_PORT, "5435")));
		//@formatter:on
	}

}
