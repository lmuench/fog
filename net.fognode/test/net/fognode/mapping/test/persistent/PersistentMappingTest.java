/*******************************************************************************
 * Copyright (C) 2018 Ludwig Muench
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
