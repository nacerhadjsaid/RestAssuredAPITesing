package tests;

import POJO.Booking;
import POJO.BookingFactory;
import client.ServiceClient;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateBookingPOJOTest {
    String bookingId;

    private final ServiceClient serviceClient = new ServiceClient();

    //set the booking object manually
//    BookingDates bookingDates =
//            new BookingDates("2018-01-01", "2019-01-01");
//
//    Booking booking =
//            new Booking("Jim", "Brown", 111, true, bookingDates, "Breakfast");


    //set the booking object using booking factory
    Booking booking = BookingFactory.createRandomBooking();


    @Test
    public void testCreateBooking() {
        Response response = serviceClient.createBooking(booking);
        response.then().statusCode(HttpStatus.SC_OK);
        response.then().body(matchesJsonSchemaInClasspath("schemas/bookingSchema.json"));
        response.body().prettyPrint();
        bookingId = response.jsonPath().getString("bookingid");
    }
}
