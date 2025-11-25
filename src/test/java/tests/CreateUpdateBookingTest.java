package tests;

import base.BaseTest;
import org.apache.http.HttpStatus;

import org.testng.annotations.Test;

import client.ServiceClient;
import io.restassured.response.Response;
import utils.JsonUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateUpdateBookingTest extends BaseTest {
	private final ServiceClient serviceClient = new ServiceClient();
	String bookingId;

	String bookingJson = JsonUtils.readJsonFile("json/booking.json");
	String updateJson = JsonUtils.readJsonFile("json/update.json");


	@Test
	public void testCreateBooking() {
		Response response = serviceClient.createBooking(bookingJson);
		response.then().statusCode(HttpStatus.SC_OK);
		response.then().body(matchesJsonSchemaInClasspath("schemas/bookingSchema.json"));
		bookingId = response.jsonPath().getString("bookingid");
		System.out.println("booking id: " + bookingId);
	}

	@Test(dependsOnMethods = {"testCreateBooking"})
	public void testUpdateBooking() {
		System.out.println("token: " + token);
		System.out.println("booking id: " + bookingId);
		Response response = serviceClient.updateBooking(token, updateJson,"/"+bookingId);
		response.then().statusCode(HttpStatus.SC_OK);
	}

}