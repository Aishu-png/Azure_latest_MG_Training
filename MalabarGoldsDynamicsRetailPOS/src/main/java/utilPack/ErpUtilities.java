package utilPack;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Supplier;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Obj;
import objectRepository.ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj;
import objectRepository.BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactions_Obj;
import objectRepository.PdcEntryExecution_Obj;
import objectRepository.CheckGPPRefundBenefitWithMultipleCategoryItem_Obj;
import testData.CheckGPPRefundBenefitWithMultipleCategoryItem_TestData;
import objectRepository.StatementPosting_Obj;
import objectRepository.StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj;
import testData.CommonData;
import testData.Utility_TestData;
import testPage.Login;

public class ErpUtilities {

	protected WebDriver driver;
	protected BasePge base;
	protected PdfUtilities pdfUtils;
	protected AppUtilities appUtils;
	protected Login login;
	Assert asrt;
	JavascriptExecutor js;

	LoginPage_Obj LoginPageObj= new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj= new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj=new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj=new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactions_Obj OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactionsObj = new OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactions_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRTGSReceivedObj =new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	StatementPosting_Obj StatementPostingObj = new StatementPosting_Obj();
	PdcEntryExecution_Obj PdcEntryExecutionObj = new PdcEntryExecution_Obj();
	CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Obj CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj = new CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj  InvoiceAdvanceWithPaymentMethodRtgsReceivedObj = new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	CheckGPPRefundBenefitWithMultipleCategoryItem_TestData CheckGPPRefundBenefitWithMultipleCategoryItemTestData =new CheckGPPRefundBenefitWithMultipleCategoryItem_TestData();
	CheckGPPRefundBenefitWithMultipleCategoryItem_Obj CheckGPPRefundBenefitWithMultipleCategoryItemObj = new CheckGPPRefundBenefitWithMultipleCategoryItem_Obj();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj=new BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj();
	ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj=new ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj();
	StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj = new StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj();


	public ErpUtilities(BasePge base) {
		this.base   = base;
		this.driver = base.driver;
		this.login  = new Login(this.driver);
		this.pdfUtils = new PdfUtilities(this.base);
		this.appUtils = new AppUtilities(this.base);
	}

	/// <summary>
	/// To Set metal rate in ERP for Gold 24k,22k,18K
	/// Author : Jhoncy,Vishnu
	/// </summary>
	public String SetMetalRateInERP(String SkuGoldRateReturn, String SkuPurityReturn) throws Exception
	{
		double OriginalRate = Double.parseDouble(SkuGoldRateReturn);
		double Purity = 0;

		if (SkuPurityReturn.contains("24")) {
			Purity = 24;
		} else if (SkuPurityReturn.contains("22")) {
			Purity = 22;
		} else if (SkuPurityReturn.contains("18")) {
			Purity = 18;
		} else {
			System.out.println("Unsupported purity: " + SkuPurityReturn);
			Purity = 24; 
		}

		//double GoldRateAs24K = (OriginalRate / Purity) * 24;
		double GoldRateAs24K;
		if (Purity == 24) {
			GoldRateAs24K = OriginalRate; // already 24K, no conversion needed
		} else {
			GoldRateAs24K = (OriginalRate / Purity) * 24;
		}
		long RoundedMetalRate = Math.round(GoldRateAs24K + 50);
		String ConvertedMetalRate = String.valueOf(RoundedMetalRate);
		System.out.println("Converted Metal Rate: " + ConvertedMetalRate);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		//Step 1.Go to ERP of that specific Legal Entity
		String OriginalWindow = driver.getWindowHandle();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		//Step 2.Search Metal rate
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		Thread.sleep(5000);		
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMetalRate);
		base.setZoom(driver, 85);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step 3. Click on New
		Thread.sleep(5000);
		//base.isExists(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol")));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));

		//Step 4.Accounted under as 'Gold'
		//Step 5.Store/Non-store as 'Store'
		//Step 6.Select the required warehouse
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"));		
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"), UtilityTestData.WarehouseId);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.WarehouseId,"Warehouse"));

		//Step 7.Select the Configuration as "24k"
		//Step 8.Enter the 24k Board Rate in Sales, click Enter
		base.buttonClick(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"));
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"), UtilityTestData.Purity24);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.Purity24,"Configuration"));
		Thread.sleep(10000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"));
		Thread.sleep(10000);
		base.clearData((NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate")));
		base.setData(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), ConvertedMetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), "ENTER");

		//Step 9.Click on OK
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Edt_Alert("OK"));

		//Step 10. Click on New
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol")));
		Thread.sleep(5000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));

		//Step 11.Accounted under as 'Gold'
		//Step 12.Store/Non-store as 'Store'
		//Step 13.Select the required warehouse
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"), UtilityTestData.WarehouseId);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.WarehouseId,"Warehouse"));

