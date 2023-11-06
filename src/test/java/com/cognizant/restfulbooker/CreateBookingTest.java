package com.cognizant.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingTest {
	
	@Test
	public void createBookingTest() {
		
		JSONObject body = new JSONObject();
		body.put("firstname", "Suresh");
		body.put("lastname", "Ramesh");
		body.put("totalprice", 150);
		body.put("depositpaid", false);
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2020-01-01");
		bookingdates.put("checkout", "2021-01-01");
		
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Lunch");
		
		String url = "https://restful-booker.herokuapp.com/booking";
		Response response = RestAssured.given().
				contentType(ContentType.JSON).
				body(body.toString()).
				post(url);
		response.print();
		
		Assert.assertEquals(response.statusCode(), 200,"Status should be 200");
		String actualFirstName = response.jsonPath().getString("booking.firstname");
		String actualLastName = response.jsonPath().getString("booking.lastname");
		
		// Hard Assert Code
//		Assert.assertEquals(actualFirstName, "Suresh1", "mismatch in first name");
//		Assert.assertEquals(actualLastName, "Ramesh1", "mismatch in last name");
		
		
		// Soft Assert Code
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualFirstName, "Suresh1", "mismatch in first name");
		softAssert.assertEquals(actualLastName, "Ramesh1", "mismatch in last name");
		
		
		// Booking ID Response
		String bookingid = response.jsonPath().getString("bookingid");
		String url1 = "https://restful-booker.herokuapp.com/booking/" + bookingid;
		Response response1 = RestAssured.get(url1);
		
		System.out.println("Booking Confirmation Response is " + response1.print());
		
		softAssert.assertAll();
		
	}

}
