package pages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utils.CalculationUtil;
import utils.WaitUtils;

public class InvoicePage {

    WebDriver driver;

    public InvoicePage(WebDriver driver) {
        this.driver = driver;
    }

    // -------- LOCATORS --------

    By invoiceMenu = By.xpath("//a[@data-original-title='Invoices']");
    By createInvoiceBtn = By.xpath("//a[contains(@class,'create-invoice btn')]");
    By clientDropdown = By.xpath("(//span[@role='combobox'])[1]");
    By clientSearch = By.xpath("//input[@type='search']");
    By confirmCreate = By.xpath("//button[@class='btn btn-success ajax-loader']");

    By itemName = By.name("item_name");
    By qtyField = By.name("item_quantity");
    By priceField = By.name("item_price");
    By discountField = By.name("item_discount_amount");
    By unitDropdown = By.name("item_product_unit_id");
    By taxDropdown = By.name("item_tax_rate_id");
    By additem =By.xpath("//a[contains(@class,'btn_add_row')]"); 

    By saveInvoiceBtn = By.id("btn_save_invoice");
    By invoiceNumberField = By.id("invoice_number");
    By statusDropdown = By.id("invoice_status_id");

    // Invoice Summary
    By invoiceSubtotal = By.xpath("//td[contains(text(),'Subtotal')]/following-sibling::td");
    By invoiceTax = By.xpath("//td[contains(text(),'Item Tax')]/following-sibling::td");
    By invoiceTotal = By.xpath("//td[contains(text(),'Total')]/following-sibling::td");
    By invoiceDiscount = By.xpath("(//div[contains(text(),'Item Discount')]/following-sibling::div)[last()]");
    
    // -------- MAIN METHOD --------

    public String createAndValidateInvoice(String clientName) throws InterruptedException {

    	WaitUtils.waitForClickable(driver, invoiceMenu);
    	driver.findElement(invoiceMenu).click();
    	
    	WaitUtils.waitForClickable(driver, createInvoiceBtn);
    	driver.findElement(createInvoiceBtn).click();
    	
        // Select Client
    	Thread.sleep(3000);
        driver.findElement(clientDropdown).click();
        driver.findElement(clientSearch).sendKeys(clientName);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//li[contains(text(),'" + clientName + "')]")).click();
        Thread.sleep(3000);
        driver.findElement(confirmCreate).click();
        
        By firstItemField = By.id("item_name");
        WaitUtils.waitForPresence(driver, firstItemField);

     // ---------- PRODUCT DATA ----------
        int qty1 = 10;
        double price1 = 1500;
        double discount1Percent = 10;
        double taxPercent = 20;

        int qty2 = 5;
        double price2 = 1000;
        double discount2Percent = 5;


        // ---------- PRODUCT 1 ----------

        // Item Name
        By item1 = By.xpath("(//input[@name='item_name'])[1]");
        WaitUtils.waitForPresence(driver, item1);
        WebElement itemField1 = driver.findElement(item1);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", itemField1);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='Item1';", itemField1);

        // Quantity
        By qty1Field = By.xpath("(//input[@name='item_quantity'])[1]");
        WaitUtils.waitForPresence(driver, qty1Field);
        WebElement qtyElement1 = driver.findElement(qty1Field);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='" + qty1 + "';", qtyElement1);

        // Price
        By price1Field = By.xpath("(//input[@name='item_price'])[1]");
        WaitUtils.waitForPresence(driver, price1Field);
        WebElement priceElement1 = driver.findElement(price1Field);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='" + price1 + "';", priceElement1);
      

        // Discount
        By discount1Field = By.xpath("(//input[@name='item_discount_amount'])[1]");
        WaitUtils.waitForPresence(driver, discount1Field);
        WebElement discountElement1 = driver.findElement(discount1Field);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='" + discount1Percent + "';", discountElement1);

   
        // Unit
        By unit1 = By.xpath("(//select[@id='item_product_unit_id'])[1]");
        WaitUtils.waitForPresence(driver, unit1);

