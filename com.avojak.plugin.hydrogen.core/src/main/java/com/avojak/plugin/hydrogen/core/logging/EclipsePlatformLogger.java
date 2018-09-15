package com.avojak.plugin.hydrogen.core.logging;

import java.text.MessageFormat;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Implementation of {@link IHydrogenLogger} using the Eclipse Platform
 * {@link ILog}.
 *
 * @author Andrew Vojak
 */
public class EclipsePlatformLogger implements IHydrogenLogger {

	private static final String DEBUG_FORMAT = "[DEBUG] [{0}] {1}"; //$NON-NLS-1$
	private static final String INFO_FORMAT = "[INFO] [{0}] {1}"; //$NON-NLS-1$
	private static final String WARN_FORMAT = "[WARN] [{0}] {1}"; //$NON-NLS-1$
	private static final String ERROR_FORMAT = "[ERROR] [{0}] {1}"; //$NON-NLS-1$
	private static final String FATAL_FORMAT = "[FATAL] [{0}] {1}"; //$NON-NLS-1$

	private final ILog log;
	private final String pluginId;
	private final String className;

	/**
	 * Constructor.
	 *
	 * @param log The instance of {@link ILog}. Cannot be null.
	 * @param pluginId The plugin ID. Cannot be null or empty.
	 * @param className The class name for this logger instance. Cannot be null
	 *            or empty.
	 */
	public EclipsePlatformLogger(final ILog log, final String pluginId, final String className) {
		if (log == null) {
			throw new IllegalArgumentException("log cannot be null"); //$NON-NLS-1$
		}
		if (pluginId == null || pluginId.trim().isEmpty()) {
			throw new IllegalArgumentException("pluginId cannot be null or empty"); //$NON-NLS-1$
		}
		if (className == null || className.trim().isEmpty()) {
			throw new IllegalArgumentException("className cannot be null or empty"); //$NON-NLS-1$
		}
		this.log = log;
		this.pluginId = pluginId;
		this.className = className;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void debug(final String message) {
		log.log(new Status(IStatus.OK, pluginId, MessageFormat.format(DEBUG_FORMAT, className, message)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void info(final String message) {
		log.log(new Status(IStatus.INFO, pluginId, MessageFormat.format(INFO_FORMAT, className, message)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warn(final String message) {
		log.log(new Status(IStatus.WARNING, pluginId, MessageFormat.format(WARN_FORMAT, className, message)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(final String message) {
		log.log(new Status(IStatus.ERROR, pluginId, MessageFormat.format(ERROR_FORMAT, className, message)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(final String message, final Throwable exception) {
		if (exception == null) {
			throw new IllegalArgumentException("exception cannot be null"); //$NON-NLS-1$
		}
		log.log(new Status(IStatus.ERROR, pluginId, MessageFormat.format(ERROR_FORMAT, className, message), exception));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fatal(final String message) {
		log.log(new Status(IStatus.CANCEL, pluginId, MessageFormat.format(FATAL_FORMAT, className, message)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fatal(final String message, final Throwable exception) {
		if (exception == null) {
			throw new IllegalArgumentException("exception cannot be null"); //$NON-NLS-1$
		}
		log.log(new Status(IStatus.CANCEL, pluginId, MessageFormat.format(FATAL_FORMAT, className, message),
				exception));
	}

}
