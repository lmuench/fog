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
package net.fognode.shadow.api;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

/**
 * Each resource of the user-defined API should be proxied by a Shadow.
 * If required, apply the "Device Shadow" IoT pattern
 * (see <a href="http://www.internetofthingspatterns.com/patterns/communication/device-shadow/">
 * Device Shadow IoT Pattern</a>) in a custom implementation of the Shadow
 * interface.
 * The StatelessDeviceShadow (@see net.fognode.shadow.stateless.StatelessDeviceShadow)
 * does not implement said pattern and simply forwards requests to its assigned
 * client.
 * Shadow objects should be created by a ShadowFactory.
 *  
 * @author Ludwig Muench
 *
 */
public interface Shadow {
	public void setClient(Client client);
	public String getProtocol();
	/**
	 * Handles a request. When the "Device Shadow" IoT pattern is not applied,
	 * this method simpy forwards the request to its  assigned client.
	 * @param req protocol-independent request (@see net.fognode.request.api.Request)
	 * @param res protocol-independent response (@see net.fognode.response.api.Response)
	 * @throws ClassNotFoundException if no appropriate Client is available
	 */
	public void handle(Request req, Response res) throws ClassNotFoundException;
}
