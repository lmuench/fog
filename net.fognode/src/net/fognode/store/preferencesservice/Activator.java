package net.fognode.store.preferencesservice;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.PreferencesService;

import net.fognode.store.api.Store;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Store.class.getName(), null)
			.setImplementation(PreferencesServiceStore.class)
			.add(
				createServiceDependency()
				.setService(PreferencesService.class)
				.setRequired(true)
			)
		);
	}
}
