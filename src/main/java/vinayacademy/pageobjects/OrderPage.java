package vinayacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import vinayacademy.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	 public OrderPage(WebDriver driver)
	 {
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	
	 
	 
	 @FindBy(css="div.cartSection h3")
	 List<WebElement> cartProduct;
	 	
	 @FindBy(css="tr td:nth-child(3)")
	 private List<WebElement> ProductsNames;
	 
	 
	 
	 public boolean verifyOrderDisplay(String productname){
		 boolean match=ProductsNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));	
		 
		 return match;
	 }
	
	 

	 }
	 
	
	 
	 
