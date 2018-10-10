package net.fognode.requesthandler.simple;

import java.util.ArrayList;
import java.util.List;

import net.fognode.mapping.api.Mapping;
import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.response.api.Response;
import net.fognode.shadow.api.Shadow;

public class SimpleRequestHandler implements RequestHandler {
	private volatile Mapping mapping;
	private volatile List<Middleware> activeMiddleware = new ArrayList<Middleware>();
	private volatile Shadow shadow;	
	
	public void added(Middleware middleware) {
		activeMiddleware.add(middleware);
	}
	
	public void removed(Middleware middleware) {
		activeMiddleware.remove(middleware);
	}

	@Override
	public void handleRequest(Request req, Response res) {
		addResourceLocationToRequest(req);
		boolean rejected = processRequest(req, res);
		if (rejected) return;
		shadow.get(req, res);
		processResponse(req, res);
	}
	
	private boolean processRequest(Request req, Response res) {
		for (Middleware middleware : activeMiddleware) {
			if(!middleware.processRequest(req, res)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean processResponse(Request req, Response res) {
		for (Middleware middleware : activeMiddleware) {
			if(!middleware.processResponse(req, res)) {
				return false;
			};
		}
		return true;
	}
	
	private void addResourceLocationToRequest(Request req) {
		req.setResourceLocation(
			mapping.getResourceLocation(req.getLocation())
		); 
	}
}
