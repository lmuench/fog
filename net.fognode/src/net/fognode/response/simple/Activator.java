package net.fognode.response.simple;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.response.api.ResponseFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {

		manager.add(
			createComponent()
			.setInterface(ResponseFactory.class.getName(), null)
			.setImplementation(SimpleResponseFactory.class)
		);
	}
}
