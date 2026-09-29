package testScenario.TenderDeclerationSafeDropIncomeExcessCustSettl;

import org.testng.annotations.Test;

import testPage.BelowCostSaleValidation;
import testPage.PdcEntryExecution;
import testPage.TenderDeclerationSafeDropIncomeExcessCustSettl;
import utilPack.BaseTest;

public class TenderDeclerationSafeDropIncomeExcessCustSettl_Test extends BaseTest {
	
	TenderDeclerationSafeDropIncomeExcessCustSettl tenderDeclerationSafeDropIncomeExcessCustSettl;
    
    @Test
    public void TC308_TenderDeclerationSafeDropIncomeExcessCustSettlTest() throws Exception {
    	tenderDeclerationSafeDropIncomeExcessCustSettl = new TenderDeclerationSafeDropIncomeExcessCustSettl(getDriver());
    	tenderDeclerationSafeDropIncomeExcessCustSettl.TC308_TenderDeclerationSafeDropIncomeExcessCustSettl();
    }

}