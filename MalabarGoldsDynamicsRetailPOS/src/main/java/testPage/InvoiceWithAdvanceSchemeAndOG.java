package testPage;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceWithAdvanceSchemeAndOG_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.InvoiceWithAdvanceSchemeAndOG_TestData;
import testData.PdcEntryExecution_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities.SKUResult;

public class InvoiceWithAdvanceSchemeAndOG extends BasePge {

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
	PdcEntryExecution_TestData PdcEntryExecutionTestData = new PdcEntryExecution_TestData();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldandSilverObj = new NormalSaleGoldandSilver_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	InvoiceWithAdvanceSchemeAndOG_Obj InvoiceWithAdvanceSchemeAndOGObj = new InvoiceWithAdvanceSchemeAndOG_Obj();
	InvoiceWithAdvanceSchemeAndOG_TestData InvoiceWithAdvanceSchemeAndOGTestData = new InvoiceWithAdvanceSchemeAndOG_TestData();

	public InvoiceWithAdvanceSchemeAndOG(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
	}

	//<summary>
	// Test Case Title : Invoice with advance ,scheme and OG
	// Automation ID : TC290
	// Author : Benson 
	// </summary>
	public void TC290_InvoiceWithAdvanceSchemeAndOG() throws Exception{

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//PreRequisite
		//1.Customer must be having a matured scheme 
		Thread.sleep(3000);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(InvoiceWithAdvanceSchemeAndOGTestData.TC290_InstallmentCount,InvoiceWithAdvanceSchemeAndOGTestData.TC290_HwManyGppAccNeeded,InvoiceWithAdvanceSchemeAndOGTestData.TC290_SchemeCode,UtilityTestData.MaturedGBScheme,UtilityTestData.AdjustMaturedGBScheme);

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

		//Error-handling , if present

		Thread.sleep(5000);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		//		try {
		//			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		//		}catch (Exception e) {
		//			
		//		}

		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),InvoiceWithAdvanceSchemeAndOGTestData.TC290_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),InvoiceWithAdvanceSchemeAndOGTestData.TC290_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double CurrentGoldRate22k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
		System.out.println("CurrentGoldRate22k "+CurrentGoldRate22k);
		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found"))){
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));		
		}

		// Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerNo="";
		String GPPAccountNumber="";
		String GPPAmount="";
		boolean ValidAccountFound = false;
		double Quantity =0.00;
		String AdjustmentAmount="";
		String NormalAdvanceAmount="";
		String MetalRateFetch="";
		for (int idx = 0; idx < MatchedAccounts.size(); idx++) {
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerNo = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) AccountMap.get("GppAmount"); 

			//-------------------------------------------------------------------------------------------				
			//EXTRA CODE To check Customer has pan number or not 
			System.out.println("\nChecking PAN for Customer: " + CustomerNo + " | GPP Account: " + GPPAccountNumber);

			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);    
			try{appUtils.AddOrVerifyPan(); } // Use your existing method

			catch (Exception e) {
				if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("OTP verification"))){
					base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					System.out.println("Otp required for this Customer"+CustomerNo);
					continue; // move to next customer
				}
			}
			appUtils.HamBurgerButtonClick("iconShop");
			appUtils.VoidTransaction();

			// Select customer
			//Click on add to estimation button
			Thread.sleep(3000);
			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);

			//Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

			//Click on GPP advance settlement 
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			// Enter scheme id
			//Click on redeem button
			//Expected: Verify whether the entered GB scheme gets added 
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
			Thread.sleep(2000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

			// Check if "No data" is displayed
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) {
				System.out.println("No data for account: " + GPPAccountNumber+ ". Retrying with next...");
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

		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.VoidTransaction();

		//PreRequisite
		//2.Customer must be having a advance(normal/offer)
		NormalAdvanceAmount = appUtils.CreateNormalAdvance(    
				CustomerNo,
				InvoiceWithAdvanceSchemeAndOGTestData.TC290_NormalAdvanceAmount, 
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//Step 3 : Select customer
		//Step 4 : Click on add to estimation button
		Thread.sleep(3000);
		appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5 : Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6: Click on normal advance settlement
		// Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(6000);
		base.actionClick(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),
				UtilityTestData.Terminal);
		Thread.sleep(1000);
		base.ClickCondition(InvoiceWithAdvanceSchemeAndOGObj.Ele_NormalAdv("h4 ellipsis cell", CurrentGoldRate22k));
		Thread.sleep(4000);
		String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		Thread.sleep(4000);
		AdjustmentAmount = base.GetValue(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);
		MetalRateFetch = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustMetalRate("option", "Metal Rate", "gridcell", 0));
		Quantity = Double.parseDouble(AdjustmentAmount) / Double.parseDouble(MetalRateFetch);

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount	+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		// Step 7: Select advance and click on cart button 
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_return("Add to cart"));

		//Step 8: Enter OTP(disabled)
		//Step 9 :Click on GPP advance settlement 
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

		//Step 11 : Enter scheme id
		//Step 12 : Click on redeem button
		//Expected: Verify whether the entered GB scheme gets added 
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(5000);
		String ActualSchemeAmount = base.GetText(InvoiceWithAdvanceSchemeAndOGObj.Ele_Scheme("tillLayout-TotalWithoutTaxField textRight","h5",2)).replaceAll("[^\\d.]", "").trim(); 
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

		double TotalWeight = Quantity + RequiredWeight; 
		double SkuQuantity = (TotalWeight/2); 

		//Step 13 : Enter OTP (disabled)
		//Step 14 : Scan multiple Gold SKU
		//Expected : Check Calculation 
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithAdvanceSchemeAndOGTestData.TC290_TransferType,
				InvoiceWithAdvanceSchemeAndOGTestData.TC290_FromCounter,
				InvoiceWithAdvanceSchemeAndOGTestData.TC290_MetalType,
				SkuQuantity,
				InvoiceWithAdvanceSchemeAndOGTestData.TC290_SkuCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;

		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double ActualRgRateInItemDetailsPge =0.0;

		ProductNames.add(InvoiceWithAdvanceSchemeAndOGTestData.TC290_DepositAdjustment);  
		ProductQtyList.add(InvoiceWithAdvanceSchemeAndOGTestData.TC290_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);

		ProductNames.add(InvoiceWithAdvanceSchemeAndOGTestData.TC290_SchemeName);
		ProductQtyList.add(InvoiceWithAdvanceSchemeAndOGTestData.TC290_Weight);
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

		//Step 15: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(14000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","6"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		System.out.println("ActualBonus: "+ActualBonus);
		double BenefitWeight = SchemeAmount / ActualRgRateInItemDetailsPge; 
		double ExpectedBonus = Double.parseDouble(mathUtils.ToGetGppBonus(
				ScannedSkuDataMap,
				SkuList.size(),
				InvoiceWithAdvanceSchemeAndOGTestData.TC290_BenefitPercent,
				BenefitWeight,
				CurrentGoldRate22k,
				InvoiceWithAdvanceSchemeAndOGTestData.TC290_CurrentGoldPurity));

		asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ActualBonus+" but got "+ExpectedBonus+" in billing page");

		ProductNames.add(InvoiceWithAdvanceSchemeAndOGTestData.TC290_GPPBonus);  
		ProductQtyList.add(InvoiceWithAdvanceSchemeAndOGTestData.TC290_GPPBonusQty);
		ProductTotalList.add(Double.toString(ExpectedBonus));

		//Step 16: Select OG
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(1000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Step 17: Click on OG own 
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));

		//Step 18:Select configuration
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),InvoiceWithAdvanceSchemeAndOGTestData.TC290_Config);

		//Step 19: Click on add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Error-handling , if present
		try {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}catch (Exception e) {

		}
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//Step 20:Select QC Person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Step 21: Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Step 22: Enter piece value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),InvoiceWithAdvanceSchemeAndOGTestData.TC290_GrossPieces);

		//Step 23: Enter gross weight
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),InvoiceWithAdvanceSchemeAndOGTestData.TC290_GrossWeight);

		//Step 24: Click on Calculate button
		//Expected : Check Check Old Gold Calculation
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(2000);
		double ActualOGPurchaseRateInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		double ExpectedOGPurchaseRateInItemDetailsPge = CurrentGoldRate22k ;

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

		ProductNames.add(ExpectedItemNameInItemDetailsPge);
		ProductQtyList.add(Double.toString(ExpectedGrossWeightInItemDetailsPge));
		ProductTotalList.add(Double.toString(ActualTotalAmountInItemDetailsPge));

		//Step 25: Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Choose sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Validation for Billing Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		//Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String NetTotal = TransactionDataLine.get("NetTotal");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		// Step 26: Save Estimate
		// Expected : Verify the invoice details
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));		 
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(4000);
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// PDF Verification Step
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC290_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		double FirstSkuAmount = Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.-]", ""));
		double SecondSkuAmount = Double.parseDouble(ProductTotalList.get(3).replaceAll("[^\\d.-]", ""));
		double TaxableValueSum = FirstSkuAmount + SecondSkuAmount;
		String TaxableValue = String.format("%.2f", TaxableValueSum);
		double InvoiceTotalValue = (
				TaxableValueSum 
				+ Double.parseDouble(Tax.replaceAll("[^\\d.-]", "")) 
				- CurrentGoldRate22k
				);
		String InvoiceTotal = String.format("%.2f", InvoiceTotalValue);
		double TotalAmntValue=TaxableValueSum-CurrentGoldRate22k;
		String TotalAmnt = String.format("%.2f", TotalAmntValue);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,   
				TotalAmnt   
				);

		//Step 27: Recall estimate from cashier screen
		//Step 28: Select estimate and click on Recall estimation button
		//Expected : Verify whether the recalled item is the same as the item in the cart
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

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,
				"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"
						+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPageBeforeExchange = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPageBeforeExchange), "Recalled item is not same as the item in the Cart");
		
		//Step 29: Select transaction type as Exchange
				base.buttonClick(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithAdvanceSchemeAndOGTestData.TC290_TransactionType1);

		//Step 30: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 31: Choose any Sales Representative(disabled)
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		//base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")); ADD IF LOOP
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 32:Click on the Amount 0.00
		//Step 33:Post exchange invoice
		// Expected Result: Check Exchange invoice details
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Amnt("h1"));
		if (base.isElementPresent(driver, NormalSaleGoldandSilverObj.Btn_Estimation("cancelButton primaryButton"))) {
			try {
				base.excuteJsClick(NormalSaleGoldandSilverObj.Btn_Estimation("cancelButton primaryButton"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			} catch (Exception e) {
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		double GrossWeight =(ExpectedGrossWeightInItemDetailsPge);
		String ExpectedGrossWeightFromBillingPge = String.format("%.3f", GrossWeight);
		String ExpectedNetWeightFromBillingPge = String.format("%.3f", ExpectedNetWeightInItemDetailsPge);
		String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",CurrentGoldRate22k);
		double TotalAmountForOG = ActualTotalAmountInItemDetailsPge;
		String ExpectedTotalAmountForOG = String.format("%.2f", TotalAmountForOG); 

		Map<String, String> OgPurchaseBill = pdfUtils.OGPdfValidation("Adjustment");  //Exchange Invoice validation
		String GrossWeightFromInvoice = OgPurchaseBill.get("GrossWt1");
		String NetWeightFromInvoice = OgPurchaseBill.get("NetWt1");
		String GoldRateFromInvoice = OgPurchaseBill.get("Rate1");
		String TotalAmountFromInvoice = OgPurchaseBill.get("TotalAmount1");
		String PaymentDetailsFromInvoice = OgPurchaseBill.get("PaymentDetails");
		asrt.assertEquals(ExpectedGrossWeightFromBillingPge, GrossWeightFromInvoice,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+GrossWeightFromInvoice+" in purchase bill");		
		asrt.assertEquals(ExpectedNetWeightFromBillingPge, NetWeightFromInvoice,"Net Weight mismatch: "+ExpectedNetWeightFromBillingPge+" but got "+NetWeightFromInvoice+" in purchase bill");	
		asrt.assertEquals(ExpectedOGPurchaseRateFromItemDetailsPge, GoldRateFromInvoice,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in purchase bill");	
		asrt.assertEquals(ExpectedTotalAmountForOG, TotalAmountFromInvoice,"Total amount mismatch: "+ExpectedTotalAmountForOG+" but got "+TotalAmountFromInvoice+" in purchase bill");	
		asrt.assertEquals(ExpectedTotalAmountForOG, PaymentDetailsFromInvoice,"Payment details mismatch: "+ExpectedTotalAmountForOG+" but got "+PaymentDetailsFromInvoice+" in purchase bill");


		//Step 34: Again recall estimate from cashier screen
		//Step 35: Select estimate and click on Recall estimation button
		//Expected : Verify whether the recalled item is the same as the item in the cart
		base.excuteJsClick(
				NormalSaleGoldandSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		ExpectedEstmnNumber = base.GetValue(NormalSaleGoldandSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");
		List<String> ItemNamesFromRecallPageBeforeSale = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPageBeforeSale), "Recalled item is not same as the item in the Cart");
		
		//Step 36: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithAdvanceSchemeAndOGTestData.TC290_TransactionType2);

		//Step 37: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 38: Choose a Sales Representative(disabled)
		//Expected: Cashier Screen after recall Estimate*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		base.setZoom(driver, 60);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		//base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		appUtils.HandleSpecifyRateModalInRecall();

		String ProductsDiscount = base.GetText(NormalSaleGoldandSilverObj.Ele_Lines("DiscountsField", "h4"));
		String PaymentAmountHDFC = base.GetText(NormalSaleGoldandSilverObj.Sel_AmountDue("Amount due"));
		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
		String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");

		ProductNames.remove(ProductNames.size()-1);
		ProductQtyList.remove(ProductQtyList.size()-1);
		ProductTotalList.remove(ProductTotalList.size()-1);

		Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashier, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		// Step 39: Click on amount
		// Step 40: Select payment method(cash/card)
		appUtils.PaymentAfterRecallEstimate(InvoiceWithAdvanceSchemeAndOGTestData.TC290_PaymentMethod);

		//Step 41: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC290TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TaxableValue;
		String TotalDiscount=ProductsDiscount;   
		String TotalQtyPcs= Integer.toString(SkuList.size());
		double HdfcValue = Double.parseDouble(PaymentAmountHDFC.replaceAll("[^\\d.-]", "").trim());
		// Round off to nearest whole number
		long roundedValue = Math.round(HdfcValue);
		// Format to show .00 like the PDF
		String PaymentHDFCCard = String.format("%.2f", (double) roundedValue);
		String TotalValue = String.format("%.2f", Double.parseDouble(TaxableValue) + Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim()));
		String NetValue = SkuNetTotal;
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String Adjustment = String.format("%.2f", CurrentGoldRate22k);  //AdjustmentAmount
		String AdvanceAmount=NormalAdvanceAmount;
		String AdjustmentTotal = String.format("%.2f",
				Double.parseDouble(AdvanceAmount.replace(",", "")) +
				Double.parseDouble(GppAdvance.replace(",", "")) +
				Double.parseDouble(GppDiscount.replace(",", ""))
				);
		TotalGrossWeight=TotalNetWeight;
		Map<String, String> PdfDetails = pdfUtils.SaleInvoiceValidationAdvanceSchemeAndOG(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,  
				TaxableValue,
				TotalValue,
				PaymentHDFCCard,
				Adjustment,
				AdvanceAmount,
				GppAdvance,
				GppDiscount,
				AdjustmentTotal,
				NetValue);

		Thread.sleep(3000);
		//Get and print the Invoice Number
		String invoiceNumber = PdfDetails.get("InvoiceNumber");
		System.out.println("Invoice Number from PDF: " + invoiceNumber);

		// Close the invoice window
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));

		// Cleanup PDF files
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	//<summary>
	// Test Case Title : Invoice with scheme and OG
	// Automation ID : TC289
	// Author :Christy Reji
	// </summary>
	public void TC289_InvoiceWithAdvanceSchemeAndOG() throws Exception{

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//PreRequisite
		//1.Customer must be having a matured scheme 
		Thread.sleep(3000);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_InstallmentCount,
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_HwManyGppAccNeeded,
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_SchemeCode,
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_MaturedGGScheme,
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_AdjustMaturedGGScheme);

		Thread.sleep(15000);
		//		Map<String, Object> ResultMap = new LinkedHashMap<>();
		//		List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
		//		Map<String, String> AccountMap1 = new HashMap<>();
		//		AccountMap1.put("GppAccountNo", "20GG000040529");
		//		AccountMap1.put("CustomerAccountNo", "20CA00433782");
		//		MatchedAccounts1.add(AccountMap1);
		//		ResultMap.put("MatchedAccounts", MatchedAccounts1);
		//		ResultMap.put("TotalFound", MatchedAccounts1.size());
		//		Map<String, Object> GPPMaturedAccountDetails =ResultMap;

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double ErpGoldRate22k = appUtils.FetchCurrentBoardRate(InvoiceWithAdvanceSchemeAndOGTestData.TC289_Purity);
		double ErpGoldRate24k = ErpGoldRate22k;//(ErpGoldRate22k*24)/22;

		System.out.println("BoardRate24k "+ErpGoldRate24k);
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

			CustomerAccount   = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber  = AccountMap.get("GppAccountNo");
			GPPAmount         = (String) GPPMaturedAccountDetails.get("GppAmount");

			// Step 2. Click on Transaction button
			base.setZoom(driver, 60);

			// Step 3 & 4. Select customer & Add to estimation
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			appUtils.SearchByCustomerID(CustomerAccount, UtilityTestData.MenuBarOptions[5]);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Step 5. Click on customer adjustment button
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

			// Step 6. GPP Advance Adjustment
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			// Step 7. Enter scheme id (GG) and click "Add to cart"
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

		//Step 8.Click on redeem button
		//Step 9.Enter the OTP
		//Step 10.Scan multiple category(Gold)
		//Expected :Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(InvoiceWithAdvanceSchemeAndOGTestData.TC289_SchemeName);
		ProductQtyList.add(InvoiceWithAdvanceSchemeAndOGTestData.TC289_SchemeWeight);
		Thread.sleep(5000);
		String ActualSchemeAmount     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		ProductTotalList.add(ActualSchemeAmount);
		double SchemeAmountNow        = Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate         = Double.parseDouble(SkuGoldRateReturn);
		TotalRgWeightReturn           = String.valueOf(SchemeAmountNow/SchemeGoldRate);
		System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
		System.out.println("ActualSchemeAmount"+ActualSchemeAmount);
		int SkuCounter = 1;
		// Gold SKUs
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_TransferType,
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_FromCounterGold,
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_MetalTypeGold,
				(Double.parseDouble(TotalRgWeightReturn)/2)+2,
				InvoiceWithAdvanceSchemeAndOGTestData.TC289_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
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

		//SKU Validation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
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

		//Check Calculation
		List<WebElement> BillingPgeTotalwithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		System.out.println("BillingPgeTotalwithoutTax: " + BillingPgeTotalwithoutTax.size());
		double val1 = Math.abs(Double.parseDouble(BillingPgeTotalwithoutTax.get(0).getText().replaceAll("[^\\d.-]", "").trim()));
		double val2 = Math.abs(Double.parseDouble(BillingPgeTotalwithoutTax.get(1).getText().replaceAll("[^\\d.-]", "").trim()));
		double val3 = Math.abs(Double.parseDouble(BillingPgeTotalwithoutTax.get(2).getText().replaceAll("[^\\d.-]", "").trim()));

		// Perform calculation
		double CalcTaxable =val2 + val3;
		double ExpSubTotal = (val2 + val3) - val1;
		double ExpTotalAmt = ExpSubTotal + Double.parseDouble(Tax);

		asrt.assertEquals(Double.parseDouble(SubTotal), ExpSubTotal,1,"Subtotal mismatch: "+ExpSubTotal+" but found "+SubTotal+" in billing page");
		asrt.assertEquals(Double.parseDouble(TotalAmount), ExpTotalAmt,1,"Total amount mismatch: "+ExpTotalAmt+" but found "+TotalAmount+" in billing page");

		//Step 11: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(10000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());

		//BenfitPercentage
		String TotalBonus  = mathUtils.ToGetGppBonus(ScannedSkuDataMap,SkuList.size(),InvoiceWithAdvanceSchemeAndOGTestData.TC289_BenefitPercentage,Double.parseDouble(TotalRgWeightReturn),ErpGoldRate24k);
		System.out.println(TotalBonus);
		asrt.assertEquals(ActualBonus, Double.parseDouble(TotalBonus),1,"Bonus mismatch: "+Double.parseDouble(TotalBonus)+" but found "+ActualBonus+" in billing page");

		ProductNames.add(InvoiceWithAdvanceSchemeAndOGTestData.TC289_BonusName);
		ProductQtyList.add(InvoiceWithAdvanceSchemeAndOGTestData.TC289_BonusQty);
		String ActualBonusString = String.valueOf(ActualBonus);
		ProductTotalList.add(ActualBonusString);

		System.out.println(ProductQtyList);
		System.out.println(ProductNames);
		System.out.println(	ProductTotalList);

		//Step 12.Select OG
		//Step 13.Click on OG own
		//Choose Exchange
		//Step 14.Select configuration
		//Step 15.Click on Add item button
		//Step 16.Select QC person
		//Step 17.Select Smith person
		//Step 18.Enter piece value
		//Step 19.Enter gross weight
		//Step 20.Click on calculate button
		//Step 21.Click on Add to cart button
		//Expected :Check Old Gold Calculation
		appUtils.FetchCurrentBoardRate(InvoiceWithAdvanceSchemeAndOGTestData.TC289_Purity);
		double ActualOgtRateInItemDetailsPge          = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		String ExpectedGrossWeightInItemDetailsPge    = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String OgTotalAmountInItemDetailsPge          = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));	
		String OgItemName                             = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ExpectedItemNameInOgtItemDetailsPge    = OgItemName.substring(OgItemName.indexOf("-") + 2);

		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(3000);

		//Note:Billling Screen
		// *No of Product lines *Displayed Item Name * Displayed Quantity *Displayed TOTAL (without Tax)
		// *TAX*Total Amount *Displayed Subtotal
		List<WebElement> ItemNames  = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-ProductNameField wrapText"));
		List<WebElement> ItemQtys   = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-QuantityField textRight"));
		List<WebElement> ItemTotals = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		double ExpectedSubTotal     = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", ""));
		double ExpectedTax          = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", ""));
		double ActualTotal          = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", ""));

		//calculation OG Billing page
		double CalculatedSubTotal   = Double.parseDouble(SubTotal)-(Double.parseDouble(OgTotalAmountInItemDetailsPge)+ActualBonus);
		CalculatedSubTotal          = Math.abs(CalculatedSubTotal);
		double CalculatedTotal      = ExpectedSubTotal + ExpectedTax;
		asrt.assertEquals(CalculatedSubTotal, ExpectedSubTotal, 1, "Subtotal mismatch from Itemdetails page Expected: "+ExpectedSubTotal+" but found : "+CalculatedSubTotal+" in billing page");
		asrt.assertEquals(Double.parseDouble(Tax), ExpectedTax, 1, "The tax value mismatch from  Itemdetails page Expected: "+ExpectedTax+ "but found :" +Tax+" in billing page");
		asrt.assertEquals(ActualTotal, CalculatedTotal, 1, "The total amount mismatch from  Itemdetails page Expected: "+ActualTotal+ "but found :" +CalculatedTotal+" in billing page");

		//Step  25. Save to estimate
		//Expected : Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(3000);
		if(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"))) {
			asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "The validation message -PAN number is required this customer-is displayed in the warning message");	
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Close"));

			//Updating Pan card to the customer
			Thread.sleep(3000);
			//appUtils.AddOrVerifyPan();
			
			try{appUtils.AddOrVerifyPan(); } // Use your existing method
			catch (Exception e) {
				if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("OTP verification")) ||
						base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"))){
					base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					System.out.println("Otp required for this Customer");
				}
			}
			
			base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		}else {System.out.println("Customer already have PAN card.:Continuing execution");}

		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		double ExpTotalAmtPerforma     = CalcTaxable-Double.parseDouble(OgTotalAmountInItemDetailsPge);
		double ExpInvoiceTotalPerforma = ExpTotalAmtPerforma+Double.parseDouble(Tax);
		String TaxableValue            = Double.toString(CalcTaxable);
		String TotalAmnt               = Double.toString(ExpTotalAmtPerforma);
		String Gst                     =  Tax;
		String InvoiceTotal            =  Double.toString(ExpInvoiceTotalPerforma);
		String NetTotalPerforma        =  Double.toString(ActualTotal);
		Thread.sleep(3000);
		String ProformaPdfName         = pdfUtils.DownloadAndRenameLatestPDF("TC289_ProformaInvoice");
		String ProFormaInvoicePath     = System.getProperty("user.dir") + "\\Invoices\\"+ProformaPdfName+"";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotalPerforma,
				TotalAmnt
				);

		//Step 23:Recall estimate from cashier screen
		//Step 24:Select estimate and click on recall estimate button
		//Expected:Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in Recall Estimation page"); 
		System.out.println("EstmnNumber"+EstmnNumber);

		// Validation
		ProductNames.add(OgItemName);
		ProductQtyList.add(ExpectedGrossWeightInItemDetailsPge);
		appUtils.RecallEstimateItemValidation(
				ProductNames,
				ProductQtyList,
				Arrays.asList(OgItemName),
				Arrays.asList(ExpectedGrossWeightInItemDetailsPge),
				ExpectedSubTotal,
				Double.parseDouble(Tax),
				ActualTotal,
				ItemNames.size(),
				"OG, Bonus, Skus "
				);

		//Step 25: Select transaction type as Exchange
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithAdvanceSchemeAndOGTestData.TC289_TransactionTypeExchange);

		//Step 26.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 27.Choose a sales representative[Step not required due new application change]
		//Step 28:  Click on amount 0.00
		//Cashier Screen after recall Estimate
		// *No of Product lines
		// *Displayed Item Name
		// *Displayed Quantity
		// *Displayed TOTAL (without Tax)
		// *Displayed Subtotal
		//*TAX
		// *Total Amount
		Thread.sleep(6000);
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
		String ExpectedItemNameInCashierScreen         = OgItemName.contains("-")? OgItemName.substring(OgItemName.lastIndexOf("-") + 1).trim(): OgItemName.trim();
		asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");			

		List<WebElement> QuantityInCashierScreen       = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
		String ActualQuantityInCashierScreen           = QuantityInCashierScreen.get(0).getText().replace("-", "");
		double ActualQtyInCashierScreen                = Double.parseDouble(ActualQuantityInCashierScreen);
		double ExpectedQtyInCashierScreen              = Double.parseDouble(ExpectedGrossWeightInItemDetailsPge);
		asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in CashierScreen");

		List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		String ActualTotalWithoutTaxInCashierScreen     =TotalWithoutTaxInCashierScreen.get(0).getText().replace("-", "").replaceAll("[^\\d.]", "").trim();                  
		double SubTotalSumInCashierScreen               = Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
		String ExpectedTotalWithoutTaxInCashierScreen   = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "").trim()));
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(OgTotalAmountInItemDetailsPge),1.0,"TotalWithoutTax mismatch: Expected is "+OgTotalAmountInItemDetailsPge+" but got "+ActualTotalWithoutTaxInCashierScreen+" in CashierScreen");

		String ExpectedSubTotalInCashierScreen          = String.format("%.2f",SubTotalSumInCashierScreen).replace("-", "").replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen            = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("-", "").replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(OgTotalAmountInItemDetailsPge),1.0,"Subtotal mismatch: "+OgTotalAmountInItemDetailsPge+" but found "+ActualSubTotalInCashierScreen+" in CashierScreen");

		double ActualTotalAmountInCashierScreen         = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("-", "").replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,Double.parseDouble(OgTotalAmountInItemDetailsPge),1.0,"Total Amount mismatch: "+OgTotalAmountInItemDetailsPge+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");

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
		String TotalAmountFromInvoice    = PurchaseBill.get("TotalAmount");
		String ExchangeFromInvoice       = PurchaseBill.get("PaymentDetails");
		String QtyFromInvoice            = PurchaseBill.get("QtyPcs");

		asrt.assertEquals(Double.parseDouble(ExchangeFromInvoice), Double.parseDouble(OgTotalAmountInItemDetailsPge), 0.01, "Mismatch in exchange value URD Purchase bill adjustment:"+ExchangeFromInvoice+"but got" +OgTotalAmountInItemDetailsPge+"rom billing page");
		asrt.assertEquals(Double.parseDouble(GrossWeightFromInvoice), Double.parseDouble(ExpectedGrossWeightInItemDetailsPge),"Mismatch in GrossWt value URD Purchase bill adjustment:"+GrossWeightFromInvoice+"but got" +ExpectedGrossWeightInItemDetailsPge+"rom billing page");
		asrt.assertEquals(Double.parseDouble(NetWeightFromInvoice), Double.parseDouble(ExpectedGrossWeightInItemDetailsPge),"Mismatch in NetWt value URD Purchase bill adjustment:"+NetWeightFromInvoice+"but got" +ExpectedGrossWeightInItemDetailsPge+"rom billing page");	
		asrt.assertEquals(Double.parseDouble(TotalAmountFromInvoice), Double.parseDouble(OgTotalAmountInItemDetailsPge),0.01,"Mismatch in TotatAmt value URD Purchase bill adjustment:"+Double.parseDouble(TotalAmountFromInvoice)+"but got" +OgTotalAmountInItemDetailsPge+"rom billing page");
		asrt.assertEquals(Integer.parseInt(QtyFromInvoice),1,"Mismatch in Line count URD Purchase bill adjustment: " + 1 +" but got " + QtyFromInvoice + " from billing page");

		//Repeating step 23-29 Again recall estimate from cashier screen
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

		//Select transaction type as sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithAdvanceSchemeAndOGTestData.TC289_TransactionTypesale);

		//Click on process estimation button[sale rep not required]
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Click on the Amount
		// Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		//Step 30 : Post the sale invoice
		//Check the final ivoice details-Sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC289_FinalSaleInvoice");
		Thread.sleep(3000);		
		String SaleInvoicePath     =System.getProperty("user.dir") + "\\Invoices\\"+FinalInvoice+"";
		Thread.sleep(3000);
		String GrossAmount         = String.valueOf(CalcTaxable);;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = String.valueOf(SkuList.size());
		String Adjustment          = OgTotalAmountInItemDetailsPge;
		String PaymentHDFCCard     = PaymentAmount;
		String SalesPdfPath        = SaleInvoicePath;
		String TotalNetWeightPdf   = TotalNetWeight;
		String TotalGrossWeightPdf = TotalNetWeightPdf;
		double CalTotalValue= ( Double.parseDouble(TaxableValue)+Double.parseDouble(Tax));
		double CalcTotalAmount     = ( Double.parseDouble(TaxableValue)+Double.parseDouble(Tax))-(Double.parseDouble(ActualSchemeAmount)+Double.parseDouble(ProductTotalList.get(3)));
		String NetValue            = String.format("%.2f", CalcTotalAmount);
		NetValue                   = NetValue.replaceAll("^0+(?!$)", "").trim();
		double calculatedAdjustment=Double.parseDouble(ActualSchemeAmount)+ ActualBonus;
		
		Thread.sleep(3000);
		Map<String, String> PdfDetails = pdfUtils.ValidateTaxInvoiceSchemeSettlementPdf(
				SaleInvoicePath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				Double.toString(CalTotalValue),
				PaymentHDFCCard,
				ActualSchemeAmount,
				Double.toString(ActualBonus),
				Double.toString(calculatedAdjustment),
				NetValue);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
}
	
}


