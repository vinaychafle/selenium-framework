package vinayacademy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayacademy.TestComponent.BaseTest;
import vinayacademy.pageobjects.CartPage;
import vinayacademy.pageobjects.CheckOutPage;
import vinayacademy.pageobjects.ConfirmationPage;
import vinayacademy.pageobjects.LandingPage;
import vinayacademy.pageobjects.OrderPage;
import vinayacademy.pageobjects.ProductCatalog;

public class SubmitOrdertest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "purchaseOrder")
	public void submitorder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("Email"), input.get("Password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProducttoCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.gotoCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutpage.submitOrder();
		String conformmessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(conformmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitorder" })
	public void orderHistoryTest() {
		ProductCatalog productCatalog = landingPage.loginApplication("superman@gmail.com", "Karan@11");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productname));

	}

	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataTOMap(
				System.getProperty("user.dir") + "//src//test//java//vinayacademy//Data//purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

		// base for above data driven hashmap using jason file to read data
		// HashMap<String,String>map=new HashMap<String,String>();
		// map.put("Email", "superman@gmail.com");
		// map.put("Password", "Karan@11");
		// map.put("product", "ZARA COAT 3");
		//
		// HashMap<String,String>map1=new HashMap<String,String>();
		// map1.put("Email", "vinay01@gmail.com");
		// map1.put("Password", "Vinay@123");
		// map1.put("productname", "ADIDAS ORIGINAL");

	}

}
