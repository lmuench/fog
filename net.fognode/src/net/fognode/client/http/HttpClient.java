package net.fognode.client.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;
import okhttp3.OkHttpClient;

public class HttpClient implements Client {
	public static final okhttp3.MediaType JSON = (
		okhttp3.MediaType.parse("application/json; charset=utf-8")
	);
	okhttp3.OkHttpClient client;
	Gson gson;
	
	public HttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(30, TimeUnit.SECONDS); 
		builder.readTimeout(30, TimeUnit.SECONDS); 
		builder.writeTimeout(30, TimeUnit.SECONDS); 
		client = builder.build();
		gson = new Gson();
	}
	
	@Override
	public void handle(Request req, Response res) {
		switch (req.getMethod()) {
			case "POST": post(req, res); break;
			case "GET": get(req, res); break;
			case "PUT": put(req, res); break;
			default: unsupportedMethod(req, res); break;
		}
	}

	private void post(Request req, Response res) {
		String json = gson.toJson(req.getPayload());
		
		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
		okhttp3.Request httpReq = (
			new okhttp3.Request.Builder()
			.url(req.getResourceLocation())
			.post(body)
			.build()
		);
		executeRequest(httpReq, res);
	}

	private void get(Request req, Response res) {
		okhttp3.Request httpReq = (
			new okhttp3.Request.Builder()
			.url(req.getResourceLocation())
			.build()
		);
		executeRequest(httpReq, res);
	}

	private void put(Request req, Response res) {
		String json = gson.toJson(req.getPayload());

		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
		okhttp3.Request httpReq = (
			new okhttp3.Request.Builder()
			.url(req.getResourceLocation())
			.put(body)
			.build()
		);
		executeRequest(httpReq, res);
	}
	
	private void executeRequest(okhttp3.Request httpReq, Response res) {
		try {
			okhttp3.Response httpRes = client.newCall(httpReq).execute();
			try {
				res.setPayload(gson.fromJson(httpRes.body().string(), Object.class));
				res.setStatus(httpRes.code());
			} catch (JsonSyntaxException e) {
				res.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
			}
			
		} catch (IOException e) {
			res.setStatus(Status.BAD_GATEWAY.getStatusCode());
		}
	}
	
	private void unsupportedMethod(Request req, Response res) {
		res.setStatus(Status.METHOD_NOT_ALLOWED.getStatusCode());
	}

	public String getProtocol() {
		return "HTTP";
	}
}
