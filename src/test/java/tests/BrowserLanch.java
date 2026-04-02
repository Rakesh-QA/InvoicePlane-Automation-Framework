package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrowserLanch {
	
	@Test
	public void browser() {
		WebDriver driver = new ChromeDriver();
	}

}
