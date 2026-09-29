package testPage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.GppOpeningAndCollectionMultiplePlans_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CashCollectionRepaymentLimitValidation_TestData;
import testData.CommonData;
import testData.CustomerCreation_TestData;
import testData.GppOpeningAndCollectionMultiplePlans_TestData;
import testData.PANCardLimitValidation_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities.SKUResult;
import utilPack.AppUtilities.SKUTypeResult;

public class CashCollectionRepaymentLimitValidation extends BasePge {
	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;
	ErpUtilities erpUtils;

	Login login = new Login(driver); 
	LoginPage_Obj LoginPageObj = new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	CashCollectionRepaymentLimitValidation_TestData CashCollectionRepaymentLimitValidationTestData=new CashCollectionRepaymentLimitValidation_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj =new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	GppOpeningAndCollectionMultiplePlans_Obj GppOpeningAndCollectionMultiplePlansObj = new GppOpeningAndCollectionMultiplePlans_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj=new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj ();


	public CashCollectionRepaymentLimitValidation(WebDriver driver) 
	{
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		pdfUtils = new PdfUtilities(base);
		mathUtils = new MathUtilities(base);
		erpUtils  = new ErpUtilities(base);
	}

	// <summary>
	// Test Case Title : Repayment to customer more than 10000 as cash
	// Automation ID : TC170
	// Author: Sangeetha M S
	// </summary>
	public void TC170_CashCollectionRepaymentLimitValidation() throws Exception
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		Thread.sleep(1500);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer 
		//Step 4. Click on add to sale button
		Thread.sleep(1500);
		appUtils.SearchByCustomerID(CashCollectionRepaymentLimitValidationTestData.TC170_CustomerPhoneNo ,UtilityTestData.MenuBarOptions[0]);

