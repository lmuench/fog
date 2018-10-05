package net.fognode.mappingbuilder.rd;

import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.mapping.api.Mapping;
import net.fognode.mappingbuilder.api.MappingBuilder;
import net.fognode.rd.api.RD;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		Properties properties = new Properties();
		properties.put("source", "RD");
		
		manager.add(
			createComponent()
			.setInterface(MappingBuilder.class.getName(), properties)
			.setImplementation(RDMappingBuilder.class)
			.add(
				createServiceDependency()
				.setService(RD.class)
				.setRequired(true)
			).add(
				createServiceDependency()
				.setService(Mapping.class)
				.setRequired(true)
			)
		);
	}
}
