package testScenario.InvoiceWithSRandOG;

import org.testng.annotations.Test;

import testPage.InvoiceWithSRandOG;
import utilPack.BaseTest;

public class InvoiceWithSRandOG_Test extends BaseTest {
	InvoiceWithSRandOG invoiceWithSRandOG;
	
	@Test
	public void TC291_InvoiceWithSRandOG() throws Exception
	{
		invoiceWithSRandOG = new InvoiceWithSRandOG(getDriver());
		invoiceWithSRandOG.TC291_InvoiceWithSRandOG();		
	}
	
	
	@Test
	public void TC292_InvoiceWithSRandOG() throws Exception
	{
		invoiceWithSRandOG = new InvoiceWithSRandOG(getDriver());
		invoiceWithSRandOG.TC292_InvoiceWithSRandOG();		
	}
	
	
}
