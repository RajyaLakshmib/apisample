package com.qa.restclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//Get method without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
	CloseableHttpResponse httpresponse = httpclient.execute(httpget);
	return httpresponse;
	
	

}
	
	
	//Get method with headers
		public CloseableHttpResponse get(String url,HashMap<String, String> headermap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpclient= HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			for(Entry<String, String> entry: headermap.entrySet()) {
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
		CloseableHttpResponse httpresponse = httpclient.execute(httpget);
		return httpresponse;
		
		

	}
		
		//post method with header
		public CloseableHttpResponse post(String url,String entitystring, HashMap<String,String> headermap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new StringEntity(entitystring));
			for(Entry<String,String> entry:headermap.entrySet()) {
				httppost.addHeader(entry.getKey(),entry.getValue());
			}
			CloseableHttpResponse httpresponse=httpclient.execute(httppost);
			return httpresponse;
		}
		
}
