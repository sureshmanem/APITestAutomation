package com.suresh.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBookingTest extends BaseTest {

	@Test
	public void deleteBookingTest() {

		// Create Booking
		Response response = createBooking();
		response.print();

		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Get Booking ID
		int bookingID = response.jsonPath().getInt("bookingid");

		Response deleteResponse = RestAssured.given(spec).auth().preemptive().basic("admin", "password123")
				.delete("/booking/" + bookingID);
		deleteResponse.print();

		// Verify response 200
		Assert.assertEquals(deleteResponse.getStatusCode(), 201, "Status code should be 201, but it's not");

		// Get response with booking
		Response checkBookingID = RestAssured.given(spec).get("/booking/" + bookingID);
		checkBookingID.print();

		Assert.assertEquals(checkBookingID.getBody().asString(), "Not Found","Response to be 'Not Found'");

	}

}
