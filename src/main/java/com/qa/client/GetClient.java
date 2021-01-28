package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

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
	
	public String NestedJSONResponse(String resp, String jPath)
	{
		JSONObject jObj = new JSONObject(resp);
		String response = jObj.get(jPath).toString();
		
		return response;
	}
	
	public HashMap<String, String> FetchValueByField(String resp, Set<Object> keyResp, String fieldName)
	{
		 HashMap<String, String> valueList = new HashMap<String, String>();
		 
		 if(resp.contains("[") || resp.contains("]"))
		 {
			 HashMap<String, String> myList = new HashMap<String, String>();
			 JSONArray jsonArray = new JSONArray(resp);
			 for(int i=0;i<jsonArray.length();i++)
			 {
				 JSONObject jsonObj = jsonArray.getJSONObject(i);
				 myList.put(Integer.toString(i), jsonObj.toString());
				 
			 }
			 
			 String findKey = null;
			 
			 for(int i=0;i<myList.size();i++)
			 {
				 for(Object j: keyResp)
				 {
					 JSONObject myObj = new JSONObject(myList.get(Integer.toString(i)));
					 if(myObj.get(j.toString()).toString().equalsIgnoreCase(fieldName))
					 {
						 findKey = Integer.toString(i);
					 }
				 }
			 }
			 
			 JSONObject respObj = new JSONObject(myList.get(findKey));
			 for(Object j: keyResp)
			 {
				 valueList.put(j.toString(), respObj.get(j.toString()).toString());
			 }
			 
		 }
		 else
		 {
			 JSONObject respObj = new JSONObject(resp);
			 for(Object j: keyResp)
			 {
				 valueList.put(j.toString(), respObj.get(j.toString()).toString());
			 }
		 }
	
		 return valueList;
		 
	}
	
}
