package testPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.GppOpeningAndCollectionMultiplePlans_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.CustomerCreation_TestData;
import testData.GppOpeningAndCollectionMultiplePlans_TestData;
import testData.POC_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.ErpUtilities.AdvanceCustomerDetails;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities.RequiredSKUResult;
import utilPack.AppUtilities.ReturnDetails;
import utilPack.AppUtilities.SKUResult;

public class RtgsPaymentToCustomer extends BasePge {

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
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	RtgsPaymentToCustomer_TestData RtgsPaymentToCustomerTestData = new RtgsPaymentToCustomer_TestData();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldandSilverObj = new NormalSaleGoldandSilver_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRTGSReceivedObj =new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();

	public RtgsPaymentToCustomer(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
	}

	//<summary>
	// Test Case Title : OG Exchange add to estimation
	// Automation ID : TC172
	// Author : Benson 
	// </summary>
	public void TC172_RtgsPaymentToCustomer() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer and  click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC172_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 4:Scan a SKU and click add to cart button
		//Expected Result: Check calculation
		SKUResult SkuOGSale = appUtils.GetOGSaleSku(RtgsPaymentToCustomerTestData.TC172_TransferType,
				RtgsPaymentToCustomerTestData.TC172_FromCounter,
				RtgsPaymentToCustomerTestData.TC172_MetalType,
				RtgsPaymentToCustomerTestData.TC172_SaleQty,
				RtgsPaymentToCustomerTestData.TC172_SkuCount);
		List<String> SkuList = SkuOGSale.PcsRateSKU;
		System.out.println("SkuList"+SkuList);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("width48 height48 buttonIcon center iconShop"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Map<String,String> ScannedSkuDataMap = new HashMap();

		appUtils.SKUIngridentTableValues(SkuList.get(0),
				RtgsPaymentToCustomerTestData.TC172_SkuCount,ScannedSkuDataMap);
		String ExpectedGrossValue = ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC172_SkuCount + "_GrossWeight");
		String ExpectedItemName = ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC172_SkuCount + "_ItemName");
		String TotalForEachItem= ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC172_SkuCount + "_Total");
		String RateKey = "SKU" + RtgsPaymentToCustomerTestData.TC172_SkuCount  + "_ITEM" + RtgsPaymentToCustomerTestData.TC172_SkuCount  + "_RATE";
		String BoardRateFromSkuTble = ScannedSkuDataMap.get(RateKey);
		Double SkuBoardRate = Double.parseDouble(BoardRateFromSkuTble);
		String Purity = ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC172_SkuCount + "_Purity");
		double BoardRate22k=0;
		if (Purity.equalsIgnoreCase("18K"))
		{
			double ConvertedBoardRate22k = (SkuBoardRate * 22) / 18;
			BoardRate22k = Math.round(ConvertedBoardRate22k);
		}else if(Purity.equalsIgnoreCase("14k")){
			double ConvertedBoardRate22k = (SkuBoardRate * 22) / 14;
			BoardRate22k = Math.round(ConvertedBoardRate22k);
		}else if(Purity.equalsIgnoreCase("22k")){
			BoardRate22k = SkuBoardRate;

		}

		mathUtils.SkuIngredientItemCalculation(ScannedSkuDataMap, RtgsPaymentToCustomerTestData.TC172_SkuCount);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//Step 5: Select OG
		//Step 6:Click on OG own
		//Step 7: Select configuration(purity 22K)
		//Step 8: Click on Add item button
		//Choose Exchange
		//Step 9:Select QC person
		//Step 10:Select Smith person
		//Step 11:Enter piece value
		//Step 12:Enter gross weight
		//Step 13:click on calculate button
		//Expected Result:  Check Exchange rate(Board rate ) ,* Check calculation Note:Billing Screen , *No of Product lines
		//*Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax), *Displayed Subtotal, *TAX, *Total Amount
		appUtils.PurchaseOldGold(RtgsPaymentToCustomerTestData.TC172_Configuration, 
				RtgsPaymentToCustomerTestData.TC172_PurchaseOrExchange,
				UtilityTestData.QCAndSmithPerson,
				UtilityTestData.QCAndSmithPerson,
				RtgsPaymentToCustomerTestData.TC172_GrossPieces,
				RtgsPaymentToCustomerTestData.TC172_GrossWeight);
		String ExpectedGrossWeightInItemDetailsPge    = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));

		//* Check calculation on OG Item detail page
		Map<String, Double> OgDataMap = new LinkedHashMap<>();
		String OgQty = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column3 textRight", "h4 ellipsis cell"));
		String OgRate = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column4 textRight", "h4 ellipsis cell"));
		String OgCvalue = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column5 textRight", "h4 ellipsis cell"));

		Double OgQtyIngredientTable =  Double.parseDouble(OgQty);
		Double OgRateIngredientTable = Double.parseDouble(OgRate);
		Double OgCValueIngredientTable = Double.parseDouble(OgCvalue);

		Double OgCalculatedCvalue = OgQtyIngredientTable * OgRateIngredientTable;
		OgDataMap.put("OgQty", OgQtyIngredientTable);
		OgDataMap.put("OgCvalue",OgCValueIngredientTable);

		asrt.assertEquals(OgCalculatedCvalue, OgCValueIngredientTable,1, "Mismatch in Cvalue,  Expected " + OgCalculatedCvalue+" but got "+OgCValueIngredientTable+" in  Old Gold Item details page");

		Double OgNetRateValue = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		asrt.assertEquals(OgRateIngredientTable, OgNetRateValue,1,"Mismatch in net rate, Expected " + OgRateIngredientTable+" but got "+OgNetRateValue+" in  Old Gold Item details page");
		Double OgNetAmountValue = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")));
		asrt.assertEquals(OgCalculatedCvalue, OgNetAmountValue,1," Mismatch in Net amount, Expected " + OgCalculatedCvalue+" but got "+OgNetAmountValue+" in  Old Gold Item details page");
		Double TotalAmountValue =Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")));
		asrt.assertEquals(OgCalculatedCvalue, TotalAmountValue,1, "Mismatch in Total amount value , Expected " + OgCalculatedCvalue+" but got "+TotalAmountValue+" in 22k Old Gold Item details page");
		Thread.sleep(1000);

		//Step 14:Click on Add to cart button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("SimpleProductDetailsView_AppBarView_appBarCommandCommand","win-commandicon"));		
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		Thread.sleep(1000);

		// Verify billing screen data
		Map<String, String> BillingScrnTableData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);		
		String SubTotal = BillingScrnTableData.get("Subtotal");
		String TotalGrossWeight=BillingScrnTableData.get("GrossWeight");
		String TotalNetWeight=BillingScrnTableData.get("NetWeight");
		String Discount=BillingScrnTableData.get("Discount");
		String Tax = BillingScrnTableData.get("Tax");
		String TotalAmount = BillingScrnTableData.get("TotalAmount");
		String NetTotal = BillingScrnTableData.get("NetTotal");
		String LinesCount = BillingScrnTableData.get("LinesCount");

		int LinesCountBilling = Integer.parseInt(LinesCount);
		double SubTotalBilling = Double.parseDouble(SubTotal.trim().replaceAll("[^\\d.]", ""));
		double TaxBilling = Double.parseDouble(Tax.trim().replaceAll("[^\\d.]", ""));
		double TotalAmountBilling = Double.parseDouble(TotalAmount.trim().replaceAll("[^\\d.]", ""));

		// Validate product lines count
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCount = AllProductRows.size();	
		asrt.assertEquals(ExpectedLinesCount, LinesCountBilling,"Mismatch in Lines count, expected " + ExpectedLinesCount + " but got " + LinesCountBilling + " in the billing screen");

		// Validate item name
		List<WebElement> ItemNamesBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		String ActualItem = ItemNamesBilling.get(0).getText().trim();
		String ExpectedFullItemName = ExpectedItemName.trim();
		String ExpectedItemNameBillingScreen = ExpectedFullItemName.contains("-") ? ExpectedFullItemName.substring(ExpectedFullItemName.indexOf("-") + 2) : ExpectedFullItemName;
		asrt.assertEquals(ExpectedItemNameBillingScreen, ActualItem,"Mismatch in item name, expected " + ExpectedItemNameBillingScreen + " but got " + ActualItem + " in the billing screen.");

		// Convert ItemNamesBilling -> List<String> to avoid type mismatch before step23
		List<String> TextItemNamesBilling = ItemNamesBilling.stream().map(WebElement::getText).map(String::trim).toList();

		//Capture Item Quantities 
		//need to add assertions here ---
		List<WebElement> ItemQtyBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields","tillLayout-QuantityField textRight"));
		List<String> TextItemQtyBilling = ItemQtyBilling.stream().map(WebElement::getText).map(String::trim).toList();
		String OGQtyBillingWithoutDecimal=TextItemQtyBilling.get(1).replaceAll("[^\\d.]", "");
		String OGQtyBillingPge = String.format("%.3f", Double.parseDouble(OGQtyBillingWithoutDecimal));
		asrt.assertEquals(OGQtyBillingPge, OgQty,"Mismatch in Quantity value,expected" + OGQtyBillingPge +" but got " + OgQty + "in the Billing screen");

		// Validate total without tax
		List<WebElement> ItemRateBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		String SkuItemRate = ItemRateBilling.get(0).getText().trim().replaceAll("[^\\d.\\-]", "");
		double FirstItemRate = Double.parseDouble(SkuItemRate);

		// Subtotal should match SKU total
		double ExpectedSubTotal = FirstItemRate-OgCValueIngredientTable;
		asrt.assertEquals(ExpectedSubTotal, SubTotalBilling, 1.0,"Mismatch in Subtotal value, expected " + ExpectedSubTotal + " but got " + SubTotalBilling + " in the Billing screen");

		// Tax should be 3% of subtotal
		double ExpectedTaxAmnt = FirstItemRate * 3 / 100;
		asrt.assertEquals(ExpectedTaxAmnt, TaxBilling, 1.0,"Mismatch in Tax value, expected " + ExpectedTaxAmnt + " but got " + TaxBilling + " in the billing screen");

		// Validate Total Amount
		double ActualTotalAmount = ExpectedSubTotal + ExpectedTaxAmnt;
		asrt.assertEquals(TotalAmountBilling, ActualTotalAmount, 1.0,"Mismatch in Total amount value, expected " + ActualTotalAmount + " but got " + TotalAmountBilling + " in the Billing screen");

		//Step 15: Save to estimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));

		//Verify invoice details
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);

		// Download and verify invoice
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC172_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;

		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProformaInvoicePath,
				SkuItemRate,     // TaxableValue
				Tax,             // Gst
				TotalAmount,     // InvoiceTotal
				NetTotal,        // NetTotal
				SubTotal         // TotalAmnt
				);	

		//Step 16:Recall estimate from cashier
		//Expected Result:Verify whether the recalled item is the same as the item in the cart.
		//Note:Cashier Screen after recall, *No of Product lines, *Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax), *Displayed Subtotal, *TAX, *Total Amount
		/*
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//Select estimate and click on Recall estimation button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		appUtils.ValidateBillingAndRecallScreenData(EstmnNumber,LinesCount,TextItemNamesBilling,TextItemQtyBilling, SubTotal, Tax, NetTotal);
        */
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in estimation page");
        
		appUtils.ValidateBillingAndRecallScreenData(EstmnNumber,LinesCount,TextItemNamesBilling,TextItemQtyBilling, SubTotal, Tax, NetTotal);
		
		// Step 17: Select transaction type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC172_TransactionType1);

		//Step 18: Click on process estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 19. Choose a sales representative
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			}

		//Step 20:  Click on amount 
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		if (base.isElementPresent(driver,NormalSaleGoldAndSilverObj.Btn_Estimation("cancelButton primaryButton"))) 
		{
			try 
			{ 
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Estimation("cancelButton primaryButton"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			} 
			catch (Exception e) {}
		} 
		// Step 21:  Post exchange invoice
		// Expected Result: Check Exchange invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Map<String, String> PurchaseBillData =pdfUtils.OGPdfValidation("Adjustment");
		String ExchngeAdjustment             = String.format("%.2f", Double.parseDouble(PurchaseBillData.get("PaymentDetails").trim()));
		String GrossWeightFromInvoice        = PurchaseBillData.get("GrossWt1").split("\\.")[0];
		String NetWeightFromInvoice          = PurchaseBillData.get("NetWt1").split("\\.")[0];;
		String OGRateFromInvoice           = String.format("%.1f", Double.parseDouble(PurchaseBillData.get("Rate1").trim()));
		String TotalAmountFromInvoice        = String.format("%.1f", Double.parseDouble(PurchaseBillData.get("TotalAmount1").trim()));
		String PaymentDetailsFromInvoice     = String.format("%.1f", Double.parseDouble(PurchaseBillData.get("PaymentDetails").trim()));
		String OgtGrossWt                    = ExpectedGrossWeightInItemDetailsPge.replaceFirst("^0+(?!$)", "");	

		asrt.assertEquals(Double.parseDouble(ExchngeAdjustment), TotalAmountValue, 1, "Mismatch in value OGPdf adjustment:"+ExchngeAdjustment+"but got" +TotalAmountValue+"rom billing page");
		asrt.assertEquals(GrossWeightFromInvoice, OgtGrossWt,"Mismatch in value OGPdf adjustment:"+GrossWeightFromInvoice+"but got" +OgtGrossWt+"rom billing page");
		asrt.assertEquals(NetWeightFromInvoice, OgtGrossWt,"Mismatch in value OGPdf adjustment:"+NetWeightFromInvoice+"but got" +OgtGrossWt+"rom billing page");	
		asrt.assertEquals(Double.parseDouble(OGRateFromInvoice), OgNetRateValue,1,"Mismatch in value OGPdf adjustment:"+Double.parseDouble(ExchngeAdjustment)+"but got" +OgNetRateValue+"rom billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmountFromInvoice), TotalAmountValue,1,"Mismatch in value OGPdf adjustment:"+Double.parseDouble(TotalAmountFromInvoice)+"but got" +TotalAmountValue+"rom billing page");
		asrt.assertEquals(Double.parseDouble(PaymentDetailsFromInvoice), TotalAmountValue,1,"Mismatch in value OGPdf adjustment:"+Double.parseDouble(PaymentDetailsFromInvoice)+"but got" +TotalAmountValue+"rom billing page");


		//Step 22. Again recall estimate from cashier screen
		/*
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		*/
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Step 23: Select transaction type as sales
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),RtgsPaymentToCustomerTestData.TC172_TransactionTypeSales);

		//Step 24: Click on process estimation button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 25: Choose a sales representative
		//	base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")); 
		appUtils.HandleSpecifyRateModalInRecall();

		//Step 26. Click on amount
		//Step 27. Select payment method(cash/card)		
		String PaymentAmountHDFC = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 28. Post sale invoice
		//Expected Results :  Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Click on "PRINT"
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Download and rename the latest PDF
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC172_SaleInvoice");
		Thread.sleep(3000);

		// Build the full path for the saved invoice
		String SalesPdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;

		// Assign expected values for validation
		String GrossAmount     = SkuItemRate;
		String TotalDiscount   = Discount;  
		String TotalQtyPcs     = LinesCount;
		String Adjustment      = ExchngeAdjustment;
		String PaymentHDFCCard = PaymentAmountHDFC;
		TotalGrossWeight       = TotalNetWeight;
		String NetValue        = Double.toString(Double.parseDouble(PaymentAmountHDFC)+Double.parseDouble(Adjustment));


		// Perform PDF validation
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(   
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,   
				SkuItemRate, 
				NetValue,
				PaymentHDFCCard,
				Adjustment,
				SkuList,
				ScannedSkuDataMap
				);

		// Print invoice number in console
		System.out.println("PDF INVOICE NO: " + PdfInvoiceNo);

		// Close the invoice window
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));

		// Cleanup PDF files
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	//<summary>
	// Test Case Title : Sales return balance amount rtgs
	// Automation ID : TC178
	// Author : Gokul.P 
	// </summary>
	public void TC178_RtgsPaymentToCustomer() throws Exception
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		//PreRequisite : Take the bill number (receipt number) that has previously been sold
		//Step 1 : Login to POS
		//Step 2 : Click on Transaction button
		String InvoiceNo          = appUtils.GetInvoiceNoAfterNormalSale(RtgsPaymentToCustomerTestData.TC178_CustomerID, RtgsPaymentToCustomerTestData.CounterTransfer);
		String ActualCustomerName = pdfUtils.ExtractCustomerNameFromSaleInvoice();

		//Step 3 : Select the customer
		//Step 4 : Click on add to estimation button
		appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC178_CustomerID,UtilityTestData.MenuBarOptions[5]);

		//Step 5 : Click on customer adjustment button
		//Step 6 : Click on return transaction button at the billing screen
		//Step 7 : Enter the Receipt Number
		//Step 8 : Select return product and  Click on return button
		//Expected Result : Check calculation : No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total amount
		ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, RtgsPaymentToCustomerTestData.TC178_ReturnProducts);

		//Validation Steps : Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax)
		List<WebElement> BillScrnProdName  = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		List<WebElement> BillScrnProdQty = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-QuantityField textRight"));
		List<WebElement> BillScrnProdPrice = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));

		List<String> ReturnProdNames        = Details.productNames;
		List<String> ReturnProdQty      = Details.productQty;
		List<String> ReturnProdPrice    = Details.productPrice;

		for (int i = 0; i < BillScrnProdName.size(); i++)
		{
			String BillScreenProdName = BillScrnProdName.get(i).getText();
			String BillScreenProQty = BillScrnProdQty.get(i).getText().replace("-", "").trim();
			String BillScreenProdPrice = BillScrnProdPrice.get(i).getText().replace("-", "").trim();
			String ExpectedReturnProdNames       = ReturnProdNames.get(i).trim();

			double ExpectedQty        = Double.parseDouble(ReturnProdQty.get(i).trim().replace("-", "").replaceAll("[^\\d.]", ""));
			double ExpectedProdPrice = Double.parseDouble(ReturnProdPrice.get(i).trim().replace("-", "").replaceAll("[^\\d.]", ""));			
			double ActualQty = Double.parseDouble(BillScreenProQty);
			double ActualPrice = Double.parseDouble(BillScreenProdPrice.replace("Ã¢â€šÂ¹", "").replace("\u20B9", "").replace(",", "").trim());

			asrt.assertEquals(ExpectedReturnProdNames, BillScreenProdName,"Item Name does not match between the return and billing screen");
			asrt.assertEquals(ExpectedQty, ActualQty,1, "Quantity does not match between the return and billing screen");
			asrt.assertEquals(ExpectedProdPrice, ActualPrice,1 ,"Price does not match between the return and billing screen");
		}

		//Validation Steps : No of Product lines, Displayed SubTotal, TAX, Total amount
		List<WebElement> ProductCount = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		List<WebElement> TotalsWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		double TotalWithoutTax = 0.0;
		for (WebElement TotalsWithoutTaxElement : TotalsWithoutTax)
		{
			String TotalWithoutTaxText = TotalsWithoutTaxElement.getText().trim().replaceAll("[^\\d.]", "");
			if (!TotalWithoutTaxText.isEmpty())
			{
				TotalWithoutTax = TotalWithoutTax + Double.parseDouble(TotalWithoutTaxText);
			}
		}
		double TaxAmount = TotalWithoutTax*3/100;
		int ActualNoOfLines = ProductCount.size();     		
		String SubTotalText = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SubTot("SubtotalField","row pad12 padBottom0","h4"));
		String TaxText = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SubTot("TaxField","row pad12 padBottom0","h4"));
		String TotalAmountText = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SubTot("TotalAmountField","row pad12 padBottom0","h4"));
		String NoOfLinesText = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SubTot("LinesField","row pad12 padBottom0","h4"));

		double SubTotal = Double.parseDouble(SubTotalText.replaceAll("[^\\d.]", "").trim());
		double Tax = Double.parseDouble(TaxText.replaceAll("[^\\d.]", "").trim());
		double ExpectedTotalAmount = SubTotal + Tax;
		double TotalAmount = Double.parseDouble(TotalAmountText.replaceAll("[^\\d.]", "").trim());
		int NoOfLines = Integer.parseInt(NoOfLinesText.replaceAll("[^\\d]", "").trim());

		asrt.assertEquals(NoOfLines, ActualNoOfLines, "Mismatch in number of product lines"+NoOfLines+" and "+ActualNoOfLines+" in Billing screen");
		asrt.assertEquals(SubTotal, TotalWithoutTax,1,  "Mismatch of subtotal"+SubTotal+" and "+TotalWithoutTax+ " in Billing screen");
		asrt.assertEquals(Tax, TaxAmount,1, "Mismatch of Tax "+Tax+" and "+TaxAmount+" in Billing screen");
		asrt.assertEquals(TotalAmount, ExpectedTotalAmount,1,  "Mismatch of TotalAmount "+TotalAmount+" and "+ExpectedTotalAmount+" in Billing screen");

		//9.Scan SKU
		// Expected:
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		// Gold SKUs
		List<String> GoldSkus = appUtils.SelectMultipleSKUs(
				RtgsPaymentToCustomerTestData.TC178_SkuCount,
				RtgsPaymentToCustomerTestData.TC178_TransferType,
				RtgsPaymentToCustomerTestData.TC178_ToCounter,
				RtgsPaymentToCustomerTestData.TC178_MetalType);
		Thread.sleep(1000);
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> TotalForEachItem = new ArrayList<>();
		TotalForEachItem.add(String.valueOf(TotalWithoutTax));
		Map<String, String> ItemData = new HashMap<>();
		for (int i = 0; i < GoldSkus.size(); i++)
		{  
			Thread.sleep(2000);
			appUtils.SKUIngridentTableValues(GoldSkus.get(i), i + 1, ItemData);
			ReturnProdNames.add(ItemData.get("SKU_" + (i+1) + "_HeaderName"));
			ReturnProdQty.add(ItemData.get("SKU_" + (i+1) + "_GrossWeight"));
			TotalForEachItem.add(ItemData.get("SKU_" + (i+1) + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ItemData, i+1);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, i+1);
		}

		Map<String, String> Results = appUtils.TransactionSKUsLinesVerify(GoldSkus, ItemData);
		String SubTotalInBillingScreen = Results.get("Subtotal");
		String Discount = Results.get("Discount");
		String TotalGrossWeight = Results.get("GrossWeight");
		String TotalNetWeightInBillingPge = Results.get("NetWeight");
		String TaxInBillingPge = Results.get("Tax");
		String InvoiceTotalInBillingPge = Results.get("TotalAmount");
		String NetTotal = Results.get("NetTotal");
		String LinesCount = Results.get("LinesCount");
		int ExpectedLinesCount = Integer.parseInt(LinesCount);
		double ExpectedSubTotal = Double.parseDouble(SubTotalInBillingScreen.trim().replaceAll("[^\\d.-]", ""));
		double ExpectedTax = Double.parseDouble(TaxInBillingPge.trim().replaceAll("[^\\d.]", ""));
		double ExpectedTotalAmountBillingPge = Double.parseDouble(InvoiceTotalInBillingPge.trim().replaceAll("[^\\d.-]", ""));
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ActualLinesCount = AllProductRows.size();
		List<WebElement> TotalsWithoutTaxBillingPage = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		double ActualSubTotal = 0.0;
		for (WebElement SubTotalElement : TotalsWithoutTaxBillingPage)
		{
			String SubTotalTextBillingPge = SubTotalElement.getText().trim().replaceAll("[^\\d.-]", "");
			if (!SubTotalTextBillingPge.isEmpty())
			{
				ActualSubTotal=ActualSubTotal + Double.parseDouble(SubTotalTextBillingPge);
			}
		}
		double ActualTaxAmount = Math.round((ActualSubTotal*3/100)*100.0)/100.0;
		String ActualAmountInBillingScrn=String.valueOf(ActualTaxAmount).trim().replaceAll("[^\\d.]", "");
		double ActualTotalAmount = ActualSubTotal+ActualTaxAmount;

		//To validate Line count, Item name, Quantity, Sub total, tax, Total amount in Billing page
		asrt.assertEquals(ExpectedLinesCount, ActualLinesCount,"Line Count mismatch: Expected is  "+ExpectedLinesCount+"but got "+ActualLinesCount+" in Billing page");
		List<WebElement> ItemNamesFromBillingPge = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		int i=0;
		String ExpectedItemName= " ";
		for (String ItemName : ReturnProdNames) {
			if(ItemName.contains("-"))
			{
				ExpectedItemName= ItemName.substring(ItemName.indexOf("-") + 2).trim();
			}else {ExpectedItemName=ItemName.trim();}

			String ItemNameFromBillingPage = ItemNamesFromBillingPge.get(i).getText().trim();

			asrt.assertTrue(ExpectedItemName.contains(ItemNameFromBillingPage),"Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ItemNameFromBillingPage + " in billing page");
			i++;
		}

		List<WebElement> BillScreenPageProductQty = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-QuantityField textRight"));
		for(int quantity = 0; quantity < BillScreenPageProductQty.size(); quantity++) {

			String BillingScrnQuantity = BillScreenPageProductQty.get(quantity).getText();
			double ExpectedQuantity    = Double.parseDouble(BillingScrnQuantity.trim().replace("-", "").replaceAll("[^\\d.]", ""));

			String ItemDetailsQuantity = ReturnProdQty.get(quantity);		   
			double ActualQuantity =  Double.parseDouble(ItemDetailsQuantity.trim().replace("-", "").replaceAll("[^\\d.]", ""));

			asrt.assertEquals(ActualQuantity, ExpectedQuantity,1, "Quantity Mismatch: Expected " + ExpectedQuantity + " but got " + ActualQuantity + " in Billing page");
		}
		for (int j =0; j<TotalsWithoutTax.size();j++) {
			double ActualTotal = Double.parseDouble(TotalForEachItem.get(j));
			double ExpectedTotal = Double.parseDouble(TotalsWithoutTaxBillingPage.get(j).getText().trim().replaceAll("[^\\d.]", ""));
			asrt.assertEquals(ExpectedTotal, ActualTotal,"Total without tax mismatch: Expected:  "+ExpectedTotal+"but got "+ActualTotal+" in Billing page");
		}
		asrt.assertEquals(ExpectedSubTotal,ActualSubTotal, 1.0,"Subtotal mismatch : Expected "+ExpectedSubTotal+"but got "+ActualSubTotal+" in Billing page");
		asrt.assertEquals(ExpectedTax,Double.parseDouble(ActualAmountInBillingScrn),1.0,"Gst amount mismatch: Expected "+ExpectedTax+"but got "+ActualTaxAmount+" in Billing page");
		asrt.assertEquals(ExpectedTotalAmountBillingPge,ActualTotalAmount, 1.0,"Total Amount mismatch: Expected "+ExpectedTotalAmountBillingPge+"but got "+ActualTotalAmount+" in Billing page");

		// Step 10: Click on Save to estimation button		
		// Expected Result:Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// PDF Verification Step
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC178_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = TaxInBillingPge;
		String TaxableValue		   = SubTotalInBillingScreen;
		String InvoiceTotal        = InvoiceTotalInBillingPge;
		String TotalAmnt           = SubTotalInBillingScreen;
		//double TotalNetWeight      = Double.parseDouble(TotalNetWeightInBillingPge) - Double.parseDouble(TotalNetWeightReturn);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				/*Double.parseDouble(TotalGrossWeight),
						TotalNetWeight*/);

		//11. Recall estimate from cashier screen
		//12 Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		//13.Select the Transaction Type as Sales return
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC178_TransationType);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//14.Click on Process Estimation 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//15.Choose a sales representative

		try{if(wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) != null)
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));			
		}}catch(Exception e) {System.out.println("Sales Representative not found");}
		// 16.Click on the Amount
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// 17. Verify Payment method(RTGS/NEFT) not displayed
		//Expected: Verify Payment method(RTGS/NEFT) not displayed
		asrt.assertFalse(base.isExists(LoginPageObj.Edt_AlertText("RTGS/NEFT")),"The Payment method(RTGS/NEFT) is displayed to the user");

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
	//<summary>
	// Test Case Title : Sales with OG balance amount Rtgs
	// Automation ID : TC174
	// Author : Aiswarya
	// </summary>
	public void TC174_RtgsPaymentToCustomer() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer   
		//Step 4: Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC174_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan a SKU 
		//Expected Result: Check calculation
		SKUResult SkuOGSale = appUtils.GetOGSaleSku(RtgsPaymentToCustomerTestData.TC174_TransferType,
				RtgsPaymentToCustomerTestData.TC174_FromCounter,
				RtgsPaymentToCustomerTestData.TC174_MetalType,
				RtgsPaymentToCustomerTestData.TC174_SaleQty,
				RtgsPaymentToCustomerTestData.TC174_SkuCount);
		List<String> SkuList = SkuOGSale.PcsRateSKU;
		System.out.println("SkuList"+SkuList);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("width48 height48 buttonIcon center iconShop"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Map<String,String> ScannedSkuDataMap = new HashMap();

		appUtils.SKUIngridentTableValues(SkuList.get(0),
				RtgsPaymentToCustomerTestData.TC174_SkuCount,ScannedSkuDataMap);
		String ExpectedGrossValue = ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC174_SkuCount + "_GrossWeight");
		String ExpectedItemName = ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC174_SkuCount + "_ItemName");
		String TotalForEachItem= ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC174_SkuCount + "_Total");
		String RateKey = "SKU" + RtgsPaymentToCustomerTestData.TC174_SkuCount  + "_ITEM" + RtgsPaymentToCustomerTestData.TC174_SkuCount  + "_RATE";
		String BoardRateFromSkuTble = ScannedSkuDataMap.get(RateKey);
		Double SkuBoardRate = Double.parseDouble(BoardRateFromSkuTble);
		String Purity = ScannedSkuDataMap.get("SKU_" + RtgsPaymentToCustomerTestData.TC174_SkuCount + "_Purity");
		double BoardRate22k=0;
		if (Purity.equalsIgnoreCase("18k"))
		{
			double ConvertedBoardRate22k = (SkuBoardRate * 22) / 18;
			BoardRate22k = Math.round(ConvertedBoardRate22k);
		}else if(Purity.equalsIgnoreCase("14k")){
			double ConvertedBoardRate22k = (SkuBoardRate * 22) / 14;
			BoardRate22k = Math.round(ConvertedBoardRate22k);
		}else if(Purity.equalsIgnoreCase("22k")){
			BoardRate22k = SkuBoardRate;

		}

		mathUtils.SkuIngredientItemCalculation(ScannedSkuDataMap, RtgsPaymentToCustomerTestData.TC174_SkuCount);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//Step 6: Search OG
		//Step 7: Click on OG own and select the purity
		//Step 8: Select configuration
		//Step 9: Click on Add item button
		//Step 10:Select QC person
		//Step 11:Select Smith person
		//Step 12:Enter piece value
		//Step 13:Enter gross weight
		//Step 14:click on calculate button
		//Expected Result:  Check Exchange rate(Board rate ) ,* Check calculation Note:Billing Screen , *No of Product lines
		//*Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax), *Displayed Subtotal, *TAX, *Total Amount
		appUtils.PurchaseOldGold(RtgsPaymentToCustomerTestData.TC174_Configuration, 
				RtgsPaymentToCustomerTestData.TC174_PurchaseOrExchange,
				UtilityTestData.QCAndSmithPerson,
				UtilityTestData.QCAndSmithPerson,
				RtgsPaymentToCustomerTestData.TC174_GrossPieces,
				RtgsPaymentToCustomerTestData.TC174_GrossWeight);

		//* Check calculation on OG Item detail page
		Map<String, Double> OgDataMap = new LinkedHashMap<>();
		String OgQty = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column3 textRight", "h4 ellipsis cell"));
		String OgRate = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column4 textRight", "h4 ellipsis cell"));
		String OgCvalue = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column5 textRight", "h4 ellipsis cell"));

		Double OgQtyIngredientTable =  Double.parseDouble(OgQty);
		Double OgRateIngredientTable = Double.parseDouble(OgRate);
		Double OgCValueIngredientTable = Double.parseDouble(OgCvalue);

		Double OgCalculatedCvalue = OgQtyIngredientTable * OgRateIngredientTable;
		OgDataMap.put("OgQty", OgQtyIngredientTable);
		OgDataMap.put("OgCvalue",OgCValueIngredientTable);

		asrt.assertEquals(OgCalculatedCvalue, OgCValueIngredientTable,1, "Mismatch in Cvalue,  Expected " + OgCalculatedCvalue+" but got "+OgCValueIngredientTable+" in  Old Gold Item details page");

		Double OgNetRateValue = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		asrt.assertEquals(OgRateIngredientTable, OgNetRateValue,1,"Mismatch in net rate, Expected " + OgRateIngredientTable+" but got "+OgNetRateValue+" in  Old Gold Item details page");
		Double OgNetAmountValue = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")));
		asrt.assertEquals(OgCalculatedCvalue, OgNetAmountValue,1," Mismatch in Net amount, Expected " + OgCalculatedCvalue+" but got "+OgNetAmountValue+" in  Old Gold Item details page");
		Double TotalAmountValue =Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")));
		asrt.assertEquals(OgCalculatedCvalue, TotalAmountValue,1, "Mismatch in Total amount value , Expected " + OgCalculatedCvalue+" but got "+TotalAmountValue+" in 22k Old Gold Item details page");
		Thread.sleep(1000);

		//Step 15: Click on Add to cart button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("SimpleProductDetailsView_AppBarView_appBarCommandCommand","win-commandicon"));		
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		Thread.sleep(1000);

		// Verify billing screen data
		Map<String, String> BillingScrnTableData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);		
		String SubTotal = BillingScrnTableData.get("Subtotal");
		String TotalGrossWeight=BillingScrnTableData.get("GrossWeight");
		String TotalNetWeight=BillingScrnTableData.get("NetWeight");
		String Discount=BillingScrnTableData.get("Discount");
		String Tax = BillingScrnTableData.get("Tax");
		String TotalAmount = BillingScrnTableData.get("TotalAmount");
		String NetTotal = BillingScrnTableData.get("NetTotal");
		String LinesCount = BillingScrnTableData.get("LinesCount");

		int LinesCountBilling = Integer.parseInt(LinesCount);
		double SubTotalBilling = Double.parseDouble(SubTotal.trim().replaceAll("[^\\d.\\-]", ""));
		double TaxBilling = Double.parseDouble(Tax.trim().replaceAll("[^\\d.]", ""));
		double TotalAmountBilling = Double.parseDouble(TotalAmount.trim().replaceAll("[^\\d.\\-]", ""));

		// Validate product lines count
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCount = AllProductRows.size();	
		asrt.assertEquals(ExpectedLinesCount, LinesCountBilling,"Mismatch in Lines count, expected " + ExpectedLinesCount + " but got " + LinesCountBilling + " in the billing screen");

		// Validate item name
		List<WebElement> ItemNamesBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		String ActualItem = ItemNamesBilling.get(0).getText().trim();
		String ExpectedFullItemName = ExpectedItemName.trim();
		String ExpectedItemNameBillingScreen = ExpectedFullItemName.contains("-") ? ExpectedFullItemName.substring(ExpectedFullItemName.indexOf("-") + 2) : ExpectedFullItemName;
		//asrt.assertEquals(ExpectedItemNameBillingScreen, ActualItem,"Mismatch in item name, expected " + ExpectedItemNameBillingScreen + " but got " + ActualItem + " in the billing screen.");
		asrt.assertTrue(ExpectedItemNameBillingScreen.contains(ActualItem),"Item name mismatch: Expected is "+ExpectedItemNameBillingScreen+" but got "+ActualItem+" in billing page");

		// Convert ItemNamesBilling -> List<String> to avoid type mismatch before step23
		List<String> TextItemNamesBilling = ItemNamesBilling.stream().map(WebElement::getText).map(String::trim).toList();

		//Capture Item Quantities 
		//need to add assertions here ---
		List<WebElement> ItemQtyBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields","tillLayout-QuantityField textRight"));
		List<String> TextItemQtyBilling = ItemQtyBilling.stream().map(WebElement::getText).map(String::trim).toList();
		String OGQtyBillingWithoutDecimal=TextItemQtyBilling.get(1).replaceAll("[^\\d.]", "");
		String OGQtyBillingPge = String.format("%.3f", Double.parseDouble(OGQtyBillingWithoutDecimal));
		asrt.assertEquals(OGQtyBillingPge, OgQty,"Mismatch in Quantity value,expected" + OGQtyBillingPge +" but got " + OgQty + "in the Billing screen");

		// Validate total without tax
		List<WebElement> ItemRateBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		String SkuItemRate = ItemRateBilling.get(0).getText().trim().replaceAll("[^\\d.\\-]", "");
		String OGItemRate = ItemRateBilling.get(1).getText().trim().replaceAll("[^\\d.\\-]", "");
		double FirstItemRate = Double.parseDouble(SkuItemRate);
		double SecondItemRate = Double.parseDouble(OGItemRate);

		// Subtotal should match SKU total
		//double ExpectedSubTotal = FirstItemRate + SecondItemRate;
		double ExpectedSubTotal = Math.round((FirstItemRate + SecondItemRate) * 100.0) / 100.0;
		asrt.assertEquals(ExpectedSubTotal, SubTotalBilling, 1.0,"Mismatch in Subtotal value, expected " + ExpectedSubTotal + " but got " + SubTotalBilling + " in the Billing screen");

		// Tax should be 3% of subtotal
		double ExpectedTaxAmnt = FirstItemRate * 3 / 100;
		asrt.assertEquals(ExpectedTaxAmnt, TaxBilling, 1.0,"Mismatch in Tax value, expected " + ExpectedTaxAmnt + " but got " + TaxBilling + " in the billing screen");

		// Validate Total Amount
		double ActualTotalAmount = Math.round((ExpectedSubTotal + ExpectedTaxAmnt) * 100.0) / 100.0;
		asrt.assertEquals(TotalAmountBilling, ActualTotalAmount, 1.0,"Mismatch in Total amount value, expected " + ActualTotalAmount + " but got " + TotalAmountBilling + " in the Billing screen");

		//Step 16: Save esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));

		//Verify invoice details
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);

		// Download and verify invoice
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC174_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;

		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProformaInvoicePath,
				SkuItemRate,     // TaxableValue
				Tax,             // Gst
				TotalAmount,     // InvoiceTotal
				NetTotal,        // NetTotal
				SubTotal         // TotalAmnt
				);	

		//Step 17: Recall estimate from cashier screen
		//Step 18: Select estimate and click on save to estimate button
		//Expected Result:Verify whether the recalled item is the same as the item in the cart.
		//Note:Cashier Screen after recall, *No of Product lines, *Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax), *Displayed Subtotal, *TAX, *Total Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");

		//Select estimate and click on Recall estimation button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		//To validate Estimation Number
		String EstmnNumberRecallPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(EstmnNumber,EstmnNumberRecallPge,"Mismatch in Estimation number, expected: "+EstmnNumber+" but got "+EstmnNumberRecallPge+" in Recall Estimation page ");
		//To validate lines count
		int LinesCountInBilling = Integer.parseInt(LinesCount);
		List<String> ItemNamesFromRecall = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		int LinesCountInRecall = ItemNamesFromRecall.size();
		asrt.assertEquals(LinesCountInBilling, LinesCountInRecall,"Mismatch in Lines count, expected: "+LinesCountInBilling+" but got "+LinesCountInRecall+" in Recall Estimation page");

		//To validate Item name
		for (int i =0; i<ItemNamesFromRecall.size();i++) 
		{			
			String ItemNameRecallPge = ItemNamesFromRecall.get(i).trim();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
			String ItemNameFromBillingPge = TextItemNamesBilling.get(i);
			asrt.assertEquals(ItemNameRecallPge,ItemNameFromBillingPge,"Mismatch in Item name, expected: "+ItemNameFromBillingPge+" but got "+ItemNameRecallPge+" in Recall estimation page");
		}

		List<String> QuantityFromRecall = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "QUANTITY"));
		for (int i = 0; i < QuantityFromRecall.size(); i++)
		{
			String DisplayedRecallQty = QuantityFromRecall.get(i).trim().replaceAll("[^\\d.]", "");
			double QtyFromRecallPge = Double.parseDouble(DisplayedRecallQty);
			String DisplayedQtyBilling = TextItemQtyBilling.get(i).trim().replaceAll("[^\\d.]", "");			
			double QtyFromBillingPge = Double.parseDouble(DisplayedQtyBilling);

			asrt.assertEquals(QtyFromRecallPge,QtyFromBillingPge,"Mismatch in Quantity Value, expected " + QtyFromBillingPge + " but got " + QtyFromRecallPge+ " in Recall Estimation page");
		}

		//To validate Total without tax
		double SubTotalFromBillingPge = Double.parseDouble(SubTotal.trim().replaceAll("[^\\d.\\-]", ""));
		double TotalWithoutTaxRecallPge = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalWithoutTax")));
		asrt.assertEquals(SubTotalFromBillingPge,TotalWithoutTaxRecallPge,"Mismatch in Total Amount value, expected: "+SubTotalFromBillingPge+" but got "+TotalWithoutTaxRecallPge+" in Recall estimation page");

		//To validate tax
		double TaxFromBillingPge = Double.parseDouble(Tax.trim().replaceAll("[^\\d.]", ""));
		double TaxFromRecallPge = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalTax")));
		asrt.assertEquals(TaxFromBillingPge,TaxFromRecallPge,"Mismatch in Tax amount, expected " + TaxFromBillingPge+" but got "+TaxFromRecallPge+" in Recall Estimation page");

		//To validate sub total
		double TotalAmntBillingPge = Double.parseDouble(NetTotal.trim().replaceAll("[^\\d.\\-]", ""));
		double SubTotalFromRecallPge = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.SubTotal")));
		asrt.assertEquals(TotalAmntBillingPge,SubTotalFromRecallPge,"Mismatch in Subtotal,expected " +TotalAmntBillingPge+" but got "+SubTotalFromRecallPge+" in Recall estimation page");

		System.out.println("====== Validation between the Billing screen and the Recall Estimate screen is completed ======  ");

		//Step 19: Select transaction type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC174_TransactionType);

		//Step 20: Click on process estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		//base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		try{if(Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) != null)
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));			
		}}catch(Exception e) {System.out.println("Sales Representative not found");}

		//Step 21:  Click on amount 
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		//Step 22: Verify Payment method(RTGS/NEFT) not displayed
		//Expected: Verify Payment method(RTGS/NEFT) not displayed
		asrt.assertFalse(base.isExists(LoginPageObj.Edt_AlertText("RTGS/NEFT")),"The Payment method(RTGS/NEFT) is displayed to the user");

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	//<summary>
	// Test Case Title : OG purchase add to sale
	// Automation ID : TC173
	// Author : Nandagopan
	// </summary>
	public void TC173_RtgsPaymentToCustomer() throws Exception{


		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: click on add to Sale button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC173_CustomerID, UtilityTestData.MenuBarOptions[0]);

		//Step 5: Select OG
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"OG");
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		//Step 6: Click on OG Own
		// Step 7:Choose Purchase
		Thread.sleep(4000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));

		//Step 8: Select configuration(purity 22K)		
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),RtgsPaymentToCustomerTestData.TC173_Purity);

		//To Handle the Unexpected Error
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");

		if (base.isElementPresent(driver, CartChangedAlert)) {		
			try {
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e) {
			}
		}

		//Step 9: Click on Add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(1000);
		//for getting current gold rate
		Thread.sleep(2000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		Thread.sleep(1000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), RtgsPaymentToCustomerTestData.TC173_PiecesByPurity);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), RtgsPaymentToCustomerTestData.TC173_GrossWeightByPurity);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(10000);
		double CurrentGoldRateInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		// Choose Purchase Option
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));

		// Step 10: Select QC person
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		// Step 11: Select Smith person
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		// Step 12: Enter piece value
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), RtgsPaymentToCustomerTestData.TC173_PiecesByPurity);

		// Step 13: Enter gross weight
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), RtgsPaymentToCustomerTestData.TC173_GrossWeightByPurity);

		// Step 14: Click on calculate button
		//Expected:Check Purchase rate Calculation 
		Thread.sleep(2000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(2000);
		double ActualOGPurchaseRateInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		double ExpectedOGPurchaseRateInItemDetailsPge = Math.round((CurrentGoldRateInItemDetailsPge * 0.98) * 100.0) / 100.0;
		asrt.assertEquals(ActualOGPurchaseRateInItemDetailsPge,ExpectedOGPurchaseRateInItemDetailsPge, 1.0,"OGPurchaseRate mismatch: Expected value is " + ExpectedOGPurchaseRateInItemDetailsPge + " but got " + ActualOGPurchaseRateInItemDetailsPge + " in item details page");

		double ExpectedNetWeightInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt")));
		double ExpectedAmountInItemDetailsPge    = Math.round(ExpectedNetWeightInItemDetailsPge * ActualOGPurchaseRateInItemDetailsPge * 100.0) / 100.0;
		double ActualAmountInItemDetailsPge      = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")));
		asrt.assertEquals(ActualAmountInItemDetailsPge,ExpectedAmountInItemDetailsPge, 1.0,"Amount mismatch: Expected value is" + ExpectedAmountInItemDetailsPge + " but got " + ActualAmountInItemDetailsPge + " in item details page");

		double ExpectedGrossWeightInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp")));
		double ActualTotalAmountInItemDetailsPge =  Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt")));
		asrt.assertEquals(ActualTotalAmountInItemDetailsPge, ExpectedAmountInItemDetailsPge,1.0,"Total amount mismatch: " + ExpectedAmountInItemDetailsPge + " but got " + ActualTotalAmountInItemDetailsPge + " in item details page");

		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ExpectedItemNameInItemDetailsPge = ItemName.substring(ItemName.indexOf("-") + 2);

		// Step 15: Click on add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));


		//Validation for Billing Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		List<WebElement> AllProductRows    = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCountInBillingPge = AllProductRows.size();
		int ActualLinesCountInBillingPge   = Integer.parseInt(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4")));
		asrt.assertEquals(ActualLinesCountInBillingPge, ExpectedLinesCountInBillingPge,"Lines count mismatch: Expected is " + ExpectedLinesCountInBillingPge + " but got " + ActualLinesCountInBillingPge + " in Billing page");

		String ActualItemNameInBillingPge  = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInItemDetailsPge, "Item name mismatch: " + ExpectedItemNameInItemDetailsPge + " but found " + ActualItemNameInBillingPge + " in billing page");

		double ActualGrossWeightInBillingPge = Double.parseDouble( base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")).replace("-", "").trim());
		asrt.assertEquals(ActualGrossWeightInBillingPge, ExpectedGrossWeightInItemDetailsPge,"Gross weight mismatch: " + ExpectedGrossWeightInItemDetailsPge + " but found " + ActualGrossWeightInBillingPge + " in billing page");

		double ActualTotalWithoutTaxInBillingPge = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replace("-", "").replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalWithoutTaxInBillingPge, ExpectedAmountInItemDetailsPge,"Total without tax mismatch: " + ExpectedAmountInItemDetailsPge + " but found " + ActualTotalWithoutTaxInBillingPge + " in billing page");

		double ExpectedSubTotalInBillingPge = ActualTotalWithoutTaxInBillingPge;
		double ActualSubTotalInBillingPge   = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualSubTotalInBillingPge, ExpectedSubTotalInBillingPge,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge + " in billing page");

		String ActualTaxInBillingPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("-", "").replace("₹", "").replace(",", "").trim();
		String ExpectedTaxInBillingPge = RtgsPaymentToCustomerTestData.TC173_OGTax;
		asrt.assertEquals(ActualTaxInBillingPge,ExpectedTaxInBillingPge,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge + " in billing page");

		double ExpectedTotalAmountInBillingPge = ExpectedSubTotalInBillingPge;
		double ActualTotalAmountInBillingPge   = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,"Total Amount mismatch: " + ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		//Step 16: Click on the Amount
		String ReceiveAmtFromInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("₹", "").replace(",", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		//Step 17: Select RTGS/NEFT button
		base.buttonClick(NormalSaleGoldandSilverObj.Sel_PaymentMethod(RtgsPaymentToCustomerTestData.TC173_PaymentDetails));
		base.ClickCondition(NormalSaleGoldandSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Step 18:Post the invoice
		// Expected:Verify Invoice Deatils
		String PaymentDetails = RtgsPaymentToCustomerTestData.TC173_PaymentDetails;
		Map<String, String> PurchaseBillData = pdfUtils.OldSkuBillPdfValidation("RTGS/NEFT");
		String GrossWt = PurchaseBillData.get("GrossWt1");
		double ActualGrossWtDouble = Double.parseDouble(GrossWt);
		String NetWt = PurchaseBillData.get("NetWt1");
		double ActualNetWtDouble = Double.parseDouble(NetWt);
		String ActualRate = PurchaseBillData.get("Rate1");
		String ActualItemTotal = PurchaseBillData.get("ItemTotal1");

		asrt.assertEquals(ActualGrossWtDouble, ExpectedGrossWeightInItemDetailsPge,1.0, "Mismatch in Gross Weight of first Product, expected "+ExpectedGrossWeightInItemDetailsPge+" but got "+ActualGrossWtDouble+" in the Purchase Bill Pdf");
		asrt.assertEquals(ActualNetWtDouble, ExpectedNetWeightInItemDetailsPge,1.0, "Mismatch in Net Weight of first Product, expected "+ExpectedNetWeightInItemDetailsPge+" but got "+ActualNetWtDouble+" in the Purchase Bill Pdf");
		asrt.assertEquals(Double.parseDouble(ActualRate), ActualOGPurchaseRateInItemDetailsPge,1.0,"Mismatch in Rate of first Product, expected "+ActualOGPurchaseRateInItemDetailsPge+" but got "+Double.parseDouble(ActualRate)+"in the Purchase Bill Pdf");	
		asrt.assertEquals(Double.parseDouble(ActualItemTotal),ExpectedAmountInItemDetailsPge,1.0,"Mismatch in Item Total of first Product, expected "+ExpectedAmountInItemDetailsPge+" but got "+Double.parseDouble(ActualItemTotal)+"in the Purchase Bill Pdf");

		String ActualTotalAmountPdf = PurchaseBillData.get("TotalAmount");
		String ActualPaymentDetailsPdf = PurchaseBillData.get("PaymentDetails");
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountPdf),ExpectedAmountInItemDetailsPge,1.0,"Mismatch in Subtotal value, expected "+ExpectedAmountInItemDetailsPge+" but got "+Double.parseDouble(ActualTotalAmountPdf)+"in the Purchase Bill Pdf");	
		asrt.assertEquals(Double.parseDouble(ActualPaymentDetailsPdf),ExpectedAmountInItemDetailsPge,1.0,"Mismatch in Total amount value, expected "+ExpectedAmountInItemDetailsPge+" but got "+Double.parseDouble(ActualPaymentDetailsPdf)+" in the Purchase Bill Pdf");

		String CustomerName = PurchaseBillData.get("BilledToName");

		// search RTGS payment details
		// verify RTGS entry line updated
		//Expected :In ERP, 1. search RTGS payment details and 2.verify RTGS entry line updated
		Thread.sleep(150000);
		erpUtils.RTGSPaymentDetails(CustomerName,ReceiveAmtFromInBillingPge);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	//<summary>
		// Test Case Title : Sales with order advance balance amount rtgs
		// Automation ID : TC175
		// Author : Neethu 
		// </summary>

		public void TC175_RtgsPaymentToCustomer() throws Exception{
			{

				//Step 1: Login to POS
				login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
				base.setZoom(driver, 60);

				//Prerequisite: Customer must be have a advance(normal,offer)
				Map<String, String> FirstOfferAdvance = appUtils.CreateWeightBookedOfferAdvanceAmount
						(RtgsPaymentToCustomerTestData.TC175_CustomerID,
								RtgsPaymentToCustomerTestData.TC175_Pieces, 
								RtgsPaymentToCustomerTestData.TC175_OfferGrossWeight2, 
								UtilityTestData.Company[0], 
								UtilityTestData.Location[0],
								RtgsPaymentToCustomerTestData.TC175_DepositPercent);
				String FirstOrderId = FirstOfferAdvance.get("OrderId");	
				Thread.sleep(3000);

				//Step 2: Click on Transaction button
				appUtils.HamBurgerButtonClick("iconShop");

				// Step 3 & 4: Select customer & Add to estimation
				appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC175_CustomerID, UtilityTestData.MenuBarOptions[5]);

				// Step 5: Click on customer adjustment button
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

				// Step 6: click on offer advance settlement
				base.buttonClick(LoginPageObj.Edt_AlertText("Order Advance Adjustment"));

				// Step 7: Select advance and Click on Add to cart button
				// Step 8: Enter OTP
				//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly

				WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				Wait.until(ExpectedConditions.presenceOfElementLocated(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions")));
				try {
					Thread.sleep(5000);
					base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"), FirstOrderId);
					String SelectedFirstOfferId = base.getTheSelectedTextInDropDown(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"));
					Assert.assertEquals(SelectedFirstOfferId, FirstOrderId, "Order ID selection mismatch");			
				} catch (Exception e) {
					Assert.fail("Order ID is not present: " + FirstOrderId);
				}

				base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell",FirstOrderId));
				String FirstOfferBalanceAmount = base.GetAttribte(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("BalanceAmount"),"value");
				String FirstOfferAdjustmentAmount =base.GetAttribte(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("InputAmount"),"value");
				String MetalRate = null;
				String BalanceGoldQty = null;
				MetalRate =	base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("win-itembox win-selected","column3"));
				BalanceGoldQty =	base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("win-itembox win-selected","column7 textRight"));

				asrt.assertEquals(Double.parseDouble(FirstOfferAdjustmentAmount),Double.parseDouble(FirstOfferBalanceAmount), "Mismatch in Balance amount, Expected"+FirstOfferBalanceAmount+" but got"+FirstOfferAdjustmentAmount+" in the Order Adjustment page");
				System.out.println("MetalRate: "+MetalRate);
				System.out.println("BalanceGoldQty: "+BalanceGoldQty);
				String SKUBalanceGoldQty = "2";
				base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

				//Step 9: Scan gold SKU
				//Expected: Check calculation Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount

				Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
				List<String> ProductNames = new ArrayList<>();
				List<String> ProductQtyList = new ArrayList<>();
				List<String> ProductTotalList = new ArrayList<>();
				ProductNames.add(RtgsPaymentToCustomerTestData.TC175_SchemeName);
				ProductQtyList.add(RtgsPaymentToCustomerTestData.TC175_SchemeWeight);
				Thread.sleep(5000);
				String ActualDepositAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
				ProductTotalList.add(ActualDepositAmount);
				double DepositAmountNow = Double.parseDouble(ActualDepositAmount.replaceAll("[^\\d.]", "").trim());

				double DepositMetalRate = Double.parseDouble(MetalRate);
				int SkuCounter = 1;
				RequiredSKUResult SkuListGold =	appUtils.GetRequiredQuantitySKU(
						RtgsPaymentToCustomerTestData.TC175_TransferType,
						RtgsPaymentToCustomerTestData.TC175_GoldFromCounter,
						RtgsPaymentToCustomerTestData.TC175_MetalType,
						Double.parseDouble(SKUBalanceGoldQty),
						RtgsPaymentToCustomerTestData.TC175_SkuGoldCount
						);
				List<String> SkuList = SkuListGold.RequiredQtySKUs;
				appUtils.HamBurgerButtonClick("iconShop");
				erpUtils.ResetRemainingReturnWeight();
				String SkuPurityReturn		="22K";
				double SkuPurityReturnDeposit = Double.parseDouble(SkuPurityReturn.replaceAll("[^\\d.]", "").trim());

				int i=0;
				for (i = 0; i < SkuList.size(); i++) 
				{ 
					int SkuNumber = i + 1;
					String Sku = SkuList.get(i);
					appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
					ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
					ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
					ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
					Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
					mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber)	;
					
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				System.out.println(ProductQtyList);
				System.out.println(ProductNames);
				System.out.println(	ProductTotalList);
				List<String> AllSkuList = new ArrayList<>();
				AllSkuList.addAll(SkuList);

				Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
				String SubTotal    			= TransactionPageItems.get("Subtotal");
				String Discount 			= TransactionPageItems.get("Discount");
				String TotalGrossWeight1	= TransactionPageItems.get("GrossWeight");
				String TotalNetWeight 		= TransactionPageItems.get("NetWeight");
				String Tax 					= TransactionPageItems.get("Tax");
				String TotalAmount 			= TransactionPageItems.get("TotalAmount");
				String NetTotal 			= TransactionPageItems.get("NetTotal");
				String LinesCount 			= TransactionPageItems.get("LinesCount");

				for (Map.Entry<String, String> entry : TransactionPageItems.entrySet()) {
					System.out.println(entry.getKey() + " = " + entry.getValue());
				}


				By CartChangedAlert = NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("GPP discount already applied");
				By OkBtn = LoginPageObj.Btn_SingnIn("Button1Close");
				if (base.isElementPresent(driver, CartChangedAlert)) {		
					try {
						WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
						OkButton.click();
					} 
					catch (TimeoutException e) {
					} 
				}

				Thread.sleep(8000);
				List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
				String ActualLinesCount = Integer.toString(AllProductRows.size());
				asrt.assertEquals(ActualLinesCount, LinesCount,"Lines count mismatch: Expected is "+ActualLinesCount+" but found "+LinesCount+" in billing page");		

				double SubTotalSum = 0.00;

				for(int j =0; j < AllProductRows.size() ;j++) {
					List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
					String ActualItemNameInBillingPge = ItemName.get(j).getText();
					if (j == 1) {
						String ExpectedItemNameInBillingPge = ProductNames.get(j).substring(ProductNames.get(j).indexOf("-") + 1).trim();
						asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),
								"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +
								"' should contain actual '" + ActualItemNameInBillingPge + "'");
					} else {
						String ExpectedItemNameInBillingPge = ProductNames.get(j).trim();
						asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),
								"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +
								"' should contain actual '" + ActualItemNameInBillingPge + "'");
					}


					List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
					String ActualQuantityInBillingPge = Quantity.get(j).getText().replace("-", "");
					String ExpectedQuantityInBillingPge = ProductQtyList.get(j);
					double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
					double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
					asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

					List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
					String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(j).getText().replaceAll("[^\\d.-]", "").trim();
					String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(j).getText().replaceAll("[^\\d.]", "").trim();
					SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInBillingPge);
					String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(j).replaceAll("[^\\d.]", "").trim()));
					asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
				}

				String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
				String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
				asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

				String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.-]", "").trim();
				double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
				double ExpectedTax = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
				String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
				asrt.assertEquals( ActualTax,ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

				double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
				double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
				asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

				//Step 10: save esimate
				//Expected: Verify the Invoice details
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
				base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
				String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
				Thread.sleep(3000);
				String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC175_ProformaInvoice");
				Thread.sleep(2000);
				String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
				String Gst= Tax;
				String TaxableValue= String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))));
				System.out.println("TaxableValue : "+TaxableValue);
				String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+ActualTax);
				System.out.println("InvoiceTotal : "+InvoiceTotal);
				String TotalAdjustment = ActualDepositAmount;
				System.out.println("TotalAdjustment : "+TotalAdjustment);

				String TotalAmt  ="";
				pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
						ProFormaInvoicePath,
						TaxableValue,
						Gst,
						InvoiceTotal,
						TotalAdjustment,
						NetTotal,
						TotalAmt);

				//Step 11:Recall estimate from cashier screen
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
				base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

				//Step 12: Select the estimate and click on Recall estimation
				//Expected Result: Verify whether the recalled item is the same as the item in the cart.
				/*
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),ActualEstmnNumber);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));		
				String EstmnNumberRecallPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				asrt.assertEquals(EstmnNumberRecallPge,ActualEstmnNumber,"Mismatch in Estimation No:, expected "+EstmnNumberRecallPge+" but got "+ActualEstmnNumber+"in the Recall page");	
                */
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
				String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in estimation page");
		        
				//Step 13: Select the Transaction Type as advance
				base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC175_TransactionTypeExchnge);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

				//Step 14: Click on Process Estimation 
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
				base.setZoom(driver, 60);
				Thread.sleep(3000);


				//			Now the represntative is hidden for recall
				//			//Step 15: Choose a sales representative
				//			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
				//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				//			String DiscountBilling = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4"));

				//Step 16: Click on the Amount
				String PaymentAmountRTGSNEFT = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Change due"));
				double PaymentAmountRTGSNEFTTrimmedValue= Double.parseDouble(PaymentAmountRTGSNEFT.trim().replaceAll("[^\\d.]", ""));
				String PdfPaymentAmountRTGSNEFT = String.valueOf(PaymentAmountRTGSNEFTTrimmedValue);
				Thread.sleep(4000);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
				Thread.sleep(3000);

				//Step 17: Select payment method as RTGS/NEFT
				base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
				base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

				//Step 18: Post advance invoice
				//Expected: Verify advance invoice 
				Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.OfferAdvanceRefundPdfValidation();
				String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
				String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
				String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
				String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");
				double ExpectedAdvanceAmount = Double.parseDouble(PdfPaymentAmountRTGSNEFT.replaceAll("[^\\d.]", "").trim());
				double ExpectedAdvanceReceived = Double.parseDouble(ActualAdvanceReceived.replaceAll("[^\\d.]", "").trim());
				double ExpectedNetReceived = Double.parseDouble(ActualNetReceived.replaceAll("[^\\d.]", "").trim());
				asrt.assertEquals(ExpectedAdvanceAmount, ExpectedAdvanceReceived,"Advance Received mismatch: Expected is "+ExpectedAdvanceAmount+" but found "+ExpectedAdvanceReceived+" in Advance refundreceipt");
				asrt.assertEquals(ExpectedAdvanceAmount, ExpectedNetReceived,"Net Advance value mismatch: Expected is "+ExpectedAdvanceAmount+" but found "+ActualNetReceived+" in Advance refund receipt");
				asrt.assertEquals(RtgsPaymentToCustomerTestData.TC175_PaymentMode, ActualPaymentMode,"Payment mode mismatch: Expected is "+RtgsPaymentToCustomerTestData.TC175_PaymentMode+" but found "+ActualPaymentMode+" in Advance refund receipt");
				asrt.assertEquals(ActualAdvanceReceived, PaymentAmount,"Payment Amount mismatch: Expected is "+ActualAdvanceReceived+" but found "+PaymentAmount+" in Advance refund receipt");

				//Step 19: again Recall estimate from cashier screen
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
				base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

				//Step 20: Select estimate and click on save to estimate button
				/*
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),ActualEstmnNumber);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));		
				String EstmnNumberRecallPgeSales = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				asrt.assertEquals(EstmnNumberRecallPgeSales,ActualEstmnNumber,"Mismatch in Estimation No:, expected "+EstmnNumberRecallPgeSales+" but got "+ActualEstmnNumber+"in the Recall page");	
                */
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
				String EstmnNumberRecallPgeSales = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				asrt.assertEquals(EstmnNumberRecallPgeSales,ActualEstmnNumber,"Mismatch in Estimation No:, expected "+EstmnNumberRecallPgeSales+" but got "+ActualEstmnNumber+"in the Recall page");	

				//Step 21: Select the Transaction Type as Sales
				base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC175_TransactionTypeSales);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

				//Step 22: Click on Process Estimation 
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
				base.setZoom(driver, 60);
				Thread.sleep(3000);

				//			Now the represntative is hidden for recall
				//			//Step 23: Choose a sales representative
				//			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
				//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				//			String DiscountBilling = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4"));
				//

				List<WebElement> ItemRateBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
				String SalesReturnSkuItemRate = ItemRateBilling.get(0).getText().trim().replaceAll("[^\\d.\\-]", "");
				String PdfSalesReturnSkuItemRate = 	ItemRateBilling.get(0).getText().trim().replaceAll("[^\\d.-]", "");

				Map<String, String> SkuBillingScrnTableDataValue = appUtils.TransactionSKUsLinesVerify(AllSkuList, ScannedSkuDataMap);
				String SkuNetTotalBillingScrn = SkuBillingScrnTableDataValue.get("TotalAmount");
				String SkuLinesCountBillingScrn = SkuBillingScrnTableDataValue.get("LinesCount");
				String TotalNetWeightRecallBillingScrn 	 =   SkuBillingScrnTableDataValue.get("NetWeight");
				double SkuNetTotalTrimmedValueBillingScrn= Double.parseDouble(SkuNetTotalBillingScrn.trim().replaceAll("[^\\d.]", ""));
				String PdfSkuNetTotalBillingScrn = String.valueOf(SkuNetTotalTrimmedValueBillingScrn);

				//Step 24: Click on amount 
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
				base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));

				//Step 25: Post invoice
				//Expected Result: Check final invoice details,* save receipt id for future reference
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC175_SaleInvoice");
				Thread.sleep(5000);		
				String SaleInvoicePath =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
				String GrossAmount = PdfSalesReturnSkuItemRate;
				String TotalDiscount = Discount;
				String TotalQtyPcs = SkuLinesCountBillingScrn;
				String Adjustment = PdfSkuNetTotalBillingScrn;
				String PaymentHDFCCard = "";
				String TotalDiamond ="";
				String SalesPdfPath = SaleInvoicePath;
				TotalGrossWeight1= TotalGrossWeight1;
				String NetValue = PdfSkuNetTotalBillingScrn;
				String TotalNetWt = TotalNetWeightRecallBillingScrn;

				String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
						SalesPdfPath,
						GrossAmount,
						TotalDiscount,
						TotalDiamond,
						TotalQtyPcs,
						TotalNetWt,
						TotalGrossWeight1,
						TaxableValue,
						NetValue,
						PaymentHDFCCard,
						Adjustment,
						AllSkuList,
						ScannedSkuDataMap);

				System.out.println("PDF INVOICE NO:-"+PdfInvoiceNo);
				base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton","Close"));
				pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
			}
		}

		//<summary>
		// Test Case Title : Sales with no benifit gpp balance amount rtgs
		// Automation ID : TC177
		// Author : Christy Reji
		// </summary>
		public void TC177_RtgsPaymentToCustomer() throws Exception{
			{
				//Step 1.Login to POS
				login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
				base.setZoom(driver, 60);
				//Manual Entry
				//			Thread.sleep(15000);
				//			Map<String, Object> ResultMap = new LinkedHashMap<>();
				//			List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
				//			Map<String, String> AccountMap1 = new HashMap<>();
				//			AccountMap1.put("GppAccountNo", "20GG000039222");
				//			AccountMap1.put("CustomerAccountNo", "20CA00429153");
				//			MatchedAccounts1.add(AccountMap1);
				//			ResultMap.put("MatchedAccounts", MatchedAccounts1);
				//			ResultMap.put("TotalFound", MatchedAccounts1.size());
				//			Map<String, Object> GPPMaturedAccountDetails =ResultMap;

				//Below Six Installments
				Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
						RtgsPaymentToCustomerTestData.TC177_Installment,
						RtgsPaymentToCustomerTestData.TC177_MaxxGppAccounts,
						RtgsPaymentToCustomerTestData.TC177_SchemeCode,
						RtgsPaymentToCustomerTestData.TC177_MaturedScheme,
						RtgsPaymentToCustomerTestData.TC177_AdjustedScheme);

				// Step : Pre-Requisite for taking BoardRate
				Thread.sleep(3000);
				appUtils.HamBurgerButtonClick("iconShop");
				double ErpGoldRate24k = appUtils.FetchCurrentBoardRate("24k");

				System.out.println("BoardRate24k "+ErpGoldRate24k);

				// Step 2. Click on Transaction button
				appUtils.HamBurgerButtonClick("iconShop");
				List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
				String CustomerAccount		="";
				String GPPAccountNumber		="";
				String GPPAmount			="";
				String SkuPurityReturn		="";
				String TotalRgWeightReturn	="";
				String SkuGoldRateReturn	="";
				boolean ValidAccountFound 	= false;

				//for (int idx = 0; idx < MatchedAccounts.size(); idx++) {
				for (int idx = MatchedAccounts.size() - 1; idx >= 0; idx--) {

					Map<String, String> AccountMap = MatchedAccounts.get(idx);
					CustomerAccount                = AccountMap.get("CustomerAccountNo");
					GPPAccountNumber               = AccountMap.get("GppAccountNo");
					GPPAmount                      = (String) GPPMaturedAccountDetails.get("GppAmount");	
					base.setZoom(driver, 60);

					// Step 3 & 4. Select customer & Add to estimation
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					appUtils.SearchByCustomerID(CustomerAccount, UtilityTestData.MenuBarOptions[5]);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

					// Step 5. Click on customer adjustment button
					Thread.sleep(2000);
					base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
					base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

					// Step 6. GPP Advance Adjustment
					base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

					// Step 7. Enter scheme id (GG) and click "Add to cart"
					//Verify whether the entered GGscheme gets added 
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
					Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(GPPAccountNumber);
					SkuPurityReturn   = (String) GPPMaturityValues.get("Purity");
					SkuGoldRateReturn = (String) GPPMaturityValues.get("AvgRate");
					SkuGoldRateReturn = SkuGoldRateReturn.replace(",", "");

					//  If no "No data", break out of loop and continue workflow
					System.out.println("Valid GPP account found: " + GPPAccountNumber);
					ValidAccountFound = true;
					break;
				}
				if (!ValidAccountFound) {
					throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
				}
				//validation
				String ActualSchemeID =base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
				Assert.assertEquals(ActualSchemeID, RtgsPaymentToCustomerTestData.TC177_SchemeName,"Mismatch in SchemeID Actual is : "+ActualSchemeID+" and Expected Scheme ID : "+RtgsPaymentToCustomerTestData.TC177_SchemeName+"");

				//Step 8.Enter the OTP
				//Step 9.Scan multiple category(Gold)
				//Expected :Check calculation
				Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
				List<String> ProductNames     = new ArrayList<>();
				List<String> ProductQtyList   = new ArrayList<>();
				List<String> ProductTotalList = new ArrayList<>();
				ProductNames.add(RtgsPaymentToCustomerTestData.TC177_SchemeName);
				ProductQtyList.add(RtgsPaymentToCustomerTestData.TC177_SchemeWeight);
				Thread.sleep(5000);
				String ActualSchemeAmount     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
				ProductTotalList.add(ActualSchemeAmount);
				double SchemeAmountNow        = Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
				double SchemeGoldRate         = Double.parseDouble(SkuGoldRateReturn);
				TotalRgWeightReturn           = String.valueOf(SchemeAmountNow/SchemeGoldRate);
				System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
				int SkuCounter = 1;
				// Gold SKUs
				double RequiredQty = Double.parseDouble(TotalRgWeightReturn) * 0.5;
				RequiredSKUResult SkuGoldSaleItem = appUtils.GetRequiredQuantitySKU(
						RtgsPaymentToCustomerTestData.TC177_TransferType,
						RtgsPaymentToCustomerTestData.TC177_FromCounterGold,
						RtgsPaymentToCustomerTestData.TC177_MetalTypeGold,
						RequiredQty,
						RtgsPaymentToCustomerTestData.TC177_SaleProductCount);
				List<String> SkuList = SkuGoldSaleItem.RequiredQtySKUs;

				appUtils.HamBurgerButtonClick("iconShop");
				erpUtils.ResetRemainingReturnWeight();
				for (String Sku : SkuList) {
					appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
					ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
					ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
					ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));

					//Note: Check average rate
					Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
					erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,String.valueOf(ErpGoldRate24k));
					SkuCounter++;
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				//SKU Validation
				Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
				String SubTotal                         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
				String Discount                         = TransactionDataLine.get("Discount");
				String TotalGrossWeight                 = TransactionDataLine.get("GrossWeight");
				String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
				String Tax                              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
				String TotalAmount                      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
				String NetTotal                         = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
				String LinesCount                       = TransactionDataLine.get("LinesCount");

				List<String> ActualNames                = mathUtils.ValidateTransactionLineCalculationsReturnProd(TransactionDataLine, ScannedSkuDataMap);
				String SKUName                          = ActualNames.toString().replace("[", "").replace("]", "").trim();
				List<String> Totals = base.GetElementTexts( NormalSaleGoldandSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
				Thread.sleep(5000);
				double TotalWithoutscheme = Double.parseDouble(Totals.get(0).replaceAll("[^\\d.]", "").trim());
				double TotalWithoutTaxSku = Double.parseDouble(Totals.get(1).replaceAll("[^\\d.]", "").trim());
//				double ExpectedSubTotal   = TotalWithoutscheme-TotalWithoutTaxSku;
				double ExpectedSubTotal = Math.round(
				        Math.abs(TotalWithoutscheme - TotalWithoutTaxSku) * 100.0
				) / 100.0;
//				double ExpectedSubTotal = TotalWithoutTaxSku - TotalWithoutscheme;
				double ExpectedTotalAmnt = Math.round(
				        Math.abs(ExpectedSubTotal - Double.parseDouble(Tax)) * 100.0
				) / 100.0;
				//double ExpectedTotalAmnt  = ExpectedSubTotal-Double.parseDouble(Tax);
				Assert.assertEquals(Double.parseDouble(SubTotal), ExpectedSubTotal, 1.00,"Mismatch in Subtotal. Actual is: " + SubTotal + " but got ExpectedSubTotal: " + ExpectedSubTotal + " after calculation in billing page");
				Assert.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmnt, 1.00, "Mismatch in Total amount. Actual is: " + TotalAmount + " but got ExpectedTotalAmnt: " + ExpectedTotalAmnt + " after calculation in billing page");

				//Step 10: Click on gpp discount button
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
				base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
				base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
				Thread.sleep(3000);

				//FOR HANDLING THE WARNING MESSAGE
				asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")), "The warning message: GPP account is not eligible for GPP discount please proceed further is not displayed in the billing page.");		
				Thread.sleep(2000);
				base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));

				//Step 11: save esimate
				//Expected:Verify the Invoice details
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
				base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));	
				String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

				Thread.sleep(3000);
				String ProFormaInvoiceName    = pdfUtils.DownloadAndRenameLatestPDF("TC177_ProformaInvoice");
				Thread.sleep(2000);
				String ProFormaInvoicePath    = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
				String Gst                    = Tax;
				double CalculatedTaxable      =  Double.parseDouble(ActualSchemeAmount)-Double.parseDouble(SubTotal) ;
				String TaxableValue           = String.valueOf(CalculatedTaxable); 
				double CalculatedInvoiceTotal = Double.parseDouble(TaxableValue) + Double.parseDouble(Tax);
				String InvoiceTotal           = String.valueOf(CalculatedInvoiceTotal); 
				String TotalAdjustment        = (ActualSchemeAmount);
				String TotalAmt               = ""; 
				pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
						ProFormaInvoicePath,
						TaxableValue,
						Gst,
						InvoiceTotal,
						TotalAdjustment,
						NetTotal,
						TotalAmt);

				//Step 12: Recall estimate from cashier screen
				//Step 13: Select estimate and click on save to estimate button
				//Expected:Verify whether the recalled item is the same as the item in the cart.
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
				base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				Thread.sleep(3000);
				base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
				String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"");

				//ValidateRecallPage GG Scheme
				List<String> ExpectedGrossWeights = ProductQtyList;
				appUtils.ValidateRecallPageOGItem(ProductNames,ExpectedGrossWeights,
						RtgsPaymentToCustomerTestData.TC177_SchemeName,
						RtgsPaymentToCustomerTestData.TC177_SchemeQty,
						Double.parseDouble(SubTotal),
						Double.parseDouble(Tax),
						Double.parseDouble(TotalAmount),
						Integer.parseInt(LinesCount),
						"GG-Scheme");

				//Step 14: Select the Transaction Type as Advance 
				base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC177_TransactionTypeAdvance);

				//Step 15: Click on Process Estimation
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

				//Step 16: Choose a sales representative[Step not required]
				Thread.sleep(5000);
				//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

				//Step 17 : Click on amount
				//Step 18: Select payment method method (RTGS/NEFT)
				//Step 19: Post the invoice(No invoice generated)
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
				Thread.sleep(3000);
				String PaymentAmountRTGS_NEFT = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
				Thread.sleep(3000);
				base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
				base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));

				//Step 20. Again recall estimate from cashier screen
				//Step 21:Select estimate and click on save to estimate button
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
				base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),ActualEstmnNumber);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

				//Step 22. Select transaction type as sale
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
				base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),RtgsPaymentToCustomerTestData.TC177_TransactionTypeSales);

				//Step 23: Click on process estimation button
				//Step 24: [sale rep not required]
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
				//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

				// Step 25:Click on the Amount
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
				Thread.sleep(3000);
				base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
				base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));

				//Step 26 : Post the sale invoice
				//Check the final ivoice details-Sale
				Thread.sleep(3000);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC177_FinalSaleInvoice");
				Thread.sleep(3000);		
				String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
				Thread.sleep(3000);
				String GrossAmount         = TaxableValue;
				String TotalDiscount       = Discount;
				String TotalQtyPcs         = String.valueOf(SkuList.size());
				String Adjustment          = InvoiceTotal;
				String PaymentAdj          = Adjustment;
				String SalesPdfPath        = SaleInvoicePath;
				String TotalNetWeightPdf   = TotalNetWeight;
				String TotalGrossWeightPdf = TotalNetWeightPdf;
				String NetValue            = PaymentAdj;
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
						PaymentAdj,
						Adjustment,
						SkuList,
						ScannedSkuDataMap
						);
				pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	
			}
		}

	//<summary>
	// Test Case Title : Sales with normal advance balance amount rtgs
	// Automation ID : TC176
	// Author : Aiswarya
	// </summary>

	public void TC176_RtgsPaymentToCustomer() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");
		
		// Prerequisite: Customer must be having an advance(Normal)
		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate(RtgsPaymentToCustomerTestData.TC176_Purity);	
		String ErpGoldRateFor24K = (ErpGoldRate24k % 1 == 0)? String.valueOf((int) ErpGoldRate24k): String.valueOf(ErpGoldRate24k);
		Thread.sleep(2000);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(2000);
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				RtgsPaymentToCustomerTestData.TC176_CustomerID,
				RtgsPaymentToCustomerTestData.TC176_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		Thread.sleep(5000);
		appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC176_CustomerID, UtilityTestData.MenuBarOptions[5]);
		Thread.sleep(4000);

		// Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		// Step 6: Click on normal advance Adjustment
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(4000);

		// Step 7: Select advance and Click on Add to cart button
		// Expected Result: The balance amount and adjustment amount for the selected advances should be shown correctly
		base.actionClick(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.setData(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), RtgsPaymentToCustomerTestData.TC176_AdvanceAmount);
		base.pressKey(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ErpGoldRateFor24K));
		Thread.sleep(5000);
		//base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(3000);
		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		// double AdvanceTotal = Double.parseDouble(AdjustmentAmount);

		// Step 9: Scan gold SKU
		// Step 10: Click on Add to cart button
		// Expected Result: Check calculation
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				RtgsPaymentToCustomerTestData.TC176_SkuCount,
				RtgsPaymentToCustomerTestData.TC176_TransferType,
				RtgsPaymentToCustomerTestData.TC176_FromCounter,
				RtgsPaymentToCustomerTestData.TC176_MetalType);

		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		ProductNames.add(RtgsPaymentToCustomerTestData.TC176_AdvanceName);
		ProductQtyList.add(RtgsPaymentToCustomerTestData.TC176_AdvanceQty);
		ProductTotalList.add(AdjustmentAmount);

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

		// Note: Billing Screen *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
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
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount,"Lines count mismatch: Expected is " + ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");
		Thread.sleep(5000);

		double SubTotalSum = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {

			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got " + ActualItemNameInBillingPge + " in billing page");
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000,"Quantity mismatch: Expected is " + ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSum = SubTotalSum + Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got " + ActualTotalWithoutTaxInBillingPge + " in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.-]", "").trim();
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge), Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge + " in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		String TaxableValue = Double.toString(Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "")));
		double ExpectedTax = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge + " in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge) + Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0,"Total Amount mismatch: " + ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		String TotalGrossWt = String.format("%.3f", Double.parseDouble(ProductQtyList.get(1)));

		// Step 11: Save esimate
		// Expected Result: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC176_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Double.toString(ActualTax);
		String NetTotalPdf 		   = Double.toString(ActualTotalAmountInBillingPge).replaceAll("[^\\d.]", "");
		String InvoiceTotal = Double.toString(Double.parseDouble(TaxableValue)+(ActualTax));
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(  
				ProFormaInvoicePath,
				TaxableValue, 
				Gst,
				InvoiceTotal,  
				AdjustmentAmount,
				NetTotalPdf,  
				"");
		Thread.sleep(2000);;

		// Step 12: Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Step 13: Select estimate and click on save to estimate button
		// Expected Result: Verify whether the recalled item is the same as the item in the cart.
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String EstmnNumberRecallPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(EstmnNumberRecallPge,ActualEstmnNumber,"Mismatch in Estimation No:, expected "+EstmnNumberRecallPge+" but got "+ActualEstmnNumber+"in the Recall page");
		
		// Step 14: Select the Transaction Type as advance
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC176_TransactionTypeAdvance);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Step 15: Click on Process Estimation 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
		base.setZoom(driver, 60);
		Thread.sleep(3000);

		// Step 16: Choose a Sales representative
		if (base.isPresent(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));	
		}

		// Step 17: Click on the Amount
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// Step 18: Select payment method as RTGS/NEFT
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		String PaymentAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", "");
		Thread.sleep(5000);
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));

		// Step 19: Post advance invoice
		// Expected Result: Verify advance invoice
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Map<String, String> Result = pdfUtils.AdvanceRefundPdfValidation();	

		String AdvanceReceived     = Result.get("AdvanceReceived");
		String NetAdvance       = Result.get("NetAdvance");
		String RTGSNEFTAmount         = Result.get("RTGSNEFTAmount");
		double PdfAdvanceReceived  = Double.parseDouble(AdvanceReceived.replaceAll("[^\\d.]", "").trim());
		double PdfNetAdvance   = Double.parseDouble(NetAdvance.replaceAll("[^\\d.]", "").trim());
		double PdfRTGSNEFTAmount     = Double.parseDouble(RTGSNEFTAmount.replaceAll("[^\\d.]", "").trim());


		asrt.assertEquals(PdfAdvanceReceived,Double.parseDouble(NetTotalPdf),1, "Mismatch in Advance Received actual "+PdfAdvanceReceived+" and Expected "+NetTotalPdf+"in AdvanceRefund Pdf ");
		asrt.assertEquals(PdfNetAdvance, Double.parseDouble(NetTotalPdf),1, "Mismatch in NetAdvance actual "+PdfNetAdvance+" and Expected "+NetTotalPdf+" in AdvanceRefund Pdf ");
		asrt.assertEquals(PdfRTGSNEFTAmount, Double.parseDouble(NetTotalPdf), "Mismatch in RTGSNEFTAmount Actual "+PdfRTGSNEFTAmount+" and Expected "+NetTotalPdf+ " in AdvanceRefund Pdf ");


		// Step 20: again Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Step 21: Select estimate and click on save to estimate button
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));		

		// Step 22: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC176_TransactionTypeSales);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Step 23: Click on Process Estimation 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
		base.setZoom(driver, 60);
		Thread.sleep(3000);

		// Step 24: Choose a Sales representative
		if (base.isPresent(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));	
		}

		//Expected Result: Note:-24.Cashier Screen after recall*No of Product lines *Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
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
		asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in billing page");

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
		double ActualTotalAmountInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");


		// Step 25: Click on the Amount
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Step 26: Post the Invoice
		// Expected Result: Check final invoice details* save receipt id for future reference
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String SalesInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC176_SalesInvoice");
		Thread.sleep(2000);
		String SalesInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + SalesInvoiceName;
		String Adjustment = InvoiceTotal;
		String TotalNetWt = TotalNetWeight.replaceAll("[^\\d.]", "");
		String GrossAmount = TaxableValue;
		pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SalesInvoicePath,
				GrossAmount,
				"",
				"",
				ActualLinesCount,
				TotalNetWt,
				TotalGrossWt,
				TaxableValue,
				InvoiceTotal,
				"",
				Adjustment,
				SkuList,
				ScannedSkuDataMap
				) ;

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	

	}


	//<summary>
		// Test Case Title : Check RTGS payment details after RTGS/NEFT payment
		// Automation ID : TC179
		// Author : Vishnu RR
		// </summary>

		public void TC179_RtgsPaymentToCustomer() throws Exception {


			// Step 1: Login to POS
			login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
			base.setZoom(driver, 60);

			// Step 2: Click on Transaction button
			appUtils.HamBurgerButtonClick("iconShop");

			//Step 3 : Select customer
			//Step 4 : Click on add to sale button
			appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC179_CustomerID,UtilityTestData.MenuBarOptions[0]);

			// Step 5. Select OG
			// Step 6.Click on OG own
			// Step 7.Choose purchase
			// Step 8. Select configuration
			// Step 9. Click on Add item button
			// Step 10. Select QC person
			//Step  11. Select Smith person
			//Step  12. Enter piece value
			// Step 13. Enter gross weight
			//Step  14. click on calculate button
			//Step   15. Click on Add to cart button
			String CustomerName = base.GetText(NormalSaleGoldAndSilverObj.Ele_Purchase("customerName"));
			Thread.sleep(3000);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
			base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
			Thread.sleep(5000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),RtgsPaymentToCustomerTestData.TC179_Purity);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// To Handle the Unexpected Error
			WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
			By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
			By OkBtn = LoginPageObj.Btn_SignInButton("OK");
			if (base.isElementPresent(driver, CartChangedAlert)) {		
				try {
					WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
					OkButton.click();
				}
				catch (TimeoutException e) {
				}
			}
			base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
			base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
			Thread.sleep(3000);
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
			Thread.sleep(3000);
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),RtgsPaymentToCustomerTestData.TC179_GrossPieces);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),RtgsPaymentToCustomerTestData.TC179_GrossWeight);
			base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			Thread.sleep(2000);
			base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
			base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
			if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
			{
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			}
			//Step 16.Click on the Amount
			//Step 17.Select payment method as RTGS/NEFT
			//Step 18. Post the invoice
			Thread.sleep(2000);
			String ReceiveAmtFromInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
			Thread.sleep(2000);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			//Thread.sleep(12000);
			TimeUnit.MINUTES.sleep(1);

			//Expected :In ERP, 1. search RTGS payment details and 2.verify RTGS entry line updated
			erpUtils.RTGSPaymentDetails(CustomerName,ReceiveAmtFromInBillingPge);

		}	
	
	//<summary>
		// Test Case Title :Check RTGS payment details after RTGS adjustment
		// Automation ID : TC180
		// Author : Nandagopan 
		// </summary>
		public void TC180_RtgsPaymentToCustomer() throws Exception{


			//Step 1: Login to POS
			login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
			base.setZoom(driver, 60);

			//Pre requisite                                                    
			//To create a  new RTGS account:
			//1.Go to ERP of specific legal entity and search RTGS payment receipt
			//2.Click on New
			//3.Enter Entry date
			//4.Enter UTR number
			//5.Enter Amount 
			//6.Provide Bank Account
			//7.Enter Remarks
			// 8.Enter Cost center
			// 9.Enter Company ID
			//10.Enter Customer Account
			//11.Select Credit date
			//12.Enter User ID
			//13.Click on Post

			//RTGS ACCOUNT CREATION IN ERP 
			Map<String, String> RtgsDetails= erpUtils.RtgsAccountCreation(RtgsPaymentToCustomerTestData.TC180_StringAmountToDeposit, RtgsPaymentToCustomerTestData.TC180_CustomerId);	

			//Step 2: Click on Transaction button
			appUtils.HamBurgerButtonClick("iconShop");

			//Step 3: Select customer
			//Step 4: Click on add to estimation button
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			appUtils.SearchByCustomerID(RtgsPaymentToCustomerTestData.TC180_CustomerId, UtilityTestData.MenuBarOptions[5]);

			//Step 5: Scan Multiple SKU's and click on Add to cart button
			// Step 6 : Select any one of the sales agent
			List<String> SkuList = appUtils.SelectMultipleSKUs(RtgsPaymentToCustomerTestData.TC180_SkuGoldCount,RtgsPaymentToCustomerTestData.TC180_TransferType,RtgsPaymentToCustomerTestData.TC180_FromCounter,RtgsPaymentToCustomerTestData.TC180_MetalType);
			appUtils.HamBurgerButtonClick("iconShop");
			Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
			for (int i = 0; i < SkuList.size(); i++) {
				int SkuNumber = i + 1;
				String Sku = SkuList.get(i);
				// Step 1: Extract & store table values
				appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
				// Step 2: Filter for this specific SKU
				Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);

			}


			// Step 7: Click on Save to estimation button
			// Step 8: Save Esimate
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
			String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

			//Step 9 : Recall estimate from cashier screen
			//Step 10 : Select estimate and click on recall estimate button
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

			String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
			

			// Step 11 : Select the Transaction Type as Sales
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), RtgsPaymentToCustomerTestData.TC180_TransactionType);

			// Step 12 : Click on Process Estimation
			// Step 13 : Select any Sales Representative		
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			base.setZoom(driver, 60);
			Thread.sleep(3000);
			
			if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
			{
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			}
			//base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			appUtils.HandleSpecifyRateModalInRecall();

			// Step 14 :  Click on the Amount
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

			// Step 15 :  Select a Payment method(RTGS/Received)
			Thread.sleep(3000);
			base.buttonClick(LoginPageObj.Edt_AlertText(RtgsPaymentToCustomerTestData.TC180_PaymentMethod));

			//Step15.1 Enter the RTGS amount
			String AmountDue=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("text","AmountDue"));
			base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"), AmountDue);
			String RtgsAmount=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"));

			double RtgsValue = Double.parseDouble(RtgsAmount);
			// Round off to 2 decimal places
			double RoundedRtgsValue = Math.round(RtgsValue);
			// Convert back to String with ".00" format
			String RtgsFinalAmount = String.format("%.2f", RoundedRtgsValue);  //FOR STEP 16 
			double RtgsAmountReceived = Double.parseDouble(RtgsAmount);   //SAME FOR STEP 16 SOMETIMES FOR KALPETTA IF ROUND OFF NOT NEEDED THERE
			WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1")));

			//15.2.Enter the UTR number
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"),RtgsDetails.get("UtrNumber"));
			Thread.sleep(4000);
			String BalanceBeforePayments=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","RTGSBalance"));
			double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayments);//Expected Balance before payment in POS
			String ActualBalanceBeforePaymentInErp=RtgsDetails.get("RtgsBalanceAmntBeforePayment").replace(",",""); //Expected Balance Before Payment in ERP
			double ActualBalanceBeforePayment=Double.parseDouble(ActualBalanceBeforePaymentInErp);
			double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - RtgsAmountReceived;
			ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0; //For rounding off to 2 decimal places

			//15.3.Click on "Apply" then "Ok"
			base.buttonClick(LoginPageObj.Btn_SingnIn("applybtn"));  //click on apply
			Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
			Thread.sleep(5000);
			base.buttonClick(LoginPageObj.Btn_SingnIn("Button1"));   //click on ok

			// Step 16:  Post the Invoice
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")); 
			Thread.sleep(3000);
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close")); 
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

			//ERP:
			//1.Go to ERP and search RTGS payment receipt details
			//2.Filter with UTR number/Customer account number
			//3.Check the 'Balance Amount' Before and after Payment
			//Expected: Check balance amount
			
			erpUtils.ValidateRTGSPaymentBalance(
					RtgsDetails.get("UtrNumber"),
					RtgsPaymentToCustomerTestData.TC180_CustomerId,
					ExpectedBalanceBeforePayment,
					ActualBalanceBeforePayment,
					ExpectedBalanceAfterPayment
					);

		}
	
	
	
	
	
	
	
	



}