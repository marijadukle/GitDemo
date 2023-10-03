package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Cart;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		Cart cart = productCatalogue.goToCart();

		boolean match = cart.checkProductsInCart(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cart.checkout();				
		checkoutPage.selectCountry("Mac");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.checkMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("marijapaneva@hotmail.com", "Shopping-2022");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		ordersPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	

	
	// Extend Reports - 
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[] [] {{data.get(0)}, {data.get(1)}};
	}
}
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
	
	//return new Object[] [] {{map}, {map1}};
//@DataProvider
//public Object[] [] getData() {
//	return new Object[] [] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
//}