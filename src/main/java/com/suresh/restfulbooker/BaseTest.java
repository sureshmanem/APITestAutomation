package com.suresh.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	protected RequestSpecification spec;

	@BeforeMethod
	public void setUp() {

		spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

	}

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

		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString())
				.post("/booking");
		return response;
	}

}
