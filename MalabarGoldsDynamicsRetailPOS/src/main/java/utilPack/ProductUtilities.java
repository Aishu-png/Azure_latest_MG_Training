
package utilPack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.GppOpeningAndCollectionMultiplePlans_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleDiamondandPlatinum_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import objectRepository.StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj;
import testData.CommonData;
import testData.Utility_TestData;
import testPage.Login;
import utilPack.AppUtilities.SKUTypeResult;
import objectRepository.CustomerCreation_Obj;



public class ProductUtilities extends BasePge {

	//	protected WebDriver driver;
	//	Assert asrt;
	//	JavascriptExecutor js;
	BasePge base;
	AppUtilities appUtils;
	Login login;
	//JavascriptExecutor js;

	LoginPage_Obj LoginPageObj= new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj=new NormalSalesReturnGoldSilverReturnSaleCounter_Obj(); 
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSaleDiamondandPlatinum_Obj NormalSaleDiamondandPlatinumObj =new NormalSaleDiamondandPlatinum_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	GppOpeningAndCollectionMultiplePlans_Obj GppOpeningAndCollectionMultiplePlansObj = new GppOpeningAndCollectionMultiplePlans_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj=new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj ();
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj = new StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj();

	public ProductUtilities(WebDriver driver) {
		super(driver);
		base   = new BasePge(driver);
		login  = new Login(driver);
		appUtils = new AppUtilities(base);
	}


	///<summary>
	/// Method to get required keral sku in range from list
	///Author : Chandana Babu
	/// </summary>
	public Map<String,List<String>> GetRequiredKeralSkuInRange(List<String> SKUs) throws InterruptedException
	{
		List<String> RequiredKeralSkusinRange1 = new ArrayList<>();
		List<String> RequiredKeralSkusinRange2 = new ArrayList<>();
		List<String> RequiredKeralSkusinRange3 = new ArrayList<>();
		Map<String,List<String>> RequiredKeralSkus = new LinkedHashMap<>();
		if (SKUs.isEmpty()) {
			System.out.println("SKU List is empty - exiting test early");
		} else {

			for (int i = 0; i < SKUs.size(); i++) {

				appUtils.HamBurgerButtonClick("iconShop");

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
				Thread.sleep(3000);
				base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SKUs.get(i));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
				if (base.isPresent(LoginPageObj.Edt_AlertText("Item details"))) {
					String TextWastageQty = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Wastage Qty"));
					double WastageQty = Double.parseDouble(TextWastageQty);
					if (WastageQty>= 2.5 && WastageQty <= 7.9) {
						RequiredKeralSkusinRange1.add(SKUs.get(i));
					}
					if (WastageQty>= 7.91 && WastageQty <= 9.9) {
						RequiredKeralSkusinRange2.add(SKUs.get(i));
					}
					if (WastageQty>= 9.91 && WastageQty <= 11.9) {
						RequiredKeralSkusinRange3.add(SKUs.get(i));
					}
				}
				else {
					base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
				}
			}
			RequiredKeralSkus.put("Range:2.5-7.9", RequiredKeralSkusinRange1);
			RequiredKeralSkus.put("Range:7.91-9.9", RequiredKeralSkusinRange2);
			RequiredKeralSkus.put("Range:9.91-11.9", RequiredKeralSkusinRange3);
		}

