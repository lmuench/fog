package net.fognode.response.api;

import java.util.Map;

public interface Response {
	public int getStatus();
	public void setStatus(int status);
	public Map<String, Object> getPayload();
	public void setPayload(Map<String, Object> payload);
}
