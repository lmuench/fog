package net.fognode.request.simple;

import net.fognode.request.api.Request;

/**
 * HTTP Request implementation.
 * For increased security (but decreased performance), create a new Request
 * implementation using defensive deep copies inside the constructor and
 * payload getter.
 * 
 * @author Ludwig Muench
 *
 */
public class SimpleRequest implements Request {
	private String protocol;
	private String method;
	private String location;
	private String resourceLocation;
	private Object payload;
	
	/**
	 * Constructor.
	 * For increased security, create a new implementation populating the
	 * payload attribute with a defensive deep copy of the payload argument. 
	 * 
	 * @param protocol
	 * @param method
	 * @param payload
	 */
	public SimpleRequest(
		String protocol,
		String method,
		String location,
		Object payload
	) {
		this.protocol = protocol;
		this.method = method;
		this.location = location;
		this.payload = payload;
	}
	
	public SimpleRequest(
		String protocol,
		String method,
		String location
	) {
		this.protocol = protocol;
		this.method = method;
		this.location = location;
		this.payload = null;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getMethod() {
		return method;
	}
	
	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public String getResourceLocation() {
		return resourceLocation;
	}
	
	@Override
	public void setResourceLocation(String resourceLocation) {
		this.resourceLocation = resourceLocation;
	}
	
	/**
	 * Payload getter.
	 * For increased security, create a new implementation returning a
	 * defensive deep copy of the payload.
	 * 
	 * @return
	 */
	@Override
	public Object getPayload() {
		return payload;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
