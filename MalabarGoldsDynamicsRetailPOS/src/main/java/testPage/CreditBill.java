package testPage;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.CreditBill_TestData;
import testData.POC_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

public class CreditBill extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	MathUtilities mathUtils;
	PdfUtilities pdfUtils;

	Login login = new Login(driver); 
	POC_TestData poctestdata = new POC_TestData();
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	CreditBill_TestData CreditBillTestData = new CreditBill_TestData();
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRTGSReceivedObj = new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj  OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj = new BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();


	public CreditBill(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
	}



	// <summary>
	// Test Case Title : Posting in customer account
	// Challenges : Steps 17–20 are not available in some stores, so I updated the code to work both with and without those steps.
	// Automation ID : TC181
	// Author: Aiswarya
	// </summary>
	public void TC181_CreditBill() throws Exception {

		//Step1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite : Customer should have credit balance facility
		String OriginalWindow = driver.getWindowHandle();
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		Thread.sleep(2000);
		driver.navigate().refresh();
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),"Credit Application");
		base.setZoom(driver, 35);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_ApplicationDate"));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Sort newest to oldest"));
		Thread.sleep(5000);
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_Store"));
		Thread.sleep(5000);
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("StoreNumber"), UtilityTestData.WarehouseId);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__MGDCreditApplication_Store_ApplyFilters_label"));
		Thread.sleep(5000);
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_BalanceAmount"));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Sort largest to smallest"));
		Thread.sleep(5000);
		String BalanceAmountBefore = base.GetValue(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_ErpCreditNo("Balance Amount",1));
		base.ScrollIntoView(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_Approved"));
		base.Hover(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_Approved"));
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_Approved"));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Ele_ERPAdjust("list"));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Ele_ERPYes("Yes"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__MGDCreditApplication_Approved_ApplyFilters_label"));
		Thread.sleep(5000);
		try {
			WebElement scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
			Actions actions = new Actions(driver);
			actions.clickAndHold(scrollbar).moveByOffset(-5, 0).release().perform();
		} 
		catch (Exception e) {
			System.out.println("Scrollbar not present, skipping scroll step");
		}
		Thread.sleep(15000);
		base.ScrollIntoView(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_CreditReference"));
		Thread.sleep(5000);
		String CreditReferenceNo = base.GetValue(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_ErpCreditNo("Credit Reference",1));
		Thread.sleep(2000);
		String CustomerAccountNo = base.GetValue(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_ErpCreditNo("Customer account",1));
		driver.switchTo().window(OriginalWindow);

		//Step3. Select customer
		//Step4. Click on add to estimation button
		appUtils.SearchByCustomerID(CustomerAccountNo,UtilityTestData.MenuBarOptions[5]);

		//Step5. Scan SKU and click on Add to cart button
		//Step6. Select any one of the seles agent
		//Expected Result: Check calculation
		List<String> SkuList = appUtils.SelectMultipleSKUs(CreditBillTestData.TC181_SkuGoldCount,
				CreditBillTestData.TC181_TransferType,
				CreditBillTestData.TC181_FromCounter,
				CreditBillTestData.TC181_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		String ProductName = "";
		String ProductQty = "";
		String ProductTotal = "";
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductName = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName");
			ProductQty = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight");
			ProductTotal = ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}		

		//Expected Result: Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);  
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String Discount 			= TransactionDataLine.get("Discount");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String TotalGrossWeight   	= TransactionDataLine.get("GrossWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step7. Click on Save to estimation button
		//Step8. save esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
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

		//Expected Result: Verify the invoice details
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC181_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;
		String Gst = Tax.replaceAll("[^\\d.]", "");
		String InvoiceTotal = TotalAmount.replaceAll("[^\\d.]", "");
		String NetTotalPdf  = NetTotal.replaceAll("[^\\d.]", "");
		String TaxableValue = SubTotal.replaceAll("[^\\d.]", "");
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotalPdf,
				ActualLinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step9. Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Step10. Select estimate and click on Recall estimation button
		//Expected Result: Verify whether the recalled item is the same as the item in the cart
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step11. Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), CreditBillTestData.TC181_TransactionTypeSales);

		//Step12. Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		appUtils.HandleSpecifyRateModalInRecall();

		//Step13. Choose a sales representative
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Expected Result: Note:-13.Cashier Screen after recall*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightInCashierScreen   		= TransactionDataLineInCashierScreen.get("NetWeight");
		String TotalGrossWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionDataLineInCashierScreen, ScannedSkuDataMap);

		//Step14. Click on the Amount
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		//Step15. Select a Payment method (customer account)
		String CustomerAccountAmount = CreditBillTestData.TC181_CustomerAccountAmount;
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Customer Account"));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"), String.valueOf(CustomerAccountAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));

		//Step16. Enter credit reference number
		Thread.sleep(3000);
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("approvercode"),CreditReferenceNo );
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		Thread.sleep(3000);

		if (base.isPresent(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("textInputDialog")))
		{
			//Step17. Enter C/O details
			base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("textInputDialog"),CreditBillTestData.TC181_Remarks );
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

			//Step18. Select due date
			Thread.sleep(3000);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), UtilityTestData.DueYear);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

			//Step19. Enter remarks
			Thread.sleep(3000);
			base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("textInputDialog"),CreditBillTestData.TC181_Remarks );

			//Step20. Click on ok button
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			Thread.sleep(5000);
		}

		//Step21. Again click on amount
		//Step22. Select payment method as card
		String PaymentAmountHDFC = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step23. Post the Invoice
		//Expected Result: Check final invoice details* save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC181_TaxInvoice");
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName + "";
		String GrossAmount = TaxableValue;
		String TotalQtyPcs = ActualLinesCount; 
		pdfUtils.SaleInvoicePdfValidationWithCustomerAccountAndHdfcCard(
				TaxInvoicePath,
				GrossAmount,
				Discount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetTotalPdf,
				PaymentAmountHDFC,
				CustomerAccountAmount,
				SkuList,
				ScannedSkuDataMap
				) ;


		//ERP
		//1. Open ERP
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		Thread.sleep(2000);
		driver.navigate().refresh();

		//2. Search credit application
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),"Credit Application");
		base.setZoom(driver, 35);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);

		//3. Search customer account
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_CustAccount"));
		Thread.sleep(5000);
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_GridAmount("CustAccount_CustAccount_"),CustomerAccountNo );
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_RunNowERP("__MGDCreditApplication_CustAccount_ApplyFilters_label"));
		Thread.sleep(5000);
		base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_CustomerAccount("MGDCreditApplication_BalanceAmount"));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Sort largest to smallest"));
		Thread.sleep(5000);

		//4. Check balance amount
		String ExpectedBalanceAmountAfter= Double.toString(Double.parseDouble(BalanceAmountBefore.replaceAll("[^\\d.]", "")) - Double.parseDouble(CustomerAccountAmount));   
		double BalanceAmountAfter = Double.parseDouble(base.GetValue(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_ErpCreditNo("Balance Amount",1)).replaceAll("[^\\d.]", ""));
		String ActualBalanceAmountAfter = Double.toString(BalanceAmountAfter);
		asrt.assertEquals(ActualBalanceAmountAfter,ExpectedBalanceAmountAfter,"Balance Amount mismatch: "+ExpectedBalanceAmountAfter+" but found "+ActualBalanceAmountAfter+" in Credit Application Page - Erp");

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
}