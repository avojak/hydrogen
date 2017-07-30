package com.thedesertmonk.plugin.hydrogen.core;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.thedesertmonk.plugin.hydrogen.core.logging.HydrogenLoggerFactory;

/**
 * The activator class controls the plug-in life cycle
 *
 * <pre>
 * AUTOGENERATED
 * </pre>
 */
public class HydrogenActivator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "com.thedesertmonk.plugin.hydrogen.core"; //$NON-NLS-1$

	private static HydrogenActivator plugin;

	/**
	 * The started flag
	 */
	boolean started;

	/**
	 * Constructor.
	 */
	public HydrogenActivator() {
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 *      BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		started = true;

		// Initialize the logger factory
		HydrogenLoggerFactory.init(getLog());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 *      BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		started = false;
		super.stop(context);
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
