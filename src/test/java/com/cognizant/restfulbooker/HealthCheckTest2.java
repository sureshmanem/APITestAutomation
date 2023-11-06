package com.cognizant.restfulbooker;

import org.testng.annotations.Test;
import io.restassured.*;

public class HealthCheckTest2 {

	@Test
	public void healthCheckTest() {
		String url = "https://restful-booker.herokuapp.com/ping";
		RestAssured.given().
		when().
			get(url).
		then().
			assertThat().
			statusCode(201);
	}

}
