package net.fognode.client.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface Client {
	public Response post(Request req);
	public Response get(Request req);
	public Response put(Request req);
	public Response delete(Request req);
	
}
