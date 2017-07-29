package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.TcpServerArgumentsBuilder;

/**
 * Test class for {@link TcpServerArgumentsBuilder}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class TcpServerArgumentsBuilderTest {

	private static final String EMPTY_STRING = " ";
	private static final String PORT = "9092";
	private static final String URL = "tcp://localhost";
	private static final String PASSWORD = "password";

	private final String startTcp = "startTcp";
	private final String allowOthers = "allowOthers";
	private final String useDaemonThread = "useDaemonThread";
	private final String port = "port";
	private final String useSsl = "useSsl";
	private final String shutdownUrl = "shutdownUrl";
	private final String shutdownPassword = "shutdownPassword";
	private final String forceShutdown = "forceShutdown";

	/**
	 * Tests the no-args constructor.
	 */
	@Test
	public void testNoArgsConstructor() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder();
		assertNull(builder.getAllowOthers());
		assertNull(builder.getUseDaemonThread());
		assertNull(builder.getPort());
		assertNull(builder.getUseSsl());
		assertNull(builder.getShutdownUrl());
		assertNull(builder.getShutdownPassword());
		assertNull(builder.getForceShutdown());
	}

	/**
	 * Tests the deep copy constructor.
	 */
	@Test
	public void testDeepCopyConstructor() {
		final TcpServerArguments arguments = new TcpServerArguments(startTcp, allowOthers, useDaemonThread, port,
				useSsl, shutdownUrl, shutdownPassword, forceShutdown);
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder(arguments);
		assertEquals(allowOthers, builder.getAllowOthers());
		assertEquals(useDaemonThread, builder.getUseDaemonThread());
		assertEquals(port, builder.getPort());
		assertEquals(useSsl, builder.getUseSsl());
		assertEquals(shutdownUrl, builder.getShutdownUrl());
		assertEquals(shutdownPassword, builder.getShutdownPassword());
		assertEquals(forceShutdown, builder.getForceShutdown());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#allowOthers()}.
	 */
	@Test
	public void testAllowOthers() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder().allowOthers();
		assertEquals(ServerOption.TCP_ALLOW_OTHERS.getParam(), builder.getAllowOthers());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#useDaemonThread()}.
	 */
	@Test
	public void testUseDaemonThread() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder().useDaemonThread();
		assertEquals(ServerOption.TCP_DAEMON.getParam(), builder.getUseDaemonThread());
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_NullPort() {
		new TcpServerArgumentsBuilder().withPort((String) null);
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_EmptyPort() {
		new TcpServerArgumentsBuilder().withPort(EMPTY_STRING);
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#withPort(String)}.
	 */
	@Test
	public void testWithPort() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder().withPort(PORT);
		assertEquals(PORT, builder.getPort());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#useSsl()}.
	 */
	@Test
	public void testUseSsl() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder().useSsl();
		assertEquals(ServerOption.TCP_SSL.getParam(), builder.getUseSsl());
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownUrl(String)}
	 * throws an exception when the given URL is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownUrl_NullUrl() {
		new TcpServerArgumentsBuilder().withShutdownUrl((String) null);
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownUrl(String)}
	 * throws an exception when the given URL is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownUrl_EmptyUrl() {
		new TcpServerArgumentsBuilder().withShutdownUrl(EMPTY_STRING);
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#withShutdownUrl(String)}.
	 */
	@Test
	public void testWithShutdownUrl() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder().withShutdownUrl(URL);
		assertEquals(URL, builder.getShutdownUrl());
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownPassword(String)}
	 * throws an exception when the given password is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownPassword_NullPassword() {
		new TcpServerArgumentsBuilder().withShutdownPassword((String) null);
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownPassword(String)}
	 * throws an exception when the given password is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownPassword_EmptyPassword() {
		new TcpServerArgumentsBuilder().withShutdownPassword(EMPTY_STRING);
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#withShutdownPassword(String)}.
	 */
	@Test
	public void testWithShutdownPassword() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder().withShutdownPassword(PASSWORD);
		assertEquals(PASSWORD, builder.getShutdownPassword());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#forceShutdown()}.
	 */
	@Test
	public void testForceShutdown() {
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder().forceShutdown();
		assertEquals(ServerOption.TCP_SHUTDOWN_FORCE.getParam(), builder.getForceShutdown());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#build()}.
	 */
	@Test
	public void testBuild() {
		//@formatter:off
		final TcpServerArgumentsBuilder builder = new TcpServerArgumentsBuilder()
			   .allowOthers()
			   .useDaemonThread()
			   .withPort(PORT)
			   .useSsl()
			   .withShutdownUrl(URL)
			   .withShutdownPassword(PASSWORD)
			   .forceShutdown();
		//@formatter:on
		final TcpServerArguments expectedServerArguments = new TcpServerArguments(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_ALLOW_OTHERS.getParam(), ServerOption.TCP_DAEMON.getParam(), PORT,
				ServerOption.TCP_SSL.getParam(), URL, PASSWORD, ServerOption.TCP_SHUTDOWN_FORCE.getParam());
		assertEquals(expectedServerArguments, builder.build());
	}

}
