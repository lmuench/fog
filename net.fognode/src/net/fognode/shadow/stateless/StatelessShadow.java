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
	public Response post(Request req) {
		return client.post(req);
	}

	@Override
	public Response get(Request req) {
		return client.get(req);
	}

	@Override
	public Response put(Request req) {
		return client.put(req);
	}

	@Override
	public Response delete(Request req) {
		return client.delete(req);
	}
}
