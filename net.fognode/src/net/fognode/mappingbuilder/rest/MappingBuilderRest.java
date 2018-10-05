package net.fognode.mappingbuilder.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.fognode.mappingbuilder.api.MappingBuilder;

public class MappingBuilderRest {
	private volatile MappingBuilder builder;
	
	@GET @Path("/endpoints")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String, Object>> getEndpoints() {
		return builder.getEndpoints();
	}
	
	@GET @Path("/mapping")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getMaping() {
		return builder.getMapping();
	}
	
	@PUT @Path("/mapping")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setMapping(Map<String, String> mapping) {
		builder.setMapping(mapping);
	}
	
	@DELETE @Path("/mapping")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteMapping() {
		builder.deleteMapping();
	}
}
