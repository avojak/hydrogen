/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

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

/**
 * @author andrewvojak
 *
 */
public abstract class HydrogenLaunchConfigurationTab extends AbstractLaunchConfigurationTab {

	private static final String FIELD_LABEL_FORMAT = "{0}:"; //$NON-NLS-1$

	/**
	 * @param parent
	 * @param label
	 * @return
	 */
	public Text createField(final Composite parent, final String label) {
		final Composite baseComposite = createFieldComposite(parent);
		addLabelToComposite(baseComposite, label);
		final Text text = addTextToComposite(baseComposite);
		return text;
	}

	/**
	 * @param parent
	 * @param label
	 * @param buttonLabel
	 * @param listener
	 * @return
	 */
	public Text createDirectoryField(final Composite parent, final String label) {
		final Composite baseComposite = createFieldComposite(parent);
		addLabelToComposite(baseComposite, label);
		final Text text = addTextToComposite(baseComposite);
		((GridLayout) baseComposite.getLayout()).numColumns++;
		final Button button = createPushButton(baseComposite, "Browse...", null);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.OPEN);
				dialog.setText("Choose a directory:");
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

}
