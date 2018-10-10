package net.fognode.requesthandler.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
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


@Path("/gateway")
public class RequestHandlerRest {
	private volatile RequestFactory requestFactory;
	private volatile ResponseFactory responseFactory;
	private volatile RequestHandler requestHandler;
	
	@GET
	@Path("{path}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> handleGetRequest(@PathParam("path") String path) {
		return handleRequest("GET", path);
	}

	private Map<String, Object> handleRequest(String method, String path) {
		path = "/" + path;
		System.out.println(method + " " + path);

		Request req = requestFactory.createRequest("HTTP", method, path);
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
