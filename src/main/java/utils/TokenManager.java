package utils;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String token;
    private static long tokenExpiryTime;

    private final static String AUTHENTICATION = "/auth";
    private static final String authJson = JsonUtils.readJsonFile("json/auth.json");

    public static synchronized String getToken() {

        if (token == null || System.currentTimeMillis() >= tokenExpiryTime) {
            generateNewToken();
        }
        return token;
    }

    private static void generateNewToken() {
        System.out.println("🔄 Generating new token...");

        Response response = given()
                .contentType("application/json")
                .body(authJson)
                .post(AUTHENTICATION)
                .then()
                .statusCode(200)
                .extract().response();
        token  = response.jsonPath().getString("token");

        // token valid for 30 minutes
        tokenExpiryTime = System.currentTimeMillis() + (30 * 60 * 1000);
    }
}
