/**
 * @author
 */
package com.thedesertmonk.plugin.hydrogen.core.contributions.configuration.launch.tab;

import com.thedesertmonk.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * @author andrewvojak
 *
 */
public class LaunchConfigurationAttributes {

	//@formatter:off
	public static final LaunchConfigurationAttribute<Boolean> START_WEB = new LaunchConfigurationAttribute<Boolean>(ServerOption.START_WEB, true);
	public static final LaunchConfigurationAttribute<Boolean> START_TCP = new LaunchConfigurationAttribute<Boolean>(ServerOption.START_TCP, true);
	public static final LaunchConfigurationAttribute<Boolean> START_PG = new LaunchConfigurationAttribute<Boolean>(ServerOption.START_PG, true);
	public static final LaunchConfigurationAttribute<Boolean> ENABLE_TRACING = new LaunchConfigurationAttribute<Boolean>(ServerOption.ENABLE_TRACING, false);
	public static final LaunchConfigurationAttribute<Boolean> IF_EXISTS = new LaunchConfigurationAttribute<Boolean>(ServerOption.IF_EXISTS, false);
	public static final LaunchConfigurationAttribute<Boolean> WEB_ALLOW_OTHERS = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_ALLOW_OTHERS, false);
	public static final LaunchConfigurationAttribute<Boolean> WEB_DAEMON = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_DAEMON, false);
	public static final LaunchConfigurationAttribute<Boolean> WEB_SSL = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_SSL, true);
	public static final LaunchConfigurationAttribute<Boolean> WEB_BROWSER = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_BROWSER, true);
	public static final LaunchConfigurationAttribute<Boolean> TCP_SSL = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_SSL, true);
	public static final LaunchConfigurationAttribute<Boolean> TCP_ALLOW_OTHERS = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_ALLOW_OTHERS, false);
	public static final LaunchConfigurationAttribute<Boolean> TCP_DAEMON = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_DAEMON, false);
	public static final LaunchConfigurationAttribute<Boolean> TCP_SHUTDOWN_FORCE = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_SHUTDOWN_FORCE, true);
	public static final LaunchConfigurationAttribute<Boolean> PG_ALLOW_OTHERS = new LaunchConfigurationAttribute<Boolean>(ServerOption.PG_ALLOW_OTHERS, false);
	public static final LaunchConfigurationAttribute<Boolean> PG_DAEMON = new LaunchConfigurationAttribute<Boolean>(ServerOption.PG_DAEMON, false);
	//@formatter:on

}
