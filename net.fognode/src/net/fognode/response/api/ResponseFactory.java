package net.fognode.response.api;

import java.util.Map;

public interface ResponseFactory {
	public Response createResponse(int code, Map<String, Object> payload);
	public Response createResponse(int code);
}
