package testPage;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import objectRepository.CustomerCreation_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities.SKUTypeResult;

public class InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived extends BasePge{

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	MathUtilities mathUtils;
	PdfUtilities pdfUtils;
	ErpUtilities erpUtils;

	Login login = new Login(driver); 
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	LoginPage_Obj LoginPageObj = new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	RtgsPaymentToCustomer_TestData RtgsPaymentToCustomerTestData = new RtgsPaymentToCustomer_TestData();
	InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived_TestData InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData = new InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRTGSReceivedObj =new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	WebDriverWait wait;

	public InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}

	//<summary>
	// Test Case Title : Invoice with multiple payment method in single invoice
	// Automation ID : TC160
	// Author : Aiswarya
	// </summary>

	public void TC160_InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre requisite                                                    
		//To create a new RTGS account:
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
		Map<String, String> RtgsDetails= erpUtils.RtgsAccountCreation(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_StringAmountToDeposit, InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_CustomerId);	

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_CustomerId, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan Multiple uncut and precia SKU's and click on Add to cart button
		//Expected Result: Check calculation 
		//Note: Billing Screen - No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		//Step 6: Select any one of the sales agent
		List<String> SkuList=new ArrayList<>();
		List<String> Uncut = appUtils.SelectMultipleSKUs(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_SkuCount,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_TransferType,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_FromCounter1,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_MetalType);
		SkuList.addAll(Uncut);
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> Precia = appUtils.SelectMultipleSKUs(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_SkuCount,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_TransferType,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_FromCounter2,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_MetalType);
		SkuList.addAll(Precia);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 7: Click on Save to estimation button
		//Step 8: Save Esimate
		//Expected Result:Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = SubTotal.replaceAll("[^\\d.]", "");
		String Gst                 = Tax.replaceAll("[^\\d.]", "");
		String InvoiceTotal        = TotalAmount.replaceAll("[^\\d.]", "");
		String NetTotalProforma	   = NetTotal.replaceAll("[^\\d.]", "");
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC160_ProFormaInvoice");
		String ProFormaInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoice,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotalProforma,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight)
				);

		//Step 9: Recall estimate from cashier screen
		//Step 10: Select estimate and click on recall estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page"); 

		//Step 11: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_TransactionType);

		//Step 12: Click on Process Estimation
		//Step 13: Select any Sales Representative
		//Expected Result: Cashier Screen after recall *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		//Step 14: Click on the Amount
		String InvoiceAmountInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		double InvoiceAmount = Double.parseDouble(InvoiceAmountInBillingPge.replaceAll("[^0-9.]", ""));

		//Always pay Rs.100 as Cash
		double CashAmount = 100.00;

		//Remaining amount after cash
		double RemainingAmount = InvoiceAmount - CashAmount;

		// Split remaining: 90% Card, 10% RTGS
		double CardAmount = Math.round(RemainingAmount * 0.90 * 100.0) / 100.0;

		//Step 15: Select a Payment method(Cash)
		//[Pay some of the amount through cash payment method]
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"), String.valueOf(CashAmount));
		String CashPaymentAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(2000);

		//Step 16: Click on the Amount again
		//Step 17: Select a Payment method(card)
		//[Pay some of the amount through card payment method]
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(CardAmount));
		String CardPaymentAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", "");
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		Thread.sleep(2000);


		//Step 18: Select a Payment method(RTGS)
		//[Pay the remaining of the amount through RTGS payment method]
		String RtgsAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(LoginPageObj.Edt_AlertText("RTGS/Received"));

		//Step 18.1: Enter the RTGS amount
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"),RtgsAmount);
		String RtgsAmountReceived = RtgsAmount.replace("₹", "").replace(",", "").trim();
		String RtgsPaymentAmount = base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"));

		//Step 18.2: Enter the UTR number
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"), RtgsDetails.get("UtrNumber"));
		String BalanceBeforePayments = base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number", "RTGSBalance"));
		double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayments);
		String ActualBalanceBeforePaymentInErp = RtgsDetails.get("RtgsBalanceAmntBeforePayment").replace(",", "");
		double ActualBalanceBeforePayment = Double.parseDouble(ActualBalanceBeforePaymentInErp);
		double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - Double.parseDouble(RtgsAmountReceived);
		ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0;

		//Step 18.3: Click on "Apply" then "Ok"
		base.buttonClick(LoginPageObj.Btn_SingnIn("applybtn")); 
		wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1")); 

		//Step 19:  Post the Invoice 
		//Expected Result: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")); 
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC160_TaxInvoice");
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName + "";
		String GrossAmount      = SubTotal.replaceAll("[^\\d.]", "");
		String TotalValue       = InvoiceTotal;
		String TotalQtyPcs      = LinesCount;
		String TotalDiscount    = Discount;
		String NetValue = String.format("%.2f", Double.parseDouble(NetTotal.trim().replaceAll("[^\\d.]", "")));

		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationWithCashCardRtgsReceived(
				TaxInvoicePath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				Double.toString(CashAmount),
				RtgsAmountReceived,
				Double.toString(CardAmount),
				SkuList,
				ScannedSkuDataMap
				);


		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

		//ERP:
		//1.Go to ERP and search RTGS payment receipt details
		//2.Filter with UTR number/Customer account number
		//3.Check the 'Balance Amount' Before and after Payment
		erpUtils.ValidateRTGSPaymentBalance(
				RtgsDetails.get("UtrNumber"),
				InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC160_CustomerId,
				ExpectedBalanceBeforePayment,
				ActualBalanceBeforePayment,
				ExpectedBalanceAfterPayment
				);
	}



	//<summary>
	// Test Case Title : Invoice with rtgs received
	// Automation ID : TC161
	// Author : Neethu 
	// </summary>
	public void TC161_InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived() throws Exception{


		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre requisite                                                    
		//To create a new RTGS account:
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
		Map<String, String> RtgsDetails= erpUtils.RtgsAccountCreation(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_StringAmountToDeposit, InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_CustomerId);	

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_CustomerId, UtilityTestData.MenuBarOptions[5]);
		//warning message click ok 
		//base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));

		//Step 5: Scan SKU's and click on Add to cart button
		//Expected Result: Check calculation
		// Step 6 : Select any one of the sales agent
		List<String> SkuList = appUtils.SelectMultipleSKUs(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_SkuGoldCount,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_TransferType,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_FromCounter,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			// Step 1: Extract & store table values
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			// Step 2: Filter for this specific SKU
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			// Step 3: Validate calculated values
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);


		// Step 7: Click on Save to estimation button
		// Step 8: Save Esimate
		// Expected Result:Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = SubTotal;
		String Gst                 = Tax;
		String InvoiceTotal        = TotalAmount;
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC251_ProFormaInvoice");
		String ProFormaInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoice,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight)
				);

		//Step 9 : Recall estimate from cashier screen
		//Step 10 : Select estimate and click on recall estimate button
		//Expected : Verify whether the recalled item is the same as the item in the cart.
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
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page"); 

		// Step 11 : Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_TransactionType);

		// Step 12 : Click on Process Estimation
		// Expected Result:Cashier Screen after recall *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		base.setZoom(driver, 60);
		Thread.sleep(3000);

		// Step 13 : Select any Sales Representative
		//base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		// Step 14 :  Click on the Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// Step 15 :  Select a Payment method(RTGS/Received)
		base.buttonClick(LoginPageObj.Edt_AlertText(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_PaymentMethod));

		//Step15.1 Enter the RTGS amount
		String AmountDue=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("text","AmountDue"));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"), AmountDue);
		double RtgsValue = Double.parseDouble(AmountDue);

		// Round off to 2 decimal places
		double RoundedRtgsValue = Math.round(RtgsValue);

		// Convert back to String with ".00" format
		String RtgsFinalAmount = String.format("%.2f", RoundedRtgsValue);  //FOR STEP 16 
		double RtgsAmountReceived = Double.parseDouble(AmountDue);   //SAME FOR STEP 16 SOMETIMES FOR KALPETTA IF ROUND OFF NOT NEEDED THERE
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1")));

		//15.2.Enter the UTR number
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"),RtgsDetails.get("UtrNumber"));
		Thread.sleep(4000);
		String BalanceBeforePayments=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","RTGSBalance"));
		double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayments);//Expected Balance before payment in POS
		String ActualBalanceBeforePaymentInErp=RtgsDetails.get("RtgsBalanceAmntBeforePayment").replace(",",""); //Expected Balance Before Payment in ERP
		ActualBalanceBeforePaymentInErp = ActualBalanceBeforePaymentInErp.replaceAll("[^0-9.]", "").trim();
		BigDecimal ActualBalanceBeforePaymentInErpvalue = new BigDecimal(ActualBalanceBeforePaymentInErp).stripTrailingZeros();
		String ActualBalanceBeforePaymentInErpResult = ActualBalanceBeforePaymentInErpvalue.toPlainString();
		double ActualBalanceBeforePayment=Double.parseDouble(ActualBalanceBeforePaymentInErp);
		double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - RtgsAmountReceived;
		ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0; //For rounding off to 2 decimal places
		Thread.sleep(5000);
		String ExpectedBalanceAmount=base.GetValue(NormalSaleGoldAndSilverObj.Edt_TextField("col", "number")).replaceAll("[^0-9.]", "").trim();

		//15.3.Click on "Apply" then "Ok"
		//Expected: The balance amount in the rtgs account should get shown
		asrt.assertEquals(ActualBalanceBeforePaymentInErpResult, ExpectedBalanceAmount,"Mismatch balance amount Actual is "+ActualBalanceBeforePaymentInErpResult+" and Expected balance amount is "+ExpectedBalanceAmount+" in RTGS receipt page");
		base.buttonClick(LoginPageObj.Btn_SingnIn("applybtn"));  //click on apply
		wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
		Thread.sleep(10000);
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1"));   //click on ok

		// Step 16:  Post the Invoice
		// Expected Result: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")); 
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC161_TaxInvoice");
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName + "";
		String GrossAmount      = SubTotal;
		String TotalValue       = InvoiceTotal;
		String TotalQtyPcs      = LinesCount;
		String TotalDiscount    = Discount;
		String NetValue = String.format("%.2f", Double.parseDouble(NetTotal.trim().replaceAll("[^\\d.]", "")));

		// Round off after that
		double rounded = Math.round(Double.parseDouble(NetValue));
		String RoundedNetValue = String.format("%.2f", rounded); //FOR VIJAYAWADA MAINLY SOMETIMES WE CAN USE NETVALUE FOR KALPETTA

		//Note Sometimes in Kalpetta rounded values not required for assertion  in.., vijayawada terminal rounded values are needed
		if(UtilityTestData.WarehouseId!="5011")
		{
			String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationWithRtgsReceived(
					TaxInvoicePath,
					GrossAmount,
					TotalDiscount,    
					TotalQtyPcs,
					TotalNetWeight,
					TotalGrossWeight,
					TaxableValue,
					RoundedNetValue,
					RtgsFinalAmount,
					SkuList,
					ScannedSkuDataMap
					);
		}else {
			String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationWithRtgsReceived(
					TaxInvoicePath,
					GrossAmount,
					TotalDiscount,    
					TotalQtyPcs,
					TotalNetWeight,
					TotalGrossWeight,
					TaxableValue,
					NetValue,
					AmountDue,
					SkuList,
					ScannedSkuDataMap );
		}

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

		//ERP:
		//1.Go to ERP and search RTGS payment receipt details
		//2.Filter with UTR number/Customer account number
		//3.Check the 'Balance Amount' Before and after Payment
		erpUtils.ValidateRTGSPaymentBalance(
				RtgsDetails.get("UtrNumber"),
				InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC161_CustomerId,
				ExpectedBalanceBeforePayment,
				ActualBalanceBeforePayment,
				ExpectedBalanceAfterPayment
				);

	}

	//<summary>
	// Test Case Title : Invoice with cash,card and rtgs received
	// Automation ID : TC162
	// Author : Vishnu Manoj K
	// </summary>

	public void TC162_InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceived() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre requisite  
		//Customer must have balance amount in RTGS account
		//To create a new RTGS account:
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
		Map<String, String> RtgsDetails= erpUtils.RtgsAccountCreation(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_StringAmountToDeposit, InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_CustomerId);	

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_CustomerId, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan SKU and click on Add to cart button
		//Expected Result: Check calculation
		//Note: Billing Screen - No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		//Step 6: Select any one of the sales agent
		List<String> SkuList=new ArrayList<>();
		List<String> GoldSku = appUtils.SelectMultipleSKUs(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_SkuCount,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_TransferType,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_FromCounter1,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_MetalType);
		SkuList.addAll(GoldSku);
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> PreciaSku = appUtils.SelectMultipleSKUs(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_SkuCount,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_TransferType,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_FromCounter2,InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_MetalType);
		SkuList.addAll(PreciaSku);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String LinesCount = TransactionDataLine.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 7: Click on Save to estimation button
		//Step 8: Save Esimate
		//Expected Result:Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(10000);
		String TaxableValue        = SubTotal;
		String Gst                 = Tax;
		String InvoiceTotal        = TotalAmount;
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC162_ProFormaInvoice");
		String ProFormaInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoice,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight)
				);

		//Step 9: Recall estimate from cashier screen
		//Step 10: Select estimate and click on recall estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page");

		//Step 11: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_TransactionType);

		//Step 12: Click on Process Estimation
		//Step 13: Select any Sales Representative
		//Expected Result: Cashier Screen after recall *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		//Step 14: Click on the Amount
		String InvoiceAmountInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		double InvoiceAmount = Double.parseDouble(InvoiceAmountInBillingPge.replaceAll("[^0-9.]", ""));

		//Step 15: Select a Payment method as RTGS/Received,cash,card when select RTGS
		//[Pay the remaining of the amount through RTGS payment method]
		double Amount = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")).replaceAll("[^0-9.]", ""));
		long WithOutDecimalAmount = (long) (Amount - (Amount % 2)); // make even by subtracting remainder
		double WithDecimalAmount = Math.round((Amount - WithOutDecimalAmount) * 100.0) / 100.0; // remainder (the subtracted part)

		double AmountHalf =WithOutDecimalAmount / 2;
		String RtgsAmount= String.valueOf(AmountHalf + WithDecimalAmount); // half of even part + remainder
		double CashAmount = Double.parseDouble(InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_CashPaymentAmount);
		double CardAmount = AmountHalf - CashAmount;

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(LoginPageObj.Edt_AlertText("RTGS/Received"));

		//Step 15.1: Enter the RTGS amount
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"),RtgsAmount);
		String RtgsAmountReceived = RtgsAmount.replace("₹", "").replace(",", "").trim();
		//String RtgsPaymentAmount = base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount")).replace(",", "");

		//Step 15.2: Enter the UTR number
		//Expected Result: The balance amount in the rtgs account should get shown
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"), RtgsDetails.get("UtrNumber"));
		String BalanceBeforePayments = base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number", "RTGSBalance"));
		double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayments);
		String ActualBalanceBeforePaymentInErp = RtgsDetails.get("RtgsBalanceAmntBeforePayment").replace(",", "");
		double ActualBalanceBeforePayment = Double.parseDouble(ActualBalanceBeforePaymentInErp);
		double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - Double.parseDouble(RtgsAmountReceived);
		ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0;
		Assert.assertEquals(ActualBalanceBeforePayment,Double.parseDouble( InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_StringAmountToDeposit),"Mismatch in Balance before payment Actual is "+ActualBalanceBeforePayment+" and Expected Balance before payment is "+InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_StringAmountToDeposit+" in RTGS receipt page");
		
		//Step 15.3: Click on "Apply" 
		//Step 15.4: Click on Ok
		base.ClickCondition(LoginPageObj.Btn_SingnIn("applybtn"));
		wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

		//Step 16: Click on the Amount again
		//Step 17: Select a Payment method(card)
		//Step 18: Enter partial amount
		//[Pay some of the amount through card payment method]
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(CardAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		Thread.sleep(2000);
		
		//Step 19: Again click on amount
		//Step 20: Select a Payment method(Cash)
		//[Pay some of the amount through cash payment method]
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"), String.valueOf(CashAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(2000);

		//Step 19:  Post the Invoice
		//Expected Result: Check final invoice details * save receipt id for future reference
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC160_TaxInvoice");
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName + "";
		String GrossAmount      = SubTotal;
		//String TotalValue       = InvoiceTotal;
		String TotalQtyPcs      = LinesCount;
		String TotalDiscount    = Discount;
		String NetValue = String.format("%.2f", Double.parseDouble(NetTotal.trim().replaceAll("[^\\d.]", "")));

		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationWithCashCardRtgsReceived(
				TaxInvoicePath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				Double.toString(CashAmount),
				RtgsAmountReceived,
				Double.toString(CardAmount),
				SkuList,
				ScannedSkuDataMap
				);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

		//ERP:
		//1.Go to ERP and search RTGS payment receipt details
		//2.Filter with UTR number/Customer account number
		//3.Check the 'Balance Amount' Before and after Payment'
		erpUtils.ValidateRTGSPaymentBalance(
				RtgsDetails.get("UtrNumber"),
				InvoiceWithAllPaymentMethodsCashCardB2bAdjustmentRtgsReceivedTestData.TC162_CustomerId,
				ExpectedBalanceBeforePayment,
				ActualBalanceBeforePayment,
				ExpectedBalanceAfterPayment
				);
	}


}
