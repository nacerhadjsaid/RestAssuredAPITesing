package tests;

import client.ServiceClient;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.JsonUtils;

public class CreateBookingNegativeTest {

    private final ServiceClient serviceClient = new ServiceClient();

    String authJson = JsonUtils.readJsonFile("json/auth.json");

    //create a json file with wrong data (digit instead of string for firstname)
    String wrongBookingJson = "{\n" +
            "    \"firstname\" : 111,\n" +
            "    \"lastname\" : \"Nadim\",\n" +
            "    \"totalprice\" : 100,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2025-11-22\",\n" +
            "        \"checkout\" : \"2025-11-25\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";

    //unhappy path
    @Test
    public void testCreateBookingWithNoPrice() {
        Response response = serviceClient.createBooking(wrongBookingJson);
        response.then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        //response.then().body(matchesJsonSchemaInClasspath("schemas/bookingSchema.json"));
        response.body().prettyPrint();
    }
}
