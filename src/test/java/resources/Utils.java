package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static String getGlobalBaseURI(String key) throws IOException {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\E-Commerce REST API End-to-End Automation Framework\\E-Commerce-REST-API-Automation-Framework\\src\\test\\java\\resources\\global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public RequestSpecification loginRequestSpecification() throws IOException {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(getGlobalBaseURI("baseURI")).setContentType(ContentType.JSON).build();
        return req;
    }

    public RequestSpecification requestSpecification(String token) throws IOException {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(getGlobalBaseURI("baseURI")).
                addHeader("Authorization", token).build();
        return req;
    }

    public RequestSpecification requestSpecificationWithContentType(String token) throws IOException {
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(getGlobalBaseURI("baseURI")).
                addHeader("Authorization", token).setContentType(ContentType.JSON).build();
        return req;
    }

    public String getJsonValue(Response response, String key) {

        String jsonResponse = response.asString();
        JsonPath js = new JsonPath(jsonResponse);
        return js.getString(key);
    }
}
