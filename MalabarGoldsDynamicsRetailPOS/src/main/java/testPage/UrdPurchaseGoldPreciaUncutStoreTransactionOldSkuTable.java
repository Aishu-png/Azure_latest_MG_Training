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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
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
import objectRepository.InvoiceWithAdvanceSchemeAndOG_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
import testData.GppOpeningAndCollectionMultiplePlans_TestData;
import testData.PANCardLimitValidation_TestData;
import testData.UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData;
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

public class UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	GPPSettlementDiscountPlan_TestData GPPSettlementDiscountPlanTestData =new GPPSettlementDiscountPlan_TestData();
	UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData=new UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	InvoiceWithAdvanceSchemeAndOG_Obj InvoiceWithAdvanceSchemeAndOGObj = new InvoiceWithAdvanceSchemeAndOG_Obj();
	AdvanceRepayment_Obj AdvanceRepaymentObj = new AdvanceRepayment_Obj();

	public UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable(WebDriver driver) {
		super(driver);
		base      = new BasePge(driver);
		appUtils  = new AppUtilities(base); 
		erpUtils  = new ErpUtilities(base);
		pdfUtils  = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	// <summary>
	// Test Case Title : URD purchase of platinum
	// Automation ID : TC205
	// Author: Christy Reji
	// </summary>
	public void TC205_UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer and Click on add to estimation button
		Thread.sleep(2000);
		appUtils.SearchByCustomerID(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 4: Select URD
		Thread.sleep(5000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.URD);
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Step 5:Click on URD platinum
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.URD_PlatiniumProduct));	

		//Step 6:Select Configuration(purity)
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_PurityConfig);

		//Error-handling , if present
		try {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}catch (Exception e) {}

		//step 7:Click on Add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8. Enter receipt number and fetch the details
		//Expected: Verify  whether the entered receipt number details are fetched
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"),UtilityTestData.URD_ReceiptNumberPlatinum);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Fetch"));
		Thread.sleep(4000);

		//Receipt valid for 15 days
		asrt.assertFalse(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "No data found popup displayed: Values not fetched or value expired");	

		//Step 9. Select QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Step 10. Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Step 11. click on calculate button
		//Expected: Check Purchase rate, * Check calculation 
		Thread.sleep(3000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(5000);

		String ExpectedURDGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String URDItemName                            = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String URDTotalAmountInItemDetailsPge         = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String URDActualAmnt                          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();;	
		String RateItemdetailPge                      = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		String NetWeightInItemDetailsPge              = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
		String MakingAmtItemDetailPge                 = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetmakkingamt"));
		String ExpectedURDItemNameIntItemDetailsPge   = URDItemName.substring(URDItemName.indexOf("-") + 2);
		//Calculation
		double ExpAmount                              = Double.parseDouble(NetWeightInItemDetailsPge) * Double.parseDouble(RateItemdetailPge);
		double ExpTotalAmount                         = Double.parseDouble(URDActualAmnt) + Double.parseDouble(MakingAmtItemDetailPge);

		Thread.sleep(2000);
		asrt.assertEquals(Double.parseDouble(RateItemdetailPge),Double.parseDouble(UtilityTestData.URD_ReceiptNumberPlatinumRate),1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberPlatinumRate+" but got "+RateItemdetailPge+" in URD item details page");	
		asrt.assertEquals(Double.parseDouble(URDActualAmnt), ExpAmount,1.0,"Amount mismatch: "+URDActualAmnt+" but got "+ExpAmount+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(URDTotalAmountInItemDetailsPge), ExpTotalAmount,1.0,"Amount mismatch: "+URDTotalAmountInItemDetailsPge+" but got "+ExpTotalAmount+" in URD item details page");

		//Step 12: Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//URD Billing page validation
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCountURD       = AllProductRows.size();
		int ActualLinesCountURD         = Integer.parseInt(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4")));
		String ActualItemNameURD        = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualGrossWeightURD     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5")).replace("-", ""); 
		Thread.sleep(3000);
		String ActualTotalWithoutTaxURD = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualSubTotalURD        = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTaxURD             = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTotalAmountURD     = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();

		asrt.assertEquals(ExpectedLinesCountURD, ActualLinesCountURD,"Lines count mismatch: Expected is "+ExpectedLinesCountURD+" but got "+ActualLinesCountURD+" in Billing page");
		asrt.assertEquals(ActualItemNameURD, ExpectedURDItemNameIntItemDetailsPge,"Item name mismatch: "+ExpectedURDItemNameIntItemDetailsPge+" but found "+ActualItemNameURD+" in billing page");
		asrt.assertEquals( Double.parseDouble(ActualGrossWeightURD), Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge), 0.01, "Mismatch in GrossWeightt: Expected " + ExpectedURDGrossWeightInItemDetailsPge + " but got " + ActualGrossWeightURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalWithoutTaxURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total without tax: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalWithoutTaxURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualSubTotalURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in value Subtotal: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualSubTotalURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalAmountURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total amount: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalAmountURD + " from billing page");

		//Step 13; Scan SKU
		//Check calculation, Note:Billling Screen *No of Product lines*Displayed Item Name*Displayed Quantity
		// *Displayed TOTAL (without Tax),*Displayed Subtotal, *TAX *Total Amount
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(ActualItemNameURD);
		ProductQtyList.add(ActualGrossWeightURD);
		ProductTotalList.add(ActualTotalAmountURD);
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_TranferType,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_FromCounterGold,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_MetalTypeGold,
				Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge),
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_SKUCount
				);

		appUtils.HamBurgerButtonClick("iconShop");
		List<String> SkuList=SkuGoldSale.PcsRateSKU;
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku    = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Discount                         = TransactionDataLine.get("Discount");
		String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotalBillScrn                 = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");
		List<String> ActualNames                = mathUtils.ValidateTransactionLineCalculationsReturnProd(TransactionDataLine,ScannedSkuDataMap);
		String SKUName                          = ActualNames.toString().replace("[", "").replace("]", "").trim();
		List<String> TotalWithoutTaxElements    = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String TotalwithoutTax                  = TotalWithoutTaxElements.get(1).replaceAll("[^\\d.]", "").trim();

		//Billing page calculation
		double ExpectedSubTotal 				= Math.round(Math.abs( Double.parseDouble(TotalwithoutTax) - Double.parseDouble(ActualTotalWithoutTaxURD) ) * 100.0) / 100.0;
		double ExpectedTotalAmount 				= Math.round((ExpectedSubTotal + Double.parseDouble(Tax)) * 100.0) / 100.0;
		double Grossweight                      = Double.parseDouble(TotalNetWeight) - Double.parseDouble(ActualGrossWeightURD);
		double ExpectedTax                      = Double.parseDouble(ProductTotalList.get(1)) * 0.03;

		Thread.sleep(3000);
		asrt.assertEquals(Double.parseDouble(Tax), ExpectedTax,1,"Tax mismatch: Expected is "+ExpectedTax+" but got "+Tax+" in Billing page");
		asrt.assertEquals(Double.parseDouble(SubTotal), ExpectedSubTotal,1,"SubTotal mismatch: Expected is "+ExpectedSubTotal+" but got "+SubTotal+" in Billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmount,1,"Total amount mismatch: "+ExpectedTotalAmount+" but found "+TotalAmount+" in billing page");

		//Step 14: save to estimate
		//Expected:  14.Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = TotalwithoutTax;
		String TotalAmnt           = Double.toString(ExpectedSubTotal);
		String Gst                 =  Tax;
		String InvoiceTotal        =  NetTotalBillScrn;
		String NetTotal            =  NetTotalBillScrn;
		Thread.sleep(3000);
		String ProformaPdfName     = pdfUtils.DownloadAndRenameLatestPDF("TC205_ProformaInvoice");
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				);

		//Step 15. Recall estimate from cashier
		//Expected : Verify whether the recalled item is the same as the item in the cart. 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 

		// Validation
		appUtils.RecallEstimateItemValidation(
				Arrays.asList(ActualItemNameURD),
				Arrays.asList( ActualGrossWeightURD),
				Arrays.asList(SKUName),
				Arrays.asList(TotalNetWeight),
				ExpectedSubTotal,
				Double.parseDouble(Tax),
				ExpectedTotalAmount,
				Integer.parseInt(LinesCount),
				"URD Platinium"
				);

		//Step 16: Select transaction type as Exchange
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_TransactionTypeExchange);

		//Step 17.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 18.Choose a sales representative[Step not required due new application change]
		//Step 19:  Click on amount 0.00	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//Step 20:Post the exchange invoice
		//Expected :  Check Exchange invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Map<String, String> PurchaseBill = pdfUtils.URDPurchaseBill("Adjustment");
		String GrossWeightFromInvoice    = PurchaseBill.get("GrossWt1");
		String NetWeightFromInvoice      = PurchaseBill.get("NetWt1");
		String TotalAmountFromInvoice    =  PurchaseBill.get("TotalAmount");
		String ExchangeFromInvoice       = PurchaseBill.get("PaymentDetails");
		String QtyFromInvoice            = PurchaseBill.get("QtyPcs");

		asrt.assertEquals(Double.parseDouble(ExchangeFromInvoice), Double.parseDouble(ActualTotalAmountURD), 0.01, "Mismatch in exchange value URD Purchase bill adjustment:"+ExchangeFromInvoice+"but got" +ActualTotalAmountURD+"rom billing page");
		asrt.assertEquals(Double.parseDouble(GrossWeightFromInvoice), Double.parseDouble(ActualGrossWeightURD),"Mismatch in GrossWt value URD Purchase bill adjustment:"+GrossWeightFromInvoice+"but got" +ActualGrossWeightURD+"rom billing page");
		asrt.assertEquals(Double.parseDouble(NetWeightFromInvoice), Double.parseDouble(ActualGrossWeightURD),"Mismatch in NetWt value URD Purchase bill adjustment:"+NetWeightFromInvoice+"but got" +ActualGrossWeightURD+"rom billing page");	
		asrt.assertEquals(Double.parseDouble(TotalAmountFromInvoice), Double.parseDouble(ActualTotalAmountURD),0.01,"Mismatch in TotatAmt value URD Purchase bill adjustment:"+Double.parseDouble(TotalAmountFromInvoice)+"but got" +ActualTotalAmountURD+"rom billing page");
		asrt.assertEquals(Integer.parseInt(QtyFromInvoice),ExpectedLinesCountURD,"Mismatch in Line count URD Purchase bill adjustment: " + QtyFromInvoice +" but got " + ExpectedLinesCountURD + " from billing page");

		//Step 21. Again recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		String ExpectedEstmnNumberSale = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumberSale,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumberSale+" in Recall Estimation page");

		List<WebElement> SKUNumberFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "SKUNUMBER"));
		String ActualSKURaw    = SKUNumberFromRecall.get(0).getText().trim();
		String ExpectedSKURaw  = SkuList.get(0);
		String ActualSKUName   = ActualSKURaw.contains("-")? ActualSKURaw.substring(ActualSKURaw.lastIndexOf("-") + 1).trim(): ActualSKURaw.trim();
		String ExpectedSKUName = ExpectedSKURaw.contains("-")? ExpectedSKURaw.substring(ExpectedSKURaw.lastIndexOf("-") + 1).trim(): ExpectedSKURaw.trim();
		asrt.assertEquals(ActualSKUName, ExpectedSKUName, "SKU Number  mismatch: Expected " + ExpectedSKUName + " but got " + ActualSKUName + "in the Recall Page.");

		//Step 22. Select transaction type as sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_TransactionTypesale);

		//Step 23: Click on process estimation button[sale rep not required]
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 24:Click on the Amount
		// Step 25:Select a Payment method(Cash or Card)
		//Expected : 24.Note:Cashier screen *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		   = TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen		 		   = TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				   = TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightInCashierScreen   		   = TransactionDataLineInCashierScreen.get("NetWeight");
		String TotalGrossWeightInCashierScreen   	   = TransactionDataLineInCashierScreen.get("GrossWeight");
		String TaxInCashierScreen 					   = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			   = TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		   = TransactionDataLineInCashierScreen.get("LinesCount");
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen       = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in CashierScreen");		

		List<WebElement> ItemNameInCashierScreen       = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInCashierScreen           = ItemNameInCashierScreen.get(0).getText();
		String ExpectedItemNameInCashierScreen         = ProductNames.get(1).trim().contains("-") ? ProductNames.get(1).substring(ProductNames.get(1).lastIndexOf("-") + 1).trim() : ProductNames.get(1).trim();
		//asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");			
		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");
		
		List<WebElement> QuantityInCashierScreen       = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
		String ActualQuantityInCashierScreen           = QuantityInCashierScreen.get(0).getText().replace("-", "");
		String ExpectedQuantityInCashierScreen         = ProductQtyList.get(1).replace("-", "");
		double ActualQtyInCashierScreen                = Double.parseDouble(ActualQuantityInCashierScreen);
		double ExpectedQtyInCashierScreen              = Double.parseDouble(ExpectedQuantityInCashierScreen);
		asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in CashierScreen");

		List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		String ActualTotalWithoutTaxInCashierScreen     = TotalWithoutTaxInCashierScreen.get(0).getText().replaceAll("[^\\d.-]", "").trim();
		double SubTotalSumInCashierScreen               = Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
		String ExpectedTotalWithoutTaxInCashierScreen   = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "").trim()));
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in CashierScreen");

		String ExpectedSubTotalInCashierScreen          = String.format("%.2f",SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen            = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in CashierScreen");

		String ActualTaxInCashierScreen                 = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxValueInCashierScreen          = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInCashierScreen               = String.format("%.2f",ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreen+" but found "+ActualTaxInCashierScreen+" in CashierScreen");

		double ExpectedTotalAmountInCashierScreen       = Double.parseDouble(ActualSubTotalInCashierScreen)+Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountInCashierScreen         = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("\u20B9", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		//Step 26 : Post the sale invoice
		//Check the final ivoice details-Sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC205_FinalSaleInvoice");
		Thread.sleep(3000);		
		String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
		Thread.sleep(3000);
		String GrossAmount         = TotalwithoutTax;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = String.valueOf(SkuList.size());
		String Adjustment          = ActualTotalAmountURD;
		String PaymentHDFCCard     = PaymentAmount;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalNetWeightPdf   = TotalNetWeight;
		String TotalGrossWeightPdf = TotalNetWeightPdf;
		double CalcTotalAmount     = Double.parseDouble(TaxableValue)+Double.parseDouble(Tax);
		String NetValue            = String.format("%.2f", CalcTotalAmount);
		NetValue                   = NetValue.replaceAll("^0+(?!$)", "").trim();
		Thread.sleep(3000);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeightPdf,
				TotalGrossWeightPdf,
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
	// Test Case Title : URD purchase of Gold
	// Automation ID : TC204
	// Author: Vishnu Manoj K
	// </summary>
	public void TC204_UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable() throws Exception {
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	 

		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		// Step : Pre-Requisite for taking BoardRate
//		Thread.sleep(3000);
//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
//		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
//		Thread.sleep(5000);
//		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
//		Thread.sleep(5000);
//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
//		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"24k");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
//		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
//		//QC person
//		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		//	Smith person
//		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),"1");
//		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),"1");
//		Thread.sleep(10000);
//		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
//		Thread.sleep(3000);
//		double ErpGoldRateFor24K = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
//		Thread.sleep(3000);
//		appUtils.HamBurgerButtonClick("iconShop");
		
		double ErpGoldRate22k = appUtils.FetchCurrentBoardRate(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_Purity);
		double OGRate24k = (ErpGoldRate22k*24)/22 ;
		double ErpGoldRateFor24K = OGRate24k;
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer and Click on add to estimation button
		Thread.sleep(2000);
		appUtils.SearchByCustomerID(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 4: Select URD
		Thread.sleep(8000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_URDG);
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Step 5:Click on URD Gold
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_URDG));	

		//Step 6:Select Configuration(purity)
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_PurityConfig);

		//Error-handling , if present
		try {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}catch (Exception e) {}

		//step 7:Click on Add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8. Enter receipt number and fetch the details
		//Expected: Verify  whether the entered receipt number details are fetched
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"),UtilityTestData.URD_ReceiptNumberGold);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Fetch"));
		Thread.sleep(4000);

		//Receipt valid for 15 days
		asrt.assertFalse(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "Entered receipt number details are not fetched or Receipt expired (15 days)");	

		//Step 9. Select QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Step 10. Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Step 11. click on calculate button
		//Expected: Check Purchase rate, * Check calculation 
		Thread.sleep(3000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(5000);

		Thread.sleep(3000);
		String ExpectedURDGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String URDItemName                            = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String URDTotalAmountInItemDetailsPge         = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String URDActualAmnt                          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();;	
		String RateItemDetailPge                      = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		String NetWeightInItemDetailsPge              = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
		String MakingAmtItemDetailPge                 = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetmakkingamt"));
		String ExpectedURDItemNameIntItemDetailsPge   = URDItemName.substring(URDItemName.indexOf("-") + 2);
		//Calculation
		double ExpAmount                              = Double.parseDouble(NetWeightInItemDetailsPge) * Double.parseDouble(RateItemDetailPge);
		double ExpTotalAmount                         = Double.parseDouble(URDActualAmnt) + Double.parseDouble(MakingAmtItemDetailPge);

		asrt.assertEquals(Double.parseDouble(RateItemDetailPge),Double.parseDouble(UtilityTestData.URD_ReceiptNumberGoldRate),1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberGoldRate+" but got "+RateItemDetailPge+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(URDActualAmnt), ExpAmount,1.0,"Amount mismatch: "+ExpAmount+" but got "+URDActualAmnt+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(URDTotalAmountInItemDetailsPge), ExpTotalAmount,1.0,"Amount mismatch: "+ExpTotalAmount+" but got "+URDTotalAmountInItemDetailsPge+" in URD item details page");
		Map<String, String> ScannedSkuDataMapUrd = new LinkedHashMap<>();	
		appUtils.ItemDetailsTableValues(1,ScannedSkuDataMapUrd);

		//Step 12: Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//URD Billing page validation
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCountURD       = AllProductRows.size();
		int ActualLinesCountURD         = Integer.parseInt(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4")));
		String ActualItemNameURD        = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualGrossWeightURD     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5")).replace("-", ""); 
		Thread.sleep(3000);
		String ActualTotalWithoutTaxURD = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualSubTotalURD        = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTaxURD             = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTotalAmountURD     = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();

		asrt.assertEquals(ExpectedLinesCountURD, ActualLinesCountURD,"Lines count mismatch: Expected is "+ExpectedLinesCountURD+" but got "+ActualLinesCountURD+" in Billing page");
		asrt.assertEquals(ActualItemNameURD, ExpectedURDItemNameIntItemDetailsPge,"Item name mismatch: "+ExpectedURDItemNameIntItemDetailsPge+" but found "+ActualItemNameURD+" in billing page");
		asrt.assertEquals( Double.parseDouble(ActualGrossWeightURD), Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge), 0.01, "Mismatch in GrossWeightt: Expected " + ExpectedURDGrossWeightInItemDetailsPge + " but got " + ActualGrossWeightURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalWithoutTaxURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total without tax: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalWithoutTaxURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualSubTotalURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in value Subtotal: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualSubTotalURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalAmountURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total amount: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalAmountURD + " from billing page");

		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(ActualItemNameURD);
		ProductQtyList.add(ActualGrossWeightURD);
		ProductTotalList.add(ActualTotalAmountURD);
		//Step 13; Scan SKU
		//Check calculation, Note:Billing Screen *No of Product lines*Displayed Item Name*Displayed Quantity
		// *Displayed TOTAL (without Tax),*Displayed Subtotal, *TAX *Total Amount
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_TranferType,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_FromCounterGold,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_MetalTypeGold,
				Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge),
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_SKUCount
				);
		erpUtils.ResetRemainingReturnWeight();
		 
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> SkuList=SkuGoldSale.PcsRateSKU;
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1; //since URD is 1st sku
			String Sku    = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber,ActualGrossWeightURD,"22K",RateItemDetailPge,String.valueOf(ErpGoldRateFor24K));
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Discount                         = TransactionDataLine.get("Discount");
		String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotalBillScrn                 = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");

		Map<String, String> AdjustedScannedSkuMapUrd = erpUtils.MergeScannedSkuDataMaps(ScannedSkuDataMapUrd, ScannedSkuDataMap);
		System.out.println("AdjustedScannedSkuMapUrd: " + AdjustedScannedSkuMapUrd.toString());
		List<String> ActualNames=erpUtils.ErpValidateTransactionLineCalculationSubtract(TransactionDataLine, AdjustedScannedSkuMapUrd,"1","1",true);
		String SKUName                          = ActualNames.toString().replace("[", "").replace("]", "").trim();
		List<String> TotalWithoutTaxElements    = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String TotalwithoutTax                  = TotalWithoutTaxElements.get(1).replaceAll("[^\\d.]", "").trim();
		double ExpectedSubTotal                 = Double.parseDouble(TotalwithoutTax) - Double.parseDouble(ActualTotalWithoutTaxURD);;
		double ExpectedTotalAmount              = ExpectedSubTotal + Double.parseDouble(Tax);
		double Grossweight                      = Double.parseDouble(TotalNetWeight) - Double.parseDouble(ActualGrossWeightURD);

		//Step 14: save to estimate
		//Expected:  14.Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = TotalwithoutTax;
		String TotalAmnt           = Double.toString(ExpectedSubTotal);
		String Gst                 =  Tax;
		String InvoiceTotal        =  NetTotalBillScrn;
		String NetTotal            =  NetTotalBillScrn;
		Thread.sleep(3000);
		String ProformaPdfName     = pdfUtils.DownloadAndRenameLatestPDF("TC204_ProformaInvoice");
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				);

		//Step 15: Recall estimate from cashier
		//Expected : Verify whether the recalled item is the same as the item in the cart. 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 	
		Assert.assertEquals(ScannedSkuDataMapUrd.get("SKU_1_SKUName"),base.GetText(NormalSaleGoldAndSilverObj.RecallItemNameVerify("gridcell",ScannedSkuDataMapUrd.get("SKU_1_SKUName"))),"Item name mismatch: "+ScannedSkuDataMapUrd.get("SKU_1_SKUName")+" but found "+base.GetText(NormalSaleGoldAndSilverObj.RecallItemNameVerify("gridcell",ScannedSkuDataMapUrd.get("SKU_1_SKUName")))+" in recall estimation Page");

		//Step 16: Select transaction type as Exchange
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_TransactionTypeExchange);

		//Step 17.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Map<String, String> CashierScreenExchange = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		System.out.println("CashierScreenExchange: " + CashierScreenExchange.toString());
		Assert.assertEquals(CashierScreenExchange.get("Item1Name"),ScannedSkuDataMapUrd.get("SKU_1_SKUName"),"Item name mismatch: "+ScannedSkuDataMapUrd.get("SKU_1_SKUName")+" but found "+CashierScreenExchange.get("Item1Name")+" in recall estimation Page");

		//Step 18.Choose a sales representative[Step not required due new application change]
		//Step 19:  Click on amount 0.00	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
		//base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));

		//Step 20:Post the exchange invoice
		//Expected :  Check Exchange invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Map<String, String> PurchaseBill = pdfUtils.URDPurchaseBill("Adjustment");
		String GrossWeightFromInvoice    = PurchaseBill.get("GrossWt1");
		String NetWeightFromInvoice      = PurchaseBill.get("NetWt1");
		String TotalAmountFromInvoice    =  PurchaseBill.get("TotalAmount");
		String ExchangeFromInvoice       = PurchaseBill.get("PaymentDetails");
		String QtyFromInvoice            = PurchaseBill.get("QtyPcs");

		asrt.assertEquals(Double.parseDouble(ExchangeFromInvoice), Double.parseDouble(ActualTotalAmountURD), 1, "Mismatch in exchange value URD Purchase bill adjustment:"+ExchangeFromInvoice+"but got" +ActualTotalAmountURD+"rom billing page");
		asrt.assertEquals(Double.parseDouble(GrossWeightFromInvoice), Double.parseDouble(ActualGrossWeightURD),"Mismatch in GrossWt value URD Purchase bill adjustment:"+GrossWeightFromInvoice+"but got" +ActualGrossWeightURD+"rom billing page");
		asrt.assertEquals(Double.parseDouble(NetWeightFromInvoice), Double.parseDouble(ActualGrossWeightURD),"Mismatch in NetWt value URD Purchase bill adjustment:"+NetWeightFromInvoice+"but got" +ActualGrossWeightURD+"rom billing page");	
		asrt.assertEquals(Double.parseDouble(TotalAmountFromInvoice), Double.parseDouble(ActualTotalAmountURD),1,"Mismatch in TotatAmt value URD Purchase bill adjustment:"+Double.parseDouble(TotalAmountFromInvoice)+"but got" +ActualTotalAmountURD+"rom billing page");
		asrt.assertEquals(Integer.parseInt(QtyFromInvoice),ExpectedLinesCountURD,"Mismatch in Line count URD Purchase bill adjustment: " + QtyFromInvoice +" but got " + ExpectedLinesCountURD + " from billing page");

		//Step 21. Again recall estimate from cashier screen
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 

		//Step 22. Select transaction type as sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_TransactionTypesale);

		//Step 23: Click on process estimation button[sale rep not required]
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Map<String, String> CashierScreenSale = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		System.out.println("CashierScreenSale: " + CashierScreenSale.toString());
		System.out.println("TransactionDataLine: " + TransactionDataLine.toString());
		Assert.assertEquals(CashierScreenSale.get("Item1Name"),TransactionDataLine.get("Item2Name"),"Item name mismatch: "+TransactionDataLine.get("Item2Name")+" but found "+CashierScreenSale.get("Item1Name")+" in recall estimation Page");

		// Step 24:Choose a sales representative
		// Step 25:Click on the Amount
		//Expected : 24.Note:Cashier screen *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightInCashierScreen   		= TransactionDataLineInCashierScreen.get("NetWeight");
		String TotalGrossWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in CashierScreen");		

		List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
