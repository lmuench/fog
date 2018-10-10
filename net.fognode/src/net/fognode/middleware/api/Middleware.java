package net.fognode.middleware.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface Middleware {
	public boolean processRequest(Request req, Response res);
	public boolean processResponse(Request req, Response res);
}
