package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArgumentsFactory;

/**
 * Test class for {@link ProgramArgumentsFactory}.
 *
 * @author Andrew Vojak
 */
public class ProgramArgumentsFactoryTest {

	/**
	 * Tests that {@link ProgramArgumentsFactory#create(ILaunchConfiguration)}
	 * throws an exception when the given {@link ILaunchConfiguration} is
	 * {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreate_NullConfiguration() {
		new ProgramArgumentsFactory().create((ILaunchConfiguration) null);
	}

}
