package net.fognode.client.test.httpmock;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import net.fognode.client.httpmock.HttpMockClient;
import net.fognode.request.api.RequestFactory;
import net.fognode.request.simple.SimpleRequestFactory;
import net.fognode.response.simple.SimpleResponseFactory;

public class HttpMockClientTest {
	RequestFactory requestFactory;
	HttpMockClient cut;

	@Before
	public void setUp() throws Exception {
		requestFactory = new SimpleRequestFactory();
		cut = new HttpMockClient();
		cut.injectResponseFactory(new SimpleResponseFactory());
	}

	@Test
	public void testLoadYaml() throws IOException {
		cut.loadYaml();
	}
}
