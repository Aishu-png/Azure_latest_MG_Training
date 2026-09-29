package testScenario.InvoiceWithAdvanceSchemeSR;

import org.testng.annotations.Test;

import testPage.InvoiceWithAdvanceSchemeSR;
import testPage.InvoiceWithSRandOG;
import utilPack.BaseTest;

public class InvoiceWithAdvanceSchemeSR_Test extends BaseTest {
	
	InvoiceWithAdvanceSchemeSR invoiceWithAdvanceSchemeSR;
	
	@Test
	public void TC286_InvoiceWithAdvanceSchemeSRTest() throws Exception
	{
		invoiceWithAdvanceSchemeSR = new InvoiceWithAdvanceSchemeSR(getDriver());
		invoiceWithAdvanceSchemeSR.TC286_InvoiceWithAdvanceSchemeSR();		
	}
	
	@Test
	public void TC288_InvoiceWithAdvanceSchemeSRTest() throws Exception
	{
		invoiceWithAdvanceSchemeSR = new InvoiceWithAdvanceSchemeSR(getDriver());
		invoiceWithAdvanceSchemeSR.TC288_InvoiceWithAdvanceSchemeSR();		
	}
	@Test
	public void TC287_InvoiceWithAdvanceSchemeSRTest() throws Exception
	{
		invoiceWithAdvanceSchemeSR = new InvoiceWithAdvanceSchemeSR(getDriver());
		invoiceWithAdvanceSchemeSR.TC287_InvoiceWithAdvanceSchemeSR();		
	}
}