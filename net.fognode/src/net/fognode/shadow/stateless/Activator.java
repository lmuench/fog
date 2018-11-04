package net.fognode.shadow.stateless;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.client.api.Client;
import net.fognode.shadow.api.ShadowFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(ShadowFactory.class.getName(), null)
			.setImplementation(StatelessShadowFactory.class)
			.add(
				createServiceDependency()
				.setService(Client.class)
				.setRequired(true)
				.setCallbacks("added", "removed")
			)
		);
	}
}