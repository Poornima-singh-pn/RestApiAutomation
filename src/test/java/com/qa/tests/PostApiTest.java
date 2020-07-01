package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Testbase;
import com.qa.client.Restclient;
import com.qa.data.Users;

public class PostApiTest  extends Testbase{

	
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
	 @Test
	 public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		 restclient = new Restclient();
		 HashMap<String, String> headerMap = new  HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			headerMap.put("userID", "5775");

		
			//jackson API:
			ObjectMapper mapper = new ObjectMapper();
			Users users = new Users("1983"); //expected users obejct
			
			//object to json file:
			mapper.writeValue(new File("D:/selenium downloads/New folder/restapi/src/main/java/com/qa/data/users.json"), users);
			
			//java object to json in String:
			String usersJsonString = mapper.writeValueAsString(users);
			System.out.println(usersJsonString);
			
     		closebaleHttpResponse = restclient.post(url, usersJsonString, headerMap); //call the API
			
			//validate response from API:
			//1. status code:
			int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
			Assert.assertEquals(statusCode, Testbase.RESPONSE_STATUS_CODE_201);
			
			//2. JsonString:
			String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
			
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("The response from API is:"+ responseJson);
			
			//json to java object:
			Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
			System.out.println(usersResObj);
			
			Assert.assertTrue(users.getauthUserID().equals(usersResObj.getauthUserID()));
			
			
			System.out.println(usersResObj.getId());
			System.out.println(usersResObj.getCreatedAt());
			
		}
		 
	 
}
