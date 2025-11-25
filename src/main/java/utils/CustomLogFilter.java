package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomLogFilter implements Filter {

    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        System.out.println("============= Start Test ============");

        System.out.println("\n======= REQUEST =======");
        System.out.println("***** Request Type: "+requestSpec.getMethod());
        System.out.println("***** Endpoint: "+ requestSpec.getURI());
        System.out.println("***** Headers: " + requestSpec.getHeaders());
        System.out.println("***** Body: " + requestSpec.getBody());

        Response response = ctx.next(requestSpec, responseSpec);

        System.out.println("\n======= RESPONSE =======");
        System.out.println("***** Status Code: " + response.getStatusCode());
        System.out.println("***** Headers: " + response.getHeaders());
        System.out.println("***** Body: " + response.getBody().prettyPrint());

        System.out.println("============= End Test ============\n");

        return response;
    }
}
