package com.cognizant.restfulbooker;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIDsTest {

	@Test
	public void getBookingIDsWithoutFilterTest() {
		String url = "https://restful-booker.herokuapp.com/booking";
		Response response = RestAssured.get(url);
		response.print();

		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it is not");

		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(), "List of bookingids is empty, but it should not be");

	}

}
