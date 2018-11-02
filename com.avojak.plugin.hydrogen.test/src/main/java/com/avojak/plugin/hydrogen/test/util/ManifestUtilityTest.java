package com.avojak.plugin.hydrogen.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.eclipse.core.runtime.ILog;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.factory.JarInputStreamFactory;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;
import com.avojak.plugin.hydrogen.core.util.ManifestUtility;

/**
 * Test class for {@link ManifestUtility}.
 * 
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class ManifestUtilityTest {

	private static final String IMPLEMENTATION_VERSION = "Implementation-Version"; //$NON-NLS-1$

	@Mock
	private JarInputStreamFactory jarInputStreamFactory;

	@Mock
	private JarInputStream jarInputStream;

	@Mock
	private Manifest manifest;

	@Mock
	private Attributes mainAttributes;

	private final String jarPath = "foobar.jar"; //$NON-NLS-1$

	private ManifestUtility manifestUtility;

	/**
	 * Setup static mocks.
	 */
	@BeforeClass
	public static void setupClass() {
		HydrogenLoggerFactory.init(Mockito.mock(ILog.class));
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		manifestUtility = new ManifestUtility(jarInputStreamFactory);
	}

	/**
	 * Tests that the constructor throws an exception when the given JAR input
	 * stream factory is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullJarInputStreamFactory() {
		new ManifestUtility(null);
	}

	/**
	 * Tests that {@link ManifestUtility#getVersion(String)} throws an exception
	 * when the given JAR path is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetVersion_NullJarPath() {
		manifestUtility.getVersion(null);
	}

	/**
	 * Tests that {@link ManifestUtility#getVersion(String)} throws an exception
	 * when the given JAR path is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetVersion_EmptyJarPath() {
		manifestUtility.getVersion(" "); //$NON-NLS-1$
	}

	/**
	 * Tests that {@link ManifestUtility#getVersion(String)} returns an empty value
	 * when the file is not found.
	 * 
	 * @throws FileNotFoundException
	 *             Unexpected.
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testGetVersion_FileNotFound() throws FileNotFoundException, IOException {
		Mockito.when(jarInputStreamFactory.create(jarPath)).thenThrow(new FileNotFoundException());
		assertTrue(!manifestUtility.getVersion(jarPath).isPresent());
	}

	/**
	 * Tests that {@link ManifestUtility#getVersion(String)} returns an empty value
	 * when there is an I/O error.
	 * 
	 * @throws FileNotFoundException
	 *             Unexpected.
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testGetVersion_IOException() throws FileNotFoundException, IOException {
		Mockito.when(jarInputStreamFactory.create(jarPath)).thenThrow(new IOException());
		assertTrue(!manifestUtility.getVersion(jarPath).isPresent());
	}

	/**
	 * Tests that {@link ManifestUtility#getVersion(String)} returns an empty value
	 * when there is no "Implementation-Version" specified in the manifest.
	 * 
	 * @throws FileNotFoundException
	 *             Unexpected.
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testGetVersion_NoVersion() throws FileNotFoundException, IOException {
		Mockito.when(jarInputStreamFactory.create(jarPath)).thenReturn(jarInputStream);
		Mockito.when(jarInputStream.getManifest()).thenReturn(manifest);
		Mockito.when(manifest.getMainAttributes()).thenReturn(mainAttributes);
		Mockito.when(mainAttributes.getValue(IMPLEMENTATION_VERSION)).thenReturn(null);

		assertTrue(!manifestUtility.getVersion(jarPath).isPresent());
	}

	/**
	 * Tests that {@link ManifestUtility#getVersion(String)} returns returns the
	 * "Implementation-Version" from the manifest.
	 * 
	 * @throws FileNotFoundException
	 *             Unexpected.
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testGetVersion() throws FileNotFoundException, IOException {
		final String version = "1.4.197"; //$NON-NLS-1$
		Mockito.when(jarInputStreamFactory.create(jarPath)).thenReturn(jarInputStream);
		Mockito.when(jarInputStream.getManifest()).thenReturn(manifest);
		Mockito.when(manifest.getMainAttributes()).thenReturn(mainAttributes);
		Mockito.when(mainAttributes.getValue(IMPLEMENTATION_VERSION)).thenReturn(version);

		assertEquals(Optional.of(version), manifestUtility.getVersion(jarPath));
	}

}
