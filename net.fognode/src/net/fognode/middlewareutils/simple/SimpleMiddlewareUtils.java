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
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> payload = (Map<String, Object>) res.getPayload();
			payload.put(key, value);
		} catch (Exception e1) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> payload = (List<Object>) res.getPayload();
				Map<String, String> map = new HashMap<String, String>();
				map.put(key, value);
				payload.add(map);
			} catch (Exception e2) {
				System.out.println(
					"SimpleMiddlewareUtils: " +
					"could not cast payload to either Map or List"
				);
				return false;
			} 
		}
		return true;
	}

}
