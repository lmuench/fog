package net.fognode.response.http;

import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.response.api.ResponseFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		Properties properties = new Properties();
		properties.put("protocol", "HTTP");
		
		manager.add(
			createComponent()
			.setInterface(ResponseFactory.class.getName(), properties)
			.setImplementation(HttpResponseFactory.class)
		);
	}
}
