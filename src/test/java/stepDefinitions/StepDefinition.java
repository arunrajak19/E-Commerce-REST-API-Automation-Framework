package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jshell.execution.Util;
import org.testng.Assert;
import pojo.LogInBody;
import pojo.LogInResponse;
import pojo.OrderDetails;
import pojo.Orders;
import resources.APIResources;
import resources.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {

    static String token;
    static String productId;
    static String orderId;
    RequestSpecification req;
    RequestSpecification reqspec;
    LogInResponse logInResponse;
    Response response;
    String userId;

    @Given("User is on login page")
    public void user_is_on_login_page() {

        LogInBody logInBody = new LogInBody();
        logInBody.setUserEmail("lionel@gmail.com");
        logInBody.setUserPassword("Messi@10");

        reqspec = given().spec(loginRequestSpecification()).body(logInBody);

    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {

        APIResources apiResources = APIResources.valueOf(resource);
        if (httpMethod.equalsIgnoreCase("POST")) {
            response = reqspec.when().post(apiResources.getResources()).
                    then().extract().response();
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = reqspec.when().get(apiResources.getResources()).
                    then().extract().response();
        } else if (httpMethod.equalsIgnoreCase("DELETE")) {
            response = reqspec.when().delete(apiResources.getResources()).
                    then().extract().response();
        }
    }

    @Then("API call got success with status code {int}")
    public void api_call_got_success_with_status_code(int statusCode) {

        String responseBody = response.asString();
        System.out.println(response.statusCode());
        System.out.println(responseBody);
        Assert.assertEquals(response.statusCode(), statusCode);

    }

    @Then("Token and userId is generated")
    public void token_and_userId_is_generated() {
        logInResponse = response.as(LogInResponse.class);

        token = logInResponse.getToken();
        userId = logInResponse.getUserId();
    }

    @Then("We get a success message {string}")
    public void we_get_a_success_message(String message) {
        String responseBody = response.asString();
        JsonPath jp = new JsonPath(responseBody);
        Assert.assertEquals(jp.getString("message"), message);
        System.out.println("Message is: " + jp.getString("message"));
    }

    @Given("User is adding product")
    public void user_is_adding_product() {

        reqspec = given().spec(requestSpecification(token)).formParam("productName", "Football").
                formParam("productAddedBy", userId).
                formParam("productCategory", "fashion").
                formParam("productSubCategory", "shirts").
                formParam("productPrice", "11000").
                formParam("productDescription", "Addias Originals").
                formParam("productFor", "men").
                multiPart("productImage", new File("C:/Users/91620/Downloads/awayKit.jpg"));

    }

    @Then("Product Id is generated")
    public void product_id_is_generated() {

        String addProductResponse = response.asString();
        JsonPath jp = new JsonPath(addProductResponse);
        productId = jp.getString("productId");

        System.out.println("ProductId is: " + productId);

    }

    @Given("User is on home page")
    public void user_is_on_home_page() {
//        req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
//                addHeader("Authorization", token).setContentType(ContentType.JSON).build();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(productId);
        System.out.println(productId);


        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);

        Orders orders = new Orders();
        orders.setOrders(orderDetailsList);

        reqspec = given().spec(requestSpecificationWithContentType(token)).body(orders);


    }

    @Then("OrderID is generated")
    public void order_id_is_generated() {
        String createProductResponse = response.asString();
        JsonPath jp = new JsonPath(createProductResponse);

        orderId = jp.getString("orders[0]");
        System.out.println(orderId);
        String orderedMesssage = jp.getString("message");
        System.out.println(orderedMesssage);
    }

    @Given("User is on orders page")
    public void user_is_on_orders_page() {

        reqspec = given().spec(requestSpecification(token)).queryParam("id", orderId);
    }

    @Then("OrderID, userId and productOrderId is generated")
    public void order_id_user_id_and_product_order_id_is_generated() {
        String getResponse = response.asString();
        JsonPath jp = new JsonPath(getResponse);

        System.out.println(getResponse);
//        String id = jp.getString("_id");
    }

    @Given("User want to delete the added product")
    public void user_want_to_delete_the_added_product() {
        reqspec = given().spec(requestSpecificationWithContentType(token)).pathParams("productId", productId);
    }
}
