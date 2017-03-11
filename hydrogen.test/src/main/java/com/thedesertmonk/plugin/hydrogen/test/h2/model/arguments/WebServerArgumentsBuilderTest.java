package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.WebServerArgumentsBuilder;

/**
 * Test class for {@link WebServerArgumentsBuilder}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class WebServerArgumentsBuilderTest {

	private static final String EMPTY_STRING = " ";
	private static final String PORT = "8082";

	private WebServerArgumentsBuilder builder;

	/**
	 * Setup test objects.
	 */
	@Before
	public void setup() {
		builder = new WebServerArgumentsBuilder();
	}

	/**
	 * Tests the constructor.
	 */
	@Test
	public void testConstructor() {
		assertEquals(singletonList(ServerOption.START_WEB.getParam()), builder.getArguments());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#allowOthers()}
	 */
	@Test
	public void testAllowOthers() {
		final List<String> expectedArguments = asList(ServerOption.START_WEB.getParam(),
				ServerOption.WEB_ALLOW_OTHERS.getParam());

		assertEquals(expectedArguments, builder.allowOthers().getArguments());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#useDaemonThread()}
	 */
	@Test
	public void testUseDaemonThread() {
		final List<String> expectedArguments = asList(ServerOption.START_WEB.getParam(),
				ServerOption.WEB_DAEMON.getParam());

		assertEquals(expectedArguments, builder.useDaemonThread().getArguments());
	}

	/**
	 * Tests that {@link WebServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_NullPort() {
		builder.withPort((String) null);
	}

	/**
	 * Tests that {@link WebServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_EmptyPort() {
		builder.withPort(EMPTY_STRING);
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#withPort(String)}.
	 */
	@Test
	public void testWithPort() {
		final List<String> expectedArguments = asList(ServerOption.START_WEB.getParam(),
				ServerOption.WEB_PORT.getParam(), PORT);

		assertEquals(expectedArguments, builder.withPort(PORT).getArguments());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#useSsl()}.
	 */
	@Test
	public void testUseSsl() {
		final List<String> expectedArguments = asList(ServerOption.START_WEB.getParam(),
				ServerOption.WEB_SSL.getParam());

		assertEquals(expectedArguments, builder.useSsl().getArguments());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#openBrowser()}.
	 */
	@Test
	public void testOpenBrowser() {
		final List<String> expectedArguments = asList(ServerOption.START_WEB.getParam(),
				ServerOption.WEB_BROWSER.getParam());

		assertEquals(expectedArguments, builder.openBrowser().getArguments());
	}

	/**
	 * Tests {@link WebServerArgumentsBuilder#build()}.
	 */
	@Test
	public void testBuild() {
		//@formatter:off
		builder.allowOthers()
			   .useDaemonThread()
			   .withPort(PORT)
			   .useSsl()
			   .openBrowser();
		//@formatter:on

		final List<String> expectedArguments = new ArrayList<String>();
		expectedArguments.add(ServerOption.START_WEB.getParam());
		expectedArguments.add(ServerOption.WEB_ALLOW_OTHERS.getParam());
		expectedArguments.add(ServerOption.WEB_DAEMON.getParam());
		expectedArguments.add(ServerOption.WEB_PORT.getParam());
		expectedArguments.add(PORT);
		expectedArguments.add(ServerOption.WEB_SSL.getParam());
		expectedArguments.add(ServerOption.WEB_BROWSER.getParam());
		final WebServerArguments expectedServerArguments = new WebServerArguments(expectedArguments);
		assertEquals(expectedServerArguments, builder.build());
	}

}
