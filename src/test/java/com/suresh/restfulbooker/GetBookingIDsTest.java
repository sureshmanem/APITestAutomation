package com.suresh.restfulbooker;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
		
		// Verify All fields
				SoftAssert softAssert = new SoftAssert();
				String actualFirstName = response.jsonPath().getString("firstname");
				softAssert.assertEquals(actualFirstName, "Sally", "firstname in response is not expected");

				String actualLastName = response.jsonPath().getString("lastname");
				softAssert.assertEquals(actualLastName, "Ericsson", "lastname in response is not expected");

				int price = response.jsonPath().getInt("totalprice");
				softAssert.assertEquals(price, 753, "totalprice in response is not expected");

				boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
				softAssert.assertTrue(depositpaid, "depositpaid should be true, but it's not");

				String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
				softAssert.assertEquals(actualCheckin, "2016-02-06", "checkin in response is not expected");

				String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
				softAssert.assertEquals(actualCheckout, "2016-09-27", "checkout in response is not expected");

				String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
				softAssert.assertEquals(actualAdditionalneeds, "Breakfast", "additionalneeds in response is not expected");
				
				softAssert.assertAll();

	}

}
