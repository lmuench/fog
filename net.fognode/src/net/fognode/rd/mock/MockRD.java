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
