package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

	public Properties configProp;
	public Properties dataProp;
	
	public TestBase()
	{
		try{
		configProp = new Properties();
		FileInputStream configip = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//qa//config//config.properties");
		configProp.load(configip);
		
		dataProp = new Properties();
		FileInputStream dataip = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//qa//config//DataSheet.properties");
		dataProp.load(dataip);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
