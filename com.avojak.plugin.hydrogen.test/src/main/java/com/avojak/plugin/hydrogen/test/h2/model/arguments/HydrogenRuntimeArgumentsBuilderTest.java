package com.avojak.plugin.hydrogen.test.h2.model.arguments;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.HydrogenRuntimeArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.HydrogenRuntimeArgumentsBuilder;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;

/**
 * Test class for {@link HydrogenRuntimeArgumentsBuilder}.
 *
 * @author Andrew Vojak
 */
@RunWith(MockitoJUnitRunner.class)
public class HydrogenRuntimeArgumentsBuilderTest {

	private static final String EMPTY_STRING = " ";

	@Mock
	private WebServerArguments webServerArguments;
	@Mock
	private TcpServerArguments tcpServerArguments;
	@Mock
	private PgServerArguments pgServerArguments;

	private HydrogenRuntimeArgumentsBuilder builder;
	private List<String> webServerArgumentsStrings;
	private List<String> tcpServerArgumentsStrings;
	private List<String> pgServerArgumentsStrings;
	private String propertiesDirectory;
	private String databaseDirectory;
	private String fromDatabaseName;
	private String toDatabaseName;

	/**
	 * Setup mocks.
	 */
	@Before
	public void setup() {
		builder = new HydrogenRuntimeArgumentsBuilder();

		webServerArgumentsStrings = singletonList("webServerArguments");
		tcpServerArgumentsStrings = singletonList("tcpServerArguments");
		pgServerArgumentsStrings = singletonList("pgServerArguments");

		when(webServerArguments.getArguments()).thenReturn(webServerArgumentsStrings);
		when(tcpServerArguments.getArguments()).thenReturn(tcpServerArgumentsStrings);
		when(pgServerArguments.getArguments()).thenReturn(pgServerArgumentsStrings);

		propertiesDirectory = "properties/directory";
		databaseDirectory = "database/directory";
		fromDatabaseName = "from-database-name";
		toDatabaseName = "to-database-name";
	}

