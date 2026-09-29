package testScenario.GPPSettlementDiscountPlan;

import utilPack.BaseTest;

import org.testng.annotations.Test;
import testPage.GPPSettlementAverageRateSingleSkuMultipleSku;
import testPage.GPPSettlementDiscountPlan;
public class GPPSettlementDiscountPlan_Test extends BaseTest{

	GPPSettlementDiscountPlan gPPSettlementDiscountPlan;

	@Test()
	public void TC125_GPPSettlementDiscountPlanTest() throws Exception 
	{	
		gPPSettlementDiscountPlan = new GPPSettlementDiscountPlan(getDriver());
		gPPSettlementDiscountPlan.TC125_GPPSettlementDiscountPlan();
	}

	@Test()
	public void TC126_GPPSettlementDiscountPlanTest() throws Exception 
	{	
		gPPSettlementDiscountPlan = new GPPSettlementDiscountPlan(getDriver());
		gPPSettlementDiscountPlan.TC126_GPPSettlementDiscountPlan();
	}
	@Test()
	public void TC122_GPPSettlementDiscountPlanTest() throws Exception 
	{	
		gPPSettlementDiscountPlan = new GPPSettlementDiscountPlan(getDriver());
		gPPSettlementDiscountPlan.TC122_GPPSettlementDiscountPlan();
	}
	@Test()
	public void TC123_GPPSettlementDiscountPlanTest() throws Exception 
	{	
		gPPSettlementDiscountPlan = new GPPSettlementDiscountPlan(getDriver());
		gPPSettlementDiscountPlan.TC123_GPPSettlementDiscountPlan();
	}
	@Test()
	public void TC120_GPPSettlementDiscountPlanTest() throws Exception 
	{	
		gPPSettlementDiscountPlan = new GPPSettlementDiscountPlan(getDriver());
		gPPSettlementDiscountPlan.TC120_GPPSettlementDiscountPlan();
	}
	@Test()
	public void TC121_GPPSettlementDiscountPlanTest() throws Exception 
	{	
		gPPSettlementDiscountPlan = new GPPSettlementDiscountPlan(getDriver());
		gPPSettlementDiscountPlan.TC121_GPPSettlementDiscountPlan();
	}
}
