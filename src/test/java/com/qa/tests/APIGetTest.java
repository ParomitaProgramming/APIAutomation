package com.qa.tests;

import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.GetClient;

import junit.framework.Assert;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeTest;

public class APIGetTest extends TestBase {

	TestBase testBase;
	GetClient getClient;

	@BeforeTest
	public void setup() {
		testBase = new TestBase();
	}

	@Test
	public void getTest() throws ClientProtocolException, IOException {
		String apiURL = configProp.getProperty("APIClientURL");
		getClient = new GetClient();
		String apiResponse = getClient.GetConnectionStatus(apiURL);
		
		apiResponse = getClient.NestedJSONResponse(apiResponse, "data");
		
		String ID = dataProp.getProperty("id");
		String FirstName = dataProp.getProperty("first_name");
		String LastName = dataProp.getProperty("last_name");
		String Email = dataProp.getProperty("email");
		
		HashMap<String, String> valueList = getClient.FetchValueByField(apiResponse, dataProp.keySet(), ID);
		
		Assert.assertEquals(valueList.get("id"), ID);
		Assert.assertEquals(valueList.get("first_name"), FirstName);
		Assert.assertEquals(valueList.get("last_name"), LastName);
		Assert.assertEquals(valueList.get("email"), Email);
	}

}
