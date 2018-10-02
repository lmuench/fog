package net.fognode.request.api;

import java.util.Map;

public interface RequestFactory {
	public Request createRequest(
		String protocol,
		String method,
		String location,
		Map<String, Object> payload
	);
	public Request createRequest(
		String protocol,
		String method,
		String location
	);
}
