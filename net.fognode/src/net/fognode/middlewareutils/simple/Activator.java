package net.fognode.middlewareutils.simple;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.middlewareutils.api.MiddlewareUtils;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(MiddlewareUtils.class.getName(), null)
			.setImplementation(SimpleMiddlewareUtils.class)
		);
	}
}
