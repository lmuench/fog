package net.fognode.mapping.simple;

import java.util.HashMap;
import java.util.Map;

import net.fognode.mapping.api.Mapping;

public class SimpleMapping implements Mapping {
	Map<String, String> mapping;
	
	public SimpleMapping() {
		mapping = new HashMap<String, String>();
	}

	@Override
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

	@Override
	public String getLocation(String location) {
		return mapping.get(location);
	}
}
