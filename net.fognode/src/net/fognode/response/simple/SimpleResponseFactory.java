package net.fognode.response.simple;

import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class SimpleResponseFactory implements ResponseFactory {
	
	@Override
	public Response createResponse() {
		return new SimpleResponse();
	}
}
