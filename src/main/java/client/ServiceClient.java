package client;

import POJO.Booking;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ServiceClient {

	//URIs
	private final static String BOOKING = "/booking";

	//get booking by id
	public Response getBooking(String bookingId){
		return given()
				.accept("application/json")
				.when()
				.get(BOOKING + bookingId);
	}

	//create booking using raw json
	public Response createBooking(String body){
		return given()
				.contentType("application/json")
				.accept("application/json")
				.body(body)
				.when()
				.post(BOOKING);
	}

	//create booking using POJO
	public Response createBooking(Booking body){
		return given()
				.contentType("application/json")
				.accept("application/json")
				.body(body)
				.when()
				.post(BOOKING);
	}

	//update booking using raw json
	public Response updateBooking(String token, String jsonToUpdate, String bookingId){
		return given()
				.contentType("application/json")
				.accept("application/json")
				.header("Cookie","token="+token)
				.header("Authorization","Basic")
				.body(jsonToUpdate)
				.when()
				.patch(BOOKING+bookingId);
	}

}
