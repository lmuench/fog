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
package net.fognode.middlewareutils.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fognode.middlewareutils.api.MiddlewareUtils;
import net.fognode.response.api.Response;

public class SimpleMiddlewareUtils implements MiddlewareUtils {

	@Override
	public boolean addToPayload(
		Response res,
		String key,
		String value
	) {
		if (res.getPayload() instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> payload = (Map<String, Object>) res.getPayload();
			payload.put(key, value);
			return true;
		} else if (res.getPayload() instanceof List) {	
			@SuppressWarnings("unchecked")
			List<Object> payload = (List<Object>) res.getPayload();
			Map<String, String> map = new HashMap<>();
			map.put(key, value);
			payload.add(map);
			return true;
		}
		return false;
	}
}
