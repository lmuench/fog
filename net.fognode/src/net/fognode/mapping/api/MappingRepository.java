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
package net.fognode.mapping.api;

import java.util.Map;

/**
 * Holds the resource-to-resource mapping from the user-defined API (e.g. "/temperatures/1")
 * to the resources (e.g. "temp1") on the physical endpoints (e.g. "http://localhost:5000").
 * A mapping entry with the exemplary values from above would be
 * <code>"/temperatures/1":"http://localhost:5000/temp1"<code/>
 *
 * @author Ludwig Muench
 */
public interface MappingRepository {
	/**
	 * Getter for the mapping
	 * @return the mapping
	 */
	public Map<String, String> getMapping();
	/**
	 * Setter for the mapping
	 * @param mapping the new mapping which will replace the old mapping
	 */
	public void setMapping(Map<String, String> mapping);
	/**
	 * Deletes the current mapping
	 */
	public void deleteMapping();
	/**
	 * @param location the resource (path) of the user-defined API (e.g. "/temperatures/1") 
	 * @return the url of the actual resource (e.g. "http://localhost:5000/temp1")
	 */
	public String getResourceLocation(String location);
}
