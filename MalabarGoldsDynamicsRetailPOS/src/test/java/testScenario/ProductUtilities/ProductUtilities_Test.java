package testScenario.ProductUtilities;

import java.util.List;

import org.testng.annotations.Test;

import utilPack.BaseTest;
import utilPack.ProductUtilities;
import utilPack.ProductUtilities.SKUTypeResult;

public class ProductUtilities_Test extends BaseTest {
	
	ProductUtilities ProductUtils;
	
	@Test()
	public void GoldPcsRateItem() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		SKUTypeResult Gold= ProductUtils.GetMakingAndPcsRateItems("Inter", "Gold", "Gold", "Gold", 2.0);
		List<String>GoldPcs = Gold.PcsRateSKU;
		List<String>GoldMaking = Gold.MakingRateSKU;
		int TotalSku = GoldPcs.size()+ GoldMaking.size();
		System.out.println("Total Number of Gold Sku: "+TotalSku);
		System.out.println("Number of Gold SKUs to check: "+GoldPcs.size());
		List<String> RequiredGoldPcsRateItem = ProductUtils.GetRequiredPcsRateItems(GoldPcs);	
		System.out.println("Number of valid Gold PcsRateSKUs: "+RequiredGoldPcsRateItem.size());
		System.out.println("Required Gold PcsRateSKUs: "+RequiredGoldPcsRateItem);
	}
	
	@Test()
	public void DiamondPcsRateItem() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		SKUTypeResult Diamond= ProductUtils.GetMakingAndPcsRateItems("Inter", "Diamond", "Gold", "Diamond", 2.0);
		List<String>DiamondPcs = Diamond.PcsRateSKU;
		List<String>DiamondMaking = Diamond.MakingRateSKU;
		int TotalSku = DiamondPcs.size()+ DiamondMaking.size();
		System.out.println("Total Number of Diamond Sku: "+TotalSku);
		System.out.println("Number of Diamond SKUs to check: "+DiamondPcs.size());
		List<String> RequiredDiamondPcsRateItem = ProductUtils.GetRequiredPcsRateItems(DiamondPcs);	
		System.out.println("Number of valid Diamond PcsRateSKUs: "+RequiredDiamondPcsRateItem.size());
		System.out.println("Required Diamond PcsRateSKUs: "+RequiredDiamondPcsRateItem);
	}
	
	@Test()
	public void SilverPcsRateItem() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		SKUTypeResult Silver= ProductUtils.GetMakingAndPcsRateItems("Inter", "Silver", "Silver", "Silver", 2.0);
		List<String>SilverPcs = Silver.PcsRateSKU;
		List<String>SilverMaking = Silver.MakingRateSKU;
		int TotalSku = SilverPcs.size()+ SilverMaking.size();
		System.out.println("Total Number of Silver Sku: "+TotalSku);
		System.out.println("Number of Silver SKUs to check: "+SilverPcs.size());
		List<String> RequiredSilverPcsRateItem =ProductUtils.GetRequiredPcsRateItems(SilverPcs);	
		System.out.println("Number of valid Silver PcsRateSKUs: "+RequiredSilverPcsRateItem.size());
		System.out.println("Required Silver PcsRateSKUs: "+RequiredSilverPcsRateItem);
	}
	
	@Test()
	public void PlatinumPcsRateItem() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		SKUTypeResult Platinum= ProductUtils.GetMakingAndPcsRateItems("Inter", "Platinum", "Platinum", "Platinum", 2.0);
		List<String>PlatinumPcs = Platinum.PcsRateSKU;
		List<String>PlatinumMaking = Platinum.MakingRateSKU;
		int TotalSku = PlatinumPcs.size()+ PlatinumMaking.size();
		System.out.println("Total Number of Platinum Sku: "+TotalSku);
		System.out.println("Number of Platinum SKUs to check: "+PlatinumPcs.size());
		List<String> RequiredPlatinumPcsRateItem = ProductUtils.GetRequiredPcsRateItems(PlatinumPcs);
		System.out.println("Number of valid Platinum PcsRateSKUs: "+RequiredPlatinumPcsRateItem.size());
		System.out.println("Required Platinum PcsRateSKUs: "+RequiredPlatinumPcsRateItem);
	}
	
	@Test()
	public void PlatinumItem() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		SKUTypeResult Platinum= ProductUtils.GetMakingAndPcsRateItems("Inter", "Platinum", "Platinum", "Platinum", 5000.0);
		List<String>PlatinumSku = Platinum.PcsRateSKU;
		System.out.println("Number of Platinum SKUs to check: "+PlatinumSku.size());
		ProductUtils.GetRequiredPlatinumItems(PlatinumSku);		
	}
	
	@Test()
	public void UncutSkuCount() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		ProductUtils.GetSkuCount("Inter", "Uncut", "Gold");	
	}
	
	@Test()
	public void PreciaSkuCount() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		ProductUtils.GetSkuCount("Inter", "Precia", "Gold");
	}
	
	@Test()
	public void VirazSkuCount() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		ProductUtils.GetRequiredVirazSku();
	}

	@Test()
	public void TotalKeralSkuCount() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		List<String> KeralSku = ProductUtils.GetAllKeralSku();
		List<String> ValidKeralSku = ProductUtils.GetRequiredKeralSku(KeralSku);
		ProductUtils.GetRequiredKeralSkuInRange(ValidKeralSku);
	}
	@Test()
	public void SilverArticlesSkuCount() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		ProductUtils.GetRequiredSilverArticleSku();
	}
	
	
	@Test()
	public void GoldCoinCount() throws InterruptedException {
		
		ProductUtils = new ProductUtilities(getDriver());
		ProductUtils.GetRequiredGoldCoinSku();
	}
}
