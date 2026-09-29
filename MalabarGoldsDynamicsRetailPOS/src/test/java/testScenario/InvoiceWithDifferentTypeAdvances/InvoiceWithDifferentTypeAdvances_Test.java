package testScenario.InvoiceWithDifferentTypeAdvances;

import org.testng.annotations.Test;

import testPage.InvoiceWithDifferentTypeAdvances;
import testPage.PANCardLimitValidation;
import utilPack.BaseTest;

public class InvoiceWithDifferentTypeAdvances_Test extends BaseTest {

	InvoiceWithDifferentTypeAdvances invoiceWithDifferentTypeAdvances;

	@Test()
	public void TC193_InvoiceWithDifferentTypeAdvancesTest() throws Exception 
	{	
		invoiceWithDifferentTypeAdvances = new InvoiceWithDifferentTypeAdvances(getDriver());
		invoiceWithDifferentTypeAdvances.TC193_InvoiceWithDifferentTypeAdvances();
	}
	
	@Test()
	public void TC197_InvoiceWithDifferentTypeAdvancesTest() throws Exception 
	{	
		invoiceWithDifferentTypeAdvances = new InvoiceWithDifferentTypeAdvances(getDriver());
		invoiceWithDifferentTypeAdvances.TC197_InvoiceWithDifferentTypeAdvances();
	}	
	
	@Test()
	public void TC194_InvoiceWithDifferentTypeAdvancesTest() throws Exception 
	{	
		invoiceWithDifferentTypeAdvances = new InvoiceWithDifferentTypeAdvances(getDriver());
		invoiceWithDifferentTypeAdvances.TC194_InvoiceWithDifferentTypeAdvances();
	}
	
	@Test()
	public void TC196_InvoiceWithDifferentTypeAdvancesTest() throws Exception 
	{	
		invoiceWithDifferentTypeAdvances = new InvoiceWithDifferentTypeAdvances(getDriver());
		invoiceWithDifferentTypeAdvances.TC196_InvoiceWithDifferentTypeAdvances();
	}

	@Test()
	public void TC195_InvoiceWithDifferentTypeAdvancesTest() throws Exception 
	{	
		invoiceWithDifferentTypeAdvances = new InvoiceWithDifferentTypeAdvances(getDriver());
		invoiceWithDifferentTypeAdvances.TC195_InvoiceWithDifferentTypeAdvances();
	}
	@Test()
	public void TC198_InvoiceWithDifferentTypeAdvancesTest() throws Exception 
	{	
		invoiceWithDifferentTypeAdvances = new InvoiceWithDifferentTypeAdvances(getDriver());
		invoiceWithDifferentTypeAdvances.TC198_InvoiceWithDifferentTypeAdvances();
	}
}