	/**
	 * Tests that the constructor initializes the {@link List} of arguments to
	 * an empty list.
	 */
	@Test
	public void testConstructor() {
		assertEquals(emptyList(), builder.getArguments());
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#withWebServer(WebServerArguments)}
	 * throws an exception when the given {@link WebServerArguments} is
	 * {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithWebServer_NullWebServerArguments() {
		builder.withWebServer((WebServerArguments) null);
	}

	/**
	 * Test
	 * {@link HydrogenRuntimeArgumentsBuilder#withWebServer(WebServerArguments)}.
	 */
	@Test
	public void testWithWebServer() {
		builder.withWebServer(webServerArguments);

		verify(webServerArguments).getArguments();
		assertEquals(webServerArgumentsStrings, builder.getArguments());
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#withTcpServer(TcpServerArguments)}
	 * throws an exception when the given {@link TcpServerArguments} is
	 * {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithTcpServer_NullTcpServerArguments() {
		builder.withTcpServer((TcpServerArguments) null);
	}

	/**
	 * Test
	 * {@link HydrogenRuntimeArgumentsBuilder#withTcpServer(TcpServerArguments)}.
	 */
	@Test
	public void testWithTcpServer() {
		builder.withTcpServer(tcpServerArguments);

		verify(tcpServerArguments).getArguments();
		assertEquals(tcpServerArgumentsStrings, builder.getArguments());
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#withPgServer(PgServerArguments)}
	 * throws an exception when the given {@link PgServerArguments} is
	 * {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithPgServer_NullPgServerArguments() {
		builder.withPgServer((PgServerArguments) null);
	}

	/**
	 * Test
	 * {@link HydrogenRuntimeArgumentsBuilder#withPgServer(PgServerArguments)}.
	 */
	@Test
	public void testWithPgServer() {
		builder.withPgServer(pgServerArguments);

		verify(pgServerArguments).getArguments();
		assertEquals(pgServerArgumentsStrings, builder.getArguments());
	}

	/**
	 * Tests that {@link HydrogenRuntimeArgumentsBuilder#withProperties(String)}
	 * throws an exception if the given directory {@link String} is
	 * {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithProperties_NullPropertiesDirectory() {
		builder.withProperties((String) null);
	}

	/**
	 * Tests that {@link HydrogenRuntimeArgumentsBuilder#withProperties(String)}
	 * throws an exception if the given directory {@link String} is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithProperties_EmptyPropertiesDirectory() {
		builder.withProperties(EMPTY_STRING);
	}

	/**
	 * Tests {@link HydrogenRuntimeArgumentsBuilder#withProperties(String)}.
	 */
	@Test
	public void testWithProperties() {
		builder.withProperties(propertiesDirectory);

		final List<String> expectedArguments = Arrays.asList(ServerOption.PROPERTIES.getParam(), propertiesDirectory);
		assertEquals(expectedArguments, builder.getArguments());
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#setBaseDirectory(String)} throws
	 * an exception if the given directory {@link String} is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetBaseDirectory_NullDatabaseDirectory() {
		builder.setBaseDirectory((String) null);
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#setBaseDirectory(String)} throws
	 * an exception if the given directory {@link String} is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetBaseDirectory_EmptyDatabaseDirectory() {
		builder.setBaseDirectory(EMPTY_STRING);
	}

	/**
	 * Tests {@link HydrogenRuntimeArgumentsBuilder#setBaseDirectory(String)}.
	 */
	@Test
	public void testSetBaseDirectory() {
		builder.setBaseDirectory(databaseDirectory);

		final List<String> expectedArguments = Arrays.asList(ServerOption.BASE_DIRECTORY.getParam(), databaseDirectory);
		assertEquals(expectedArguments, builder.getArguments());
	}

	/**
	 * Tests
	 * {@link HydrogenRuntimeArgumentsBuilder#onlyOpenExistingDatabases()}.
	 */
	@Test
	public void testOnlyOpenExistingDatabase() {
		builder.onlyOpenExistingDatabases();

		assertEquals(singletonList(ServerOption.IF_EXISTS.getParam()), builder.getArguments());
	}

	/**
	 * Tests {@link HydrogenRuntimeArgumentsBuilder#enableTracing()}.
	 */
	@Test
	public void testEnableTracing() {
		builder.enableTracing();

		assertEquals(singletonList(ServerOption.ENABLE_TRACING.getParam()), builder.getArguments());
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#withDatabaseMapping(String, String)}
	 * throws an exception if the given 'from' database name is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithDatabaseMapping_NullFromDatabaseName() {
		builder.withDatabaseMapping((String) null, "name");
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#withDatabaseMapping(String, String)}
	 * throws an exception if the given 'from' database name is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithDatabaseMapping_EmptyFromDatabaseName() {
		builder.withDatabaseMapping(EMPTY_STRING, "name");
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#withDatabaseMapping(String, String)}
	 * throws an exception if the given 'to' database name is {@code null}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithDatabaseMapping_NullToDatabaseName() {
		builder.withDatabaseMapping("name", (String) null);
	}

	/**
	 * Tests that
	 * {@link HydrogenRuntimeArgumentsBuilder#withDatabaseMapping(String, String)}
	 * throws an exception if the given 'to' database name is empty.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWithDatabaseMapping_EmptyToDatabaseName() {
		builder.withDatabaseMapping("name", EMPTY_STRING);
	}

	/**
	 * Tests
	 * {@link HydrogenRuntimeArgumentsBuilder#withDatabaseMapping(String, String)}.
	 */
	@Test
	public void testWithDatabaseMapping() {
		builder.withDatabaseMapping(fromDatabaseName, toDatabaseName);

		final List<String> expectedArguments = Arrays.asList(ServerOption.KEY.getParam(), fromDatabaseName,
				toDatabaseName);
		assertEquals(expectedArguments, builder.getArguments());
	}

	/**
	 * Tests {@link HydrogenRuntimeArgumentsBuilder#build}.
	 */
	@Test
	public void testBuild() {
		//@formatter:off
		builder.withWebServer(webServerArguments)
			   .withTcpServer(tcpServerArguments)
			   .withPgServer(pgServerArguments)
			   .withProperties(propertiesDirectory)
			   .setBaseDirectory(databaseDirectory)
			   .onlyOpenExistingDatabases()
			   .enableTracing()
			   .withDatabaseMapping(fromDatabaseName, toDatabaseName);
		//@formatter:on

		final List<String> expectedArguments = new ArrayList<String>();
		expectedArguments.addAll(webServerArgumentsStrings);
		expectedArguments.addAll(tcpServerArgumentsStrings);
		expectedArguments.addAll(pgServerArgumentsStrings);
		expectedArguments.add(ServerOption.PROPERTIES.getParam());
		expectedArguments.add(propertiesDirectory);
		expectedArguments.add(ServerOption.BASE_DIRECTORY.getParam());
		expectedArguments.add(databaseDirectory);
		expectedArguments.add(ServerOption.IF_EXISTS.getParam());
		expectedArguments.add(ServerOption.ENABLE_TRACING.getParam());
		expectedArguments.add(ServerOption.KEY.getParam());
		expectedArguments.add(fromDatabaseName);
		expectedArguments.add(toDatabaseName);
		final HydrogenRuntimeArguments expectedHydrogenServerArguments = new HydrogenRuntimeArguments(
				expectedArguments);
		assertEquals(expectedHydrogenServerArguments, builder.build());
	}

}
