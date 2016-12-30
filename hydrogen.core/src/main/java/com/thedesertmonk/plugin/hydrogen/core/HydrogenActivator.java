package com.thedesertmonk.plugin.hydrogen.core;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class HydrogenActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "hydrogen.core"; //$NON-NLS-1$

	// The shared instance
	private static HydrogenActivator plugin;

	boolean started;

	private boolean isServerRunning;

	/**
	 * The constructor
	 */
	public HydrogenActivator() {
		isServerRunning = false;
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

	public void notifyOnLaunchButtonClicked() {
		System.out.println("HydrogenActivator notified on launch button clicked");
		isServerRunning = true;
	}

	public void notifyOnTerminateButtonClicked() {
		System.out.println("HydrogenActivator notified on terminate button clicked");
		isServerRunning = false;
	}

	public boolean isServerRunning() {
		return isServerRunning;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static HydrogenActivator getDefault() {
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
