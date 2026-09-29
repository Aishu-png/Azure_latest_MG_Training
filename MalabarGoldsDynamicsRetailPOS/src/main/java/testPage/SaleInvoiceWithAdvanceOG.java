
package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_TestData;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.NormalSaleGoldandSilver_TestData;
import testData.NormalSalesReturnGoldSilverReturnSaleCounter_TestData;
import testData.OldPurchaseExchangeOwnorOtherGoldorSilverSale_TestData;
import testData.SaleInvoiceWithAdvanceOG_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.SKUResult;
import utilPack.AppUtilities.SKUTypeResult;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;


public class SaleInvoiceWithAdvanceOG extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	MathUtilities mathUtils;
	PdfUtilities pdfUtils;
	ErpUtilities erpUtils;
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj = new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	SaleInvoiceWithAdvanceOG_TestData SaleInvoiceWithAdvanceOGTestData = new SaleInvoiceWithAdvanceOG_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj=new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj= new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();


	public SaleInvoiceWithAdvanceOG(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base);
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
	}


	// <summary>
	// Test Case Title : Sale with advance and OG and multiple gold item
	// Automation ID : TC155
	// Author: Vishnu Manoj K
	// </summary>
	public void TC155_SaleInvoiceWithAdvanceOG()
			throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		double ErpGoldRateFor24K = appUtils.FetchCurrentBoardRate(UtilityTestData.Purity24);
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 2: Click on Transaction button
		// Step : Pre-Requisite
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				SaleInvoiceWithAdvanceOGTestData.TC155_CustomerNo,
				SaleInvoiceWithAdvanceOGTestData.TC155_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		Map<String, String> SkuDetailsRatePick = new LinkedHashMap<>();
		List<String> PurityOfItemsRatePick = new ArrayList();
		List<Double> AllRatesRatePick = new ArrayList<>();
		List<String> SkuListGoldRatePick = appUtils.SelectMultipleSKUs(
				SaleInvoiceWithAdvanceOGTestData.TC155_ProductCount,
				SaleInvoiceWithAdvanceOGTestData.TC155_TransferType,
				SaleInvoiceWithAdvanceOGTestData.TC155_FromCounter,
				SaleInvoiceWithAdvanceOGTestData.TC155_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");	
		// Transaction
		for (int i = 0; i < SkuListGoldRatePick.size(); i++)

		{
			String CurrentGoldSku = SkuListGoldRatePick.get(i);
			appUtils.SKUIngridentTableValues(CurrentGoldSku.toString(), (i + 1), SkuDetailsRatePick);
			PurityOfItemsRatePick.add(SkuDetailsRatePick.get("SKU_" + (i + 1) + "_Purity"));
			for (int ItemIndex = 1;; ItemIndex++) {
				String Rate = "SKU" + (i + 1) + "_ITEM" + ItemIndex + "_RATE";

				if (SkuDetailsRatePick.containsKey(Rate))
				{
					try
					{
						double SkuRate = Double.parseDouble(SkuDetailsRatePick.get(Rate));
						AllRatesRatePick.add(SkuRate);
					} catch (NumberFormatException e) {
						System.out.println("Rate not fetched");
					}
				} else {
					break;
				}
			}

			SkuDetailsRatePick.put("SKU_" + (i + 1) + "_AllRates", AllRatesRatePick.toString());
		}
		// For conversion of Rate

		String ItemPurity = PurityOfItemsRatePick.get(0);
		double ExchangeRate = AllRatesRatePick.get(0);
		double ExpectedGoldRate22k = 0;
		double ExpectedGoldRate18k = 0;
		if (ItemPurity.toUpperCase().contains("18")) {
			double ConvertedGoldRate = (ExchangeRate * 22) / 18;
			ExpectedGoldRate22k = Math.round(ConvertedGoldRate);
			ExpectedGoldRate18k = ExchangeRate;

		} else if (ItemPurity.toUpperCase().contains("24")) {
			// Convert from 24K to 22K and 18K
			ExpectedGoldRate22k = Math.round((ExchangeRate * 22) / 24);
			ExpectedGoldRate18k = (ExchangeRate * 18) / 24;

		} else {
			// Assume default is 22K
			ExpectedGoldRate22k = Math.round(ExchangeRate);
			ExpectedGoldRate18k = (ExchangeRate * 18) / 22;
		}

		System.out.print("ExpectedGoldRate22k: "+ExpectedGoldRate22k);
		appUtils.VoidTransaction();

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(
				SaleInvoiceWithAdvanceOGTestData.TC155_CustomerNo,
				UtilityTestData.MenuBarOptions[5]);

		// Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		// Step 6: click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		// Step 7:  Select advance and Click on redeem button
		// Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.Terminal);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);
		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		String MetalRate = null;
		String BalanceGoldQty = null;
		if (base.isExists(LoginPageObj.Ele_ErrorMessage("win-itembox win-selected"))) {
			MetalRate =	base.GetText(NormalSaleGoldAndSilverObj.Sel_TheMetalRate("win-itembox win-selected","dataListLine","Metal Rate"));
			BalanceGoldQty =	base.GetText(NormalSaleGoldAndSilverObj.Sel_TheMetalRate("win-itembox win-selected","dataListLine","Balance gold qty"));
		}
		System.out.println("MetalRate: "+MetalRate);
		System.out.println("BalanceGoldQty: "+BalanceGoldQty);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		// Step 8: Enter OTP
		// Step 9: Scan Gold SKU
		// Expected : Check calculation
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				SaleInvoiceWithAdvanceOGTestData.TC155_TransferType,
				SaleInvoiceWithAdvanceOGTestData.TC155_FromCounter,
				SaleInvoiceWithAdvanceOGTestData.TC155_MetalType,
				2,
				SaleInvoiceWithAdvanceOGTestData.TC155_ProductCount);
			List<String> SkuList = SkuGoldSale.PcsRateSKU;
			System.out.println("Gold Selected SKUs: " + SkuList);
			appUtils.HamBurgerButtonClick("iconShop");	
			
