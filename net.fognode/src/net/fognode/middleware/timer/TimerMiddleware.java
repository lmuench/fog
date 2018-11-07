package net.fognode.middleware.timer;

import java.time.Instant;

import net.fognode.middleware.api.Middleware;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class TimerMiddleware implements Middleware {

	@Override
	public boolean processRequest(Request req, Response res) {
		System.out.println(
			"TimerMiddleware# " +
			req.getLocation() +
			" requested @ " +
			Instant.now().toString()
		);
		return true;
	}

	@Override
	public boolean processResponse(Request req, Response res) {
		System.out.println(
			"TimerMiddleware# " +
			req.getLocation() +
			" responded @ " +
			Instant.now().toString()
		);
		return true;
	}
}
