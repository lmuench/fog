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
package net.fognode.requesthandler.simple;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import net.fognode.mapping.api.Mapping;
import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.response.api.Response;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.api.ShadowFactory;

public class SimpleRequestHandler implements RequestHandler {
	private volatile Mapping mapping;
	private volatile List<Middleware> activeMiddleware = new ArrayList<Middleware>();
	private volatile ShadowFactory shadowFactory;	
	
	public void added(Middleware middleware) {
		activeMiddleware.add(middleware);
	}
	
	public void removed(Middleware middleware) {
		activeMiddleware.remove(middleware);
	}

	@Override
	public void handleRequest(Request req, Response res) throws UnsupportedOperationException {
		addResourceLocationToRequest(req);
		if(null == req.getResourceLocation()) {
			res.setStatus(Status.NOT_FOUND.getStatusCode());
			return;
		}
		
		boolean rejected = !processRequest(req, res);
		if (rejected) return;
		
		try {
			Shadow shadow;
			shadow = shadowFactory.createShadow(req.getProtocol());
			
			shadow.handle(req, res);
			
			processResponse(req, res);
			
		} catch (UnsupportedOperationException e) {
			throw new UnsupportedOperationException(
				"SimpleRequestHandler# No client available for protocol: " +
				req.getProtocol()
			);
		}
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
