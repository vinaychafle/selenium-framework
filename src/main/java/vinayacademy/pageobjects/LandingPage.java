package vinayacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayacademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	 public LandingPage(WebDriver driver)
	 {
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	
	 @FindBy(id="userEmail")
	 WebElement userEmail;
	 
	 @FindBy(id="userPassword")
	 WebElement userPassword;
	 
	 @FindBy(id="login")
	 WebElement login;
	 
	 @FindBy(css="[class*='flyInOut']")
	 WebElement errormessage;
	 
	 public ProductCatalog loginApplication(String Email,String Password){
		 userEmail.sendKeys(Email);
		 userPassword.sendKeys(Password);
		 login.click();
		 ProductCatalog productCatalog=new ProductCatalog(driver);
		 return productCatalog;
		 
	 }
	 public String getErrorMessage(){
		 waitForWebElementToappear(errormessage);
		return errormessage.getText();
	 }
	 
	 public void goTo(){
		 driver.get("https://rahulshettyacademy.com/client");
	 }
}
