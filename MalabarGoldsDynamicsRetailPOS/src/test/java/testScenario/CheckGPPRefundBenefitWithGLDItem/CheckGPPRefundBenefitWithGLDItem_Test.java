package testScenario.CheckGPPRefundBenefitWithGLDItem;
import org.testng.annotations.Test;

import testPage.CheckGPPRefundBenefitWithGLDItem;
import utilPack.BaseTest;

public class CheckGPPRefundBenefitWithGLDItem_Test extends BaseTest {

	CheckGPPRefundBenefitWithGLDItem checkGPPRefundBenefitWithGLDItem;

    @Test
    public void TC298_CheckGPPRefundBenefitWithGLDItem() throws Exception {
    	checkGPPRefundBenefitWithGLDItem = new CheckGPPRefundBenefitWithGLDItem(getDriver());
    	checkGPPRefundBenefitWithGLDItem.TC298_CheckGPPRefundBenefitWithGLDItem();
    }
    
    @Test
    public void TC299_CheckGPPRefundBenefitWithGLDItem() throws Exception {
    	checkGPPRefundBenefitWithGLDItem = new CheckGPPRefundBenefitWithGLDItem(getDriver());
    	checkGPPRefundBenefitWithGLDItem.TC299_CheckGPPRefundBenefitWithGLDItem();
    }
    
}
