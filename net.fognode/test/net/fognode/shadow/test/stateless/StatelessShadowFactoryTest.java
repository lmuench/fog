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
