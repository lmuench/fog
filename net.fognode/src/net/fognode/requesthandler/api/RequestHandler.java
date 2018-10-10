package net.fognode.requesthandler.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface RequestHandler {
	public void handleRequest(Request req, Response res);
}
