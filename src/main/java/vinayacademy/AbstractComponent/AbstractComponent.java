package vinayacademy.AbstractComponent;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vinayacademy.pageobjects.CartPage;
import vinayacademy.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		 PageFactory.initElements(driver, this);
	}
	 @FindBy(xpath="//ul/li[4]/button")
	 WebElement cartHeader;
	 
	 @FindBy(css="[routerlinks*='myorders")
	 WebElement orderHeader;
	
	
	public void waitForElementToappear(By findby){
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
}
	
	public void waitForWebElementToappear(WebElement findby){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	public void waitForElementToDissApear(WebElement ele) throws InterruptedException{
		
		Thread.sleep(1000);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
}
	public CartPage goToCartPage(){
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage(){
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
