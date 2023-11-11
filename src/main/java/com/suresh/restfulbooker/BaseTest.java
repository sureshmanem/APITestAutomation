package com.suresh.restfulbooker;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	
	protected Response createBooking() {
		JSONObject body = new JSONObject();
		body.put("firstname", "Suresh");
		body.put("lastname", "Manem");
		body.put("totalprice", 150);
		body.put("depositpaid", false);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2020-01-01");
		bookingdates.put("checkout", "2021-01-01");

		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Lunch");

		String url = "https://restful-booker.herokuapp.com/booking";
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post(url);
		return response;
	}

}
