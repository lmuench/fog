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
package net.fognode.mappingbuilder.rd;

import java.util.List;
import java.util.Map;

import net.fognode.mapping.api.Mapping;
import net.fognode.mappingbuilder.api.MappingBuilder;
import net.fognode.rd.api.RD;

public class RDMappingBuilder implements MappingBuilder {
	private volatile RD rd;
	private volatile Mapping mapping;

	@Override
	public List<Map<String, Object>> getEndpoints() {
		return rd.getEndpoints();
	}
	
	@Override
	public Map<String, String> getMapping() {
		return mapping.getMapping();
	}

	@Override
	public void setMapping(Map<String, String> mapping) {
		this.mapping.setMapping(mapping);
	}

	@Override
	public void deleteMapping() {
		mapping.deleteMapping();
	}
}
