package vinayacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vinayacademy.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	 public ConfirmationPage(WebDriver driver)
	 {
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 @FindBy(css="h1.hero-primary")
	 WebElement confirmationMessage;
	 
	 public String getConfirmMessage(){
		return confirmationMessage.getText();
	 }
}
