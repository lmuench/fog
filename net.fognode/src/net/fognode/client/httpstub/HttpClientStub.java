package net.fognode.client.httpstub;

import java.util.HashMap;
import java.util.Map;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class HttpClientStub implements Client {
	ResponseFactory responseFactory;

	@Override
	public Response post(Request req) {
		return responseFactory.createResponse(201, req.getPayload());
	}

	@Override
	public Response get(Request req) {
		return responseFactory.createResponse(200, createRandomPayload());
	}

	@Override
	public Response put(Request req) {
		return responseFactory.createResponse(204);
	}

	@Override
	public Response delete(Request req) {
		return responseFactory.createResponse(204);
	}
	
	@Override
	public String getProtocol() {
		return "HTTP";
	}
	
	private Map<String, Object> createRandomPayload() {
		Map<String, Object> payload = new HashMap<>();
		payload.put("someString", "foo");
		payload.put("someNumber", 4.2);
		payload.put("someArray", new Double[] {1.0, 1.2, 1.4});
		return payload;
	}
	
	/**
	 * Manual dependency injection for OSGi-independent unit testing
	 * @param responseFactory
	 */
	public void injectResponseFactory(ResponseFactory responseFactory) {
		this.responseFactory = responseFactory;
	}
}
