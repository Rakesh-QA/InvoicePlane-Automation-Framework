package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReportsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By reportsMenu = By.xpath("//span[text()='Reports']");
    By paymentHistory = By.xpath("//a[contains(text(),'Payment History')]");
    By dateFilter = By.id("from_date");
    By generateReportBtn = By.xpath("//input[@type='submit']");


    public void openPaymentHistoryReport() {

        String parentWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.elementToBeClickable(reportsMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(paymentHistory)).click();

        wait.until(ExpectedConditions.elementToBeClickable(generateReportBtn)).click();

        Set<String> windows = driver.getWindowHandles();

        Assert.assertTrue(windows.size() > 1, "Report did not open in a new tab");

        for (String window : windows) {

            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        System.out.println("Payment History report opened in new tab successfully.");
    }
}