package vinayacademy.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vinayacademy.TestComponent.BaseTest;
import vinayacademy.pageobjects.CartPage;
import vinayacademy.pageobjects.CheckOutPage;
import vinayacademy.pageobjects.ConfirmationPage;
import vinayacademy.pageobjects.LandingPage;
import vinayacademy.pageobjects.ProductCatalog;

public class StepDefinationImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatlog;
	public ConfirmationPage confirmationPage;

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username  (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatlog = landingPage.loginApplication(username, password);
	}

	@When("^add product (.+)  to cart $")
	public void add_product_to_cart(String ProductName) throws InterruptedException {
		List<WebElement> products = productCatlog.getProductList();
		productCatlog.addProducttoCart(ProductName);
	}

	@When("^Checkout  (.+) and submit the order $")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatlog.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		CheckOutPage checkoutpage = cartPage.gotoCheckout();
		checkoutpage.selectCountry("india");
		confirmationPage = checkoutpage.submitOrder();
	}

	@Then("{String} message is displayed on the confirmation page")
	public void message_displayed_on_the_confirmation_page(String string) {
		String conformmessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(conformmessage.equalsIgnoreCase(string));
	}
}
