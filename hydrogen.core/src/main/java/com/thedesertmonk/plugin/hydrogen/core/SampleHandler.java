package com.thedesertmonk.plugin.hydrogen.core;

import java.io.PrintStream;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.menus.UIElement;

import com.thedesertmonk.plugin.hydrogen.core.server.ServerDelegate;

/**
 * Executed by click menu.<br/>
 */
public class SampleHandler extends AbstractHandler implements IElementUpdater {

	private final IWorkbenchWindow window;

	private final ServerDelegate serverDelegate;
	private final MessageConsole messageConsole;

	/**
	 * constructor.
	 */
	public SampleHandler() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		this.window = workbench.getActiveWorkbenchWindow();

		messageConsole = new MessageConsole("H2 Server Console", null);
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { messageConsole });

		serverDelegate = new ServerDelegate(new PrintStream(messageConsole.newMessageStream(), true));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		if (!serverDelegate.isServerRunning()) {
			messageConsole.clearConsole();
			messageConsole.activate();
			serverDelegate.startServer();
			// HandlerUtil.updateRadioState(event.getCommand(),
			// RadioState.PARAMETER_ID);
		} else {
			serverDelegate.stopServer();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.commands.IElementUpdater#updateElement(org.eclipse.ui.menus.UIElement,
	 *      java.util.Map)
	 */
	@Override
	public void updateElement(final UIElement element, final Map parameters) {
		System.out.println("> Updating element [" + serverDelegate.isServerRunning() + "]");
		if (serverDelegate.isServerRunning()) {
			element.setIcon(Activator.getImageDescriptor("icons/db-stop.gif"));
			element.setTooltip("Stop H2 Server");
		} else {
			element.setIcon(Activator.getImageDescriptor("icons/db-start.gif"));
			element.setTooltip("Start H2 Server");
		}

	}

}
