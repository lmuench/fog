package net.fognode.mappingbuilder.rd;

import java.util.List;
import java.util.Map;

import net.fognode.mapping.api.Mapping;
import net.fognode.mappingbuilder.api.MappingBuilder;
import net.fognode.rd.api.RD;

public class RDMappingBuilder implements MappingBuilder {
	private volatile RD rd;
	private volatile Mapping mapping;

	@Override
	public List<Map<String, Object>> getEndpoints() {
		return rd.getEndpoints();
	}

	@Override
	public void setMapping(Map<String, String> mapping) {
		this.mapping.setMapping(mapping);
	}
}
