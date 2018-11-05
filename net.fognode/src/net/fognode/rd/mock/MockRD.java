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
package net.fognode.rd.mock;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import net.fognode.rd.api.RD;

public class MockRD implements RD {
	private String yamlPath;
	private Yaml yaml;
	private List<Map<String, Object>> endpoints;
	
	public MockRD() {
		yamlPath = "/net/fognode/rd/mock/endpoints.yaml";
		yaml = new Yaml();
	}

	@Override
	public List<Map<String, Object>> getEndpoints() {
		try {
			loadYaml();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return endpoints;
	}
	
	@SuppressWarnings("unchecked")
	public void loadYaml() throws IOException {
		endpoints = new ArrayList<Map<String, Object>>();
		
		InputStream input = getClass().getResourceAsStream(yamlPath);

		for (Object data : yaml.loadAll(input)) {
			endpoints.add((Map<String, Object>) data);
		}
	}
}
