package net.fognode.requesthandler.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public Object handleRequest (@PathParam("path") String path) {
		String protocol = "";
		String method = "GET";
		String location = "";
		Request req = requestFactory.createRequest(protocol, method, location);
		Response res = responseFactory.createResponse();
		requestHandler.handleRequest(req, res);
		return createJsonResponse();
	}

	private Object createJsonResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}
