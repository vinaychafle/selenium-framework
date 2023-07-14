package vinayacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import vinayacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	 public CartPage(WebDriver driver)
	 {
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	
	 
	 
	 @FindBy(css="div.cartSection h3")
	 List<WebElement> cartProduct;
	 	
	 @FindBy(css=".totalRow button")
	 WebElement checkOut;
	 
	 
	 
	 public boolean verifyProductDisplay(String productname){
		 boolean match=cartProduct.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));	
		 
		 return match;
	 }
	 public CheckOutPage gotoCheckout(){
		 checkOut.click();
		return new CheckOutPage(driver);
		
	 }
	 

	 }
	 
	
	 
	 
