package testScenario.CreditBill;

import org.testng.annotations.Test;

import testPage.CreditBill;
import testPage.CustomerCreation;
import utilPack.BaseTest;


public class CreditBill_Test extends BaseTest {
	
	CreditBill  creditBill;
	
	@Test()
	public void TC181_CreditBillTest() throws Exception 
	{	
		creditBill= new CreditBill(getDriver());
		creditBill.TC181_CreditBill();
	}
	

}

