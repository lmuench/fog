package net.fognode.client.httpmock;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class HttpMockClient implements Client {
	ResponseFactory responseFactory;
	String yamlPath;
	Yaml yaml;
	List<Map<String, Object>> endpoints;
	
	public HttpMockClient() {
		yamlPath = "/net/fognode/client/httpmock/endpoints.yaml";
		yaml = new Yaml();
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
	
	@SuppressWarnings("unchecked")
	public void loadYaml() throws IOException {
		endpoints = new ArrayList<Map<String, Object>>();
		
		InputStream input = getClass().getResourceAsStream(yamlPath);

		for (Object data : yaml.loadAll(input)) {
			endpoints.add((Map<String, Object>) data);
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
