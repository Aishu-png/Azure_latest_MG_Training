package testScenario.InvoiceWithPaymentMethodB2BVoucher;

import org.testng.annotations.Test;

import testPage.InvoiceWithDifferentTypeAdvances;
import testPage.InvoiceWithPaymentMethodB2BVoucher;
import utilPack.BaseTest;

public class InvoiceWithPaymentMethodB2BVoucher_Test extends BaseTest {
	
	InvoiceWithPaymentMethodB2BVoucher invoiceWithPaymentMethodB2bVoucher;
	@Test()
	public void TC201_InvoiceWithPaymentMethodB2BVoucherTest() throws Exception 
	{	
		invoiceWithPaymentMethodB2bVoucher = new InvoiceWithPaymentMethodB2BVoucher(getDriver());
		invoiceWithPaymentMethodB2bVoucher.TC201_InvoiceWithPaymentMethodB2BVoucher();
	}
	@Test()
	public void TC199_InvoiceWithPaymentMethodB2BVoucherTest() throws Exception 
	{	
		invoiceWithPaymentMethodB2bVoucher = new InvoiceWithPaymentMethodB2BVoucher(getDriver());
		invoiceWithPaymentMethodB2bVoucher.TC199_InvoiceWithPaymentMethodB2BVoucher();
	}
	
	@Test()
	public void TC200_InvoiceWithPaymentMethodB2BVoucherTest() throws Exception 
	{	
		invoiceWithPaymentMethodB2bVoucher = new InvoiceWithPaymentMethodB2BVoucher(getDriver());
		invoiceWithPaymentMethodB2bVoucher.TC200_InvoiceWithPaymentMethodB2BVoucher();
	}
}
