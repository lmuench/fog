package net.fognode.store.preferences;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.fognode.store.api.Store;

/**
 * Key value store for string values only 
 * @author Ludwig Muench
 *
 */
public class PreferencesStore implements Store {

	@Override
	public void put(String node, String key, String value) {
		Preferences preferences = Preferences.userRoot().node(node);
		try {
			preferences.clear();
			preferences.put(key, (String) value);
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void putMap(String node, Map<String, String> value) {
		Preferences preferences = Preferences.userRoot().node(node);
		try {
			preferences.clear();
			for (Map.Entry<String, String> entry : value.entrySet()) {
				preferences.put(entry.getKey(), entry.getValue());
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String get(String node, String key) {
		Preferences preferences = Preferences.userRoot().node(node);
		return preferences.get(key, "");
	}
	
	@Override
	public Map<String, String> getMap(String node) {
		Preferences preferences = Preferences.userRoot().node(node);
		Map<String, String> map = new HashMap<>();
		try {
			for (String key : preferences.keys()) {
				map.put(key, preferences.get(key, ""));
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public void delete(String node) {}

	@Override
	public void delete(String node, String key) {}
}
