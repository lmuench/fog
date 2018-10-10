package net.fognode.client.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface Client {
	public void post(Request req, Response res);
	public void get(Request req, Response res);
	public void put(Request req, Response res);
	public void delete(Request req, Response res);
	public String getProtocol();
}
