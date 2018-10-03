package net.fognode.shadow.test.stateless;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.fognode.client.httpstub.HttpClientStub;
import net.fognode.shadow.api.Shadow;
import net.fognode.shadow.stateless.StatelessShadowFactory;

public class StatelessShadowFactoryTest {
	StatelessShadowFactory cut;

	@Before
	public void setUp() throws Exception {
		cut = new StatelessShadowFactory();
		cut.added(new HttpClientStub());
	}

	/**
	 * Positive test: creating shadow for supported protocol "HTTP"
	 */
	@Test
	public void test1() {
		Shadow shadow = cut.createShadow("HTTP");
		assertNotNull(shadow);
	}
	
	/**
	 * Negative test: trying to create shadow for unsupported protocol "ABCDEF"
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test2() {
		cut.createShadow("ABCDEF");
	}
}
