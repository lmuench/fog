package net.fognode.request.simple;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, Object> payload;
	
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
		Map<String, Object> payload
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
		this.payload = new HashMap<String, Object>();
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

	/**
	 * Payload getter.
	 * For increased security, create a new implementation returning a
	 * defensive deep copy of the payload.
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> getPayload() {
		return payload;
	}
}
