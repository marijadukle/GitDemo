package rahulshettyacademy.tests;

import java.io.IOException;
import rahulshettyacademy.TestComponents.Retry;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Cart;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {

		
		landingPage.loginApplication("marijapaneva@hotmail.com", "Shopp-2022");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("marijapaneva@hotmail.com", "Shopping-2022");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		Cart cart = productCatalogue.goToCart();

		boolean match = cart.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
