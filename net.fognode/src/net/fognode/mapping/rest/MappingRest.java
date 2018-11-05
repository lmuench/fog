package net.fognode.mapping.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.fognode.mapping.api.MappingRepository;

@Path("/mapping")
public class MappingRest {
	private volatile MappingRepository mappingRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getMaping() {
		return mappingRepository.getMapping();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void setMapping(Map<String, String> mapping) {
		mappingRepository.setMapping(mapping);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteMapping() {
		mappingRepository.deleteMapping();
	}
}
