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
package net.fognode.mappingrepository.test.api;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.mappingrepository.api.MappingRepository;
import net.fognode.store.dummy.StoreDummy;

public abstract class MappingRepositoryTest {
	protected String key1 = "/rooms/1/temperature";
	protected String key2 = "/rooms/1/lights/left";
	protected String val1 = "http://192.168.1.16:1883/temperature";
	protected String val2 = "http://192.168.1.16:1883/light";
	protected Map<String, String> mappings;
	protected MappingRepository cut;

	@Before
	public void setUp() throws Exception {
		instantiateCUT();
		if (null == cut) {
			System.out.println("You must instantiate the CUT. Example:");
			System.out.println("instantiateCUT() { super.cut = new MyImpl(); }");
		}
		org.junit.Assume.assumeNotNull(cut);
		
		mappings = new HashMap<String, String>();
		mappings.put(key1, val1);
		mappings.put(key2, val2);
		Field cutStore = cut.getClass().getDeclaredField("store");
		cutStore.setAccessible(true);
		cutStore.set(cut, new StoreDummy());
	}
	
	/**
	 * Instantiate super.cut with the implementation under test.
	 */
	protected abstract void instantiateCUT();
	
	@Test
	public void test() {
		cut.setMappings(mappings);
		assertEquals(val1, cut.getOutgoingURL(key1));
		assertEquals(val2, cut.getOutgoingURL(key2));
	}
}
