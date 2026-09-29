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
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import objectRepository.PdcEntryExecution_Obj;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
import testData.GppOpeningAndCollectionMultiplePlans_TestData;
import testData.PANCardLimitValidation_TestData;
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

//import basePackage.BasePge;

public class PANCardLimitValidation extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	GPPSettlementDiscountPlan_TestData GPPSettlementDiscountPlanTestData =new GPPSettlementDiscountPlan_TestData();
	PANCardLimitValidation_TestData PANCardLimitValidationTestData = new PANCardLimitValidation_TestData();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	PdcEntryExecution_Obj PdcEntryExecutionObj = new PdcEntryExecution_Obj();
	
	public PANCardLimitValidation(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	// <summary>
	// <summary>
	// Test Case Title : Customer with pan card having Invoice amount more than 2 lak -payment method cash and card
	// Automation ID : TC167
	// Author: Robin T Abraham
	// </summary>
	public void TC167_PANCardLimitValidation() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);

		//Fetching Board Rate ()
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Edt_Name("Search")));
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Edt_Name("Search"),30);
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
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),UtilityTestData.QCAndSmithPerson);
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),UtilityTestData.QCAndSmithPerson);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),PANCardLimitValidationTestData.TC167_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),PANCardLimitValidationTestData.TC167_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double CurrentGoldRate22k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
		System.out.println("CurrentGoldRate22k "+CurrentGoldRate22k);
		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found"))){
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));		
		}

		//Step 2:  Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3:  Select customer
		//Step 4: Click on add to estimate button
		appUtils.SearchByCustomerID(PANCardLimitValidationTestData.TC167_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5:  Scan multiple SKU (Amount should be more than 2 Lakh )and click on Add to cart button
		//Step 6:  Select any one of the sales agent
		double SaleAmount = PANCardLimitValidationTestData.TC167_SaleAmount;
		double RequiredSaleQty = SaleAmount / CurrentGoldRate22k;

		System.out.println("Required Sale Quantity"+RequiredSaleQty);

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter = 1;

		List<String> SkuList = appUtils.SelectMultipleSKUs(
				PANCardLimitValidationTestData.TC167_SkuCount,
				PANCardLimitValidationTestData.TC167_TransferType,
				PANCardLimitValidationTestData.TC167_FromCounterGold,
				PANCardLimitValidationTestData.TC167_MetalTypeGold);
		appUtils.HamBurgerButtonClick("iconShop");		
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				PANCardLimitValidationTestData.TC167_TransferType,
				PANCardLimitValidationTestData.TC167_FromCounterUncut,
				PANCardLimitValidationTestData.TC167_MetalTypeUncut,
				RequiredSaleQty,
				PANCardLimitValidationTestData.TC167_SkuCount);
		List<String> SkuListUncut = SkuGoldSaleItem.PcsRateSKU;
		SkuList.addAll(SkuListUncut);
		appUtils.HamBurgerButtonClick("iconShop");

		for (String Sku : SkuList) {

			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
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

		//Step 7:  Click on Save to estimation button
		//Step 8:  save esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// PDF Verification Step
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC167_ProformaInvoice");
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

		//Step 9: Recall estimate from cashier screen
		//Step 10: Select estimate and click on Recall estimation button
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

		//Step 11: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC167_TransationType);

		//Step 12: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Choose a sales representative
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();

		//Note: Note: Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		//Note: Customer pays partially with a card, then completes the payment with cash.
		//Step 14: Click on the Amount and Select a Payment method(Card)

		//Get billing page total amount
		String InvoiceAmountInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		System.out.println("Invoice Amount (String): " + InvoiceAmountInBillingPge);

		double InvoiceAmount = Double.parseDouble(InvoiceAmountInBillingPge.replaceAll("[^0-9.]", ""));
		System.out.println("Invoice Amount (Double): " + InvoiceAmount);

		//Subtract Rs 500 for cash payment
		double CardAmount = InvoiceAmount - PANCardLimitValidationTestData.TC167_CardAmount;
		System.out.println("Card Payment Amount: " + CardAmount);
		double PaymentAmountCash = PANCardLimitValidationTestData.TC167_CardAmount;
		System.out.println("Cash Payment Amount: " + PaymentAmountCash);

		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);

		base.ClickCondition(PdcEntryExecutionObj.Ele_Payment("HDFC"));

		//Step 15: Enter the Amount, the Appr code,the Card No.
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(
				CustomerCreationObj.Ele_ERPCustid("Payment amount"),
				String.valueOf(CardAmount)
				);

		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));

		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		//Step 16: Click on the Amount
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);
		//Step 17: Select a Payment method(Cash)
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		//Step 18: Enter the Amount
		Thread.sleep(3000);
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		//Step 19: Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC167SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotal;
		String TotalDiscount=Discount;
		String TotalQtyPcs=LinesCount;
		String NetValue=NetTotal;
		String PdfInvoiceNo = pdfUtils.SaleInvoiceCashPdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				String.valueOf(CardAmount),
				String.valueOf(PaymentAmountCash),
				SkuList,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Customer with pan card having Invoice amount more than 2 lakh -payment method cash
	// Automation ID : TC166
	// Author: Sangeetha M S
	// </summary>
	public void TC166_PANCardLimitValidation() throws Exception 
	{
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite : Customer's pan card must already be uploaded
		String CustPhone=appUtils.CreateANewCustomerWithPan(PANCardLimitValidationTestData.PanRequired);
		System.out.println("Customer phone"+CustPhone);

		//Fetching Board Rate 		
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		double CurrentGoldRate22k =appUtils.FetchCurrentBoardRate("22k");

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search customer
		//Step 4: Click on add to estimate button
		appUtils.SearchByCustomerID(CustPhone,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Add SKU (above 2 lakhs)
		//Expected :Check calculation Billing Screen
		//*No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal,*TAX,*Total Amount
		//Step 6: Click on Add to cart button
		double SaleAmount = PANCardLimitValidationTestData.TC166_SaleAmount;
		double RequiredSaleQty = SaleAmount / CurrentGoldRate22k;
		System.out.println("Required Sale Quantity"+RequiredSaleQty);

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter = 1;

		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				PANCardLimitValidationTestData.TC166_TransferType,
				PANCardLimitValidationTestData.TC166_FromCounterGold,
				PANCardLimitValidationTestData.TC166_MetalTypeGold,
				RequiredSaleQty,
				PANCardLimitValidationTestData.TC166_SkuCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");

		for (String Sku : SkuListGold)
		{
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal");
		String Discount         = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax              = TransactionDataLine.get("Tax");
		String TotalAmount      = TransactionDataLine.get("TotalAmount");
		String NetTotal         = TransactionDataLine.get("NetTotal");
		String LinesCount       = TransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 7: Click on Save to estimation button
		//Step 8: Recall estimate from cashier screen
		//Expected : Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(2000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC166_ProformaInvoice");
		Thread.sleep(1000);
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

		//Step 9: Select estimate and click on Recall estimation button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));	
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 10: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC166_TransationType);

		//Step 11: Click on Process Estimation 
		//Step 12: Choose a sales representative
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));	
		base.setZoom(driver, 60);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();

		//Note: Note: Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		//Step 13: Click on the Amount 
		//Step 14: Select a Payment method(Cash )
		String InvoiceAmountInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		double InvoiceAmount = Double.parseDouble(InvoiceAmountInBillingPge.replaceAll("[^0-9.]", ""));
		System.out.println("Invoice Amount (Double): " + InvoiceAmount);

		//Subtract cash limit of Rs.199999 from Invoice Amount
		double CashLimit = PANCardLimitValidationTestData.TC166_CashLimit;
		double CashAmount=0.00;
		double CardAmount=0.00;
		if (InvoiceAmount > CashLimit) 
		{
			CashAmount = CashLimit;               
			CardAmount = InvoiceAmount - CashLimit; 
		} 
		else 
		{
			CashAmount = InvoiceAmount; 
			CardAmount = 0;          
		}
		CardAmount = Math.round(CardAmount * 100.0) / 100.0;

		double PaymentAmountCash = CashAmount;
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PANCardLimitValidationTestData.TC166_PaymentMethod));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(CashAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PANCardLimitValidationTestData.TC166_CardPaymentMethod));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		//Step 15.Post the Invoice
		//Expected : Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(1000);

		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC166SaleInvoice");
		Thread.sleep(1000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotal;
		String TotalDiscount=Discount;
		String TotalQtyPcs=LinesCount;
		String NetValue=NetTotal.replaceAll("[^0-9.]", "");
		System.out.println("NetValue"+NetValue);
		Thread.sleep(2000);
		String PdfInvoiceNo = pdfUtils.SaleInvoiceCashPdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				String.valueOf(CardAmount),
				String.valueOf(PaymentAmountCash),
				SkuListGold,
				ScannedSkuDataMap);		
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
	}

	// <summary>
	// Test Case Title : Customer without pan card having Invoice amount more than 2 lakh ,payment method cash
	// Automation ID : TC163
	// Author: Christy Reji
	// </summary>
	public void TC163_PANCardLimitValidation() throws Exception 
	{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


		//Step 2: Click on Add customer
		//Step 3: Click on "Create new customer"
		//Step 4:Click on Customer type as "Person", Add mail id, religion, DOB,Address and save
		Thread.sleep(3000);
		String CustPhone = appUtils.CreateANewCustomerWithPan(PANCardLimitValidationTestData.TC163_PanRequiredNo);
		System.out.println("Customer phone"+CustPhone);

		//Fetching Board Rate to purchase amount above 2 lakh
		appUtils.HamBurgerButtonClick("iconShop");
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");

		//Step 5: .Go to transaction, Search the customer with phone number.
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(CustPhone,UtilityTestData.MenuBarOptions[5]);

		//Step 6: Add SKU (above 2 lakhs)
		//Step 7: click on Add to cart button
		//Expected :Check calculation Billing Screen
		//*No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),
		//*Displayed Subtotal,*TAX,*Total Amount
		double SaleAmount                     = PANCardLimitValidationTestData.TC166_SaleAmount;
		double RequiredSaleQty                = SaleAmount / CurrentGoldRate22k;
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter                        = 1;
		System.out.println("Required Sale Quantity"+RequiredSaleQty);
		Thread.sleep(3000);

		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				PANCardLimitValidationTestData.TC163_TransferType,
				PANCardLimitValidationTestData.TC163_FromCounterGold,
				PANCardLimitValidationTestData.TC163_MetalTypeGold,
				RequiredSaleQty,
				PANCardLimitValidationTestData.TC163_SkuCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");

		for (String Sku : SkuListGold)
		{
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Discount         = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotal         = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount       = TransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 8: Click on Save to estimation button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		// Note: check for the validation to update PAN card
		Thread.sleep(3000);
		base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"));
		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "The validation message -PAN number is required this customer-is displayed in the warning message");	
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Close"));

		//Updating Pan card to the customer
		Thread.sleep(3000);
		appUtils.AddOrVerifyPan();

		//Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC163_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String TaxableValue        = SubTotal;
		String InvoiceTotal        = TotalAmount;
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step 9:Recall estimate from cashier screen
		//Verify whether the recalled item is the same as the item in the cart.
		//Note: Cashier Screen after recall *No of Product lines  *Displayed Item Name *Displayed Quantity  *Displayed TOTAL (without Tax)
		// *Displayed Subtotal	 *TAX*Total Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));	
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Validation Recall page
		appUtils.RecallEstimateItemValidation(
				ProductNames,        
				ProductQtyList,             
				ProductNames,      
				ProductQtyList,   
				Double.parseDouble(SubTotal),
				Double.parseDouble(Tax),
				Double.parseDouble(TotalAmount),
				Integer.parseInt(LinesCount),
				"PAN Card RecallPage"                      
				);	

		//Step 10: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC163_TransationType);

		//Step 11: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 12: Choose a sales representative
		try {
			Thread.sleep(3000);
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			} else {
				System.out.println("Sales Representative popup not displayed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while handling Sales Representative popup: " + e.getMessage());
		}

		//Step 13: Click on the Amount 
		//Step 14: Select a Payment method(Cash )
		String InvoiceAmountInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		double InvoiceAmount             = Double.parseDouble(InvoiceAmountInBillingPge.replaceAll("[^0-9.]", ""));

		//Subtract cash limit of Rs.199999 from Invoice Amount
		double CashLimit = PANCardLimitValidationTestData.TC163_CashLimit;
		double CashAmount=0.00;
		double CardAmount=0.00;
		if (InvoiceAmount > CashLimit) 
		{
			CashAmount = CashLimit;               
			CardAmount = InvoiceAmount - CashLimit; 
		} 
		else 
		{
			CashAmount = InvoiceAmount; 
			CardAmount = 0;          
		}
		CardAmount = Math.round(CardAmount * 100.0) / 100.0;
		double PaymentAmountCash = CashAmount;
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PANCardLimitValidationTestData.TC163_PaymentMethod));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(CashAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(2000);

		//Remaining payment via card
		String PaymentViaCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 15.Post the Invoice
		//Expected : Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);

		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC163_FinalTaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount    = SubTotal;
		String TotalDiscount  = Discount;
		String TotalQtyPcs    = LinesCount;
		double RoundNetValue  = CashAmount + Double.parseDouble(PaymentViaCard);
		String NetValue       = String.format("%.2f", RoundNetValue);
		String PdfInvoiceNo   = pdfUtils.SaleInvoiceCashPdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				String.valueOf(PaymentViaCard),
				String.valueOf(PaymentAmountCash),
				SkuListGold,
				ScannedSkuDataMap);		
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Customer without pan card having Invoice amount more than 2 lak -payment with advance/scheme adjustment
	// Automation ID : TC165
	// Author: Vishnu RR
	// </summary>

	public void TC165_PANCardLimitValidation() throws Exception 
	{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite : Customer's creation with out pan card 
		String CustPhone=appUtils.CreateANewCustomerWithPan(PANCardLimitValidationTestData.PANCardRequired);
		System.out.println("Customer phone"+CustPhone);

		// Prerequisite : Customer Must have an offer Advance
		Map<String, String> Details = appUtils.CreateWeightBookedOfferAdvance(
				CustPhone,
				PANCardLimitValidation_TestData.TC165_PiecesByPurity,
				PANCardLimitValidation_TestData.TC165_GrossWeights,
				UtilityTestData.Company[0], UtilityTestData.Location[0],
				PANCardLimitValidation_TestData.TC165_DepositPercent);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Search customer
		//Step 4: Click on add to estimate button
		appUtils.SearchByCustomerID(CustPhone,UtilityTestData.MenuBarOptions[5]);

		//Step 5. Click on customer adjustments-->order advance adjustment
		//Step 6. choose advance from dropdown-->enter the adjustment amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"))).click();
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"), Details.get("OrderId"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("option", Details.get("OrderId")));

		// Calculating the Advance  Amount
		double AdvanceBalance = 0;
		// Balance Amount in the box
		Thread.sleep(2000);
		AdvanceBalance = Double.parseDouble(base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount")));
		String BalanceAmtInAdvanceAdj = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount"));
		String DefaultAdjAmt = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		System.out.println("Balance Amount from the box " + AdvanceBalance);
		double Adjustment = AdvanceBalance * 0.1;
		System.out.println(Adjustment);
		base.clearData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"), Double.toString(Adjustment));

		// Step 7. Click on cart button
		// Expected Result:The balance amount and adjustment amount for the selected advances should be shown correctly
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		asrt.assertEquals(BalanceAmtInAdvanceAdj, DefaultAdjAmt,"Mismatch in balance amount and adjusted amount displayed in advance adjustment page");

		//Fetching Board Rate ()
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"22k");
		//To Handle the Unexpected Error
		WebDriverWait Waits = new WebDriverWait(driver, Duration.ofSeconds(8));
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),PANCardLimitValidationTestData.TC165_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),PANCardLimitValidationTestData.TC165_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double CurrentGoldRate22k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
		System.out.println("CurrentGoldRate22k "+CurrentGoldRate22k);
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));		
		}

		double SaleAmount = PANCardLimitValidationTestData.TC165_SaleAmount;
		double RequiredSaleQty = SaleAmount / CurrentGoldRate22k;
		System.out.println("Required Sale Quantity"+RequiredSaleQty);
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 8. Scan SKU above 2 lakh and click add to cart button
		// Step 9. Select any one of the sales agent
		// Checking the No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		SKUResult SkuOGSale = appUtils.GetOGSaleSku(
				PANCardLimitValidation_TestData.TC165_TransferType,
				PANCardLimitValidation_TestData.TC165_FromCounter,
				PANCardLimitValidation_TestData.TC165_MetalType,
				RequiredSaleQty,
				PANCardLimitValidation_TestData.TC165_SkuCount);

		List<String> SkuList = SkuOGSale.PcsRateSKU;
		System.out.println("SkuList" + SkuList);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("width48 height48 buttonIcon center iconShop"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		int SkuCounter =1;
		Map<String, String> ScannedSkuDataMap = new HashMap();
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		appUtils.SKUIngridentTableValues(SkuList.get(0),PANCardLimitValidation_TestData.TC165_SkuCount,ScannedSkuDataMap);

		String ExpectedGrossValue = ScannedSkuDataMap.get("SKU_"+ PANCardLimitValidation_TestData.TC165_SkuCount+ "_GrossWeight");
		String ExpectedNetValue = ScannedSkuDataMap.get("SKU_"+ PANCardLimitValidation_TestData.TC165_SkuCount+ "_TotalRGWeight");
		String ExpectedItemName = ScannedSkuDataMap.get("SKU_"+ PANCardLimitValidation_TestData.TC165_SkuCount+ "_ItemName");
		String TotalForEachItem = ScannedSkuDataMap.get("SKU_"+ PANCardLimitValidation_TestData.TC165_SkuCount+ "_Total");
		String RateKey = "SKU"+ PANCardLimitValidation_TestData.TC165_SkuCount+ "_ITEM"+ PANCardLimitValidation_TestData.TC165_SkuCount+ "_RATE";
		String BoardRateFromSkuTble = ScannedSkuDataMap.get(RateKey);

		Double SkuBoardRate = Double.parseDouble(BoardRateFromSkuTble);
		String Purity = ScannedSkuDataMap.get("SKU_"+ PANCardLimitValidation_TestData.TC165_SkuCount+ "_Purity");

		erpUtils.ResetRemainingReturnWeight();
		String SkuPurityReturn		="22K";
		double SkuPurityReturnDeposit = Double.parseDouble(SkuPurityReturn.replaceAll("[^\\d.]", "").trim());

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		Map<String, String> BillingScrnTableData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = BillingScrnTableData.get("Subtotal");
		String TotalGrossWeight = BillingScrnTableData.get("GrossWeight");
		String TotalNetWeight = BillingScrnTableData.get("NetWeight");
		String Tax = BillingScrnTableData.get("Tax");
		String TotalAmount = BillingScrnTableData.get("TotalAmount");
		String NetTotal = BillingScrnTableData.get("NetTotal");
		String LinesCount = BillingScrnTableData.get("LinesCount");

		int LinesCountBilling = Integer.parseInt(LinesCount);
		double SubTotalBilling = Double.parseDouble(SubTotal.trim().replaceAll("[^\\d.]", ""));
		double TaxBilling = Double.parseDouble(Tax.trim().replaceAll("[^\\d.]", ""));
		double TotalAmountBilling = Double.parseDouble(TotalAmount.trim().replaceAll("[^\\d.]", ""));

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		List<WebElement> ItemNamesBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		List<WebElement> ItemRateBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		List<WebElement> ItemQtyBilling = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-QuantityField textRight"));
		List<String> TextItemNamesBilling = new ArrayList<>();
		List<String> TextItemQtyBilling = new ArrayList<>();

		int ExpectedLinesCount = AllProductRows.size();
		asrt.assertEquals(ExpectedLinesCount, LinesCountBilling, "Mismatch in Lines count,expected "+ ExpectedLinesCount + " but got " + LinesCountBilling + " in the billing screen");

		String SkuItemRate = ItemRateBilling.get(1).getText().trim().replaceAll("[^\\d.\\-]", "");
		double FirstItemRate = Double.parseDouble(SkuItemRate);
		double ExpectedSubTotal = 0.0;
		ExpectedSubTotal = FirstItemRate - (Adjustment);

		double ExpectedTaxAmnt = FirstItemRate * 3 / 100;
		double ActualTotalAmount = ExpectedSubTotal + ExpectedTaxAmnt;

		asrt.assertEquals(ExpectedTaxAmnt, TaxBilling, 1.0, "Mismatch in Tax value, expected" + ExpectedTaxAmnt+ " but got " + TaxBilling + " in the billing screen");

		for (int n = 0; n < ItemNamesBilling.size(); n++) {
			TextItemNamesBilling.add(ItemNamesBilling.get(n).getText().trim());
		}
		Thread.sleep(3000);
		String ExpectedFullItemName = ExpectedItemName.trim();
		String ExpectedTrimmedItemName = ExpectedFullItemName.contains("-")
				? ExpectedFullItemName.substring(ExpectedFullItemName.indexOf("-") + 2)
						: ExpectedFullItemName;
		String ActualItemName = ItemNamesBilling.get(1).getText().trim();

		asrt.assertEquals(ExpectedTrimmedItemName, ActualItemName, "Mismatch in item name, expected "+ ExpectedTrimmedItemName + "but got " + ActualItemName + "  in the billing screen.");

		String ExpectedTotalStr = ScannedSkuDataMap.get("SKU_" + (1) + "_Total");
		double ExpectedTotal = Double.parseDouble(TotalForEachItem.replaceAll("[^\\d.]", ""));
		String ActualTotalStr = ItemRateBilling.get(1).getText().trim();
		double ActualTotal = Double.parseDouble(ActualTotalStr.replaceAll("[^\\d.]", ""));
		System.out.println("ExpectedTotal" + TotalForEachItem);
		System.out.println("ActualTotal" + ActualTotal);

		asrt.assertEquals(ExpectedTotal, ActualTotal, 1.0, "Mismatch in total amounts (without tax),expected "+ ExpectedTotal + " but got " + ActualTotal + " in the billing screen.");

		for (int n = 0; n < ItemQtyBilling.size(); n++) 
		{
			TextItemQtyBilling.add(ItemQtyBilling.get(n).getText().trim());
		}

		for (int n = 0; n < ItemQtyBilling.size(); n++) 
		{
			TextItemQtyBilling.add(ItemQtyBilling.get(n).getText().trim());
		}

		String ExpectedGrossWt = ExpectedGrossValue.replace("-", "").trim();
		Double ExpectedQty = Double.parseDouble(ExpectedGrossWt);
		String BillingQtySku = ItemQtyBilling.get(1).getText().replace("-", "").trim();
		Double ActualGrossWtSku = Double.parseDouble(BillingQtySku.replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ExpectedQty, ActualGrossWtSku, 1.0, "Mismatch in Qty value,expected " + ExpectedQty+ " but got " + ActualGrossWtSku + " in the Billing screen");
		asrt.assertEquals(ExpectedSubTotal, SubTotalBilling, 1.0, "Mismatch in Subtotal value, expected "+ ExpectedSubTotal + " but got " + SubTotalBilling + "in the Billing screen");
		asrt.assertEquals(TotalAmountBilling, ActualTotalAmount, 1.0, "Mismatch in Total amount value, expected "+ TotalAmountBilling + " but got " + ActualTotalAmount + " in the Billing screen");

		// Step 10: Save to estimate
		// Expected Result: Verify Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));

		// Step 11. Verify PAN card validation
		// Expected Result VerifyPAN card validation
		asrt.assertTrue(base.isExists(LoginPageObj.Ele_ErrorMessage("grow col scrollY")),"Validation saying 'Pan Card Does not exist' is not displayed ");
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));

		// Checking the pan details of the customer and Adding the Pan
		appUtils.AddOrVerifyPan();

		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC165_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice + "";
		String TaxableValue = SkuItemRate;
		String InvoiceTotal = Double.toString(Double.parseDouble(TaxableValue.replaceAll("[^\\d.]", "").trim())+ Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment = Double.toString(Adjustment);
		String Gst = Tax;
		String TotalAmnt = "";
		Thread.sleep(3000);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(ProformaInvoicePath, TaxableValue, Gst,InvoiceTotal, TotalAdjustment, NetTotal, TotalAmnt);

		// Step 12. Recall estimate from cashier screen
		// Step 13. Select estimate and click on recall estimation	
		//Expected : Verify whether the recalled item is same as the item in the Cart 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		appUtils.ValidateBillingAndRecallScreenData(EstmnNumber, LinesCount, TextItemNamesBilling, TextItemQtyBilling,SubTotal, Tax, NetTotal);

		// Step 14: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),PANCardLimitValidation_TestData.TC165_TransactionType);

		// Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 16: Choose a Sales Representative
		base.setZoom(driver, 60);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {

			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));  
		}		
		appUtils.HandleSpecifyRateModalInRecall();
		String Discount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4"));
		String PaymentAmountHDFC = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
		String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");

		// Step 17: Click on amount
		// Select payment method(cash/card)
		appUtils.PaymentAfterRecallEstimate(PANCardLimitValidation_TestData.TC165_PaymentMethod);

		// Step 18:Select a Payment method(Cash or Card)
		// Step 19: Post exchange invoice(sale)
		// Expected Result: Check final invoice details,* save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC165_SaleInvoice");
		Thread.sleep(3000);
		String SaleInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
		String GrossAmount = SkuItemRate;
		String TotalDiscount = Discount;
		String TotalQtyPcs = SkuLinesCount;
		String OGAdjustment = null;
		String TotalDiamond = null;
		//String PaymentHDFCCard = PaymentAmountHDFC;
		//String PaymentHDFCCard = String.format("%.2f", (double) Math.round(Double.parseDouble(PaymentAmountHDFC.replaceAll("[^\\d.-]", ""))));
		String PaymentHDFCCard = "";
		if(UtilityTestData.WarehouseId!="5011") {
			PaymentHDFCCard =  String.format("%.2f",(double)Math.round(Double.parseDouble(PaymentAmountHDFC.replaceAll("[^\\d.-]", ""))));
		}
		else {
			PaymentHDFCCard = PaymentAmountHDFC;
		}
		String SalesPdfPath = SaleInvoicePath;
		String AdvanceAmountFinalInvoice = Double.toString(Adjustment);
	//	TotalNetWeight = ExpectedGrossWt;
		TotalGrossWeight = ExpectedGrossWt;
		//	String NetValue = SkuNetTotal;
		//	String NetValue = String.format("%.2f", (double) Math.round(Double.parseDouble(SkuNetTotal.replaceAll("[^\\d.-]", ""))));
		String NetValue = "";
		if(UtilityTestData.WarehouseId!="5011") {
			NetValue =  String.format("%.2f",(double)Math.round(Double.parseDouble(SkuNetTotal.replaceAll("[^\\d.-]", ""))));
		}
		else {
			NetValue = SkuNetTotal;
		}
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidationAdvance(SalesPdfPath, GrossAmount,
				TotalDiscount, TotalDiamond, TotalQtyPcs, TotalNetWeight, TotalGrossWeight, TaxableValue, NetValue,
				PaymentHDFCCard, OGAdjustment, AdvanceAmountFinalInvoice, SkuList, ScannedSkuDataMap);

		System.out.println("PDF INVOICE NO:-" + PdfInvoiceNo);
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Customer without pancard having invoice amount 199999
	// Automation ID : TC169
	// Author: Chandana Babu
	// </summary>
	public void TC169_PANCardLimitValidation() throws Exception {
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step : Pre-Requisite for taking BoardRate
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");

		//Step 2.Click on Add customer
		//Step 3.Click on "Create new customer"
		//Step4.Click on Customer type as "Person", Add mail id, religion, DOB,Address and save
		String CustomerId =appUtils.CreateANewCustomerWithPan("No");


		//Step 5.Go to transaction, Search the customer with phone number.
		appUtils.SearchByCustomerID(CustomerId ,UtilityTestData.MenuBarOptions[5]);

		//Step 6.Add SKU (almost 199999)
		//Expected: Check calculation
		//Step 7.click on Add to cart button
		double RequiredWeight = (PANCardLimitValidationTestData.TC169_Price/CurrentGoldRate22k)-PANCardLimitValidationTestData.TC169_ReducingWeightBy;
		RequiredSKUResult SkuGoldSaleItem = appUtils.GetRequiredQuantitySKU(
				PANCardLimitValidationTestData.TC169_TransferType,
				PANCardLimitValidationTestData.TC169_FromCounter,
				PANCardLimitValidationTestData.TC169_MetalType,
				RequiredWeight,
				PANCardLimitValidationTestData.TC169_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.RequiredQtySKUs;
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		int SkuCounter = 1;	
		String RpRateInSkutemDetailsPge ="";
		String TotalAmountInSkuItemDetailsPge ="";
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//To get required BC weight
		double Amount = Double.parseDouble(base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim());
		double RemainingAmount = PANCardLimitValidationTestData.TC169_Price-Amount;
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), PANCardLimitValidationTestData.TC169_BC);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "BC")));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "BC"));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), UtilityTestData.Purity22);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), PANCardLimitValidationTestData.TC169_GrossWeight);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(4000);
		base.ScrollIntoView(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: WsDiscQty"));
		double WastageQty = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: WsDiscQty")));
		double WastageQtyPercent = WastageQty/100;
		double BcQuantity = RemainingAmount/((1+WastageQtyPercent+0.03+(0.03*WastageQtyPercent))*CurrentGoldRate22k);		
		double TruncatedBcQuantity = Math.floor(BcQuantity * 1000) / 1000.0;
		String RequiredBcQuantity = String.valueOf(TruncatedBcQuantity);
		Thread.sleep(2000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), RequiredBcQuantity);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		String BcItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		Thread.sleep(5000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		String BcTotalAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		ProductNames.add(BcItemName);
		ProductQtyList.add(RequiredBcQuantity);
		ProductTotalList.add(BcTotalAmount);

		//Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal =TransactionDataLine.get("Subtotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String NetWeightFromSku = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCount, ActualLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");				
		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//asrt.assertEquals(ActualItemNameInBillingPge,ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge), "Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replaceAll("[^\\d.]", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPge),Double.parseDouble(ExpectedQuantityInBillingPge),1.0,"Quantity mismatch: Expected is "+ExpectedQuantityInBillingPge+" but got "+ActualQuantityInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = SubTotal.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		double ActualTaxInBillingPge = Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim());
		double ExpectedTaxInBillingPge = SubTotalSum*0.03;
		asrt.assertEquals(ActualTaxInBillingPge,ExpectedTaxInBillingPge,1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxInBillingPge;
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 8.Click on Save to estimate button.		
		//Note: check for the validation to update PAN card
		//Expected: Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		asrt.assertFalse(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "Warning message is displayed to update PAN card");
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		double CalculatedNetWeight = Double.parseDouble(NetWeightFromSku)+Double.parseDouble(RequiredBcQuantity);
		String TotalNetWeight = String.format("%.3f", CalculatedNetWeight);
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC169_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue=SubTotal;
		String InvoiceTotal=TotalAmount;
		int LinesCount = SkuList.size();
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				Integer.toString(LinesCount),
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step 9.Recall estimate from cashier screen
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 10.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC169_TransactionType);

		//Step 11.Click on Process Estimation 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 12.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen  = TransactionDataLineInCashierScreen.get("Subtotal");
		String TaxInCashierScreen  = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen  = TransactionDataLineInCashierScreen.get("TotalAmount");
		String NetTotalInCashierScreen  = TransactionDataLineInCashierScreen.get("NetTotal");
		String ActualLinesCountInCashierScreen  = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen  = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen  = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen , ActualLinesCountInCashierScreen ,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen +" but found "+ActualLinesCountInCashierScreen +" in Cashier Screen");				
		double SubTotalSumInCashierScreen  = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen .size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen  = ItemName.get(i).getText();
			String ExpectedItemNameInCashierScreen  = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//asrt.assertEquals(ActualItemNameInCashierScreen ,ExpectedItemNameInCashierScreen ,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen +" but got "+ActualItemNameInCashierScreen +" in Cashier Screen");			
			asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen), "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen  = Quantity.get(i).getText().replaceAll("[^\\d.]", "");
			String ExpectedQuantityInCashierScreen  = ProductQtyList.get(i);
			asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen),Double.parseDouble(ExpectedQuantityInCashierScreen),1.0,"Quantity mismatch: Expected is "+ExpectedQuantityInCashierScreen +" but got "+ActualQuantityInCashierScreen+" in Cashier Screen");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInCashierScreen  = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSumInCashierScreen  = SubTotalSumInCashierScreen +Double.parseDouble(ActualTotalWithoutTaxInCashierScreen );
			String ExpectedTotalWithoutTaxInCashierScreen  = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}

		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen);
		String ActualSubTotalInCashierScreen = SubTotal.replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in Cashier Screen");

		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen.replaceAll("[^\\d.]", "").trim());
		double ExpectedTaxInCashierScreen = SubTotalSumInCashierScreen*0.03;
		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen,1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreen+" but found "+ActualTaxInCashierScreen+" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxInBillingPge;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 13.Click on the Amount
		//Step 14.Select a Payment method
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 15.Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC169SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotal;
		String TotalDiscount=Discount;
		String TotalQtyPcs=ActualLinesCountInCashierScreen;
		String NetValue=PaymentAmount;
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

	// <summary>
	// Test Case Title : Customer with pan card having Invoice amount more than 2 lakh -payment with advance/scheme adjustment
	// Automation ID : TC168
	// Author:Sangeetha M S
	// </summary>
	public void TC168_PANCardLimitValidation() throws Exception 
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite  1.Customer must have matured scheme(GB,GG,GS)    
		Thread.sleep(1500);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				PANCardLimitValidationTestData.TC168_RequiredSettlement,
				PANCardLimitValidationTestData.TC168_AccountCount,
				PANCardLimitValidationTestData.TC168_SchemeCode,
				PANCardLimitValidationTestData.TC168_Matured,
				PANCardLimitValidationTestData.TC168_Adjusted);

		//For taking Gold Rate	
		Thread.sleep(1500);
		double CurrentGoldRate22k =appUtils.FetchCurrentBoardRate("22k");

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerAccount="";
		String GPPAccountNumber="";
		String GPPAmount="";
		String SkuPurityReturn="";
		String TotalRgWeightReturn="";
		String SkuGoldRateReturn="";
		boolean validAccountFound = false;

		//for (int idx = 0; idx < MatchedAccounts.size(); idx++)
		for (int idx = MatchedAccounts.size() - 1; idx >= 0; idx--) 
		{
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerAccount =AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			//GPPAmount = (String) GPPMaturedAccountDetails.get("GppAmount");
			GPPAmount =AccountMap.get("GppAmount");

			//			CustomerAccount ="20CA00257904";
			//			GPPAccountNumber ="20GB000042027" ;
			//			GPPAmount ="22000.00";

			//Step 3. Select customer
			//Step 4. Click on add to estimation button
			appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

			//Step 5. Click on customer adjustment button
			base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
			base.ClickCondition(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			//Step 6. click on GPP advance settlement
			base.ClickCondition(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			//Step 7. Enter scheme id(GG) and click on redeem button
			//Expected :Verify whether the entered GB scheme gets added 
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
			Thread.sleep(1000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
			Thread.sleep(1000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

			//Check if "No data" is displayed
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) 
			{
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
		if (!validAccountFound) 
		{
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}

		//Prerequisite 2.Customer Pan must be already uploaded
		appUtils.AddOrVerifyPan();

		Thread.sleep(2000);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;		

		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		//To get the Required weight
		double SchemeAmount =Double.parseDouble(ActualSchemeAmount);		
		double BenefitWeightFor22k = PANCardLimitValidationTestData.TC168_SaleAmount/CurrentGoldRate22k;
		double CurrentGoldRate18k =(CurrentGoldRate22k*18)/22;
		double BenefitWeightFor18k = SchemeAmount/CurrentGoldRate18k;
		double RequiredWeight =0.0;
		if(BenefitWeightFor22k>BenefitWeightFor18k)
		{
			RequiredWeight= BenefitWeightFor22k;
		}
		if(BenefitWeightFor22k<BenefitWeightFor18k) 
		{
			RequiredWeight= BenefitWeightFor18k;
		}

		//Step 8. Enter OTP
		//Step 9. Scan multiple Gold SKU ( amount should be more than 2 lakhs)
		//Expected : Check calculation
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				PANCardLimitValidationTestData.TC168_TransferType,
				PANCardLimitValidationTestData.TC168_FromCounterDiamond,
				PANCardLimitValidationTestData.TC168_MetalTypeGold,
				RequiredWeight,
				PANCardLimitValidationTestData.TC168_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double TotalDiamondValue = 0.0;
		double ActualRgRateInItemDetailsPge =0.0;
		ProductNames.add(PANCardLimitValidationTestData.TC168_SchemeName);
		ProductQtyList.add(PANCardLimitValidationTestData.TC168_Weight);
		ProductTotalList.add(GPPAmount);
		int SkuCounter = 1;	

		for (String Sku : SkuList)
		{			
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalDiamondValue = TotalDiamondValue +Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueDIAMOND"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 10. Click on gpp discount button
		//Expected :Verify the GPP Bonus amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(30000);		
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","4"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		double ExpectedBonus = 0.0;
		Thread.sleep(2000);
		if (TotalDiamondValue>SchemeAmount) 
		{
			ExpectedBonus = SchemeAmount*0.2;
		}
		else 
		{
			ExpectedBonus = TotalDiamondValue*0.2;
		}
		Thread.sleep(3000);

		asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ExpectedBonus+" but got "+ActualBonus+" in billing page");

		ProductNames.add(PANCardLimitValidationTestData.TC168_BonusName);
		ProductQtyList.add(PANCardLimitValidationTestData.TC168_Weight);
		ProductTotalList.add(Bonus);

		// Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount,ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) 
		{
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1)
			{
				//String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();

				asrt.assertTrue(ProductNames.get(i).trim().contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ProductNames.get(i).trim()+" but got "+ActualItemNameInBillingPge+" in billing page");			
			}
			else 
			{
				//String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();

				asrt.assertTrue(ProductNames.get(i).trim().contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ActualItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
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
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));

			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTax = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxInBillingPge = Double.parseDouble(ActualTax);
		double ExpectedTaxInBillingPge = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTax = String.format("%.2f",ExpectedTaxInBillingPge);

		asrt.assertEquals(ActualTaxInBillingPge,ExpectedTaxInBillingPge,1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxInBillingPge;
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());

		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 11. save estimate
		//Expected :.Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(1500);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC168_ProformaInvoice");
		Thread.sleep(1000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= Double.toString(Double.parseDouble(ProductTotalList.get(1)));
		String InvoiceTotal= Double.toString(Double.parseDouble(ProductTotalList.get(1))+ActualTaxInBillingPge);
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

		//Step 12.Recall estimate from cashier screen
		//Step 13. Select estimate and click on Recall estimation button
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in estimation page");

		//Step 14.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC168_TransactionTypeSales);

		//Step 15.Click on Process Estimation 
		//Step 16.Choose a sales representative[Removed after Data refresh]
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount		
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 	= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 	    = TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 				= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 		= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen	= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in Cashier Screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) 
		{
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i==1||i==2) 
			{
				//String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();

				asrt.assertTrue(ProductNames.get(i).trim().contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ProductNames.get(i).trim()+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");			
			}
			else 
			{
				//String ExpectedItemNameInCashierScreen = ProductNames.get(i).trim();

				asrt.assertTrue(ProductNames.get(i).trim().contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ProductNames.get(i).trim()+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");
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
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));

			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in Cashier Screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxFromCashierScreen = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxFromCashierScreen);

		asrt.assertEquals(ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxFromCashierScreen;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());

		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17.Click on the Amount
		//Step 18.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 19: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(1500);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC168TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1)));
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String TotalGrossWeight = String.format("%.3f",Double.parseDouble(ProductQtyList.get(1)));		
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
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
	}


	// <summary>
	// Test Case Title : Customer without pan card having Invoice amount more than 2 lakh -payment method cash and card
	// Automation ID : TC164
	// @Author: Gokul.P
	// </summary>
	public void TC164_PANCardLimitValidation() throws Exception 
	{
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Step 2: Click on Add customer
		//Step 3: Click on "Create new customer"
		//Step 4:Click on Customer type as "Person", Add mail id, religion, DOB,Address and save
		String CustPhone = appUtils.CreateANewCustomerWithPan(PANCardLimitValidationTestData.TC164_PanRequiredNo);
		System.out.println("Customer phone"+CustPhone);

		//Fetching Board Rate to purchase amount above 2 lakhs
		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");			

		//Step 5: .Go to transaction, Search the customer with phone number.
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(CustPhone,UtilityTestData.MenuBarOptions[5]);

		//Step 6: Add SKU (above 2 lakhs)
		//Step 7: click on Add to cart button
		//Expected :Check calculation Billing Screen
		//*No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),
		//*Displayed Subtotal,*TAX,*Total Amount
		double SaleAmount                     = PANCardLimitValidationTestData.TC164_CashLimit;
		double RequiredSaleQty                = SaleAmount / CurrentGoldRate22k;
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter                        = 1;
		System.out.println("Required Sale Quantity"+RequiredSaleQty);
		Thread.sleep(3000);

		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				PANCardLimitValidationTestData.TC164_TransferType,
				PANCardLimitValidationTestData.TC164_FromCounterGold,
				PANCardLimitValidationTestData.TC164_MetalTypeGold,
				RequiredSaleQty,
				PANCardLimitValidationTestData.TC164_SkuCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");

		for (String Sku : SkuListGold)
		{
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Discount         = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax              = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotal         = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount       = TransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 8: Click on Save to estimation button
		//Expected:  Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		// Note: check for the validation to update PAN card
		Thread.sleep(3000);

		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "The validation message -PAN number is required this customer-is displayed in the warning message");	
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Close"));

		//Updating Pan card to the customer
		Thread.sleep(3000);
		appUtils.AddOrVerifyPan();

		//Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC164_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String TaxableValue        = SubTotal;
		String InvoiceTotal        = TotalAmount;
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step 9:Recall estimate from cashier screen
		//Verify whether the recalled item is the same as the item in the cart.
		//Note: Cashier Screen after recall *No of Product lines  *Displayed Item Name *Displayed Quantity  *Displayed TOTAL (without Tax)
		// *Displayed Subtotal	 *TAX*Total Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));	
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Validation Recall page
		appUtils.RecallEstimateItemValidation(
				ProductNames,        
				ProductQtyList,             
				ProductNames,      
				ProductQtyList,   
				Double.parseDouble(SubTotal),
				Double.parseDouble(Tax),
				Double.parseDouble(TotalAmount),
				Integer.parseInt(LinesCount),
				"PAN Card RecallPage"                      
				);	



		//Step 10: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC164_TransationType);

		//Step 11: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 12: Choose a sales representative
		try {
			Thread.sleep(3000);
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			} else {
				System.out.println("Sales Representative popup not displayed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while handling Sales Representative popup: " + e.getMessage());
		}

		Map<String, String> RecallTransactionDataLines = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(RecallTransactionDataLines, ProductNames, ProductQtyList, ProductTotalList, "Cashier Screen");

		//Step 13: Click on the Amount
		//Note:Customer pays partially with a card, then completes the payment with cash.
		//14.Select a Payment method(Card)
		// 15.Enter the Amount, the Appr code,the Card No.
		// 16.Click on the Amount
		String InvoiceAmountInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		double InvoiceAmount             = Double.parseDouble(InvoiceAmountInBillingPge.replaceAll("[^0-9.]", ""));

		//Subtract cash limit of Rs.199999 from Invoice Amount
		double CashLimit = PANCardLimitValidationTestData.TC164_CashLimit;
		double CashAmount=0.00;
		double CardAmount=0.00;
		if (InvoiceAmount > CashLimit) 
		{
			CashAmount = CashLimit;               
			CardAmount = InvoiceAmount - CashLimit; 
		} 
		else 
		{
			CashAmount = InvoiceAmount; 
			CardAmount = 0;          
		}
		CardAmount = Math.round(CardAmount * 100.0) / 100.0;
		double PaymentAmountCash = CashAmount;
		Thread.sleep(2000);
		// 17.Select a Payment method(Cash)
		// 18.Enter the Amount
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PANCardLimitValidationTestData.TC164_PaymentMethod));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(CashAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(2000);

		//Remaining payment via card
		String PaymentViaCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 19.Post the Invoice
		//Expected : Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);

		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC164_FinalTaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount    = SubTotal;
		String TotalDiscount  = Discount;
		String TotalQtyPcs    = LinesCount;
		double RoundNetValue  = CashAmount + Double.parseDouble(PaymentViaCard);
		String NetValue       = String.format("%.2f", RoundNetValue);
		String PdfInvoiceNo   = pdfUtils.SaleInvoiceCashPdfValidation(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				String.valueOf(PaymentViaCard),
				String.valueOf(PaymentAmountCash),
				SkuListGold,
				ScannedSkuDataMap);		
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Customer without pan card having Invoice amount more than 2 lak -Same day multiple transaction
	// Automation ID : TC320
	// Author: Vishnu Manoj K
	// </summary>
	public void TC320_PANCardLimitValidation() throws Exception {

		//First Transaction
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2.Click on Add customer
		//Step 3.Click on "Create new customer"
		//Step4.Click on Customer type as "Person", Add mail id, religion, DOB,Address and save
		String CustomerId =appUtils.CreateANewCustomerWithPan("No");

		//Step 5.Go to transaction, Search the customer with phone number.
		appUtils.SearchByCustomerID(CustomerId ,UtilityTestData.MenuBarOptions[5]);

		//Step 6.Add SKU (below 2 lakh)
		//Expected: Check calculation Note:Billling Screen*No of Product lines *Displayed Item Name *Displayed Quantity*Displayed TOTAL (without Tax) *Displayed Subtotal *TAX*Total Amount
		//Step 7.click on Add to cart button
		RequiredSKUResult SkuGoldSaleItem = appUtils.GetRequiredQuantitySKU(
				PANCardLimitValidationTestData.TC320_TransferType,
				PANCardLimitValidationTestData.TC320_FromCounter,
				PANCardLimitValidationTestData.TC320_MetalType,
				PANCardLimitValidationTestData.TC320_MetalWeightBelow,
				PANCardLimitValidationTestData.TC320_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.RequiredQtySKUs;
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter = 1;	
		String RpRateInSkutemDetailsPge ="";
		String TotalAmountInSkuItemDetailsPge ="";
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal =TransactionDataLine.get("Subtotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String NetWeightFromSku = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");
		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 8.Click on Save to estimate button.		
		//Expected: Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarSelect("Save estimation"));
		double CalculatedNetWeight = Double.parseDouble(NetWeightFromSku);
		String TotalNetWeight = String.format("%.3f", CalculatedNetWeight);
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC320_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue=SubTotal;
		String InvoiceTotal=TotalAmount;
		int LinesCount = SkuList.size();
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				Integer.toString(LinesCount),
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step 9.Recall estimate from cashier screen
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 10.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC320_TransactionType);

		//Step 11.Click on Process Estimation 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 12.Choose a sales representative
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen  = TransactionDataLineInCashierScreen.get("Subtotal");
		String TaxInCashierScreen  = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen  = TransactionDataLineInCashierScreen.get("TotalAmount");
		String NetTotalInCashierScreen  = TransactionDataLineInCashierScreen.get("NetTotal");
		String ActualLinesCountInCashierScreen  = TransactionDataLineInCashierScreen.get("LinesCount");
		for (String Key : TransactionDataLine.keySet()) {
			String ExpectedValue = TransactionDataLine.get(Key);        // Billing Page value
			String ActualValue   = TransactionDataLineInCashierScreen.get(Key);  // Cashier Screen value
			if (ExpectedValue == null && ActualValue == null) { System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' – both values are null");
			} else if (ExpectedValue != null && ExpectedValue.equals(ActualValue)) {
				System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' " +"Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			} else {

				asrt.assertEquals(ActualValue,ExpectedValue,"Billing Page vs Cashier Screen: Mismatch in '" + Key + "' " + "Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");

			}
		}

		//Step 13.Click on the Amount
		//Step 14.Select a Payment method (Cash )
		String PaymentAmountCash = appUtils.PaymentAfterRecallEstimate("Cash");

		//Step 19: Post the Invoice
		//Expected Result: Check final invoice details,* save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC320_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotal;
		String TotalDiscount=Discount;
		String TotalQtyPcs=ActualLinesCountInCashierScreen;
		String NetValue=NetTotal;
		String PdfInvoiceNo = pdfUtils.SaleInvoiceCashPdfValidationCash(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				String.valueOf(PaymentAmountCash),
				SkuList,
				ScannedSkuDataMap);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Second Transaction
		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(CustomerId ,UtilityTestData.MenuBarOptions[5]);

		// Step 5: Scan SKU and click on Add to cart button
		// Step 6: Select any one of the sales agent
		// Expected Result: Check calculation Note:Billling Screen *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		SKUResult SkuListFinal = appUtils.GetOGSaleSku(
				PANCardLimitValidationTestData.TC320_TransferType,
				PANCardLimitValidationTestData.TC320_FromCounter,
				PANCardLimitValidationTestData.TC320_MetalType,
				Double.parseDouble(PANCardLimitValidationTestData.TC320_MetalWeightAbove),
				PANCardLimitValidationTestData.TC320_SkuGoldCount);
		List<String> SkuList2 = SkuListFinal.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap2 = new LinkedHashMap<>();
		for (int i = 0; i < SkuList2.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList2.get(i);
			// Step 1: Extract & store table values
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap2);
			// Step 2: Filter for this specific SKU
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap2, SkuNumber);
			// Step 3: Validate calculated values
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine2 = appUtils.TransactionSKUsLinesVerify(SkuList2, ScannedSkuDataMap2);
		String SubTotal2 = TransactionDataLine2.get("Subtotal");
		String Discount2 = TransactionDataLine2.get("Discount");
		String TotalGrossWeight2 = TransactionDataLine2.get("GrossWeight");
		String TotalNetWeight2 = TransactionDataLine2.get("NetWeight");
		String Tax2 = TransactionDataLine2.get("Tax");
		String TotalAmount2 = TransactionDataLine2.get("TotalAmount");
		String NetTotal2 = TransactionDataLine2.get("NetTotal");
		String LinesCount2 = TransactionDataLine2.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine2, ScannedSkuDataMap2);

		// Step 7: Click on Save to estimation button
		// Step 8: Save Esimate
		// Note: check for the validation to update PAN card
		// Expected Result: Verify the invoice details
		// Expected Result: Verify PAN card validation
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("PAN number is required this customer")),"PAN number is required this customer Message not displayed");

		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		appUtils.AddOrVerifyPan();
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarSelect("Save estimation"));

		// PDF Verification Step
		Thread.sleep(3000);
		String ProFormaInvoiceName2 = pdfUtils.DownloadAndRenameLatestPDF("TC320_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath2 = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName2;
		String Gst2= Tax2;
		String TaxableValue2=SubTotal2;
		String InvoiceTotal2=TotalAmount2;
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath2,
				TaxableValue2,
				Gst2,
				InvoiceTotal2,
				NetTotal2,
				LinesCount2,
				Double.parseDouble(TotalGrossWeight2),
				Double.parseDouble(TotalNetWeight2));

		// Step 9: Recall estimate from cashier screen
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
		ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, EstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+EstmnNumber+""); 

		// Step 10: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PANCardLimitValidationTestData.TC320_TransactionType);

		// Step 11: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 12: Select any Sales Representative [Due to new Fix in the application this step is Removed] 
		// Expected Result: Verify whether the recalled item is the same as the item in the cart.Note:-13.Cashier Screen after recall*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList2, ScannedSkuDataMap2);
		for (String Key : TransactionDataLine2.keySet()) {
			String ExpectedValue = TransactionDataLine2.get(Key);        // Billing Page value
			String ActualValue   = TransactionRecallDataLine.get(Key);  // Cashier Screen value
			if (ExpectedValue == null && ActualValue == null) { System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' – both values are null");
			} else if (ExpectedValue != null && ExpectedValue.equals(ActualValue)) {
				System.out.println("Billing Page vs Cashier Screen: Identical values for '" + Key + "' " +"Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			} else {
				asrt.assertEquals(ActualValue,ExpectedValue,"Billing Page vs Cashier Screen: Mismatch in '" + Key + "' " + "Billing Page = [" + ExpectedValue + "], Cashier Screen = [" + ActualValue + "]");
			}
		}

		// Step 13:  Click on the Amount
		// Step 14:  Select a Payment method(Cash or Card)
		String PaymentHDFCCard2 = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		// Step 15:  Post the Invoice
		// Expected Result: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice2 = pdfUtils.DownloadAndRenameLatestPDF("TC320_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath2 = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice2;
		String GrossAmount2 = SubTotal2;
		String TotalDiscount2=Discount2;
		String TotalQtyPcs2=LinesCount2;
		String NetValue2=NetTotal2;
		String PdfInvoiceNo2 = pdfUtils.SaleInvoicePdfValidation(
				InvoicePdfPath2,
				GrossAmount2,
				TotalDiscount2,
				TotalQtyPcs2,
				TotalNetWeight2,
				TotalGrossWeight2,
				TaxableValue2,
				NetValue2,
				PaymentHDFCCard2,
				SkuList2,
				ScannedSkuDataMap2);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
}