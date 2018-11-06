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
package net.fognode.middleware.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

/**
 * Middleware can manipulate the Request and a Response object of any request
 * handled by the RequestHandler and create side-effects as well.
 * The default RequestHandler implementation
 * (@see net.fognode.requesthandler.simple.SimpleRequestHandler) passes the
 * Request and Response objects to all Middleware implementations in ACTIVE
 * state, in the same order as the implementations were started.
 *  
 * @author Ludwig Muench
 */
public interface Middleware {
	/**
	 * This method gets called by the RequestHandler before forwarding the
	 * request to the actual resource (e.g. a sensor).
	 * @param req the request
	 * @param res the response (which, unless manipulated by middleware, should
	 * still be empty at the point of time processRequest() is called)
	 * @return a boolean which halts the requests and triggers an immediate
	 * response if it equals false
	 */
	public boolean processRequest(Request req, Response res);
	/**
	 * This method gets called by the RequestHandler before after forwarding the
	 * request to the actual resource (e.g. a sensor).
	 * @param req the request
	 * @param res the response (which should contain a status and possibly a
	 * payload at the point of point of time processResponse() is called)
	 * @return a boolean which triggers an immediate response, skipping
	 * remaining middleware if it equals false
	 */
	public boolean processResponse(Request req, Response res);
}
