package net.fognode.shadow.simple;

import java.util.ArrayList;
import java.util.List;

import net.fognode.client.api.Client;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.api.ShadowFactory;

public class SimpleShadowFactory implements ShadowFactory {
	private List<Client> clients = new ArrayList<Client>();
	
	public void added(Client client) {
		clients.add(client);
	}
	
	public void removed(Client client) {
		clients.remove(client);
	}

	/**
	 * Creates a shadow and injects a matching client.
	 * Throws IllegalArgumentException, if no matching client is available.
	 */
	@Override
	public Shadow createShadow(String protocol) {
		Client client = (
			clients
			.stream()
			.filter(c -> c.getProtocol().equalsIgnoreCase(protocol))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(
				"No client available for protocol: " + protocol
			))
		);
		return new SimpleShadow(client);
	}
}
