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
package net.fognode.requesthandler.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

/**
 * The RequestHandler is at the heart of the gateway. It has outgoing
 * dependencies to most of the main components of the gateway:
 * MappingRepository, Middleware, ShadowFactory and Shadow.
 * It has an ingoing dependency coming from its associated REST API component
 * (@see net.fognode.requesthandler.rest.RequestHandlerRest).
 * It handles every request received by said REST API component in the
 * following sequence:
 * 1. Get the URL of the actual resource (e.g. a sensor) from the
 * MappingRepository (@see net.fognode.mapping.api.MappingRepository) and add  
 * it to the handled Request (@see net.fognode.request.api.Request) object.
 * 2. Pass the Request object to all active middleware
 * (@see net.fognode.middleware.api.Middleware) services.
 * 3. Pass the Request object to an appropriate device shadow
 * (@see net.fognode.shadow.api.Shadow) 
 * 
 * @author Ludwig Muench
 *
 */
public interface RequestHandler {
	/**
	 * Handles a request. Both arguments, req and res, may be manipulated
	 * inside this method, by processing them with middleware.
	 * @param req The received protocol-independent Request object built from
	 * the ingoing protocol-specifc (e.g. HTTP) request 
	 * @param res The empty Response object, meant to be filled by the
	 * appropriate (e.g. HTTP-) client. 
	 */
	public void handleRequest(Request req, Response res);
}
