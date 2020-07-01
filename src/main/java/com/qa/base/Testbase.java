package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class Testbase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public static int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_500 = 500;


	
	
	
	public  static Properties prop;

	 public Testbase(){
		 try {
	      prop = new Properties();
	
		 FileInputStream fp = new FileInputStream("D:\\selenium downloads\\New folder\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
		 prop.load(fp);
	} 
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
