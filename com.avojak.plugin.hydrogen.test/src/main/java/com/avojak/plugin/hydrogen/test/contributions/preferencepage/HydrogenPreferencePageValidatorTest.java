package com.avojak.plugin.hydrogen.test.contributions.preferencepage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.nio.file.FileSystem;
import java.nio.file.Path;

import org.eclipse.jface.preference.FileFieldEditor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.contributions.preferencepage.FileValidator;
import com.avojak.plugin.hydrogen.core.contributions.preferencepage.HydrogenPreferencePageValidator;

/**
 * Test class for {@link HydrogenPreferencePageValidator}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class HydrogenPreferencePageValidatorTest {

	@Mock
	private FileSystem fileSystem;
	@Mock
	private FileValidator fileValidator;
	@Mock
	private FileFieldEditor editor;
	@Mock
	private Path path;

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link FileSystem} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullFileSystem() {
		new HydrogenPreferencePageValidator((FileSystem) null, fileValidator);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link FileValidator} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullFileValidator() {
		new HydrogenPreferencePageValidator(fileSystem, (FileValidator) null);
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(String)}
	 * returns {@code false} when the given location is {@code null}.
	 */
	@Test
	public void testValidateExecutableFile_NullFileLocation() {
		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile(null));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(String)}
	 * returns {@code false} when the given location is an empty value.
	 */
	@Test
	public void testValidateExecutableFile_EmptyLocation() {
		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile(" "));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(String)}
	 * returns {@code false} when the given {@link FileFieldEditor} contains an
	 * value which refers to a directory.
	 */
	@Test
	public void testValidateExecutableFile_NotFile() {
		when(fileSystem.getPath("mock")).thenReturn(path);
		when(fileValidator.isDirectory(path)).thenReturn(true);
		when(fileValidator.isExecutable(path)).thenReturn(true);

		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile("mock"));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(String)}
	 * returns {@code false} when the given {@link FileFieldEditor} contains an
	 * value which refers to a non-executable file.
	 */
	@Test
	public void testValidateExecutableFile_NotExecutable() {
		when(fileSystem.getPath("mock")).thenReturn(path);
		when(fileValidator.isDirectory(path)).thenReturn(false);
		when(fileValidator.isExecutable(path)).thenReturn(false);

		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile("mock"));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(String)}
	 * returns {@code true} when the given {@link FileFieldEditor} contains an value
	 * which refers to an executable file.
	 */
	@Test
	public void testValidateExecutableFile() {
		when(fileSystem.getPath("mock")).thenReturn(path);
		when(fileValidator.isDirectory(path)).thenReturn(false);
		when(fileValidator.isExecutable(path)).thenReturn(true);

		assertTrue(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile("mock"));
	}

}
