package com.avojak.plugin.hydrogen.test.contributions.preferencepage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import com.avojak.plugin.hydrogen.core.contributions.preferencepage.FileValidator;

/**
 * Test class for {@link FileValidator}
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class FileValidatorTest {

	private FileValidator validator;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		validator = new FileValidator();
	}

	/**
	 * Tests that {@link FileValidator#isDirectory(Path)} throws an exception when
	 * the given {@link Path} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsDirectory_NullPath() {
		validator.isDirectory((Path) null);
	}

	/**
	 * Tests that {@link FileValidator#isDirectory(Path)} returns {@code false} when
	 * the given {@link Path} refers to a file which is not a directory.
	 *
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testIsDirectory_NotDirectory() throws IOException {
		final Path path = createTemporaryFile(true);
		assertFalse("Path is directory: " + path.toString(), validator.isDirectory(path));
	}

	/**
	 * Tests that {@link FileValidator#isDirectory(Path)} returns {@code true} when
	 * the given {@link Path} refers to a file which is a directory.
	 *
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testIsDirectory_Directory() throws IOException {
		final Path path = createTemporaryDirectory();
		assertTrue("Path is not directory: " + path.toString(), validator.isDirectory(path));
	}

	/**
	 * Tests that {@link FileValidator#isExecutable(Path)} throws an exception when
	 * the given {@link Path} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsExecutable_NullPath() {
		validator.isExecutable((Path) null);
	}

	/**
	 * Tests that {@link FileValidator#isExecutable(Path)} returns {@code false}
	 * when the given {@link Path} refers to a file which is not executable.
	 *
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testIsExecutable_NotExecutable() throws IOException {
		final Path path = createTemporaryFile(false);
		assertFalse("Path is executable: " + path.toString(), validator.isExecutable(path));
	}

	/**
	 * Tests that {@link FileValidator#isExecutable(Path)} returns {@code true} when
	 * the given {@link Path} refers to a file which is executable.
	 *
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testIsExecutable_Executable() throws IOException {
		final Path path = createTemporaryFile(true);
		assertTrue("Path is not executable: " + path.toString(), validator.isExecutable(path));
	}

	private static Path createTemporaryFile(final boolean isExecutable) throws IOException {
		final File file = Files.createTempFile("hydrogen-junit", ".tmp").toFile();
		file.setExecutable(isExecutable);
		if (!isExecutable) {
			file.delete(); // Delete now to make it non-executable
		}
		file.deleteOnExit();
		return file.toPath();
	}

	private static Path createTemporaryDirectory() throws IOException {
		final File file = Files.createTempDirectory("hydrogen-junit").toFile();
		file.deleteOnExit();
		return file.toPath();
	}

}
