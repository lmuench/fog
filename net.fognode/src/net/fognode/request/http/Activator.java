package net.fognode.request.http;

import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.request.api.RequestFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		Properties properties = new Properties();
		properties.put("protocol", "HTTP");
		
		manager.add(
			createComponent()
			.setInterface(RequestFactory.class.getName(), properties)
			.setImplementation(HttpRequestFactory.class)
		);
	}
}
