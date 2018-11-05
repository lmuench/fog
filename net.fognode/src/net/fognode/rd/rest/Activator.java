package net.fognode.rd.rest;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.rd.api.RD;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Object.class.getName(), null)
			.setImplementation(RDRest.class)
			.add(
				createServiceDependency()
				.setService(RD.class)
				.setRequired(true)
			)
		);
	}
}
