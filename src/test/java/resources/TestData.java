package resources;

import pojo.LogInBody;
import pojo.OrderDetails;
import pojo.Orders;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static LogInBody loginData() {
        LogInBody logInBody = new LogInBody();
        logInBody.setUserEmail("lionel@gmail.com");
        logInBody.setUserPassword("Messi@10");
        return logInBody;
    }

    public static Orders ordersData(String productId) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(productId);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails);
        Orders orders = new Orders();
        orders.setOrders(orderDetailsList);
        return orders;
    }
}
