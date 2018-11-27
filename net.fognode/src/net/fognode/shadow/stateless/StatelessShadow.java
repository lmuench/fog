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
package net.fognode.shadow.stateless;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.shadow.api.Shadow;

/**
 * Stateless Shadow (@see net.fognode.shadow.api.Shadow) implementation, simply
 * forwarding requests and returning responses.
 * 
 * If a request times out because a device is temporarily offline, this
 * implementation will not wait until it comes back online to synchronize
 * states.
 * 
 * @author Ludwig Muench
 */
public class StatelessShadow implements Shadow {
	private volatile Client client;
	private String protocol;
	
	public StatelessShadow(String protocol) {
		this.protocol = protocol;
	}
	
	@Override
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Override
	public String getProtocol() {
		return protocol;
	}
	
	@Override
	public void handle(Request req, Response res) {
		if (null != client) {
			client.handle(req, res);
		} else {
			throw new IllegalStateException();
		}
	}
}
