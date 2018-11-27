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
 * particularly: Mapping (@see net.fognode.mapping.api.Mapping),
 * Middleware (@see net.fognode.middleware.api.Middleware) and
 * Shadow (@see net.fognode.shadow.api.Shadow).
 * The store's content is organized in nodes, with the nodes at the top level
 * identifying both the application and the different components. For example,
 * should a persistent MappingRepository (@see net.fognode.mapping.api.MappingRepository)
 * implementation store the user-defined mapping below a "fognode/mappings" node.
 * Any component can freely access the data stored under nodes used by other
 * components.
 * @author Ludwig Muench
 */
public interface Store {
	/**
	 * Stores a key-value pair under a given top-level node.
	 * @param node the top-level node (e.g. "mapping")
	 * @param key the key-value pair's key
	 * @param value the key-value pair's value
	 */
	public void put(String node, String key, String value);
	/**
	 * Stores all entries of a Map<String, String> under a given top-level node.
	 * @param node the top-level node (e.g. "mapping")
	 * @param value the Map
	 */
	public void put(String node, Map<String, String> value);
	/**
	 * Returns the value for a given key of a key-value pair under a given node.
	 * @param node the top-level node (e.g. "mapping")
	 * @param key the key-value pair's key
	 * @return the key-value pair's value
	 */
	public String get(String node, String key);
	/**
	 * Returns all key-value pairs under a given node as a Map<String, String>.
	 * @param node the top-level node (e.g. "mapping")
	 * @return the Map containing all key-value pairs under the given node
	 */
	public Map<String, String> get(String node);
	/**
	 * Deletes a key-value pair under a given node.
	 * @param node the top-level node (e.g. "mapping")
	 * @param key the to-be-deleted key-value pair's key 
	 */
	public void delete(String node, String key);
	/**
	 * Deletes all key-value pairs under a given node.
	 * @param node the top-level node (e.g. "mapping") 
	 */
	public void delete(String node);
}
