package net.fognode.client.httpmock;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.yaml.snakeyaml.Yaml;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class HttpMockClient implements Client {
	ResponseFactory responseFactory;
	URL resourcesUrl; 
	Yaml yaml;
	
//	public HttpMockClient() {
//		yaml = new Yaml();
//		System.out.println("constructed");
//	}
	
	public HttpMockClient() throws IOException {
		yaml = new Yaml();
		loadYaml();
		System.out.println("constructed");
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

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void loadYaml() throws IOException {
		InputStream input = resourcesUrl.openStream();
		for (Object data : yaml.loadAll(input)) {
			System.out.println(data);
		}
	}

	/**
	 * Manual dependency injection for OSGi-independent unit testing
	 * @param responseFactory
	 */
	public void injectResponseFactory(ResponseFactory responseFactory) {
		this.responseFactory = responseFactory;
	}
}
