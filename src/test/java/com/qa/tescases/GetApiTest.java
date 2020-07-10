package com.qa.tescases;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetApiTest extends TestBase {

	TestBase testbase;
	RestClient restClient;
	String serviceUrl;
	String apiUrl;
	String url;
	CloseableHttpResponse closablehttpresponse;

	@BeforeTest
	public void setUp() {
		testbase = new TestBase();

		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("apiURL");

		url = serviceUrl + apiUrl;

	}

	@Test
	public void getApiTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closablehttpresponse = restClient.get(url);

		// a.Status code
		int statusCode = closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status Code----->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not as expected");

		// b.JSON String
		String responseString = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API----->" + responseJson);

		// c.All Headers
		Header[] headersArray = closablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("HeadersArray---->" + allHeaders);
	}

	@Test
	public void getApiTestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username", "swetha");
		// headerMap.put("password", "dfdff");

		closablehttpresponse = restClient.get(url);

		// a.Status code
		int statusCode = closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status Code----->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not as expected");

		// b.JSON String
		String responseString = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API----->" + responseJson);

		// c.All Headers
		Header[] headersArray = closablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("HeadersArray---->" + allHeaders);
	}

}
