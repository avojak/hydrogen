package com.avojak.plugin.hydrogen.core.contributions.configuration.launch.tab;

import java.text.MessageFormat;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.avojak.plugin.hydrogen.core.contributions.configuration.launch.PortValidator;

/**
 * Launch configuration tab with additional methods to create common UI
 * components.
 *
 * @author Andrew Vojak
 */
public abstract class HydrogenLaunchConfigurationTab extends AbstractLaunchConfigurationTab {

	private static final String FIELD_LABEL_FORMAT = "{0}:"; //$NON-NLS-1$

	/**
	 * Creates a text field.
	 *
	 * @param parent
	 *            The parent {@link Composite}. Cannot be null.
	 * @param label
	 *            The label. Cannot be null, may be empty.
	 * @return The new {@link Text} object.
	 */
	public Text createField(final Composite parent, final String label) {
		if (parent == null) {
			throw new IllegalArgumentException("parent cannot be null"); //$NON-NLS-1$
		}
		if (label == null) {
			throw new IllegalArgumentException("label cannot be null"); //$NON-NLS-1$
		}
		final Composite baseComposite = createFieldComposite(parent);
		addLabelToComposite(baseComposite, label);
		final Text text = addTextToComposite(baseComposite);
		return text;
	}

	/**
	 * Creates a directory text field.
	 *
	 * @param parent
	 *            The parent {@link Composite}. Cannot be null.
	 * @param label
	 *            The label. Cannot be null, may be empty.
	 * @return The new directory {@link Text} object.
	 */
	public Text createDirectoryField(final Composite parent, final String label) {
		if (parent == null) {
			throw new IllegalArgumentException("parent cannot be null"); //$NON-NLS-1$
		}
		if (label == null) {
			throw new IllegalArgumentException("label cannot be null"); //$NON-NLS-1$
		}
		final Composite baseComposite = createFieldComposite(parent);
		addLabelToComposite(baseComposite, label);
		final Text text = addTextToComposite(baseComposite);
		((GridLayout) baseComposite.getLayout()).numColumns++;
		final Button button = createPushButton(baseComposite, "&Browse...", null); //$NON-NLS-1$
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.OPEN);
				dialog.setText("Choose a directory:"); //$NON-NLS-1$
				final String path = dialog.open();
				if (path == null) {
					return;
				}
				text.setText(path);
			}
		});
		return text;
	}

	private Composite createFieldComposite(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		final GridLayout gridLayout = new GridLayout(0, false);
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		composite.setLayout(gridLayout);
		return composite;
	}

	private void addLabelToComposite(final Composite parent, final String label) {
		((GridLayout) parent.getLayout()).numColumns++;
		final Label textLabel = new Label(parent, SWT.NONE);
		textLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		textLabel.setText(MessageFormat.format(FIELD_LABEL_FORMAT, label));
	}

	private Text addTextToComposite(final Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;
		final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		text.addModifyListener(new ModifyListener() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void modifyText(final ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		return text;
	}

	/**
	 * Method to be called when the content of the tab changes.
	 */
	public void onContentChanged() {
		setDirty(true);
		updateLaunchConfigurationDialog();
	}

	/**
	 * Validates the given port number.
	 *
	 * @param port
	 *            The port number String.
	 * @return {@code true} if the port number is valid, otherwise {@code false}.
	 */
	public boolean validatePortNumber(final String port) {
		return PortValidator.isValid(port);
	}

	/**
	 * Displays the message warning against allowing other computers to connect to
	 * the server.
	 */
	public void showAllowOthersWarning() {
		setWarningMessage("Allowing other computers to connect to the server is potentially risky."); //$NON-NLS-1$
	}

	/**
	 * Clears the current warning message.
	 */
	public void clearWarningMessage() {
		setWarningMessage(null);
	}

	/**
	 * Displays the invalid port number error message.
	 */
	public void showInvalidPortNumberError() {
		setErrorMessage("Invalid port number"); //$NON-NLS-1$
	}

	/**
	 * Clears the current error message.
	 */
	public void clearErrorMessage() {
		setErrorMessage(null);
	}

}
