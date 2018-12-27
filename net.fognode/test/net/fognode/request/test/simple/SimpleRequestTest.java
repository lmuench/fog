package net.fognode.request.test.simple;

import net.fognode.request.simple.SimpleRequest;
import net.fognode.request.test.api.RequestTest;

public class SimpleRequestTest extends RequestTest {

	@Override
	protected void setCUT(String method, String ingoingPath) {
		super.cut = new SimpleRequest(method, ingoingPath);
	}
}
