package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver= DriverFactory.initDriver();
		driver.get(" https://demo.invoiceplane.com/");
	}
	
	@AfterMethod
	 public void close() {
		DriverFactory.quitDriver();
	}
}
