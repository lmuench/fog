/*******************************************************************************
 * Copyright (C) 2018 Ludwig Muench
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package net.fognode.rd.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.fognode.rd.api.RD;

/**
 * RD (@see net.fognode.rd.api.RD) REST API returning JSON.
 * Offers an "/endpoint" resource, representing endpoints registered in an RD.
 * Accepts GET requests to "/endpoints", parses endpoints between
 * List<Map<String, Object>> and JSON arrays and forwards requests to an active
 * RD OSGi service.
 * 
 * @author Ludwig Muench
 */
@Path("/rd")
public class RDRest {
	private volatile RD rd;
	
	@PUT @Path("/url")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean setRdUrl(Map<String, String> map) {
		return rd.setRdUrl(map.get("url"));
	}
	
	@GET @Path("/url")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getRdUrl() {
		Map<String, String> map = new HashMap<>();
		map.put("url", rd.getRdUrl());
		return map;
	}

	@GET @Path("/endpoints")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String, Object>> getEndpoints() {
		return rd.getEndpoints();
	}
	
	@GET @Path("/resources")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String, String>> getResources() {
		return rd.getResources();
	}
}
