package net.fognode.client.http;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import net.fognode.client.api.Client;
import net.fognode.request.api.Request;
import net.fognode.response.api.Response;

public class HttpClient implements Client {
	public static final okhttp3.MediaType JSON = (
		okhttp3.MediaType.parse("application/json; charset=utf-8")
	);
	okhttp3.OkHttpClient client;
	Gson gson;
	
	public HttpClient() {
		client = new okhttp3.OkHttpClient();
		gson = new Gson();
	}
	
//	String post(String url, String json) throws IOException {
//		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
//		okhttp3.Request request = (
//			new okhttp3.Request.Builder()
//			.url(url)
//			.post(body)
//			.build()
//		);
//		okhttp3.Response response = client.newCall(request).execute();
//		return response.body().string();
//	}

	@Override
	public void post(Request req, Response res) {
//		String json = req.getPayload()
//		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, req.getPayload());
//		okhttp3.Request request = (
//			new okhttp3.Request.Builder()
//			.url(url)
//			.post(body)
//			.build()
//		);
//		okhttp3.Response response = client.newCall(request).execute();
//		return response.body().string();
	}

	@Override
	public void get(Request req, Response res) {
		okhttp3.Request httpReq = (
			new okhttp3.Request.Builder()
			.url(req.getResourceLocation())
			.build()
		);
		okhttp3.Response httpRes;

		try {
			httpRes = client.newCall(httpReq).execute();
			
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

	@Override
	public void put(Request req, Response res) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Request req, Response res) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProtocol() {
		return "HTTP";
	}
}