		//Step 14.Select the Configuration as "22k"
		//Step 15.Enter the 24k Board Rate in Sales, click Enter
		base.ClickCondition(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"), UtilityTestData.Purity22);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.Purity22,"Configuration"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"));
		Thread.sleep(5000);
		base.clearData((NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate")));
		base.setData(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), ConvertedMetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), "ENTER");

		//Step 16.Click on OK
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		//Step 17. Click on New
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol")));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));

		//Step 18.Accounted under as 'Gold'
		//Step 19.Store/Non-store as 'Store'
		//Step 20.Select the required warehouse
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"), UtilityTestData.WarehouseId);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.WarehouseId,"Warehouse"));
		Thread.sleep(5000);

		//Step 21.Select the Configuration as "18K"
		//Step 22.Enter the 24k Board Rate in Sales, click Enter
		base.ClickCondition(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"));
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"), UtilityTestData.Erp_Purity18);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.Erp_Purity18,"Configuration"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"));
		Thread.sleep(5000);
		base.clearData((NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate")));
		base.setData(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), ConvertedMetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), "ENTER");

		//Step 23.Click on OK
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		//Step 17. Click on New
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol")));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));

		//Step 18.Accounted under as 'Gold'
		//Step 19.Store/Non-store as 'Store'
		//Step 20.Select the required warehouse
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("InventLocationId_input","Warehouse"), UtilityTestData.WarehouseId);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.WarehouseId,"Warehouse"));
		Thread.sleep(5000);

		//Step 21.Select the Configuration as "99.9"
		//Step 22.Enter the 99.9k Board Rate in Sales, click Enter
		base.ClickCondition(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"));
		base.setData(NormalSaleGoldAndSilverObj.Erp_Config("EcoResConfiguraionName","Configuration"), UtilityTestData.Erp_Purity99);
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"));
		Thread.sleep(5000);
		base.clearData((NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate")));
		base.setData(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), ConvertedMetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate"), "ENTER");

		//Step 23.Click on OK
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		base.newWindow(2);
		driver.get(UtilityTestData.ERPURL);

		//step 24.In ERP, Go to Distribution Schedule
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
		base.isExists(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(6000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(2000);

		//Step 25.Filter the job ""N-METALRATE"" and click on ""Run now""
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input")));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), UtilityTestData.NmetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);
		for(int i=0;i<5;i++) {
			base.ClickCondition(LoginPageObj.Edt_Alert("Run now"));
			Thread.sleep(5000);
			if(base.isPresent(LoginPageObj.Edt_Alert("OK"))) {
				break;
			}
		}
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		base.newWindow(3);
		driver.get(UtilityTestData.ERPURL);

		boolean jobEnded = false;
		int maxAttempts = 8; // 1 normal + 1 retry

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Step 26: Search My Batch Job
				wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
				base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 20);
				Thread.sleep(1000);
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
				Thread.sleep(5000);

				// Step 27: Sort by "Scheduled start date/time"
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time")));
				By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Scroll horizontally
				List<WebElement> Scrollbars =driver.findElements(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
				if (!Scrollbars.isEmpty()) {
					WebElement Scrollbar = Scrollbars.get(0);
					Actions actions = new Actions(driver);
					actions.clickAndHold(Scrollbar).moveByOffset(250, 0).release().perform();
					Thread.sleep(10000);
				} else {
					System.out.println("Horizontal scrollbar not present. Skipping scroll.");
				}

				// Filter by Company Accounts
				appUtils.ClickWithRetry(
						NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Company accounts"),
						NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_BatchJob_Company_Grid"),
						3,
						2
						);
				base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_BatchJob_Company_Grid"), UtilityTestData.TerminalName);
				base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__BatchJob_Company_Grid_ApplyFilters_label", "Apply"));

				// Step 28: Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				jobEnded = true;
				break; 

			} catch (StaleElementReferenceException stale) {
				System.out.println("Attempt " + attempt + ": Stale element. Retrying after refresh...");
				if (attempt < maxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Stale element not resolved after retries", stale);
				}
			} catch (TimeoutException timeout) {
				System.out.println("Attempt " + attempt + ": Timeout while waiting for 'Ended' job status.");
				if (attempt < maxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
		}

		driver.switchTo().window(OriginalWindow);
		return ConvertedMetalRate;

	}

	///<summary>
	/// Method To get invoice no, Total Netweight, Line Count, Net Total... after normal sale
	/// Author : Vishnu Manoj K
	///</summary>
	public Map<String, Object> GetInvoiceDetailsAfterNormalSale(String CustomerNo, List<String> CounterTransfer) throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		Thread.sleep(5000);

		// Step 2: Navigate to the Transaction page
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer and click in add to sale button
		appUtils.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);

		// Step 4: Scan Multiple SKUs
		List<String> AllSkuList = new ArrayList<>();
		for (int J = 0; J + 2 < CounterTransfer.size(); J += 3) {
			List<String> RequiredSkuList = appUtils.SelectMultipleSKUs(
					Integer.parseInt(CounterTransfer.get(J)), "Inter", CounterTransfer.get(J + 1), CounterTransfer.get(J + 2));
			AllSkuList.addAll(RequiredSkuList);
			appUtils.HamBurgerButtonClick("iconShop");
		}

		// Step 5: Extract SKU table data
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int I = 0; I < AllSkuList.size(); I++) {
			appUtils.SKUIngridentTableValues(AllSkuList.get(I), I + 1, ScannedSkuDataMap);
		}

		// Step 6: Extract required values
		double TotalNetWeight = 0.0;
		String FirstSkuPurity = "N/A";
		String FirstSkuGoldRate = "Not Found";
		boolean FoundRg = false;

		double TotalRgWeightInFirstPurity = 0.0;
		double FirstPurity = 0.0;

		Map<String, String> SkuWiseData = new LinkedHashMap<>();

		for (int Index = 0; Index < AllSkuList.size(); Index++) {
			int SkuNumber = Index + 1;

			String NetWeightStr = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_NetWeight");
			try {
				double NetWeight = Double.parseDouble(NetWeightStr);
				TotalNetWeight += NetWeight;
			} catch (Exception Ex) {
				System.out.println(" - Error parsing Net Weight for SKU " + SkuNumber + ": " + NetWeightStr);
			}
			SkuWiseData.put("SKU_" + SkuNumber + "_NetWeight", NetWeightStr);

			String GrossWeight = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_GrossWeight", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_GrossWeight", GrossWeight);

			String StoneWeight = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_StoneWeight", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_StoneWeight", StoneWeight);

			String SkuTotal = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_Total", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_Total", SkuTotal);

			String RgWeightStr = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_TotalRGWeight", "0.0");
			double RgWeight = 0.0;
			try {
				RgWeight = Double.parseDouble(RgWeightStr);
			} catch (Exception Ex) {
				System.out.println(" Invalid RG Weight for SKU_" + SkuNumber + ": " + RgWeightStr);
				continue;
			}

			if (RgWeight == 0.0) continue;

			String ThisPurityStr = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_Purity", "").replaceAll("(?i)k", "").trim();
			double ThisPurity = 0.0;
			try {
				ThisPurity = Double.parseDouble(ThisPurityStr);
			} catch (Exception Ex) {
				System.out.println("Invalid Purity for SKU_" + SkuNumber + ": " + ThisPurityStr);
				continue;
			}

			if (!FoundRg) {
				FirstPurity = ThisPurity;
				FirstSkuPurity = ThisPurityStr;

				for (String Key : ScannedSkuDataMap.keySet()) {
					if (Key.startsWith("SKU" + SkuNumber + "_ITEM") && Key.endsWith("_ITEMID")) {
						String ItemKeyPrefix = Key.replace("_ITEMID", "");
						String ItemId = ScannedSkuDataMap.get(Key);
						if ("RG".equalsIgnoreCase(ItemId)) {
							FirstSkuGoldRate = ScannedSkuDataMap.getOrDefault(ItemKeyPrefix + "_RATE", "Not Found");
							break;
						}
					}
				}
				FoundRg = true;
			}

			double ThisSkuGoldRate = 0.0;
			for (String Key : ScannedSkuDataMap.keySet()) {
				if (Key.startsWith("SKU" + SkuNumber + "_ITEM") && Key.endsWith("_ITEMID")) {
					String ItemKeyPrefix = Key.replace("_ITEMID", "");
					String ItemId = ScannedSkuDataMap.get(Key);
					if ("RG".equalsIgnoreCase(ItemId)) {
						try {
							ThisSkuGoldRate = Double.parseDouble(
									ScannedSkuDataMap.getOrDefault(ItemKeyPrefix + "_RATE", "0.0")
									.replaceAll("[^0-9.]", ""));
						} catch (Exception Ex) {
							System.out.println(" Invalid RATE for SKU_" + SkuNumber + ": " + ScannedSkuDataMap.get(ItemKeyPrefix + "_RATE"));
						}
						break;
					}
				}
			}

			double FirstGoldRate = 0.0;
			try {
				FirstGoldRate = Double.parseDouble(FirstSkuGoldRate.replaceAll("[^0-9.]", ""));
			} catch (Exception Ex) {
				System.out.println(" Invalid First RG Rate: " + FirstSkuGoldRate);
				continue;
			}

			double ConvertedRgWeight = 0.0;
			if (FirstPurity == ThisPurity && FirstGoldRate == ThisSkuGoldRate) {
				ConvertedRgWeight = RgWeight;
			} else {
				ConvertedRgWeight = (RgWeight * ThisSkuGoldRate) / (FirstGoldRate);
				ConvertedRgWeight = Math.round(ConvertedRgWeight * 1000.0) / 1000.0;
			}

			TotalRgWeightInFirstPurity += ConvertedRgWeight;

			System.out.println("SKU_" + SkuNumber + " RG Weight = " + RgWeight +
					", Purity = " + ThisPurity + ", RG Rate = " + ThisSkuGoldRate +
					" => Converted to First Purity (" + FirstPurity + "K @ " + FirstGoldRate +
					") = " + ConvertedRgWeight);
		}

		System.out.println(" Total RG Weight Converted to First Purity (" + FirstPurity + "K): " + TotalRgWeightInFirstPurity);

		// Step 7: Finalize transaction
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNewNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");

		appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName;
		String InvoiceNo = pdfUtils.ExtractReceiptNoFrmSaleInvoice(TaxInvoicePath);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Step 8: Collect returned product names
		List<String> ReturnedProducts = new ArrayList<>();
		for (String Key : ScannedSkuDataMap.keySet()) {
			if (Key.matches("SKU\\d+_ITEM\\d+_ITEMNAME")) {
				ReturnedProducts.add(ScannedSkuDataMap.get(Key));
			}
		}

		// Step 9: Return all collected data
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		ResultMap.put("InvoiceNo", InvoiceNo);
		ResultMap.put("TotalNetWeight", String.format("%.3f", TotalNetWeight));
		ResultMap.put("TotalRgWeight", String.format("%.3f", TotalRgWeightInFirstPurity));
		ResultMap.put("FirstSkuPurity", FirstSkuPurity);
		ResultMap.put("FirstSkuGoldRate", FirstSkuGoldRate);
		ResultMap.put("SKUWiseDetails", SkuWiseData);
		ResultMap.put("SubTotal", SubTotal);
		ResultMap.put("Discount", Discount);
		ResultMap.put("TotalGrossWeight", TotalGrossWeight);
		ResultMap.put("Tax", Tax);
		ResultMap.put("TotalAmount", TotalAmount);
		ResultMap.put("NetTotal", NetTotal);
		ResultMap.put("LinesCount", LinesCount);
		ResultMap.put("ScannedSKUDataMap", ScannedSkuDataMap);
		ResultMap.put("TransactionDataLine", TransactionDataLine);
		ResultMap.put("AllSkuList",AllSkuList);
		return ResultMap;
	}

	/// <summary>
	/// Validates SKU-level financial metrics:
	/// 1. Recalculates ingredient CValue (Qty Rate) and matches with expected.
	/// 2. Verifies GrossWeight by summing ingredient quantities.
	/// 3. Validates MakingValue from NetWeight and MakingRate.
	/// 4. Calculates WastageAmount from NetWeight Rate WastageQty%.
	/// 5. Ensures Total = TotalCValue + Making or Wastage.
	/// 6. Asserts RG item gold rate matches expected based on RemainingReturnWeight logic.
	/// Author: VISHNU MANOJ K
	/// </summary>
	public void ResetRemainingReturnWeight() {
		RemainingReturnWeight = -1;
		FirstPurity = -1;
		FirstRate = -1;
	}
	private static double RemainingReturnWeight = -1;
	private static double FirstPurity = -1; 
	private static double FirstRate = -1; 
	public double ErpSkuIngredientItemCalculation(Map<String, String> SkuIngredientData, int SkuNumber,
			String TotalRgWeightReturn, String FirstSkuPurityReturn,
			String FirstSkuGoldRateReturn, String CurrentGoldRate24K) {

		System.out.println("====== Validating SKU_" + SkuNumber + " ======");

		double CurrentPurity = ParsePurity(SkuIngredientData.getOrDefault("SKU_" + SkuNumber + "_Purity", ""));

		if (FirstPurity < 0) {
			FirstPurity = ParsePurity(FirstSkuPurityReturn);
			FirstRate = ParseDouble(FirstSkuGoldRateReturn);
		}

		double CurrentFirstRate = (CurrentPurity != FirstPurity)
				? Round((FirstRate * CurrentPurity) / FirstPurity)
						: FirstRate;

		double Rate24K = ParseDouble(CurrentGoldRate24K);

		System.out.println("CurrentFirstRate: " + CurrentFirstRate + " FirstRate: " + FirstRate);
		System.out.println("CurrentPurity: " + CurrentPurity + " FirstPurity: " + FirstPurity + " Rate24K: " + Rate24K);			
		double AdjustedGoldRate = (CurrentPurity != 24.0)
				? (Rate24K * CurrentPurity / 24.0)
						: Rate24K;

		System.out.println("AdjustedGoldRate: " + AdjustedGoldRate);


		// Custom rounding logic
		double FloorValue = Math.floor(AdjustedGoldRate);
		double DecimalPart = AdjustedGoldRate - FloorValue;

		System.out.println("FloorValue: " + FloorValue);
		System.out.println("DecimalPart: " + DecimalPart);



		if (DecimalPart > 0.5) {
			AdjustedGoldRate = Math.ceil(AdjustedGoldRate);        // Round up

			System.out.println("AdjustedGoldRate if DecimalPart>0.5 " + AdjustedGoldRate);


		} else if (DecimalPart == 0.5) {
			AdjustedGoldRate = FloorValue + 0.5;                   // Keep 0.5

			System.out.println("AdjustedGoldRate if DecimalPart==0.5 " + AdjustedGoldRate);

		} else {
			AdjustedGoldRate = Math.floor(AdjustedGoldRate);       // Round down

			System.out.println("AdjustedGoldRate if DecimalPart<0.5 " + AdjustedGoldRate);

		}
		System.out.printf("Adjusted Gold Rate for %.1fK: %.2f\n", CurrentPurity, AdjustedGoldRate);

		if (RemainingReturnWeight < 0) {
			double RgWeight = ParseDouble(TotalRgWeightReturn);
			RemainingReturnWeight = (CurrentPurity != FirstPurity)
					? Round((RgWeight * FirstRate) / ((FirstRate * CurrentPurity) / FirstPurity))
							: RgWeight;

			System.out.println("Total RG Weight Converted = " + RemainingReturnWeight);
		} else {
			if (CurrentPurity != FirstPurity) {
				RemainingReturnWeight = (RemainingReturnWeight * FirstRate) / ((FirstRate * CurrentPurity) / FirstPurity);
				RemainingReturnWeight = Round(RemainingReturnWeight);
			}
			System.out.println("RemainingReturnWeight already used or calculated: " + RemainingReturnWeight);
		}

		double SumOfCValues = 0.0;
		double SumOfRgQty = 0.0;
		double Rate = 0.0;

		for (String Key : SkuIngredientData.keySet()) {
			if (Key.endsWith("_QTY")) {
				String Base = Key.replace("_QTY", "");
				double Qty = ParseDouble(SkuIngredientData.get(Base + "_QTY"));
				Rate = ParseDouble(SkuIngredientData.get(Base + "_RATE"));
				double ExpectedCValue = ParseDouble(SkuIngredientData.get(Base + "_CVALUE"));
				String ItemId = SkuIngredientData.getOrDefault(Base + "_ITEMID", "").trim().toUpperCase();
				double CalcCValue = 0.00;
				if (!ItemId.equalsIgnoreCase("POLA") && !ItemId.equalsIgnoreCase("THREAD")) {
					CalcCValue = Round(Qty * Rate);

					System.out.printf("CValue for %s: Qty=%.3f, Rate=%.2f, CalcValue=%.2f, ExpValue=%.2f\n",
							Base, Qty, Rate, CalcCValue, ExpectedCValue);

					asrt.assertEquals(CalcCValue, ExpectedCValue, 1.0, "Mismatch in CValue for " + Base);
				}
				//String ItemId = SkuIngredientData.getOrDefault(Base + "_ITEMID", "").trim().toUpperCase();
				if ("RG".equals(ItemId)) {
					SumOfCValues += Qty * Rate;
					SumOfRgQty += Qty;
				}
			}
		}

		System.out.println("Total RG CValue Sum: " + SumOfCValues);
		System.out.println("Total RG Qty Sum: " + SumOfRgQty);

		ValidateWeightsAndCharges(SkuIngredientData, SkuNumber, AdjustedGoldRate);

		double ExpectedGoldRate;

		if (RemainingReturnWeight >= SumOfRgQty) {
			ExpectedGoldRate = CurrentFirstRate;
			RemainingReturnWeight = RemainingReturnWeight - SumOfRgQty;
		} else if (RemainingReturnWeight > 0.0) {
			double ExcessQty = SumOfRgQty - RemainingReturnWeight;
			ExpectedGoldRate = ((ExcessQty * AdjustedGoldRate) + (RemainingReturnWeight * CurrentFirstRate)) / SumOfRgQty;
			RemainingReturnWeight = 0.0;
		} else {
			ExpectedGoldRate = AdjustedGoldRate;
		}

		ExpectedGoldRate = Round(ExpectedGoldRate);

		double RgRateInThisSku = GetRgRate(SkuIngredientData, SkuNumber);
		System.out.println("Assertion: RG Rate in SKU UI = " + RgRateInThisSku + " vs Expected = " + ExpectedGoldRate);

		asrt.assertEquals(RgRateInThisSku, ExpectedGoldRate, 1.0, "Mismatch in RG gold rate for SKU_" + SkuNumber);

		FirstRate = CurrentFirstRate;
		FirstPurity = CurrentPurity;


		System.out.println("DEBUG RG Rate Mismatch for SKU_" + SkuNumber);
		System.out.println("CurrentFirstRate = " + CurrentFirstRate);
		System.out.println("CurrentPurity = " + CurrentPurity);
		System.out.println("AdjustedGoldRate = " + AdjustedGoldRate);
		System.out.println("RemainingReturnWeight = " + RemainingReturnWeight);
		System.out.println("SumOfRgQty = " + SumOfRgQty);
		System.out.println("ExpectedGoldRate (calc) = " + ExpectedGoldRate);
		System.out.println("UI RG Rate = " + RgRateInThisSku);


		System.out.println("====== Validation Completed for SKU_" + SkuNumber + " ======\n");
		return CurrentPurity;
	}


	// --- Utility Methods ---
	private double ParsePurity(String PurityStr) {
		return ParseDouble(PurityStr.replaceAll("(?i)k", "").trim());
	}

	private double ParseDouble(String Str) {
		try {
			return Double.parseDouble(Str.replaceAll("[^\\d.]", ""));
		} catch (Exception e) {
			return 0.0;
		}
	}

	private double Round(double Value) {
		return Math.round(Value * 100.0) / 100.0;
	}

	private double GetRgRate(Map<String, String> Data, int SkuNum) {
		for (String Key : Data.keySet()) {
			if (Key.startsWith("SKU" + SkuNum + "_ITEM") && Key.endsWith("_ITEMID")
					&& "RG".equals(Data.get(Key).trim().toUpperCase())) {
				return ParseDouble(Data.getOrDefault(Key.replace("_ITEMID", "_RATE"), "0"));
			}
		}
		return -1.0;
	}

	private void ValidateWeightsAndCharges(Map<String, String> Data, int SkuNum, double AdjustedRate) {
		double Gross = 0.0;
		String QtyPattern  = "^SKU" + SkuNum + "_ITEM(\\d+)_QTY$";

		for (String Key : Data.keySet()) {

			Matcher M = Pattern.compile(QtyPattern).matcher(Key);
			if (M.find()) {
				int ItemIndex = Integer.parseInt(M.group(1));
				double Qty = ParseDouble(Data.get(Key));
				String Unit = Data.getOrDefault("SKU" + SkuNum + "_ITEM" + ItemIndex + "_UNIT", "").toLowerCase();
				Gross += Unit.contains("ct") ? Qty * 0.2 : Qty;

				System.out.println("Gross  " + Gross);

			}
		}

		double ActualGross = Round(Gross);
		double ExpectedGross = ParseDouble(Data.get("SKU_" + SkuNum + "_GrossWeight"));
		asrt.assertEquals(ActualGross, ExpectedGross, 1.0, "Mismatch in Gross Weight for SKU_" + SkuNum);

		double NetWeight = ParseDouble(Data.get("SKU_" + SkuNum + "_NetWeight"));

		System.out.println("ActualGross  " + ActualGross);
		System.out.println("ExpectedGross  " + ExpectedGross);

		double MakingRate = ParseDouble(Data.getOrDefault("SKU_" + SkuNum + "_MakingRate", "0"));
		double ExpectedMaking = ParseDouble(Data.get("SKU_" + SkuNum + "_MakingValue"));
		String MakingType = Data.getOrDefault("SKU_" + SkuNum + "_MakingType", "");
		double CalcMaking = NetWeight * MakingRate;

		System.out.println("NetWeight  " + NetWeight);
		System.out.println("MakingRate  " + MakingRate);
		System.out.println("ExpectedMaking  " + ExpectedMaking);
		System.out.println("MakingType  " + MakingType);
		System.out.println("CalcMaking  " + CalcMaking);


		if (MakingType.equals("Total") || MakingType.equals("Pcs")) {
			asrt.assertEquals(MakingRate, ExpectedMaking, 1, "Mismatch in MakingRate for SKU_" + SkuNum);
		} else {
			asrt.assertEquals(CalcMaking, ExpectedMaking, 1, "Mismatch in MakingValue for SKU_" + SkuNum);
		}

		double WastagePercent = ParseDouble(Data.getOrDefault("SKU_" + SkuNum + "_WastageQty", "0"));
		double ExpectedWastage = ParseDouble(Data.get("SKU_" + SkuNum + "_WastageAmount"));
		double CalcWastage = NetWeight * AdjustedRate * WastagePercent / 100.0;

		System.out.println("WastagePercent  " + WastagePercent);
		System.out.println("ExpectedWastage  " + ExpectedWastage);
		System.out.println("CalcWastage  " + CalcWastage);

		asrt.assertEquals(CalcWastage, ExpectedWastage, 1.0 , "Mismatch in Wastage Amount for SKU_" + SkuNum);

		double CValue = ParseDouble(Data.get("SKU_" + SkuNum + "_TotalCValue"));
		double ExpectedTotal = ParseDouble(Data.get("SKU_" + SkuNum + "_Total"));
		double ActualTotal = Round(CValue + (ExpectedMaking == 0.0 ? ExpectedWastage : ExpectedMaking));

		System.out.println("CValue  " + CValue);
		System.out.println("ExpectedTotal  " + ExpectedTotal);
		System.out.println("ActualTotal  " + ActualTotal);

		asrt.assertEquals(ActualTotal, ExpectedTotal, 1.0, "Mismatch in Total Amount for SKU_" + SkuNum);

		System.out.printf("NetWeight raw: %.15f%n", NetWeight);
		System.out.printf("AdjustedRate raw: %.15f%n", AdjustedRate);
		System.out.printf("WastagePercent raw: %.15f%n", WastagePercent);
		System.out.printf("CalcWastage raw: %.15f%n", CalcWastage);



	}


	/// <summary>
	/// Validate SKU Count- Compares the number of SKUs shown on the transaction page with the LinesCount from TransactionDataLine.
	/// Validate SKU Names- Ensures each displayed SKU name matches the expected name from ScannedSkuDataMap, ignoring prefix codes.
	/// Validate Gross Weight (Qty)-Confirms the displayed quantity (gross weight) for each SKU matches the expected gross weight.
	/// Validate Total Without Tax (Per SKU)-Asserts that each SKU's displayed total (without tax) matches the expected total value.
	/// Validate Subtotal (Sum of All SKU Totals)-Adds all displayed SKU totals and compares the sum to the Subtotal value from TransactionDataLine.
	/// Validate Tax (3% of Subtotal) -Calculates 3% of the subtotal and verifies it matches the displayed Tax value.
	/// Validate Total Amount (Subtotal + Tax)-Adds subtotal and tax and confirms it equals the displayed TotalAmount.
	/// Author: VISHNU MANOJ K
	/// </summary>
	public void ErpValidateTransactionLineCalculation(Map<String, String> TransactionDataLine, Map<String, String> ScannedSkuDataMap) {
		System.out.println("====== Validation Started for Transaction Page Line ======");

		// Step 1: SKU Count validation
		List<String> SkuNameElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")
				);
		int ExpectedLinesCount = Integer.parseInt(TransactionDataLine.get("LinesCount").replaceAll("[^\\d]", ""));

		Assert.assertEquals(SkuNameElements.size(), ExpectedLinesCount,
				"After Calculation Value Mismatch in number of SKUs. Actual: " + SkuNameElements.size() + ", Expected: " + ExpectedLinesCount);

		// Step 2: SKU Name Validation
		List<String> ActualSkuNamesOnScreen = SkuNameElements.stream().map(String::trim).collect(Collectors.toList());
		List<String> ExpectedProductNames = new ArrayList<>();
		int TotalSkuCount = (int) ScannedSkuDataMap.keySet().stream()
				.filter(k -> k.endsWith("_SKUName")).count();

		for (int Index = 1; Index <= TotalSkuCount; Index++) {
			String ExpectedSkuName = ScannedSkuDataMap.get("SKU_" + Index + "_SKUName");
			Assert.assertNotNull(ExpectedSkuName, "Expected SKU name is null for SKU_" + Index);
			String[] Split = ExpectedSkuName.trim().split(" - ", 2);
			String ExpectedSkuNameOnly = (Split.length > 1) ? Split[1].trim() : ExpectedSkuName.trim();
			ExpectedProductNames.add(ExpectedSkuNameOnly);
		}

		for (String ExpectedProductName : ExpectedProductNames) {
			System.out.println("Validating presence of expected SKU Name: " + ExpectedProductName);

			Assert.assertTrue(ActualSkuNamesOnScreen.contains(ExpectedProductName),
					"SKU name not found in UI: " + ExpectedProductName);
		}

		// Step 3: Gross Weight Validation
		List<String> ActualSkuNamesGrossWeight = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")
				);
		List<String> ActualQuantities = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")
				);

		Map<String, Double> ActualSkuNameToQtyMap = new HashMap<>();
		for (int Index = 0; Index < ActualSkuNamesGrossWeight.size(); Index++) {
			String SkuName = ActualSkuNamesGrossWeight.get(Index).trim();
			String QtyStr = ActualQuantities.get(Index).trim().replaceAll("[^\\d.]", "");
			double Qty = Double.parseDouble(QtyStr);
			ActualSkuNameToQtyMap.put(SkuName + "_SKU_" + (Index + 1), Qty);
		}

		for (int Index = 1; Index <= TotalSkuCount; Index++) {
			String ExpectedSkuNameFull = ScannedSkuDataMap.get("SKU_" + Index + "_SKUName");
			Assert.assertNotNull(ExpectedSkuNameFull, "Expected SKU name is null for SKU_" + Index);
			String[] Split = ExpectedSkuNameFull.trim().split(" - ", 2);
			String ExpectedSkuNameOnly = (Split.length > 1) ? Split[1].trim() : ExpectedSkuNameFull.trim();
			String ExpectedQtyStr = ScannedSkuDataMap.get("SKU_" + Index + "_GrossWeight");
			Assert.assertNotNull(ExpectedQtyStr, "Expected GrossWeight is null for SKU_" + Index);
			double ExpectedQty = Double.parseDouble(ExpectedQtyStr.trim().replaceAll("[^\\d.]", ""));
			double ActualQty = ActualSkuNameToQtyMap.get(ExpectedSkuNameOnly + "_SKU_" + Index);

			System.out.println("Asserting GrossWeight for SKU_" + Index + ": Expected = " + ExpectedQty + ", Actual = " + ActualQty);

			Assert.assertEquals(Math.abs(ActualQty), ExpectedQty, 1.00,
					"Mismatch in GrossWeight. Actual: " + ActualQty + ", Expected: " + ExpectedQty + " for SKU_" + Index);
		}

		// Step 4 & 5: Total Without Tax and Subtotal
		List<String> ActualSkuNamesTotal = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")
				);
		List<String> ActualTotalElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")
				);

		double ListSubTotalNoTax = 0.0;
		Map<String, Double> ActualSkuToTotalMap = new HashMap<>();

		for (int Index = 0; Index < ActualSkuNamesTotal.size(); Index++) {
			String SkuName = ActualSkuNamesTotal.get(Index).trim();
			String RawTotal = ActualTotalElements.get(Index).trim();
			String TotalStr = RawTotal.replaceAll("[^\\d.-]", "");
			if (TotalStr.isEmpty()) {
				throw new IllegalArgumentException("Invalid total value for SKU: " + SkuName + " Raw: " + RawTotal);
			}
			double Total = Double.parseDouble(TotalStr);
			ListSubTotalNoTax += Total;
			ActualSkuToTotalMap.put(SkuName + "_SKU_" + (Index + 1), Total);
		}

		for (int Index = 1; Index <= TotalSkuCount; Index++) {
			String ExpectedSkuNameFull = ScannedSkuDataMap.get("SKU_" + Index + "_SKUName");
			Assert.assertNotNull(ExpectedSkuNameFull, "Expected SKU name is null for SKU_" + Index);
			String[] Split = ExpectedSkuNameFull.trim().split(" - ", 2);
			String ExpectedSkuNameOnly = (Split.length > 1) ? Split[1].trim() : ExpectedSkuNameFull.trim();
			String ExpectedTotalStr = ScannedSkuDataMap.get("SKU_" + Index + "_Total");

			Assert.assertNotNull(ExpectedTotalStr, "Expected Total is null for SKU_" + Index);

			double ExpectedTotal = Double.parseDouble(ExpectedTotalStr.trim().replaceAll("[^\\d.]", ""));
			double ActualTotal = ActualSkuToTotalMap.get(ExpectedSkuNameOnly + "_SKU_" + Index);

			System.out.println("Asserting TOTAL (Without Tax) for SKU_" + Index + ": Expected = " + ExpectedTotal + ", Actual = " + ActualTotal);

			Assert.assertEquals(Math.abs(ActualTotal), ExpectedTotal, 1,
					"Mismatch in TOTAL (Without Tax). Actual: " + ActualTotal + ", Expected: " + ExpectedTotal + " for SKU_" + Index);
		}

		// Step 6: Subtotal
		double ExpectedSubtotal = Double.parseDouble(TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", ""));
		System.out.println("Asserting TOTAL (Without Tax) SUM vs Subtotal:");
		System.out.println("Expected Subtotal = " + ExpectedSubtotal + ", Actual Sum = " + ListSubTotalNoTax);

		Assert.assertEquals(Math.abs(ListSubTotalNoTax), ExpectedSubtotal, 1,
				"Mismatch in total sum. Actual: " + Math.abs(ListSubTotalNoTax) + ", Expected: " + ExpectedSubtotal);

		// Step 7: Tax
		double CalculatedTax = Math.round((ExpectedSubtotal * 0.03) * 100.0) / 100.0;
		double ExpectedTax = Double.parseDouble(TransactionDataLine.get("Tax").replaceAll("[^\\d.]", ""));
		System.out.println("Asserting calculated 3% TAX vs displayed Tax:");
		System.out.println("Expected Tax = " + ExpectedTax + ", Calculated Tax = " + CalculatedTax);

		Assert.assertEquals(Math.abs(CalculatedTax), ExpectedTax, 1.00,
				"Mismatch in calculated Tax. Calculated: " + CalculatedTax + ", Expected: " + ExpectedTax);

		// Step 8: Total Amount
		double CalculatedTotalAmount = Math.round((ExpectedSubtotal + ExpectedTax) * 100.0) / 100.0;
		double ExpectedTotalAmount = Double.parseDouble(TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", ""));
		System.out.println("Asserting Total Amount (Subtotal + Tax) with displayed TotalAmount:");
		System.out.println("Expected TotalAmount = " + ExpectedTotalAmount + ", Calculated TotalAmount = " + CalculatedTotalAmount);

		Assert.assertEquals(Math.abs(CalculatedTotalAmount), ExpectedTotalAmount, 1.00,
				"Mismatch in Total Amount. Calculated: " + CalculatedTotalAmount + ", Expected: " + ExpectedTotalAmount);

		System.out.println("====== Validation Completed for Transaction Page Line ======");
	}

	/// <summary>
	/// To verify RTGS PaymentDetails
	/// Author: Jhoncy and Chandana Babu
	/// <summary>
	public void RTGSPaymentDetails(String ActualCustName, String ReceiveAmt) throws Exception
	{
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		//Step 1:search RTGS payment details
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		//		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchRTGSPaymentDetails);
		//		Thread.sleep(5000);
		//		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		//
		//		base.newWindow(1);
		//		driver.get(UtilityTestData.ERPURL);

		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchRTGSPaymentDetails);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		// Select Scheme Code
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Cust name"));
		Thread.sleep(10000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRtgsPaymentDetails_CustName_CustName"),ActualCustName );
		//base.buttonClick(LoginPageObj.Edt_Alert("Apply"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRtgsPaymentDetails_CustName_ApplyFilters_label", "Apply"));

		// Scroll horizontally
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(15000);

		this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Receive amt"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Receive amt"));
		Thread.sleep(5000);
		if(!base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRtgsPaymentDetails_ReceiveAmt_ReceiveAmt"))) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Receive amt"));
		}
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRtgsPaymentDetails_ReceiveAmt_ReceiveAmt"), ReceiveAmt);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRtgsPaymentDetails_ReceiveAmt_ApplyFilters_label", "Apply"));

		ActionsObj.clickAndHold(Scrollbar).moveByOffset(-300, 0).release().perform();
		Thread.sleep(1000);	
		int MaxRetries = 20;
		boolean IsElementVisible = false;

		for (int i = 0; i < MaxRetries; i++) {
			try {
				// Click the refresh button
				Thread.sleep(3000);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Btn_Refresh("Refresh","dynamicsButton")
						));
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Refresh("Refresh","dynamicsButton"));

				// Wait for the ERP Customer Name element to be visible

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_ERPCustName(ActualCustName)
						));

				// Element is now visible
				IsElementVisible = true;
				break;

			} catch (TimeoutException e) {
				System.out.println("Attempt " + (i + 1) + ": ERP Customer name not visible. Retrying...");
			}
		}

		if (!IsElementVisible) {
			throw new RuntimeException("ERP Customer name not visible after " + MaxRetries + " attempts.");
		}

		// Now safe to get the value
		String CustomerNameFromERP = base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPCustName(ActualCustName));

		//String CustomerNameFromERP = base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPCustName(ActualCustName));
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing ViewMore-symbol"));
		asrt.assertEquals(CustomerNameFromERP,ActualCustName ," RTGS entry line not updated: Customer mismatch: " +"Expected: " + CustomerNameFromERP + ", but found: " + ActualCustName);
	}

	///<summary>
	/// Performs a normal sale of Dual Tone Platinum SKU
	///
	/// Logs in to the POS system
	/// Selects customer and scans Dual Tone Platinum SKUs
	/// Extracts and processes SKU details (net weight, RG weight, purity, rate)
	/// Converts all RG weights to a common purity and rate
	/// Finalizes transaction, makes payment, downloads invoice
	/// Returns invoice number, totals, SKU-level data, and scanned values
	///
	/// Author : Robin T Abraham
	///</summary>
	public Map<String, Object> GetInvoiceDetailsAfterNormalSaleDualTonePlatinum(String CustomerNo) throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		Thread.sleep(5000);

		// Step 2: Navigate to the Transaction page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer and click in add to sale button
		appUtils.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);

		// Step 4: Scan Multiple SKUs
		List<String> AllSkuList = new ArrayList<>();
		List<String>ScannedSkus = appUtils.ScanMultiplePlatinumSkus("Inter","Platinum", "Platinum");
		List<String> PlatinumSkus = appUtils.FetchDualTonePlatinumSku(ScannedSkus, 1);

		AllSkuList.addAll(PlatinumSkus);
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("headerSplitViewToggleButton expandedNavButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h5 textLeft centerY", "Transaction"));
		WebDriverWait HamBurgerWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			if (HamBurgerWait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SingnIn("CART_ITEM_ID"))) != null)
			{
				Thread.sleep(3000);
				base.excuteJsClick(LoginPageObj.Btn_SingnIn("CART_ITEM_ID"));
			}
		} catch (Exception e) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc(
					"height48 centerY","Show or hide navigation bar"));
			Thread.sleep(3000);
			base.excuteJsClick(LoginPageObj.Btn_SingnIn("CART_ITEM_ID"));
		}
		// Step 5: Extract SKU table data
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int I = 0; I < AllSkuList.size(); I++) {
			appUtils.SKUIngridentTableValues(AllSkuList.get(I), I + 1, ScannedSkuDataMap);
		}

		// Step 6: Extract required values
		double TotalNetWeight = 0.0;
		String FirstSkuPurity = "N/A";
		String FirstSkuGoldRate = "Not Found";
		boolean FoundRg = false;

		double TotalRgWeightInFirstPurity = 0.0;
		double FirstPurity = 0.0;

		Map<String, String> SkuWiseData = new LinkedHashMap<>();

		for (int Index = 0; Index < AllSkuList.size(); Index++) {
			int SkuNumber = Index + 1;

			String NetWeightStr = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_NetWeight");
			try {
				double NetWeight = Double.parseDouble(NetWeightStr);
				TotalNetWeight += NetWeight;
			} catch (Exception Ex) {
				System.out.println(" - Error parsing Net Weight for SKU " + SkuNumber + ": " + NetWeightStr);
			}
			SkuWiseData.put("SKU_" + SkuNumber + "_NetWeight", NetWeightStr);

			String GrossWeight = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_GrossWeight", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_GrossWeight", GrossWeight);

			String StoneWeight = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_StoneWeight", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_StoneWeight", StoneWeight);

			String SkuTotal = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_Total", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_Total", SkuTotal);

			String RgWeightStr = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_TotalRGWeight", "0.0");
			double RgWeight = 0.0;
			try {
				RgWeight = Double.parseDouble(RgWeightStr);
			} catch (Exception Ex) {
				System.out.println("â Œ Invalid RG Weight for SKU_" + SkuNumber + ": " + RgWeightStr);
				continue;
			}

			if (RgWeight == 0.0) continue;

			String ThisPurityStr = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_Purity", "").replaceAll("(?i)k", "").trim();
			double ThisPurity = 0.0;
			try {
				ThisPurity = Double.parseDouble(ThisPurityStr);
			} catch (Exception Ex) {
				System.out.println("â Œ Invalid Purity for SKU_" + SkuNumber + ": " + ThisPurityStr);
				continue;
			}

			if (!FoundRg) {
				FirstPurity = ThisPurity;
				FirstSkuPurity = ThisPurityStr;

				for (String Key : ScannedSkuDataMap.keySet()) {
					if (Key.startsWith("SKU" + SkuNumber + "_ITEM") && Key.endsWith("_ITEMID")) {
						String ItemKeyPrefix = Key.replace("_ITEMID", "");
						String ItemId = ScannedSkuDataMap.get(Key);
						if ("RG".equalsIgnoreCase(ItemId)) {
							FirstSkuGoldRate = ScannedSkuDataMap.getOrDefault(ItemKeyPrefix + "_RATE", "Not Found");
							break;
						}
					}
				}
				FoundRg = true;
			}

			double ThisSkuGoldRate = 0.0;
			for (String Key : ScannedSkuDataMap.keySet()) {
				if (Key.startsWith("SKU" + SkuNumber + "_ITEM") && Key.endsWith("_ITEMID")) {
					String ItemKeyPrefix = Key.replace("_ITEMID", "");
					String ItemId = ScannedSkuDataMap.get(Key);
					if ("RG".equalsIgnoreCase(ItemId)) {
						try {
							ThisSkuGoldRate = Double.parseDouble(
									ScannedSkuDataMap.getOrDefault(ItemKeyPrefix + "_RATE", "0.0")
									.replaceAll("[^0-9.]", ""));
						} catch (Exception Ex) {
							System.out.println("â Œ Invalid RATE for SKU_" + SkuNumber + ": " + ScannedSkuDataMap.get(ItemKeyPrefix + "_RATE"));
						}
						break;
					}
				}
			}

			double FirstGoldRate = 0.0;
			try {
				FirstGoldRate = Double.parseDouble(FirstSkuGoldRate.replaceAll("[^0-9.]", ""));
			} catch (Exception Ex) {
				System.out.println("â Œ Invalid First RG Rate: " + FirstSkuGoldRate);
				continue;
			}

			double ConvertedRgWeight = 0.0;
			if (FirstPurity == 18.0 && FirstGoldRate == ThisSkuGoldRate) {
				ConvertedRgWeight = RgWeight;
			} else {
				ConvertedRgWeight = (RgWeight * ThisSkuGoldRate) / (FirstGoldRate);
				ConvertedRgWeight = Math.round(ConvertedRgWeight * 1000.0) / 1000.0;
			}

			TotalRgWeightInFirstPurity += ConvertedRgWeight;

			System.out.println("SKU_" + SkuNumber + " RG Weight = " + RgWeight +
					", Purity = " + ThisPurity + ", RG Rate = " + ThisSkuGoldRate +
					" => Converted to First Purity (" + FirstPurity + "K @ " + FirstGoldRate +
					") = " + ConvertedRgWeight);
		}

		System.out.println("âœ… Total RG Weight Converted to First Purity (" + FirstPurity + "K): " + TotalRgWeightInFirstPurity);

		// Step 7: Finalize transaction
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotal          = TransactionDataLine.get("Subtotal");
		String Discount          = TransactionDataLine.get("Discount");
		String TotalGrossWeight  = TransactionDataLine.get("GrossWeight");
		String TotalNewNetWeight = TransactionDataLine.get("NetWeight");
		String Tax               = TransactionDataLine.get("Tax");
		String TotalAmount       = TransactionDataLine.get("TotalAmount");
		String NetTotal          = TransactionDataLine.get("NetTotal");
		String LinesCount        = TransactionDataLine.get("LinesCount");

		appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName;
		String InvoiceNo = pdfUtils.ExtractReceiptNoFrmSaleInvoice(TaxInvoicePath);
		String Purity = pdfUtils.ExtractPurityFrmSaleInvoice(TaxInvoicePath);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Step 8: Collect returned product names
		List<String> ReturnedProducts = new ArrayList<>();
		for (String Key : ScannedSkuDataMap.keySet()) {
			if (Key.matches("SKU\\d+_ITEM\\d+_ITEMNAME")) {
				ReturnedProducts.add(ScannedSkuDataMap.get(Key));
			}
		}

		// Step 9: Return all collected data
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		ResultMap.put("InvoiceNo", InvoiceNo);
		ResultMap.put("TotalNetWeight", String.format("%.3f", TotalNetWeight));
		ResultMap.put("TotalRgWeight", String.format("%.3f", TotalRgWeightInFirstPurity));
		ResultMap.put("Purity",	Purity);
		ResultMap.put("FirstSkuPurity", FirstSkuPurity);
		ResultMap.put("FirstSkuGoldRate", FirstSkuGoldRate);
		ResultMap.put("SKUWiseDetails", SkuWiseData);
		ResultMap.put("SubTotal", SubTotal);
		ResultMap.put("Discount", Discount);
		ResultMap.put("TotalGrossWeight", TotalGrossWeight);
		ResultMap.put("Tax", Tax);
		ResultMap.put("TotalAmount", TotalAmount);
		ResultMap.put("NetTotal", NetTotal);
		ResultMap.put("LinesCount", LinesCount);
		ResultMap.put("ScannedSKUDataMap", ScannedSkuDataMap);

		return ResultMap;
	}

	///<summary>
	/// Merges return and sale SKU maps and re-indexes keys as SKU_1, SKU_2, ...
	/// Author: Vishnu M K
	///</summary>
	public Map<String, String> MergeScannedSkuDataMaps(Map<String, String> ReturnSkuMap, Map<String, String> NewSkuMap) {
		Map<String, String> MergedMap = new LinkedHashMap<>();
		int SkuIndex = 1;

		// Step 1: Merge return SKUs first
		if (ReturnSkuMap != null) {
			Map<Integer, Map<String, String>> GroupedReturnMap = GroupByIndex(ReturnSkuMap);
			for (Map<String, String> SkuGroup : GroupedReturnMap.values()) {
				for (Map.Entry<String, String> Entry : SkuGroup.entrySet()) {
					String Suffix = Entry.getKey().substring(Entry.getKey().indexOf("_", 4)); // from SKU_1_GrossWeight → _GrossWeight
					MergedMap.put("SKU_" + SkuIndex + Suffix, Entry.getValue());
				}
				SkuIndex++;
			}
		}

		// Step 2: Merge new scanned SKUs next
		if (NewSkuMap != null) {
			Map<Integer, Map<String, String>> GroupedNewMap = GroupByIndex(NewSkuMap);
			for (Map<String, String> SkuGroup : GroupedNewMap.values()) {
				for (Map.Entry<String, String> Entry : SkuGroup.entrySet()) {
					String Suffix = Entry.getKey().substring(Entry.getKey().indexOf("_", 4));
					MergedMap.put("SKU_" + SkuIndex + Suffix, Entry.getValue());
				}
				SkuIndex++;
			}
		}

		return MergedMap;
	}

	// Helper method to group SKU fields by index
	private Map<Integer, Map<String, String>> GroupByIndex(Map<String, String> SkuMap) {
		Map<Integer, Map<String, String>> GroupedMap = new TreeMap<>();
		for (Map.Entry<String, String> Entry : SkuMap.entrySet()) {
			String Key = Entry.getKey();
			if (Key.startsWith("SKU_")) {
				String[] Parts = Key.split("_", 3);
				if (Parts.length >= 3) {
					try {
						int Index = Integer.parseInt(Parts[1]);
						GroupedMap.computeIfAbsent(Index, k -> new LinkedHashMap<>())
						.put("SKU_" + Index + "_" + Parts[2], Entry.getValue());
					} catch (NumberFormatException e) {
						// Skip malformed keys like SKU_X
					}
				}
			}
		}
		return GroupedMap;
	}
	/// <summary>
	/// Validate SKU Count- Compares the number of SKUs shown on the transaction page with the LinesCount from TransactionDataLine.
	/// Validate SKU Names- Ensures each displayed SKU name matches the expected name from ScannedSkuDataMap, ignoring prefix codes.
	/// Validate Gross Weight (Qty)-Confirms the displayed quantity (gross weight) for each SKU matches the expected gross weight.
	/// Validate Total Without Tax (Per SKU)-Asserts that each SKU's displayed total (without tax) matches the expected total value.
	/// Validate Subtotal (Sum of All SKU Totals)-Adds all displayed SKU totals and compares the sum to the Subtotal value from TransactionDataLine.
	/// Validate Tax (3% of Subtotal) -Calculates 3% of the subtotal and verifies it matches the displayed Tax value.
	/// Validate Total Amount (Subtotal + Tax)-Adds subtotal and tax and confirms it equals the displayed TotalAmount.
	/// Author: VISHNU MANOJ K
	/// <summary>
	public void ErpValidateTransactionLineCalculation(Map<String, String> TransactionDataLine,Map<String, String> ScannedSkuDataMap,String LinesCountSale)
	{
		System.out.println("====== Validation Started for Transaction Page Line ======  ");

		// === Step 1: Assert that number of SKUs matches LinesCount ===
		List<String> SkuNameElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")
				);
		System.out.println("Total SKUs displayed on Transaction Page: " + SkuNameElements);
		int ExpectedLinesCount = Integer.parseInt(TransactionDataLine.get("LinesCount").replaceAll("[^\\d]", ""));
		Assert.assertEquals(SkuNameElements.size(), ExpectedLinesCount, "After Calculation Value Mismatch in number of SKUs Actual:"+SkuNameElements.size()+" and number of SKUs Expected:"+ExpectedLinesCount+" LinesCount in Transaction Line Page");

		// === Step 2: Assert each SKU Name ===
		for (int i = 0; i < SkuNameElements.size(); i++) {
			String ActualSkuName = SkuNameElements.get(i).trim();
			String ExpectedSkuName = ScannedSkuDataMap.get("SKU_" + (i + 1) + "_SKUName");
			Assert.assertNotNull(ExpectedSkuName, "Expected SKU name is null for SKU_" + (i + 1));

			String[] Split = ExpectedSkuName.trim().split(" - ", 2);
			String ExpectedProductNameOnly = (Split.length > 1) ? Split[1].trim() : ExpectedSkuName.trim();

			System.out.println("Asserting SKU_" + (i + 1) + " Name: Expected = " + ExpectedProductNameOnly + ", Actual = " + ActualSkuName);
			Assert.assertEquals(ActualSkuName, ExpectedProductNameOnly, "After Calculation Mismatch in SKU name Actual:"+ActualSkuName+" and SKU name Expected:"+ExpectedProductNameOnly+"  for SKU_" + (i + 1));
		}

		// === Step 3: Assert GrossWeight (Qty) ===
		List<String> SkuQuantityElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")
				);

		for (int i = 0; i < SkuQuantityElements.size(); i++) {
			String ActualQtyStr = SkuQuantityElements.get(i).trim().replaceAll("[^\\d.]", "");
			String ExpectedGrossWeightStr = ScannedSkuDataMap.get("SKU_" + (i + 1) + "_GrossWeight");

			Assert.assertNotNull(ExpectedGrossWeightStr, "Expected GrossWeight is null for SKU_" + (i + 1));

			double ActualQty = Double.parseDouble(ActualQtyStr);
			double ExpectedGrossWeight = Double.parseDouble(ExpectedGrossWeightStr.trim().replaceAll("[^\\d.]", ""));

			System.out.println("Asserting GrossWeight for SKU_" + (i + 1) + ": Expected = " + ExpectedGrossWeight + ", Actual = " + ActualQty);
			Assert.assertEquals(ActualQty, ExpectedGrossWeight,1, "After Calculation Mismatch in GrossWeight Actual:"+ActualQty+" and Expected:"+ExpectedGrossWeight+" for SKU_" + (i + 1));
		}

		// === Step 4 & 5: Assert Total Without Tax and Subtotal ===
		List<String> TotalWithoutTaxElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")
				);

		int LinesCountSaleInt = Integer.parseInt(LinesCountSale);

		double FinalAdjustedSum = 0.0;

		for (int i = 0; i < TotalWithoutTaxElements.size(); i++) {
			int SkuNumber = i + 1;
			String ActualTotalStr = TotalWithoutTaxElements.get(i).trim().replaceAll("[^\\d.]", "");
			String ExpectedTotalStr = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");

			double ActualTotal = Double.parseDouble(ActualTotalStr);
			double ExpectedTotal = Double.parseDouble(ExpectedTotalStr.trim().replaceAll("[^\\d.]", ""));

			System.out.println("Asserting TOTAL (Without Tax) for SKU_" + SkuNumber + ": Expected = " + ExpectedTotal + ", Actual = " + ActualTotal);
			Assert.assertEquals(ActualTotal, ExpectedTotal, 1, "After Calculation Mismatch in TOTAL (Without Tax) Actual:" + ActualTotal + " and Expected:" + ExpectedTotal + " for SKU_" + SkuNumber);

			// Add or Subtract from FinalAdjustedSum based on LinesCount
			if (SkuNumber <= LinesCountSaleInt) {
				FinalAdjustedSum += ActualTotal; // Add first N (return) SKUs
			} else {
				FinalAdjustedSum -= ActualTotal; // Subtract newly scanned SKUs
			}
		}

		double ExpectedSubtotal = Double.parseDouble(TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", ""));
		System.out.println("Asserting Final Adjusted TOTAL (Without Tax) vs Subtotal:");
		System.out.println("Expected Subtotal = " + ExpectedSubtotal + ", Final Adjusted Sum = " + FinalAdjustedSum);
		Assert.assertEquals(Math.abs(FinalAdjustedSum), ExpectedSubtotal, 1, "After Calculation Mismatch in Adjusted Total (Without Tax) Actual:" + FinalAdjustedSum + " and Expected Subtotal:" + ExpectedSubtotal);


		// === Step 6: TAX = 3% of Subtotal ===
		double CalculatedTax = Math.round((ExpectedSubtotal * 0.03) * 100.0) / 100.0;
		double ExpectedTax = Double.parseDouble(TransactionDataLine.get("Tax").replaceAll("[^\\d.]", ""));

		System.out.println("Asserting calculated 3% TAX vs displayed Tax:");
		System.out.println("Expected Tax = " + ExpectedTax + ", Calculated Tax = " + CalculatedTax);
		Assert.assertEquals(CalculatedTax, ExpectedTax,1, "After Calculation Mismatch in calculated Tax "+CalculatedTax+" and Expected Tax value is"+ExpectedTax+"");

		// === Step 7: TotalAmount = Subtotal + Tax ===
		double CalculatedTotalAmount = Math.round((ExpectedSubtotal + ExpectedTax) * 100.0) / 100.0;
		double ExpectedTotalAmount = Double.parseDouble(TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", ""));

		System.out.println("Asserting Total Amount (Subtotal + Tax) with displayed TotalAmount:");
		System.out.println("Expected TotalAmount = " + ExpectedTotalAmount + ", Calculated TotalAmount = " + CalculatedTotalAmount);
		Assert.assertEquals(CalculatedTotalAmount, ExpectedTotalAmount,1, "After Calculation Mismatch in Total Amount Calculated "+CalculatedTotalAmount+" and Expected Total Amount "+ExpectedTotalAmount+"");

		System.out.println("====== Validation Completed for Transaction Page Line ======  ");

	}

	//	/// <summary>
	//	/// To find ongoing GGScheme
	//	/// Author: Jhoncy
	//	/// <summary>
	//	/// <summary>
	//	/// To find ongoing GGScheme
	//	/// Author: Jhoncy
	//	/// <summary>
	//	public GPPAccountDetails ToGetOnGoingGGScheme() throws Exception
	//	{
	//		//Login to ERP
	//		base.newWindow(1);
	//		driver.get(UtilityTestData.ERPURL);
	//
	//		//Search GPP Account opening
	//		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
	//		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);
	//		Thread.sleep(5000);
	//		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
	//
	//		//Select SchemeCode(GG),set and apply
	//		Thread.sleep(2000);
	//		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Scheme code"));
	//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_SchemeCode_SchemeCode"),UtilityTestData.SchemeCode);
	//		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_SchemeCode_ApplyFilters_label","Apply"));
	//
	//		//Select Store Number(5011),set and apply 
	//		Thread.sleep(4000);
	//		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Store number"));
	//		Thread.sleep(6000);
	//		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"));
	//		Thread.sleep(2000);
	//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"),UtilityTestData.WarehouseId);
	//		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RetailStoreId_ApplyFilters_label","Apply"));
	//
	//		//Scroll left to right
	//		WebElement scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
	//		Actions actions = new Actions(driver);
	//		actions.clickAndHold(scrollbar)
	//		.moveByOffset(650, 0)
	//		.release()
	//		.perform();
	//		Thread.sleep(4000);
	//
	//		//Select Matured Field(No),Set and apply
	//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Matured"));
	//		Thread.sleep(5000);
	//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"),UtilityTestData.GPPAdjustMatured);
	//		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Matured_ApplyFilters_label","Apply"));	
	//
	//		//Select Adjusted Field(No),Set and apply
	//		Thread.sleep(3000);
	//		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Adjusted"));
	//		Thread.sleep(5000);
	//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Adjusted_Adjusted"),UtilityTestData.GPPAdjustMatured);
	//		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Adjusted_ApplyFilters_label","Apply"));	
	//
	//
	//		//Select Total Amount paid and sort largest to smallest
	//		Thread.sleep(4000);
	//		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Total amount paid"));
	//		Thread.sleep(1000);
	//		base.buttonClick(LoginPageObj.Edt_Alert("Sort largest to smallest"));
	//		Thread.sleep(5000);
	//		//scroll right to left
	//		Actions action = new Actions(driver);
	//		Thread.sleep(1000);
	//		action.clickAndHold(scrollbar)
	//		.moveByOffset(-650, 0)
	//		.release()
	//		.perform();
	//		String CustomerAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_CustAccount"));	
	//		System.out.println(CustomerAccount);
	//		String GppAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_GPPAccountNo"));	
	//		System.out.println(GppAccount);
	//		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());		
	//		driver.switchTo().window(Tabs.get(0));	
	//
	//		return new GPPAccountDetails(CustomerAccount, GppAccount);
	//	}
	//	
	//	
	//
	//	public class GPPAccountDetails {
	//		public String CustomerAccount;
	//		public String GppAccount;
	//
	//		public GPPAccountDetails(String CustomerAccount, String GppAccount) {
	//			this.CustomerAccount = CustomerAccount;
	//			this.GppAccount = GppAccount;
	//		}
	/// <summary>
	/// To find ongoing GGScheme
	/// Author: Jhoncy
	/// <summary>
	public GPPAccountDetails ToGetOnGoingGGScheme() throws Exception
	{
		//Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		//Search GPP Account opening
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Select SchemeCode(GG),set and apply
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Scheme code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_SchemeCode_SchemeCode"),UtilityTestData.SchemeCode);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_SchemeCode_ApplyFilters_label","Apply"));

		// Scroll horizontally
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(10000);

		//Select Total Amount paid and sort largest to smallest
		Thread.sleep(4000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Total amount paid"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_Alert("Sort largest to smallest"));
		Thread.sleep(5000);

		// Store Number Filter
		this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		Thread.sleep(9000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RetailStoreId_ApplyFilters_label", "Apply"));

		// Scroll horizontally
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(4000);

		//Select Matured Field(No),Set and apply
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Matured"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"),UtilityTestData.GPPAdjustMatured);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Matured_ApplyFilters_label","Apply"));	

		//Select Adjusted Field(No),Set and apply
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Adjusted"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Adjusted_Adjusted"),UtilityTestData.GPPAdjustMatured);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Adjusted_ApplyFilters_label","Apply"));	

		//		//Select Total Amount paid and sort largest to smallest
		//		Thread.sleep(4000);
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Total amount paid"));
		//		Thread.sleep(1000);
		//		base.buttonClick(LoginPageObj.Edt_Alert("Sort largest to smallest"));
		//		Thread.sleep(5000);
		//scroll right to left
		Actions action = new Actions(driver);
		WebElement scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Thread.sleep(1000);
		action.clickAndHold(scrollbar)
		.moveByOffset(-850, 0)
		.release()
		.perform();
		String CustomerAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_CustAccount"));	
		System.out.println(CustomerAccount);
		String GppAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_GPPAccountNo"));	
		System.out.println(GppAccount);
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());		
		driver.switchTo().window(Tabs.get(0));	

		return new GPPAccountDetails(CustomerAccount, GppAccount);
	}

	public class GPPAccountDetails {
		public String CustomerAccount;
		public String GppAccount;

		public GPPAccountDetails(String CustomerAccount, String GppAccount) {
			this.CustomerAccount = CustomerAccount;
			this.GppAccount = GppAccount;
		}
	}

	/// <summary>
	/// To Validate Matured GGScheme
	/// Author: Jhoncy
	/// <summary>
	public void ToGetMaturedGGScheme(String GPPAccountNo) throws Exception
	{
		//Login ERP Application
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		//Search GPP Account opening
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Select GPP Account number,set and apply 
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","GPP account no"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_GPPAccountNo_GPPAccountNo"),GPPAccountNo);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_GPPAccountNo_ApplyFilters_label","Apply"));

		//scroll left to right
		Thread.sleep(2000);
		WebElement scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions actions = new Actions(driver);
		actions.clickAndHold(scrollbar)
		.moveByOffset(650, 0)
		.release()
		.perform();
		Thread.sleep(1000);	

		//Expected: Matured field should be checked
		asrt.assertTrue(base.isExists(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_Matured("Grid_Matured","_nc9l2u","Yes")),"GG Scheme not matured");
	}


	//	/// <summary>
	//	/// Validate SKU Count- Compares the number of SKUs shown on the transaction page with the LinesCount from TransactionDataLine.
	//	/// Validate SKU Names- Ensures each displayed SKU name matches the expected name from ScannedSkuDataMap, ignoring prefix codes.
	//	/// Validate Gross Weight (Qty)-Confirms the displayed quantity (gross weight) for each SKU matches the expected gross weight.
	//	/// Validate Total Without Tax (Per SKU)-Asserts that each SKU's displayed total (without tax) matches the expected total value.
	//	/// Validate Subtotal (Sum of All SKU Totals)-Adds all displayed SKU totals and compares the sum to the Subtotal value from TransactionDataLine.
	//	/// Validate Tax (3% of Subtotal) -Calculates 3% of the subtotal and verifies it matches the displayed Tax value.
	//	/// Validate Total Amount (Subtotal + Tax)-Adds subtotal and tax and confirms it equals the displayed TotalAmount.
	//	/// Author: VISHNU MANOJ K
	//	/// <summary>
	//	public void ErpValidateTransactionLineCalculationSubtract(
	//			Map<String, String> TransactionDataLine,
	//			Map<String, String> ScannedSkuDataMap,
	//			String FirstHowManyIndex,
	//			String SecondHowManyIndex,
	//			boolean PlusFirstThenMinus
	//			) {
	//		System.out.println("====== Validation Started for Transaction Page Line ======");
	//
	//		// === Step 1: Assert that number of SKUs matches LinesCount ===
	//		List<String> SkuNameElements = base.GetElementTexts(
	//				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")
	//				);
	//
	//		int ExpectedLinesCount = Integer.parseInt(TransactionDataLine.get("LinesCount").replaceAll("[^\\d]", ""));
	//		Assert.assertEquals(SkuNameElements.size(), ExpectedLinesCount,
	//				"After Calculation Value Mismatch in number of SKUs Actual:" + SkuNameElements.size() +
	//				" and number of SKUs Expected:" + ExpectedLinesCount +
	//				" LinesCount in Transaction Line Page");
	//
	//		// === Step 2: Assert each SKU Name ===
	//		for (int I = 0; I < SkuNameElements.size(); I++) {
	//			String ActualSkuName = SkuNameElements.get(I).trim();
	//			String ExpectedSkuName = ScannedSkuDataMap.get("SKU_" + (I + 1) + "_SKUName");
	//			Assert.assertNotNull(ExpectedSkuName, "Expected SKU name is null for SKU_" + (I + 1));
	//
	//			String[] Split = ExpectedSkuName.trim().split(" - ", 2);
	//			String ExpectedProductNameOnly = (Split.length > 1) ? Split[1].trim() : ExpectedSkuName.trim();
	//
	//			System.out.println("Asserting SKU_" + (I + 1) + " Name: Expected = " + ExpectedProductNameOnly + ", Actual = " + ActualSkuName);
	//			Assert.assertEquals(ActualSkuName, ExpectedProductNameOnly,
	//					"After Calculation Mismatch in SKU name Actual:" + ActualSkuName +
	//					" and SKU name Expected:" + ExpectedProductNameOnly + " for SKU_" + (I + 1));
	//		}
	//
	//		// === Step 3: Assert GrossWeight (Qty) ===
	//		List<String> SkuQuantityElements = base.GetElementTexts(
	//				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")
	//				);
	//
	//		for (int I = 0; I < SkuQuantityElements.size(); I++) {
	//			String ActualQtyStr = SkuQuantityElements.get(I).trim().replaceAll("[^\\d.]", "");
	//			String ExpectedGrossWeightStr = ScannedSkuDataMap.get("SKU_" + (I + 1) + "_GrossWeight");
	//			Assert.assertNotNull(ExpectedGrossWeightStr, "Expected GrossWeight is null for SKU_" + (I + 1));
	//
	//			double ActualQty = Double.parseDouble(ActualQtyStr);
	//			double ExpectedGrossWeight = Double.parseDouble(ExpectedGrossWeightStr.trim().replaceAll("[^\\d.]", ""));
	//
	//			System.out.println("Asserting GrossWeight for SKU_" + (I + 1) +
	//					": Expected = " + ExpectedGrossWeight + ", Actual = " + ActualQty);
	//			Assert.assertEquals(Math.abs(ActualQty), Math.abs(ExpectedGrossWeight), 1,
	//					"After Calculation Mismatch in GrossWeight Actual:" + ActualQty +
	//					" and Expected:" + ExpectedGrossWeight + " for SKU_" + (I + 1));
	//		}
	//
	//		// === Step 4 & 5: Assert Total Without Tax and Subtotal with plus/minus logic ===
	//		List<String> TotalWithoutTaxElements = base.GetElementTexts(
	//				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")
	//				);
	//
	//		int FirstCount = Integer.parseInt(FirstHowManyIndex);
	//		int SecondCount = Integer.parseInt(SecondHowManyIndex);
	//
	//		double FinalAdjustedSum = 0.0;
	//		double FinalAdjustedSumBeforeLast = 0.0;
	//		double SumWithoutNegatives = 0.0;
	//		for (int I = 0; I < TotalWithoutTaxElements.size(); I++) {
	//			int SkuNumber = I + 1;
	//			String ActualTotalStrRaw = TotalWithoutTaxElements.get(I).trim();
	//
	//			// Skip values that contain a negative sign
	//			if (!ActualTotalStrRaw.contains("-")) {
	//				String CleanValue = ActualTotalStrRaw.replaceAll("[^\\d.]", "");
	//				if (!CleanValue.isEmpty()) {
	//					SumWithoutNegatives += Double.parseDouble(CleanValue);
	//				}
	//			}
	//
	//			String ActualTotalStr = TotalWithoutTaxElements.get(I).trim().replaceAll("[^\\d.]", "");
	//			String ExpectedTotalStr = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
	//
	//			double ActualTotal = Double.parseDouble(ActualTotalStr);
	//			double ExpectedTotal = Double.parseDouble(ExpectedTotalStr.trim().replaceAll("[^\\d.]", ""));
	//
	//			System.out.println("Asserting TOTAL (Without Tax) for SKU_" + SkuNumber +
	//					": Expected = " + ExpectedTotal + ", Actual = " + ActualTotal);
	//			Assert.assertEquals(Math.abs(ActualTotal), Math.abs(ExpectedTotal), 1,
	//					"After Calculation Mismatch in TOTAL (Without Tax) Actual:" + ActualTotal +
	//					" and Expected:" + ExpectedTotal + " for SKU_" + SkuNumber);
	//
	//			boolean IsFirstGroup = (SkuNumber <= FirstCount);
	//			boolean IsSecondGroup = (!IsFirstGroup && SkuNumber <= (FirstCount + SecondCount));
	//
	//			if (IsFirstGroup) {
	//				if (PlusFirstThenMinus) {
	//					FinalAdjustedSum += ActualTotal;
	//				} else {
	//					FinalAdjustedSum -= ActualTotal;
	//				}
	//			} else if (IsSecondGroup) {
	//				if (PlusFirstThenMinus) {
	//					FinalAdjustedSum -= ActualTotal;
	//				} else {
	//					FinalAdjustedSum += ActualTotal;
	//				}
	//			} else {
	//				// Beyond both groups — default add
	//				FinalAdjustedSum += ActualTotal;
	//			}
	//
	//			if (I == TotalWithoutTaxElements.size() - 1) {
	//				FinalAdjustedSumBeforeLast = FinalAdjustedSum;
	//			}
	//		}
	//
	//		double ExpectedSubtotal = Double.parseDouble(TransactionDataLine.get("Subtotal").replaceAll("[^\\d.-]", ""));
	//		System.out.println("Asserting Final Adjusted TOTAL (Without Tax) vs Subtotal:");
	//		System.out.println("Expected Subtotal = " + ExpectedSubtotal + ", Final Adjusted Sum = " + FinalAdjustedSum);
	//		Assert.assertEquals(Math.abs(FinalAdjustedSum), Math.abs(ExpectedSubtotal), 1,
	//				"After Calculation Mismatch in Adjusted Total (Without Tax) Actual:" + FinalAdjustedSum +
	//				" and Expected Subtotal:" + ExpectedSubtotal);
	//
	//		// === Step 6: TAX = 3% of Subtotal ===
	//		double CalculatedTax = Math.round((SumWithoutNegatives * 0.03) * 100.0) / 100.0;//Non Negatives
	//		double ExpectedTax = Double.parseDouble(TransactionDataLine.get("Tax").replaceAll("[^\\d.-]", ""));
	//		System.out.println("Asserting calculated 3% TAX vs displayed Tax:");
	//		System.out.println("Expected Tax = " + ExpectedTax + ", Calculated Tax = " + CalculatedTax);
	//		Assert.assertEquals(Math.abs(CalculatedTax), Math.abs(ExpectedTax), 1,
	//				"After Calculation Mismatch in calculated Tax " + CalculatedTax +
	//				" and Expected Tax value is " + ExpectedTax);
	//
	//		// === Step 7: TotalAmount = Subtotal + Tax ===
	//		double CalculatedTotalAmount = Math.round((ExpectedSubtotal + ExpectedTax) * 100.0) / 100.0;
	//		double ExpectedTotalAmount = Double.parseDouble(TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.-]", ""));
	//		System.out.println("Asserting Total Amount (Subtotal + Tax) with displayed TotalAmount:");
	//		System.out.println("Expected TotalAmount = " + ExpectedTotalAmount + ", Calculated TotalAmount = " + CalculatedTotalAmount);
	//		Assert.assertEquals(Math.abs(CalculatedTotalAmount), Math.abs(ExpectedTotalAmount), 1,
	//				"After Calculation Mismatch in Total Amount Calculated " + CalculatedTotalAmount +
	//				" and Expected Total Amount " + ExpectedTotalAmount);
	//
	//		System.out.println("====== Validation Completed for Transaction Page Line ======");
	//	}
	public List<String> ErpValidateTransactionLineCalculationSubtract(
			Map<String, String> TransactionDataLine,
			Map<String, String> ScannedSkuDataMap,
			String FirstHowManyIndex,
			String SecondHowManyIndex,
			boolean PlusFirstThenMinus
			) {
		System.out.println("====== Validation Started for Transaction Page Line ======");

		// === Step 1: Assert that number of SKUs matches LinesCount ===
		List<String> SkuNameElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")
				);

		int ExpectedLinesCount = Integer.parseInt(TransactionDataLine.get("LinesCount").replaceAll("[^\\d]", ""));
		Assert.assertEquals(SkuNameElements.size(), ExpectedLinesCount,
				"After Calculation Value Mismatch in number of SKUs Actual:" + SkuNameElements.size() +
				" and number of SKUs Expected:" + ExpectedLinesCount +
				" LinesCount in Transaction Line Page");
		List<String> ActualSkuNamesList = new ArrayList<>();
		// === Step 2: Assert each SKU Name ===
		for (int I = 0; I < SkuNameElements.size(); I++) {
			String ActualSkuName = SkuNameElements.get(I).trim();
			ActualSkuNamesList.add(ActualSkuName);
			String ExpectedSkuName = ScannedSkuDataMap.get("SKU_" + (I + 1) + "_SKUName");
			Assert.assertNotNull(ExpectedSkuName, "Expected SKU name is null for SKU_" + (I + 1));

			String[] Split = ExpectedSkuName.trim().split(" - ", 2);
			String ExpectedProductNameOnly = (Split.length > 1) ? Split[1].trim() : ExpectedSkuName.trim();

			System.out.println("Asserting SKU_" + (I + 1) + " Name: Expected = " + ExpectedProductNameOnly + ", Actual = " + ActualSkuName);
			Assert.assertTrue(ExpectedSkuName.contains(ActualSkuName), 
					"After Calculation Mismatch in SKU name Actual:" + ActualSkuName +
					" and SKU name Expected:" + ExpectedProductNameOnly + " for SKU_" + (I + 1));
		}

		// === Step 3: Assert GrossWeight (Qty) ===
		List<String> SkuQuantityElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")
				);
		List<String> SkuNamesElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")
				);
		for (int I = 0; I < SkuQuantityElements.size(); I++) {
			String ActualQtyStr = SkuQuantityElements.get(I).trim().replaceAll("[^\\d.]", "");
			String ExpectedGrossWeightStr = ScannedSkuDataMap.get("SKU_" + (I + 1) + "_GrossWeight");
			Assert.assertNotNull(ExpectedGrossWeightStr, "Expected GrossWeight is null for SKU_" + (I + 1));

			double ActualQty = Double.parseDouble(ActualQtyStr);
			double ExpectedGrossWeight = Double.parseDouble(ExpectedGrossWeightStr.trim().replaceAll("[^\\d.]", ""));

			System.out.println("Asserting GrossWeight for SKU_" + (I + 1) +
					": Expected = " + ExpectedGrossWeight + ", Actual = " + ActualQty);
			Assert.assertEquals(Math.abs(ActualQty), Math.abs(ExpectedGrossWeight), 1,
					"After Calculation Mismatch in GrossWeight Actual:" + ActualQty +
					" and Expected:" + ExpectedGrossWeight + " for SKU_" + (I + 1));
		}

		// === Step 4 & 5: Assert Total Without Tax and Subtotal with plus/minus logic ===
		List<String> TotalWithoutTaxElements = base.GetElementTexts(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")
				);

		int FirstCount = Integer.parseInt(FirstHowManyIndex);
		int SecondCount = Integer.parseInt(SecondHowManyIndex);

		double FinalAdjustedSum = 0.0;
		double FinalAdjustedSumBeforeLast = 0.0;
		double SumofTotalWithoutTax = 0.0;      // excluding OD, OU, OT, OGT
		double SumofTotalWithoutTaxAll = 0.0;   // including OD, OU, OT, OGT

		for (int I = 0; I < TotalWithoutTaxElements.size(); I++) {
			int SkuNumber = I + 1;
			String ActualTotalStrRaw = TotalWithoutTaxElements.get(I).trim();
			String CleanValue = ActualTotalStrRaw.replaceAll("[^\\d.-]", ""); // keep minus for detection

			if (CleanValue.isEmpty()) {
				System.out.println("SKU_" + SkuNumber + " total empty, skipping");
				continue;
			}

			double Value = Double.parseDouble(CleanValue);
			double absValue = Math.abs(Value);

			// === Always update SumofTotalWithoutTaxAll (including OD, OU, OT, OGT) ===
			if (ActualTotalStrRaw.contains("-")) {
				SumofTotalWithoutTaxAll -= absValue;
				System.out.println("Subtracting (All) value for SKU_" + SkuNumber + ": -" + absValue);
			} else {
				SumofTotalWithoutTaxAll += absValue;
				System.out.println("Adding (All) value for SKU_" + SkuNumber + ": +" + absValue);
			}

			// === Inline exact-token exclusion: tokenize SKU name and check tokens ===
			String CurrentSkuName = SkuNamesElements.get(I); // keep original for logging
			String up = (CurrentSkuName == null) ? "" : CurrentSkuName.toUpperCase().trim();
			String[] tokens = up.split("[^A-Z0-9]+"); // split on non-alphanumeric, yields tokens
			boolean isExcluded = false;
			System.out.println("Tokens for SKU_" + SkuNumber + " (" + CurrentSkuName + "): " + String.join(", ", tokens));
			for (String t : tokens) {
				if (t.equals("OD") || t.equals("OU") || t.equals("OT") || t.equals("OGT") || t.equals("URD")) {
					isExcluded = true;
					break;
				}
			}

			if (isExcluded) {
				System.out.println("Skipping SKU_" + SkuNumber + " (" + CurrentSkuName + ") from excluded-sum calculation");

				// print running totals BEFORE continuing so excluded SKUs also show the totals.
				System.out.println("Running Sum of Total Without Tax (Excluding OD/OU/OT/OGT) = " + SumofTotalWithoutTax);
				System.out.println("Running Sum of Total Without Tax (Including All) = " + SumofTotalWithoutTaxAll);

				continue; // crucial: prevents excluded SKU from affecting SumofTotalWithoutTax
			}

			// === Update SumofTotalWithoutTax only for non-excluded SKUs ===
			if (ActualTotalStrRaw.contains("-")) {
				SumofTotalWithoutTax -= absValue;
				System.out.println("Subtracting (Excluding) value for SKU_" + SkuNumber + ": -" + absValue);
			} else {
				SumofTotalWithoutTax += absValue;
				System.out.println("Adding (Excluding) value for SKU_" + SkuNumber + ": +" + absValue);
			}

			// Print running totals for non-excluded SKUs
			System.out.println("Running Sum of Total Without Tax (Excluding OD/OU/OT/OGT) = " + SumofTotalWithoutTax);
			System.out.println("Running Sum of Total Without Tax (Including All) = " + SumofTotalWithoutTaxAll);

			// === Per-SKU total assertion (preserve existing logic) ===
			String ActualTotalStr = TotalWithoutTaxElements.get(I).trim().replaceAll("[^\\d.]", "");
			String ExpectedTotalStr = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");

			double ActualTotal = Double.parseDouble(ActualTotalStr);
			double ExpectedTotal = Double.parseDouble(ExpectedTotalStr.trim().replaceAll("[^\\d.]", ""));

			System.out.println("Asserting TOTAL (Without Tax) for SKU_" + SkuNumber +
					": Expected = " + ExpectedTotal + ", Actual = " + ActualTotal);
			Assert.assertEquals(Math.abs(ActualTotal), Math.abs(ExpectedTotal), 1,
					"After Calculation Mismatch in TOTAL (Without Tax) Actual:" + ActualTotal +
					" and Expected:" + ExpectedTotal + " for SKU_" + SkuNumber);
		} // end for-loop

		// === After loop: verify Subtotal, Tax, TotalAmount ===
		double ExpectedSubtotal = Double.parseDouble(TransactionDataLine.get("Subtotal").replaceAll("[^\\d.-]", ""));
		System.out.println("Asserting Final Adjusted TOTAL (Without Tax) vs Subtotal:");
		System.out.println("Expected Subtotal = " + ExpectedSubtotal + ", Final Adjusted Sum = " + SumofTotalWithoutTaxAll);
		Assert.assertEquals(Math.abs(SumofTotalWithoutTaxAll), Math.abs(ExpectedSubtotal), 1,
				"After Calculation Mismatch in Adjusted Total (Without Tax) Actual:" + SumofTotalWithoutTaxAll +
				" and Expected Subtotal:" + ExpectedSubtotal);

		// === Step 6: TAX = 3% of Subtotal (based on excluding-sum per your business rule) ===
		double CalculatedTax = Math.round((SumofTotalWithoutTax * 0.03) * 100.0) / 100.0; // Non negatives
		double ExpectedTax = Double.parseDouble(TransactionDataLine.get("Tax").replaceAll("[^\\d.-]", ""));
		System.out.println("Asserting calculated 3% TAX vs displayed Tax:");
		System.out.println("Expected Tax = " + ExpectedTax + ", Calculated Tax = " + CalculatedTax);
		Assert.assertEquals(Math.abs(CalculatedTax), Math.abs(ExpectedTax), 1,
				"After Calculation Mismatch in calculated Tax " + CalculatedTax +
				" and Expected Tax value is " + ExpectedTax);

		// === Step 7: TotalAmount = Subtotal + Tax ===
		double CalculatedTotalAmount = Math.round((ExpectedSubtotal + ExpectedTax) * 100.0) / 100.0;
		double ExpectedTotalAmount = Double.parseDouble(TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.-]", ""));
		System.out.println("Asserting Total Amount (Subtotal + Tax) with displayed TotalAmount:");
		System.out.println("Expected TotalAmount = " + ExpectedTotalAmount + ", Calculated TotalAmount = " + CalculatedTotalAmount);
		Assert.assertEquals(Math.abs(CalculatedTotalAmount), Math.abs(ExpectedTotalAmount), 1,
				"After Calculation Mismatch in Total Amount Calculated " + CalculatedTotalAmount +
				" and Expected Total Amount " + ExpectedTotalAmount);



		System.out.println("====== Validation Completed for Transaction Page Line ======");
		return ActualSkuNamesList;
	}


	/// <summary>
	/// To Set reduced metal rate in ERP for Gold 24k,22k,18K
	/// Author : Chandana Babu
	/// </summary>
	public String ToUpdateMetalRateWithLowerValue(String SkuGoldRateReturn, String SkuPurityReturn) throws Exception
	{
		double OriginalRate = Double.parseDouble(SkuGoldRateReturn);
		double Purity = 0;

		if (SkuPurityReturn.contains("24")) {
			Purity = 24;
		} else if (SkuPurityReturn.contains("22")) {
			Purity = 22;
		} else if (SkuPurityReturn.contains("18")) {
			Purity = 18;
		} else {
			System.out.println("Unsupported purity: " + SkuPurityReturn);
			Purity = 24; 
		}

		double GoldRateAs24K = (OriginalRate / Purity) * 24;
		long RoundedMetalRate = Math.round(GoldRateAs24K - 50);
		String ConvertedMetalRate = String.valueOf(RoundedMetalRate);
		System.out.println("Converted Metal Rate: " + ConvertedMetalRate);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		//Step 1.Go to ERP of that specific Legal Entity
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(Tabs.get(1));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.navigate().to(UtilityTestData.ERPURL);

		driver.navigate().refresh();
		//Step 2.Search Metal rate
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMetalRate);
		base.setZoom(driver, 85);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step 3. Click on New
		Thread.sleep(5000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));

		//Step 4.Accounted under as 'Gold'
		//Step 5.Store/Non-store as 'Store'
		//Step 6.Select the required warehouse
		Thread.sleep(6000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventLocationId"));
		Thread.sleep(6000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventLocationId"), UtilityTestData.WarehouseId);
		Thread.sleep(6000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.WarehouseId,"Warehouse"));

		//Step 7.Select the Configuration as "24k"
		//Step 8.Enter the 24k Board Rate in Sales, click Enter
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EcoResConfiguraionName"));
		Thread.sleep(6000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EcoResConfiguraionName"), UtilityTestData.Purity24);
		Thread.sleep(6000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.Purity24,"Configuration"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"));
		Thread.sleep(6000);
		base.clearData((NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate")));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"), ConvertedMetalRate);
		Thread.sleep(6000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"), "ENTER");

		//Step 9.Click on OK
		base.buttonClick(LoginPageObj.Edt_Alert("OK"));

		//Step 10. Click on New
		Thread.sleep(5000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));

		//Step 11.Accounted under as 'Gold'
		//Step 12.Store/Non-store as 'Store'
		//Step 13.Select the required warehouse
		Thread.sleep(6000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventLocationId"));
		Thread.sleep(6000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventLocationId"), UtilityTestData.WarehouseId);
		Thread.sleep(6000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.WarehouseId,"Warehouse"));

		//Step 14.Select the Configuration as "22k"
		//Step 15.Enter the 24k Board Rate in Sales, click Enter
		Thread.sleep(6000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EcoResConfiguraionName"));
		Thread.sleep(6000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EcoResConfiguraionName"), UtilityTestData.Purity22);
		Thread.sleep(6000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.Purity22,"Configuration"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"));
		base.clearData((NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate")));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"), ConvertedMetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"), "ENTER");

		//Step 16.Click on OK
		Thread.sleep(6000);
		base.buttonClick(LoginPageObj.Edt_Alert("OK"));

		//Step 17. Click on New
		Thread.sleep(5000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing New-symbol"));

		//Step 18.Accounted under as 'Gold'
		//Step 19.Store/Non-store as 'Store'
		//Step 20.Select the required warehouse
		Thread.sleep(6000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventLocationId"));
		Thread.sleep(6000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventLocationId"), UtilityTestData.WarehouseId);
		Thread.sleep(6000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.WarehouseId,"Warehouse"));
		Thread.sleep(6000);

		//Step 21.Select the Configuration as "18K"
		//Step 22.Enter the 24k Board Rate in Sales, click Enter
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EcoResConfiguraionName"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EcoResConfiguraionName"), UtilityTestData.Erp_Purity18);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseCode(UtilityTestData.Erp_Purity18,"Configuration"));
		Thread.sleep(500);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"));
		base.clearData((NormalSaleGoldAndSilverObj.Ele_SalesReturnErpDual("SalesRate")));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"), ConvertedMetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SalesRate"), "ENTER");

		//Step 23.Click on OK
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Edt_Alert("OK"));

		((JavascriptExecutor) driver).executeScript("window.open()");
		driver.switchTo().window(Tabs.get(1));
		driver.navigate().to(UtilityTestData.ERPURL);

		//step 24.In ERP, Go to Distribution Schedule
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step 25.Filter the job ""N-METALRATE"" and click on ""Run now""
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), UtilityTestData.NmetalRate);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		base.excuteJsClick(LoginPageObj.Edt_Alert("OK"));

		((JavascriptExecutor) driver).executeScript("window.open()");
		driver.switchTo().window(Tabs.get(1));
		driver.navigate().to(UtilityTestData.ERPURL);

		boolean jobEnded = false;
		int maxAttempts = 8; // 1 normal + 1 retry

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Step 26: Search My Batch Job
				//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

				// Step 27: Sort by "Scheduled start date/time"
				Thread.sleep(2000);
				By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Step 28: Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				jobEnded = true;
				break; // âœ… success, break the loop

			} catch (StaleElementReferenceException stale) {
				System.out.println("Attempt " + attempt + ": Stale element. Retrying after refresh...");
				if (attempt < maxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Stale element not resolved after retries", stale);
				}
			} catch (TimeoutException timeout) {
				System.out.println("Attempt " + attempt + ": Timeout while waiting for 'Ended' job status.");
				if (attempt < maxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
		}
		driver.switchTo().window(Tabs.get(0));
		return ConvertedMetalRate;
	}

	/// <summary>
	/// To verify Wrongly Created mature GPP
	/// Author: Nivya
	/// <summary>
	public void WronglyCreatedMatureGPP(int MaxAttemptsNo) throws Exception {
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.WronglyCreatedGPP);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(2000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("OK"));

		// Open new tab
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.navigate().to(UtilityTestData.ERPURL);

		boolean JobEnded = false;
		int MaxAttempts = MaxAttemptsNo;

		for (int Attempt = 1; Attempt <= MaxAttempts; Attempt++) {
			try {
				// Search My Batch Job
				//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

				// Wait for "Ended" job
				FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20))
						.ignoring(StaleElementReferenceException.class)
						.pollingEvery(Duration.ofSeconds(2));

				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				JobEnded = true;

				driver.switchTo().window(tabs.get(0));
				System.out.println("Job ended successfully. Switched back to first tab.");
				break;

			} catch (StaleElementReferenceException stale) {
				System.out.println("Attempt " + Attempt + ": Stale element. Retrying after refresh...");
				if (Attempt < MaxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Stale element not resolved after retries", stale);
				}
			} catch (TimeoutException timeout) {
				System.out.println("Attempt " + Attempt + ": Timeout while waiting for 'Ended' job status.");
				if (Attempt < MaxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
		}

		if (!JobEnded) {
			throw new RuntimeException("Job did not reach 'Ended' status after max attempts.");
		}
	}	

	/// <summary>
	/// To Update query for Normal Advance, Offer Advance, scheme mapping
	/// Author : Christy Reji
	/// </summary>
	public List<String> ERPQueryUpdate(String MappingQuery, String CustomerAcc) throws InterruptedException {

		// Window handles 
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());
		List<String> TransactionIDs = new ArrayList<>();

		//Normal advance mapping- Fetching transaction IDs
		if (MappingQuery.equals("NormalAdvance")) { 
			// Get transaction numbers
			List<String> TxnNumbers = GetTransactionNumber();
			String FirstTxn  = TxnNumbers.get(0);
			String SecondTxn = TxnNumbers.get(1);
			// String ThirdTxn  = TxnNumbers.get(2);

			// Store them in return list
			TransactionIDs.add(FirstTxn);
			TransactionIDs.add(SecondTxn);
			//  txnNumbers.add(ThirdTxn);

			System.out.println("Using FirstTxn No : in query " + FirstTxn);
			System.out.println("Using SecondTxn No: in query : " + SecondTxn);
			// System.out.println("Using ThirdTxn No : in query : " + ThirdTxn);

			// Navigating to ERP_Query 
			((JavascriptExecutor) driver).executeScript("window.open()");
			Tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(Tabs.get(1)); // switch to new tab for query update
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));

			// Navigate to ERP URL
			driver.navigate().to(UtilityTestData.ERPQuery_URL);
			//	driver.navigate().to("https://m365-perf.sandbox.operations.dynamics.com/?cmp=mgpl&mi=sysclassrunner&cls=MGDSQLBrowserHelper");
			driver.navigate().refresh();

			// Prepare query with fetched Txn numbers and CustomerAcc
			String AdvanceQuery = "UPDATE MGDRETAILDEPOSITTABLE " +
					"SET CUSTACCOUNT = '"+CustomerAcc+"' " +
					"WHERE TRANSACTIONID IN ('" + FirstTxn + "','" + SecondTxn + "');";

			System.out.println("Final Query_Advance: " + AdvanceQuery);

			base.ClickClearEnter(LoginPageObj.Ele_ERPQuery("textArea"), AdvanceQuery);
			base.ClickClearEnter(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Fld2_1_input"), "advance");
		}
		else if (MappingQuery.equals("OfferAdvance")) {
			//Need to write the query
		}
		else if (MappingQuery.equals("SchemeMapping")) {
			//Need to write the query
		}

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("toggle-thumb"));
		Thread.sleep(1000);
		base.buttonClick(CustomerCreationObj.Ele_BankAccounts("OkButton_l"));
		base.isExists(LoginPageObj.Edt_Alert("Done"));
		System.out.println("============ERP_Query : "+MappingQuery+ " updated successfully==============");    

		//Switch back to the first/original tab
		driver.switchTo().window(Tabs.get(0));

		// Return the transaction numbers
		return TransactionIDs;
	}

	/// <summary>
	/// To Fetch Transaction from Retail Deposit Details page
	//Filtering Store number-5011, adjusted-No
	/// Author : Christy Reji
	/// </summary>
	public List<String> GetTransactionNumber() throws InterruptedException {

		// Step 1. Go to ERP 
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(Tabs.get(1));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.navigate().to(UtilityTestData.ERPURL);

		driver.navigate().refresh();

		// Step 2. Search Retail Deposit Details
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Retail Deposit Details");
		base.setZoom(driver, 85);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(3000);

		// Store filter-5011
		base.Hover(LoginPageObj.Edt_AlertText("Store number"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Store number"));
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("__FilterField_MGDRetailDepositTable_Store_Store_Input"), "5011");
		base.buttonClick(LoginPageObj.Btn_SingnIn("__MGDRetailDepositTable_Store_ApplyFilters"));
		Thread.sleep(3000);

		//Is locked filter-No
		ScrollIntoView(LoginPageObj.Edt_AlertText("Is locked"));
		base.Hover(LoginPageObj.Edt_AlertText("Is locked"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Is locked"));
		base.buttonClick(LoginPageObj.Ele_ERPAdjust("list"));
		base.buttonClick(LoginPageObj.Ele_ERPYes("No"));
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("__MGDRetailDepositTable_IsLocked_ApplyFilters"));
		Thread.sleep(4000);

		// Scroll to right until Deposit Type column is visible
		//Deposit type- Normal
		ScrollIntoView(LoginPageObj.Edt_AlertText("Deposit type"));
		Thread.sleep(3000);
		base.Hover(LoginPageObj.Edt_AlertText("Deposit type"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Deposit type"));
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("DepositType_DepositType_Input"), "Normal");
		base.buttonClick(LoginPageObj.Ele_ERPYes("Normal"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("__MGDRetailDepositTable_DepositType_ApplyFilters"));
		Thread.sleep(4000);

		// Scroll to right until Adjusted column is visible
		ScrollIntoView(LoginPageObj.Edt_AlertText("Is adjusted"));
		Thread.sleep(3000);
		// Adjusted - No 
		base.Hover(LoginPageObj.Edt_AlertText("Is adjusted"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Is adjusted"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("IsAdjusted_IsAdjusted_Input"), "No");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Purchase("__FilterField_MGDRetailDepositTable_IsAdjusted_IsAdjusted_0_title"));
		Thread.sleep(3000);
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("__MGDRetailDepositTable_IsAdjusted_ApplyFilters"));
		Thread.sleep(8000);
		base.Hover(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("ScrollbarLayout_main ScrollbarLayout_mainHorizontal"));

		// Fetching transaction IDs
		List<WebElement> TransactionNo = driver.findElements(LoginPageObj.Ele_TransactionID("MGDRetailDepositTable_TransactionId"));

		//Extract all numbers
		List<String> allTxnNumbers = new ArrayList<>();
		for (WebElement txn : TransactionNo) {
			String value = txn.getAttribute("value");
			if (value != null && value.startsWith("33")) {   // Step 2: keep only starting with 33
				allTxnNumbers.add(value);
			}
		}

		// Safety check
		if (allTxnNumbers.size() < 3) {
			throw new RuntimeException("Not enough transactions starting with 33. Found only: " + allTxnNumbers.size());
		}

		// Step 3: Pick random 3 unique numbers
		Collections.shuffle(allTxnNumbers);   // shuffle the list
		String FirstTxn  = allTxnNumbers.get(0);
		String SecondTxn = allTxnNumbers.get(1);
		String ThirdTxn  = allTxnNumbers.get(2);

		System.out.println("Random Transaction 1: " + FirstTxn);
		System.out.println("Random Transaction 2: " + SecondTxn);

		// Step 4: Return them
		List<String> TxnNumbers = new ArrayList<>();
		TxnNumbers.add(FirstTxn);
		TxnNumbers.add(SecondTxn);
		TxnNumbers.add(ThirdTxn);

		return TxnNumbers;
	}

	/// <summary>
	/// Method to scroll to element until element view

	/// Author : Vishnu Manoj
	public void ScrollIntoView(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Scroll into view
			WebElement elementToScroll = driver.findElement(locator);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToScroll);

			// Ensure element is visible & clickable before control returns
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));

			System.out.println(" Successfully scrolled into view: " + locator.toString());

		} catch (Exception e) {
			System.out.println(" Failed to scroll into view: " + locator.toString());
			e.printStackTrace();
		}


	}
	/// <summary>
	/// To find Matured  GBScheme through Filter
	/// Author: Sangeetha M S & VishnuManoj K
	/// <summary>
	public Map<String, Object> GPPMaturedAccountDetails(double Installment, int MaxGppAccounts,String SchemeCode, String MaturedScheme, String AdjustedScheme ) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();
		// Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// Search GPP Account opening
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// Sort Account opening date
		base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Account opening date"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Account opening date"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));
		Thread.sleep(5000);

		// Select Scheme Code
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheme code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_SchemeCode_SchemeCode"),SchemeCode );
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_SchemeCode_ApplyFilters_label", "Apply"));

		// Sort Total Amount Paid
		Thread.sleep(8000);
		//base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Total amount paid"));
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Total amount paid"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_TotalAmountPaid_TotalAmountPaid"),
				5,
				2
				);
		Thread.sleep(3000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Sort largest to smallest"));
		Thread.sleep(10000);

		// Scroll horizontally
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(25000);

		// Store Number Filter
		//base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"),
				5,
				2
				);
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		Thread.sleep(7000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));

		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RetailStoreId_ApplyFilters_label", "Apply"));



		//		// Matured Field
		//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Matured"));
		//		Thread.sleep(5000);
		//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"),MaturedScheme );
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"));
		//		Thread.sleep(5000);
		//		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Matured_ApplyFilters_label", "Apply"));

		Thread.sleep(5000);
		// Matured Field
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Matured"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CartMoney("lookupDock-dockContainer lookupDock-comboBox displayoption hasFocusedChild","lookupButton"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_DistrSched("__FilterField_Grid_Matured_Matured_Input_0_0_list_item1"));
		//base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"),MaturedScheme );
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"));
		Thread.sleep(5000);
		base.excuteJsClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Matured_ApplyFilters_label", "Apply"));

		// Adjusted Field
		Thread.sleep(5000);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Adjusted"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Adjusted_ApplyFilters_label", "Apply"),
				5,
				2
				);
		//base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Adjusted"));
		Thread.sleep(5000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Adjusted_Adjusted"), AdjustedScheme);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_DistrSched("__FilterField_Grid_Adjusted_Adjusted_Input_0_0_list_item0"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Adjusted_ApplyFilters_label", "Apply"));
		Thread.sleep(20000);

		// Result Map
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		//List<String> GppAccountNos = new ArrayList<>();
		Set<String> Seen = new HashSet<>();

		final By Grid = By.cssSelector("div[role='grid'][aria-label='GPP account opening']");
		final By VScrollFace = By.cssSelector(".ScrollbarLayout_mainVertical .ScrollbarLayout_face");
		final By TotalPaidLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Total amount paid']");
		final By EmiLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='EMI amount']");
		final By GppAccLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='GPP account no']");
		final By CustomerAccLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Customer account']");

		List<Map<String, String>> MatchedAccounts = new ArrayList<>();


		WebDriverWait WaitObj = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));

		int ScrollCount = 0;
		int NoMovementTries = 0;

		java.util.function.Supplier<String> TopKey = () -> {
			try {
				List<WebElement> Keys = driver.findElements(GppAccLocator);
				if (!Keys.isEmpty()) return Keys.get(0).getAttribute("value");
			} catch (Exception ignored) {}
			return "";
		};

		java.util.function.BiPredicate<Double, Double> ApproxEq =
				(a, b) -> Math.abs(a - b) < 0.0001;

				while (MatchedAccounts.size() < MaxGppAccounts && ScrollCount < 120) {
					System.out.println("Scroll iteration: " + ScrollCount);

					List<WebElement> TotalPaidList = driver.findElements(TotalPaidLocator);
					List<WebElement> EmiList = driver.findElements(EmiLocator);
					List<WebElement> GppAccList = driver.findElements(GppAccLocator);

					for (int i = 0; i < Math.min(TotalPaidList.size(), Math.min(EmiList.size(), GppAccList.size())); i++) {
						try {
							String TotalPaidStr = TotalPaidList.get(i).getAttribute("value").replace(",", "").trim();
							String EmiStr = EmiList.get(i).getAttribute("value").replace(",", "").trim();
							String GppAccNo = GppAccList.get(i).getAttribute("value").trim();
							String CustomerAccNo = driver.findElements(CustomerAccLocator).get(i).getAttribute("value").trim();

							if (!TotalPaidStr.isEmpty() && !EmiStr.isEmpty() && !GppAccNo.isEmpty() && !CustomerAccNo.isEmpty()) {
								double TotalPaid = Double.parseDouble(TotalPaidStr);
								double EmiVal = Double.parseDouble(EmiStr);
								double CurrentInstallment = (EmiVal > 0) ? (TotalPaid / EmiVal) : 0.0;

								if (EmiVal > 0 && ApproxEq.test(CurrentInstallment, Installment) && !Seen.contains(GppAccNo)) {
									Map<String, String> AccountMap = new HashMap<>();
									AccountMap.put("GppAccountNo", GppAccNo);
									AccountMap.put("CustomerAccountNo", CustomerAccNo);
									AccountMap.put("GppAmount", TotalPaidStr);
									MatchedAccounts.add(AccountMap);
									Seen.add(GppAccNo);

									if (MatchedAccounts.size() >= MaxGppAccounts) break;
								}
							}
						} catch (Exception e) {
							System.out.println("Error parsing row: " + e.getMessage());
						}
					}

					if (MatchedAccounts.size() >= MaxGppAccounts) break;

					String Before = TopKey.get();
					boolean Moved = false;

					try {
						WebElement GridEl = WaitObj.until(d -> d.findElement(Grid));
						((JavascriptExecutor) driver).executeScript(
								"var el = arguments[0];" +
										"var e = new WheelEvent('wheel', {deltaY: arguments[1], bubbles: true});" +
										"el.dispatchEvent(e);", GridEl, 800);
						Thread.sleep(600);
						String After = TopKey.get();
						Moved = (After != null && !After.isEmpty() && !After.equals(Before));
					} catch (Exception ignored) {}

					if (!Moved) {
						try {
							WebElement Face = WaitObj.until(d -> d.findElement(VScrollFace));
							new Actions(driver).clickAndHold(Face).moveByOffset(0, 180).release().perform();
							Thread.sleep(700);
							String After = TopKey.get();
							Moved = (After != null && !After.isEmpty() && !After.equals(Before));
						} catch (Exception ignored) {}
					}

					if (!Moved) {
						try {
							List<WebElement> Cells = driver.findElements(GppAccLocator);
							WebElement FocusCell = !Cells.isEmpty() ? Cells.get(Math.min(1, Cells.size() - 1)) : null;
							if (FocusCell != null) {
								new Actions(driver).moveToElement(FocusCell).click().perform();
							} else {
								WebElement GridEl = WaitObj.until(d -> d.findElement(Grid));
								new Actions(driver).moveToElement(GridEl, 10, 10).click().perform();
							}
							new Actions(driver).sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();
							Thread.sleep(700);
							String After = TopKey.get();
							Moved = (After != null && !After.isEmpty() && !After.equals(Before));
						} catch (Exception ignored) {}
					}

					ScrollCount++;
					if (Moved) {
						NoMovementTries = 0;
						System.out.println("Grid moved, continuing scroll");
					} else {
						NoMovementTries++;
						System.out.println("Grid did not move, attempt " + NoMovementTries);
						if (NoMovementTries >= 3) break;
					}
				}
				if (Installment != 11) {
					Collections.reverse(MatchedAccounts);
				} else {

				}
				ResultMap.put("MatchedAccounts", MatchedAccounts);
				ResultMap.put("TotalFound", MatchedAccounts.size());

				//					ResultMap.put("MatchedAccounts", GppAccountNos);
				//					ResultMap.put("TotalFound", GppAccountNos.size());
				System.out.println("Finished scanning. Total Matched=" + MatchedAccounts.size());
				System.out.println(MatchedAccounts);
				driver.switchTo().window(OriginalWindow);
				return ResultMap;

	}
	/// <summary>
	/// To find values in Matured GPP Accounts(Purity, GoldRate, Weight)
	/// Author: VishnuManoj K & Chandana
	/// <summary>
	public Map<String, Object> GPPMaturityValues(String GppAccountNo) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();
		// Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// Search GPP Account opening
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.MaturedGppDetails);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(8000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "GPP account no"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_GPPAccountNo"), GppAccountNo);//_GPPAccountNo_Input_0
		Thread.sleep(2000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_GPPAccountNo_ApplyFilters_label", "Apply"));
		Thread.sleep(5000);
		String Purity= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Default config id"));
		String TotalQty= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Total qty")); 
		String AvgRate= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Metal cost amount")); 
		String TotalAmount= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Total amount")); 
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		ResultMap.put("Purity", Purity);
		ResultMap.put("TotalQty", TotalQty);
		ResultMap.put("AvgRate", AvgRate);
		ResultMap.put("Amount", TotalAmount);
		// print the ResultMap
		System.out.println("ResultMap contents:");
		for (Map.Entry<String, Object> entry : ResultMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		driver.switchTo().window(OriginalWindow);
		return ResultMap;
	}


	/// <summary>
	/// To find ongoing GGScheme for discount details
	/// Author: Neethu
	/// <summary>
	public GPPAccountDiscountDetails ToGetDiscountOnGoingGGScheme() throws Exception
	{
		//Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		//Search GPP Account opening
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Select SchemeCode(GG),set and apply
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Scheme code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_SchemeCode_SchemeCode"),UtilityTestData.SchemeCode);
		Thread.sleep(8000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_SchemeCode_ApplyFilters_label","Apply"));
		Thread.sleep(40000);
		//base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);

		//			//Select Account opening date(Oldest to newest)
		//			Thread.sleep(20000);
		//			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Account opening date"));
		//			Thread.sleep(20000);
		//			base.buttonClick(LoginPageObj.Edt_Alert("Sort oldest to newest"));
		//			Thread.sleep(2000);

		// Scroll horizontally
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(10000);

		// Store Number Filter
		this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		Thread.sleep(9000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RetailStoreId_ApplyFilters_label", "Apply"));
		Thread.sleep(9000);

		// Scroll horizontally
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();
		Thread.sleep(4000);

		//Select Matured Field(No),Set and apply
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Matured"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"),UtilityTestData.GPPAdjustMatured);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Matured_ApplyFilters_label","Apply"));	

		//Select Adjusted Field(No),Set and apply
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Adjusted"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Adjusted_Adjusted"),UtilityTestData.GPPAdjustMatured);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Adjusted_ApplyFilters_label","Apply"));	
		Thread.sleep(5000);

		//scroll right to left
		Actions action = new Actions(driver);
		WebElement scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Thread.sleep(1000);
		action.clickAndHold(scrollbar)
		.moveByOffset(-850, 0)
		.release()
		.perform();

		//Select Account opening date(Oldest to newest)
		Thread.sleep(20000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent","Account opening date"));
		Thread.sleep(20000);
		base.buttonClick(LoginPageObj.Edt_Alert("Sort oldest to newest"));
		Thread.sleep(2000);

		String CustomerAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_CustAccount"));	
		System.out.println(CustomerAccount);
		String GppAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_GPPAccountNo"));	
		System.out.println(GppAccount);
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());		
		driver.switchTo().window(Tabs.get(0));	

		return new GPPAccountDiscountDetails(CustomerAccount, GppAccount);
	}

	public class GPPAccountDiscountDetails {
		public String CustomerAccount;
		public String GppAccount;

		public GPPAccountDiscountDetails(String CustomerAccount, String GppAccount) {
			this.CustomerAccount = CustomerAccount;
			this.GppAccount = GppAccount;
		}
	}

	/// <summary>
	/// To find Matured  GScheme through Filter
	/// Author: Bharath
	/// <summary>
	public Map<String, Object> GSMaturedAccountDetails(double Installment, int MaxGppAccounts,String SchemeCode, String MaturedScheme, String AdjustedScheme ) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();
		// Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// Search GPP Account opening
		////base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// Sort Account opening date
		base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Account opening date"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Account opening date"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));
		Thread.sleep(5000);

		// Select Scheme Code
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheme code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_SchemeCode_SchemeCode"),SchemeCode );
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_SchemeCode_ApplyFilters_label", "Apply"));

		// Sort Total Amount Paid
		Thread.sleep(5000);
		// base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Total amount paid"));
		for(int i=0;i<5;i++)
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Total amount paid"));
			if(base.isExists(By.xpath("//div[@data-dyn-form-name='Grid_TotalAmountPaid' and @data-dyn-lastvisible='true']")))
			{
				break;
			}
		}
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_Alert("Sort largest to smallest"));
		Thread.sleep(10000);

		// Store Number Filter
		base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"),
				5,
				2
				);

		Thread.sleep(7000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		for(int i=0;i<5;i++)
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
			if(base.isExists(By.xpath("//div[@data-dyn-form-name='Grid_RetailStoreId' and @data-dyn-lastvisible='true']")))
			{
				break;
			}
		}
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RetailStoreId_ApplyFilters_label", "Apply"));

		// Scroll horizontally
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(25000);

		// Matured Field
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Matured"));
		Thread.sleep(5000);
		for(int i=0;i<5;i++)
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Matured"));
			if(base.isExists(By.xpath("//div[@data-dyn-form-name='Grid_Matured' and @data-dyn-lastvisible='true']")))
			{
				break;
			}
		}
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Matured_Matured"),MaturedScheme );
		Thread.sleep(10000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_DistrSched("__FilterField_Grid_Matured_Matured_Input_0_0_list_item0"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Matured_ApplyFilters_label", "Apply"));

		// Adjusted Field
		Thread.sleep(5000);
		//		appUtils.ClickWithRetry(
		//				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Adjusted"),
		//				NormalSaleGoldAndSilverObj.Ele_DistrSched("__FilterField_Grid_Adjusted_Adjusted_Input_0_0_list_item0"),
		//				5,
		//				2
		//				);
		//		//				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Adjusted"));
		//		//				Thread.sleep(5000);
		for(int i=0;i<5;i++)
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Adjusted"));
			Thread.sleep(3000);
			if(base.isPresent(By.xpath("//div[@data-dyn-form-name='Grid_Adjusted' and @data-dyn-lastvisible='true']")))
			{
				break;
			}
		}
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Adjusted_Adjusted"), AdjustedScheme);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_DistrSched("__FilterField_Grid_Adjusted_Adjusted_Input_0_0_list_item0"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Adjusted_ApplyFilters_label", "Apply"));

		Thread.sleep(10000);
		// Result Map
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		//List<String> GppAccountNos = new ArrayList<>();
		Set<String> Seen = new HashSet<>();

		final By Grid = By.cssSelector("div[role='grid'][aria-label='GPP account opening']");
		final By VScrollFace = By.cssSelector(".ScrollbarLayout_mainVertical .ScrollbarLayout_face");
		final By TotalPaidLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Total amount paid']");
		final By EmiLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='EMI amount']");
		final By GppAccLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='GPP account no']");
		final By CustomerAccLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Customer account']");
		final By AccDate = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Account opening date']");

		List<Map<String, String>> MatchedAccounts = new ArrayList<>();


		WebDriverWait WaitObj = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));

		int ScrollCount = 0;
		int NoMovementTries = 0;

		java.util.function.Supplier<String> TopKey = () -> {
			try {
				List<WebElement> Keys = driver.findElements(GppAccLocator);
				if (!Keys.isEmpty()) return Keys.get(0).getAttribute("value");
			} catch (Exception ignored) {}
			return "";
		};

		java.util.function.BiPredicate<Double, Double> ApproxEq =
				(a, b) -> Math.abs(a - b) < 0.0001;

				while (MatchedAccounts.size() < MaxGppAccounts && ScrollCount < 120) {
					System.out.println("Scroll iteration: " + ScrollCount);

					List<WebElement> TotalPaidList = driver.findElements(TotalPaidLocator);
					List<WebElement> EmiList = driver.findElements(EmiLocator);
					List<WebElement> GppAccList = driver.findElements(GppAccLocator);
					List<WebElement> AccOpeningDate = driver.findElements(AccDate);

					for (int i = 0; i < Math.min(TotalPaidList.size(), Math.min(EmiList.size(), GppAccList.size())); i++) {
						try {
							String TotalPaidStr = TotalPaidList.get(i).getAttribute("value").replace(",", "").trim();
							String EmiStr = EmiList.get(i).getAttribute("value").replace(",", "").trim();
							String GppAccNo = GppAccList.get(i).getAttribute("value").trim();
							String CustomerAccNo = driver.findElements(CustomerAccLocator).get(i).getAttribute("value").trim();
							String AccOpening = AccOpeningDate.get(i).getAttribute("value").trim();

							if (!TotalPaidStr.isEmpty() && !EmiStr.isEmpty() && !GppAccNo.isEmpty() && !CustomerAccNo.isEmpty()) {
								double TotalPaid = Double.parseDouble(TotalPaidStr);
								double EmiVal = Double.parseDouble(EmiStr);
								DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
								LocalDate CurrentDate = LocalDate.now();
								LocalDate AccopeningDate = LocalDate.parse(AccOpening, Formatter);
								long CurrentInstallmentMonth = ChronoUnit.MONTHS.between(AccopeningDate, CurrentDate);
								double CurrentInstallment = Math.max(5.0, Math.min(11.0, CurrentInstallmentMonth));


								if (EmiVal > 0 && ApproxEq.test((double)CurrentInstallment, Installment) && !Seen.contains(GppAccNo)) {
									Map<String, String> AccountMap = new HashMap<>();
									AccountMap.put("GppAccountNo", GppAccNo);
									AccountMap.put("CustomerAccountNo", CustomerAccNo);
									AccountMap.put("GppAmount", TotalPaidStr);
									MatchedAccounts.add(AccountMap);
									Seen.add(GppAccNo);

									if (MatchedAccounts.size() >= MaxGppAccounts) break;
								}
							}
						} catch (Exception e) {
							System.out.println("Error parsing row: " + e.getMessage());
						}
					}

					if (MatchedAccounts.size() >= MaxGppAccounts) break;

					String Before = TopKey.get();
					boolean Moved = false;

					try {
						WebElement GridEl = WaitObj.until(d -> d.findElement(Grid));
						((JavascriptExecutor) driver).executeScript(
								"var el = arguments[0];" +
										"var e = new WheelEvent('wheel', {deltaY: arguments[1], bubbles: true});" +
										"el.dispatchEvent(e);", GridEl, 800);
						Thread.sleep(600);
						String After = TopKey.get();
						Moved = (After != null && !After.isEmpty() && !After.equals(Before));
					} catch (Exception ignored) {}

					if (!Moved) {
						try {
							WebElement Face = WaitObj.until(d -> d.findElement(VScrollFace));
							new Actions(driver).clickAndHold(Face).moveByOffset(0, 180).release().perform();
							Thread.sleep(700);
							String After = TopKey.get();
							Moved = (After != null && !After.isEmpty() && !After.equals(Before));
						} catch (Exception ignored) {}
					}

					if (!Moved) {
						try {
							List<WebElement> Cells = driver.findElements(GppAccLocator);
							WebElement FocusCell = !Cells.isEmpty() ? Cells.get(Math.min(1, Cells.size() - 1)) : null;
							if (FocusCell != null) {
								new Actions(driver).moveToElement(FocusCell).click().perform();
							} else {
								WebElement GridEl = WaitObj.until(d -> d.findElement(Grid));
								new Actions(driver).moveToElement(GridEl, 10, 10).click().perform();
							}
							new Actions(driver).sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();
							Thread.sleep(700);
							String After = TopKey.get();
							Moved = (After != null && !After.isEmpty() && !After.equals(Before));
						} catch (Exception ignored) {}
					}

					ScrollCount++;
					if (Moved) {
						NoMovementTries = 0;
						System.out.println("Grid moved, continuing scroll");
					} else {
						NoMovementTries++;
						System.out.println("Grid did not move, attempt " + NoMovementTries);
						if (NoMovementTries >= 3) break;
					}
				}
				ResultMap.put("MatchedAccounts", MatchedAccounts);
				ResultMap.put("TotalFound", MatchedAccounts.size());

				//					ResultMap.put("MatchedAccounts", GppAccountNos);
				//					ResultMap.put("TotalFound", GppAccountNos.size());
				System.out.println("Finished scanning. Total Matched=" + MatchedAccounts.size());
				System.out.println(MatchedAccounts);
				driver.switchTo().window(OriginalWindow);
				return ResultMap;

	}


	/// <summary>
	/// To create RTGS account with enough balance in ERP
	/// Author: Hasna & Benson
	/// <summary>
	public Map<String, String> RtgsAccountCreation(String AmountToDeposit, String CustomerId) throws Exception
	{
		String OriginalWindow = driver.getWindowHandle();

		// Step 1: Go to ERP and search RTGS payment receipt details
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		base.isExists(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.RtgsPaymentReceiptSearch); //pass this as utility testdata later ie RTGS payement receipt details
		Thread.sleep(5000); 
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step 2: Click on New
		Thread.sleep(3000);  
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label","New"));
		Thread.sleep(5000); 

		//Step 3:Enter Entry date
		base.isExists(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Entry date", 1));
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Entry date", 1));
		String CurrentDate = appUtils.getCurrentDate("MM/dd/yyyy"); 
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Entry date", 1),CurrentDate);

		//Step 4: Enter UTR number
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("UTR number",1));
		String UtrNumber = base.CreateRandom(7);
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("UTR number",1), UtrNumber);

		//Step 5: Enter Amount 
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Amount",1));
		Thread.sleep(2000);

		//		base.pressKey(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Amount",1), "CONTROL_A");
		//		base.pressKey(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Amount",1), "Backspace"); 

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", driver.findElement(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Amount",1)));

		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Amount",1), AmountToDeposit);

		//Step 6: Provide Bank Account
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Bank account",1));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Bank account",1),UtilityTestData.RtgsBankAccountNumber); 

		//Step 7: Enter Remarks
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Remarks",1));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Remarks",1),UtilityTestData.RtgsRemarks); 

		//Step 8: Enter Cost center
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Cost center",1));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Cost center",1),UtilityTestData.WarehouseId); 
		base.pressKey(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Cost center",1), "ENTER");

		//Step 9: Enter Company ID
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Company ID",1));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Company ID",1),UtilityTestData.Company[0]); 
		base.pressKey(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Company ID",1), "ENTER");

		//Step 10: Enter Customer Account
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Customer account",1));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Customer account",1),CustomerId); 
		base.pressKey(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Customer account",1), "ENTER");

		//Step 11: Select Credit date
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Credit date",1));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Credit date",1),CurrentDate); 

		//Step 12: Enter User ID
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("User ID",1));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("User ID",1),UtilityTestData.RtgsClaimsUserId);
		base.pressKey(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("User ID",1), "ENTER");
		Thread.sleep(3000);	

		//Step13: Click on Post
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label", "Post"));
		Thread.sleep(3000);

		//Fetch balance amount before Payment
		//need to filter with utr and customer number
		//		//Thread.sleep(3000);
		//		for(int i=0;i<5;i++) {
		//			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("Grid_UTRNumber"));
		//			Thread.sleep(5000);
		//			if(base.isPresent(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountFilter("FilterField_Grid_UTRNumber_UTRNumber")))
		//			{
		//				break;
		//			}
		//		}
		appUtils.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("Grid_UTRNumber"),
				NormalSaleGoldAndSilverObj.Ele_RunNowERP("__Grid_UTRNumber_ApplyFilters_label"),
				5,
				2
				);
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_UTRNumber_UTRNumber_Input"), UtrNumber);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__Grid_UTRNumber_ApplyFilters_label"));
		//		Thread.sleep(2000);
		//		for(int i=0;i<5;i++) {
		//			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("Grid_CustomerAccount"));
		//			Thread.sleep(5000);
		//			if(base.isPresent(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountFilter("FilterField_Grid_CustomerAccount_CustomerAccount")))
		//			{
		//				break;
		//			}
		//		}
		appUtils.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("Grid_CustomerAccount"),
				NormalSaleGoldAndSilverObj.Ele_RunNowERP("__Grid_CustomerAccount_ApplyFilters_label"),
				5,
				2
				);
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_CustomerAccount_CustomerAccount_Input"), CustomerId);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__Grid_CustomerAccount_ApplyFilters_label"));

		Thread.sleep(2000);
		String RtgsBalanceBeforePayment=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("Grid_BalanceAmount")); 
		//above code i got balance amount before payment

		Map<String, String> ResultMap = new LinkedHashMap<>();
		ResultMap.put("RtgsBalanceAmntBeforePayment", RtgsBalanceBeforePayment);
		ResultMap.put("UtrNumber", UtrNumber);
		driver.switchTo().window(OriginalWindow);
		return ResultMap;

	}

	/// <summary>
	/// Validate RTGS Payment Balance from ERP using UTR and Customer Number
	/// Author: Benson
	/// <summary>
	public void ValidateRTGSPaymentBalance(String UtrNum,String CustomerNo,double ExpectedBalanceBeforePayment,double ActualBalanceBeforePayment,double ExpectedBalanceAfterPayment) throws Exception {

		// Step 1: Go to ERP and search RTGS payment receipt details
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.RtgsPaymentReceiptSearch); //pass this as utility testdata later ie RTGS payement receipt details
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		// Step 2: Filter with UTR number and Customer account number
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("Grid_UTRNumber"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_UTRNumber_UTRNumber_Input"), UtrNum);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__Grid_UTRNumber_ApplyFilters_label"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("Grid_CustomerAccount"));
		Thread.sleep(2000);
		for(int i=0;i<=5;i++)
		{
			if(base.isPresent(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountFilter("Grid_CustomerAccount")))
			{
				break;
			}
			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("Grid_CustomerAccount"));
			Thread.sleep(2000);
		}
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_CustomerAccount_CustomerAccount_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_CustomerAccount_CustomerAccount_Input"), CustomerNo);
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__Grid_CustomerAccount_ApplyFilters_label"));

		// Step 3: Check the 'Balance Amount' Before and After Payment
		Thread.sleep(5000);
		Assert.assertEquals(ActualBalanceBeforePayment, ExpectedBalanceBeforePayment,
				"Mismatch in Balance Amount Before Payment. Actual: "
						+ ActualBalanceBeforePayment + ", Expected: " + ExpectedBalanceBeforePayment);
		Thread.sleep(5000);
		String ActualBalancePaymentAfter = base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("Grid_BalanceAmount"));
		double ActualBalanceAfterPayment = Double.parseDouble(ActualBalancePaymentAfter.replace(",", ""));
		Assert.assertEquals(ActualBalanceAfterPayment, ExpectedBalanceAfterPayment,"Mismatch in Balance Amount After Payment. Actual: "+ ActualBalanceAfterPayment + ", Expected: " + ExpectedBalanceAfterPayment);
	}

	/// <summary>
	/// To find Customer have a advance(Order)
	/// Author: Neethu
	/// <summary>
	public AdvanceCustomerDetails ToGetCustomerOrderID() throws Exception
	{
		//Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		//Search Retail deposit details
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchRetailDepositDetails);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		// Store Number Filter
		//	this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		Thread.sleep(9000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_Store"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_Store_ApplyFilters_labels", "Apply"));
		Thread.sleep(9000);

		//Is Locked Filter
		//	this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Is locked"));
		Thread.sleep(9000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_IsLocked"), UtilityTestData.IsLocked);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsLocked_ApplyFilters_label", "Apply"));
		Thread.sleep(9000);

		// Scroll horizontally
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(450, 0).release().perform();
		Thread.sleep(10000);

		//Deposit Type Filter
		this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Deposit type"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Deposit type"));
		Thread.sleep(9000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositType"), UtilityTestData.CustomerDepositType);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_DepositType_ApplyFilters_label", "Apply"));
		Thread.sleep(9000);

		// Scroll horizontally
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(200, 0).release().perform();
		Thread.sleep(10000);

		//Is adjusted Filter
		this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Is adjusted"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Is adjusted"));
		Thread.sleep(9000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_IsAdjusted"), UtilityTestData.GPPAdjustMatured);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsAdjusted_ApplyFilters_label", "Apply"));
		Thread.sleep(9000);

		//scroll right to left
		Actions action = new Actions(driver);
		WebElement scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Thread.sleep(1000);
		action.clickAndHold(scrollbar)
		.moveByOffset(-250, 0)
		.release()
		.perform();

		//Date Filter
		this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Date"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Date"));
		Thread.sleep(9000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Ascending_MGDRetailDepositTable_TransDate_label", "Sort oldest to newest"));
		//	base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsAdjusted_ApplyFilters_label", "Apply"));
		Thread.sleep(9000);

		//scroll right to left
		Actions actiondep = new Actions(driver);
		WebElement scrollbarright = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Thread.sleep(1000);
		actiondep.clickAndHold(scrollbarright)
		.moveByOffset(-250, 0)
		.release()
		.perform();

		//Deposit amount Filter
		//this.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Date"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Deposite amount"));
		Thread.sleep(9000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_MGDRetailDepositTable_DepositAmount_label", "Sort largest to smallest"));
		//	base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsAdjusted_ApplyFilters_label", "Apply"));
		Thread.sleep(9000);

		String DepositTransactionID = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("MGDRetailDepositTable_DepositTransId"));	
		System.out.println("DepositTransactionID : "+DepositTransactionID);

		//scroll right to left
		Actions actioncus = new Actions(driver);
		WebElement scrollbarrighttoleft = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Thread.sleep(1000);
		actioncus.clickAndHold(scrollbarrighttoleft)
		.moveByOffset(-100, 0)
		.release()
		.perform();

		String CustomerAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("MGDRetailDepositTable_CustAccount"));	
		System.out.println("CustomerAccount : "+CustomerAccount);
		//				String GppAccount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_GPPAccountNo"));	
		//				System.out.println(GppAccount);
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());		
		driver.switchTo().window(Tabs.get(0));	

		return new AdvanceCustomerDetails(CustomerAccount, DepositTransactionID);
	}

	public class AdvanceCustomerDetails {
		public String CustomerAccount;
		public String DepositTransactionID;

		public AdvanceCustomerDetails(String CustomerAccount, String DepositTransactionID) {
			this.CustomerAccount = CustomerAccount;
			this.DepositTransactionID = DepositTransactionID;
		}
	}

	/// <summary>
	/// To find SKU (numeric-only) for transfer order with Product name & Purity
	/// Limits the number of SKUs collected to `maxSkus` (if > 0)
	/// Author: Chandana Babu
	/// </summary>
	public Map<String, Map<String, String>> ToGetSkuForTransferOrder(int maxSkus) throws InterruptedException {
		String OriginalWindow = driver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));

		// Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchOnHandList);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);
		base.ClickCondition(StatementPostingObj.Btn_Add("SystemDefinedFilterPane_FilterDisplay_ResetFilters"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("itemName")));

		if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_return("Remove filter on SKU number"))) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Remove filter on SKU number"));
		}

		// 2. Enter warehouse (Eg:5205)
		//base.setData(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Filter field: Warehouse, operator: begins with"), UtilityTestData.WarehouseId+"BO");
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Filter field: Warehouse, operator: is exactly"), UtilityTestData.WarehouseId+"BO");
		// 3. Click on Apply button
		base.ClickCondition(StatementPostingObj.Btn_Add("SystemDefinedFilterPane_FilterDisplay_ApplyFilters"));

		// 4. Click on dimensions
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Dimensions"));

		// 5. Click on checkbox
		base.ClickCondition(CustomerCreationObj.Ele_ERPCustomerAgeGroupCode("Configuration"));
		base.ClickCondition(CustomerCreationObj.Ele_ERPCustomerAgeGroupCode("SKU number"));
		base.ClickCondition(CustomerCreationObj.Ele_ERPCustomerAgeGroupCode("Site"));
		base.ClickCondition(CustomerCreationObj.Ele_ERPCustomerAgeGroupCode("Warehouse"));

		// 6. Click on OK button
		base.scrollToElement(StatementPostingObj.Btn_Add("Ok"));
		base.ClickCondition(StatementPostingObj.Btn_Add("Ok"));

		// --- Collect rows (SKU + Product name + Purity) ---
		Thread.sleep(10000);

		final By Grid             = By.xpath("//div[@class='reactGrid fill-width fill-height']");
		final By SkuLocator       = By.xpath("//input[@aria-label='SKU number']");
		final By AvailableLocator = By.xpath("//input[@aria-label='Available physical']");
		final By ProductLocator   = By.xpath("//input[@aria-label='Product name']");
		final By PurityLocator    = By.xpath("//input[@aria-label='Purity']");

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Actions Actions = new Actions(driver);

		Map<String, Map<String, String>> Result = new LinkedHashMap<>();
		Set<String> Seen = new HashSet<>();

		// normalize limit
		final int LIMIT = (maxSkus > 0) ? maxSkus : Integer.MAX_VALUE;
		boolean done = false;

		// Helper – get bottom SKU to detect scroll progress
		Supplier<String> BottomSku = () -> {
			try {
				List<WebElement> Skus = driver.findElements(SkuLocator);
				if (!Skus.isEmpty())
					return Skus.get(Skus.size() - 1).getAttribute("value");
			} catch (Exception ignored) {}
			return "";
		};

		// Focus grid once
		WebElement GridElement = Wait.until(ExpectedConditions.presenceOfElementLocated(Grid));
		Actions.moveToElement(GridElement).click().perform();
		Thread.sleep(500);

		int ScrollCount = 0;
		int NoMovementTries = 0;
		String LastSeenBottomSku = "";

		while (ScrollCount < 250 && !done) {

			List<WebElement> Skus     = driver.findElements(SkuLocator);
			List<WebElement> Avails   = driver.findElements(AvailableLocator);
			List<WebElement> Products = driver.findElements(ProductLocator);
			List<WebElement> Purities = driver.findElements(PurityLocator);

			int Rows = Math.min(Math.min(Skus.size(), Avails.size()), Math.min(Products.size(), Purities.size()));

			for (int i = 0; i < Rows; i++) {
				try {
					String Sku   = getVal(Skus.get(i));
					String Avail = getVal(Avails.get(i));
					if (Sku.isEmpty() || !isAllDigits(Sku) || !hasValue(Avail) || !Seen.add(Sku)) continue;
					String Product = getVal(Products.get(i));
					String Purity  = getVal(Purities.get(i));
					Map<String, String> Info = new LinkedHashMap<>();
					Info.put("ProductName",       Product);
					Info.put("Purity",            Purity);
					Info.put("AvailablePhysical", Avail);
					Result.put(Sku, Info);
					if (Result.size() >= LIMIT) {
						done = true;
						break;
					}
				} catch (Exception ignored) {}
			}
			if (done) break;
			String BeforeBottom = BottomSku.get();
			Actions.sendKeys(Keys.PAGE_DOWN).perform();
			try {
				Actions.scrollByAmount(0, 300).perform(); 
			} catch (Exception ignored) {}
			Thread.sleep(800);
			String AfterBottom = BottomSku.get();
			if (AfterBottom != null && !AfterBottom.isEmpty() && !AfterBottom.equals(BeforeBottom)) {
				NoMovementTries = 0;
				LastSeenBottomSku = AfterBottom;
			} else {
				NoMovementTries++;
				try {
					WebElement GridEl = Wait.until(ExpectedConditions.presenceOfElementLocated(Grid));
					Actions.moveToElement(GridEl).click().sendKeys(Keys.PAGE_DOWN).perform();
					Thread.sleep(600);
					String AfterFallback = BottomSku.get();
					if (AfterFallback != null && !AfterFallback.isEmpty() && !AfterFallback.equals(BeforeBottom)) {
						NoMovementTries = 0;
						LastSeenBottomSku = AfterFallback;
					}
				} catch (Exception ignored) {}
				if (NoMovementTries >= 5) {
					break;
				}
			}
			ScrollCount++;
		}
		return Result;
	}

	// ---------- Helpers ----------
	private static String getVal(WebElement el) {
		try {
			String v = el.getAttribute("value");
			if (v == null || v.trim().isEmpty()) v = el.getText();
			return v == null ? "" : v.trim();
		} catch (Exception e) {
			return "";
		}
	}

	private static boolean hasValue(String s) {
		if (s == null || s.isBlank()) return false;
		try {
			double v = Double.parseDouble(s.replaceAll("[^0-9.-]", ""));
			return v > 0; // treat numeric > 0 as "has value"
		} catch (Exception ignore) {
			return !s.isBlank(); // fallback: any non-blank counts
		}
	}

	// numeric-only (no spaces/letters), at least one digit
	private static boolean isAllDigits(String s) {
		return s != null && s.matches("\\d+");
	}


	/// <summary>
	/// Method to handle PDC workflow — entry, deposit, and execution.
	/// Author-Benson
	/// </summary>
	public void PDCEntryDepositAndExecution(String PdcId,String CustomerNo) throws Exception{

		//Go to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		//Go to PDC entry
		Thread.sleep(8000);	
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.PdcEntrySearch);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Filtering with  pdc entry date newest to oldest to get latest created entry
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCEntryTable_TransDate"));
		base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));
		Thread.sleep(20000);
		driver.navigate().refresh();
		Thread.sleep(10000);

		//Custom loop to wait for pdc entry
		// Locator for the PDC ID in ERP
		By pdcIdLocator = NormalSaleGoldAndSilverObj.Ele_ERPInvoice(PdcId);
		boolean isPdcFound = false;
		int maxAttempts = 20; // total attempts

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			System.out.println(" Attempt #" + attempt + ": Filtering and checking for PDC ID " + PdcId);
			driver.navigate().refresh();
			Thread.sleep(2000);

			try {
				// --- Apply sorting again 
				base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCEntryTable_TransDate"));
				base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));
				Thread.sleep(2000);

				// --- Apply filter using PDC Entry Code
				base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCEntryTable_PDCEntryCode"));
				Thread.sleep(2000);
				base.setData( NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_PDCEntryTable_PDCEntryCode_PDCEntryCode_Input"),PdcId);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__PDCEntryTable_PDCEntryCode_ApplyFilters_label"));
				Thread.sleep(2000);

				// --- Wait for the filtered PDC ID to appear
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(pdcIdLocator));
				System.out.println(" PDC ID " + PdcId + " found in ERP after " + attempt + " refresh attempts!");
				isPdcFound = true;
				break;
			} catch (TimeoutException e) {
				System.out.println(" PDC ID not found yet. Retrying after 30s...");
				Thread.sleep(30000);
			} catch (Exception e) {
				System.out.println(" Minor error during attempt " + attempt + ": " + e.getMessage());
				Thread.sleep(30000);
			}
		}

		if (!isPdcFound) {
			throw new Exception(" PDC ID " + PdcId + " not found in ERP after " + maxAttempts + " refresh attempts.");
		}

		//Select customer and click on confirm
		//Expected : Verify already created PDC entry listed
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("PDC entry code", 1));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label","Confirm" ));
		Thread.sleep(3000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Yes"));  
		String ActualPdcId=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("PDC entry code", 1));

		asrt.assertEquals(ActualPdcId, PdcId, "PDC ID mismatch: Expected is " + PdcId + " but got " + ActualPdcId + " in PDC entry list.");

		//Go to PDC deposit
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.PdcDepositSearch); 
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Click new --> Select warehouse and click on fetch button
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(2000);
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Warehouse",1));
		Thread.sleep(5000);
		base.ClickClearEnter(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Warehouse",1),UtilityTestData.WarehouseId); 
		base.buttonClick(LoginPageObj.Edt_Alert("Fetch"));
		Thread.sleep(3000);

		//In lines, choose the deposit bank and cheque presented date against the required customer
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCDepositeTable_PDCDepositeCode"));
		base.ClickCondition(LoginPageObj.Edt_Alert("Sort Z to A"));
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("PDC deposit code", 1));
		Thread.sleep(5000);		
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("dyn-svg-symbol dyn-lookup-button")); 
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("AutoLookup_AccountID_")); 

		Thread.sleep(3000);
		base.ClickCondition(PdcEntryExecutionObj.Ele_ChequePresentedCheckbox("_p")); 
		String ChequePresentedDate=appUtils.getCurrentDate("MM/dd/yyyy"); 
		base.buttonClick(CustomerCreationObj.Ele_ERPCustid("Cheque presented date"));
		base.setData(CustomerCreationObj.Ele_ERPCustid("Cheque presented date"), ChequePresentedDate);

		//Click on confirm button
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label","Confirm" ));
		base.ClickCondition(LoginPageObj.Edt_Alert("Yes")); 

		//Expected : After confirmation the PDC Deposit code the Confirm field should be shown Tick icon(✓) .
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCDepositeTable_PDCDepositeCode"));
		for(int i=0;i<=5;i++)
		{
			if(!base.isPresent(By.xpath("//div[@data-dyn-form-name='PDCDepositeTable_PDCDepositeCode' and @data-dyn-lastvisible='true']"))) {
				base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCDepositeTable_PDCDepositeCode"));
			}
			else {
				break;
			}

		}
		base.ClickCondition(LoginPageObj.Edt_Alert("Sort Z to A"));

		asrt.assertTrue(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Yes", 1)),"User is not able to view the Tick (✓) icon in the Confirm field after confirming the PDC Deposit code.");

		driver.navigate().refresh();  
		//Go to PDC execution
		Thread.sleep(5000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.PdcExecutionSearch); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		Thread.sleep(7000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(7000);

		//Click new --> Select warehouse and click on fetch button
		base.buttonClick(LoginPageObj.Edt_Alert("New"));
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Warehouse",1));  
		Thread.sleep(2000);
		base.ClickClearEnter(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Warehouse",1),UtilityTestData.WarehouseId);
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Edt_Alert("Fetch"));
		Thread.sleep(7000);

		//Filtering PDC Execution code latest to oldest to get latest entry
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCExecutionTable_PDCExecutionCode"));
		base.buttonClick(LoginPageObj.Edt_Alert("Sort Z to A"));

		//In lines, choose honoured or dishonoured.
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("PDC execution code", 1));
		base.ClickCondition(CustomerCreationObj.Ele_ERPCustid("Honoured/DisHonoured"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", driver.findElement(CustomerCreationObj.Ele_ERPCustid("Honoured/DisHonoured")));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("combobox-button") );
		base.ClickCondition(LoginPageObj.Ele_ERPNo("Honoured"));

		//Choose the honoured date
		String HonouredDate=appUtils.getCurrentDate("MM/dd/yyyy"); 
		Thread.sleep(3000);
		this.ScrollIntoView(CustomerCreationObj.Ele_ERPCustid("Honoured/DisHonoured date"));
		base.ClickCondition(CustomerCreationObj.Ele_ERPCustid("Honoured/DisHonoured date"));
		base.setData(CustomerCreationObj.Ele_ERPCustid("Honoured/DisHonoured date"), HonouredDate);

		//Click on confirm.
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label","Confirm" ));
		base.buttonClick(LoginPageObj.Edt_Alert("Yes"));  
		//Expected : After confirmation the PDC execution code the Confirm field should be shown Tick icon(✓) 
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("PDCExecutionTable_PDCExecutionCode"));
		base.buttonClick(LoginPageObj.Edt_Alert("Sort Z to A"));

		asrt.assertTrue(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Yes", 1)),"User is not able to view the Tick (✓) icon in the Confirm field after confirming the PDC Execution code.");
	}
	/// <summary>
	/// To find multiple advance
	/// Author: Chandana Babu
	/// </summary>
	// ---- Locators (no horizontal scroll) ----
	private final By GRID_ROOT                = By.xpath("//div[@class='reactGrid fill-width fill-height']");
	private final By CUSTOMER_ACCOUNT_INP     = By.xpath("//input[@aria-label='Customer account']");
	private final By DEPOSIT_TYPE_INP         = By.xpath("//input[@aria-label='Deposit type']");
	private final By VERTICAL_SCROLL_FACE     = By.xpath("//div[@class='ScrollbarLayout_face ScrollbarLayout_faceVertical public_Scrollbar_face']");
	private final By TRANSACTION_NUMBER_INP   = By.xpath("//input[@aria-label='Transaction number']"); // reserved (no change)

	// ---- Small helpers (no change) ----
	private static String norm(String s) { return s == null ? "" : s.trim(); }
	private static String val(WebElement el) {
		try {
			String v = el.getAttribute("value");
			if (v != null && !v.trim().isEmpty()) return v.trim();
			String t = el.getText();
			return t == null ? "" : t.trim();
		} catch (Exception e) { return ""; }
	}
	private static boolean exactEq(String a, String b) {
		return !norm(a).isEmpty() && norm(a).equalsIgnoreCase(norm(b));
	}
	private static void fastSleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }
	private static boolean isEmptyStr(String s) { return s == null || s.trim().isEmpty(); }
	private static boolean different(String a, String b) {
		if (a == null && b == null) return false;
		if (a == null || b == null) return true;
		return !a.equals(b);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> ToFindCustomerWithMultipleNormalOfferAdvance(
			int MinimumRequiredCountForFirstAdvance, 
			String FirstAdvance, 
			int MinimumRequiredCountForSecondAdvance, 
			String SecondAdvance, 
			int AccountsNeeded) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();

		// ====================== NAVIGATE & FILTER (your original steps) ======================
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),
				UtilityTestData.RetailDepositDetails);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);

		// Store
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("MGDRetailDepositTable_Store"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_Store_Store_Input"),
				UtilityTestData.WarehouseId);
		base.ClickCondition(StatementPostingObj.Btn_Add("MGDRetailDepositTable_Store_ApplyFilters"));

		// IsLocked = No
		for (int i = 0; i < 5; i++) {
			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("MGDRetailDepositTable_IsLocked"));
			Thread.sleep(1000);
			if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_IsLocked_IsLocked_Input")))
				break;
		}
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly(
				"FilterField_MGDRetailDepositTable_IsLocked_IsLocked_Input"), "No");
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_IsLocked_IsLocked_Input"), "ENTER");
		base.ClickCondition(StatementPostingObj.Btn_Add("MGDRetailDepositTable_IsLocked_ApplyFilters"));

		// Date descending
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj= new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(100, 0).release().perform();
		Thread.sleep(15000);

		for (int i = 0; i < 5; i++) {
			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("MGDRetailDepositTable_TransDate"));
			Thread.sleep(1000);
			if (base.isPresent(StatementPostingObj.Btn_Add("Descending_MGDRetailDepositTable_TransDate"))) break;
		}
		base.ClickCondition(StatementPostingObj.Btn_Add("Descending_MGDRetailDepositTable_TransDate"));
		Thread.sleep(5000);

		// IsAdjusted = No	    
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(300, 0).release().perform();
		Thread.sleep(15000);

		for (int i = 0; i < 5; i++) {
			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("MGDRetailDepositTable_IsAdjusted"));
			Thread.sleep(1000);
			if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly(
					"FilterField_MGDRetailDepositTable_IsAdjusted_IsAdjusted_Input"))) break;
		}
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly(
				"FilterField_MGDRetailDepositTable_IsAdjusted_IsAdjusted_Input"), "No");
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_IsAdjusted_IsAdjusted_Input"), "ENTER");
		base.ClickCondition(StatementPostingObj.Btn_Add("MGDRetailDepositTable_IsAdjusted_ApplyFilters"));
		Thread.sleep(5000);

		ActionsObj.clickAndHold(Scrollbar).moveByOffset(-350, 0).release().perform();
		Thread.sleep(15000);

		// =========================== FETCH (FASTER, RELIABLE) ===========================
		WebDriverWait Wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(8));
		WebElement Grid = Wait.until(ExpectedConditions.presenceOfElementLocated(GRID_ROOT));
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		Actions Actions = new Actions(driver);

		// Internal state
		Map<String, int[]> CountsByAccount = new LinkedHashMap<>();  // account -> [A,B]
		Set<String> Emitted = new HashSet<>();
		List<String> Accounts = new ArrayList<>();
		Set<String> SeenRowKeys = new HashSet<>(); // account|type|txn to avoid recount

		// --- Tuning knobs (optimized) ---
		final int SCROLL_LIMIT   = 240;  // a bit lower; faster finish with early-stops
		final int NO_MOVE_LIMIT  = 6;    // tolerant on virtualized lag
		final int WHEEL_DELTA    = 2000; // smooth
		final int DRAG_OFFSET_Y  = 520;  // strong nudge
		final int SLEEP_MS       = 180;  // shorter micro-wait

		// txn format gate (TypeA only)
		final java.util.regex.Pattern TXN_PATTERN = java.util.regex.Pattern.compile("^[0-9]{4}-[A-Z0-9]+-[0-9]{13}$");
		java.util.function.Predicate<String> goodTxn = s -> s != null && TXN_PATTERN.matcher(s.trim()).matches();

		// movement keys (top & bottom) + signature (first+last+count)
		java.util.function.Supplier<String> topKey = () -> {
			try {
				List<WebElement> ca = driver.findElements(CUSTOMER_ACCOUNT_INP);
				return ca.isEmpty() ? "" : val(ca.get(0));
			} catch (Exception ignored) { return ""; }
		};
		java.util.function.Supplier<String> bottomKey = () -> {
			try {
				List<WebElement> ca = driver.findElements(CUSTOMER_ACCOUNT_INP);
				return ca.isEmpty() ? "" : val(ca.get(ca.size() - 1));
			} catch (Exception ignored) { return ""; }
		};
		java.util.function.Supplier<String> signature = () -> {
			try {
				List<WebElement> ca = driver.findElements(CUSTOMER_ACCOUNT_INP);
				if (ca.isEmpty()) return "";
				return val(ca.get(0)) + "|" + val(ca.get(ca.size()-1)) + "|" + ca.size();
			} catch (Exception ignored) { return ""; }
		};

		// ===== FAST PATH: single JS sweep to get visible rows (account,type,txn) =====
		final String JS_SCRAPE_ROWS =
				"var rows=[];\n" +
						"var wrapSel='div.fixedDataTableRowLayout_rowWrapper';\n" +
						"var rowsEls=document.querySelectorAll(wrapSel);\n" +
						"for (var i=0;i<rowsEls.length;i++){\n" +
						"  var r=rowsEls[i];\n" +
						"  var acc=r.querySelector(\"input[aria-label='Customer account']\");\n" +
						"  var typ=r.querySelector(\"input[aria-label='Deposit type']\");\n" +
						"  var txn=r.querySelector(\"input[aria-label='Transaction number']\");\n" +
						"  var a=acc?(acc.value||acc.textContent||'').trim():'';\n" +
						"  var t=typ?(typ.value||typ.textContent||'').trim():'';\n" +
						"  var x=txn?(txn.value||txn.textContent||'').trim():'';\n" +
						"  if (a||t||x) rows.push([a,t,x]);\n" +
						"}\n" +
						"return rows;";
		// scan visible viewport (JS first, WebElement fallback)
		Runnable scanViewport = () -> {
			try {
				@SuppressWarnings("unchecked")
				List<List<Object>> rows = (List<List<Object>>) js.executeScript(JS_SCRAPE_ROWS);
				if (rows != null) {
					for (List<Object> row : rows) {
						if (Accounts.size() >= AccountsNeeded) break;
						String Account = norm(row.get(0) == null ? "" : row.get(0).toString());
						String DepositCell = norm(row.get(1) == null ? "" : row.get(1).toString());
						String TransactionCell = norm(row.get(2) == null ? "" : row.get(2).toString());
						if (Account.isEmpty() || DepositCell.isEmpty()) continue;

						String RowKey = Account + "|" + DepositCell + "|" + TransactionCell;
						if (!SeenRowKeys.add(RowKey)) continue; // already processed this virtualized row

						int[] ab = CountsByAccount.computeIfAbsent(Account, k -> new int[]{0, 0});
						if (exactEq(DepositCell, FirstAdvance)) {
							if (goodTxn.test(TransactionCell)) ab[0]++; // gate for TypeA
						}
						if (exactEq(DepositCell, SecondAdvance)) {
							ab[1]++; // no gate for TypeB
						}

						if (!Emitted.contains(Account) && ab[0] >= MinimumRequiredCountForFirstAdvance && ab[1] >=  MinimumRequiredCountForSecondAdvance) {
							Accounts.add(Account);
							Emitted.add(Account);
						}
					}
					return; // success via JS
				}
			} catch (Exception ignore) {
				// fall back to WebElement path if JS fails
			}

			// Fallback: row-wise alignment by querying inputs per row index
			try {
				List<WebElement> AccEls = driver.findElements(CUSTOMER_ACCOUNT_INP);
				List<WebElement> DepEls = driver.findElements(DEPOSIT_TYPE_INP);
				List<WebElement> TxnEls = driver.findElements(TRANSACTION_NUMBER_INP);

				int n = Math.min(AccEls.size(), Math.min(DepEls.size(), TxnEls.size()));
				for (int i = 0; i < n && Accounts.size() < AccountsNeeded; i++) {
					String account = val(AccEls.get(i));
					String depCell = val(DepEls.get(i));
					String txnCell = val(TxnEls.get(i));
					if (account.isEmpty() || depCell.isEmpty()) continue;

					String rowKey = account + "|" + depCell + "|" + txnCell;
					if (!SeenRowKeys.add(rowKey)) continue;

					int[] ab = CountsByAccount.computeIfAbsent(account, k -> new int[]{0, 0});
					if (exactEq(depCell, FirstAdvance)) {
						if (goodTxn.test(txnCell)) ab[0]++;
					}
					if (exactEq(depCell, SecondAdvance)) {
						ab[1]++;
					}

					if (!Emitted.contains(account) && ab[0] >= MinimumRequiredCountForFirstAdvance && ab[1] >=  MinimumRequiredCountForSecondAdvance) {
						Accounts.add(account);
						Emitted.add(account);
					}
				}
			} catch (Exception ignored) {}
		};

		// explicit micro-wait for new rows after each scroll attempt
		Runnable WaitForRows = () -> {
			try {
				new WebDriverWait(driver, java.time.Duration.ofSeconds(1))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CUSTOMER_ACCOUNT_INP));
			} catch (Exception ignored) {}
		};

		// stronger adaptive scrollOnce: wheel → drag → PAGE_DOWN, check signature
		java.util.function.Supplier<Boolean> scrollOnce = () -> {
			String BeforeTop = topKey.get();
			String BeforeBottom = bottomKey.get();
			String BeforeSig = signature.get();
			boolean Moved = false;

			// 1) Wheel
			try {
				js.executeScript(
						"var el = arguments[0]; var e = new WheelEvent('wheel', {deltaY: arguments[1], bubbles: true}); el.dispatchEvent(e);",
						Grid, WHEEL_DELTA);
				fastSleep(SLEEP_MS);
				WaitForRows.run();
				String afterSig = signature.get();
				Moved = !isEmptyStr(afterSig) && different(BeforeSig, afterSig);
			} catch (Exception ignored) {}

			// 2) Drag
			if (!Moved) {
				try {
					WebElement Face = Wait.until(ExpectedConditions.presenceOfElementLocated(VERTICAL_SCROLL_FACE));
					new Actions(driver).clickAndHold(Face).moveByOffset(0, DRAG_OFFSET_Y).release().perform();
					fastSleep(SLEEP_MS);
					WaitForRows.run();
					String afterSig = signature.get();
					Moved = !isEmptyStr(afterSig) && different(BeforeSig, afterSig);
				} catch (Exception ignored) {}
			}

			// 3) PAGE_DOWN
			if (!Moved) {
				try {
					new Actions(driver).moveToElement(Grid, 10, 10).click()
					.sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();
					fastSleep(SLEEP_MS);
					WaitForRows.run();
					String afterSig = signature.get();
					Moved = !isEmptyStr(afterSig) && different(BeforeSig, afterSig);
				} catch (Exception ignored) {}
			}
			return Moved;
		};

		// Main loop (fast & reliable)
		int Scrolls = 0, NoMove = 0;
		// ensure first paint
		Wait.until(d -> !driver.findElements(CUSTOMER_ACCOUNT_INP).isEmpty());

		while (Accounts.size() < AccountsNeeded && Scrolls < SCROLL_LIMIT) {
			scanViewport.run();
			if (Accounts.size() >= AccountsNeeded) break;

			boolean Moved = scrollOnce.get();
			Scrolls++;
			if (Moved) {
				NoMove = 0;
			} else if (++NoMove >= NO_MOVE_LIMIT) {
				break; // end or stuck
			}
		}
		// === Return: only accounts ===
		Map<String, Object> out = new LinkedHashMap<>();
		out.put("AccountsNeeded", AccountsNeeded);
		out.put("AccountsMatched", Accounts.size());
		out.put("Accounts", Accounts);
		driver.switchTo().window(OriginalWindow);
		return out;
	}

	///<Summary>
	///Creating PDC Entry  With advance
	///</Summary>
	public HashMap<String,String> CreatePDCEntry(String CustomerID, String PdcType) throws Exception
	{
		//Step 3 : Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4 : Click on PDC entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5 : Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(CustomerID,UtilityTestData.MenuBarOptions[7]);     
		Thread.sleep(2000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));

		//Step 6 : Choose PDC type as normal advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcType);

		//Step 7 : Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Step 8 : Enter the following
		//Step 8.1 : Issue bank 
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),UtilityTestData.IssueBank);

		//Step 8.2 : Branch name
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),UtilityTestData.Branch);

		//Step 8.3 : MICR number
		String MicrNumber=base.CreateRandomNumber(9); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);

		//Step 8.4 : Cheque number
		//Step 8.5 : Cheque date
		String ChequeNo=base.CreateRandomNumber(6); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNo);

		//Step 8.6 : Cheque amount
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUEAMOUNT"),UtilityTestData.ChequeAmount);

		//Step 9 : Click on add cheque and then click commit PDC
		base.buttonClick(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000);

		//Nominee details popup 
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.NomineeDobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));		
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));

		//Navigate to ERP
		this.PDCEntryDepositAndExecution(PdcId,CustomerID );
		String AdvanceId=base.GetValue(PdcEntryExecutionObj.Ele_PdcNormalAdvance("PDCExecutionCode","PDC execution code",String.valueOf(1)));
		Thread.sleep(2000);
		String ChequeAmount=base.GetValue(PdcEntryExecutionObj.Ele_ChequeAmount("PDCExecutionDetails_ChequeAmount"));
		HashMap<String,String> PdcValue=new HashMap<>();
		PdcValue.put("Advance ID", AdvanceId);
		PdcValue.put("Cheque Amount", ChequeAmount);
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());		
		driver.switchTo().window(Tabs.get(0));	
		return  PdcValue;
	}

	/// <summary>
	/// To find Valid promotion discount
	/// Author: Sangeetha M S
	/// </summary>
	public Map<String, String> GetValidPromotionDiscount(String ItemType) throws Exception
	{
		// Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// Search Promotion discount 
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		// Sort by From date (newest to oldest)
		base.ClickCondition(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("dyn-headerCell _","From date"));
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _9px0ch isFilterable", "From date"));
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Sort newest to oldest"));
		Thread.sleep(5000);

		//Apply Gold
		Thread.sleep(2000);
		base.ClickCondition(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("public_fixedDataTableCell_cellContent","Category"));
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"), ItemType);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// Apply Region filter
		Thread.sleep(2000);
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Region code"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Store number"));
		Thread.sleep(1000);
		//base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RegionCode_RegionCode_Input_0"), UtilityTestData.RegionCode);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"), UtilityTestData.WarehouseId);
		//base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RegionCode_ApplyFilters_label", "Apply"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj= new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();
		Thread.sleep(15000);

		// Apply Active filter
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Active"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active_Active_Input_0"), UtilityTestData.Status);
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Ele_ERPNo("Yes"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));

		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			// Get promotion data
			String PromotionDescription=base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("AVAMGD_GiftItemPromotionAgreementValueWise_PromoDescrption_"));
			System.out.println(PromotionDescription);

			String PromotionType = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("_PromotionAgreementValueWise_OverwriteAppend_"));
			System.out.println(PromotionType);

			String Amount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"));
			System.out.println("Amount    "+Amount);

			String Multiplier =  base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_MultipleOf_"));
			System.out.println(" Multiplier   "+Multiplier );

			// Switch back to main tab
			ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(Tabs.get(0));

			// Store in Map
			Map<String, String> PromotionData = new HashMap<>();
			PromotionData.put("PromotionType", PromotionType);
			PromotionData.put("Amount", Amount);
			PromotionData.put("Multiplier", Multiplier);

			return PromotionData;
		}
		else
		{
			return null;
		}
	}

	/// <summary>
	/// To find Valid Refunded Offer Advance
	/// Author: Bharath
	/// </summary>		
	public Map<String, Map<String, Object>> RefundedAdvance(String DepositType, int RequiredCount, int RequiredAccount) throws Exception {
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.switchTo().newWindow(WindowType.TAB);
		List<String> Tabs = new ArrayList<>(driver.getWindowHandles());
		driver.navigate().to(UtilityTestData.ERPURL);

		// --- Search Deposit ---
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 50);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.RetailDepositDetails);
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"), 30
				);

		// --- Set Store Filter ---
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_Store"),
				10
				);
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_Store"),
				UtilityTestData.WarehouseId
				);
		base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// --- Set Deposit Type Filter ---
		base.dragScrollToFindElement(
				driver,
				By.cssSelector(".ScrollbarLayout_faceHorizontal"),
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "MGDRetailDepositTable_DepositType"),
				20
				);
		base.ClickWithRetry(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "MGDRetailDepositTable_DepositType"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositType"),
				10
				);
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_DepositType"),
				DepositType
				);
		for (int i = 0; i < 5; i++) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositType"), 3)) {
				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
				System.out.println("Unable to click on Apply button");
				continue;
			}
			break;
		}

		// --- Set IsRefundedBenefit Filter ---
		base.dragScrollToFindElement(
				driver,
				By.cssSelector(".ScrollbarLayout_faceHorizontal"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"),
				300
				);
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"),
				20
				);
		base.setData(
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("AVAMGDIsRefundBenefitRedeemed"),
				"No"
				);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());

		// --- Set IsRefunded Filter ---
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		for (int i = 0; i < 5; i++) {
			base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "IsRefunded"));
			if (base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_AVAMGDIsRefunded"), 5)) {
				base.setData(
						NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("AVAMGDIsRefunded"),
						"Yes"
						);
				break;
			}
		}
		for (int i = 0; i < 5; i++) {
			base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
			Thread.sleep(2000);
			if (!base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_AVAMGDIsRefunded"), 5)) {
				break;
			}
		}

		// --- Sort by Transaction Date ---
		base.dragScrollToFindElement(
				driver,
				By.cssSelector(".ScrollbarLayout_faceHorizontal"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_DepositType"),
				-310
				);
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_TransDate"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_TransDate"),
				20
				);
		base.UnifiedClick(LoginPageObj.Btn_SingnIn("__Ascending_MGDRetailDepositTable_TransDate"));

		// --- Initialize data structures ---
		final By CustomerAccounts = By.xpath("//input[contains(@id,'MGDRetailDepositTable_CustAccount')]");
		Map<String, Integer> DepositCount = new HashMap<>();
		List<String> ValidDeposits = new ArrayList<>();
		Map<String, Map<String, Object>> ValidDepositDetails = new HashMap<>();
		Map<String, Map<String, Object>> FinalResult = new HashMap<>();
		JavascriptExecutor Js = (JavascriptExecutor) driver;

		//Improved vertical scroll
		List<WebElement> scrollBars =
				driver.findElements(By.cssSelector(".ScrollbarLayout_mainVertical"));

		boolean hasScrollbar = !scrollBars.isEmpty();
		WebElement Scrollbar = hasScrollbar ? scrollBars.get(0) : null;

		boolean ReachedEnd = false;
		int previousScrollTop = -1;

		do {
			// Wait for rows
			base.WaitforElementToBePresentWithTimeout(
					CustomerCreationObj.Ele_ERPCustid("Deposit trans id"), 30);

			//			List<WebElement> Deposits = base.GetElement(
			//					CustomerCreationObj.Ele_ERPCustid("Deposit trans id"));
			List<WebElement> Deposits = driver.findElements(CustomerCreationObj.Ele_ERPCustid("Deposit trans id"));
			Thread.sleep(2000);
			if(Deposits.isEmpty())
			{
				throw new Exception("Insufficient Refunded offer advance records.");
			}


			base.dragScrollToFindElement(
					driver,
					By.cssSelector(".ScrollbarLayout_faceHorizontal"),
					CustomerCreationObj.Ele_ERPCustid("Customer account"),
					-50
					);

			List<WebElement> Customers = base.GetElement(
					CustomerCreationObj.Ele_ERPCustid("Customer account"));
			List<WebElement> Rates = base.GetElement(
					CustomerCreationObj.Ele_ERPCustid("Metal rate"));
			List<WebElement> Amounts = base.GetElement(
					CustomerCreationObj.Ele_ERPCustid("Deposite amount"));

			int rowCount = Deposits.size();

			for (int i = 0; i < rowCount; i++) {

				String DepositId = Deposits.get(i).getAttribute("value").trim();
				if (DepositId.isEmpty()) continue;

				String CustomerId = Customers.get(i).getAttribute("value").trim();
				String MetalRate  = Rates.get(i).getAttribute("value")
						.replaceAll(",", "").trim();
				String Amount     = Amounts.get(i).getAttribute("value").trim();

				int idx = i;
				String RefundRate = (String) Js.executeScript(
						"return document.querySelectorAll(\"input[id*='MGDRetailDepositTable_AVAMGDRefundMetalRate']\")[" + idx + "].value;"
						);

				MetalRate =
						(Double.parseDouble(MetalRate) <
								Double.parseDouble(RefundRate.replaceAll(",", "").trim()))
						? MetalRate : RefundRate;

				int Count = DepositCount.getOrDefault(DepositId, 0) + 1;
				DepositCount.put(DepositId, Count);

				ValidDepositDetails.putIfAbsent(DepositId, new HashMap<>());
				Map<String, Object> Details = ValidDepositDetails.get(DepositId);

				Details.put("DepositId", DepositId);
				Details.put("CustomerID", CustomerId);
				Details.put("MetalRate", MetalRate);

				Details.putIfAbsent("Amounts", new ArrayList<String>());
				((List<String>) Details.get("Amounts")).add(Amount);

				if (Count >= RequiredCount && !ValidDeposits.contains(DepositId)) {
					ValidDeposits.add(DepositId);
				}

				if (ValidDeposits.size() >= RequiredAccount) {
					ReachedEnd = true;
					break;
				}
			}

			if (!hasScrollbar || ReachedEnd) {
				break;
			}

			Long scrollTop = (Long) Js.executeScript(
					"return arguments[0].scrollTop;", Scrollbar);

			if (scrollTop.intValue() == previousScrollTop) {
				break;
			}

			previousScrollTop = scrollTop.intValue();
			Js.executeScript("arguments[0].scrollTop += 200;", Scrollbar);
			Thread.sleep(500);

		} while (!ReachedEnd);


		// --- Build FinalResult ---
		for (String Deposit : ValidDeposits) {
			Map<String, Object> Details = ValidDepositDetails.get(Deposit);
			FinalResult.put(Deposit, Details);
			System.out.println("DepositId: " + Details.get("DepositId"));
			System.out.println("CustomerID: " + Details.get("CustomerID"));
			System.out.println("MetalRate: " + Details.get("MetalRate"));
			System.out.println("Amounts: " + Details.get("Amounts"));
		}

		driver.switchTo().window(Tabs.get(0));
		return FinalResult;
	}

	/// <summary>
	/// To find Refunded Matured  GPPAccounts through Filter In the Retail deposit Page
	/// Author: Benson
	/// <summary>
	public Map<String, Object> RefundedGPPMaturedAccountDetails( int MaxGppAccounts,String SchemeCode, String IsRefunded, String IsRefundBenefit ) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// Search Retail deposit details
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		Thread.sleep(7000);
		base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 30);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchRetailDepositDetails);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		//Thread.sleep(10000);  
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"), 10);

		// StoreNumber Filter
		//base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_Store_Store_Input"),
				5,
				2
				);
		//Thread.sleep(7000);
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_Store_Store_Input"), 8);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_Store_Store_Input"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_Store_ApplyFilters_label", "Apply"));

		//Set Deposit Trans id as GB
		//base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Deposit trans id"));
		Thread.sleep(3000);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Deposit trans id"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"),
				5,
				2
				);

		//Thread.sleep(7000); 
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), 7);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), SchemeCode);  
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_DepositTransId_ApplyFilters_label", "Apply"));


		//Set Is Refunded Filter as 'Yes'
		Thread.sleep(5000);
		base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Is refunded?"));
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Is refunded?"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_AVAMGDIsRefunded_AVAMGDIsRefunded_Input"),
				5,
				2
				);
		//Thread.sleep(7000);   
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_AVAMGDIsRefunded_AVAMGDIsRefunded_Input"), 10);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_AVAMGDIsRefunded_AVAMGDIsRefunded_Input"), IsRefunded);  
		base.ClickCondition(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_Refunded("Yes","__FilterField_MGDRetailDepositTable_AVAMGDIsRefunded_AVAMGDIsRefunded_Input"));
		Thread.sleep(4000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_AVAMGDIsRefunded_ApplyFilters_label", "Apply"));

		//Set Is Refunded Benefit Filter as 'No'
		Thread.sleep(5000);
		base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Is refund benefit redeemed?"));
		base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Is refund benefit redeemed?"), 10);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Is refund benefit redeemed?"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"),
				5,
				2
				);
		//Thread.sleep(7000); 
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"), 7);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"), IsRefundBenefit);
		base.ClickCondition(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_Refunded("No", "__FilterField_MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed_AVAMGDIsRefundBenefitRedeemed_Input"));
		Thread.sleep(2000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed_ApplyFilters_label", "Apply")); 

		//Scrolling to the left
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj2= new Actions(driver);
		ActionsObj2.clickAndHold(Scrollbar).moveByOffset(-650, 0).release().perform();
		//Thread.sleep(15000);  
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Refund adjust date"), 12);


		//Sort Refund Adjusted date as newest to oldest 
		base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Refund adjust date"));
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Refund adjust date"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_RefundAdjustDate_RefundAdjustDate_Input"),
				5,
				2
				);
		//Thread.sleep(9000); 
		base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_Alert("Sort newest to oldest"), 8);
		base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));	


		// STEP 11: Initialize result containers
		// ➤ These hold the matched accounts and prevent duplicate entries.
		Thread.sleep(10000);
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		Set<String> Seen = new HashSet<>();
		List<Map<String, String>> MatchedAccounts = new ArrayList<>();

		// STEP 12: Define element locators for grid data
		// ➤ Identifies key input fields like Total Paid, EMI, GPP Account No, and Customer Account.
		final By Grid = By.cssSelector("div[role='grid'][aria-label='Retail deposit details']");
		final By VScrollFace = By.cssSelector(".ScrollbarLayout_mainVertical .ScrollbarLayout_face");
		//final By GppAccLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Deposit trans id']"); //CHANGE XPATH POM MODEL
		final By GppAccLocator = CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_CustomerAccountInput("fixedDataTableRowLayout_rowWrapper", "Deposit trans id");
		//final By CustomerAccLocator = By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Customer account']");
		final By CustomerAccLocator = CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_CustomerAccountInput("fixedDataTableRowLayout_rowWrapper", "Customer account");
		WebDriverWait WaitObj = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
		int ScrollCount = 0;
		int NoMovementTries = 0;


		// STEP 13: Define helper functions
		// ➤ TopKey: Gets the first visible account number (used to detect scroll changes).
		java.util.function.Supplier<String> TopKey = () -> {
			try {
				List<WebElement> Keys = driver.findElements(GppAccLocator);
				if (!Keys.isEmpty()) return Keys.get(0).getAttribute("value");
			} catch (Exception ignored) {}
			return "";
		};


		// STEP 14: Begin scanning grid and collecting matched accounts
		// ➤ Loops through the grid, scrolling multiple times to collect eligible records.
		Set<String> SeenGppAccounts = new HashSet<>();
		Set<String> SeenCustomerAccounts = new HashSet<>();

		while (MatchedAccounts.size() < MaxGppAccounts && ScrollCount < 120) {
			System.out.println("Scroll iteration: " + ScrollCount);

			// STEP 14.1: Collect visible data rows
			// ➤ Reads only GPP Account No and Customer Account No from current visible rows.
			List<WebElement> GppAccList = driver.findElements(GppAccLocator);
			List<WebElement> CustomerAccList = driver.findElements(CustomerAccLocator);

			for (int i = 0; i < Math.min(GppAccList.size(), CustomerAccList.size()); i++) {
				try {
					String GppAccNo = GppAccList.get(i).getAttribute("value").trim();
					String CustomerAccNo = CustomerAccList.get(i).getAttribute("value").trim();

					// Proceed only if both values are valid and not duplicates
					if (!GppAccNo.isEmpty() && !CustomerAccNo.isEmpty()
							&& !SeenGppAccounts.contains(GppAccNo)
							&& !SeenCustomerAccounts.contains(CustomerAccNo)) {

						Map<String, String> AccountMap = new HashMap<>();
						AccountMap.put("GppAccountNo", GppAccNo);
						AccountMap.put("CustomerAccountNo", CustomerAccNo);

						MatchedAccounts.add(AccountMap);
						SeenGppAccounts.add(GppAccNo);
						SeenCustomerAccounts.add(CustomerAccNo);

						// Stop when enough accounts are found
						if (MatchedAccounts.size() >= MaxGppAccounts) break;
					}

				} catch (Exception e) {
					System.out.println("Error parsing row: " + e.getMessage());
				}
			}


			// STEP 14.3: Scroll grid to load next set of records
			// ➤ Performs vertical scroll using mouse or JS if needed to load new data.
			if (MatchedAccounts.size() >= MaxGppAccounts) break;
			String Before = TopKey.get();
			boolean Moved = false;

			try {
				WebElement GridEl = WaitObj.until(d -> d.findElement(Grid));
				((JavascriptExecutor) driver).executeScript(
						"var el = arguments[0]; var e = new WheelEvent('wheel', {deltaY: arguments[1], bubbles: true}); el.dispatchEvent(e);",
						GridEl, 800);
				Thread.sleep(600);
				String After = TopKey.get();
				Moved = (After != null && !After.isEmpty() && !After.equals(Before));
			} catch (Exception ignored) {}

			// STEP 14.4: Fallback scroll using vertical scrollbar
			// ➤ If the JS scroll didn’t move the grid, try dragging scrollbar manually.
			if (!Moved) {
				try {
					WebElement Face = WaitObj.until(d -> d.findElement(VScrollFace));
					new Actions(driver).clickAndHold(Face).moveByOffset(0, 180).release().perform();
					Thread.sleep(700);
					String After = TopKey.get();
					Moved = (After != null && !After.isEmpty() && !After.equals(Before));
				} catch (Exception ignored) {}
			}

			// STEP 14.5: Last fallback — use PAGE_DOWN
			// ➤ If still no movement, simulate pressing PAGE_DOWN key.
			if (!Moved) {
				try {
					List<WebElement> Cells = driver.findElements(GppAccLocator);
					WebElement FocusCell = !Cells.isEmpty() ? Cells.get(Math.min(1, Cells.size() - 1)) : null;
					if (FocusCell != null) {
						new Actions(driver).moveToElement(FocusCell).click().perform();
					} else {
						WebElement GridEl = WaitObj.until(d -> d.findElement(Grid));
						new Actions(driver).moveToElement(GridEl, 10, 10).click().perform();
					}
					new Actions(driver).sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();
					Thread.sleep(700);
					String After = TopKey.get();
					Moved = (After != null && !After.isEmpty() && !After.equals(Before));
				} catch (Exception ignored) {}
			}

			// STEP 14.6: Update scroll tracking variables
			ScrollCount++;
			if (Moved) {
				NoMovementTries = 0;
				System.out.println("Grid moved, continuing scroll");
			} else {
				NoMovementTries++;
				System.out.println("Grid did not move, attempt " + NoMovementTries);
				if (NoMovementTries >= 3) break;
			}
		}

		// STEP 15: Finalize and return results
		//Reverses list to maintain original order and puts all data into the result map.
		Collections.reverse(MatchedAccounts);
		ResultMap.put("MatchedAccounts", MatchedAccounts);
		ResultMap.put("TotalFound", MatchedAccounts.size());
		System.out.println("Finished scanning. Total Matched=" + MatchedAccounts.size());
		System.out.println(MatchedAccounts);

		// STEP 16: Switch back to original browser window
		driver.switchTo().window(OriginalWindow);

		// STEP 17: Return result map
		//Returns all matched GPP accounts with their details.
		return ResultMap;

	}
	/// <summary>
	/// To find the Installment Months for a given Customer Account in Retail Deposit page
	///Updated for GG And GS Schemes
	/// Author: Benson & Nandagopan
	/// </summary>
	public double GetInstallmentMonthsByCustomerAccount(String CustomerNo,String GPPAccount) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();
		// Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		double InstallmentMonths=0.00;
		if(!GPPAccount.contains("GS"))
		{Thread.sleep(7000);
		base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 30);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchRetailDepositDetails);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Filter by Gpp AccNum in deposit trans id 
		//Thread.sleep(3000);
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Deposit trans id"));
			Thread.sleep(5000);
			if(base.isPresent(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositTransId")))
			{
				break;
			}
		}
		//Thread.sleep(9000);  
		base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), 7);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), GPPAccount);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_DepositTransId_ApplyFilters_label", "Apply"));
		//Thread.sleep(9000);   

		//Filter by Customer Number sometimes same gb account is mapped to diff account so to prevent that 
		//base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Customer account"));
		Thread.sleep(15000);
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Customer account"));
			Thread.sleep(5000);
			if(base.isPresent(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_CustAccount")))
			{
				break;
			}
		}
		Thread.sleep(9000);  
		base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_CustAccount_CustAccount_Input"), 7);

		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_CustAccount_CustAccount_Input"),CustomerNo );
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_CustAccount_ApplyFilters_label", "Apply"));
		//Thread.sleep(9000);  
		base.WaitforElementToBeInteractable(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_CustomerAccountInput("fixedDataTableRowLayout_rowWrapper", "Customer account"),7);

		// STEP 4: Capture list of filtered customer account rows
		//  Finds all matching customer account elements in the grid.
		//List<WebElement> CustomerRows = driver.findElements(By.xpath("//div[@class='fixedDataTableRowLayout_rowWrapper']//input[@aria-label='Customer account']")); 
		List<WebElement> CustomerRows = driver.findElements(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_CustomerAccountInput("fixedDataTableRowLayout_rowWrapper", "Customer account"));  

		// STEP 5: Get the total count of rows (Installment Months)
		//  Each row represents one installment month.
		int RowCount = CustomerRows.size();
		InstallmentMonths = (double) RowCount;
		}
		else {
			Thread.sleep(7000);
			base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 30);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchGPPAccount);
			Thread.sleep(5000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
			Thread.sleep(10000);

			//Filter by Gpp AccNum in deposit trans id 
			//Thread.sleep(3000);
			for(int i=0;i<5;i++) {
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "GPP account no"));
				Thread.sleep(5000);
				if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_GPPAccountNo_GPPAccountNo_Input_")))
				{
					break;
				}
			}
			//Thread.sleep(9000);  
			base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_GPPAccountNo_GPPAccountNo_Input_"), 7);
			base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_GPPAccountNo_GPPAccountNo_Input_"), GPPAccount);
			base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_GPPAccountNo_ApplyFilters_label", "Apply"));
			//Thread.sleep(9000);   

			//Filter by Customer Number sometimes same gb account is mapped to diff account so to prevent that 
			//base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Customer account"));
			Thread.sleep(3000);
			for(int i=0;i<5;i++) {
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Customer account"));
				Thread.sleep(5000);
				if(base.isPresent(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("__FilterField_Grid_CustAccount_CustAccount_Input_0_0_input")))
				{
					break;
				}
			}
			//Thread.sleep(9000);  
			base.WaitforElementToBeInteractable(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("__FilterField_Grid_CustAccount_CustAccount_Input_0_0_input"), 7);

			base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("__FilterField_Grid_CustAccount_CustAccount_Input_0_0_input"),CustomerNo );
			base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_CustAccount_ApplyFilters_label", "Apply"));
			//Thread.sleep(9000);  
			base.WaitforElementToBeInteractable(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_CustomerAccountInput("fixedDataTableRowLayout_rowWrapper", "Customer account"),7);

			LocalDate CurrentDate = LocalDate.now();
			By AccDate = By.xpath("//div[@class='dyn-container _1cq1e0l dyn-dateEdit']//input[@aria-label='Account opening date']");
			String OpeningDate= base.GetValue(AccDate);
			LocalDate StartingDate = LocalDate.parse(
					OpeningDate,
					DateTimeFormatter.ofPattern("M/d/yyyy")
					);

			InstallmentMonths = ChronoUnit.MONTHS.between(StartingDate, CurrentDate);


		}
		// STEP 6: Log output for verification
		System.out.println("Gpp Account: " + GPPAccount);
		System.out.println("Total Installment Months1: " + InstallmentMonths);

		// STEP 7: Switch back to original window
		driver.switchTo().window(OriginalWindow);

		// STEP 8: Return InstallmentMonths
		return InstallmentMonths;

	}

	/// <summary>
	/// To Create a promotion discount agreement
	/// Author: Sangeetha M S
	/// </summary>
	public void CreateValidPromotionDiscountAgreement(String Category) throws Exception
	{
		// Login to ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// Search Promotion discount 
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Click on New Button
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(3000);

		//Enter From date
		// Click date picker
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_FromDate", "dyn-date-picker-button"));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Today"));

		//Enter To date
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_ToDate", "dyn-date-picker-button"));
		LocalDate NextToDate = LocalDate.now().plusDays(1);
		String ToNextDay = String.valueOf(NextToDate.getDayOfMonth());
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_Date(ToNextDay));

		//Advance applicability
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("AVAMGD_PromotionAgreementValueWise_AdvanceApplicabilityDate","dyn-date-picker-button"));
		LocalDate Tomorrow = LocalDate.now().plusDays(1);
		String NextDay = String.valueOf(Tomorrow.getDayOfMonth());
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_Date(NextDay));

		//Promotion code
		Thread.sleep(3000);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"),UtilityTestData.PromotionCode);

		//Category-Gold
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"),Category);

		//Store number
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"),UtilityTestData.WarehouseId);

		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj= new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();
		Thread.sleep(15000);

		//Multiple of Amount.
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_MultipleOf_"));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", driver.findElement(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Multiple of",1)));
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_MultipleOf_"),UtilityTestData.AmountMultiple);

		//Quantity	
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"));
		Thread.sleep(3000);
		js.executeScript("arguments[0].value = '';", driver.findElement(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Quantity",1)));
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"),UtilityTestData.MultiplyQuantity);

		//Click Overwrite /Append
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dyn-container _w611ys dyn-svg-symbol dyn-combobox-button"));
		base.ClickCondition(LoginPageObj.Ele_ERPNo(UtilityTestData.PromotionType));

		//Click on activate and save
		base.ClickCondition(StatementPostingObj.Btn_Calculate("Activate","Activate"));
		System.out.println("****Activated successfully****");

		//Go to distribution schedule
		Thread.sleep(15000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Search n-agreement
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "N-AGREEMENT");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);

		//Filter the job ""N-AGREEMENT"" and click on ""Run now""
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "N-AGREEMENT");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);
		System.out.println("N agreement completed");

		base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_Alert("Run now"), 20);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Run now successfully completed");

		//Click on ok
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		//Go to Batch Job-->check the job status changed has been changed to ended
		boolean JobEnded = false;
		int MaxAttempts = 8; 
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int Attempt = 1; Attempt <= MaxAttempts; Attempt++) 
		{
			try 
			{
				//Search My Batch Job
				Thread.sleep(10000);
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

				//Sort by "Scheduled start date/time"
				Thread.sleep(2000);
				By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
				Thread.sleep(1000);
				//Wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.waitForClickable(sortColumn, 90);
				base.ClickCondition(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Wait for "Ended" job
				Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				JobEnded = true;
				break; // success, break the loop
			} 
			catch (StaleElementReferenceException stale) 
			{
				System.out.println("Attempt " + Attempt + ": Stale element. Retrying after refresh...");
				if (Attempt < MaxAttempts)
				{
					driver.navigate().refresh();
					Thread.sleep(3000);
				} 
				else
				{
					throw new RuntimeException("Stale element not resolved after retries", stale);
				}
			} 
			catch (TimeoutException timeout)
			{
				System.out.println("Attempt " + Attempt + ": Timeout while waiting for 'Ended' job status.");
				if (Attempt < MaxAttempts) 
				{
					driver.navigate().refresh();
					Thread.sleep(3000);
				} 
				else
				{
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
			System.out.println("Completed attempt"+Attempt);
		}
		Thread.sleep(10000);
	}

	/// <summary>
	/// To Create  Tranfer order bulk BC TC_227
	/// Author: Christy Reji
	/// </summary>
	public String CreateTranferOrderBulk() throws InterruptedException {
		//Step 1: Go to ERP of required store .
		//PreRequisite Step 1: Search On hand list
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.Bulk_Onhandlist);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//PreRequisite Step 2: Enter warehouse& Desig code(Eg:5205)
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventSum"),UtilityTestData.Bulk_Product);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventSum"), "ENTER");
		//Warehouse Selection
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventDim_InventLocationId"),UtilityTestData.WarehouseId+"BO");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventDim_InventLocationId"), "ENTER");

		//PreRequisite Step 3: Click on Apply button
		base.excuteJsClick(CustomerCreationObj.Ele_BankAccounts("ApplyFilters_label"));

		//PreRequisite Step 4: Click on dimensions
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Dimensions"));

		//PreRequisite Step 5:Click on checkbox
		//PreRequisite Step 5.1: Configuration
		base.ClickCondition(CustomerCreationObj.Ele_BankAccounts("ProductDimensions_ConfigIdFlag_check"));

		//PreRequisite Step 5.2://Site
		base.ClickCondition(CustomerCreationObj.Ele_BankAccounts("StorageDimensions_InventSiteIdFlag_check"));

		//PreRequisite Step 5.3://Warehouse
		base.ClickCondition(CustomerCreationObj.Ele_BankAccounts("StorageDimensions_InventLocationIdFlag_check"));

		//PreRequisite Step 5.4://Batch Number
		base.ClickCondition(CustomerCreationObj.Ele_BankAccounts("TrackingDimensions_InventBatchIdFlag_check"));

		//PreRequisite Step 6://Click on OK
		base.ScrollIntoView(CustomerCreationObj.Ele_BankAccounts("Ok_label"));
		Thread.sleep(500);
		base.ClickCondition(CustomerCreationObj.Ele_BankAccounts("Ok_label"));


		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(15000);

		// Get all AvailPhysical input fields
		List<WebElement> AvailInputs =base.GetElement(NormalSaleGoldAndSilverObj.Sel_AvailPhysicalList("AvailPhysical", "_input"));
		String BatchId="";
		String Purity="";
		for (WebElement AvailInput : AvailInputs) {
			String AvailValue = AvailInput.getAttribute("value");
			// Skip rows where AvailPhysical is empty
			if (AvailValue == null || AvailValue.trim().isEmpty()) {continue;}
			WebElement Row = AvailInput.findElement(By.xpath("./ancestor::div[contains(@class,'fixedDataTableRowLayout_rowWrapper')]"));
			// ----------- Fetch BatchId from same row --------------
			WebElement BatchInput = Row.findElement(By.xpath(".//input[contains(@id,'InventoryDimensionsGrid_inventBatchId') and contains(@id,'_input')]"));
			BatchId = BatchInput.getAttribute("value");
			// ----------- Fetch Purity (configId) from same row -----
			WebElement PurityInput = Row.findElement(By.xpath(".//input[contains(@id,'InventoryDimensionsGrid_configId') and contains(@id,'_input')]"));
			Purity = PurityInput.getAttribute("value");
			System.out.println("BatchId: " + BatchId +" | Purity: " + Purity +" | AvailPhysical: " + AvailValue);
		}

		//Step 2: Search Transfer Order
		base.newWindow(2);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.Bulk_TransferOrder);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Step 3: Click on New
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		if(!base.isPresent(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"))) {
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.Bulk_TransferOrder);
			Thread.sleep(5000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
			Thread.sleep(10000);
			base.ClickCondition(LoginPageObj.Edt_Alert("New"));			
		}

		//Step 4: Select From warehouse and To warehouse
		//Expected: Check if POS warehouse is not listed
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), UtilityTestData.WarehouseId);
		Thread.sleep(2000);
		List<WebElement>WareHouses = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Warehouse"));
		List<String>WareHousesList = new ArrayList<>();
		for(WebElement WareHouse : WareHouses ) {
			String WareHouseValue = WareHouse.getAttribute("value");
			WareHousesList.add(WareHouseValue);
		}

		asrt.assertFalse(WareHouses.contains(UtilityTestData.WarehouseId), "POS warehouse is listed in from warehouse list");

		base.clearData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), UtilityTestData.WarehouseId+"BO");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), "ENTER");
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdTo"), UtilityTestData.WarehouseId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdTo"), "ENTER");
		Thread.sleep(1000);

		//Step 5: Select Transfer type as "Bulk"
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ErpInputArrowClick("Transfer type","InventTransferTable","lookupDock-buttonContainer layout-ignoreArrange","lookupButton"));
		base.excuteJsClick(LoginPageObj.Ele_ERPYes("Bulk"));
		Thread.sleep(1000);

		//Step 6: Select Accounted under as "Gold"
		base.clearData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDMetalType"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDMetalType"), UtilityTestData.Bulk_AccountedUnder);

		//Step 7: Select Purpose code as "Stock Transfer"[00001]
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDPurposeCode"), UtilityTestData.Bulk_StockTransfer);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDPurposeCode"), "ENTER");
		Thread.sleep(1000);

		//Step 8.In Tranfer order lines ,Click on "Add"
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Add"));

		//Step9.Select Design Code as BC
		By DesignCodeField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Design Code", 1);
		base.ClickCondition(DesignCodeField);
		Thread.sleep(1000);
		WebElement DesignCodeFieldEl = driver.findElement(DesignCodeField);
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						DesignCodeFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						DesignCodeFieldEl,UtilityTestData.Bulk_Product
				);
		base.pressKey(DesignCodeField, "ENTER");
		Thread.sleep(1000);

		//10.In Line details, Add the Quantity
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Line details"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Line details"));
		Thread.sleep(12000);
		base.scrollToElementtoCenter(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Dimensions"));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Dimensions"));
		//Purity
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_configId"),Purity);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_configId"), "ENTER");
		//Batch Number
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_inventBatchId"),BatchId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_inventBatchId"), "ENTER");
		//Ingredients
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Ingredient"));
		Thread.sleep(12000);
		By QuantityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Quantity", 1);
		base.ClickCondition(QuantityField);
		Thread.sleep(1000);
		WebElement QuantityFieldEl = driver.findElement(QuantityField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityFieldEl, UtilityTestData.Bulk_AddLineDetailQty
				);
		base.pressKey(QuantityField, "ENTER");
		Thread.sleep(1000);

		//Step 11: In Transfer order lines, Add Transfer quantity
		By TransferQuantityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Transfer quantity", 1);
		base.ClickCondition(TransferQuantityField);
		Thread.sleep(1000);
		WebElement TransferQuantityFieldEl = driver.findElement(TransferQuantityField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						TransferQuantityFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						TransferQuantityFieldEl, UtilityTestData.Bulk_TransferOrderLineQty
				);
		base.pressKey(TransferQuantityField, "ENTER");
		Thread.sleep(1000);

		//Step 12: Click on Calculate line
		base.ClickCondition(LoginPageObj.Edt_Alert("Calculate line"));

		//Step 13: Click on ship
		wait.until(ExpectedConditions.visibilityOfElementLocated(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Check("InventTransferLine_MGDCostCalculation", "Yes")));		
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("ShipmentTab_button"));
		Thread.sleep(1000);

		//Step 14: Click on Generate picking list
		base.ClickCondition(LoginPageObj.Edt_Alert("Generate picking list"));

		//Step 15: Select "All" in Update dropdown and click on OK
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ErpArrowClick("pickUpdateQty","symbol"));
		base.buttonClick(LoginPageObj.Ele_ERPNo("All"));	
		Thread.sleep(2000);
		base.excuteJsClick(StatementPostingObj.Btn_Add("OK"));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoginPageObj.Edt_Alert("Processing operation - Transfer order - Picking list")));

		//Step 16: Click on "Ship transfer order"
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("ShipmentTab_button"));
		base.ClickCondition(LoginPageObj.Edt_Alert("Ship transfer order"));

		//Step 17: Select "All" in Update dropdown and click on OK
		//Expected: Check the status of the order has been changed to shipped
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ErpArrowClick("ShipUpdateQty","symbol"));
		base.ClickCondition(LoginPageObj.Ele_ERPNo("All"));
		Thread.sleep(1000);
		base.excuteJsClick(StatementPostingObj.Btn_Add("OK"));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoginPageObj.Edt_Alert("Processing operation - Transfer order - Shipment")));
		Thread.sleep(2000);
		String Status = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EntityStatus_TransferStatus"), "title");


		String TransferOrder = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_MetalRateBlankMsg("Transfer order:"));
		String TransferOrderNumber = TransferOrder.split(":")[1].trim();
		base.newWindow(0);
		return TransferOrderNumber;
	}


	/// <summary>
	/// To find OfferPercentage
	/// Author: Chandana Babu and Bharath
	/// </summary>
	public Map<String, List<String>> ToFindOfferPercentageForOfferAdvance(List<String>OfferId) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();           
		Map<String,List<String>> ValidOfferPercent = new HashMap<String, List<String>>();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),UtilityTestData.RetailDepositDetails);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		base.dragScrollToFindElement(driver, By.cssSelector(".ScrollbarLayout_faceHorizontal"), InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("MGDRetailDepositTable_DepositTransId"), 300);
		Thread.sleep(15000);

		for(int i =0; i<OfferId.size();i++) {
			List<String>OfferPercentage = new ArrayList<>();            
			int PageRetry = 0;
			boolean flowSuccess;
			while (PageRetry < 5) {
				flowSuccess = true;
				// ---------- FIRST FOR LOOP ----------
				boolean firstLoopSuccess = false;

				for (int j = 0; j < 5; j++) {
					base.UnifiedClickUpdated (
							InvoiceAdvanceWithPaymentMethodRTGSReceivedObj
							.Ele_CustomerAccountText("MGDRetailDepositTable_DepositTransId")
							);
					Thread.sleep(1000);

					if (base.isPresent(
							InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositTransId")
							)) {
						firstLoopSuccess = true;
						break;
					}
				}
				if (!firstLoopSuccess) {
					flowSuccess = false;
				}

				if (!flowSuccess) {
					PageRetry++;
					driver.navigate().refresh();
					base.setZoom(driver, 60);
					Thread.sleep(5000);
					Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));                  
					continue; 
				}
				// ---------- ACTION AFTER FIRST LOOP ----------
				base.clearData(
						NormalSaleGoldAndSilverObj
						.Ele_ERPCustAccntisExactly(
								"FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input")
						);
				base.setData(
						NormalSaleGoldAndSilverObj
						.Ele_ERPCustAccntisExactly(
								"FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"),
						OfferId.get(i)
						);
				Thread.sleep(1000);

				// ---------- SECOND FOR LOOP ----------
				boolean secondLoopSuccess = false;


				for (int j = 0; j < 5; j++) {
					base.UnifiedClickUpdated(
							CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply()
							);
					Thread.sleep(5000);

					if (!base.WaitforElementToBePresentWithTimeout(
							InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositTransId"),
							20)) {
						secondLoopSuccess = true;
						break;
					}
				}

				if (!secondLoopSuccess) {
					flowSuccess = false;
				}

				// ---------- FINAL DECISION ----------
				if (!flowSuccess) {
					PageRetry++;
					driver.navigate().refresh();
					base.setZoom(driver, 60);
					Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));                  
					continue; 
				}
				break; 
			}                           
			Thread.sleep(5000); 
			base.dragScrollToFindElement(driver, By.cssSelector(".ScrollbarLayout_faceHorizontal"), InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_FixWtDlvDate"), 20);
			String OfferPercent = base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Offer percentage "));
			OfferPercentage.add(OfferPercent);  
			String FixedDate = base.GetValue(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Fixed weight delivery date"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
			LocalDate DueDate = LocalDate.parse(FixedDate, formatter);
			if(LocalDate.now().isBefore(DueDate))
			{
				OfferPercentage.add("true");
				ValidOfferPercent.put("OfferID"+i,OfferPercentage);
			}
			else {
				OfferPercentage.add("false");
				ValidOfferPercent.put("OfferID"+i,OfferPercentage);
			}
		}
		driver.switchTo().window(OriginalWindow);
		return ValidOfferPercent;
	}

	/// <summary>
	/// To verify PMLA cash tracker
	/// Author: Chandana Babu
	/// </summary>
	public void VerifyPMLACashTracker(String CustomerNo, String ExpectedDailyCash, String ExpectedAdjustment) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.PMLACashTracker);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);

		Boolean IsPresent = false;
		int attempts = 0;
		int maxAttempts = 5;

		while (!IsPresent && attempts < maxAttempts) {

			attempts++;

			for (int j = 0; j < 5; j++) {
				base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("Grid_CustAccount"));
				Thread.sleep(1000);
				if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_CustAccount_CustAccount"))) break;
			}

			base.clearData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_CustAccount_CustAccount"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_CustAccount_CustAccount"), CustomerNo);
			Thread.sleep(1000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_CustAccount_CustAccount"), "ENTER");
			base.UnifiedClick(StatementPostingObj.Btn_Add("Grid_CustAccount_ApplyFilters"));
			Thread.sleep(5000);

			if (base.isPresent(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("Grid_DailyCashAmount"))) {

				By scrollbarLocator = By.cssSelector(".ScrollbarLayout_faceHorizontal");
				if (base.isPresent(scrollbarLocator)) {
					WebElement scrollbar = driver.findElement(scrollbarLocator);
					new Actions(driver).clickAndHold(scrollbar).moveByOffset(100, 0).release().perform();
					Thread.sleep(5000);
				}

				for (int i = 0; i < 5; i++) {
					base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("Grid_createdDateTime"));
					Thread.sleep(1000);
					if (base.isPresent(StatementPostingObj.Btn_Add("Descending_Grid_createdDateTime"))) break;
				}

				base.ClickCondition(StatementPostingObj.Btn_Add("Descending_Grid_createdDateTime"));
				Thread.sleep(5000);

				IsPresent = true;

			} else {
				driver.navigate().refresh();
				Thread.sleep(5000);
			}
		}

		if (!IsPresent)
			Assert.fail("Daily cash details not found after " + maxAttempts + " attempts for Customer " + CustomerNo + " in PMLA Cash Tracker");

		String ActualDailyCash = base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("Grid_DailyCashAmount")).replaceAll("[^\\d.]", "").trim();
		String ActualAdjustment = base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("Grid_AdjustmentCashAmount")).replaceAll("[^\\d.]", "").trim();

		if (ExpectedDailyCash != null && !ExpectedDailyCash.trim().isEmpty())
			asrt.assertEquals(Double.parseDouble(ActualDailyCash), Double.parseDouble(ExpectedDailyCash), 1.0, "Daily cash amount mismatch: Expected is " + ExpectedDailyCash + " but got " + ActualDailyCash + " in PMLA cash tracker page");

		if (ExpectedAdjustment != null && !ExpectedAdjustment.trim().isEmpty())
			asrt.assertEquals(Double.parseDouble(ActualAdjustment), Double.parseDouble(ExpectedAdjustment), 1.0, "Adjustment cash amount mismatch: Expected is " + ExpectedAdjustment + " but got " + ActualAdjustment + " in PMLA cash tracker page");
	}

	/// <summary>
	/// To find Valid CashMOP scehem from retail deposit details
	/// Author: Chandana Babu
	/// </summary>		

	public Map<String, Map<String, Object>>  CashMopSchemes(String DepositType, String SchemeCode, int RequiredCount, int RequiredAccount) throws Exception {

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.switchTo().newWindow(WindowType.TAB);
		List<String> Tabs = new ArrayList<>(driver.getWindowHandles());
		driver.navigate().to(UtilityTestData.ERPURL);

		// --- Search Deposit ---
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 50);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.RetailDepositDetails);
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"), 30
				);

		// --- Set Store Filter ---
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_Store"),
				10
				);
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_Store"),
				UtilityTestData.WarehouseId
				);
		base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//Filter Scheme Code
		Thread.sleep(3000);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Deposit trans id"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"),
				5,
				2
				);

		//Thread.sleep(7000); 
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), 7);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), SchemeCode);  
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_DepositTransId_ApplyFilters_label", "Apply"));

		WebElement ScrollbarMove = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj= new Actions(driver);
		ActionsObj.clickAndHold(ScrollbarMove).moveByOffset(200, 0).release().perform();
		Thread.sleep(15000);
		base.ClickWithRetry(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "MGDRetailDepositTable_DepositType"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositType"),
				10
				);
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_DepositType"),
				DepositType
				);
		for (int i = 0; i < 5; i++) {
			Thread.sleep(5000);			
			if (base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositType"), 5)) {
				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
				System.out.println("Unable to click on Apply button");
				continue;
			}
			break;
		}
		ActionsObj.clickAndHold(ScrollbarMove).moveByOffset(300, 0).release().perform();
		Thread.sleep(15000);
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_TransDate"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_TransDate"),
				20
				);
		base.UnifiedClick(LoginPageObj.Btn_SingnIn("__Descending_MGDRetailDepositTable_TransDate"));
		base.ClickWithRetry(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "MGDRetailDepositTable_CashMOP"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_CashMOP"),
				10
				);
		//*******************
		base.ClickCondition(By.xpath("//div[contains(@aria-labelledby,'FilterField_MGDRetailDepositTable_CashMOP')]//span[@class='button-label button-label-dropDown']"));
		base.ClickCondition(By.xpath("//div[@data-dyn-form-name='MGDRetailDepositTable_CashMOP']/following-sibling::div//span[normalize-space(text())='not equal to']"));
		//base.ClickCondition(By.xpath("//div[@data-dyn-form-name='MGDRetailDepositTable_CashMOP']/following-sibling::div//span[normalize-space(text())='between']"));
		//****************************
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_CashMOP_CashMOP"),
				"0.00"
				);	
		for (int i = 0; i < 5; i++) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_CashMOP"), 3)) {
				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
				System.out.println("Unable to click on Apply button");
				continue;
			}
			break;
		}

		// IsAdjusted = No	    
		for (int i = 0; i < 5; i++) {
			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccountText("MGDRetailDepositTable_IsAdjusted"));
			Thread.sleep(1000);
			if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly(
					"FilterField_MGDRetailDepositTable_IsAdjusted_IsAdjusted_Input"))) break;
		}
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly(
				"FilterField_MGDRetailDepositTable_IsAdjusted_IsAdjusted_Input"), "No");
		Thread.sleep(1000);
		base.ClickCondition(StatementPostingObj.Btn_Add("MGDRetailDepositTable_IsAdjusted_ApplyFilters"));
		Thread.sleep(5000);

		ActionsObj.clickAndHold(ScrollbarMove).moveByOffset(-400, 0).release().perform();
		Thread.sleep(15000);

		final By CustomerAccounts =
				By.xpath("//input[contains(@id,'MGDRetailDepositTable_CustAccount')]");

		final By DepositIdLocator =
				CustomerCreationObj.Ele_ERPCustid("Deposit trans id");

		final By Grid =
				By.cssSelector("div[role='grid']");

		final By VScrollFace =
				By.cssSelector(".ScrollbarLayout_mainVertical .ScrollbarLayout_face");

		Map<String, Integer> DepositCount = new HashMap<>();
		Set<String> SeenDepositIds = new HashSet<>();
		List<String> ValidDeposits = new ArrayList<>();
		Map<String, Map<String, Object>> ValidDepositDetails = new HashMap<>();
		Map<String, Map<String, Object>> FinalResult = new HashMap<>();

		Actions ActionObj = new Actions(driver);

		// Pattern: 42GB000000939 style
		Pattern DepositPattern =
				Pattern.compile("^\\d{2}" + SchemeCode + "\\d{9}$");

		// Focus grid
		WebElement ScrollContainer =
				driver.findElement(By.cssSelector(".ScrollbarLayout_mainVertical"));
		ScrollContainer.click();

		int StableScrolls = 0;
		final int MaxStableScrolls = 10;
		boolean StopScrolling = false;

		//Helper: get top visible deposit id
		java.util.function.Supplier<String> TopDepositKey = () -> {
			try {
				List<WebElement> List = driver.findElements(DepositIdLocator);
				if (!List.isEmpty())
					return List.get(0).getAttribute("value");
			} catch (Exception ignored) {}
			return "";
		};

		while (true) {

			base.WaitforElementToBePresentWithTimeout(DepositIdLocator, 30);

			List<WebElement> Deposits = driver.findElements(DepositIdLocator);
			List<WebElement> Customers = driver.findElements(CustomerAccounts);

			int NewDataInThisPass = 0;

			for (int I = 0; I < Math.min(Deposits.size(), Customers.size()); I++) {
				try {
					String DepositId = Deposits.get(I).getAttribute("value").trim();
					String CustomerId = Customers.get(I).getAttribute("value").trim();

					if (DepositId.isEmpty()) continue;

					// ❗ STOP when invalid ID found
					if (!DepositPattern.matcher(DepositId).matches()) {
						System.out.println("Stopping scroll. Invalid Deposit ID found: " + DepositId);
						StopScrolling = true;
						break;
					}

					if (!SeenDepositIds.add(DepositId)) continue;

					NewDataInThisPass++;

					int Count = DepositCount.getOrDefault(DepositId, 0) + 1;
					DepositCount.put(DepositId, Count);

					ValidDepositDetails.putIfAbsent(DepositId, new HashMap<>());
					Map<String, Object> Details = ValidDepositDetails.get(DepositId);

					Details.put("DepositId", DepositId);
					Details.put("CustomerID", CustomerId);

					if (Count >= RequiredCount && !ValidDeposits.contains(DepositId)) {
						ValidDeposits.add(DepositId);
					}

				} catch (org.openqa.selenium.StaleElementReferenceException ignored) {
				}
			}

			if (StopScrolling) break;

			// ---------- END DETECTION ----------
			if (NewDataInThisPass == 0) {
				StableScrolls++;
			} else {
				StableScrolls = 0;
			}

			if (StableScrolls >= MaxStableScrolls) break;

			// ================= SCROLL LOGIC =================
			String Before = TopDepositKey.get();
			boolean Moved = false;

			// 🔽 1️⃣ Mouse wheel scroll
			try {
				WebElement GridEl = driver.findElement(Grid);
				((JavascriptExecutor) driver).executeScript(
						"var e = new WheelEvent('wheel', {deltaY: 900, bubbles: true});" +
								"arguments[0].dispatchEvent(e);", GridEl);

				Thread.sleep(700);
				String After = TopDepositKey.get();
				Moved = (After != null && !After.equals(Before));
			} catch (Exception ignored) {}

			// PAGE_DOWN fallback
			if (!Moved) {
				try {
					ActionObj.sendKeys(Keys.PAGE_DOWN).perform();
					Thread.sleep(700);
					String After = TopDepositKey.get();
					Moved = (After != null && !After.equals(Before));
				} catch (Exception ignored) {}
			}

			//Scrollbar drag (last fallback)
			if (!Moved) {
				try {
					WebElement Face = driver.findElement(VScrollFace);
					ActionObj.clickAndHold(Face)
					.moveByOffset(0, 180)
					.release()
					.perform();

					Thread.sleep(700);
					String After = TopDepositKey.get();
					Moved = (After != null && !After.equals(Before));
				} catch (Exception ignored) {}
			}

			if (!Moved) {
				StableScrolls++;
			}
		}

		// -------- Build Final Result --------
		for (String Deposit : ValidDeposits) {
			FinalResult.put(Deposit, ValidDepositDetails.get(Deposit));
		}

		System.out.println("Total unique deposits fetched : " + SeenDepositIds.size());
		System.out.println("Valid deposits count          : " + FinalResult.size());

		driver.switchTo().window(Tabs.get(0));
		return FinalResult;
	}

	/// <summary>
	/// To get CashMOP scheme details from retail deposit details
	/// Author: Chandana Babu
	/// </summary>		

	public Map<String, Double> CashSchemeDatails (String CustomerNo,  String DepositId) throws Exception {

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.switchTo().newWindow(WindowType.TAB);
		List<String> Tabs = new ArrayList<>(driver.getWindowHandles());
		driver.navigate().to(UtilityTestData.ERPURL);
		Map<String, Double> CashMopSchemeDetails = new HashMap<>();

		// --- Search Deposit ---
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 50);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.RetailDepositDetails);
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"), 30
				);

		// --- Set Store Filter ---
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_Store"),
				10
				);
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_Store"),
				UtilityTestData.WarehouseId
				);
		base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//Filter Customer account
		//Thread.sleep(15000);
		Thread.sleep(3000);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Customer account"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_CustAccount_CustAccount_Input"),
				5,
				2
				);

		Thread.sleep(3000);  
		base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_CustAccount_CustAccount_Input"), 7);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_CustAccount_CustAccount_Input"),CustomerNo );
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_CustAccount_ApplyFilters_label", "Apply"));

		//Filter Scheme Code
		Thread.sleep(3000);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Deposit trans id"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"),
				5,
				2
				);

		Thread.sleep(3000); 
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), 7);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_DepositTransId_DepositTransId_Input"), DepositId);  
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_DepositTransId_ApplyFilters_label", "Apply"));
		Thread.sleep(9000); 

		WebElement ScrollbarMove = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj= new Actions(driver);
		ActionsObj.clickAndHold(ScrollbarMove).moveByOffset(400, 0).release().perform();
		Thread.sleep(15000);

		double TotalCashMop = 0.00;
		List<WebElement> CashMop = base.GetElement(InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("MGDRetailDepositTable_CashMOP"));
		for (WebElement Cash : CashMop) {
			Thread.sleep(3000);
			TotalCashMop = TotalCashMop+  Double.parseDouble(Cash.getAttribute("value").replaceAll("[^\\d.]", "").trim());				
		}
		int RowCount = CashMop.size();
		double InstallmentMonths = (double) RowCount;
		Thread.sleep(3000);
		CashMopSchemeDetails.put("InstallmentMonths", InstallmentMonths);
		CashMopSchemeDetails.put("TotalCashMop", TotalCashMop);

		driver.switchTo().window(Tabs.get(0));

		return CashMopSchemeDetails;			
	}

	///<summary>
	/// Method To get invoice no, Total Netweight, Line Count, Net Total... after normal sale with requiredweight sku 
	/// Author : Aiswarya
	///</summary>
	public Map<String, Object> GetInvoiceDetailsAfterNormalSaleWt(String CustomerNo, List<String> CounterTransfer ) throws Exception {

		// Step 3: Select customer and click in add to sale button
		appUtils.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);

		// Step 4: Scan Multiple SKUs
		List<String> AllSkuList = new ArrayList<>();
		for (int J = 0; J + 2 < CounterTransfer.size(); J += 3) {
			List<String> RequiredSkuList = appUtils.SelectMultipleSKUs(
					Integer.parseInt(CounterTransfer.get(J)), "Inter", CounterTransfer.get(J + 1), CounterTransfer.get(J + 2));
			AllSkuList.addAll(RequiredSkuList);
			appUtils.HamBurgerButtonClick("iconShop");
		}

		// Step 5: Extract SKU table data
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int I = 0; I < AllSkuList.size(); I++) {
			appUtils.SKUIngridentTableValues(AllSkuList.get(I), I + 1, ScannedSkuDataMap);
		}

		// Step 6: Extract required values
		double TotalNetWeight = 0.0;
		String FirstSkuPurity = "N/A";
		String FirstSkuGoldRate = "Not Found";
		boolean FoundRg = false;

		double TotalRgWeightInFirstPurity = 0.0;
		double FirstPurity = 0.0;

		Map<String, String> SkuWiseData = new LinkedHashMap<>();

		for (int Index = 0; Index < AllSkuList.size(); Index++) {
			int SkuNumber = Index + 1;

			String NetWeightStr = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_NetWeight");
			try {
				double NetWeight = Double.parseDouble(NetWeightStr);
				TotalNetWeight += NetWeight;
			} catch (Exception Ex) {
				System.out.println(" - Error parsing Net Weight for SKU " + SkuNumber + ": " + NetWeightStr);
			}
			SkuWiseData.put("SKU_" + SkuNumber + "_NetWeight", NetWeightStr);

			String GrossWeight = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_GrossWeight", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_GrossWeight", GrossWeight);

			String StoneWeight = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_StoneWeight", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_StoneWeight", StoneWeight);

			String SkuTotal = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_Total", "0.0");
			SkuWiseData.put("SKU_" + SkuNumber + "_Total", SkuTotal);

			String RgWeightStr = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_TotalRGWeight", "0.0");
			double RgWeight = 0.0;
			try {
				RgWeight = Double.parseDouble(RgWeightStr);
			} catch (Exception Ex) {
				System.out.println(" Invalid RG Weight for SKU_" + SkuNumber + ": " + RgWeightStr);
				continue;
			}

			if (RgWeight == 0.0) continue;

			String ThisPurityStr = ScannedSkuDataMap.getOrDefault("SKU_" + SkuNumber + "_Purity", "").replaceAll("(?i)k", "").trim();
			double ThisPurity = 0.0;
			try {
				ThisPurity = Double.parseDouble(ThisPurityStr);
			} catch (Exception Ex) {
				System.out.println("Invalid Purity for SKU_" + SkuNumber + ": " + ThisPurityStr);
				continue;
			}

			if (!FoundRg) {
				FirstPurity = ThisPurity;
				FirstSkuPurity = ThisPurityStr;

				for (String Key : ScannedSkuDataMap.keySet()) {
					if (Key.startsWith("SKU" + SkuNumber + "_ITEM") && Key.endsWith("_ITEMID")) {
						String ItemKeyPrefix = Key.replace("_ITEMID", "");
						String ItemId = ScannedSkuDataMap.get(Key);
						if ("RG".equalsIgnoreCase(ItemId)) {
							FirstSkuGoldRate = ScannedSkuDataMap.getOrDefault(ItemKeyPrefix + "_RATE", "Not Found");
							break;
						}
					}
				}
				FoundRg = true;
			}

			double ThisSkuGoldRate = 0.0;
			for (String Key : ScannedSkuDataMap.keySet()) {
				if (Key.startsWith("SKU" + SkuNumber + "_ITEM") && Key.endsWith("_ITEMID")) {
					String ItemKeyPrefix = Key.replace("_ITEMID", "");
					String ItemId = ScannedSkuDataMap.get(Key);
					if ("RG".equalsIgnoreCase(ItemId)) {
						try {
							ThisSkuGoldRate = Double.parseDouble(
									ScannedSkuDataMap.getOrDefault(ItemKeyPrefix + "_RATE", "0.0")
									.replaceAll("[^0-9.]", ""));
						} catch (Exception Ex) {
							System.out.println(" Invalid RATE for SKU_" + SkuNumber + ": " + ScannedSkuDataMap.get(ItemKeyPrefix + "_RATE"));
						}
						break;
					}
				}
			}

			double FirstGoldRate = 0.0;
			try {
				FirstGoldRate = Double.parseDouble(FirstSkuGoldRate.replaceAll("[^0-9.]", ""));
			} catch (Exception Ex) {
				System.out.println(" Invalid First RG Rate: " + FirstSkuGoldRate);
				continue;
			}

			double ConvertedRgWeight = 0.0;
			if (FirstPurity == ThisPurity && FirstGoldRate == ThisSkuGoldRate) {
				ConvertedRgWeight = RgWeight;
			} else {
				ConvertedRgWeight = (RgWeight * ThisSkuGoldRate) / (FirstGoldRate);
				ConvertedRgWeight = Math.round(ConvertedRgWeight * 1000.0) / 1000.0;
			}

			TotalRgWeightInFirstPurity += ConvertedRgWeight;

			System.out.println("SKU_" + SkuNumber + " RG Weight = " + RgWeight +
					", Purity = " + ThisPurity + ", RG Rate = " + ThisSkuGoldRate +
					" => Converted to First Purity (" + FirstPurity + "K @ " + FirstGoldRate +
					") = " + ConvertedRgWeight);
		}

		System.out.println(" Total RG Weight Converted to First Purity (" + FirstPurity + "K): " + TotalRgWeightInFirstPurity);

		// Step 7: Finalize transaction
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNewNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");

		appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName;
		String InvoiceNo = pdfUtils.ExtractReceiptNoFrmSaleInvoice(TaxInvoicePath);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Step 8: Collect returned product names
		List<String> ReturnedProducts = new ArrayList<>();
		for (String Key : ScannedSkuDataMap.keySet()) {
			if (Key.matches("SKU\\d+_ITEM\\d+_ITEMNAME")) {
				ReturnedProducts.add(ScannedSkuDataMap.get(Key));
			}
		}

		// Step 9: Return all collected data
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		ResultMap.put("InvoiceNo", InvoiceNo);
		ResultMap.put("TotalNetWeight", String.format("%.3f", TotalNetWeight));
		ResultMap.put("TotalRgWeight", String.format("%.3f", TotalRgWeightInFirstPurity));
		ResultMap.put("FirstSkuPurity", FirstSkuPurity);
		ResultMap.put("FirstSkuGoldRate", FirstSkuGoldRate);
		ResultMap.put("SKUWiseDetails", SkuWiseData);
		ResultMap.put("SubTotal", SubTotal);
		ResultMap.put("Discount", Discount);
		ResultMap.put("TotalGrossWeight", TotalGrossWeight);
		ResultMap.put("Tax", Tax);
		ResultMap.put("TotalAmount", TotalAmount);
		ResultMap.put("NetTotal", NetTotal);
		ResultMap.put("LinesCount", LinesCount);
		ResultMap.put("ScannedSKUDataMap", ScannedSkuDataMap);
		ResultMap.put("TransactionDataLine", TransactionDataLine);
		ResultMap.put("AllSkuList",AllSkuList);
		return ResultMap;
	}

	/// <summary>
	/// To find Refunded AdvanceAccounts through Filter In the Retail deposit Page
	/// Author: Benson
	/// <summary>
	public Map<String, Object> RefundedAdvanceAccountDetails( int MaxAdvanceAccounts, String IsRefunded, String IsRefundBenefit, String AdvanceType,String DepositType) throws InterruptedException {

		String OriginalWindow = driver.getWindowHandle();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// --- Search Retail Deposit Details ---
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 50);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.RetailDepositDetails);
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"), 30
				);

		// --- Set Store Filter ---
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_Store"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_Store"),
				10
				);
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_Store"),
				UtilityTestData.WarehouseId 
				);
		base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// --- Set Deposit Type Filter ---
		base.dragScrollToFindElement(
				driver,
				By.cssSelector(".ScrollbarLayout_faceHorizontal"),
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "MGDRetailDepositTable_DepositType"),
				20
				);
		base.ClickWithRetry(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "MGDRetailDepositTable_DepositType"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositType"),
				10
				);
		base.setData(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_GridAmount("FilterField_MGDRetailDepositTable_DepositType"),
				DepositType 
				);
		for (int i = 0; i < 5; i++) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_DepositType"), 3)) {
				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
				System.out.println("Unable to click on Apply button");
				continue;
			}
			break;
		}

		// --- Set IsRefundedBenefit Filter ---
		base.dragScrollToFindElement(
				driver,
				By.cssSelector(".ScrollbarLayout_faceHorizontal"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"),
				300
				);
		base.ClickWithRetry(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccount("MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_AVAMGDIsRefundBenefitRedeemed"),
				20
				);
		base.setData(
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("AVAMGDIsRefundBenefitRedeemed"),
				IsRefundBenefit
				);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());

		// --- Set IsRefunded Filter ---
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		for (int i = 0; i < 5; i++) {
			base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_DepositType("columnheader", "IsRefunded"));
			if (base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_AVAMGDIsRefunded"), 5)) {
				base.setData(
						NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("AVAMGDIsRefunded"),
						IsRefunded
						);
				break;
			}
		}
		for (int i = 0; i < 5; i++) {
			base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
			if (!base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("MGDRetailDepositTable_AVAMGDIsRefunded"), 5)) {
				break;
			}
		}

		//Adding new column Advance Type
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Grid options"));
		Thread.sleep(2000);
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Insert columns..."));
		Thread.sleep(3000);

		//Click on advance Type checkbox
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "Advance Type");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(2000);
		for (int i = 0; i < 5; i++) {
			base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_GridSelected("FormControlFieldSelector_Grid_Selected_", "checkbox"));
			if(base.WaitforElementToBePresentWithTimeout(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Check("FormControlFieldSelector_Grid_Selected_","Yes"), 50))	
			{
				break;
			}

		}
		Thread.sleep(3000);
		//Click on Update button
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Btn_Estimation("Auto button dynamicsButton button-isDefault"));

		//wait for some time 
		Thread.sleep(10000);

		//Scroll to Advance Type and Filter By Advance Type
		base.dragScrollToFindElement(
				driver,
				By.cssSelector(".ScrollbarLayout_faceHorizontal"),
				LoginPageObj.Edt_AlertText("Advance type"),
				100
				);

		//		base.ClickWithRetry(
		//				LoginPageObj.Edt_AlertText("Advance type"), 
		//				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("AdvanceType_Input"),
		//				20
		//				);

		for(int i=0;i<5;i++)
		{
			base.ClickCondition(LoginPageObj.Edt_AlertText("Advance type"));
			Thread.sleep(2000);
			if(base.WaitforElementToBePresentWithTimeout(By.xpath("//div[contains(@aria-label,'Advance type') and @data-dyn-lastvisible='true']"), 20)) 
			{
				break;
			}
		}

		base.setData(
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("AdvanceType_Input"),
				AdvanceType
				);

		for (int i = 0; i < 5; i++) {
			base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
			if (!base.WaitforElementToBePresentWithTimeout(
					NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("AdvanceType_Input"), 5)) {
				break;
			}
		}

		// STEP 11: Initialize result containers
		// ➤ These hold the matched accounts and prevent duplicate entries.
		Thread.sleep(10000);
		Map<String, Object> ResultMap = new LinkedHashMap<>();
		Set<String> Seen = new HashSet<>();
		List<Map<String, String>> MatchedAccounts = new ArrayList<>();


		// ================= STEP 12: Initialize data structures =================
		final By CustomerAccounts = By.xpath("//input[contains(@id,'MGDRetailDepositTable_CustAccount')]");

		Set<String> UniqueCustomerAccounts = new LinkedHashSet<>(); // preserves order
		JavascriptExecutor Js = (JavascriptExecutor) driver;

		if(base.WaitforElementToBePresentWithTimeout(By.cssSelector(".ScrollbarLayout_mainVertical"),10))
		{

			// ================= STEP 13: Vertical scrollbar element =================
			WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_mainVertical"));
			boolean ReachedEnd = false;
			int PreviousScrollTop = -1;

			// ================= STEP 14: Scroll + collect customer accounts =================
			while (!ReachedEnd && UniqueCustomerAccounts.size() < MaxAdvanceAccounts) {

				// Wait until at least one row is visible
				base.WaitforElementToBePresentWithTimeout(CustomerAccounts, 30);

				List<WebElement> Customers = driver.findElements(CustomerAccounts);

				for (WebElement customer : Customers) {
					try {
						String CustomerAccNo = customer.getAttribute("value").trim();

						if (!CustomerAccNo.isEmpty()) {
							UniqueCustomerAccounts.add(CustomerAccNo);
						}

						if (UniqueCustomerAccounts.size() >= MaxAdvanceAccounts) {
							ReachedEnd = true;
							break;
						}
					} catch (Exception ignored) {}
				}

				if (ReachedEnd) break;

				// ================= STEP 14.1: Detect end of grid =================
				Long ScrollTop = (Long) Js.executeScript("return arguments[0].scrollTop;", Scrollbar);
				Long ScrollHeight = (Long) Js.executeScript("return arguments[0].scrollHeight;", Scrollbar);
				Long ClientHeight = (Long) Js.executeScript("return arguments[0].clientHeight;", Scrollbar);

				// End reached
				if (ScrollTop + ClientHeight >= ScrollHeight - 5) {
					break;
				}

				// No movement protection
				if (ScrollTop.intValue() == PreviousScrollTop) {
					break;
				}

				PreviousScrollTop = ScrollTop.intValue();

				// ================= STEP 14.2: Scroll down =================
				Js.executeScript("arguments[0].scrollTop += 250;", Scrollbar);
				Thread.sleep(500);
			}

			// ================= STEP 15: Prepare result =================
			//Map<String, Object> ResultMap = new HashMap<>();
			ResultMap.put("CustomerAccounts", new ArrayList<>(UniqueCustomerAccounts));
			ResultMap.put("TotalFound", UniqueCustomerAccounts.size());

			System.out.println("Customer Accounts Found: " + UniqueCustomerAccounts.size());
			System.out.println(UniqueCustomerAccounts);

			// ================= STEP 16: Switch back to original tab =================
			driver.switchTo().window(OriginalWindow);

			// ================= STEP 17: Return result =================
			return ResultMap;
		}
		else {

			// ================= Collect visible customer accounts (NO SCROLLBAR) =================
			base.WaitforElementToBePresentWithTimeout(CustomerAccounts, 30);

			List<WebElement> Customers = driver.findElements(CustomerAccounts);

			for (WebElement customer : Customers) {
				try {
					String CustomerAccNo = customer.getAttribute("value").trim();
					if (!CustomerAccNo.isEmpty()) {
						UniqueCustomerAccounts.add(CustomerAccNo);
					}
				} catch (Exception ignored) {}
			}

			// ================= STEP 15: Prepare result =================
			//Map<String, Object> ResultMap = new HashMap<>();
			ResultMap.put("CustomerAccounts", new ArrayList<>(UniqueCustomerAccounts));
			ResultMap.put("TotalFound", UniqueCustomerAccounts.size());

			System.out.println("Customer Accounts Found: " + UniqueCustomerAccounts.size());
			System.out.println(UniqueCustomerAccounts);

			// ================= STEP 16: Switch back to original tab =================
			driver.switchTo().window(OriginalWindow);

			// ================= STEP 17: Return result =================
			return ResultMap;
		}

	}

	/// <summary>
	/// To Delete an existing agreement and  Create a promotion discount agreement
	///uses  multiple of amount that is 30000 and quantity 0.100
	/// Author: Robin, Bharath
	/// </summary>
	public Map<String, String> CreateDeleteValidPromotionDiscountAgreement(String Category) throws Exception {

		// Login to ERP
		String OriginalWindow= driver.getWindowHandle();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 150);

		//Prerequisite - To Delete If promotion discount exists
		// 1. Search On hand list
		//base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");

		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		js.executeScript("arguments[0].click();arguments[0].value=''; arguments[0].value='Promotion Agreement Value Wise';", driver.findElement(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(By.xpath("//span[text()='Promotion Agreement Value Wise' and @class='formCaption']"), 30);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),30))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),Category);

		//	js.executeScript("arguments[0].click();arguments[0].value=''; arguments[0].value='"+Category+"';", driver.findElement(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0")));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// ---------------- Store Number ------------
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
			Thread.sleep(2000);
			if(base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"),30))
			{
				break;
			}
		}
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"), UtilityTestData.WarehouseId);

		//js.executeScript("arguments[0].click();arguments[0].value=''; arguments[0].value='"+UtilityTestData.WarehouseId+"';", driver.findElement(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0")));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------

		//WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		//Actions ActionsObj = new Actions(driver);
		//ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		WebElement Scrollbar = null;
		Actions ActionsObj = new Actions(driver);
		try {
			Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));


			ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();

			System.out.println("Scrollbar found and scrolled successfully.");

		} catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step.");
		}
		Thread.sleep(15000);
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Active"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active")))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		base.ClickCondition(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_Refunded("Yes","__FilterField_Grid_Active_Active_Input"));
		base.UnifiedClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));

		//Select Row
		Thread.sleep(5000);
		if(base.WaitforElementToBePresentWithTimeout(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1),30))
		{
			//List<WebElement> Agreement = driver.findElements(By.xpath("//div[@title='Select or unselect row']"));
			List<WebElement> Agreement = base.GetElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_Delete("Select or unselect row"));
			for(int i=0;i<Agreement.size();i++)
			{
				Agreement = driver.findElements(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
				base.ClickCondition(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
				ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();
				Thread.sleep(5000);
				base.ClickCondition(StatementPostingObj.Btn_Calculate("Activate","Activate"));
			}
		}

		//Delete Promotion Discount
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable isFiltered", "Active"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active_Active_Input_0"),"No");
		for (int i = 0; i < 5; i++) {
			Thread.sleep(2000);
			if (base.WaitforElementToBePresentWithTimeout(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedObj.Ele_CustomerAccountFilter("Grid_Active"), 20)) {
				base.ClickCondition(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_Apply());
				System.out.println("Unable to click on Apply button");
				continue;
			}
			break;
		}
		if(base.WaitforElementToBePresentWithTimeout(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1),20)) {
			base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_Delete("Select or unselect all rows"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
			base.waitForClickable(StatementPostingObj.Btn_Calculate("SysBoxForm","Yes"),40);
			base.ClickCondition(StatementPostingObj.Btn_Calculate("SysBoxForm","Yes"));
			System.out.println("Existing Promotion Agreement deleted Successfully");
		}

		//Step:1 Go to promotion agreement ValueWise
		Thread.sleep(2000);
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 30);
		js.executeScript("arguments[0].value=''; arguments[0].value='"+UtilityTestData.SearchPromotionDiscount+"';", driver.findElement(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
		//base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step: 2 Click on New Button
		//Thread.sleep(2000);
		base.waitForClickable(LoginPageObj.Edt_Alert("New"),40);
		for(int i=0;i<5;i++) {	
			Thread.sleep(2000);
			base.ClickCondition(LoginPageObj.Edt_Alert("New"));
			if (driver.findElements(By.xpath("(//input[@aria-label='To date' and @readonly])[1]")).isEmpty()) {
				break;
			}
		}

		//Step:3.Enter the following
		//Step:3.1 From date
		Thread.sleep(5000);
		for(int i=0;i<5;i++) {
			base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_FromDate", "dyn-date-picker-button"));
			if(base.WaitforElementToBeInteractable(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Today"), 4)) {
				base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Today"));
				break;
			}
		}

		//Step:3.2 To Date
		Thread.sleep(3000);
		base.ClickCondition(By.xpath("(//input[@aria-label='To date'])[1]"));
		LocalDate NextToDate = LocalDate.now().plusMonths(1);
		String ToNextDay = NextToDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		base.setData(By.xpath("(//input[@aria-label='To date'])[1]"), ToNextDay);

		//Step:3.3 Advance Applicability
		Thread.sleep(3000);
		base.ClickCondition(By.xpath("(//input[@aria-label='Advance applicability'])[1]"));
		base.setData(By.xpath("(//input[@aria-label='Advance applicability'])[1]"), ToNextDay);

		//Step:3.4 Promotion Code
		Thread.sleep(3000);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"),UtilityTestData.PromotionCode);

		//Step:3.5 Category-Gold
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"),Category);

		//Step:3.6 Store number
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"),UtilityTestData.WarehouseId);
		WebElement Scrollbars = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObjs= new Actions(driver);
		ActionsObjs.clickAndHold(Scrollbars).moveByOffset(400, 0).release().perform(); 

		//Step:3.7 Multiple of Amount.
		base.waitForClickable(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1), 20);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1));
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();arguments[0].value = '';", driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1)));
		//		Thread.sleep(3000);		
		base.setData(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1),UtilityTestData.AmountMultiple);
		//js.executeScript("arguments[0].click();arguments[0].value=''; arguments[0].value='"+UtilityTestData.AmountMultiple+"';", driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1)));
		//base.pressKey(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1), "ENTER");

		//Step:3.8 Quantity
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"));
		js.executeScript("arguments[0].click();arguments[0].value = '';", driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Quantity",1)));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"),UtilityTestData.MultiplyQuantity);	

		//Step:3.9 Click on activate/deactivate and save
		//Click Overwrite /Append
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dyn-container _w611ys dyn-svg-symbol dyn-combobox-button"));
		base.ClickCondition(LoginPageObj.Ele_ERPNo(UtilityTestData.PromotionType));

		//Click on activate/deactivate and save
		Thread.sleep(5000);
		base.ClickCondition(StatementPostingObj.Btn_Calculate("Activate","Activate"));
		System.out.println("****Activated successfully****");

		//Step:4.Go to distribution schedule 
		Thread.sleep(5000);
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 10);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(1000);

		//Step: 5.Search n-agreement
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), 20);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "N-AGREEMENT");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);

		//Step:6.click on run now
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "N-AGREEMENT");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);
		System.out.println("N agreement completed");

		WebDriverWait Wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement RunNowBtn = Wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Run now successfully completed");

		//Step: 7.Click on Ok
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		//Step: 8 Go to Batch Job-->check the job status changed has been changed to ended
		boolean JobEnded = false;
		int MaxAttempts = 8;
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int Attempt = 1; Attempt <= MaxAttempts; Attempt++)

		{
			try
			{
				//Search My Batch Job
				Thread.sleep(10000);
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

				//Sort by "Scheduled start date/time"
				Thread.sleep(2000);
				By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
				Thread.sleep(1000);
				Wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.ClickCondition(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Wait for "Ended" job
				Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				JobEnded = true;
				System.out.println("Try block");

				break;
			}
			catch (StaleElementReferenceException Stale)
			{
				System.out.println("Attempt " + Attempt + ": Stale element. Retrying after refresh...");
				if (Attempt < MaxAttempts)

				{
					driver.navigate().refresh();
					Thread.sleep(3000);
				}
				else
				{
					throw new RuntimeException("Stale element not resolved after retries", Stale);
				}
			}
			catch (TimeoutException Timeout)
			{
				System.out.println("Attempt " + Attempt + ": Timeout while waiting for 'Ended' job status.");
				if (Attempt < MaxAttempts)
				{
					driver.navigate().refresh();
					Thread.sleep(3000);
				}
				else
				{
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", Timeout);
				}
			}

			System.out.println("Completed");
		}

		//Expected Result: Filtering to Verify if a tick mark appears against the newly added line.		
		// Searching the Promotion discount
		driver.navigate().refresh();
		Thread.sleep(5000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(By.xpath("//span[text()='Promotion Agreement Value Wise' and @class='formCaption']"), 30);

		//sorting the From date
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "From date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_FromDate_label","Sort newest to oldest"));
		Thread.sleep(5000);

		// sorting the To date
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "To date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_ToDate_label","Sort newest to oldest"));

		// selecting the category
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),30))

			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),Category);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"), "ENTER");	
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// Store number
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
			Thread.sleep(2000);
			if(base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"),30))
			{
				break;
			}
		}
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"), UtilityTestData.WarehouseId);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// selecting the active status
		WebElement ScrollBar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionObj = new Actions(driver);
		ActionObj.clickAndHold(ScrollBar).moveByOffset(650, 0).release().perform();
		Thread.sleep(15000);
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Active"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active")))
			{
				break;
			}

		}
		Thread.sleep(13000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		base.ClickCondition(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_Refunded("Yes","__FilterField_Grid_Active_Active_Input"));
		//base.UnifiedClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));

		// Expected Result: Verify if a tick mark appears against the newly added line.
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			// Get promotion data
			String PromotionDescription=base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("AVAMGD_GiftItemPromotionAgreementValueWise_PromoDescrption_"));
			System.out.println(PromotionDescription);

			String PromotionType = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("_PromotionAgreementValueWise_OverwriteAppend_"));
			System.out.println(PromotionType);

			String Amount = base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"));
			System.out.println("Amount    "+Amount);

			String Multiplier =  base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_MultipleOf_"));
			System.out.println(" Multiplier   "+Multiplier );

			// Switch back to main tab
			driver.switchTo().window(OriginalWindow);

			// Store in Map
			Map<String, String> PromotionData = new HashMap<>();
			PromotionData.put("PromotionType", PromotionType);
			PromotionData.put("Amount", Amount);
			PromotionData.put("Multiplier", Multiplier);
			return PromotionData;
		}
		else
		{
			return null;
		}

	}

}
