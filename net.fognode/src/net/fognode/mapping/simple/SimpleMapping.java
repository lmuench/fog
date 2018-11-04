package net.fognode.mapping.simple;

import java.util.HashMap;
import java.util.Map;

import net.fognode.mapping.api.Mapping;
import net.fognode.store.api.Store;

public class SimpleMapping implements Mapping {
	private volatile Store store;
	private static Map<String, String> mapping = new HashMap<String, String>();
	
	public void init() {
		Map<String, String> mapping = store.getMap("mapping");
		if (mapping == null) return;
		SimpleMapping.mapping = mapping;
		System.out.println("SimpleMapping# mapping restored: ");
		System.out.println(SimpleMapping.mapping);
	}

	@Override
	public Map<String, String> getMapping() {
		return mapping;
	}

	@Override
	public void setMapping(Map<String, String> mapping) {
		SimpleMapping.mapping = mapping;
	}

	@Override
	public void deleteMapping() {
		mapping = new HashMap<String, String>();
	}
	
	@Override
	public String getResourceLocation(String location) {
		return mapping.get(location);
	}
		
	public void persistMapping() { 
		store.putMap("mapping", mapping);
		System.out.println("SimpleMapping# mapping persisted: ");
		System.out.println(SimpleMapping.mapping);
	}
}
