package vinayacademy;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayacademy.pageobjects.LandingPage;

public class Standalonetest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		//WebDriver driver=new ChromeDriver(options);
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		//give the wait
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//land on website
		driver.get("https://rahulshettyacademy.com/client");
		//window maximize
		
		
		//create object of LandingPage class
		
		
		//username and password  and click on login button
		LandingPage obj=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("superman@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Karan@11");
		driver.findElement(By.id("login")).click();
		String productname="ZARA COAT 3";
		//for loading products on page we set explicit wait for all products
	
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//css selector is used .mb-3 or generic div.card (tagename.classname also be used)
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		
		//apply stream on webelement list then filter element and find product name  by css selector of product then if  first name matches then  it wil goes and stores in(text manner stores) prod Webelement or not then stores null 
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);	
		
		System.out.println(prod.getText());
		
		//apply findelement on prod Webelement because scope of webelement under the card or product 
		prod.findElement(By.cssSelector("div.card button:last-of-type")).click(); // click on add to cart button
		// why it is done on prod because scope of driver is under the zara coat section and scope of search will be on second add to cart button only
		
		//wait for add to cart and message show added product	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));
		//.ng-animating css for loading and wait for loading
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		//click on cart button to see product is added 
		driver.findElement(By.xpath("//ul/li[4]/button")).click();
		
		
		// verify the correct product is added in cart for that create again webelement list  and get the product names 
		
		//parent to chid css tagename.classname space tage of product  OR we can also use xpath :-//div[@class='cartSection']/h3
		List<WebElement>cartProducts=driver.findElements(By.cssSelector("div.cartSection h3"));
		
		//get ara coat match text in that list for that anymatch maethod is used it returns boolean value
		boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		
		Assert.assertTrue(match);
		
		
		//click on checkout button 
		//.totalRow button
		//we can also use text based xpath //button[text()='Checkout']
		driver.findElement(By.cssSelector(".subtotal button")).click();
		//.subtotal button
		
		//auto suggestive drop down box
		//we use Action class
		
		Actions a=new Actions(driver);
		//sendkeys to box & css selector are used tagename[attribute='value']
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		
		///wait for options 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
		
		//click on india which is on second place OR also use css .ta-item:nth-of-type(2)
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//click on place order button
		//By.xpath("//a[text()='Place Order ']")
		WebElement po=driver.findElement(By.xpath("//a[text()='Place Order ']"));
		//driver.findElement(By.cssSelector(".action__submit")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		//js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red')", po);
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.elementToBeClickable(po));
		//js.executeScript("arguments[0].click();", po);
		po.click();
		//driver.findElement(By.cssSelector(".action__submit")).click();
		//po.findElement(By.xpath("//a[text()='Place Order ']")).click();
		//driver.findElement(By.linkText("Apply Coupon")).click();
		//verify the thank you for order page
		String conformmessage=driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		
		//compare conform message with expected		
		Assert.assertTrue(conformmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		//close browser
		driver.close();
	}

}
