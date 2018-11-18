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

/**
 * Mock RD (@see net.fognode.rd.api.RD) implementation.
 * It returns a List of endpoints, as they're defined in
 * /net/fognode/rd/mock/endpoints.yaml inside the bundle's jar file.
 * 
 * Example endpoints.yaml:
 * <code>
 * ---
 * ep: endpoint1                     # endpoint name
 * d: THK/F07/ZW717                  # sector
 * lt: 43200                         # lifetime in seconds
 * base: 'http://localhost:5000'     # base URI "scheme://authority"
 * gp: null                          # group name                           
 * resources:
 *   - protocol: HTTP                # protocol
 *     if: sensor                    # interface description
 *     rt: temperature               # resource type
 *     path: /temp                   # resource path
 *   - protocol: HTTP                # protocol
 *     if: sensor                    # interface description
 *     rt: humidity                  # resource type
 *     path: /humid                  # resource path
 *   - protocol: HTTP                # protocol
 *     if: actuator                  # interface description
 *     rt: light                     # resource type
 *     path: /light                  # resource path
 * ---
 * ep: endpoint2                     # endpoint name
 * d: THK/F07/ZW717                  # sector
 * lt: 43200                         # lifetime in seconds
 * base: 'http://localhost:5001'     # base URI "scheme://authority"
 * gp: null                          # group name                           
 * resources:
 *   - protocol: HTTP                # protocol
 *     if: actuator                  # interface description
 *     rt: light                     # resource type
 *     path: /light                  # resource path
 * </code>
 * 
 * @author Ludwig Muench
 */
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getResources() {
		List<Map<String, Object>> endpoints = getEndpoints();
		List<Map<String, String>> resources = new ArrayList<>();
		
		try {
			endpoints.forEach(endpoint -> {
				((List<Map<String, String>>) endpoint.get("resources")).forEach(resource -> {
					endpoint.forEach((key, value) -> {
						if (value instanceof String) {
							resource.put(key, (String) value);
						}
					});
					resources.add(resource);
				});
			});
		} catch (Exception e) {
			System.out.println(
				"MockRD#getResources() Exception: " + 
				"Could not extract all resources. " +
				"Possible cause: syntax error in endpoints.yaml file."
			);
		}
		return resources;
	}
}
