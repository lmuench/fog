package net.fognode.mapping.simple;

import java.util.HashMap;
import java.util.Map;

import net.fognode.mapping.api.Mapping;

public class SimpleMapping implements Mapping {
	private Map<String, String> mapping;
	
	public SimpleMapping() {
		mapping = new HashMap<String, String>();
	}
	
	@Override
	public Map<String, String> getMapping() {
		return mapping;
	}

	@Override
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

	@Override
	public void deleteMapping() {
		mapping = new HashMap<String, String>();
	}
	
	@Override
	public String getLocation(String location) {
		return mapping.get(location);
	}
}
