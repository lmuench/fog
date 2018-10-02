package net.fognode.request.api;

import java.util.Map;

public interface Request {
	public String getProtocol();
	public String getMethod();
	public String getLocation();
	public Map<String, Object> getPayload();
}
