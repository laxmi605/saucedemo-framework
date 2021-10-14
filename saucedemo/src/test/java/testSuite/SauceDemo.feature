#Author: 
@SauceDemo
Feature: ShoppingCart

@openSauceDemo
Scenario: validate saucedemo url is working
When open the saucedemo url
Then the login page is displayed

@InvalidLogin
Scenario: Validate for invalid login functionality
Given user is on login page
When enter valid username and invalid pwd and click Login
Then error message is displayed

@ValidLogin
Scenario: validate for valid login
Given user is on login page
When input valid username and valid pwd and click login
Then user should login successfully


@Validatonofproducts
Scenario: validate the number of products
Given user should login successfully
Then the total products should be 6

@sortproducts
Scenario: validate the products are sorted Low to High
When select the option to sort price low to high
Then the products are sorted price wise Low to high

@addtocart
Scenario: validate the products added
When add products to cart
Then the number of added products should match

@removefromcart
Scenario: validate the number of products in cart after remove
When remove a products from cart
Then the number of items in cart should be 1 less




