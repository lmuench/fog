package net.fognode.store.preferencesservice;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;

import net.fognode.store.api.Store;

/**
 * Key value store for string values only 
 * @author Ludwig Muench
 *
 */
public class PreferencesServiceStore implements Store {
	private volatile PreferencesService preferencesService;

	@Override
	public void put(String node, String key, String value) {
		Preferences preferences = preferencesService.getSystemPreferences().node(node);
		
		preferences.put(key, (String) value);
	}
	
	@Override
	public void putMap(String node, Map<String, String> value) {
		Preferences preferences = preferencesService.getSystemPreferences().node(node);
		
		for (Map.Entry<String, String> entry : value.entrySet()) {
			preferences.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public String get(String node, String key) {
		Preferences preferences = preferencesService.getSystemPreferences().node(node);
		
		return preferences.get(key, "");
	}
	
	@Override
	public Map<String, String> getMap(String node) {
		Preferences preferences = preferencesService.getSystemPreferences().node(node);
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
	public void delete(String node, String key) {}

	@Override
	public void delete(String node) {}
}
