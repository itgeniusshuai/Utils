package com.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientUtils {

	public static Object doGet(String url, Map<String, String> headers) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		for (String key : headers.keySet()) {
			getMethod.addRequestHeader(key, headers.get(key));
		}
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] bs = getMethod.getResponseBody();
			String str = new String(bs, "utf-8");
			return str;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return null;

	}
}