        WebElement unitElement1 = driver.findElement(unit1);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[0].options[1].value;", 
                unitElement1);
    

        // Tax
        By taxdrp1= By.xpath("(//select[contains(@name,'item_tax_rate_id')])[1]");
        WaitUtils.waitForPresence(driver, taxdrp1);

        WebElement taxElement1 = driver.findElement(taxdrp1);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[0].options[1].value;", 
                taxElement1);
        
        WaitUtils.waitForClickable(driver, additem);
        driver.findElement(additem).click();
        
        
        // ---------- PRODUCT 2 ----------
        
     
        // Item Name
        By item2 = By.xpath("(//input[@name='item_name'])[2]");
        WaitUtils.waitForPresence(driver, item2);
        WebElement itemField2 = driver.findElement(item2);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", itemField2);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='Item2';", itemField2);
       

        // Quantity
        By qty2Field = By.xpath("(//input[@name='item_quantity'])[2]");
        WaitUtils.waitForPresence(driver, qty2Field);
        WebElement qtyElement2 = driver.findElement(qty2Field);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='" + qty2 + "';", qtyElement2);

     
        // Price
        By price2Field = By.xpath("(//input[@name='item_price'])[2]");
        WaitUtils.waitForPresence(driver, price2Field);
        WebElement priceElement2 = driver.findElement(price2Field);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='" + price2 + "';", priceElement2);
        Thread.sleep(2000);

        // Discount
        By discount2Field = By.xpath("(//input[@name='item_discount_amount'])[2]");
        WaitUtils.waitForPresence(driver, discount2Field);
        WebElement discountElement2 = driver.findElement(discount2Field);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='" + discount2Percent + "';", discountElement2);
        Thread.sleep(2000);

        // Unit
        By unit2 = By.xpath("(//select[@id='item_product_unit_id'])[2]");
        WaitUtils.waitForPresence(driver, unit2);

        WebElement unitElement2 = driver.findElement(unit2);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[0].options[1].value;", 
                unitElement2);
        Thread.sleep(2000);
        // Tax
        By taxdrp2 = By.xpath("(//select[contains(@name,'item_tax_rate_id')])[2]");
        WaitUtils.waitForPresence(driver, taxdrp2);

        WebElement taxElement2 = driver.findElement(taxdrp2);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[0].options[1].value;", 
                taxElement2);

        Thread.sleep(3000);
        // ---------- SAVE ----------
        driver.findElement(saveInvoiceBtn).click();
        Thread.sleep(2000);
        
     // ---------- CALCULATIONS ----------

     // Product 1
     BigDecimal subtotal1 = BigDecimal.valueOf(qty1).multiply(BigDecimal.valueOf(price1));
     BigDecimal discount1 = BigDecimal.valueOf(qty1).multiply(BigDecimal.valueOf(discount1Percent));
     BigDecimal afterDiscount1 = subtotal1.subtract(discount1);

     BigDecimal taxValue1 = afterDiscount1
             .multiply(BigDecimal.valueOf(taxPercent))
             .divide(BigDecimal.valueOf(100));

     // Product 2
     BigDecimal subtotal2 = BigDecimal.valueOf(qty2).multiply(BigDecimal.valueOf(price2));
     BigDecimal discount2 = BigDecimal.valueOf(qty2).multiply(BigDecimal.valueOf(discount2Percent));
     BigDecimal afterDiscount2 = subtotal2.subtract(discount2);

     // Final expected values
     BigDecimal expectedSubtotal = afterDiscount1.add(afterDiscount2);

     BigDecimal expectedDiscount = discount1; // system shows only first item discount

     BigDecimal expectedTax = taxValue1;

     BigDecimal expectedTotal = expectedSubtotal.add(expectedTax);

        // ---------- READ VALUES FROM UI ----------

        WaitUtils.waitForVisibility(driver, invoiceTotal);
        
        String uiSubtotalText = driver.findElement(invoiceSubtotal).getText();
        String uiDiscountText = driver.findElement(invoiceDiscount).getText();
        String uiTaxText = driver.findElement(invoiceTax).getText();
        String uiTotalText = driver.findElement(invoiceTotal).getText();

        System.out.println("UI Subtotal: " + uiSubtotalText);
        System.out.println("UI Discount: " + uiDiscountText);
        System.out.println("UI Tax: " + uiTaxText);
        System.out.println("UI Total: " + uiTotalText);

        BigDecimal uiSubtotal = convertToBigDecimal(uiSubtotalText);
        BigDecimal uiDiscount = convertToBigDecimal(uiDiscountText);
        BigDecimal uiTax = convertToBigDecimal(uiTaxText);
        BigDecimal uiTotal = convertToBigDecimal(uiTotalText);
        
  

        // ---------- VALIDATION ----------

        Assert.assertEquals(uiSubtotal.setScale(2), expectedSubtotal.setScale(2), "Subtotal mismatch!");
        Assert.assertEquals(uiDiscount.setScale(2), expectedDiscount.setScale(2), "Discount mismatch!");
        Assert.assertEquals(uiTax.setScale(2), expectedTax.setScale(2), "Tax mismatch!");
        Assert.assertEquals(uiTotal.setScale(2), expectedTotal.setScale(2), "Total mismatch!");



        // ---------- CHANGE STATUS ----------
   
        driver.findElement(By.xpath("//span[contains(@id,'invoice_status_id-container')]")).click();
        WaitUtils.waitForPresence(driver, By.xpath("//ul[contains(@id,'invoice_status_id-results')]"));
        driver.findElement(By.xpath("//ul[@id='select2-invoice_status_id-results']//li[contains(.,'Sent')]")).click();
        driver.findElement(saveInvoiceBtn).click();
        Thread.sleep(2000);

        
    
    // ---------- VALIDATE INVOICE NUMBER ----------

    WaitUtils.waitForPresence(driver, invoiceNumberField);

    String invoiceNumber = driver.findElement(invoiceNumberField).getAttribute("value");

    System.out.println("Generated Invoice Number: " + invoiceNumber);

    Assert.assertTrue(invoiceNumber != null && !invoiceNumber.trim().isEmpty(),
            "Invoice number not generated!");
    
    return invoiceNumber; 
    }  
    
    private BigDecimal convertToBigDecimal(String value) {

        // Remove currency symbol and spaces
        value = value.replaceAll("[^0-9,.-]", "");

        // Convert European format (19 875,00) → (19875.00)
        value = value.replace(".", "");
        value = value.replace(",", ".");

        return new BigDecimal(value);
    }
    
    public double getInvoiceTotal() {

    	String totalText = driver.findElement(invoiceTotal)
    	        .getText()
    	        .replace("$", "")
    	        .replace(" ", "")
    	        .replace(",", ".")
    	        .trim();

    	return Double.parseDouble(totalText);
    }
}