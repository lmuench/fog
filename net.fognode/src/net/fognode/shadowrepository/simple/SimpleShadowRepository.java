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
package net.fognode.shadowrepository.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fognode.client.api.Client;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.api.ShadowFactory;
import net.fognode.shadowrepository.api.ShadowRepository;

public class SimpleShadowRepository implements ShadowRepository {
	private Map<String, Shadow> shadows = new HashMap<>();
	private volatile ShadowFactory shadowFactory;
	private volatile List<Client> clients = new ArrayList<Client>();
	
	public void added(Client client) {
		clients.add(client);
	}
	
	public void removed(Client client) {
		clients.remove(client);
	}

	@Override
	public Shadow getShadow(String url) throws ClassNotFoundException {
		Shadow shadow = shadows.get(url);
		if (null == shadow) {
			shadow = shadowFactory.createShadow(url);
			shadows.put(url, shadow);
//			System.out.println("ShadowRepository# now containing:");
//			System.out.println(shadows.keySet());
		}

		injectClient(shadow);
		return shadow;
	}
	
	/*
	 * Injects a matching Client.
	 * Throws IllegalArgumentException, if no Client matching the Shadow's
	 * protocol attribute is installed and active.
	 */
	private void injectClient(Shadow shadow) throws ClassNotFoundException {
		String protocol = shadow.getProtocol();
		Client client = (
			clients
			.stream()
			.filter(c -> c.getProtocol().equalsIgnoreCase(protocol))
			.findAny()
			.orElseThrow(() -> new ClassNotFoundException(
				"No client available for protocol: " + protocol
			))
		);
		shadow.setClient(client);
	}
}