		System.out.println("RequiredKeralSkusInRange : "+RequiredKeralSkus);
		return RequiredKeralSkus;
	}

	///<summary>
	/// Method to get Making Rate and Piece rate items from product search page
	/// Author : Anagha, Chandana Babu
	/// </summary>	
	public SKUTypeResult GetMakingAndPcsRateItems(String TransferType, String FromCounter, String MetalType, String Item, double RequiredQty) throws InterruptedException 
	{

		//Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Counter Transfer"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		List<String> SeenSKUs = new  ArrayList<>();
		List<String> AllProductNames =new ArrayList<>();
		List<String> Quantities =new ArrayList<>();

		List<WebElement> AllSKUs = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int SameCount = 0;
		int ScrollPauseTime = 400;

		int PreviousTotalCount = 0;

		while (true) {
			List<WebElement> CurrentRows = driver.findElements(By.xpath("//div[@class='win-container']"));
			int NewItems = 0;

			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;

				String[] Parts = RowText.split("\\n");
				if (Parts.length < 6) continue;

				String SKU = Parts[0].trim();
				String ProductName = Parts[4].trim();
				String Quantity = Parts[6].trim();

				if (!SeenSKUs.contains(SKU)) {
					SeenSKUs.add(SKU);
					AllProductNames.add(ProductName);
					Quantities.add(Quantity);
					AllSKUs.add(Row);
					NewItems++;
				}
			}
			if (NewItems == 0) {
				SameCount++;
			} else {
				SameCount = 0;
			}

			if (SameCount >= 5) {
				break;
			}
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(ScrollPauseTime);
		}
		List<String> PcsRateSKUs = new ArrayList<>();
		List<String> MakingRateSKUs = new ArrayList<>();
		for (int i = 0; i < SeenSKUs.size(); i++) {
			if (Item.equals("Gold")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				//String ProductName = AllProductNames.get(i);
				if (QtyNum <= RequiredQty  ) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > RequiredQty ) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
			if (Item.equals("Silver")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				//String ProductName = AllProductNames.get(i);
				if (QtyNum <= RequiredQty ) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > RequiredQty ) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
			if (Item.equals("Diamond")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				//String ProductName = AllProductNames.get(i);
				if (QtyNum <= RequiredQty ) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > RequiredQty ) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
			if (Item.equals("Platinum")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				if (QtyNum <= RequiredQty ) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > RequiredQty ) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
		}
		return new SKUTypeResult(PcsRateSKUs, MakingRateSKUs);
	}

	public class SKUTypeResult 
	{
		public List<String> PcsRateSKU;
		public List<String> MakingRateSKU;

		public SKUTypeResult(List<String> pcsRateSKUs, List<String> makingRateSKUs) 
		{
			this.PcsRateSKU = pcsRateSKUs;
			this.MakingRateSKU = makingRateSKUs;
		}
	}

	///<summary>
	/// Method to get required pcs rate items from list
	///Author : Chandana Babu
	/// </summary>
	public List<String> GetRequiredPcsRateItems(List<String> SKUs) throws InterruptedException
	{
		List<String> RequiredPcsRateSKUs = new ArrayList<>();
		if (SKUs.isEmpty()) {
			System.out.println("SKU List is empty - exiting test early");
		} else {

			for (int i = 0; i < SKUs.size(); i++) {

				appUtils.HamBurgerButtonClick("iconShop");

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
				Thread.sleep(3000);
				base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SKUs.get(i));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
				String TextMakingRate = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
				double MakingRate = Double.parseDouble(TextMakingRate);
				String TextMakingValue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
				double MakingValue = Double.parseDouble(TextMakingValue);
				String TextMakingType = base.getTheSelectedTextInDropDown(NormalSaleGoldAndSilverObj.Sel_DepositType("makingTypeOptions"));
				if (TextMakingType.equals("Total")||TextMakingType.equals("Pcs") && MakingRate==MakingValue) {
					System.out.println("ValidSku_"+i+1+":"+SKUs.get(i));
					RequiredPcsRateSKUs.add(SKUs.get(i));
				}
			}
		}
		return RequiredPcsRateSKUs;
	}

	///<summary>
	/// Method to get required pcs rate items from list
	/// Author : Chandana Babu
	/// </summary>
	public Map<String, List<String>> GetRequiredPlatinumItems(List<String> SKUs) throws InterruptedException {
		List<String> DiamondWithRPandRGSku = new ArrayList<>();
		List<String> DualTonePlatinumSku = new ArrayList<>();
		List<String> NormalPlatinumSku = new ArrayList<>();
		List<String> DiamondWithRP = new ArrayList<>();

		Map<String, List<String>> RequiredPlaitinumSKUs = new HashMap<>();

		if (SKUs.isEmpty()) {
			System.out.println("SKU List is empty - exiting test early");
			return RequiredPlaitinumSKUs;
		}

		for (String sku : SKUs) {
			// Navigate to Transaction page
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("headerSplitViewToggleButton expandedNavButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
			Thread.sleep(1000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h5 textLeft centerY", "Transaction"));

			// Add SKU to cart
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
			Thread.sleep(3000);
			base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), sku);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
			base.setZoom(driver, 40);
			base.scrollToElement(By.xpath("//div[@class='dataListLine']//div[contains(@aria-label,'Item Id')]"));
			// Get item types from cart
			List<String> items = base.GetElementTexts(By.xpath("//div[@class='dataListLine']//div[contains(@aria-label,'Item Id')]"));

			// Categorization based on item combinations
			boolean hasRP = items.contains("RP");
			boolean hasRG = items.contains("RG");
			boolean hasDiamond = items.contains("Diamond");
			boolean hasDiamond1 = items.contains("DIAMOND");
			boolean hasDiamond2 = items.contains("diamond");
			boolean hasAnyDiamond = hasDiamond || hasDiamond1 || hasDiamond2;

			if (hasRP && hasRG && hasAnyDiamond) {
				DiamondWithRPandRGSku.add(sku);     // All 3 present
			} else if (hasRP && hasAnyDiamond && !hasRG) {
				DiamondWithRP.add(sku);             // RP and Diamond only
			} else if (hasRP && hasRG && !hasAnyDiamond) {
				DualTonePlatinumSku.add(sku);       // RP and RG only
			} else if (hasRP && !hasRG && !hasAnyDiamond) {
				NormalPlatinumSku.add(sku);         // Only RP present
			}
		}
		// Collect all categories into the map
		RequiredPlaitinumSKUs.put("DiamondWithRPandRG", DiamondWithRPandRGSku);
		RequiredPlaitinumSKUs.put("DiamondWithRP", DiamondWithRP);
		RequiredPlaitinumSKUs.put("DualTonePlatinumSku", DualTonePlatinumSku);
		RequiredPlaitinumSKUs.put("NormalPlatinum", NormalPlatinumSku);

		System.out.println(RequiredPlaitinumSKUs);
		return RequiredPlaitinumSKUs;
	}

	///<summary>
	/// Method to get Sku Count from product search page
	/// Author : Chandana Babu
	/// </summary>	
	public List<Integer> GetSkuCount(String TransferType, String FromCounter, String MetalType) throws InterruptedException 
	{
		//Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		List<String> SeenSKUs = new  ArrayList<>();
		List<String> AllProductNames =new ArrayList<>();
		List<String> Quantities =new ArrayList<>();
		List<WebElement> AllSKUs = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int SameCount = 0;
		int ScrollPauseTime = 400;
		int PreviousTotalCount = 0;
		while (true) {
			List<WebElement> CurrentRows = driver.findElements(By.xpath("//div[@class='win-container']"));
			int NewItems = 0;

			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;

				String[] Parts = RowText.split("\\n");
				if (Parts.length < 6) continue;

				String SKU = Parts[0].trim();
				String ProductName = Parts[4].trim();
				String Quantity = Parts[6].trim();

				if (!SeenSKUs.contains(SKU)) {
					SeenSKUs.add(SKU);
					AllProductNames.add(ProductName);
					Quantities.add(Quantity);
					AllSKUs.add(Row);
					NewItems++;
				}
			}
			if (NewItems == 0) {
				SameCount++;
			} else {
				SameCount = 0;
			}

			if (SameCount >= 5) {
				break;
			}
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(ScrollPauseTime);
		}
		int SKU = SeenSKUs.size();
		int SKUName = AllProductNames.size();
		List<Integer>Count = new ArrayList<>();
		Count.add(SKU);
		//Count.add(SKUName);
		System.out.println(FromCounter+" Sku Count: "+Count.get(0));
		return Count;
	}

	///<summary>
	/// Method to get all keral sku 
	///Author : Aiswarya
	/// </summary>

	public List<String> GetAllKeralSku() throws InterruptedException
	{

		// Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TransferType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), "Inter");

		// Select From Counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FromCounter']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), "Gold");

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='MetalType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), "Gold");

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Init data collections
		List<String> SeenSkus = new ArrayList<>();
		List<String> MatchingSkus = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrollPauseTime = 1000;
		int maxTriesWithoutNew = 60;
		int triesWithoutNew = 0;

		while (triesWithoutNew < maxTriesWithoutNew) {
			List<WebElement> CurrentRows = driver.findElements(By.xpath("//div[@class='win-container']"));
			int NewItems = 0;
			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;
				String[] Parts = RowText.split("\\n");
				if (Parts.length < 7) continue;

				String SKU = Parts[0].trim();
				String ItemName = Parts[2].trim();

				if (!SeenSkus.contains(SKU)) {
					SeenSkus.add(SKU);
					String cleanedItemName = ItemName.trim();

					// Match if name contains "VZ" or ends with "(VZ)"
					if (cleanedItemName.contains("Keral")) {
						MatchingSkus.add(SKU);
						System.out.println("Matching Item: " + cleanedItemName + " | SKU: " + SKU);
						NewItems++;
					}
				}
			}

			if (NewItems == 0) triesWithoutNew++;
			else triesWithoutNew = 0;

			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(scrollPauseTime);
		}

		System.out.println("--------------------------------------------------");
		System.out.println("Total Matching SKUs with Keral : " + MatchingSkus.size());
		//System.out.println("Matching SKU List: " + MatchingSkus);
		System.out.println("--------------------------------------------------");
		return MatchingSkus;

	}

	///<summary>
	/// Method to get required keral sku within package discount range 
	///Author : Chandana Babu
	/// </summary>
	public List<String> GetRequiredKeralSku(List<String> SKUs ) throws InterruptedException
	{
		List<String> RequiredKeralSKUs = new ArrayList<>();
		if (SKUs.isEmpty()) {
			System.out.println("SKU List is empty - exiting test early");
		} else {

			for (int i = 0; i < SKUs.size(); i++) {

				appUtils.HamBurgerButtonClick("iconShop");

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
				Thread.sleep(3000);
				base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SKUs.get(i));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
				String TextWastageQty = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Wastage Qty"));
				double WastageQty = Double.parseDouble(TextWastageQty);
				if (WastageQty>= 2.5 && WastageQty <= 11.9) {
					RequiredKeralSKUs.add(SKUs.get(i));
				}
			}
		}
		System.out.println("Number of valid keral sku: "+RequiredKeralSKUs.size());
		System.out.println("RequiredKeralSKUs: "+RequiredKeralSKUs);
		return RequiredKeralSKUs;
	}

	///<summary>
	/// Method to get all Viraz sku 
	///Author : Aiswarya
	/// </summary>
	public List<String> GetRequiredVirazSku() throws InterruptedException
	{
		// Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TransferType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), "Inter");

		// Select From Counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FromCounter']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), "Uncut");

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='MetalType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), "Gold");

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Init data collections
		List<String> SeenSkus = new ArrayList<>();
		List<String> MatchingSkus = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int scrollPauseTime = 400;
		int maxTriesWithoutNew = 10;   // increased a bit for stability
		int triesWithoutNew = 0;

		while (triesWithoutNew < maxTriesWithoutNew) {

			List<WebElement> CurrentRows = driver.findElements(By.xpath("//div[contains(@class,'win-container')]"));
			int NewItems = 0;

			for (WebElement Row : CurrentRows) {

				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;

				String[] Parts = RowText.split("\\n");
				if (Parts.length < 3) continue;   // only need 0 and 2

				String SKU = Parts[0].trim();
				String ItemName = Parts[2].trim();

				if (!SeenSkus.contains(SKU)) {
					SeenSkus.add(SKU);

					String cleanedItemName = ItemName.trim();

					// Match if name contains VZ or ends with (VZ)
					if (cleanedItemName.toUpperCase().contains("VZ") || cleanedItemName.toUpperCase().endsWith("(VZ)")) {
						MatchingSkus.add(SKU);
						System.out.println("Matching Item: " + cleanedItemName + " | SKU: " + SKU);
					}

					NewItems++;
				}
			}

			if (NewItems == 0) triesWithoutNew++;
			else triesWithoutNew = 0;

			// ✅ BEST SCROLL: last row into center (loads next set)
			js.executeScript(
					"let rows = document.querySelectorAll('.win-container');" +
							"if(rows.length > 0) {" +
							"  rows[rows.length - 1].scrollIntoView({block:'center'});" +
							"}"
					);

			Thread.sleep(scrollPauseTime);
		}

		System.out.println("--------------------------------------------------");
		System.out.println("Total Matching Viraz SKUs: " + MatchingSkus.size());
		System.out.println("Viraz Sku List: " + MatchingSkus);
		String finalFormattedList = "[\"" + String.join("\", \"", MatchingSkus) + "\"]";
		System.out.println("Final Matching SKUs: " + finalFormattedList);
		System.out.println("--------------------------------------------------");

		return MatchingSkus;
	}


	/*
	public List<String> GetRequiredVirazSku() throws InterruptedException

	{
		// Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TransferType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), "Inter");

		// Select From Counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FromCounter']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), "Uncut");

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='MetalType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), "Gold");

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Init data collections
		List<String> SeenSkus = new ArrayList<>();
		List<String> MatchingSkus = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrollPauseTime = 400;
		int maxTriesWithoutNew = 3;
		int triesWithoutNew = 0;

		while (triesWithoutNew < maxTriesWithoutNew) {
			List<WebElement> CurrentRows = driver.findElements(By.xpath("//div[@class='win-container']"));
			int NewItems = 0;
			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;
				String[] Parts = RowText.split("\\n");
				if (Parts.length < 7) continue;

				String SKU = Parts[0].trim();
				String ItemName = Parts[2].trim();

				if (!SeenSkus.contains(SKU)) {
					SeenSkus.add(SKU);
					String cleanedItemName = ItemName.trim();

					// Match if name contains "VZ" or ends with "(VZ)"
					if (cleanedItemName.toUpperCase().contains("VZ") || cleanedItemName.endsWith("(VZ)")) {
						MatchingSkus.add(SKU);
						System.out.println("Matching Item: " + cleanedItemName + " | SKU: " + SKU);
						NewItems++;
					}
				}
			}

			if (NewItems == 0) triesWithoutNew++;
			else triesWithoutNew = 0;

			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(scrollPauseTime);
		}

		System.out.println("--------------------------------------------------");
		System.out.println("Total Matching Viraz SKUs: " + MatchingSkus.size());
		System.out.println("Viraz Sku List: " + MatchingSkus);
		String finalFormattedList = "[\"" + String.join("\", \"", MatchingSkus) + "\"]";
		System.out.println("Final Matching SKUs: " + finalFormattedList);
		System.out.println("--------------------------------------------------");

		return MatchingSkus;
	}
	 */

	///<summary>
	/// Method to get all Silver article
	///Author : Sangeetha M S
	/// </summary>
	public List<String> GetRequiredSilverArticleSku() throws InterruptedException
	{
		// Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TransferType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), "Inter");

		// Select From Counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FromCounter']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), "Silver");

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='MetalType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), "Silver");

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		List<String> SeenSkus = new ArrayList<>();
		List<String> MatchingSkus = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrollPauseTime = 700;
		int previousRowCount = 0;

		while (true)
		{
			List<WebElement> currentRows =driver.findElements(By.xpath("//div[@class='win-container']"));
			if (currentRows.size() == previousRowCount) 
			{
				break;
			}
			previousRowCount = currentRows.size();
			for (WebElement row : currentRows) 
			{
				String rowText = row.getText().trim();
				if (rowText.isEmpty()) 
					continue;
				String[] parts = rowText.split("\\n");
				if (parts.length < 4) 
					continue;
				String sku = parts[0].trim();
				String category = parts[3].trim();
				if (!SeenSkus.contains(sku)) 
				{
					SeenSkus.add(sku);
					if (category.equalsIgnoreCase("Silver Article"))
					{
						MatchingSkus.add(sku);
					}
				}
			}
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(scrollPauseTime);
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Total Matching Silver Article SKUs: " + MatchingSkus.size());
		System.out.println("Silver Article SKU List: " + MatchingSkus);
		String finalFormattedList ="[\"" + String.join("\", \"", MatchingSkus) + "\"]";
		System.out.println("Final Matching SKUs: " + finalFormattedList);
		System.out.println("--------------------------------------------------");
		return MatchingSkus;
	}
	
	
	///<summary>
	/// Method to get all GoldCoinSku
	///Author : Bharath & Nandagopan	
	/// </summary>
	public List<String> GetRequiredGoldCoinSku() throws InterruptedException
	{
		// Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TransferType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), "Inter");

		// Select From Counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FromCounter']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), "Gold");

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='MetalType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), "Gold");

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		List<String> SeenSkus = new ArrayList<>();
		List<String> MatchingSkus = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrollPauseTime = 700;
		int previousRowCount = 0;

		while (true)
		{
			List<WebElement> currentRows =driver.findElements(By.xpath("//div[@class='win-container']"));
			if (currentRows.size() == previousRowCount) 
			{
				break;
			}
			previousRowCount = currentRows.size();
			for (WebElement row : currentRows) 
			{
				String rowText = row.getText().trim();
				if (rowText.isEmpty()) 
					continue;
				String[] parts = rowText.split("\\n");
				if (parts.length < 4) 
					continue;
				String sku = parts[0].trim();
				String Product = parts[4].trim();
				if (!SeenSkus.contains(sku)) 
				{
					SeenSkus.add(sku);
					if (Product.equalsIgnoreCase("Coin"))
					{
						MatchingSkus.add(sku);
					}
				}
			}
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(scrollPauseTime);
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Total Matching Gold Coins SKUs: " + MatchingSkus.size());
		System.out.println("Gold Coins SKU List: " + MatchingSkus);
		String finalFormattedList ="[\"" + String.join("\", \"", MatchingSkus) + "\"]";
		System.out.println("Final Matching SKUs: " + finalFormattedList);
		System.out.println("--------------------------------------------------");
		return MatchingSkus;
	}

}