package com.accenture.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	Properties pro;
	
	public ConfigReader()
	{
		pro=new Properties();
		try {
			pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/src/main//resources/configuration.properties")));
		}  catch (IOException e) 
		{
			System.out.println("Exception while reading file "+e.getMessage());
		}
	}
	
	public String getValue(String key) {
		return pro.getProperty(key);
		
	}
	
	/*
	 * 
	 * public static void main(String[] args) throws IOException { // TODO
	 * Auto-generated method stub
	 * //E:\SeleniumFramework\SeleniumMukesh\src\main\resources\configuration.
	 * properties Properties prop=new Properties(); FileReader f1=new
	 * FileReader(System.getProperty("user.dir")+
	 * "/src/main//resources/configuration.properties"); prop.load(f1); String
	 * str=prop.getProperty("browser");
	 * System.out.println("properties file data = "+ str);
	 * 
	 * System.out.println("properties file data2 = "+ prop.getProperty("url"));
	 * 
	 * 
	 * }
	 */
}
