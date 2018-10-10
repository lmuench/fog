package net.fognode.client.httpstub;

import java.util.HashMap;
import java.util.Map;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class HttpClientStub implements Client {

	@Override
	public void post(Request req, Response res) {
		res.setStatus(201);
		res.setPayload(req.getPayload());
	}

	@Override
	public void get(Request req, Response res) {
		res.setStatus(200);
		res.setPayload(createRandomPayload());
	}

	@Override
	public void put(Request req, Response res) {
		res.setStatus(204);
	}

	@Override
	public void delete(Request req, Response res) {
		res.setStatus(204);
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
}
