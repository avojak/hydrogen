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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

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

				final SashForm sashForm = new SashForm(baseComposite, SWT.VERTICAL);
				sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				sashForm.setLayout(new GridLayout(2, false));

				final Composite leftComposite = new Composite(sashForm, SWT.NONE);
				leftComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				leftComposite.setLayout(new GridLayout());

				final Composite rightComposite = new Composite(sashForm, SWT.NONE);
				rightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				rightComposite.setLayout(new GridLayout());

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
				// TODO Auto-generated method stub
				super.buttonPressed(buttonId);
			}
		};
		dialog.setBlockOnOpen(false);
		dialog.setTitle("Launch Configurations");
	}

	/**
	 * @return
	 */
	public int open() {
		final int returnCode = dialog.open();
		dialog.setMessage("Create a configuration to launch an H2 server.");
		return returnCode;
	}

}
