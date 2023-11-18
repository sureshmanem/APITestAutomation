package com.suresh.restfulbooker;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class HealthCheckTest extends BaseTest {

	@Test
	public void healthCheckTest() {
		
		RestAssured.given().spec(spec).when().get("/ping").then().assertThat().statusCode(201);
		
	}

}
