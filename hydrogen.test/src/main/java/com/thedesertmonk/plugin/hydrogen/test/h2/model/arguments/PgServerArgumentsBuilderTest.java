package com.thedesertmonk.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;
import com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments.PgServerArgumentsBuilder;

/**
 * Test class for {@link PgServerArgumentsBuilder}.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class PgServerArgumentsBuilderTest {

	private static final String EMPTY_STRING = " ";
	private static final String PORT = "5435";

	private PgServerArgumentsBuilder builder;

	/**
	 * Setup test objects.
	 */
	@Before
	public void setup() {
		builder = new PgServerArgumentsBuilder();
	}

	/**
	 * Tests the constructor.
	 */
	@Test
	public void testConstructor() {
		assertEquals(singletonList(ServerOption.START_PG.getParam()), builder.getArguments());
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#allowOthers()}.
	 */
	@Test
	public void testAllowOthers() {
		final List<String> expectedArguments = Arrays.asList(ServerOption.START_PG.getParam(),
				ServerOption.PG_ALLOW_OTHERS.getParam());

		assertEquals(expectedArguments, builder.allowOthers().getArguments());
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#useDaemonThread()}.
	 */
	@Test
	public void testUseDaemonThread() {
		final List<String> expectedArguments = Arrays.asList(ServerOption.START_PG.getParam(),
				ServerOption.PG_DAEMON.getParam());

		assertEquals(expectedArguments, builder.useDaemonThread().getArguments());
	}

	/**
	 * Tests that {@link PgServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_NullPort() {
		builder.withPort((String) null);
	}

	/**
	 * Tests that {@link PgServerArgumentsBuilder#withPort(String)} throws an
	 * exception when the given port is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPort_EmptyPort() {
		builder.withPort(EMPTY_STRING);
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#withPort(String)}.
	 */
	@Test
	public void testWithPort() {
		final List<String> expectedArguments = Arrays.asList(ServerOption.START_PG.getParam(),
				ServerOption.PG_PORT.getParam(), PORT);

		assertEquals(expectedArguments, builder.withPort(PORT).getArguments());
	}

	/**
	 * Tests {@link PgServerArgumentsBuilder#build()}.
	 */
	@Test
	public void testBuild() {
		//@formatter:off
		builder.allowOthers()
			   .useDaemonThread()
			   .withPort(PORT);
		//@formatter:on

		final List<String> expectedArguments = new ArrayList<String>();
		expectedArguments.add(ServerOption.START_PG.getParam());
		expectedArguments.add(ServerOption.PG_ALLOW_OTHERS.getParam());
		expectedArguments.add(ServerOption.PG_DAEMON.getParam());
		expectedArguments.add(ServerOption.PG_PORT.getParam());
		expectedArguments.add(PORT);
		final PgServerArguments expectedServerArguments = new PgServerArguments(expectedArguments);
		assertEquals(expectedServerArguments, builder.build());
	}

}
