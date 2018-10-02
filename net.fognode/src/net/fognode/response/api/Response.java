package net.fognode.response.api;

import java.util.Map;

public interface Response {
	public int getCode();
	public Map<String, Object> getPayload();
}