//		List<String> SkuList = appUtils.SelectMultipleSKUs(
//				SaleInvoiceWithAdvanceOGTestData.TC155_ProductCount,
//				SaleInvoiceWithAdvanceOGTestData.TC155_TransferType,
//				SaleInvoiceWithAdvanceOGTestData.TC155_FromCounter,
//				SaleInvoiceWithAdvanceOGTestData.TC155_MetalType);
//		appUtils.HamBurgerButtonClick("iconShop");  

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(SaleInvoiceWithAdvanceOGTestData.TC155_DepositAdjustment);
		ProductQtyList.add(SaleInvoiceWithAdvanceOGTestData.TC155_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		String SkuPurityReturn		="22K";
		erpUtils.ResetRemainingReturnWeight();
		double ActualRgRateInItemDetailsPge =0.00;
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			ActualRgRateInItemDetailsPge = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG"));
			String Purity = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Purity");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber,BalanceGoldQty,SkuPurityReturn,MetalRate,String.valueOf(ErpGoldRateFor24K));
		}

		// Note:Billing Screen No of Product lines, Displayed Item Name, Displayed
		// Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total
		// Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

		double SubTotalSum = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			//			if (i == 1) {
			//				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//
			//				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			//
			//			} else {
			//				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			//			}
			String Actual = ItemName.get(i).getText().trim();
			String Expected = ProductNames.get(i).trim();
			asrt.assertTrue(Expected.contains(Actual),"Item name mismatch: Expected '" + Expected + "' to contain actual '" + Actual + "in billing page");
			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);

			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSum = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));

			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);

		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		// Step  10: Select OG
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		// Step  11: Click on OG own
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));

		// Step  12. Select configuration
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),SaleInvoiceWithAdvanceOGTestData.TC155_Purity22);

		// Step  13. Click on Add item button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(10000);
		List<WebElement> StoneErrorMessage = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));
		if (!StoneErrorMessage.isEmpty()) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
		}	

		// Step  14. Select QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step  15. Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step  16. Enter piece value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),SaleInvoiceWithAdvanceOGTestData.TC155_GrossPieces);

		// Step  17. Enter gross weight
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),SaleInvoiceWithAdvanceOGTestData.TC155_GrossWeight);

		// Step  18. Click on calculate button
		// Expected: Check OG calculation
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(10000);
		double ExpectedOGPurchaseRateInItemDetailsPge = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_OGRate("column4 textRight","gridcell")));		

		asrt.assertEquals(ExpectedOGPurchaseRateInItemDetailsPge, ExpectedGoldRate22k,1,"OG Purchase Rate mismatch: Expected value is "+ExpectedOGPurchaseRateInItemDetailsPge+" but got "+ExpectedGoldRate22k+" in item details page");

		String ExpectedNetWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));
		double NetWeightInItemDetailsPge = Double.parseDouble(ExpectedNetWeightInItemDetailsPge);
		double ExpectedAmountInItemDetailsPge = Math.round(NetWeightInItemDetailsPge * ExpectedOGPurchaseRateInItemDetailsPge * 100.0) / 100.0;
		double ActualAmountInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")));

		asrt.assertEquals(ActualAmountInItemDetailsPge,ExpectedAmountInItemDetailsPge,1,"Cvalue mismatch: "+ExpectedAmountInItemDetailsPge+" but got "+ActualAmountInItemDetailsPge+" in item details page");		

		String ExpectedGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String ExpectedTotalAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));
		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ExpectedItemNameInItemDetailsPge = ItemName.substring(ItemName.indexOf("-") + 2);
		String ExpectedGrossWeight = String.valueOf(Double.parseDouble(ExpectedGrossWeightInItemDetailsPge));
		String ExpectedTotalAmount = String.valueOf(Double.parseDouble(ExpectedTotalAmountInItemDetailsPge));

		// Step  19. Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		Thread.sleep(10000);

		Map<String, String> BillingPageValue = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);

		System.out.println("Item1 Name = " + BillingPageValue.get("Item1Name"));
		System.out.println("Item1 Quantity = " + BillingPageValue.get("Item1Quantity"));
		System.out.println("Item1 TotalWithoutTax = " + BillingPageValue.get("Item1TotalWithoutTax"));
		System.out.println("Item1 SalesRep = " + BillingPageValue.get("Item1SalesRep"));

		System.out.println("Item2 Name = " + BillingPageValue.get("Item2Name"));
		System.out.println("Item2 Quantity = " + BillingPageValue.get("Item2Quantity"));
		System.out.println("Item2 TotalWithoutTax = " + BillingPageValue.get("Item2TotalWithoutTax"));
		System.out.println("Item2 SalesRep = " + BillingPageValue.get("Item2SalesRep"));

		System.out.println("Item3 Name = " + BillingPageValue.get("Item3Name"));
		System.out.println("Item3 QuantiSty = " + BillingPageValue.get("Item3Quantity"));
		System.out.println("Item3 TotalWithoutTax = " + BillingPageValue.get("Item3TotalWithoutTax"));
		System.out.println("Item3 SalesRep = " + BillingPageValue.get("Item3SalesRep"));

		List<String> TotalWithoutTax = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));

		// Step 20: save estimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC155_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		for (String Ele : TotalWithoutTax) {
			System.out.println(Ele);
		}
		// GST
		String Gst = Double.toString(Double.parseDouble(TotalWithoutTax.get(1).replaceAll("[^\\d.]", "").trim()) * 0.03);
		// Taxable Value
		String TaxableValue = Double.toString(Double.parseDouble(TotalWithoutTax.get(1).replaceAll("[^\\d.]", "").trim()));
		// Invoice Total
		String InvoiceTotal = Double.toString(Double.parseDouble(TotalWithoutTax.get(1).replaceAll("[^\\d.-]", "").trim())+ Double.parseDouble(Gst)+ Double.parseDouble(TotalWithoutTax.get(2).replaceAll("[^\\d.-]", "").trim())  );
		// Adjustment
		String TotalAdjustment = TotalWithoutTax.get(0).replaceAll("[^\\d.]", "").trim();
		String TotalAmt = "";
		NetTotal=Double.toString(Double.parseDouble(InvoiceTotal)-Double.parseDouble(TotalAdjustment));
		System.out.println(Gst + " gsr " + TaxableValue + " taxab " + InvoiceTotal + " invoice " + TotalAdjustment + " NetTotal " + NetTotal);

		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(ProFormaInvoicePath, 
				TaxableValue, 
				Gst, 
				InvoiceTotal, 
				TotalAdjustment, 
				NetTotal, 
				TotalAmt);

		// Step 21: Recall estimate from cashier screen
		// Step 22: Select estimate and click on recall estimate button
		// Expected: Verify whether the recalled item is the same as the item in the cart.
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		// Step 23: Select transaction type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),SaleInvoiceWithAdvanceOGTestData.TC155_TransactionType1);

		// Step 24: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 25: Choose a sales representative 
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		// Step 26: Click on amount 0.00
		// Step 27: Post exchange invoice
		// Expected: Verify exchange invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(2000);
		base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",ExpectedOGPurchaseRateInItemDetailsPge);
		Map<String, String> OgPurchaseBill = pdfUtils.OGPdfValidation("Adjustment");
		String GrossWeightFromInvoice = OgPurchaseBill.get("GrossWt1");
		String GrossWeight = String.valueOf(Double.parseDouble(GrossWeightFromInvoice));	
		String NetWeightFromInvoice = OgPurchaseBill.get("NetWt1");
		String GoldRateFromInvoice = OgPurchaseBill.get("Rate1");
		String TotalAmountFromInvoice = OgPurchaseBill.get("TotalAmount1");
		String TotalAmountExchange = String.valueOf(Double.parseDouble(TotalAmountFromInvoice));	
		String PaymentDetailsFromInvoice = OgPurchaseBill.get("PaymentDetails");
		String PaymentDetails = String.valueOf(Double.parseDouble(PaymentDetailsFromInvoice));
		String InvoiceNo = OgPurchaseBill.get("InvoiceNumber");

		asrt.assertEquals(Double.parseDouble(GrossWeight),Double.parseDouble(SaleInvoiceWithAdvanceOGTestData.TC155_GrossWeight),1, "Gross Weight mismatch: "+SaleInvoiceWithAdvanceOGTestData.TC155_GrossWeight+" but got "+GrossWeight+" in Invoice Pdf");		
		asrt.assertEquals(Double.parseDouble(NetWeightFromInvoice),Double.parseDouble(ExpectedNetWeightInItemDetailsPge),1,"Net Weight mismatch: "+ExpectedNetWeightInItemDetailsPge+" but got "+NetWeightFromInvoice+" in Invoice Pdf");	
		asrt.assertEquals(Double.parseDouble(GoldRateFromInvoice),Double.parseDouble(ExpectedOGPurchaseRateFromItemDetailsPge),1,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in Invoice Pdf");	
		asrt.assertEquals(Double.parseDouble(TotalAmountExchange),Double.parseDouble(ExpectedTotalAmountInItemDetailsPge),1,"Total amount mismatch: "+ExpectedTotalAmountInItemDetailsPge+" but got "+Double.parseDouble(TotalAmountExchange)+" in Invoice Pdf");	
		asrt.assertEquals(Double.parseDouble(PaymentDetails),Double.parseDouble(ExpectedTotalAmountInItemDetailsPge),1,"Payment details mismatch: "+Double.parseDouble(ExpectedTotalAmountInItemDetailsPge)+" but got "+PaymentDetails+" in Invoice Pdf");

		//Step 28: Again recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Step 29: Select transaction type as sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), SaleInvoiceWithAdvanceOGTestData.TC155_TransactionTypeSale);

		//Step 30: Click on process estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 31: Choose a sales representative
		Map<String, String> SaleCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		System.out.println("SaleCashierScreen: " + SaleCashierScreen);
		String PaymentsRaw = SaleCashierScreen.get("Payments");
		String Payments = PaymentsRaw.replaceAll("[^\\d.]", "");

		//Step 32: Click on amount
		//Step 33: Select payment method(cash/card)
		//Step 34: Post exchange invoice
		//Expected: Note:-Cashier Screen after recall*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX *Total Amount
		Map<String, String> CashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		System.out.println("CashierScreen: " + CashierScreen);
		for (Map.Entry<String, String> Entry : TransactionDataLine.entrySet()) {
			String Key = Entry.getKey();
			if (Key.equalsIgnoreCase("Payments") || Key.equalsIgnoreCase("NetTotal")) { continue;}
			String ExpectedValue = Entry.getValue();
			String ActualValue = CashierScreen.get(Key);
			System.out.println("Verifying Field: " + Key + " | Expected: " + ExpectedValue + " | Actual: " + ActualValue);
			Assert.assertEquals( ActualValue,ExpectedValue,"Mismatch at Field: " + Key + "  Expected: " + ExpectedValue + ", but got: " + ActualValue);
		}

		String PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 28: Post exchange invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC155_TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ProductTotalList.get(1);
		String TotalDiscount = Discount;
		String TotalQtyPcs = Integer.toString(SkuList.size());
		String TotalDiamond = "";
		String PaymentHDFCCard = PaymentAmountForSale;
		String Adjustment =Payments;
		String NetValue =String.valueOf(Double.parseDouble(Payments.replaceAll("[^\\d.]", "")) + Double.parseDouble(PaymentAmountForSale.replaceAll("[^\\d.]", "")));
		String TotalGrossWeight = ProductQtyList.get(1);
		System.out.println("InvoicePdfPath: " + InvoicePdfPath);
		System.out.println("GrossAmount: " + GrossAmount);
		System.out.println("TotalDiscount: " + TotalDiscount);
		System.out.println("TotalQtyPcs: " + TotalQtyPcs);
		System.out.println("TotalDiamond: " + TotalDiamond);
		System.out.println("NetValue: " + NetValue);
		System.out.println("PaymentHDFCCard: " + PaymentHDFCCard);
		System.out.println("Adjustment: " + Adjustment);
		System.out.println("TotalGrossWeight: " + TotalGrossWeight);
		System.out.println("AdvanceAmount: " + AdvanceAmount);
		System.out.println("TotalAdjustment: " + TotalAdjustment);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationAdvanceDoubleAdjustment(InvoicePdfPath, GrossAmount,
				TotalDiscount, TotalDiamond, TotalQtyPcs, TotalNetWeight, TotalGrossWeight, TaxableValue, NetValue,
				PaymentHDFCCard, Adjustment, TotalAdjustment,  AdvanceAmount, SkuList, ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();



	}


	// <summary>
	// Test Case Title : Sale with offer advance and multiple OD -multiple category
	// Automation ID : TC158
	// Author: Vishnu RR
	// </summary>
	public void TC158_SaleInvoiceWithAdvanceOG()
			throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);


		//Prerequisite: Customer must have offer Advance
		Thread.sleep(1000);
		Map<String, String> FirstOfferAdvance = appUtils.CreateWeightBookedOfferAdvance
				(SaleInvoiceWithAdvanceOG_TestData.TC158_CustomerID,
						SaleInvoiceWithAdvanceOG_TestData.TC158_Pieces, 
						SaleInvoiceWithAdvanceOG_TestData.TC158_OfferGrossWeight1, 
						UtilityTestData.Company[0], 
						UtilityTestData.Location[0],
						SaleInvoiceWithAdvanceOG_TestData.TC158_DepositPercent);

		String FirstOrderId = FirstOfferAdvance.get("OrderId"); // remove first word from order id 
		String BoardRate22k    = FirstOfferAdvance.get("FixedGoldRate");
		double FirstOfferAmount = Double.parseDouble(FirstOfferAdvance.get("WillingToPayAmount"));
		System.out.println("BoardRate22k:- "+BoardRate22k);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(SaleInvoiceWithAdvanceOG_TestData.TC158_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//  Step 5. Click on customer adjustment button
		//Step 6. click on offer advance settlement
		//Step 7. Select advance and Click on redeem button
		//Expected Result : The balance amount and adjustment amount for the selected advances should be shown correctly
		//Step 8. Enter OTP
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"))).click();

		double AdvanceBalance1 = 0;
		String AdvanceBalanceAmount1 = "";
		String MetalRateFetch = "";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"), FirstOfferAdvance.get("OrderId")); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("option", FirstOfferAdvance.get("Orderid")));
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",FirstOfferAdvance.get("Orderid")));
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));

		AdvanceBalance1 = Double.parseDouble(base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount")));
		System.out.println("Balance Amount from the box " + AdvanceBalance1);

		// Balance Amount in the Offer advance page 
		Thread.sleep(2000);
		AdvanceBalanceAmount1 = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine", "Balance amount"));
		System.out.println("Advance Amt = " + AdvanceBalanceAmount1);

		// Balance Amt in the Offer advance page 
		Thread.sleep(2000);
		AdvanceBalance1 = Double.parseDouble(base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount")));
		System.out.println("Balance Amount from the box " + AdvanceBalance1);

		MetalRateFetch = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_MetalRate("column3", "h4 ellipsis cell", "Metal Rate"));
		System.out.println("Metal Rate = " + MetalRateFetch);

		// Selecting the list order id from the offer advance list 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("column1", "h4 ellipsis cell", "1"));
		String BalanceAmtInAdvanceAdj1 = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount"));
		String DefaultAdjAmt1 = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		double Adjustment = AdvanceBalance1 * 0.1;
		String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		base.clearData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"), Double.toString(Adjustment));
		String AdjustmentAmount      =base.GetAttribte(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("InputAmount"),"value");

		// Click on cart button
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		double Adjustment22K = Double.parseDouble(AdjustmentAmount);
		double Quantity22k   = (Adjustment22K/Double.parseDouble(AdvanceRate))/SaleInvoiceWithAdvanceOG_TestData.TC158_DepositPercent;
		double MetalRate18k = (Double.parseDouble(AdvanceRate)*18)/22;
		double Quantity18k  = (Adjustment22K/MetalRate18k)/SaleInvoiceWithAdvanceOG_TestData.TC158_DepositPercent;

		asrt.assertEquals(BalanceAmtInAdvanceAdj1, DefaultAdjAmt1,"Mismatch in balance amount " +BalanceAmtInAdvanceAdj1+" and adjusted amount " +DefaultAdjAmt1+" displayed in advance adjustment page"); 

		//Expected Result:Average Calculation  
		String ErpGoldRateFor24K= erpUtils.SetMetalRateInERP(AdvanceRate, "22k"); //ErpGoldRateFor24K
		Thread.sleep(120000); //Wait needed for ERP Gold Rate to Change
		System.out.println("ErpGoldRateFor24K1:::" + ErpGoldRateFor24K);

		double Current22kRate = (Double.parseDouble(ErpGoldRateFor24K)*22)/24;
		double Current18kRate = (Double.parseDouble(ErpGoldRateFor24K)*18)/24;

		List<String> SelectedGoldSkuList = new ArrayList<>();
		int SkuCounter = 1;

		// Step 9 : Scan multiple SKU's and click on add to cartGold SKUs
		SKUResult SkuOGSaleGold = appUtils.GetOGSaleSku(
				SaleInvoiceWithAdvanceOG_TestData.TC158_TransferType,
				SaleInvoiceWithAdvanceOG_TestData.TC158_FromCounter,
				SaleInvoiceWithAdvanceOG_TestData.TC158_MetalType,
				SaleInvoiceWithAdvanceOG_TestData.TC158_SkuQtySale,
				//				Quantity22k,
				SaleInvoiceWithAdvanceOG_TestData.TC158_SkuGoldCount);

		appUtils.HamBurgerButtonClick("iconShop");

		SKUResult SkuOGSaleSilver = appUtils.GetOGSaleSku(
				SaleInvoiceWithAdvanceOG_TestData.TC158_TransferType,
				SaleInvoiceWithAdvanceOG_TestData.TC158_FromCounter1,
				SaleInvoiceWithAdvanceOG_TestData.TC158_MetalType1,
				Quantity22k,
				SaleInvoiceWithAdvanceOG_TestData.TC158_SkuGoldCount);

		List<String> SkuList = SkuOGSaleGold.PcsRateSKU;
		SkuList.addAll(SkuOGSaleSilver.PcsRateSKU);

		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		ProductNames.add(SaleInvoiceWithAdvanceOG_TestData.TC158_AdvanceName);
		ProductQtyList.add(SaleInvoiceWithAdvanceOG_TestData.TC158_DepositAdjustmentQty);
		ProductTotalList.add(Double.toString(Adjustment));

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);

			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));

			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);

			//Expected Result:Check Calculations
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber, String.valueOf(Quantity22k), "22k",AdvanceRate , ErpGoldRateFor24K);
			SkuCounter++;
		}
		String CstProdQty = SaleInvoiceWithAdvanceOG_TestData.TC158_GrossPieces;
		String ExpectedAdvanceName = SaleInvoiceWithAdvanceOG_TestData.TC158_AdvanceName;

		//Expected Result:Billing Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal,TAX,Total Amount
		String RateKey = "SKU"+  SaleInvoiceWithAdvanceOG_TestData.TC158_ProductCount+ "_ITEM"+  SaleInvoiceWithAdvanceOG_TestData.TC158_ProductCount+ "_RATE";
		List<WebElement> ItemNamesBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TaxBillingPge = TransactionDataLine.get("Tax");
		String InvoiceTotal = TransactionDataLine.get("InvoiceTotal");
		String TaxableValue = TransactionDataLine.get("TaxableValue");
		String AmountDue = TransactionDataLine.get("AmountDue");
		String LinesCount = TransactionDataLine.get("LinesCount");
		String TotalGrossWt = TransactionDataLine.get("TotalGrossWtSku");
		String ExpectedNetValue = ScannedSkuDataMap.get("SKU_"
				+ SaleInvoiceWithAdvanceOG_TestData.TC158_ProductCount
				+ "_TotalRGWeight");
		String Linecount = String.valueOf(SaleInvoiceWithAdvanceOG_TestData.TC158_SkuGoldCount);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		// Step 10: Select OD
		// Step 11: Click on OD own
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"OD");
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		//12. Select configuration
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.OldDiamondProduct));

		//13. Click on Add item button
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		Thread.sleep(3000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),SaleInvoiceWithAdvanceOG_TestData.TC158_Purity);

		//To Handle the Unexpected Error
		//			WebDriverWait Waits = new WebDriverWait(driver, Duration.ofSeconds(8));
		//			By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		//			By OkBtn = LoginPageObj.Btn_SignInButton("OK");
		//
		//			if (base.isElementPresent(driver, CartChangedAlert)) {		
		//				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
		//				OkButton.click();
		//			}
		if(base.WaitForElementToBePresentWithTimeouts(LoginPageObj.Btn_SignInButton("OK"), 5)){
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(1000);

		//Step 14: Enter receipt number
		Thread.sleep(5000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"), UtilityTestData.InvoiceNumber);

		//Step 15: Enter line Number and Click on fetch button
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogpreflineno"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogpreflineno"), SaleInvoiceWithAdvanceOG_TestData.TC158_LineNo);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Fetch"));

		// Step 16: select QC Person
		//QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		// Step 17: select Smith Person 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		// Step 18: click on calculate button
		Thread.sleep(5000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(5000);
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(2000);

		//Step 19 : Click on Add to Cart Button 
		base.ClickCondition(LoginPageObj.Edt_Alert("Add to Cart"));
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		Bonus = Bonus.replace("-", "");
		ProductNames.add("OD");
		ProductQtyList.add("12.82");
		ProductTotalList.add(Bonus);
		String InvoiceTotal1 = String.format("%.2f",
				(Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "")) +
						Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.-]", "")) -
						Double.parseDouble(ProductTotalList.get(3).replaceAll("[^\\d.]", "")) + 
						Double.parseDouble(TaxBillingPge.replaceAll("[^\\d.-]", "")))
				);

		String GrossAmountactual = String.format("%.2f",
				(Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "")) +
						Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.-]", "")))
				);

		double TaxBillingValue = Double.parseDouble(TaxBillingPge.replaceAll("[^\\d.-]", ""));
		double NetValueActual = Double.parseDouble(GrossAmountactual) + TaxBillingValue - Adjustment;
		String NetValuePdf = String.format("%.2f", NetValueActual);

		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = SkuBillingScrnTbleData.get("Subtotal");
		String TotalGrossWeight = SkuBillingScrnTbleData.get("GrossWeight");
		String TotalNetWeight = SkuBillingScrnTbleData.get("NetWeight");
		String Tax = SkuBillingScrnTbleData.get("Tax");
		String TotalAmount = SkuBillingScrnTbleData.get("TotalAmount");
		String NetTotal = SkuBillingScrnTbleData.get("NetTotal");
		String LinesCounts = SkuBillingScrnTbleData.get("LinesCount");
		List<WebElement> ItemNamesBillings = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		List<WebElement> ItemQtyBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-QuantityField textRight"));
		List<String> TextItemNamesBilling = new ArrayList<>();
		List<String> TextItemQtyBilling = new ArrayList<>();

		for (int n = 0; n < ItemNamesBillings.size(); n++) {
			TextItemNamesBilling.add(ItemNamesBillings.get(n).getText().trim());
		}

		for (int n = 0; n < ItemQtyBilling.size(); n++) 
		{
			TextItemQtyBilling.add(ItemQtyBilling.get(n).getText().trim());
		}
		String BillingQtySku = ItemQtyBilling.get(1).getText().replace("-", "").trim();		

		// Step 20: Click on Save Estimation
		// Expected Result: Verify the estimate details 	
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		// PDF Verification Step
		Thread.sleep(4000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC158_ProformaInvoice");
		Thread.sleep(5000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = TaxBillingPge;
		String TotalAmnt = TaxableValue;

		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal1,
				Double.toString(Adjustment), 
				NetTotal, 
				TotalAmnt);

		Thread.sleep(2000);
		//Step 21. Recall Estimation from the cashier screen
		//Step 22.Select Estimate and click on recall estimation
		//Expected :Verify whether the recalled item is the same as the item in the cart.
		//Expected : Cashier Screen after recall
		//*No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal, *TAX,*Total Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", EstmnNumber));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page"); 

		appUtils.ValidateBillingAndRecallScreenData(EstmnNumber, LinesCounts, TextItemNamesBilling, TextItemQtyBilling,SubTotal, Tax, NetTotal);

		// Select the Transaction Type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), SaleInvoiceWithAdvanceOG_TestData.TC158_TransactionType1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		//	base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		if (base.isElementPresent(driver,NormalSaleGoldAndSilverObj.Btn_Estimation("cancelButton primaryButton"))) 
		{
			try { 
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Estimation("cancelButton primaryButton"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			} catch (Exception e) {}
		}
		Thread.sleep (3000);

		//				if (base.isElementPresent(driver, NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"))) {
		//				    base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		//				}

		try {
			NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close");
		}catch (Exception e) {}

		//Step 23.Select the Transaction Type as Sales 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),SaleInvoiceWithAdvanceOG_TestData.TC158_TransactionTypeSales);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Step 24.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));  
		base.setZoom(driver, 60);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Step 25.Choose Sales Representative 
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		String Discount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4"));
		String PaymentAmountHDFC = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));

		Map<String, String> RecallTransactionDataLines = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(RecallTransactionDataLines, ProductNames, ProductQtyList, ProductTotalList, "Cashier Screen");

		//Step 26: Click on amount
		//Step 27: Select payment method(cash/card)
		Thread.sleep(2000);
		appUtils.PaymentAfterRecallEstimate(SaleInvoiceWithAdvanceOG_TestData.TC158_PaymentMethod);

		// Step 28: Post exchange invoice(sale)
		// Expected Result: Check final invoice details,* save receipt id for future Reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC158_SaleInvoice");
		Thread.sleep(3000);
		String SaleInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
		String GrossAmount = GrossAmountactual ;
		String TotalDiscount = Discount;
		String TotalQtyPcs = Linecount;
		String ODAdjustment = Bonus;
		String TotalDiamond = null;
		String PaymentHDFCCard = PaymentAmountHDFC;
		String SalesPdfPath = SaleInvoicePath;
		String AdvanceAmountFinalInvoice = Double.toString(Adjustment);
		String TotalNetWeightNew = TotalNetWeight;
		String TotalGrossWeightNew = String.format("%.2f", 
				Double.parseDouble(ProductQtyList.get(1).replaceAll("[^\\d.-]", "")) + 
				Double.parseDouble(ProductQtyList.get(2).replaceAll("[^\\d.-]", "")));	;
				String NetValue = NetValuePdf;
				String Taxvalue = GrossAmountactual;
				System.out.print("TotalNetWeightNew" + TotalNetWeightNew);
				System.out.print("TotalGrossweight" + TotalGrossWeight);
				System.out.print("TotalQtyPcs" + TotalQtyPcs);
				Thread.sleep(5000);
				String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidationAdvance(SalesPdfPath, 
						GrossAmount,
						TotalDiscount, 
						TotalDiamond, 
						TotalQtyPcs, 
						TotalNetWeightNew, 
						TotalGrossWeightNew, 
						Taxvalue,
						NetValue,
						PaymentHDFCCard,
						AdjustmentAmount, 
						AdvanceAmountFinalInvoice,
						SkuList, 
						ScannedSkuDataMap);
				System.out.println("PDF 5000T04lN0:-" + PdfInvoiceNo);
				base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
				pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Sale with advance,OG and multiple category of items
	// Automation ID : TC157
	// @Author: Gokul.P
	// </summary>
	public void TC157_SaleInvoiceWithAdvanceOG()throws Exception
	{
		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: Customer must be having an advance(Normal/Offer)			
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				SaleInvoiceWithAdvanceOGTestData.TC157_CustomerID,
				SaleInvoiceWithAdvanceOGTestData.TC157_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//3. Select customer
		//4. Click on add to estimation button
		appUtils.SearchByCustomerID(SaleInvoiceWithAdvanceOGTestData.TC157_CustomerID,UtilityTestData.MenuBarOptions[5]);

		//5. Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//6. click on normal advance settlement
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		//7. Select advance and Click on redeem button
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.Terminal);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		String BalanceAmount    = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		String AdvanceRate      = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRate, "22k");
		Thread.sleep(120000); // Wait needed for ERP Gold Rate to Change    
		double Adjustment     = Double.parseDouble(AdjustmentAmount);
		double Quantity22k    = Double.parseDouble(BalanceAmount) / Double.parseDouble(AdvanceRate);
		Thread.sleep(1000);
		double Current22kRate = (Double.parseDouble(ErpGoldRateFor24K) * 22) / 24;
		appUtils.HamBurgerButtonClick("iconShop");		
		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");
		Thread.sleep(4000);

		// 8. Enter OTP
		// 9. Scan Gold,DMD,Precia SKU's
		//Note: Check average rate
		//Expected: Check calculation
		// Note:Billing Screen:No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Sub-total,TAX,Total Amount
		List<String> Skulist       = new ArrayList<>();
		List<String> GoldSkuList   = appUtils.SelectMultipleSKUs(SaleInvoiceWithAdvanceOGTestData.TC157_SkuCount,SaleInvoiceWithAdvanceOGTestData.TC157_TransferType,SaleInvoiceWithAdvanceOGTestData.TC157_FromCounter1,SaleInvoiceWithAdvanceOGTestData.TC157_MetalType1);
		appUtils.HamBurgerButtonClick("iconShop");		
		List<String> DmdSkuList    = appUtils.SelectMultipleSKUs(SaleInvoiceWithAdvanceOGTestData.TC157_SkuCount,SaleInvoiceWithAdvanceOGTestData.TC157_TransferType,SaleInvoiceWithAdvanceOGTestData.TC157_FromCounter2,SaleInvoiceWithAdvanceOGTestData.TC157_MetalType2);
		appUtils.HamBurgerButtonClick("iconShop");	
		List<String> PreciaSkuList = appUtils.SelectMultipleSKUs(SaleInvoiceWithAdvanceOGTestData.TC157_SkuCount,SaleInvoiceWithAdvanceOGTestData.TC157_TransferType,SaleInvoiceWithAdvanceOGTestData.TC157_FromCounter3,SaleInvoiceWithAdvanceOGTestData.TC157_MetalType3);
		Skulist.addAll(GoldSkuList);
		Skulist.addAll(DmdSkuList);
		Skulist.addAll(PreciaSkuList);	
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(SaleInvoiceWithAdvanceOGTestData.TC157_DepositAdjustment);
		ProductQtyList.add(	SaleInvoiceWithAdvanceOGTestData.TC157_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		WebDriverWait HamBurgerWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			if (HamBurgerWait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SingnIn("CART_ITEM_ID"))) != null)
			{
				Thread.sleep(3000);
				base.ClickCondition(LoginPageObj.Btn_SingnIn("CART_ITEM_ID"));
			}
		} catch (Exception e) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("height48 centerY","Show or hide navigation bar"));
			Thread.sleep(3000);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("CART_ITEM_ID"));
		}
		String ErpGoldRate24k = ErpGoldRateFor24K;
		int SkuCounter = 1;
		erpUtils.ResetRemainingReturnWeight();
		Thread.sleep(10000);
		for (String Sku : Skulist) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			Thread.sleep(2000);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,String.valueOf(Quantity22k),"22k",AdvanceRate,ErpGoldRate24k);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(Skulist, ScannedSkuDataMap);
		String SubTotal                 = TransactionDataLine.get("Subtotal");
		String NetTotal                 = TransactionDataLine.get("NetTotal");
		String Discount                 = TransactionDataLine.get("Discount");
		String TotalNetWeight           = TransactionDataLine.get("NetWeight");
		String Tax                      = TransactionDataLine.get("Tax");
		String TotalAmount              = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount         = TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));		
		String ExpectedLinesCount       = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

		double SubTotalSum = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName         = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i == 1||i==2||i==3) {
				//String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue( ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge + "' to contain actual '" + ActualItemNameInBillingPge + "'");
				//asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			}
			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge    = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge  = ProductQtyList.get(i);
			double ActualQtyInBillingPge         = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge       = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax           = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSum                                = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

		String ActualTaxInBillingPge   = Tax.replaceAll("[^\\d.]", "").trim();
		double ExpectedTax             =( Double.parseDouble(ProductTotalList.get(1))+ Double.parseDouble(ProductTotalList.get(2))+ Double.parseDouble(ProductTotalList.get(3))) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		//			 10. Select OG
		//			 11.Click on OG own
		//			 12. Select configuration
		//			 13. Click on Add item button
		//			 14. Select QC person
		//			 15. Select Smith person
		//			 16. Enter piece value
		//			 17. Enter gross weight
		//			 18. Click on calculate button
		//			 19. Click on Add to cart button
		//           Expected:Check OG calculation
		appUtils.PurchaseOldGold(SaleInvoiceWithAdvanceOGTestData.TC157_Configuration, 
				SaleInvoiceWithAdvanceOGTestData.TC157_PurchaseOrExchange,
				"",
				"",
				SaleInvoiceWithAdvanceOGTestData.TC157_GrossPieces,
				SaleInvoiceWithAdvanceOGTestData.TC157_GrossWeight);
		Map<String, Double> Og22kData = mathUtils.ValidateOldGoldItemDetailsData(Current22kRate, SaleInvoiceWithAdvanceOGTestData.TC157_Configuration);
		Double OgQty22k               = Og22kData.get("OgQty");		
		Double OgRate22k              = Og22kData.get("OgCvalue")/OgQty22k;
		Double OgCvalue22k            = (Og22kData.get("OgCvalue")*-1);	

		ProductQtyList.add(String.valueOf(OgQty22k));
		ProductNames.add(UtilityTestData.OGProduct);
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("SimpleProductDetailsView_AppBarView_appBarCommandCommand","win-commandicon"));	
		double ItemRate                     = Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+Double.parseDouble(ProductTotalList.get(3));
		Double SkuItemRate                  = ItemRate+OgCvalue22k;
		Double TotalAmountAfterOgBillingPge = SkuItemRate+Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim());
		List<WebElement> TotalWithoutTaxOgInBillingPge    = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		System.out.println("The OG Tax is :"+TotalWithoutTaxOgInBillingPge.get(4).getText());
		double NetTotalOgBillingScreen      = Double.parseDouble(NetTotal.replaceAll("[^\\d.]", "").trim())+(Double.parseDouble(TotalWithoutTaxOgInBillingPge.get(4).getText().replaceAll("[^\\d.-]", "").trim()));
		WebDriverWait Wait                  = new WebDriverWait(driver, Duration.ofSeconds(8));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		// 20. save estimate
		// Expected:Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);	
		String FirstInvoice        = pdfUtils.DownloadAndRenameLatestPDF("TC157_ProformaInvoice");
		Thread.sleep(4000);		
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+FirstInvoice+"";
		String TaxableValue        = String.valueOf(ItemRate);
		String InvoiceTotal        = String.valueOf(TotalAmountAfterOgBillingPge);
		String Gst                 = Tax;
		String TotalAmnt =String.valueOf(SkuItemRate);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				String.valueOf(NetTotalOgBillingScreen),
				TotalAmnt				
				);

		// 21. Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));

		// 22. Select estimate and click on recall estimate button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));

		// 23. Select transaction type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), SaleInvoiceWithAdvanceOGTestData.TC157_TransactionExchange);

		// 24. Click on process estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// 25. Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		// 26. Click on amount 0.00
		// 27. Post exchange invoice										
		String AmountDueForExchangeFromBillingPge    = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForExchangeInFromBillingPge  = Double.parseDouble(AmountDueForExchangeFromBillingPge);			
		String ExpectedAmountForExchangeInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		if (AmountDueForExchangeInFromBillingPge<0.00) {
			appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
					UtilityTestData.SalePersonNumber,
					UtilityTestData.DueYear,
					UtilityTestData.DobYear,
					UtilityTestData.NomineeName,
					UtilityTestData.NomineeRelation);
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			String ActualAdvanceReceived                     = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived                         = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode                         = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount                             = AdvanceReceiptVoucherDetails.get("PaymentAmount");
			String ApproxWeight                              = AdvanceReceiptVoucherDetails.get("ApproxWeight");
			String ActualFixedGoldRate                       = AdvanceReceiptVoucherDetails.get("FixedGoldRate");
			String ActualDepositType                         = AdvanceReceiptVoucherDetails.get("DepositType");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else 
		{
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			double GrossWeight                              = OgQty22k;
			String ExpectedGrossWeightFromBillingPge        = String.format("%.3f", GrossWeight);  
			String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",OgRate22k);
			double TotalAmountForOG                         = OgCvalue22k;
			String ExpectedTotalAmountForOG                 = String.format("%.2f", Double.parseDouble(String.valueOf(TotalAmountForOG).replaceAll("[^\\d.]", "").trim()));  			
			Map<String, String> OgPurchaseBill              = pdfUtils.OGPdfValidation("Adjustment");
			String GrossWeightFromInvoice                   = OgPurchaseBill.get("GrossWt1");
			String NetWeightFromInvoice                     = OgPurchaseBill.get("NetWt1");
			String GoldRateFromInvoice                      = OgPurchaseBill.get("Rate1");
			String TotalAmountFromInvoice                   = OgPurchaseBill.get("TotalAmount1");
			String PaymentDetailsFromInvoice                = OgPurchaseBill.get("PaymentDetails");
			asrt.assertEquals(ExpectedGrossWeightFromBillingPge, GrossWeightFromInvoice,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+GrossWeightFromInvoice+" in purchase bill");		
			asrt.assertEquals(OgQty22k, Double.parseDouble(NetWeightFromInvoice),1.0,"Net Weight mismatch: "+OgQty22k+" but got "+NetWeightFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedOGPurchaseRateFromItemDetailsPge, GoldRateFromInvoice,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, TotalAmountFromInvoice,"Total amount mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+TotalAmountFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, PaymentDetailsFromInvoice,"Payment details mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+PaymentDetailsFromInvoice+" in purchase bill");
		}

		// 28. Again recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));					

		// 29. Select transaction type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), SaleInvoiceWithAdvanceOGTestData.TC157_TransactionSales);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// 30. Click on process estimation button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// 31. Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		// 32. Click on amount
		// 33. Select payment method(cash/card)
		// 34. Post Sales invoice
		// Expected:Check final invoice details					
		Thread.sleep(3000);
		List<WebElement> TotalWithoutTaxCashierPge     = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(Skulist, ScannedSkuDataMap);
		String SubTotalInCashierPge                    = TransactionDataLineCashier.get("Subtotal");
		String TaxInCashierPge                         = TransactionDataLineCashier.get("Tax");
		String TotalAmountInCashierPge                 = TransactionDataLineCashier.get("TotalAmount");
		String ActualLinesCountInCashierPge            = TransactionDataLineCashier.get("LinesCount");
		List<WebElement> AllProductRowsInCashierPge    = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierPge          = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCountInCashierPge, ExpectedLinesCountInCashierPge, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCountInCashierPge + " in cashier page");

		double SubTotalSumInCashierPge = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName         = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierPge = ItemName.get(i).getText();
			if (i == 1||i==2||i==3) {
				//String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue( ExpectedItemNameInBillingPge.contains(ActualItemNameInCashierPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge + "' to contain actual '" + ActualItemNameInCashierPge + "'");
				//asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInCashierPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInCashierPge + " in billing page");
			}
			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInCashierPge    = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierPge  = ProductQtyList.get(i);
			double ActualQtyInCashierPge         = Double.parseDouble(ActualQuantityInCashierPge);
			double ExpectedQtyInCashierPge       = Double.parseDouble(ExpectedQuantityInCashierPge);
			asrt.assertEquals(ActualQtyInCashierPge, ExpectedQtyInCashierPge, 1.000, "Quantity mismatch: Expected is "+ ActualQtyInCashierPge + " but got " + ExpectedQtyInCashierPge + " in cashier page");

			List<WebElement> TotalWithoutTaxInCashierPge = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierPge     = TotalWithoutTaxCashierPge.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSumInCashierPge                      = SubTotalSumInCashierPge + Double.parseDouble(ActualTotalWithoutTaxInCashierPge);
			String ExpectedTotalWithoutTaxInCashierPge   = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierPge + " but got "+ ActualTotalWithoutTaxInCashierPge + " in cashier page");
		}
		String ExpectedSubTotalInCashierPge = String.format("%.2f", SubTotalSumInCashierPge).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierPge),Double.parseDouble(ExpectedSubTotalInCashierPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierPge + " but found " + ActualSubTotalInCashierPge+ " in billing page");

		String ActualTaxInCashierPge   = TaxInCashierPge.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxCashierPge   = ( Double.parseDouble(ProductTotalList.get(1))+ Double.parseDouble(ProductTotalList.get(2))+ Double.parseDouble(ProductTotalList.get(3))) * 0.03;
		String ExpectedTaxInCashierPge = String.format("%.2f", ExpectedTaxCashierPge);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierPge), Double.parseDouble(ExpectedTaxInCashierPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierPge + " but found " + ActualTaxInCashierPge+ " in billing page");

		double ExpectedTotalAmountInCashierPge = Double.parseDouble(ActualSubTotalInCashierPge)+ Double.parseDouble(ActualTaxInCashierPge);
		double ActualTotalAmountInCashierPge   = Double.parseDouble(TotalAmountInCashierPge.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierPge, ExpectedTotalAmountInCashierPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		double GrossAmountForSale               = Double.parseDouble( ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+Double.parseDouble(ProductTotalList.get(3));
		String TaxableValueForSale              = String.valueOf(GrossAmountForSale);
		String AmountAdvance                    = AdvanceAmount.replaceAll("[^\\d.]", "").trim();
		double CalculatedTotalValue             = (Double.parseDouble(TaxableValueForSale.replaceAll("[^\\d.]", "").trim())*0.03)-Double.parseDouble(AmountAdvance);
		String TotalValueForSale                = String.valueOf(CalculatedTotalValue);
		String TotalQtyPcsForSale               = String.valueOf(Skulist.size());
		String TotalDiscountForSale             = Discount;
		String TotalNetWeightForSale            = TotalNetWeight;
		double TotalGrossWeight                 = Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2))+Double.parseDouble(ProductQtyList.get(3));
		String TotalGrossWeightForSale          = String.format("%.3f",TotalGrossWeight);
		String AdjustmentForSale                = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("PaymentsField", "h4")).replaceAll("[^\\d.]", "").trim();
		String AmountDueForSaleFromBillingPge   = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleInFromBillingPge = Double.parseDouble(AmountDueForSaleFromBillingPge);
		String PaymentAmountForSale             = "";
		if (AmountDueForSaleInFromBillingPge==0.00) {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			PaymentAmountForSale = "";
		}
		else {
			PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");
		}
		Thread.sleep(5000);
		Double TotalNetValueForSale =Double.parseDouble(PaymentAmountForSale.replaceAll("[^\\d.]", "").trim())+Double.parseDouble(AdjustmentForSale.replaceAll("[^\\d.]", "").trim());
		String NetValue             =String.valueOf(TotalNetValueForSale);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC157_TaxInvoice");		
		String TaxInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String TotalDiamond   = "";
		String PdfInvoiceNo   = pdfUtils.SaleInvoicePdfAdjustmentAdvanceValidation(
				TaxInvoice,
				String.valueOf(GrossAmountForSale),
				TotalDiscountForSale,
				TotalDiamond,
				TotalQtyPcsForSale,
				TotalNetWeightForSale,
				TotalGrossWeightForSale,
				TaxableValueForSale,
				NetValue,
				PaymentAmountForSale,
				AdjustmentForSale,
				Skulist,
				ScannedSkuDataMap
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Sale with normal advance and offer advance with OG
	// Automation ID : TC159
	// @Author: Nandagopan
	// </summary>
	public void TC159_SaleInvoiceWithAdvanceOG()throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: Customer must be having an advance(Normal/Offer)			
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				SaleInvoiceWithAdvanceOG_TestData.TC159_CustomerID,
				SaleInvoiceWithAdvanceOGTestData.TC159_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);
		String AdvanceStr = (AdvanceAmount.replaceAll("[^\\d.]", ""));

		//Prerequisite: Customer must have offer Advance
		Thread.sleep(1000);
		Map<String, String> FirstOfferAdvance = appUtils.CreateWeightBookedOfferAdvance
				(SaleInvoiceWithAdvanceOG_TestData.TC159_CustomerID,
						SaleInvoiceWithAdvanceOG_TestData.TC159_Pieces, 
						SaleInvoiceWithAdvanceOG_TestData.TC159_OfferGrossWeight1, 
						UtilityTestData.Company[0], 
						UtilityTestData.Location[0],
						SaleInvoiceWithAdvanceOG_TestData.TC159_DepositPercent);

		String FirstOrderId = FirstOfferAdvance.get("OrderId"); // remove first word from order id 
		String BoardRate22k    = FirstOfferAdvance.get("FixedGoldRate");
		double GoldRate = Double.parseDouble(BoardRate22k.replaceAll("[^\\d.]", ""));
		double FirstOfferAmount = Double.parseDouble(FirstOfferAdvance.get("WillingToPayAmount"));
		System.out.println("BoardRate22k:- "+BoardRate22k);


		//3. Select customer
		//4. Click on add to estimation button
		appUtils.SearchByCustomerID(SaleInvoiceWithAdvanceOG_TestData.TC159_CustomerID,UtilityTestData.MenuBarOptions[5]);

		//5. Click on customer adjustment button
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//6. click on normal advance settlement
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		//7. Select advance and Click on redeem button
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),AdvanceStr);
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(3000);
		String Rate = BigDecimal.valueOf(GoldRate).stripTrailingZeros().toPlainString();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",Rate));

		String BalanceAmount    = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
		//String AdvanceRate      = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");			
		double AdvanceQuantity = Double.parseDouble(BalanceAmount)/Double.parseDouble(Rate);

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		//Step8:Enter OTP
		//Step 9:Click on customer adjustments-->order advance adjustment
		//Step 10: choose advance from dropdown-->enter the adjustment amount,Click on add to cart
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"))).click();
		double AdvanceBalance1 = 0;
		String AdvanceBalanceAmount1 = "";
		String MetalRateFetch = "";
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"), FirstOfferAdvance.get("OrderId")); 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("option", FirstOfferAdvance.get("OrderId")));
		AdvanceBalance1 = Double.parseDouble(base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount")));
		System.out.println("Balance Amount from the box " + AdvanceBalance1);

		// Balance Amount in the Offer advance page 
		Thread.sleep(2000);
		AdvanceBalanceAmount1 = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine", "Balance amount"));
		System.out.println("Advance Amt = " + AdvanceBalanceAmount1);

		// Balance Amt in the Offer advance page 
		Thread.sleep(2000);
		AdvanceBalance1 = Double.parseDouble(base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount")));
		System.out.println("Balance Amount from the box " + AdvanceBalance1);

		MetalRateFetch = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_MetalRate("column3", "h4 ellipsis cell", "Metal Rate"));
		System.out.println("Metal Rate = " + MetalRateFetch);

		// Selecting the list order id from the offer advance list 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("column1", "h4 ellipsis cell", "1"));
		String BalanceAmtInAdvanceAdjOffer = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount"));
		String DefaultAdjAmt1 = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		double Adjustment = AdvanceBalance1;
		String AdvanceRateOffer = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		base.clearData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"), Double.toString(Adjustment));
		String AdjustmentAmount1      =base.GetAttribte(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("InputAmount"),"value");

		// Click on cart button
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));					
		//	double AdvanceQuantityOffer = Double.parseDouble(BalanceAmtInAdvanceAdjOffer)/Double.parseDouble(AdvanceRateOffer);
		double AdvanceQuantityOffer = (Double.parseDouble(BalanceAmtInAdvanceAdjOffer)/Double.parseDouble(AdvanceRateOffer))/SaleInvoiceWithAdvanceOG_TestData.TC159_DepositPercent;
		double AdvanceQuantityERP =AdvanceQuantity+AdvanceQuantityOffer;//Sum of both adjustment calculated qty
		double Adjustment22K = Double.parseDouble(AdjustmentAmount1);
		double Quantity22k   = (Adjustment22K/Double.parseDouble(AdvanceRateOffer))/SaleInvoiceWithAdvanceOG_TestData.TC159_DepositPercent;
		double MetalRate18k = (Double.parseDouble(AdvanceRateOffer)*18)/22;
		double Quantity18k  = (Adjustment22K/MetalRate18k)/SaleInvoiceWithAdvanceOG_TestData.TC159_DepositPercent;
		int  Quantity=(int)Quantity22k;
		
		asrt.assertEquals(BalanceAmtInAdvanceAdjOffer, DefaultAdjAmt1,"Mismatch in balance amount " +BalanceAmtInAdvanceAdjOffer+" and adjusted amount " +DefaultAdjAmt1+" displayed in advance adjustment page");

		//Total adjustment
		Double TotalAdjustment = Double.parseDouble(BalanceAmtInAdvanceAdjOffer) +Double.parseDouble(BalanceAmount);
		String TotalAdjustmentStr = String.valueOf(TotalAdjustment);
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRateOffer, "22k");
		TimeUnit.MINUTES.sleep(6);// Wait needed for ERP Gold Rate to Change
		double Current22kRate = (Double.parseDouble(ErpGoldRateFor24K) * 22) / 24;
        
		//Step 11 :Scan Gold SKU
		// Note: Check average rate
		//Expected: Check calculation
		//List<String> RequiredSku = appUtils.SelectMultipleSKUs(SaleInvoiceWithAdvanceOGTestData.TC159_SkuGoldCount,SaleInvoiceWithAdvanceOGTestData.TC159_TransferType,SaleInvoiceWithAdvanceOGTestData.TC159_FromCounterSKU,SaleInvoiceWithAdvanceOGTestData.TC159_MetalTypeSKU);
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				SaleInvoiceWithAdvanceOGTestData.TC159_TransferType,
				SaleInvoiceWithAdvanceOGTestData.TC159_FromCounterSKU,
				SaleInvoiceWithAdvanceOGTestData.TC159_MetalTypeSKU,
				Quantity,
				SaleInvoiceWithAdvanceOGTestData.TC159_SkuGoldCount);
		List<String> RequiredSku = SkuGoldSale.PcsRateSKU;
		System.out.println("Gold Selected SKUs: " + RequiredSku);
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> ProductTotal = new ArrayList<>();

		ProductNames.add(SaleInvoiceWithAdvanceOGTestData.TC159_DepositAdjustment);
		ProductQtyList.add("1");
		ProductTotalList.add(BalanceAmount);

		ProductNames.add(SaleInvoiceWithAdvanceOGTestData.TC159_DepositAdjustment);
		ProductQtyList.add("1");
		ProductTotalList.add(BalanceAmtInAdvanceAdjOffer);
		String CvalueRG = "";
		String ItemNameInItemDetailsPge = "";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		String WastageQty = "";
		String WastageAmt = "";
		String MakingRate = "";

		erpUtils.ResetRemainingReturnWeight();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredSku.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = RequiredSku.get(i);
			// Extract SKU data
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			MakingRate = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_MakingRate");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
			ProductTotal.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber, Double.toString(AdvanceQuantityERP),SaleInvoiceWithAdvanceOGTestData.TC159_Configuration, BoardRate22k, ErpGoldRateFor24K);
		}

		//			Step 12: Select OG
		//			Step 11:Click on OG own
		//			Step 12. Select configuration
		//			Step 13. Click on Add item button
		//			Step 14. Select QC person
		//			Step 15. Select Smith person
		//			Step 16. Enter piece value
		//			Step 17. Enter gross weight
		//			Step 18. click on calculate button
		//			Step 19. Click on Add to cart button
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),SaleInvoiceWithAdvanceOGTestData.TC159_OldProduct);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),SaleInvoiceWithAdvanceOGTestData.TC159_Configuration);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(10000);
		List<WebElement>ErrorMessage = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));
		if (!ErrorMessage.isEmpty()) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
		}
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), SaleInvoiceWithAdvanceOGTestData.TC159_Pieces);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), SaleInvoiceWithAdvanceOGTestData.TC159_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(4000);

		String ExpectedGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String TotalAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));						
		Map<String, Double> Og22kData = mathUtils.ValidateOldGoldItemDetailsData(Current22kRate, SaleInvoiceWithAdvanceOGTestData.TC159_Configuration);
		Double OgQty22k               = Og22kData.get("OgQty");		
		Double OgRate22k              = Og22kData.get("OgCvalue")/OgQty22k;
		Double OgCvalue22k            = (Og22kData.get("OgCvalue")*-1);							
		ProductNames.add(SaleInvoiceWithAdvanceOGTestData.TC159_OldProduct);
		ProductQtyList.add(ExpectedGrossWeightInItemDetailsPge);
		ProductTotalList.add(TotalAmountInItemDetailsPge);							
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		if(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Cart has changed"))) 
		{
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));


		}

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLineBillingScreen = appUtils.TransactionSKUsLinesVerify(RequiredSku, ScannedSkuDataMap);
		String SubTotal 		= TransactionDataLineBillingScreen.get("Subtotal");
		String TotalGrossWeight = TransactionDataLineBillingScreen.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLineBillingScreen.get("NetWeight");
		String Tax 		        = TransactionDataLineBillingScreen.get("Tax");
		String TotalAmount      = TransactionDataLineBillingScreen.get("TotalAmount");
		String NetTotal         = TransactionDataLineBillingScreen.get("NetTotal");
		String LinesCount       = TransactionDataLineBillingScreen.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineBillingScreen, ProductNames, ProductQtyList, ProductTotalList,"Billing Screen");

		//Step 20: Save to estimate
		//Expected: Verify the Invoice details
		//Step 21: Recall estimate from cashier screen
		//Step 22: Select estimate and click on save estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(10000);
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(8000);

		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC159_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		Thread.sleep(3000);
		String Gst = Tax;
		String TaxableValue = ProductTotalList.get(2).replaceAll("[^\\d.]", "");
		String InvoiceTotal = Double.toString(( Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "")) - Double.parseDouble(ProductTotalList.get(3).replaceAll("[^\\d.]", "")))   + Double.parseDouble(Tax.replaceAll("[^\\d.]", "")));
		String TotalAdjustmentProForma = TotalAdjustmentStr;
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(ProFormaInvoicePath, TaxableValue, Gst,
				InvoiceTotal, TotalAdjustmentProForma, NetTotal, TotalAmt);

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
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"in Recall estimation page");

		// Step 23: Select transaction type as Exchange
		// Step 24. Click on process estimation button
		// Step 25. Choose a sales representative
		// Step 26. Click on amount 0.00
		//Step 27:	Post the invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),SaleInvoiceWithAdvanceOGTestData.TC159_TransactionType1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		if (base.isElementPresent(driver, NormalSaleGoldAndSilverObj.Btn_Estimation("cancelButton primaryButton"))) {
			try {
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Estimation("cancelButton primaryButton"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			} catch (Exception e) {
			}
		}

		String AmountDueForExchangeFromBillingPge    = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForExchangeInFromBillingPge  = Double.parseDouble(AmountDueForExchangeFromBillingPge);			
		String ExpectedAmountForExchangeInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		if (AmountDueForExchangeInFromBillingPge<0.00) {
			appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
					UtilityTestData.SalePersonNumber,
					UtilityTestData.DueYear,
					UtilityTestData.DobYear,
					UtilityTestData.NomineeName,
					UtilityTestData.NomineeRelation);

			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			String ActualAdvanceReceived                     = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived                         = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode                         = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount                             = AdvanceReceiptVoucherDetails.get("PaymentAmount");
			String ApproxWeight                              = AdvanceReceiptVoucherDetails.get("ApproxWeight");
			String ActualFixedGoldRate                       = AdvanceReceiptVoucherDetails.get("FixedGoldRate");
			String ActualDepositType                         = AdvanceReceiptVoucherDetails.get("DepositType");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else 
		{
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			double GrossWeightOG                             = OgQty22k;
			String ExpectedGrossWeightFromBillingPge        = String.format("%.3f", GrossWeightOG);  
			String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",OgRate22k);
			double TotalAmountForOG                         = OgCvalue22k;
			String ExpectedTotalAmountForOG                 = String.format("%.2f", Double.parseDouble(String.valueOf(TotalAmountForOG).replaceAll("[^\\d.]", "").trim()));  			
			Map<String, String> OgPurchaseBill              = pdfUtils.OGPdfValidation("Adjustment");
			String GrossWeightFromInvoice                   = OgPurchaseBill.get("GrossWt1");
			String NetWeightFromInvoice                     = OgPurchaseBill.get("NetWt1");
			String GoldRateFromInvoice                      = OgPurchaseBill.get("Rate1");
			String TotalAmountFromInvoice                   = OgPurchaseBill.get("TotalAmount1");
			String PaymentDetailsFromInvoice                = OgPurchaseBill.get("PaymentDetails");
			asrt.assertEquals(ExpectedGrossWeightFromBillingPge, GrossWeightFromInvoice,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+GrossWeightFromInvoice+" in purchase bill");		
			asrt.assertEquals(OgQty22k, Double.parseDouble(NetWeightFromInvoice),1.0,"Net Weight mismatch: "+OgQty22k+" but got "+NetWeightFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedOGPurchaseRateFromItemDetailsPge, GoldRateFromInvoice,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, TotalAmountFromInvoice,"Total amount mismatch: "+ExpectedTotalAmountForOG+" but got "+TotalAmountFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, PaymentDetailsFromInvoice,"Payment details mismatch: "+ExpectedTotalAmountForOG+" but got "+PaymentDetailsFromInvoice+" in purchase bill");
		}

		// Step 27: Again recall estimate from cashier screen
		// Step 28:Select estimate and click on Recall estimation button
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		// Step 29: Select the Transaction Type as Sales
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),SaleInvoiceWithAdvanceOGTestData.TC159_TransactionType2);

		// Step 30: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 31: Choose a Sales Representative
		base.setZoom(driver, 60);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		appUtils.HandleSpecifyRateModalInRecall();
		ProductNames.removeLast();
		ProductQtyList.removeLast();
		ProductTotalList.removeLast();

		//Note:cashierScreen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLineCashierScreen = appUtils.TransactionSKUsLinesVerify(RequiredSku, ScannedSkuDataMap);
		String SubTotalCashierScreen 		 = TransactionDataLineCashierScreen.get("Subtotal");
		String TotalGrossWeightCashierScreen = TransactionDataLineCashierScreen.get("GrossWeight");
		String TotalNetWeightCashierScreen   = TransactionDataLineCashierScreen.get("NetWeight");
		String TaxCashierScreen	        	 = TransactionDataLineCashierScreen.get("Tax");
		String TotalAmountCashierScreen      = TransactionDataLineCashierScreen.get("TotalAmount");
		String NetTotalCashierScreen         = TransactionDataLineCashierScreen.get("NetTotal");
		String LinesCountCashierScreen       = TransactionDataLineCashierScreen.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashierScreen, ProductNames, ProductQtyList, ProductTotalList,"Cashier Screen");

		//Step 32: Click on the Amount
		//Step 33: Select a Payment method(Cash or Card)
		//Step 34 :Post the sale invoice
		//Expected:Check final invoice details
		Thread.sleep(2000);
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");						
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);

		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC159TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ProductTotalList.get(2);
		String TotalDiscount = "";
		String TotalQtyPcs = Integer.toString(RequiredSku.size());
		double NetValueDouble =Double.parseDouble(TaxableValue.replaceAll("[^\\d.]", "")) +Double.parseDouble(Gst.replaceAll("[^\\d.]", "")) - Double.parseDouble(TotalAdjustmentStr.replaceAll("[^\\d.]", ""));
		String NetValue = String.format("%.2f", NetValueDouble);
		String PaymentHDFCCard = PaymentAmount;
		String TotalGrossWeightTaxInv = ProductQtyList.get(2);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationTaxInvoice(InvoicePdfPath, GrossAmount,
				TotalDiscount,TotalQtyPcs, TotalNetWeight, TotalGrossWeightTaxInv, TaxableValue, NetValue,
				PaymentHDFCCard, TotalAdjustmentStr, RequiredSku, ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Sale with advance,OG and multiple DMD item
	// Automation ID : TC156
	// @Author: Gokul.P
	// </summary>
	public void TC156_SaleInvoiceWithAdvanceOG()throws Exception
	{
		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: Customer must be having an advance(Normal/Offer)			
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				SaleInvoiceWithAdvanceOGTestData.TC156_CustomerID,
				SaleInvoiceWithAdvanceOGTestData.TC156_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//3. Select customer
		//4. Click on add to estimation button
		appUtils.SearchByCustomerID(SaleInvoiceWithAdvanceOGTestData.TC156_CustomerID,UtilityTestData.MenuBarOptions[5]);

		//5. Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//6. click on normal advance settlement
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		//7. Select advance and Click on redeem button
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.Terminal);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		String BalanceAmount    = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		String AdvanceRate      = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRate, "22k");
		Thread.sleep(120000); // Wait needed for ERP Gold Rate to Change    
		double Adjustment     = Double.parseDouble(AdjustmentAmount);
		double Quantity22k    = Double.parseDouble(BalanceAmount) / Double.parseDouble(AdvanceRate);
		Thread.sleep(1000);
		double Current22kRate = (Double.parseDouble(ErpGoldRateFor24K) * 22) / 24;
		//		appUtils.HamBurgerButtonClick("iconShop");		
		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");
		Thread.sleep(4000);

		// 8. Enter OTP
		//  9. Scan multiple DMD SKU
		//Note: Check average rate
		//Expected: Check calculation
		// Note:Billing Screen:No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Sub-total,TAX,Total Amount
		List<String> Skulist       = new ArrayList<>();
		appUtils.HamBurgerButtonClick("iconShop");		
		List<String> DmdSkuList    = appUtils.SelectMultipleSKUs(SaleInvoiceWithAdvanceOGTestData.TC156_SkuCount,SaleInvoiceWithAdvanceOGTestData.TC156_TransferType,SaleInvoiceWithAdvanceOGTestData.TC156_FromCounter,SaleInvoiceWithAdvanceOGTestData.TC156_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");	
		Skulist.addAll(DmdSkuList);
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double TotalDiamondValue=0.0;
		ProductNames.add(SaleInvoiceWithAdvanceOGTestData.TC156_DepositAdjustment);
		ProductQtyList.add(	SaleInvoiceWithAdvanceOGTestData.TC156_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		appUtils.HamBurgerButtonClick("iconShop");	
		String ErpGoldRate24k = ErpGoldRateFor24K;
		int SkuCounter = 1;
		erpUtils.ResetRemainingReturnWeight();
		Thread.sleep(10000);
		for (String Sku : Skulist) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			Thread.sleep(2000);
			TotalDiamondValue = TotalDiamondValue +Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueDIAMOND"));
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,String.valueOf(Quantity22k),"22k",AdvanceRate,ErpGoldRate24k);
			SkuCounter++;
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(Skulist, ScannedSkuDataMap);
		String SubTotal                 = TransactionDataLine.get("Subtotal");
		String NetTotal                 = TransactionDataLine.get("NetTotal");
		String Discount                 = TransactionDataLine.get("Discount");
		String TotalNetWeight           = TransactionDataLine.get("NetWeight");
		String Tax                      = TransactionDataLine.get("Tax");
		String TotalAmount              = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount         = TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));		
		String ExpectedLinesCount       = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

		double SubTotalSum = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName         = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i == 1||i==2) {
				//String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue( ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge + "' to contain actual '" + ActualItemNameInBillingPge + "'");
				//asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			}
			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge    = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge  = ProductQtyList.get(i);
			double ActualQtyInBillingPge         = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge       = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax           = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSum                                = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

		String ActualTaxInBillingPge   = Tax.replaceAll("[^\\d.]", "").trim();
		double ExpectedTax             =( Double.parseDouble(ProductTotalList.get(1))+ Double.parseDouble(ProductTotalList.get(2))) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		//			 10. Select OG
		//			 11.Click on OG own
		//			 12. Select configuration
		//			 13. Click on Add item button
		//			 14. Select QC person
		//			 15. Select Smith person
		//			 16. Enter piece value
		//			 17. Enter gross weight
		//			 18. Click on calculate button
		//			 19. Click on Add to cart button
		//           Expected:Check OG calculation
		appUtils.PurchaseOldGold(SaleInvoiceWithAdvanceOGTestData.TC156_Configuration, 
				SaleInvoiceWithAdvanceOGTestData.TC156_PurchaseOrExchange,
				"",
				"",
				SaleInvoiceWithAdvanceOGTestData.TC156_GrossPieces,
				SaleInvoiceWithAdvanceOGTestData.TC156_GrossWeight);
		Map<String, Double> Og22kData = mathUtils.ValidateOldGoldItemDetailsData(Current22kRate, SaleInvoiceWithAdvanceOGTestData.TC156_Configuration);
		Double OgQty22k               = Og22kData.get("OgQty");		
		Double OgRate22k              = Og22kData.get("OgCvalue")/OgQty22k;
		Double OgCvalue22k            = (Og22kData.get("OgCvalue")*-1);	

		ProductQtyList.add(String.valueOf(OgQty22k));
		ProductNames.add(UtilityTestData.OGProduct);
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("SimpleProductDetailsView_AppBarView_appBarCommandCommand","win-commandicon"));	
		double ItemRate                     = Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2));
		Double SkuItemRate                  = ItemRate+OgCvalue22k;
		Double TotalAmountAfterOgBillingPge = SkuItemRate+Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim());
		List<WebElement> TotalWithoutTaxOgInBillingPge    = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		System.out.println("The OG Tax is :"+TotalWithoutTaxOgInBillingPge.get(3).getText());
		double NetTotalOgBillingScreen      = Double.parseDouble(NetTotal.replaceAll("[^\\d.]", "").trim())+(Double.parseDouble(TotalWithoutTaxOgInBillingPge.get(3).getText().replaceAll("[^\\d.-]", "").trim()));
		WebDriverWait Wait                  = new WebDriverWait(driver, Duration.ofSeconds(8));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		// 20. save estimate
		// Expected:Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);	
		String FirstInvoice        = pdfUtils.DownloadAndRenameLatestPDF("TC156_ProformaInvoice");
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+FirstInvoice+"";
		String TaxableValue        = String.valueOf(ItemRate);
		String InvoiceTotal        = String.valueOf(TotalAmountAfterOgBillingPge);
		String Gst                 = Tax;
		String TotalAmnt =String.valueOf(SkuItemRate);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				String.valueOf(NetTotalOgBillingScreen),
				TotalAmnt				
				);

		// 21. Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));

		// 22. Select estimate and click on recall estimate button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));

		// 23. Select transaction type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"in recall estimation page"); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), SaleInvoiceWithAdvanceOGTestData.TC156_TransactionExchange);

		// 24. Click on process estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// 25. Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		// 26. Click on amount 0.00
		// 27. Post exchange invoice										
		String AmountDueForExchangeFromBillingPge    = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForExchangeInFromBillingPge  = Double.parseDouble(AmountDueForExchangeFromBillingPge);			
		String ExpectedAmountForExchangeInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		if (AmountDueForExchangeInFromBillingPge<0.00) {
			appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
					UtilityTestData.SalePersonNumber,
					UtilityTestData.DueYear,
					UtilityTestData.DobYear,
					UtilityTestData.NomineeName,
					UtilityTestData.NomineeRelation);
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			String ActualAdvanceReceived                     = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived                         = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode                         = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount                             = AdvanceReceiptVoucherDetails.get("PaymentAmount");
			String ApproxWeight                              = AdvanceReceiptVoucherDetails.get("ApproxWeight");
			String ActualFixedGoldRate                       = AdvanceReceiptVoucherDetails.get("FixedGoldRate");
			String ActualDepositType                         = AdvanceReceiptVoucherDetails.get("DepositType");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else 
		{
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			double GrossWeight                              = OgQty22k;
			String ExpectedGrossWeightFromBillingPge        = String.format("%.3f", GrossWeight);  
			String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",OgRate22k);
			double TotalAmountForOG                         = OgCvalue22k;
			String ExpectedTotalAmountForOG                 = String.format("%.2f", Double.parseDouble(String.valueOf(TotalAmountForOG).replaceAll("[^\\d.]", "").trim()));  			
			Map<String, String> OgPurchaseBill              = pdfUtils.OGPdfValidation("Adjustment");
			String GrossWeightFromInvoice                   = OgPurchaseBill.get("GrossWt1");
			String NetWeightFromInvoice                     = OgPurchaseBill.get("NetWt1");
			String GoldRateFromInvoice                      = OgPurchaseBill.get("Rate1");
			String TotalAmountFromInvoice                   = OgPurchaseBill.get("TotalAmount1");
			String PaymentDetailsFromInvoice                = OgPurchaseBill.get("PaymentDetails");
			asrt.assertEquals(ExpectedGrossWeightFromBillingPge, GrossWeightFromInvoice,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+GrossWeightFromInvoice+" in purchase bill");		
			asrt.assertEquals(OgQty22k, Double.parseDouble(NetWeightFromInvoice),1.0,"Net Weight mismatch: "+OgQty22k+" but got "+NetWeightFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedOGPurchaseRateFromItemDetailsPge, GoldRateFromInvoice,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, TotalAmountFromInvoice,"Total amount mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+TotalAmountFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, PaymentDetailsFromInvoice,"Payment details mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+PaymentDetailsFromInvoice+" in purchase bill");
		}

		// 28. Again recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));					

		// 29. Select transaction type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), SaleInvoiceWithAdvanceOGTestData.TC156_TransactionSales);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// 30. Click on process estimation button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// 31. Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		// 32. Click on amount
		// 33. Select payment method(cash/card)
		// 34. Post Sales invoice
		// Expected:Check final invoice details					
		Thread.sleep(3000);
		List<WebElement> TotalWithoutTaxCashierPge     = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(Skulist, ScannedSkuDataMap);
		String SubTotalInCashierPge                    = TransactionDataLineCashier.get("Subtotal");
		String TaxInCashierPge                         = TransactionDataLineCashier.get("Tax");
		String TotalAmountInCashierPge                 = TransactionDataLineCashier.get("TotalAmount");
		String ActualLinesCountInCashierPge            = TransactionDataLineCashier.get("LinesCount");
		List<WebElement> AllProductRowsInCashierPge    = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierPge          = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCountInCashierPge, ExpectedLinesCountInCashierPge, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCountInCashierPge + " in cashier page");

		double SubTotalSumInCashierPge = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName         = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierPge = ItemName.get(i).getText();
			if (i == 1||i==2||i==3) {
				//String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue( ExpectedItemNameInBillingPge.contains(ActualItemNameInCashierPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge + "' to contain actual '" + ActualItemNameInCashierPge + "'");
				//asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInCashierPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInCashierPge + " in billing page");
			}
			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInCashierPge    = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierPge  = ProductQtyList.get(i);
			double ActualQtyInCashierPge         = Double.parseDouble(ActualQuantityInCashierPge);
			double ExpectedQtyInCashierPge       = Double.parseDouble(ExpectedQuantityInCashierPge);
			asrt.assertEquals(ActualQtyInCashierPge, ExpectedQtyInCashierPge, 1.000, "Quantity mismatch: Expected is "+ ActualQtyInCashierPge + " but got " + ExpectedQtyInCashierPge + " in cashier page");

			List<WebElement> TotalWithoutTaxInCashierPge = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierPge     = TotalWithoutTaxCashierPge.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSumInCashierPge                      = SubTotalSumInCashierPge + Double.parseDouble(ActualTotalWithoutTaxInCashierPge);
			String ExpectedTotalWithoutTaxInCashierPge   = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierPge + " but got "+ ActualTotalWithoutTaxInCashierPge + " in cashier page");
		}
		String ExpectedSubTotalInCashierPge = String.format("%.2f", SubTotalSumInCashierPge).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierPge),Double.parseDouble(ExpectedSubTotalInCashierPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierPge + " but found " + ActualSubTotalInCashierPge+ " in billing page");

		String ActualTaxInCashierPge   = TaxInCashierPge.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxCashierPge   = ( Double.parseDouble(ProductTotalList.get(1))+ Double.parseDouble(ProductTotalList.get(2))) * 0.03;
		String ExpectedTaxInCashierPge = String.format("%.2f", ExpectedTaxCashierPge);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierPge), Double.parseDouble(ExpectedTaxInCashierPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierPge + " but found " + ActualTaxInCashierPge+ " in billing page");

		double ExpectedTotalAmountInCashierPge = Double.parseDouble(ActualSubTotalInCashierPge)+ Double.parseDouble(ActualTaxInCashierPge);
		double ActualTotalAmountInCashierPge   = Double.parseDouble(TotalAmountInCashierPge.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierPge, ExpectedTotalAmountInCashierPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		double GrossAmountForSale               = Double.parseDouble( ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2));
		String TaxableValueForSale              = String.valueOf(GrossAmountForSale);
		String AmountAdvance                    = AdvanceAmount.replaceAll("[^\\d.]", "").trim();
		double CalculatedTotalValue             = (Double.parseDouble(TaxableValueForSale.replaceAll("[^\\d.]", "").trim())*0.03)-Double.parseDouble(AmountAdvance);
		String TotalValueForSale                = String.valueOf(CalculatedTotalValue);
		String TotalQtyPcsForSale               = String.valueOf(Skulist.size());
		String TotalDiscountForSale             = Discount;
		String TotalNetWeightForSale            = TotalNetWeight;
		double TotalGrossWeight                 = Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2));
		String TotalGrossWeightForSale          = String.format("%.3f",TotalGrossWeight);
		String AdjustmentForSale                = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("PaymentsField", "h4")).replaceAll("[^\\d.]", "");
		String AmountDueForSaleFromBillingPge   = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleInFromBillingPge = Double.parseDouble(AmountDueForSaleFromBillingPge);
		String PaymentAmountForSale             = "";
		if (AmountDueForSaleInFromBillingPge==0.00) {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			PaymentAmountForSale = "";
		}
		else {
			PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");
		}
		Thread.sleep(5000);
		Double TotalNetValueForSale =Double.parseDouble(PaymentAmountForSale.replaceAll("[^\\d.]", "").trim())+Double.parseDouble(AdjustmentForSale.replaceAll("[^\\d.]", "").trim());
		String NetValue             =String.valueOf(TotalNetValueForSale);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC156_TaxInvoice");		
		String TaxInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String TotalDiamond   = String.valueOf(TotalDiamondValue);
		String PdfInvoiceNo   = pdfUtils.SaleInvoicePdfAdjustmentAdvanceValidation(
				TaxInvoice,
				String.valueOf(GrossAmountForSale),
				TotalDiscountForSale,
				TotalDiamond,
				TotalQtyPcsForSale,
				TotalNetWeightForSale,
				TotalGrossWeightForSale,
				TaxableValueForSale,
				NetValue,
				PaymentAmountForSale,
				AdjustmentForSale,
				Skulist,
				ScannedSkuDataMap
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}


}
