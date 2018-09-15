package com.avojak.plugin.hydrogen.core.h2.model.configuration.attributes;

import com.avojak.plugin.hydrogen.core.h2.model.ServerOption;

/**
 * Models all available launch configuration attributes.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("javadoc")
public class LaunchConfigurationAttributes {

	//@formatter:off
	public static final LaunchConfigurationAttribute<Boolean> START_WEB = new LaunchConfigurationAttribute<Boolean>(ServerOption.START_WEB, Boolean.TRUE);
	public static final LaunchConfigurationAttribute<Boolean> START_TCP = new LaunchConfigurationAttribute<Boolean>(ServerOption.START_TCP, Boolean.TRUE);
	public static final LaunchConfigurationAttribute<Boolean> START_PG = new LaunchConfigurationAttribute<Boolean>(ServerOption.START_PG, Boolean.TRUE);
	public static final LaunchConfigurationAttribute<Boolean> ENABLE_TRACING = new LaunchConfigurationAttribute<Boolean>(ServerOption.ENABLE_TRACING, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<Boolean> IF_EXISTS = new LaunchConfigurationAttribute<Boolean>(ServerOption.IF_EXISTS, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<Boolean> WEB_ALLOW_OTHERS = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_ALLOW_OTHERS, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<Boolean> WEB_DAEMON = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_DAEMON, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<Boolean> WEB_SSL = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_SSL, Boolean.TRUE);
	public static final LaunchConfigurationAttribute<Boolean> WEB_BROWSER = new LaunchConfigurationAttribute<Boolean>(ServerOption.WEB_BROWSER, Boolean.TRUE);
	public static final LaunchConfigurationAttribute<Boolean> TCP_SSL = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_SSL, Boolean.TRUE);
	public static final LaunchConfigurationAttribute<Boolean> TCP_ALLOW_OTHERS = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_ALLOW_OTHERS, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<Boolean> TCP_DAEMON = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_DAEMON, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<Boolean> TCP_SHUTDOWN_FORCE = new LaunchConfigurationAttribute<Boolean>(ServerOption.TCP_SHUTDOWN_FORCE, Boolean.TRUE);
	public static final LaunchConfigurationAttribute<Boolean> PG_ALLOW_OTHERS = new LaunchConfigurationAttribute<Boolean>(ServerOption.PG_ALLOW_OTHERS, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<Boolean> PG_DAEMON = new LaunchConfigurationAttribute<Boolean>(ServerOption.PG_DAEMON, Boolean.FALSE);
	public static final LaunchConfigurationAttribute<String> BASE_DIRECTORY = new LaunchConfigurationAttribute<String>(ServerOption.BASE_DIRECTORY, System.getProperty("user.home")); //$NON-NLS-1$
	public static final LaunchConfigurationAttribute<String> PROPERTIES = new LaunchConfigurationAttribute<String>(ServerOption.PROPERTIES, ""); //$NON-NLS-1$
	public static final LaunchConfigurationAttribute<String> WEB_PORT = new LaunchConfigurationAttribute<String>(ServerOption.WEB_PORT, "8082"); //$NON-NLS-1$
	public static final LaunchConfigurationAttribute<String> TCP_PORT = new LaunchConfigurationAttribute<String>(ServerOption.TCP_PORT, "9092"); //$NON-NLS-1$
	public static final LaunchConfigurationAttribute<String> PG_PORT = new LaunchConfigurationAttribute<String>(ServerOption.PG_PORT, "5435"); //$NON-NLS-1$
	//@formatter:on

}
