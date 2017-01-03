/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @author andrewvojak
 *
 */
public class LaunchConfigurationsDialog {

	private static final int LAUNCH_ID = IDialogConstants.CLIENT_ID + 1;

	private final TitleAreaDialog dialog;

	/**
	 * @param shell
	 */
	public LaunchConfigurationsDialog(final Shell shell) {
		dialog = new TitleAreaDialog(shell) {
			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.
			 *      widgets.Shell)
			 */
			@Override
			protected void configureShell(final Shell newShell) {
				super.configureShell(newShell);
				newShell.setText("Launch Configurations");
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see Window#canHandleShellCloseEvent()
			 */
			@Override
			protected boolean canHandleShellCloseEvent() {
				return true;
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
			 */
			@Override
			protected Control createDialogArea(final Composite parent) {
				final Composite baseComposite = new Composite(parent, SWT.NONE);
				baseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				baseComposite.setLayout(new GridLayout());

				final SashForm sashForm = new SashForm(baseComposite, SWT.HORIZONTAL);
				sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				sashForm.setLayout(new GridLayout(2, false));

				final Composite configurationListPanelBaseComposite = new Composite(sashForm, SWT.BORDER);
				configurationListPanelBaseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				configurationListPanelBaseComposite.setLayout(new GridLayout());
				configurationListPanelBaseComposite
						.setBackground(new Color(Display.getCurrent(), new RGB(125, 125, 255)));

				final Composite configurationListControlButtonsComposite = new Composite(
						configurationListPanelBaseComposite, SWT.NONE);
				configurationListControlButtonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
				configurationListControlButtonsComposite.setLayout(new GridLayout());
				configurationListControlButtonsComposite
						.setBackground(new Color(Display.getCurrent(), new RGB(255, 125, 125)));

				final Text filterText = new Text(configurationListPanelBaseComposite, SWT.SINGLE | SWT.BORDER);
				filterText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

				final Tree configurationListTree = new Tree(configurationListPanelBaseComposite, SWT.NONE);
				configurationListTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				configurationListTree.setLayout(new GridLayout());

				// TODO Temporary
				for (int i = 0; i < 20; ++i) {
					final TreeItem item = new TreeItem(configurationListTree, SWT.NONE, i);
					item.setText("Item " + i);
				}

				final Composite configurationDetailPanelBaseComposite = new Composite(sashForm, SWT.BORDER);
				configurationDetailPanelBaseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				configurationDetailPanelBaseComposite.setLayout(new GridLayout());

				return baseComposite;
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.
			 *      eclipse.swt.widgets.Composite)
			 */
			@Override
			protected void createButtonsForButtonBar(final Composite parent) {
				createButton(parent, IDialogConstants.CLOSE_ID, "Close", false);
				createButton(parent, LAUNCH_ID, "Launch", true);
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see Dialog#createButton(org.eclipse.swt.widgets.Composite, int,
			 *      java.lang.String, boolean)
			 */
			@Override
			protected Button createButton(final Composite parent, final int id, final String label,
					final boolean defaultButton) {
				return super.createButton(parent, id, label, defaultButton);
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
			 */
			@Override
			protected void buttonPressed(final int buttonId) {
				super.buttonPressed(buttonId);
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.jface.dialogs.TrayDialog#handleShellCloseEvent()
			 */
			@Override
			protected void handleShellCloseEvent() {
				super.handleShellCloseEvent();
			}

			/**
			 * {@inheritDoc}
			 *
			 * @see org.eclipse.jface.dialogs.Dialog#isResizable()
			 */
			@Override
			protected boolean isResizable() {
				return true;
			}

		};
		dialog.setBlockOnOpen(false);

	}

	/**
	 * @return
	 */
	public int open() {
		final int returnCode = dialog.open();
		dialog.setTitle("Create, manage, and launch configurations.");
		// TODO set message based on current selection
		// dialog.setMessage("Create a configuration to launch an H2 server.");
		return returnCode;
	}

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);

		final LaunchConfigurationsDialog dialog = new LaunchConfigurationsDialog(shell);
		dialog.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		dialog.dialog.close();
		display.dispose();
	}

}
