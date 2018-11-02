package net.fognode.middlewareutils.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fognode.middlewareutils.api.MiddlewareUtils;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class SimpleMiddlewareUtils implements MiddlewareUtils {

	@Override
	public boolean addToPayload(
		Request req,
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
	
//	@Override
//	public boolean addToPayload(
//		Request req,
//		Response res,
//		String key,
//		String value
//	) {
//		if (res.getPayload() instanceof Map) {
//			try {				
//				@SuppressWarnings("unchecked")
//				Map<String, Object> payload = (Map<String, Object>) res.getPayload();
//				payload.put(key, value);
//				return true;
//			} catch (Exception e) {
//				return false;
//			}
//		} else if (res.getPayload() instanceof List) {
//			try {				
//				@SuppressWarnings("unchecked")
//				List<Object> payload = (List<Object>) res.getPayload();
//				Map<String, String> map = new HashMap<>();
//				map.put(key, value);
//				payload.add(map);
//				return true;
//			} catch (Exception e) {
//				return false;
//			}
//		}  
//		return false;
//	}
}
