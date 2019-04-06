package com.avojak.plugin.hydrogen.test.h2.model.arguments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.PgServerArgumentsBuilder;

/**
 * Test class for {@link PgServerArgumentsBuilder}.
 *
 * @author Andrew Vojak
 */
public class PgServerArgumentsBuilderTest {

	private static final String EMPTY_STRING = " ";
	private static final String PORT = "5435";

	private final String startPg = "startPg";
	private final String allowOthers = "allowOthers";
	private final String useDaemonThread = "useDaemonThread";
	private final String port = "port";

	/**
	 * Tests the no-args constructor.
	 */
	@Test
	public void testNoArgsConstructor() {
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder();
		assertNull(builder.getAllowOthers());
		assertNull(builder.getUseDaemonThread());
		assertNull(builder.getPort());
	}

	/**
	 * Tests that the deep copy constructor throws an exception when the old
	 * arguments are null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeepCopyConstructor_NullArguments() {
		new PgServerArgumentsBuilder(null);
	}

	/**
	 * Tests the deep copy constructor.
	 */
	@Test
	public void testDeepCopyConstructor() {
		final PgServerArguments arguments = new PgServerArguments(startPg, allowOthers, useDaemonThread, port);
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder(arguments);
		assertEquals(allowOthers, builder.getAllowOthers());
		assertEquals(useDaemonThread, builder.getUseDaemonThread());
		assertEquals(port, builder.getPort());
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#allowOthers()}.
	 */
	@Test
	public void testAllowOthers() {
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder().allowOthers();
		assertEquals(ServerOption.PG_ALLOW_OTHERS.getParam(), builder.getAllowOthers());
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#useDaemonThread()}.
	 */
	@Test
	public void testUseDaemonThread() {
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder().useDaemonThread();
		assertEquals(ServerOption.PG_DAEMON.getParam(), builder.getUseDaemonThread());
	}

	/**
	 * Tests that {@link PgServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_NullPort() {
		new PgServerArgumentsBuilder().withPort((String) null);
	}

	/**
	 * Tests that {@link PgServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_EmptyPort() {
		new PgServerArgumentsBuilder().withPort(EMPTY_STRING);
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#withPort(String)}.
	 */
	@Test
	public void testWithPort() {
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder().withPort(PORT);
		assertEquals(PORT, builder.getPort());
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#build()}.
	 */
	@Test
	public void testBuild() {
		//@formatter:off
		final PgServerArgumentsBuilder builder = new PgServerArgumentsBuilder()
				.allowOthers()
			    .useDaemonThread()
			    .withPort(PORT);
		//@formatter:on
		final PgServerArguments expectedServerArguments = new PgServerArguments(ServerOption.START_PG.getParam(),
				ServerOption.PG_ALLOW_OTHERS.getParam(), ServerOption.PG_DAEMON.getParam(), PORT);
		assertEquals(expectedServerArguments, builder.build());
	}

}
