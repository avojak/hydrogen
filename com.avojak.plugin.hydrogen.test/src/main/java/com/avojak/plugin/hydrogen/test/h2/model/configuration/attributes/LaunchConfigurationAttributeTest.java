package com.avojak.plugin.hydrogen.test.h2.model.configuration.attributes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.h2.model.configuration.attributes.LaunchConfigurationAttribute;

/**
 * Test class for {@link LaunchConfigurationAttribute}.
 *
 * @author Andrew Vojak
 */
public class LaunchConfigurationAttributeTest {

	private final ServerOption serverOption = ServerOption.BASE_DIRECTORY;
	private final String defaultValue = "defaultValue";

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link ServerOption} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullServerOption() {
		new LaunchConfigurationAttribute<String>((ServerOption) null, defaultValue);
	}

	/**
	 * Tests that the constructor throws an exception when the given default
	 * value is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullDefaultValue() {
		new LaunchConfigurationAttribute<String>(serverOption, (String) null);
	}

	/**
	 * Tests {@link LaunchConfigurationAttribute#getServerOption()}.
	 */
	@Test
	public void testGetServerOption() {
		assertEquals(serverOption,
				new LaunchConfigurationAttribute<String>(serverOption, defaultValue).getServerOption());
	}

	/**
	 * Tests {@link LaunchConfigurationAttribute#getDefaultValue()}.
	 */
	@Test
	public void testGetDefaultValue() {
		assertEquals(defaultValue,
				new LaunchConfigurationAttribute<String>(serverOption, defaultValue).getDefaultValue());
	}

	/**
	 * Tests {@link LaunchConfigurationAttribute#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(serverOption.name(),
				new LaunchConfigurationAttribute<String>(serverOption, defaultValue).getName());
	}

	/**
	 * Tests {@link LaunchConfigurationAttribute#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals(
				"LaunchConfigurationAttribute [serverOption=" + serverOption + ", defaultValue=" + defaultValue + "]",
				new LaunchConfigurationAttribute<String>(serverOption, defaultValue).toString());
	}

	/**
	 * Tests {@link LaunchConfigurationAttribute#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		final LaunchConfigurationAttribute<String> attribute1 = new LaunchConfigurationAttribute<String>(
				ServerOption.BASE_DIRECTORY, defaultValue);
		final LaunchConfigurationAttribute<String> attribute2 = new LaunchConfigurationAttribute<String>(
				ServerOption.ENABLE_TRACING, defaultValue);

		assertTrue(attribute1.hashCode() == attribute1.hashCode());
		assertFalse(attribute1.hashCode() == attribute2.hashCode());
	}

	/**
	 * Tests {@link LaunchConfigurationAttribute#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		final LaunchConfigurationAttribute<String> attribute1 = new LaunchConfigurationAttribute<String>(
				ServerOption.BASE_DIRECTORY, defaultValue);
		final LaunchConfigurationAttribute<String> attribute2 = new LaunchConfigurationAttribute<String>(
				ServerOption.BASE_DIRECTORY, defaultValue);
		final LaunchConfigurationAttribute<String> attribute3 = new LaunchConfigurationAttribute<String>(
				ServerOption.ENABLE_TRACING, defaultValue);
		final LaunchConfigurationAttribute<String> attribute4 = new LaunchConfigurationAttribute<String>(
				ServerOption.BASE_DIRECTORY, "some other value");

		assertTrue(attribute1.equals(attribute1));
		assertFalse(attribute1.equals(null));
		assertFalse(attribute1.equals("String"));
		assertTrue(attribute1.equals(attribute2));
		assertFalse(attribute1.equals(attribute3));
		assertFalse(attribute1.equals(attribute4));
	}

}
