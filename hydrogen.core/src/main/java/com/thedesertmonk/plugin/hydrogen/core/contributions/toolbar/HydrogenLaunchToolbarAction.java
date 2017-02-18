/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.toolbar;

import org.eclipse.debug.ui.actions.AbstractLaunchToolbarAction;

/**
 * @author andrewvojak
 *
 */
public class HydrogenLaunchToolbarAction extends AbstractLaunchToolbarAction {

	public HydrogenLaunchToolbarAction() {
		this("hydrogen.core.launchGroup1");
	}

	/**
	 * @param launchGroupIdentifier
	 */
	public HydrogenLaunchToolbarAction(final String launchGroupIdentifier) {
		super(launchGroupIdentifier);
	}

}
