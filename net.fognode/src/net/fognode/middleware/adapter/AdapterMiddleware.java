package net.fognode.middleware.adapter;

import java.util.ArrayList;

import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class AdapterMiddleware implements Middleware {

	@Override
	public boolean processRequest(Request req, Response res) {
		return true;
	}

	@Override
	public boolean processResponse(Request req, Response res) {
		
		if (res.getStatus() == 200 && res.getPayload() == null) {
			res.setPayload(new ArrayList<>());
		}
		
		return true;
	}
}
