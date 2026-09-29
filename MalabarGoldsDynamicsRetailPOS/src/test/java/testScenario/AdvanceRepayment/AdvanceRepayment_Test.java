package testScenario.AdvanceRepayment;

import org.testng.annotations.Test;

import testPage.AdvanceRepayment;
import testPage.CustomerCreation;
import utilPack.BaseTest;



public class AdvanceRepayment_Test  extends BaseTest
{
	AdvanceRepayment advanceRepayment;
	
	@Test()
	public void TC182_AdvanceRepaymentTest() throws Exception 
	{	
		advanceRepayment= new AdvanceRepayment(getDriver());
		advanceRepayment.TC182_AdvanceRepayment();
	}
	@Test()
	public void TC184_AdvanceRepaymentTest() throws Exception 
	{	
		advanceRepayment= new AdvanceRepayment(getDriver());
		advanceRepayment.TC184_AdvanceRepayment();
	}	
	@Test()
	public void TC185_AdvanceRepaymentTest() throws Exception 
	{	
		advanceRepayment= new AdvanceRepayment(getDriver());
		advanceRepayment.TC185_AdvanceRepayment();
	}	
	@Test()
	public void TC183_AdvanceRepaymentTest() throws Exception 
	{	
		advanceRepayment= new AdvanceRepayment(getDriver());
		advanceRepayment.TC183_AdvanceRepayment();
	}	
}


