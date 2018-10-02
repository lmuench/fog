package test.net.fognode.request.http;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.request.api.Request;
import net.fognode.request.http.HttpRequest;

public class HttpRequestTest {
	String protocol;
	String method;
	String location;
	Map<String, Object> payload;

	@Before
	public void setUp() throws Exception {
		protocol = "HTTP";
		method = "POST";
		location = "http://www.example.com/foo/bar";
		payload = new HashMap<>();
		payload.put("someString", "foo");
		payload.put("someNumber", 4.2);
		payload.put("someArray", new Double[] {1.0, 1.2, 1.4});
	}

	@Test
	public void testHttpRequest() {
		Request req = new HttpRequest(protocol, method, location, payload);
		assertEquals(req.getProtocol(), protocol);
		assertEquals(req.getMethod(), method);
		assertEquals(req.getLocation(), location);
		assertEquals(req.getPayload(), payload);
	}
	
	/**
	 * Test case demonstrating how this request implementation is not secure.
	 * Since the request's payload attribute isn't populated with a defensive
	 * deep copy, anyone with a reference to the payload can change it at any
	 * point of time. 
	 */
	@Test
	public void testHttpRequestChangingPayload() {
		Request req = new HttpRequest(protocol, method, location, payload);
		payload.replace("someNumber", 2.3);
		assertEquals(req.getPayload().get("someNumber"), 2.3);
	}
}
