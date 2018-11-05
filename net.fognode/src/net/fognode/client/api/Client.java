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
package net.fognode.client.api;

import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

/**
 * Protocol-independent interface for protocol-specific clients (e.g. HTTP).
 * Clients execute requests and return responses.
 * Each client supports one protocol.
 * For the gateway to support further protocols, additional clients can be implemented. 
 * 
 * @author Ludwig Muench
 */
public interface Client {
	/**
	 * Translates and forwards requests:
	 * Receives a protocol-independent Request object and reads its method
	 * (e.g. GET), destination URL and payload to create and execute a request
	 * in its supported protocol.
	 * Fills the received Response object with the received payload and/or the
	 * appropriate status code.
	 * 
	 * @param req protocol-independent request (@see net.fognode.request.api.Request)
	 * @param res protocol-independent response (@see net.fognode.response.api.Response)
	 */
	public void handle(Request req, Response res);
	/**
	 * Returns the supported protocol (e.g. "HTTP") as a string.
	 * The returned string has no specified case (e.g. uppercase), therefore ignore case
	 * when comparing it (e.g. <code>getPrococol().equalsIgnoreCase("http")</code>)
	 * 
	 * @return the supported protocol
	 */
	public String getProtocol();
}
