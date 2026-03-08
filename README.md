# InvoicePlane Payment Flow Automation

## Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)

## Test Flow
1. Login to InvoicePlane
2. Create Client
3. Create Invoice
4. Validate calculations (Subtotal, Discount, Tax, Total)
5. Perform Partial Payment
6. Validate Balance
7. Perform Remaining Payment
8. Verify Status changes to Paid
9. Validate Payment History report opens in new tab

## How to Run
1. Clone repository
2. Run `InvoicePaymentFlowTest.java`
