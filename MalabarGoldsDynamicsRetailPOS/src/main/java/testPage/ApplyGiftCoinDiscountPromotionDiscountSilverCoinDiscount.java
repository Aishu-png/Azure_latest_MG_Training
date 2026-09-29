package testPage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj;
import objectRepository.BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj;
import objectRepository.CheckGPPRefundBenefitWithMultipleCategoryItem_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import objectRepository.PdcEntryExecution_Obj;
import objectRepository.StatementPosting_Obj;
import objectRepository.MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj;
import testData.ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData;
import testData.BulkItemSalesSalesReturnServiceItemSalesStockSummary_TestData;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
import testData.MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.RequiredSKUResult;
import utilPack.AppUtilities.ReturnDetails;
import utilPack.AppUtilities.SKUResult;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.ErpUtilities.GPPAccountDetails;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.PdfUtilities.PdfInvoiceDetails;



public class ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData = new ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj =new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj = new BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj();
	MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj  MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj = new MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	PdcEntryExecution_Obj PdcEntryExecutionObj = new PdcEntryExecution_Obj();
	StatementPosting_Obj StatementPostingObj = new StatementPosting_Obj();
	CheckGPPRefundBenefitWithMultipleCategoryItem_Obj CheckGPPRefundBenefitWithMultipleCategoryItemObj = new CheckGPPRefundBenefitWithMultipleCategoryItem_Obj();
	ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj = new ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRTGSReceivedObj = new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();

	public ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount(WebDriver driver) {

		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);

	}

	// <summary>
	// Test Case Title : Generate GBC category item and check the offer not applied
	// Automation ID : TC277
	// Author: Nandagopan
	// </summary>
	public void TC277_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception{


		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search a customer with Phone number
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC277_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(4000);

		//Step 5: Scan SKU's and click on Add to cart button
		//Note: GoldCoin(Purity 99.9)			
		//Expected: Check calculation
		List <String> RequiredGoldCoinSku = appUtils.FetchPieceRateItem(UtilityTestData.GoldCoinSkus, 1);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                = "";
		String ItemNameInItemDetailsPge = "";
		String GrossWeight             = "";
		String TotalWithoutTax         = "";
		String WastageQty              = "";
		String WastageAmt              = "";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredGoldCoinSku.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = RequiredGoldCoinSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt =  ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}


		//Step 6: In billing, Go to discount->Apply Gift Coin Discount:
		//(Offer doesnt get applied)
		//Expected Condition:Verify that Offer doesn't get applied
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Gift Coin Discount"));

		asrt.assertTrue(base.isExists(LoginPageObj.Ele_ErrorMessage("h4 wrapTextWithoutHyphen wrapTextWithLineBreaks")),"Please add gift item into cart before apply promotion !");
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));
		Thread.sleep(2000);
		appUtils.VoidTransaction();

	}

	// <summary>
	// Test Case Title : Generate Gold, Diamond, Platinum, Uncut invoice and apply normal silver gift coin
	// Automation ID : TC312
	// Author: Chandana Babu
	// </summary>
	public void TC312_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer and click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4. Scan Gold, Diamond, Platinum, Uncut SKU and click on Add to cart button
		//Expected: Check calculation
		List<String> SkuListGold = appUtils.SelectMultipleSKUs(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_SkuCount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_FromCounterGold,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_MetalTypeGold);
		appUtils.HamBurgerButtonClick("iconShop");
		SKUResult SkuDiamondSaleItem = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_FromCounterDiamond,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_MetalTypeGold,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_DiamondRequiredQtyMultiple,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_SkuCount);
		List<String> SkuListDiamond = SkuDiamondSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> SkuListPlatinum = appUtils.SelectMultipleSKUs(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_SkuCount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_FromCounterPlatinum,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_MetalTypePlatinum);
		appUtils.HamBurgerButtonClick("iconShop");
		SKUResult SkuUncutSaleItem = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_FromCounterUncut,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_MetalTypeGold,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_UncutRequiredQtyMultiple,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_SkuCount);
		List<String> SkuListUncut = SkuUncutSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListGold);
		SkuList.addAll(SkuListDiamond);
		SkuList.addAll(SkuListPlatinum);
		SkuList.addAll(SkuListUncut);
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		Map<String, Double> ValuesToCalculateSilverCoin = new LinkedHashMap<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			String ProductName = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			String ExactProductName = ProductName.substring(ProductName.indexOf("-") + 1).trim();
			if(ExactProductName.toUpperCase().startsWith("G")) {
				ValuesToCalculateSilverCoin.put("TotalAmountGold",Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total")));
			}
			else if(ExactProductName.toUpperCase().startsWith("D")) {
				ValuesToCalculateSilverCoin.put("NetWeightDiamond",Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_NetWeight")));

			}
			else if(ExactProductName.toUpperCase().startsWith("P")) {
				ValuesToCalculateSilverCoin.put("TotalAmountPlatinum",Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total")));

			}
			else{
				ValuesToCalculateSilverCoin.put("NetWeightUnut",Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_NetWeight")));
			}
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		int SilverCoinForGold = (int)(ValuesToCalculateSilverCoin.get("TotalAmountGold")/ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_RequiredAmountMultiple);
		int SilverCoinForDiamond = (int)(ValuesToCalculateSilverCoin.get("NetWeightDiamond")/ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_DiamondRequiredQtyMultiple);
		int SilverCoinForPlatinum = (int)(ValuesToCalculateSilverCoin.get("TotalAmountPlatinum")/ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_RequiredAmountMultiple);
		Double uncutWeight = ValuesToCalculateSilverCoin.get("NetWeightUnut");
		int SilverCoinForUncut = 0;
		if (uncutWeight != null) {
		    SilverCoinForUncut = (int)(uncutWeight /
		        ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_UncutRequiredQtyMultiple);
		}
		//int SilverCoinForUncut = (int)(ValuesToCalculateSilverCoin.get("NetWeightUnut")/ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_UncutRequiredQtyMultiple);
		int TotalSilverCoinGrossWeight = SilverCoinForGold+SilverCoinForDiamond+SilverCoinForPlatinum+SilverCoinForUncut;

		//Step 5.Search CGC or silver coin
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_SilverCoin);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		//Step 6.Choose the required item(Silvercoin)
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "SILVERCOIN"));

		//Step 7.Click on add item after adding the purity
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_SilverCoinPurity);
		//Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));
		if (base.WaitForElementToBePresentWithTimeouts(LoginPageObj.Btn_SignInButton("OK"),5)) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8.Enter the weight (rate will be auto calculated )
		//Expected: Check calculation
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), Integer.toString(TotalSilverCoinGrossWeight));
		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		String MakingRate = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*TotalSilverCoinGrossWeight ;
		String ActualMakingValue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		asrt.assertEquals(Double.parseDouble(ActualMakingValue), ExpectedMakingValue,1.0, "Making value mismatch: Expected is "+ExpectedMakingValue+" but got "+ActualMakingValue+" in item details page");

		String RgRate = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue = TotalSilverCoinGrossWeight*Double.parseDouble(RgRate);
		String ActualCvalue = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));
		asrt.assertEquals(Double.parseDouble(ActualCvalue), ExpectedCvalue,1.0, "Making value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");

		String ActualTotalSilverCoinAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		double ExpectedTotalSilverCoinAmount = ExpectedCvalue+ExpectedMakingValue;
		asrt.assertEquals(Double.parseDouble(ActualTotalSilverCoinAmount), ExpectedTotalSilverCoinAmount,1.0, "Making value mismatch: Expected is "+ExpectedTotalSilverCoinAmount+" but got "+ActualTotalSilverCoinAmount+" in item details page");

		ProductNames.add(ItemName);
		ProductQtyList.add(Integer.toString(TotalSilverCoinGrossWeight));
		ProductTotalList.add(ActualTotalSilverCoinAmount);

		//Step 9.Click on add to cart
		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(1000); // Short delay for modal to close
			}
		} catch (Exception e) {
		}
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLineInBillingPge.get("Subtotal");
		String Tax 					= TransactionDataLineInBillingPge.get("Tax");
		String TotalAmount 			= TransactionDataLineInBillingPge.get("TotalAmount");
		String TotalGrossWeight     = TransactionDataLineInBillingPge.get("GrossWeight");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10.Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 11.Click on apply silver gift coin
		//Expected: Verify whether coin value deducts from sale amount
		base.ClickCondition(LoginPageObj.Edt_AlertText("Apply Silver Gift Coin"));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_ComplementSilverCoin)));
		double ExpectedSaleAmount = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim());
		Thread.sleep(2000);
		String ActualSaleAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSaleAmount), ExpectedSaleAmount,1.0,"Sale amount mismatch: Expected is "+ExpectedSaleAmount+" but got "+ActualSaleAmount+" in transaction page");

		ProductNames.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_ComplementSilverCoin);
		ProductQtyList.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_ComplementSilverCoinQty);
		ProductTotalList.add(Double.toString(-Double.parseDouble(ActualTotalSilverCoinAmount)));
		Thread.sleep(1000);

		//Step 12. Click on Save to estimation button
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		//Step 13. save estimate
		//Expected: Verify the Invoice details
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC312_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String InvoiceTotal=TotalAmount;
		String TaxableValue = SubTotal;
		String NetTotal = Double.toString(Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment = ActualTotalSilverCoinAmount;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal,
				"");

		//Step 14.Recall estimate from cashier screen
		//Step 15. Select estimate and click on save to estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		Thread.sleep(1000);
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 16.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),"Sales");

		//Step 17.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		WebDriverWait AlertWaitRecall = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			if(AlertWaitRecall.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(1000); // Short delay for modal to close
			}
		} catch (Exception e) {
		}

		//Step 18.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String NetWeight = TransactionDataLineInCashierScreen.get("NetWeight"); 
		double TotalNetWeight = Double.parseDouble(NetWeight)+TotalSilverCoinGrossWeight;
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		//Step 19.Click on the Amount
		//Step 20.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 21.Post the Invoice
		//Expected: save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC312_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalDiscount = "";
		String TotalQtyPcs = Integer.toString(SkuList.size());
		String NetValue = PaymentAmount;
		String InvoiceNo = pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				TotalDiscount,
				TotalQtyPcs,
				Double.toString(TotalNetWeight),
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				TotalAdjustment,
				SkuList,
				ScannedSkuDataMap
				);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_CustomerNo, InvoiceNo, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_RequiredReturnProduct);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}

	// <summary>
	// Test Case Title : Generate Platinuminvoice and apply normal silver gift coin
	// Automation ID : TC275
	// Author:Christy Reji
	// </summary>
	public void TC275_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		//Step 1.Login to POS
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer and click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4.Scan a Platinium SKU and click on Add to cart button
		//Expected: Check calculation
		List<String> SkuListPlatinum = appUtils.SelectMultipleSKUs(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_SkuCount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_FromCounterPlatinum,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_MetalTypePlatinum);
		appUtils.HamBurgerButtonClick("iconShop");

		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListPlatinum);

		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		Map<String, Double> ValuesToCalculateSilverCoin = new LinkedHashMap<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			String ProductName      = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			String ExactProductName = ProductName.substring(ProductName.indexOf("-") + 1).trim();
			if(ExactProductName.toUpperCase().startsWith("P")) {
				ValuesToCalculateSilverCoin.put("TotalAmountPlatinum",Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total")));
			}
			else{
				ValuesToCalculateSilverCoin.put("NetWeightUnut",Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_NetWeight")));
			}
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			//calculation
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		//Billing screen validation-sale
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalWithoutTax                  = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);
		int SilverCoinForPlatinum      = (int)(ValuesToCalculateSilverCoin.get("TotalAmountPlatinum")/ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_RequiredAmountMultiple);
		int TotalSilverCoinGrossWeight = SilverCoinForPlatinum;

		//Step 5.Search CGC or silver coin
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_SilverCoin);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		//Step 6.Choose the required item(Silvercoin)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "SILVERCOIN"));

		//Step 7.Click on add item after adding the purity
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_SilverCoinPurity);
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8.Enter the weight (rate will be auto calculated )
		//Expected: Check calculation
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), Integer.toString(TotalSilverCoinGrossWeight));
		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		String MakingRate          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*TotalSilverCoinGrossWeight ;
		String ActualMakingValue   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		asrt.assertEquals(Double.parseDouble(ActualMakingValue), ExpectedMakingValue,1.0, "Making value mismatch: Expected is "+ExpectedMakingValue+" but got "+ActualMakingValue+" in item details page");

		String RgRate              = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue      = TotalSilverCoinGrossWeight*Double.parseDouble(RgRate);
		String ActualCvalue        = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));
		asrt.assertEquals(Double.parseDouble(ActualCvalue), ExpectedCvalue,1.0, "Making value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");

		String ActualTotalSilverCoinAmount   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		double ExpectedTotalSilverCoinAmount = ExpectedCvalue+ExpectedMakingValue;
		asrt.assertEquals(Double.parseDouble(ActualTotalSilverCoinAmount), ExpectedTotalSilverCoinAmount,1.0, "Making value mismatch: Expected is "+ExpectedTotalSilverCoinAmount+" but got "+ActualTotalSilverCoinAmount+" in item details page");

		ProductNames.add(ItemName);
		ProductQtyList.add(Integer.toString(TotalSilverCoinGrossWeight));
		ProductTotalList.add(ActualTotalSilverCoinAmount);

		//Step 9.Click on add to cart
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(1000); // Short delay for modal to close
			}
		} catch (Exception e) {}

		//Check calculation Note:Billing Screen  *No of Product lines  *Displayed Item Name  *Displayed Quantity *Displayed TOTAL (without Tax)
		//*Displayed Subtotal	 *TAX*Total Amount
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		                        = TransactionDataLineInBillingPge.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Tax 					                        = TransactionDataLineInBillingPge.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount 			                        = TransactionDataLineInBillingPge.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String TotalGrossWeight                             = TransactionDataLineInBillingPge.get("GrossWeight");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10.Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 11.Click on apply silver gift coin
		//Expected: Verify whether coin value deducts from sale amount
		base.ClickCondition(LoginPageObj.Edt_AlertText("Apply Silver Gift Coin"));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_ComplementSilverCoin)));
		double ExpectedSaleAmount = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim());
		Thread.sleep(2000);
		String ActualSaleAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSaleAmount), ExpectedSaleAmount,1.0,"Sale amount mismatch: Expected is "+ExpectedSaleAmount+" but got "+ActualSaleAmount+" in transaction page");

		ProductNames.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_ComplementSilverCoin);
		ProductQtyList.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_ComplementSilverCoinQty);
		ProductTotalList.add(Double.toString(-Double.parseDouble(ActualTotalSilverCoinAmount)));
		Thread.sleep(1000);

		//Step 12. Click on Save to estimation button
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		//Step 13. save estimate
		//Expected: Verify the Invoice details
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC275_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String InvoiceTotal        = TotalAmount;
		String TaxableValue        = SubTotal;
		String NetTotal            = Double.toString(Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment     = ActualTotalSilverCoinAmount;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal,
				"");

		//Step 14.Recall estimate from cashier screen
		//Step 15. Select estimate and click on save to estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		//Validation
		List<WebElement> ItemNamesFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		List<String> actualItemNames         = ItemNamesFromRecall.stream().map(e -> {String ActualItemRaw = e.getText();          
		return ActualItemRaw.contains("-")  ? ActualItemRaw.substring(ActualItemRaw.lastIndexOf("-") + 1).trim(): ActualItemRaw.trim();}) .collect(Collectors.toList());   	       

		//validation-Recall Page
		appUtils.RecallEstimateItemValidation(
				ProductNames,
				ProductQtyList,
				actualItemNames, 
				ProductQtyList,
				Double.parseDouble(TotalWithoutTax),
				Double.parseDouble(Tax),
				Double.parseDouble(ActualSaleAmount),
				ProductNames.size(), 
				"Platinium invoice and apply normal silver gift coin "
				);
		//Step 16.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),"Sales");

		//Step 17.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		appUtils.HandleSpecifyRateModalInRecall();

		//Step 18.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String NetWeight      = TransactionDataLineInCashierScreen.get("NetWeight"); 
		double TotalNetWeight = Double.parseDouble(NetWeight)+TotalSilverCoinGrossWeight;

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		//Step 19.Click on the Amount
		//Step 20.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 21.Post the Invoice
		//Expected: save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice   = pdfUtils.DownloadAndRenameLatestPDF("TC275_FinalSaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalDiscount  = "";
		String TotalQtyPcs    = Integer.toString(SkuList.size());
		String NetValue       = PaymentAmount;
		String InvoiceNo      = pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				TotalDiscount,
				TotalQtyPcs,
				Double.toString(TotalNetWeight),
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				TotalAdjustment,
				SkuList,
				ScannedSkuDataMap
				);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_CustomerNo, InvoiceNo, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_RequiredReturnProduct);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}


	// <summary>
	// Test Case Title : Generate Gold invoice and apply normal silver gift coin
	// Automation ID : TC273
	// Author: Neethu
	// </summary>
	public void TC273_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception{

		//Step1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step3. Select customer and Click on add to estimation button 
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4. Scan Gold SKU and click on Add to cart button
		//Expected: Check calculation
		//Fetching Board Rate 		
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		double CurrentGoldRate22k =appUtils.FetchCurrentBoardRate("22k");
		System.out.println("CurrentGoldRate22k :"+CurrentGoldRate22k);

		Thread.sleep(2000);
		appUtils.HamBurgerButtonClick("iconShop");
		double RequiredSaleQty = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_SaleAmount / CurrentGoldRate22k;

		SKUResult SkuListGold = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_FromCounterGold,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_MetalTypeGold,
				RequiredSaleQty,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273__SkuCount);

		List<String> SkuList = SkuListGold.PcsRateSKU;	
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}


		String SkuAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();  
		double SkuTotalAmount = Double.parseDouble(SkuAmount);
		double Multiples = SkuTotalAmount / ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_SaleAmount; //Find how many times 30,000 fits
		int FullMultiples = (int) Multiples;
		int SilverCoinQuantity = FullMultiples;
		System.out.println("SilverCoinQuantity: "+SilverCoinQuantity);

		//Step5. Search cgc or silver coin
		//Step6. Choose the required item
		//Step7. Click on add item after adding the purity
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_SilverGiftCOin);
		Thread.sleep(3000);
		base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_SilverCoin));	

		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_Purity);
		Thread.sleep(5000);

		Thread.sleep(2000);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));


		//Step8. Enter the weight (rate will be autocalculated)
		//Expected: Check calculation
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),Double.toString(SilverCoinQuantity));
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),"Enter");
		Thread.sleep(7000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		String MakingRate = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*SilverCoinQuantity ;
		System.out.println("ExpectedMakingValue :"+ExpectedMakingValue);
		String ActualMakingValue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		asrt.assertEquals(Double.parseDouble(ActualMakingValue), ExpectedMakingValue,1.0, "Making value mismatch: Expected is "+ExpectedMakingValue+" but got "+ActualMakingValue+" in item details page");

		String RgRate = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue = SilverCoinQuantity*Double.parseDouble(RgRate);
		String ActualCvalue = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));
		asrt.assertEquals(Double.parseDouble(ActualCvalue), ExpectedCvalue,1.0, "Making value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");

		Thread.sleep(2000);
		String ActualTotalSilverCoinAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		Thread.sleep(3000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		String MakingValue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		double ExpectedTotalSilverCoinAmount = ExpectedCvalue+Double.parseDouble(MakingValue);;
		asrt.assertEquals(Double.parseDouble(ActualTotalSilverCoinAmount), ExpectedTotalSilverCoinAmount,1.0, "Making value mismatch: Expected is "+ExpectedTotalSilverCoinAmount+" but got "+ActualTotalSilverCoinAmount+" in item details page");

		String CGCQty = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value");
		String CGCTotal = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value");
		String CGCItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		ProductNames.add(CGCItemName);
		ProductQtyList.add(CGCQty);
		ProductTotalList.add(CGCTotal);

		//Step9. Click on add to cart
		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));
		appUtils.HandleSpecifyRateModalInRecall();

		String SubTotalBeforeDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalAmount 			= TransactionDataLineInBillingPge.get("TotalAmount");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step10. Go to discounts
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		//Step11. Click on apply silver gift coin
		//Expected: Verify whether coin value deducts from sale amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Silver Gift Coin"));
		Thread.sleep(2000);
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC312_ComplementSilverCoin)));
		double ExpectedSaleAmount = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim());
		Thread.sleep(2000);
		System.out.println("ExpectedSaleAmount :"+ExpectedSaleAmount);
		String ActualSaleAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		System.out.println("ActualSaleAmount :"+ActualSaleAmount);

		asrt.assertEquals(Double.parseDouble(ActualSaleAmount), ExpectedSaleAmount,1.0,"Sale amount mismatch: Expected is "+ExpectedSaleAmount+" but got "+ActualSaleAmount+" in transaction page");

		String CGCDiscountItemName = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_CGCItemName ;
		String CGCDiscountQty = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_CGCQty;
		String CGCDiscountTotal = "-" + CGCTotal;
		ProductNames.add(CGCDiscountItemName);
		ProductQtyList.add(CGCDiscountQty);
		ProductTotalList.add(CGCDiscountTotal);

		//In billing screen, *No of Product lines *Displayed Item Name *Displayed Quantity *Discount value *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String Discount 			= TransactionDataLine.get("Discount");
		String TaxAmt 					= TransactionDataLine.get("Tax");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount,ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		Thread.sleep(5000);;
		String ActualSubTotalAfterDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();
		double ExpectedSubtotal = Double.parseDouble(SubTotalBeforeDiscount) + Double.parseDouble(ProductTotalList.get(2));
		asrt.assertEquals(Double.parseDouble(ActualSubTotalAfterDiscount),ExpectedSubtotal,1.00,"Subtotal after applying coin discount mismatch: Expected is "+ExpectedSubtotal+" but found "+ActualSubTotalAfterDiscount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemNme = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemNme.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();	
			SubTotalSum = SubTotalSum + Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = TaxAmt.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		String TaxableValue = Double.toString(Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1)));
		double ExpectedTax = (Double.parseDouble(ProductTotalList.get(0))+Double.parseDouble(ProductTotalList.get(1)))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge),1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble( base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");
		String TotalGrossWt = String.format("%.3f", Double.parseDouble(ProductQtyList.get(0)) + Double.parseDouble(ProductQtyList.get(1)));


		//Step12. Click on Save to estimation button
		//Step13. save esimate
		//Expected Result: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(5000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);

		//Expected Result: Verify the Invoice details
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC273_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;
		String Gst                 = Double.toString(ActualTax);
		String InvoiceTotal = Double.toString(Double.parseDouble(TaxableValue)+(ActualTax));
		String AdjustmentAmount = ProductTotalList.get(1);
		String NetTotalPdf 		   = Double.toString(Double.parseDouble(InvoiceTotal) - Double.parseDouble(AdjustmentAmount));
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				AdjustmentAmount,
				NetTotalPdf,
				"");

		//Step14. Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");

		//Step15. Select estimate and  click on save to estimate button
		//Expected Result: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		//Step16. Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_TransactionTypeSales);
		Thread.sleep(5000);

		//Step17. Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		appUtils.HandleSpecifyRateModalInRecall();

		//Step18. Choose a sales representative
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}


		//Expected Result:Note:Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX *Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TaxInCashierScreen = TransactionDataLineInCashierScreen.get("Tax"); 
		String ActualLinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is " + ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		double SubTotalSumInCashierScreen = 0.00;
		for (int i = 0; i < AllProductRowsInCashierScreen.size(); i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText(); String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got " + ActualItemNameInCashierScreen + " in cashier screen");
			asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in cashier screen");

			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", ""); String ExpectedQuantityInCashierScreen = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen); double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen, ExpectedQtyInCashierScreen, 1.000, "Quantity mismatch: Expected is " + ExpectedQtyInCashierScreen + " but got " + ActualQtyInCashierScreen + " in cashier screen");

			List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim(); String TotalWithoutTaxValueInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSumInCashierScreen += Double.parseDouble(TotalWithoutTaxValueInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen), Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), 1.0, "TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierScreen + " but got " + ActualTotalWithoutTaxInCashierScreen + " in cashier screen");
		}

		String ExpectedSubTotalInCashierScreen = String.format("%.2f", SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen), Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0, "Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen + " in cashier screen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim(); double ActualTaxValueInCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxValueInCashierScreen = (Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1))) * 0.03; String ExpectedTaxInCashierScreen = String.format("%.2f", ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen), 1.0, "Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen + " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen) + Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountValueInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountValueInCashierScreen, ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: " + ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountValueInCashierScreen + " in cashier screen");

		//Step19. Click on the Amount
		//Step20. Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step21. Post the Invoice
		//Expected Result: save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC273_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalNetWt = TotalGrossWt;
		String InvoiceNo      = pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				Discount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_TotalQtyPcs,
				TotalNetWt,
				TotalGrossWt,
				TaxableValue,
				NetTotalPdf,
				PaymentAmount,
				AdjustmentAmount,
				SkuList,
				ScannedSkuDataMap
				);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_CustomerNo, InvoiceNo, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_RequiredReturnProduct);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 	
	}


	// <summary>
	// Test Case Title : Apply promotion discount for Gold
	// Automation ID : TC316
	// Author: Sangeetha M S
	// </summary>
	public void TC316_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception
	{
		// Step 1. Login to POS
		Thread.sleep(2000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2. Click on Transaction button
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite To take a Valid promotion discount from erp
		//		Map<String, String> PromotionData = erpUtils.GetValidPromotionDiscount(UtilityTestData.Category);
		//		if (PromotionData == null) 
		//		{
		//			erpUtils.CreateValidPromotionDiscountAgreement(UtilityTestData.Category);
		//			PromotionData = erpUtils.GetValidPromotionDiscount(UtilityTestData.Category); 
		//		}
		//		String PromotionType = PromotionData.get("PromotionType");
		//		String MultiplyAmount = PromotionData.get("Amount");
		//		String Multiplier = PromotionData.get("Multiplier");
		//		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", ""); 

		Map<String, String> PromotionData = erpUtils.CreateDeleteValidPromotionDiscountAgreement(UtilityTestData.Category);
		String PromotionType = PromotionData.get("PromotionType");
		String MultiplyAmount = PromotionData.get("Amount");
		String Multiplier = PromotionData.get("Multiplier");
		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", "");

		//To take current goldrate 
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		double CurrentGoldRate22k =appUtils.FetchCurrentBoardRate(UtilityTestData.Purity22);
		double Weight=Double.parseDouble(Multiplier.replaceAll(",", ""))/CurrentGoldRate22k;

		//Step 3. Select customer and Click on add to estimation button
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC316_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4. Scan Gold SKU and click on Add to cart button
		//Expected : Check calculation 
		//Billing Screen *No of Product lines,*Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax)
		//               *Displayed Subtotal,*TAX,*Total Amount,*Discount
		Thread.sleep(1000);		
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC316_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC316_FromCounterGold,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC316_MetalTypeGold,
				Weight,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC316_SKUCount);
		List<String> RequiredGoldSku = SkuGoldSale.PcsRateSKU;
		System.out.println("Gold Selected SKUs: " + RequiredGoldSku);
		appUtils.HamBurgerButtonClick("iconShop");	

		String ItemNameInItemDetailsPge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		String RGRate="";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredGoldSku.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku = RequiredGoldSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			RGRate=ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//To calculate Promotion discount of Sku based on the Promotion agreement added
		Thread.sleep(1000);
		double Amount = Double.parseDouble(TotalWithoutTax);
		double PromotionAmount = 0.00;
		Thread.sleep(1000);
		double Multiple=Double.parseDouble(Multiplier.replace("₹", "").replace(",", "").trim());
		double Range=Double.parseDouble(MultiplyAmount);

		if(Amount >= Multiple)
		{
			int AmountMultiple = (int)((Amount - Multiple) / Multiple) + 1;  
			double MultiplierValue=AmountMultiple * Range;
			PromotionAmount = Double.parseDouble(RGRate) * MultiplierValue;
		}       
		System.out.println("PromotionAmount  "+PromotionAmount);

		//Transcation Page Verify
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		Map<String, String> GoldSkuTransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredGoldSku,ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(GoldSkuTransactionDataLine, ScannedSkuDataMap);

		// Step 5. Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(3000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		// Step 6.Click on apply promotion discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));

		Thread.sleep(3000);
		Map<String, String> TransactionDataLines = appUtils.TransactionSKUsLinesVerify(RequiredGoldSku, ScannedSkuDataMap);
		String SubTotal = TransactionDataLines.get("Subtotal");
		String TotalGrossWeight = TransactionDataLines.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLines.get("NetWeight");
		String Tax 		        = TransactionDataLines.get("Tax");
		String TotalAmount      = TransactionDataLines.get("TotalAmount");
		String NetTotal         = TransactionDataLines.get("NetTotal");
		String LinesCount       = TransactionDataLines.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String PromotionActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax);

		// Step 7. Click on Save to estimation button
		// Step 8. save estimate
		//Expected : Verify performa invoice
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		Thread.sleep(1000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC316_ProformaInvoice");
		Thread.sleep(1000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String TotalAdjustment ="";		
		String AdditionalDiscount = PromotionActualDiscount;
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		// Step 9.Recall estimate from cashier screen
		// Step 10. Select estimate and click on recall estimation
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		// Step 11.Select the Transaction Type as Sales 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC316_TransactionTypeSales);

		// Step 12.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 13.Choose a sales representative(Not available after data refresh)
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Expected :Cashier Screen after recall Estimate *No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal
		//                                               *Discount,*TAX,*Total Amount
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));

		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		String ActualItemNameInCashierScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsPge.trim();

		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in cashier screen");

		String ActualQuantityInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInCashierScreen = GrossWeight;

		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in cashier screen");

		String ActualPromotionDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double PromotionDiscount=Double.parseDouble(ActualPromotionDiscountInCashierScreen);

		asrt.assertEquals(PromotionDiscount, PromotionAmount,1.0,"Discount value mismatch: Expected is "+PromotionAmount+" but got "+PromotionDiscount+" in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		double ExpectedTotalWithoutTaxInCashierScreen =0.00;
		ExpectedTotalWithoutTaxInCashierScreen = ExpectedTotalWithoutTaxInBillingPage-PromotionAmount;

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),ExpectedTotalWithoutTaxInCashierScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;

		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)   +   Double.parseDouble(TaxInCashierScreen);
		ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		// Step 14.Click on the Amount
		// Step 15.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// Step 16.Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(2000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC316_SaleInvoice");
		Thread.sleep(1000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount=ActualPromotionDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationAfterPromotion(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				PromotionAmount,
				RequiredGoldSku,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}

	// <summary>
	// Test Case Title: Apply silver gift coin discount,gift coin discount and promotion discount
	// Automation ID: TC283
	// Author: Robin T. Abraham
	// </summary>
	public void TC283_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite To take a Promotion from ERP
		Map<String, String> PromotionData = erpUtils.GetValidPromotionDiscount(UtilityTestData.Category);
		String MultiplyAmount = PromotionData.get("Amount");
		String Multiplier = PromotionData.get("Multiplier");
		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", ""); 

		//Prerequisite
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");
		double RequiredSaleQty1 = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_SaleAmount1 / CurrentGoldRate22k;
		double RequiredSaleQty2 = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_SaleAmount2 / CurrentGoldRate22k;
		System.out.println("Required Sale Quantity"+RequiredSaleQty1);
		System.out.println("Required Sale Quantity"+RequiredSaleQty2);

		//Step 3: Select customer and click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4: Scan SKU and click on Add to cart button
		//Expected: Check calculation
		RequiredSKUResult SkuGoldSaleItem = appUtils.GetRequiredQuantitySKUInRange(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_TransferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_FromCounter,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_MetalType,
				RequiredSaleQty1,
				RequiredSaleQty2,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_SkuGoldCount);

		List<String> SkuList = SkuGoldSaleItem.RequiredQtySKUs;
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		String TotalWithoutTax        = "";
		String RGRate		          = "";
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			RGRate=ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		String SkuAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();  
		double SkuTotalAmount = Double.parseDouble(SkuAmount);
		double CoinQuantity = 0.0;
		if      (SkuTotalAmount >= 10000   && SkuTotalAmount <= 90000)   CoinQuantity = 0.10;//CGC
		else if (SkuTotalAmount >= 90001   && SkuTotalAmount <= 190000)  CoinQuantity = 0.20;
		else if (SkuTotalAmount >= 190001  && SkuTotalAmount <= 290000)  CoinQuantity = 0.30;
		else if (SkuTotalAmount >= 290001  && SkuTotalAmount <= 390000)  CoinQuantity = 0.40;
		double Multiples = SkuTotalAmount / 30000; //Find how many times 30,000 fits
		int FullMultiples = (int) Multiples;
		int SilverCoinQuantity = FullMultiples;
		System.out.println("SilverCoinQuantity: "+SilverCoinQuantity);

		Thread.sleep(3000);
		double Amount = Double.parseDouble(TotalWithoutTax);
		double PromotionAmount = 0.00;
		Thread.sleep(500);
		double Multiple=Double.parseDouble(Multiplier.replace("₹", "").replace(",", "").trim());
		double Range=Double.parseDouble(MultiplyAmount);

		if(Amount >= Multiple)
		{
			int AmountMultiple = (int)((Amount - Multiple) / Multiple) + 1;  
			double MultiplierValue=AmountMultiple * Range;
			PromotionAmount = (CurrentGoldRate22k) * MultiplierValue;
		}
		System.out.println("PromotionAmount  "+PromotionAmount);

		//Step 5: search silver coin
		//Step 6: select sliver coin
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_SilverCoin);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "SILVERCOIN"));

		//Step 7: Select configuration(999)
		//Step 8: Click on add to cart button
		Thread.sleep(5000);		
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_SilverCoinPurity);
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), Integer.toString(SilverCoinQuantity));
		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		String MakingRate = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*SilverCoinQuantity ;
		String ActualMakingValue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));

		String RgRate = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue = SilverCoinQuantity*Double.parseDouble(RgRate);
		String ActualCvalue = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));

		String ActualTotalSilverCoinAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		double ExpectedTotalSilverCoinAmount = ExpectedCvalue+ExpectedMakingValue;

		ProductNames.add(ItemName);
		ProductQtyList.add(Integer.toString(SilverCoinQuantity));
		ProductTotalList.add(ActualTotalSilverCoinAmount);
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(1000); // Short delay for modal to close
			}
		} catch (Exception e) {
		}

		//Step 9: Search CGC 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_GiftCoin);

		//Step 10:Choose the required item
		Thread.sleep(3000);
		base.ClickCondition(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
		Thread.sleep(3000);
		List<String> ProductNumberList = base.GetElementTexts(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_ProductList("dataListLine","CGC_")); 
		for (int i = 0; i < ProductNumberList.size(); i++) {
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Edt_Name("Search"), 10);
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),ProductNumberList.get(i));
			base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"), 10);
			base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			Thread.sleep(10000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",ProductNumberList.get(i)));

			Thread.sleep(5000);
			if (base.isPresent(LoginPageObj.Ele_ErrorMessage("messageText h5"))) 
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), 10);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				continue;
			}
			base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
			//Thread.sleep(7000);
			base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("OK"), 10);

			if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				continue;
			}
			break;
		}

		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),Double.toString(CoinQuantity));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),"Enter");
		Thread.sleep(3000);
		String CGCQty = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value");
		String CGCTotal = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value");
		String CGCItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		ProductNames.add(CGCItemName);
		ProductQtyList.add(CGCQty);
		ProductTotalList.add(CGCTotal);

		//Step 11:Click on add item after adding the purity
		//Step 12:Enter the weight (rate will be auto calculated )
		//Step 13:Click on add to cart
		//Note:Billling Screen
		//No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));
		if (base.isPresent(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price"))) {			
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
			Thread.sleep(500); 
		} else {
			Thread.sleep(500); 
		}

		Thread.sleep(3000);
		String SubTotalBeforeDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();

		//Step 14: Go to discounts
		//Step 15: Click on apply promotion discount
		//Expected: Verify discount value
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(2000);
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));
		Thread.sleep(3000);

		String ActualPromotionDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();
		String ExpectedPromotionDiscount = Double.toString(Double.parseDouble(SubTotalBeforeDiscount)-PromotionAmount);
		asrt.assertEquals( Double.parseDouble(ActualPromotionDiscount),Double.parseDouble(ExpectedPromotionDiscount), 1.0, "Promotion Discount Not applied: Expected is " + ExpectedPromotionDiscount + " but got " + ActualPromotionDiscount + " in billing page.");

		System.out.println("ActualPromotionDiscount: " +ActualPromotionDiscount);

		//Step 16:Click on apply silver gift coin discount 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Silver Gift Coin"));
		Thread.sleep(3000);
		String SilverCoinDiscountItemName = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_ComplementSilverCoin ;
		String SilverCoinDiscountQty      = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC283_ComplementSilverCoinQty;
		String SilverCoinDiscountTotal    = "-" + ProductTotalList.get(1);
		ProductNames.add(SilverCoinDiscountItemName);
		ProductQtyList.add(SilverCoinDiscountQty);
		ProductTotalList.add(SilverCoinDiscountTotal);
		ProductTotalList.remove(0);
		double SkuTotalAfterDisount = Double.parseDouble(TotalWithoutTax)-PromotionAmount;
		ProductTotalList.add(0, Double.toString(SkuTotalAfterDisount));
		System.out.println("ProductList: "+ProductTotalList);
		Thread.sleep(3000);
		String ActualSubTotalAfterSilverCoinDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();
		String ExpectedSubTotalAfterSilverCoinDiscount = Double.toString(Double.parseDouble(SubTotalBeforeDiscount)-(PromotionAmount + Double.parseDouble(ProductTotalList.get(1))));
		Thread.sleep(2000);

		asrt.assertEquals(Double.parseDouble(ActualSubTotalAfterSilverCoinDiscount), Double.parseDouble(ExpectedSubTotalAfterSilverCoinDiscount), 1.0, "Silver Discount Not applied: Expected is "+ExpectedSubTotalAfterSilverCoinDiscount+" but got "+ActualSubTotalAfterSilverCoinDiscount+" in billing page.");

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 17: Click on gift coin discount
		//Expected: Verify validation" Gift coin discount and promotion discount cannot be applied together
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Gift Coin Discount"));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Gift coin discount and promotion discount cannot be applied together")),"Gift coin discount and promotion discount can be applied together");

		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}

		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Cash "));
		appUtils.VoidTransaction();
	}
	
	// <summary>
	// Test Case Title : Apply promotion discount for Diamond
	// Automation ID : TC281
	// <Author : Vishnu Manoj K
	// </summary>
	public void TC281_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_CustomerNo,Utility_TestData.MenuBarOptions[5]);

		// Step 4: Scan Diamond SKU and click on Add to cart button
		// Expected: Check calculation Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount*Discount
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_SkuGoldCount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_TransferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_FromCounter,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_MetalType);
		System.out.println("Final Valid SKUs: " + SkuList);	   
		appUtils.HamBurgerButtonClick("iconShop");		
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		double DiamondValue=0.00;
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			DiamondValue=DiamondValue+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + (i+1) + "_CvalueDIAMOND"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		Map<String, String> TransactionDataBeforeDisocunt = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalBeforeDiscount = TransactionDataBeforeDisocunt.get("Subtotal");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataBeforeDisocunt, ScannedSkuDataMap);

		String TotalGoldWeight=ScannedSkuDataMap.get("SKU_1_TotalRGWeight").replace('\u00A0', ' ') .trim().replaceAll("[^0-9.\\-]", "");
		System.out.println("TotalGold "+TotalGoldWeight); 
		String ParentWindow = driver.getWindowHandle(); 

		//Prerequisite: Create Promotion Agreement
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// ---------------- Promotion code ----------------
		By PromotionCodeFields = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Promotion code", 1);
		base.buttonClick(PromotionCodeFields);
		Thread.sleep(1000);
		WebElement PromotionCodeFieldE2 = driver.findElement(PromotionCodeFields);
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		// Clear field
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldE2
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldE2, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_PromotionCodeFields
				);

		// Commit the value
		base.pressKey(PromotionCodeFields, "ENTER");
		Thread.sleep(1000);

		// ---------------- Category ----------------
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Category"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"),
				5,
				2
				);
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Category"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("ItemTypeCode"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_Category);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// ---------------- Store Number ------------
		Thread.sleep(6000);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input"),
				5,
				2
				);
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("StoreNumber"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj = new Actions(driver);
			ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}

		// ---------------- Active ------------------
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Active"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"),
				5,
				2
				);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("Active"),"Yes");
		Thread.sleep(3000);
		base.pressKey(LoginPageObj.Ele_ERPYes("Yes"), "ENTER");
		base.excuteJsClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));

		//Select Row
		Thread.sleep(10000);
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			base.buttonClick(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
			if(base.isExists(NormalSaleGoldAndSilverObj.Ele_CheckDelete("Delete","disabled")))
			{
				base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
				Thread.sleep(5000);
				//				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				//				base.actionClick(LoginPageObj.Edt_Alert("Yes"));
			}
			else
			{
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
			}
		}
		else
		{}
		base.newWindow(2);
		driver.get(UtilityTestData.ERPURL);

		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Click on New
		base.excuteJsClick(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(5000);

		//Click From Date
		LocalDate CurrentDate = LocalDate.now();
		String FormattedDate = CurrentDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		System.out.println(FormattedDate);  

		// ---------------- From Date ----------------
		By FromDateField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("From date", 1);
		base.buttonClick(FromDateField);
		Thread.sleep(1000);
		WebElement FromDateEl = driver.findElement(FromDateField);

		// Clear the field via JS
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						FromDateEl
				);

		// Set new formatted date value (e.g., M/d/yyyy)
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						FromDateEl, FormattedDate
				);

		// Commit entry
		base.pressKey(FromDateField, "ENTER");
		Thread.sleep(1000);

		// ---------------- To Date ----------------
		By ToDateField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("To date", 1);
		base.buttonClick(ToDateField);
		Thread.sleep(1000);
		WebElement ToDateEl = driver.findElement(ToDateField);

		// Clear existing value
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						ToDateEl
				);

		// Set new date
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						ToDateEl, FormattedDate
				);

		// Commit entry
		base.pressKey(ToDateField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Advance Applicability ----------------
		By AdvanceApplicabilityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Advance applicability", 1);
		base.buttonClick(AdvanceApplicabilityField);
		Thread.sleep(1000);
		WebElement AdvanceApplicabilityFieldEl = driver.findElement(AdvanceApplicabilityField);

		// Clear existing value
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						AdvanceApplicabilityFieldEl
				);

		// Set new date
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						AdvanceApplicabilityFieldEl, FormattedDate
				);

		// Commit entry
		base.pressKey(ToDateField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Promotion code ----------------
		By PromotionCodeField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Promotion code", 1);
		base.buttonClick(PromotionCodeField);
		Thread.sleep(1000);
		WebElement PromotionCodeFieldEl = driver.findElement(PromotionCodeField);

		// Clear field
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldEl
				);

		// Set Design Code value
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_PromotionCodeFields
				);

		// Commit the value
		base.pressKey(PromotionCodeField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Category ----------------
		By CategoryField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Category", 1);
		base.buttonClick(CategoryField);
		Thread.sleep(1000);
		WebElement CategoryEl = driver.findElement(CategoryField);

		// Clear field
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						CategoryEl
				);

		// Set Design Code value
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						CategoryEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_Category
				);

		// Commit the value
		base.pressKey(PromotionCodeField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar2 = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj2 = new Actions(driver);
			ActionsObj2.clickAndHold(Scrollbar2).moveByOffset(650, 0).release().perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}
		//		// ---------------- Store Number ------------
		//		Thread.sleep(6000);
		//		appUtils.ClickWithRetry(
		//				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"),
		//				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"),
		//				5,
		//				2
		//				);
		//		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("StoreNumber"), UtilityTestData.WarehouseId);
		//		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// ---------------- Store Number ----------------

		By StoreNumberField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Store number", 1);
		base.buttonClick(StoreNumberField);
		Thread.sleep(1000);
		WebElement StoreNumberEl = driver.findElement(StoreNumberField);

		// Clear existing text
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						StoreNumberEl
				);

		// Set WarehouseId
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						StoreNumberEl, UtilityTestData.WarehouseId
				);

		// Commit entry
		base.pressKey(StoreNumberField, "ENTER");
		base.pressKey(StoreNumberField, "ENTER");
		base.pressKey(StoreNumberField, "ENTER");
		Thread.sleep(5000);

		// ---------------- Multiple of net weight ----------------
		By MultipleOfNetWeightField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("net weight", 1);
		base.buttonClick(MultipleOfNetWeightField);
		Thread.sleep(1000);
		WebElement MultipleOfNetWeightEl = driver.findElement(MultipleOfNetWeightField);

		// Clear existing text
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						MultipleOfNetWeightEl
				);

		// Set value “0.01”
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						MultipleOfNetWeightEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_AgreementNetWeight
				);

		// Commit
		base.pressKey(MultipleOfNetWeightField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar2 = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj2 = new Actions(driver);
			ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}
		//ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
		Thread.sleep(15000);

		// ---------------- Quantity ----------------
		By QuantityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Quantity", 1);
		base.buttonClick(QuantityField);
		Thread.sleep(1000);
		WebElement QuantityEl = driver.findElement(QuantityField);

		// Clear field
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityEl
				);

		// Set value “1000”
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_AgreementQuantity
				);

		// Commit
		base.pressKey(QuantityField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar2 = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj2 = new Actions(driver);
			ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}
		//ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
		Thread.sleep(15000);

		//		// ---------------- Active ------------------
		//		appUtils.ClickWithRetry(
		//				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Active"),
		//				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"),
		//				5,
		//				2
		//				);
		//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		//		Thread.sleep(3000);
		//		base.pressKey(LoginPageObj.Ele_ERPYes("Yes"), "ENTER");
		//		base.excuteJsClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
		// ---------------- OverwriteAppend ----------------
		By OverwriteAppendField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Overwrite", 1);
		base.buttonClick(OverwriteAppendField);
		Thread.sleep(1000);
		WebElement OverwriteAppendEl = driver.findElement(OverwriteAppendField);

		// Clear field via JS
		Js.executeScript(
				"const el=arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						OverwriteAppendEl
				);

		// Set new value “Overwrite”
		Js.executeScript(
				"const el=arguments[0], val=arguments[1];" +
						"const setter=Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						OverwriteAppendEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_AgreementOverwrite
				);

		// Commit entry
		base.pressKey(OverwriteAppendField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Click on Activate ----------------
		base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
		System.out.println("Activated successfully");

		//----------------Go to Distribution Schedule ----------------
		base.newWindow(3);
		driver.get(UtilityTestData.ERPURL);

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Filter the job ""N-AGREEMENT"" and click on ""Run now""
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "N-AGREEMENT");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);

		WebDriverWait Wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement RunNowBtn = Wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Activated successfully");
		base.buttonClick(LoginPageObj.Edt_Alert("OK"));

		//---------------- Batch Job ---------------- 
		base.newWindow(4);
		driver.get(UtilityTestData.ERPURL);
		String ScheduledDateInBatchjobs="";
		boolean JobEnded = false;
		int maxAttempts = 8; 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Search My Batch Job
				//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

				// Sort by "Scheduled start date/time"
				Thread.sleep(2000);
				By SortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(SortColumn)).click();
				base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));
				Thread.sleep(6000);
				JobEnded = true;
				ScheduledDateInBatchjobs= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Scheduled", 1));
				break; // success, break the loop

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
		System.out.println("ScheduledDateInBatchjobs:"+ScheduledDateInBatchjobs );
		base.newWindow(5);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Download session");
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		// Sort by "Scheduled start date/time"
		Thread.sleep(40000);	
		By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Date applied");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
		base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));

		// Wait for "Applied" job
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Applied")));
		String ScheduledDateInDownloadSession= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Date", 1));
		String BatchJobDate = ScheduledDateInBatchjobs;
		String DownloadSessionDate = ScheduledDateInDownloadSession;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm:ss a", Locale.US);

		// Parse strings into LocalDateTime
		LocalDateTime BatchJobDateShedule = LocalDateTime.parse(BatchJobDate, formatter);
		LocalDateTime DownloadSessionDateShedule = LocalDateTime.parse(DownloadSessionDate, formatter);

		// Calculate difference in minutes
		long diffInMinutes = Duration.between(BatchJobDateShedule, DownloadSessionDateShedule).toMinutes();

		// Assertion with 3-minute tolerance
		Assert.assertTrue(Math.abs(diffInMinutes) <= 3,
				"Dates differ more than 3 minutes or Job not successfully ended " +
						"BatchJob: " + BatchJobDateShedule + ", DownloadSession: " + DownloadSessionDateShedule);

		driver.switchTo().window(ParentWindow);

		//Step 5: Go to discounts
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 6: Click a Apply Promotion Discount.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));
		Thread.sleep(5000);		
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String LinesCount = TransactionDataLine.get("LinesCount");
		String Discount = TransactionDataLine.get("Discounts").trim().replaceAll("[^0-9.]", "");
		System.out.println("DiscountInBIllingPage"+Discount);
		double MultipleofNetWtCalc=Double.parseDouble(TotalGoldWeight)/Double.parseDouble(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_AgreementNetWeight);
		MultipleofNetWtCalc = Math.floor(MultipleofNetWtCalc);   
		double QtyWithMultofNetWtCalc= MultipleofNetWtCalc * Double.parseDouble(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_AgreementQuantity);
		double CurrentRate22k = appUtils.FetchCurrentBoardRate("22k");
		appUtils.HamBurgerButtonClick("iconShop");
		double CalcDiscount=CurrentRate22k * QtyWithMultofNetWtCalc;
		System.out.println("CalcDiscount"+CalcDiscount);

		asrt.assertEquals(Double.parseDouble(Discount),CalcDiscount,1, "Mismatch in Promotion Discount, Expected Discount "+CalcDiscount+"but Found "+Discount+" in Billing Page");

		// Step 7: Save Esimate
		// Step 8: Click on Save to estimation button
		//Expected Result: Verify performa invoice
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_BillingBack("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC281_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String AdditionalDiscount = Discount;
		String TotalAdjustment ="";	
		String TotalWithoutTax=SubTotalBeforeDiscount;
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);


		// Step 10: Recall estimate from cashier screen
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		// Step 11: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_TransationType);

		// Step 12: Click on Process Estimation
		// Step 13: Select any Sales Representative [Due to new Fix in the application this step is Removed] 
		// 13..Cashier Screen after recall Estimate *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal *Discount*TAX*Total Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Map<String, String> TransactionDataLineCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		for (String Key : TransactionDataLine.keySet()) {
			String ExpectedValue = TransactionDataLine.get(Key);        // Billing Page value
			String ActualValue   = TransactionDataLineCashierScreen.get(Key);  // Cashier Screen value
			if (ExpectedValue == null && ActualValue == null) { System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' – both values are null");
			} else if (ExpectedValue != null && ExpectedValue.equals(ActualValue)) {
				System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' " +"Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			} else {
				asrt.assertEquals(ActualValue,ExpectedValue,"Billing Page vs Cashier Screen: Mismatch in '" + Key + "' " + "Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			}
		}
		appUtils.HandleSpecifyRateModalInRecall();

		/*
		// Step 14:  Click on the Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// Step 15:  Select a Payment method(Cash or Card)
		base.buttonClick(LoginPageObj.Edt_AlertText("HDFC (Credit Card)"));
		String PaymentHDFCCard = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_CartMoney("flexGrow50 flexRow row-enter","h1 marginBottom0 iconReturnKey button-content-return"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_ApprovalCode);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_ApprovalCodeAgain);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		//String PaymentHDFCCard = appUtils.PaymentAfterRecallEstimate(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_PaymentMethod);
		 */
		String PaymentHDFCCard = appUtils.PaymentAfterRecallEstimate(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC281_PaymentMethod);

		// Step 16:  Post the Invoice
		// Expected Result: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC281_TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount = Discount;
		String TotalQtyPcs = Integer.toString(SkuList.size());
		String NetValue =NetTotal ;
		String TotalDiamond= Double.toString(DiamondValue);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationPromotionDiscount(InvoicePdfPath, GrossAmount,
				TotalDiscount, TotalDiamond, TotalQtyPcs, TotalNetWeight, TotalGrossWeight, ExpectedTaxableValue, NetValue,
				PaymentHDFCCard,SkuList, ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Create promotion discount agreement for "multiple of Net wt"
	// Automation ID : TC280
	// <Author : Vishnu Manoj K
	// </summary>
	public void TC280_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		//Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);

		// PreRequisite : Disable the Current Agreements if any exist
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// ---------------- Promotion code ----------------
		By PromotionCodeFields = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Promotion code", 1);
		base.buttonClick(PromotionCodeFields);
		Thread.sleep(1000);
		WebElement PromotionCodeFieldE2 = driver.findElement(PromotionCodeFields);
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		// Clear field
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldE2
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldE2, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC280_PromotionCodeFields
				);

		// Commit the value
		base.pressKey(PromotionCodeFields, "ENTER");
		Thread.sleep(1000);

		// ---------------- Category ----------------
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Category"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"),
				5,
				2
				);
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Category"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("ItemTypeCode"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC280_Category);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// ---------------- Store Number ------------
		Thread.sleep(6000);
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input"),
				5,
				2
				);
		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("StoreNumber"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj = new Actions(driver);
			ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}

		// ---------------- Active ------------------
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Active"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"),
				5,
				2
				);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		Thread.sleep(3000);
		base.pressKey(LoginPageObj.Ele_ERPYes("Yes"), "ENTER");
		base.excuteJsClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
		if(base.isExists(LoginPageObj.Edt_Alert("Yes")))
			base.buttonClick(LoginPageObj.Edt_Alert("Yes"));
		//Select Row
		else
			Thread.sleep(6000);
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			base.buttonClick(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));

			if(base.isExists(NormalSaleGoldAndSilverObj.Ele_CheckDelete("Delete","disabled")))
			{
				base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
				Thread.sleep(5000);
				//				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				//				base.actionClick(LoginPageObj.Edt_Alert("Yes"));
			}
			else
			{
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
			}
		}

		base.newWindow(2);
		driver.get(UtilityTestData.ERPURL);

		// Step 1: Go to promotion agreement valuewise
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// Step 2: Click on New
		base.excuteJsClick(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(5000);

		//Step 3: Enter the following
		//Step 3.1: From date
		LocalDate CurrentDate = LocalDate.now();
		String FormattedDate = CurrentDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		System.out.println(FormattedDate);  
		By FromDateField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("From date", 1);
		base.buttonClick(FromDateField);
		Thread.sleep(1000);
		WebElement FromDateEl = driver.findElement(FromDateField);

		// Clear the field via JS
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						FromDateEl
				);

		// Set new formatted date value (e.g., M/d/yyyy)
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						FromDateEl, FormattedDate
				);
		// Commit entry
		base.pressKey(FromDateField, "ENTER");
		Thread.sleep(1000);

		//Step 3.2: To date
		By ToDateField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("To date", 1);
		base.buttonClick(ToDateField);
		Thread.sleep(1000);
		WebElement ToDateEl = driver.findElement(ToDateField);
		// Clear existing value
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						ToDateEl
				);
		// Set new date
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						ToDateEl, FormattedDate
				);
		base.pressKey(ToDateField, "ENTER");
		Thread.sleep(1000);

		//Step 3.3:  Advance Applicability 
		By AdvanceApplicabilityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Advance applicability", 1);
		base.buttonClick(AdvanceApplicabilityField);
		Thread.sleep(1000);
		WebElement AdvanceApplicabilityFieldEl = driver.findElement(AdvanceApplicabilityField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						AdvanceApplicabilityFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						AdvanceApplicabilityFieldEl, FormattedDate
				);
		base.pressKey(AdvanceApplicabilityField, "ENTER");
		Thread.sleep(1000);

		//Step 3.4: Promotion code 
		By PromotionCodeField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Promotion code", 1);
		base.ClickCondition(PromotionCodeField);
		Thread.sleep(1000);
		WebElement PromotionCodeFieldEl = driver.findElement(PromotionCodeField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						PromotionCodeFieldEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC280_PromotionCodeFields
				);
		base.pressKey(PromotionCodeField, "ENTER");
		Thread.sleep(1000);

		//Step 3.5: Category-Diamond
		By CategoryField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Category", 1);
		base.buttonClick(CategoryField);
		Thread.sleep(1000);
		WebElement CategoryEl = driver.findElement(CategoryField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						CategoryEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						CategoryEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC280_Category
				);
		base.pressKey(CategoryField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar2 = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj2 = new Actions(driver);
			ActionsObj2.clickAndHold(Scrollbar2).moveByOffset(650, 0).release().perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}
		//Step 3.6: Store Number 
		By StoreNumberField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Store number", 1);
		base.buttonClick(StoreNumberField);
		Thread.sleep(1000);
		WebElement StoreNumberEl = driver.findElement(StoreNumberField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						StoreNumberEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						StoreNumberEl, UtilityTestData.WarehouseId
				);
		base.pressKey(StoreNumberField, "ENTER");
		base.pressKey(StoreNumberField, "ENTER");
		base.pressKey(StoreNumberField, "ENTER");
		Thread.sleep(5000);

		//Step 3.7: Multiple of Net wt.
		By MultipleOfNetWeightField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("net weight", 1);
		base.buttonClick(MultipleOfNetWeightField);
		Thread.sleep(1000);
		WebElement MultipleOfNetWeightEl = driver.findElement(MultipleOfNetWeightField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						MultipleOfNetWeightEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						MultipleOfNetWeightEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC280_AgreementNetWeight
				);
		base.pressKey(MultipleOfNetWeightField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar2 = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj2 = new Actions(driver);
			ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}
		//ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
		Thread.sleep(15000);

		//Step 3.8: Quantity
		By QuantityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Quantity", 1);
		base.buttonClick(QuantityField);
		Thread.sleep(1000);
		WebElement QuantityEl = driver.findElement(QuantityField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC280_AgreementQuantity
				);
		base.pressKey(QuantityField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Scroll horizontally-------------
		try {
			WebElement Scrollbar2 = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions ActionsObj2 = new Actions(driver);
			ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
			Thread.sleep(15000);
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}

		Thread.sleep(15000);
		// ---------------- OverwriteAppend ----------------
		By OverwriteAppendField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Overwrite", 1);
		base.buttonClick(OverwriteAppendField);
		Thread.sleep(1000);
		WebElement OverwriteAppendEl = driver.findElement(OverwriteAppendField);
		Js.executeScript(
				"const el=arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						OverwriteAppendEl
				);
		Js.executeScript(
				"const el=arguments[0], val=arguments[1];" +
						"const setter=Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						OverwriteAppendEl, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC280_AgreementOverwrite
				);
		base.pressKey(OverwriteAppendField, "ENTER");
		Thread.sleep(1000);

		//Step 3.9: Click on activate/deactivate and save
		//Expected Result:  Verify if a tick mark appears against the newly added line.
		base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
		// ---------------- Active ------------------
		appUtils.ClickWithRetry(
				NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Active"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"),
				5,
				2
				);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		Thread.sleep(3000);
		base.pressKey(LoginPageObj.Ele_ERPYes("Yes"), "ENTER");
		base.excuteJsClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_CheckActive("true")), "Tick mark not appeared against the newly added line");

		//Step 4: Go to Distribution Schedule 
		base.newWindow(3);
		driver.get(UtilityTestData.ERPURL);
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Step 5: Search n-agreement
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "N-AGREEMENT");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);

		//Step 6: Click on run now
		WebDriverWait Wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement RunNowBtn = Wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Activated successfully");

		//Step 7: Click on ok
		base.buttonClick(LoginPageObj.Edt_Alert("OK"));

		//Step 8: Go to Batch Job-->check the job status changed has been changed to ended
		base.newWindow(4);
		driver.get(UtilityTestData.ERPURL);
		String ScheduledDateInBatchjobs="";
		boolean JobEnded = false;
		int maxAttempts = 8; 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Step 26: Search My Batch Job
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

				// Step 27: Sort by "Scheduled start date/time"
				Thread.sleep(2000);
				By SortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(SortColumn)).click();
				base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Step 28: Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				Thread.sleep(6000);
				JobEnded = true;
				ScheduledDateInBatchjobs= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Scheduled", 1));
				break; // success, break the loop

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
		System.out.println("ScheduledDateInBatchjobs:"+ScheduledDateInBatchjobs );

		//Step 9: Go to download session-->Check if the status has been changed to applied
		base.newWindow(5);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Download session");
		Thread.sleep(1000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Sort by "Scheduled start date/time"
		Thread.sleep(40000);	
		By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Date applied");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
		base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));
		//Wait for "Applied" job
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Applied")));
		String ScheduledDateInDownloadSession= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Date", 1));

		String BatchJobDate = ScheduledDateInBatchjobs;
		String DownloadSessionDate = ScheduledDateInDownloadSession;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm:ss a", Locale.US);
		// Parse strings into LocalDateTime
		LocalDateTime BatchJobDateShedule = LocalDateTime.parse(BatchJobDate, formatter);
		LocalDateTime DownloadSessionDateShedule = LocalDateTime.parse(DownloadSessionDate, formatter);
		// Calculate difference in minutes
		long diffInMinutes = Duration.between(BatchJobDateShedule, DownloadSessionDateShedule).toMinutes();

		// Assertion with 3-minute tolerance
		asrt.assertTrue(Math.abs(diffInMinutes) <= 3,
				"Dates differ more than 3 minutes or Job not successfully ended " +
						"BatchJob: " + BatchJobDateShedule + ", DownloadSession: " + DownloadSessionDateShedule);
	}


	// <summary>
	// Test Case Title : Generate Diamond invoice and apply normal silver gift coin
	// Automation ID : TC274
	// Author:Nandagopan
	// </summary>
	public void TC274_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		//Step 1.Login to POS
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer and click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4.Scan a Diamond SKU and click on Add to cart button
		//Expected: Check calculation
		SKUResult SkuDiamondSaleItem = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_FromCounterDiamond,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_MetalTypeGold,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_DiamondRequiredQtyMultiple,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_SkuCount);
		List<String> SkuListDiamond = SkuDiamondSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListDiamond);
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double NetWeightDiamond =0.00;

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			String ProductName      = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			String ExactProductName = ProductName.substring(ProductName.indexOf("-") + 1).trim();								
			NetWeightDiamond=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_NetWeight"));			
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);				
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);			
		}			
		//Billing screen validation-sale
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalWithoutTax                  = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);
		int SilverCoinForDiamond = (int)NetWeightDiamond / ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_DiamondRequiredQtyMultiple;
		int TotalSilverCoinGrossWeight = SilverCoinForDiamond;

		//Step 5.Search CGC or silver coin
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_SilverCoin);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		//Step 6.Choose the required item(Silvercoin)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "SILVERCOIN"));

		//Step 7.Click on add item after adding the purity
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_SilverCoinPurity);
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8.Enter the weight (rate will be auto calculated )
		//Expected: Check calculation
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), Integer.toString(TotalSilverCoinGrossWeight));
		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		String MakingRate          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*TotalSilverCoinGrossWeight ;
		String ActualMakingValue   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		asrt.assertEquals(Double.parseDouble(ActualMakingValue), ExpectedMakingValue,1.0, "Making value mismatch: Expected is "+ExpectedMakingValue+" but got "+ActualMakingValue+" in item details page");

		String RgRate              = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue      = TotalSilverCoinGrossWeight*Double.parseDouble(RgRate);
		String ActualCvalue        = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));
		asrt.assertEquals(Double.parseDouble(ActualCvalue), ExpectedCvalue,1.0, "Making value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");

		String ActualTotalSilverCoinAmount   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		double ExpectedTotalSilverCoinAmount = ExpectedCvalue+ExpectedMakingValue;
		asrt.assertEquals(Double.parseDouble(ActualTotalSilverCoinAmount), ExpectedTotalSilverCoinAmount,1.0, "Making value mismatch: Expected is "+ExpectedTotalSilverCoinAmount+" but got "+ActualTotalSilverCoinAmount+" in item details page");

		ProductNames.add(ItemName);
		ProductQtyList.add(Integer.toString(TotalSilverCoinGrossWeight));
		ProductTotalList.add(ActualTotalSilverCoinAmount);

		//Step 9.Click on add to cart
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(1000); // Short delay for modal to close
			}
		} catch (Exception e) {}

		//Check calculation Note:Billing Screen  *No of Product lines  *Displayed Item Name  *Displayed Quantity *Displayed TOTAL (without Tax)
		//*Displayed Subtotal *TAX*Total Amount
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		                        = TransactionDataLineInBillingPge.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Tax 					                        = TransactionDataLineInBillingPge.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount 			                        = TransactionDataLineInBillingPge.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String TotalGrossWeight                             = TransactionDataLineInBillingPge.get("GrossWeight");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing Screen");

		//Step 10.Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 11.Click on apply silver gift coin
		//Expected: Verify whether coin value deducts from sale amount
		base.ClickCondition(LoginPageObj.Edt_AlertText("Apply Silver Gift Coin"));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_ComplementSilverCoin)));
		double ExpectedSaleAmount = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim());
		Thread.sleep(2000);
		String ActualSaleAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSaleAmount), ExpectedSaleAmount,1.0,"Sale amount mismatch: Expected is "+ExpectedSaleAmount+" but got "+ActualSaleAmount+" in transaction page");

		ProductNames.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_ComplementSilverCoin);
		ProductQtyList.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_ComplementSilverCoinQty);
		ProductTotalList.add(Double.toString(-Double.parseDouble(ActualTotalSilverCoinAmount)));
		Thread.sleep(1000);

		//Step 12. Click on Save to estimation button
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		//Step 13. save estimate
		//Expected: Verify the Invoice details
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC274_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String InvoiceTotal        = TotalAmount;
		String TaxableValue        = SubTotal;
		String NetTotal            = Double.toString(Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment     = ActualTotalSilverCoinAmount;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal,
				"");

		//Step 14.Recall estimate from cashier screen
		//Step 15. Select estimate and click on save to estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		//Validation
		List<WebElement> ItemNamesFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		List<String> actualItemNames         = ItemNamesFromRecall.stream().map(e -> {String ActualItemRaw = e.getText();          
		return ActualItemRaw.contains("-")  ? ActualItemRaw.substring(ActualItemRaw.lastIndexOf("-") + 1).trim(): ActualItemRaw.trim();}) .collect(Collectors.toList());   	       
		//validation-Recall Page
		appUtils.RecallEstimateItemValidation(
				ProductNames,
				ProductQtyList,
				actualItemNames, 
				ProductQtyList,
				Double.parseDouble(TotalWithoutTax),
				Double.parseDouble(Tax),
				Double.parseDouble(ActualSaleAmount),
				ProductNames.size(), 
				"Diamond invoice and apply normal silver gift coin "
				);

		//Step 16.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),"Sales");

		//Step 17.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		appUtils.HandleSpecifyRateModalInRecall();

		//Step 18.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String NetWeight      = TransactionDataLineInCashierScreen.get("NetWeight"); 
		double TotalNetWeight = Double.parseDouble(NetWeight)+TotalSilverCoinGrossWeight;

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier Screen");

		//Step 19.Click on the Amount
		//Step 20.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 21.Post the Invoice
		//Expected: save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice   = pdfUtils.DownloadAndRenameLatestPDF("TC274_FinalSaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalDiscount  = "";
		String TotalQtyPcs    = Integer.toString(SkuList.size());
		String NetValue       = PaymentAmount;
		String InvoiceNo      = pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				TotalDiscount,
				TotalQtyPcs,
				Double.toString(TotalNetWeight),
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				TotalAdjustment,
				SkuList,
				ScannedSkuDataMap
				);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_CustomerNo, InvoiceNo, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC274_RequiredReturnProduct);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 

	}	

	// <summary>
	// Test Case Title : Apply other discount on an invoice having promotion discount
	// Automation ID : TC284
	// Author: Aiswarya
	// </summary>
	public void TC284_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		//Step1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite To take Valid promotion discount agreement
		/*
		String OriginalWindow = driver.getWindowHandle();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);

		// Search Promotion discount 
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		// Sort by From date (newest to oldest)
		base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("dyn-headerCell", "From date"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));
		Thread.sleep(5000);

		//Apply Gold
		Thread.sleep(2000);
		base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("dyn-headerCell", "Category"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"), UtilityTestData.Category);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));


		// ---------------- Store Number ------------
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"), UtilityTestData.WarehouseId);
		Thread.sleep(3000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj= new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();
		Thread.sleep(15000);

		// Apply Active filter
		Thread.sleep(2000);
		base.buttonClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("dyn-headerCell", "Active"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active_Active_Input_0"), UtilityTestData.Status);
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Ele_ERPNo("Yes"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(-400, 0).release().perform();

		//Select Row
		Thread.sleep(6000);
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			base.buttonClick(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
			ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();

			if(base.isExists(NormalSaleGoldAndSilverObj.Ele_ERPInvoice("Overwrite"))) 
			{
				base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
				Thread.sleep(5000);
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable isFiltered", "Active"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active_Active_Input_0"),"No");
				Thread.sleep(1000);
				base.ClickCondition(LoginPageObj.Ele_ERPNo("No"));
				base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
				Thread.sleep(5000);
				ActionsObj.clickAndHold(Scrollbar).moveByOffset(-400, 0).release().perform();
				Thread.sleep(3000);
				base.buttonClick(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
				base.buttonClick(LoginPageObj.Edt_Alert("Edit"));
				ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();
				Thread.sleep(3000);
				base.setData(NormalSaleGoldAndSilverObj.Ele_ERPInvoice("Overwrite"),"Append");
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPInvoice("Overwrite"), "ENTER");
				Thread.sleep(5000);
				base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
				System.out.println("Activated successfully");
				//----------------Go to Distribution Schedule ----------------
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

				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
				WebElement runNowBtn = wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
				base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
				System.out.println("Run now successfully completed");

				//Click on ok
				base.buttonClick(LoginPageObj.Edt_Alert("OK"));
				//---------------- Batch Job ---------------- 
				boolean jobEnded = false;
				int maxAttempts = 8;
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
				for (int attempt = 1; attempt <= maxAttempts; attempt++)
				{
					try
					{
						//Search My Batch Job
						Thread.sleep(10000);
						base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
						Thread.sleep(3000);
						base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
						Thread.sleep(3000);
						base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");


						//Sort by "Scheduled start date/time"
						Thread.sleep(2000);
						By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
						Thread.sleep(1000);
						wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
						base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));

						// Wait for "Ended" job
						wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

						jobEnded = true;
						System.out.println("Try block");
						break; // success, break the loop

					}
					catch (StaleElementReferenceException stale)
					{
						System.out.println("Attempt " + attempt + ": Stale element. Retrying after refresh...");
						if (attempt < maxAttempts)
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
						System.out.println("Attempt " + attempt + ": Timeout while waiting for 'Ended' job status.");
						if (attempt < maxAttempts)
						{
							driver.navigate().refresh();
							Thread.sleep(3000);
						}
						else
						{
							throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
						}
					}
					System.out.println("Completed");
				}
				Thread.sleep(10000);
			}
			else
			{
				System.out.println("Active Promotion Agreement With Append already Exists");
			}
		}
		else {
			erpUtils.CreateValidPromotionDiscountAgreement("GLD");
		}

		driver.switchTo().window(OriginalWindow);


		Map<String, String> promotionData = erpUtils.GetValidPromotionDiscount("GLD");
		String PromotionType = promotionData.get("PromotionType");
		String MultiplyAmount = promotionData.get("Amount");
		String Multiplier = promotionData.get("Multiplier");
		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", ""); 
		 */
		Map<String, String> PromotionData = erpUtils.CreateDeleteValidPromotionDiscountAgreement("GLD");
		String PromotionType = PromotionData.get("PromotionType");
		String MultiplyAmount = PromotionData.get("Amount");
		String Multiplier = PromotionData.get("Multiplier");
		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", "");

		//Step3. Select customer
		//Step4. Click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step5. Scan SKU and click on Add to cart button
		//Expected Result: Check calculation
		List<String> SkuList = appUtils.CollectValidSkuByVerfiy( 
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_SkuGoldCount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_TransferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_FromCounter,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_MetalType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_AllSkuFetchNo, 
				Arrays.asList("WastageQty"));
		System.out.println("Final Valid SKUs: " + SkuList);	   
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		String ProductName = "";
		String ProductQty = "";
		String ProductTotal = "";
		String CvalueRG = "";
		String RGRate = "";
		String TotalGrossWeight ="";
		String TotalAmountWithoutTax= "";
		double ActualRgRateInItemDetailsPge =0.0;
		int SkuCounter = 1;	
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductName = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			ProductQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			ProductTotal = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueRG");
			RGRate=ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG");
			TotalGrossWeight = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight");
			TotalAmountWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}		

		//To calculate Promotion discount of Sku based on the Promotion agreement added
		Thread.sleep(500);
		double Amount = Double.parseDouble(ProductTotal);
		double PromotionAmount = 0.00;
		Thread.sleep(500);
		double Multiple=Double.parseDouble(Multiplier.replace("\u20B9", "").replace(",", "").trim());  
		double Range=Double.parseDouble(MultiplyAmount);

		if(Amount >= Multiple)
		{
			int AmountMultiple = (int)((Amount - Multiple) / Multiple) + 1;  
			double MultiplierValue= AmountMultiple * Range;
			PromotionAmount = Double.parseDouble(RGRate) * MultiplierValue;
		}       
		System.out.println("PromotionAmount  "+PromotionAmount);

		//Step 6: In billing, Go to discount
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 7: Select Normal discount
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));	

		//Step 7.1: Select making discount
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_DiscountCategory);

		//Step 7.2: Select making discount type as "Reduce making percentage"
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_MakingDiscountType);

		//Step 7.3: Discount type as "Percentage"
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrMakingDiscSubType"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_DiscountType);

		//Step 7.4: Add a discount value
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_DiscountValue);

		//Step 7.5: Select a category
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_Category);

		//Step 7.6: Click on Apply
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));
		double ExpectedMakingDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_DiscountValue))/100;
		ExpectedMakingDiscount = Math.round(ExpectedMakingDiscount * 100.0) / 100.0;

		//Step 8: Click on apply promotion discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));
		Thread.sleep(3000);
		double ExpectedDiscount = ExpectedMakingDiscount + PromotionAmount;  

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount"); 

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		String ActualItemNameInBillingPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInBillingPage = ProductName.substring(ProductName.indexOf("-") + 1).trim();
		asrt.assertTrue(ExpectedItemNameInBillingPage.contains(ActualItemNameInBillingPage),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPage+" but got "+ActualItemNameInBillingPage+" in billing page");

		String ActualQuantityInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedQuantityInBillingPage = ProductQty;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPage), Double.parseDouble(ExpectedQuantityInBillingPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPage + " but got " + ActualQuantityInBillingPage + " in billing page");

		String ActualTotalWithoutTaxInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedTotalWithoutTaxInBillingPage = String.format("%.2f", Double.parseDouble(ProductTotal.replaceAll("[^\\d.]", "").trim())-ExpectedDiscount);
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPage),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");

		String ExpectedSubTotalInBillingPage = ActualTotalWithoutTaxInBillingPage.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPage = SubTotal.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPage),Double.parseDouble(ExpectedSubTotalInBillingPage), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPage + " but found " + ActualSubTotalInBillingPage+ " in billing page");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		String TaxInBillingPage = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTaxInBillingPage = Double.parseDouble(TaxInBillingPage);
		double ExpectedTaxInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)*0.03;
		asrt.assertEquals(ActualTaxInBillingPage,ExpectedTaxInBillingPage, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPage + " but found " + ActualTaxInBillingPage+ " in billing page");

		double ExpectedTotalAmountInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)+ ActualTaxInBillingPage;
		String ActualTotalAmountInBillingPage = TotalAmount.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInBillingPage), ExpectedTotalAmountInBillingPage, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPage + " but found " + ActualTotalAmountInBillingPage + " in billing page");

		//Step 9: Click on "save to estimate"
		//Expected: Verify Performa invoice
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC284_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue = SubTotal; 
		String InvoiceTotal= TotalAmount;
		String AdditionalDiscount = ActualDiscount;
		String TotalAdjustment ="";		
		String LinesCount = Integer.toString(SkuList.size());
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				TotalAmountWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		//Step 10: Click on recall estimate from cashier screen
		//Step 11: Select estimate and click on recall button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		String ItemNameFromRecallPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ActualItemNameInBillingPage.contains(ItemNameFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 12: Select transaction type as" sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC284_TransactionType);

		//Step 13: Click on "Process estimation " button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 14: Select any one of the SA		
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen              = TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen              = TransactionDataLineInCashierScreen.get("NetTotal");
		String TotalNetWeightInCashierScreen        = TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen                   = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen           = TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen      = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		String ActualLinesCountInBillingPageInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));

		asrt.assertEquals(ActualLinesCountInBillingPageInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected " + ExpectedLinesCountInCashierScreen +" but found " + ActualLinesCountInBillingPageInCashierScreen + " in CashierScreen");

		String ActualItemNameInCashierScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInCashierScreen = ProductName.substring(ProductName.indexOf("-") + 1).trim();

		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected " + ExpectedItemNameInCashierScreen +" but got " + ActualItemNameInCashierScreen + " in CashierScreen");

		String ActualQuantityInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedQuantityInCashierScreen = ProductQty;

		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen),Double.parseDouble(ExpectedQuantityInCashierScreen),1.000,"Quantity mismatch: Expected " + ExpectedQuantityInCashierScreen +" but got " + ActualQuantityInCashierScreen + " in CashierScreen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedTotalWithoutTaxInCashierScreen =String.format("%.2f", Double.parseDouble(ProductTotal.replaceAll("[^\\d.]", "").trim()) - ExpectedDiscount);

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected " + ExpectedTotalWithoutTaxInCashierScreen +" but got " + ActualTotalWithoutTaxInCashierScreen + " in CashierScreen");

		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = SubTotalInCashierScreen.replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: Expected " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen + " in CashierScreen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen),ExpectedDiscount,1.0,"Discount mismatch: Expected " + ExpectedDiscount + " but got " + ActualDiscountInCashierScreen + " in CashierScreen");

		String TaxValueInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ActualTaxValueInCashierScreen = Double.parseDouble(TaxValueInCashierScreen);
		double ExpectedTaxValueInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen) * 0.03;

		asrt.assertEquals(ActualTaxValueInCashierScreen,ExpectedTaxValueInCashierScreen,1.0,"Tax mismatch: Expected " + ExpectedTaxValueInCashierScreen +" but found " + ActualTaxValueInCashierScreen + " in CashierScreen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen) + ActualTaxValueInCashierScreen;
		String ActualTotalAmountValueInCashierScreen = TotalAmountInCashierScreen.replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualTotalAmountValueInCashierScreen),ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: Expected " + ExpectedTotalAmountInCashierScreen +" but found " + ActualTotalAmountValueInCashierScreen + " in CashierScreen");

		//Step 15: Click on the Amount
		//Step 16: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 17: Post the Invoice
		//Expected: Check final invoice details* Save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC284TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ProductTotal;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String TotalValue = Double.toString(Double.parseDouble(ExpectedTaxableValue.replaceAll("[^\\d.]", "").trim())+Double.parseDouble(TaxInCashierScreen.replaceAll("[^\\d.]", "").trim()));
		String TotalDiscount=ActualDiscountInCashierScreen;
		String NetValue = PaymentAmount;
		String TaxableValue = ExpectedTaxableValue;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationAfterPromotion(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				PromotionAmount,
				SkuList,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();    
	}

	// <summary>
	// Test Case Title : Create promotion discount agreement for "multiple of gross wt"
	// Automation ID : TC313
	// Author: Vishnu RR 
	// </summary>
	public void TC313_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception{

		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		base.newWindow(1);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.navigate().to(UtilityTestData.ERPURL);

		driver.navigate().refresh();
		Thread.sleep(3000);

		//Prerequisite - To Delete If promotion discount exists
		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);


		// ---------------- Promotion code ----------------
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Promotion code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_PromotionCode_PromotionCode_Input_0"),"0001");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_PromotionCode_ApplyFilters_label", "Apply"));

		// ---------------- Category code ----------------
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0")))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),"UNC");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// ---------------- Store Number ------------
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0")))
			{
				break;
			}
		}
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"), UtilityTestData.WarehouseId);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
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
		Thread.sleep(6000);
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			base.ClickCondition(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
			ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();

			if(base.isExists(NormalSaleGoldAndSilverObj.Ele_CheckDelete("Delete","disabled")))
			{
				Thread.sleep(5000);
				base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
				Thread.sleep(3000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable isFiltered", "Active"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active_Active_Input_0"),"No");
				Thread.sleep(1000);
				base.ClickCondition(LoginPageObj.Ele_ERPNo("No"));
				base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
				//no
				Thread.sleep(5000);
				base.UnifiedClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				Thread.sleep(5000);
				base.actionClick(StatementPostingObj.Btn_Calculate("SysBoxForm","Yes"));
				System.out.println("Existing Promotion Agreement deleted Successfully");
			}
			else
			{
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				System.out.println("Existing Promotion Agreement deleted Successfully");
			}
		}


		//Step:1 Go to promotion agreement ValueWise
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step: 2 Click on New Button
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(3000);

		//Step:3.Enter the following
		//Step:3.1 From date
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_FromDate", "dyn-date-picker-button"));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Today"));

		//Step:3.2 To Date 
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_ToDate", "dyn-date-picker-button"));
		LocalDate NextToDate = LocalDate.now().plusDays(1);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String ToFullDate = NextToDate.format(formatter);

		String ToNextDay = String.valueOf(NextToDate.getDayOfMonth());
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_FullDate(ToFullDate, ToNextDay));

		//Step:3.3 Advance Applicability
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("AVAMGD_PromotionAgreementValueWise_AdvanceApplicabilityDate","dyn-date-picker-button"));
		//				LocalDate Tomorrow = LocalDate.now().plusDays(1);
		//				String NextDay = String.valueOf(Tomorrow.getDayOfMonth());
		//base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_Date(ToNextDay));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_FullDate(ToFullDate, ToNextDay));

		//Step:3.4 Promotion Code 
		Thread.sleep(3000);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_PromotionCode);

		//Step:3.5 Category-Uncut
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_Categoryuncut);

		//Step:3.6 Store number
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"),UtilityTestData.WarehouseId);

		WebElement Scrollbars = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObjs= new Actions(driver);
		ActionsObjs.clickAndHold(Scrollbars).moveByOffset(400, 0).release().perform();
		Thread.sleep(15000);

		//Step:3.7 Multiple of Gross Weight.
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("AVAMGD_PromotionAgreementValueWise_MultipleOfNetweight_"));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", driver.findElement(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Multiple of gross weight",1)));
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("AVAMGD_PromotionAgreementValueWise_MultipleOfNetweight_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_MultipleOfGrossWeight);

		//Step:3.8 Quantity
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"));
		Thread.sleep(3000);
		js.executeScript("arguments[0].value = '';", driver.findElement(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Quantity",1)));
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_Quantity);

		//Step:3.9 Click on activate/deactivate and save
		//Click Overwrite /Append
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dyn-container _w611ys dyn-svg-symbol dyn-combobox-button"));
		base.ClickCondition(LoginPageObj.Ele_ERPNo(UtilityTestData.PromotionType));

		//Click on activate/deactivate and save
		base.ClickCondition(StatementPostingObj.Btn_Calculate("Activate","Activate"));
		System.out.println("****Activated successfully****");


		//Expected Result: Filtering to Verify if a tick mark appears against the newly added line.
		// Searching the Promotion discount 
		Thread.sleep(5000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);

		//sorting the From date 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "From date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_FromDate_label","Sort newest to oldest"));	
		Thread.sleep(5000);

		/// sorting the To date
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "To date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_ToDate_label","Sort newest to oldest"));	

		// selecting the catogory
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0")))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),"UNC");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));


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
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		base.ClickCondition(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_Refunded("Yes","__FilterField_Grid_Active_Active_Input"));
		base.UnifiedClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));


		// Expected Result: Verify if a tick mark appears against the newly added line.
		Thread.sleep(6000);
		asrt.assertTrue(base.isExists(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ActiveStatus("Active", "Yes")),"User is not able to view the Tick (✓) icon in the Confirm field after confirming the PDC Deposit code.");


		//Step:4.Go to distribution schedule 
		Thread.sleep(15000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Step: 5.Search n-agreement
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
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

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement runNowBtn = wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Run now successfully completed");

		//Step: 7.Click on Ok
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		//Step: 8 Go to Batch Job-->check the job status changed has been changed to ended
		boolean jobEnded = false;
		int maxAttempts = 8;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int attempt = 1; attempt <= maxAttempts; attempt++)
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
				wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.ClickCondition(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				jobEnded = true;
				System.out.println("Try block");
				break; // success, break the loop

			}
			catch (StaleElementReferenceException stale)
			{
				System.out.println("Attempt " + attempt + ": Stale element. Retrying after refresh...");
				if (attempt < maxAttempts)
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
				System.out.println("Attempt " + attempt + ": Timeout while waiting for 'Ended' job status.");
				if (attempt < maxAttempts)
				{
					driver.navigate().refresh();
					Thread.sleep(3000);
				}
				else
				{
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
			System.out.println("Completed");
		}
		Thread.sleep(12000);

		//Step:9.Go to download session --> Check if the status has been changed to applied
		driver.navigate().refresh();
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_SearchDownloadSessions);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(8000);			

		//Checking the Status has been changed to Applied 
		Thread.sleep(8000);
		String ActualStatus = base.GetValue(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Status",1));
		System.out.println("Status is: " + ActualStatus);

		asrt.assertEquals(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_ExpectedStatus, ActualStatus,"Mismatch in status displayed: "+ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_ExpectedStatus+" but found "+ActualStatus+" in Download Session Page ");		
	}

	// <summary>
	// Test Case Title : Create promotion discount agreement for "multiple of Amount""
	// Automation ID : TC314
	// Author: Vishnu RR 
	// </summary>
	public void TC314_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception{

		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		base.newWindow(1);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.navigate().to(UtilityTestData.ERPURL);

		driver.navigate().refresh();
		Thread.sleep(3000);

		//Prerequisite - To Delete If promotion discount exists
		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// ---------------- Promotion code ----------------
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Promotion code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_PromotionCode_PromotionCode_Input_0"),"0002");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_PromotionCode_ApplyFilters_label", "Apply"));

		// ---------------- Category code ----------------
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0")))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),"PLT");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// ---------------- Store Number ------------
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0")))
			{
				break;
			}
		}
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"), UtilityTestData.WarehouseId);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
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
		Thread.sleep(6000);
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			base.buttonClick(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
			ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();

			if(base.isExists(NormalSaleGoldAndSilverObj.Ele_CheckDelete("Delete","disabled")))
			{
				Thread.sleep(5000);
				base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
				Thread.sleep(3000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable isFiltered", "Active"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active_Active_Input_0"),"No");
				Thread.sleep(1000);
				base.ClickCondition(LoginPageObj.Ele_ERPNo("No"));
				base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
				//no
				Thread.sleep(5000);
				base.UnifiedClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				Thread.sleep(5000);
				base.actionClick(StatementPostingObj.Btn_Calculate("SysBoxForm","Yes"));
				System.out.println("Existing Promotion Agreement deleted Successfully");
			}
			else
			{
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				System.out.println("Existing Promotion Agreement deleted Successfully");
			}
		}


		//Step:1 Go to promotion agreement ValueWise
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step: 2 Click on New Button
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(3000);

		//Step:3.Enter the following
		//Step:3.1 From date
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_FromDate", "dyn-date-picker-button"));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Today"));

		//Step:3.2 To Date 
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_ToDate", "dyn-date-picker-button"));
		LocalDate NextToDate = LocalDate.now().plusDays(1);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String ToFullDate = NextToDate.format(formatter);

		String ToNextDay = String.valueOf(NextToDate.getDayOfMonth());
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_FullDate(ToFullDate, ToNextDay));

		//Step:3.3 Advance Applicability
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("AVAMGD_PromotionAgreementValueWise_AdvanceApplicabilityDate","dyn-date-picker-button"));
		//				LocalDate Tomorrow = LocalDate.now().plusDays(1);
		//				String NextDay = String.valueOf(Tomorrow.getDayOfMonth());
		//base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_Date(ToNextDay));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_FullDate(ToFullDate, ToNextDay));

		//Step:3.4 Promotion Code 
		Thread.sleep(3000);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC314_PromotionCode);

		//Step:3.5 Category-Platinum
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC314_Categoryuncut);

		//Step:3.6 Store number
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"),UtilityTestData.WarehouseId);

		WebElement Scrollbars = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObjs= new Actions(driver);
		ActionsObjs.clickAndHold(Scrollbars).moveByOffset(400, 0).release().perform();
		Thread.sleep(15000);

		//Step:3.7 Multiple of Amount.
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1)));
		Thread.sleep(3000);
		//base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("__FilterField_Grid_MultipleOf_MultipleOf_Input_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC314_MultipleOfWeight);
		base.setData(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC314_MultipleOfWeight);

		//Step:3.8 Quantity
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"));
		Thread.sleep(3000);
		js.executeScript("arguments[0].value = '';", driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Quantity",1)));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_Quantity);

		//Step:3.9 Click on activate/deactivate and save
		//Click Overwrite /Append
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dyn-container _w611ys dyn-svg-symbol dyn-combobox-button"));
		base.ClickCondition(LoginPageObj.Ele_ERPNo(UtilityTestData.PromotionType));

		//Click on activate/deactivate and save
		base.ClickCondition(StatementPostingObj.Btn_Calculate("Activate","Activate"));
		System.out.println("****Activated successfully****");

		//Expected Result: Filtering to Verify if a tick mark appears against the newly added line.
		// Searching the Promotion discount 
		Thread.sleep(5000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);

		//sorting the From date 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "From date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_FromDate_label","Sort newest to oldest"));	
		Thread.sleep(5000);

		/// sorting the To date
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "To date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_ToDate_label","Sort newest to oldest"));	

		// selecting the catogory
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0")))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),"PLT");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));


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
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		base.ClickCondition(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_Refunded("Yes","__FilterField_Grid_Active_Active_Input"));
		base.UnifiedClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));


		// Expected Result: Verify if a tick mark appears against the newly added line.
		Thread.sleep(6000);
		asrt.assertTrue(base.isExists(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ActiveStatus("Active", "Yes")),"User is not able to view the Tick (✓) icon in the Confirm field after confirming the PDC Deposit code.");


		//Step:4.Go to distribution schedule 
		Thread.sleep(15000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Step: 5.Search n-agreement
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
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

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement runNowBtn = wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Run now successfully completed");

		//Step: 7.Click on Ok
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		//Step: 8 Go to Batch Job-->check the job status changed has been changed to ended
		boolean jobEnded = false;
		int maxAttempts = 8;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int attempt = 1; attempt <= maxAttempts; attempt++)
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
				wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.ClickCondition(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				jobEnded = true;
				System.out.println("Try block");
				break; // success, break the loop

			}
			catch (StaleElementReferenceException stale)
			{
				System.out.println("Attempt " + attempt + ": Stale element. Retrying after refresh...");
				if (attempt < maxAttempts)
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
				System.out.println("Attempt " + attempt + ": Timeout while waiting for 'Ended' job status.");
				if (attempt < maxAttempts)
				{
					driver.navigate().refresh();
					Thread.sleep(3000);
				}
				else
				{
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
			System.out.println("Completed");
		}
		Thread.sleep(12000);

		//Step:9.Go to download session --> Check if the status has been changed to applied
		driver.navigate().refresh();
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_SearchDownloadSessions);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(8000);			

		//Checking the Status has been changed to Applied 
		Thread.sleep(8000);
		String ActualStatus = base.GetValue(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Status",1));
		System.out.println("Status is: " + ActualStatus);

		asrt.assertEquals(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC314_ExpectedStatus, ActualStatus,"Mismatch in status displayed: "+ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC314_ExpectedStatus+" but found "+ActualStatus+" in Download Session Page ");

	}

	// <summary>
	// Test Case Title : Apply promotion discount for Uncut
	// Automation ID : TC317
	// Author: Robin
	// </summary>
	public void TC317_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception
	{
		// Step 1. Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite To take a Valid promotion discount from erp
		Map<String, String> PromotionData = erpUtils.CreateDeleteValidPromotionDiscountAgreement(UtilityTestData.CategoryUncut); 
		String PromotionType = PromotionData.get("PromotionType");
		String MultiplyAmount = PromotionData.get("Amount");
		String Multiplier = PromotionData.get("Multiplier");
		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", ""); 

		// Step 2. Click on Transaction button
		//To take current goldrate 
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		double CurrentGoldRate22k =appUtils.FetchCurrentBoardRate(UtilityTestData.Purity22);
		double Weight=Double.parseDouble(Multiplier.replaceAll(",", ""))/CurrentGoldRate22k;

		//Step 3. Select customer and Click on add to estimation button
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC317_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4. Scan Uncut SKU and click on Add to cart button
		//Expected : Check calculation 
		//Billing Screen *No of Product lines,*Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax)
		//*Displayed Subtotal,*TAX,*Total Amount,*Discount
		Thread.sleep(1000);		
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC317_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC317_FromCounterUncut,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC317_MetalTypeGold,
				Weight,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC317_SKUCount);
		List<String> RequiredUncutSku = SkuGoldSale.PcsRateSKU;
		System.out.println("Uncut Selected SKUs: " + RequiredUncutSku);
		appUtils.HamBurgerButtonClick("iconShop");	
		String ItemNameInItemDetailsPge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		String RGRate="";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredUncutSku.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku = RequiredUncutSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			RGRate=ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//To calculate Promotion discount of Sku based on the Promotion agreement added
		double Amount = Double.parseDouble(TotalWithoutTax);
		double PromotionAmount = 0.00;
		double Multiple=Double.parseDouble(Multiplier.replace("₹", "").replace(",", "").trim());
		double Range=Double.parseDouble(MultiplyAmount);
		if(Amount >= Multiple)
		{
			int AmountMultiple = (int)((Amount - Multiple) / Multiple) + 1;  
			double MultiplierValue=AmountMultiple * Range;
			PromotionAmount = CurrentGoldRate22k* MultiplierValue;
		}       
		System.out.println("PromotionAmount  "+PromotionAmount);

		//Transcation Page Verify
		String ActualTotalAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		// Step 5. Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(3000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		// Step 6.Click on apply promotion discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));

		Thread.sleep(3000);
		Map<String, String> TransactionDataLines = appUtils.TransactionSKUsLinesVerify(RequiredUncutSku, ScannedSkuDataMap);
		String SubTotal = TransactionDataLines.get("Subtotal");
		String TotalGrossWeight = TransactionDataLines.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLines.get("NetWeight");
		String Tax 		        = TransactionDataLines.get("Tax");
		String TotalAmount      = TransactionDataLines.get("TotalAmount");
		String NetTotal         = TransactionDataLines.get("NetTotal");
		String LinesCount       = TransactionDataLines.get("LinesCount");

		System.out.println("SubTotal: "+SubTotal);
		System.out.println("TotalGrossWeight: "+TotalGrossWeight);
		System.out.println("TotalNetWeight: "+TotalNetWeight);
		System.out.println("Tax: "+Tax);
		System.out.println("TotalAmount: "+TotalAmount);
		System.out.println("NetTotal: "+NetTotal);
		System.out.println("LinesCount: "+LinesCount);

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String PromotionActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax);

		List<WebElement> AllProductRowsInBillingScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));

		asrt.assertEquals(ActualLinesCountInBillingScreen, ExpectedLinesCountInBillingScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingScreen + " but found " + ActualLinesCountInBillingScreen + " in cashier screen");

		String ActualItemNameInBillingScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInBillingScreen = ItemNameInItemDetailsPge.trim();

		asrt.assertTrue(ExpectedItemNameInBillingScreen.contains(ActualItemNameInBillingScreen),"Item name mismatch: Expected is " + ExpectedItemNameInBillingScreen + " but got "+ ActualItemNameInBillingScreen + " in cashier screen");

		String ActualQuantityInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInBillingScreen = GrossWeight;

		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingScreen), Double.parseDouble(ExpectedQuantityInBillingScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingScreen + " but got " + ActualQuantityInBillingScreen + " in cashier screen");

		String ActualPromotionDiscountInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double PromotionDiscount=Double.parseDouble(ActualPromotionDiscountInBillingScreen);

		asrt.assertEquals(PromotionDiscount, PromotionAmount,1.0,"Discount value mismatch: Expected is "+PromotionAmount+" but got "+PromotionDiscount+" in cashier screen");

		String ActualTotalWithoutTaxInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedSubTotalInBillingScreen = ActualTotalWithoutTaxInBillingScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingScreen),Double.parseDouble(ExpectedSubTotalInBillingScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingScreen + " but found " + ActualSubTotalInBillingScreen+ " in cashier screen");

		double ExpectedTotalWithoutTaxInBillingScreen =0.00;
		ExpectedTotalWithoutTaxInBillingScreen = ExpectedTotalWithoutTaxInBillingPage-PromotionAmount;

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingScreen.replace("-", "")),ExpectedTotalWithoutTaxInBillingScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingScreen+" but got "+ActualTotalWithoutTaxInBillingScreen+ " in cashier screen");

		String TaxInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInBillingScreen = Double.parseDouble(TaxInBillingScreen);
		double ExpectedTaxInBillingScreen = Double.parseDouble(ActualSubTotalInBillingScreen)*0.03;

		asrt.assertEquals(ActualTaxInBillingScreen,ExpectedTaxInBillingScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingScreen + " but found " + ActualTaxInBillingScreen+ " in cashier screen");

		double ExpectedTotalAmountInBillingScreen = Double.parseDouble(ActualSubTotalInBillingScreen)   +   Double.parseDouble(TaxInBillingScreen);
		ActualTotalAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualTotalAmount), ExpectedTotalAmountInBillingScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingScreen + " but found " + ActualTotalAmount + " in cashier screen");

		// Step 7. Click on Save to estimation button
		// Step 8. save estimate
		//Expected : Verify performa invoice
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC317_ProformaInvoice");
		Thread.sleep(3000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String TotalAdjustment ="";		
		String AdditionalDiscount = PromotionActualDiscount;
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		// Step 9.Recall estimate from cashier screen
		// Step 10. Select estimate and click on recall estimation
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		// Step 11.Select the Transaction Type as Sales 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC317_TransactionTypeSales);

		// Step 12.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 13.Choose a sales representative(Not available after data refresh)
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Expected :Cashier Screen after recall Estimate *No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal
		// *Discount,*TAX,*Total Amount
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));

		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		String ActualItemNameInCashierScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsPge.trim();

		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in cashier screen");

		String ActualQuantityInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInCashierScreen = GrossWeight;

		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in cashier screen");

		String ActualPromotionDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		PromotionDiscount=Double.parseDouble(ActualPromotionDiscountInCashierScreen);

		asrt.assertEquals(PromotionDiscount, PromotionAmount,1.0,"Discount value mismatch: Expected is "+PromotionAmount+" but got "+PromotionDiscount+" in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		double ExpectedTotalWithoutTaxInCashierScreen =0.00;
		ExpectedTotalWithoutTaxInCashierScreen = ExpectedTotalWithoutTaxInBillingPage-PromotionAmount;

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),ExpectedTotalWithoutTaxInCashierScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;

		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)   +   Double.parseDouble(TaxInCashierScreen);
		ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		// Step 14.Click on the Amount
		// Step 15.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// Step 16.Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC317_SaleInvoice");
		Thread.sleep(3000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount=ActualPromotionDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationAfterPromotion(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				PromotionAmount,
				RequiredUncutSku,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}

	// <summary>
	// Test Case Title : Apply promotion discount for Platinum
	// Automation ID : TC318
	// Author: Robin
	// </summary>
	public void TC318_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception
	{
		// Step 1. Login to POS
		Thread.sleep(2000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite To take a Valid promotion discount from erp
		Map<String, String> PromotionData  =	erpUtils.CreateDeleteValidPromotionDiscountAgreement(UtilityTestData.CategoryPlatinum);	
		String PromotionType = PromotionData.get("PromotionType");
		String MultiplyAmount = PromotionData.get("Amount");
		String Multiplier = PromotionData.get("Multiplier");
		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", ""); 

		// Step 2. Click on Transaction button
		//To take current goldrate 
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		double CurrentGoldRate22k =appUtils.FetchCurrentBoardRate(UtilityTestData.Purity22);
		double Weight=Double.parseDouble(Multiplier.replaceAll(",", ""))/CurrentGoldRate22k;

		//Step 3. Select customer and Click on add to estimation button
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC318_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4. Scan Platinum SKU and click on Add to cart button
		//Expected : Check calculation 
		//Billing Screen *No of Product lines,*Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax)
		//               *Displayed Subtotal,*TAX,*Total Amount,*Discount
		Thread.sleep(1000);		
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC318_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC318_FromCounterPlatinum,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC318_MetalTypePlatinum,
				Weight,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC318_SKUCount);
		List<String> RequiredGoldSku = SkuGoldSale.PcsRateSKU;
		System.out.println("Gold Selected SKUs: " + RequiredGoldSku);
		appUtils.HamBurgerButtonClick("iconShop");	
		String ItemNameInItemDetailsPge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		String RGRate="";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredGoldSku.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku = RequiredGoldSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			RGRate=ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//To calculate Promotion discount of Sku based on the Promotion agreement added
		double Amount = Double.parseDouble(TotalWithoutTax);
		double PromotionAmount = 0.00;
		double Multiple=Double.parseDouble(Multiplier.replace("₹", "").replace(",", "").trim());
		double Range=Double.parseDouble(MultiplyAmount);

		if(Amount >= Multiple)
		{
			int AmountMultiple = (int)((Amount - Multiple) / Multiple) + 1;  
			double MultiplierValue=AmountMultiple * Range;
			PromotionAmount =  CurrentGoldRate22k * MultiplierValue;//Double.parseDouble(CurrentGoldRate22k) * MultiplierValue;
		}       
		System.out.println("PromotionAmount  "+PromotionAmount);

		//Transcation Page Verify
		String ActualTotalAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		// Step 5. Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(3000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		// Step 6.Click on apply promotion discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));

		Thread.sleep(3000);
		Map<String, String> TransactionDataLines = appUtils.TransactionSKUsLinesVerify(RequiredGoldSku, ScannedSkuDataMap);
		String SubTotal = TransactionDataLines.get("Subtotal");
		String TotalGrossWeight = TransactionDataLines.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLines.get("NetWeight");
		String Tax 		        = TransactionDataLines.get("Tax");
		String TotalAmount      = TransactionDataLines.get("TotalAmount");
		String NetTotal         = TransactionDataLines.get("NetTotal");
		String LinesCount       = TransactionDataLines.get("LinesCount");

		System.out.println("SubTotal: "+SubTotal);
		System.out.println("TotalGrossWeight: "+TotalGrossWeight);
		System.out.println("TotalNetWeight: "+TotalNetWeight);
		System.out.println("Tax: "+Tax);
		System.out.println("TotalAmount: "+TotalAmount);
		System.out.println("NetTotal: "+NetTotal);
		System.out.println("LinesCount: "+LinesCount);

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String PromotionActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax);

		List<WebElement> AllProductRowsInBillingScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));

		asrt.assertEquals(ActualLinesCountInBillingScreen, ExpectedLinesCountInBillingScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingScreen + " but found " + ActualLinesCountInBillingScreen + " in cashier screen");

		String ActualItemNameInBillingScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInBillingScreen = ItemNameInItemDetailsPge.trim();

		asrt.assertTrue(ExpectedItemNameInBillingScreen.contains(ActualItemNameInBillingScreen),"Item name mismatch: Expected is " + ExpectedItemNameInBillingScreen + " but got "+ ActualItemNameInBillingScreen + " in cashier screen");

		String ActualQuantityInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInBillingScreen = GrossWeight;

		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingScreen), Double.parseDouble(ExpectedQuantityInBillingScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingScreen + " but got " + ActualQuantityInBillingScreen + " in cashier screen");

		String ActualPromotionDiscountInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double PromotionDiscount=Double.parseDouble(ActualPromotionDiscountInBillingScreen);

		asrt.assertEquals(PromotionDiscount, PromotionAmount,1.0,"Discount value mismatch: Expected is "+PromotionAmount+" but got "+PromotionDiscount+" in cashier screen");

		String ActualTotalWithoutTaxInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedSubTotalInBillingScreen = ActualTotalWithoutTaxInBillingScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingScreen),Double.parseDouble(ExpectedSubTotalInBillingScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingScreen + " but found " + ActualSubTotalInBillingScreen+ " in cashier screen");

		double ExpectedTotalWithoutTaxInBillingScreen =0.00;
		ExpectedTotalWithoutTaxInBillingScreen = ExpectedTotalWithoutTaxInBillingPage-PromotionAmount;

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingScreen.replace("-", "")),ExpectedTotalWithoutTaxInBillingScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingScreen+" but got "+ActualTotalWithoutTaxInBillingScreen+ " in cashier screen");

		String TaxInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInBillingScreen = Double.parseDouble(TaxInBillingScreen);
		double ExpectedTaxInBillingScreen = Double.parseDouble(ActualSubTotalInBillingScreen)*0.03;

		asrt.assertEquals(ActualTaxInBillingScreen,ExpectedTaxInBillingScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingScreen + " but found " + ActualTaxInBillingScreen+ " in cashier screen");

		double ExpectedTotalAmountInBillingScreen = Double.parseDouble(ActualSubTotalInBillingScreen)   +   Double.parseDouble(TaxInBillingScreen);
		ActualTotalAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualTotalAmount), ExpectedTotalAmountInBillingScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingScreen + " but found " + ActualTotalAmount + " in cashier screen");

		// Step 7. Click on Save to estimation button
		// Step 8. save estimate
		//Expected : Verify performa invoice
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC318_ProformaInvoice");
		Thread.sleep(3000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String TotalAdjustment ="";		
		String AdditionalDiscount = PromotionActualDiscount;
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		// Step 9.Recall estimate from cashier screen
		// Step 10. Select estimate and click on recall estimation
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		// Step 11.Select the Transaction Type as Sales 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC318_TransactionTypeSales);

		// Step 12.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 13.Choose a sales representative(Not available after data refresh)
		//Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Expected :Cashier Screen after recall Estimate *No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal
		//                                               *Discount,*TAX,*Total Amount
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));

		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		String ActualItemNameInCashierScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsPge.trim();

		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in cashier screen");

		String ActualQuantityInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInCashierScreen = GrossWeight;

		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in cashier screen");

		String ActualPromotionDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		PromotionDiscount=Double.parseDouble(ActualPromotionDiscountInCashierScreen);

		asrt.assertEquals(PromotionDiscount, PromotionAmount,1.0,"Discount value mismatch: Expected is "+PromotionAmount+" but got "+PromotionDiscount+" in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		double ExpectedTotalWithoutTaxInCashierScreen =0.00;
		ExpectedTotalWithoutTaxInCashierScreen = ExpectedTotalWithoutTaxInBillingPage-PromotionAmount;

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),ExpectedTotalWithoutTaxInCashierScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;

		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)   +   Double.parseDouble(TaxInCashierScreen);
		ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		// Step 14.Click on the Amount
		// Step 15.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// Step 16.Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC318_SaleInvoice");
		Thread.sleep(3000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount=ActualPromotionDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationAfterPromotion(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				PromotionAmount,
				RequiredGoldSku,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}

	// <summary>
	// Test Case Title : Apply coin discount with other discount
	// Automation ID : TC279
	// Author:Nandagopan
	// </summary>
	public void TC279_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		//Step 1.Login to POS
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer
		//Step 4.click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5.Scan SKU( eligible for package discount)
		//Step 6.Click on Add to cart button
		//Expected: Check calculation
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkusWithAgreementTc, 1);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                = "";
		String TotalWithoutTax         = "";
		String WastageQty              = "";
		String WastageAmt              = "";

		List<String> ProductNames	  = new ArrayList<>();
		List<String> ProductQtyList	  = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredKeralSku.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = RequiredKeralSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt =  ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Step 7: Select any one of the product in cart
		//Step 8: Click on discount button
		//Step 9: Click on normal discount button
		//Step 10: Select package making discount
		//Step 11: Select discount range
		//Step 12: Enter discount percentage
		//Step 13: Click on Checkbox(Apply higher discount)
		//Step 14: Click on apply button
		//Step 15: Enter OTP
		//Note: check calculation
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_DiscountCategory);
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);

		// Select option based on WastageQty range and set making percentage
		int SlabNumber				  = 0;
		double PackageDiscountPercent = 0;

		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + WastageQtyValue);
		} else {
			System.out.println("Wastage qty out of range: " + WastageQtyValue);
		}

		if (SlabNumber != 0) {
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				String Value = option.getAttribute("value");
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {
					Select.selectByValue(Value);
					System.out.println("Selected package: " + Value);
					Thread.sleep(3000);
					base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
					base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));
					break;
				} else {
					System.out.println("No matching package found for slab " + SlabNumber);
				}
			}
		}

		Thread.sleep(3000);
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
		double ExpectedDiscount = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;
		double ExpectedTotalWithoutTax = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;
		String ActualSaleAmountBeforeCGCDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();  			
		double SkuTotalAmount = Double.parseDouble(ActualSaleAmountBeforeCGCDiscount);
		double CoinQuantity = 0.0;		
		if      (SkuTotalAmount >= 10000   && SkuTotalAmount <= 90000)   CoinQuantity = 0.10;
		else if (SkuTotalAmount >= 90001   && SkuTotalAmount <= 190000)  CoinQuantity = 0.20;
		else if (SkuTotalAmount >= 190001  && SkuTotalAmount <= 290000)  CoinQuantity = 0.30;
		else if (SkuTotalAmount >= 290001  && SkuTotalAmount <= 390000)  CoinQuantity = 0.40;

		//Step 16. Search cgc 
		//Step 17. Choose the required item
		//Step 18. Click on add item after adding the purity
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_GiftCoin);
		Thread.sleep(3000);
		base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
		Thread.sleep(3000);
		List<String> ProductNumberList = base.GetElementTexts(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_ProductList("dataListLine","CGC_")); 
		for (int i = 0; i < ProductNumberList.size(); i++) {
			int ProductNumber = i + 1;
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			Thread.sleep(3000);
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),ProductNumberList.get(i));
			Thread.sleep(3000);
			base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
			Thread.sleep(3000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",ProductNumberList.get(i)));
			Thread.sleep(5000);
			if (base.isPresent(LoginPageObj.Ele_ErrorMessage("messageText h5"))) 
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			Thread.sleep(3000);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
			Thread.sleep(7000);
			if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				continue;
			}
			break;
		}

		//Step 19. Enter the weight (rate will be autocalculated )
		//Expected:Check calculation
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),Double.toString(CoinQuantity));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),"Enter");
		Thread.sleep(3000);
		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));

		String MakingRate          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*CoinQuantity ;
		String ActualMakingValue   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		asrt.assertEquals(Double.parseDouble(ActualMakingValue), ExpectedMakingValue,1.0, "Making value mismatch: Expected is "+ExpectedMakingValue+" but got "+ActualMakingValue+" in item details page");

		String RgRate              = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue      = CoinQuantity*Double.parseDouble(RgRate);
		String ActualCvalue        = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));
		asrt.assertEquals(Double.parseDouble(ActualCvalue), ExpectedCvalue,1.0, "Making value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");

		String ActualTotalGoldCoinAmount   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		String WasteAmnt =driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_InputFieldByLabel("Wastage amount")).getAttribute("value");
		double WastageAmnt = Double.parseDouble(WasteAmnt);
		double ExpectedTotalGoldCoinAmount = Math.round((ExpectedCvalue + WastageAmnt) * 100.0) / 100.0;
		asrt.assertEquals(Double.parseDouble(ActualTotalGoldCoinAmount), ExpectedTotalGoldCoinAmount,1.0, "Making value mismatch: Expected is "+ExpectedTotalGoldCoinAmount+" but got "+ActualTotalGoldCoinAmount+" in item details page");

		String CGCQty = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value");
		String CGCTotal = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value");
		String CGCItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		ProductNames.add(CGCItemName);
		ProductQtyList.add(CGCQty);
		ProductTotalList.add(CGCTotal);

		//Step 20. Click on add to cart
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));

		// Check for "Specify Price" modal
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(2000); // Short delay for modal to close
			}
		} catch (Exception e) {}

		String SubTotalBeforeDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();

		//Step 21. Go to discounts
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		//Step 21.1: apply gift coin discount in case of gold
		//Expected:Verify whether coin value deducts from sale amount
		//Note:Since we are selecting Sku which applies for Package Discount, Below steps are not needed as per Requested by the client-
		//Step 21.1 Select discount type
		// Step 21.2 Choose discount percentage
		// Step 21.3 Choose discount category
		//Step 22.Click on apply button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Gift Coin Discount"));
		Thread.sleep(2000); 
		// Check for "Specify Price" modal
		if (base.isElementPresent(driver, NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price"))) {			
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
			Thread.sleep(500); 
		} else {
			Thread.sleep(500); 
		}

		String CGCDiscountItemName = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_CGCItemName ;
		String CGCDiscountQty = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_CGCQty;
		String CGCDiscountTotal = "-" + CGCTotal;
		ProductNames.add(CGCDiscountItemName);
		ProductQtyList.add(CGCDiscountQty);
		ProductTotalList.add(CGCDiscountTotal);

		double ExpectedSaleAmount = Double.parseDouble(SubTotalBeforeDiscount) + Double.parseDouble(ProductTotalList.get(2));
		asrt.assertEquals(Double.parseDouble(ActualSaleAmountBeforeCGCDiscount),ExpectedSaleAmount,1.00,"Sale amount mismatch: Expected is"+ExpectedSaleAmount+" but found "+ActualSaleAmountBeforeCGCDiscount+" in billing page");

		//Expected Result: In billing screen, Note: *No of Product lines *Displayed Item Name *Displayed Quantity *Discount value *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String Discount 			= TransactionDataLine.get("Discount");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ActualLinesCount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount,ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		
		Thread.sleep(5000);;

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName1 = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName1.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax1 = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax1.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String TotalWithoutTaxInBillingPge = TotalWithoutTax1.get(i).getText().replaceAll("[^\\d.-]", "").trim();	
			SubTotalSum = SubTotalSum + Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		String TaxableValue = Double.toString(Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1)));
		double ExpectedTax = (Double.parseDouble(ProductTotalList.get(0))+Double.parseDouble(ProductTotalList.get(1)))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge),1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble( base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");
		String TotalGrossWt = String.format("%.3f", Double.parseDouble(ProductQtyList.get(0)) + Double.parseDouble(ProductQtyList.get(1)));

		//Step 23. Click on Save to estimation button
		//Step 24. Save estimate
		//Expected Result:Verify Invoice Details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(5000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC279_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;
		String Gst                 = (Tax);
		String cleanedSubTotal = SubTotal.replaceAll("[₹, ]", "");
		String cleanedTax = Tax.replaceAll("[₹, ]", "");
		double InvoiceTotal = Double.parseDouble(TaxableValue) + Double.parseDouble(cleanedTax);
		String InvoiceTotl = Double.toString(InvoiceTotal);
		String AdjustmentAmount = ProductTotalList.get(1);
		String NetTotalPdf 		   = Double.toString((InvoiceTotal) - Double.parseDouble(AdjustmentAmount));
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotl,
				AdjustmentAmount,
				NetTotalPdf,
				"");

		//Step 25. Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");

		//Step 26: Select estimate and click on recall estimation
		//Expected:Verify whether the recalled item is the same as the item in the cart
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		//Step 27. Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),"Sales");

		//Step 28. Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		appUtils.HandleSpecifyRateModalInRecall();

		//Step 29: Choose a sales representative
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Expected Result:Note:Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX *Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotalInCashierScreen = TransactionDataLineInCashierScreen.get("Subtotal"); 
		String NetTotalInCashierScreen = TransactionDataLineInCashierScreen.get("NetTotal"); 
		String DiscountInCashierScreen = TransactionDataLineInCashierScreen.get("Discount"); 
		String TotalNetWeightInCashierScreen = TransactionDataLineInCashierScreen.get("NetWeight"); 
		String TaxInCashierScreen = TransactionDataLineInCashierScreen.get("Tax"); 
		String TotalAmountInCashierScreen = TransactionDataLineInCashierScreen.get("TotalAmount"); 
		String ActualLinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is " + ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		double SubTotalSumInCashierScreen = 0.00;
		for (int i = 0; i < AllProductRowsInCashierScreen.size(); i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText(); String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in cashier screen");

			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", ""); String ExpectedQuantityInCashierScreen = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen); double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen, ExpectedQtyInCashierScreen, 1.000, "Quantity mismatch: Expected is " + ExpectedQtyInCashierScreen + " but got " + ActualQtyInCashierScreen + " in cashier screen");

			List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim(); String TotalWithoutTaxValueInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSumInCashierScreen += Double.parseDouble(TotalWithoutTaxValueInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen), Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), 1.0, "TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierScreen + " but got " + ActualTotalWithoutTaxInCashierScreen + " in cashier screen");
		}

		String ExpectedSubTotalInCashierScreen = String.format("%.2f", SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen), Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0, "Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen + " in cashier screen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim(); double ActualTaxValueInCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxValueInCashierScreen = (Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1))) * 0.03; String ExpectedTaxInCashierScreen = String.format("%.2f", ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen), 1.0, "Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen + " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen) + Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountValueInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountValueInCashierScreen, ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: " + ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountValueInCashierScreen + " in cashier screen");

		//Step 30. Click on the Amount
		//Step 31. Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 32. Post the Invoice
		//Expected Result: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC279_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalNetWt = TotalGrossWt;
		String InvoiceNo = pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				Discount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_TotalQtyPcs,
				TotalNetWt,
				TotalGrossWt,
				TaxableValue,
				NetTotalPdf,
				PaymentAmount,
				AdjustmentAmount,
				RequiredKeralSku,
				ScannedSkuDataMap
				);
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(5000);
		appUtils.SalesReturnFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_CustomerNo, InvoiceNo, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_ReturnProducts);
		appUtils.CounterFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_FromCounterSKU,ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC279_MetalTypeSKU,RequiredKeralSku, UtilityTestData.Terminal);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	
	}


	// <summary>
	// Test Case Title : Create promotion discount agreement for "Advance applicability"
	// Automation ID : TC315
	// Author: Aiswarya
	// </summary>
	public void TC315_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception{

		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		base.newWindow(1);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.navigate().to(UtilityTestData.ERPURL);

		driver.navigate().refresh();
		Thread.sleep(3000);

		//Prerequisite - To Delete If promotion discount exists
		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Promotion Agreement Value Wise");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// ---------------- Promotion code ----------------
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Promotion code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_PromotionCode_PromotionCode_Input_0"),"0002");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_PromotionCode_ApplyFilters_label", "Apply"));

		// ---------------- Category code ----------------
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0")))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),"GLD");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));

		// ---------------- Store Number ------------
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0")))
			{
				break;
			}
		}
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_StoreNumber_StoreNumber_Input_0"), UtilityTestData.WarehouseId);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_StoreNumber_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
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
		Thread.sleep(6000);
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			base.buttonClick(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));
			ActionsObj.clickAndHold(Scrollbar).moveByOffset(400, 0).release().perform();

			if(base.isExists(NormalSaleGoldAndSilverObj.Ele_CheckDelete("Delete","disabled")))
			{
				Thread.sleep(5000);
				base.excuteJsClick(StatementPostingObj.Btn_Calculate("Activate","Activate"));
				Thread.sleep(3000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable isFiltered", "Active"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active_Active_Input_0"),"No");
				Thread.sleep(1000);
				base.ClickCondition(LoginPageObj.Ele_ERPNo("No"));
				base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));
				//no
				Thread.sleep(5000);
				base.UnifiedClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				Thread.sleep(5000);
				base.actionClick(StatementPostingObj.Btn_Calculate("SysBoxForm","Yes"));
				System.out.println("Existing Promotion Agreement deleted Successfully");
			}
			else
			{
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				System.out.println("Existing Promotion Agreement deleted Successfully");
			}
		}


		//Step:1 Go to promotion agreement ValueWise
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

		//Step: 2 Click on New Button
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(3000);

		//Step:3.Enter the following
		//Step:3.1 From date
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_FromDate", "dyn-date-picker-button"));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Today"));

		//Step:3.2 To Date 
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("Grid_ToDate", "dyn-date-picker-button"));
		LocalDate NextToDate = LocalDate.now().plusDays(1);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String ToFullDate = NextToDate.format(formatter);

		String ToNextDay = String.valueOf(NextToDate.getDayOfMonth());
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_FullDate(ToFullDate, ToNextDay));

		//Step:3.3 Advance Applicability
		Thread.sleep(3000);
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Btn_DateClick("AVAMGD_PromotionAgreementValueWise_AdvanceApplicabilityDate","dyn-date-picker-button"));
		//				LocalDate Tomorrow = LocalDate.now().plusDays(1);
		//				String NextDay = String.valueOf(Tomorrow.getDayOfMonth());
		//base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_Date(ToNextDay));
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_FullDate(ToFullDate, ToNextDay));

		//Step:3.4 Promotion Code 
		Thread.sleep(3000);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_PromotionCode"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC315_PromotionCode);

		//Step:3.5 Category-Gold
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_ItemTypeCode_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC315_Categoryuncut);

		//Step:3.6 Store number
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_StoreNumber_"),UtilityTestData.WarehouseId);

		WebElement Scrollbars = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObjs= new Actions(driver);
		ActionsObjs.clickAndHold(Scrollbars).moveByOffset(400, 0).release().perform();
		Thread.sleep(15000);

		//Step:3.7 Multiple of Amount.
		base.ClickCondition(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1)));
		Thread.sleep(3000);
		//base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("__FilterField_Grid_MultipleOf_MultipleOf_Input_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC315_MultipleOfWeight);
		base.setData(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Multiple of",1),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC315_MultipleOfWeight);

		//Step:3.8 Quantity
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"));
		Thread.sleep(3000);
		js.executeScript("arguments[0].value = '';", driver.findElement(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Quantity",1)));
		Thread.sleep(3000);
		base.setData(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("Grid_Qty_"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC315_Quantity);

		//Step:3.9 Click on activate/deactivate and save
		//Click Overwrite /Append
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dyn-container _w611ys dyn-svg-symbol dyn-combobox-button"));
		base.ClickCondition(LoginPageObj.Ele_ERPNo(UtilityTestData.PromotionType));

		//Click on activate/deactivate and save
		base.ClickCondition(StatementPostingObj.Btn_Calculate("Activate","Activate"));
		System.out.println("****Activated successfully****");

		//Expected Result: Filtering to Verify if a tick mark appears against the newly added line.
		// Searching the Promotion discount 
		Thread.sleep(5000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchPromotionDiscount);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);

		//sorting the From date 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "From date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_FromDate_label","Sort newest to oldest"));	
		Thread.sleep(5000);

		/// sorting the To date
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "To date"));
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Descending_Grid_ToDate_label","Sort newest to oldest"));	

		// selecting the catogory
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dyn-headerCell _edgl5a isFilterable", "Category"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0")))
			{
				break;
			}
		}

		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemTypeCode_ItemTypeCode_Input_0"),"PLT");
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemTypeCode_ApplyFilters_label", "Apply"));


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
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Active"),"Yes");
		base.ClickCondition(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_Refunded("Yes","__FilterField_Grid_Active_Active_Input"));
		base.UnifiedClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Active_ApplyFilters_label", "Apply"));


		// Expected Result: Verify if a tick mark appears against the newly added line.
		Thread.sleep(6000);
		asrt.assertTrue(base.isExists(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ActiveStatus("Active", "Yes")),"User is not able to view the Tick (✓) icon in the Confirm field after confirming the PDC Deposit code.");


		//Step:4.Go to distribution schedule 
		Thread.sleep(15000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Step: 5.Search n-agreement
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
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

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement runNowBtn = wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Run now successfully completed");

		//Step: 7.Click on Ok
		base.ClickCondition(LoginPageObj.Edt_Alert("OK"));

		//Step: 8 Go to Batch Job-->check the job status changed has been changed to ended
		boolean jobEnded = false;
		int maxAttempts = 8;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int attempt = 1; attempt <= maxAttempts; attempt++)
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
				wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.ClickCondition(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				jobEnded = true;
				System.out.println("Try block");
				break; // success, break the loop

			}
			catch (StaleElementReferenceException stale)
			{
				System.out.println("Attempt " + attempt + ": Stale element. Retrying after refresh...");
				if (attempt < maxAttempts)
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
				System.out.println("Attempt " + attempt + ": Timeout while waiting for 'Ended' job status.");
				if (attempt < maxAttempts)
				{
					driver.navigate().refresh();
					Thread.sleep(3000);
				}
				else
				{
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
			System.out.println("Completed");
		}
		Thread.sleep(12000);

		//Step:9.Go to download session --> Check if the status has been changed to applied
		driver.navigate().refresh();
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC313_SearchDownloadSessions);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(8000);			

		//Checking the Status has been changed to Applied 
		Thread.sleep(8000);
		String ActualStatus = base.GetValue(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_ERPStatus("Status",1));
		System.out.println("Status is: " + ActualStatus);

		asrt.assertEquals(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC315_ExpectedStatus, ActualStatus,"Mismatch in status displayed: "+ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData.TC315_ExpectedStatus+" but found "+ActualStatus+" in Download Session Page ");

	}


	// <summary>
	// Test Case Title : Apply promotion discount for Multiple category
	// Automation ID : TC319
	// Author: Robin
	// </summary>
	public void TC319_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {
		// Step 1. Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Prerequisite: To take valid promotion discounts from ERP for two categories
		String[] DiscountCategories = { UtilityTestData.CategoryDiamond, UtilityTestData.CategoryUncut };

		Map<String, Map<String, String>> PromotionDataMap = new HashMap<>();

		for (String DiscountCategory : DiscountCategories) 
		{
			Map<String, String> PromotionData = erpUtils.CreateDeleteValidPromotionDiscountAgreement(DiscountCategory);
			PromotionDataMap.put(DiscountCategory, PromotionData);
		}

		Map<String, String> PromotionData_Diamond = PromotionDataMap.get(UtilityTestData.CategoryDiamond);
		Map<String, String> PromotionData_Uncut   = PromotionDataMap.get(UtilityTestData.CategoryUncut);

		// Extract DIAMOND promotion values
		String PromotionType_Diamond     = PromotionData_Diamond.get("PromotionType");
		String MultiplyAmount_DiamondStr = PromotionData_Diamond.get("Amount");
		String Multiplier_DiamondStr     = PromotionData_Diamond.get("Multiplier");
		MultiplyAmount_DiamondStr = MultiplyAmount_DiamondStr.replaceAll("[^0-9.]", "");
		Multiplier_DiamondStr     = Multiplier_DiamondStr.replaceAll("[^0-9.]", "");

		// Extract UNCUT promotion values
		String PromotionType_Uncut     = PromotionData_Uncut.get("PromotionType");
		String MultiplyAmount_UncutStr = PromotionData_Uncut.get("Amount");
		String Multiplier_UncutStr     = PromotionData_Uncut.get("Multiplier");
		MultiplyAmount_UncutStr = MultiplyAmount_UncutStr.replaceAll("[^0-9.]", "");
		Multiplier_UncutStr     = Multiplier_UncutStr.replaceAll("[^0-9.]", "");

		double Range_Diamond  	= Double.parseDouble(MultiplyAmount_DiamondStr.trim());
		double Multiple_Diamond = Double.parseDouble(Multiplier_DiamondStr.trim());
		double Range_Uncut    = Double.parseDouble(MultiplyAmount_UncutStr.trim());
		double Multiple_Uncut = Double.parseDouble(Multiplier_UncutStr.trim());

		// Step 2. Click on Transaction button
		//To fetch current Gold rate 
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate(UtilityTestData.Purity22);
		double Weight_Diamond = Double.parseDouble(Multiplier_DiamondStr.replaceAll(",", "")) / CurrentGoldRate22k;
		double Weight_Uncut   = Double.parseDouble(Multiplier_UncutStr.replaceAll(",", ""))   / CurrentGoldRate22k;

		//Step 3. Select customer and Click on add to estimation button
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_CustomerNo, UtilityTestData.MenuBarOptions[5]);

		//Step 4. Scan SKUs and add to cart
		//Expected: Check calculation
		//Diamond
		Thread.sleep(1000);		
		SKUResult SkuDiamondSale = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_FromCounterDiamond,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_MetalTypeGold,
				Weight_Diamond,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_SKUCount);
		List<String> RequiredDiamondSku = SkuDiamondSale.PcsRateSKU;
		System.out.println("Diamond Selected SKUs: " + RequiredDiamondSku);
		appUtils.HamBurgerButtonClick("iconShop");	

		//Uncut
		Thread.sleep(1000);
		SKUResult SkuUncutSale = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_FromCounterUncut,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_MetalTypeGold,
				Weight_Uncut,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_SKUCount);
		List<String> RequiredUncutSku = SkuUncutSale.PcsRateSKU;
		System.out.println("Uncut Selected SKUs: " + RequiredUncutSku);
		appUtils.HamBurgerButtonClick("iconShop");	

		String ItemNameInItemDetailsPge = "";
		String GrossWeight              = "";
		String TotalWithoutTax          = "";
		String RGRate                   = "";

		List<String> AllSkuList = new ArrayList<>();
		AllSkuList.addAll(RequiredDiamondSku);
		AllSkuList.addAll(RequiredUncutSku);
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotal     = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double TotalDiamondAmount     = 0.0;
		double TotalUncutAmount       = 0.0;

		for (int i = 0; i < AllSkuList.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku = AllSkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);

			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight              = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax          = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			RGRate                   = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG");
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotal.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);

			double LineTotal = 0.0;
			if (TotalWithoutTax != null && !TotalWithoutTax.isEmpty()) {
				try {
					LineTotal = Double.parseDouble(TotalWithoutTax.replaceAll("[^\\d.]", "").trim());
				} catch (NumberFormatException nfe) {
					LineTotal = 0.0;
				}
			}

			if (i < RequiredDiamondSku.size()) {
				TotalDiamondAmount += LineTotal;
			} else {
				TotalUncutAmount += LineTotal;
			}
		}

		Thread.sleep(1000);
		double PromotionAmount_Diamond = 0.0;
		if (TotalDiamondAmount > 0.0) {
			double Amount = TotalDiamondAmount;
			if (Amount >= Multiple_Diamond) {
				int AmountMultiple = (int) ((Amount - Multiple_Diamond) / Multiple_Diamond) + 1;
				double MultiplierValue = AmountMultiple * Range_Diamond;
				PromotionAmount_Diamond = CurrentGoldRate22k * MultiplierValue;
			}
		}

		double PromotionAmount_Uncut = 0.0;
		if (TotalUncutAmount > 0.0) {
			double Amount = TotalUncutAmount;
			if (Amount >= Multiple_Uncut) {
				int AmountMultiple = (int) ((Amount - Multiple_Uncut) / Multiple_Uncut) + 1;
				double MultiplierValue = AmountMultiple * Range_Uncut;
				PromotionAmount_Uncut = CurrentGoldRate22k * MultiplierValue;
			}
		}

		double PromotionAmount = PromotionAmount_Diamond + PromotionAmount_Uncut;
		System.out.println("PromotionAmount_Diamond: " + PromotionAmount_Diamond);
		System.out.println("PromotionAmount_Uncut: " + PromotionAmount_Uncut);
		System.out.println("PromotionAmount TOTAL: " + PromotionAmount);

		List<Double> PromotionDiscountValue = new ArrayList<>();
		PromotionDiscountValue.add(PromotionAmount_Diamond);
		PromotionDiscountValue.add(PromotionAmount_Uncut);

		for(int i =0; i<PromotionDiscountValue.size();i++){
			double Total = Double.parseDouble(ProductTotal.get(i))-PromotionDiscountValue.get(i);
			ProductTotalList.add(Double.toString(Total));
		}

		// Transaction Page Verify
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		Map<String, String> GoldSkuTransactionDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(GoldSkuTransactionDataLine, ScannedSkuDataMap);

		// Step 5. Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(3000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		// Step 6.Click on apply promotion discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));

		Thread.sleep(3000);
		Map<String, String> TransactionDataLines = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLines.get("Subtotal");
		String TotalGrossWeight = TransactionDataLines.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLines.get("NetWeight");
		String Tax 		        = TransactionDataLines.get("Tax");
		String TotalAmount      = TransactionDataLines.get("TotalAmount");
		String NetTotal         = TransactionDataLines.get("NetTotal");
		String LinesCount       = TransactionDataLines.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLines, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		String ActualPromotionDiscountInBillingScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double PromotionDiscountBillingScreen = Double.parseDouble(ActualPromotionDiscountInBillingScreen);
		asrt.assertEquals(PromotionDiscountBillingScreen, PromotionAmount,1.0,"Discount value mismatch: Expected is "+PromotionAmount+" but got "+PromotionDiscountBillingScreen+" in Billing screen");

		System.out.println("SubTotal: "+SubTotal);
		System.out.println("TotalGrossWeight: "+TotalGrossWeight);
		System.out.println("TotalNetWeight: "+TotalNetWeight);
		System.out.println("Tax: "+Tax);
		System.out.println("TotalAmount: "+TotalAmount);
		System.out.println("NetTotal: "+NetTotal);
		System.out.println("LinesCount: "+LinesCount);

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String PromotionActualDiscount  = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		Thread.sleep(3000);
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax);

		// Step 7. Click on Save to estimation button
		// Step 8. save estimate
		//Expected : Verify performa invoice
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 

		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(3000);
		String ProFormaInvoiceName    = pdfUtils.DownloadAndRenameLatestPDF("TC319_ProformaInvoice");
		Thread.sleep(3000);
		String ProFormaInvoicePath    = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                    = Tax.replaceAll("[^\\d.]", "");
		String ExpectedTaxableValue   = SubTotal.replaceAll("[^\\d.]", "");
		String InvoiceTotal           = TotalAmount.replaceAll("[^\\d.]", "");
		String TotalAdjustment        = "";
		String AdditionalDiscount     = PromotionActualDiscount;
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				Double.toString(Double.parseDouble(ExpectedTaxableValue.replaceAll("[^\\d.]", "")) +  Double.parseDouble(AdditionalDiscount.replaceAll("[^\\d.]", ""))),
				AdditionalDiscount,
				ExpectedTaxableValue,  
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal.replaceAll("[^\\d.]", "")
				);

		// Step 9.Recall estimate from cashier screen
		// Step 10. Select estimate and click on recall estimation
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page");

		// Step 11.Select the Transaction Type as Sales 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC319_TransactionTypeSales);

		// Step 12.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		//Thread.sleep(3000);
		//appUtils.HandleSpecifyRateModalInRecall();

		// Step 13.Choose a sales representative(Not available after data refresh)
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();

		//Expected: Cashier Screen after recall Estimate 
		//*No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal
		//*Discount,*TAX,*Total Amount
		Thread.sleep(3000);
		Map<String, String> TransactionDataLinesCashier = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLinesCashier, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String ActualPromotionDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double PromotionDiscountCashier = Double.parseDouble(ActualPromotionDiscountInCashierScreen);

		asrt.assertEquals(PromotionDiscountCashier, PromotionAmount,1.0,"Discount value mismatch: Expected is "+PromotionAmount+" but got "+PromotionDiscountCashier+" in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInCashierScreen =0.00;
		ExpectedTotalWithoutTaxInCashierScreen = ExpectedTotalWithoutTaxInBillingPage-PromotionAmount;
		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;
		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)   +   Double.parseDouble(TaxInCashierScreen);
		ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		// Step 14.Click on the Amount
		// Step 15.Select a Payment method(Cash or Card)
		// Step 16.Post the Invoice
		//Expected: Check final invoice details
		//Save receipt id for future reference
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC319_SaleInvoice");
		Thread.sleep(3000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount    = Double.toString(Double.parseDouble(ExpectedTaxableValue.replaceAll("[^\\d.]", "")) + Double.parseDouble(AdditionalDiscount.replaceAll("[^\\d.]", "")));
		String TotalDiscount  = ActualPromotionDiscountInCashierScreen;
		String TotalQtyPcs    = LinesCount;
		String NetValue 	  = PaymentAmount;
		String TaxableValue   = ActualSubTotalInCashierScreen;
		String PdfInvoiceNo   = pdfUtils.SaleInvoicePdfValidationAfterPromotion(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				PromotionAmount,
				AllSkuList,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}

	// <summary>
	// Test Case Title : Apply coin discount
	// Automation ID : TC278
	// Author: Vishnu RR
	// </summary>

	public void TC278_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception{

		//Step1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step3. Select customer and Click on add to estimation button 
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4. Scan Gold SKU and click on Add to cart button
		//Expected: Check calculation
		//Fetching Board Rate 		
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		double CurrentGoldRate22k =appUtils.FetchCurrentBoardRate("22k");
		System.out.println("CurrentGoldRate22k :"+CurrentGoldRate22k);

		Thread.sleep(2000);
		appUtils.HamBurgerButtonClick("iconShop");
		double RequiredSaleQty = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_SaleAmount / CurrentGoldRate22k;

		SKUResult SkuListGold = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_FromCounterGold,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_MetalTypeGold,
				RequiredSaleQty,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278__SkuCount);

		List<String> SkuList = SkuListGold.PcsRateSKU;	
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		String SkuAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();  
		double SkuTotalAmount = Double.parseDouble(SkuAmount);
		double Multiples = SkuTotalAmount / ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_SaleAmount; //Find how many times 30,000 fits
		int FullMultiples = (int) Multiples;
		int SilverCoinQuantity = FullMultiples;
		System.out.println("SilverCoinQuantity: "+SilverCoinQuantity);

		//Step5. Search cgc or silver coin
		//Step6. Choose the required item
		//Step7. Click on add item after adding the purity
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_SilverGiftCOin);
		Thread.sleep(3000);
		base.ClickCondition(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_SilverCoin));	
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_Purity);
		Thread.sleep(5000);

		Thread.sleep(2000);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//Step8. Enter the weight (rate will be autocalculated)
		//Expected: Check calculation
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),Double.toString(SilverCoinQuantity));
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),"Enter");
		Thread.sleep(4000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		Thread.sleep(3000);
		String MakingRate = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*SilverCoinQuantity ;
		System.out.println("ExpectedMakingValue :"+ExpectedMakingValue);
		String ActualMakingValue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		asrt.assertEquals(Double.parseDouble(ActualMakingValue), ExpectedMakingValue,1.0, "Making value mismatch: Expected is "+ExpectedMakingValue+" but got "+ActualMakingValue+" in item details page");

		String RgRate = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue = SilverCoinQuantity*Double.parseDouble(RgRate);
		String ActualCvalue = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));
		asrt.assertEquals(Double.parseDouble(ActualCvalue), ExpectedCvalue,1.0, "Making value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");

		String ActualTotalSilverCoinAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		double ExpectedTotalSilverCoinAmount = ExpectedCvalue+ExpectedMakingValue;
		asrt.assertEquals(Double.parseDouble(ActualTotalSilverCoinAmount), ExpectedTotalSilverCoinAmount,1.0, "Making value mismatch: Expected is "+ExpectedTotalSilverCoinAmount+" but got "+ActualTotalSilverCoinAmount+" in item details page");

		String CGCQty = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value");
		String CGCTotal = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value");
		String CGCItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		ProductNames.add(CGCItemName);
		ProductQtyList.add(CGCQty);
		ProductTotalList.add(CGCTotal);

		//Step9. Click on add to cart
		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Add to Cart"));
		appUtils.HandleSpecifyRateModalInRecall();

		String SubTotalBeforeDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalAmount 			= TransactionDataLineInBillingPge.get("TotalAmount");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step10. Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		//Step11. Click on apply silver gift coin
		//Expected: Verify whether coin value deducts from sale amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Silver Gift Coin"));
		Thread.sleep(2000);
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_ComplementSilverCoin)));
		double ExpectedSaleAmount = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim());
		Thread.sleep(2000);
		System.out.println("ExpectedSaleAmount :"+ExpectedSaleAmount);
		String ActualSaleAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		System.out.println("ActualSaleAmount :"+ActualSaleAmount);

		asrt.assertEquals(Double.parseDouble(ActualSaleAmount), ExpectedSaleAmount,1.0,"Sale amount mismatch: Expected is "+ExpectedSaleAmount+" but got "+ActualSaleAmount+" in transaction page");

		String CGCDiscountItemName = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_CGCItemName ;
		String CGCDiscountQty = ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC273_CGCQty;
		String CGCDiscountTotal = "-" + CGCTotal;
		ProductNames.add(CGCDiscountItemName);
		ProductQtyList.add(CGCDiscountQty);
		ProductTotalList.add(CGCDiscountTotal);

		//In billing screen, *No of Product lines *Displayed Item Name *Displayed Quantity *Discount value *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String Discount 			= TransactionDataLine.get("Discount");
		String TaxAmt 					= TransactionDataLine.get("Tax");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount,ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		Thread.sleep(5000);;
		String ActualSubTotalAfterDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();
		double ExpectedSubtotal = Double.parseDouble(SubTotalBeforeDiscount) + Double.parseDouble(ProductTotalList.get(2));
		asrt.assertEquals(Double.parseDouble(ActualSubTotalAfterDiscount),ExpectedSubtotal,1.00,"Subtotal after applying coin discount mismatch: Expected is "+ExpectedSubtotal+" but found "+ActualSubTotalAfterDiscount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemNme = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemNme.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();	
			SubTotalSum = SubTotalSum + Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = TaxAmt.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		String TaxableValue = Double.toString(Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1)));
		double ExpectedTax = (Double.parseDouble(ProductTotalList.get(0))+Double.parseDouble(ProductTotalList.get(1)))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge),1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble( base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");
		String TotalGrossWt = String.format("%.3f", Double.parseDouble(ProductQtyList.get(0)) + Double.parseDouble(ProductQtyList.get(1)));

		//Step12. Click on Save to estimation button
		//Step13. save esimate
		//Expected Result: Verify the Invoice details
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(5000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);

		//Expected Result: Verify the Invoice details
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC278_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;
		String Gst                 = Double.toString(ActualTax);
		String InvoiceTotal = Double.toString(Double.parseDouble(TaxableValue)+(ActualTax));
		String AdjustmentAmount = ProductTotalList.get(1);
		String NetTotalPdf 		   = Double.toString(Double.parseDouble(InvoiceTotal) - Double.parseDouble(AdjustmentAmount));
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				AdjustmentAmount,
				NetTotalPdf,
				"");

		//Step14. Recall estimate from cashier screen
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");

		//Step15. Select estimate and  click on save to estimate button
		//Expected Result: Verify whether the recalled item is the same as the item in the cart.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		//Step16. Select the Transaction Type as Sales 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_TransactionTypeSales);
		Thread.sleep(5000);

		//Step17. Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		appUtils.HandleSpecifyRateModalInRecall();

		//Step18. Choose a sales representative
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Expected Result:Note:Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX *Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TaxInCashierScreen = TransactionDataLineInCashierScreen.get("Tax"); 
		String ActualLinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is " + ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		double SubTotalSumInCashierScreen = 0.00;
		for (int i = 0; i < AllProductRowsInCashierScreen.size(); i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText(); String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got " + ActualItemNameInCashierScreen + " in cashier screen");
			asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in cashier screen");

			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", ""); String ExpectedQuantityInCashierScreen = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen); double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen, ExpectedQtyInCashierScreen, 1.000, "Quantity mismatch: Expected is " + ExpectedQtyInCashierScreen + " but got " + ActualQtyInCashierScreen + " in cashier screen");

			List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim(); String TotalWithoutTaxValueInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSumInCashierScreen += Double.parseDouble(TotalWithoutTaxValueInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen), Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), 1.0, "TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierScreen + " but got " + ActualTotalWithoutTaxInCashierScreen + " in cashier screen");
		}

		String ExpectedSubTotalInCashierScreen = String.format("%.2f", SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen), Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0, "Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen + " in cashier screen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim(); double ActualTaxValueInCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxValueInCashierScreen = (Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1))) * 0.03; String ExpectedTaxInCashierScreen = String.format("%.2f", ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen), 1.0, "Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen + " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen) + Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountValueInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountValueInCashierScreen, ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: " + ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountValueInCashierScreen + " in cashier screen");

		//Step19. Click on the Amount
		//Step20. Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step21. Post the Invoice
		//Expected Result: save receipt id for future reference
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC278_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalNetWt = TotalGrossWt;
		String InvoiceNo = pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				Discount,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_TotalQtyPcs,
				TotalNetWt,
				TotalGrossWt,
				TaxableValue,
				NetTotalPdf,
				PaymentAmount,
				AdjustmentAmount,
				SkuList,
				ScannedSkuDataMap
				);

		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC278_CustomerNo, InvoiceNo, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_RequiredReturnProduct);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Generate Silverinvoice and apply normal silver gift coin
	// Automation ID : TC276
	// Author:Benson
	// </summary>
	public void TC276_ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount() throws Exception {

		//Step 1.Login to POS
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer and click on add to estimation button
		appUtils.SearchByCustomerID(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 4.Scan a Silver SKU and click on Add to cart button
		//Expected: Check calculation
		SKUResult SkuSilver = appUtils.GetOGSaleSku(
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_TranferType,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_FromCounterSilver,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_MetalTypeSilver,
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_SilverSkuQty,  
				ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_SkuCount);
		List<String> SkuList = SkuSilver.PcsRateSKU;

		appUtils.HamBurgerButtonClick("iconShop");

		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		Map<String, Double> ValuesToCalculateSilverCoin = new LinkedHashMap<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			String ProductName      = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			String ExactProductName = ProductName.substring(ProductName.indexOf("-") + 1).trim();
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		//Billing screen validation-sale
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalWithoutTax                  = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);
		int TotalSilverCoinGrossWeight =ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_SilverCoinGrossWeight;

		//Step 5.Search CGC or silver coin
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_SilverCoin);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		//Step 6.Choose the required item(Silvercoin)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "SILVERCOIN"));

		//Step 7.Click on add item after adding the purity
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_SilverCoinPurity);
		//Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));
		if (base.WaitForElementToBePresentWithTimeouts(LoginPageObj.Btn_SignInButton("OK"),5)) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8.Enter the weight (rate will be auto calculated )
		//Expected: Check calculation
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), Integer.toString(TotalSilverCoinGrossWeight));
		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		String MakingRate          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
		double ExpectedMakingValue = Double.parseDouble(MakingRate)*TotalSilverCoinGrossWeight ;
		String ActualMakingValue   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
		asrt.assertEquals(Double.parseDouble(ActualMakingValue), ExpectedMakingValue,1.0, "Making value mismatch: Expected is "+ExpectedMakingValue+" but got "+ActualMakingValue+" in item details page");

		String RgRate              = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		double ExpectedCvalue      = TotalSilverCoinGrossWeight*Double.parseDouble(RgRate);
		String ActualCvalue        = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","CValue"));
		asrt.assertEquals(Double.parseDouble(ActualCvalue), ExpectedCvalue,1.0, "Making value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");

		String ActualTotalSilverCoinAmount   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		double ExpectedTotalSilverCoinAmount = ExpectedCvalue+ExpectedMakingValue;
		asrt.assertEquals(Double.parseDouble(ActualTotalSilverCoinAmount), ExpectedTotalSilverCoinAmount,1.0, "Making value mismatch: Expected is "+ExpectedTotalSilverCoinAmount+" but got "+ActualTotalSilverCoinAmount+" in item details page");

		ProductNames.add(ItemName);
		ProductQtyList.add(Integer.toString(TotalSilverCoinGrossWeight));
		ProductTotalList.add(ActualTotalSilverCoinAmount);

		//Step 9.Click on add to cart
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(1000); // Short delay for modal to close
			}
		} catch (Exception e) {}

		//Check calculation Note:Billing Screen  *No of Product lines  *Displayed Item Name  *Displayed Quantity *Displayed TOTAL (without Tax)
		//*Displayed Subtotal	 *TAX*Total Amount
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		                        = TransactionDataLineInBillingPge.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Tax 					                        = TransactionDataLineInBillingPge.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount 			                        = TransactionDataLineInBillingPge.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String TotalGrossWeight                             = TransactionDataLineInBillingPge.get("GrossWeight");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10.Go to discounts
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 11.Click on apply silver gift coin
		//Expected: Verify whether coin value deducts from sale amount
		base.ClickCondition(LoginPageObj.Edt_AlertText("Apply Silver Gift Coin"));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_ComplementSilverCoin)));
		double ExpectedSaleAmount = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim());
		Thread.sleep(10000);
		String ActualSaleAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSaleAmount), ExpectedSaleAmount,1.0,"Sale amount mismatch: Expected is "+ExpectedSaleAmount+" but got "+ActualSaleAmount+" in transaction page");

		ProductNames.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_ComplementSilverCoin);
		ProductQtyList.add(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_ComplementSilverCoinQty);
		ProductTotalList.add(Double.toString(-Double.parseDouble(ActualTotalSilverCoinAmount)));
		Thread.sleep(1000);

		//Step 12. Click on Save to estimation button
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		//Step 13. save estimate
		//Expected: Verify the Invoice details
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC275_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String InvoiceTotal        = TotalAmount;
		String TaxableValue        = SubTotal;
		String NetTotal            = Double.toString(Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim())-Double.parseDouble(ActualTotalSilverCoinAmount.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment     = ActualTotalSilverCoinAmount;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal,
				"");

		//Step 14.Recall estimate from cashier screen
		//Step 15. Select estimate and click on save to estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		//Step 16.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),"Sales");

		//Step 17.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		appUtils.HandleSpecifyRateModalInRecall();

		//Step 18.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String NetWeight      = TransactionDataLineInCashierScreen.get("NetWeight"); 
		double TotalNetWeight = Double.parseDouble(NetWeight)+TotalSilverCoinGrossWeight;

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		//Step 19.Click on the Amount
		//Step 20.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 21.Post the Invoice
		//Expected: save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice   = pdfUtils.DownloadAndRenameLatestPDF("TC276_FinalSaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalDiscount  = "";
		String TotalQtyPcs    = Integer.toString(SkuList.size());
		String NetValue       = PaymentAmount;
		String InvoiceNo      = pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				TotalDiscount,
				TotalQtyPcs,
				Double.toString(TotalNetWeight),
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				TotalAdjustment,
				SkuList,
				ScannedSkuDataMap
				);

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlow(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC276_CustomerNo, InvoiceNo, ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData.TC275_RequiredReturnProduct);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}

}
