package vinayacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vinayacademy.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	}
	 @FindBy(css="input[placeholder='Select Country']")
	 WebElement country;
	 	
	 @FindBy(css="a.btnn")
	 WebElement submit;
	 
	 @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	 WebElement selectCountry;
	 
	 By result=By.cssSelector("a.btnn");
	 public void selectCountry(String countryName){
		 Actions a=new Actions(driver);		
			a.sendKeys(country, countryName).build().perform();				
			waitForElementToappear(By.cssSelector("section.ta-results"));				
			selectCountry.click();
	 }
	 
	 public ConfirmationPage submitOrder(){
		 submit.click();
		 return new ConfirmationPage(driver);
		 
	 }

}
