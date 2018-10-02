package net.fognode.client.stub.http;

import java.util.HashMap;
import java.util.Map;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class HttpClientStub implements Client {
	ResponseFactory factory;

	@Override
	public Response post(Request req) {
		return factory.createResponse(201, req.getPayload());
	}

	@Override
	public Response get(Request req) {
		return factory.createResponse(200, createRandomPayload());
	}

	@Override
	public Response put(Request req) {
		return factory.createResponse(204);
	}

	@Override
	public Response delete(Request req) {
		return factory.createResponse(204);
	}
	
	private Map<String, Object> createRandomPayload() {
		Map<String, Object> payload = new HashMap<>();
		payload.put("someString", "foo");
		payload.put("someNumber", 4.2);
		payload.put("someArray", new Double[] {1.0, 1.2, 1.4});
		return payload;
	}
}
