package net.fognode.mappingbuilder.api;

import java.util.List;
import java.util.Map;

public interface MappingBuilder {
	public List<Map<String, Object>> getEndpoints();
	public void setMapping(Map<String, String> mapping);
}
