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

import net.fognode.mappingrepository.api.MappingRepository;
import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.response.api.Response;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadowrepository.api.ShadowRepository;

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
 * (@see net.fognode.mappingrepository.api.MappingRepository) service and stop
 * processing the request if no matching outgoing URL is found. 
 * 2. Either get the protocol from resource attributes or extract the protocol
 * from the URL scheme. Stop processing the request if no protocol attribute
 * exists and the URL string does not contain a scheme either.
 * 3. Apply all active middleware (@see net.fognode.middleware.api.Middleware)
 * in the order the middleware services were started and stop processing the
 * request if and as soon as any middleware service returns <code>false</code>.
 * 4. Try to get a device shadow (@see net.fognode.shadow.api.Shadow) suitable
 * for the current request's protocol (e.g. HTTP). Then either pass on the
 * request to the device shadow or throw an exception if no device shadow
 * implementation for said protocol is available.
 * 5. As soon as the device shadow has handled the request, apply all active
 * middleware once again, processing the response this time.
 * 
 * @author Ludwig Muench
 */
public class SimpleRequestHandler implements RequestHandler {
	private volatile MappingRepository mappingRepository;
	private volatile List<Middleware> activeMiddleware = new ArrayList<Middleware>();
	private volatile ShadowRepository shadowRepository;	
	
	public void added(Middleware middleware) {
		activeMiddleware.add(middleware);
	}
	
	public void removed(Middleware middleware) {
		activeMiddleware.remove(middleware);
	}

	@Override
	public void handleRequest(Request req, Response res) {
		addOutgoingUrlToRequest(req);
		if(null == req.getOutgoingURL()) {
			res.setStatus(Status.NOT_FOUND.getStatusCode());
			return;
		}
		
		boolean protocolFound = addProtocolToRequest(req);
		if (!protocolFound) {
			res.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return;
		}
		
		boolean rejected = !processRequest(req, res);
		if (rejected) return;
		
		try {
			Shadow shadow;
			shadow = shadowRepository.getShadow(req.getOutgoingURL());
			shadow.handle(req, res);
			
			processResponse(req, res);
			
		} catch (ClassNotFoundException e) {
			res.setStatus(Status.NOT_IMPLEMENTED.getStatusCode());
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
		req.setOutgoingURL(
			mappingRepository.getOutgoingURL(req.getIngoingPath())
		); 
	}
	
	private boolean addProtocolToRequest(Request req) {
		return getProtocolFromAttributes(req) || extractProtocolFromUrl(req);
	}
	
	private boolean getProtocolFromAttributes(Request req) {
		String protocol = mappingRepository.getAttribute(
			req.getIngoingPath(), "protocol"
		);
		if (null != protocol && protocol.length() > 0) {
			req.setProtocol(protocol);
			return true;
		}
		return false;
	}
	
	private boolean extractProtocolFromUrl(Request req) {
//		String protocol = req.getOutgoingURL().split(":")[0];
//		if (protocol.length() > 0) {
//			req.setProtocol(protocol);
//			return true;
//		}

		String[] splitString = req.getOutgoingURL().split(":");
		if (splitString.length > 1) {
			req.setProtocol(splitString[0]);
			return true;
		}

		System.out.println(
			"SimpleRequestHandler# Can't forward request: " +
			"outgoing URL doesn't contain scheme!"
		);
		return false;
	}
}
