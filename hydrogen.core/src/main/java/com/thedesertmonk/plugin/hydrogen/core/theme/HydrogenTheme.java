/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.theme;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.thedesertmonk.plugin.hydrogen.core.Activator;

/**
 * @author andrewvojak
 *
 */
public class HydrogenTheme {

	private final Map<ImageDescriptor, Image> imageMap;

	/**
	 *
	 */
	public HydrogenTheme() {
		imageMap = new HashMap<ImageDescriptor, Image>();
	}

	/**
	 * @return
	 */
	public Image getServerStartIconImage() {
		final ImageDescriptor imageDescriptor = Activator.getImageDescriptor("icons/db-run.gif");
		if (imageMap.containsKey(imageDescriptor)) {
			return imageMap.get(imageDescriptor);
		}
		final Image image = imageDescriptor.createImage();
		imageMap.put(imageDescriptor, image);
		return image;
	}

	/**
	 * @return
	 */
	public Image getServerStopIconImage() {
		return getImage("icons/db-run.gif");
	}

	private Image getImage(final String path) {
		final ImageDescriptor imageDescriptor = Activator.getImageDescriptor(path);
		if (imageMap.containsKey(imageDescriptor)) {
			return imageMap.get(imageDescriptor);
		}
		final Image image = imageDescriptor.createImage();
		imageMap.put(imageDescriptor, image);
		return image;
	}

	/**
	 *
	 */
	public void dispose() {
		for (final Entry<ImageDescriptor, Image> entry : imageMap.entrySet()) {
			entry.getValue().dispose();
		}
	}

}
