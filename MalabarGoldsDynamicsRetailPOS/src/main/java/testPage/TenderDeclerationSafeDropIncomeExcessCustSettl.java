package testPage;

import java.time.Duration;
import java.util.ArrayList;
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

import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj;
import objectRepository.BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj;
import objectRepository.CheckGPPRefundBenefitWithMultipleCategoryItem_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import objectRepository.PdcEntryExecution_Obj;
import objectRepository.StatementPosting_Obj;
import testData.ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData;
import testData.CommonData;
import testData.InvoiceWithSRandOG_TestData;
import testData.Utility_TestData;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities;
import utilPack.AppUtilities.ReturnDetails;

public class TenderDeclerationSafeDropIncomeExcessCustSettl extends BasePge {

	AppUtilities appUtils;
	PdfUtilities pdfUtils;
	ErpUtilities erpUtils;
	BasePge base;
	Assert asrt;

	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountTestData = new ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj =new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj = new BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj();
	MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj  MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj = new MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	PdcEntryExecution_Obj PdcEntryExecutionObj = new PdcEntryExecution_Obj();
	StatementPosting_Obj StatementPostingObj = new StatementPosting_Obj();
	CheckGPPRefundBenefitWithMultipleCategoryItem_Obj CheckGPPRefundBenefitWithMultipleCategoryItemObj = new CheckGPPRefundBenefitWithMultipleCategoryItem_Obj();
	ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj = new ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRTGSReceivedObj = new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj = new GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj();
	public TenderDeclerationSafeDropIncomeExcessCustSettl(WebDriver driver){
		super(driver);
		base = new BasePge(driver);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
		appUtils = new AppUtilities(base);
	}
	// <summary>
	// Test Case Title : Add tender , income/expense for watches/customer settlement/excess,safe drop
	// Automation ID : TC308
	// Author: Vishnu Manoj K
	// </summary>
	public void TC308_TenderDeclerationSafeDropIncomeExcessCustSettl() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver,60);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		appUtils.HamBurgerButtonClick("iconHome");
		Thread.sleep(10000);
		base.buttonClick(LoginPageObj.Edt_PosReport("POS Reports"));
		Thread.sleep(10000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("HomeView_saveBarCommandCommand","win-commandimage"));
		Thread.sleep(2000);
		String IncomeAndDifferenceAny = PdfUtilities.DownloadAndRenameLatestPDF("IncomeAndDifferenceAny_CashRegisterInvoice");
		String PDfPath = System.getProperty("user.dir") + "\\Invoices\\" + IncomeAndDifferenceAny;
		Map<String, Double> IncomeAndDifferenceAnyValues = pdfUtils.ExtractIncomeAndDifference(PDfPath);

		double Income = IncomeAndDifferenceAnyValues.get("Income");
		double DifferenceIfAny = Math.abs(IncomeAndDifferenceAnyValues.get("Difference"));
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" Income: " + Income);
		System.out.println(" DifferenceIfAny: " + DifferenceIfAny);
		System.out.println("-------------------------------------------------------------------");
		
		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Income Accounts-Watches:
		// Income/expense for watches:
		// Step 1.Go to Cash
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));

		// Step 2.Select Income accounts
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Income accounts")));
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Income accounts"));

		// Step 3.Select Watches
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine","Watches"),NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("win-selectionborder"),5, 2);
		appUtils.ClickWithRetry(LoginPageObj.Btn_SingnIn("selectAccountAppBar"),LoginPageObj.Btn_SignInButton("Accounts"),5, 2);

		// Step 4.Enter amount 
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj.Ele_CustomerAccountInput("numpad-control currency-mode flexCol","Enter amount"),"2000");

		// Step 5.Click on save
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("saveAppBar","win-commandimage"));

		// Step 6.Click on the Amount
		// Step 7.Select a payment method, click on Cash.
		// Expected Result: (1)Check income amount added in "Income" field
		// Expected Result: (2)Check if the added amount gets added to 'Difference if any' Column in cash register
		String WatchesPaymentAmountCash = appUtils.PaymentAfterRecallEstimate("Cash");
		System.out.println("Watches_PaymentAmountCash: " + WatchesPaymentAmountCash);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		
		appUtils.HamBurgerButtonClick("iconHome");
		Thread.sleep(2000);
		//base.waitForClickable(LoginPageObj.Edt_AlertText("POS Reports"), 10);
		base.ClickCondition(LoginPageObj.Edt_PosReport("POS Reports"));
		Thread.sleep(10000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("HomeView_saveBarCommandCommand","win-commandimage"));
		Thread.sleep(10000);
		String WatchesIncomeAndDifferenceAny = PdfUtilities.DownloadAndRenameLatestPDF("WatchesIncomeAndDifferenceAny_CashRegisterInvoice");
		String WatchesPDfPath = System.getProperty("user.dir") + "\\Invoices\\" + WatchesIncomeAndDifferenceAny;
		Map<String, Double> WatchesIncomeAndDifferenceAnyValues = pdfUtils.ExtractIncomeAndDifference(WatchesPDfPath);
		double WatchesIncome = WatchesIncomeAndDifferenceAnyValues.get("Income");
		double WatchesDifferenceIfAny = Math.abs(WatchesIncomeAndDifferenceAnyValues.get("Difference"));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Watches Income: " + WatchesIncome);
		System.out.println("Watches DifferenceIfAny: " + WatchesDifferenceIfAny);
		System.out.println("-------------------------------------------------------------------");

		asrt.assertEquals((WatchesIncome - 2000.00),Income, 1.0,"Income amount mismatch for Watches in Cash Register Pdf. Expected is "+Income+"but got "+(WatchesIncome - 2000.00));
		asrt.assertEquals((WatchesDifferenceIfAny-2000.00),DifferenceIfAny, 1.0,"DifferenceIfAny amount mismatch for Watches in Cash Register Pdf. Expected is "+DifferenceIfAny+" but got "+(WatchesDifferenceIfAny-2000.00));

		// Income Accounts-Excess
		// Income/expense for watches:
		// Step 1.Go to Cash
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));

		// Step 2.Select Income accounts
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Income accounts")));
		base.buttonClick(LoginPageObj.Edt_AlertText("Income accounts"));

		// Step 3.Select Excess
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine","Excess"),NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("win-selectionborder"),5, 2);
		appUtils.ClickWithRetry(LoginPageObj.Btn_SingnIn("selectAccountAppBar"),LoginPageObj.Btn_SignInButton("Accounts"),5, 2);

		// Step 4.Enter amount
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("numpad-control currency-mode flexCol","Enter amount"), "2000");

		// Step 5.Click on save
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("saveAppBar","win-commandimage"));

		// Step 6.Click on the Amount
		// Step 7.Select a payment method, click on Cash.
		// Expected Result: (1)Check income amount added in "Income" field
		// Expected Result: (2) Check if the added amount gets added to 'Difference if any' Column in cash register
		String ExcessPaymentAmountCash = appUtils.PaymentAfterRecallEstimate("Cash");
		System.out.println("Excess_PaymentAmountCash: " + ExcessPaymentAmountCash);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		appUtils.HamBurgerButtonClick("iconHome");
		base.buttonClick(LoginPageObj.Edt_PosReport("POS Reports"));
		Thread.sleep(10000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("HomeView_saveBarCommandCommand","win-commandimage"));
		Thread.sleep(10000);
		String ExcessIncomeAndDifferenceAny = PdfUtilities.DownloadAndRenameLatestPDF("ExcessIncomeAndDifferenceAny_CashRegisterInvoice");
		String ExcessPDfPath = System.getProperty("user.dir") + "\\Invoices\\" + ExcessIncomeAndDifferenceAny;
		Map<String, Double> ExcessIncomeAndDifferenceAnyValues = pdfUtils.ExtractIncomeAndDifference(ExcessPDfPath);

		double ExcessIncome = ExcessIncomeAndDifferenceAnyValues.get("Income");
		double ExcessDifferenceIfAny = Math.abs(ExcessIncomeAndDifferenceAnyValues.get("Difference"));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Excess Income: " + ExcessIncome);
		System.out.println("Excess DifferenceIfAny: " + ExcessDifferenceIfAny);
		System.out.println("-------------------------------------------------------------------");

		asrt.assertEquals(( ExcessIncome- 2000.00),WatchesIncome, 1.0,"Income amount mismatch for Excess in Cash Register. Expected is "+WatchesIncome+"but got "+(ExcessIncome - 2000.00));
		asrt.assertEquals((ExcessDifferenceIfAny-2000.00),WatchesDifferenceIfAny, 1.0,"DifferenceIfAny amount mismatch for Excess in Cash Register. Expected is "+WatchesDifferenceIfAny+" but got "+(ExcessDifferenceIfAny-2000.00));


		// Income Accounts-Customer Settlement
		// 1.Login to POS
		// Income/expense for watches:
		// Step 1.Go to Cash
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));

		// Step 2.Select Income accounts
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Income accounts")));
		base.buttonClick(LoginPageObj.Edt_AlertText("Income accounts"));

		// Step 3.Select Customer Settlement
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine","Customer Settlement"),NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("win-selectionborder"), 5, 2);
		appUtils.ClickWithRetry(LoginPageObj.Btn_SingnIn("selectAccountAppBar"),LoginPageObj.Btn_SignInButton("Accounts"),5, 2);

		// Step 4.Enter amount
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("numpad-control currency-mode flexCol","Enter amount"),"2000");

		// Step 5.Click on save
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("saveAppBar","win-commandimage"));

		// Step 6.Click on the Amount
		// Step 7.Select a payment method, click on Cash.
		// Expected Result: (1)Check income amount added in "Income" field
		// Expected Result: (2) Check if the added amount gets added to 'Difference if any' Column in cash register
		String CustomerSettlementPaymentAmountCash= appUtils.PaymentAfterRecallEstimate("Cash");
		System.out.println("CustomerSettlement_PaymentAmountCash: " + CustomerSettlementPaymentAmountCash);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		appUtils.HamBurgerButtonClick("iconHome");
		base.buttonClick(LoginPageObj.Edt_PosReport("POS Reports"));
		Thread.sleep(10000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("HomeView_saveBarCommandCommand","win-commandimage"));
		Thread.sleep(10000);

		String CustomerSettlementIncomeAndDifferenceAny = PdfUtilities.DownloadAndRenameLatestPDF("CustomerSettlementIncomeAndDifferenceAny_CashRegisterInvoice");
		String CustomerSettlementPDfPath = System.getProperty("user.dir") + "\\Invoices\\" + CustomerSettlementIncomeAndDifferenceAny;
		Map<String, Double> CustomerSettlementIncomeAndDifferenceAnyValues = pdfUtils.ExtractIncomeAndDifference(CustomerSettlementPDfPath);
		double CustomerSettlementIncome = CustomerSettlementIncomeAndDifferenceAnyValues.get("Income");
		double CustomerSettlementDifferenceIfAny = Math.abs(CustomerSettlementIncomeAndDifferenceAnyValues.get("Difference"));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("CustomerSettlement Income: " + CustomerSettlementIncome);
		System.out.println("CustomerSettlement DifferenceIfAny: " + CustomerSettlementDifferenceIfAny);
		System.out.println("-------------------------------------------------------------------");

		asrt.assertEquals(( CustomerSettlementIncome- 2000.00),ExcessIncome, 1.0,"Income amount mismatch for Customer Settlement in Cash Register. Expected is "+ExcessIncome+"but got "+(CustomerSettlementIncome - 2000.00));
		asrt.assertEquals((CustomerSettlementDifferenceIfAny-2000.00),ExcessDifferenceIfAny, 1.0,"DifferenceIfAny amount mismatch for Customer Settlement in Cash Register. Expected is "+ExcessDifferenceIfAny+" but got "+(CustomerSettlementDifferenceIfAny-2000.00));

		// Safe drop:
		// Step 1.Go to Cash
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"),
				NormalSaleGoldAndSilverObj.Btn_Transaction("h4 margin0","Cashier Task"), 5, 2);

		// Step 2.Go to Cashier task
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 margin0","Cashier Task")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 margin0","Cashier Task"));
		
		// Step 3.Click on Safe drop
		base.buttonClick(LoginPageObj.Edt_AlertText("Safe Drop"));
		
		// Step 4.Declare a Safe drop, Enter total amount.
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_AmountDue("Cash total"));
		double SafeDropValue =appUtils.GetAdjustedAmount(CustomerSettlementDifferenceIfAny);
		System.out.println("SafeDropValue: " + SafeDropValue);
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter total amount"),Double.toString(SafeDropValue));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow50", "h1"));
		
		// Step 5.Click on save button
		// Expected Result : (1)Check Safe drop amount added in "Safe drop" field
		// Expected Result : (2) Check the safe drop amount gets deducted from 'difference if any' column in cash register
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("performOperationAppBar","win-commandimage"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		appUtils.HamBurgerButtonClick("iconHome");
		base.buttonClick(LoginPageObj.Edt_PosReport("POS Reports"));
		Thread.sleep(10000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("HomeView_saveBarCommandCommand","win-commandimage"));
		Thread.sleep(10000);

		String SafeDropIncomeAndDifferenceAny = PdfUtilities.DownloadAndRenameLatestPDF("SafeDropIncomeAndDifferenceAny_CashRegisterInvoice");
		String SafeDropPDfPath = System.getProperty("user.dir") + "\\Invoices\\" + SafeDropIncomeAndDifferenceAny;
		Map<String, Double> SafeDropIncomeAndDifferenceAnyValues = pdfUtils.ExtractIncomeAndDifference(SafeDropPDfPath);
		double SafeDropIncome = SafeDropIncomeAndDifferenceAnyValues.get("Income");
		double SafeDropDifferenceIfAny = Math.abs(SafeDropIncomeAndDifferenceAnyValues.get("Difference"));
		double SafeDrop = SafeDropIncomeAndDifferenceAnyValues.get("SafeDrop"); 
		System.out.println("-------------------------------------------------------------------");
		System.out.println("SafeDrop Income: " + SafeDropIncome);
		System.out.println("SafeDrop DifferenceIfAny: " + SafeDropDifferenceIfAny);
		System.out.println("SafeDrop Value: " + SafeDrop);
		System.out.println("-------------------------------------------------------------------");

		asrt.assertEquals(SafeDrop,SafeDropValue,1.0,"Safedrop amount mismatch in Cash Register. Expected is "+SafeDropValue+"but got "+SafeDrop);
		asrt.assertEquals(SafeDropDifferenceIfAny,(CustomerSettlementDifferenceIfAny-SafeDropValue), 1.0,"DifferenceIfAny amount mismatch for Safe drop in Cash Register. Expected is "+(CustomerSettlementDifferenceIfAny-SafeDropValue)+" but got "+SafeDropDifferenceIfAny);

		//Tender:
		// Step 1.Go to Cash
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"),
				NormalSaleGoldAndSilverObj.Btn_Transaction("h4 margin0","Tender Declaration"), 5, 2);

		// Step 2.Go to Cashier task
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 margin0","Cashier Task"));
		
		// Step 3.Click on Tender Declaration
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Tender Declaration")));
		base.buttonClick(LoginPageObj.Edt_AlertText("Tender Declaration"));
		
		// Step 4.Declare a Tender, Enter total amount.
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_AmountDue("Cash total"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter total amount"),Double.toString(SafeDropDifferenceIfAny));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow50", "h1"));
		
		// Step 5.Click on count
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarSelect("Cash INR"));
		
		// Step 6.Enter Quantity for each Denomination
		//Note:Check whether the total matches with the declared tender.
		Map<Integer, Integer> Denomination = appUtils.DenominationUtil((int)SafeDropDifferenceIfAny);

		int Qty500 = Denomination.get(500);
		int Qty200 = Denomination.get(200);
		int Qty100 = Denomination.get(100);
		int Qty50  = Denomination.get(50);
		int Qty20  = Denomination.get(20);
		int Qty10  = Denomination.get(10);
		int Qty5   = Denomination.get(5);
		int Qty2   = Denomination.get(2);
		int Qty1   = Denomination.get(1);
		System.out.println("Denomination Quantities: 500s="+Qty500+", 200s="+Qty200+", 100s="+Qty100+", 50s="+Qty50+",10s="+Qty20+", 10s="+Qty10+", 5s="+Qty5+", 2s="+Qty2+", 1s="+Qty1);

		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 1.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty1));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 2.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty2));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 5.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty5));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 10.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty10));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 20.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty20));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 50.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty50));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 100.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty100));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 200.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty200));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ClickQuantity("Quantity 0 for 500.00 INR","1"));
		base.setDataWithoutClearJs(CheckGPPRefundBenefitWithMultipleCategoryItemObj .Ele_CustomerAccountInput("search-wrapper flex","Enter quantity"),Double.toString(Qty500));
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom")));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickEnter("flexGrow75", "h1 marginBottom"));
		wait.until(ExpectedConditions.elementToBeClickable(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor")));
		base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("iconCancel primaryFontColor"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ClickSave("DenominationsView","iconSave","win-commandimage"));

		// Step 7.Click on Save.
		// Expected Result : Check if 'Difference if any' becomes zero after tender declaration
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("performOperationAppBar","win-commandimage"));
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Tender Declaration")));

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		appUtils.HamBurgerButtonClick("iconHome");
		base.buttonClick(LoginPageObj.Edt_PosReport("POS Reports"));
		Thread.sleep(10000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("HomeView_saveBarCommandCommand","win-commandimage"));
		Thread.sleep(10000);

		String TenderDeclerationIncomeAndDifferenceAny = PdfUtilities.DownloadAndRenameLatestPDF("TenderDeclerationIncomeAndDifferenceAny_CashRegisterInvoice");
		String TenderDeclerationPDfPath = System.getProperty("user.dir") + "\\Invoices\\" + TenderDeclerationIncomeAndDifferenceAny;
		Map<String, Double> TenderDeclerationIncomeAndDifferenceAnyValues = pdfUtils.ExtractIncomeAndDifference(TenderDeclerationPDfPath);
		double TenderDeclerationIncome = TenderDeclerationIncomeAndDifferenceAnyValues.get("Income");
		double TenderDeclerationDifferenceIfAny = Math.abs(TenderDeclerationIncomeAndDifferenceAnyValues.get("Difference"));
		System.out.println("-------------------------------------------------------------------");
		System.out.println("TenderDecleration Income: " + TenderDeclerationIncome);
		System.out.println("TenderDecleration DifferenceIfAny: " + TenderDeclerationDifferenceIfAny);
		System.out.println("-------------------------------------------------------------------");

		asrt.assertEquals(TenderDeclerationDifferenceIfAny,0.00,"Difference if any' not becomes zero after tender declaration in Cash register");
	}

}

