package net.fognode.shadow.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface Shadow {
	public Response post(Request req);
	public Response get(Request req);
	public Response put(Request req);
	public Response delete(Request req);
}
