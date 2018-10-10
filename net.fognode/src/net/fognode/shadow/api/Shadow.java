package net.fognode.shadow.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface Shadow {
	public void post(Request req, Response res);
	public void get(Request req, Response res);
	public void put(Request req, Response res);
	public void delete(Request req, Response res);
}
