package net.fognode.client.test.httpstub;

import net.fognode.client.httpstub.HttpClientStub;
import net.fognode.client.test.api.ClientTest;

public class HttpClientStubTest extends ClientTest {

	@Override
	protected void setCUT() {
		super.cut = new HttpClientStub();
	}
}
