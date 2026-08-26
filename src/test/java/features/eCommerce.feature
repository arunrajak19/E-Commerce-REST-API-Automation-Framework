Feature: Validating ECommerce API

  Scenario: Login
    Given User is on login page
    When User calls "loginAPI" with "POST" http request
    Then API call got success with status code 200
    And Token and userId is generated
    And We get a success message "Login Successfully"

  Scenario: Create Product
    Given User is adding product
    When User calls "addProductAPI" with "POST" http request
    Then API call got success with status code 201
    And Product Id is generated
    And We get a success message "Product Added Successfully"

  Scenario: Create Order
    Given User is on home page
    When User calls "createOrderAPI" with "POST" http request
    Then API call got success with status code 201
    And OrderID is generated
    And We get a success message "Order Placed Successfully"
