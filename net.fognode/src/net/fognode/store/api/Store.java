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
package net.fognode.store.api;

import java.util.Map;

/**
 * Key-value store for persisting data, accessible by any component,
 * specifically: Mapping (@see net.fognode.mapping.api.Mapping),
 * Middleware (@see net.fognode.middleware.api.Middleware),
 * Shadow (@see net.fognode.shadow.api.Shadow)
 * @author Ludwig Muench
 */
public interface Store {
	/**
	 * 
	 * @param node
	 * @param key
	 * @param value
	 */
	public void put(String node, String key, String value);
	public void putMap(String node, Map<String, String> value);
	public String get(String node, String key);
	public Map<String, String> getMap(String node);
	public void delete(String node, String key);
	public void delete(String node);
}
