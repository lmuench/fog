package net.fognode.request.api;

import java.util.Map;

public interface Request {
	public String getProtocol();
	public String getMethod();
	public String getLocation();
	public String getResourceLocation();
	public void setResourceLocation(String resourceLocation);
	public Map<String, Object> getPayload();
	public void setPayload(Map<String, Object> payload);
}
