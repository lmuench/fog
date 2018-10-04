package net.fognode.client.httpmock;

import java.io.IOException;
import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import net.fognode.response.api.ResponseFactory;

public class HttpMockClient implements Client {
	ResponseFactory responseFactory;
	String resourcesPath;
	Yaml yaml;
	
	public HttpMockClient() {
		resourcesPath = "/net/fognode/client/httpmock/resources.yaml";
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
	
	public void loadYaml() throws IOException {
		InputStream input = getClass().getResourceAsStream(resourcesPath);

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
