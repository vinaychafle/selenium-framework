package vinayacademy;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import vinayacademy.TestComponent.BaseTest;
import vinayacademy.TestComponent.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void submitorder() throws IOException, InterruptedException{
		
		String productname="ZARA COAT 3";
		landingPage.loginApplication("superman@gmail.com", "Karan@1");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
		
		
		
	}

}
