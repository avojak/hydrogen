package com.thedesertmonk.plugin.hydrogen.core;

import java.sql.SQLException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.h2.tools.Server;

/**
 * Executed by click menu.<br/>
 */
public class SampleHandler extends AbstractHandler {

	private final IWorkbenchWindow window;

	/**
	 * constructor.
	 */
	public SampleHandler() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		this.window = workbench.getActiveWorkbenchWindow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		MessageDialog.openInformation(window.getShell(), "Eclipse Plugin Archetype",
				"Hello, Maven+Eclipse world,\n hydrogen is built with Tycho");

		try {
			final Server server = Server.createTcpServer().start();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
