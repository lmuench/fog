package net.fognode.store.api;

import java.util.Map;

public interface Store {
	public void put(String node, String key, String value);
	public void putMap(String node, Map<String, String> value);
	public String get(String node, String key);
	public Map<String, String> getMap(String node);
	public void delete(String node);
	public void delete(String node, String key);
}
