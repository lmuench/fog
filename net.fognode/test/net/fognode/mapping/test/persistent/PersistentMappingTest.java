package net.fognode.mapping.test.persistent;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.mapping.api.Mapping;
import net.fognode.mapping.persistent.PersistentMapping;

public class PersistentMappingTest {
	String key1 = "http://192.168.1.50:8080/temperature/room1";
	String key2 = "http://192.168.1.50:8080/light/room1/left";
	String val1 = "http://192.168.1.16:1883/temp1";
	String val2 = "http://192.168.1.16:1883/light1";
	Map<String, String> mapping;
	Mapping cut;

	@Before
	public void setUp() throws Exception {
		mapping = new HashMap<String, String>();
		mapping.put(key1, val1);
		mapping.put(key2, val2);
		cut = new PersistentMapping();
	}

	@Test
	public void test() {
		cut.setMapping(mapping);
		assertEquals(val1, cut.getResourceLocation(key1));
		assertEquals(val2, cut.getResourceLocation(key2));
		assertNotEquals(val1, cut.getResourceLocation(key2));
		assertNotEquals(val2, cut.getResourceLocation(key1));
	}
}
