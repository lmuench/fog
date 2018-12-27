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
package net.fognode.client.httpstub;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

/**
 * Client (@see net.fognode.client.api.Client) stub implementation for unit
 * testing.
 * It returns random values for any POST and GET HTTP request, as well as
 * success statusses for any POST, GET, PUT and DELETE request.
 * For any other request type (e.g. PATCH) it returns status 405 ("Method Not
 * Allowed").    
 * 
 * @author Ludwig Muench
 */
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
