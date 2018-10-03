package net.fognode.response.simple;

import java.util.HashMap;
import java.util.Map;

import net.fognode.response.api.Response;

public class SimpleResponse implements Response {
	private int code;
	private Map<String, Object> payload;
	
	public SimpleResponse(int code, Map<String, Object> payload) {
		this.code = code;
		this.payload = payload;
	}
	
	public SimpleResponse(int code) {
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
