package net.fognode.rd.test.mock;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.rd.api.RD;
import net.fognode.rd.mock.MockRD;

public class MockRDTest {
	List<Map<String, Object>> endpoints;
	RD cut;

	@Before
	public void setUp() throws Exception {
		endpoints = null;
		cut = new MockRD();
	}

	@Test
	public void testGetEndpoints() {
		endpoints = cut.getEndpoints();
		assertNotNull(endpoints);
		assertTrue(endpoints.size() > 0);
	}
}
