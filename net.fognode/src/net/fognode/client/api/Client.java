package net.fognode.client.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface Client {
	public void handle(Request req, Response res);
	public String getProtocol();
}
