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

import java.util.List;
import java.util.Map;

/**
 * Holds the resource-to-resource mapping from the user-defined API (e.g. "/temperatures/1")
 * to the resources (e.g. "temp1") on the physical endpoints (e.g. "http://localhost:5000").
 * A mapping entry with the exemplary values from above would be
 * <code>"/temperatures/1":"http://localhost:5000/temp1"<code/>.
 * To store CoRE Link Format attributes, a client can save them as key-value pairs
 * with the key consisting of the path, a colon and the attribute name
 * (e.g. "/temperatures/1:rt" => "sensor").
 *
 * @author Ludwig Muench
 */
public interface MappingRepository {
	/**
	 * Getter for the mapping
	 * @return the mapping
	 */
	public Map<String, String> getMappings();
	/**
	 * Setter for the mapping
	 * @param mappings the new mapping which will replace the old mapping
	 */
	public void setMappings(Map<String, String> mappings);
	/**
	 * Deletes the current mapping
	 */
	public void deleteMappings();
	/**
	 * Returns the API (= the ingoing paths of the user-defined API,
	 * e.g. ["/temperatures/1", ...])
	 * @return the API 
	 */
	public List<String> getApi();
	/**
	 * Returns the URL of the resource the request should be forwarded to (e.g.
	 * "http://localhost:5000/temp1") 
	 * @param ingoingPath the ingoing path of the user-defined API (e.g. "/temperatures/1") 
	 * @return the URL or null if it doesn't exist
	 */
	public String getOutgoingURL(String ingoingPath);
	/**
	 * Returns an attribute. Attributes can be, but are not limited to, CoRE Link
	 * attributes (e.g. "rt").
	 * @param ingoingPath the ingoing path of the user-defined API (e.g. "/temperatures/1")
	 * @param attribute the name of the attribute (e.g. "rt") 
	 * @return the attribute's value or null if it doesn't exist
	 */
	public String getAttribute(String ingoingPath, String attribute);
}
