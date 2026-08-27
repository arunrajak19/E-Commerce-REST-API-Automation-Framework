package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

    public RequestSpecification loginRequestSpecification() {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        return req;
    }

    public RequestSpecification requestSpecification(String token) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addHeader("Authorization", token).build();

        return req;
    }

    public RequestSpecification requestSpecificationWithContentType(String token) {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addHeader("Authorization", token).setContentType(ContentType.JSON).build();

        return req;
    }
}
