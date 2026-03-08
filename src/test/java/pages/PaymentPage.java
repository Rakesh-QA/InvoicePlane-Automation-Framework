package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PaymentPage {

    WebDriver driver;
    WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By optionsBtn = By.xpath("//a[contains(@class,'btn btn-default dropdown-toggle')]");
    By enterPaymentBtn = By.xpath("//a[@class='invoice-add-payment']");
    By amountField = By.id("payment_amount");
    By sumbitBtn = By.xpath("//button[@class='btn btn-success']");
    By saveBtn = By.id("btn-submit");
    By balanceAmount = By.xpath("//td[contains(@class,'amount last')]");
    By filterBtn = By.id("filter");
    By optionBtn2 = By.xpath("//a[@class='btn btn-default btn-sm dropdown-toggle']");
    By FilterBtn2 = By.xpath("//input[@placeholder='Filter Invoices']");
    


    // STEP 3 - Partial Payment
    public void makePartialPayment(double partialAmount, String invoiceNumber) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(optionsBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(enterPaymentBtn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField)).clear();
        driver.findElement(amountField).sendKeys(String.valueOf(partialAmount));
        
        System.out.println("Partial Payment Entered: " + partialAmount);

        wait.until(ExpectedConditions.elementToBeClickable(sumbitBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        
        System.out.println("Partial Payment Saved Successfully for Invoice: " + invoiceNumber);

 	   By invoiceMenu = By.xpath("//a[@data-original-title='Invoices']");

       wait.until(ExpectedConditions.elementToBeClickable(invoiceMenu)).click();
        searchInvoice(invoiceNumber);
    }


    // Search invoice
    public void searchInvoice(String invoiceNumber) throws InterruptedException {
    	
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterBtn)).clear();
        driver.findElement(filterBtn).sendKeys(invoiceNumber);

        // Wait until invoice appears in table
        By invoiceRow = By.xpath("//a[contains(text(),'" + invoiceNumber + "')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(invoiceRow));
        
        Thread.sleep(4000);
    }


    // Get balance amount
    public double getBalanceAmount(String invoiceNumber) {

        By balanceForInvoice = By.xpath(
            "//tr[td/a[contains(text(),'" + invoiceNumber + "')]]//td[contains(@class,'amount last')]"
        );

        String balanceText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(balanceForInvoice))
                .getText()
                .replace("$", "")
                .replace(" ", "")
                .replace(",", ".")
                .trim();

        return Double.parseDouble(balanceText);
    }


 // Validate balance
    public void validateBalance(double total, double paid, String invoiceNumber) {

        double expected = total - paid;
        double actual = getBalanceAmount(invoiceNumber);

        Assert.assertEquals(actual, expected, "Balance mismatch!");
    }


    // STEP 4 - Remaining Payment
    public void payRemainingAmount(double remainingAmount, String invoiceNumber) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(optionBtn2)).click();
        wait.until(ExpectedConditions.elementToBeClickable(enterPaymentBtn)).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(sumbitBtn)).click();
        System.out.println("Full Payment Completed for Invoice: " + invoiceNumber);

        
        Thread.sleep(2000);
  

        searchInvoice(invoiceNumber);
    }
    
 // Verify invoice status is Paid
    public void verifyInvoicePaid(String invoiceNumber) {
    	
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(FilterBtn2)).clear();
         driver.findElement(filterBtn).sendKeys(invoiceNumber);
    	
        By paidStatus = By.xpath(
            "//tr[td/a[contains(text(),'" + invoiceNumber + "')]]//span[contains(@class,'paid')]"
        );

        String status = wait.until(
                ExpectedConditions.visibilityOfElementLocated(paidStatus))
                .getText()
                .trim();

        System.out.println("Invoice Status After Full Payment: " + status);

        Assert.assertEquals(status, "Paid", "Invoice status is not Paid!");

        System.out.println("Status successfully changed to PAID for Invoice: " + invoiceNumber);
    }
}