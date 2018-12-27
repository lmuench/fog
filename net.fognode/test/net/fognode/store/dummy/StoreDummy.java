package net.fognode.store.dummy;

import java.util.HashMap;
import java.util.Map;

import net.fognode.store.api.Store;

public class StoreDummy implements Store {

	@Override
	public void put(String node, String key, String value) {}

	@Override
	public void put(String node, Map<String, String> values) {}

	@Override
	public String get(String node, String key) {
		return "";
	}

	@Override
	public Map<String, String> get(String node) {
		return new HashMap<String, String>();
	}

	@Override
	public void delete(String node, String key) {}

	@Override
	public void delete(String node) {}
}
