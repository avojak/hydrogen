package com.avojak.plugin.hydrogen.test.contributions.preferencepage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.nio.file.FileSystem;
import java.nio.file.Path;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.contributions.preferencepage.FileValidator;
import com.avojak.plugin.hydrogen.core.contributions.preferencepage.HydrogenPreferencePageValidator;

/**
 * Test class for {@link HydrogenPreferencePageValidator}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
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
	 * {@link HydrogenPreferencePageValidator#isValuePresent(StringFieldEditor)}
	 * throws an exception when the given {@link StringFieldEditor} is
	 * {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsValuePresent_NullStringFieldEditor() {
		new HydrogenPreferencePageValidator(fileSystem, fileValidator).isValuePresent((StringFieldEditor) null);
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#isValuePresent(StringFieldEditor)}
	 * returns {@code false} when the given {@link StringFieldEditor} contains a
	 * {@code null} value.
	 */
	@Test
	public void testIsValuePresent_NullValue() {
		when(editor.getStringValue()).thenReturn(null);
		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).isValuePresent(editor));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#isValuePresent(StringFieldEditor)}
	 * returns {@code false} when the given {@link StringFieldEditor} contains
	 * an empty value.
	 */
	@Test
	public void testIsValuePresent_EmptyValue() {
		when(editor.getStringValue()).thenReturn(" ");
		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).isValuePresent(editor));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#isValuePresent(StringFieldEditor)}
	 * returns {@code true} when the given {@link StringFieldEditor} contains an
	 * non-empty and non-null {@code String} value.
	 */
	@Test
	public void testIsValuePresent() {
		when(editor.getStringValue()).thenReturn("mock");
		assertTrue(new HydrogenPreferencePageValidator(fileSystem, fileValidator).isValuePresent(editor));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(FileFieldEditor)}
	 * throws an exception when the given {@link FileFieldEditor} is
	 * {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateExecutableFile_NullFileFieldEditor() {
		new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile((FileFieldEditor) null);
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(FileFieldEditor)}
	 * returns {@code false} when the given {@link FileFieldEditor} contains a
	 * {@code null} value.
	 */
	@Test
	public void testValidateExecutableFile_NullValue() {
		when(editor.getStringValue()).thenReturn(null);
		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile(editor));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(FileFieldEditor)}
	 * returns {@code false} when the given {@link FileFieldEditor} contains an
	 * empty value.
	 */
	@Test
	public void testValidateExecutableFile_EmptyValue() {
		when(editor.getStringValue()).thenReturn(" ");
		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile(editor));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(FileFieldEditor)}
	 * returns {@code false} when the given {@link FileFieldEditor} contains an
	 * value which refers to a directory.
	 */
	@Test
	public void testValidateExecutableFile_NotFile() {
		when(editor.getStringValue()).thenReturn("mock");
		when(fileSystem.getPath("mock")).thenReturn(path);
		when(fileValidator.isDirectory(path)).thenReturn(true);
		when(fileValidator.isExecutable(path)).thenReturn(true);

		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile(editor));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(FileFieldEditor)}
	 * returns {@code false} when the given {@link FileFieldEditor} contains an
	 * value which refers to a non-executable file.
	 */
	@Test
	public void testValidateExecutableFile_NotExecutable() {
		when(editor.getStringValue()).thenReturn("mock");
		when(fileSystem.getPath("mock")).thenReturn(path);
		when(fileValidator.isDirectory(path)).thenReturn(false);
		when(fileValidator.isExecutable(path)).thenReturn(false);

		assertFalse(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile(editor));
	}

	/**
	 * Tests that
	 * {@link HydrogenPreferencePageValidator#validateExecutableFile(FileFieldEditor)}
	 * returns {@code true} when the given {@link FileFieldEditor} contains an
	 * value which refers to an executable file.
	 */
	@Test
	public void testValidateExecutableFile() {
		when(editor.getStringValue()).thenReturn("mock");
		when(fileSystem.getPath("mock")).thenReturn(path);
		when(fileValidator.isDirectory(path)).thenReturn(false);
		when(fileValidator.isExecutable(path)).thenReturn(true);

		assertTrue(new HydrogenPreferencePageValidator(fileSystem, fileValidator).validateExecutableFile(editor));
	}

}
