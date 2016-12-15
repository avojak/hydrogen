/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.listener;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author andrewvojak
 *
 */
public class ServerExecutionListener implements IExecutionListener {

	private final ICommandService commandService;

	/**
	 * @param commandService
	 *
	 */
	public ServerExecutionListener(final ICommandService commandService) {
		if (commandService == null) {
			throw new IllegalArgumentException("commandService cannot be null"); //$NON-NLS-1$
		}
		this.commandService = commandService;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.commands.IExecutionListener#notHandled(java.lang.String,
	 *      org.eclipse.core.commands.NotHandledException)
	 */
	@Override
	public void notHandled(final String commandId, final NotHandledException exception) {
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.commands.IExecutionListener#postExecuteFailure(java.lang
	 *      .String, org.eclipse.core.commands.ExecutionException)
	 */
	@Override
	public void postExecuteFailure(final String commandId, final ExecutionException exception) {
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.commands.IExecutionListener#postExecuteSuccess(java.lang
	 *      .String, java.lang.Object)
	 */
	@Override
	public void postExecuteSuccess(final String commandId, final Object returnValue) {
		commandService.refreshElements("command.start", Collections.EMPTY_MAP);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.commands.IExecutionListener#preExecute(java.lang.String,
	 *      org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public void preExecute(final String commandId, final ExecutionEvent event) {
	}

}
