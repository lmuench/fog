package net.fognode.middleware.timestamp;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.middleware.api.Middleware;
import net.fognode.middlewareutils.api.MiddlewareUtils;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Middleware.class.getName(), null)
			.setImplementation(TimestampMiddleware.class)
			.add(
				createServiceDependency()
				.setService(MiddlewareUtils.class)
				.setRequired(true)
			)
		);
	}
}