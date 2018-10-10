package net.fognode.requesthandler.simple;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.mapping.api.Mapping;
import net.fognode.middleware.api.Middleware;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.shadow.api.ShadowFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(RequestHandler.class.getName(), null)
			.setImplementation(SimpleRequestHandler.class)
			.add(
				createServiceDependency()
				.setService(Mapping.class)
				.setRequired(true)
			).add(
				createServiceDependency()
				.setService(Middleware.class)
				.setRequired(false)
				.setCallbacks("added", "removed")
			).add(
				createServiceDependency()
				.setService(ShadowFactory.class)
				.setRequired(true)
			)
		);
	}
}
