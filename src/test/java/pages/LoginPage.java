package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By emailField = By.id("email");
    By passwordField = By.id("password");
    By loginButton = By.xpath("//button[@type='submit']");

    public void login(String email, String password) {

        WaitUtils.waitForVisibility(driver, emailField);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}