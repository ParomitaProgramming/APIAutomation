package com.qa.tests;

import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.GetClient;

import java.io.IOException;

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
		
		
	}



}
