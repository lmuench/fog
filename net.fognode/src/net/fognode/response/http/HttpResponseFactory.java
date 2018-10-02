package net.fognode.response.http;

import java.util.Map;

import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class HttpResponseFactory implements ResponseFactory {
	
	@Override
	public Response createResponse(int code, Map<String, Object> payload) {
		return new HttpResponse(code, payload);
	}
	
	@Override
	public Response createResponse(int code) {
		return new HttpResponse(code);
	}
}
