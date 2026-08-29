package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    RequestSpecification req;

    public static String getGlobalBaseURI(String key) throws IOException {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\E-Commerce REST API End-to-End Automation Framework\\E-Commerce-REST-API-Automation-Framework\\src\\test\\java\\resources\\global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public RequestSpecification loginRequestSpecification() throws IOException {
        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("loging.txt"));
            RequestSpecification req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalBaseURI("baseURI"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
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
