package net.fognode.shadow.test.simple;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.fognode.client.httpstub.HttpClientStub;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.simple.SimpleShadowFactory;

public class SimpleShadowFactoryTest {
	SimpleShadowFactory cut;

	@Before
	public void setUp() throws Exception {
		cut = new SimpleShadowFactory();
		cut.added(new HttpClientStub());
	}

	@Test
	public void test1() {
		Shadow shadow = cut.createShadow("HTTP");
		assertNotNull(shadow);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test2() {
		Shadow shadow = cut.createShadow("ABCDEF");
	}
}
