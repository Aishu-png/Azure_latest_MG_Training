package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
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

public class GPPSettlementDiscountPlan extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	GPPSettlementDiscountPlan_TestData GPPSettlementDiscountPlanTestData =new GPPSettlementDiscountPlan_TestData();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();

	public GPPSettlementDiscountPlan(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	// <summary>
	// Test Case Title : Settle a GB scheme with partial benefit
	// Automation ID : TC125
	// Author: Chandana Babu
	// </summary>
	public void TC125_GPPSettlementDiscountPlan() throws Exception {		
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		//*******************************
		//			//Step 2: Click on Transaction button
		//			base.setZoom(driver, 60);
		//			appUtils.HamBurgerButtonClick("iconShop");
		//**********************************

		//Prerequisite Customer must have a matured GB scheme
		Thread.sleep(3000);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				GPPSettlementDiscountPlanTestData.TC125_RequiredSettlement,
				GPPSettlementDiscountPlanTestData.TC125_AccountCount,
				GPPSettlementDiscountPlanTestData.TC125_SchemeCode,
				GPPSettlementDiscountPlanTestData.TC125_Matured,
				GPPSettlementDiscountPlanTestData.TC125_Adjusted);

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
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GPPSettlementDiscountPlanTestData.TC125_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GPPSettlementDiscountPlanTestData.TC125_GrossWeight);
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
		//			//Step 3: Select customer
		//			//Step 4: Click on add to estimation button
		//			appUtils.SearchByCustomerID("9868911081",UtilityTestData.MenuBarOptions[5]);
		//
		//			//Step 5: Click on customer adjustment button
		//			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		//			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		//
		//			//Step 6: Click on GPP Advance Adjustment
		//			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));
		//
		//			//Step 7: Enter scheme id(GG) and click on "Add to cart" button
		//			//Expected :Verify whether the entered GG scheme gets add
		//			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		//			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"33GB000001326");
		//			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"ENTER");
		//			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
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
		//Step 9: Scan SKU and click on add to cart button
		//Expected: Check calculation
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementDiscountPlanTestData.TC125_TransferType,
				GPPSettlementDiscountPlanTestData.TC125_FromCounterGold,
				GPPSettlementDiscountPlanTestData.TC125_MetalTypeGold,
				RequiredWeight,
				GPPSettlementDiscountPlanTestData.TC125_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double ActualRgRateInItemDetailsPge =0.0;
		ProductNames.add(GPPSettlementDiscountPlanTestData.TC125_SchemeName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC125_Weight);
		ProductTotalList.add(GPPAmount);
		int SkuCounter = 1;	
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);

		Thread.sleep(10000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","4"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		System.out.println("ActualBonus: "+ActualBonus);

		double BenefitWeight = SchemeAmount / ActualRgRateInItemDetailsPge;
		double ExpectedBonus = Double.parseDouble(mathUtils.ToGetGppBonus(
				ScannedSkuDataMap,
				SkuList.size(),
				GPPSettlementDiscountPlanTestData.TC125_BenefitPercent,
				BenefitWeight,
				CurrentGoldRate22k,
				GPPSettlementDiscountPlanTestData.TC125_CurrentGoldPurity));
		asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ActualBonus+" but got "+ExpectedBonus+" in billing page");

		ProductNames.add(GPPSettlementDiscountPlanTestData.TC125_BonusName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC125_Weight);
		ProductTotalList.add(Bonus);

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
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ActualItemNameInBillingPge,ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			
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
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTax = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxInBillingPge = Double.parseDouble(ActualTax);
		double ExpectedTaxInBillingPge = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTax = String.format("%.2f",ExpectedTaxInBillingPge);
		asrt.assertEquals(ActualTaxInBillingPge,ExpectedTaxInBillingPge,1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxInBillingPge;
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 11: save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC125_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= ProductTotalList.get(1);
		String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+ActualTaxInBillingPge);
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
		//Step 13: Select estimate and click on proceed button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 14: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementDiscountPlanTestData.TC125_TransactionTypeSales);

		//Step 15: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

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
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");			
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
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in Cashier Screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxFromCashierScreen = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxFromCashierScreen);
		asrt.assertEquals(ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxFromCashierScreen;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 19: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC125TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ProductTotalList.get(1);
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String TotalGrossWeight = ProductQtyList.get(1);
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

		//Step 20: Click on "INSURANCE CERTIFICATE" check box
		//		Thread.sleep(3000);
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));

		//Step 21: Click on print button
		//Expected: Verify INSURANCE CERTIFICATE
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		//		pdfUtils.ValidateInvoiceValue(TotalValue);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	//<summary>
	// Test Case Title : Settle a GB scheme with no benefit
	// Automation ID : TC126
	// Author : Nandagopan
	// </summary>

	public void TC126_GPPSettlementDiscountPlan() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite Customer must have a matured GB scheme
		Thread.sleep(3000);
		//Below Six Installments
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(GPPSettlementDiscountPlan_TestData.TC126_Installment,GPPSettlementDiscountPlan_TestData.TC126_MaxxGppAccounts,GPPSettlementDiscountPlan_TestData.TC126_SchemeCode,GPPSettlementDiscountPlan_TestData.TC126_MaturedScheme,GPPSettlementDiscountPlan_TestData.TC126_AdjustedScheme);

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
			GPPAmount = AccountMap.get("GppAmount");

			//Step 2: Click on Transaction button
			appUtils.HamBurgerButtonClick("iconShop");

			//Step 3: Select customer
			//Step 4: Click on add to estimation button
			appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

			//Step 5: Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			//Step 6: click on GPP advance settlement
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			//Step 7: Enter scheme id(GB with partial benefit) and click on add to cart button
			//Expected:Verify whether the entered GB scheme gets added
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
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");


		//Step 8: Enter OTP
		//Step 9: Scan SKU and click on add to cart button
		//Expected:Check calculation

		List<String> SkuList  = appUtils.SelectMultipleSKUs(
				GPPSettlementDiscountPlanTestData.TC126_SkuCount,
				GPPSettlementDiscountPlanTestData.TC126_TransferType,
				GPPSettlementDiscountPlanTestData.TC126_FromCounter,
				GPPSettlementDiscountPlanTestData.TC126_MetalType
				//RequiredWeight,
				);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> RgWeights = new ArrayList<>();
		List<String> WastageQty = new ArrayList<>();
		List<String> MakingRate = new ArrayList<>();
		List<Double> RgRates = new ArrayList<>();
		ProductNames.add(GPPSettlementDiscountPlanTestData.TC126_SchemeName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC126_Weight);
		ProductTotalList.add(ActualSchemeAmount);

		double ActualRgRateInItemDetailsPge =0.0;
		int SkuCounter = 1;	
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			RgWeights.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			WastageQty.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_WastageQty"));
			MakingRate.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_MakingRate"));
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			//RgRates.add(Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG")));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Step 10: Click on gpp discount button
		//Expected:Verify the GPP Bonus amount[Note:No GPP Discount will get added since it is a not Benefit Scheme]
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		Thread.sleep(3000);

		//FOR HANDLING THE WARNING MESSAGE
		if(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));	
		}

		//Note:Billling Screen
		//*No of Product lines
		//*Displayed Item Name
		//*Displayed Quantity
		//*Displayed TOTAL (without Tax)
		//*Displayed Subtotal
		//*TAX
		//*Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCount, ActualLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ExpectedItemNameInBillingPge, ActualItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			
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

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInBillingPge), Double.parseDouble(ActualSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ExpectedTax, ActualTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");
		appUtils.AddOrVerifyPan();

		//Step 11: save esimate
		//Expected:Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC126_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= ProductTotalList.get(1);
		String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+ActualTax);
		String TotalAdjustment = (ActualSchemeAmount);
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
		//Step 13: Select estimate and click on proceed button
		//Expected:Verify whether the recalled item is the same as the item in the cart.

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"");

		//Step 14: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),GPPSettlementDiscountPlanTestData.TC126_TransactionTypeSales);


		//Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		= TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, ActualLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in Cashier Screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");			
			}
			else {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).trim();
				asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");
			}
			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ExpectedQtyInCashierScreen, ActualQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in Cashier Screen");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInBillingPge), Double.parseDouble(ActualSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in Cashier Screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTaxFromCashierScreen = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ExpectedTaxFromCashierScreen , ActualTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 19: Post the Invoice
		//Expected: Check final invoice details
		//save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC126TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ProductTotalList.get(1);
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue =  Double.toString(
				Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "")) +
				Double.parseDouble(Tax.replaceAll("[^\\d.]", ""))
				);

		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = "0.0";
		String TotalGrossWeight = ProductQtyList.get(1);
		Map<String, String> PdfDetails = pdfUtils.ValidateTaxInvoiceSchemeSettlementPdfGPPDiscount(
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
		String TotalValueInTaxInvoice = PdfDetails.get("TotalValue");

		//		//Step 20: Click on "INSURANCE CERTIFICATE" check box
		//		Thread.sleep(2000);
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));
		//
		//		//Step 21: Click on print button
		//		//Expected:Verify INSURANCE CERTIFICATE
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		//		pdfUtils.ValidateInvoiceValue(TotalValueInTaxInvoice);
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Settle a GB scheme with multiple viraz item items
	// Automation ID : TC122
	// Author : Sangeetha M S
	// </summary>
	//=============Challenge in Bonus Amount calculation of Viraz Sku in Step:11 Variation in bonus calculation 6 to 7 value showing===========================
	public void TC122_GPPSettlementDiscountPlan() throws Exception
	{
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite Customer must have a matured 11 months GB scheme
		Thread.sleep(1500);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				GPPSettlementDiscountPlanTestData.TC122_RequiredSettlement,
				GPPSettlementDiscountPlanTestData.TC122_AccountCount,
				GPPSettlementDiscountPlanTestData.TC122_SchemeCode,
				GPPSettlementDiscountPlanTestData.TC122_Matured,
				GPPSettlementDiscountPlanTestData.TC122_Adjusted);

		//Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double ErpGoldRate18k =appUtils.FetchCurrentBoardRate("18K");

		System.out.println("BoardRate18k       "+ErpGoldRate18k);

		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerAccount="";
		String GPPAccountNumber="";
		String GPPAmount="";
		String SkuPurityReturn="";
		String TotalRgWeightReturn="";
		String SkuGoldRateReturn="";
		boolean ValidAccountFound = false;
		for (int idx = 0;idx<MatchedAccounts.size(); idx++)
			//for (int idx = MatchedAccounts.size() - 1; idx >= 0; idx--) 
		{
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerAccount =AccountMap.get("CustomerAccountNo");
			System.out.println(CustomerAccount);
			GPPAccountNumber =AccountMap.get("GppAccountNo");
			GPPAmount =AccountMap.get("GppAmount");

			//		CustomerAccount ="20CA00474418";
			//		GPPAccountNumber ="20GB000043726" ;
			//		GPPAmount ="11000.00";

			//Step 2. Click on Transaction button
			base.setZoom(driver, 60);
			appUtils.HamBurgerButtonClick("iconShop");

			//Step 3. Select customer
			//Step 4. Click on add to estimation button
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			appUtils.SearchByCustomerID(CustomerAccount, UtilityTestData.MenuBarOptions[5]);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Step 5. In billing, Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			// Step 6. Click on GPP Advance Adjustment
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			// Step 7. Enter scheme id (GB) and click "Add to cart" button
			//Expected  : Verify whether  the entered GB scheme gets added 
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
			System.out.println("Valid GPP account found: " + GPPAccountNumber);
			ValidAccountFound = true;
			break;
		}
		if (!ValidAccountFound) 
		{
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}

		appUtils.AddOrVerifyPan();
		Thread.sleep(1500);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;

		asrt.assertEquals(Double.parseDouble(ActualSchemeAmount), Double.parseDouble(GPPAmount),1.00,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		double SchemeAmount =Double.parseDouble(ActualSchemeAmount);	
		double BenefitWeightFor18k = SchemeAmount/ErpGoldRate18k;
		double ErpGoldRate22k =(ErpGoldRate18k*22)/18;
		double BenefitWeightFor22k = SchemeAmount/ErpGoldRate18k;
		double RequiredWeight =0.0;
		if(BenefitWeightFor18k>BenefitWeightFor22k)
		{
			RequiredWeight= BenefitWeightFor18k;
		}
		if(BenefitWeightFor18k<BenefitWeightFor22k)
		{
			RequiredWeight= BenefitWeightFor22k;
		}

		//Step 8. Enter OTP
		//Step 9. Scan multiple viraz item SKU's
		//Step 10. Choose sales representative
		//Expected: Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double ActualRgRateInItemDetailsPge =0.0;
		ProductNames.add(GPPSettlementDiscountPlanTestData.TC122_SchemeName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC122_Weight);
		ProductTotalList.add(ActualSchemeAmount);
		double VirazMakingRate=0.0;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		List<String> SkuList = appUtils.FetchPieceRateItem(Utility_TestData.UncutVirazSkus, 2);
		for (int i = 0; i < SkuList.size(); i++) 
		{
			int SkuNumber = i + 1;
			String Sku =SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_RateRG"));
			VirazMakingRate=Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_MakingRate"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 11. In billing, click on Customer adjustments then Click on gpp discount button
		//Expected: Verify the GPP Bonus amount (Facing issue in Bonus amount calculation of viraz sku)
		//Note: Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(30000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		double benefitwt = SchemeAmount / ActualRgRateInItemDetailsPge;
		benefitwt = Math.round(benefitwt * 10.0) / 10.0;
		double TotalBonus = VirazMakingRate*benefitwt*0.18;

		asrt.assertEquals(ActualBonus, TotalBonus,1.0,"Mismatch in GPP Bonus Amount,Expected : '"+TotalBonus+"' but got '"+ActualBonus+"'");

		ProductNames.add(GPPSettlementDiscountPlanTestData.TC122_BonusName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC122_Weight);
		ProductTotalList.add(String.valueOf(ActualBonus));

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ExpectedLinesCount, ActualLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		double SubTotalSum = 0.00;
		double TotalWithoutTaxInBillingPge=0.00;
		double GPPTotalWithoutTaxInBillingPge=0.00;
		String EachItemTotalWithoutTaxInBillingPge="";
		String ExpectedTotalWithoutTaxInBillingPge="";
		for(int i =0; i < AllProductRows.size() ;i++) 
		{
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));		
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1||i==2)
			{
				//String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();

				asrt.assertTrue(ProductNames.get(i).trim().contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ProductNames.get(i).trim()+" but got "+ActualItemNameInBillingPge+" in billing page");			

				TotalWithoutTaxInBillingPge+=  Double.parseDouble(TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim());
				ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
				EachItemTotalWithoutTaxInBillingPge=TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();

				asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), Double.parseDouble(EachItemTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+EachItemTotalWithoutTaxInBillingPge+" in billing page");			
			}
			else
			{
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();

				asrt.assertEquals(ExpectedItemNameInBillingPge, ActualItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

				GPPTotalWithoutTaxInBillingPge += Double.parseDouble( TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim());
				ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
				EachItemTotalWithoutTaxInBillingPge=TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();

				asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), Double.parseDouble(EachItemTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+EachItemTotalWithoutTaxInBillingPge+" in billing page");						
			}
			SubTotalSum = TotalWithoutTaxInBillingPge-GPPTotalWithoutTaxInBillingPge;
			System.out.println("SubTotalSum"+SubTotalSum);

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);

			asrt.assertEquals(ExpectedQtyInBillingPge, ActualQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInBillingPge), Double.parseDouble(ActualSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())+Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);

		asrt.assertEquals(ExpectedTax, ActualTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge =Double.parseDouble(ExpectedSubTotalInBillingPge.replaceAll("[^\\d.]", "").trim())
				+ Math.abs(Double.parseDouble(ActualTaxInBillingPge.replaceAll("[^\\d.]", "").trim()));
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim());

		ExpectedTotalAmountInBillingPge =Math.round(ExpectedTotalAmountInBillingPge * 100.0) / 100.0;

		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 12. Click on save to estimate button
		//Step 13. Click on Save estimation 
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(1000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC122_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));//ExpectedTaxInBillingPge
		String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTax);
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

		//Step 14. Recall estimate from cashier screen
		//Step 15. Select estimate and click on Process estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 16. Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementDiscountPlanTestData.TC122_TransactionType);

		//Step 17. Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		= TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, ActualLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in Cashier Screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) 
		{
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i==1 || i==2)
			{
				//String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertTrue(ProductNames.get(i).trim().contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ProductNames.get(i).trim()+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");			
			}
			else 
			{
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).trim();
				asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");
			}
			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ExpectedQtyInCashierScreen, ActualQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in Cashier Screen");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInBillingPge), Double.parseDouble(ActualSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in Cashier Screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTaxFromCashierScreen = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())+Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;		
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ExpectedTaxFromCashierScreen , ActualTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 18. Click on the Amount
		//Step 19. Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 20. Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC122TaxInvoice");
		System.out.println("FinalInvoice     " +FinalInvoice);
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		double GrossAmountSum=Double.parseDouble(ProductTotalList.get(1))+ Double.parseDouble(ProductTotalList.get(2));
		String GrossAmount = Double.toString(GrossAmountSum);
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		double WeightSum=Double.parseDouble(ProductQtyList.get(1))+ Double.parseDouble(ProductQtyList.get(2));
		String TotalGrossWeight =Double.toString(WeightSum);
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
		//base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		//      pdfUtils.ValidateInvoiceValue(TotalValue);

		//		//Step 21. Click on "INSURANCE CERTIFICATE" check box(After Data refresh step not available)
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));
		//
		//		//Step 22. Click on print button
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		//		Thread.sleep(3000);		

		//appUtils.SalesReturnFlow(CustomerAccount,ActualEstmnNumber, GPPSettlementDiscountPlanTestData.TC122_ReturnProducts);
		//appUtils.CounterFlow(GPPSettlementDiscountPlanTestData.TC122_FromCounter,GPPSettlementDiscountPlanTestData.TC122_MetalType,SkuList,UtilityTestData.Terminal);
	}




	//<summary>
	// Test Case Title : Settle a GB scheme with multiple category items(gold,UNC,DMD)
	// Automation ID : TC123
	// Author : Robin T. Abraham
	// </summary>
	public void TC123_GPPSettlementDiscountPlan() throws Exception {		
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		/*//Step 2: Click on Transaction button
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID("33CA00032268",UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

		//Step 6: Click on GPP Advance Adjustment
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

		//Step 7: Enter scheme id(GB) and click on "Add to cart" button
		//Expected :Verify whether the entered GG scheme gets add
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"33GB000008143");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"ENTER");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));*/


		//Prerequisite Customer must have a matured GB scheme
		Thread.sleep(3000);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				GPPSettlementDiscountPlanTestData.TC123_RequiredSettlement,
				GPPSettlementDiscountPlanTestData.TC123_AccountCount,
				GPPSettlementDiscountPlanTestData.TC123_SchemeCode,
				GPPSettlementDiscountPlanTestData.TC123_Matured,
				GPPSettlementDiscountPlanTestData.TC123_Adjusted);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"22k");
		Thread.sleep(10000);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),UtilityTestData.QCAndSmithPerson);
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),UtilityTestData.QCAndSmithPerson);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		Thread.sleep(2000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		Thread.sleep(2000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GPPSettlementDiscountPlanTestData.TC123_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GPPSettlementDiscountPlanTestData.TC123_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double ErpGoldRate22k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
		System.out.println("BoardRate24k "+ErpGoldRate22k);

		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerAccount="";
		String GPPAccountNumber="";
		String GPPAmount="";
		String SkuPurityReturn="";
		String TotalRgWeightReturn="";
		String SkuGoldRateReturn="";
		boolean validAccountFound = false;
		//for (int idx = 0; idx < MatchedAccounts.size(); idx++)
		for (int idx = MatchedAccounts.size() - 1; idx >= 0; idx--) {

			Map<String, String> AccountMap = MatchedAccounts.get(idx);

			CustomerAccount = AccountMap.get("CustomerAccountNo");
			System.out.println(CustomerAccount);
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) GPPMaturedAccountDetails.get("GppAmount");

			// Step 2. Click on Transaction button
			base.setZoom(driver, 60);
			appUtils.HamBurgerButtonClick("iconShop");

			// Step 3 & 4. Select customer & Add to estimation
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			base.waitForClickable(NormalSaleGoldAndSilverObj.Edt_Name("Search"),40);
			appUtils.SearchByCustomerID(CustomerAccount, UtilityTestData.MenuBarOptions[5]);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Step 5. Billing → Customer Adjustment
			base.waitForClickable(LoginPageObj.Edt_AlertText("Billing"),40);			
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			// Step 6. GPP Advance Adjustment
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			// Step 7. Enter scheme id (GB) and click "Add to cart"
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
				continue; 
			}// go to next MatchedAccounts entry
			//  If no "No data", break out of loop and continue workflow
			System.out.println("Valid GPP account found: " + GPPAccountNumber);
			validAccountFound = true;
			break;
		}
		if (!validAccountFound) {
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}
		appUtils.AddOrVerifyPan();
		Thread.sleep(10000);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		//asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		double SchemeAmount =Double.parseDouble(ActualSchemeAmount);		
		double BenefitWeightFor22k = SchemeAmount/ErpGoldRate22k;
		double ErpGoldRate18k =(ErpGoldRate22k*18)/22;
		double BenefitWeightFor18k = SchemeAmount/ErpGoldRate18k;
		double RequiredWeight =0.0;
		if(BenefitWeightFor22k>BenefitWeightFor18k) {
			RequiredWeight= BenefitWeightFor22k;
		}
		if(BenefitWeightFor22k<BenefitWeightFor18k) {
			RequiredWeight= BenefitWeightFor18k;
		}

		//Step 8: Enter OTP
		//Step 9: Scan SKU and click on add to cart button
		//Expected: Check calculation
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementDiscountPlanTestData.TC123_TransferType,
				GPPSettlementDiscountPlanTestData.TC123_FromCounterGold,
				GPPSettlementDiscountPlanTestData.TC123_MetalTypeGold,
				RequiredWeight,
				GPPSettlementDiscountPlanTestData.TC123_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		List<String> SkuUncutList = appUtils.SelectMultipleSKUs(
				GPPSettlementDiscountPlanTestData.TC123_SaleProductCount, 
				GPPSettlementDiscountPlanTestData.TC123_TransferType,
				GPPSettlementDiscountPlanTestData.TC123_UncutFromCounter,
				GPPSettlementDiscountPlanTestData.TC123_UncutMetalType);

		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		SkuList.addAll(SkuUncutList);
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<Double> ActualRgRateInItemDetailsPge = new ArrayList<>();
		double TotalDiamondValue = 0.0;
		ProductNames.add(GPPSettlementDiscountPlanTestData.TC123_SchemeName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC123_Weight);
		ProductTotalList.add(ActualSchemeAmount);
		int SkuCounter = 1;	
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			ActualRgRateInItemDetailsPge.add(Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG")));
			TotalDiamondValue = TotalDiamondValue +Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueDIAMOND"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);

		Thread.sleep(15000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		double ExpectedBonusDmd = 0.0;
		double ExpectedBonusUnct = 0.0;

		Thread.sleep(2000);

		double NewSchemeAmt = SchemeAmount-TotalDiamondValue;

		if (TotalDiamondValue>SchemeAmount) {
			ExpectedBonusDmd = SchemeAmount*0.2;
		}
		else {
			ExpectedBonusDmd = TotalDiamondValue*0.2;
		}

		if(NewSchemeAmt>0) {
			double BenefitWeight= NewSchemeAmt/ActualRgRateInItemDetailsPge.get(1);
			ExpectedBonusUnct = Double.parseDouble(mathUtils.ToGetGppBonusForRequiredProduct(
					ScannedSkuDataMap,
					2,
					18,
					BenefitWeight,
					ErpGoldRate22k,
					GPPSettlementDiscountPlanTestData.TC123_CurrentGoldPurity));
		}
		else {
			ExpectedBonusUnct = 0;
		}

		double ExpectedBonus = ExpectedBonusDmd+ExpectedBonusUnct;

		Thread.sleep(4000);
		asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ExpectedBonus+" but got "+ActualBonus+" in billing page");

		ProductNames.add(GPPSettlementDiscountPlanTestData.TC123_BonusName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC123_Weight);
		ProductTotalList.add(Bonus);

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		//String Discount 			= TransactionDataLine.get("Discount");
		//String TotalGrossWeight 	= TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCount, ActualLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1||i==2) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				//asrt.assertEquals(ExpectedItemNameInBillingPge, ActualItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			
				asrt.assertTrue(ExpectedItemNameInBillingPge.trim().contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");

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

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInBillingPge), Double.parseDouble(ActualSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())+Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ExpectedTax, ActualTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 11: save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC123_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));
		String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTax);
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
		//Step 13: Select estimate and click on proceed button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),"ENTER");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+""); 

		//Step 14: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementDiscountPlanTestData.TC123_TransactionTypeSales);

		//Step 15: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		    = TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		= TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 			    = TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 		= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen	= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, ActualLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in Cashier Screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i==1||i==2) {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				//asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");			
				asrt.assertTrue(ExpectedItemNameInCashierScreen.trim().contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in Cashier Screen");

			}
			else {
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).trim();
				asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in Cashier Screen");
			}
			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ExpectedQtyInCashierScreen, ActualQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in Cashier Screen");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInBillingPge), Double.parseDouble(ActualSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in Cashier Screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTaxFromCashierScreen = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()) + Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim())) *0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ExpectedTaxFromCashierScreen , ActualTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ExpectedTotalAmountInBillingPge, ActualTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 19: Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC123TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim()));
		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String TotalGrossWeight = Double.toString(Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2)));

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


		//Step 20: Click on "INSURANCE CERTIFICATE" check box
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));

		//Step 21: Click on print button
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		//pdfUtils.ValidateInvoiceValue(TotalValue);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	//<summary>
	// Test Case Title : Settle a GB scheme with multiple gold item (No benefit)
	// Automation ID : TC120
	// Author : Bharath
	// </summary>

	public void TC120_GPPSettlementDiscountPlan() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite Customer must have a matured GB scheme
		Thread.sleep(3000);

		//Prerequisite ManualEntry of GPP account

		//				Thread.sleep(15000);
		//				Map<String, Object> ResultMap = new LinkedHashMap<>();
		//				List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
		//				Map<String, String> AccountMap1 = new HashMap<>();
		//				AccountMap1.put("GppAccountNo", "33GB000009023");
		//				AccountMap1.put("CustomerAccountNo","33CA00138897");
		//				AccountMap1.put("GppAmount", "796000");				
		//				MatchedAccounts1.add(AccountMap1);
		//				ResultMap.put("MatchedAccounts", MatchedAccounts1);
		//				ResultMap.put("TotalFound", MatchedAccounts1.size());
		//				
		//				Map<String, Object> GPPMaturedAccountDetails =ResultMap;
		//		
		//Below Six Installments
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				GPPSettlementDiscountPlan_TestData.TC120_Installment,
				GPPSettlementDiscountPlan_TestData.TC120_MaxxGppAccounts,
				GPPSettlementDiscountPlan_TestData.TC120_SchemeCode,
				GPPSettlementDiscountPlan_TestData.TC120_MaturedScheme,
				GPPSettlementDiscountPlan_TestData.TC120_AdjustedScheme);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(6000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"22k");
		if (base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("OK"),12)){
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));		
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		Thread.sleep(5000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GPPSettlementDiscountPlanTestData.TC120_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GPPSettlementDiscountPlanTestData.TC120_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double CurrentGoldRate22k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
		System.out.println("CurrentGoldRate22k "+CurrentGoldRate22k);
		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found"))){
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));		
		}

		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerAccount="";
		String GPPAccountNumber="";
		String GPPAmount="";
		String SkuPurityReturn="";
		String SkuGoldRateReturn="";
		boolean ValidAccountFound = false;
		for (int idx = 0; idx < MatchedAccounts.size(); idx++) {
			Map<String, String> AccountMap = MatchedAccounts.get(idx);

			CustomerAccount = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) AccountMap.get("GppAmount");

			//Step 2: Click on Transaction button
			appUtils.HamBurgerButtonClick("iconShop");

			//Step 3: Select customer
			//Step 4: Click on add to estimation button
			appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

			//Step 5: In billing, Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			//Step 6: Click on GPP Advance Adjustment
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			//Step 7: Enter scheme id(GB with no benefit) and click on add to cart button
			//Expected:Verify whether the entered GB scheme gets added
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
			ValidAccountFound = true;
			break;
		}
		if (!ValidAccountFound) {
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		//Step 8: Enter OTP
		//Step 9: Scan SKU and click on add to cart button
		//Step 10: Choose sales representative
		//Expected:Check calculation
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
		try {
			appUtils.AddOrVerifyPan();
		}catch(Exception e)
		{
			Thread.sleep(5000);
			int Retry =0;
			while(Retry<5)
			{
				if(base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
					base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
					base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"));
					String PhoneNumber = (7) + "" + (System.currentTimeMillis() % 1000000000L);
					base.setData(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"),PhoneNumber);
					base.ClickCondition(LoginPageObj.Btn_SingnIn("cmdSaveCustomerDetails"));
				} else break;
			}
		}


		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementDiscountPlanTestData.TC120_TransferType,
				GPPSettlementDiscountPlanTestData.TC120_FromCounter,
				GPPSettlementDiscountPlanTestData.TC120_MetalType,
				RequiredWeight,
				GPPSettlementDiscountPlanTestData.TC120_SkuCount);

		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> RgWeights = new ArrayList<>();
		List<String> WastageQty = new ArrayList<>();
		List<String> MakingRate = new ArrayList<>();
		List<Double> RgRates = new ArrayList<>();
		ProductNames.add(GPPSettlementDiscountPlanTestData.TC126_SchemeName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC126_Weight);
		ProductTotalList.add(ActualSchemeAmount);
		double ActualRgRateInItemDetailsPge =0.0;
		int SkuCounter = 1;	
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			RgWeights.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			WastageQty.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_WastageQty"));
			MakingRate.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_MakingRate"));
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//Step 11: In billing, go to Customer adjustments then Click on gpp discount button
		//Expected:Verify the GPP Bonus amount[Note:No GPP Discount will get added since it is a not Benefit Scheme]
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		Thread.sleep(3000);

		//FOR Asserting the WARNING MESSAGE
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")), "The warning message: GPP account is not eligible for GPP discount please proceed further is not displayed in the billing page.");		
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));

		//Note:Billing Screen
		//*No of Product lines
		//*Displayed Item Name
		//*Displayed Quantity
		//*Displayed TOTAL (without Tax)
		//*Displayed Subtotal
		//*TAX
		//*Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCount, ActualLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i == 1 ||  i==2) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),
						"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +
						"' should contain actual '" + ActualItemNameInBillingPge + "'");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),
						"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +
						"' should contain actual '" + ActualItemNameInBillingPge + "'");
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

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()) + Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals( ActualTax,ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 12: Click on Save to Estimate button
		//Step 13. Click on Save esimation
		//Expected:Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC120_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))));
		String InvoiceTotal= Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTax);
		String TotalAdjustment = (ActualSchemeAmount);
		String TotalAmt  =""; 
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal,
				TotalAmt);

		//Step 14: Recall estimate from cashier screen
		//Expected:Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"");

		//Step 15: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),GPPSettlementDiscountPlanTestData.TC126_TransactionTypeSales);

		//Step 16: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		= TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 	= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ExpectedLinesCountInCashierScreen, ActualLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in Cashier Screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i == 1 ||  i==2) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInCashierScreen),
						"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +
						"' should contain actual '" + ActualItemNameInCashierScreen + "'");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInCashierScreen),
						"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +
						"' should contain actual '" + ActualItemNameInCashierScreen + "'");
			}
			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals( ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in Cashier Screen");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals( Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals( Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in Cashier Screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTaxFromCashierScreen = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()) + Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTax);
		asrt.assertEquals( ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");
		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate(GPPSettlementDiscountPlanTestData.TC120_PaymentType);

		//Step 18: Post the Invoice
		//Expected: Check final invoice details
		//save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC120TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		double GrossAmountForSale = Double.parseDouble(ProductTotalList.get(1)) + Double.parseDouble(ProductTotalList.get(2));
		String GrossAmount = String.format("%.2f", GrossAmountForSale);
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim()));
		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = "0.0";
		String TotalGrossWeight = String.format("%.3f", (Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2))));
		Map<String, String> PdfDetails = pdfUtils.ValidateTaxInvoiceSchemeSettlementPdfGPPDiscount(
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
		String TotalValueInTaxInvoice = PdfDetails.get("TotalValue");

		//		//Step 19: Click on "INSURANCE CERTIFICATE" check box
		//		Thread.sleep(2000);
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));


		//		//Step 20: Click on print button
		//		//Expected:Verify INSURANCE CERTIFICATE

		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		//		Thread.sleep(3000);
		//		pdfUtils.ValidateInvoiceValue(TotalValueInTaxInvoice);
		//		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		//		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Settle a GB scheme with multiple DMD items
	// Automation ID : TC121
	// Author: Chandana Babu
	// </summary>
	public void TC121_GPPSettlementDiscountPlan() throws Exception {		
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
				GPPSettlementDiscountPlanTestData.TC121_RequiredSettlement,
				GPPSettlementDiscountPlanTestData.TC121_AccountCount,
				GPPSettlementDiscountPlanTestData.TC121_SchemeCode,
				GPPSettlementDiscountPlanTestData.TC121_Matured,
				GPPSettlementDiscountPlanTestData.TC121_Adjusted);

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
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GPPSettlementDiscountPlanTestData.TC121_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GPPSettlementDiscountPlanTestData.TC121_GrossWeight);
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

			//Step 5: In billing, Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			//Step 6: Click on GPP Advance Adjustment
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			//Step 7:  Enter scheme id(GB) and click on "Add to cart" button
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
		//appUtils.SearchByCustomerID("20CA00286119",UtilityTestData.MenuBarOptions[5]);
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
		//base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"),"20GB000042528");
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
		//Step 9: Scan multiple DMD SKU's
		//Expected: Check calculation
		//Step 10: Choose sales representative	
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementDiscountPlanTestData.TC121_TransferType,
				GPPSettlementDiscountPlanTestData.TC121_FromCounterDiamond,
				GPPSettlementDiscountPlanTestData.TC121_MetalTypeGold,
				RequiredWeight,
				GPPSettlementDiscountPlanTestData.TC121_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double TotalDiamondValue = 0.0;
		ProductNames.add(GPPSettlementDiscountPlanTestData.TC121_SchemeName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC121_Weight);
		ProductTotalList.add(GPPAmount);
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

		//Step 11: In billing, click on Customer adjustments then Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(30000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		double ExpectedBonus = 0.0;
		Thread.sleep(2000);
		if (TotalDiamondValue>SchemeAmount) {
			ExpectedBonus = SchemeAmount*0.2;
		}
		else {
			ExpectedBonus = TotalDiamondValue*0.2;
		}
		Thread.sleep(2000);
		asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ExpectedBonus+" but got "+ActualBonus+" in billing page");
		ProductNames.add(GPPSettlementDiscountPlanTestData.TC121_BonusName);
		ProductQtyList.add(GPPSettlementDiscountPlanTestData.TC121_Weight);
		ProductTotalList.add(Bonus);

		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
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
			if (i==1 || i==2) {
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
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTax = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxInBillingPge = Double.parseDouble(ActualTax);
		double ExpectedTaxInBillingPge = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())+Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTax = String.format("%.2f",ExpectedTaxInBillingPge);
		asrt.assertEquals(ActualTaxInBillingPge,ExpectedTaxInBillingPge,1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxInBillingPge;
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 12: Click on Save to Estimate button
		//Expected: Verify the Invoice details
		//Step 13: Click on Save esimation
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC121_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));
		String InvoiceTotal= Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTaxInBillingPge);
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

		//Step 14: Recall estimate from cashier screen
		//Step 15: Select estimate and click on save to estimate button	
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

		//Step 16: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementDiscountPlanTestData.TC121_TransactionTypeSales);

		//Step 17: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		//if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		//}

		//Step 18: Click on the amount
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
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText();
			if (i==1||i==2) {
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
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen);
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in Cashier Screen");

		String ActualTaxInCashierScreen = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxFromCashierScreen = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())+Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTaxFromCashierScreen);
		asrt.assertEquals(ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+ActualTaxFromCashierScreen;
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 19: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC121TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));
		String TotalDiscount=DiscountInCashierScreen;
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String NetValue = PaymentAmount;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String TotalGrossWeight = String.format("%.3f",Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2)));
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

		//Step 20: Click on "INSURANCE CERTIFICATE" check box
		//Thread.sleep(3000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));

		//Step 21: Click on print button
		//Expected: Verify INSURANCE CERTIFICATE
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		//pdfUtils.ValidateInvoiceValue(TotalValue);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
}
