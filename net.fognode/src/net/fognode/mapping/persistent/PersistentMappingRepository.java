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
	private static Map<String, String> mapping = new HashMap<String, String>();
	
	public void init() {
		Map<String, String> mapping = store.getMap("mapping");
		if (mapping == null) return;
		PersistentMappingRepository.mapping = mapping;
//		System.out.println("PersistentMappingRepository# mapping restored: ");
//		System.out.println(PersistentMappingRepository.mapping);
	}

	@Override
	public Map<String, String> getMapping() {
		return mapping;
	}

	@Override
	public void setMapping(Map<String, String> mapping) {
		PersistentMappingRepository.mapping = mapping;
		persistMapping();
	}

	@Override
	public void deleteMapping() {
		mapping = new HashMap<String, String>();
		persistMapping();
	}
	
	@Override
	public String getResourceLocation(String location) {
		return mapping.get(location);
	}
		
	public void persistMapping() { 
		store.putMap("mapping", mapping);
//		System.out.println("PersistentMappingRepository# mapping persisted: ");
//		System.out.println(mapping);
	}
}
