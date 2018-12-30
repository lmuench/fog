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
package net.fognode.shadow.test.api;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.client.httpstub.HttpClientStub;
import net.fognode.request.api.Request;
import net.fognode.request.api.RequestFactory;
import net.fognode.request.simple.SimpleRequest;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;
import net.fognode.response.simple.SimpleResponse;
import net.fognode.shadow.api.Shadow;

/**
 * In a running OSGi container, gateway services have their dependencies to other
 * OSGi services injected by the Felix Dependency Manager.
 * Since unit testing shouldn't depend on an OSGi framework, these references
 * are injected manually through reflection.
 * To verify the correct implementation of interfaces and enable reuse of tests,
 * interfaces are tested with abstract classes which must be extended to
 * implement an instantiateCUT() method. This method is used to instantiate the 
 * class under test with the implementation that is to be tested.  
 * 
 * @author Ludwig Muench
 *
 */
public abstract class ShadowTest {
	protected String protocol;
	protected String method;
	protected String ingoingPath;
	protected String outgoingURL;
	protected Map<String, Object> payload;
	protected RequestFactory requestFactory;
	protected ResponseFactory responseFactory;
	protected Shadow cut;
	protected Request req;
	protected Response res;

	@Before
	public void setUp() throws Exception {
		protocol = "HTTP";
		ingoingPath = "/foo/bar/42";
		outgoingURL = "http://127.0.0.1/foo";
		payload = new HashMap<>();
		payload.put("someString", "foo");
		payload.put("someNumber", 4.2);
		payload.put("someArray", new Double[] {1.0, 1.2, 1.4});
		
		instantiateCUT(protocol);
		if (null == cut) {
			System.out.println("You must instantiate the CUT. Example:");
			System.out.println("instantiateCUT() { super.cut = new MyImpl(); }");
		}
		org.junit.Assume.assumeNotNull(cut);
		
		cut.setClient(new HttpClientStub());
		res = new SimpleResponse();
	}
	
	/**
	 * Instantiate super.cut with the implementation under test.
	 */
	protected abstract void instantiateCUT(String protocol);

	@Test
	public void testPost() throws ClassNotFoundException {
		req = new SimpleRequest("POST", ingoingPath);
		setRequestFields(req);
		cut.handle(req, res);
		assertEquals(res.getStatus(), 201);
	}

	@Test
	public void testGet() throws ClassNotFoundException {
		req = new SimpleRequest("GET", ingoingPath);
		setRequestFields(req);
		cut.handle(req, res);
		assertEquals(res.getStatus(), 200);
	}

	@Test
	public void testPut() throws ClassNotFoundException {
		req = new SimpleRequest("PUT", ingoingPath);
		setRequestFields(req);
		cut.handle(req, res);
		assertEquals(res.getStatus(), 204);
	}

	@Test
	public void testDelete() throws ClassNotFoundException {
		req = new SimpleRequest("DELETE", ingoingPath);
		setRequestFields(req);
		cut.handle(req, res);
		assertEquals(res.getStatus(), 204);
	}
	
	protected void setRequestFields(Request req) {
		req.setOutgoingURL(outgoingURL);
		req.setProtocol(protocol);
		req.setPayload(payload);
	}
}
