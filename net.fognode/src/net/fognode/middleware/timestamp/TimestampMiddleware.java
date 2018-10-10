package net.fognode.middleware.timestamp;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class TimestampMiddleware implements Middleware {

	@Override
	public boolean processRequest(Request req, Response res) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean processResponse(Request req, Response res) {
		String timestamp = Instant.now().toString();

		try {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Map payload = (Map<String, Object>) res.getPayload();
			payload.put("timestamp", timestamp);
		} catch (Exception e1) {
			try {
				List<Object> payload = (List<Object>) res.getPayload();
				Map<String, String> map = new HashMap<String, String>();
				map.put("timestamp", timestamp);
				payload.add(map);
			} catch (Exception e2) {
				System.out.println(
					"Timestamp Middleware: " +
					"could not cast payload to either Map or List"
				);
			} 
		}
		return true;
	}
}
