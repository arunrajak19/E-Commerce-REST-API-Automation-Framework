Feature: Validating ECommerce API

  Scenario: Login
    Given User is on login page
    When User calls "loginAPI" with "POST" http request
    Then API call got success with status code 200
    And Token and userId is generated
    And We get a message "Login Successfully"

  Scenario: Create Product
    Given User is adding product
    When User calls "addProductAPI" with "POST" http request
    Then API call got success with status code 201
    And Product Id is generated
    And We get a message "Product Added Successfully"

  Scenario: Create Order
    Given User is on home page
    When User calls "createOrderAPI" with "POST" http request
    Then API call got success with status code 201
    And OrderID is generated
    And We get a message "Order Placed Successfully"

  Scenario: Get Oder Details
    Given User is on orders page
    When User calls "getOrderAPI" with "GET" http request
    Then API call got success with status code 200
    And OrderID, userId and productOrderId is generated
    And We get a message "Orders fetched for customer Successfully"

  Scenario: Delete the product
    Given User want to delete the added product
    When User calls "deleteProductAPI" with "DELETE" http request
    Then API call got success with status code 200
    And We get a message "Product Deleted Successfully"

  Scenario: Delete the order
    Given User want to delete the order
    When User calls "deleteOrderAPI" with "DELETE" http request
    Then API call got success with status code 200
    And We get a message "Orders Deleted Successfully"

  Scenario: Find Oder Details after order is deleted
    Given User is on orders page
    When User calls "getOrderAPI" with "GET" http request
    Then API call got success with status code 400
    And We get a message "Order not found"