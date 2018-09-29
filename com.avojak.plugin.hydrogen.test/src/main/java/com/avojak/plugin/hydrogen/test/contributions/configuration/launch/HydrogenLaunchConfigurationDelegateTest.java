package com.avojak.plugin.hydrogen.test.contributions.configuration.launch;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.avojak.plugin.hydrogen.core.HydrogenActivator;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.HydrogenLaunchConfigurationDelegate;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.LaunchDelegatePortPool;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.factory.FileFactory;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.factory.VMRunnerConfigurationFactory;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.listener.HydrogenLaunchListener;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.wrapper.DebugPluginWrapper;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.wrapper.HydrogenActivatorWrapper;
import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.wrapper.JavaRuntimeWrapper;
import com.avojak.plugin.hydrogen.core.contributions.preferencepage.PreferenceConstants;
import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.HydrogenRuntimeArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.PgServerArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.PgServerArgumentsBuilder;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.ProgramArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.ProgramArgumentsFactory;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.TcpServerArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.TcpServerArgumentsBuilder;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.WebServerArguments;
import com.avojak.plugin.hydrogen.core.h2.model.arguments.WebServerArgumentsBuilder;
import com.avojak.plugin.hydrogen.core.logging.HydrogenLoggerFactory;

/**
 * Test class for {@link HydrogenLaunchConfigurationDelegate}.
 * 
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
@RunWith(MockitoJUnitRunner.class)
public class HydrogenLaunchConfigurationDelegateTest {

	@Mock
	private JavaRuntimeWrapper javaRuntimeWrapper;

	@Mock
	private IVMInstall vmInstall;

	@Mock
	private IVMRunner vmRunner;

	@Mock
	private DebugPluginWrapper debugPluginWrapper;

	@Mock
	private DebugPlugin debugPlugin;

	@Mock
	private HydrogenActivatorWrapper hydrogenActivatorWrapper;

	@Mock
	private HydrogenActivator hydrogenActivator;

	@Mock
	private IPreferenceStore preferenceStore;

	@Mock
	private ILaunchManager launchManager;

	@Mock
	private FileFactory fileFactory;

	@Mock
	private File executable;

	@Mock
	private VMRunnerConfigurationFactory vmRunnerConfigurationFactory;

	@Mock
	private VMRunnerConfiguration runConfig;

	@Mock
	private ProgramArgumentsFactory programArgumentsFactory;

	@Mock
	private ProgramArguments programArguments;

	@Mock
	private HydrogenRuntimeArguments runtimeArguments;

	@Mock
	private WebServerArguments webServerArguments;

	@Mock
	private TcpServerArguments tcpServerArguments;

	@Mock
	private PgServerArguments pgServerArguments;

	@Mock
	private LaunchDelegatePortPool portPool;

	@Mock
	private ILaunchConfiguration launchConfiguration;

	@Mock
	private ILaunch launch;

	@Mock
	private IProgressMonitor progressMonitor;

	private final String executablePathname = "C:/h2.jar";
	private final String workingDirectory = "C:/";
	private final String executableName = "h2.jar";
	private final String serverClass = "org.h2.tools.Server";
	private final List<String> runtimeArgumentsList = Arrays.asList("arg0", "arg1", "arg2");

	private HydrogenLaunchConfigurationDelegate delegate;

	/**
	 * Setup static mocks.
	 */
	@BeforeClass
	public static void setupClass() {
		HydrogenLoggerFactory.init(Mockito.mock(ILog.class));
	}

	/**
	 * Setup mocks.
	 * 
	 * @throws CoreException
	 *             Unexpected.
	 * @throws IOException
	 */
	@Before
	public void setup() throws CoreException, IOException {
		// Mock the JVM install
		when(javaRuntimeWrapper.computeVMInstall(launchConfiguration)).thenReturn(vmInstall);
		when(vmInstall.getInstallLocation()).thenReturn(Files.createTempFile("mock-jvm", null).toFile());
		when(vmInstall.getVMRunner(Matchers.anyString())).thenReturn(vmRunner);
		// Mock the DebugPlugin
		when(debugPluginWrapper.getDefault()).thenReturn(debugPlugin);
		when(debugPlugin.getLaunchManager()).thenReturn(launchManager);
		// the HydrogenActivator
		when(hydrogenActivatorWrapper.getDefault()).thenReturn(hydrogenActivator);
		when(hydrogenActivator.getPreferenceStore()).thenReturn(preferenceStore);
		// Mock the preference store
		when(preferenceStore.getString(PreferenceConstants.P_EXECUTABLE)).thenReturn(executablePathname);
		// Mock the executable
		when(fileFactory.create(executablePathname)).thenReturn(executable);
		when(executable.isFile()).thenReturn(true);
		when(executable.canExecute()).thenReturn(true);
		when(executable.getParent()).thenReturn(workingDirectory);
		when(executable.getName()).thenReturn(executableName);
		// Mock the run configuration
		when(vmRunnerConfigurationFactory.create(serverClass, new String[] { executableName })).thenReturn(runConfig);
		// Mock the program arguments
		when(programArgumentsFactory.create(launchConfiguration)).thenReturn(programArguments);
		when(programArguments.getWebServerArguments()).thenReturn(Optional.of(webServerArguments));
		when(programArguments.getTcpServerArguments()).thenReturn(Optional.of(tcpServerArguments));
		when(programArguments.getPgServerArguments()).thenReturn(Optional.of(pgServerArguments));
		// Mock the web server arguments
		when(webServerArguments.getAllowOthers()).thenReturn(Optional.empty());
		when(webServerArguments.getUseDaemonThread()).thenReturn(Optional.empty());
		when(webServerArguments.getPort()).thenReturn(Optional.of("8082"));
		when(webServerArguments.getUseSsl()).thenReturn(Optional.empty());
		when(webServerArguments.getOpenBrowser()).thenReturn(Optional.empty());
		// Mock the TCP server arguments
		when(tcpServerArguments.getAllowOthers()).thenReturn(Optional.empty());
		when(tcpServerArguments.getUseDaemonThread()).thenReturn(Optional.empty());
		when(tcpServerArguments.getPort()).thenReturn(Optional.of("9092"));
		when(tcpServerArguments.getUseSsl()).thenReturn(Optional.empty());
		when(tcpServerArguments.getShutdownUrl()).thenReturn(Optional.empty());
		when(tcpServerArguments.getShutdownPassword()).thenReturn(Optional.empty());
		when(tcpServerArguments.getForceShutdown()).thenReturn(Optional.empty());
		// Mock the PostgreSQL server arguments
		when(pgServerArguments.getAllowOthers()).thenReturn(Optional.empty());
		when(pgServerArguments.getUseDaemonThread()).thenReturn(Optional.empty());
		when(pgServerArguments.getPort()).thenReturn(Optional.of("5435"));
		// Mock the Hydrogen runtime arguments
		when(programArguments.getArguments()).thenReturn(runtimeArguments);
		when(runtimeArguments.getArguments()).thenReturn(runtimeArgumentsList);
		// Mock the port pool
		when(portPool.isPortFree(Matchers.anyInt())).thenReturn(true);

		delegate = new HydrogenLaunchConfigurationDelegate(javaRuntimeWrapper, debugPluginWrapper,
				hydrogenActivatorWrapper, fileFactory, vmRunnerConfigurationFactory, programArgumentsFactory, portPool);
	}

	/**
	 * Tests that the constructor adds the launch listener.
	 */
	@Test
	public void testConstructor() {
		verify(launchManager).addLaunchListener(Matchers.refEq(new HydrogenLaunchListener(portPool)));
	}

	/**
	 * Tests that no launch occurs when in profile mode.
	 * 
	 * @throws CoreException
	 *             Unexpected.
	 */
	@Test
	public void testLaunch_ProfileMode() throws CoreException {
		delegate.launch(launchConfiguration, ILaunchManager.PROFILE_MODE, launch, progressMonitor);
		verifyZeroInteractions(vmRunner);
	}

	/**
	 * Tests that no launch occurs when in debug mode.
	 * 
	 * @throws CoreException
	 *             Unexpected.
	 */
	@Test
	public void testLaunch_DebugMode() throws CoreException {
		delegate.launch(launchConfiguration, ILaunchManager.DEBUG_MODE, launch, progressMonitor);
		verifyZeroInteractions(vmRunner);
	}

	/**
	 * Tests that the launch aborts if the executable preference is null.
	 */
	@Test
	public void testLaunch_NullExecutablePreference() {
		when(preferenceStore.getString(PreferenceConstants.P_EXECUTABLE)).thenReturn(null);

		try {
			delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);
			fail("Expected exception to be thrown");
		} catch (final CoreException e) {
			verifyZeroInteractions(vmRunner);
		}
	}

	/**
	 * Tests that the launch aborts if the executable preference is empty.
	 */
	@Test
	public void testLaunch_EmptyExecutablePreference() {
		when(preferenceStore.getString(PreferenceConstants.P_EXECUTABLE)).thenReturn(" ");

		try {
			delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);
			fail("Expected exception to be thrown");
		} catch (final CoreException e) {
			verifyZeroInteractions(vmRunner);
		}
	}

	/**
	 * Tests that the launch aborts if the executable preference refers to a
	 * non-file.
	 */
	@Test
	public void testLaunch_InvalidExecutable_NotFile() {
		when(executable.isFile()).thenReturn(false);

		try {
			delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);
			fail("Expected exception to be thrown");
		} catch (final CoreException e) {
			verifyZeroInteractions(vmRunner);
		}
	}

	/**
	 * Tests that the launch aborts if the executable preference refers to a file
	 * which cannot be executed.
	 */
	@Test
	public void testLaunch_InvalidExecutable_CannotExecute() {
		when(executable.canExecute()).thenReturn(false);

		try {
			delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);
			fail("Expected exception to be thrown");
		} catch (final CoreException e) {
			verifyZeroInteractions(vmRunner);
		}
	}

	@Test
	public void testLaunch_NoServersSpecified() throws CoreException {
		when(programArguments.getWebServerArguments()).thenReturn(Optional.empty());
		when(programArguments.getTcpServerArguments()).thenReturn(Optional.empty());
		when(programArguments.getPgServerArguments()).thenReturn(Optional.empty());

		delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);

		verify(launch, never()).setAttribute(Matchers.eq(ServerOption.WEB_PORT.name()), Matchers.anyString());
		verify(launch, never()).setAttribute(Matchers.eq(ServerOption.TCP_PORT.name()), Matchers.anyString());
		verify(launch, never()).setAttribute(Matchers.eq(ServerOption.PG_PORT.name()), Matchers.anyString());

		// TODO: Revisit this test with issue #37
	}

	@Test
	public void testLaunch_NoPortsSpecified() throws CoreException {
		when(webServerArguments.getPort()).thenReturn(Optional.empty());
		when(tcpServerArguments.getPort()).thenReturn(Optional.empty());
		when(pgServerArguments.getPort()).thenReturn(Optional.empty());

		// TODO: Need to handle this case in the code
	}

	/**
	 * Tests that new ports are assigned for the launch when the specified ports are
	 * already in use.
	 * 
	 * @throws CoreException
	 *             Unexpected.
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testLaunch_PortsAlreadyInUse() throws CoreException, IOException {
		when(portPool.isPortFree(Matchers.anyInt())).thenReturn(false);
		when(portPool.getFreePort()).thenReturn(8000, 8001, 8002);

		final WebServerArguments expectedWebServerArguments = new WebServerArgumentsBuilder(webServerArguments)
				.withPort("8000").build();
		final TcpServerArguments expectedTcpServerArguments = new TcpServerArgumentsBuilder(tcpServerArguments)
				.withPort("8001").build();
		final PgServerArguments expectedPgServerArguments = new PgServerArgumentsBuilder(pgServerArguments)
				.withPort("8002").build();

		final ProgramArguments updatedProgramArguments = Mockito.mock(ProgramArguments.class);
		when(programArgumentsFactory.create(expectedWebServerArguments, expectedTcpServerArguments,
				expectedPgServerArguments)).thenReturn(updatedProgramArguments);
		when(updatedProgramArguments.getWebServerArguments()).thenReturn(Optional.of(expectedWebServerArguments));
		when(updatedProgramArguments.getTcpServerArguments()).thenReturn(Optional.of(expectedTcpServerArguments));
		when(updatedProgramArguments.getPgServerArguments()).thenReturn(Optional.of(expectedPgServerArguments));
		when(updatedProgramArguments.getArguments()).thenReturn(runtimeArguments);

		delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);

		verify(runConfig).setWorkingDirectory(workingDirectory);
		verify(launch).setAttribute(ServerOption.WEB_PORT.name(), "8000");
		verify(launch).setAttribute(ServerOption.TCP_PORT.name(), "8001");
		verify(launch).setAttribute(ServerOption.PG_PORT.name(), "8002");
		verify(runConfig).setProgramArguments(runtimeArgumentsList.toArray(new String[0]));
		verify(runConfig).setVMArguments(new String[] { "-Djava.awt.headless=true" });
		verify(vmRunner).run(runConfig, launch, progressMonitor);
	}

	/**
	 * Tests that the launch is aborted when attempts to obtain a free port fail.
	 * 
	 * @throws IOException
	 *             Unexpected.
	 */
	@Test
	public void testLaunch_FailedToGetFreePort() throws IOException {
		when(portPool.isPortFree(Matchers.anyInt())).thenReturn(false);
		Mockito.doThrow(IOException.class).when(portPool).getFreePort();

		try {
			delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);
			fail("Expected exception to be thrown");
		} catch (final CoreException e) {
			verifyZeroInteractions(vmRunner);
		}
	}

	/**
	 * Tests a successful launch.
	 * 
	 * @throws CoreException
	 *             Unexpected.
	 */
	@Test
	public void testLaunch() throws CoreException {
		delegate.launch(launchConfiguration, ILaunchManager.RUN_MODE, launch, progressMonitor);

		final InOrder inOrder = Mockito.inOrder(runConfig, launch, vmRunner);
		inOrder.verify(runConfig).setWorkingDirectory(workingDirectory);
		inOrder.verify(launch).setAttribute(ServerOption.WEB_PORT.name(), "8082");
		inOrder.verify(launch).setAttribute(ServerOption.TCP_PORT.name(), "9092");
		inOrder.verify(launch).setAttribute(ServerOption.PG_PORT.name(), "5435");
		inOrder.verify(runConfig).setProgramArguments(runtimeArgumentsList.toArray(new String[0]));
		inOrder.verify(runConfig).setVMArguments(new String[] { "-Djava.awt.headless=true" });
		inOrder.verify(vmRunner).run(runConfig, launch, progressMonitor);
	}

}
