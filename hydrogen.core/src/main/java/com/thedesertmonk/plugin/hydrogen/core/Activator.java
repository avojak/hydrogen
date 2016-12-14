package com.thedesertmonk.plugin.hydrogen.core;

import java.io.PrintStream;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.thedesertmonk.plugin.hydrogen.core.server.ServerDelegate;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "hydrogen.core"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private final ServerDelegate serverDelegate;
	private final MessageConsole messageConsole;

	boolean started;

	/**
	 * The constructor
	 */
	public Activator() {
		messageConsole = new MessageConsole("H2 Server Console", getImageDescriptor("icons/db.gif"));
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { messageConsole });

		serverDelegate = new ServerDelegate(new PrintStream(messageConsole.newMessageStream(), true));

	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 *      BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		System.out.println("> START");
		super.start(context);
		plugin = this;
		started = true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 *      BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		System.out.println("> STOP");
		plugin = null;
		started = false;
		super.stop(context);
	}

	public void notifyOnStartButtonClicked() {
		System.out.println("> Activator notified on start button clicked");
		serverDelegate.startServer();
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(final String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

}
