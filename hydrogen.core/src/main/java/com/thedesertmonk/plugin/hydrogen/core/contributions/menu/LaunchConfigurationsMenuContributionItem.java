/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.menu;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;

import com.thedesertmonk.plugin.hydrogen.core.contributions.dialog.LaunchConfigurationsDialog;

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
				final LaunchConfigurationsDialog dialog = new LaunchConfigurationsDialog(
						PlatformUI.getWorkbench().getModalDialogShellProvider().getShell());
				dialog.open();
			}
		});
	}

}
