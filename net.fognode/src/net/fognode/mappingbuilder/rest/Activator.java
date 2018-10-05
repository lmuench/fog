package net.fognode.mappingbuilder.rest;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.mappingbuilder.api.MappingBuilder;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Object.class.getName(), null)
			.setImplementation(MappingBuilderRest.class)
			.add(
				createServiceDependency()
				.setService(MappingBuilder.class)
				.setRequired(true)
			)
		);
	}
}
