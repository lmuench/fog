package net.fognode.mapping.persistent;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.mapping.api.Mapping;
import net.fognode.store.api.Store;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Mapping.class.getName(), null)
			.setImplementation(PersistentMapping.class)
			.add(
				createServiceDependency()
				.setService(Store.class)
				.setRequired(true)
			)
		);
	}
}
