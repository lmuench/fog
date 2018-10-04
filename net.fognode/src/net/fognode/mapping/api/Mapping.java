package net.fognode.mapping.api;

import java.util.Map;

public interface Mapping {
	public void setMapping(Map<String, String> mapping);
	public String getLocation(String location);
}
