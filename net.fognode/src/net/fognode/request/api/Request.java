package net.fognode.request.api;

public interface Request {
	public String getProtocol();
	public String getMethod();
	public String getLocation();
	public String getResourceLocation();
	public void setResourceLocation(String resourceLocation);
	public Object getPayload();
	public void setPayload(Object payload);
}
