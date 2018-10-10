package net.fognode.response.api;

public interface Response {
	public int getStatus();
	public void setStatus(int status);
	public Object getPayload();
	public void setPayload(Object payload);
}
