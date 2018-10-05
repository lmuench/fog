package net.fognode.mappingbuilder.api;

import java.util.List;
import java.util.Map;

public interface MappingBuilder {
	public List<Map<String, Object>> getEndpoints();
	public Map<String, String> getMapping();
	public void setMapping(Map<String, String> mapping);
	public void deleteMapping();
}
