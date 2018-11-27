package net.fognode.shadowrepository.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fognode.client.api.Client;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.api.ShadowFactory;
import net.fognode.shadowrepository.api.ShadowRepository;

public class SimpleShadowRepository implements ShadowRepository {
	private Map<String, Shadow> shadows = new HashMap<>();
	private volatile ShadowFactory shadowFactory;
	private volatile List<Client> clients = new ArrayList<Client>();
	
	public void added(Client client) {
		clients.add(client);
	}
	
	public void removed(Client client) {
		clients.remove(client);
	}

	@Override
	public Shadow getShadow(String url) throws IllegalArgumentException {
		Shadow shadow = shadows.get(url);
		if (null == shadow) {
			shadow = shadowFactory.createShadow(url);
			shadows.put(url, shadow);
//			System.out.println("ShadowRepository# now containing:");
//			System.out.println(shadows.keySet());
		}

		injectClient(shadow);
		return shadow;
	}
	
	private void injectClient(Shadow shadow) {
		String protocol = shadow.getProtocol();
		Client client = (
			clients
			.stream()
			.filter(c -> c.getProtocol().equalsIgnoreCase(protocol))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException (
				"No client available for protocol: " + protocol
			))
		);
		shadow.setClient(client);
	}
}
