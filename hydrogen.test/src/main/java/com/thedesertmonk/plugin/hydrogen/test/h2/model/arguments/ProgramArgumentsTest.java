package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.HydrogenServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.HydrogenServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.ProgramArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArgumentsBuilder;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArgumentsBuilder;
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
	private HydrogenServerArgumentsBuilder hydrogenServerArgumentsBuilder;
	@Mock
	private WebServerArgumentsBuilder webServerArgumentsBuilder;
	@Mock
	private WebServerArguments webServerArguments;
	@Mock
	private TcpServerArgumentsBuilder tcpServerArgumentsBuilder;
	@Mock
	private TcpServerArguments tcpServerArguments;
	@Mock
	private PgServerArgumentsBuilder pgServerArgumentsBuilder;
	@Mock
	private PgServerArguments pgServerArguments;
	@Mock
	private ILaunchConfiguration configuration;
	@Mock
	private HydrogenServerArguments arguments;

	/**
	 * Setup mocks.
	 *
	 * @throws CoreException unexpected.
	 */
	@Before
	public void setup() throws CoreException {
		// General attributes
		when(configuration.getAttribute(LaunchConfigurationAttributes.START_WEB.getName(),
				LaunchConfigurationAttributes.START_WEB.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.START_TCP.getName(),
				LaunchConfigurationAttributes.START_TCP.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.START_PG.getName(),
				LaunchConfigurationAttributes.START_PG.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.ENABLE_TRACING.getName(),
				LaunchConfigurationAttributes.ENABLE_TRACING.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName(),
				LaunchConfigurationAttributes.IF_EXISTS.getDefaultValue())).thenReturn(Boolean.TRUE);
		// Web server attributes
		when(configuration.getAttribute(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getName(),
				LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.WEB_DAEMON.getName(),
				LaunchConfigurationAttributes.WEB_DAEMON.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.WEB_SSL.getName(),
				LaunchConfigurationAttributes.WEB_SSL.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.WEB_BROWSER.getName(),
				LaunchConfigurationAttributes.WEB_BROWSER.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.WEB_PORT.getName(),
				LaunchConfigurationAttributes.WEB_PORT.getDefaultValue())).thenReturn("8082");
		// TCP server attributes
		when(configuration.getAttribute(LaunchConfigurationAttributes.TCP_SSL.getName(),
				LaunchConfigurationAttributes.TCP_SSL.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.TCP_ALLOW_OTHERS.getName(),
				LaunchConfigurationAttributes.TCP_ALLOW_OTHERS.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.TCP_DAEMON.getName(),
				LaunchConfigurationAttributes.TCP_DAEMON.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE.getName(),
				LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.TCP_PORT.getName(),
				LaunchConfigurationAttributes.TCP_PORT.getDefaultValue())).thenReturn("9092");
		// PG server attributes
		when(configuration.getAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getName(),
				LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.PG_DAEMON.getName(),
				LaunchConfigurationAttributes.PG_DAEMON.getDefaultValue())).thenReturn(Boolean.TRUE);
		when(configuration.getAttribute(LaunchConfigurationAttributes.PG_PORT.getName(),
				LaunchConfigurationAttributes.PG_PORT.getDefaultValue())).thenReturn("5435");

		when(configuration.hasAttribute(anyString())).thenReturn(true);

		when(webServerArgumentsBuilder.build()).thenReturn(webServerArguments);
		when(tcpServerArgumentsBuilder.build()).thenReturn(tcpServerArguments);
		when(pgServerArgumentsBuilder.build()).thenReturn(pgServerArguments);
		when(hydrogenServerArgumentsBuilder.build()).thenReturn(arguments);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link HydrogenServerArgumentsBuilder} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullHydrogenServerArgumentsBuilder() {
		new ProgramArguments((HydrogenServerArgumentsBuilder) null, webServerArgumentsBuilder,
				tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link WebServerArgumentsBuilder} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullWebServerArgumentsBuilder() {
		new ProgramArguments(hydrogenServerArgumentsBuilder, (WebServerArgumentsBuilder) null,
				tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link TcpServerArgumentsBuilder} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullTcpServerArgumentsBuilder() {
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder,
				(TcpServerArgumentsBuilder) null, pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link PgServerArgumentsBuilder} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullPgServerArgumentsBuilder() {
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				(PgServerArgumentsBuilder) null, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the given
	 * {@link ILaunchConfiguration} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullConfiguration() {
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, (ILaunchConfiguration) null);
	}

	/**
	 * Tests that the constructor throws a {@link RuntimeException} when unable
	 * to retrieve attributes from the configuration.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testUnableToRetrieveAttributes() {
		try {
			when(configuration.hasAttribute(anyString())).thenThrow(CoreException.class);
			new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
					pgServerArgumentsBuilder, configuration);
		} catch (final CoreException e) {
			fail("CoreException not expected");
		} catch (final RuntimeException e) {
			assertEquals("Unable to retrieve attributes", e.getMessage());
			return;
		}
		fail("Expected RuntimeException");
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#START_WEB} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_START_WEB() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.START_WEB.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#START_TCP} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_START_TCP() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.START_TCP.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#START_PG} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_START_PG() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.START_PG.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#ENABLE_TRACING} attribute is
	 * missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_ENABLE_TRACING() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.ENABLE_TRACING.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#IF_EXISTS} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_IF_EXISTS() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.IF_EXISTS.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#WEB_ALLOW_OTHERS} attribute is
	 * missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_WEB_ALLOW_OTHERS() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.WEB_ALLOW_OTHERS.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#WEB_DAEMON} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_WEB_DAEMON() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.WEB_DAEMON.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#WEB_SSL} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_WEB_SSL() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.WEB_SSL.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#WEB_BROWSER} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_WEB_BROWSER() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.WEB_BROWSER.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#WEB_PORT} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_WEB_PORT() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.WEB_PORT.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#TCP_SSL} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_TCP_SSL() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.TCP_SSL.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#TCP_ALLOW_OTHERS} attribute is
	 * missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_TCP_ALLOW_OTHERS() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.TCP_ALLOW_OTHERS.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#TCP_DAEMON} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_TCP_DAEMON() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.TCP_DAEMON.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#TCP_SHUTDOWN_FORCE} attribute is
	 * missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_TCP_SHUTDOWN_FORCE() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.TCP_SHUTDOWN_FORCE.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#TCP_PORT} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_TCP_PORT() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.TCP_PORT.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#PG_ALLOW_OTHERS} attribute is
	 * missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_PG_ALLOW_OTHERS() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.PG_ALLOW_OTHERS.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#PG_DAEMON} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_PG_DAEMON() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.PG_DAEMON.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests that the constructor throws an exception when the
	 * {@link LaunchConfigurationAttributes#PG_PORT} attribute is missing.
	 *
	 * @throws CoreException
	 */
	@Test(expected = IllegalStateException.class)
	public void testMissingAttribute_PG_PORT() throws CoreException {
		when(configuration.hasAttribute(LaunchConfigurationAttributes.PG_PORT.getName())).thenReturn(false);
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);
	}

	/**
	 * Tests the constructor.
	 */
	@Test
	public void testConstructor() {
		new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder, tcpServerArgumentsBuilder,
				pgServerArgumentsBuilder, configuration);

		verify(hydrogenServerArgumentsBuilder).withWebServer(webServerArguments);
		verify(hydrogenServerArgumentsBuilder).withTcpServer(tcpServerArguments);
		verify(hydrogenServerArgumentsBuilder).withPgServer(pgServerArguments);

		verify(webServerArgumentsBuilder).allowOthers();
		verify(webServerArgumentsBuilder).useDaemonThread();
		verify(webServerArgumentsBuilder).useSsl();
		verify(webServerArgumentsBuilder).openBrowser();
		verify(webServerArgumentsBuilder).withPort("8082");
		verify(webServerArgumentsBuilder).build();

		verify(tcpServerArgumentsBuilder).allowOthers();
		verify(tcpServerArgumentsBuilder).useDaemonThread();
		verify(tcpServerArgumentsBuilder).useSsl();
		verify(tcpServerArgumentsBuilder).forceShutdown();
		verify(tcpServerArgumentsBuilder).withPort("9092");
		verify(tcpServerArgumentsBuilder).build();

		verify(pgServerArgumentsBuilder).allowOthers();
		verify(pgServerArgumentsBuilder).useDaemonThread();
		verify(pgServerArgumentsBuilder).withPort("5435");
		verify(pgServerArgumentsBuilder).build();
	}

	/**
	 * Tests {@link ProgramArguments#getArguments()}.
	 */
	@Test
	public void testGetArguments() {
		when(hydrogenServerArgumentsBuilder.build()).thenReturn(arguments);
		assertEquals(arguments, new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder,
				tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration).getArguments());
	}

	/**
	 * Tests {@link ProgramArguments#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("ProgramArguments [arguments=" + arguments.toString() + "]",
				new ProgramArguments(hydrogenServerArgumentsBuilder, webServerArgumentsBuilder,
						tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration).toString());
	}

	/**
	 * Tests {@link ProgramArguments#hashCode()}.
	 *
	 * @throws CoreException Unexpected.
	 */
	@Test
	public void testHashCode() throws CoreException {
		when(hydrogenServerArgumentsBuilder.build()).thenReturn(arguments,
				new HydrogenServerArguments(Collections.emptyList()));

		final ProgramArguments programArguments1 = new ProgramArguments(hydrogenServerArgumentsBuilder,
				webServerArgumentsBuilder, tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration);
		final ProgramArguments programArguments2 = new ProgramArguments(hydrogenServerArgumentsBuilder,
				webServerArgumentsBuilder, tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration);

		assertTrue(programArguments1.hashCode() == programArguments1.hashCode());
		assertFalse(programArguments1.hashCode() == programArguments2.hashCode());
	}

	/**
	 * Tests {@link ProgramArguments#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		when(hydrogenServerArgumentsBuilder.build()).thenReturn(arguments, arguments,
				new HydrogenServerArguments(Collections.emptyList()));

		final ProgramArguments programArguments1 = new ProgramArguments(hydrogenServerArgumentsBuilder,
				webServerArgumentsBuilder, tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration);
		final ProgramArguments programArguments2 = new ProgramArguments(hydrogenServerArgumentsBuilder,
				webServerArgumentsBuilder, tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration);
		final ProgramArguments programArguments3 = new ProgramArguments(hydrogenServerArgumentsBuilder,
				webServerArgumentsBuilder, tcpServerArgumentsBuilder, pgServerArgumentsBuilder, configuration);

		assertTrue(programArguments1.equals(programArguments1));
		assertFalse(programArguments1.equals(null));
		assertFalse(programArguments1.equals("String"));
		assertTrue(programArguments1.equals(programArguments2));
		assertFalse(programArguments1.equals(programArguments3));
	}

}