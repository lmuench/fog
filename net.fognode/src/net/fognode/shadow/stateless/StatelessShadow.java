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
	public void post(Request req, Response res) {
		client.post(req, res);
	}

	@Override
	public void get(Request req, Response res) {
		client.get(req, res);
	}

	@Override
	public void put(Request req, Response res) {
		client.put(req, res);
	}

	@Override
	public void delete(Request req, Response res) {
		client.delete(req, res);
	}
}
