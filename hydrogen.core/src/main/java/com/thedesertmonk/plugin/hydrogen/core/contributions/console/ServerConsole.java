/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.console;

import org.eclipse.ui.console.MessageConsole;

import com.thedesertmonk.plugin.hydrogen.core.HydrogenActivator;

/**
 * @author andrewvojak
 *
 */
public class ServerConsole extends MessageConsole {

	public ServerConsole() {
		super("H2 Server", HydrogenActivator.getImageDescriptor("icons/server.png"));
	}

}
