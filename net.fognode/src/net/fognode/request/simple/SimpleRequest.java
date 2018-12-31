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
package net.fognode.request.simple;

import java.util.HashMap;
import java.util.Map;

import net.fognode.request.api.Request;

/**
 * Request (@see net.fognode.request.api.Request) implementation.
 * For increased security (but decreased performance), create a new Request
 * implementation which makes defensive deep copies inside constructor, getters
 * and setters.
 * 
 * @author Ludwig Muench
 */
public class SimpleRequest implements Request {
	private String protocol;
	private String method;
	private String ingoingPath;
	private String outgoingURL;
	private Object payload;
	private Map<String, String> attributes;
	
	public SimpleRequest(
		String method,
		String inqoingPath
	) {
		this.method = method;
		this.ingoingPath = inqoingPath;
		this.payload = null;
		this.attributes = new HashMap<String, String>();
	}

	@Override
	public String getProtocol() {
		return protocol;
	}
	
	@Override
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Override
	public String getMethod() {
		return method;
	}
	
	@Override
	public String getIngoingPath() {
		return ingoingPath;
	}

	@Override
	public String getOutgoingURL() {
		return outgoingURL;
	}
	
	@Override
	public void setOutgoingURL(String resourceLocation) {
		this.outgoingURL = resourceLocation;
	}
	
	@Override
	public Object getPayload() {
		return payload;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	@Override
	public String getAttribute(String key) {
		return attributes.get(key);
	}

	@Override
	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}
}
