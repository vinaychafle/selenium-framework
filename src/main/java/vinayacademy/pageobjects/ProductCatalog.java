package vinayacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import vinayacademy.AbstractComponent.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;
	 public ProductCatalog(WebDriver driver)
	 {
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	// List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	 
	 
	 @FindBy(css=".mb-3")
	 List<WebElement> products;
	 
	 @FindBy(css=".ng-animating")
	 WebElement spinner;
	 
	 By productBy=By.cssSelector(".mb-3");
	By addtocart= By.cssSelector("div.card button:last-of-type");
	By tostMessage= By.cssSelector("div#toast-container");
	 public List<WebElement> getProductList(){
		 waitForElementToappear(productBy);
		 return products;
	 }
	 
	 public WebElement getProductByName(String ProductName){
		 WebElement prod= getProductList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);	
		 
		 return prod;
	 }
	 public void addProducttoCart(String ProductName) throws InterruptedException{
		 WebElement prod=getProductByName(ProductName);
		 prod.findElement(addtocart).click();
		 
		 waitForElementToappear(tostMessage);
		 waitForElementToDissApear(spinner);
		
	 }
	 

	 }
	 
	
	 
	 
