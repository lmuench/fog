package net.fognode.response.simple;

import net.fognode.response.api.Response;

public class SimpleResponse implements Response {
	private int status;
	private Object payload;

	@Override
	public int getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
