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
package net.fognode.requesthandler.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import net.fognode.request.api.Request;
import net.fognode.request.api.RequestFactory;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

/**
 * RequestHandler (@see net.fognode.requesthandler.api.RequestHandler) REST API
 * accepting and returning JSON.
 * Offers a "/gateway" resource which forwards ingoing requests to an active
 * RequestHandler. 
 * Accepts POST, GET and PUT requests to "/gateway", parses between Maps / List
 * and JSON objects / arrays, creates Request (@see net.fognode.request.api.Request)
 * and Response (@see net.fognode.response.api.Response) objects, and passes them to
 * an active RequestHandler OSGi service.
 * The path segments following "/gateway" become the Request object's location
 * property. Example: a call to http://localhost:8080/services/gateway/temperatures/1
 * causes a Request object to be created with the location property "/temperatures/1".
 * 
 * If the RequestHandler throws an <code>UnsupportetOperationException</code>, which
 * the SimpleRequestHandler (@see net.fognode.requesthandler.api.simple) does when no
 * active HTTP client service is available, the HTTP response status becomes 501
 * ("Not Implemented").
 * 
 * @author Ludwig Muench
 */
@Path("/gateway")
public class RequestHandlerRest {
	private volatile RequestFactory requestFactory;
	private volatile ResponseFactory responseFactory;
	private volatile RequestHandler requestHandler;
	
	@POST
	@Path("{path : .+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> handlePostRequest(Object httpBody, @PathParam("path") String path) {
		return handleRequest("POST", path, httpBody);
	}
	
	@GET
	@Path("{path : .+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> handleGetRequest(@PathParam("path") String path) {
		return handleRequest("GET", path, null);
	}
	
	@PUT
	@Path("{path : .+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> handlePutRequest(Object httpBody, @PathParam("path") String path) {
		return handleRequest("PUT", path, httpBody);
	}

	private Map<String, Object> handleRequest(String method, String path, Object httpBody) {
		path = "/" + path;
//		System.out.println("RequestHandlerRest# " + method + " " + path);
		Request req = requestFactory.createRequest("HTTP", method, path);
		req.setPayload(httpBody);
	
		Response res = responseFactory.createResponse();
		Map<String, Object> json = new HashMap<>();

		try {
			requestHandler.handleRequest(req, res);
			json.put("status", res.getStatus());
			json.put("payload", res.getPayload());
		} catch (UnsupportedOperationException e) {
			json.put("status", Status.NOT_IMPLEMENTED.getStatusCode());
		}

		return json;
	}
}
