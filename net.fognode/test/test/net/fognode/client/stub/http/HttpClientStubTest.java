package test.net.fognode.client.stub.http;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.client.api.Client;
import net.fognode.client.stub.http.HttpClientStub;
import net.fognode.request.api.Request;
import net.fognode.request.http.HttpRequest;
import net.fognode.response.api.Response;

public class HttpClientStubTest {
	String protocol;
	String method;
	String location;
	Map<String, Object> payload;
	Client cut;

	@Before
	public void setUp() throws Exception {
		protocol = "HTTP";
		method = "POST";
		location = "http://www.example.com/foo/bar";
		payload = new HashMap<>();
		payload.put("someString", "foo");
		payload.put("someNumber", 4.2);
		payload.put("someArray", new Double[] {1.0, 1.2, 1.4});
		cut = new HttpClientStub(); 
	}

	@Test
	public void testPost() {
		Request req = new HttpRequest(protocol, method, location, payload);
		Response res = cut.post(req);
		assertEquals(res.getCode(), 201);
		assertEquals(res.getPayload(), payload);
	}

	@Test
	public void testGet() {
		Request req = new HttpRequest(protocol, method, location);
		Response res = cut.get(req);
		assertEquals(res.getCode(), 200);
		assertFalse(res.getPayload().isEmpty());
	}

	@Test
	public void testPut() {
		Request req = new HttpRequest(protocol, method, location, payload);
		Response res = cut.put(req);
		assertEquals(res.getCode(), 204);
		assertTrue(res.getPayload().isEmpty());
	}

	@Test
	public void testDelete() {
		Request req = new HttpRequest(protocol, method, location);
		Response res = cut.delete(req);
		assertEquals(res.getCode(), 204);
		assertTrue(res.getPayload().isEmpty());
	}

}
