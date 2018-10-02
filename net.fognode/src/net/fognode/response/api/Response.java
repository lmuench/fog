package net.fognode.response.api;

import java.util.Map;

public interface Response {
	public String getProtocol();
	public String getMethod();
	public Map<String, Object> getPayload();
}
