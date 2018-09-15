package com.avojak.plugin.hydrogen.test.h2.model.arguments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.WebServerArgumentsBuilder;

/**
 * Test class for {@link WebServerArgumentsBuilder}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class WebServerArgumentsBuilderTest {

	private static final String EMPTY_STRING = " ";
	private static final String PORT = "8082";

	private final String startWeb = "startWeb";
	private final String allowOthers = "allowOthers";
	private final String useDaemonThread = "useDaemonThread";
	private final String port = "port";
	private final String useSsl = "useSsl";
	private final String openBrowser = "openBrowser";

	/**
	 * Tests the no-args constructor.
	 */
	@Test
	public void testNoArgsConstructor() {
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder();
		assertNull(builder.getAllowOthers());
		assertNull(builder.getUseDaemonThread());
		assertNull(builder.getPort());
		assertNull(builder.getUseSsl());
		assertNull(builder.getOpenBrowser());
	}

	/**
	 * Tests the deep copy constructor.
	 */
	@Test
	public void testDeepCopyConstructor() {
		final WebServerArguments arguments = new WebServerArguments(startWeb, allowOthers, useDaemonThread, port,
				useSsl, openBrowser);
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder(arguments);
		assertEquals(allowOthers, builder.getAllowOthers());
		assertEquals(useDaemonThread, builder.getUseDaemonThread());
		assertEquals(port, builder.getPort());
		assertEquals(useSsl, builder.getUseSsl());
		assertEquals(openBrowser, builder.getOpenBrowser());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#allowOthers()}
	 */
	@Test
	public void testAllowOthers() {
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder().allowOthers();
		assertEquals(ServerOption.WEB_ALLOW_OTHERS.getParam(), builder.getAllowOthers());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#useDaemonThread()}
	 */
	@Test
	public void testUseDaemonThread() {
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder().useDaemonThread();
		assertEquals(ServerOption.WEB_DAEMON.getParam(), builder.getUseDaemonThread());
	}

	/**
	 * Tests that {@link WebServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_NullPort() {
		new WebServerArgumentsBuilder().withPort((String) null);
	}

	/**
	 * Tests that {@link WebServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_EmptyPort() {
		new WebServerArgumentsBuilder().withPort(EMPTY_STRING);
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#withPort(String)}.
	 */
	@Test
	public void testWithPort() {
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder().withPort(PORT);
		assertEquals(PORT, builder.getPort());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#useSsl()}.
	 */
	@Test
	public void testUseSsl() {
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder().useSsl();
		assertEquals(ServerOption.WEB_SSL.getParam(), builder.getUseSsl());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#openBrowser()}.
	 */
	@Test
	public void testOpenBrowser() {
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder().openBrowser();
		assertEquals(ServerOption.WEB_BROWSER.getParam(), builder.getOpenBrowser());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#build()}.
	 */
	@Test
	public void testBuild() {
		//@formatter:off
		final WebServerArgumentsBuilder builder = new WebServerArgumentsBuilder()
				.allowOthers()
				.useDaemonThread()
				.withPort(PORT)
				.useSsl()
				.openBrowser();
		//@formatter:on
		final WebServerArguments expectedServerArguments = new WebServerArguments(ServerOption.START_WEB.getParam(),
				ServerOption.WEB_ALLOW_OTHERS.getParam(), ServerOption.WEB_DAEMON.getParam(), PORT,
				ServerOption.WEB_SSL.getParam(), ServerOption.WEB_BROWSER.getParam());
		assertEquals(expectedServerArguments, builder.build());
	}

}
