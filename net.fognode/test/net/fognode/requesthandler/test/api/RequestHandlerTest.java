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
package net.fognode.requesthandler.test.api;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.fognode.client.httpstub.HttpClientStub;
import net.fognode.mappingrepository.api.MappingRepository;
import net.fognode.mappingrepository.persistent.PersistentMappingRepository;
import net.fognode.requesthandler.api.RequestHandler;
import net.fognode.request.api.Request;
import net.fognode.request.simple.SimpleRequest;
import net.fognode.response.api.Response;
import net.fognode.response.simple.SimpleResponse;
import net.fognode.shadow.stateless.StatelessShadowFactory;
import net.fognode.shadowrepository.simple.SimpleShadowRepository;
import net.fognode.store.dummy.StoreDummy;

/**
 * In a running OSGi container, gateway services have their dependencies to other
 * OSGi services injected by the Felix Dependency Manager.
 * Since unit testing shouldn't depend on an OSGi framework, these references
 * are injected manually through reflection.
 * To verify the correct implementation of interfaces and enable reuse of tests,
 * interfaces are tested with abstract classes which must be extended to
 * implement an instantiateCUT() method. This method is used to instantiate the 
 * class under test with the implementation that is to be tested.  
 * 
 * @author Ludwig Muench
 *
 */
public abstract class RequestHandlerTest {
	protected RequestHandler cut;
	protected MappingRepository mappingRepository;
	protected SimpleShadowRepository shadowRepository;
	protected String ingoingPath1 = "/rooms/1/temperature";
	protected String ingoingPath2 = "/rooms/1/lights/left";
	protected String outgoingUrl1 = "http://192.168.1.16:1883/temperature";
	protected String outgoingUrl2 = "http://192.168.1.16:1883/light";
	protected Map<String, String> mappings;
	protected Request req;
	protected Response res;

	@Before
	public void setUp() throws Exception {
		instantiateCUT();
		if (null == cut) {
			System.out.println("You must instantiate the CUT. Example:");
			System.out.println("instantiateCUT() { super.cut = new MyImpl(); }");
		}
		org.junit.Assume.assumeNotNull(cut);
		
		mappingRepository = new PersistentMappingRepository(); 
		Field storeField = mappingRepository.getClass().getDeclaredField("store");
		storeField.setAccessible(true);
		storeField.set(mappingRepository, new StoreDummy());
		
		Field mappingRepositoryField = null;
		try {
			mappingRepositoryField = cut.getClass().getDeclaredField("mappingRepository");
			mappingRepositoryField.setAccessible(true);
			mappingRepositoryField.set(cut, mappingRepository);
		} catch (NoSuchFieldException e) {
			System.out.println(
				"Your RequestHandler implementation must have a " +
				"'mappingRepository' field for this test to manually inject " +
				" a PersistentMappingRepository instance."
			);
			org.junit.Assume.assumeNotNull(mappingRepositoryField);
		}
		
		shadowRepository = new SimpleShadowRepository();
		shadowRepository.added(new HttpClientStub());
		
		Field shadowFactoryField = shadowRepository.getClass().getDeclaredField("shadowFactory");
		shadowFactoryField.setAccessible(true);
		shadowFactoryField.set(shadowRepository, new StatelessShadowFactory());

		
		Field shadowRepositoryField = null;
		try {
			shadowRepositoryField = cut.getClass().getDeclaredField("shadowRepository");
			shadowRepositoryField.setAccessible(true);
			shadowRepositoryField.set(cut, shadowRepository);
		} catch (NoSuchFieldException e) {
			System.out.println(
				"Your RequestHandler implementation must have a " +
				"'shadowRepository' field for this test to manually inject " +
				" a SimpleShadowRepository instance."
			);
			org.junit.Assume.assumeNotNull(shadowRepositoryField);
		}
		
		mappings = new HashMap<String, String>();
	}
	
	/**
	 * Instantiate super.cut with the implementation under test.
	 */
	protected abstract void instantiateCUT();
	
	@Test
	public void test() {
		mappings.put(ingoingPath1, outgoingUrl1);
		mappings.put(ingoingPath2, outgoingUrl2);
		mappingRepository.setMappings(mappings);
		
		req = new SimpleRequest("POST", ingoingPath1);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl1, req.getOutgoingURL());
		assertNotNull(res.getStatus());
		
		req = new SimpleRequest("GET", ingoingPath1);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl1, req.getOutgoingURL());
		assertNotNull(res.getStatus());
		
		req = new SimpleRequest("PUT", ingoingPath1);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl1, req.getOutgoingURL());
		assertNotNull(res.getStatus());
		
		req = new SimpleRequest("DELETE", ingoingPath1);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl1, req.getOutgoingURL());
		assertNotNull(res.getStatus());
		
		req = new SimpleRequest("POST", ingoingPath2);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl2, req.getOutgoingURL());
		assertNotNull(res.getStatus());
		
		req = new SimpleRequest("GET", ingoingPath2);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl2, req.getOutgoingURL());
		assertNotNull(res.getStatus());
		
		req = new SimpleRequest("PUT", ingoingPath2);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl2, req.getOutgoingURL());
		assertNotNull(res.getStatus());
		
		req = new SimpleRequest("DELETE", ingoingPath2);
		res = new SimpleResponse();
		cut.handleRequest(req, res);
		assertEquals(outgoingUrl2, req.getOutgoingURL());
		assertNotNull(res.getStatus());
	}
}
