package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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

	private TcpServerArgumentsBuilder builder;

	/**
	 * Setup test objects.
	 */
	@Before
	public void setup() {
		builder = new TcpServerArgumentsBuilder();
	}

	/**
	 * Tests the constructor.
	 */
	@Test
	public void testConstructor() {
		assertEquals(singletonList(ServerOption.START_TCP.getParam()), builder.getArguments());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#allowOthers()}.
	 */
	@Test
	public void testAllowOthers() {
		final List<String> expectedArguments = asList(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_ALLOW_OTHERS.getParam());

		assertEquals(expectedArguments, builder.allowOthers().getArguments());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#useDaemonThread()}.
	 */
	@Test
	public void testUseDaemonThread() {
		final List<String> expectedArguments = asList(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_DAEMON.getParam());

		assertEquals(expectedArguments, builder.useDaemonThread().getArguments());
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_NullPort() {
		builder.withPort((String) null);
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_EmptyPort() {
		builder.withPort(EMPTY_STRING);
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#withPort(String)}.
	 */
	@Test
	public void testWithPort() {
		final List<String> expectedArguments = asList(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_PORT.getParam(), PORT);

		assertEquals(expectedArguments, builder.withPort(PORT).getArguments());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#useSsl()}.
	 */
	@Test
	public void testUseSsl() {
		final List<String> expectedArguments = asList(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_SSL.getParam());

		assertEquals(expectedArguments, builder.useSsl().getArguments());
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownUrl(String)}
	 * throws an exception when the given URL is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownUrl_NullUrl() {
		builder.withShutdownUrl((String) null);
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownUrl(String)}
	 * throws an exception when the given URL is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownUrl_EmptyUrl() {
		builder.withShutdownUrl(EMPTY_STRING);
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#withShutdownUrl(String)}.
	 */
	@Test
	public void testWithShutdownUrl() {
		final List<String> expectedArguments = asList(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_SHUTDOWN_URL.getParam(), URL);

		assertEquals(expectedArguments, builder.withShutdownUrl(URL).getArguments());
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownPassword(String)}
	 * throws an exception when the given password is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownPassword_NullPassword() {
		builder.withShutdownPassword((String) null);
	}

	/**
	 * Tests that {@link TcpServerArgumentsBuilder#withShutdownPassword(String)}
	 * throws an exception when the given password is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithShutdownPassword_EmptyPassword() {
		builder.withShutdownPassword(EMPTY_STRING);
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#withShutdownPassword(String)}.
	 */
	@Test
	public void testWithShutdownPassword() {
		final List<String> expectedArguments = asList(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_SHUTDOWN_PASSWORD.getParam(), PASSWORD);

		assertEquals(expectedArguments, builder.withShutdownPassword(PASSWORD).getArguments());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#forceShutdown()}.
	 */
	@Test
	public void testForceShutdown() {
		final List<String> expectedArguments = asList(ServerOption.START_TCP.getParam(),
				ServerOption.TCP_SHUTDOWN_FORCE.getParam());

		assertEquals(expectedArguments, builder.forceShutdown().getArguments());
	}

	/**
	 * Tests {@link TcpServerArgumentsBuilder#build()}.
	 */
	@Test
	public void testBuild() {
		//@formatter:off
		builder.allowOthers()
			   .useDaemonThread()
			   .withPort(PORT)
			   .useSsl()
			   .withShutdownUrl(URL)
			   .withShutdownPassword(PASSWORD)
			   .forceShutdown();
		//@formatter:on

		final List<String> expectedArguments = new ArrayList<String>();
		expectedArguments.add(ServerOption.START_TCP.getParam());
		expectedArguments.add(ServerOption.TCP_ALLOW_OTHERS.getParam());
		expectedArguments.add(ServerOption.TCP_DAEMON.getParam());
		expectedArguments.add(ServerOption.TCP_PORT.getParam());
		expectedArguments.add(PORT);
		expectedArguments.add(ServerOption.TCP_SSL.getParam());
		expectedArguments.add(ServerOption.TCP_SHUTDOWN_URL.getParam());
		expectedArguments.add(URL);
		expectedArguments.add(ServerOption.TCP_SHUTDOWN_PASSWORD.getParam());
		expectedArguments.add(PASSWORD);
		expectedArguments.add(ServerOption.TCP_SHUTDOWN_FORCE.getParam());
		final TcpServerArguments expectedServerArguments = new TcpServerArguments(expectedArguments);
		assertEquals(expectedServerArguments, builder.build());
	}

}
