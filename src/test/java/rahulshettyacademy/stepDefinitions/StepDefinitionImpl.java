package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Cart;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
		//code
	}
	
	 @Given ("^Logged in with username (.+) and password (.+)$")
	 public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username,password);

	 }
	 
	 @When ("^I add product (.+) to Cart$")
	 public void I_added_product_to_cart(String productName) {
		 productCatalogue.getProductList();
		 productCatalogue.addProductToCart(productName);
	 }
	 
	 @When ("^Checkout (.+) and submit the order$")
	 public void Checkout_submit_order(String productName) {
		 	Cart cart = productCatalogue.goToCart();

			boolean match = cart.checkProductsInCart(productName);
			Assert.assertTrue(match);

			CheckoutPage checkoutPage = cart.checkout();				
			checkoutPage.selectCountry("Mac");
			confirmationPage = checkoutPage.submitOrder();
	 }

	    @Then ("{string} message is displayed on ConfirmationPage")
	    public void message_displayed_confirmationPage(String string) {
	    	String confirmMessage = confirmationPage.checkMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	    }
	    
	    @Then ("^\"([^\"]*)\" message is displayed$")
	    public void something_message_is_displayed(String strArg1) {
			Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
			driver.close();
	    }

}
