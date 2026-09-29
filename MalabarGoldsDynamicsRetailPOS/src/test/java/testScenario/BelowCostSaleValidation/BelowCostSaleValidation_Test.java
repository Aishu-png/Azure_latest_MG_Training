package testScenario.BelowCostSaleValidation;

import org.testng.annotations.Test;

import testPage.BelowCostSaleValidation;
import testPage.PdcEntryExecution;
import utilPack.BaseTest;

public class BelowCostSaleValidation_Test extends BaseTest {
	
	BelowCostSaleValidation belowCostSaleValidation;
    
    @Test
    public void TC188_BelowCostSaleValidationTest() throws Exception {
    	belowCostSaleValidation = new BelowCostSaleValidation(getDriver());
    	belowCostSaleValidation.TC188_BelowCostSaleValidation();
    }

}