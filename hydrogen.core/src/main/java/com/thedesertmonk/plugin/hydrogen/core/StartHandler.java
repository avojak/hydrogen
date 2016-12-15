package com.thedesertmonk.plugin.hydrogen.core;

import java.io.PrintStream;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.menus.UIElement;

import com.thedesertmonk.plugin.hydrogen.core.contributions.console.ServerConsole;
import com.thedesertmonk.plugin.hydrogen.core.listener.ServerExecutionListener;
import com.thedesertmonk.plugin.hydrogen.core.server.ServerDelegate;

/**
 * Executed by click menu.<br/>
 */
public class StartHandler extends AbstractHandler implements IElementUpdater {

	private final IWorkbenchWindow window;

	private final ServerDelegate serverDelegate;
	private final MessageConsole messageConsole;

	/**
	 * constructor.
	 */
	public StartHandler() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		this.window = workbench.getActiveWorkbenchWindow();

		final ICommandService commandService = workbench.getService(ICommandService.class);
		commandService.addExecutionListener(new ServerExecutionListener(commandService));

		messageConsole = new ServerConsole();
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { messageConsole });

		serverDelegate = new ServerDelegate(new PrintStream(messageConsole.newMessageStream(), true));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		System.out.println("> Handler execute");
		if (!serverDelegate.isServerRunning()) {
			messageConsole.clearConsole();
			messageConsole.activate();
			serverDelegate.launchServer();
			// HandlerUtil.updateRadioState(event.getCommand(),
			// RadioState.PARAMETER_ID);
		} else {
			serverDelegate.terminateServer();
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
			element.setIcon(HydrogenActivator.getImageDescriptor("icons/server-terminate.png"));
			element.setTooltip("Stop H2 Server");
		} else {
			element.setIcon(HydrogenActivator.getImageDescriptor("icons/server-launch.png"));
			element.setTooltip("Start H2 Server");
		}
	}

}
