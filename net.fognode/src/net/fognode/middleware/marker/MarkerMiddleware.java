package net.fognode.middleware.marker;

import java.util.HashMap;
import java.util.Map;

import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class MarkerMiddleware implements Middleware {

	@Override
	public boolean processRequest(Request req, Response res) {
		return true;
	}

	@Override
	public boolean processResponse(Request req, Response res) {
		
		String[] segments = req.getOutgoingURL().split("/");		
		String host = (
			segments.length > 2 ? segments[2] : "indeterminable-host"
		);
		
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put(host, res.getPayload());
		
		res.setPayload(wrapper);
		return true;
	}
}
