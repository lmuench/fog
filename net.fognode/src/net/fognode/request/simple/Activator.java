package net.fognode.request.simple;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.request.api.RequestFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		
		manager.add(
			createComponent()
			.setInterface(RequestFactory.class.getName(), null)
			.setImplementation(SimpleRequestFactory.class)
		);
	}
}
