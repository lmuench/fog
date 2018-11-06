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
package net.fognode.middlewareutils.api;

import net.fognode.response.api.Response;

/**
 * MiddlewareUtils provide helper-methods for writing Middleware.
 * 
 * @author Ludwig Muench
 */
public interface MiddlewareUtils {
	/**
	 * Adds a key-value pair to the response's payload. The method works
	 * for both Map<String, Object> (e.g. JSON-object) and List<Object>
	 * (e.g. JSON-array) payloads.
	 * @param key the to-be-added key-value pair's key
	 * @param value the to-be-added key-value pair's value
	 * @return false if the response's payload is neither an instance of List
	 * nor an instance Map, which should only occur if the response doesn't
	 * doesn't have any payload (e.g. an HTTP response with an empty message
	 * body).
	 */
	public boolean addToPayload(
		Response res,
		String key,
		String value
	);
}
