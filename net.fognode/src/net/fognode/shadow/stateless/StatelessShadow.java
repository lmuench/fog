package net.fognode.shadow.stateless;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.shadow.api.Shadow;

/**
 * Stateless shadow implementation, simply forwarding requests and returning
 * responses.
 * When a request times out because a device is temporarely offline, this
 * implementation will not wait until it comes back online to synchronize
 * states.
 * 
 * @author Ludwig Muench
 *
 */
public class StatelessShadow implements Shadow {
	private volatile Client client;
	
	public StatelessShadow(Client client) {
		this.client = client;
	}
	
	@Override
	public void handle(Request req, Response res) {
		client.handle(req, res);
	}
}
