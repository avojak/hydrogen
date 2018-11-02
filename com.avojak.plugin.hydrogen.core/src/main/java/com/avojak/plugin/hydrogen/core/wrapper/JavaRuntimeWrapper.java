package com.avojak.plugin.hydrogen.core.wrapper;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;

/**
 * Wrapper class for {@link JavaRuntime} to allow facilitated mocking.
 * 
 * @author Andrew Vojak
 */
public class JavaRuntimeWrapper {

	/**
	 * Wraps {@link JavaRuntime#computeVMInstall(ILaunchConfiguration)}.
	 * 
	 * @param configuration
	 *            The {@link ILaunchConfiguration}.
	 * @return The {@link IVMInstall}.
	 * @throws CoreException
	 *             if unable to compute a VM install
	 * 
	 * @see JavaRuntime#computeVMInstall(ILaunchConfiguration)
	 */
	public IVMInstall computeVMInstall(final ILaunchConfiguration configuration) throws CoreException {
		return JavaRuntime.computeVMInstall(configuration);
	}

}
