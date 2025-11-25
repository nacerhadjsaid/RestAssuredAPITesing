package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigManager;
import utils.CustomLogFilter;
import utils.TokenManager;

public class BaseTest {

    protected String token;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.get("url");
        RestAssured.filters(new CustomLogFilter());
        token = TokenManager.getToken();
        System.out.println("Retrieved token: " + token );
    }

}
