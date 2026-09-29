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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceRepayment_Obj;
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
import testData.PackageDiscount_TestData;
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

public class PackageDiscount extends BasePge {

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
	PackageDiscount_TestData PackageDiscountTestData = new PackageDiscount_TestData();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj=new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	AdvanceRepayment_Obj AdvanceRepaymentObj=new AdvanceRepayment_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();

	public PackageDiscount(WebDriver driver) {
		super(driver);
		base      = new BasePge(driver);
		appUtils  = new AppUtilities(base); 
		erpUtils  = new ErpUtilities(base);
		pdfUtils  = new PdfUtilities(base);
		mathUtils = new MathUtilities(base);
	}

	// <summary>
	// Test Case Title : Invoice with Package discount making discount diamond discount
	// Automation ID : TC257
	// Author: Robin T Abraham
	// </summary>
	public void TC257_PackageDiscount() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search a customer with Phone number
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC257_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(5000);

		//Step 5: Scan Gold SKU's and click on Add to cart button
		//Note: KeralSku
		//Step 6: Select any one of the sales agent
		//Expected: Check calculation
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                = "";
		String ItemNameInItemDetailsPge = "";
		String GrossWeight             = "";
		String TotalWithoutTax         = "";
		String WastageQty              = "";
		String WastageAmt              = "";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredKeralSku.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = RequiredKeralSku.get(i);
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

