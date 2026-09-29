package testScenario.CashCollectionRepaymentLimitValidation;
import org.testng.annotations.Test;
import testPage.CashCollectionRepaymentLimitValidation;
import utilPack.BaseTest;

public class CashCollectionRepaymentLimitValidation_Test extends BaseTest 
{
	CashCollectionRepaymentLimitValidation cashCollectionRepaymentLimitValidation;
	@Test()
	public void TC170_CashCollectionRepaymentLimitValidationTest() throws Exception 
	{
		cashCollectionRepaymentLimitValidation = new CashCollectionRepaymentLimitValidation(getDriver());
		cashCollectionRepaymentLimitValidation.TC170_CashCollectionRepaymentLimitValidation();
	}
	@Test()
	public void TC171_CashCollectionRepaymentLimitValidationTest() throws Exception 
	{
		cashCollectionRepaymentLimitValidation = new CashCollectionRepaymentLimitValidation(getDriver());
		cashCollectionRepaymentLimitValidation.TC171_CashCollectionRepaymentLimitValidation();
	}
	@Test()
	public void TC321_CashCollectionRepaymentLimitValidationTest() throws Exception 
	{
		cashCollectionRepaymentLimitValidation = new CashCollectionRepaymentLimitValidation(getDriver());
		cashCollectionRepaymentLimitValidation.TC321_CashCollectionRepaymentLimitValidation();
	}

	@Test()
	public void TC323_CashCollectionRepaymentLimitValidationTest() throws Exception 
	{
		cashCollectionRepaymentLimitValidation = new CashCollectionRepaymentLimitValidation(getDriver());
		cashCollectionRepaymentLimitValidation.TC323_CashCollectionRepaymentLimitValidation();
	}

	
	@Test()
	public void TC324_CashCollectionRepaymentLimitValidationTest() throws Exception 
	{
		cashCollectionRepaymentLimitValidation = new CashCollectionRepaymentLimitValidation(getDriver());
		cashCollectionRepaymentLimitValidation.TC324_CashCollectionRepaymentLimitValidation();
	}
	@Test()
	public void TC322_CashCollectionRepaymentLimitValidationTest() throws Exception 
	{
		cashCollectionRepaymentLimitValidation = new CashCollectionRepaymentLimitValidation(getDriver());
		cashCollectionRepaymentLimitValidation.TC322_CashCollectionRepaymentLimitValidation();
	}

}
