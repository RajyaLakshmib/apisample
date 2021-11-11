package com.qa.test;

//gorest.co.in
//fakeresapi.azurewebsites.net/api/activities

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.data.UsersData;
import com.qa.restclient.RestClient;

public class PostAPITest extends TestBase  {
	
	TestBase testbase;
	String serviceurl;
	String apiurl ;
	String url;
	CloseableHttpResponse httpresponse;
	
	@BeforeMethod
	public void setup() {
		testbase = new TestBase();
		serviceurl = prop.getProperty("url");
		 apiurl = prop.getProperty("serviceurl");
		url = serviceurl+apiurl;
		
	}
	
	@Test
	public void postApi() throws JsonGenerationException, JsonMappingException, IOException {
RestClient restclient = new RestClient();
	
		
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
		headermap.put("x-rapidapi-key", "SIGN-UP-FOR-KEY");
		
		//Jackson Api- Marshalling(object to JSON)
		ObjectMapper objectmapper = new ObjectMapper();
		UsersData users= new UsersData("morphus","leader");//Expected user object
		//object to JSON file
		objectmapper.writeValue(new File("C:\\Users\\Rajya Lakshmi\\eclipse-workspace\\apisample\\src\\main\\java\\com\\qa\\data\\users.json"),users);
		
		//object to JSON String
		String userJSONString = objectmapper.writeValueAsString(users);
		 httpresponse = restclient.post(url,userJSONString,headermap);
		
		//Status Check
			int status = httpresponse.getStatusLine().getStatusCode();
			System.out.println("Status:"+status);
			Assert.assertEquals(status,response_status_code,"Status code is not 200");
			
			//JSONString check
			String responsestring = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
			JSONObject responsejson = new JSONObject(responsestring);
			System.out.println("response JSON:"+responsejson);
			
			//JSON to Java object
			UsersData userresobj = objectmapper.readValue(responsestring,UsersData.class);//Actual user object
			
			System.out.println(users.getName().equals(userresobj.getName()));
			
			
			
	}
}
