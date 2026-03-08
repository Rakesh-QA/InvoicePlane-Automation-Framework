package pages;

import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.WaitUtils;

public class ClientPage {

    WebDriver driver;

    public ClientPage(WebDriver driver) {
        this.driver = driver;
    }

    // -------- Locators --------

    By clientsMenu = By.xpath("//a[@data-original-title='Clients']");
    By addClientBtn = By.xpath("(//a[contains(@class,'btn btn-primary btn-sm')])[1]");

    By clientNameField = By.id("client_name");
    By addressField = By.id("client_address_1");
    By cityField = By.id("client_city");
    By stateField = By.id("client_state");

    By countryDropdown = By.id("select2-client_country-container");
    By countrySearch = By.xpath("//input[@type='search']");
    By selectIndia = By.xpath("//li[contains(text(),'India')]");

    By contactField = By.id("client_invoicing_contact");

    By saveBtn = By.id("btn-submit");

    By clientTableRows = By.xpath("//table//tbody//tr");

    // -------- Create Client --------

    public String createClient() {

        String dynamicClientName = "Client_" + UUID.randomUUID().toString().substring(0,5);

        // Open Clients menu
        WaitUtils.waitForPresence(driver, clientsMenu);
        WaitUtils.waitForClickable(driver, clientsMenu);
        driver.findElement(clientsMenu).click();

        // Click Add Client
        WaitUtils.waitForClickable(driver, addClientBtn);
        driver.findElement(addClientBtn).click();

        // Fill Client Name
        WaitUtils.waitForVisibility(driver, clientNameField);
        driver.findElement(clientNameField).sendKeys(dynamicClientName);

        // Fill Address
        WaitUtils.waitForVisibility(driver, addressField);
        driver.findElement(addressField).sendKeys("Saibaba colony");

        // Fill City
        WaitUtils.waitForVisibility(driver, cityField);
        driver.findElement(cityField).sendKeys("Coimbatore");

        // Fill State
        WaitUtils.waitForVisibility(driver, stateField);
        driver.findElement(stateField).sendKeys("Tamil Nadu");

        // Select Country
        WaitUtils.waitForClickable(driver, countryDropdown);
        driver.findElement(countryDropdown).click();

        WaitUtils.waitForVisibility(driver, countrySearch);
        driver.findElement(countrySearch).sendKeys("India");

        WaitUtils.waitForClickable(driver, selectIndia);
        driver.findElement(selectIndia).click();

        // Contact Number
        WaitUtils.waitForVisibility(driver, contactField);
        driver.findElement(contactField).sendKeys("9234123423");

        // Save Client
        WaitUtils.waitForClickable(driver, saveBtn);
        driver.findElement(saveBtn).click();

        return dynamicClientName;
    }

    // -------- Verification --------

    public void verifyClientCreated(String clientName) {

        WaitUtils.waitForClickable(driver, clientsMenu);
        driver.findElement(clientsMenu).click();

        WaitUtils.waitForPresence(driver, clientTableRows);

        List<WebElement> rows = driver.findElements(clientTableRows);

        boolean found = false;

        for (WebElement row : rows) {
            if (row.getText().contains(clientName)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found, "Client was not found in Clients list!");
    }
}