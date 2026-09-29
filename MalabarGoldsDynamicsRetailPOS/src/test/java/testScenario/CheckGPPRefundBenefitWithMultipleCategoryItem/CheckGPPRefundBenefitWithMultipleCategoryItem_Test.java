package testScenario.CheckGPPRefundBenefitWithMultipleCategoryItem;

import org.testng.annotations.Test;

import testPage.CheckGPPRefundBenefitWithMultipleCategoryItem;
import utilPack.BaseTest;

public class CheckGPPRefundBenefitWithMultipleCategoryItem_Test extends BaseTest {

    CheckGPPRefundBenefitWithMultipleCategoryItem checkGPPRefundBenefitWithMultipleCategoryItem;

    @Test
    public void TC301_CheckGPPRefundBenefitWithMultipleCategoryItemTest() throws Exception {
        checkGPPRefundBenefitWithMultipleCategoryItem = new CheckGPPRefundBenefitWithMultipleCategoryItem(getDriver());
        checkGPPRefundBenefitWithMultipleCategoryItem.TC301_CheckGPPRefundBenefitWithMultipleCategoryItem();
    }
    
    @Test
    public void TC302_CheckGPPRefundBenefitWithMultipleCategoryItemTest() throws Exception {
        checkGPPRefundBenefitWithMultipleCategoryItem = new CheckGPPRefundBenefitWithMultipleCategoryItem(getDriver());
        checkGPPRefundBenefitWithMultipleCategoryItem.TC302_CheckGPPRefundBenefitWithMultipleCategoryItem();
    }
}