//		String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(0).getText();
//		String ExpectedItemNameInCashierScreen = ProductNames.get(1).substring(ProductNames.get(1).indexOf("-") + 1).trim();
//		asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");			
		String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(0).getText();
		String ExpectedItemNameInCashierScreen = ProductNames.get(1).trim();
		String Actual = ActualItemNameInCashierScreen;
		String Expected = ExpectedItemNameInCashierScreen;
		asrt.assertTrue(Expected.contains(Actual),"Item name mismatch: Expected '" + Expected + "' to contain actual '" + Actual + "'in Cashier Screen");
		
		List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
		String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(0).getText().replace("-", "");
		String ExpectedQuantityInCashierScreen = ProductQtyList.get(1).replace("-", "");
		double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
		double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
		asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in CashierScreen");

		List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(0).getText().replaceAll("[^\\d.-]", "").trim();
		double SubTotalSumInCashierScreen = Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
		String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "").trim()));
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in CashierScreen");

		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in CashierScreen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxValueInCashierScreen = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreen+" but found "+ActualTaxInCashierScreen+" in CashierScreen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("\u20B9", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		//Step 26 : Post the sale invoice
		//Check the final ivoice details-Sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC204_FinalSaleInvoice");
		Thread.sleep(3000);		
		String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
		Thread.sleep(3000);
		String GrossAmount         = TotalwithoutTax;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = String.valueOf(SkuList.size());
		String Adjustment          = ActualTotalAmountURD;
		String PaymentHDFCCard     = PaymentAmount;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalNetWeightPdf   = TotalNetWeight;
		String TotalGrossWeightPdf = TotalNetWeightPdf;
		double CalcTotalAmount     = Double.parseDouble(TaxableValue)+Double.parseDouble(Tax);
		String NetValue            = String.format("%.2f", CalcTotalAmount);
		NetValue                   = NetValue.replaceAll("^0+(?!$)", "").trim();
		Thread.sleep(3000);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeightPdf,
				TotalGrossWeightPdf,
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
	// Test Case Title : URd purchase of precia
	// Automation ID : TC203
	// Author: Neethu
	// </summary>
	public void TC203_UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable() throws Exception {

		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double ErpGoldRate22k = appUtils.FetchCurrentBoardRate(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_Purity);
		double OGRate24k = (ErpGoldRate22k*24)/22 ;
		String GoldRateFor24K = Double.toString(OGRate24k);
		
//		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate("24k");
//		System.out.println("BoardRate24k "+ErpGoldRate24k);

		//Step 3: Select customer and Click on add to estimation button
		Thread.sleep(2000);
		appUtils.SearchByCustomerID(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 4: Select URD
		Thread.sleep(8000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.URD);
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Step 5:Click on URD precia
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.URD_PerciaProduct));	

		//Step 6:Select Configuration(purity)
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_PurityConfig);

		//Error-handling , if present
		try {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}catch (Exception e) {}


		//step 7:Click on Add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8. Enter receipt number and fetch the details
		//Expected: Verify  whether the entered receipt number details are fetched
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"),UtilityTestData.URD_ReceiptNumberPercia);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Fetch"));
		Thread.sleep(4000);

		//Receipt valid for 15 days
		asrt.assertFalse(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "No data found popup displayed: Values not fetched or value expired");	

		//Step 9. Select QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Step 10. Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Step 11. click on calculate button
		//Expected: Check Purchase rate, * Check calculation 
		Thread.sleep(3000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(5000);

		Thread.sleep(3000);
		String ExpectedURDGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String URDItemName                            = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String URDTotalAmountInItemDetailsPge         = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String URDActualAmnt                          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();;	
		String RateItemdetailPge                      = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		String NetWeightInItemDetailsPge              = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
		String MakingAmtItemDetailPge                 = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetmakkingamt"));
		String URDPerciousStoneAmount                 = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("PrcOgpstoneamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();;	
		String ExpectedURDItemNameIntItemDetailsPge   = URDItemName.substring(URDItemName.indexOf("-") + 2);

		//Calculation
		double ExpAmount                              = Double.parseDouble(NetWeightInItemDetailsPge) * Double.parseDouble(RateItemdetailPge);
		double ExpTotalAmount                         = Double.parseDouble(URDActualAmnt) + Double.parseDouble(MakingAmtItemDetailPge) + Double.parseDouble(URDPerciousStoneAmount) ;

		asrt.assertEquals(Double.parseDouble(RateItemdetailPge),Double.parseDouble(UtilityTestData.URD_ReceiptNumberPerciaGoldRate),1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberPerciaGoldRate+" but got "+RateItemdetailPge+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(URDActualAmnt), ExpAmount,1.0,"Amount mismatch: "+URDActualAmnt+" but got "+ExpAmount+" in URDP item details page");
		asrt.assertEquals(Double.parseDouble(URDTotalAmountInItemDetailsPge), ExpTotalAmount,1.0,"Amount mismatch: "+URDTotalAmountInItemDetailsPge+" but got "+ExpTotalAmount+" in URDP item details page");

		Map<String, String> ScannedSkuDataMapUrd = new LinkedHashMap<>();	
		appUtils.ItemDetailsTableValues(1,ScannedSkuDataMapUrd);

		//Step 12: Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//URD Billing page validation
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCountURD       = AllProductRows.size();
		int ActualLinesCountURD         = Integer.parseInt(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4")));
		String ActualItemNameURD        = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualGrossWeightURD     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5")).replace("-", ""); 
		Thread.sleep(3000);
		String ActualTotalWithoutTaxURD = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualSubTotalURD        = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTotalAmountURD     = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();

		asrt.assertEquals(ExpectedLinesCountURD, ActualLinesCountURD,"Lines count mismatch: Expected is "+ExpectedLinesCountURD+" but got "+ActualLinesCountURD+" in Billing page");
		asrt.assertEquals(ActualItemNameURD, ExpectedURDItemNameIntItemDetailsPge,"Item name mismatch: "+ExpectedURDItemNameIntItemDetailsPge+" but found "+ActualItemNameURD+" in billing page");
		asrt.assertEquals( Double.parseDouble(ActualGrossWeightURD), Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge), 0.01, "Mismatch in GrossWeightt: Expected " + ExpectedURDGrossWeightInItemDetailsPge + " but got " + ActualGrossWeightURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalWithoutTaxURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total without tax: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalWithoutTaxURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualSubTotalURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in value Subtotal: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualSubTotalURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalAmountURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total amount: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalAmountURD + " from billing page");

		System.out.println("ExpectedURDGrossWeightInItemDetailsPge :"+ExpectedURDGrossWeightInItemDetailsPge);
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(ActualItemNameURD);
		ProductQtyList.add(ActualGrossWeightURD);
		ProductTotalList.add(ActualTotalAmountURD);

		//Step 13: Scan SKU
		//Check calculation,
		//Check average rate
		//Note:Billing Screen *No of Product lines*Displayed Item Name*Displayed Quantity, *Displayed TOTAL (without Tax),*Displayed Subtotal, *TAX *Total Amount
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();	
		RequiredSKUResult SkuGoldSaleItem = appUtils.GetRequiredQuantitySKU(
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_TranferType,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_FromCounterGold,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_MetalTypeGold,
				Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge),
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_SKUCount
				);
		List<String> SkuList = SkuGoldSaleItem.RequiredQtySKUs;
		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		int SkuCounter = 1;	
		String SkuPurityReturn = "22k";

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku    = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,NetWeightInItemDetailsPge,SkuPurityReturn,RateItemdetailPge,GoldRateFor24K);
		}

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.-]", "").replaceAll("[^0-9.-]", "").trim();
		SubTotal = SubTotal.replace("-", "");
		String Discount                         = TransactionDataLine.get("Discount");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String NetTotalBillScrn                 = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.-]", "").trim();
		TotalAmount = TotalAmount.replace("-", "");
		double ExpectedTax = Double.parseDouble(ProductTotalList.get(1)) * 0.03;


		//Note:Billing Screen *No of Product lines*Displayed Item Name*Displayed Quantity, *Displayed TOTAL (without Tax),*Displayed Subtotal, *TAX *Total Amount
		List<String> ActualNames                = mathUtils.ValidateTransactionLineCalculationsReturnProd(TransactionDataLine,ScannedSkuDataMap);

		String SKUName                          = ActualNames.toString().replace("[", "").replace("]", "").trim();
		List<String> TotalWithoutTaxElements    = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String TotalwithoutTax                  = TotalWithoutTaxElements.get(1).replaceAll("[^\\d.]", "").trim();

		//Billing page calculation
		double ExpectedSubTotl                  = Double.parseDouble(TotalwithoutTax) - Double.parseDouble(ActualTotalWithoutTaxURD);;
		double ExpectedSubTotal                 = Math.abs(ExpectedSubTotl);
		double ExpectedSubTotalAmt              = Math.round((ExpectedSubTotal) * 100.0) / 100.0;
		double ExpectedTotalAmt                 = ExpectedSubTotal - Double.parseDouble(Tax);
		double ExpectedTotalAmount              = Math.abs(ExpectedTotalAmt);


		asrt.assertEquals(Double.parseDouble(Tax), ExpectedTax,1,"Tax mismatch: Expected is "+ExpectedTax+" but got "+Tax+" in Billing page");
		asrt.assertEquals(Double.parseDouble(SubTotal), ExpectedSubTotalAmt,1,"SubTotal mismatch: Expected is "+ExpectedSubTotalAmt+" but got "+SubTotal+" in Billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmount,1,"Total amount mismatch: "+ExpectedTotalAmount+" but found "+TotalAmount+" in billing page");

		//Step 14: save to estimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = TotalwithoutTax;
		String TotalAmnt           = Double.toString(ExpectedSubTotal);
		String Gst                 =  Tax;
		String InvoiceTotal        =  NetTotalBillScrn;
		String NetTotal            =  NetTotalBillScrn;
		Thread.sleep(3000);
		String ProformaPdfName     = pdfUtils.DownloadAndRenameLatestPDF("TC203_ProformaInvoice");
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				);

		//Step 15: Recall estimate from cashier
		//Expected : Verify whether the recalled item is the same as the item in the cart. 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 

		// Validation
		appUtils.RecallEstimateItemValidation(
				Arrays.asList(ActualItemNameURD),
				Arrays.asList( ActualGrossWeightURD),
				Arrays.asList(SKUName),
				Arrays.asList(TotalNetWeight),
				ExpectedSubTotal,
				Double.parseDouble(Tax),
				ExpectedTotalAmount,
				Integer.parseInt(LinesCount),
				"URD Percia"
				);

		//Step 16: Select transaction type as Exchange
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_TransactionTypeExchange);

		//Step 17: Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 18: Choose a sales representative[Step not required due new application change]
		//Step 19: Click on amount 	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.isExists(LoginPageObj.Edt_AlertText("Convert to Advance"));
		base.buttonClick(LoginPageObj.Edt_AlertText(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_PaymentMode2));	
		Thread.sleep(4000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SalesPerson("3"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Ele_SalesPerson("3"),1);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), UtilityTestData.DueYear);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Remarks("6"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"), UtilityTestData.Remarks);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_NomineeName);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"),1);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), "1989");
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));	
		Thread.sleep(3000);	
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		Thread.sleep(5000);	
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));
		Thread.sleep(8000);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));

		//Step 20:Post the exchange invoice
		//Expected: Check Exchange invoice details		
		Map<String, String> Result = pdfUtils.NormalAdvancePdfValidation();	

		String AdvanceReceived = Result.get("AdvanceReceived");
		String FixedGoldRate = Result.get("FixedGoldRate");
		String MaxGoldRate = Result.get("MaxGoldRate");
		String ApproxWeight = Result.get("ApproxWeight");
		String DepositType = Result.get("DepositType");
		double PdfAdvanceReceived = Double.parseDouble(AdvanceReceived.replaceAll("[^\\d.]", "").trim());
		double PdfFixedGoldRate = Double.parseDouble(FixedGoldRate.replaceAll("[^\\d.]", "").trim());
		double PdfMaxGoldRate = Double.parseDouble(MaxGoldRate.replaceAll("[^\\d.]", "").trim());

		asrt.assertEquals(PdfAdvanceReceived,ExpectedTotalAmount,1.0, "Mismatch in total amount in billing screen actual "+PdfAdvanceReceived+" and Expected "+ExpectedTotalAmount+"in Normal Advance PDF");
		asrt.assertEquals(PdfMaxGoldRate, PdfFixedGoldRate,1.0, "Mismatch in Fixed Gold Rate actual "+PdfMaxGoldRate+" and Expected "+PdfFixedGoldRate+" Max Gold Rate in Normal Advance PDF");
		asrt.assertEquals(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_DepositType, DepositType, "Mismatch in Deposit Type Actual "+UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC203_DepositType+" and Expected "+DepositType+ " in Normal Advance PDF");

		Double WeightValue = PdfAdvanceReceived/PdfMaxGoldRate;
		BigDecimal CalcWeightValue = new BigDecimal(WeightValue).setScale(3, RoundingMode.HALF_UP);
		double ApproxWeightPdfValue = Double.parseDouble(ApproxWeight.replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ApproxWeightPdfValue, CalcWeightValue.doubleValue(),1.0, "Mismatch in Approx Weight Value Actual "+ApproxWeightPdfValue+" and Expected "+CalcWeightValue.doubleValue()+" Approx Weight in Normal Advance PDF");

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton","Close"));

		//Step 21. Again recall estimate from cashier screen
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

		//Step 22. Select transaction type as sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC205_TransactionTypesale);

		//Step 23: Click on process estimation button
		//Step 24: Choose a sales representative[sale rep not required]
		//Expected : Note:Cashier screen *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);

		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in CashierScreen");		

		List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(0).getText();
		String ExpectedItemNameInCashierScreen = ProductNames.get(1).substring(ProductNames.get(1).indexOf("-") + 1).trim();
		asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");			

		List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
		String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(0).getText().replace("-", "");
		String ExpectedQuantityInCashierScreen = ProductQtyList.get(1).replace("-", "");
		double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
		double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
		asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in CashierScreen");

		List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(0).getText().replaceAll("[^\\d.-]", "").trim();
		double SubTotalSumInCashierScreen = Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
		String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "").trim()));
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in CashierScreen");

		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in CashierScreen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxValueInCashierScreen = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreen+" but found "+ActualTaxInCashierScreen+" in CashierScreen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+Double.parseDouble(ActualTaxInCashierScreen);;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("\u20B9", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");

		// Step 25:Click on the Amount
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		//Step 26 : Post the sale invoice
		//Expected: Check the final invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC203_FinalSaleInvoice");
		Thread.sleep(3000);		
		String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
		Thread.sleep(3000);
		String GrossAmount         = TotalwithoutTax;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = String.valueOf(SkuList.size());
		double CalcTotalAmount     = Double.parseDouble(TaxableValue)+Double.parseDouble(Tax);
		String NetValue            = String.format("%.2f", CalcTotalAmount);	
		NetValue                   = NetValue.replaceAll("^0+(?!$)", "").trim();
		String Adjustment          = NetValue;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalNetWeightPdf   = TotalNetWeight;
		String TotalGrossWeightPdf = TotalNetWeightPdf;
		Thread.sleep(3000);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeightPdf,
				TotalGrossWeightPdf,
				TaxableValue,
				NetValue,          
				"",
				Adjustment,
				SkuList,
				ScannedSkuDataMap
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	 
	}

	// <summary>
	// Test Case Title : URD purchase of Uncut
	// Automation ID : TC202
	// Author: Aiswarya
	// </summary>
	public void TC202_UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable() throws Exception {
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	 

		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer and Click on add to estimation button
		Thread.sleep(2000);
		appUtils.SearchByCustomerID(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 4: Select URD
		Thread.sleep(8000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.URD);
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Step 5:Click on URD Uncut
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.URD_UncutProduct));	

		//Step 6:Select Configuration(purity)
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_PurityConfig);

		//Error-handling , if present
		try {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}catch (Exception e) {}

		//step 7:Click on Add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 8. Enter receipt number and fetch the details
		//Expected: Verify  whether the entered receipt number details are fetched
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"),UtilityTestData.URD_ReceiptNumberUncut);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Fetch"));
		Thread.sleep(5000);

		//Receipt valid for 15 days
		asrt.assertFalse(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "No data found popup displayed: Values not fetched or value expired");	

		//Step 9. Select QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Step 10. Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Step 11. click on calculate button
		//Expected: Check Purchase rate, * Check calculation 
		Thread.sleep(3000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(5000);

		Thread.sleep(3000);
		String ExpectedURDGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String URDItemName                            = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String PreciaStoneAmount					  =	base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("PrcOgpstoneamt"));
		String URDTotalAmountInItemDetailsPge         = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String URDActualAmnt                          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();;	
		String RateItemdetailPge                      = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		String NetWeightInItemDetailsPge              = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
		String MakingAmtItemDetailPge                 = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetmakkingamt"));
		String ExpectedURDItemNameIntItemDetailsPge   = URDItemName.substring(URDItemName.indexOf("-") + 2);
		//Calculation
		double ExpAmount                              = Double.parseDouble(NetWeightInItemDetailsPge) * Double.parseDouble(RateItemdetailPge);
		double ExpTotalAmount                         = Double.parseDouble(URDActualAmnt) + Double.parseDouble(MakingAmtItemDetailPge) + Double.parseDouble(PreciaStoneAmount);

		Thread.sleep(3000);
		asrt.assertEquals(Double.parseDouble(URDActualAmnt), ExpAmount,1.0,"Amount mismatch: "+ExpAmount+" but got "+URDActualAmnt+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(URDTotalAmountInItemDetailsPge), ExpTotalAmount,1.0,"Amount mismatch: "+ExpTotalAmount+" but got "+URDTotalAmountInItemDetailsPge+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(RateItemdetailPge),Double.parseDouble(UtilityTestData.URD_ReceiptNumberUncutGoldRate),1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberUncutGoldRate+" but got "+RateItemdetailPge+" in URD item details page");

		//Step 12: Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//URD Billing page validation
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCountURD       = AllProductRows.size();
		int ActualLinesCountURD         = Integer.parseInt(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4")));
		String ActualItemNameURD        = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualGrossWeightURD     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5")).replace("-", ""); 
		Thread.sleep(3000);
		String ActualTotalWithoutTaxURD = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualSubTotalURD        = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTaxURD             = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTotalAmountURD     = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();

		asrt.assertEquals(ExpectedLinesCountURD, ActualLinesCountURD,"Lines count mismatch: Expected is "+ExpectedLinesCountURD+" but got "+ActualLinesCountURD+" in Billing page");
		asrt.assertEquals(ActualItemNameURD, ExpectedURDItemNameIntItemDetailsPge,"Item name mismatch: "+ExpectedURDItemNameIntItemDetailsPge+" but found "+ActualItemNameURD+" in billing page");
		asrt.assertEquals( Double.parseDouble(ActualGrossWeightURD), Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge), 0.01, "Mismatch in GrossWeightt: Expected " + ExpectedURDGrossWeightInItemDetailsPge + " but got " + ActualGrossWeightURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalWithoutTaxURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total without tax: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalWithoutTaxURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualSubTotalURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in value Subtotal: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualSubTotalURD + " from billing page");
		asrt.assertEquals( Double.parseDouble(ActualTotalAmountURD), Double.parseDouble(URDTotalAmountInItemDetailsPge), 0.01, "Mismatch in Total amount: Expected " + URDTotalAmountInItemDetailsPge + " but got " + ActualTotalAmountURD + " from billing page");

		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(ActualItemNameURD);
		ProductQtyList.add(ActualGrossWeightURD);
		ProductTotalList.add(ActualTotalAmountURD);
		String UrdQuantity22K = NetWeightInItemDetailsPge ;
		String BoardRate22k = RateItemdetailPge;
		double ErpGoldRate22k = appUtils.FetchCurrentBoardRate(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_Purity);
		double OGRate24k = (ErpGoldRate22k*24)/22 ;
		String GoldRateFor24K = Double.toString(OGRate24k); 
		double SkuQty = Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge)+3;
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 13; Scan SKU
		//Expected: Check calculation, 
		//Note:Billing Screen *No of Product lines*Displayed Item Name*Displayed Quantity *Displayed TOTAL (without Tax),*Displayed Subtotal, *TAX *Total Amount
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_TranferType,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_FromCounterGold,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_MetalTypeGold,
				SkuQty,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_SKUCount
				);

		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		List<String> SkuList=SkuGoldSale.PcsRateSKU;
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku    = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber, UrdQuantity22K, "22k", BoardRate22k, GoldRateFor24K);
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.-]", "").trim();
		String Discount                         = TransactionDataLine.get("Discount");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.-]", "").trim();
		String NetTotalBillScrn                 = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount                       = TransactionDataLine.get("LinesCount");
		List<String> ActualNames                = mathUtils.ValidateTransactionLineCalculationsReturnProd(TransactionDataLine,ScannedSkuDataMap);
		String SKUName                          = ActualNames.toString().replace("[", "").replace("]", "").trim();
		List<String> TotalWithoutTaxElements    = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String TotalwithoutTax                  = TotalWithoutTaxElements.get(1).replaceAll("[^\\d.]", "").trim();

		//Billing page calculation
		double ExpectedSubTotal                 = Double.parseDouble(TotalwithoutTax) - Double.parseDouble(ActualTotalWithoutTaxURD);
		double ExpectedSubTotalAmt              = Math.round((ExpectedSubTotal) * 100.0) / 100.0;
		double ExpectedTotalAmount              = ExpectedSubTotal + Double.parseDouble(Tax);
		double ExpectedTax = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		Thread.sleep(3000);

		asrt.assertEquals(Double.parseDouble(Tax), ExpectedTax,1,"Tax mismatch: Expected is "+ExpectedTax+" but got "+Tax+" in Billing page");
		asrt.assertEquals(Double.parseDouble(SubTotal), ExpectedSubTotalAmt,1,"SubTotal mismatch: Expected is "+ExpectedSubTotalAmt+" but got "+SubTotal+" in Billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmount,1,"Total amount mismatch: "+ExpectedTotalAmount+" but found "+TotalAmount+" in billing page");

		//Step 14: save to estimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = TotalwithoutTax;
		String TotalAmnt           = Double.toString(ExpectedSubTotal);
		String Gst                 =  Tax;
		String InvoiceTotal        =  NetTotalBillScrn;
		String NetTotal            =  NetTotalBillScrn;
		Thread.sleep(5000);
		String ProformaPdfName     = pdfUtils.DownloadAndRenameLatestPDF("TC202_ProformaInvoice");
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				);

		//Step 15. Recall estimate from cashier
		//Expected : Verify whether the recalled item is the same as the item in the cart. 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 
		appUtils.RecallEstimateItemValidation(
				Arrays.asList(ActualItemNameURD),
				Arrays.asList( ExpectedURDGrossWeightInItemDetailsPge),
				Arrays.asList(SKUName),
				Arrays.asList(ProductQtyList.get(1)),
				ExpectedSubTotal,
				Double.parseDouble(Tax),
				ExpectedTotalAmount,
				Integer.parseInt(LinesCount),
				"URD Uncut"
				);

		//Step 16: Select transaction type as Exchange
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_TransactionTypeExchange);

		//Step 17.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 18.Choose a sales representative[Step not required due new application change]
		//Step 19:  Click on amount 	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//Step 20:Post the exchange invoice
		//Expected : Check Exchange invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Map<String, String> PurchaseBill = pdfUtils.URDPurchaseBill("Adjustment");
		String GrossWeightFromInvoice    = PurchaseBill.get("GrossWt1");
		String NetWeightFromInvoice      = PurchaseBill.get("NetWt1");
		String TotalAmountFromInvoice    =  PurchaseBill.get("TotalAmount");
		String ExchangeFromInvoice       = PurchaseBill.get("PaymentDetails");
		String QtyFromInvoice            = PurchaseBill.get("QtyPcs");

		asrt.assertEquals(Double.parseDouble(ExchangeFromInvoice), Double.parseDouble(ActualTotalAmountURD), 0.01, "Mismatch in exchange value URD Purchase bill adjustment:"+ExchangeFromInvoice+"but got" +ActualTotalAmountURD+"rom billing page");
		asrt.assertEquals(Double.parseDouble(GrossWeightFromInvoice), Double.parseDouble(ActualGrossWeightURD),"Mismatch in GrossWt value URD Purchase bill adjustment:"+GrossWeightFromInvoice+"but got" +ActualGrossWeightURD+"rom billing page");
		asrt.assertEquals(Double.parseDouble(NetWeightFromInvoice), Double.parseDouble(NetWeightInItemDetailsPge),"Mismatch in NetWt value URD Purchase bill adjustment:"+NetWeightFromInvoice+"but got" +NetWeightInItemDetailsPge+"rom billing page");	
		asrt.assertEquals(Double.parseDouble(TotalAmountFromInvoice), Double.parseDouble(ActualTotalAmountURD),0.01,"Mismatch in TotatAmt value URD Purchase bill adjustment:"+Double.parseDouble(TotalAmountFromInvoice)+"but got" +ActualTotalAmountURD+"rom billing page");
		asrt.assertEquals(Integer.parseInt(QtyFromInvoice),ExpectedLinesCountURD,"Mismatch in Line count URD Purchase bill adjustment: " + QtyFromInvoice +" but got " + ExpectedLinesCountURD + " from billing page");

		//Step 21. Again recall estimate from cashier screen
		//Expected : Verify whether the recalled item is the same as the item in the cart.
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

		String ExpectedEstmnNumberSale = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumberSale,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumberSale+" in Recall Estimation page"); 

		List<WebElement> SKUNumberFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "SKUNUMBER"));
		String ActualSKURaw = SKUNumberFromRecall.get(0).getText().trim();
		String ExpectedSKURaw = SkuList.get(0);
		String ActualSKUName = ActualSKURaw.contains("-")? ActualSKURaw.substring(ActualSKURaw.lastIndexOf("-") + 1).trim(): ActualSKURaw.trim();
		String ExpectedSKUName = ExpectedSKURaw.contains("-")? ExpectedSKURaw.substring(ExpectedSKURaw.lastIndexOf("-") + 1).trim(): ExpectedSKURaw.trim();
		asrt.assertEquals(ActualSKUName, ExpectedSKUName, "SKU Number  mismatch: Expected " + ExpectedSKUName + " but got " + ActualSKUName + "in the Recall Page.");

		//Step 22. Select transaction type as sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC202_TransactionTypesale);

		//Step 23: Click on process estimation button[sale rep not required]
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 24: Choose a sales representative[Step not required due new application change]
		//Expected : 24.Note:Cashier screen *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightInCashierScreen   		= TransactionDataLineInCashierScreen.get("NetWeight");
		String TotalGrossWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in CashierScreen");		

		List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(0).getText();
		String ExpectedItemNameInCashierScreen = ProductNames.get(1).substring(ProductNames.get(1).indexOf("-") + 1).trim();
		//asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");			
		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");

		
		List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
		String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(0).getText().replace("-", "");
		String ExpectedQuantityInCashierScreen = ProductQtyList.get(1).replace("-", "");
		double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
		double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
		asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in CashierScreen");

		List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(0).getText().replaceAll("[^\\d.-]", "").trim();
		double SubTotalSumInCashierScreen = Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
		String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "").trim())); 
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in CashierScreen");

		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in CashierScreen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxValueInCashierScreen = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreen+" but found "+ActualTaxInCashierScreen+" in CashierScreen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("\u20B9", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");


		// Step 25:Click on the Amount
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		//Step 26 : Post the sale invoice
		//Check the final ivoice details-Sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC202_FinalSaleInvoice");
		Thread.sleep(3000);		
		String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
		Thread.sleep(3000);
		String GrossAmount         = TotalwithoutTax;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = String.valueOf(SkuList.size());
		String Adjustment          = ActualTotalAmountURD;
		String PaymentHDFCCard     = PaymentAmount;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalNetWeightPdf   = TotalNetWeight;
		String TotalGrossWeightPdf = ProductQtyList.get(1);
		double CalcTotalAmount     = Double.parseDouble(TaxableValue)+Double.parseDouble(Tax);
		String NetValue            = String.format("%.2f", CalcTotalAmount);
		NetValue                   = NetValue.replaceAll("^0+(?!$)", "").trim();
		Thread.sleep(3000);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeightPdf,
				TotalGrossWeightPdf,
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
	// Test Case Title : URD with advance/scheme
	// Automation ID : TC207
	// Author: Vishnu RR
	// </summary>
	public void TC207_UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable() throws Exception {	 

		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Pre-Requisite : Normal Advances 
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_CustomerNo,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		// Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"24k");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		try {
			NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close");
		}catch (Exception e) {}

		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		//Error-handling , if present
		try {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}catch (Exception e) {}

		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		try {
			NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close");
		}catch (Exception e) {}

		//QC person
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//	Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),"1");
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),"1");
		Thread.sleep(10000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double ErpGoldRateFor24K = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		System.out.println("CurrentGoldRate24k "+ErpGoldRateFor24K);
		String GoldRate22k =  Double.toString(Math.round((ErpGoldRateFor24K*22)/24)).replaceAll("\\.0+$", "");
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		Thread.sleep(8000);
		appUtils.SearchByCustomerID(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		// Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		// Step 6: click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		// Step 7: Select advance and click on cart button
		// Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.Terminal);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(1000);
		//base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));

		By TableContainer = AdvanceRepaymentObj.Ele_Scroll(".win-vertical");
		JavascriptExecutor JsExecutor = (JavascriptExecutor) driver;
		WebElement Container = driver.findElement(TableContainer);
		JsExecutor.executeScript("arguments[0].scrollTop = 0;", Container);
		Thread.sleep(500);

		boolean Found = false;
		int LastCount = -1;
		String CleanAmt = AdvanceAmount.replaceAll(",", "").replaceAll("\\.00$", "");

		// Combined XPath for Rate + Deposit
		//	String CombinedXPath ="//div[contains(@class,'h4 ellipsis cell') and normalize-space(text())='" +Current22k.trim() +"']/ancestor::div[contains(@aria-label,'" + CleanAmt + "')]";
		By CombinedXPath = AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.NormalAdvance(GoldRate22k,CleanAmt);
		while (!Found) {
			List<WebElement> MatchingRows = driver.findElements(CombinedXPath);
			for (int Index = 0; Index < MatchingRows.size(); Index++) {
				try {
					WebElement DepositBtn = MatchingRows.get(Index);

					// Scroll element into center of container
					JsExecutor.executeScript(
							"arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
							DepositBtn
							);
					Thread.sleep(300);
					try {
						DepositBtn.click();
					} catch (Exception Exception) {
						JsExecutor.executeScript("arguments[0].click();", DepositBtn);
					}
					Found = true;
					break;

				} catch (StaleElementReferenceException Exception) {
					MatchingRows = driver.findElements(CombinedXPath);
					Index--; 
				} catch (Exception Exception) {

				}
			}

			if (Found) break;
			JsExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].clientHeight;",Container);
			Thread.sleep(600);
			//int CurrentCount = driver.findElements(By.xpath("//div[contains(@class,'h4 ellipsis cell')]")).size();
			int CurrentCount = driver.findElements(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("h4 ellipsis cell")).size();
			if (CurrentCount == LastCount) {
				break;
			}
			LastCount = CurrentCount;
		}

		Thread.sleep(3000);
		//base.ClickCondition(InvoiceWithAdvanceSchemeAndOGObj.Ele_NormalAdv("h4 ellipsis cell", ErpGoldRateFor24K));
		String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		//Step 8:  Enter OTP
		//Step 9: Select URD
		Thread.sleep(8000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_URDG);
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Step 10:Click on URD Gold
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_URDG));	

		//Step11:Select Configuration(purity)
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC204_PurityConfig);

		//Error-handling , if present
		try {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}catch (Exception e) {}

		//step 12:Click on Add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 13. Enter receipt number and fetch the details
		//Expected: Verify  whether the entered receipt number details are fetched
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"),UtilityTestData.URD_ReceiptNumberGold);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Fetch"));
		Thread.sleep(4000);

		//Receipt valid for 15 days
		asrt.assertFalse(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "Entered receipt number details are not fetched or Receipt expired (15 days)");	

		//Step 14. Select QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Step 15. Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Step 16. click on calculate button
		//Expected: Check Purchase rate, * Check calculation 
		Thread.sleep(3000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(5000);

		Thread.sleep(3000);
		String ExpectedURDGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String URDItemName                            = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String URDTotalAmountInItemDetailsPge         = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String URDActualAmnt                          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();;	
		String RateItemDetailPge                      = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		String NetWeightInItemDetailsPge              = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
		String MakingAmtItemDetailPge                 = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetmakkingamt"));
		String ExpectedURDItemNameIntItemDetailsPge   = URDItemName.substring(URDItemName.indexOf("-") + 2);
		double ExpAmount                              = Double.parseDouble(NetWeightInItemDetailsPge) * Double.parseDouble(RateItemDetailPge);
		double ExpTotalAmount                         = Double.parseDouble(URDActualAmnt) + Double.parseDouble(MakingAmtItemDetailPge);

		asrt.assertEquals(Double.parseDouble(RateItemDetailPge),Double.parseDouble(UtilityTestData.URD_ReceiptNumberGoldRate),1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberGoldRate+" but got "+RateItemDetailPge+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(URDActualAmnt), ExpAmount,1.0,"Amount mismatch: "+ExpAmount+" but got "+URDActualAmnt+" in URD item details page");
		asrt.assertEquals(Double.parseDouble(URDTotalAmountInItemDetailsPge), ExpTotalAmount,1.0,"Amount mismatch: "+ExpTotalAmount+" but got "+URDTotalAmountInItemDetailsPge+" in URD item details page");
		Map<String, String> ScannedSkuDataMapUrd = new LinkedHashMap<>();	
		appUtils.ItemDetailsTableValues(1,ScannedSkuDataMapUrd);	

		//Step 17. click on Add to cart Button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(3000);
		String ActualGrossWeightURD     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5")).replace("-", ""); 

		//Step 18: Scan SkU
		// Expected Result: Check Calculation 
		// Expected Result:Validating  No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC207_TranferType,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC207_FromCounterGold,
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC207_MetalTypeGold,
				Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge),
				UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC207_SKUCount
				);

		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		Map<String, String> ScannedSkuDataMaps = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		ProductNames.add(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_AdvanceName);
		ProductQtyList.add(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_DepositAdjustmentQty);
		ProductTotalList.add((AdjustmentAmount));
		String URDName = (URDItemName);
		if (URDName.contains("-")) {
			URDName = URDName.substring(URDName.indexOf("-") + 1).trim();
		}
		ProductNames.add(URDName);
		ProductQtyList.add(NetWeightInItemDetailsPge);
		ProductTotalList.add(URDTotalAmountInItemDetailsPge);
		List<String> SkuList=SkuGoldSale.PcsRateSKU;
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku    = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);			
			String SkuProductName = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			if (SkuProductName.contains("-")) {
				SkuProductName = SkuProductName.substring(SkuProductName.indexOf("-") + 1).trim();
			}
			ProductNames.add(SkuProductName);
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);

			// Expected Result: Check Calculation 
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber,ExpectedURDGrossWeightInItemDetailsPge,"22K",RateItemDetailPge,String.valueOf(ErpGoldRateFor24K)); 

		}	
		// Expected Result:Validating  No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
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
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "
				+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

		double SubTotalSum = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName = base
					.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			//				if (i == 1) {
			String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();		
			//.substring(ProductNames.get(i).indexOf("-") + 1).trim();
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge), "Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			//asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			//				}
			//				else {
			//					String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
			//					String ExpectedcleanProductName;
			//					if (ExpectedItemNameInBillingPge.contains("-")) {
			//						ExpectedcleanProductName = ExpectedItemNameInBillingPge.substring(ExpectedItemNameInBillingPge.indexOf("-") + 1).trim();
			//					} else {
			//						ExpectedcleanProductName = ExpectedItemNameInBillingPge.trim();
			//					}
			//					asrt.assertTrue(ExpectedcleanProductName.contains(ActualItemNameInBillingPge), "Item name mismatch: Expected is " + ExpectedcleanProductName + " but got "+ ActualItemNameInBillingPge + " in billing page");
			//					//asrt.assertEquals(ActualItemNameInBillingPge, ExpectedcleanProductName,"Item name mismatch: Expected is " + ExpectedcleanProductName + " but got "+ ActualItemNameInBillingPge + " in billing page");
			//				}
			List<WebElement> Quantity = base
					.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "
					+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(
					NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "")
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

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = Double.parseDouble(ProductTotalList.get(2)) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,
				"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge
				+ " in billing page");

		String SubTotals= base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		//double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTotalAmountInBillingPge = Double.parseDouble(SubTotals)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "
				+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		String InvoiceTotalAmount= String.format("%.2f",
				(

						Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "")) + 
						Double.parseDouble(Tax.replaceAll("[^\\d.-]", "")))-
				Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", ""))
				);

		double InvoiceTotalAmountBillingPage = Double.parseDouble(InvoiceTotalAmount.replaceAll("[^\\d.-]", ""));
		double AdjustmentAmountBillingPage = Double.parseDouble(AdjustmentAmount.replaceAll("[^\\d.-]", ""));
		double NetTotalCalulation = InvoiceTotalAmountBillingPage - AdjustmentAmountBillingPage;
		String NetTotalBillScrn = String.format("%.2f", NetTotalCalulation);

		//Step 19: save to estimate
		//Expected:Verify the Invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		String TaxableValue        = Double.toString(Double.parseDouble(ProductTotalList.get(2)));
		String Gst                 =  Tax;
		String InvoiceTotal        =  InvoiceTotalAmount;
		String NetTotalAmount            =  NetTotalBillScrn;
		String	TotalAmnt = Double.toString(Double.parseDouble(ProductTotalList.get(2)));;
		Thread.sleep(3000);
		String ProformaPdfName     = pdfUtils.DownloadAndRenameLatestPDF("TC207_ProformaInvoice");
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotalAmount,
				TotalAmnt
				);

		//Step:20 Recall the estimate from cashier screen
		//Expected Result: Verify whether the recalled item is the same as the item in the cart
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.clearData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", EstmnNumber));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page"); 
		appUtils.ValidateBillingAndRecallScreenData(EstmnNumber, ActualLinesCount, ProductNames, ProductQtyList,SubTotal, Tax, NetTotal);

		//Step:20 Select the Transaction Type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_TransactionType1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
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

		try {
			NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close");
		}catch (Exception e) {}

		//Step 21.Select the Transaction Type as Sales 
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_TransactionType2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Step 22.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));  
		base.setZoom(driver, 60);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Step 23.Choose Sales Representative 
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		String PaymentAmountHDFC = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));

		//Step 24: Click on amount
		//Step 25: Select payment method(cash/card)
		Thread.sleep(2000);
		//appUtils.PaymentAfterRecallEstimate(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData.TC207_PaymentMethod);
		appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();


		// Step 26: Post exchange invoice(sale)
		// Expected Result: Check final invoice details,* save receipt id for future Reference
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC207_FinalSaleInvoice");
		Thread.sleep(3000);		
		String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
		Thread.sleep(3000);
		String GrossAmount         = TaxableValue;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = String.valueOf(SkuList.size());
		String Adjustment          = URDTotalAmountInItemDetailsPge;
		String PaymentHDFCCard     = PaymentAmountHDFC;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalNetWeightPdf   = TotalNetWeight;
		String TotalGrossWeightPdf = TotalNetWeightPdf;
		double CalcTotalAmount = Double.parseDouble(TaxableValue.replaceAll("[^\\d.-]", "")) + 
				Double.parseDouble(Tax.replaceAll("[^\\d.-]", "")) - Double.parseDouble(AdjustmentAmount.replaceAll("[^\\d.-]", ""));    
		String NetValue = String.format("%.2f", CalcTotalAmount); 
		NetValue = NetValue.replaceAll("^0+(?!$)", "").trim();
		Thread.sleep(3000);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentAdvanceValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeightPdf,
				TotalGrossWeightPdf,
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
		// Test Case Title : URD purchase of uncut,gold,precia,and platinum
		// Automation ID : TC206
		// Author: Bharath
		// </summary>
		public void TC206_UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable() throws Exception {

			//Step 1: Login to POS
			//Step 2: URL: https://scunmv1je3k79005544-cpos.su.retail.dynamics.com/
			Thread.sleep(3000);
			login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
			base.setZoom(driver, 60);


			// Step : Pre-Requisite for taking BoardRate
			Thread.sleep(3000);
			double CurrentBoardRate=appUtils.FetchCurrentBoardRate("24k");
			System.out.println("-----------Currenet Board Rate--------\n"+CurrentBoardRate);

			//Step 3: Click on Transaction button
			Thread.sleep(5000);
			appUtils.HamBurgerButtonClick("iconShop");

			//Step 4: Select customer and Click on add to estimation button
			Thread.sleep(2000);
			appUtils.SearchByCustomerID(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_CustomerID, UtilityTestData.MenuBarOptions[5]);			
			List<String> ExpectedURDGrossWeightInItemDetailsPge= new ArrayList<>();
			List<String> URDItemName= new ArrayList<>();
			List<String> PreciaStoneAmount= new ArrayList<>();
			List<String> URDTotalAmountInItemDetailsPge= new ArrayList<>();
			List<String> URDActualAmnt= new ArrayList<>();                
			List<String> RateItemdetailPge = new ArrayList<>();             
			List<String> NetWeightInItemDetailsPge = new ArrayList<>();     
			List<String> MakingAmtItemDetailPge = new ArrayList<>();    
			List<String> ExpectedURDItemNameIntItemDetailsPge = new ArrayList<>();
			List<String> URDStoneAmnt = new ArrayList<>();
			boolean PurityChange = false;
			int PurityChangeRetry = 0;
			String PurityGold="";
			 double ExpectedURDSubtotal=0;
			 double RequiredURDNetWeight=0;
			 String ActualTotalAmountURD ="";
			 int ActualLinesCountURD = 0;
			 double NetURDRgWeight = 0.0;
			 Map<String, String> ScannedSkuDataMapUrd = new LinkedHashMap<>();
			 int UrdSkuNumber=1;
			 String ActualTotalWithoutTaxURD="";
			 String ActualGrossWeightURD="";
			 double RequiredURDGrossWeight =0.0;
			 

			 //			 Step 5: Select URD
			 //			 Step 6: Click on URD Gold
			 //			 Step 7: Select configuration(purity )
			 //			 Step 8: Click on Add item button
			 //			 Step 9: Choose Exchange
			 //			 Step 10: Enter receipt number and fetch details
			 //			 Step 11: Select QC person
			 //			 Step 12: Select Smith person
			 //			 Step 13: Click on calculate button
			 //			 Step 14: Click on Add to cart button
			 //			 Step 15: Select URD
			 //			 Step 16: Click on URD Uncut
			 //			 Step 17: Select configuration(purity )
			 //			 Step 18: Click on Add item button
			 //			 Step 19: Choose Exchange
			 //			 Step 20: Enter receipt number and fetch details
			 //			 Step 21: Select QC person
			 //			 Step 22: Select Smith person
			 //			 Step 23: Click on calculate button
			 //			 Step 24: Click on Add to cart button
			 //			 Step 25: Select URD
			 //			 Step 26: Click on URD Precia
			 //			 Step 27: Select configuration(purity )
			 //			 Step 28: Click on Add item button
			 //			 Step 29: Choose Exchange
			 //			 Step 30: Enter receipt number and fetch details
			 //			 Step 31: Select QC person
			 //			 Step 32: Select Smith person
			 //			 Step 33: Click on calculate button
			 //			 Step 34: Click on Add to cart button
			 //			 Step 35: Select URD
			 //			 Step 36: Click on URD platinum
			 //			 Step 37: Select configuration(purity )
			 //			 Step 38: Click on Add item button
			 //			 Step 39: Choose Exchange
			 //			 Step 40: Enter receipt number and fetch details
			 //			 Step 41: Select QC person
			 //			 Step 42: Select Smith person
			 //			 Step 43: Click on calculate button
			 //			 Step 44: Click on Add to cart button
			 //			 Expected: Verify whether the entered receipt number details are getting fetched
			 //			 Expected: Check Purchase rate for gold
			 //			 *Check calculation
			 //			 Expected: Check Purchase rate for uncut
			 //			 Check calculation
			 //			 Expected: Check Purchase rate for Precia
			 //			 Check calculation
			 //			 Expected: Check Purchase rate for Platinum
			 //			 Check calculation

			for(int i=0;i<UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_URD.size();i++)
			{
				PurityChangeRetry=0;
				PurityGold ="";
		
			// Select URD
			Thread.sleep(2000);
			while(!base.isPresent(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"))) {
			//base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Edt_Name("Search"), 30);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			Thread.sleep(3000);
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.URD);
			}
			Thread.sleep(2000);
			base.isPresent(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));
			
			// Click on URD 
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_URD.get(i)));			       
        	   while(PurityChangeRetry<2)
        	   {
        		   
			//Select Configuration(purity)
			if(!PurityChange)
			{
			if(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_URD.get(i)=="URDT")
			{
				Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_PurityConfig.getLast());
			}
			else {
				Thread.sleep(3000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_PurityConfig.getFirst());
			}
			
			}else {
				Thread.sleep(3000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
				WebElement Dropdown = driver.findElement(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
				Select SelectPurity = new Select(Dropdown);
				List<WebElement> Purity = SelectPurity.getOptions();				
				for (WebElement Purityoption : Purity) {
					System.out.print(Purityoption.getText().replaceAll("[^0-9]", "")+"and Purity from test data"+UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_PurityConfig.get(1));
				    if (UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_PurityConfig.get(1).trim().contains(Purityoption.getText().replaceAll("[^0-9]", "").trim()) && !Purityoption.getText().replaceAll("[^0-9]", "").trim().isEmpty()) {
				    	PurityGold = Purityoption.getText().trim();
				    	SelectPurity.selectByVisibleText(Purityoption.getText());
				        break;
				    }
				}
			}
           
			//Error-handling , if present		
			if(base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("OK"),30))
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}

			// Click on Add item button
			// Choose Exchange
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			try
			{
				//base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				driver.findElement(LoginPageObj.Btn_SignInButton("OK")).click();
			}catch(Exception e) {}
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

			//Enter receipt number and fetch the details
			//Expected: Verify  whether the entered receipt number details are fetched
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"));
			Thread.sleep(3000);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogprefreceiptno"),UtilityTestData.URD_ReceiptNumber);
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("ogpreflineno"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("ogpreflineno"),Integer.toString(i+1));
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			Thread.sleep(5000);
			PurityChangeRetry++;
			if(base.WaitforElementToBePresentWithTimeout(LoginPageObj.Ele_ErrorMessage("h4 wrapTextWithoutHyphen wrapTextWithLineBreaks"),40)) {
			if(base.GetText(LoginPageObj.Ele_ErrorMessage("h4 wrapTextWithoutHyphen wrapTextWithLineBreaks")).toLowerCase().contains("purity of 18k"))
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				Thread.sleep(3000);
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("iconBack"));
				PurityChange = true;
				continue;
			}
        }else {
        	
        	PurityChange = false;
			break;
        }
        	   }

			//Receipt valid for 15 days
			asrt.assertFalse(base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"),20), "No data found popup displayed: Values not fetched or value expired");
			
			//Select QC person
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),40);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

			//Select Smith person
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
			
			//click on calculate button
			//Expected: Check Purchase rate, * Check calculation 
			Thread.sleep(3000);
			base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			Thread.sleep(10000);
			 ExpectedURDGrossWeightInItemDetailsPge.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp")));
			 URDItemName.add(base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader")));
			 PreciaStoneAmount.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("PrcOgpstoneamt")));
			 URDStoneAmnt.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpstoneamt")));
			 URDTotalAmountInItemDetailsPge.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim());
			 URDActualAmnt.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")).replace("-", "").replace("\u20B9", "").replace(",", "").trim());	
			 RateItemdetailPge.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
			 NetWeightInItemDetailsPge.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt")));		
			 MakingAmtItemDetailPge.add(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetmakkingamt")));
			 ExpectedURDItemNameIntItemDetailsPge.add(URDItemName.get(i).substring(URDItemName.get(i).indexOf("-") + 2));
			  ExpectedURDSubtotal = URDTotalAmountInItemDetailsPge.stream()
					    .mapToDouble(value -> Double.parseDouble(value))  
					    .sum();
			  RequiredURDNetWeight = NetWeightInItemDetailsPge.stream()
					    .mapToDouble(value -> Double.parseDouble(value))  
					    .sum();
			  RequiredURDGrossWeight = ExpectedURDGrossWeightInItemDetailsPge.stream()
					    .mapToDouble(value -> Double.parseDouble(value))  
					    .sum();
			  
			  if(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_URD.get(i)!="URDT") {
				  if(PurityGold.isEmpty()) {				  
			   NetURDRgWeight += Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt")));			   
			  }
				  else {
					double URD18Weight =  Double.parseDouble(URDActualAmnt.get(i))/Double.parseDouble(UtilityTestData.URD_ReceiptNumberGoldRateForUncut_Precia);
					NetURDRgWeight = NetURDRgWeight+URD18Weight;
				  }
			  }
			double ExpAmount = Double.parseDouble(NetWeightInItemDetailsPge.get(i)) * Double.parseDouble(RateItemdetailPge.get(i));
			double ExpTotalAmount = Double.parseDouble(URDActualAmnt.get(i)) + Double.parseDouble(MakingAmtItemDetailPge.get(i)) + Double.parseDouble(PreciaStoneAmount.get(i))+Double.parseDouble(URDStoneAmnt.get(i));
			
			Thread.sleep(3000);
			asrt.assertEquals(Double.parseDouble(URDActualAmnt.get(i)), ExpAmount,1.0,"Amount mismatch: "+ExpAmount+" but got "+URDActualAmnt+" in URD item details page");
			asrt.assertEquals(Double.parseDouble(URDActualAmnt.get(i)), ExpAmount,1.0,"Amount mismatch: "+ExpAmount+" but got "+URDActualAmnt.get(i)+" in URD item details page");
			 
			
			if(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_URD.get(i)!="URDT") {
				if(PurityGold.isEmpty()) {
									
			asrt.assertEquals(Double.parseDouble(RateItemdetailPge.get(i)),Double.parseDouble(UtilityTestData.URD_ReceiptNumberGoldRateForUncut_Precia),1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberGoldRateForUncut_Precia+" but got "+RateItemdetailPge.get(i)+" in URD item details page");
			
				}else {
					
					Double GoldRate18= (Double.parseDouble(UtilityTestData.URD_ReceiptNumberGoldRateForUncut_Precia)*Double.parseDouble(PurityGold.replaceAll("[^0-9]", ""))/Double.parseDouble(UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_PurityConfig.getFirst().replaceAll("[^0-9]", "")));
					asrt.assertEquals(Double.parseDouble(RateItemdetailPge.get(i)),GoldRate18,1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberGoldRateForUncut_Precia+" but got "+RateItemdetailPge.get(i)+" in URD item details page");
				}
				
			}else {
				
				asrt.assertEquals(Double.parseDouble(RateItemdetailPge.get(i)),Double.parseDouble(UtilityTestData.URD_ReceiptNumberGoldRateForUncut_Precia_PlatinumRate),1.0,"Rate mismatch: "+UtilityTestData.URD_ReceiptNumberGoldRate+" but got "+RateItemdetailPge.get(i)+" in URD item details page");
			}
			
			appUtils.ItemDetailsTableValues(UrdSkuNumber,ScannedSkuDataMapUrd);
			UrdSkuNumber++;
			
			// Click on Add to cart button
			base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
			base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
			if(base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"),40)) {
				
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			
			}
			
			//URD Billing page validation
			List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
			int ExpectedLinesCountURD       = AllProductRows.size();
			 ActualLinesCountURD         = Integer.parseInt(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4")));
			String ActualItemNameURD = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText")).get(i);
			 ActualGrossWeightURD     = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5")).get(i).replace("-", ""); 
			Thread.sleep(3000);
			 ActualTotalWithoutTaxURD = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).get(i).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
			String ActualSubTotalURD        = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
			String ActualTaxURD             = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
			 ActualTotalAmountURD     = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
			

			asrt.assertEquals(ExpectedLinesCountURD, ActualLinesCountURD,"Lines count mismatch: Expected is "+ExpectedLinesCountURD+" but got "+ActualLinesCountURD+" in Billing page");
			asrt.assertEquals(ActualItemNameURD, ExpectedURDItemNameIntItemDetailsPge.get(i),"Item name mismatch: "+ExpectedURDItemNameIntItemDetailsPge.get(i)+" but found "+ActualItemNameURD+" in billing page");
			asrt.assertEquals( Double.parseDouble(ActualGrossWeightURD), Double.parseDouble(ExpectedURDGrossWeightInItemDetailsPge.get(i)), 0.01, "Mismatch in GrossWeightt: Expected " + ExpectedURDGrossWeightInItemDetailsPge.get(i) + " but got " + ActualGrossWeightURD + " from billing page");
			asrt.assertEquals( Double.parseDouble(ActualTotalWithoutTaxURD), Double.parseDouble(URDTotalAmountInItemDetailsPge.get(i)), 0.01, "Mismatch in Total without tax: Expected " + URDTotalAmountInItemDetailsPge.get(i) + " but got " + ActualTotalWithoutTaxURD + " from billing page");
			asrt.assertEquals( Double.parseDouble(ActualSubTotalURD), ExpectedURDSubtotal, 1.00, "Mismatch in value Subtotal: Expected " + URDTotalAmountInItemDetailsPge.get(i) + " but got " + ActualSubTotalURD + " from billing page");
			asrt.assertEquals( Double.parseDouble(ActualTotalAmountURD), ExpectedURDSubtotal, 1.00, "Mismatch in Total amount: Expected " + URDTotalAmountInItemDetailsPge.get(i) + " but got " + ActualTotalAmountURD + " from billing page");
            
			}
	
			//Step 45: Scan SKU
			//Check calculation, Note:Billing Screen *No of Product lines*Displayed Item Name*Displayed Quantity
			// *Displayed TOTAL (without Tax),*Displayed Subtotal, *TAX *Total Amount
			List<String> ProductNames     = new ArrayList<>();
			List<String> ProductQtyList   = new ArrayList<>();
			List<String> ProductTotalList = new ArrayList<>();
			Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
			
			//If the weight of items in the created receipt in the prerequisite is large use two categories
			double RequiredURDWeight = RequiredURDGrossWeight;   //double RequiredURDWeight = NetURDRgWeight/2;
