package testSuite;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SauceDemo;

public class SauceDemoStep 
{

		SauceDemo sd =new SauceDemo();
		
		@When("^open the saucedemo url$")
	    public void open_the_saucedemo_url() throws Throwable {
	        sd.setUp();
	        sd.openUrl();
	    }

	    @Then("^the login page is displayed$")
	    public void the_login_page_is_displayed() throws Throwable {
	        
	    	sd.validateTitle();
	    }
		/////************************************Invalid Login scenario
	    @Given("^user is on login page$")
	    public void user_is_on_login_page() throws Throwable
	    {	    	
	        sd.openUrl();
	    }
	    @When("^enter valid username and invalid pwd and click Login$")
	    public void enter_valid_username_and_invalid_pwd_and_click_login() throws Throwable
	    {
	        sd.invalidLogin();
	    }
	    @Then("^error message is displayed$")
	    public void error_message_is_displayed() throws Throwable 
	    {
	        sd.validateErrormsg();
	    }
	    	    
	    /////************************************Valid Login scearnio
	    @When("^input valid username and valid pwd and click login$")
	    public void input_valid_username_and_valid_pwd_and_click_login() throws Throwable 
	    {
	        sd.validLogin();
	    }
	    @Then("^user should login successfully$")
	    public void user_should_login_successfully() throws Throwable
	    {
	        sd.validateHomepage();
	    }
	    
	    ////*****************************************validate number of products
	    @Then("^the total products should be 6$")
	    public void the_total_products_should_be_6() throws Throwable
	    {
	        sd.validatenoofProducts();
	    }
	    
	    ////***************************************** sort products low to high
	    @When("^select the option to sort price low to high$")
	    public void select_the_option_to_sort_price_low_to_high() throws Throwable 
	    {
	        sd.sortProducts();
	    }	   
	    @Then("^the products are sorted price wise Low to high$")
	    public void the_products_are_sorted_price_wise_low_to_high() throws Throwable
	    {
	        sd.validateProductssorted();
	    }
	    
	    ////****************************************add products to cart
	    @When("^add products to cart$")
	    public void add_products_to_cart() throws Throwable 
	    {
	        sd.addTocart();
	    }	    
	    @Then("^the number of added products should match$")
	    public void the_number_of_added_products_should_match() throws Throwable
	    {
	        sd.validateaddedcart();
	    }
	    
	    ////************************************* remove product from cart
	    @When("^remove a products from cart$")
	    public void remove_a_products_from_cart() throws Throwable 
	    {
	        sd.removeFromCart();
	    }	    
	    @Then("^the number of items in cart should be 1 less$")
	    public void the_number_of_items_in_cart_should_be_1_less() throws Throwable
	    {
	        sd.validateRemovedCart();
	        sd.tearDown();
	    }	    

}
