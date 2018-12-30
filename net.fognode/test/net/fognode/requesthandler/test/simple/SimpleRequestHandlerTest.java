package net.fognode.requesthandler.test.simple;

import net.fognode.requesthandler.simple.SimpleRequestHandler;
import net.fognode.requesthandler.test.api.RequestHandlerTest;

public class SimpleRequestHandlerTest extends RequestHandlerTest {

	@Override
	protected void instantiateCUT() {
		super.cut = new SimpleRequestHandler();
	}
}
