package testScenario.CheckAdvanceRefundBenefitWithGldMultipleCategoryItem;

import org.testng.annotations.Test;

import testPage.CheckAdvanceRefundBenefitWithGldMultipleCategoryItem;
import utilPack.BasePge;
import utilPack.BaseTest;

public class CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Test extends BaseTest{
	
	CheckAdvanceRefundBenefitWithGldMultipleCategoryItem checkAdvanceRefundBenefitWithGldMultipleCategoryItem;
	
	@Test
	public void TC296_CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTest() throws Exception
	{
		checkAdvanceRefundBenefitWithGldMultipleCategoryItem = new CheckAdvanceRefundBenefitWithGldMultipleCategoryItem(getDriver());
		checkAdvanceRefundBenefitWithGldMultipleCategoryItem.TC296_CheckAdvanceRefundBenefitWithGldMultipleCategoryItem();		
	}
	
	@Test
	public void TC297_CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTest() throws Exception
	{
		checkAdvanceRefundBenefitWithGldMultipleCategoryItem = new CheckAdvanceRefundBenefitWithGldMultipleCategoryItem(getDriver());
		checkAdvanceRefundBenefitWithGldMultipleCategoryItem.TC297_CheckAdvanceRefundBenefitWithGldMultipleCategoryItem();		
	}
	@Test
	public void TC295_CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTest() throws Exception
	{
		checkAdvanceRefundBenefitWithGldMultipleCategoryItem = new CheckAdvanceRefundBenefitWithGldMultipleCategoryItem(getDriver());
		checkAdvanceRefundBenefitWithGldMultipleCategoryItem.TC295_CheckAdvanceRefundBenefitWithGldMultipleCategoryItem();		
	}

}
