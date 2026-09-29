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
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.ReturnDetails;
import utilPack.AppUtilities.SKUResult;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.ErpUtilities.GPPAccountDetails;
import utilPack.ErpUtilities.GPPAccountDiscountDetails;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

//import basePackage.BasePge;

public class GPPSettlementAverageRateSingleSkuMultipleSku extends BasePge {

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
	GPPSettlementAverageRateSingleSkuMultipleSku_TestData GPPSettlementAverageRateSingleSkuMultipleSkuTestData = new GPPSettlementAverageRateSingleSkuMultipleSku_TestData();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();

	public GPPSettlementAverageRateSingleSkuMultipleSku(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	/// <summary>
	/// Test Case Title : Mature a GG scheme
	//==========Challenge (scroll of gpp account opening table) to find customer from ERP ========
	/// Automation ID : TC110
	/// Author : Jhoncy
	/// </summary>
	public void TC110_GPPSettlementAverageRateSingleSkuMultipleSku() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite Customer must be having a ongoing GG scheme
		GPPAccountDetails Details = erpUtils.ToGetOnGoingGGScheme();
		String CustomerAccount = Details.CustomerAccount;
		String GPPAccountNumber = Details.GppAccount;

		//Step 2:Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3:Click on GPP button
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP"));

		//Step 4:Click on GPP Maturity button
		Thread.sleep(3000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("GPP Maturity"));
		Thread.sleep(7000);

		//Step 5: Select customer 
		//Step 6: Click on add to GPP Maturity button
		appUtils.SearchByCustomerIDForGPP(CustomerAccount, UtilityTestData.GPPMaturityButton);

		//Step 7:Select GPP account
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("Select gpp account..."));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("Select gpp account..."),GPPAccountNumber);

		//Step 8:Click on Mature button
		//Expected Result:Verify validation *Successfuly matured scheme*ERP- verify matured column enabled
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("SearchView_AppBarView_appBarCommandCommand"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton","YES"));
		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Matured successfully. account no. - "+GPPAccountNumber)),"Validation saying 'Matured successfully.' is not displayed ");
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton","OK"));

		Thread.sleep(5000);
		erpUtils.ToGetMaturedGGScheme(GPPAccountNumber);	
	}

	/// <summary>
	/// Test Case Title : Check the discount details shown in the maturity screen
	/// Automation ID : TC111
	/// Author : Neethu
	/// </summary>

	public void TC111_GPPSettlementAverageRateSingleSkuMultipleSku() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite Customer must be having a ongoing GG scheme
		GPPAccountDiscountDetails Details = erpUtils.ToGetDiscountOnGoingGGScheme();
		String CustomerAccount = Details.CustomerAccount;
		String GPPAccountNumber = Details.GppAccount;

		//Step 2:Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3:Click on GPP button
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP"));
		Thread.sleep(2000);

		//Step 4:Click on GPP Maturity button
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Maturity"));
		Thread.sleep(7000);

		//Step 5: Select customer 
		//Step 6: Click on add to GPP Maturity button
		appUtils.SearchByCustomerIDForGPP(CustomerAccount, UtilityTestData.GPPMaturityButton);

		//Step 7:Select GPP account
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("Select gpp account..."));
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("Select gpp account..."),GPPAccountNumber);

		//Step 8: Click on discount details button
		//Expected Result:Verify discount details table
		Thread.sleep(5000);
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("SearchView_AppBarView_appBarCommandDiscountCommand"));
		Thread.sleep(6000);

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		if (base.isElementPresent(driver, NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Error"))) {		
			try {
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Ele_ErrorMessage("buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20")));
				OkButton.click();
				base.buttonClick(LoginPageObj.Btn_SingnIn("SearchView_AppBarView_appBarCommandDiscountCommand"));

			} 
			catch (TimeoutException e) {
			} 
		}


		String[] Option = {GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC111_GLDDiscountDetails,GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC111_PRCDiscountDetails,GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC111_UNCDiscountDetails,GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC111_GBCDiscountDetails};
		for(String DiscountDetails : Option)
		{
			asrt.assertTrue(base.isExists(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount(DiscountDetails,"win-item")), "Mismatch in discount details " + DiscountDetails + "in GPP maturity page.");
		}

	}


	/// <summary>
	/// Test Case Title : Settle a GG partial benefit scheme with multiple gold item
	/// Automation ID : TC113
	/// Author : Vishnu Manoj K
	/// </summary>
	public void TC113_GPPSettlementAverageRateSingleSkuMultipleSku() throws Exception
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//	Prerequisite ManualEntry of GPP account
		//			Thread.sleep(15000);
		//			Map<String, Object> ResultMap = new LinkedHashMap<>();
		//			List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
		//			Map<String, String> AccountMap1 = new HashMap<>();
		//			AccountMap1.put("GppAccountNo", "33GG000005851");
		//			AccountMap1.put("CustomerAccountNo", "1117140297");
		//			MatchedAccounts1.add(AccountMap1);
		//			ResultMap.put("MatchedAccounts", MatchedAccounts1);
		//			ResultMap.put("TotalFound", MatchedAccounts1.size());
		//			Map<String, Object> GPPMaturedAccountDetails =ResultMap;


		//Prerequisite to take the Customer  6 months Installment must be completed and a matured GG scheme
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_InstallmentCount,GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_HwManyGppAccNeeded,UtilityTestData.SchemeCode,UtilityTestData.MaturedGBScheme,UtilityTestData.AdjustMaturedGBScheme);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_Purity);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		List<WebElement> StoneErrorMessage = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));
		if (!StoneErrorMessage.isEmpty()) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
		}	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double ErpGoldRate24k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
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

		for (int idx = 0; idx < MatchedAccounts.size(); idx++) {
			//for (int idx = MatchedAccounts.size() - 1; idx >= 0; idx--) {

			Map<String, String> AccountMap = MatchedAccounts.get(idx);

			CustomerAccount = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) GPPMaturedAccountDetails.get("GppAmount");

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

			// Step 7. Enter scheme id (GB) and click "Add to cart"
			//Verify whether the entered GB scheme gets added - verification done in 10th Step
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
			SkuPurityReturn = (String) GPPMaturityValues.get("Purity");
			//TotalRgWeightReturn = (String) GPPMaturityValues.get("TotalQty");
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
		appUtils.AddOrVerifyPan();
		//Step 8.Enter the OTP
		//Step 9.Scan multiple Gold item SKU's
		//Expected :Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_SchemeName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_SchemeWeight);
		Thread.sleep(5000);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		ProductTotalList.add(ActualSchemeAmount);
		double SchemeAmountNow=Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate=Double.parseDouble(SkuGoldRateReturn);
		TotalRgWeightReturn=String.valueOf(SchemeAmountNow/SchemeGoldRate);
		System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
		int SkuCounter = 1;
		// Gold SKUs
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_TransferType,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_FromCounterGold,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_MetalTypeGold,
				Double.parseDouble(TotalRgWeightReturn),
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,String.valueOf(ErpGoldRate24k));
			SkuCounter++;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(10000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		//BenfitPercentage
		String TotalBonus = mathUtils.ToGetGppBonus(ScannedSkuDataMap,SkuList.size(),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_BenefitPercentage,Double.parseDouble(TotalRgWeightReturn),ErpGoldRate24k);
		System.out.println(TotalBonus);
		asrt.assertEquals(ActualBonus, Double.parseDouble(TotalBonus),1,"Bonus mismatch: "+Double.parseDouble(TotalBonus)+" but found "+ActualBonus+" in billing page");

		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_BonusName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_BonusQty);
		ProductTotalList.add(Bonus);

		System.out.println(ProductQtyList);
		System.out.println(ProductNames);
		System.out.println(	ProductTotalList);

		Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
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
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		//Step 11: save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC113_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))));
		String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTax);
		double SchemeAmount =Double.parseDouble(ActualSchemeAmount);
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
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC113_TransactionTypeSales);

		//Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		    = TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 		= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   		= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in Cashier Screen");		

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
		//double ExpectedTaxFromCashierScreen = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTax);
		asrt.assertEquals( ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 19: Post the Invoice
		//Expected Result:  Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC113_TaxInvoice");
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
		String GppDiscount = Double.toString(ActualBonus);
		String TotalGrossWeight = String.format("%.3f", (Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2))));
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
		String TotalValueInTaxInvoice = PdfDetails.get("TotalValue");

		//			//Step 20: Click on "INSURANCE CERTIFICATE" check box
		//			Thread.sleep(3000);
		//			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));
		//
		//			//Step 21: Click on print button
		//			//Expected Resuly:  Verify INSURANCE CERTIFICATE
		//			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		//			Thread.sleep(3000);
		//			pdfUtils.ValidateInvoiceValue(TotalValueInTaxInvoice);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	/// <summary>
	/// Test Case Title : Settle a GG scheme with multiple gold item
	/// Automation ID : TC112
	/// Author : Neethu
	/// </summary>
	public void TC112_GPPSettlementAverageRateSingleSkuMultipleSku() throws Exception
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//			//Prerequisite ManualEntry of GPP account
		//			Thread.sleep(15000);
		//			Map<String, Object> ResultMap = new LinkedHashMap<>();
		//			List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
		//			Map<String, String> AccountMap1 = new HashMap<>();
		//			AccountMap1.put("GppAccountNo", "33GG000008808");
		//			AccountMap1.put("CustomerAccountNo", "33CA00090782");
		//			MatchedAccounts1.add(AccountMap1);
		//			ResultMap.put("MatchedAccounts", MatchedAccounts1);
		//			ResultMap.put("TotalFound", MatchedAccounts1.size());
		//			Map<String, Object> GPPMaturedAccountDetails =ResultMap;

		//Prerequisite to take the Customer 11 months Installment must be completed and a matured GG scheme
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_InstallmentCount,GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_HwManyGppAccNeeded,UtilityTestData.SchemeCode,UtilityTestData.MaturedGBScheme,UtilityTestData.AdjustMaturedGBScheme);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double ErpGoldRate24k=appUtils.FetchCurrentBoardRate("24k");
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

			CustomerAccount = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) GPPMaturedAccountDetails.get("GppAmount");

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
			//Verify whether the entered GG scheme gets added - verification done in 10th Step
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
			SkuPurityReturn = (String) GPPMaturityValues.get("Purity");
			//TotalRgWeightReturn = (String) GPPMaturityValues.get("TotalQty");
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
		appUtils.AddOrVerifyPan();

		//Step 8.Enter the OTP
		//Step 9.Scan multiple Gold item SKU's
		//Expected :Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_SchemeName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_SchemeWeight);
		Thread.sleep(5000);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		ProductTotalList.add(ActualSchemeAmount);
		double SchemeAmountNow=Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate=Double.parseDouble(SkuGoldRateReturn);
		TotalRgWeightReturn=String.valueOf(SchemeAmountNow/SchemeGoldRate);
		System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
		int SkuCounter = 1;
		// Gold SKUs
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_TransferType,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_FromCounterGold,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_MetalTypeGold,
				Double.parseDouble(TotalRgWeightReturn),
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,String.valueOf(ErpGoldRate24k));
			SkuCounter++;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(10000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		//BenfitPercentage
		String TotalBonus = mathUtils.ToGetGppBonus(ScannedSkuDataMap,SkuList.size(),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_BenefitPercentage,Double.parseDouble(TotalRgWeightReturn),ErpGoldRate24k);
		System.out.println(TotalBonus);
		asrt.assertEquals(ActualBonus, Double.parseDouble(TotalBonus),1,"Bonus mismatch: "+Double.parseDouble(TotalBonus)+" but found "+ActualBonus+" in billing page");

		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_BonusName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_BonusQty);
		ProductTotalList.add(Bonus);

		System.out.println(ProductQtyList);
		System.out.println(ProductNames);
		System.out.println(	ProductTotalList);

		Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
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
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		//Step 11: save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC112_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))));
		String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTax);
		double SchemeAmount =Double.parseDouble(ActualSchemeAmount);
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
		Thread.sleep(7000);

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
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC112_TransactionTypeSales);

		//Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative[Step removed due to new update]
		//	base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		    = TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 		= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   		= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in Cashier Screen");		

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
		//double ExpectedTaxFromCashierScreen = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTax);
		asrt.assertEquals( ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 19: Post the Invoice
		//Expected Result:  Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC112_TaxInvoice");
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
		String GppDiscount = Double.toString(ActualBonus);
		String TotalGrossWeight = String.format("%.3f", (Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2))));
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
		String TotalValueInTaxInvoice = PdfDetails.get("TotalValue");
		/*
					//Step 20: Click on "INSURANCE CERTIFICATE" check box
					Thread.sleep(3000);
					base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));

					//Step 21: Click on print button
					//Expected Result:  Verify INSURANCE CERTIFICATE
					base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
					Thread.sleep(3000);
					pdfUtils.ValidateInvoiceValue(TotalValueInTaxInvoice);
		 */
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	/// <summary>
	/// Test Case Title : settle a GG scheme with multiple categroy items(gold,UNC,DMD etc) Verify insurance value is correct when an SKU is added after applying the GPP discount to the cart.]
	/// Automation ID : TC114
	/// Author : Vishnu Manoj K
	/// </summary>
	public void TC114_GPPSettlementAverageRateSingleSkuMultipleSku() throws Exception
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		Thread.sleep(15000);
		//			Map<String, Object> ResultMap = new LinkedHashMap<>();
		//			List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
		//			Map<String, String> AccountMap1 = new HashMap<>();
		//			AccountMap1.put("GppAccountNo", "33GG000014183");
		//			AccountMap1.put("CustomerAccountNo", "33CA00127008   ");
		//			MatchedAccounts1.add(AccountMap1);
		//			ResultMap.put("MatchedAccounts", MatchedAccounts1);
		//			ResultMap.put("TotalFound", MatchedAccounts1.size());
		//			Map<String, Object> GPPMaturedAccountDetails =ResultMap;

		//Prerequisite to take the Customer  11 months Installment must be completed and a matured GG scheme
		//11 Installments completed
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_InstallmentCount,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_HwManyGppAccNeeded,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SchemeCode,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_MaturedScheme,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_AdjustedScheme);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_Purity);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double ErpGoldRate24k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
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

			CustomerAccount = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) GPPMaturedAccountDetails.get("GppAmount");

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

			// Step 7. Enter scheme id (GB) and click "Add to cart"
			//Verify whether the entered GB scheme gets added - verification done in 10th Step
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
			SkuPurityReturn = (String) GPPMaturityValues.get("Purity");
			//TotalRgWeightReturn = (String) GPPMaturityValues.get("TotalQty");
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

		appUtils.AddOrVerifyPan();

		//Step 8.Enter the OTP
		//Step 9.Scan multiple category(Gold,Uncut,Diamond)
		//Expected :Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		List<String> ProductTotalList         = new ArrayList<>();
		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SchemeName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SchemeWeight);
		Thread.sleep(5000);
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		ProductTotalList.add(ActualSchemeAmount);
		double SchemeAmountNow=Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate=Double.parseDouble(SkuGoldRateReturn);
		TotalRgWeightReturn=String.valueOf(SchemeAmountNow/SchemeGoldRate);
		System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
		int SkuCounter = 1;
		// Gold SKUs
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_TransferType,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_FromCounterGold,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_MetalTypeGold,
				Double.parseDouble(TotalRgWeightReturn)/2,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SaleProductCount);
		//For kalpetta 
		//		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
		//				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_TransferType,
		//				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_FromCounterGold,
		//				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_MetalTypeGold,
		//				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_GoldQty,
		//				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SkuGoldCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		SKUResult GoldSkus = appUtils.GetOGSaleSku(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_TransferType,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_FromCounter,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_MetalType,
				Double.parseDouble(TotalRgWeightReturn)/2,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SaleProductCount);
		//			List<String> GoldSkus = appUtils.SelectMultipleSKUs(
		//					GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SaleProductCount,
		//					GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_TransferType,
		//					GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_FromCounter,
		//					GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_MetalType);
		//			SkuList.addAll(GoldSkus);
		SkuList.addAll(GoldSkus.PcsRateSKU);
		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			Thread.sleep(5000);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,String.valueOf(ErpGoldRate24k));
			SkuCounter++;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(10000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		//BenfitPercentage
		String TotalBonus = mathUtils.ToGetGppBonus(ScannedSkuDataMap,SkuList.size(),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_BenefitPercentage,Double.parseDouble(TotalRgWeightReturn),ErpGoldRate24k);
		System.out.println(TotalBonus);
		asrt.assertEquals(ActualBonus, Double.parseDouble(TotalBonus),1,"Bonus mismatch: "+Double.parseDouble(TotalBonus)+" but found "+ActualBonus+" in billing page");

		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_BonusName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_BonusQty);
		ProductTotalList.add(Bonus);

		System.out.println(ProductQtyList);
		System.out.println(ProductNames);
		System.out.println(	ProductTotalList);

		Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
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
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i == 1 ||  i==2) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +"' should contain actual '" + ActualItemNameInBillingPge + "'");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +"' should contain actual '" + ActualItemNameInBillingPge + "'");
			}

			List<WebElement> Quantity           = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge   = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge        = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge      = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax           = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInBillingPge         = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInBillingPge   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum                                = SubTotalSum+Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge   = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTax               = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax             = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()) + Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals( ActualTax,ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 11: save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC114_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String TaxableValue        = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))));
		String InvoiceTotal        = Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTax);
		double SchemeAmount        = Double.parseDouble(ActualSchemeAmount);
		String TotalAdjustment     = Double.toString(SchemeAmount+ActualBonus);
		String TotalAmt            ="";
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
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"");

		//Step 14: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_TransactionTypeSales);

		//Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative[Step removed due to new update]
		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		    = TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 		= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   		= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in Cashier Screen");		

		double SubTotalSumInCashierScreen = 0.00;
		for(int i =0; i < AllProductRowsInCashierScreen.size() ;i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen     = ItemNameInCashierScreen.get(i).getText();
			if (i == 1 ||  i==2) {
				String ExpectedItemNameInBillingPge  = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +"' should contain actual '" + ActualItemNameInCashierScreen + "'");
			} else {
				String ExpectedItemNameInBillingPge  = ProductNames.get(i).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge +"' should contain actual '" + ActualItemNameInCashierScreen + "'");
			}
			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInCashierScreen     = QuantityInCashierScreen.get(i).getText().replace("-", "");
			String ExpectedQuantityInCashierScreen   = ProductQtyList.get(i);
			double ActualQtyInCashierScreen          = Double.parseDouble(ActualQuantityInCashierScreen);
			double ExpectedQtyInCashierScreen        = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals( ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in Cashier Screen");

			List<WebElement> TotalWithoutTax         = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String TotalWithoutTaxInCashierScreen    = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTaxInCashierScreen   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalWithoutTaxInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals( Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in Cashier Screen");
		}
		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInCashierScreen   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals( Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in Cashier Screen");

		String ActualTaxInCashierScreen        = Tax.replaceAll("[^\\d.-]", "").trim();
		double ActualTaxFromCashierScreen      = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTaxFromCashierScreen    = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()) + Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		//double ExpectedTaxFromCashierScreen = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInCashierScreen      = String.format("%.2f",ExpectedTax);
		asrt.assertEquals( ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 19: Post the Invoice
		//Expected Result:  Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC114_TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath     = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		double GrossAmountForSale = Double.parseDouble(ProductTotalList.get(1)) + Double.parseDouble(ProductTotalList.get(2));
		String GrossAmount        = String.format("%.2f", GrossAmountForSale);
		String TotalDiscount      = DiscountInCashierScreen;
		String TotalQtyPcs        = Integer.toString(SkuList.size());
		String PaymentHDFCCard    = PaymentAmount;
		String TotalValue         = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim()));
		String NetValue           = PaymentAmount;
		String GppAdvance         = ActualSchemeAmount;
		String GppDiscount        = Double.toString(ActualBonus);
		String TotalGrossWeight   = String.format("%.3f", (Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2))));
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
		String TotalValueInTaxInvoice = PdfDetails.get("TotalValue");
		/*
				//Step 20: Click on "INSURANCE CERTIFICATE" check box[Step not required: New application change]
				Thread.sleep(3000);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));

				//Step 21: Click on print button
				//Expected Resuly:  Verify INSURANCE CERTIFICATE
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
				Thread.sleep(3000);
				pdfUtils.ValidateInvoiceValue(TotalValueInTaxInvoice);
		 */
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	/// <summary>
	/// Test Case Title : Settle a GG scheme with no benefit
	/// Automation ID : TC116
	/// Author : Christy Reji
	/// </summary>

	public void TC116_GPPSettlementAverageRateSingleSkuMultipleSku() throws Exception {

		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//		Thread.sleep(15000);
		//		Map<String, Object> ResultMap = new LinkedHashMap<>();
		//		List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
		//		Map<String, String> AccountMap1 = new HashMap<>();
		//		AccountMap1.put("GppAccountNo", "20GG000078572");
		//		AccountMap1.put("CustomerAccountNo", "20CA00327213");
		//		MatchedAccounts1.add(AccountMap1);
		//		ResultMap.put("MatchedAccounts", MatchedAccounts1);
		//		ResultMap.put("TotalFound", MatchedAccounts1.size());
		//		Map<String, Object> GPPMaturedAccountDetails =ResultMap;

		//Below Six Installments
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_Installment,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_MaxxGppAccounts,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_SchemeCode,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_MaturedScheme,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_AdjustedScheme);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_Purity);

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
			//Verify whether the entered GGscheme gets added - verification done in 10th Step
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

		//Step 8.Enter the OTP
		//Step 9.Scan multiple category(Gold)
		//Expected :Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SchemeName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC114_SchemeWeight);
		Thread.sleep(5000);
		String ActualSchemeAmount     = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
		ProductTotalList.add(ActualSchemeAmount);
		double SchemeAmountNow        = Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate         = Double.parseDouble(SkuGoldRateReturn);
		TotalRgWeightReturn           = String.valueOf(SchemeAmountNow/SchemeGoldRate);
		System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
		int SkuCounter = 1;
		// Gold SKUs
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_TransferType,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_FromCounterGold,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_MetalTypeGold,
				Double.parseDouble(TotalRgWeightReturn),
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_SaleProductCount);
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
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


		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billling Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
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

		// Adding PAN card if the cutomer doest not have
		Thread.sleep(3000);
		if(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"))) {
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Close"));
			Thread.sleep(3000);
			appUtils.AddOrVerifyPan();
			base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		}
		else {System.out.println("Customer already have valid PAN number");}
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName    = pdfUtils.DownloadAndRenameLatestPDF("TC116_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath    = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                    = Tax;
		double CalculatedTaxable      = Double.parseDouble(SubTotal) + Double.parseDouble(ActualSchemeAmount);
		String TaxableValue           = String.valueOf(CalculatedTaxable); 
		double CalculatedInvoiceTotal = Double.parseDouble(TotalAmount) + Double.parseDouble(ActualSchemeAmount);
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
		//Step 13: Select estimate and click on proceed button
		//Expected:Verify whether the recalled item is the same as the item in the cart.

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"");

		//ValidateRecallPage GG Scheme
		List<String> ExpectedGrossWeights = ProductQtyList;
		appUtils.ValidateRecallPageOGItem(ProductNames,ExpectedGrossWeights,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_SchemeName,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_SchemeQty,
				Double.parseDouble(SubTotal),
				Double.parseDouble(Tax),
				Double.parseDouble(TotalAmount),
				Integer.parseInt(LinesCount),
				"GG-Scheme");

		//Step 13: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_TransactionTypeSales);

		//Step 14: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 15: Choose a sales representative[Step not required]
		Thread.sleep(3000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Step 16 : Click on amount
		//Step 17: Select payment method method (cash/card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 18 Post invoice
		//Expected : Check final invoice details	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice      = pdfUtils.DownloadAndRenameLatestPDF("TC116FinalTaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath    = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		double CalcTaxvalue      = Double.parseDouble(TaxableValue);
		String GrossAmount       = String.format("%.2f", CalcTaxvalue);
		String TotalDiscount     = Discount;
		String TotalQtyPcs       = String.valueOf(ActualNames.size());
		String PaymentHDFCCard   = PaymentAmount;
		String TotalValue        = InvoiceTotal;
		String NetValue          = PaymentAmount;
		String GppAdvance        = ActualSchemeAmount;
		String GppDiscount       = "0.0";
		double CalculatedGrossWt = Double.parseDouble(TotalGrossWeight) + Double.parseDouble(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC116_SchemeQty);	
		String PDFGrossWt        = String.valueOf(CalculatedGrossWt);

		Map<String, String> PdfDetails = pdfUtils.ValidateTaxInvoiceSchemeSettlementPdfGPPDiscount(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				PDFGrossWt,
				TaxableValue,
				TotalValue,
				PaymentHDFCCard,
				GppAdvance,
				GppDiscount,
				TotalAdjustment,
				NetValue);
		String TotalValueInTaxInvoice = PdfDetails.get("TotalValue");
		/*Insurance certificate validation is not required [new change]
			//Step 20: Click on "INSURANCE CERTIFICATE" check box
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));

			//Step 21: Click on print button
			//Expected:Verify INSURANCE CERTIFICATE
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Thread.sleep(3000);
			pdfUtils.ValidateInvoiceValue(TotalValueInTaxInvoice);*/
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
	
	/// <summary>
	/// Test Case Title : No benefit GS scheme settlement with multiple item
	/// Automation ID : TC118
	/// Author : Bharath
	/// </summary>

	public void TC118_GPPSettlementAverageRateSingleSkuMultipleSku() throws Exception
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite ManualEntry of GPP account

		//				Thread.sleep(15000);
		//				Map<String, Object> ResultMap = new LinkedHashMap<>();
		//				List<Map<String, String>> MatchedAccounts1 = new ArrayList<>();
		//				Map<String, String> AccountMap1 = new HashMap<>();
		//				AccountMap1.put("GppAccountNo", "20GS000002150");
		//				AccountMap1.put("CustomerAccountNo", "20CA00431586");
		//				AccountMap1.put("GppAmount", "161747.12");
		//				MatchedAccounts1.add(AccountMap1);
		//				ResultMap.put("MatchedAccounts", MatchedAccounts1);
		//				ResultMap.put("TotalFound", MatchedAccounts1.size());
		//				Map<String, Object> GPPMaturedAccountDetails =ResultMap;

		//Prerequisite to take the Customer 11 months Installment must be completed and a matured GS scheme
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GSMaturedAccountDetails(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_InstallmentCount,GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_HwManyGppAccNeeded,GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_SchemeCode,UtilityTestData.MaturedGBScheme,UtilityTestData.AdjustMaturedGBScheme);

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_Purity);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1); 
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		double ErpGoldRate24k = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		Thread.sleep(3000);
		System.out.println("BoardRate24k "+ErpGoldRate24k);
		Thread.sleep(10000);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
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

			CustomerAccount = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) AccountMap.get("GppAmount");

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

			// Step 7. Enter scheme id (GS) and click "Add to cart"
			//Verify whether the entered GS scheme gets added - verification done in 10th Step
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
			SkuPurityReturn = (String) GPPMaturityValues.get("Purity");
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
		String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

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
		    	 Random random = new Random();
		         int FirstDigit = 6 + random.nextInt(4); // gives 6, 7, 8, or 9
		         long RemainingDigits = (long)(random.nextDouble() * 1_000_000_000L);
		         String PhoneNumber = FirstDigit + String.format("%09d", RemainingDigits);
		 		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"),PhoneNumber);
		 		base.ClickCondition(LoginPageObj.Btn_SingnIn("cmdSaveCustomerDetails"));
		     } else break;
		    	 }
		     }
		
		//Step 8.Enter the OTP
		//Step 9.Scan multiple Gold item SKU's
		//Expected :Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_SchemeName);
		ProductQtyList.add(GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_SchemeWeight);
		Thread.sleep(5000);
		ProductTotalList.add(ActualSchemeAmount);
		double SchemeAmountNow=Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate=Double.parseDouble(SkuGoldRateReturn);
		TotalRgWeightReturn=String.valueOf(SchemeAmountNow/SchemeGoldRate);
		System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
		int SkuCounter = 1;
		// Gold SKUs
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_TransferType,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_FromCounterGold,
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_MetalTypeGold,
				Double.parseDouble(TotalRgWeightReturn),
				GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_SaleProductCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,String.valueOf(ErpGoldRate24k));
			SkuCounter++;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 10: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(10000);

		//FOR Asserting the WARNING MESSAGE
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")), "The warning message: GPP account is not eligible for GPP discount please proceed further is not displayed in the billing page.");		
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));

		System.out.println(ProductQtyList);
		System.out.println(ProductNames);
		System.out.println(	ProductTotalList);

		Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
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
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		//Step 11: save esimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC118_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String TaxableValue= String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))));
		String InvoiceTotal=Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))+ActualTax);
		double SchemeAmount =Double.parseDouble(ActualSchemeAmount);
		String TotalAdjustment = Double.toString(SchemeAmount);
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
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GPPSettlementAverageRateSingleSkuMultipleSkuTestData.TC118_TransactionTypeSales);

		//Step 15: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16: Choose a sales representative
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String NetTotalInCashierScreen	 		    = TransactionDataLineInCashierScreen.get("NetTotal");
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String TotalGrossWeightInCashierScreen 		= TransactionDataLineInCashierScreen.get("GrossWeight");
		String TotalNetWeightInCashierScreen   		= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxInCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountInCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in Cashier Screen");		

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
		//double ExpectedTaxFromCashierScreen = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim())*0.03;
		String ExpectedTaxInCashierScreen = String.format("%.2f",ExpectedTax);
		asrt.assertEquals( ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 19: Post the Invoice
		//Expected Result:  Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC118_TaxInvoice");
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
		String GppDiscount = "0.00";
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

//
//		//Step 20: Click on "INSURANCE CERTIFICATE" check box
//		Thread.sleep(3000);
//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "INSURANCE CERTIFICATE"));
//
//		//Step 21: Click on print button
//		//Expected Result:  Verify INSURANCE CERTIFICATE

//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
//		Thread.sleep(3000);
//		pdfUtils.ValidateInvoiceValue(TotalValueInTaxInvoice);
//		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

}