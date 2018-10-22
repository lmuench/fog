package net.fognode.shadow.test.stateless;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.client.httpstub.HttpClientStub;
import net.fognode.request.api.Request;
import net.fognode.request.api.RequestFactory;
import net.fognode.request.simple.SimpleRequestFactory;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;
import net.fognode.response.simple.SimpleResponseFactory;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.stateless.StatelessShadow;

public class StatelessShadowTest {
	String protocol;
	String method;
	String location;
	Map<String, Object> payload;
	RequestFactory requestFactory;
	ResponseFactory responseFactory;
	Shadow cut;

	@Before
	public void setUp() throws Exception {
		protocol = "HTTP";
		location = "http://www.example.com/foo/bar";
		payload = new HashMap<>();
		payload.put("someString", "foo");
		payload.put("someNumber", 4.2);
		payload.put("someArray", new Double[] {1.0, 1.2, 1.4});
		requestFactory = new SimpleRequestFactory();
		responseFactory = new SimpleResponseFactory();
		HttpClientStub client = new HttpClientStub();
		cut = new StatelessShadow(client);
	}

	@Test
	public void testPost() {
		Request req = requestFactory.createRequest(protocol, "POST", location, payload);
		Response res = responseFactory.createResponse();
		cut.handle(req, res);
		assertEquals(res.getStatus(), 201);
		assertEquals(res.getPayload(), payload);
	}

	@Test
	public void testGet() {
		Request req = requestFactory.createRequest(protocol, "GET", location);
		Response res = responseFactory.createResponse();
		cut.handle(req, res);
		assertEquals(res.getStatus(), 200);
	}

	@Test
	public void testPut() {
		Request req = requestFactory.createRequest(protocol, "PUT", location, payload);
		Response res = responseFactory.createResponse();
		cut.handle(req, res);
		assertEquals(res.getStatus(), 204);
	}

	@Test
	public void testDelete() {
		Request req = requestFactory.createRequest(protocol, "DELETE", location);
		Response res = responseFactory.createResponse();
		cut.handle(req, res);
		assertEquals(res.getStatus(), 204);
	}
}
