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

import net.fognode.mapping.api.MappingRepository;
import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.response.api.Response;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.api.ShadowFactory;

/**
 * RequestHandler (@see net.fognode.requesthandler.api.RequestHandler)
 * implementation.
 * 
 * The SimpleRequestHandler keeps references to a MappingRepository OSGi
 * service, a ShadowFactory service, as well as all active Middleware
 * services (@see net.fognode.requesthandler.simple.Activator). 
 * 
 * Request handling (@see SimpleRequestHandler#handleRequest(Request, Response)})
 * sequence of actions:
 * 1. Get outgoing URL from MappingRepository
 * (@see net.fognode.mapping.api.MappingRepository) service and stop processing
 * the request if no matching outgoing URL is found. 
 * 2. Apply all active middleware (@see net.fognode.middleware.api.Middleware)
 * in the order the middleware services were started, and stop processing the
 * request if and as soon as any middleware service returns <code>false</code>.
 * 3. Try to create a device shadow (@see net.fognode.shadow.api.Shadow) suitable
 * for the current request's protocol (e.g. HTTP). Then either pass on the
 * request to the device shadow, or throw and exception if no device shadow
 * implementation for said protocol is available.
 * 4. As soon as the device shadow has handled the request, apply all active
 * middleware once again, processing the response this time.
 * 
 * @author Ludwig Muench
 */
public class SimpleRequestHandler implements RequestHandler {
	private volatile MappingRepository mapping;
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
		addOutgoingUrlToRequest(req);
		if(null == req.getOutgoingURL()) {
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
	
	private void processResponse(Request req, Response res) {
		for (Middleware middleware : activeMiddleware) {
			if(!middleware.processResponse(req, res)) {
				return;
			};
		}
	}
	
	private void addOutgoingUrlToRequest(Request req) {
		req.setResourceLocation(
			mapping.getOutgoingURL(req.getIngoingPath())
		); 
	}
}
