package com.qa.test;

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

import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {
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
	public void getApiTest() throws ClientProtocolException, IOException {
		RestClient restclient = new RestClient();
	
		
//		HashMap<String,String> headermap = new HashMap<String,String>();
//		headermap.put("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
//		headermap.put("x-rapidapi-key", "SIGN-UP-FOR-KEY");
//		
		httpresponse = restclient.get(url);
		System.out.println(url);
		
		//Status Check
		int status = httpresponse.getStatusLine().getStatusCode();
		System.out.println("Status:"+status);
		Assert.assertEquals(status,response_status_code,"Status code is not 200");
		
		
		//Payload check
		String responsestring = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("response JSON:"+responsejson);
		String perpagevalue = TestUtil.getValueByJpath(responsejson,"/data/first_name");
		Assert.assertEquals(perpagevalue, "Janet");
		System.out.println(perpagevalue);
		
		
		//Header check
		 Header[] headerarray = httpresponse.getAllHeaders();
		 HashMap<String ,String> headerlist = new HashMap<String,String>();
		 for(Header header:headerarray) {
			 headerlist.put(header.getName(), header.getValue());
		 }
		 System.out.println("headerlist:"+headerlist);
		
		}
	

		
	

}
