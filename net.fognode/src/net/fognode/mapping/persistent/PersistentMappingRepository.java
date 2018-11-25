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
package net.fognode.mapping.persistent;

import java.util.HashMap;
import java.util.Map;

import net.fognode.mapping.api.MappingRepository;
import net.fognode.store.api.Store;

/**
 * Persistent MappingRepository (@see net.fognode.mapping.api.MappingRepository)
 * implementation. This implementation uses a currently active Store
 * (@see net.fognode.store.api.Store) service to persist its mapping data
 * immediatly, each time it receives a new mapping.
 * When the bundle gets initialized, it restores the persisted mapping from an
 * active Store. 
 * 
 * @author Ludwig Muench
 *
 */
public class PersistentMappingRepository implements MappingRepository {
	private volatile Store store;
	private static Map<String, String> mappings = new HashMap<String, String>();  // TODO why is this static when services are singletons?
	
	public void init() {
		Map<String, String> mappings = store.getMap("fognode:mappings");
		if (mappings == null) return;
		PersistentMappingRepository.mappings = mappings;
//		System.out.println("PersistentMappingRepository# mapping restored: ");
//		System.out.println(PersistentMappingRepository.mapping);		
	}

	@Override
	public Map<String, String> getMappings() {
		return mappings;
	}

	@Override
	public void setMappings(Map<String, String> mappings) {
		PersistentMappingRepository.mappings = mappings;
		persistMapping();
	}

	@Override
	public void deleteMappings() {
		mappings = new HashMap<String, String>();
		persistMapping();
	}
	
	@Override
	public String getOutgoingURL(String ingoingPath) {
		return mappings.get(ingoingPath);
	}
	
	private void persistMapping() { 
		store.putMap("fognode:mappings", mappings);
//		System.out.println("PersistentMappingRepository# mapping persisted: ");
//		System.out.println(mapping);
	}
	
	@Override
	public String getAttribute(String ingoingPath, String attribute) {
		return mappings.get(ingoingPath + ":" + attribute);
	}
}
