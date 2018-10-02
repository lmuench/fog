package net.fognode.response.http;

import java.util.HashMap;
import java.util.Map;

import net.fognode.response.api.Response;

public class HttpResponse implements Response {
	private int code;
	private Map<String, Object> payload;
	
	public HttpResponse(int code, Map<String, Object> payload) {
		this.code = code;
		this.payload = payload;
	}
	
	public HttpResponse(int code) {
		this.code = code;
		this.payload = new HashMap<>();
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public Map<String, Object> getPayload() {
		return payload;
	}
}
