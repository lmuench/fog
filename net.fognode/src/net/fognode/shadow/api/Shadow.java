package net.fognode.shadow.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface Shadow {
	public void handle(Request req, Response res);
}
