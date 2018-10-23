package net.fognode.client.httpstub;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class HttpClientStub implements Client {
	
	@Override
	public void handle(Request req, Response res) {
		switch (req.getMethod()) {
			case "POST": post(req, res); break;
			case "GET": get(req, res); break;
			case "PUT": put(req, res); break;
			case "DELETE": delete(req, res); break;
			default: unsupportedMethod(req, res); break;
		}
	}

	public void post(Request req, Response res) {
		res.setStatus(201);
		res.setPayload(req.getPayload());
	}

	public void get(Request req, Response res) {
		res.setStatus(200);
		res.setPayload(createRandomPayload());
	}

	public void put(Request req, Response res) {
		res.setStatus(204);
	}

	public void delete(Request req, Response res) {
		res.setStatus(204);
	}
	
	private void unsupportedMethod(Request req, Response res) {
		res.setStatus(Status.METHOD_NOT_ALLOWED.getStatusCode());
	}
	
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
