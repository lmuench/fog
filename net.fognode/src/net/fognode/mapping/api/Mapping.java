package net.fognode.mapping.api;

import java.util.Map;

public interface Mapping {
	public Map<String, String> getMapping();
	public void setMapping(Map<String, String> mapping);
	public void deleteMapping();
	public String getResourceLocation(String location);
}
