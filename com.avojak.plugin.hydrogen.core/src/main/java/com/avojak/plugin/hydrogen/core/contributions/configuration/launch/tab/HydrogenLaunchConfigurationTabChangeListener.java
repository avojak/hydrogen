package com.avojak.plugin.hydrogen.core.contributions.configuration.launch.tab;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * Implementation of {@link SelectionListener} and {@link ModifyListener} to
 * update a {@link HydrogenLaunchConfigurationTab} when the content changes.
 *
 * @author Andrew Vojak
 */
public class HydrogenLaunchConfigurationTabChangeListener implements SelectionListener, ModifyListener {

	private final HydrogenLaunchConfigurationTab launchConfigurationTab;

	/**
	 * Constructor.
	 *
	 * @param launchConfigurationTab The {@link HydrogenLaunchConfigurationTab}.
	 *            Cannot be null.
	 */
	public HydrogenLaunchConfigurationTabChangeListener(final HydrogenLaunchConfigurationTab launchConfigurationTab) {
		if (launchConfigurationTab == null) {
			throw new IllegalArgumentException("launchConfigurationTab cannot be null"); //$NON-NLS-1$
		}
		this.launchConfigurationTab = launchConfigurationTab;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void widgetSelected(final SelectionEvent e) {
		launchConfigurationTab.onContentChanged();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void widgetDefaultSelected(final SelectionEvent e) {
		// Do nothing
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void modifyText(final ModifyEvent e) {
		launchConfigurationTab.onContentChanged();
	}

}
