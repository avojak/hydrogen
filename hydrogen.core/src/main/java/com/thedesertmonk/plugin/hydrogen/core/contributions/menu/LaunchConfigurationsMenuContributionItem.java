/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.menu;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @author andrewvojak
 *
 */
public class LaunchConfigurationsMenuContributionItem extends ContributionItem {

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Menu,
	 *      int)
	 */
	@Override
	public void fill(final Menu menu, final int index) {
		final MenuItem menuItem = new MenuItem(menu, SWT.PUSH, index);
		menuItem.setText("Launch Configurations...");
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				// final LaunchConfigurationsDialog dialog = new
				// LaunchConfigurationsDialog(
				// PlatformUI.getWorkbench().getModalDialogShellProvider().getShell());
				// dialog.open();

				// final IHandlerService handlerService =
				// PlatformUI.getWorkbench().getService(IHandlerService.class);
				// try {
				// handlerService.executeCommand("org.eclipse.debug.ui.commands.OpenRunConfigurations",
				// null);
				// } catch (ExecutionException | NotDefinedException |
				// NotEnabledException | NotHandledException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

				final IHandlerService handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
				try {
					handlerService.executeCommand("org.eclipse.debug.ui.commands.OpenRunConfigurations", null);
				} catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// DebugUITools.openLaunchConfigurationDialog(
				// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				// config,
				// DebugUITools.getLaunchGroup(savedConfig,
				// "run").getIdentifier(), null);
			}
		});
	}

}
