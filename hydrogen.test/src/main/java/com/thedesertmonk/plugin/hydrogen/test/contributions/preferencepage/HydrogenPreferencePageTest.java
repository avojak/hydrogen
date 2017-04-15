package com.thedesertmonk.plugin.hydrogen.test.contributions.preferencepage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage.HydrogenPreferencePage;
import com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage.HydrogenPreferencePageValidator;

/**
 * Test class for {@link HydrogenPreferencePage}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class HydrogenPreferencePageTest {

	@Mock
	private HydrogenPreferencePageValidator validator;

	@Test
	public void testIsValid() {
		new HydrogenPreferencePage(validator).createFieldEditors();
	}

}
