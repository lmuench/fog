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
package net.fognode.store.preferences;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.fognode.store.api.Store;

/**
 * Key-value Store (@see net.fognode.store.api.Store) implementation based on
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/prefs/Preferences.html">
 * java.util.prefs.Preferences</a>.
 * Note: Using Java preferences, the data can be over-written by other
 * applications.
 *   
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
	public void put(String node, Map<String, String> values) {
		Preferences preferences = Preferences.userRoot().node(node);
		try {
			preferences.clear();
			for (Map.Entry<String, String> entry : values.entrySet()) {
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
	public Map<String, String> get(String node) {
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
