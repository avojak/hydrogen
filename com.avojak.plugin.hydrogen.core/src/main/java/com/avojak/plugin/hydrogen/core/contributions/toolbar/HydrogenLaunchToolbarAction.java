package com.avojak.plugin.hydrogen.core.contributions.toolbar;

import org.eclipse.debug.ui.actions.AbstractLaunchToolbarAction;

/**
 * Hydrogen launcher toolbar action.
 *
 * @author Andrew Vojak
 */
public class HydrogenLaunchToolbarAction extends AbstractLaunchToolbarAction {

	/**
	 * Constructor.
	 */
	public HydrogenLaunchToolbarAction() {
		this("com.avojak.plugin.hydrogen.core.launchGroup1"); //$NON-NLS-1$
	}

	/**
	 * Constructor.
	 *
	 * @param launchGroupIdentifier The launch group identifier.
	 */
	public HydrogenLaunchToolbarAction(final String launchGroupIdentifier) {
		super(launchGroupIdentifier);
	}

}
