/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author andrewvojak
 *
 */
public class GeneralLaunchConfigurationTab extends AbstractLaunchConfigurationTab {

	private Composite baseComposite;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent) {
		baseComposite = new Composite(parent, SWT.NONE);
		baseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		baseComposite.setLayout(new GridLayout());

		// General properties
		// properties location
		// base directory for H2 databases (all servers)
		// only existing databases may be opened (all servers)
		// tracing - print additional info (all servers)
		// map database name (all servers)

		final Group webGroup = new Group(baseComposite, SWT.NONE);
		webGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		webGroup.setLayout(new GridLayout());
		webGroup.setText("Web Server");

		createCheckButton(webGroup, "Allow other computers to connect");
		createCheckButton(webGroup, "Use a daemon thread");
		createText(webGroup, "Port");
		createCheckButton(webGroup, "Use encrypted (HTTPS) connections");

		final Group tcpGroup = new Group(baseComposite, SWT.NONE);
		tcpGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		tcpGroup.setLayout(new GridLayout());
		tcpGroup.setText("TCP Server");

		createCheckButton(tcpGroup, "Allow other computers to connect");
		createCheckButton(tcpGroup, "Use a daemon thread");
		createText(tcpGroup, "Port");
		createCheckButton(tcpGroup, "Use encrypted (HTTPS) connections");
		// TODO add field to specify password
		createText(tcpGroup, "Shutdown password");
		// TODO add field to specify shutdown url?
		createText(tcpGroup, "Shutdown URL");
		createCheckButton(tcpGroup, "Force shutdown");

		final Group pgGroup = new Group(baseComposite, SWT.NONE);
		pgGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		pgGroup.setLayout(new GridLayout());
		pgGroup.setText("PostgreSQL Server");

		createCheckButton(pgGroup, "Allow other computers to connect");
		createCheckButton(pgGroup, "Use a daemon thread");
		createText(pgGroup, "Port");
	}

	private Text createText(final Composite parent, final String label) {
		final Composite textComposite = new Composite(parent, SWT.NONE);
		textComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		final GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		textComposite.setLayout(gridLayout);
		final Label textLabel = new Label(textComposite, SWT.NONE);
		textLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
		textLabel.setText(label + ":");
		final Text text = new Text(textComposite, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
	 * {@inheritDoc}
	 */
	@Override
	public Control getControl() {
		return baseComposite;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLaunchConfigurationDialog(final ILaunchConfigurationDialog dialog) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "General";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activated(final ILaunchConfigurationWorkingCopy workingCopy) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivated(final ILaunchConfigurationWorkingCopy workingCopy) {
		// TODO Auto-generated method stub

	}

}
