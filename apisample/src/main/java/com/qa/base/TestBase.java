package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public Properties prop;
	public int response_status_code = 200;
	
public TestBase(){
	try {
		prop= new Properties();
		FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
		prop.load(ip);
		
		
	}
	catch(FileNotFoundException e) {
		
	}
	catch(IOException e) {
		
	}
	
}
}
