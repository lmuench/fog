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
package net.fognode.request.api;

/**
 * A generic protocol-independent request, used by the gateway internally. 
 * A Request object is created on the ingoing edge of the gateway, for every
 * ingoing request to the user-defined API.
 * The Request object must be created by the RequestFactory
 * (@see net.fognode.request.api.RequestFactory).
 * At the outgoing edge, a specific client handling the request's protocol
 * (e.g. HTTP) creates a request based on the Request object's properties.
 * In between both edges, middleware can read and manipulate those properties.
 * 
 * @author Ludwig Muench
 */
public interface Request {
	/**
	 * Protocol (e.g. "HTTP") getter.
	 * @return the request's protocol
	 */
	public String getProtocol();
	/**
	 * Method (e.g. "POST") getter.
	 * @return the request's method
	 */
	public String getMethod();
	/**
	 * Location getter. The location is only the path of a REST resource of the
	 * user-defined API, which was called (e.g. "/humidity/1") and has
	 * triggered the creation of the current request.
	 * @return the request's location
	 */
	public String getLocation();
	/**
	 * Resource location getter. The resource location is the complete URL
	 * (e.g. "http://127.0.0.1:5000/humid1") of the targeted resource, as it
	 * was registered in the Resource Directory when the mapping was created.
	 * The client (@see net.fognode.client.api.Client) at the outgoing edge of
	 * the gateway will send a protocol-specific request to this URL.
	 * @return the request's location
	 */
	public String getResourceLocation();
	/**
	 * Resource location setter. 
	 * @param resourceLocation the resource location
	 */
	public void setResourceLocation(String resourceLocation);
	/**
	 * Payload (e.g. HTTP request body) getter.
	 * @return the request's payload
	 */
	public Object getPayload();
	/**
	 * Payload setter.
	 * @param payload the request's payload
	 */
	public void setPayload(Object payload);
}
