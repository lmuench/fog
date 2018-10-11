package net.fognode.middlewareutils.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public interface MiddlewareUtils {
	public boolean addToPayload(
		Request req,
		Response res,
		String key,
		String value
	);
}
