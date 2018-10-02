package net.fognode.request.http;

import java.util.Map;

import net.fognode.request.api.Request;

/**
 * HTTP Request implementation.
 * For increased security, create a new Request implementation using defensive
 * copies inside the constructor and payload getter.
 * 
 * @author Ludwig Muench
 *
 */
public class HttpRequest implements Request {
	private String protocol;
	private String method;
	private Map<String, Object> payload;
	
	/**
	 * Constructor.
	 * For increased security, create a new implementation populating the
	 * payload attribute with a defensive copy of the payload argument. 
	 * 
	 * @param protocol
	 * @param method
	 * @param payload
	 */
	public HttpRequest(
		String protocol,
		String method,
		Map<String, Object> payload
	) {
		this.protocol = protocol;
		this.method = method;
		this.payload = payload;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getMethod() {
		return method;
	}

	/**
	 * Payload getter.
	 * For increased security, create a new implementation returning a
	 * defensive copy of the payload.
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> getPayload() {
		return payload;
	}
	
}
