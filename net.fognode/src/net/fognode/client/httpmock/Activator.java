package net.fognode.client.httpmock;

import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import net.fognode.client.api.Client;
import net.fognode.response.api.ResponseFactory;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext context, DependencyManager manager) throws Exception {
		Properties properties = new Properties();
		properties.put("protocol", "HTTP");

		manager.add(
			createComponent()
			.setInterface(Client.class.getName(), properties)
			.setImplementation(HttpMockClient.class)
			.add(
				createServiceDependency()
				.setService(ResponseFactory.class)
				.setRequired(true)
			).add(
				createResourceDependency()
				.setResource(
					context.getBundle()
//					.getResource("/net/fognode/client/httpmock/resources.yaml")
					.getResource("/META-INF/MANIFEST.MF")
				)
				.setRequired(true)
			)
		);
	}
}
