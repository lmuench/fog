package net.fognode.filters.response.cors;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

	public void filter(
		ContainerRequestContext requestContext,
		ContainerResponseContext responseContext
	) throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");			
		headers.add("Access-Control-Allow-Headers", "Content-Type");
	}
}