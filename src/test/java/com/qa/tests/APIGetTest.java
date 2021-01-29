package com.qa.tests;

import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.GetClient;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class APIGetTest extends TestBase {

	TestBase testBase;
	GetClient getClient;
	ExtentReports reports;
	ExtentTest test;

	@BeforeTest
	public void setup() {
		testBase = new TestBase();
		reports = new ExtentReports(System.getProperty("user.dir")+"//test-output//ExtentReports//ExtentReports_"+System.currentTimeMillis()+".html");
		test = reports.startTest("APIGetTesting");
	}

	@Test
	public void getTest() throws ClientProtocolException, IOException {
		String apiURL = configProp.getProperty("APIClientURL");
		getClient = new GetClient();
		
		ArrayList<String> jsonResponse = getClient.GetConnectionStatus(apiURL);
		
		String connectionStatus = jsonResponse.get(0);
		String connectionPhrase = jsonResponse.get(1);
		String apiResponse = jsonResponse.get(2);
		
		if(connectionStatus.equalsIgnoreCase("200")) test.log(LogStatus.PASS, "Connection Status: "+ connectionStatus);
		else test.log(LogStatus.FAIL, "Connection Status: " +connectionStatus);
		
		if(connectionPhrase.equalsIgnoreCase("OK")) test.log(LogStatus.PASS, "Connection Phrase: "+ connectionPhrase);
		else test.log(LogStatus.FAIL, "Connection Phrase: " +connectionPhrase);

		apiResponse = getClient.NestedJSONResponse(apiResponse, "data");

		String ID = dataProp.getProperty("id");
		String FirstName = dataProp.getProperty("first_name");
		String LastName = dataProp.getProperty("last_name");
		String Emailid = dataProp.getProperty("email");

		HashMap<String, String> valueList = getClient.FetchValueByField(apiResponse, dataProp.keySet(), LastName);

		try{
			Assert.assertEquals(valueList.get("id"), ID);
			Assert.assertEquals(valueList.get("first_name"), FirstName);
			Assert.assertEquals(valueList.get("last_name"), LastName);
			Assert.assertEquals(valueList.get("email"), Emailid);
			test.log(LogStatus.PASS, "Actual and Expected Values are Matching");
		}
		catch(Throwable e)
		{
			test.log(LogStatus.FAIL, e.toString());
		}
	}
	
	@AfterTest
	public void tearDown(){
		reports.endTest(test);
		reports.flush();
	}

}
