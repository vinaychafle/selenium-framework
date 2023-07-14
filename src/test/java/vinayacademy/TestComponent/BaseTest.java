package vinayacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import vinayacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initilizationOfDriver() throws IOException{
	
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//vinayacademy//Resourses//GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			options.addArguments("--remote-allow-origins=*");
			 driver=new ChromeDriver(options);
			 driver.manage().window().setSize(new Dimension(1440,900)); //full screen run
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","D:\\Selenium\\setup\\Chrome driver\\geckodriver.exe");
			 driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver","edge.exe");
			 driver=new EdgeDriver();
		}
		
					
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.manage().window().maximize();
	return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException{
		driver=initilizationOfDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		driver.close();
	}
	
	

public List<HashMap<String,String>> getJsonDataTOMap(String filePath) throws IOException{
		
		//read JSON to String
		String JSONcontent=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//String to HashMap jackson Datbind
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(JSONcontent, new TypeReference<List<HashMap<String,String>>>() {
	});
		return data;
}
public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
}

}
