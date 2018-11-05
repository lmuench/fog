package net.fognode.mapping.rest;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.mapping.api.MappingRepository;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Object.class.getName(), null)
			.setImplementation(MappingRest.class)
			.add(
				createServiceDependency()
				.setService(MappingRepository.class)
				.setRequired(true)
			)
		);
	}
}