		//Step 7: In billing, Go to discount->Normal discount:
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 7.1: Select discount category as"Package Making discount"
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC257_DiscountCategory);

		//Step 7.2: Select Package code
		//Step 7.3: Add "Apply making percentage"
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);

		// Select option based on WastageQty range and set making percentage
		int SlabNumber = 0;
		double PackageDiscountPercent = 0;

		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + WastageQtyValue);
		} else {
			System.out.println("Wastage qty out of range: " + WastageQtyValue);
		}

		if (SlabNumber != 0) {
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				String Value = option.getAttribute("value"); //PKG_AP_1 or PKG_KL_1
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {

					Select.selectByValue(Value);

					System.out.println("Selected package: " + Value);

					// Set making percentage
					base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
					base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));

					break;
				} else {
					System.out.println("No matching package found for slab " + SlabNumber);
				}
			}
		}

		//Step 7.4: Click on Apply discount
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
		double ExpectedDiscount = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax 		        = TransactionDataLine.get("Tax");
		String TotalAmount      = TransactionDataLine.get("TotalAmount");
		String NetTotal         = TransactionDataLine.get("NetTotal");
		String LinesCount       = TransactionDataLine.get("LinesCount");

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		String ActualItemNameInBillingPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		//String ExpectedItemNameInBillingPage = ItemNameInItemDetailsPge.split(" - ")[1].trim();
		//asrt.assertEquals(ActualItemNameInBillingPage, ExpectedItemNameInBillingPage,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");
		asrt.assertTrue(ItemNameInItemDetailsPge.trim().contains(ActualItemNameInBillingPage),"Item name mismatch: Expected is " + ItemNameInItemDetailsPge + " but got "+ ActualItemNameInBillingPage + " in billing page");

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

		//Step 8: Save esimate
		//Step 9: Recall estimate from cashier screen
		//Step 10: Select estimate and click on recall estimation
		//Expected: Verify the Invoice details
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		//Note:Cashier Screen after recall
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC257_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String TotalAdjustment ="";		
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
				TotalAdjustment,
				NetTotal);

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

		//Step 11: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PackageDiscountTestData.TC257_TransactionType);

		//Step 12: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:cashierScreen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		String ActualItemNameInCashierScreen = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		//String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsPge.split(" - ")[1].trim();
		//asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in cashier screen");
		asrt.assertTrue(ItemNameInItemDetailsPge.trim().contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is " + ItemNameInItemDetailsPge + " but got "+ ActualItemNameInCashierScreen + " in billing page");

		String ActualQuantityInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInCashierScreen = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInCashierScreen = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),ExpectedTotalWithoutTaxInCashierScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;
		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ ActualTaxInCashierScreen;
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		//Step 14: Click on the Amount
		//Step 15: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 16: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC257_SaleInvoice");
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
				RequiredKeralSku,
				ScannedSkuDataMap);

		appUtils.SalesReturnFlow(PackageDiscountTestData.TC257_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC257_ReturnProducts);
		appUtils.CounterFlow(PackageDiscountTestData.TC257_FromCounter,PackageDiscountTestData.TC257_MetalType,RequiredKeralSku, UtilityTestData.Terminal);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Package discount with advance settlement
	// Automation ID : TC263
	// Author: Robin T Abraham
	// </summary>
	public void TC263_PackageDiscount() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Fetching current board rate
		Thread.sleep(3000);
		double CurrentGoldRate = appUtils.FetchCurrentBoardRate(PackageDiscountTestData.TC263_Purity);
		System.out.println("CurrentGoldRate:"+CurrentGoldRate);
		appUtils.HamBurgerButtonClick("iconShop");

		//Pre-Requisite: Creating normal advance
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				PackageDiscountTestData.TC263_CustomerNo,
				PackageDiscountTestData.TC263_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//Step 3: Select customer
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC263_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(5000);

		// Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		// Step 6: click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		// Step 7: Select advance and click on cart button
		// Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),PackageDiscountTestData.TC263_AdvanceAmount);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(5000);
		String Rate = BigDecimal.valueOf(CurrentGoldRate).stripTrailingZeros().toPlainString();

		By TableContainer = AdvanceRepaymentObj.Ele_Scroll(".win-vertical");
		By TargetRow = AdvanceRepaymentObj.Ele_Row("h4 ellipsis cell","Balance amount");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean Found = false;
		int LastCount = 0;
		while (!Found) {

			List<WebElement> Rows = base.GetElement(TargetRow);

			for (WebElement Row : Rows) {
				if (Row.getText().trim().equals(Rate)) {
					js.executeScript("arguments[0].scrollIntoView(true);", Row);
					Row.click();
					Found = true;
					break;
				}
			}

			if (Found) break;
			WebElement container = driver.findElement(TableContainer);
			js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].clientHeight;", container);
			Thread.sleep(600);
			if (Rows.size() == LastCount) {
				System.out.println("Reached end — value not found");
				break;
			}
			LastCount = Rows.size();
		}

		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",Rate));
		String BalanceAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(5000);

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		//Step 8: Enter OTP
		//Step 9.Scan SKU and add to cart
		//Note: KeralSku
		//Expected: Check calculation
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                = "";
		String TotalWithoutTax         = "";
		String WastageQty              = "";
		String WastageAmt              = "";

		List<String> ProductNames	  = new ArrayList<>();
		List<String> ProductQtyList	  = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(PackageDiscountTestData.TC263_DepositAdjustment);
		ProductQtyList.add(PackageDiscountTestData.TC263_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);

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

		//Step 10: Select any one of the product in cart
		//Step 11: Click on discount button
		//Step 12: Click on normal discount button
		//Step 14: Select discount range
		//Step 15: Enter discount percentage
		//Step 16: Click on checkbox(Apply higher discount)
		//Step 17: Click on apply button
		//Step 18: Enter OTP
		//Note: check calculation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC263_DiscountCategory);
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);

		// Select option based on WastageQty range and set making percentage
		int SlabNumber				  = 0;
		double PackageDiscountPercent = 0;

		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = PackageDiscountTestData.TC263_PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = PackageDiscountTestData.TC263_PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = PackageDiscountTestData.TC263_PackageDiscountPercent_SlabC;
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
		ProductTotalList.removeLast();
		ProductTotalList.add(Double.toString(ExpectedTotalWithoutTax));

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal 		= TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax 		        = TransactionDataLine.get("Tax");
		String TotalAmount      = TransactionDataLine.get("TotalAmount");
		String NetTotal         = TransactionDataLine.get("NetTotal");
		String LinesCount       = TransactionDataLine.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		//Step 19: Click on Save to estimation button
		//Step 20: Save estimate
		//Step 21: Recall estimate from cashier screen
		//Step 22: Select estimate and click on recall estimation
		//Expected: Verify the Invoice details
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC263_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =ProductTotalList.get(1);
		String InvoiceTotal= Double.toString((Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "")) + Double.parseDouble(AdjustmentAmount)));
		String TotalAdjustment = AdjustmentAmount;		
		String AdditionalDiscount = Double.toString(ExpectedDiscount);
		System.out.println(RequiredKeralSku.size());
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				Integer.toString(RequiredKeralSku.size()),
				Double.parseDouble(ProductQtyList.get(1)),
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

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

		//Step 23: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PackageDiscountTestData.TC263_TransactionType);

		//Step 24: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 25: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:Cashier Screen after recall
		//No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX, Total Amount
		Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashier, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String ActualDiscountCashier = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountCashier), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscountCashier+" in cashier page");


		//Step 26: Click on the Amount
		//Step 27: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 28: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);

		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC263_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount=Double.toString(ExpectedDiscount);
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = ExpectedTaxableValue;
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
				RequiredKeralSku,
				ScannedSkuDataMap);

		//Return Keral Sku
		appUtils.SalesReturnFlow(PackageDiscountTestData.TC263_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC263_ReturnProducts);
		appUtils.CounterFlow(PackageDiscountTestData.TC263_FromCounter,PackageDiscountTestData.TC263_MetalType,RequiredKeralSku, UtilityTestData.Terminal);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}



	// <summary>
	// Test Case Title : Invoice with package and non package product
	// Automation ID : TC256
	// Author: Nandagopan
	// </summary>
	public void TC256_PackageDiscount() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search a customer with Phone number
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC256_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(3000);

		//Step 5. Scan gold SKU( eligible for package discount/not eligible for package discount)
		//Expected:Check calculation
		//Step 6.click on Add to cart button
		List<String> RequiredSku = appUtils.SelectMultipleSKUs(PackageDiscountTestData.TC256_SkuGoldCount,PackageDiscountTestData.TC256_TransferType,PackageDiscountTestData.TC256_FromCounterSKU,PackageDiscountTestData.TC256_MetalTypeSKU);
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1);
		List<String> CombinedSkus = new ArrayList<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> ProductTotal = new ArrayList<>();	
		CombinedSkus.addAll(RequiredSku);
		CombinedSkus.addAll(RequiredKeralSku);
		String CvalueRG = "";
		String ItemNameInItemDetailsPge = "";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		String WastageQty = "";
		String WastageAmt = "";

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < CombinedSkus.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = CombinedSkus.get(i);
			// Extract SKU data
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
			ProductTotal.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);   
		}

		//Step 7: Select any one of the product in cart
		//Step 8: Go to discount
		//     8.1.Select Normal discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//	  8.2: Select discount category as"Package Making discount"
		Thread.sleep(3000);
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC257_DiscountCategory);
		Thread.sleep(4000);

		//    8.3: Select Package code
		//	  8.4: Add "Apply making percentage"
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);
		Thread.sleep(2000);

		// Select option based on WastageQty range and set making percentage
		int SlabNumber = 0;
		double PackageDiscountPercent = 0;

		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = PackageDiscountTestData.TC256_PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = PackageDiscountTestData.TC256_PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = PackageDiscountTestData.TC256_PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + WastageQtyValue);
		} else {
			System.out.println("Wastage qty out of range: " + WastageQtyValue);
		}

		if (SlabNumber != 0) {
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				String Value = option.getAttribute("value"); //PKG_AP_1 or PKG_KL_1
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {

					Select.selectByValue(Value);

					System.out.println("Selected package: " + Value);

					// Set making percentage
					base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
					base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));

					break;
				} else {
					System.out.println("No matching package found for slab " + SlabNumber);
				}
			}
		}

		//Step 9: Click on Apply discount
		// Note: check calculation
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
		double ExpectedDiscount = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;
		double CalculatedTotalWithoutTax = Double.parseDouble(TotalWithoutTax)-ExpectedDiscount;			
		ProductTotalList.removeLast();
		ProductTotalList.add(Double.toString(CalculatedTotalWithoutTax));
		Thread.sleep(2000);

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLineBillingScreen = appUtils.TransactionSKUsLinesVerify(CombinedSkus, ScannedSkuDataMap);
		String SubTotal 		= TransactionDataLineBillingScreen.get("Subtotal");
		String TotalGrossWeight = TransactionDataLineBillingScreen.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLineBillingScreen.get("NetWeight");
		String Tax 		        = TransactionDataLineBillingScreen.get("Tax");
		String TotalAmount      = TransactionDataLineBillingScreen.get("TotalAmount");
		String NetTotal         = TransactionDataLineBillingScreen.get("NetTotal");
		String LinesCount       = TransactionDataLineBillingScreen.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineBillingScreen, ProductNames, ProductQtyList, ProductTotalList,PackageDiscountTestData.TC256_BillingScreen);

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		//Step 10:Click on Save to estimation button
		//Step 11: Save esimate
		//Expected: Verify the Invoice details
		//Step 12: Recall estimate from cashier screen
		//Step 13: Select estimate and click on recall estimation
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC256_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String TotalAdjustment ="";		
		String AdditionalDiscount = ActualDiscount;
		String TotalWithoutTaxProForma = String.format("%.2f", Double.parseDouble(ProductTotal.get(0))+Double.parseDouble(ProductTotal.get(1)));
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTaxProForma,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

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

		//Step 14: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PackageDiscountTestData.TC256_TransactionType);

		//Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:cashierScreen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLineCashierScreen = appUtils.TransactionSKUsLinesVerify(CombinedSkus, ScannedSkuDataMap);
		String SubTotalCashierScreen 		 = TransactionDataLineCashierScreen.get("Subtotal");
		String TotalGrossWeightCashierScreen = TransactionDataLineCashierScreen.get("GrossWeight");
		String TotalNetWeightCashierScreen   = TransactionDataLineCashierScreen.get("NetWeight");
		String TaxCashierScreen	        	 = TransactionDataLineCashierScreen.get("Tax");
		String TotalAmountCashierScreen      = TransactionDataLineCashierScreen.get("TotalAmount");
		String NetTotalCashierScreen         = TransactionDataLineCashierScreen.get("NetTotal");
		String LinesCountCashierScreen       = TransactionDataLineCashierScreen.get("LinesCount");

		double ExpectedDiscountInCashierScreen = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedDiscountInCashierScreen = Math.round(ExpectedDiscountInCashierScreen * 100.0) / 100.0;

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashierScreen, ProductNames, ProductQtyList, ProductTotalList,PackageDiscountTestData.TC256_CashScreen);

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedDiscountInCashierScreen,1.0,"Discount value mismatch: Expected is "+ExpectedDiscountInCashierScreen+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 19: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC256_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTaxProForma;
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = SubTotalCashierScreen;
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
				CombinedSkus,
				ScannedSkuDataMap);

		appUtils.SalesReturnFlow(PackageDiscountTestData.TC256_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC256_ReturnProducts);
		appUtils.CounterFlow(PackageDiscountTestData.TC256_FromCounterSKU,PackageDiscountTestData.TC256_MetalTypeSKU,CombinedSkus, UtilityTestData.Terminal);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title :Invoice with package discount, sales return, Old gold 
	// Automation ID : TC259
	// Author: Robin T Abraham
	// </summary>
	public void TC259_PackageDiscount() throws Exception {

		//Step 1: Login to POS
		//Step 2: Click on Transaction button
		//Prerequisite: The product must be sold by the same customer
		Map<String, Object> InvoiceDetails = erpUtils.GetInvoiceDetailsAfterNormalSale(PackageDiscountTestData.TC259_CustomerNo, PackageDiscountTestData.TC259_Products);
		String ActualCustomerName 	= pdfUtils.ExtractCustomerNameFromSaleInvoice();
		String InvoiceNo            = (String) InvoiceDetails.get("InvoiceNo");
		String TotalNetWeightReturn = (String) InvoiceDetails.get("TotalNetWeight");
		String SkuPurityReturn      = (String) InvoiceDetails.get("FirstSkuPurity");
		String SkuGoldRateReturn    = (String) InvoiceDetails.get("FirstSkuGoldRate");
		String TotalRgWeightReturn  = (String) InvoiceDetails.get("TotalRgWeight");

		// Extract SKU-wise map
		Map<String, String> SkuWiseData = (Map<String, String>) InvoiceDetails.get("SKUWiseDetails");
		String SkuNetWeight   = SkuWiseData.getOrDefault("SKU_1_NetWeight", "N/A");
		String SkuGrossWeight = SkuWiseData.getOrDefault("SKU_1_GrossWeight", "N/A");
		String SkuStoneWeight = SkuWiseData.getOrDefault("SKU_1_StoneWeight", "N/A");
		String SkuTotal	   	  = SkuWiseData.getOrDefault("SKU_1_Total", "N/A");
		System.out.print("SkuTotal: "+SkuTotal);
		Map<String, String> ScannedSkuDataMapSale = (Map<String, String>) InvoiceDetails.get("ScannedSKUDataMap");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC259_CustomerNo, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		//Step 6: Click on return transaction button at the billing screen
		//Step 7: Enter reciept number
		//Step 8: Select return product and click on return button		
		//Expected: The product meant to be returned should be previously bought by the same customer.
		ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, PackageDiscountTestData.TC259_ProductsToReturn);
		asrt.assertEquals(PackageDiscountTestData.TC259_CustomerName,ActualCustomerName,"Customer name for sale is not the same as the customer name from return. " +"ExpectedCustomerNameFrom MethodInvoicePdf: " + ActualCustomerName + ", but found: " + PackageDiscountTestData.TC259_CustomerName);

		List<String> ReturnProductNames        = Details.productNames;
		List<String> ReturnProductQtyList      = Details.productQty;
		List<String> ReturnProductTotalList    = Details.productPrice;

		//Step 9: Scan gold SKU (eligible for package)
		//Step 10: Click on add to cart button
		//Expected: Check calculation
		//Billling Screen: No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX, Total Amount
		List<String> SaleProductNames         = new ArrayList<>();
		List<String> SaleProductQtyList       = new ArrayList<>();
		List<String> SaleProductTotalList     = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter = 1;
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1);
		appUtils.HamBurgerButtonClick("iconShop");

		String CvalueRG                 = "";
		String ItemNameInItemDetailsPge = "";
		String GrossWeight             	= "";
		String TotalWithoutTax         	= "";
		String WastageQty              	= "";
		String WastageAmt              	= "";
		String RgRate                   = "";

		for (String Sku : RequiredKeralSku) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			SaleProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			SaleProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			SaleProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_WastageQty");
			WastageAmt =  ScannedSkuDataMap.get("SKU_" + SkuCounter + "_WastageAmount");
			RgRate = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Step 11: Select any one of the product in cart
		//Step 12: Click on discount button
		//Step 13: Click on normal discount button
		//Step 14: Select package making discount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC263_DiscountCategory);

		//Step 15: select discount range
		//Step 16: Enter discount percentage
		//Step 17: Click on apply button
		//Note: check calculation
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);

		// Select option based on WastageQty range and set making percentage
		int SlabNumber = 0;
		double PackageDiscountPercent = 0;

		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = PackageDiscountTestData.TC259_PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = PackageDiscountTestData.TC259_PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = PackageDiscountTestData.TC259_PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + WastageQtyValue);
		} else {
			System.out.println("Wastage qty out of range: " + WastageQtyValue);
		}

		if (SlabNumber != 0) {
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				String Value = option.getAttribute("value"); //PKG_AP_1 or PKG_KL_1
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {

					Select.selectByValue(Value);

					System.out.println("Selected package: " + Value);

					// Set making percentage
					base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
					base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));

					break;
				} else {
					System.out.println("No matching package found for slab " + SlabNumber);
				}
			}
		}

		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
		double ExpectedDiscount = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;

		double CalculatedTotalWithoutTax = Double.parseDouble(SaleProductTotalList.get(0))-ExpectedDiscount;
		SaleProductTotalList.remove(SaleProductTotalList.size()-1);
		SaleProductTotalList.add(Double.toString(CalculatedTotalWithoutTax));

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String Discount 			= TransactionDataLine.get("Discount");
		String TotalGrossWeight 	= TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.addAll(ReturnProductNames);
		ProductNames.addAll(SaleProductNames);
		ProductQtyList.addAll(ReturnProductQtyList);
		ProductQtyList.addAll(SaleProductQtyList);
		ProductTotalList.addAll(ReturnProductTotalList); 
		ProductTotalList.addAll(SaleProductTotalList);

		//TranscationPageVerifyLines
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCount, ActualLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		
		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				//asrt.assertEquals(ExpectedItemNameInBillingPge, ActualItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
				asrt.assertTrue(ExpectedItemNameInBillingPge.trim().contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is " + ActualItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			}
			else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ExpectedItemNameInBillingPge, ActualItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
			}
			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ExpectedQtyInBillingPge, ActualQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTaxInBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTaxInBilling.get(i).getText().replace("\u20B9", "").replace(",", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replace("\u20B9", "").replace(",", "").trim()));
			//asrt.assertEquals(ExpectedTotalWithoutTaxInBillingPge.replace("-", ""), ActualTotalWithoutTaxInBillingPge.replace("-", ""),"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge.replace("-", "")), Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("\u20B9", "").replace(",", "").trim();
		asrt.assertEquals(ExpectedSubTotalInBillingPge, ActualSubTotalInBillingPge,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replace("\u20B9", "").replace(",", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = SubTotalSum*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ExpectedTax, ActualTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replace("\u20B9", "").replace(",", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscount+" in billing page");


		//Step 18: Select OG
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),PackageDiscountTestData.TC259_OldProduct);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		//Step 19: Click on OG own
		Thread.sleep(7000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));	

		//Step 20: Select configuration
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),PackageDiscountTestData.TC259_Configuration);

		//Step 21: Click on Add item button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(8000);
		List<WebElement>ErrorMessage = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));
		if (!ErrorMessage.isEmpty()) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
		}

		//Step 22: Select QC person
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);

		//Step 23: Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);

		//Step 24: Enter piece value
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), PackageDiscountTestData.TC259_Pieces);

		//Step 25: Enter gross weight
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), PackageDiscountTestData.TC259_GrossWeight);

		//Step 26: click on calculate button
		//Step 27: Click on Add to cart button
		//Expected: Check Old Gold Calculation
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(4000);
		double ActualCurrentGoldRate22kInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		double ExpectedCurrentGoldRate22k = Double.parseDouble(RgRate);
		asrt.assertEquals(ExpectedCurrentGoldRate22k, ActualCurrentGoldRate22kInItemDetailsPge,1.0,"Gold Rate mismatch: Expected is "+ExpectedCurrentGoldRate22k+" but found "+ActualCurrentGoldRate22kInItemDetailsPge+"in item details page");

		String ExpectedNetWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
		double NetWeightInItemDetailsPge = Double.parseDouble(ExpectedNetWeightInItemDetailsPge);
		double ExpectedGoldAmountInItemDetailsPge = Math.round(NetWeightInItemDetailsPge * ActualCurrentGoldRate22kInItemDetailsPge * 100.0) / 100.0;
		String GoldAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt"));
		double ActualGoldAmountInItemDetailsPge = Double.parseDouble(GoldAmountInItemDetailsPge);
		asrt.assertEquals(ExpectedGoldAmountInItemDetailsPge, ActualGoldAmountInItemDetailsPge,1.0,"GoldAmount mismatch: "+ExpectedGoldAmountInItemDetailsPge+" but got "+ActualGoldAmountInItemDetailsPge+" in item details page");

		String ExpectedGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String TotalAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));
		double ActualTotalAmountInItemDetailsPge = Double.parseDouble(TotalAmountInItemDetailsPge);
		double ExpectedTotalAmountInItemDetailsPge = ActualGoldAmountInItemDetailsPge;
		asrt.assertEquals(ExpectedTotalAmountInItemDetailsPge, ActualTotalAmountInItemDetailsPge,1.0, "Total amount mismatch: Expected is "+ExpectedTotalAmountInItemDetailsPge+" but got "+ActualTotalAmountInItemDetailsPge+" in item details page");

		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		if(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Cart has changed"))) {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}
		//Step 28: save estimate
		//Step 29:Recall estimate from cashier screen
		//Expected: Verify the invoice details
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		double SubTotalInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)-ActualTotalAmountInItemDetailsPge;
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC259_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		double CalculatedTax       = (Double.parseDouble(ActualSubTotalInBillingPge))*0.03;
		String Gst                 = String.format("%.2f",CalculatedTax).replace("-", "");
		String TaxableValue		   = Double.toString(Double.parseDouble(ActualSubTotalInBillingPge)).replace("-", "");
		String InvoiceTotal        = String.format("%.2f",SubTotalInBillingPge+CalculatedTax).replace("-", "");
		String TotalAmnt           = String.format("%.2f",SubTotalInBillingPge+CalculatedTax).replace("-", "");
		String NetTotal            = String.format("%.2f",SubTotalInBillingPge+CalculatedTax).replace("-", "");
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				);

		//Step 30: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),"ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"in Recall estimation page");

		List<String> ItemNamesFromRecallPageBeforeSaleReturn = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPageBeforeSaleReturn), "Recalled item is not same as the item in the Cart");

		//Step 31:Select the Transaction Type as sales return 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PackageDiscountTestData.TC259_TransactionTypeReturn);

		//Step 32:Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 33:Choose a sales representative
		//Step 34:Click on the Amount
		//Step 35: Post the return invoice
		//Expected: Verify return invoice
		String AmountDueForSaleReturnFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleReturnInFromBillingPge = Double.parseDouble(AmountDueForSaleReturnFromBillingPge);
		String ExpectedAmountForSaleReturnInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		double TaxForSaleReturnProducts = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("\u20B9", "").replace(",", "").replace("-", ""));
		if (AmountDueForSaleReturnInFromBillingPge<0.00) {
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
			String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");
			String ApproxWeight = AdvanceReceiptVoucherDetails.get("ApproxWeight");
			String ActualFixedGoldRate = AdvanceReceiptVoucherDetails.get("FixedGoldRate");
			String ActualDepositType = AdvanceReceiptVoucherDetails.get("DepositType");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(PackageDiscountTestData.TC259_PaymentMode, ActualPaymentMode,"Payment mode mismatch: Expected is "+PackageDiscountTestData.TC259_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			Map<String, String> CreditNoteDetails = pdfUtils.SalesReturnPDFCredit();
			String ActualGrossWt = CreditNoteDetails.get("Product1.GrossWt");
			String ActualStoneWt = CreditNoteDetails.get("Product1.StoneWt");
			String ActualNetWt = CreditNoteDetails.get("Product1.NetWt");
			String ActualAmount = CreditNoteDetails.get("Product1.Amount");
			String ActualTaxableValue   = CreditNoteDetails.get("TaxableValue");
			String ActualTotalNetValue  = CreditNoteDetails.get("TotalNetValue");
			String ActualPaymentDetails = CreditNoteDetails.get("PaymentDetails");
			double ExpectedTaxableValue = Double.parseDouble(SkuTotal);
			System.out.print("ExpectedTaxableValue: "+ExpectedTaxableValue);
			double TotalAmountInBillingPge = ExpectedTaxableValue+TaxForSaleReturnProducts;

			asrt.assertEquals(SkuGrossWeight, ActualGrossWt,"Gross Weight mismatch: Expected is "+SkuGrossWeight+" but found "+ActualGrossWt+" in credit note");
			asrt.assertEquals(SkuStoneWeight, ActualStoneWt,"StoneWt mismatch: Expected is"+SkuStoneWeight+" but found "+ActualStoneWt+" in credit note");
			asrt.assertEquals(SkuNetWeight, ActualNetWt,"NetWt mismatch: Expected is"+SkuNetWeight+" but found "+ActualNetWt+" in credit note");
			asrt.assertEquals(Double.parseDouble(SkuTotal), Double.parseDouble(ActualAmount),1.0,"Amount mismatch: Expected is"+SkuTotal+" but found "+ActualAmount+" in credit note");

			asrt.assertEquals(ExpectedTaxableValue, Double.parseDouble(ActualTaxableValue),1.0,"Taxable Value mismatch: Expected is"+ExpectedTaxableValue+" but found "+ActualTaxableValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualTotalNetValue),1.0,"Total Net Value mismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualTotalNetValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualPaymentDetails),1.0,"Payment Detailsmismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualPaymentDetails+" in credit note");
		}	

		//Step 36: Again Recall estimate from cashier screen
		//Step 37: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),"ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		List<String> ItemNamesFromRecallPageBeforeExchange = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPageBeforeExchange), "Recalled item is not same as the item in the Cart");

		//Step 38:Select the Transaction Type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PackageDiscountTestData.TC259_TransactionTypeExchange);

		//Step 39:Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 40:Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 41:Click on the Amount
		//Step 42: Post the exchange invoice
		//Expected: Verify Exchange invoice
		String AmountDueForExchangeFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForExchangeInFromBillingPge = Double.parseDouble(AmountDueForExchangeFromBillingPge);			
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
			String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");
			String ApproxWeight = AdvanceReceiptVoucherDetails.get("ApproxWeight");
			String ActualFixedGoldRate = AdvanceReceiptVoucherDetails.get("FixedGoldRate");
			String ActualDepositType = AdvanceReceiptVoucherDetails.get("DepositType");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(PackageDiscountTestData.TC259_PaymentMode, ActualPaymentMode,"Payment Mode mismatch: Expected is "+PackageDiscountTestData.TC259_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			double GrossWeightOG = Double.parseDouble(ExpectedGrossWeightInItemDetailsPge);
			String ExpectedGrossWeightFromBillingPge = String.format("%.3f", GrossWeightOG);  
			String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",ActualCurrentGoldRate22kInItemDetailsPge);
			double TotalAmountForOG = Double.parseDouble(TotalAmountInItemDetailsPge);
			String ExpectedTotalAmountForOG = String.format("%.2f", TotalAmountForOG);  			
			Map<String, String> OgPurchaseBill = pdfUtils.OGPdfValidation("Adjustment");
			String GrossWeightFromInvoice = OgPurchaseBill.get("GrossWt1");
			String NetWeightFromInvoice = OgPurchaseBill.get("NetWt1");
			String GoldRateFromInvoice = OgPurchaseBill.get("Rate1");
			String TotalAmountFromInvoice = OgPurchaseBill.get("TotalAmount1");
			String PaymentDetailsFromInvoice = OgPurchaseBill.get("PaymentDetails");
			asrt.assertEquals(ExpectedGrossWeightFromBillingPge, GrossWeightFromInvoice,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+GrossWeightFromInvoice+" in purchase bill");		
			asrt.assertEquals(ExpectedNetWeightInItemDetailsPge, NetWeightFromInvoice,"Net Weight mismatch: "+ExpectedNetWeightInItemDetailsPge+" but got "+NetWeightFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedOGPurchaseRateFromItemDetailsPge, GoldRateFromInvoice,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, TotalAmountFromInvoice,"Total amount mismatch: "+ExpectedTotalAmountForOG+" but got "+TotalAmountFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, PaymentDetailsFromInvoice,"Payment details mismatch: "+ExpectedTotalAmountForOG+" but got "+PaymentDetailsFromInvoice+" in purchase bill");
		}

		//Step 43:again Recall estimate from cashier screen
		//Step 44: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),"ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		List<String> ItemNamesFromRecallPageBeforeSale = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPageBeforeSale), "Recalled item is not same as the item in the Cart");

		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PackageDiscountTestData.TC259_TransactionTypeSales);

		//Step 45:Select the Transaction Type as sales 
		//Step 46: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 47: Choose a sales representative
		//Expected: Cashier Screen after recall Estimate:
		//No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashier, SaleProductNames, SaleProductQtyList, SaleProductTotalList, "Cashier");

		//Discount
		String ActualDiscountCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountCashierScreen), ExpectedDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedDiscount+" but got "+ActualDiscountCashierScreen+" in cashier page");

		//Step 48: Click on the Amount
		//Step 49: Select a Payment method(Cash or Card)
		String GrossAmountForSale= TotalWithoutTax;
		String TaxableValueForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"));
		String TotalValueForSale=base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		String TotalNetValueForSale=base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		String TotalQtyPcsForSale = String.valueOf(RequiredKeralSku.size());
		String TotalDiscountForSale = Double.toString(ExpectedDiscount);
		String TotalNetWeightForSale = TotalNetWeight;
		String TotalGrossWeightForSale= SaleProductQtyList.get(0);
		String AdjustmentForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("PaymentsField", "h4"));;
		String AmountDueForSaleFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleInFromBillingPge = Double.parseDouble(AmountDueForSaleFromBillingPge);
		String PaymentAmountForSale ="";
		if (AmountDueForSaleInFromBillingPge==0.00) {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			PaymentAmountForSale = "";
		}
		else {
			PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");
		}

		//Step 50: Post the Invoice
		//Expected: Check final invoice details
		//Save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC259_TaxInvoice");		
		String TaxInvoice =System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String TotalDiamond = "";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				TaxInvoice,
				GrossAmountForSale,
				TotalDiscountForSale,
				TotalDiamond,
				TotalQtyPcsForSale,
				TotalNetWeightForSale,
				TotalGrossWeightForSale,
				TaxableValueForSale,
				TotalNetValueForSale,
				PaymentAmountForSale,
				AdjustmentForSale,
				RequiredKeralSku,
				ScannedSkuDataMap
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

		//Return Keral Sku
		appUtils.SalesReturnFlow(PackageDiscountTestData.TC263_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC263_ReturnProducts);
		appUtils.CounterFlow(PackageDiscountTestData.TC259_FromCounter,PackageDiscountTestData.TC259_MetalType,RequiredKeralSku, UtilityTestData.Terminal);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	//<summary>
	// Test Case Title : Sales return of package discount applied invoice
	// Automation ID : TC260
	// Author: Aiswarya
	// </summary>
	public void TC260_PackageDiscount() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: Old package discount applied sale receipt
		Thread.sleep(3000);
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC260_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(5000);
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                = "";
		String ItemNameInItemDetailsPge = "";
		String GrossWeight             = "";
		String TotalWithoutTax         = "";
		String WastageQty              = "";
		String WastageAmt              = "";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredKeralSku.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = RequiredKeralSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt =  ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
		}
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC260_DiscountCategory);
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);
		int SlabNumber = 0;
		double PackageDiscountPercent = 0;
		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = PackageDiscountTestData.TC260_PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = PackageDiscountTestData.TC260_PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = PackageDiscountTestData.TC260_PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + WastageQtyValue);
		} else {
			System.out.println("Wastage qty out of range: " + WastageQtyValue);
		}

		if (SlabNumber != 0) {
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				String Value = option.getAttribute("value"); //PKG_AP_1 or PKG_KL_1
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {
					Select.selectByValue(Value);
					System.out.println("Selected package: " + Value);
					base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
					base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));
					break;
				} else {
					System.out.println("No matching package found for slab " + SlabNumber);
				}
			}
		}
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC260_ProformaInvoice");
		Thread.sleep(2000);
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PackageDiscountTestData.TC260_TransactionType);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC260_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;	
		Thread.sleep(3000);
		String InvoiceNo = pdfUtils.ExtractReceiptNoFrmSaleInvoice(InvoicePdfPath);
		String ActualCustomerName = PdfUtilities.ExtractCustomerNameFromSaleInvoice();
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//Step 3 : Select customer
		//Step 4 : Click on Add to Sale button
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC260_CustomerNo, UtilityTestData.MenuBarOptions[0]);

		//Step 5 : Click on customer adjustment button
		//Step 6 : Click on return transaction button at the billing screen
		//Step 7 : Enter receipt number
		//Step 8 : Select return product (package discount applied product)
		//Step 9 : Click on return button
		//Expected : The product meant to be returned should be previously bought by the same customer. 
		ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, PackageDiscountTestData.TC260_ReturnProducts);

		String ExpectedCustomerName = PackageDiscountTestData.TC260_CustomerName;
		asrt.assertEquals(ActualCustomerName,ExpectedCustomerName ," Customer name for sale is not the same as the customer name from return. " +"Expected: " + ActualCustomerName + ", but found: " + ExpectedCustomerName+"in Billing Screen");

		//Expected : Note:Billing Screen *No of Product lines  *Displayed Item Name  *Displayed Quantity *Displayed TOTAL (without Tax)  *Displayed Subtotal  *Discounts  *TAX  *Total Amount 
		double ExpectedDiscount = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax 		        = TransactionDataLine.get("Tax");
		String TotalAmount      = TransactionDataLine.get("TotalAmount");
		String NetTotal         = TransactionDataLine.get("NetTotal");
		String LinesCount       = TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		String ActualItemNameInBillingPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInBillingPage = ItemNameInItemDetailsPge.split(" - ")[1].trim();
		//asrt.assertEquals(ActualItemNameInBillingPage, ExpectedItemNameInBillingPage,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");
		asrt.assertTrue(ExpectedItemNameInBillingPage.contains(ActualItemNameInBillingPage),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPage+" but got "+ActualItemNameInBillingPage+" in billing page");


		String ActualQuantityInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
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

		//Step 10 : Click on the Amount
		//Step 11 : Select a Payment method as convert advance
		//Step 12 : Select transaction type as normal
		//Step 13 : Select sales person
		//Step 14 : Select the due date
		//Step 15 : Enter remarks
		//Step 16 : Click on nominee details
		//Step 16.1 : Enter nominee name
		//Step 16.2 : Select nominee relation
		//Step 16.3 : Click on check box (Same as customer address)
		//Step 16.4 : Click on OK button
		//Step 17 : Click on Deposit button
		Thread.sleep(3000);
		appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
				UtilityTestData.SalePersonNumber,
				UtilityTestData.DueYear,
				UtilityTestData.DobYear,				
				UtilityTestData.NomineeName,
				UtilityTestData.NomineeRelation);
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(1000);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));

		//Expected : Check ADVANCE RECEIPT VOUCHER details  *Towards the advance against purchase of approximate value *Fixed Gold rate *Deposit Type  *Advance Received * save receipt id for future reference
		Map<String, String> NormalAdvPdf = pdfUtils.NormalAdvancePdfValidation();	
		String AdvanceReceived = NormalAdvPdf.get("AdvanceReceived");
		String FixedGoldRate = NormalAdvPdf.get("FixedGoldRate");
		String MaxGoldRate = NormalAdvPdf.get("MaxGoldRate");
		String ApproxWeight = NormalAdvPdf.get("ApproxWeight");
		String DepositType = NormalAdvPdf.get("DepositType");
		double PdfAdvanceReceived = Double.parseDouble(AdvanceReceived.replaceAll("[^\\d.]", "").trim());
		double PdfFixedGoldRate = Double.parseDouble(FixedGoldRate.replaceAll("[^\\d.]", "").trim());
		double PdfMaxGoldRate = Double.parseDouble(MaxGoldRate.replaceAll("[^\\d.]", "").trim());

		asrt.assertEquals(PdfAdvanceReceived,Double.parseDouble(ActualTotalAmountInBillingPage),1.0, "Mismatch in total amount in billing screen actual "+PdfAdvanceReceived+" and Expected "+Double.parseDouble(ActualTotalAmountInBillingPage)+"in Normal Advance PDF");
		asrt.assertEquals(PdfMaxGoldRate, PdfFixedGoldRate,1.0, "Mismatch in Fixed Gold Rate actual "+PdfMaxGoldRate+" and Expected "+PdfFixedGoldRate+" Max Gold Rate in Normal Advance PDF");
		asrt.assertEquals(PackageDiscountTestData.TC260_DepositType, DepositType, "Mismatch in Deposit Type Actual "+PackageDiscountTestData.TC260_DepositType+" and Expected "+DepositType+ " in Normal Advance PDF");

		Double WeightValue = PdfAdvanceReceived/PdfMaxGoldRate;
		BigDecimal CalcWeightValue = new BigDecimal(WeightValue).setScale(3, RoundingMode.HALF_UP);
		double ApproxWeightPdfValue = Double.parseDouble(ApproxWeight.replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ApproxWeightPdfValue, CalcWeightValue.doubleValue(),1.0, "Mismatch in Approx Weight Value Actual "+ApproxWeightPdfValue+" and Expected "+CalcWeightValue.doubleValue()+" Approx Weight in Normal Advance PDF");

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(3000);
		appUtils.CounterFlow(PackageDiscountTestData.TC256_FromCounterSKU,PackageDiscountTestData.TC260_MetalType,RequiredKeralSku, UtilityTestData.Terminal);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Package discount with apply promotion discount
	// Automation ID : TC262
	// Author: Sangeetha M S
	// </summary>
	public void TC262_PackageDiscount() throws Exception
	{
		// Step 1. Login to POS
		Thread.sleep(1000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2. Click on Transaction button
		Thread.sleep(1000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite To take a promotion from erp
//		Map<String, String> PromotionData = erpUtils.GetValidPromotionDiscount(UtilityTestData.Category);
//		if (PromotionData == null) 
//		{
//			erpUtils.CreateValidPromotionDiscountAgreement(UtilityTestData.Category);
//			PromotionData = erpUtils.GetValidPromotionDiscount(UtilityTestData.Category); 
//		}
		Map<String, String> PromotionData  =	erpUtils.CreateDeleteValidPromotionDiscountAgreement(UtilityTestData.Category);	
		String PromotionType = PromotionData.get("PromotionType");
		String MultiplyAmount = PromotionData.get("Amount");
		String Multiplier = PromotionData.get("Multiplier");
		MultiplyAmount = MultiplyAmount.replaceAll("[^0-9.]", ""); 

		// Step 3. Select customer
		// Step 4. Click on add to estimate button
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC262_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		// Step 5. Scan SKU (eligible for package discount)
		//Expected :Check calculation
		//Note : Billing Screen *No of Product lines*Displayed Item Name,*Displayed Quantity, *Displayed TOTAL (without Tax),*Displayed Subtotal, *Discounts,*TAX, *Total Amount
		// Step 6. Click on Add to cart button
		// Step 7. Select the product in cart
		Thread.sleep(1000);
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1);

		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG = "";
		String ItemNameInItemDetailsPge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		String WastageQty = "";
		String WastageAmt = "";
		String RGRate="";
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredKeralSku.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku = RequiredKeralSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt =  ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
			RGRate=ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//To calculate Promotion discount of Sku based on the Promotion agreement added
		Thread.sleep(500);
		double Amount = Double.parseDouble(TotalWithoutTax);
		double PromotionAmount = 0.00;
		Thread.sleep(500);
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

		Map<String, String> GoldSkuTransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku,ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(GoldSkuTransactionDataLine, ScannedSkuDataMap);

		// Step 8. Click on discount button
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		// Step 9. Click on normal discount button
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		// Step 10. Select package making discount
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC262_DiscountCategory);

		// Step 11. Select discount range
		// Step 12. Enter discount percentage
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);

		// Select option based on WastageQty range and set making percentage
		int SlabNumber = 0;
		double PackageDiscountPercent = 0;
		Thread.sleep(1000);
		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9)
		{
			SlabNumber = 1; // Slab A
			PackageDiscountPercent = PackageDiscountTestData.TC262_PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} 
		else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) 
		{
			SlabNumber = 2; // Slab B
			PackageDiscountPercent = PackageDiscountTestData.TC262_PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} 
		else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) 
		{
			SlabNumber = 3; // Slab C
			PackageDiscountPercent = PackageDiscountTestData.TC262_PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + WastageQtyValue);
		} 
		else 
		{
			System.out.println("Wastage qty out of range: " + WastageQtyValue);
		}

		if (SlabNumber != 0) 
		{
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;

			for (WebElement option : Select.getOptions()) 
			{
				String value = option.getAttribute("value"); 
				if (value != null && value.contains("PKG") && value.contains(String.valueOf(SlabNumber))) 
				{
					FinalValue = value;
					break;
				}
			}

			if (FinalValue != null) 
			{
				Select.selectByValue(FinalValue);
				base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
				base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));
			} 
			else 
			{
				System.out.println("No matching package found for slab " + SlabNumber);
			}
		}

		// Step 13. Click on apply button
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));

		//To calculate Package discount value
		double ExpectedPackageDiscount = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedPackageDiscount = Math.round(ExpectedPackageDiscount * 100.0) / 100.0;
		double ExpectedTotalWithoutTaxInBillingPage = Double.parseDouble(TotalWithoutTax)-ExpectedPackageDiscount;
		String PackageActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String PackageSubTotal = TransactionDataLine.get("Subtotal");
		String PackageTotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String PackageTotalNetWeight   = TransactionDataLine.get("NetWeight");
		String PackageTax 		        = TransactionDataLine.get("Tax");
		String PackageTotalAmount      = TransactionDataLine.get("TotalAmount");
		String PackageNetTotal         = TransactionDataLine.get("NetTotal");
		String PackageLinesCount       = TransactionDataLine.get("LinesCount");

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		List<WebElement> ProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(ProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		String ActualItemNameInBillingPage = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedItemNameInBillingPage = ItemNameInItemDetailsPge.trim();
		asrt.assertTrue(ExpectedItemNameInBillingPage.contains(ActualItemNameInBillingPage),"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");

		String ActualQuantityInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ExpectedQuantityInBillingPage = GrossWeight;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPage), Double.parseDouble(ExpectedQuantityInBillingPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPage + " but got " + ActualQuantityInBillingPage + " in billing page");

		String ActualTotalWithoutTaxInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTotalWithoutTaxInBillingPge = Double.parseDouble(TotalWithoutTax)-ExpectedPackageDiscount;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),ExpectedTotalWithoutTaxInBillingPage,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");

		String ExpectedSubTotalInBillingPage = ActualTotalWithoutTaxInBillingPage.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPage = PackageSubTotal.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPage),Double.parseDouble(ExpectedSubTotalInBillingPage), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPage + " but found " + ActualSubTotalInBillingPage+ " in billing page");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedPackageDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedPackageDiscount+" but got "+ActualDiscount+" in billing page");

		String TaxInBillingPage = PackageTax.replaceAll("[^\\d.]", "").trim();
		double ActualTaxInBillingPage = Double.parseDouble(TaxInBillingPage);
		double ExpectedTaxInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)*0.03;
		asrt.assertEquals(ActualTaxInBillingPage,ExpectedTaxInBillingPage, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPage + " but found " + ActualTaxInBillingPage+ " in billing page");

		double ExpectedTotalAmountInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)+ ActualTaxInBillingPage;
		String ActualTotalAmountInBillingPage = PackageTotalAmount.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInBillingPage), ExpectedTotalAmountInBillingPage, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPage + " but found " + ActualTotalAmountInBillingPage + " in billing page");

		// Step 14. Go to discounts
		// Step 15. Click on apply promotion discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Promotion Discount"));

		Thread.sleep(3000);
		Map<String, String> TransactionDataLines = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal = TransactionDataLines.get("Subtotal");
		String TotalGrossWeight = TransactionDataLines.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLines.get("NetWeight");
		String Tax 		        = TransactionDataLines.get("Tax");
		String TotalAmount      = TransactionDataLines.get("TotalAmount");
		String NetTotal         = TransactionDataLines.get("NetTotal");
		String LinesCount       = TransactionDataLines.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String PromotionActualSubTotalInBillingPage = SubTotal.replaceAll("[^\\d.]", "").trim();
		String PromotionActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double PromotionExpectedTotalAmountInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)+ ActualTaxInBillingPage;

		// Step 16. Click on Save to estimation button
		// Step 17. Save estimate
		//Expected : Verify the Invoice details
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		Thread.sleep(500);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC262_ProformaInvoice");
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

		// Step 18. Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Step 19. Select estimate and click on recall estimation
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		// Step 20. Select the Transaction Type as Sales
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PackageDiscountTestData.TC262_TransactionTypeSales);

		// Step 21. Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 22. Choose a sales representative(Not available after data refresh)
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:Cashier Screen after recall No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
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

		Thread.sleep(2000);
		String ActualPromotionDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double PromotionDiscount=Double.parseDouble(ActualPromotionDiscountInCashierScreen);
		double ExpectedPromotionDiscount=Double.parseDouble(PackageActualDiscount)+PromotionAmount;

		asrt.assertEquals(Double.parseDouble(ActualPromotionDiscountInCashierScreen), Double.parseDouble(PromotionActualDiscount),1.0,"Discount value mismatch: Expected is "+PromotionActualDiscount+" but got "+ActualPromotionDiscountInCashierScreen+" in cashier screen");

		String ActualTotalWithoutTaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in cashier screen");

		double ExpectedTotalWithoutTaxInCashierScreen =0.00;
		if(PromotionType.equals("Append"))
		{
			ExpectedTotalWithoutTaxInCashierScreen = ExpectedTotalWithoutTaxInBillingPage-PromotionAmount;
		}
		else if(PromotionType.equals("Overwrite"))
		{
			ExpectedTotalWithoutTaxInCashierScreen = Amount-ExpectedPromotionDiscount;
		}

		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),ExpectedTotalWithoutTaxInCashierScreen,1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in cashier screen");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.03;

		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)   +   Double.parseDouble(TaxInCashierScreen);
		ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in cashier screen");

		// Step 23. Click on the Amount
		// Step 24. Select a Payment method (Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// Step 25. Post the Invoice
		//Expected : Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(2000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC262_SaleInvoice");
		Thread.sleep(1000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TotalWithoutTax;
		String TotalDiscount=ActualPromotionDiscountInCashierScreen;
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
				RequiredKeralSku,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 

		//appUtils.SalesReturnFlow(PackageDiscountTestData.TC262_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC262_ReturnProducts);
		//appUtils.CounterFlow(PackageDiscountTestData.TC262_FromCounter,PackageDiscountTestData.TC262_MetalType,RequiredKeralSku, UtilityTestData.Terminal);	
	}

	// <summary>
	// Test Case Title : Invoice with 8 sku single package
	// Automation ID : TC254
	// Author: Christy Reji
	// </summary>
	public void TC254_PackageDiscount() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search a customer with Phone number
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC254_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 5: Scan 8 SKU's(eligible for package discount) and click on Add to cart button
		//Note: KeralSku
		//Expected 5:Check calculation
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 8);
		System.out.println("RequiredKeralSku Taken : "+RequiredKeralSku);
		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG                 = "";
		String ItemNameInItemDetailsPge = "";
		String GrossWeight              = "";
		String TotalWithoutTax          = "";
		String WastageQty               = "";
		String WastageAmt               = "";
		String Prodnames                = "";
		List<String> WastageAmtList     = new ArrayList<>();
		List<String> CvalueRGList       = new ArrayList<>();
		List<String> WastageQtyList     = new ArrayList<>();
		List<String> GrossWtList        = new ArrayList<>();
		List<String> ItemNameList       = new ArrayList<>();

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredKeralSku.size(); i++) {
			int SkuNumber            = i + 1;
			String Sku               = RequiredKeralSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG                 = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight              = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax          = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty               = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt               = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
			Prodnames                = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");

			// Add values to the lists
			WastageAmtList.add(WastageAmt);
			CvalueRGList.add(CvalueRG);
			WastageQtyList.add(WastageQty);
			GrossWtList.add(GrossWeight);
			ItemNameList.add(Prodnames);

			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		// Note:Billing Screen *No of Product lines  *Displayed Item Name  *Displayed Quantity  *Displayed TOTAL (without Tax)
		// *Displayed Subtotal *Discounts  *TAX  *Total Amount
		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLineGold = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotalGold         = TransactionDataLineGold.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String DiscountGold         = TransactionDataLineGold.get("Discount");
		String TotalGrossWeightGold = TransactionDataLineGold.get("GrossWeight");
		String TotalNetWeightGold   = TransactionDataLineGold.get("NetWeight");
		String TaxGold              = TransactionDataLineGold.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmountGold      = TransactionDataLineGold.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotalGold         = TransactionDataLineGold.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCountGold       = TransactionDataLineGold.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLineGold, ScannedSkuDataMap);

		//Step 6: In billing, Go to discount
		// 6.1.Select Normal discount
		//  6.2.Select Package making discount
		//  6.3.Select Package code
		//  6.4.package discount percentage
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		double TotalExpectedDiscount = 0.0;   // Final sum of ExpectedDiscount for all SKUs

		for (int i = 0; i < ItemNameList.size(); i++) {

			//Click the product i 
			WebElement Product = base.GetElement(NormalSaleGoldAndSilverObj.Sel_TheListOfName("tillLayout-ProductNameField","h5")).get(i);	
			if (i > 4) {base.ScrollToWebElements(Product);}
			Product.click();

			Thread.sleep(3000);;
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
			Thread.sleep(1000);
			base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC254_DiscountCategory);

			//All existing code (slab selection + percentage set)
			double WastageQtyValue = Double.parseDouble(WastageQtyList.get(i));

			int SlabNumber                = 0;
			double PackageDiscountPercent = 0;
			Thread.sleep(3000);
			if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
				SlabNumber = 1;
				PackageDiscountPercent = PackageDiscountTestData.TC254_PackageDiscountPercent_SlabA;
			} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
				SlabNumber = 2;
				PackageDiscountPercent = PackageDiscountTestData.TC254_PackageDiscountPercent_SlabB;
			} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
				SlabNumber = 3;
				PackageDiscountPercent = PackageDiscountTestData.TC254_PackageDiscountPercent_SlabC;
			}

			if (SlabNumber != 0) {
				Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));

				for (WebElement option : Select.getOptions()) {
					String Value = option.getAttribute("value");

					if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {
						Select.selectByValue(Value);

						base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
						base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));
						break;
					}
				}
			}

			base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
			Thread.sleep(1000);;

			// Step 3: Calculate ExpectedDiscount for this SKU
			double WastageAmnt       = Double.parseDouble(WastageAmtList.get(i));
			double C_ValueRG         = Double.parseDouble(CvalueRGList.get(i));
			double ExpectedDiscount  = WastageAmnt - (C_ValueRG * PackageDiscountPercent) / 100;
			ExpectedDiscount         = Math.round(ExpectedDiscount * 100.0) / 100.0;
			System.out.println("Expected Discount for SKU " + (i + 1) + ": " + ExpectedDiscount);

			//Add to total sum
			TotalExpectedDiscount  += ExpectedDiscount;
		}

		// Step 5: Print final combined total
		System.out.println("Total Expected Discount for all SKUs: " + TotalExpectedDiscount);

		//Billing page calculation after applying discount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax 		                        = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotal                         = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows        = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());	
		List<WebElement> TotalElements         = driver.findElements(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		double TotalWithoutTaxAfterDiscount    = 0.0;

		for (int i = 0; i < TotalElements.size(); i++) {

			String Text                   = TotalElements.get(i).getText().replaceAll("[^\\d.]", "").trim();
			double Taxvalue               = Double.parseDouble(Text);
			TotalWithoutTaxAfterDiscount += Taxvalue;
			System.out.println("Total Without Tax for SKU " + (i + 1) + ": " + Taxvalue);
		}

		String ActualDiscount                   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxInBillingPage         = Double.parseDouble(SubTotal)*0.03;
		double ExpectedTotalAmountInBillingPage = Double.parseDouble(SubTotal) + Double.parseDouble(Tax);

		asrt.assertEquals(LinesCount, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + LinesCount + " in billing page");
		asrt.assertEquals(Double.parseDouble(SubTotal),TotalWithoutTaxAfterDiscount,1.0,"TotalWithoutTax mismatch: Expected is "+TotalWithoutTaxAfterDiscount+" but got "+SubTotal+ " in billing page");
		asrt.assertEquals(Double.parseDouble(ActualDiscount), TotalExpectedDiscount,1.0,"Discount value mismatch: Expected is "+TotalExpectedDiscount+" but got "+ActualDiscount+" in billing page");
		asrt.assertEquals(Double.parseDouble(Tax),ExpectedTaxInBillingPage, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPage + " but found " + Tax+ " in billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmountInBillingPage, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPage + " but found " + TotalAmount + " in billing page");

		//Step 8: Save esimate
		//Expected : Very the invoice details	
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber  = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName  = pdfUtils.DownloadAndRenameLatestPDF("TC254_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath  = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                  = Tax;
		String ExpectedTaxableValue = SubTotal;
		String InvoiceTotal         = TotalAmount;
		String TotalAdjustment      = "";		
		String AdditionalDiscount   = ActualDiscount;

		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				SubTotalGold,
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		//Step 9: Recall estimate from cashier screen
		//Step 10.Select estimate and click on save to estimate button
		//Expected Verify whether the recalled item is the same as the item in the cart.
		// Note:Cashier Screen after recall  *No of Product lines *Displayed Item Name *Displayed Quantity  *Displayed TOTAL (without Tax)
		// *Displayed Subtotal  *Discounts  *TAX *Total Amount				 
		System.out.println("recall page names: "+ItemNameList);
		System.out.println("recall page GrossWtList: "+GrossWtList);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),"ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));		
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"");

		//Validation
		appUtils.RecallEstimateItemValidation(
				ItemNameList,
				GrossWtList,
				ItemNameList,
				GrossWtList,
				Double.parseDouble(SubTotal),
				Double.parseDouble(Tax),
				Double.parseDouble(InvoiceTotal),
				Integer.parseInt(LinesCount), 
				"Package discount keral SKU "
				);

		//Step 11: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PackageDiscountTestData.TC257_TransactionType);

		//Step 12: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Choose a sales representative
		Thread.sleep(4000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Step 14: Click on the Amount
		//Step 15: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 16: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice   = pdfUtils.DownloadAndRenameLatestPDF("TC254_FinalSaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount    = SubTotalGold;
		String TotalDiscount  = AdditionalDiscount;
		String TotalQtyPcs    = LinesCount;
		String NetValue       = PaymentAmount;
		String TaxableValue   = SubTotal;
		String PdfInvoiceNo   = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				RequiredKeralSku,
				ScannedSkuDataMap);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Reason for Comment: Product available in the Somajiguda store, return and counter-in causing a delay of approximately 45 minutes during single execution.
		//appUtils.SalesReturnFlow(PackageDiscountTestData.TC254_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC254_ReturnProducts);
		//appUtils.CounterFlow(PackageDiscountTestData.TC254_FromCounter,PackageDiscountTestData.TC254_MetalType,RequiredKeralSku, UtilityTestData.Terminal);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Invoice with package discount ,making per gram discount,roundoff,stone discount,diamond discount
	// Automation ID : TC258
	// Author: Benson
	// </summary>
	public void TC258_PackageDiscount() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search a customer with Phone number
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC258_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(5000);

		//Step 5 : Scan Gold(Keral),Gold , uncut(vz) and Diamond SKU's and click on Add to cart button
		//Step 6: Select any one of the sales agent
		//Expected: Check calculation

		//Note: KeralSku
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1); 
		if (RequiredKeralSku.size() != 1) {
		    throw new Exception("Required Keral SKU not available. Test stopped.");
		}
		appUtils.HamBurgerButtonClick("iconShop");

		//Note: GoldSku
		List<String> SkuListGold = appUtils.SelectMultipleSKUs(
				PackageDiscountTestData.TC258_SkuCount,
				PackageDiscountTestData.TC258_TranferType,
				PackageDiscountTestData.TC258_FromCounterGold,
				PackageDiscountTestData.TC258_MetalTypeGold);
		appUtils.HamBurgerButtonClick("iconShop");

		//Note: Uncut Sku
		List<String> SkuListUncut = appUtils.FetchPieceRateItem(Utility_TestData.UncutVirazSkus, 1);
		appUtils.HamBurgerButtonClick("iconShop");

		//Note: Diamond Sku
		List<String> SkuListDiamond = appUtils.SelectMultipleSKUs(
				PackageDiscountTestData.TC258_SkuCount,
				PackageDiscountTestData.TC258_TranferType,
				PackageDiscountTestData.TC258_FromCounterDiamond,
				PackageDiscountTestData.TC258_MetalTypeGold);
		appUtils.HamBurgerButtonClick("iconShop");

		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(RequiredKeralSku);
		SkuList.addAll(SkuListGold);
		SkuList.addAll(SkuListUncut);
		SkuList.addAll(SkuListDiamond);

		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> ProductTotal = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		double Keral_WastageQty=0.00;
		double Keral_WastageAmt=0.00;
		double Keral_RgCvalue=0.00;
		double Gold_RgCvalue=0.00;
		double Diamond_RgCvalue=0.00;
		double Uncut_RgCvalue=0.00;

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotal.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			if(SkuNumber==1) {
				Keral_WastageQty=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty"));
				Keral_WastageAmt=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount"));
				Keral_RgCvalue=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG"));
			}
			if(SkuNumber==2) {
				Gold_RgCvalue=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG"));
			}
			if(SkuNumber==3) {
				Uncut_RgCvalue=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueSTONES"));
			}
			if(SkuNumber==4) {
				Diamond_RgCvalue=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueDIAMOND"));
			}

			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Select keral product in cart
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("row tillLayout-SelectedLinesFields","1"));

		//Step 7: In billing, Go to discount->Normal discount:
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 7.1: Select discount category as"Package Making discount"
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC258_DiscountCategory);

		//Step 7.2: Select Package code
		//Step 7.3: Add "Apply making percentage"
		// Select option based on WastageQty range and set making percentage
		int SlabNumber = 0;
		double PackageDiscountPercent = 0;

		if (Keral_WastageQty >= 2.5 && Keral_WastageQty <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + Keral_WastageQty);          
		} else if (Keral_WastageQty > 7.9 && Keral_WastageQty <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + Keral_WastageQty);
		} else if (Keral_WastageQty > 9.9 && Keral_WastageQty <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + Keral_WastageQty);
		} else {
			System.out.println("Wastage qty out of range: " + Keral_WastageQty);
		}

		if (SlabNumber != 0) {
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				String Value = option.getAttribute("value"); //PKG_AP_1 or PKG_KL_1
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {

					Select.selectByValue(Value);

					System.out.println("Selected package: " + Value);

					// Set making percentage
					base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
					base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));

					break;
				} else {
					System.out.println("No matching package found for slab " + SlabNumber);
				}
			}
		}

		//Step 7.4: Click on Apply discount
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
		double PackageDiscount = Keral_WastageAmt - (Keral_RgCvalue*PackageDiscountPercent)/100;
		PackageDiscount = Math.round(PackageDiscount * 100.0) / 100.0;

		//SELECT GOLD Product in cart
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("row tillLayout-SelectedLinesFields","2"));

		//Step 8: Click a normal discount.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 8.1: Select a discount category(Line Making Discount).  
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC258_GoldDiscountCategory);

		//Step 8.2: Select making discount type-Reduce Making.
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("LineMkDiscype"), PackageDiscountTestData.TC258_DiscountType);

		//Step 8.3:Discount type as "Percentage"(Default)
		//Step 8.4: Enter Discount value.
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: Linemkdiscamt"), PackageDiscountTestData.TC258_FirstDiscountValue);
		Thread.sleep(2000); 

		//Step 8.5: Select Category.(no need for line making discount)
		//Step 8.6: Click on apply discount.
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsLineMaking", "Apply discount"));
		double MakingDiscount = (Gold_RgCvalue)*Double.parseDouble(PackageDiscountTestData.TC258_FirstDiscountValue)/100;
		MakingDiscount = Math.round(MakingDiscount * 100.0) / 100.0;

		//Click on Uncut Product in cart
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("row tillLayout-SelectedLinesFields","3"));

		//Step	9: Select Normal discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step  9.1: Select discount category Diamond/Stone discount.
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC258_DiscountCategoryStone);

		//Step	9.2: Select type stone discount.
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("StnDmdDiscType"), PackageDiscountTestData.TC258_DiscountTypeStone);

		//Step  9.3:Discount sub type as "Percentage"(Default)
		//Step	9.4: Enter discount value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("stnInput"),PackageDiscountTestData.TC258_DiscountValue);
		Thread.sleep(2000); 
		
		//Step	9.5: Click on Apply
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsStone","Apply discount"));			
		double StoneDiscount = (Uncut_RgCvalue) *(Double.parseDouble(PackageDiscountTestData.TC258_DiscountValue)/100);
		System.out.println("StoneDiscount "+StoneDiscount);	

		//Click on Diamond Product in cart
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("row tillLayout-SelectedLinesFields","4"));

		//Step 10: Select Normal discount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 10.1: Select diamond discount
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC258_DiscountCategoryStone);

		//Step 10.2: Select making discount type as diamond discount
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("StnDmdDiscType"), PackageDiscountTestData.TC258_DiscountTypeDiamond);

		//Step 10.3: Discount type as "Percentage"
		//Step 10.4: Add a discount value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("stnInput"),PackageDiscountTestData.TC258_DiscountValue);
		Thread.sleep(2000); 
		
		//Step 10.5: Click on Apply
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsStone","Apply discount"));
		double DiamondDiscount = (Diamond_RgCvalue) * (Double.parseDouble(PackageDiscountTestData.TC258_DiscountValue)/100);
		double ExpectedTotalDiscount=PackageDiscount+MakingDiscount+StoneDiscount+DiamondDiscount;

		List<Double>Discounts=new ArrayList<>();
		Discounts.add(PackageDiscount);
		Discounts.add(MakingDiscount);
		Discounts.add(StoneDiscount);
		Discounts.add(DiamondDiscount);

		int i=0;
		for( String Total: ProductTotal) {
			double TotalWithoutTax=Double.parseDouble(Total)-Discounts.get(i);
			ProductTotalList.add(Double.toString(TotalWithoutTax));
			i++;
		}

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		System.out.println(ProductTotal);
		System.out.println(ProductTotalList);
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLineInBillingPge.get("Subtotal");
		String Tax 					= TransactionDataLineInBillingPge.get("Tax");
		String TotalAmount 			= TransactionDataLineInBillingPge.get("TotalAmount");
		String TotalGrossWeight     = TransactionDataLineInBillingPge.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLineInBillingPge.get("NetWeight");
		String NetTotal         = TransactionDataLineInBillingPge.get("NetTotal");
		String LinesCount       = TransactionDataLineInBillingPge.get("LinesCount");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");
		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), ExpectedTotalDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedTotalDiscount+" but got "+ActualDiscount+" in billing page");

		//Step 11: Save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC258_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		String InvoiceTotal=TotalAmount;
		String TotalAdjustment ="";		
		String AdditionalDiscount = ActualDiscount;
		double TotalWithoutTax=Double.parseDouble(ProductTotal.get(0))+Double.parseDouble(ProductTotal.get(1))+Double.parseDouble(ProductTotal.get(2))+Double.parseDouble(ProductTotal.get(3));
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				Double.toString(TotalWithoutTax),
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		//Step 12: Recall estimate from cashier screen
		//Step 13: Select estimate and click on recall estimation
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),"ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 14: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PackageDiscountTestData.TC258_TransactionType);

		//Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:cashierScreen No of Product lines,Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen  = TransactionDataLineInCashierPge.get("Subtotal");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierPge, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String ActualDiscountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierScreen), ExpectedTotalDiscount,1.0,"Discount value mismatch: Expected is "+ExpectedTotalDiscount+" but got "+ActualDiscountInCashierScreen+" in cashier screen");

		//Step 14: Click on the Amount
		//Step 15: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 16: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC258_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = Double.toString(TotalWithoutTax);
		String TotalDiscount=ActualDiscountInCashierScreen;
		String TotalQtyPcs=LinesCount;
		String NetValue = PaymentAmount;
		String TaxableValue = SubTotalInCashierScreen;
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
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		appUtils.SalesReturnFlow(PackageDiscountTestData.TC258_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC258_ReturnProducts);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.CounterFlow(PackageDiscountTestData.TC258_FromCounter,PackageDiscountTestData.TC258_MetalType,RequiredKeralSku, UtilityTestData.Terminal);
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.CounterFlow(PackageDiscountTestData.TC258_FromCounterUncut,PackageDiscountTestData.TC258_MetalType,SkuListUncut, UtilityTestData.Terminal);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Invoice with 9 sku multiple package
	// Automation ID : TC255
	// Author: Chandana Babu
	// </summary>
	public void TC255_PackageDiscount() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search a customer with Phone number
		//Step 4: Click on Add to Estimation
		appUtils.SearchByCustomerID(PackageDiscountTestData.TC255_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		//appUtils.HamBurgerButtonClick("iconShop");

		//Step 5: Scan 9 gold SKU( eligible for package discount)
		//Expected: Check calculation
		//Step 6: Click on Add to cart button
		//Step 7: Select any one of the product in cart
		List <String> RequiredKeralSkuInFirstRange = appUtils.FetchPieceRateItem(UtilityTestData.KeralaSkusWithRange.get("Range:2.5-7.9"), PackageDiscountTestData.TC255_SKUCountFromFirstRange);
		List <String> RequiredKeralSkuInSecondRange = appUtils.FetchPieceRateItem(UtilityTestData.KeralaSkusWithRange.get("Range:7.91-9.9"), PackageDiscountTestData.TC255_SKUCountFromSecondRange);
		List<String> RequiredKeralSku = new ArrayList<>();
		RequiredKeralSku.addAll(RequiredKeralSkuInFirstRange);
		RequiredKeralSku.addAll(RequiredKeralSkuInSecondRange);

		System.out.println("RequiredKeralSku Taken : "+RequiredKeralSku);
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> CvalueRG = new ArrayList<>();
		List<String> WastageQty = new ArrayList<>();
		List<String> WastageAmt = new ArrayList<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> ProductTotal = new ArrayList<>();

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < RequiredKeralSku.size(); i++) {
			int SkuNumber            = i + 1;
			String Sku               = RequiredKeralSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotal.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));						
			CvalueRG.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG"));
			WastageQty.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty"));
			WastageAmt.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Step 8: In billing, Go to discount
		//8.1.Select Normal discount
		//8.2.Select Package making discount
		//8.3.Select Package code
		//8.4.package discount percentage
		//Note: check calculation, Again continue step 8-14 for other discount range
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		double TotalExpectedDiscount = 0.0;
		double SubTotalWithoutDiscount = 0.0;

		for (int i = 0; i < PackageDiscountTestData.TC255_PackageDiscountPercent.size(); i++) {
			Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
			Thread.sleep(1000);
			base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC255_DiscountCategory);
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				Thread.sleep(3000);
				String Value = option.getAttribute("value"); //PKG_AP_1 or PKG_KL_1
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(i+1))) {
					Select.selectByValue(Value);
					break;
				}
			}
			base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
			base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountTestData.TC255_PackageDiscountPercent.get(i)));
			base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
			Thread.sleep(1000);
		}

		for (int j = 0; j<RequiredKeralSku.size();j++) {
			if (j<PackageDiscountTestData.TC255_SKUCountFromFirstRange) {
				double Discount = Double.parseDouble(WastageAmt.get(j))-(Double.parseDouble(CvalueRG.get(j))*(PackageDiscountTestData.TC255_PackageDiscountPercent.get(0)/100.0));
				double TotalWithoutTax  = Double.parseDouble(ProductTotal.get(j))-Discount;
				ProductTotalList.add(String.valueOf(TotalWithoutTax));
				TotalExpectedDiscount = TotalExpectedDiscount+Discount;	
				SubTotalWithoutDiscount = SubTotalWithoutDiscount +Double.parseDouble(ProductTotal.get(j));
			}
			else {
				double Discount = Double.parseDouble(WastageAmt.get(j))-(Double.parseDouble(CvalueRG.get(j))*(PackageDiscountTestData.TC255_PackageDiscountPercent.get(1)/100.0));
				double TotalWithoutTax  = Double.parseDouble(ProductTotal.get(j))-Discount;
				ProductTotalList.add(String.valueOf(TotalWithoutTax));
				TotalExpectedDiscount = TotalExpectedDiscount+Discount;		
				SubTotalWithoutDiscount = SubTotalWithoutDiscount +Double.parseDouble(ProductTotal.get(j));
			}
		}

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax 		                        = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotal                         = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		String ActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscount), TotalExpectedDiscount,1.0,"Discount value mismatch: Expected is "+TotalExpectedDiscount+" but got "+ActualDiscount+" in billing page");

		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
        Thread.sleep(1000);
		
		//Step 9: Click on Save to estimation button
		//Step 10: Save esimate
		//Expected : Very the invoice details	
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Back", "BDisc"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber  = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName  = pdfUtils.DownloadAndRenameLatestPDF("TC255_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath  = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                  = Tax;
		String ExpectedTaxableValue = SubTotal;
		String InvoiceTotal         = TotalAmount;
		String TotalAdjustment      = "";		
		String AdditionalDiscount   = ActualDiscount;

		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight),
				String.valueOf(SubTotalWithoutDiscount),
				AdditionalDiscount,
				ExpectedTaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		//Step 11: Recall estimate from cashier screen
		//Step 12.Select estimate and click on save to estimate button
		//Expected Verify whether the recalled item is the same as the item in the cart.				 
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

		Thread.sleep(2000);
		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 13: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PackageDiscountTestData.TC257_TransactionType);

		//Step 14: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 16: Click on the Amount
		//Step 17: Select a Payment method(Cash or Card)
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, Discounts, TAX and Total Amount
		Thread.sleep(4000);
		Map<String, String> TransactionDataLineCashierSCreen = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String ActualDiscountInCashierSCreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualDiscountInCashierSCreen), TotalExpectedDiscount,1.0,"Discount value mismatch: Expected is "+TotalExpectedDiscount+" but got "+ActualDiscountInCashierSCreen+" in cashier screen");

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 18: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice   = pdfUtils.DownloadAndRenameLatestPDF("TC255_FinalSaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount    = String.valueOf(SubTotalWithoutDiscount);
		String TotalDiscount  = AdditionalDiscount;
		String TotalQtyPcs    = LinesCount;
		String NetValue       = PaymentAmount;
		String TaxableValue   = SubTotal;
		String PdfInvoiceNo   = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmount,
				RequiredKeralSku,
				ScannedSkuDataMap);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		appUtils.SalesReturnFlow(PackageDiscountTestData.TC255_CustomerNo, PdfInvoiceNo, PackageDiscountTestData.TC255_ReturnProducts);
		appUtils.CounterFlow(PackageDiscountTestData.TC255_FromCounter,PackageDiscountTestData.TC255_MetalType,RequiredKeralSku, UtilityTestData.Terminal);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Invoice with package, advance , gpp redemption.
	// Automation ID : TC261
	// Author: Sangeetha M S
	// </summary>
	public void TC261_PackageDiscount() throws Exception
	{
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double ErpGoldRate22k =appUtils.FetchCurrentBoardRate("22k");
		System.out.println("BoardRate18k       "+ErpGoldRate22k);

		//Prerequisite Existing scheme	
		Thread.sleep(1500);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				PackageDiscountTestData.TC261_RequiredSettlement,
				PackageDiscountTestData.TC261_AccountCount,
				PackageDiscountTestData.TC261_SchemeCode,
				PackageDiscountTestData.TC261_Matured,
				PackageDiscountTestData.TC261_Adjusted);
		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerAccount="";
		String GPPAccountNumber="";
		String GPPAmount="";
		String SkuPurityReturn="";
		String TotalRgWeightReturn="";
		String SkuGoldRateReturn="";
		boolean ValidAccountFound = false;
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> AdvanceRates = new ArrayList<>();
		List<Double> AdvanceQty = new ArrayList<>();
		List<String> AdvanceTotal = new ArrayList<>();
		List<String> TransactionNumber = new ArrayList<>();

		double TotalQuantity = 0.0;
		double TotalAdvanceAmount=0.0;
		double Quantity = 0.0;
		Thread.sleep(1000);
		for (int idx = 0;idx<MatchedAccounts.size(); idx++)
		{
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerAccount =AccountMap.get("CustomerAccountNo");
			System.out.println(CustomerAccount);
			GPPAccountNumber =AccountMap.get("GppAccountNo");
			GPPAmount =AccountMap.get("GppAmount");

//		CustomerAccount ="1115121843";
//		GPPAccountNumber ="42GG000004935" ;
//		GPPAmount ="200000.00";

		//Click on Transaction button
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		//Select customer
		//Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		appUtils.SearchByCustomerID(CustomerAccount, UtilityTestData.MenuBarOptions[5]);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//In billing, Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

		//Click on GPP Advance Adjustment
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

		//Enter scheme id (GG) and click "Add to cart" button
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
		Thread.sleep(1500);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(1000);

		// Check if "No data" is displayed
		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) 
		{
			System.out.println("No data for account: " + GPPAccountNumber + ". Retrying with next...");
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			appUtils.HamBurgerButtonClick("iconShop");
			appUtils.VoidTransaction();
			continue; 
		}
		Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(GPPAccountNumber);
		SkuPurityReturn = (String) GPPMaturityValues.get("Purity");
		SkuGoldRateReturn = (String) GPPMaturityValues.get("AvgRate");
		SkuGoldRateReturn = SkuGoldRateReturn.replace(",", "");
		System.out.println("Valid GPP account found: " + GPPAccountNumber);
		ValidAccountFound = true;
		break;
		}
		if (!ValidAccountFound) 
		{
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();
		
		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.VoidTransaction();

		//Prerequisite: Customer must be having an advance(Normal)
		Thread.sleep(2000);
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				CustomerAccount,
				PackageDiscountTestData.TC261_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//Metal Rate change
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(Double.toString(ErpGoldRate22k), "22k");
		TimeUnit.MINUTES.sleep(2); //Wait needed for ERP Gold Rate to Change
		double ErpGoldRateFor22k =appUtils.FetchCurrentBoardRate("22k");
		System.out.println("BoardRate22k  -     "+ErpGoldRate22k);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4:Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		appUtils.SearchByCustomerID(CustomerAccount, UtilityTestData.MenuBarOptions[5]);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Step 5. Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6. click on normal advance settlement
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(4000);

		//Step 7. Select advance and click on cart button		
		//Step 8. Enter OTP(Not available)
		Thread.sleep(3000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.WarehouseId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(3000);
		base.buttonClick(AdvanceRepaymentObj.Txt_BalanceAmount("h4 ellipsis cell",PackageDiscountTestData.TC261_AdvanceAmount));
		String BalanceAmount    = base.GetText(AdvanceRepaymentObj.Txt_BalanceAmount("h4 ellipsis cell", PackageDiscountTestData.TC261_AdvanceAmount));
		String AdvanceRate      = base.GetText(AdvanceRepaymentObj.Txt_MetalRate(PackageDiscountTestData.TC261_AdvanceAmount, "Metal Rate"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);
		double AdvanceQuantity= Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
		ProductNames.add(PackageDiscountTestData.TC261_AdvanceName);
		ProductQtyList.add(PackageDiscountTestData.TC261_SchemaAdvanceQty);
		ProductTotalList.add(AdjustmentAmount);
		
		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Normal advance amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		//Step 9. Click on customer adjustment button
		//Step 10. click on GPP advance settlement
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

		//Step 11. Enter scheme id(GG)
		//Step 12. Click on redeem button
		//Expected : Verify whether the entered GB scheme gets added 
		//Step 13. Enter OTP
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
		Thread.sleep(1500);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(1000);

		//Step 14. Scan SKU
		//Expected : Check calculation
		//Step Note: Check average rate		
		ProductNames.add(PackageDiscountTestData.TC261_SchemeName);
		ProductQtyList.add(PackageDiscountTestData.TC261_SchemaAdvanceQty);
		ProductTotalList.add(GPPAmount);
		Thread.sleep(5000);
		List<String> ActualSchemeAmounts =base.GetElementTexts( NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));//.replaceAll("[^\\d.]", "").trim();
		String SchemeAmount=ActualSchemeAmounts.get(1).replaceAll("[^\\d.]", "").trim();
		double SchemeAmountNow=Double.parseDouble(SchemeAmount);
		double SchemeGoldRate=Double.parseDouble(SkuGoldRateReturn);
		TotalRgWeightReturn=String.valueOf(SchemeAmountNow/SchemeGoldRate);
		AdvanceRates.add(SkuGoldRateReturn);
		AdvanceTotal.add(SchemeAmount);
		AdvanceQty.add(Double.parseDouble(TotalRgWeightReturn));
		TotalQuantity = TotalQuantity+Double.parseDouble(TotalRgWeightReturn);
		TotalAdvanceAmount = TotalAdvanceAmount +SchemeAmountNow;

		//Normal advance
		AdvanceRates.add(AdvanceRate);
		AdvanceTotal.add(BalanceAmount);
		Quantity= Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
		AdvanceQty.add(Quantity);
		TotalQuantity = TotalQuantity+Quantity;
		TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);

		int SkuCounter = 1;
		Thread.sleep(1000);
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List <String> RequiredKeralSku = appUtils.FetchPieceRateItem(UtilityTestData.KeralSkus, 1);

		appUtils.HamBurgerButtonClick("iconShop");
		String CvalueRG = "";
		String ItemNameInItemDetailsPge ="";
		String GrossWeight = "";
		String TotalWithoutTax = "";
		String WastageQty = "";
		String WastageAmt = "";
		String RGRate="";
		for (int i = 0; i < RequiredKeralSku.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku = RequiredKeralSku.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			CvalueRG = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_CvalueRG");
			ItemNameInItemDetailsPge = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			GrossWeight = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			TotalWithoutTax = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			WastageQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageQty");
			WastageAmt =  ScannedSkuDataMap.get("SKU_" + SkuNumber + "_WastageAmount");
			double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG"));
			String ProductName = ItemNameInItemDetailsPge.contains("-") ? ItemNameInItemDetailsPge.substring(ItemNameInItemDetailsPge.indexOf("-") + 1).trim(): ItemNameInItemDetailsPge;
			ProductNames.add(ProductName);
			ProductQtyList.add(GrossWeight);
			ProductTotalList.add(TotalWithoutTax);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			System.out.println("i"+i);
			if (TotalQuantity==0)
			{
				mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
			}
			double Total = 0.0;                
			double ExpectedRgRate = 0.0;       
			double SaleQuantity = RGQuantity;  
			int FullyConsumedFromHead = 0;     
			List<String> AdvanceType = new ArrayList<>( Arrays.asList(  "GG","Normal"));
			if (TotalQuantity != 0)
			{
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++)
				{
					System.out.println("j"+j);
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));

					if (Qty <= 0) continue;

					String AdvType = AdvanceType.get(j);  

					double EffectiveRate;

					if (AdvType.equalsIgnoreCase("GG"))
					{
						EffectiveRate = Rate;  
					}
					else if (AdvType.equalsIgnoreCase("Normal"))
					{
						EffectiveRate = Math.min(Rate, ErpGoldRate22k);
					}
					else
					{
						EffectiveRate = Math.min(Rate, ErpGoldRate22k);
					}
					double UseQty = Math.min(Qty, SaleQuantity);//Variable Advanceqty
					Total += UseQty * EffectiveRate;
					SaleQuantity -= UseQty;
					TotalQuantity -= UseQty;
					double RemainingInRow = Qty - UseQty;
					AdvanceQty.set(j, RemainingInRow);
					if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead)
					{
						FullyConsumedFromHead++;
					}
				}
				// If advance qty finishes → use current rate
				if (SaleQuantity > 0)
				{
					Total += SaleQuantity *ErpGoldRateFor22k;// ErpGoldRate22k;//;
					SaleQuantity = 0;
				}
				ExpectedRgRate = Total / RGQuantity;
				// Remove consumed rows
				for (int k = 0; k < FullyConsumedFromHead; k++)
				{
					AdvanceQty.removeFirst();
					AdvanceTotal.removeFirst();
					AdvanceRates.removeFirst();
					AdvanceType.removeFirst();  
				}
				asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0,"Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");
				mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuNumber, PackageDiscountTestData.TC261_Purity ,ErpGoldRateFor24K);		
			}
		}
		
		//Step 15. Click on GPP discount button
		//Expected : Verify the GPP Bonus amount
		//Note Billing Screen *No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax)
		//           *Displayed Subtotal,*Discounts, *TAX, *Total Amount
		//Step 16. Click on discount button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(10000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		String TotalBonus = mathUtils.ToGetGppBonus(ScannedSkuDataMap,RequiredKeralSku.size(),PackageDiscountTestData.TC261_BenefitPercentage,Double.parseDouble(TotalRgWeightReturn),Double.parseDouble(ErpGoldRateFor24K));
		System.out.println(TotalBonus);
		
		asrt.assertEquals(ActualBonus, Double.parseDouble(TotalBonus),1,"Bonus mismatch: "+Double.parseDouble(TotalBonus)+" but found "+ActualBonus+" in billing page");
		
		ProductNames.add(PackageDiscountTestData.TC261_BonusName);
		ProductQtyList.add(PackageDiscountTestData.TC261_BonusQty);
		ProductTotalList.add(Bonus);

		Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);
		String SubTotal    			= TransactionPageItems.get("Subtotal");
		String Discount 			= TransactionPageItems.get("Discount");
		String TotalGrossWeight1	= TransactionPageItems.get("GrossWeight");
		String TotalNetWeight 		= TransactionPageItems.get("NetWeight");
		String Tax 					= TransactionPageItems.get("Tax");
		String TotalAmount 			= TransactionPageItems.get("TotalAmount");
		String NetTotal 			= TransactionPageItems.get("NetTotal");
		String LinesCount 			= TransactionPageItems.get("LinesCount");

		for (Map.Entry<String, String> entry : TransactionPageItems.entrySet()) 
		{
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By CartChangedAlert = NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("GPP discount already applied");
		By OkBtn = LoginPageObj.Btn_SingnIn("Button1Close");
		if (base.isElementPresent(driver, CartChangedAlert))
		{		
			try 
			{
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			} 
			catch (TimeoutException e) 
			{
			} 
		}
		Thread.sleep(8000);
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ActualLinesCount = Integer.toString(AllProductRows.size());
		
		asrt.assertEquals(ActualLinesCount, LinesCount,"Lines count mismatch: Expected is "+ActualLinesCount+" but found "+LinesCount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) 
		{
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==2)
			{
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +"' should contain actual '" + ActualItemNameInBillingPge + "'");
			} 
			else 
			{
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +"' should contain actual '" + ActualItemNameInBillingPge + "'");
			}

			List<WebElement> Quantitys = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantitys.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTaxes = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInBillingPge = TotalWithoutTaxes.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTaxes.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		System.out.println("ActualTax  "+ActualTax);
		double ExpectedTax =  Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		
		asrt.assertEquals( ActualTax,ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 17. Click on normal discount button
		//Step 18. Select package making discount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), PackageDiscountTestData.TC257_DiscountCategory);

		//Step 19. select discount range
		//Step 20. Enter discount percentage
		double WastageQtyValue = 0.0;
		WastageQtyValue = Double.parseDouble(WastageQty);

		// Select option based on WastageQty range and set making percentage
		int SlabNumber = 0;
		double PackageDiscountPercent = 0;

		if (WastageQtyValue >= 2.5 && WastageQtyValue <= 7.9) {
			SlabNumber = 1;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabA;
			System.out.println("Slab A selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 7.9 && WastageQtyValue <= 9.9) {
			SlabNumber = 2;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabB;
			System.out.println("Slab B selected for wastage " + WastageQtyValue);
		} else if (WastageQtyValue > 9.9 && WastageQtyValue <= 11.9) {
			SlabNumber = 3;
			PackageDiscountPercent = PackageDiscountTestData.PackageDiscountPercent_SlabC;
			System.out.println("Slab C selected for wastage " + WastageQtyValue);
		} else {
			System.out.println("Wastage qty out of range: " + WastageQtyValue);
		}

		if (SlabNumber != 0) {
			Select Select = new Select(driver.findElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PkgInput")));
			String FinalValue = null;
			for (WebElement option : Select.getOptions()) {
				String Value = option.getAttribute("value"); //PKG_AP_1 or PKG_KL_1
				if (Value != null && Value.contains("PKG") && Value.contains(String.valueOf(SlabNumber))) {

					Select.selectByValue(Value);

					System.out.println("Selected package: " + Value);

					// Set making percentage
					base.clearData(CustomerCreationObj.Ele_Name("pkgdiscper"));
					base.setData(CustomerCreationObj.Ele_Name("pkgdiscper"), String.valueOf(PackageDiscountPercent));

					break;
				} else {
					System.out.println("No matching package found for slab " + SlabNumber);
				}
			}
		}

		//Step 21. Click on apply button
		//Step Note: check calculation
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsPackage", "Apply discount"));
		double ExpectedDiscount = Double.parseDouble(WastageAmt) - (Double.parseDouble(CvalueRG)*PackageDiscountPercent)/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;
		String PackageActualDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField","h4")).replaceAll("[^\\d.]", "").trim();	
		String SkuTotal = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","4"));
		double ActualSkuTotal = Double.parseDouble(SkuTotal.replaceAll("[^\\d.]", "").trim());
		
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(RequiredKeralSku, ScannedSkuDataMap);		
		SubTotal = TransactionDataLine.get("Subtotal");
		TotalNetWeight   = TransactionDataLine.get("NetWeight");
		Tax 		        = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		Discount 			= TransactionPageItems.get("Discount");
		TotalAmount      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		NetTotal         = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		LinesCount       = TransactionDataLine.get("LinesCount");

		for (Map.Entry<String, String> entry : TransactionDataLine.entrySet()) 
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		String GrossAmount1 = TotalWithoutTax.replaceAll("[^\\d.]", "").trim();
		String GrossAmount2 = TransactionDataLine.get("Item4TotalWithoutTax").replaceAll("[^\\d.]", "").trim();
		double grossAmountValue = Double.parseDouble(GrossAmount1) + Double.parseDouble(GrossAmount2);
		
		//Step 22. Click on Save to estimation button
		//Expected : Verify the Invoice details
		//Step 23. save estimate
		String AmountDue =base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();;
		double AmountDueForSaleReturnInFromBillingPge = Double.parseDouble(AmountDue);
		String PaymentAmount="";
		String AmountDueFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueBillingPge = Double.parseDouble(AmountDueFromBillingPge);
		String ExpectedAmountnBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		double TaxProducts = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("\u20B9", "").replace(",", "").replace("-", ""));

		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(15000);
		double Adjustment=ActualBonus+Double.parseDouble(GPPAmount)+Double.parseDouble(AdjustmentAmount);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC261_ProformaInvoice");
		Thread.sleep(1000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String ExpectedTaxableValue =SubTotal;
		double TotalGrossWeight=Double.parseDouble(GrossWeight);
		String TotalAdjustment = Double.toString(Adjustment);	
		double DiscountValue = Double.parseDouble(PackageActualDiscount);
		SubTotal=SubTotal.replaceAll("[^\\d.]", "");
		String AdditionalDiscount = String.format("%.2f", DiscountValue);
		String ExpectedTaxableValuePdf =Double.toString( ActualSkuTotal);
		String GstPdf=Tax;
		String InvoiceTotal=Double.toString(ActualSkuTotal+Double.parseDouble(GstPdf));
		pdfUtils.ProFormaInvoicePDFVerifyDiscount(
				ProFormaInvoicePath,
				LinesCount,
				TotalGrossWeight,
				Double.parseDouble(TotalNetWeight),
				TotalWithoutTax,
				AdditionalDiscount,
				ExpectedTaxableValuePdf,
				GstPdf,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal);

		//Step 24.Recall estimate from cashier screen
		//Step 25. Select estimate and click on recall estimation
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

		//Step 26.Select the Transaction Type as Sales 
		//Step 27.Click on Process Estimation 
		//Step 28.Choose a sales representative(Not available after data refresh)
		//Step 29.Click on the Amount
		//Step 30.Select a Payment method(Cash or Card)
		//Step 31.Post the Invoice
		//Expected :  Check final invoice details
		String invoiceNumber="";		
		if(AmountDueForSaleReturnInFromBillingPge<= 0.00)
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PackageDiscountTestData.TC261_TransactionypeAdvance);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(5000);
			appUtils.HandleSpecifyRateModalInRecall();
			Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Convert to Advance"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SalesPerson("3"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Ele_SalesPerson("3"),1);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), PackageDiscountTestData.TC261_DueYear);
			base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Remarks("6"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"),PackageDiscountTestData.TC261_Remark);
			base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);   
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			if(base.isExists(LoginPageObj.Btn_SignInButton("Close")))
			{
				LoginPageObj.Btn_SignInButton("Close");
			}

			//For sale
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
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PackageDiscountTestData.TC261_Transactionype);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(5000);
			appUtils.HandleSpecifyRateModalInRecall();
			Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Thread.sleep(2000);
			String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC261_SaleInvoice");
			Thread.sleep(1000);
			String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
			String TotalDiscount=AdditionalDiscount;
			String GrossAmount = String.format("%.2f", grossAmountValue);
			String TotalGrossAmount=GrossAmount;
			String TotalQtyPcs= "2";
			double NetWeight = Double.parseDouble(TotalNetWeight);
			NetWeight = NetWeight + 1;
			String TotalGrossWeights= String.format("%.3f", NetWeight);	
			double ProductTotalSale = Double.parseDouble(GrossAmount2)+ActualSkuTotal;
			String ExpectedTaxableValueSalePdf=String.format("%.2f", ProductTotalSale);
			double TotalValueCalc=Double.parseDouble(InvoiceTotal) -Double.parseDouble(GrossAmount2);
			String TotalValue = String.format("%.2f", TotalValueCalc);

			String  PdfDetails =pdfUtils.SaleInvoicePdfGppBonusAdjustmentValidation(
					InvoicePdfPath,
					TotalGrossAmount,
					TotalDiscount,
					TotalQtyPcs,
					TotalNetWeight,
					TotalGrossWeights,
					ExpectedTaxableValueSalePdf,
					TotalValue,
					TotalValue,
					RequiredKeralSku,
					ScannedSkuDataMap);
			Thread.sleep(1000);
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			appUtils.SalesReturnFlow(CustomerAccount, PdfDetails, PackageDiscountTestData.TC261_ReturnProducts);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Error"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			Thread.sleep(2000);
			appUtils.CounterFlow(PackageDiscountTestData.TC261_FromCounterGold, PackageDiscountTestData.TC261_MetalType, RequiredKeralSku, UtilityTestData.Terminal);
		}
		else
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PackageDiscountTestData.TC261_Transactionype);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(5000);

			PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Thread.sleep(2000);
			String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC261_SaleInvoice");
			Thread.sleep(1000);
			String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
			String TotalDiscount=AdditionalDiscount;		
			String GrossAmount = TotalWithoutTax;
			String TotalQtyPcs= "1";
			String NetWeight = GrossWeight;
			String TotalGrossWeights=  NetWeight;	
			double ProductTotalSale = Double.parseDouble(GrossAmount2)+ActualSkuTotal;
			String ExpectedTaxableValueSalePdf=ExpectedTaxableValuePdf;//String.format("%.2f", ProductTotalSale);
			String TotalValue = InvoiceTotal;		
			String Advance=SchemeAmount;
			String Adjustments=String.format("%.2f", Adjustment);
			double Total=Double.parseDouble(TotalValue)+Double.parseDouble(Adjustments);
			String NetTotals=PaymentAmount;
			Bonus = Bonus.replaceAll("[₹,\\s-]", "").trim();
			PaymentAmount = PaymentAmount.replaceAll("[,₹\\s]", "");
			
			Map<String, String> PdfDetails =
			        pdfUtils.SaleInvoiceValidationAdvanceSchemes(
			                InvoicePdfPath,              
			                GrossAmount,                 
			                TotalDiscount,              
			                TotalQtyPcs,                 
			                NetWeight,                   
			                TotalGrossWeights,           
			                ExpectedTaxableValueSalePdf, 
			                TotalValue,                  
			                PaymentAmount,              
			                AdvanceAmount,               
			                Advance,                     
			                Bonus,                       
			                Adjustments,                
			                NetTotals                    
			        );
			String InvoiceNumber = PdfDetails.get("InvoiceNumber");
			System.out.println("Invoice Number from PDF: " + InvoiceNumber);
			Thread.sleep(1000);
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			appUtils.SalesReturnFlow(CustomerAccount, InvoiceNumber, PackageDiscountTestData.TC261_ReturnProducts);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Error"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			Thread.sleep(2000);
			appUtils.CounterFlow(PackageDiscountTestData.TC261_FromCounterGold, PackageDiscountTestData.TC261_MetalType, RequiredKeralSku, UtilityTestData.Terminal);			
		}
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}
}
