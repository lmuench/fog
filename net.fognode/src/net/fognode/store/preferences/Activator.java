package net.fognode.store.preferences;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import net.fognode.store.api.Store;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		manager.add(
			createComponent()
			.setInterface(Store.class.getName(), null)
			.setImplementation(PreferencesStore.class)
		);
	}
}
