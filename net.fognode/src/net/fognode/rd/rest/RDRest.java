package net.fognode.rd.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.fognode.rd.api.RD;

@Path("/rd")
public class RDRest {
	private volatile RD rd;

	@GET @Path("/endpoints")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String, Object>> getEndpoints() {
		return rd.getEndpoints();
	}
}