//			SKUResult SkuGoldSale = appUtils.GetOGSaleSku(
//					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_TranferType,
//					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_FromCounterGold,
//					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_MetalTypeGold,
//					RequiredURDWeight,
//					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_SKUCount
//					);
			//	appUtils.HamBurgerButtonClick("iconShop");
			
			Thread.sleep(5000);			
			SKUResult SkuUncutSale = appUtils.GetOGSaleSku(
					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_TranferType,
					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_FromCounterUncut,
					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_MetalTypeGold,
					RequiredURDWeight,
					UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_SKUCount
					);
			
			System.out.println("-----------Currenet Board Rate--------\n"+CurrentBoardRate);
			appUtils.HamBurgerButtonClick("iconShop");	
			erpUtils.ResetRemainingReturnWeight();
			List<String> SkuList=SkuUncutSale.PcsRateSKU;
			//SkuList.addAll(SkuUncutSale.PcsRateSKU);
			for (int i = 0; i < SkuList.size(); i++) {
				int SkuNumber = i + 1;
				String Sku    = SkuList.get(i);
				appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
				ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
				ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
				ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
				Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);				
				erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber, Double.toString(NetURDRgWeight), UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_Purity, UtilityTestData.URD_ReceiptNumberGoldRateForUncut_Precia, Double.toString(CurrentBoardRate));
			}
	
			Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
			String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
			String Discount                         = TransactionDataLine.get("Discount");
			String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
			String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
			String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
			String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
			String NetTotalBillScrn                 = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
			String LinesCount                       = TransactionDataLine.get("LinesCount");

			Map<String, String> AdjustedScannedSkuMapUrd = erpUtils.MergeScannedSkuDataMaps(ScannedSkuDataMapUrd, ScannedSkuDataMap);
			System.out.println("AdjustedScannedSkuMapUrd: " + AdjustedScannedSkuMapUrd.toString());
			List<String> ActualNames=erpUtils.ErpValidateTransactionLineCalculationSubtract(TransactionDataLine, AdjustedScannedSkuMapUrd,"1","1",true);
			String SKUName                          = ActualNames.toString().replace("[", "").replace("]", "").trim();
			List<String> TotalWithoutTaxElements    = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String TotalwithoutTax                  = TotalWithoutTaxElements.get(1).replaceAll("[^\\d.]", "").trim();
			double ExpectedSubTotal                 = Double.parseDouble(TotalwithoutTax) - Double.parseDouble(ActualTotalWithoutTaxURD);
			double ExpectedTotalAmount              = ExpectedSubTotal + Double.parseDouble(Tax);
            double SumofTotalWithoutTax = TotalWithoutTaxElements.stream().map(s -> s.replaceAll("[^\\d.]", "").trim()).mapToDouble(Double::parseDouble).sum();

			//Step 46: save to estimate
			//Expected:Verify the Invoice details
            List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
			String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
			String TaxableValue =  Double.toString(SumofTotalWithoutTax-ExpectedURDSubtotal);
			String TotalAmnt           = Double.toString(ExpectedSubTotal);
			String Gst                 =  Tax;
			String InvoiceTotal        =  NetTotalBillScrn;
			String NetTotal            =  NetTotalBillScrn;
			Thread.sleep(5000);
			String ProformaPdfName     = pdfUtils.DownloadAndRenameLatestPDF("TC206_ProformaInvoice");
			String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
			pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
					ProFormaInvoicePath,
					TaxableValue,
					Gst,
					InvoiceTotal,
					NetTotal,
					TotalAmnt
					);
             
			//Step 47: Recall estimate from cashier
			//Expected : Verify whether the recalled item is the same as the item in the cart.
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
			
			String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
			List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
			asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 
	        asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not same as the item in the Cart");

			//Step 48: Select transaction type as Exchange
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_TransactionTypeExchange);

			//Step 49: Click on Process Estimation
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

			//Step 50: Choose a sales representative[Step not required due new application change]
			//Step 51:  Click on amount 	
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(3000);
			base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

			//Step 52:Post the exchange invoice
			//Expected : Check Exchange invoice details
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			Map<String, String> PurchaseBill = pdfUtils.URDPurchaseBill("Adjustment");						
			String TotalAmountFromInvoice    =  PurchaseBill.get("TotalAmount");
			String ExchangeFromInvoice       = PurchaseBill.get("PaymentDetails");
			String QtyFromInvoice            = PurchaseBill.get("QtyPcs");
			String GrossWeightFromInvoice    = PurchaseBill.get("TotalGrossWt");			
			String NetWeightFromInvoice      = PurchaseBill.get("TotalNetWt");

			asrt.assertEquals(Double.parseDouble(ExchangeFromInvoice), Double.parseDouble(ActualTotalAmountURD), 1.00, "Mismatch in exchange value URD Purchase bill adjustment:"+ExchangeFromInvoice+"but got" +ActualTotalAmountURD+"rom billing page");
			asrt.assertEquals(Double.parseDouble(GrossWeightFromInvoice), RequiredURDGrossWeight,"Mismatch in GrossWt value URD Purchase bill adjustment:"+GrossWeightFromInvoice+"but got" +RequiredURDGrossWeight+"rom billing page");
			asrt.assertEquals(Double.parseDouble(NetWeightFromInvoice), RequiredURDNetWeight,"Mismatch in NetWt value URD Purchase bill adjustment:"+NetWeightFromInvoice+"but got" +RequiredURDNetWeight+"rom billing page");	
			asrt.assertEquals(Double.parseDouble(TotalAmountFromInvoice), Double.parseDouble(ActualTotalAmountURD),1.00,"Mismatch in TotatAmt value URD Purchase bill adjustment:"+Double.parseDouble(TotalAmountFromInvoice)+"but got" +ActualTotalAmountURD+"rom billing page");
			asrt.assertEquals(Integer.parseInt(QtyFromInvoice),ActualLinesCountURD,"Mismatch in Line count URD Purchase bill adjustment: " + QtyFromInvoice +" but got " + ActualLinesCountURD + " from billing page");

			//Step 53: Again recall estimate from cashier screen
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
            
			ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
			String ExpectedEstmnNumberSale = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
			asrt.assertEquals(EstmnNumber, ExpectedEstmnNumberSale,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumberSale+" in Recall Estimation page");
			asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not same as the item in the Cart");
			
			List<WebElement> SKUNumberFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "SKUNUMBER"));
			String ActualSKURaw    = SKUNumberFromRecall.get(0).getText().trim();
			String ExpectedSKURaw  = SkuList.get(0);
			String ActualSKUName   = ActualSKURaw.contains("-")? ActualSKURaw.substring(ActualSKURaw.lastIndexOf("-") + 1).trim(): ActualSKURaw.trim();
			String ExpectedSKUName = ExpectedSKURaw.contains("-")? ExpectedSKURaw.substring(ExpectedSKURaw.lastIndexOf("-") + 1).trim(): ExpectedSKURaw.trim();
			asrt.assertTrue(ExpectedSKURaw.trim().contains(ActualSKURaw), "SKU Number  mismatch: Expected " + ExpectedSKUName + " but got " + ActualSKUName + "in the Recall Page.");

			//Step 54: Select transaction type as sale
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData.TC206_TransactionTypesale);

			//Step 55: Click on process estimation button
			//Step 56: Choose a sales representative[sale rep not required]
			// Step 57:Click on the Amount
			//Expected : Note:Cashier screen *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));			
			Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
			String SubTotalInCashierScreen		 		   = TransactionDataLineInCashierScreen.get("Subtotal");
			String NetTotalInCashierScreen		 		   = TransactionDataLineInCashierScreen.get("NetTotal");
			String DiscountInCashierScreen 				   = TransactionDataLineInCashierScreen.get("Discount");
			String TotalNetWeightInCashierScreen   		   = TransactionDataLineInCashierScreen.get("NetWeight");
			String TotalGrossWeightInCashierScreen   	   = TransactionDataLineInCashierScreen.get("GrossWeight");
			String TaxInCashierScreen 					   = TransactionDataLineInCashierScreen.get("Tax");
			String TotalAmountInCashierScreen 			   = TransactionDataLineInCashierScreen.get("TotalAmount");
			String ActualLinesCountInCashierScreen		   = TransactionDataLineInCashierScreen.get("LinesCount");
			List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
			double SubTotalSumInCashierScreen = 0.0;
			
			String ExpectedLinesCountInCashierScreen       = Integer.toString(AllProductRowsInCashierScreen.size());
			asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in CashierScreen");
			
			for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++)
			{
				
			List<WebElement> ItemNameInCashierScreen       = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen           = ItemNameInCashierScreen.get(i).getText();
			String ExpectedItemNameInCashierScreen         = ProductNames.get(i).trim().contains("-") ? ProductNames.get(i).substring(ProductNames.get(i).lastIndexOf("-") + 1).trim() : ProductNames.get(i).trim();
			asrt.assertTrue(ProductNames.get(i).trim().contains(ActualItemNameInCashierScreen), "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");			

			List<WebElement> QuantityInCashierScreen       = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen           = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen         = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInCashierScreen                = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen              = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in CashierScreen");

			List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInCashierScreen     = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			 SubTotalSumInCashierScreen += Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen   = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in CashierScreen");
		}
			String ExpectedSubTotalInCashierScreen          = String.format("%.2f",SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
			String ActualSubTotalInCashierScreen            = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
			asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in CashierScreen");

			String ActualTaxInCashierScreen                 = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
			double ExpectedTaxValueInCashierScreen          = (ProductTotalList.stream().map(String::trim).mapToDouble(Double::parseDouble).sum()) * 0.03;
			String ExpectedTaxInCashierScreen               = String.format("%.2f",ExpectedTaxValueInCashierScreen);
			asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreen+" but found "+ActualTaxInCashierScreen+" in CashierScreen");

			double ExpectedTotalAmountInCashierScreen       = Double.parseDouble(ActualSubTotalInCashierScreen)+Double.parseDouble(ActualTaxInCashierScreen);
			double ActualTotalAmountInCashierScreen         = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
			asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");

			String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

			//Step 58 : Post the sale invoice
			//Check the final invoice details-Sale
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC206_FinalSaleInvoice");
			Thread.sleep(3000);		
			String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
			Thread.sleep(3000);
			String GrossAmount         = TaxableValue;
			String TotalDiscount       = Discount;
			String TotalQtyPcs         = String.valueOf(SkuList.size());
			String Adjustment          = ActualTotalAmountURD;
			String PaymentHDFCCard     = PaymentAmount;
			String SalesPdfPath        = SaleInvoicePath;
			String TotalNetWeightPdf   = TotalNetWeight;
			String TotalGrossWeightPdf = TotalGrossWeightInCashierScreen;
			double CalcTotalAmount     = Double.parseDouble(TaxableValue)+Double.parseDouble(Tax);
			String NetValue            = String.format("%.2f", CalcTotalAmount);
			NetValue                   = NetValue.replaceAll("^0+(?!$)", "").trim();
			Thread.sleep(3000);
			String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
					SalesPdfPath,
					GrossAmount,
					TotalDiscount,
					"",
					TotalQtyPcs,
					TotalNetWeightPdf,
					TotalGrossWeightPdf,
					TaxableValue,
					NetValue,          
					PaymentHDFCCard,
					Adjustment,
					SkuList,
					ScannedSkuDataMap
					);
			pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	 				
		}	
}
		
