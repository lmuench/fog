package net.fognode.requesthandler.rest;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.request.api.RequestFactory;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.response.api.ResponseFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Object.class.getName(), null)
			.setImplementation(RequestHandlerRest.class)
			.add(
				createServiceDependency()
				.setService(RequestFactory.class)
				.setRequired(true)
			).add(
				createServiceDependency()
				.setService(ResponseFactory.class)
				.setRequired(true)
			).add(
				createServiceDependency()
				.setService(RequestHandler.class)
				.setRequired(true)
			)
		);
	}
}
