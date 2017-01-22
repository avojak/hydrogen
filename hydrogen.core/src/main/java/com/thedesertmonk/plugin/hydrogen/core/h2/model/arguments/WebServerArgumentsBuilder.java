/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.h2.model.arguments;

import java.util.ArrayList;
import java.util.List;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Builder class to create instances of {@link WebServerArguments}.
 *
 * @author andrewvojak
 */
public class WebServerArgumentsBuilder {

	private final List<String> arguments;

	/**
	 * Constructor.
	 */
	public WebServerArgumentsBuilder() {
		arguments = new ArrayList<String>();
		arguments.add(ServerOption.START_WEB.getParam());
	}

	/**
	 * Sets the {@link ServerOption#WEB_ALLOW_OTHERS} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder allowOthers() {
		arguments.add(ServerOption.WEB_ALLOW_OTHERS.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_DAEMON} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder useDaemonThread() {
		arguments.add(ServerOption.WEB_DAEMON.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_PORT} property.
	 *
	 * @param port The port number. Cannot be null or empty.
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder withPort(final String port) {
		if (port == null || port.trim().isEmpty()) {
			throw new IllegalArgumentException("port cannot be null or empty"); //$NON-NLS-1$
		}
		arguments.add(ServerOption.WEB_PORT.getParam());
		arguments.add(port);
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_SSL} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder useSsl() {
		arguments.add(ServerOption.WEB_SSL.getParam());
		return this;
	}

	/**
	 * Sets the {@link ServerOption#WEB_BROWSER} property.
	 *
	 * @return The current {@link WebServerArgumentsBuilder} instance.
	 */
	public WebServerArgumentsBuilder openBrowser() {
		arguments.add(ServerOption.WEB_BROWSER.getParam());
		return this;
	}

	/**
	 * Creates a new instance of {@link WebServerArguments}.
	 *
	 * @return A new, non-null instance of {@link WebServerArguments}.
	 */
	public WebServerArguments build() {
		return new WebServerArguments(arguments);
	}

}
