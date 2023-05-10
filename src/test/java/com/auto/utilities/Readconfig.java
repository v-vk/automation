package com.auto.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Readconfig {

	Properties prop;
	
	public Readconfig() {
		File file = new File("./resources/config.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}
	
	public String getApplicationUrl() {
		return prop.getProperty("url");
	}
	
	
	public String getUserName() {
		return prop.getProperty("username");
	}
	
	public String getPassword() {
		return prop.getProperty("password");
	}
}
