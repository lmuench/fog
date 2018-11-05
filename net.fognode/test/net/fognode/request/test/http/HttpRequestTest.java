/*******************************************************************************
 * Copyright (C) 2018 Ludwig Muench
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package net.fognode.request.test.http;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.request.api.Request;
import net.fognode.request.simple.SimpleRequest;

public class HttpRequestTest {
	String protocol;
	String method;
	String location;
	Map<String, Object> payload;
	Request cut;

	@Before
	public void setUp() throws Exception {
		protocol = "HTTP";
		method = "POST";
		location = "http://www.example.com/foo/bar";
		payload = new HashMap<>();
		payload.put("someString", "foo");
		payload.put("someNumber", 4.2);
		payload.put("someArray", new Double[] {1.0, 1.2, 1.4});
		cut = new SimpleRequest(protocol, method, location, payload);
	}

	@Test
	public void testHttpRequest() {
		assertEquals(cut.getProtocol(), protocol);
		assertEquals(cut.getMethod(), method);
		assertEquals(cut.getLocation(), location);
		assertEquals(cut.getPayload(), payload);
	}
	
	/**
	 * Test case demonstrating how this request implementation is not secure.
	 * Since the request's payload attribute isn't populated with a defensive
	 * deep copy, anyone with a reference to the payload can change it at any
	 * point of time. 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testHttpRequestChangingPayload() {
		payload.replace("someNumber", 2.3);
		assertEquals(((Map<String, Object>) cut.getPayload()).get("someNumber"), 2.3);
	}
}
