package net.fognode.shadow.simple;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.shadow.api.Shadow;

public class SimpleShadow implements Shadow {
	Client client;
	
	public SimpleShadow(Client client) {
		this.client = client;
	}

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
}
