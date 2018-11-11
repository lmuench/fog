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

import net.fognode.request.api.Request;

/**
 * Request (@see net.fognode.request.api.Request) implementation.
 * For increased security (but decreased performance), create a new Request
 * implementation using defensive deep copies inside the constructor and
 * payload getter.
 * 
 * @author Ludwig Muench
 */
public class SimpleRequest implements Request {
	private String protocol;
	private String method;
	private String ingoingPath;
	private String outgoingURL;
	private Object payload;
	
	/*
	 * Constructor.
	 * For increased security, create a new implementation populating the
	 * payload attribute with a defensive deep copy of the payload argument. 
	 */
	public SimpleRequest(
		String protocol,  // TODO set protocol later; needs to be gotten from Mapping (just like outgoing URL)!!!
		String method,
		String ingoingPath,
		Object payload
	) {
		this.protocol = protocol;
		this.method = method;
		this.ingoingPath = ingoingPath;
		this.payload = payload;
	}
	
	public SimpleRequest(
		String protocol,
		String method,
		String inqoingPath
	) {
		this.protocol = protocol;
		this.method = method;
		this.ingoingPath = inqoingPath;
		this.payload = null;
	}

	@Override
	public String getProtocol() {
		return protocol;
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
	public void setResourceLocation(String resourceLocation) {
		this.outgoingURL = resourceLocation;
	}
	
	/*
	 * Payload getter.
	 * For increased security, create a new implementation returning a
	 * defensive deep copy of the payload.
	 */
	@Override
	public Object getPayload() {
		return payload;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
