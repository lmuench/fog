package net.fognode.response.simple;

import java.util.Map;

import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class SimpleResponseFactory implements ResponseFactory {
	
	@Override
	public Response createResponse(int code, Map<String, Object> payload) {
		return new SimpleResponse(code, payload);
	}
	
	@Override
	public Response createResponse(int code) {
		return new SimpleResponse(code);
	}
}
