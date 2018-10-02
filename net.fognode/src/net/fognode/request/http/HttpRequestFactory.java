package net.fognode.request.http;

import java.util.Map;

import net.fognode.request.api.Request;
import net.fognode.request.api.RequestFactory;

public class HttpRequestFactory implements RequestFactory {

	@Override
	public Request createRequest(
		String protocol,
		String method,
		String location,
		Map<String, Object> payload
	) {
		return new HttpRequest(protocol, method, location, payload);
	}

	@Override
	public Request createRequest(
		String protocol,
		String method,
		String location
	) {
		return new HttpRequest(protocol, method, location);
	}
}
