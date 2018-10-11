package net.fognode.middleware.timestamp;

import java.time.Instant;

import net.fognode.middleware.api.Middleware;
import net.fognode.middlewareutils.api.MiddlewareUtils;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class TimestampMiddleware implements Middleware {
	private volatile MiddlewareUtils utils;

	@Override
	public boolean processRequest(Request req, Response res) {
		return true;
	}

	@Override
	public boolean processResponse(Request req, Response res) {
		String timestamp = Instant.now().toString();
		utils.addToPayload(req, res, "timestamp", timestamp);
		return true;
	}
}
