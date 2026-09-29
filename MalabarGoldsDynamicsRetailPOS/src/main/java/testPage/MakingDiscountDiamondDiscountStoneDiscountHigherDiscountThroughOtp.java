package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
import testData.MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.ReturnDetails;
import utilPack.AppUtilities.SKUResult;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.ErpUtilities.GPPAccountDetails;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

//import basePackage.BasePge;

public class MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj  MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj = new MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj();
	MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_TestData MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData = new MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_TestData();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();

	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj=new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();

	public MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	// <summary>
	// Test Case Title : Apply discount from billing ,then change the discount from cashier
	// Automation ID : TC213
	// Author: Chandana Babu
	// </summary>
	public void TC213_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan a SKU and click on add to cart
		//Expected: Check calculation
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_SkuGoldCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_FromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG = "";
		String ItemNameInItemDetailsge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Step 6: Click on discount button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 7: Click a normal discount.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 8: Select a discount category(Eg:Making Discount).
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_DiscountCategory);

		//Step 9: Select making discount type-Reduce Making.
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_DiscountType);

		//Step 10: Enter Discount value.
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_FirstDiscountValue);

		//Step 11: Select Category.
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_Category);

		//Step 12: Click on apply discount.
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));
		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_FirstDiscountValue))/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));

		//Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		String ActualItemNameInBillingPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInBillingPage = ItemNameInItemDetailsge.substring(ItemNameInItemDetailsge.indexOf("-") + 1).trim();
		//asrt.assertEquals(ActualItemNameInBillingPage, ExpectedItemNameInBillingPage,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");
		asrt.assertTrue(ExpectedItemNameInBillingPage.contains(ActualItemNameInBillingPage), "Item name mismatch: Expected is "+ExpectedItemNameInBillingPage+" but got "+ActualItemNameInBillingPage+" in billing page");

		String ActualQuantityInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInBillingPage = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPage), Double.parseDouble(ExpectedQuantityInBillingPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPage + " but got " + ActualQuantityInBillingPage + " in billing page");

		String ActualTotalWithoutTaxInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),ExpectedTotalWithoutTaxInBillingPage,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");

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

		//Step 13: Click on save estimate button.
		//Expected: Verify performa invoice.
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC213_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String AdditionalDiscount = ActualDiscount;
		String TotalAdjustment ="";		
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

		//Step 14: Recall estimate from cashier Screen.
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		//Step 15: Select estimate and click on recall button
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

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 16: Select transaction type as "sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_TransactionType);

		//Step 17: Click on "Process estimation" button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 18: Select any one of the SA
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 19: Click on discount button
		base.ClickCondition(LoginPageObj.Mult_Text("Cash","cash"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Cashier"));

		//Step 20: Click a normal discount.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 21: Select a discount category(Eg:Making Discount).
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_DiscountCategory);

		//Step 22: Select making discount type-Reduce Making.
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_DiscountType);

		//Step 23: Enter Discount value.
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_SecondDiscountValue);

		//Step 24: Select Category.
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_Category);

		//Step 25: Click on apply discount.		
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount")); 
		Thread.sleep(5000);

		//Note:cashierScreen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		double ExpectedDiscountInCashierScreen = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC213_SecondDiscountValue))/100;
		ExpectedDiscountInCashierScreen = Math.round(ExpectedDiscountInCashierScreen * 100.0) / 100.0;

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		String ActualItemNameInCashierScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsge.substring(ItemNameInItemDetailsge.indexOf("-") + 1).trim();
		//asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in cashier screen");
		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen), "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in cashier screen");

		String ActualQuantityInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInCashierScreen = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInCashierScreen = Double.parseDouble(TotalWithoutTax)-ExpectedDiscountInCashierScreen;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),ExpectedTotalWithoutTaxInCashierScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscountInCashierScreen,1.0,"Discount value mismatch: Expected is "+ExpectedDiscountInCashierScreen+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;
		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ ActualTaxInCashierScreen;
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		//Step 26: Click on the Amount
		//Step 27: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 28: Post the Invoice
		//Expected: Verify final Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC213SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				SkuList,
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	//<summary>
	// Test Case Title : Invoice with multiple item -apply diamond discount
	// Automation ID : TC209
	// Author : Aiswarya
	// </summary>
	public void TC209_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception
	{
		//Step	1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step	2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step	3: Select customer
		//Step	4: Click on add to estimate button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step	5: Scan multiple SKUs and click on Add to cart button
		//Expected: Check calculation
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_SkuCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_FromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");

		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> CvalueDiamond = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueDiamond.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueDIAMOND"));
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));

			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		System.out.println(CvalueDiamond);
		double TotalCValueDiamond = 0.0;

		for (String value : CvalueDiamond) {
			if (value != null && !value.trim().isEmpty() && !value.equalsIgnoreCase("null")) {
				try {
					// Remove commas or unwanted symbols just in case
					value = value.replace(",", "").trim();
					TotalCValueDiamond += Double.parseDouble(value);
				} catch (NumberFormatException e) {
					System.out.println(" Skipping invalid CvalueDIAMOND entry: " + value);
				}
			}
		}
		System.out.println("TotalCValueDiamond "+TotalCValueDiamond);

		//Step 6: In billing, Go to discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));


		List<WebElement> DiscountProductsList = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		for (WebElement DiscountProduct : DiscountProductsList ) {

			DiscountProduct.click();
			//Step 7: Select Normal discount
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

			//Step 7.1: Select diamond discount
			base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_DiscountCategory);

			//Step 7.2: Select making discount type as diamond discount
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("StnDmdDiscType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_DiscountType);

			//Step 7.3: Discount type as "Percentage"
			//Step 7.4: Add a discount value
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("stnInput"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_DiscountValue);

			//Step 7.5: Click on Apply
			base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsStone","Apply discount"));			
		}

		double FirstDiscount = Double.parseDouble(CvalueDiamond.get(0)) * (Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_DiscountValue)/100);
		double SecondDiscount = Double.parseDouble(CvalueDiamond.get(1)) *(Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_DiscountValue)/100);
		double ExpectedDiscount = Math.round((FirstDiscount + SecondDiscount)*100)/100;

		List<Double> Discount = new ArrayList<>();
		Discount.add(FirstDiscount);
		Discount.add(SecondDiscount);

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Gst = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCount, LinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+LinesCount+" in billing page");		
		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
		//	asrt.assertEquals(ActualItemNameInBillingPge,ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.0,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.\\-]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.\\-]", "").trim())-Discount.get(i)));
			asrt.assertEquals(ActualTotalWithoutTaxInBillingPge.replace("-", ""),ExpectedTotalWithoutTaxInBillingPge,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = SubTotal.replaceAll("[^\\d.\\-]", "").trim();
		asrt.assertEquals(ActualSubTotalInBillingPge,ExpectedSubTotalInBillingPge,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Gst.replaceAll("[^\\d.\\-]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = SubTotalSum*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ActualTax,ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTax;
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.\\-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 8: Click on "save to estimation"
		//Expected: Verify invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC209_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;
		double TotalWithoutGst = Double.parseDouble(ProductTotalList.get(0))+ Double.parseDouble(ProductTotalList.get(1));
		String TotalAdjustment ="";	
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProformaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				Double.toString(TotalWithoutGst), 
				ActualDiscount,
				SubTotal, 
				Gst,
				TotalAmount,
				TotalAdjustment,
				NetTotal);

		//Step 9: Click on recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");

		//Step 10: Select estimate and click on recall button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		//Step 11: Select transaction type as" sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_TransactionTypeSales);

		//Step 12: Click on "Process estimation " button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Select any one of the SA
		//Note:Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX *Total Amount
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen = TransactionDataLineInCashierScreen.get("Subtotal");
		String TotalGrossWeightInCashierScreen = TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen = TransactionDataLineInCashierScreen.get("NetWeight");
		String GstInCashierScreen = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen = TransactionDataLineInCashierScreen.get("TotalAmount");
		String NetTotalInCashierScreen = TransactionDataLineInCashierScreen.get("NetTotal");
		String LinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, LinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+LinesCountInCashierScreen+" in cashier screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
		//	asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in cashier screen");			
			asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in billing page");

			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.0,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in cashier screen");

			List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.\\-]", "").trim();
			SubTotalSumInCashierScreen = SubTotalSumInCashierScreen+Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.\\-]", "").trim())-Discount.get(i)));
			asrt.assertEquals(ActualTotalWithoutTaxInCashierScreen.replace("-", ""),ExpectedTotalWithoutTaxInCashierScreen,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in cashier screen");
		}

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen);
		String ActualSubTotalInCashierScreen = SubTotalInCashierScreen.replaceAll("[^\\d.\\-]", "").trim();
		asrt.assertEquals(ActualSubTotalInCashierScreen,ExpectedSubTotalInCashierScreen,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in cashier screen");

		String ActualTaxInCashierScreen = GstInCashierScreen.replaceAll("[^\\d.\\-]", "").trim();
		double ActualTaxInCashierScreenVal = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxInCashierScreenVal = SubTotalSumInCashierScreen*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxInCashierScreenVal);
		asrt.assertEquals(ActualTaxInCashierScreenVal,ExpectedTaxInCashierScreenVal,1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreenVal+" but found "+ActualTaxInCashierScreenVal+" in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ActualTaxInCashierScreenVal;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmountInCashierScreen.replaceAll("[^\\d.\\-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in cashier screen");

		//Step 14: Click on the Amount
		//Step 15: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 16: Post the Invoice
		//Expected: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC209_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = Double.toString(TotalWithoutGst);
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidationAdvance(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				Double.toString(TotalCValueDiamond),
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				"",
				"",
				SkuList,
				ScannedSkuDataMap);		
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}
	
	// <summary>
	// Test Case Title :Apply discount from billing,then add another discount from cashier
	// Automation ID : TC214
	// Author: Sangeetha M S
	// </summary>
	public void TC214_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception
	{	
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		Thread.sleep(1000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer 
		//Step 4: Click on add to estimate button
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_CustomerPhoneNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan SKU and click on Add to cart button
		//Expected :Check calculation-Billing Screen *No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),
		//*Displayed Subtotal,*Discounts,*TAX,*Total Amount
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		String CvalueRG = "";
		String ItemNameInItemDetailsPage ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";

		List<String> SkuGoldList = appUtils.SelectMultipleSKUs(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_SkuCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_FromCounterGold,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_MetalTypeGold);
		appUtils.HamBurgerButtonClick("iconShop");		

		for (int i = 0; i < SkuGoldList.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku = SkuGoldList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPage = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//TranscationPageVerifyLines
		Map<String, String> GoldSkuTransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuGoldList,ScannedSkuDataMap);
		String SkuSubTotal         = GoldSkuTransactionDataLine.get("Subtotal");
		String SkuDiscount         = GoldSkuTransactionDataLine.get("Discount");
		String SkuTotalGrossWeight = GoldSkuTransactionDataLine.get("GrossWeight");
		String SkuTotalNetWeight   = GoldSkuTransactionDataLine.get("NetWeight");
		String SkuTax              = GoldSkuTransactionDataLine.get("Tax");
		String SkuTotalAmount      = GoldSkuTransactionDataLine.get("TotalAmount");
		String SkuNetTotal         = GoldSkuTransactionDataLine.get("NetTotal");
		String SkuLinesCount       = GoldSkuTransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(GoldSkuTransactionDataLine, ScannedSkuDataMap);

		//Step 6: In billing, Go to discount
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		//Step 7: Select Normal discount
		//Step 7.1.Select making discount
		//Step 7.2.Select making discount type as "Reduce making percentage"
		//Step 7.3.Discount type as "Percentage"
		//Step 7.4.Add a discount value
		//Step 7.5.Select a category
		//Step 7.6.Click on Apply
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_DiscountCategory);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_DiscountType);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_FirstDiscountValue);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_Category);
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));
		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_FirstDiscountValue))/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuGoldList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		String ActualItemNameInBillingPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText")).split(" - ")[0].trim();;
		String ExpectedItemNameInBillingPage = ItemNameInItemDetailsPage.split(" - ")[1].trim();
		asrt.assertTrue(ExpectedItemNameInBillingPage.contains(ActualItemNameInBillingPage),"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");

		String ActualQuantityInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInBillingPage = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPage), Double.parseDouble(ExpectedQuantityInBillingPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPage + " but got " + ActualQuantityInBillingPage + " in billing page");

		String ActualTotalWithoutTaxInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),ExpectedTotalWithoutTaxInBillingPage,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");

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

		//Step 8.Click on "save to estimate"
		//Step 9.Click on "save to estimation"
		//Expected : Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(2000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC214_ProformaInvoice");
		Thread.sleep(1000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String AdditionalDiscount = ActualDiscount;
		String TotalAdjustment ="";	
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

		//Step 10.Click on recall estimate from cashier screen
		//Step 11.Select estimate and click on recall button
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		List<String> ProductNames             = new ArrayList<>();
		ProductNames.add(ActualItemNameInBillingPage);
		List<String> ProductQuantity            = new ArrayList<>();
		ProductQuantity.add(ActualQuantityInBillingPage);
		List<String> ProductGrossWt            = new ArrayList<>();
		ProductGrossWt.add(TotalGrossWeight);

		appUtils.RecallEstimateItemValidation(
				ProductNames,        
				ProductQuantity,             
				ProductNames,      
				ProductGrossWt,   
				Double.parseDouble(ActualSubTotalInBillingPage.replaceAll("[^\\d.]", "").trim()),
				Double.parseDouble(Gst.replaceAll("[^\\d.]", "").trim()),
				Double.parseDouble(InvoiceTotal.replaceAll("[^\\d.]", "").trim()),
				Integer.parseInt(LinesCount.replaceAll("[^\\d.]", "").trim()),
				"Recall"                    
				);

		//Step  12.Select transaction type as" sales"
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_TransactionType);

		//Step  13.Click on "Process estimation " button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 14.Select any one of the SA
		//base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Note:Cashier Screen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> AllProductsRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesOfCountInCashierScreen = Integer.toString(AllProductRows.size());
		String ActualLinesOfCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesOfCountInCashierScreen, ExpectedLinesOfCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesOfCountInCashierScreen + " but found " + ActualLinesOfCountInCashierScreen + " in cashier screen");

		String ActualItemNameInCashierPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInCashierPage = ItemNameInItemDetailsPage.trim();
		asrt.assertTrue(ExpectedItemNameInCashierPage.contains(ActualItemNameInCashierPage),"Item name mismatch: Expected is " + ExpectedItemNameInCashierPage + " but got "+ ActualItemNameInCashierPage + " in cashier screen");

		String ActualQuantityInCashierPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInCashierPage = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierPage), Double.parseDouble(ExpectedQuantityInCashierPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierPage + " but got " + ActualQuantityInCashierPage + " in cashier screen");

		String ActualTotalWithoutTaxInCashierPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ActualTotalAmountInCashierPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInCashierPage = Double.parseDouble(ActualSubTotalInCashierPage);//(TotalWithoutTax)-ExpectedDiscountInCashierScreen;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierPage.replace("-", "")),ExpectedTotalWithoutTaxInCashierPage,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierPage+" but got "+ActualTotalWithoutTaxInCashierPage+ " in cashier screen");

		String ExpectedSubTotalInCashierPage = ActualTotalWithoutTaxInCashierPage.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierPage),Double.parseDouble(ExpectedSubTotalInCashierPage), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierPage + " but found " + ActualSubTotalInCashierPage+ " in cashier screen");

		String ActualDiscountInCashierPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedDiscountInCashierPage=Double.parseDouble(ActualDiscount);
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierPage), ExpectedDiscountInCashierPage,1.0,"Discount value mismatch: Expected is "+ExpectedDiscountInCashierPage+" but got "+ActualDiscountInCashierPage+" in cashier screen");

		String TaxInCashierPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierPage = Double.parseDouble(TaxInCashierPage);
		double ExpectedTaxInCashierPage = Double.parseDouble(ActualSubTotalInCashierPage)*0.03;
		asrt.assertEquals(ActualTaxInCashierPage,ExpectedTaxInCashierPage, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierPage + " but found " + ActualTaxInCashierPage+ " in cashier screen");

		double ExpectedTotalAmountInCashierPage = Double.parseDouble(ActualSubTotalInCashierPage)+ ActualTaxInCashierPage;
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierPage), ExpectedTotalAmountInCashierPage, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierPage + " but found " + ActualTotalAmountInCashierPage+ " in cashier screen");

		//Step 15.Go to discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Cash "));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Cashier"));

		//Step 16.Select Normal discount
		//Step 16.1.Select Round off discount
		//Step 16.2.Enter a Discount value
		//Step 16.3.Click on "Apply Discount"
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));		
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_RoundOffDiscountCategory);		
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("RoundedOff"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("RoundedOff"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC214_SecondDiscountValue);		
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyRoundOffDiscount("text: buttontxtCalcskuaging, event: { click: calcRoundOff }","Apply discount"));
		Thread.sleep(2000);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");
		System.out.println("PaymentAmount   "+PaymentAmount)	;

		//Step 19: Post the Invoice
		//Expected: Verify final Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC214SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;		
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;

		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				SkuGoldList,
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();		
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));	
	}

	// <summary>
	// Test Case Title : Apply discount on a bill having OG adjustment
	// Automation ID : TC219
	// Author: Christy Reji
	// </summary>
	public void TC219_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan a SKU and click on add to cart
		//Expected: Check calculation
		//Step 6: Click on add to Cart button
		//Step 7: Select any one of the product in cart
		List<String> ProductNames   = new ArrayList<>();
		List<String> ProductGross   = new ArrayList<>();
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_FromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_MetalType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_ReqQty,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_SkuGoldCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                = "";
		String ItemNameInItemDetailsge ="";
		String GrossWeight             = "";
		String TotalWithoutTax         = "";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku    = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG                           = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsge            = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight                        = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax                    = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLineGold = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalGold         = TransactionDataLineGold.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String DiscountGold         = TransactionDataLineGold.get("Discount");
		String TotalGrossWeightGold = TransactionDataLineGold.get("GrossWeight");
		String TotalNetWeightGold   = TransactionDataLineGold.get("NetWeight");
		String TaxGold              = TransactionDataLineGold.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmountGold      = TransactionDataLineGold.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotalGold         = TransactionDataLineGold.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCountGold       = TransactionDataLineGold.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLineGold, ScannedSkuDataMap);

		//Step 8: Click on discount button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 9: Click a normal discount.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 10: Select a discount category(Eg:Making Discount).
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_DiscountCategory);

		//Step 11: Select discount range
		//Step 12: Enter discount percentage
		//Step 13: Click on apply button
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_DiscountType);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_FirstDiscountValue);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_Category);
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));

		//Expected : Check Calculation
		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_FirstDiscountValue))/100;
		ExpectedDiscount        = Math.round(ExpectedDiscount * 100.0) / 100.0;
		Thread.sleep(3000);
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotals                        = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");
		String ActualTotalWithoutTaxInBillingPage   = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ActualDiscount                       = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;
		double ExpectedSubTotalInBillingPage        = Double.parseDouble(SubTotalGold)-ExpectedDiscount;
		double ExpectedTaxInBillingPage             = Double.parseDouble(SubTotal)*0.03;
		double ExpectedTotalAmountInBillingPage     = Double.parseDouble(SubTotal)+ Double.parseDouble(Tax);
		Thread.sleep(3000);

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),ExpectedTotalWithoutTaxInBillingPage,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");
		asrt.assertEquals(Double.parseDouble(SubTotal),ExpectedSubTotalInBillingPage, 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPage + " but found " + SubTotal+ " in billing page");
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");
		asrt.assertEquals(Double.parseDouble(Tax),ExpectedTaxInBillingPage, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPage + " but found " + Tax+ " in billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmountInBillingPage, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPage + " but found " + TotalAmount + " in billing page");

		//Step 14: Select OG
		//Step 15:Click on OG own, Choose Exchange
		//Step 16: Select Configuration
		//Step 17:Click on Add item button
		//Step 18: Select QC person
		//Step 19: Select Smith person
		//Step 20:Enter piece value
		//Step 21:Enter gross weight
		//Step 22: Click on Calculate button
		//Step 23: Click on add to cart button
		//Expected :Check Exchange rate for OG  * Check calculation
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");

		double ActualOgtRateInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		System.out.println("OGT Rate : "+ActualOgtRateInItemDetailsPge);
		String ExpectedGrossWeightInItemDetailsPge    = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String OgtTotalAmountInItemDetailsPge         = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));	
		String OgtItemName                            = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ExpectedItemNameInOgtItemDetailsPge    = OgtItemName.substring(OgtItemName.indexOf("-") + 2);
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));

		//Note:Billling Screen  *No of Product lines *Displayed Item Name *Displayed Quantity
		//*Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		// OG Own Item Validation in Billing Page
		List<WebElement> ItemNames  = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		List<WebElement> ItemQtys   = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-QuantityField textRight"));
		List<WebElement> ItemTotals = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));

		boolean OgtItemFound        = false;
		double OgtQty               = 0.0, OgtTotal = 0.0;
		double LastNonOgtItemTotal  = 0.0;
		int TotalLinesBillingPage   = ItemNames.size();

		// Validate OGT Item Name, Quantity, and Total (Without Tax)
		for (int i = 0; i < ItemNames.size(); i++) {
			String ItemName        = ItemNames.get(i).getText().trim();
			String QtyStr          = ItemQtys.get(i).getText().trim().replaceAll("[^\\d.]", "");
			String TotalStr        = ItemTotals.get(i).getText().trim().replaceAll("[^\\d.]", "");
			double Qty             = QtyStr.isEmpty() ? 0.0 : Double.parseDouble(QtyStr);
			double ItemTotal       = TotalStr.isEmpty() ? 0.0 : Double.parseDouble(TotalStr);

			if (ItemName.equalsIgnoreCase(ExpectedItemNameInOgtItemDetailsPge)) {
				OgtItemFound       = true;
				OgtQty             = Qty;
				OgtTotal           = ItemTotal;

				System.out.println("OGT total transaction page: " + OgtTotal);
				Assert.assertEquals(ItemName, ExpectedItemNameInOgtItemDetailsPge, "OGT Item Name mismatch from OGT Exchange page :"+ItemName+"but found"+ExpectedItemNameInOgtItemDetailsPge+"in Billing page");
				asrt.assertEquals(OgtTotal, Double.parseDouble(OgtTotalAmountInItemDetailsPge), 1,"OGT total without tax mismatch from OGT Exchange page: " + OgtTotal + " but found " + Double.parseDouble(OgtTotalAmountInItemDetailsPge) + " in Billing page");
			} else {
				LastNonOgtItemTotal = ItemTotal; 
				ProductNames.add(ItemName);// capture last non-OG item total
			}

		}

		if (!OgtItemFound) {
			System.out.println("OG Own  item not found in the Billing Page.");
		}

		double CalculatedSubTotal  =   Double.parseDouble(SubTotal)-OgtTotal;
		double ExpectedSubTotal    = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", ""));
		CalculatedSubTotal         = Math.abs(CalculatedSubTotal);
		double ExpectedTax         = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", ""));
		double ExpectedTotal       = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", ""));
		double CalculatedTotal     = ExpectedSubTotal + ExpectedTax;
		asrt.assertEquals(CalculatedSubTotal, ExpectedSubTotal, 1, "Subtotal mismatch from Itemdetails page Expected: "+ExpectedSubTotal+" but found : "+CalculatedSubTotal+" in billing page");
		asrt.assertEquals(Double.parseDouble(Tax), ExpectedTax, 1, "The tax value mismatch from  Itemdetails page Expected: "+ExpectedTax+ "but found :" +Tax+" in billing page");
		asrt.assertEquals(ExpectedTotal, CalculatedTotal, 1, "The total amount mismatch from  Itemdetails page Expected: "+ExpectedTotal+ "but found :" +CalculatedTotal+" in billing page");

		//Step  25. Save to estimate
		//Expected : Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = Double.toString(LastNonOgtItemTotal);
		String TotalAmnt           = Double.toString(ExpectedSubTotal);
		String Gst                 =  Tax;
		String InvoiceTotal        =  Double.toString(CalculatedTotal);
		String NetTotal            =  Double.toString(CalculatedTotal);
		Thread.sleep(3000);
		String ProformaPdfName     = pdfUtils.DownloadAndRenameLatestPDF("TC219_ProformaInvoice");
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				);

		//Step 26. Recall estimate from cashier
		//step 27: Select estimate and click on Recall estimation button
		//Expected : Verify whether the recalled item is the same as the item in the cart. Note:Cashier Screen after recall
		// *No of Product lines,*Displayed Item Name, *Displayed Quantity *Displayed TOTAL (without Tax)*Displayed Subtotal *TAX*Total Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 


		// Validation
		ProductGross.add(TotalGrossWeight);
		appUtils.ValidateRecallPageOGItem(ProductNames,ProductGross,ExpectedItemNameInOgtItemDetailsPge,ExpectedGrossWeightInItemDetailsPge
				,ExpectedSubTotal,ExpectedTax,ExpectedTotal,TotalLinesBillingPage,"OG Own Discount");

		//Step 28: Select transaction type as Exchange
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_TransactionTypeOG);

		//Step 29.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 30.Choose a sales representative[Step not required due new application change]
		//Step 31:  Click on amount 0.00	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(2000);
		base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));


		//Step 32:Post the exchange invoice
		//Expected :  Check Exchange invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		//validation
		Map<String, String> PurchaseBillData = pdfUtils.OGPdfValidation("Adjustment");
		String ExchngeAdjustment             = String.format("%.2f", Double.parseDouble(PurchaseBillData.get("PaymentDetails").trim()));
		String GrossWeightFromInvoice        = PurchaseBillData.get("GrossWt1").split("\\.")[0];
		String NetWeightFromInvoice          = PurchaseBillData.get("NetWt1").split("\\.")[0];;
		String OGRateFromInvoice           = String.format("%.1f", Double.parseDouble(PurchaseBillData.get("Rate1").trim()));
		String TotalAmountFromInvoice        = String.format("%.1f", Double.parseDouble(PurchaseBillData.get("TotalAmount1").trim()));
		String PaymentDetailsFromInvoice     = String.format("%.1f", Double.parseDouble(PurchaseBillData.get("PaymentDetails").trim()));
		String OgtGrossWt                    = ExpectedGrossWeightInItemDetailsPge.replaceFirst("^0+(?!$)", "");	

		asrt.assertEquals(Double.parseDouble(ExchngeAdjustment), OgtTotal, 1, "Mismatch in value OGPdf adjustment:"+ExchngeAdjustment+"but got" +OgtTotal+"rom billing page");
		asrt.assertEquals(GrossWeightFromInvoice, OgtGrossWt,"Mismatch in value OGPdf adjustment:"+GrossWeightFromInvoice+"but got" +OgtGrossWt+"rom billing page");
		asrt.assertEquals(NetWeightFromInvoice, OgtGrossWt,"Mismatch in value OGPdf adjustment:"+NetWeightFromInvoice+"but got" +OgtGrossWt+"rom billing page");	
		asrt.assertEquals(Double.parseDouble(OGRateFromInvoice), ActualOgtRateInItemDetailsPge,1,"Mismatch in value OGPdf adjustment:"+Double.parseDouble(ExchngeAdjustment)+"but got" +ActualOgtRateInItemDetailsPge+"rom billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmountFromInvoice), OgtTotal,1,"Mismatch in value OGPdf adjustment:"+Double.parseDouble(TotalAmountFromInvoice)+"but got" +OgtTotal+"rom billing page");
		asrt.assertEquals(Double.parseDouble(PaymentDetailsFromInvoice), OgtTotal,1,"Mismatch in value OGPdf adjustment:"+Double.parseDouble(PaymentDetailsFromInvoice)+"but got" +OgtTotal+"rom billing page");

		//Step 33. Again recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Step 34. Select transaction type as sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC219_TransactionType);

		//Step 35: Click on process estimation button[sale rep not required]
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 36:Click on the Amount
		// Step 37:Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		//Step 38 : Post the sale invoice
		//Check the final ivoice details-Sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice    = pdfUtils.DownloadAndRenameLatestPDF("TC219_FinalSaleInvoice");
		Thread.sleep(3000);		
		String SaleInvoicePath =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";

		String GrossAmount     = SubTotalGold;
		String TotalDiscount   = Double.toString(ExpectedDiscount);
		String TotalQtyPcs     = LinesCountGold;
		String Adjustment      = ExchngeAdjustment;
		String PaymentHDFCCard = PaymentAmount;
		String SalesPdfPath    = SaleInvoicePath;
		TotalNetWeight         = TotalNetWeight;
		TotalGrossWeight       = TotalGrossWeight;
		double ConvertNetValue = Double.parseDouble(NetTotals);
		String NetValue        = String.format("%.2f", ConvertNetValue);
		NetValue               = NetValue.replaceAll("^0+(?!$)", "").trim();
		Thread.sleep(3000);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,          
				PaymentHDFCCard,
				Adjustment,
				SkuList,
				ScannedSkuDataMap
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Apply discount on bulk item sale
	// Automation ID : TC215
	// Author: Robin T Abraham
	// </summary>
	public void TC215_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search a customer with Phone number
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(5000);

		//Step 5: Scan BC
		//Step 6: Select back chain
		//Expected: Check calculation
		//Step 7: Select a configuration and click on ""Add item""
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_BC);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "BC"));
		Thread.sleep(6000);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), UtilityTestData.Purity22);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8: Add a gross weight.
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_RequiredQuantityBc);

		//Step 9: Click on "Add to cart"
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);
		String BcItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		String BcTotalAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		String CvalueRG = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("dataListLine","column5 textRight"));
		String BcRate = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("dataListLine","column4 textRight"));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		double ExpectedBcCvalue = Double.parseDouble(BcRate) * Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_RequiredQuantityBc);

		asrt.assertEquals( Double.parseDouble(CvalueRG), ExpectedBcCvalue,1,"Cvalue mismatch: Expected is " + ExpectedBcCvalue + " but got "+ CvalueRG + "in Item details page");

		String WastageQty = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: WsDiscQty"));
		String WastageAmt = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: WsDiscAmt"));
		double ExpectedWastageAmt = (Double.parseDouble(WastageQty)/100) * Double.parseDouble(CvalueRG);
		asrt.assertEquals(Double.parseDouble(WastageAmt), ExpectedWastageAmt,1, "Wastage amount mismatch: Expected is "+ ExpectedWastageAmt + " but found " + WastageAmt + "in Item details page");

		String ActualTotalAmt = base.GetValue(CustomerCreationObj.Ele_Name("totalInput"));
		double ExpectedTotalAmt = Double.parseDouble(CvalueRG) + Double.parseDouble(WastageAmt);
		asrt.assertEquals(Double.parseDouble(ActualTotalAmt), ExpectedTotalAmt,1, "Total amount mismatch: Expected is "+ ExpectedTotalAmt + " but found " + ActualTotalAmt + "in Item details page");

		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));

		//Step 10: Choose a sales representative
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Step 11: In billling, Go to discount->Normal discount->Select making discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 11.1: Select making discount type as ""Reduce making percentage""
		//Step 11.2: Discount type as ""Percentage""
		//Step 11.3: Add a discount value
		//Step 11.4: Select a category
		Thread.sleep(3000);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_DiscountCategory);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_DiscountType);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_DiscountValue);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_Category);

		//Step 12: Click on Apply
		//Expected: Check calculation
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount")); 
		Thread.sleep(5000);
		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_DiscountValue))/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		List<String> ItemName = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInBillingPage = ItemName.get(0);
		String ExpectedItemNameInBillingPage = BcItemName.split(" - ")[1].trim();
		//asrt.assertEquals(ActualItemNameInBillingPage, ExpectedItemNameInBillingPage,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");
		asrt.assertTrue(ExpectedItemNameInBillingPage.trim().contains(ActualItemNameInBillingPage),"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");

		List<String> Quantity = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ActualQuantityInBillingPage = Quantity.get(0);
		String ExpectedQuantityInBillingPage = MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_RequiredQuantityBc;

		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPage), Double.parseDouble(ExpectedQuantityInBillingPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPage + " but got " + ActualQuantityInBillingPage + " in billing page");

		List<String> TotalWithoutTax = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String ActualTotalWithoutTaxInBillingPage = TotalWithoutTax.get(0).replaceAll("[^\\d.]", "").trim();
		String ExpectedTotalWithoutTaxInBillingPage =Double.toString( Double.parseDouble(BcTotalAmount)-ExpectedDiscount);
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPage),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");

		String ExpectedSubTotalInBillingPage = ActualTotalWithoutTaxInBillingPage.replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPage),Double.parseDouble(ExpectedSubTotalInBillingPage), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPage + " but found " + ActualSubTotalInBillingPage+ " in billing page");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		String TaxInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInBillingPage = Double.parseDouble(TaxInBillingPage);
		double ExpectedTaxInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)*0.03;
		asrt.assertEquals(ActualTaxInBillingPage,ExpectedTaxInBillingPage, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPage + " but found " + ActualTaxInBillingPage+ " in billing page");

		double ExpectedTotalAmountInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)+ ActualTaxInBillingPage;
		String ActualTotalAmountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInBillingPage), ExpectedTotalAmountInBillingPage, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPage + " but found " + ActualTotalAmountInBillingPage + " in billing page");

		//Step 13: Click on ""save to estimate""
		//Step 14: Click on ""save to estimation""
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(By.xpath("//button[@title='Back' and contains(@aria-describedby,'BDisc')]"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC215_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= TaxInBillingPage;
		String ExpectedTaxableValue =ActualSubTotalInBillingPage;
		String InvoiceTotal=ActualTotalAmountInBillingPage;
		String AdditionalDiscount = ActualDiscount;
		String LinesCount = "";
		String NetTotal = ActualTotalAmountInBillingPage;	
		String TotalAdjustment ="";	
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_RequiredQuantityBc),
				Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_RequiredQuantityBc),
				BcTotalAmount,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		//Step 15: Click on recall estimate from cashier screen
		//Step 16: Select estimate and click on recall button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		//Note:Cashier Screen after recall
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),"ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 17: Select transaction type as "sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_TransactionType);

		//Step 18: Click on "Process estimation" button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		List<String> ItemNameInCashierScreen = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(0);
		String ExpectedItemNameInCashierScreen = BcItemName.split(" - ")[1].trim();
		//asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in cashier screen");
		asrt.assertTrue(ExpectedItemNameInCashierScreen.trim().contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in cashier page");

		List<String> QuantityInCashierScreen = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(0);
		String ExpectedQuantityInCashierScreen = MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC215_RequiredQuantityBc;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in cashier screen");

		List<String> TotalWithoutTaxInCashierScreen = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(0).replaceAll("[^\\d.]", "").trim();
		String ExpectedTotalWithoutTaxInCashierScreen = Double.toString(Double.parseDouble(BcTotalAmount)-ExpectedDiscount);
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")), Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), 1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen), Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscount, 1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;
		asrt.assertEquals(ActualTaxInCashierScreen, ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen) + ActualTaxInCashierScreen;
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		//Step 19: Select any one of the SA
		//Step 20: Click on total amount, select cash or card.
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");
		System.out.println("PaymentAmount to be:"+PaymentAmount);

		//Step 21: Post invoice
		//Expected: Check final invoice details
		//save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC215SaleInvoice");
		Thread.sleep(4000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = BcTotalAmount;
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=ActualLinesCountInBillingPage;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;
		String Adjustment= "";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationForServiceItem(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TaxableValue,
				NetValue,
				PaymentAmount,
				Adjustment);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Apply discount on scheme adjusted bill
	// Automation ID : TC217
	// Author: Chandana Babu
	// </summary>
	public void TC217_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		//*******************************
		////Step 2: Click on Transaction button
		//base.setZoom(driver, 60);
		//appUtils.HamBurgerButtonClick("iconShop");
		//**********************************

		//Prerequisite Customer must have a matured GB scheme
		Thread.sleep(3000);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_RequiredSettlement,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_AccountCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_SchemeCode,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_Matured,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_Adjusted);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"22k");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double CurrentGoldRate22k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
		System.out.println("CurrentGoldRate22k "+CurrentGoldRate22k);
		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found"))){
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));		
		}

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerAccount="";
		String GPPAccountNumber="";
		String GPPAmount="";
		String SkuPurityReturn="";
		String TotalRgWeightReturn="";
		String SkuGoldRateReturn="";
		boolean validAccountFound = false;
		for (int idx = 0; idx < MatchedAccounts.size(); idx++) {
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerAccount = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			//GPPAmount = (String) GPPMaturedAccountDetails.get("GppAmount");
			GPPAmount = AccountMap.get("GppAmount");

			//Step 3: Select customer
			//Step 4: Click on add to estimation button
			appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

			//Step 5: Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			//Step 6: click on GPP advance settlement
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			//Step 7: Enter scheme id(GB with partial benefit) and click on add to cart button
			//Expected: Verify whether the entered GB scheme gets added 
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
			Thread.sleep(2000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
			// Check if "No data" is displayed
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) {
				System.out.println("No data for account: " + GPPAccountNumber + ". Retrying with next...");
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue; // go to next MatchedAccounts entry
			}
			System.out.println("Valid GPP account found: " + GPPAccountNumber);
			validAccountFound = true;
			break;
		}
		if (!validAccountFound) {
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}
		appUtils.AddOrVerifyPan();
		//**********************************
		////Step 3: Select customer
		////Step 4: Click on add to estimation button
		//appUtils.SearchByCustomerID("9868911081",UtilityTestData.MenuBarOptions[5]);
		//
		////Step 5: Click on customer adjustment button
		//base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		//base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		//
		////Step 6: Click on GPP Advance Adjustment
		//base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));
		//
		////Step 7: Enter scheme id(GG) and click on "Add to cart" button
		////Expected :Verify whether the entered GG scheme gets add
		//base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		//base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"33GB000001326");
		//base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"ENTER");
		//base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		//**********************************

		Thread.sleep(5000);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		double SchemeAmount =Double.parseDouble(ActualSchemeAmount);		
		double BenefitWeightFor22k = SchemeAmount/CurrentGoldRate22k;
		double CurrentGoldRate18k =(CurrentGoldRate22k*18)/22;
		double BenefitWeightFor18k = SchemeAmount/CurrentGoldRate18k;
		double RequiredWeight =0.0;
		if(BenefitWeightFor22k>BenefitWeightFor18k) {
			RequiredWeight= BenefitWeightFor22k;
		}
		if(BenefitWeightFor22k<BenefitWeightFor18k) {
			RequiredWeight= BenefitWeightFor18k;
		}

		//Step 8: Enter OTP
		//Step 9: Scan multiple category SKU
		//Expected: Check calculation
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_FromCounterGold,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_MetalTypeGold,
				RequiredWeight,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double ActualRgRateInItemDetailsPge =0.0;
		String CvalueRG = "";
		String TotalGrossWeight ="";
		String TotalAmountWithoutTax= "";
		ProductNames.add(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_SchemeName);
		ProductQtyList.add(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_Weight);
		ProductTotalList.add(GPPAmount);
		int SkuCounter = 1;	
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueRG");
			TotalGrossWeight = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight");
			TotalAmountWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		//Note: Check average rate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(30000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","4"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		System.out.println("ActualBonus: "+ActualBonus);

		double BenefitWeight = SchemeAmount / ActualRgRateInItemDetailsPge;
		double ExpectedBonus = Double.parseDouble(mathUtils.ToGetGppBonus(
				ScannedSkuDataMap,
				SkuList.size(),
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_BenefitPercent,
				BenefitWeight,
				CurrentGoldRate22k,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_CurrentGoldPurity));
		ExpectedBonus = Math.round(ExpectedBonus * 100.0) / 100.0;
		asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ExpectedBonus+" but got "+ActualBonus+" in billing page");

		ProductNames.add(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_BonusName);
		ProductQtyList.add(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_Weight);
		ProductTotalList.add(Bonus);

		//Step 11: In billing, Go to discount->Normal discount->Select making discount
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));		
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));		
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_DiscountCategory);

		//Step 11.1: Select making discount type as "Reduce making percentage"
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_MakingDiscountType);

		//Step 11.2: Discount type as "Percentage"
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrMakingDiscSubType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_DiscountType);

		//Step 11.3: Add a discount value
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_DiscountValue);

		//Step 11.4: Select a category
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_Category);

		//Step 12: Click on Apply
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));
		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_DiscountValue))/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount,ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				//asrt.assertEquals(ActualItemNameInBillingPge,ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge), "Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
			}
			else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInBillingPge,ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
			}
			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInBillingPge);
			if (i==1) {
				String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim())-ExpectedDiscount);
				asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
			}
			else {
				String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
				asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
			}
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		String ActualTax = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxInBillingPge = Double.parseDouble(ActualTax);
		double ExpectedTaxInBillingPge = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())-ExpectedDiscount)*0.03;
		String ExpectedTax = String.format("%.2f",ExpectedTaxInBillingPge);
		asrt.assertEquals(ActualTaxInBillingPge,ExpectedTaxInBillingPge,1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxInBillingPge;
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 13: save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC217_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue = Double.toString(Double.parseDouble(TotalAmountWithoutTax)-Double.parseDouble(ActualDiscount));
		String InvoiceTotal= Double.toString(Double.parseDouble(ExpectedTaxableValue)+ExpectedTaxInBillingPge);
		String AdditionalDiscount = ActualDiscount;
		String TotalAdjustment = Double.toString(SchemeAmount+ActualBonus);
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

		//Step 14: Recall estimate from cashier screen
		//Step 15: Select estimate and click on save to estimate button
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

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 16: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC217_TransactionType);

		//Step 17: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 18: Choose a sales representative		
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 	= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 	    = TransactionDataLineInCashierScreen.get("NetTotal");
		String TotalGrossWeightInCashierScreen 	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 				= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 		= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen	= TransactionDataLineInCashierScreen.get("LinesCount");
		double ExpectedDiscountInCashierScreen = ExpectedDiscount;

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in Cashier Screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				//asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");			
				asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen), "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");
			}
			else {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");
			}
			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in Cashier Screen");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSumInCashierScreen = SubTotalSumInCashierScreen+Double.parseDouble(TotalWithoutTaxInCashierScreen);
			if(i==1) {
				String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim())-ExpectedDiscountInCashierScreen);
				asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");	
			}
			else {
				String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
				asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");	
			}
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in Cashier Screen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscountInCashierScreen,1.0,"Discount value mismatch: Expected is "+ExpectedDiscountInCashierScreen+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxFromCashierScreen = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())-ExpectedDiscount)*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxFromCashierScreen);
		asrt.assertEquals(ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxFromCashierScreen;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen"); 

		//Step 19: Click on the Amount
		//Step 20: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 21: Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC217TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ProductTotalList.get(1);
		String TotalDiscount=Double.toString(ExpectedDiscountInCashierScreen);
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = Double.toString(Double.parseDouble(ExpectedTaxableValue.replaceAll("[^\\d.]", "").trim())+Double.parseDouble(TaxInCashierScreen.replaceAll("[^\\d.]", "").trim()));
		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String TaxableValue = ExpectedTaxableValue;
		Map<String, String> PdfDetails = pdfUtils.ValidateTaxInvoiceSchemeSettlementPdf(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				TotalValue,
				PaymentHDFCCard,
				GppAdvance,
				GppDiscount,
				TotalAdjustment,
				NetValue);	
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	//<summary>
	// Test Case Title : Invoice with multiple item -apply stone discount
	// Automation ID : TC210
	// Author : Neethu
	// </summary>
	public void TC210_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception
	{
		//Step	1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step	2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step	3: Select customer
		//Step	4: Click on add to estimate button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan multiple SKUs and click on Add to cart button
		//Expected Result: Check calculation
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> Cvalue_STONES = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();

		List<String> SkuList = appUtils.GetVirazSkus(Utility_TestData.UncutVirazSkus, 2);

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku =SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			Cvalue_STONES.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueSTONES"));
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));

			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 6: In billing, Go to discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));


		List<WebElement> DiscountProductsList = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		for (WebElement DiscountProduct : DiscountProductsList ) {


			DiscountProduct.click();
			//Step 7: Select Normal discount
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

			//Step 7.1: Select diamond discount
			base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_DiscountCategory);

			//Step 7.2: Select making discount type as stone discount
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("StnDmdDiscType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_DiscountType);

			//Step 7.3: Discount type as "Percentage"
			//Step 7.4: Add a discount value
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("stnInput"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_DiscountValue);

			//Step 7.5: Click on Apply
			base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsStone","Apply discount"));			

		}
		double FirstDiscount = Double.parseDouble(Cvalue_STONES.get(0)) * (Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_DiscountValue)/100);
		double SecondDiscount = Double.parseDouble(Cvalue_STONES.get(1)) *(Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_DiscountValue)/100);
		double ExpectedDiscount = Math.round((FirstDiscount + SecondDiscount)*100)/100;


		List<Double> Discount = new ArrayList<>();
		Discount.add(FirstDiscount);
		Discount.add(SecondDiscount);	
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Gst = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");	

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCount, LinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+LinesCount+" in billing page");		
		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			asrt.assertEquals(ActualItemNameInBillingPge,ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			


			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.0,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.\\-]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.\\-]", "").trim())-Discount.get(i)));
			asrt.assertEquals(ActualTotalWithoutTaxInBillingPge.replace("-", ""),ExpectedTotalWithoutTaxInBillingPge,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = SubTotal.replaceAll("[^\\d.\\-]", "").trim();
		asrt.assertEquals(ActualSubTotalInBillingPge,ExpectedSubTotalInBillingPge,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Gst.replaceAll("[^\\d.\\-]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = SubTotalSum*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ActualTax,ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTax;
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.\\-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");


		//Step 8: Click on "save to estimation"
		//Step 9: Click on "save to estimation"
		//Expected: Verify invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC210_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;
		double TotalWithoutGst = Double.parseDouble(ProductTotalList.get(0))+ Double.parseDouble(ProductTotalList.get(1));
		String TotalAdjustment ="";	
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProformaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				Double.toString(TotalWithoutGst), 
				ActualDiscount,
				SubTotal, 
				Gst,
				TotalAmount,
				TotalAdjustment,
				NetTotal);

		//Step 10: Click on recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//Step 11: Select estimate and click on recall button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 


		//Step 12: Select transaction type as" sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC209_TransactionTypeSales);

		//Step 13: Click on "Process estimation " button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 14: Select any one of the SA
		//Note:Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX *Total Amount
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}


		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen = TransactionDataLineInCashierScreen.get("Subtotal");
		String TotalGrossWeightInCashierScreen = TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen = TransactionDataLineInCashierScreen.get("NetWeight");
		String GstInCashierScreen = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen = TransactionDataLineInCashierScreen.get("TotalAmount");
		String NetTotalInCashierScreen = TransactionDataLineInCashierScreen.get("NetTotal");
		String LinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, LinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+LinesCountInCashierScreen+" in cashier screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in cashier screen");			

			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.0,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in cashier screen");

			List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.\\-]", "").trim();
			SubTotalSumInCashierScreen = SubTotalSumInCashierScreen+Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.\\-]", "").trim())-Discount.get(i)));
			asrt.assertEquals(ActualTotalWithoutTaxInCashierScreen.replace("-", ""),ExpectedTotalWithoutTaxInCashierScreen,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in cashier screen");
		}

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen);
		String ActualSubTotalInCashierScreen = SubTotalInCashierScreen.replaceAll("[^\\d.\\-]", "").trim();
		asrt.assertEquals(ActualSubTotalInCashierScreen,ExpectedSubTotalInCashierScreen,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in cashier screen");

		String ActualTaxInCashierScreen = GstInCashierScreen.replaceAll("[^\\d.\\-]", "").trim();
		double ActualTaxInCashierScreenVal = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxInCashierScreenVal = SubTotalSumInCashierScreen*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxInCashierScreenVal);
		asrt.assertEquals(ActualTaxInCashierScreenVal,ExpectedTaxInCashierScreenVal,1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreenVal+" but found "+ActualTaxInCashierScreenVal+" in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ActualTaxInCashierScreenVal;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmountInCashierScreen.replaceAll("[^\\d.\\-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in cashier screen");

		//Step 15: Click on the Amount
		//Step 16: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 17: Post the Invoice
		//Expected: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC210_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = Double.toString(TotalWithoutGst);
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ActualSubTotalInCashierScreen;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				SkuList,
				ScannedSkuDataMap);


		//Sales return and counter in flow
		appUtils.SalesReturnFlow(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_CustomerID, PdfInvoiceNo,MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_ReturnProducts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Error"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		Thread.sleep(5000);

		appUtils.CounterFlow(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_ToCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC210_MetalType,
				SkuList,
				UtilityTestData.Terminal);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Apply discount on advance adjusted bill
	// Automation ID : TC216
	// Author: Nandagopan
	// </summary>

	public void TC216_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Pre-Requsite-Customer must have an advance
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_CustomerNo,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_CustomerNo,
				UtilityTestData.MenuBarOptions[5]);

		// Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		// Step 6: click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		// Step 7: Select advance and click on cart button
		// Expected: The balance amount and adjustment amount for the selected advances
		// should be shown correctly

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

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		double AdvanceQuantity = Double.parseDouble(BalanceAmount)/Double.parseDouble(AdvanceRate);
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRate, "22k");
		Thread.sleep(300000); // Wait needed for ERP Gold Rate to Change


		//Step 9: Scan a SKU and click on add to cart,Note:Check Average Rate
		//Expected: Check calculation 
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_SkuGoldCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_FromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_MetalType);

		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG = "";
		String ItemNameInItemDetailsge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_DepositAdjustment);
		ProductQtyList.add(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		double Total = 0.0;
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			Total = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber, Double.toString(AdvanceQuantity), "22K", AdvanceRate, ErpGoldRateFor24K);

		}

		//				 step 10.In billling, Go to discount->Normal discount->Select making discount
		//				  10.1.Select making discount type as "Reduce making percentage"
		//				  10.2.Discount type as "Percentage"
		//				  10.3.Add a discount value
		//				  10.4.Select a category
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));		
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_DiscountCategory);	
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_DiscountType);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_DiscountValue);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_Category);

		//Step 11: Click on apply discount.
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));
		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_DiscountValue))/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;
		double CalculatedTotalWithoutTax = Total-ExpectedDiscount;
		ProductTotalList.add(Double.toString(CalculatedTotalWithoutTax));

		//Note: Billing Screen – No. of Product Lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX, Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
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
			if (i == 1) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i)
						.substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,
						"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "
								+ ActualItemNameInBillingPge + " in billing page");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,
						"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "
								+ ActualItemNameInBillingPge + " in billing page");
			}
			List<WebElement> Quantity = base
					.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);

			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "
					+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax1 = base.GetElement(
					NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));

			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax1.get(i).getText().replaceAll("[^\\d.-]", "")
					.trim();
			SubTotalSum = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",
					Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),
					Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,
					"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "
							+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();
		;
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"))
				.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),
				Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,
				"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge
				+ " in billing page");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");


		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,
				"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge
				+ " in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)
				+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "
				+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		//Step 12: Click on save estimate button.
		//Expected: Verify proforma invoice.
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC216_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =String.format("%.2f", CalculatedTotalWithoutTax);
		//		String InvoiceTotal=Double.parseDouble(ExpectedTaxableValue)+ExpectedTaxInBillingPge;
		//		String InvoiceTotal = String.valueOf(Double.parseDouble(ExpectedTaxableValue) + ExpectedTaxInBillingPge);
		//		String InvoiceTotal = String.format("%.2f", Double.parseDouble(ExpectedTaxableValue) + ExpectedTaxInBillingPge);
		double total = (Double.parseDouble(ExpectedTaxableValue) + ExpectedTax);
		String InvoiceTotal = String.format("%.2f", total);

		//		System.out.println("Invoice Total: " + InvoiceTotal);

		System.out.println("Invoice Total: " + InvoiceTotal);

		String Adjustment= AdvanceAmount;
		String AdditionalDiscount = ActualDiscount;
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,					
				Integer.toString(SkuList.size()),
				Double.parseDouble(GrossWeight),
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				Adjustment,
				NetTotal);

		//Step 13: Recall estimate from cashier Screen.
		//Step 14: Select estimate and click on recall button
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
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number in recall screen"+ExpectedEstmnNumber+""); 

		//Step 15: Select transaction type as "sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_TransactionType);

		//Step 16: Click on "Process estimation" button.
		//Step 17:Choose a sales representative
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Note:cashierScreen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount				
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList,
				ScannedSkuDataMap);
		String TaxInCashierScreen = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen = TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		double ExpectedDiscountInCashierScreen = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_DiscountValue))/100;
		ExpectedDiscountInCashierScreen = Math.round(ExpectedDiscountInCashierScreen * 100.0) / 100.0;

		List<WebElement> AllProductRowsInCashierScreen = base
				.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, ActualLinesCountInCashierScreen,
				"Lines count mismatch: Expected is " + ExpectedLinesCountInCashierScreen + " but found "
						+ ActualLinesCountInCashierScreen + " in Cashier Screen");

		double SubTotalSumInCashierScreen = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName = base
					.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemName.get(i).getText();
			if (i == 1) {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i)
						.substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,
						"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "
								+ ActualItemNameInCashierScreen + " in Cashier Screen");
			} else {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).trim();
				asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,
						"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "
								+ ActualItemNameInCashierScreen + " in Cashier Screen");
			}
			List<WebElement> Quantity = base
					.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));

			String ActualQuantityInCashierScreen = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ExpectedQtyInCashierScreen, ActualQtyInCashierScreen, 1.000,
					"Quantity mismatch: Expected is " + ExpectedQtyInCashierScreen + " but got "
							+ ActualQtyInCashierScreen + " in Cashier Screen");

			List<WebElement> TotalWithoutTaxCashierScrn = base.GetElement(
					NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxCashierScrn.get(i).getText().replaceAll("[^\\d.-]", "")
					.trim();

			SubTotalSumInCashierScreen = SubTotalSumInCashierScreen
					+ Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);				
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f",
					Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),
					Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")), 1.0,
					"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierScreen + " but got "
							+ ActualTotalWithoutTaxInCashierScreen + " in Cashier Screen");
		}

		String ExpectedSubTotalInCashierScreen = String.format("%.2f", SubTotalSumInCashierScreen)
				.replaceAll("[^\\d.]", "").trim();

		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"))
				.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInCashierScreen),
				Double.parseDouble(ActualSubTotalInCashierScreen), 1.0,
				"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen
				+ " in Cashier Screen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscountInCashierScreen,1.0,"Discount value mismatch: Expected is "+ExpectedDiscountInCashierScreen+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxCalculated = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f", ExpectedTaxCalculated);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),
				1.0, "Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen
				+ " in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)
				+ Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountInCashierScreen = Double
				.parseDouble(TotalAmountInCashierScreen.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInCashierScreen, ActualTotalAmountInCashierScreen, 1.0,
				"Total Amount mismatch: " + ExpectedTotalAmountInCashierScreen + " but found "
						+ ActualTotalAmountInCashierScreen + " in Cashier Screen");


		//Step 18: Click on the Amount
		//Step 19: Select a Payment method(Cash or Card)
		Thread.sleep(2000);
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 20: Post the Invoice
		//Expected: Verify final Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC216SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=ExpectedLinesCount;
		String NetValue = PaymentAmount;
		String AdjustmentTaxInvoice= AdvanceAmount;
		String TaxableValue = ExpectedTaxableValue;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationTaxInvoice(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				GrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				AdjustmentTaxInvoice,
				SkuList,
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Void a discount applied bill from cash
	// Automation ID : TC223
	// Author: Benson
	// </summary>
	public void TC223_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan a SKU and click on add to cart
		//Expected: Check calculation
		//Step 6: Click on add to Cart button
		//Step 7: Select any one of the product in cart
		List<String> SkuList = appUtils.SelectMultipleSKUs(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_SkuGoldCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_FromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                = "";
		String ItemNameInItemDetailsge ="";
		String GrossWeight             = "";
		String TotalWithoutTax         = "";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			// Step 1: Extract & store table values
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG                           = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsge            = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight                        = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax                    = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			// Step 2: Filter for this specific SKU
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			// Step 3: Validate calculated values
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount 
		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLineGold = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalGold         = TransactionDataLineGold.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String DiscountGold         = TransactionDataLineGold.get("Discount");
		String TotalGrossWeightGold = TransactionDataLineGold.get("GrossWeight");
		String TotalNetWeightGold   = TransactionDataLineGold.get("NetWeight");
		String TaxGold              = TransactionDataLineGold.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmountGold      = TransactionDataLineGold.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotalGold         = TransactionDataLineGold.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCountGold       = TransactionDataLineGold.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLineGold, ScannedSkuDataMap);

		//Step 8: Click on discount button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 9: Click on normal discount button.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 10: Select making discount
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_DiscountCategory);

		//Step 11: Select discount range
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_DiscountType);

		//Step 12: Enter discount percentage
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_DiscountValue);

		//Select discount category
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_Category);

		//Step 13: Click on apply button
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));

		//Step 14:Check calculation
		//Expected: Verify Discount Calculation
		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_DiscountValue))/100;
		ExpectedDiscount        = Math.round(ExpectedDiscount * 100.0) / 100.0;
		Thread.sleep(3000);
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotals                        = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		//After discount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		String ActualItemNameInBillingPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInBillingPage = ItemNameInItemDetailsge.split(" - ")[1].trim();
		asrt.assertTrue(ActualItemNameInBillingPage.contains(ExpectedItemNameInBillingPage), "Item name mismatch: Expected item name to contain '"  + ExpectedItemNameInBillingPage +  "' but got '" + ActualItemNameInBillingPage + "' in billing page");


		String ActualQuantityInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInBillingPage = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPage), Double.parseDouble(ExpectedQuantityInBillingPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPage + " but got " + ActualQuantityInBillingPage + " in billing page");

		String ActualTotalWithoutTaxInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),ExpectedTotalWithoutTaxInBillingPage,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");

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

		//Step 15:Click on Save to estimation button
		//Step 16:Save esimate
		//Expected: Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC223_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String AdditionalDiscount = ActualDiscount;
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
				"",
				NetTotals);

		//Step 17:Recall estimate from cashier screen
		//Step 18:Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart
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
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 19: Select transaction type as "Sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_TransactionType);

		//Step 20: Click on Process estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 21: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Verify CashierScreen After Recall: No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		double ExpectedDiscountInCashierScreen = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_DiscountValue))/100;
		ExpectedDiscountInCashierScreen = Math.round(ExpectedDiscountInCashierScreen * 100.0) / 100.0;

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		String ActualItemNameInCashierScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsge.split(" - ")[1].trim();
		asrt.assertTrue( ActualItemNameInCashierScreen.contains(ExpectedItemNameInCashierScreen), "Item name mismatch: Expected item name to contain '" + ExpectedItemNameInCashierScreen   + "' but got '"   + ActualItemNameInCashierScreen  + "' in cashier screen");

		String ActualQuantityInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInCashierScreen = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInCashierScreen = Double.parseDouble(TotalWithoutTax)-ExpectedDiscountInCashierScreen;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),ExpectedTotalWithoutTaxInCashierScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscountInCashierScreen,1.0,"Discount value mismatch: Expected is "+ExpectedDiscountInCashierScreen+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;
		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ ActualTaxInCashierScreen;
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		//Step 22. Click on void button
		//Step 23. click on void transaction button
		//Expected: Verify able to void the discount applied bill.
		appUtils.VoidTransaction();
		String ExpectedLinesCountAfterVoid= MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC223_LinesCountAfterVoid;
		String ActualLinesCountAfterVoid = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountAfterVoid, ExpectedLinesCountAfterVoid,"User is not able to void the discount applied bill in the Transaction Page");

	}

	// <summary>
	// Test Case Title : Invoice with gold and dmd -apply making discount
	// Automation ID : TC208
	// Author: Nandagopan
	// </summary>

	public void TC208_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		List<String> AllSkuList             = new ArrayList<>();
		Map<String, String> SkuDetails      = new LinkedHashMap<>();
		List<String> ExpectedItemNames      = new ArrayList<>();
		List<String> TotalForEachItems      = new ArrayList<>();
		List<String> ItemQuantity           = new ArrayList<>();
		List<Double> AllRates               = new ArrayList<>();

		//Step 5: Scan a SKU and click on add to cart
		//Expected: Check calculation 
		List<String> GoldSkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_SkuGoldCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_FromCounterSKU,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_MetalType);

		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG = "";
		String ItemNameInItemDetailsge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";

		List<String> DiamondSkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_SkuCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_FromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_MetalType);
		String CvalueDiamond = "";
		appUtils.HamBurgerButtonClick("iconShop");

		AllSkuList.addAll(DiamondSkuList);
		AllSkuList.addAll(GoldSkuList);
		List<String> ProductTotalList = new ArrayList<>();
		List<String> ProductTotalGross = new ArrayList<>();
		List<String> ProductTotal = new ArrayList<>();			
		appUtils.HamBurgerButtonClick("iconShop");	
		double Total =0.0;

		for (int i = 0; i < AllSkuList.size(); i++)
		{
			String  CurrentSkuList = AllSkuList.get(i);
			int SkuCount = i + 1;
			appUtils.SKUIngridentTableValues(CurrentSkuList.toString(),SkuCount, SkuDetails);
			ExpectedItemNames.add(SkuDetails.get("SKU_" + (i+1) + "_HeaderName"));
			TotalForEachItems.add(SkuDetails.get("SKU_" + (i+1) + "_Total"));
			ItemQuantity.add(SkuDetails.get("SKU_" + (i+1) + "_GrossWeight"));
			Total = Double.parseDouble(SkuDetails.get("SKU_" + (i+1) + "_Total"));
			CvalueRG = SkuDetails.get("SKU_" + (i+1) + "_CvalueRG");
			CvalueDiamond= (SkuDetails.get("SKU_" + (i+1) + "_CvalueDIAMOND"));
			ItemNameInItemDetailsge = SkuDetails.get("SKU_" + (i+1) + "_HeaderName");
			GrossWeight = SkuDetails.get("SKU_" + (i+1) + "_GrossWeight");
			TotalWithoutTax = SkuDetails.get("SKU_" + (i+1) + "_Total");
			ProductTotalList.add(SkuDetails.get("SKU_" + (i+1) + "_Total"));
			ProductTotalGross.add(SkuDetails.get("SKU_" + (i+1) + "_GrossWeight"));
			ProductTotal.add(SkuDetails.get("SKU_" + (i+1) + "_Total"));

			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(SkuDetails, SkuCount);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCount);
		}

		//	Step 6.In billling, Go to discount
		//	Step 7: Select Normal discount
		//		7.1: Select making discount
		//	  	7.2.Select making discount type as "Reduce making percentage"
		//	 	7.3.Discount type as "Percentage"
		//	  	7.4.Add a discount value
		//	 	7.5.Select a category
		//	  	7.6: Click on apply discount.
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));		
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_DiscountCategory);	
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_DiscountType);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_DiscountValue);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_Category);
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));

		double ExpectedDiscount = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_DiscountValue))/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;
		double CalculatedTotalWithoutTax = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;
		ProductTotalList.removeLast();
		//			System.out.println(ProductTotalList);
		ProductTotalList.add(Double.toString(CalculatedTotalWithoutTax));
		//			System.out.println(ProductTotalList);
		Thread.sleep(3000);

		//Note: Billing Screen – No. of Product Lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX, Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, SkuDetails);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String NetTotal = TransactionDataLine.get("NetTotal");
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
			String ExpectedItemNameInBillingPge = ExpectedItemNames.get(i).substring(ExpectedItemNames.get(i).indexOf("-") + 1).trim();
			asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));

			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ItemQuantity.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax1 = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));

			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax1.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSum = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();

		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");


		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = Math.round(((Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1))) * 0.03) * 100.0) / 100.0;
		double ExpectedTaxInBillingPge =ExpectedTax;
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge),ExpectedTaxInBillingPge, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");


		//Step 8: Click on save estimate button.
		// Step 9:Click on save to estimation
		//Expected: Verify proforma invoice.
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC208_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =String.format("%.2f", Double.parseDouble(ProductTotalList.get(0))+Double.parseDouble(ProductTotalList.get(1)));
		String InvoiceTotal=TotalAmount;
		String Adjustment="";
		String AdditionalDiscount = ActualDiscount;
		String ExpectedGrossWeight = String.format("%.3f", Double.parseDouble(ProductTotalGross.get(0))+Double.parseDouble(ProductTotalGross.get(1)));
		String ExpectedTaxableAmount = String.format("%.2f", Double.parseDouble(ProductTotal.get(0))+Double.parseDouble(ProductTotal.get(1)));

		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,					
				Integer.toString(AllSkuList.size()),
				Double.parseDouble(ExpectedGrossWeight),
				Double.parseDouble(TotalNetWeight),
				ExpectedTaxableAmount,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				"",
				NetTotal);

		//Step 10: Recall estimate from cashier Screen.
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
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number in recall screen"+ExpectedEstmnNumber+""); 

		//Step 12: Select transaction type as "sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_TransactionType);

		//Step 13: Click on "Process estimation" button.
		//Step 14:Choose a sales representative
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);

		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:cashierScreen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount				
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(AllSkuList,SkuDetails);

		String TaxInCashierScreen = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen = TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		double ExpectedDiscountInCashierScreen = (Double.parseDouble(CvalueRG)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC208_DiscountValue))/100;
		ExpectedDiscountInCashierScreen = Math.round(ExpectedDiscountInCashierScreen * 100.0) / 100.0;

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, ActualLinesCountInCashierScreen,"Lines count mismatch: Expected is " + ExpectedLinesCountInCashierScreen + " but found "+ ActualLinesCountInCashierScreen + " in Cashier Screen");

		double SubTotalSumInCashierScreen = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemName.get(i).getText();
			String ExpectedItemNameInCashierScreen = ExpectedItemNames.get(i).substring(ExpectedItemNames.get(i).indexOf("-") + 1).trim();
			asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in Cashier Screen");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));

			String ActualQuantityInCashierScreen = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ItemQuantity.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ExpectedQtyInCashierScreen, ActualQtyInCashierScreen, 1.000,"Quantity mismatch: Expected is " + ExpectedQtyInCashierScreen + " but got "+ ActualQtyInCashierScreen + " in Cashier Screen");

			List<WebElement> TotalWithoutTaxCashierScrn = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxCashierScrn.get(i).getText().replaceAll("[^\\d.-]", "").trim();

			SubTotalSumInCashierScreen = SubTotalSumInCashierScreen+ Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);				
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierScreen + " but got "+ ActualTotalWithoutTaxInCashierScreen + " in Cashier Screen");
		}

		String ExpectedSubTotalInCashierScreen = String.format("%.2f", SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();	
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInCashierScreen),Double.parseDouble(ActualSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in Cashier Screen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscountInCashierScreen,1.0,"Discount value mismatch: Expected is "+ExpectedDiscountInCashierScreen+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxCashierScreen = Math.round(((Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1))) * 0.03) * 100.0) / 100.0;
		String ExpectedTaxInCashierScreen = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0, "Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmountInCashierScreen.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInCashierScreen, ActualTotalAmountInCashierScreen, 1.0,"Total Amount mismatch: " + ExpectedTotalAmountInCashierScreen + " but found "+ ActualTotalAmountInCashierScreen + " in Cashier Screen");

		//Step 15: Click on the Amount
		//Step 16: Select a Payment method(Cash or Card)
		Thread.sleep(2000);
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 17: Post the Invoice
		//Expected: Verify final Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC208SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ExpectedTaxableAmount;
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=ExpectedLinesCount;
		String NetValue = PaymentAmount;		
		String TaxableValue = ExpectedTaxableValue;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				ExpectedGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				AllSkuList,
				SkuDetails);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Invoice with multiple item -apply making,diamond,stone discount[Verify that the value of the stones is being included in the total invoice amount where a discount has been applied.]
	// Prerequisite  :Product must have a valid discount agreement
	// Automation ID : TC211
	// Author: Vishnu Manoj K
	// </summary>
	public void TC211_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		List<String> AllSkuList             = new ArrayList<>();

		//Step 5: Scan multiple SKUs(Gold,Uncut,Diamond) and click on Add to cart button
		//Note: .Check calculation Billing Screen – No. of Product Lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX, Total Amount
		List<String> GoldSkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_SkuGoldCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_GoldFromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_MetalType);

		appUtils.HamBurgerButtonClick("iconShop");
		List<String> DiamondSkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_SkuDiamondCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiamondFromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_MetalType);

		appUtils.HamBurgerButtonClick("iconShop");
		List<String> UncutVirazSkuList = appUtils.GetVirazSkus(Utility_TestData.UncutVirazSkus, 1);

		AllSkuList.addAll(GoldSkuList);
		AllSkuList.addAll(DiamondSkuList);
		AllSkuList.addAll(UncutVirazSkuList);

		appUtils.HamBurgerButtonClick("iconShop");	
		List<String> CvalueDiamond = new ArrayList<>();
		List<String> CvalueStone = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < AllSkuList.size(); i++)
		{		
			int SkuNumber = i + 1;
			String Sku = AllSkuList.get(i);
			// Step 1: Extract & store table values
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			// Step 2: Filter for this specific SKU
			CvalueDiamond.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueDIAMOND"));
			CvalueStone.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueSTONES"));
			System.out.println("CvalueDiamond  "+CvalueDiamond);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			// Step 3: Validate calculated values
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		System.out.println(CvalueDiamond);
		double TotalCValueDiamond = 0.0;

		for (String value : CvalueDiamond) {
			if (value != null && !value.trim().isEmpty() && !value.equalsIgnoreCase("null")) {
				try {
					// Remove commas or unwanted symbols just in case
					value = value.replace(",", "").trim();
					TotalCValueDiamond += Double.parseDouble(value);
				} catch (NumberFormatException e) {
					System.out.println(" Skipping invalid CvalueDIAMOND entry: " + value);
				}
			}
		}
		System.out.println("TotalCValueDiamond "+TotalCValueDiamond);

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotalBeforeDiscount = TransactionDataLine.get("Subtotal");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//	Step 6.In billing,Select Gold Category,Go to discount
		base.buttonClick(LoginPageObj.Edt_AlertText(TransactionDataLine.get("Item1Name")));

		//Step 7: Select Normal discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));	

		//Step 7.1: Select making discount
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountCategory);	

		//Step 7.2: Select making discount type as "Reduce making percentage"
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountType);

		//Step 7.3: Discount type as "Percentage"
		//Step 7.4: Add a discount value
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountValue);

		//Step7.5: Select a category
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_Category);

		//Step 7.6: Click on apply discount.
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));
		String GoldCvalue= ScannedSkuDataMap.get("SKU_1_CvalueRG");
		System.out.println("GoldCvalue"+GoldCvalue);
		double GoldDiscount = (Double.parseDouble(GoldCvalue)*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountValue))/100;
		System.out.println("GoldDiscount "+GoldDiscount);
		Thread.sleep(10000);
		System.out.println("------------------------------------------ ");

		//Step 8: Select Diamond Category
		base.buttonClick(LoginPageObj.Edt_AlertText(TransactionDataLine.get("Item2Name")));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Step 8: Select Normal discount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 8.1: Select type as diamond discount.
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountCategoryDiamond);

		//Step 8.2: Select making discount type as diamond discount
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("StnDmdDiscType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountTypeDiamond);

		//Step 8.3: Discount type as "Percentage"
		//Step 8.4: Add a discount value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("stnInput"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountValueDiamond);

		//Step 8.5: Click on Apply
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsStone","Apply discount"));			
		String DiamondCvalue= ScannedSkuDataMap.get("SKU_2_CvalueDIAMOND");
		System.out.println("DiamondCvalue  "+DiamondCvalue);
		double DiamondDiscount = Double.parseDouble(DiamondCvalue) *(Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountValueDiamond)/100);
		System.out.println("DiamondDiscount "+DiamondDiscount);
		System.out.println("------------------------------------------ ");

		//Step	9: Select Uncut Category
		base.ClickCondition(LoginPageObj.Edt_AlertText(TransactionDataLine.get("Item3Name")));

		//Step	9: Select Normal discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step  9.1: Select discount category Diamond/Stone discount.
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountCategoryStone);

		//Step	9.2: Select type stone discount.
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("StnDmdDiscType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountTypeStone);

		//Step	9.3: Enter discount value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("stnInput"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountValueStone);

		//Step	9.4: Click on Apply
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsStone","Apply discount"));			
		String StoneCvalue= ScannedSkuDataMap.get("SKU_3_CvalueSTONES");
		System.out.println("StoneCvalue  "+StoneCvalue);
		double StoneDiscount = Double.parseDouble(StoneCvalue) *(Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_DiscountValueStone)/100);
		System.out.println("StoneDiscount "+StoneDiscount);	
		System.out.println("------------------------------------------ ");

		double TotalDiscountApplied=StoneDiscount+DiamondDiscount+GoldDiscount;
		TotalDiscountApplied=Math.round(TotalDiscountApplied * 100.0) / 100.0;
		System.out.println("TotalDiscountApplied"+TotalDiscountApplied);
		System.out.println("------------------------------------------ ");

		Map<String, String> TransactionDataAfterDisocunt = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataAfterDisocunt.get("Subtotal");
		String TotalGrossWeight = TransactionDataAfterDisocunt.get("GrossWeight");
		String NetTotal = TransactionDataAfterDisocunt.get("NetTotal");
		String TotalNetWeight = TransactionDataAfterDisocunt.get("NetWeight");
		String Tax = TransactionDataAfterDisocunt.get("Tax");
		String TotalAmount = TransactionDataAfterDisocunt.get("TotalAmount");
		String ActualLinesCount = TransactionDataAfterDisocunt.get("LinesCount");
		String Discounts = TransactionDataAfterDisocunt.get("Discounts");
		Discounts = Discounts.replaceAll("[^\\d.\\-]", "").trim();

		asrt.assertEquals(Double.parseDouble(Discounts), TotalDiscountApplied,1, "Mismatch in Discount Actual is "+Double.parseDouble(Discounts)+" and Expected Discount After Applying in billing screen"+TotalDiscountApplied+""); 

		//Step 10: Click on save estimate button.
		//Step 11: Click on save to estimation
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC211_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String InvoiceTotal=TotalAmount;
		String TaxableValue=SubTotal;
		String TotalAdjustment="";
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,					
				Integer.toString(AllSkuList.size()),
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				SubTotalBeforeDiscount,
				Discounts,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		//Step 12: Recall estimate from cashier Screen.
		//Step 13: Select estimate and click on recall button
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
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number in recall screen"+ExpectedEstmnNumber+""); 

		//Step 14: Select transaction type as "sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_TransactionType);

		//Step 15: Click on "Process estimation" button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);

		//Step 16: Select any one of the SA
		//Expected:.Cashier Screen after recall *No of Product lines*Displayed Item Name *Displayed Quantity*Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX*Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		for (String Key : TransactionDataAfterDisocunt.keySet()) {
			String ExpectedValue = TransactionDataAfterDisocunt.get(Key);        // Billing Page value
			String ActualValue   = TransactionRecallDataLine.get(Key);  // Cashier Screen value
			if (ExpectedValue == null && ActualValue == null) { System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' – both values are null");
			} else if (ExpectedValue != null && ExpectedValue.equals(ActualValue)) {
				System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' " +"Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			} else {
				asrt.assertEquals(ActualValue,ExpectedValue,"Billing Page vs Cashier Screen: Mismatch in '" + Key + "' " + "Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			}
		}

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		Thread.sleep(2000);
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 19: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC211_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotalBeforeDiscount;
		String TotalDiscount=Discounts;
		String TotalQtyPcs=ActualLinesCount;
		String NetValue = PaymentAmount;	
		String TotalDiamond="";
		String Adjustment="";
		String AdvanceAmount="";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidationAdvance(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				Double.toString(TotalCValueDiamond),
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				Adjustment,
				AdvanceAmount,
				AllSkuList,
				ScannedSkuDataMap);

		//Sales return and counter in flow
		appUtils.SalesReturnFlow(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_CustomerNo, PdfInvoiceNo,MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_ReturnProducts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Error"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		Thread.sleep(5000);

		appUtils.CounterFlow(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_ToCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC211_MetalTypeUncut,
				UncutVirazSkuList,
				UtilityTestData.Terminal);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Invoice with multiple Item-apply SKU aging discount [Verify the taxable amount is correctly calculated based on the discounted line total after a SKU aging discount is applied.]
	// Prerequisite  :Product must have a valid Staging agreement
	// Automation ID : TC212
	// Author: Vishnu Manoj K
	// </summary>
	public void TC212_MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC212_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		List<String> AllSkuList             = new ArrayList<>();

		//Step 5: Scan multiple SKUs(Gold,Uncut,Diamond) and click on Add to cart button
		//Note: .Check calculation Billing Screen – No. of Product Lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX, Total Amount
		List<String> GoldSkuList = appUtils.SelectMultipleSKUs(
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC212_SkuGoldCount,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC212_TransferType,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC212_GoldFromCounter,
				MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC212_MetalType);

		appUtils.HamBurgerButtonClick("iconShop");	
		AllSkuList.addAll(GoldSkuList);
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < AllSkuList.size(); i++)
		{		
			int SkuNumber = i + 1;
			String Sku = AllSkuList.get(i);
			// Step 1: Extract & store table values
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			// Step 2: Filter for this specific SKU
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			// Step 3: Validate calculated values
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotalBeforeDiscount = TransactionDataLine.get("Subtotal");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//	Step 6.In billing,Select Gold Category,Go to discount
		base.buttonClick(LoginPageObj.Edt_AlertText(TransactionDataLine.get("Item1Name")));

		//Step 7: Select Normal discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));	

		//Step 8: .Select discount category as "SKU aging Discount" and Click on Apply discount
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC212_DiscountCategory);	
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsSKUAge", "Apply discount"));
		String GoldMakingValue= ScannedSkuDataMap.get("SKU_1_MakingValue");
		String GoldWastageAmount= ScannedSkuDataMap.get("SKU_1_WastageAmount");
		System.out.println("GoldMakingValue "+GoldMakingValue);
		System.out.println("GoldWastageAmount "+GoldWastageAmount);
		double McValue= Double.parseDouble(GoldMakingValue)+ Double.parseDouble(GoldWastageAmount);
		double GoldStagingDiscount = (McValue*Double.parseDouble(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC212_DiscountValue))/100;
		System.out.println("GoldStagingDiscount "+GoldStagingDiscount);
		Thread.sleep(10000);
		System.out.println("------------------------------------------ ");

		Map<String, String> TransactionDataAfterDisocunt = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataAfterDisocunt.get("Subtotal");
		String TotalGrossWeight = TransactionDataAfterDisocunt.get("GrossWeight");
		String NetTotal = TransactionDataAfterDisocunt.get("NetTotal");
		String TotalNetWeight = TransactionDataAfterDisocunt.get("NetWeight");
		String Tax = TransactionDataAfterDisocunt.get("Tax");
		String TotalAmount = TransactionDataAfterDisocunt.get("TotalAmount");
		String ActualLinesCount = TransactionDataAfterDisocunt.get("LinesCount");
		String Discounts = TransactionDataAfterDisocunt.get("Discounts");
		Discounts = Discounts.replaceAll("[^\\d.\\-]", "").trim();

		asrt.assertEquals(Double.parseDouble(Discounts), GoldStagingDiscount,1, "Mismatch in Staging Discount Actual is "+Double.parseDouble(Discounts)+" and Expected Discount After Applying in billing screen"+GoldStagingDiscount+""); 

		//Step 9: Click on save estimate button.
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC212_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String InvoiceTotal=TotalAmount;
		String TaxableValue=SubTotal;
		String TotalAdjustment="";
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,					
				Integer.toString(AllSkuList.size()),
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				SubTotalBeforeDiscount,
				Discounts,
				TaxableValue,
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
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number in recall screen"+ExpectedEstmnNumber+""); 

		//Step 12: Select transaction type as "sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpTestData.TC216_TransactionType);

		//Step 13: Click on "Process estimation" button.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);

		//Step 14: Select any one of the SA
		//Expected:.Cashier Screen after recall *No of Product lines*Displayed Item Name *Displayed Quantity*Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX*Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
		for (String Key : TransactionDataAfterDisocunt.keySet()) {
			String ExpectedValue = TransactionDataAfterDisocunt.get(Key);        // Billing Page value
			String ActualValue   = TransactionRecallDataLine.get(Key);  // Cashier Screen value
			if (ExpectedValue == null && ActualValue == null) { System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' – both values are null");
			} else if (ExpectedValue != null && ExpectedValue.equals(ActualValue)) {
				System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' " +"Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			} else {
				asrt.assertEquals(ActualValue,ExpectedValue,"Billing Page vs Cashier Screen: Mismatch in '" + Key + "' " + "Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			}
		}

		//Step 15: Click on the Amount
		//Step 16: Select a Payment method(Cash or Card)
		Thread.sleep(2000);
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 17: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC212_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotalBeforeDiscount;
		String TotalDiscount=Discounts;
		String TotalQtyPcs=ActualLinesCount;
		String NetValue = PaymentAmount;	
		String TotalDiamond="";
		String Adjustment="";
		String AdvanceAmount="";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				AllSkuList,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}















}