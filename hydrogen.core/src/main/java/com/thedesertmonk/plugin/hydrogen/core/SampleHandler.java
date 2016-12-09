package com.thedesertmonk.plugin.hydrogen.core;

import java.io.PrintStream;
import java.sql.SQLException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.h2.tools.Server;

/**
 * Executed by click menu.<br/>
 */
public class SampleHandler extends AbstractHandler {

	private final IWorkbenchWindow window;

	private final MessageConsole messageConsole;

	/**
	 * constructor.
	 */
	public SampleHandler() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		this.window = workbench.getActiveWorkbenchWindow();

		messageConsole = new MessageConsole("CONSOLE_NAME", null); // TODO image
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { messageConsole });
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		MessageDialog.openInformation(window.getShell(), "Eclipse Plugin Archetype",
				"Hello, Maven+Eclipse world,\n hydrogen is built with Tycho");

		// Get a PrintStream from the MessageConsoleStream to be used by the
		// server
		final PrintStream printStream = new PrintStream(messageConsole.newMessageStream(), true);
		printStream.print("Hello, world!");
		// Start server
		try {
			final Server server = Server.createTcpServer();
			server.setOut(printStream);
			// server.start();
			// server.stop();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
