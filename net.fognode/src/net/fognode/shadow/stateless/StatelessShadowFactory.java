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

import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.api.ShadowFactory;

/**
 * ShadowFactory (@see net.fognode.shadow.api.ShadowFactory)
 * implementation, creating StatelessShadow
 * (@see net.fognode.shadow.stateless.StatelessShadow) objects.
 * 
 * It keeps references to all active Clients (@see net.fognode.client.api.Client)
 * (@see net.fognode.requesthandler.simple.Activator).
 * 
 * @author Ludwig Muench
 */
public class StatelessShadowFactory implements ShadowFactory {
	
	/*
	 * Creates a shadow and injects a matching client.
	 * Throws IllegalArgumentException, if no client matching the protocol
	 * argument (e.g. "HTTP") is available.
	 */
	@Override
	public Shadow createShadow(String url) {
		return new StatelessShadow(extractProtocol(url));
	}
	
	private String extractProtocol(String url) {
		return url.split(":")[0];
	}
}
