package tests;

import base.BaseTest;
import client.ServiceClient;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.CSVDataProvider;

public class GetBookingTest extends BaseTest {

    private final ServiceClient serviceClient = new ServiceClient();


    //data driven test(used data provider from  CSV file)
    @Test(dataProvider = "userData", dataProviderClass = CSVDataProvider.class)
    public void testGetBooking(String bookingId) {
        Response response = serviceClient.getBooking("/" + bookingId);
        response.then().statusCode(HttpStatus.SC_OK);
    }
}
