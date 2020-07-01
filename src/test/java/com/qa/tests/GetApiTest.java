package com.qa.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Testbase;
import com.qa.client.Restclient;
import com.qa.util.TestUtil;

public class GetApiTest extends Testbase{
	
	Testbase testbase;
	String serviceUrl;
	String apiUrl;
	String url;
	Restclient restclient;
	CloseableHttpResponse closebaleHttpResponse;
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {
		testbase= new Testbase();
		 serviceUrl = prop.getProperty("URL");
		 apiUrl =prop.getProperty("serviceURL");
		
		url = serviceUrl + apiUrl;
		
	
	}
		
	@Test(priority= 1)
	public void getTestwithoutHeader() throws ClientProtocolException, IOException {
		
		restclient = new Restclient();
		closebaleHttpResponse = restclient.get(url);
		
		
		//a. get status code:
		 int statusCode= closebaleHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("status code----->"+ statusCode);
		 Assert.assertEquals(statusCode ,RESPONSE_STATUS_CODE_200 ,"status code is not 200");
		 
		 
		 //b. json string:
		 String responeString= EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
		 
		 JSONObject  responseJson = new JSONObject(responeString);
		 System.out.println("response json from api----->"+ responseJson);
		 
		 
		 // single value assertion
		 //  per page:
		 String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		 System.out.println("value of per page is ---->" +perPageValue);
	//	 Assert.assertEquals(perPageValue, 6);// but this is throw an error  because perpage value is in integer but we take the obj as string then we have to convert it
		 Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		 
		 // total:
		 String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		 System.out.println("value of per page is ---->" +totalValue);
		 Assert.assertEquals(Integer.parseInt(totalValue), 12);
		 
		 
		 // get value from JSON Array:
		 String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		 String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		 String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		 String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		 String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		 
		 System.out.println(id);
		 System.out.println(email);
		 System.out.println(first_name);
		 System.out.println(last_name);
		 System.out.println(avatar);
		
		  
		 
		 //c. all headers:
		 Header[] headersArray = closebaleHttpResponse.getAllHeaders();
		 HashMap<String, String> allHeaders = new HashMap<String, String>();
		 
		 for(Header header : headersArray){
			 allHeaders.put(header.getName(), header.getValue());
		 }
		 System.out.println("all header--->"+ allHeaders);
		}
		
	
	@Test(priority= 2)
	public void getTestwithHeader() throws ClientProtocolException, IOException {
		
		restclient = new Restclient();
		HashMap<String, String> headerMap = new  HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("userID", "5775");

		
		
		
		
		closebaleHttpResponse = restclient.get(url);
		
		
		//a. get status code:
		 int statusCode= closebaleHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("status code----->"+ statusCode);
		 Assert.assertEquals(statusCode ,RESPONSE_STATUS_CODE_200 ,"status code is not 200");
		 
		 
		 //b. json string:
		 String responeString= EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
		 
		 JSONObject  responseJson = new JSONObject(responeString);
		 System.out.println("response json from api----->"+ responseJson);
		 
		 
		 // single value assertion
		 //  per page:
		 String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		 System.out.println("value of per page is ---->" +perPageValue);
	//	 Assert.assertEquals(perPageValue, 6);// but this is throw an error  because perpage value is in integer but we take the obj as string then we have to convert it
		 Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		 
		 // total:
		 String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		 System.out.println("value of per page is ---->" +totalValue);
		 Assert.assertEquals(Integer.parseInt(totalValue), 12);
		 
		 
		 // get value from JSON Array:
		 String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		 String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
		 String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		 String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		 String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		 
		 System.out.println(id);
		 System.out.println(email);
		 System.out.println(first_name);
		 System.out.println(last_name);
		 System.out.println(avatar);
		
		  
		 
		 //c. all headers:
		 Header[] headersArray = closebaleHttpResponse.getAllHeaders();
		 HashMap<String, String> allHeaders = new HashMap<String, String>();
		 
		 for(Header header : headersArray){
			 allHeaders.put(header.getName(), header.getValue());
		 }
		 System.out.println("all header--->"+ allHeaders);
		}
		

	}


