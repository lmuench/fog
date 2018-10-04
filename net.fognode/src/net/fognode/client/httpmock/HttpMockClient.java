package net.fognode.client.httpmock;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class HttpMockClient implements Client {
	ResponseFactory responseFactory;

	@Override
	public Response post(Request req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response get(Request req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response put(Request req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response delete(Request req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Manual dependency injection for OSGi-independent unit testing
	 * @param responseFactory
	 */
	public void injectResponseFactory(ResponseFactory responseFactory) {
		this.responseFactory = responseFactory;
	}
}
