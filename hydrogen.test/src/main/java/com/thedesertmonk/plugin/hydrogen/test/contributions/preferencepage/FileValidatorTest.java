package com.thedesertmonk.plugin.hydrogen.test.contributions.preferencepage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.contributions.preferencepage.FileValidator;

/**
 * Test class for {@link FileValidator}
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class FileValidatorTest {

	private static final String PREFIX = "hydrogen-junit";
	private static final FileAttribute<Set<PosixFilePermission>> OWNER_READ_ATTRIBUTE = PosixFilePermissions
			.asFileAttribute(Collections.singleton(PosixFilePermission.OWNER_READ));
	private static final FileAttribute<Set<PosixFilePermission>> OWNER_EXECUTE_ATTRIBUTE = PosixFilePermissions
			.asFileAttribute(Collections.singleton(PosixFilePermission.OWNER_EXECUTE));

	private FileValidator validator;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		validator = new FileValidator();
	}

	/**
	 * Tests that {@link FileValidator#isDirectory(Path)} throws an exception
	 * when the given {@link Path} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsDirectory_NullPath() {
		validator.isDirectory((Path) null);
	}

	/**
	 * Tests that {@link FileValidator#isDirectory(Path)} returns {@code false}
	 * when the given {@link Path} refers to a file which is not a directory.
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testIsDirectory_NotDirectory() throws IOException {
		final Path path = Files.createTempFile(PREFIX, null, OWNER_READ_ATTRIBUTE);
		path.toFile().deleteOnExit();

		assertFalse(validator.isDirectory(path));
	}

	/**
	 * Tests that {@link FileValidator#isDirectory(Path)} returns {@code true}
	 * when the given {@link Path} refers to a file which is a directory.
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testIsDirectory_Directory() throws IOException {
		final Path path = Files.createTempDirectory(PREFIX, OWNER_READ_ATTRIBUTE);
		path.toFile().deleteOnExit();

		assertTrue(validator.isDirectory(path));
	}

	/**
	 * Tests that {@link FileValidator#isExecutable(Path)} throws an exception
	 * when the given {@link Path} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsExecutable_NullPath() {
		validator.isExecutable((Path) null);
	}

	/**
	 * Tests that {@link FileValidator#isExecutable(Path)} returns {@code false}
	 * when the given {@link Path} refers to a file which is not executable.
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testIsExecutable_NotExecutable() throws IOException {
		final Path path = Files.createTempFile(PREFIX, null, OWNER_READ_ATTRIBUTE);
		path.toFile().deleteOnExit();

		assertFalse(validator.isExecutable(path));
	}

	/**
	 * Tests that {@link FileValidator#isExecutable(Path)} returns {@code true}
	 * when the given {@link Path} refers to a file which is executable.
	 *
	 * @throws IOException Unexpected.
	 */
	@Test
	public void testIsExecutable_Executable() throws IOException {
		final Path path = Files.createTempFile(PREFIX, null, OWNER_EXECUTE_ATTRIBUTE);
		path.toFile().deleteOnExit();

		assertFalse(validator.isExecutable(path));
	}

}
