package com.qa.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetClient {

	public String GetConnectionStatus(String apiURL) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(apiURL);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println("Connection Status: "+httpResponse.getStatusLine().getStatusCode());
		System.out.println("Connection Phrase: "+httpResponse.getStatusLine().getReasonPhrase());
		
		String response = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		return response;
	}
	
}