		//Step 5.Search OG
		//Step 6.Select Old Gold
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));

		//Step 7.Select a configuration and click on "Add item"
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),CashCollectionRepaymentLimitValidationTestData.TC170_Purity);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//Select Exchange
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		Thread.sleep(2000);
		//		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),UtilityTestData.QCAndSmithPerson);
		//		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),UtilityTestData.QCAndSmithPerson);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		if (base.isExists(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));        
		}
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),CashCollectionRepaymentLimitValidationTestData.TC170_Pieces);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),CashCollectionRepaymentLimitValidationTestData.TC170_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(1000);
		double ExchangeBoardRateInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		System.out.println(ExchangeBoardRateInItemDetailsPge);

		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));        
		}


		//Select Purchase
		Thread.sleep(2000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));		
		Thread.sleep(1000);
		//		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),UtilityTestData.QCAndSmithPerson);
		//		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),UtilityTestData.QCAndSmithPerson);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), CashCollectionRepaymentLimitValidationTestData.TC170_Pieces);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), CashCollectionRepaymentLimitValidationTestData.TC170_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

		Thread.sleep(2000);
		double ActualOGPurchaseRateInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		double ExpectedOGPurchaseRateInItemDetailsPge = Math.round((ExchangeBoardRateInItemDetailsPge*0.98)*100.0)/100.0;

		asrt.assertEquals(ExpectedOGPurchaseRateInItemDetailsPge, ActualOGPurchaseRateInItemDetailsPge,1.0,"OG Purchase Rate mismatch: Expected value is "+ExpectedOGPurchaseRateInItemDetailsPge+" but got "+ActualOGPurchaseRateInItemDetailsPge+" in item details page");

		String ExpectedNetWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));
		double NetWeightInItemDetailsPge = Double.parseDouble(ExpectedNetWeightInItemDetailsPge);
		double ExpectedAmountInItemDetailsPge = Math.round(NetWeightInItemDetailsPge * ActualOGPurchaseRateInItemDetailsPge * 100.0) / 100.0;
		double ActualAmountInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt")));

		asrt.assertEquals(ExpectedAmountInItemDetailsPge, ActualAmountInItemDetailsPge,1.0,"Cvalue mismatch: "+ExpectedAmountInItemDetailsPge+" but got "+ActualAmountInItemDetailsPge+" in item details page");		

		String ExpectedGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String ExpectedTotalAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));
		String ActualTotalAmountInItemDetailsPge = Double.toString(ActualAmountInItemDetailsPge);

		asrt.assertEquals(Double.parseDouble(ExpectedTotalAmountInItemDetailsPge), Double.parseDouble(ActualTotalAmountInItemDetailsPge),1.00,"Total amount mismatch: "+ExpectedTotalAmountInItemDetailsPge+" but got "+ActualTotalAmountInItemDetailsPge+" in item details page");

		String ItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ExpectedItemNameInItemDetailsPge = ItemName.substring(ItemName.indexOf("-") + 2);
		String ExpectedGrossWeight = String.valueOf(Double.parseDouble(ExpectedGrossWeightInItemDetailsPge));
		String ExpectedTotalAmount = String.valueOf(Double.parseDouble(ExpectedTotalAmountInItemDetailsPge));

		//Step 9.Click on "Add to cart"
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("bottomLeft grow pad12", "1"));

		//Step 10.Click on total amount
		//Expected :Billling Screen *No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal,*TAX,*Total Amount
		//To validate LinesCount, Displayed ItemName, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal,TAX, Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		int ExpectedLinesCountInBillingPge = AllProductRows.size();
		int ActualLinesCountInBillingPge = Integer.parseInt(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4")));
		asrt.assertEquals(ExpectedLinesCountInBillingPge, ActualLinesCountInBillingPge,"Lines count mismatch: Expected is "+ExpectedLinesCountInBillingPge+" but got "+ActualLinesCountInBillingPge+" in Billing page");

		String ActualItemNameInBillingPge = base.GetText(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		asrt.assertTrue(ExpectedItemNameInItemDetailsPge.contains(ActualItemNameInBillingPge),"Item name mismatch: "+ExpectedItemNameInItemDetailsPge+" but found "+ActualItemNameInBillingPge+" in Billing page");

		String ActualGrossWeightInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5")).replace("-", "");
		String ActualGrossWeight = String.valueOf(Double.parseDouble(ActualGrossWeightInBillingPge));
		asrt.assertEquals(ExpectedGrossWeight, ActualGrossWeight,"Gross weight mismatch: "+ExpectedGrossWeight+" but found "+ActualGrossWeight+" in Billing page");

		String ActualTotalWithoutTaxInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTotalWithoutTax = String.valueOf(Double.parseDouble(ActualTotalWithoutTaxInBillingPge));		
		asrt.assertEquals(ExpectedTotalAmount, ActualTotalWithoutTax,"Total without tax mismatch: "+ExpectedTotalAmount+" but found "+ActualTotalWithoutTax+" in Billing page");

		String ExpectedSubTotalInBillingPge = ActualTotalWithoutTax;
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualSubTotal = String.valueOf(Double.parseDouble(ActualSubTotalInBillingPge));	
		asrt.assertEquals(ExpectedSubTotalInBillingPge, ActualSubTotal,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotal+" in Billing page");

		String ActualTaxInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ExpectedTaxInBillingPge = CashCollectionRepaymentLimitValidationTestData.TC170_OGTax;
		asrt.assertEquals(ExpectedTaxInBillingPge, ActualTaxInBillingPge,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in Billing page");

		String ExpectedTotalAmountInBillingPge = ExpectedSubTotalInBillingPge;	
		String ActualTotalAmountInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		String ActualTotalAmount = String.valueOf(Double.parseDouble(ActualTotalAmountInBillingPge));	
		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmount,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmount+" in Billing page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		//Step 11.Select payment method as Cash
		//Expected :Verify validation *Repayment to customer more than 10000 as cash not allowed
		base.buttonClick(LoginPageObj.Edt_AlertText("Cash"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));

		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h4 wrapTextWithoutHyphen wrapTextWithLineBreaks")),"Validation message Cash pay limit exceeded for this customer is not displayed in Payment page");						

		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 12. Again click on payment method RTGS NEFT 
		//Step 13. Post the invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		String ExpectedGrossWeightFromBillingPge = ActualGrossWeight;
		String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",ActualOGPurchaseRateInItemDetailsPge);

		Map<String, String> OgPurchaseBill = pdfUtils.OGPdfValidation(CashCollectionRepaymentLimitValidationTestData.TC170_PaymentDetails);
		String GrossWeightFromInvoice = OgPurchaseBill.get("GrossWt1");
		String GrossWeight = String.valueOf(Double.parseDouble(GrossWeightFromInvoice));	
		String NetWeightFromInvoice = OgPurchaseBill.get("NetWt1");
		String GoldRateFromInvoice = OgPurchaseBill.get("Rate1");
		String TotalAmountFromInvoice = OgPurchaseBill.get("TotalAmount1");
		String TotalAmount = String.valueOf(Double.parseDouble(TotalAmountFromInvoice));	
		String PaymentDetailsFromInvoice = OgPurchaseBill.get("PaymentDetails");
		String PaymentDetails = String.valueOf(Double.parseDouble(PaymentDetailsFromInvoice));
		String InvoiceNo = OgPurchaseBill.get("InvoiceNumber");

		asrt.assertEquals(GrossWeight,ExpectedGrossWeightFromBillingPge,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+GrossWeight+" in Invoice Pdf");		
		asrt.assertEquals(NetWeightFromInvoice,ExpectedNetWeightInItemDetailsPge,"Net Weight mismatch: "+ExpectedNetWeightInItemDetailsPge+" but got "+NetWeightFromInvoice+" in Invoice Pdf");	
		asrt.assertEquals(GoldRateFromInvoice,ExpectedOGPurchaseRateFromItemDetailsPge,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in Invoice Pdf");	
		asrt.assertEquals(TotalAmount,ExpectedTotalAmountInBillingPge,"Total amount mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+TotalAmount+" in Invoice Pdf");	
		asrt.assertEquals(PaymentDetails,ExpectedTotalAmountInBillingPge,"Payment details mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+PaymentDetails+" in Invoice Pdf");

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		//base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
	}

	// <summary>
	// Test Case Title : Cash collection from customer more than 200000
	// Automation ID : TC171
	// Author: Robin T Abraham
	// </summary>

	public void TC171_CashCollectionRepaymentLimitValidation() throws Exception {

		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);

		//Fetching Board Rate ()
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");
		System.out.println("CurrentGoldRate22k "+CurrentGoldRate22k);
		
		//Create a new customer with Pan Card.
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");
		String Custcode=appUtils.CreateANewCustomerWithPan("Yes");
		System.out.println("Custcode "+Custcode);

		//Step 2.Click on Transaction button
		//Step 3.Select customer
		//Step 4.Click on add to estimate button	
		appUtils.HamBurgerButtonClick("iconShop");

		Thread.sleep(3000);
		appUtils.SearchByCustomerID(Custcode, UtilityTestData.MenuBarOptions[5]);

		//Step 5. Scan SKU worth amount greater than 200000 and click on Add to cart button
		//Step 6. Select any one of the sales agent
		double SaleAmount = CashCollectionRepaymentLimitValidationTestData.TC171_SaleAmount;
		double RequiredSaleQty = SaleAmount / CurrentGoldRate22k;

		System.out.println("Required Sale Quantity"+RequiredSaleQty);

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter = 1;

		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				CashCollectionRepaymentLimitValidationTestData.TC171_TransferType,
				CashCollectionRepaymentLimitValidationTestData.TC171_FromCounterUncut,
				CashCollectionRepaymentLimitValidationTestData.TC171_MetalTypeUncut,
				RequiredSaleQty,
				CashCollectionRepaymentLimitValidationTestData.TC171_SkuCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");

		for (String Sku : SkuList) {

			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			//5.Check Calculation.
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal");
		String Discount         = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax              = TransactionDataLine.get("Tax");
		String TotalAmount      = TransactionDataLine.get("TotalAmount");
		String NetTotal         = TransactionDataLine.get("NetTotal");
		String LinesCount       = TransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 7. Click on Save to estimation button
		//Step 8. save esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// PDF Verification Step
		Thread.sleep(6000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC171_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue=SubTotal;
		String InvoiceTotal=TotalAmount;
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step 9.Recall estimate from cashier screen
		//Step 10. Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
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
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Note:Verify whether the recalled item is the same as the item in the cart.
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 11.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), CashCollectionRepaymentLimitValidationTestData.TC171_TransationType);

		//Step 12.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13.Choose a sales representative
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		appUtils.HandleSpecifyRateModalInRecall();

		//Step 14.Click on the Amount
		//Note: Note: Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		//Step 15.Select a Payment method(Cash).
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);

		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));

		//*Verify Validation(payment limit)
		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")),"Payment limit has been reached and verified.");
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 16.Again,Click on 
		//Step 17.Select payment method as cash(<=199999).
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));

		String CashAmount = CashCollectionRepaymentLimitValidationTestData.TC171_CashAmount;
		base.actionSetData( CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(CashAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));

		//Step 18.Again Click on balance amount.
		//Step 19.Select payment method card
		Thread.sleep(3000);
		String PaymentAmountCard = appUtils.PaymentAfterRecallEstimate("HDFC");
		double CalculatedNetValue =
				Double.parseDouble(CashAmount.replaceAll("[^\\d.]", "").trim()) +
				Double.parseDouble(PaymentAmountCard.replaceAll("[^\\d.]", "").trim());

		String NetValue = Double.toString(CalculatedNetValue);

		//Step 20.Post invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC171SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotal;
		String TotalDiscount=Discount;
		String TotalQtyPcs=LinesCount;
		String PdfInvoiceNo = pdfUtils.SaleInvoiceCashPdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				String.valueOf(PaymentAmountCard),
				String.valueOf(CashAmount),
				SkuList,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Make sale with advance settlement(advance amount paid by cash) and collect remaing amount by cash 199999/-
	// Automation ID : TC321
	// Author: Chandana Babu
	// </summary>
	public void TC321_CashCollectionRepaymentLimitValidation() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));		
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

		//Step 2: Click on Transaction button
		//appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(2000);
		String CustomerId =appUtils.CreateANewCustomerWithPan("Yes");
		Thread.sleep(2000);
		String AdvanceAmount = appUtils.CreateNormalAdvanceCashMop(
				CustomerId,
				CashCollectionRepaymentLimitValidationTestData.TC321_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		double RemainingCash = CashCollectionRepaymentLimitValidationTestData.TC321_CashMaxAmount-	Double.parseDouble(CashCollectionRepaymentLimitValidationTestData.TC321_AdvanceAmount);

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(CustomerId,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6: click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		//Step 7: Select advance and Enter amount
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

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		double Amount = Double.parseDouble(AdjustmentAmount);
		double Quantity22k = Amount / Double.parseDouble(AdvanceRate);
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRate, "22k");
		Thread.sleep(120000); // Wait needed for ERP Gold Rate to Change

		//Step 8: Click on cart button and Enter OTP
		//Step 9: Scan SKU
		//Expected: Check calculation
		//Note: Check average rate
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				CashCollectionRepaymentLimitValidationTestData.TC321_ProductCount,
				CashCollectionRepaymentLimitValidationTestData.TC321_TransferType,
				CashCollectionRepaymentLimitValidationTestData.TC321_FromCounter,
				CashCollectionRepaymentLimitValidationTestData.TC321_MetalType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("headerSplitViewToggleButton expandedNavButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h5 textLeft centerY", "Transaction"));
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames  		= new ArrayList<>();
		List<String> ProductQtyList	 	= new ArrayList<>();
		List<String> ProductTotalList 	= new ArrayList<>();
		ProductNames.add(CashCollectionRepaymentLimitValidationTestData.TC321_DepositAdjustment);
		ProductQtyList.add(CashCollectionRepaymentLimitValidationTestData.TC321_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		erpUtils.ResetRemainingReturnWeight();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber 	= i + 1;
			String Sku 		= SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);

			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber,Double.toString(Quantity22k),"22k",AdvanceRate,ErpGoldRateFor24K);

		}
		List<String> ItemNamesInBillingPageForFirstTransaction = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String NetTotal         = TransactionDataLineInBillingPge.get("NetTotal");
		String Tax              = TransactionDataLineInBillingPge.get("Tax");
		String Discount         = TransactionDataLineInBillingPge.get("Discount");
		String TotalNetWeight   = TransactionDataLineInBillingPge.get("NetWeight");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10: save estimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber 	= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName 	= pdfUtils.DownloadAndRenameLatestPDF("TC321_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath 	= System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst 					= Tax;
		String TaxableValue 		= ProductTotalList.get(1);
		String InvoiceTotal 		= Double.toString(Double.parseDouble(ProductTotalList.get(1)) + Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment 		= AdjustmentAmount;
		String TotalAmt 			= "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment, 
				NetTotal, 
				TotalAmt);

		//Step 11: Recall estimate from cashier screen
		//Step 12: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPageForFirstTransaction = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));

		asrt.assertTrue(ItemNamesInBillingPageForFirstTransaction.containsAll(ItemNamesFromRecallPageForFirstTransaction), "Recalled item is not the same as the item in the cart");

		//Step 13: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),CashCollectionRepaymentLimitValidationTestData.TC321_TransactionType);

		//Step 14: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 15: Choose a sales representative
		//Step 16: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines. Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String AmountDueInCashierScreen = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		double AmountDue = Double.parseDouble(AmountDueInCashierScreen);
		double RemainingCashForSecondTransaction 	= 0.00;
		String PaymentViaCard 						= "";
		String PaymentAmountCashForFirstTransaction = "";

		if(AmountDue>RemainingCash) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(1000);
			base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
			base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(RemainingCash));
			base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			Thread.sleep(2000);
			PaymentAmountCashForFirstTransaction = String.valueOf(RemainingCash);
			PaymentViaCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		}
		else {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			PaymentAmountCashForFirstTransaction = String.valueOf(Math.round(AmountDue));
			RemainingCashForSecondTransaction = RemainingCash-Double.parseDouble(PaymentAmountCashForFirstTransaction);
			wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("Close")));
			if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
			}
		}

		//Step 17: Select a Payment method(Cash or Card)
		//Step 18: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice 	= pdfUtils.DownloadAndRenameLatestPDF("TC321TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath 	= System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount 		= ProductTotalList.get(1);
		String TotalDiscount 	= Discount;
		String TotalQtyPcs 		= Integer.toString(SkuList.size());
		String NetValue 		= NetTotal;
		String TotalGrossWeight = ProductQtyList.get(1);		
		String PdfInvoiceNo   	= pdfUtils.SaleInvoiceWithCashCardAndAdvancePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentViaCard,
				PaymentAmountCashForFirstTransaction,
				AdvanceAmount,
				SkuList,
				ScannedSkuDataMap);	
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		}

		//Step 1: Login to POS
		//Step 2: Click on Transaction button
		//Step 3: Select customer		
		//Step 4: Click on add to estmate button
		appUtils.SearchByCustomerID(CustomerId,UtilityTestData.MenuBarOptions[5]);
		Thread.sleep(1000);
		String CustomerNo = base.GetText(LoginPageObj.Ele_ErrorMessage("h6 ellipsis primaryFontColor"));

		//Step 5: Scan gold SKU and click on Add to cart button
		//Expected: Check calculation
		//Step 6: Scan another gold SKU and click on Add to cart button
		//Expected: Check calculation
		//Step 7: Select any one of the seles agent
		List<String> SkuListGold = appUtils.SelectMultipleSKUs(
				CashCollectionRepaymentLimitValidationTestData.TC321_SkuGoldCount,
				CashCollectionRepaymentLimitValidationTestData.TC321_TransferType,
				CashCollectionRepaymentLimitValidationTestData.TC321_FromCounter,
				CashCollectionRepaymentLimitValidationTestData.TC321_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> SaleProductNames = new ArrayList<>();
		List<String> SaleProductQtyList = new ArrayList<>();
		List<String> SaleProductTotalList = new ArrayList<>();
		Map<String, String> ScannedSkuDataMapGold = new LinkedHashMap<>();
		for (int i = 0; i < SkuListGold.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuListGold.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMapGold);
			SaleProductNames.add(ScannedSkuDataMapGold.get("SKU_" + SkuNumber + "_HeaderName"));
			SaleProductQtyList.add(ScannedSkuDataMapGold.get("SKU_" + SkuNumber + "_GrossWeight"));
			SaleProductTotalList.add(ScannedSkuDataMapGold.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentGoldSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMapGold, SkuNumber);

			mathUtils.SkuIngredientItemCalculation(CurrentGoldSkuData, SkuNumber);

		}
		List<String> ItemNamesInBillingPageForSecondTransaction = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMapGold);
		String SubTotalForSecondTransaction = TransactionDataLine.get("Subtotal");
		String DiscountForSecondTransaction = TransactionDataLine.get("Discount");
		String TotalGrossWeightForSecondTransaction = TransactionDataLine.get("GrossWeight");
		String TotalNetWeightForSecondTransaction = TransactionDataLine.get("NetWeight");
		String TaxForSecondTransaction = TransactionDataLine.get("Tax");
		String TotalAmountForSecondTransaction = TransactionDataLine.get("TotalAmount");
		String NetTotalForSecondTransaction = TransactionDataLine.get("NetTotal");
		String LinesCountForSecondTransaction = TransactionDataLine.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, SaleProductNames, SaleProductQtyList, SaleProductTotalList, "Billing");

		//Step 8: Click on Save to estimation button
		//Step 9: save esimate
		//Expected: Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumberForSecondTransaction = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoice 					= pdfUtils.DownloadAndRenameLatestPDF("TC321_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePathName 			= System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoice;
		String GstForSecondTransaction			= TaxForSecondTransaction;
		String TaxableValueForSecondTransaction	=SubTotalForSecondTransaction;
		String InvoiceTotalForSecondTransaction	=TotalAmountForSecondTransaction;
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePathName,
				TaxableValueForSecondTransaction,
				GstForSecondTransaction,
				InvoiceTotalForSecondTransaction,
				NetTotalForSecondTransaction,
				LinesCountForSecondTransaction,
				Double.parseDouble(TotalGrossWeightForSecondTransaction),
				Double.parseDouble(TotalNetWeightForSecondTransaction));

		//Step 10: Recall estimate from cashier screen
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumberForSecondTransaction);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumberForSecondTransaction);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumberForSecondTransaction));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumberForSecondTransaction = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumberForSecondTransaction, ExpectedEstmnNumberForSecondTransaction,"Mismatch in Estimation number Actual is " + ActualEstmnNumberForSecondTransaction + " and Expected Estimation number"+ ExpectedEstmnNumberForSecondTransaction + " in recall estimation page");

		List<String> ItemNamesFromRecallPageForSecondTransaction = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));

		asrt.assertTrue(ItemNamesInBillingPageForSecondTransaction.containsAll(ItemNamesFromRecallPageForSecondTransaction), "Recalled item is not the same as the item in the cart");

		//Step 11: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), CashCollectionRepaymentLimitValidationTestData.TC321_TransationType);

		//Step 12: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Choose a sales representative
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionDataLineAfterRecall = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMapGold);

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineAfterRecall, SaleProductNames, SaleProductQtyList, SaleProductTotalList, "Cashier");

		//Step 14: Click on the Amount
		//Step 15: Select a Payment method(Cash or Card)
		String AmountDueInCashierScreenForSecondTransaction = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		double AmountDueForSecondTransaction 				= Double.parseDouble(AmountDueInCashierScreenForSecondTransaction);
		String PaymentHDFCCard 								= "";
		String PaymentAmountCashForSecondTransaction 		= "";

		if(AmountDueForSecondTransaction>RemainingCashForSecondTransaction && RemainingCashForSecondTransaction != 0.00) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(2000);
			base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
			base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(RemainingCashForSecondTransaction));
			base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			Thread.sleep(2000);
			PaymentAmountCashForSecondTransaction = String.valueOf(RemainingCashForSecondTransaction);
			PaymentHDFCCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		}
		else if (AmountDueForSecondTransaction<RemainingCashForSecondTransaction && RemainingCashForSecondTransaction != 0.00) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			PaymentAmountCashForSecondTransaction = String.valueOf(Math.round(AmountDueForSecondTransaction));
			wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("Close")));
			if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
			}
		}
		else if (RemainingCashForSecondTransaction == 0.00) {
			PaymentHDFCCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();			
		}

		//Step 16: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoiceForSecondTransaction 	= pdfUtils.DownloadAndRenameLatestPDF("TC321SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPathForSecondTransaction 	= System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoiceForSecondTransaction;
		String GrossAmountForSecondTransaction 		= SubTotalForSecondTransaction;
		String TotalDiscountForSecondTransaction	=DiscountForSecondTransaction;
		String TotalQtyPcsForSecondTransaction		=LinesCountForSecondTransaction;
		String NetValueForSecondTransaction			=NetTotalForSecondTransaction;		
		String PdfInvoiceNoForSecondTransaction   	= pdfUtils.SaleInvoiceCashPdfValidation(
				InvoicePdfPathForSecondTransaction,
				GrossAmountForSecondTransaction,
				TotalDiscountForSecondTransaction,
				TotalQtyPcsForSecondTransaction,
				TotalNetWeightForSecondTransaction,
				TotalGrossWeightForSecondTransaction,
				TaxableValueForSecondTransaction,
				NetValueForSecondTransaction,
				PaymentHDFCCard,
				PaymentAmountCashForSecondTransaction,
				SkuListGold,
				ScannedSkuDataMapGold);	

		double DailyCash = 0.00;
		if(PaymentAmountCashForSecondTransaction !="") {
			DailyCash = Double.parseDouble(PaymentAmountCashForFirstTransaction)+Double.parseDouble(PaymentAmountCashForSecondTransaction)+Double.parseDouble(AdvanceAmount);
		}
		else {
			DailyCash = Double.parseDouble(PaymentAmountCashForFirstTransaction)+Double.parseDouble(AdvanceAmount);			
		}
		String Adjustment =  AdvanceAmount;		

		//Verify PMLA Cash tracker
		TimeUnit.MINUTES.sleep(6);
		erpUtils.VerifyPMLACashTracker(CustomerNo,  Double.toString(DailyCash), Adjustment);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : make sale with scheme settlement(advance amount paid by cash) and take collection(Scheme) remaining amount by cash 199999/-
	// Automation ID : TC323
	// Author: Chandana Babu
	// </summary>
	public void TC323_CashCollectionRepaymentLimitValidation() throws Exception {	

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));	

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//*******************************
		/*//Step 2: Click on Transaction button
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");*/
		//**********************************

		//Prerequisite Customer must have a matured GB scheme
		Thread.sleep(3000);
		// need to write a new method 
		Map<String, Map<String, Object>> CashMopScehemesFromErp =erpUtils.CashMopSchemes("GPP","GB",1,100);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		List<String> DepositIds = CashMopScehemesFromErp.values()
				.stream()
				.map(Details -> (String) Details.get("DepositId"))
				.distinct()
				.collect(Collectors.toList());
		String CustomerAccount = "";
		String CurrentDepositId = "";
		boolean ValidAccountFound = false;

		for (int idx = 0; idx < DepositIds.size(); idx++) {
			//for (int idx = DepositIds.size() - 1; idx >= 0; idx--) {

			CurrentDepositId = DepositIds.get(idx);
			String ValidDepositId = CurrentDepositId;

			CustomerAccount = CashMopScehemesFromErp.values()
					.stream()
					.filter(details -> ValidDepositId.equals(details.get("DepositId"))) 
					.map(details -> (String) details.get("CustomerID"))                    
					.collect(Collectors.joining(", "));   

			//Step 3: Select customer
			//Step 4: Click on add to estimation button
			appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

			//Step 5: Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			//Step 6: click on GPP advance settlement
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			//Step 7: Enter scheme id and click on add to cart button
			//Expected: Verify whether the entered GB scheme gets added 
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), CurrentDepositId);
			Thread.sleep(2000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
			// Check if "No data" is displayed
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) {
				System.out.println("No data for account: " + CurrentDepositId + ". Retrying with next...");
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue; // go to next MatchedAccounts entry
			}
			System.out.println("Valid GPP account found: " + CurrentDepositId);
			ValidAccountFound = true;
			break;
		}		
		if (!ValidAccountFound) {
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}

		Thread.sleep(2000);	
		appUtils.AddOrVerifyPan();
		Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(CurrentDepositId);
		String GPPAmount = (String) GPPMaturityValues.get("Amount");
		Thread.sleep(5000);

		//To Find the number of installemt
		Map<String, Double> CashMopSchemeDetails = erpUtils.CashSchemeDatails(CustomerAccount, CurrentDepositId);
		double IntallmentMonths = CashMopSchemeDetails.getOrDefault("InstallmentMonths",0.00);
		double TotalCashMop = CashMopSchemeDetails.getOrDefault("TotalCashMop",0.00);
		System.out.println("IntallmentMonths: "+IntallmentMonths);
		System.out.println("TotalCashMop: "+TotalCashMop);

		double BenefitPercent = 0.00;
		if (6<= IntallmentMonths && IntallmentMonths<=10) {
			BenefitPercent =0.1;

		}
		else if(11<= IntallmentMonths){
			BenefitPercent =0.2;
		}
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");
		//**********************************

		/*//Step 3: Select customer
		//Step 4: Click on add to estimation button
		String CustomerAccount = "20CA00478072";
		String	CurrentDepositId = "20GB000043435";

		appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

		//Step 6: Click on GPP Advance Adjustment
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

		//Step 7: Enter scheme id(GG) and click on "Add to cart" button
		//Expected :Verify whether the entered GG scheme gets add
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),CurrentDepositId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"ENTER");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Map<String, Double> CashMopSchemeDetails = erpUtils.CashSchemeDatails(CustomerAccount, CurrentDepositId);
		double IntallmentMonths = CashMopSchemeDetails.get("InstallmentMonths");
		double TotalCashMop = CashMopSchemeDetails.get("TotalCashMop");
		double BenefitPercent = 0.00;
		if (6<= IntallmentMonths && IntallmentMonths<=10) {
			BenefitPercent =0.1;

		}
		else if(11<= IntallmentMonths){
			BenefitPercent =0.2;
		}
		Thread.sleep(5000);
		Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(CurrentDepositId);
		String GPPAmount = (String) GPPMaturityValues.get("Amount");
		System.out.println("GPPAmount: "+GPPAmount);*/
		//**********************************

		Thread.sleep(5000);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		asrt.assertEquals(ActualSchemeAmount, GPPAmount.replaceAll("[^\\d.]", "").trim(),"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		double RemainingCash = CashCollectionRepaymentLimitValidationTestData.TC323_CashMaxAmount-TotalCashMop;

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
		//Step 9: Scan multiple DMD SKU's
		//Expected: Check calculation	
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				CashCollectionRepaymentLimitValidationTestData.TC323_TransferType,
				CashCollectionRepaymentLimitValidationTestData.TC323_FromCounterDiamond,
				CashCollectionRepaymentLimitValidationTestData.TC323_MetalTypeGold,
				RequiredWeight,
				CashCollectionRepaymentLimitValidationTestData.TC323_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double TotalDiamondValue = 0.0;
		ProductNames.add(CashCollectionRepaymentLimitValidationTestData.TC323_SchemeName);
		ProductQtyList.add(CashCollectionRepaymentLimitValidationTestData.TC323_Weight);
		ProductTotalList.add(GPPAmount.replaceAll("[^\\d.]", "").trim());
		int SkuCounter = 1;	
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalDiamondValue = TotalDiamondValue +Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueDIAMOND"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		double ActualBonus = 0.00;
		if(BenefitPercent != 0.00) {
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
			base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
			base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
			base.setZoom(driver, 60);
			Thread.sleep(30000);
			String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
			ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
			double ExpectedBonus = 0.0;
			Thread.sleep(2000);
			if (TotalDiamondValue>SchemeAmount) {
				ExpectedBonus = SchemeAmount*BenefitPercent;
			}
			else {
				ExpectedBonus = TotalDiamondValue*BenefitPercent;
			}
			Thread.sleep(2000);
			asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ExpectedBonus+" but got "+ActualBonus+" in billing page");
			ProductNames.add(CashCollectionRepaymentLimitValidationTestData.TC323_BonusName);
			ProductQtyList.add(CashCollectionRepaymentLimitValidationTestData.TC323_Weight);
			ProductTotalList.add(Bonus);
		}
		else{
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
			base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
			base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
			base.setZoom(driver, 60);
			Thread.sleep(15000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"))){
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}		
		}

		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 11: Save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(4000);
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC323_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));
		String InvoiceTotal= Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment = Double.toString(SchemeAmount+ActualBonus);
		String TotalAmt  =""; 
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
		//Expected: Verify whether the recalled item is the same as the item in the cart.		
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
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
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in estimation page"); 

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 14: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), CashCollectionRepaymentLimitValidationTestData.TC323_TransactionTypeSales);

		//Step 15: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 17: Click on the amount
		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		//Step 18: Select a Payment method(Cash or Card)
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 	= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 	    = TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 				= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 		= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen	= TransactionDataLineInCashierScreen.get("LinesCount");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String AmountDueInCashierScreen = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		double AmountDue = Double.parseDouble(AmountDueInCashierScreen);
		double RemainingCashForSecondTransaction = 0.00;
		String PaymentViaCard = "";
		String PaymentAmountCashForFirstTransaction = "";

		if(AmountDue>RemainingCash && RemainingCash > 0.00) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(1000);
			base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
			base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(RemainingCash));
			base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			Thread.sleep(2000);
			PaymentAmountCashForFirstTransaction = String.valueOf(RemainingCash);

			//Remaining payment via card
			PaymentViaCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		}
		else if (AmountDue<RemainingCash && RemainingCash > 0.00){
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			PaymentAmountCashForFirstTransaction = String.valueOf(Math.round(AmountDue));
			wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("Close")));
			if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
			}
		}
		else {
			PaymentViaCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();	
		}

		//Step 19: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC323TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentViaCard;
		String TotalValue = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String NetValue = NetTotalInCashierScreen;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String TotalGrossWeight = String.format("%.3f",Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2)));
		Map<String, String> PdfDetails = pdfUtils.ValidateTaxInvoiceSchemeSettlementWithCashAndCardPdf(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				TotalValue,
				PaymentAmountCashForFirstTransaction,
				PaymentHDFCCard,
				GppAdvance,
				GppDiscount,
				TotalAdjustment,
				NetValue);

		//Step 20: Click on "INSURANCE CERTIFICATE" check box
		//Thread.sleep(3000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));

		//Step 21: Click on print button
		//Expected: Verify INSURANCE CERTIFICATE
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		//pdfUtils.ValidateInvoiceValue(TotalValue);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		}

		//-------------------SecondTransaction-------------------------------------
		//Step 1: Login to POS
		//Step 2: Go to GPP
		base.ClickCondition(LoginPageObj.Edt_AlertText("GPP"));

		//Step 3: Select New GPP Account Opening
		WebDriverWait Wait =new WebDriverWait(driver, Duration.ofSeconds(50));
		Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Btn_return("New GPP Account")));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_return("New GPP Account"));

		//Step 4: Search customer phone number
		//Step 5: Add to GPP ac open
		appUtils.SearchByCustomerID(CustomerAccount ,UtilityTestData.MenuBarOptions[6]);

		//Step 6: Select scheme code(GS)
		//Expected: Verify that selecting Scheme Code "GS" sets the installment amount to the default minimum of "1" and is also editable
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("SchmTypeOptions"),CashCollectionRepaymentLimitValidationTestData.TC323_Scheme);
		Thread.sleep(1000);
		String DefaultGSInstallmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("InstlamtInput"));
		asrt.assertEquals(DefaultGSInstallmentAmount, CashCollectionRepaymentLimitValidationTestData.TC323_DefaultInstallmentAmount,"Default GS Installment Amount mismatch: Expected is "+CashCollectionRepaymentLimitValidationTestData.TC323_DefaultInstallmentAmount+" but got "+DefaultGSInstallmentAmount+" in GPP account opening page");

		//Step 7: Enter installment amount	
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("InstlamtInput"), Double.toString(TotalCashMop));
		Thread.sleep(1000);
		String ActualInstallmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("InstlamtInput"));
		asrt.assertEquals(Double.parseDouble(ActualInstallmentAmount), TotalCashMop,1.0,"Installment Amount mismatch: Expected is "+TotalCashMop+" but got "+ActualInstallmentAmount+" in GPP account opening page");


		//Step 8: Select sales person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"), 1);

		//Step 9: Select ID Proof 1
		//Step 10: Enter ID Proof number
		//Note: If customer doesn't have bank account add bank details
		String IdProofNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("Idproofno1"));
		if (IdProofNumber == null || IdProofNumber.trim().isEmpty()) {
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("IdTypeOptions"), CashCollectionRepaymentLimitValidationTestData.TC323_IdProofType);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("Idproofno1"), CashCollectionRepaymentLimitValidationTestData.TC323_IdProofNo);
		}

		//Step 11: Select bank proof type
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("BankProof"),CashCollectionRepaymentLimitValidationTestData.TC323_BankProof);

		//Step 12: Enter nominee name
		base.setData(GppOpeningAndCollectionMultiplePlansObj.Sel_GPPNomineeName("NomineeName"),UtilityTestData.NomineeName);

		//Step 13: Select nominee relation
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("NomineeRelation"),UtilityTestData.NomineeRelation);

		//Step 14: Click on check box( same as above)
		base.ClickCondition(GppOpeningAndCollectionMultiplePlansObj.Sel_GPPNomineeName("IsSameAsAbove"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);

		//Step 15: Click on save button
		//Expected: Verify Application ID, Scheme Details, Customer Details, Nominee Details, Bank Details			
		String CustomerName=base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("CustNmInput"));
		String SchemeDetails=base.getTheSelectedTextInDropDown(NormalSaleGoldAndSilverObj.Sel_DepositType("SchmTypeOptions"));
		String NomineeName=UtilityTestData.NomineeName;
		String BankAccountNo=base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("Bnkacno"));
		String BankIFSC=base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("Bnkcode"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_return("View more"));
		Thread.sleep(1000);	
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("SearchView_AppBarView_appBarCommandCommand"));
		Thread.sleep(1000);	
		Map<String, String> NewSchemePdfDetails = pdfUtils.ExtractDetailsFromGPPPdf(CashCollectionRepaymentLimitValidationTestData.TC323_NewSchemeCode);
		String ApplicationIdPdf   = NewSchemePdfDetails.get("ApplicationID");
		String SchemePdf          = NewSchemePdfDetails.get("Plan");
		String CustomerNamePdf    = NewSchemePdfDetails.get("CustomerName");
		String NomineeNamePdf     = NewSchemePdfDetails.get("NomineeName");
		String BankAccountNoPdf   = NewSchemePdfDetails.get("BankAccountNo");
		String IFSCCodePdf        = NewSchemePdfDetails.get("IFSCCode");
		asrt.assertNotNull(ApplicationIdPdf,"Application ID " + ApplicationIdPdf + " not found in Golden Bliss Wedding Advance Plan PDF content.");
		asrt.assertEquals(SchemePdf, SchemeDetails,"Scheme name mismatch: Expected is "+SchemeDetails+" but got "+SchemePdf+" in Golden Bliss Wedding Advance Plan Pdf");		
		asrt.assertEquals(CustomerNamePdf.replaceAll("\\s+", " ").trim(), CustomerName.replaceAll("\\s+", " ").trim(),"Customer name Mismatch: Expected is "+CustomerName+" but got "+CustomerNamePdf+" in  Golden Bliss Wedding Advance Plan Pdf");		
		asrt.assertEquals(NomineeNamePdf, NomineeName,"Nominee name mismatch: Expected is "+NomineeName+" but got "+NomineeNamePdf+" in  Golden Bliss Wedding Advance Plan Pdf");		
		asrt.assertEquals(BankAccountNoPdf, BankAccountNo,"Bank Account number mismatch: Expected is"+BankAccountNo+" but got "+BankAccountNoPdf+" in  Golden Bliss Wedding Advance Plan Pdf");		
		asrt.assertEquals(IFSCCodePdf, BankIFSC,"Bank IFSC code mismatch: Expected is "+BankIFSC+" but got "+IFSCCodePdf+" in  Golden Bliss Wedding Advance Plan Pdf");		

		//Step 16: Select customer
		//Step 17: Click on Add to sale button
		appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[0]);

		//Step 18: Click on GPP
		base.ClickCondition(LoginPageObj.Edt_AlertText("GPP"));

		//Step 19: GPP collection entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("GPP Collection Entry"));
		Thread.sleep(3000);
		if(base.isPresent(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}

		//Step 20: Choose deposit type
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"),CashCollectionRepaymentLimitValidationTestData.TC323_DepositType);

		//Step 21: Enter transaction number
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), ApplicationIdPdf);
		String ExpectedDepositAmount = String.format("%.2f",Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"))));

		//Step 22: Select sales person
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);
		//base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"),UtilityTestData.SalePersonName);		 

		//Step 23: Click on deposit
		//Note: Billing screen: Total Amount
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		Thread.sleep(1000);
		String ActualDepositAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(ActualDepositAmount, ExpectedDepositAmount,"Deposit amount mismatch: Expected is "+ExpectedDepositAmount+" but got "+ActualDepositAmount+" in billing page");

		//Step 24: Click on the Amount	
		//Step 25: Select a Payment method(Cash or Card)
		String AmountDueInCashierScreenForSecondTransaction = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		double AmountDueForSecondTransaction = Double.parseDouble(AmountDueInCashierScreenForSecondTransaction);
		String PaymentCard = "";
		String PaymentAmountCashForSecondTransaction = "";
		String ExpectedPaymentMode = "";

		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC323_CashPaymentMethod));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		PaymentAmountCashForSecondTransaction = String.valueOf(Math.round(AmountDueForSecondTransaction));
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("Close")));
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		}
		ExpectedPaymentMode = CashCollectionRepaymentLimitValidationTestData.TC323_CashPaymentMethod;

		Map<String, String> PaymentDetails = pdfUtils.ExtractPaymentDetailsFromPdf();
		String ActualAmountInGppPdf = PaymentDetails.get("Amt");
		String ActualCumulativeAmountInGppPdf = PaymentDetails.get("CumAmt");			
		String ActualPaymentModeInGppPdf = PaymentDetails.getOrDefault("PaymentMode", "").trim();
		String ActualApplicationIdInGppPdf = PaymentDetails.getOrDefault("ApplicationID", "").trim();

		String ExpectedAmountInGppPdf = ActualDepositAmount;
		String ExpectedApplicationIdInGppPdf= ApplicationIdPdf;

		asrt.assertEquals(ActualAmountInGppPdf,ExpectedAmountInGppPdf,"Amount mismatch: Expected is "+ExpectedAmountInGppPdf+" but got "+ActualAmountInGppPdf+" in Gpp Reciept PDF");
		asrt.assertEquals(ActualCumulativeAmountInGppPdf,ExpectedAmountInGppPdf, "Cummilative Amount mismatch: Expected is " + ExpectedAmountInGppPdf + " but found " + ActualCumulativeAmountInGppPdf+ " in Gpp Reciept PDF");
		asrt.assertEquals(ActualPaymentModeInGppPdf.replaceAll("\\s+", " ").trim(),ExpectedPaymentMode.replaceAll("\\s+", " ").trim(),"Payment mode mismatch: Expected " + ExpectedPaymentMode + ", but found " + ActualPaymentModeInGppPdf+ " in Gpp Reciept PDF");
		asrt.assertEquals(ActualApplicationIdInGppPdf,ExpectedApplicationIdInGppPdf,"Application Id mismatch: Expected " + ExpectedApplicationIdInGppPdf + ", but found " + ActualApplicationIdInGppPdf +" in Gpp Reciept PDF");

		double DailyCash = 0.00;
		if(PaymentAmountCashForFirstTransaction !="") {
			DailyCash = Double.parseDouble(PaymentAmountCashForFirstTransaction)+Double.parseDouble(PaymentAmountCashForSecondTransaction);
		}
		else {
			DailyCash = Double.parseDouble(PaymentAmountCashForSecondTransaction);			
		}
		String Adjustment = Double.toString(TotalCashMop);

		//Verify PMLA Cash tracker
		TimeUnit.MINUTES.sleep(6);
		erpUtils.VerifyPMLACashTracker(CustomerAccount, Double.toString(DailyCash), Adjustment);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	//<summary>
	// Test Case Title : check add-on advance cash validation
	// Automation ID : TC324
	// Author : Aiswarya
	// </summary>
	public void TC324_CashCollectionRepaymentLimitValidation() throws Exception{

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		String CustomerId =appUtils.CreateANewCustomerWithPan("Yes");

		Map<String, String> OfferDetails = appUtils.OfferAdvanceParentCashmop(CustomerId,
				CashCollectionRepaymentLimitValidationTestData.TC324_Pieces,
				CashCollectionRepaymentLimitValidationTestData.TC324_GrossWeight,
				UtilityTestData.Company[0],
				UtilityTestData.Location[0],
				CashCollectionRepaymentLimitValidationTestData.TC324_DepositPercent);

		String OfferAdvanceId              = OfferDetails.get("OrderId");
		String ExpectedOfferExpiryDate     = OfferDetails.get("OfferExpiryDate");
		String WillingToPayAmount          = OfferDetails.get("WillingToPayAmount");
		double ActualInitialAdvanceReceived = Double.parseDouble(OfferDetails.get("AdvanceReceived"));
		double ExpectedTotalAdvanceAmount       = Double.parseDouble(OfferDetails.get("AdvanceAmount"));
		double ExpectedFixedGoldRate       = Double.parseDouble(OfferDetails.get("FixedGoldRate"));
		int    ExpectedInitialValidityDays = Integer.parseInt(OfferDetails.get("ValidityDays").toString());
		String ExpectedDepositType         = CashCollectionRepaymentLimitValidationTestData.TC324_DepositType;
		double ExpectedApproxWeight         = Double.parseDouble(CashCollectionRepaymentLimitValidationTestData.TC324_GrossWeight);  

		System.out.println("ExpectedTotalAdvanceAmount:::" + ExpectedTotalAdvanceAmount );

		// Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: Click on Add to Sale button
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(CustomerId,UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(1000);
		String CustomerNo = base.GetText(LoginPageObj.Ele_ErrorMessage("h6 ellipsis primaryFontColor"));

		// Step 5: Click on Advance button at the transaction screen
		base.waitForClickable(LoginPageObj.Edt_AlertText("Advance"),15);
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance"));

		// Step 6: Click on Advance Collection button
		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"),15);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"));

		if (base.isExists(LoginPageObj.Btn_SingnIn("Button1Close"))) {
			base.waitForClickable(LoginPageObj.Btn_SingnIn("Button1Close"),15);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}

		// Step 7: Select deposit type as Order
		Thread.sleep(5000);
		base.waitForVisible(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"));
		Thread.sleep(5000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"), CashCollectionRepaymentLimitValidationTestData.TC324_Deposit);

		// Step 8: Choose transaction number from the dropdown
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"));
		Thread.sleep(5000);
		base.waitForVisible(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"));	
		Thread.sleep(5000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"),OfferAdvanceId);

		// Step 9: Enter deposit amount
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),WillingToPayAmount);

		// Step 10: Select sales person
		base.waitForClickable(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"),15);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));

		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);

		// Step 11: Click on Deposit button
		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"),15);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));

		// Step 12: Click on Amount
		base.waitForClickable(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"),15);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Thread.sleep(5000);

		// Step 13: Select a payment method (Cash)
		// Note: Verify validation
		// Expected : Verify cashlimit validation
		// Step 14: Again click on Amount and select payment method (Card)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(WillingToPayAmount));
		double ExpectedAdvanceAmount = Double.parseDouble(base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", ""));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(1000);
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("exceed"))) {  //Monthly cash transaction limit has exceeded for this customer
			asrt.assertTrue(base.isPresent(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("exceed")),"A validation message showing - Monthly cash transaction limit has exceeded for this customer- didnt appear in Payment page");
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("buttonIcon center iconBack"));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

		}
		Thread.sleep(1000);
		System.out.println("ExpectedTotalAdvanceAmount:::" + ExpectedTotalAdvanceAmount );


		// Step 15: Post the Invoice
		// Expected : Check ADVANCE RECEIPT VOUCHER details Towards the advance against purchase of approximate value,Fixed Gold rate,Deposit Type,Advance Received,Validity ,Offer Expriry date, save receipt id for future reference
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		String NewPdfName = PdfUtilities.DownloadAndRenameLatestPDF("OfferAdvanceAddonCollection");
		Map<String, String> AdvanceReceiptPdf = pdfUtils.OfferAdvancePdfValidation();
		double ActualAdvanceReceived      = Double.parseDouble(AdvanceReceiptPdf.get("AdvanceReceived"));
		double ActualApproxWeight         = Double.parseDouble(AdvanceReceiptPdf.get("ApproxWeight"));
		double ActualFixedGoldRate        = Double.parseDouble(AdvanceReceiptPdf.get("FixedGoldRate"));
		String ActualDepositType          = AdvanceReceiptPdf.get("DepositType");
		int    ActualValidityDays         = Integer.parseInt(AdvanceReceiptPdf.get("ValidityDays").toString());
		String ActualOfferExpiryDate      = AdvanceReceiptPdf.get("OfferExpiryDate");
		//double PercentUsed                = ((ActualAdvanceReceived + ActualInitialAdvanceReceived) / ExpectedTotalAdvanceAmount) * 100;
		double PercentUsed = Math.ceil((((ActualAdvanceReceived + ActualInitialAdvanceReceived) / ExpectedTotalAdvanceAmount) * 100) * 100) / 100;

		int    ExpectedValidityDays;
		if (PercentUsed >= 3 && PercentUsed <= 4.99)        ExpectedValidityDays = 15;
		else if (PercentUsed >= 5 && PercentUsed <= 9.99)   ExpectedValidityDays = 30;
		else if (PercentUsed >= 10 && PercentUsed <= 19.99) ExpectedValidityDays = 60;
		else if (PercentUsed >= 20 && PercentUsed <= 34.99) ExpectedValidityDays = 90;
		else if (PercentUsed >= 35 && PercentUsed <= 49.99) ExpectedValidityDays = 120;
		else if (PercentUsed >= 50 && PercentUsed <= 74.99) ExpectedValidityDays = 180;
		else if (PercentUsed >= 75 && PercentUsed <= 99.99) ExpectedValidityDays = 330;
		else if (PercentUsed >= 100)                        ExpectedValidityDays = 345;
		else ExpectedValidityDays = 0;
		System.out.println("PercentUsed:::" + PercentUsed );
		System.out.println("ExpectedValidityDays:::" + ExpectedValidityDays );


		DateTimeFormatter DateFormatter   = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate InitialExpiryDate       = LocalDate.parse(ExpectedOfferExpiryDate, DateFormatter);
		LocalDate UpdatedExpiryDate       = InitialExpiryDate.plusDays(ExpectedValidityDays-ExpectedInitialValidityDays);
		String    ExpectedExpiryDate      = UpdatedExpiryDate.format(DateFormatter); 

		String ExpectedDailyCashAmount = Double.toString((ActualAdvanceReceived + ActualInitialAdvanceReceived));
		System.out.println("ExpectedDailyCashAmount:::" + ExpectedDailyCashAmount );

		asrt.assertEquals(ActualAdvanceReceived,ExpectedAdvanceAmount,1,"Advance Received mismatch: Expected is "+ExpectedAdvanceAmount+" but found "+ActualAdvanceReceived+" in Advance Receipt");
		asrt.assertEquals(ActualApproxWeight,ExpectedApproxWeight,1,"ApproxWeight mismatch: Expected is "+ExpectedApproxWeight+" but found "+ActualApproxWeight+" in Advance Receipt");
		asrt.assertEquals(ActualFixedGoldRate,ExpectedFixedGoldRate,1,"Fixed Gold Rate mismatch: Expected is "+ExpectedFixedGoldRate+" but found "+ActualFixedGoldRate+" in Advance Receipt");
		asrt.assertEquals(ActualDepositType,CashCollectionRepaymentLimitValidationTestData.TC324_DepositType,"Deposit Type mismatch: Expected is "+ CashCollectionRepaymentLimitValidationTestData.TC324_DepositType+" but found "+ActualDepositType+" in Advance Receipt");
		asrt.assertEquals(ActualValidityDays,ExpectedValidityDays,"Validity Days mismatch: Expected is "+ExpectedValidityDays+" but found "+ActualValidityDays+" in Advance Receipt");
		asrt.assertEquals(ActualOfferExpiryDate,ExpectedExpiryDate,"Offer Expiry Date mismatch: Expected is "+ExpectedOfferExpiryDate+" but found "+ExpectedExpiryDate+" in Advance Receipt");		


		// Verify PMLA Cash Tracker
		TimeUnit.MINUTES.sleep(6);
		erpUtils.VerifyPMLACashTracker(CustomerNo, ExpectedDailyCashAmount, "");  

	}

	// <summary>
	// Test Case Title :make sale with advance settlement(advance amount paid by cash) and take collection(Advance) remaing amount by cash 199999/-
	// Automation ID : TC322
	// Author: Christy Reji
	// </summary>
	public void TC322_CashCollectionRepaymentLimitValidation() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));		
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		String CustomerId    = appUtils.CreateANewCustomerWithPan("Yes");
		String AdvanceAmount = appUtils.CreateNormalAdvanceCashMop(
				CustomerId,
				CashCollectionRepaymentLimitValidationTestData.TC322_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		double RemainingCash = CashCollectionRepaymentLimitValidationTestData.TC321_CashMaxAmount-	Double.parseDouble(CashCollectionRepaymentLimitValidationTestData.TC322_AdvanceAmount);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));

		}

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(CustomerId,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6: click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		//Step 7: Select advance and Enter amount
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

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		double Amount            = Double.parseDouble(AdjustmentAmount);
		double Quantity22k       = Amount / Double.parseDouble(AdvanceRate);
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRate, "22k");
		Thread.sleep(120000); // Wait needed for ERP Gold Rate to Change

		//Step 8: Click on cart button and Enter OTP
		//Step 9: Scan SKU
		//Expected: Check calculation
		//Note: Check average rate
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				CashCollectionRepaymentLimitValidationTestData.TC322_ProductCount,
				CashCollectionRepaymentLimitValidationTestData.TC322_TransferType,
				CashCollectionRepaymentLimitValidationTestData.TC322_FromCounter,
				CashCollectionRepaymentLimitValidationTestData.TC322_MetalType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("headerSplitViewToggleButton expandedNavButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h5 textLeft centerY", "Transaction"));
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(CashCollectionRepaymentLimitValidationTestData.TC321_DepositAdjustment);
		ProductQtyList.add(CashCollectionRepaymentLimitValidationTestData.TC321_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);

			//Validation
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber,Double.toString(Quantity22k),"22k",AdvanceRate,ErpGoldRateFor24K);
		}

		List<String> ItemNamesInBillingPageForFirstTransaction = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLineInBillingPge = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String NetTotal         = TransactionDataLineInBillingPge.get("NetTotal");
		String Tax              = TransactionDataLineInBillingPge.get("Tax");
		String Discount         = TransactionDataLineInBillingPge.get("Discount");
		String TotalNetWeight   = TransactionDataLineInBillingPge.get("NetWeight");
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInBillingPge, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10: save estimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC322_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String TaxableValue        = ProductTotalList.get(1);
		String InvoiceTotal        = Double.toString(Double.parseDouble(ProductTotalList.get(1)) + Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment     = AdjustmentAmount;
		String TotalAmt            = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment, 
				NetTotal, 
				TotalAmt);

		//Step 11: Recall estimate from cashier screen
		//Step 12: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		//RecallPageValidation
		List<String> ItemNamesFromRecallPageForFirstTransaction = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPageForFirstTransaction.containsAll(ItemNamesFromRecallPageForFirstTransaction), "Recalled item is not the same as the item in the cart");

		//Step 13: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),CashCollectionRepaymentLimitValidationTestData.TC321_TransactionType);

		//Step 14: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 16: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines. Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String AmountDueInCashierScreen             = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		double AmountDue                            = Double.parseDouble(AmountDueInCashierScreen);
		double RemainingCashForSecondTransaction    = 0.00;
		String PaymentViaCard                       = "";
		String PaymentAmountCashForFirstTransaction = "";

		if(AmountDue>RemainingCash) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(1000);
			base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
			base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(RemainingCash));
			base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			Thread.sleep(2000);
			PaymentAmountCashForFirstTransaction = String.valueOf(RemainingCash);
			PaymentViaCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		}
		else {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(CashCollectionRepaymentLimitValidationTestData.TC321_PaymentMethod));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			PaymentAmountCashForFirstTransaction = String.valueOf(Math.round(AmountDue));
			RemainingCashForSecondTransaction = RemainingCash-Double.parseDouble(PaymentAmountCashForFirstTransaction);
			wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("Close")));
			if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
			}
		}

		//Step 17: Select a Payment method(Cash or Card)
		//Step 18: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice     = pdfUtils.DownloadAndRenameLatestPDF("TC322TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath   = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount      = ProductTotalList.get(1);
		String TotalDiscount    = Discount;
		String TotalQtyPcs      = Integer.toString(SkuList.size());
		String NetValue         = NetTotal;
		String TotalGrossWeight = ProductQtyList.get(1);		
		String PdfInvoiceNo     = pdfUtils.SaleInvoiceWithCashCardAndAdvancePdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentViaCard,
				PaymentAmountCashForFirstTransaction,
				AdvanceAmount,
				SkuList,
				ScannedSkuDataMap);	
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		}
		//Step 1: Login to POS
		//Step 2: Click on Transaction button
		//Step 3: Select customer		
		//Step 4: Click on add to sale button
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");

		appUtils.SearchByCustomerID(CustomerId,UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(1000);
		String CustomerNo = base.GetText(LoginPageObj.Ele_ErrorMessage("h6 ellipsis primaryFontColor"));

		// Step6. Click on advance collection button
		//Step 7.select deposit type as normal
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance Collection"));
		Thread.sleep(5000);
		if(base.isPresent(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}
		Thread.sleep(3000);

		//Step 8 Enter deposit amount
		System.out.println("RemainingCashForSecondTransaction :"+RemainingCashForSecondTransaction);
		if(RemainingCashForSecondTransaction!=0) {
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),String.valueOf(RemainingCashForSecondTransaction));
		}else {base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),AdvanceAmount);}

		//Step 9 Select sales person
		base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"),1);

		//Step 10 Select due date
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"),  UtilityTestData.DueYear);

		//Step 11Enter remarks
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"),UtilityTestData.Remarks);

		//Step 12 Add nominee details
		//Enter nominee name
		//Select nominee relation
		//Click on check box(Same as customer address)					
		//Click on OK button
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"), UtilityTestData.NomineeName);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"), UtilityTestData.NomineeRelation);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"),UtilityTestData.DobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		//Step 13 Click on deposit button
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		Thread.sleep(3000);

		//Step 14 Click on amount
		//select a Payment method(Cash or Card)
		String PaymentAmountCashForSecondTransaction = "";
		if(RemainingCashForSecondTransaction==0) {
			Thread.sleep(2000);
			String CardPayment=appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();		
		}
		else {
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
			Thread.sleep(2000);
			PaymentAmountCashForSecondTransaction = String.valueOf(RemainingCashForSecondTransaction);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
			Thread.sleep(5000);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));

			//Step 16 Post the Invoice
			//Check ADVANCE RECEIPT VOUCHER details
			//*Towards the advance against purchase of approximate value
			//*Fixed Gold rate *Deposit Type *Advance received  * save receipt id for future reference
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived     = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualDepositType     = AdvanceReceiptVoucherDetails.get("DepositType");
			String MaxGoldRate           = AdvanceReceiptVoucherDetails.get("MaxGoldRate");

			asrt.assertEquals(Double.parseDouble(ActualAdvanceReceived),RemainingCashForSecondTransaction,1, "Mismatch in Advance amount in billing screen actual "+ActualAdvanceReceived+" and Expected "+RemainingCashForSecondTransaction+"in Normal Advance PDF");
			asrt.assertEquals(Double.parseDouble(ActualNetReceived),RemainingCashForSecondTransaction,1, "Mismatch in Net received amount in billing screen actual "+ActualAdvanceReceived+" and Expected "+RemainingCashForSecondTransaction+"in Normal Advance PDF");
			asrt.assertEquals(Utility_TestData.DepositType, ActualDepositType, "Mismatch in Deposit Type Actual "+ActualDepositType+" and Expected "+Utility_TestData.DepositType+ " in Normal Advance PDF");
			asrt.assertEquals(Double.parseDouble(MaxGoldRate),CurrentGoldRate22k, 1,"Mismatch in Gold rate screen actual "+MaxGoldRate+" and Expected "+CurrentGoldRate22k+"in Normal Advance PDF");
		}
		double DailyCash = 0.00;
		if(PaymentAmountCashForSecondTransaction !="") {
			DailyCash = Double.parseDouble(PaymentAmountCashForFirstTransaction)+Double.parseDouble(PaymentAmountCashForSecondTransaction)+Double.parseDouble(AdvanceAmount);
		}
		else {
			DailyCash = Double.parseDouble(PaymentAmountCashForFirstTransaction)+Double.parseDouble(AdvanceAmount);			
		}
		String Adjustment =  AdvanceAmount;		

		//Verify PMLA Cash tracker
		TimeUnit.MINUTES.sleep(6);
		erpUtils.VerifyPMLACashTracker(CustomerNo,  Double.toString(DailyCash), Adjustment);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
}