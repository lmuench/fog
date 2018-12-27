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
package net.fognode.shadowrepository.api;

import net.fognode.shadow.api.Shadow;

/**
 * Maintains a device shadow for each resource of the user-defined REST API. 
 * @author Ludwig Muench
 *
 */
public interface ShadowRepository {
	/**
	 * Returns a Shadow (@see net.fognode.shadow.api.Shadow) for the provided
	 * outgoing URL and injects a Client whose "protocol" service property
	 * matches the requested Shadow's protocol attribute (e.g. "HTTP").
	 * This method shall either return an already-existing Shadow, or create a
	 * new one and keep it for further requests.
	 * @param url The outgoing URL used as a primary key for Shadows.
	 * @return a Shadow representing the resource identified by the outgoing URL
	 * @throws ClassNotFoundException if no matching Client for the requested
	 * Shadow's protocol (e.g. HTTP) is currently installed and active. 
	 */
	public Shadow getShadow(String url) throws ClassNotFoundException;
}
