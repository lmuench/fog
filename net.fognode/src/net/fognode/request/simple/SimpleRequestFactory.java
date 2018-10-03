package net.fognode.request.simple;

import java.util.Map;

import net.fognode.request.api.Request;
import net.fognode.request.api.RequestFactory;

public class SimpleRequestFactory implements RequestFactory {

	@Override
	public Request createRequest(
		String protocol,
		String method,
		String location,
		Map<String, Object> payload
	) {
		return new SimpleRequest(protocol, method, location, payload);
	}

	@Override
	public Request createRequest(
		String protocol,
		String method,
		String location
	) {
		return new SimpleRequest(protocol, method, location);
	}
}
