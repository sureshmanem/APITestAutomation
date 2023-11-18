package com.suresh.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PartialUpdateBookingTest extends BaseTest{

	@Test
	public void updateBookingTest() {

		// Create Booking
		Response response = createBooking();
		response.print();

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");
		
		// Get Booking ID
		int bookingID = response.jsonPath().getInt("bookingid");

		// Update Booking
		JSONObject body = new JSONObject();
		body.put("firstname", "Suresh");
		body.put("lastname", "Manem");
		body.put("totalprice", 200);
		body.put("depositpaid", false);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2020-01-01");
		bookingdates.put("checkout", "2021-01-01");

		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Lunch");

		Response updateResponse = RestAssured.given().auth().preemptive().basic("admin", "password123").
				contentType(ContentType.JSON).body(body.toString()).put("/booking/"+bookingID);
		updateResponse.print();

		
		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = updateResponse.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Suresh", "firstname in response is not expected");

		String actualLastName = updateResponse.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Manem", "lastname in response is not expected");

		int price = updateResponse.jsonPath().getInt("totalprice");
		softAssert.assertEquals(price, 200, "totalprice in response is not expected");

		boolean depositpaid = updateResponse.jsonPath().getBoolean("depositpaid");
		softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

		String actualCheckin = updateResponse.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2020-01-01", "checkin in response is not expected");

		String actualCheckout = updateResponse.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2021-01-01", "checkout in response is not expected");

		String actualAdditionalneeds = updateResponse.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Lunch", "additionalneeds in response is not expected");

		softAssert.assertAll();

	}

	

}
