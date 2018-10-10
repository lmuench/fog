package net.fognode.response.simple;

import java.util.Map;

import net.fognode.response.api.Response;

public class SimpleResponse implements Response {
	private int status;
	private Map<String, Object> payload;

	@Override
	public int getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public Map<String, Object> getPayload() {
		return payload;
	}

	@Override
	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
}
