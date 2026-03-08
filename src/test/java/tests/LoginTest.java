package tests;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.ClientPage;
import pages.InvoicePage;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ReportsPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLogin() throws InterruptedException{

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin@invoiceplane.com", "demopassword");
        
        ClientPage clientPage = new ClientPage(driver);
        String clientName = clientPage.createClient();
        clientPage.verifyClientCreated(clientName);
        
        InvoicePage invoicePage = new InvoicePage(driver);
        String invoiceNumber = invoicePage.createAndValidateInvoice(clientName);
        double totalAmount = invoicePage.getInvoiceTotal();

        PaymentPage paymentPage = new PaymentPage(driver);

        double partialPayment = totalAmount * 0.4;

        paymentPage.makePartialPayment(partialPayment, invoiceNumber);

        paymentPage.validateBalance(totalAmount, partialPayment, invoiceNumber);

        double remaining = totalAmount - partialPayment;

        paymentPage.payRemainingAmount(remaining, invoiceNumber);

        paymentPage.verifyInvoicePaid(invoiceNumber);
        
        ReportsPage reportsPage = new ReportsPage(driver);

        reportsPage.openPaymentHistoryReport();
        

    }
}