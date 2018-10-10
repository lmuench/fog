package net.fognode.shadow.stateless;

import java.util.ArrayList;
import java.util.List;

import net.fognode.client.api.Client;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.api.ShadowFactory;

public class StatelessShadowFactory implements ShadowFactory {
	private volatile List<Client> clients = new ArrayList<Client>();
	
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
	public Shadow createShadow(String protocol) throws IllegalArgumentException {
		Client client = (
			clients
			.stream()
			.filter(c -> c.getProtocol().equalsIgnoreCase(protocol))
			.findAny()
			.orElseThrow(() -> new UnsupportedOperationException(
				"No client available for protocol: " + protocol
			))
		);
		return new StatelessShadow(client);
	}
}
