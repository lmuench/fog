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
package net.fognode.rd.api;

import java.util.List;
import java.util.Map;

/**
 * Provider for a CoRE Resource Directory as specified by the IETF
 * (see <a href="https://datatracker.ietf.org/doc/draft-ietf-core-resource-directory/">
 * draft-ietf-core-resource-directory</a>).
 * @author Ludwig Muench
 */
public interface RD {
	/**
	 * Method for getting all "endpoints" registered in a CoRE Resource Directory.
	 * Each "endpoint" can have multiple "resources".
	 * @return list of "endpoints", as specified in the CoRE Resource Directory drafts
	 */
	public List<Map<String, Object>> getEndpoints();
	/**
	 * Method for getting all "resources" registered in a CoRE Resource Directory.
	 * @return list of "resources"
	 */
	public List<Map<String, String>> getResources();
}
