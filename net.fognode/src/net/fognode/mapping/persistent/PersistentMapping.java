package net.fognode.mapping.persistent;

import java.util.HashMap;
import java.util.Map;

import net.fognode.mapping.api.Mapping;
import net.fognode.store.api.Store;

public class PersistentMapping implements Mapping {
	private volatile Store store;
	private static Map<String, String> mapping = new HashMap<String, String>();
	
	public void init() {
		Map<String, String> mapping = store.getMap("mapping");
		if (mapping == null) return;
		PersistentMapping.mapping = mapping;
//		System.out.println("PersistentMapping# mapping restored: ");
//		System.out.println(PersistentMapping.mapping);
	}

	@Override
	public Map<String, String> getMapping() {
		return mapping;
	}

	@Override
	public void setMapping(Map<String, String> mapping) {
		PersistentMapping.mapping = mapping;
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
//		System.out.println("PersistentMapping# mapping persisted: ");
//		System.out.println(mapping);
	}
}
