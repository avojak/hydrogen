package com.thedesertmonk.plugin.hydrogen.core;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

/**
 * Executed by click menu.<br/>
 */
public class StartHandler extends AbstractHandler implements IElementUpdater {

	private final IWorkbenchWindow window;

	/**
	 * constructor.
	 */
	public StartHandler() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		this.window = workbench.getActiveWorkbenchWindow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		// if (!serverDelegate.isServerRunning()) {
		// messageConsole.clearConsole();
		// messageConsole.activate();
		// serverDelegate.startServer();
		// // HandlerUtil.updateRadioState(event.getCommand(),
		// // RadioState.PARAMETER_ID);
		// } else {
		// serverDelegate.stopServer();
		// }
		Activator.getDefault().notifyOnStartButtonClicked();
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
		// System.out.println("> Updating element [" +
		// serverDelegate.isServerRunning() + "]");
		// if (serverDelegate.isServerRunning()) {
		// element.setIcon(Activator.getImageDescriptor("icons/db-stop.gif"));
		// element.setTooltip("Stop H2 Server");
		// } else {
		// element.setIcon(Activator.getImageDescriptor("icons/db-start.gif"));
		// element.setTooltip("Start H2 Server");
		// }

	}

}
