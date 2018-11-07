package net.fognode.middleware.timer;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.middleware.api.Middleware;

public class Activator extends DependencyActivatorBase {
	
	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Middleware.class.getName(), null)
			.setImplementation(TimerMiddleware.class)
		);
	}
}
