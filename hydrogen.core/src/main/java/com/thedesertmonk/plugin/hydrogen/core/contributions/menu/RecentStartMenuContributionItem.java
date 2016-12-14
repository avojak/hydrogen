/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.menu;

import java.text.MessageFormat;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * @author andrewvojak
 *
 */
public class RecentStartMenuContributionItem extends ContributionItem {

	private static final String MENU_ITEM_FORMAT = "{0} {1}";

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Menu,
	 *      int)
	 */
	@Override
	public void fill(final Menu menu, final int index) {
		final MenuItem menuItem = new MenuItem(menu, SWT.CHECK, index);
		menuItem.setText(MessageFormat.format(MENU_ITEM_FORMAT, index + 1, "Hello, server!"));
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				// TODO Open preferences window
			}
		});
	}

}
