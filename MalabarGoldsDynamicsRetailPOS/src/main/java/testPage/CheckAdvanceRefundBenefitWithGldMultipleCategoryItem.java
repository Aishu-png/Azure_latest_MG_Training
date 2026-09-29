package testPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.devtools.v123.database.Database;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj;
import objectRepository.CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.PdcEntryExecution_Obj;
import testData.CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_TestData;
import testData.CommonData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.PdfUtilities;

public class CheckAdvanceRefundBenefitWithGldMultipleCategoryItem extends BasePge {

	BasePge base;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	Assert asrt;
	Login login = new Login(driver);
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSaleGoldandSilver_Obj NormalSaleGoldandSilverObj = new NormalSaleGoldandSilver_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRtgsReceivedObj = new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Obj CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj = new CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Obj();
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_TestData CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData = new CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_TestData();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj = new BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj=new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	PdcEntryExecution_Obj PdcEntryExecutionObj = new PdcEntryExecution_Obj();

	public CheckAdvanceRefundBenefitWithGldMultipleCategoryItem(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base);
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
	}


	// <summary>
	// Test Case Title : Offer advance settlement
	// Automation ID : TC296
	// Author: Bharath
	// </summary>
	public void TC296_CheckAdvanceRefundBenefitWithGldMultipleCategoryItem() throws Exception
	{
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);

		//Pre-Requisite:
		Map<String, Map<String, Object>> RefundedAdvance =erpUtils.RefundedAdvance(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_DepositType,CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_CollectionCount,CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_CustomerAccounts);
		if(RefundedAdvance.isEmpty()||RefundedAdvance==null)
		{
			System.out.println("Insufficient Refunded offer Accounts");
			throw new Exception("Insufficient Refunded offer advance records.");
		}
		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate("24k");
		List<String> CustomerIds = RefundedAdvance.values()
				.stream()
				.map(Details -> (String) Details.get("CustomerID"))
				.distinct()
				.collect(Collectors.toList());
		List<String> MetalRates = RefundedAdvance.values()
				.stream()
				.map(Details -> (String) Details.get("MetalRate"))
				.collect(Collectors.toList());
		List<String> DepositId= RefundedAdvance.values()
				.stream()
				.map(entry -> (String) entry.get("DepositId"))
				.collect(Collectors.toList());

		System.out.println(CustomerIds);
		List<WebElement> Advances= new ArrayList<WebElement>();
		List<WebElement> Option;
		String AdvanceMetalRate= "";
		Select CustomerOrderNoList;
		String CustomerOrderNo ="";
		String ExpectedCustomerOrder="";
		boolean ValidAccounts=false;

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		System.out.println(DepositId.size());

		for(int i=0;i<DepositId.size();i++)
		{

			String CurrentDepositId = DepositId.get(i);

			String CurrentCustomerAccount = RefundedAdvance.values()
					.stream()
					.filter(details -> CurrentDepositId.equals(details.get("DepositId")))
					.map(details -> (String) details.get("CustomerID"))
					.collect(Collectors.joining(","));


			AdvanceMetalRate = RefundedAdvance.values()
					.stream()
					.filter(details -> CurrentDepositId.equals(details.get("DepositId")))
					.map(details -> (String) details.get("MetalRate"))
					.collect(Collectors.joining(","));


			//Step 3: Select customer
			//Step 4: Click on add to estimation button
			appUtils.SearchByCustomerID(CurrentCustomerAccount, UtilityTestData.MenuBarOptions[5]);

			//Step 5: Click on customer adjustment button
			base.UnifiedClick(LoginPageObj.Edt_AlertText("Billing"));
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

			//Step 6: Click on Auto refund benefit
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldandSilverObj.Btn_return("Auto Refund Benefit"), 40);
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_return("Auto Refund Benefit"));

			//Step 7: Select offer advance and click on cart button
			//Expected: Verify whether the offer advances for the selected customer are displayed correctly in the 'Customer Order No' dropdown
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldandSilverObj.Btn_return("Advance Refund Benefit"), 30);
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_return("Advance Refund Benefit"));
			base.WaitforElementToBePresentWithTimeout(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), 30);
			Thread.sleep(3000);
			base.selectorByVisibleText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), "Customer_Advance");
			Thread.sleep(3000);
			if(base.WaitforElementToBePresentWithTimeout(CustomerCreationObj.Btn_AddtoCart("win-itembox"),20))
			{
				Advances = base.GetElement(CustomerCreationObj.Btn_AddtoCart("win-itembox"));
				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"));
				//base.selectorByVisibleText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), "Customer_Advance");
				base.WaitforElementToBeInteractable(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Customer order no"), 20);
				base.pressKey(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), "ENTER");		
				CustomerOrderNoList = new Select(driver.findElement(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Customer order no")));
				Option = CustomerOrderNoList.getOptions();
				for (WebElement opt : Option) {
					if (opt.getAttribute("value").equals(DepositId.get(i))) {
						opt.click();   
						CustomerOrderNo = opt.getAttribute("value");
						ExpectedCustomerOrder = DepositId.get(i);
						break;         
					}
				}
				Thread.sleep(3000);
				ValidAccounts=true;
				break;
			}
			else
			{
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue;
			}
		}
		if(ValidAccounts==false)
		{
			System.out.println("Insufficient Refunded offer Accounts");
			throw new Exception("Insufficient Refunded offer advance records.");

		}

		asrt.assertEquals(CustomerOrderNo, ExpectedCustomerOrder,"The offer advances for the selected customer are not displayed correctly in the 'Customer Order No' dropdown Expected:"+DepositId+"but got"+CustomerOrderNo+"in Advance Refund Page");

		List<WebElement> AdvanceWeights ;
		List<String> ProductNames = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		String OfferAdvanceWeight ="";
		int SkuCounter = 1;
		double ReturnOfferAdvanceWeight = 0.00;
		for(WebElement ValidAdvances:Advances) 
		{
			AdvanceWeights = base.GetElement(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceWeight("win-itembox","Refunded qty","gridcell"));
			Advances = base.GetElement(CustomerCreationObj.Btn_AddtoCart("win-itembox"));
			OfferAdvanceWeight = AdvanceWeights.get(0).getText();
			ReturnOfferAdvanceWeight += Double.parseDouble(OfferAdvanceWeight);
			Advances.get(0).click();
			ProductNames.add(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_DepositAdjustment);
			ProductTotalList.add(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_RefundedOfferTotalAmount);
			ProductQtyList.add(Integer.toString(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_ProductCount));
			base.UnifiedClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
			base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 60);
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_return("Advance Refund Benefit"));
			base.WaitforElementToBePresentWithTimeout(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), 30);
			base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"));
			base.selectorByVisibleText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), "Customer_Advance");
			CustomerOrderNoList = new Select(driver.findElement(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Customer order no")));
			Option = CustomerOrderNoList.getOptions();
			for (WebElement opt : Option) {
				if (opt.getAttribute("value").equals(CustomerOrderNo)) {
					opt.click();   
					break;         
				}
			}
		}


		// Step 8: Enter OTP
		// Step 9: Scan Gold SKU
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		// Note: In case of gold rate which ever is less that is considered
		// Expected: Check calculation
		//Expected: Check Average Rate
		//Expected: Note:Billing Screen
		//No of Product lines
		//Displayed Item Name
		//Displayed Quantity
		//Displayed TOTAL (without Tax)
		//Displayed Subtotal
		//TAX
		//Total Amount
		System.out.println(ReturnOfferAdvanceWeight);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(2000);

		String AdjustmentAmount = base.GetText(NormalSaleGoldandSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("\\u20B9", "").trim();		
		asrt.assertEquals(AdjustmentAmount, CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_RefundedOfferTotalAmount,"Mismatch in balance"+ CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_RefundedOfferTotalAmount+" and adjustment amount"+AdjustmentAmount+"in billing page");

		List<String> SkuList = appUtils.SelectMultipleSKUs(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_ProductCount,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_TransferType,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_FromCounter,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_MetalType);

		appUtils.HamBurgerButtonClick("iconShop"); 		
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,Double.toString(ReturnOfferAdvanceWeight),CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_ReturnPurity,AdvanceMetalRate,Double.toString(ErpGoldRate24k));
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Tax = TransactionDataLine.get("Tax").replaceAll("\\u20B9", "").trim();
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing page");

		//Step 10: Save estimate
		base.UnifiedClick(LoginPageObj.Edt_AlertText("Billing"));
		base.waitForClickable(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"), 20);
		base.scrollToElement(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"));
		base.ClickWithRetry(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"), LoginPageObj.Edt_AlertText("Save To Estimate"), 30);
		base.UnifiedClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Estimation no"));
		base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));			 
		Thread.sleep(8000);
		String ProFormaInvoiceName  = pdfUtils.DownloadAndRenameLatestPDF("TC296_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = ProductTotalList.getLast();
		String InvoiceTotal = Double.toString(Double.parseDouble(ProductTotalList.get(1).replaceAll("\\u20B9", "").trim()) + Double.parseDouble(Tax));
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue,
				Gst,
				InvoiceTotal, 
				"", 
				NetTotal, 
				TotalAmt);

		// Step 11: Recall estimate from cashier screen
		// Step 12: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		// Step 13: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"),CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_TransactionType);

		// Step 14: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		// Step 16: Click on the Amount
		// Expected: Cashier Screen after recall No of Product lines, Displayed Item
		// Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal,
		// TAX and Total Amount		
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);		
		String DiscountCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		// Step 17: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// Step 18 Post invoice
		// Expected : Check final invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice    = pdfUtils.DownloadAndRenameLatestPDF("TC296_FinalSaleInvoice");
		Thread.sleep(3000);
		String SaleInvoicePath     = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
		String GrossAmount         = TaxableValue;
		String TotalDiscount       = DiscountCashierScreen;
		String TotalQtyPcs         = Integer.toString(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC296_ProductCount);
		String PaymentHDFCCard     = PaymentAmount;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalGrossWt        = TotalNetWeightCashierScreen;
		String NetValue            = NetTotal;
		String PdfInvoiceNo        = pdfUtils.SaleInvoicePdfValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeightCashierScreen,
				TotalGrossWt,
				TaxableValue,
				NetValue,
				PaymentHDFCCard,
				SkuList,
				ScannedSkuDataMap);
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>			
	// Test Case Title : Add on advance settlement
	// Automation ID : TC297
	// Author: Bharath
	// </summary>
	public void TC297_CheckAdvanceRefundBenefitWithGldMultipleCategoryItem() throws Exception
	{
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);

		//Pre-Requisite:
		Map<String, Map<String, Object>> RefundedAdvance =erpUtils.RefundedAdvance(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_DepositType,CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_CollectionCount,CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_CustomerAccounts);
		if(RefundedAdvance.isEmpty()||RefundedAdvance==null)
		{
			System.out.println("Insufficient Refunded offer Accounts");
			throw new Exception("Insufficient Refunded offer advance records.");
		}
		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate("24k");
		List<String> CustomerIds = RefundedAdvance.values()
				.stream()
				.map(Details -> (String) Details.get("CustomerID"))
				.distinct()
				.collect(Collectors.toList());
		List<String> MetalRates = RefundedAdvance.values()
				.stream()
				.map(Details -> (String) Details.get("MetalRate"))
				.collect(Collectors.toList());

		List<String> ValidDepositAmount = new ArrayList<>();

		List<String> DepositId= RefundedAdvance.values()
				.stream()
				.map(entry -> (String) entry.get("DepositId"))
				.collect(Collectors.toList());

		System.out.println(CustomerIds);
		List<WebElement> Advances= new ArrayList<WebElement>();
		List<WebElement> Option;
		String AdvanceMetalRate= "";
		Select CustomerOrderNoList;
		String CustomerOrderNo ="";
		String ExpectedCustomerOrder="";
		boolean ValidAccounts=false;

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");		
		for(int i=0;i<DepositId.size();i++)
		{

			String CurrentDepositId = DepositId.get(i);

			String CurrentCustomerAccount = RefundedAdvance.values()
					.stream()
					.filter(details -> CurrentDepositId.equals(details.get("DepositId")))
					.map(details -> (String) details.get("CustomerID"))
					.collect(Collectors.joining(","));


			AdvanceMetalRate = RefundedAdvance.values()
					.stream()
					.filter(details -> CurrentDepositId.equals(details.get("DepositId")))
					.map(details -> (String) details.get("MetalRate"))
					.collect(Collectors.joining(","));


			ValidDepositAmount = RefundedAdvance.values()
					.stream()
					.filter(details ->CurrentDepositId.equals(details.get("DepositId")))
					.flatMap(details -> ((List<String>) details.get("Amounts")).stream())
					.map(amount -> amount.replaceAll(",", "")) 
					.collect(Collectors.toList());

			//Step 3: Select customer
			//Step 4: Click on add to estimation button
			appUtils.SearchByCustomerID(CurrentCustomerAccount, UtilityTestData.MenuBarOptions[5]);

			//Step 5: Click on customer adjustment button
			base.UnifiedClick(LoginPageObj.Edt_AlertText("Billing"));
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

			//Step 6: Click on Auto refund benefit
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldandSilverObj.Btn_return("Auto Refund Benefit"), 40);
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_return("Auto Refund Benefit"));

			//Step 7: Select offer advance and click on cart button
			//Expected: Verify whether the offer advances for the selected customer are displayed correctly in the 'Customer Order No' dropdown
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldandSilverObj.Btn_return("Advance Refund Benefit"), 30);
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_return("Advance Refund Benefit"));
			base.WaitforElementToBePresentWithTimeout(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), 30);
			Thread.sleep(3000);
			if(base.WaitforElementToBePresentWithTimeout(CustomerCreationObj.Btn_AddtoCart("win-itembox"),20))
			{
				Advances = base.GetElement(CustomerCreationObj.Btn_AddtoCart("win-itembox"));
				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"));
				base.selectorByVisibleText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), "Customer_Advance");
				base.WaitforElementToBeInteractable(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Customer order no"), 20);
				base.pressKey(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), "ENTER");		
				CustomerOrderNoList = new Select(driver.findElement(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Customer order no")));
				Option = CustomerOrderNoList.getOptions();
				for (WebElement opt : Option) {
					if (opt.getAttribute("value").equals(DepositId.get(i))) {
						opt.click();   
						CustomerOrderNo = opt.getAttribute("value");
						ExpectedCustomerOrder = DepositId.get(i);
						break;
					}
				}
				Thread.sleep(3000);
				ValidAccounts=true;
				break;
			}
			else
			{
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue;
			}
		}

		if(ValidAccounts==false) {
			System.out.println("Insufficient Refunded offer Accounts");
			throw new Exception("Insufficient Refunded offer advance records.");

		}

		asrt.assertEquals(CustomerOrderNo,ExpectedCustomerOrder,"The offer advances for the selected customer are not displayed correctly in the 'Customer Order No' dropdown Expected:"+DepositId+"but got"+CustomerOrderNo+"in Advance Refund Page");

		List<String> ProductNames = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		int SkuCounter = 1;
		System.out.println("Deposit Amount"+ValidDepositAmount.get(1));
		Thread.sleep(4000);
		base.waitForClickable(PdcEntryExecutionObj.Ele_OrderID("dataListLine", ValidDepositAmount.get(1).endsWith(".00")
				? ValidDepositAmount.get(1).replace(".00", "")
						: ValidDepositAmount.get(1),"1"), 20);
		base.ClickCondition(PdcEntryExecutionObj.Ele_OrderID("dataListLine", ValidDepositAmount.get(1).endsWith(".00")
				? ValidDepositAmount.get(1).replace(".00", "")
						: ValidDepositAmount.get(1),"1"));
		String ReturnOfferAdvanceWeight =   base.GetText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_OfferAdvanceWeight("win-item","true","Refunded qty","gridcell"));				
		ProductNames.add(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_DepositAdjustment);
		ProductTotalList.add(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_RefundedOfferTotalAmount);
		ProductQtyList.add(Integer.toString(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_ProductCount));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 60);

		// Step 8: Enter OTP
		// Step 9: Scan Gold SKU
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		// Note: In case of gold rate which ever is less that is considered
		// Expected: Check calculation
		//Expected: Check Average Rate
		//Expected: Note:Billing Screen
		//No of Product lines
		//Displayed Item Name
		//Displayed Quantity
		//Displayed TOTAL (without Tax)
		//Displayed Subtotal
		//TAX
		//Total Amount
		System.out.println(ReturnOfferAdvanceWeight);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(2000);

		String AdjustmentAmount = base.GetText(NormalSaleGoldandSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("\\u20B9", "").trim();		
		asrt.assertEquals(AdjustmentAmount, CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_RefundedOfferTotalAmount,"Mismatch in balance"+ CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_RefundedOfferTotalAmount+" and adjustment amount"+AdjustmentAmount+"in billing page");

		List<String> SkuList = appUtils.SelectMultipleSKUs(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_ProductCount,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_TransferType,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_FromCounter,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_MetalType);

		appUtils.HamBurgerButtonClick("iconShop"); 		
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,ReturnOfferAdvanceWeight,CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_ReturnPurity,AdvanceMetalRate,Double.toString(ErpGoldRate24k));
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);				
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Tax = TransactionDataLine.get("Tax").replaceAll("\\u20B9", "").trim();
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing page");

		//Step 10: Save estimate
		base.UnifiedClick(LoginPageObj.Edt_AlertText("Billing"));
		base.waitForClickable(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"), 20);
		base.scrollToElement(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"));
		base.ClickWithRetry(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"), LoginPageObj.Edt_AlertText("Save To Estimate"), 30);
		base.UnifiedClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Estimation no"));
		base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));			 
		Thread.sleep(8000);
		String ProFormaInvoiceName  = pdfUtils.DownloadAndRenameLatestPDF("TC297_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = ProductTotalList.getLast();
		String InvoiceTotal = Double.toString(Double.parseDouble(ProductTotalList.get(1).replaceAll("\\u20B9", "").trim()) + Double.parseDouble(Tax));
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue,
				Gst,
				InvoiceTotal, 
				"", 
				NetTotal, 
				TotalAmt);

		// Step 11: Recall estimate from cashier screen
		// Step 12: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		// Step 13: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"),CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_TransactionType);

		// Step 14: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		// Step 16: Click on the Amount
		// Expected: Cashier Screen after recall No of Product lines, Displayed Item
		// Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal,
		// TAX and Total Amount		
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);		
		String DiscountCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		// Step 17: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// Step 18 Post invoice
		// Expected : Check final invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice    = pdfUtils.DownloadAndRenameLatestPDF("TC297_FinalSaleInvoice");
		Thread.sleep(3000);
		String SaleInvoicePath     = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
		String GrossAmount         = TaxableValue;
		String TotalDiscount       = DiscountCashierScreen;
		String TotalQtyPcs         = Integer.toString(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC297_ProductCount);
		String PaymentHDFCCard     = PaymentAmount;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalGrossWt        = TotalNetWeightCashierScreen;
		String NetValue            = NetTotal;
		String PdfInvoiceNo        = pdfUtils.SaleInvoicePdfValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeightCashierScreen,
				TotalGrossWt,
				TaxableValue,
				NetValue,
				PaymentHDFCCard,
				SkuList,
				ScannedSkuDataMap);
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>			
	// Test Case Title : Converted advance settlement
	// Automation ID : TC295
	// Author: Benson
	// </summary>
	public void TC295_CheckAdvanceRefundBenefitWithGldMultipleCategoryItem() throws Exception
	{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);

		//Pre-Requisite:
		Map<String, Object> RefundAdvanceAccountDetails = erpUtils.RefundedAdvanceAccountDetails(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_AdvanceAccountsCount,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_IsRefunded,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_IsRefunBenefit,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_AdvanceType,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_DepositType);
				
		// Step : Pre-Requisite for taking BoardRate
		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate("24k");

		//Step 2:Click On Transaction Button
		appUtils.HamBurgerButtonClick("iconShop");	

		List<String> MatchedAccounts =(List<String>) RefundAdvanceAccountDetails.get("CustomerAccounts");
		System.out.println("RefundAdvanceAccountDetails = " + RefundAdvanceAccountDetails);
		System.out.println("MatchedAccounts = " + MatchedAccounts);

		String CustomerNo="";
		String AdvanceAmountRefunded="";
		String MetalRate="";
		boolean ValidAccounts=false;

		//for (int idx = 0; idx < MatchedAccounts.size(); idx--)
		for(int idx = MatchedAccounts.size() -1 ; idx >=0; idx--){
			CustomerNo = MatchedAccounts.get(idx);

			System.out.println("\nChecking PAN for Customer: " + CustomerNo );
			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);    
			try{appUtils.AddOrVerifyPan(); } 

			catch (Exception e) {
				if(base.isPresent(NormalSaleGoldandSilverObj.Ele_Payement("OTP verification")) ||
						base.isPresent(NormalSaleGoldandSilverObj.Ele_Payement("Warning!"))){
					base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					System.out.println("Otp required for this Customer"+CustomerNo);
					continue; // move to next customer
				}
			}
			appUtils.HamBurgerButtonClick("iconShop");
			appUtils.VoidTransaction();

			//Step 3: Select customer
			//Step 4 :Click on add to estimation button
			Thread.sleep(3000);
			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);

			//Step 5:Click on customer adjustment button
			base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldandSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

			//Step 6:Click on Auto Refund Benefit 
			base.buttonClick(LoginPageObj.Edt_AlertText("Auto Refund Benefit"));

			//Step 7:Select converted advance and click on cart button
			//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldandSilverObj.Btn_return("Advance Refund Benefit"), 30);
			base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_return("Advance Refund Benefit"));
			base.WaitforElementToBePresentWithTimeout(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), 30);
			Thread.sleep(3000);
			//List<WebElement>AdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
			//int AdvanceRowSize = AdvanceRows.size();
			if(base.WaitforElementToBePresentWithTimeout(CustomerCreationObj.Btn_AddtoCart("win-itembox"),30))
			{

				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"));
				base.selectorByVisibleText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Sel_AdvanceType("Advance type"), "Normal_Advance");
				MetalRate = base.GetText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_TransactionField("Metal Rate", "h4 ellipsis cell", 1));
				AdvanceAmountRefunded=base.GetText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_TransactionField("Refunded amount", "h4 ellipsis cell", 1));
				base.UnifiedClick(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_TransactionField("Transaction no", "h4 ellipsis cell", 1));
				Thread.sleep(3000);
				ValidAccounts=true;
				break;
			}
			else {
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue;

			}
		}
		if(ValidAccounts==false)
		{
			System.out.println("Insufficient Refunded Converted Accounts.All Refunded Converted accounts have no data");
			throw new Exception("Insufficient Refunded Converted advance records.All Refunded Converted accounts have no data");

		}
		List<String> ProductNames = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		int SkuCounter = 1;
		Thread.sleep(4000);
		String ReturnConvertedAdvanceWeight =   base.GetText(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Ele_TransactionField("Refunded qty", "h4 ellipsis cell", 1));				
		ProductNames.add(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_DepositAdjustment);
		ProductTotalList.add(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_RefundedOfferTotalAmount);
		ProductQtyList.add(Integer.toString(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_ProductCount));
		base.UnifiedClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 60);

		String AdjustmentAmount = base.GetText(NormalSaleGoldandSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("\\u20B9", "").trim();		
		asrt.assertEquals(AdjustmentAmount, CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_RefundedOfferTotalAmount,"Mismatch in balance"+ CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_RefundedOfferTotalAmount+" and adjustment amount"+AdjustmentAmount+"in billing page");

		// Step 8: Enter OTP
		// Step 9: Scan Gold SKU
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		// Expected: Check calculation
		//Expected: Check Average Rate
		//Expected: Note:Billing Screen
		//No of Product lines
		//Displayed Item Name
		//Displayed Quantity
		//Displayed TOTAL (without Tax)
		//Displayed Subtotal
		//TAX
		//Total Amount
		System.out.println(ReturnConvertedAdvanceWeight);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(2000);

		List<String> SkuList = appUtils.SelectMultipleSKUs(
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_ProductCount,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_TransferType,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_FromCounter,
				CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_MetalType);

		appUtils.HamBurgerButtonClick("iconShop"); 		
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,ReturnConvertedAdvanceWeight,CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_ReturnPurity,MetalRate,Double.toString(ErpGoldRate24k));
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);				
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Tax = TransactionDataLine.get("Tax").replaceAll("\\u20B9", "").trim();
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing page");

		//Step 10: Save estimate
		base.UnifiedClick(LoginPageObj.Edt_AlertText("Billing"));
		base.waitForClickable(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"), 20);
		base.scrollToElement(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"));
		base.ClickWithRetry(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemObj.Btn_Back("Back","Back"), LoginPageObj.Edt_AlertText("Save To Estimate"), 30);
		base.UnifiedClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Estimation no"));
		base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));			 
		Thread.sleep(8000);
		String ProFormaInvoiceName  = pdfUtils.DownloadAndRenameLatestPDF("TC295_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = ProductTotalList.getLast();
		String InvoiceTotal = Double.toString(
				Double.parseDouble(
						ProductTotalList.get(1)
						.replace("\u20B9", "")
						.replace(",", "")
						.trim()
						) +
				Double.parseDouble(
						Tax.replace("\u20B9", "")
						.replace(",", "")
						.trim()
						)
				);

		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue,
				Gst,
				InvoiceTotal, 
				"", 
				NetTotal, 
				TotalAmt);

		// Step 11: Recall estimate from cashier screen
		// Step 12: Select estimate and click on Recall estimation button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		// Step 13: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"),CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_TransactionType);

		// Step 14: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		// Step 16: Click on the Amount
		// Expected: Cashier Screen after recall No of Product lines, Displayed Item
		// Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal,
		// TAX and Total Amount		
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);		
		String DiscountCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		// Step 17: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// Step 18 Post invoice
		// Expected : Check final invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.UnifiedClick(NormalSaleGoldandSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice    = pdfUtils.DownloadAndRenameLatestPDF("TC295_FinalSaleInvoice");
		Thread.sleep(3000);
		String SaleInvoicePath     = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
		String GrossAmount         = TaxableValue;
		String TotalDiscount       = DiscountCashierScreen;
		String TotalQtyPcs         = Integer.toString(CheckAdvanceRefundBenefitWithGldMultipleCategoryItemTestData.TC295_ProductCount);
		String PaymentHDFCCard     = PaymentAmount;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalGrossWt        = TotalNetWeightCashierScreen;
		String NetValue            = NetTotal;
		String PdfInvoiceNo        = pdfUtils.SaleInvoicePdfValidation(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeightCashierScreen,
				TotalGrossWt,
				TaxableValue,
				NetValue,
				PaymentHDFCCard,
				SkuList,
				ScannedSkuDataMap);
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}




}

