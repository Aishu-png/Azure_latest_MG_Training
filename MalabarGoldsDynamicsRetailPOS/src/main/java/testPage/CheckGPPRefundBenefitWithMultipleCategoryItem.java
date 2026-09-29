package testPage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
import testData.CheckGPPRefundBenefitWithMultipleCategoryItem_TestData;
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

public class CheckGPPRefundBenefitWithMultipleCategoryItem extends BasePge {

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
	CheckGPPRefundBenefitWithMultipleCategoryItem_TestData CheckGPPRefundBenefitWithMultipleCategoryItemTestData =new CheckGPPRefundBenefitWithMultipleCategoryItem_TestData();
	InvoiceWithAdvanceSchemeAndOG_TestData InvoiceWithAdvanceSchemeAndOGTestData = new InvoiceWithAdvanceSchemeAndOG_TestData();
	WebDriverWait HamBurgerWait  = new WebDriverWait(driver, Duration.ofSeconds(5));

	public CheckGPPRefundBenefitWithMultipleCategoryItem(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
	}

	//<summary>
	// Test Case Title : Settle refunded GG Scheme with multiple category item
	// Automation ID : TC301 
	// Author : Benson 
	// </summary>
	public void TC301_CheckGPPRefundBenefitWithMultipleCategoryItem() throws Exception{



		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		Thread.sleep(15000);
		//PreRequisite
		//1. Customer must be having a matured GG scheme and amount should be refunded
		// 2. 6-11 months installments must be completed
		// ERP
		//1. Search Retail deposit details
		//2. filter isrefunded as "Yes"
		// 3.Isrefund benifit as "No"
		//Thread.sleep(3000);
		Map<String, Object> RefundedGPPMaturedAccountDetails = erpUtils.RefundedGPPMaturedAccountDetails(
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_HwManyGppAccNeeded,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_SchemeCode,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_IsRefunded ,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_IsRefundBenefit); 


		// Step : Pre-Requisite for taking BoardRate
		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate("24k");

		appUtils.HamBurgerButtonClick("iconShop");	
		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) RefundedGPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerNo="";
		String GPPAccountNumber="";
		String GPPSchemeAmount=CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_RefundedGGAmount;  
		boolean ValidAccountFound = false;
		String SkuPurityReturn		="";
		String TotalRgWeightReturn	="";
		String SkuGoldRateReturn	="";
		double InstallmentMonths=0.00;
		String GppAmount="";
		//for (int idx = 0; idx < MatchedAccounts.size(); idx--)
		for(int idx = MatchedAccounts.size() -1 ; idx >=0; idx--){
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerNo = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			System.out.println("\nChecking PAN for Customer: " + CustomerNo + " | GPP Account: " + GPPAccountNumber);

			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);    
			try{appUtils.AddOrVerifyPan(); } 

			catch (Exception e) {
				if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("OTP verification")) ||
						base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"))){
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
			base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

			//Click on Auto Refund Benefit 
			base.buttonClick(LoginPageObj.Edt_AlertText("Auto Refund Benefit"));

			//Click on GPP Refund Benefit
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Refund Benefit"));

			// Enter scheme id
			//Click on redeem button
			//Thread.sleep(3000); 
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), 3);
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
			Thread.sleep(2000); 
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
			//Thread.sleep(2000); 
			base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"),3);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
			// Check if "No data" is displayed
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) {
				System.out.println("No data for account: " + GPPAccountNumber+ ". Retrying again after unlock adjustment");
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
				
				/* 
				//Extra Code To Unlock Adjustments              
                appUtils.HamBurgerButtonClick("iconHome");
 
                //Click on Unlock Adjustments
                //Thread.sleep(3000); 
                base.WaitforElementToBePresentWithTimeout(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","Unlock Adjustments"), 4);
                for(int i=0;i<=5;i++)
                {
                    base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","Unlock Adjustments"));
                    //                  try {
                    //                      if(HamBurgerWait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Payement("Unlock adjustment"))) != null)
                    //                      {
                    //                          break;
                    //                      }
                    //                  }catch(Exception e) {}
                    if(base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Payement("Unlock adjustment"), 20))
                    {
                        break;
                    }
                    Thread.sleep(2000);
                }
 
                //Select deposit type as GPP
                base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrDepositType"), CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_DepositType);
 
                //Enter GPP Account number
                base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("TransactionId"), GPPAccountNumber);
 
                //Click on Unlock Button
                base.ClickCondition(LoginPageObj.Btn_SignInButton("Unlock"));
 
                //Click on OK button
                base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_Alert("Unlocked successfully !"), 15);
                base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
                appUtils.HamBurgerButtonClick("iconShop");
 
                //Click on GPP Refund Benefit
                base.buttonClick(LoginPageObj.Edt_AlertText("GPP Refund Benefit"));
 
                // Enter scheme id again after unlock adjustment 
                //Click on redeem button
                //Thread.sleep(3000); 
                base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), 4);
                base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
                base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
                Thread.sleep(2000);
                base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
                //Thread.sleep(2000); 
                base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"), 3);
                base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
                // Check if "No data" is displayed
                if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) {
                    System.out.println("No data for account: " + GPPAccountNumber+ ". Retrying with next customer...");
                    base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
                }
                 */
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue; 
			}

			// Installment Months
			InstallmentMonths = erpUtils.GetInstallmentMonthsByCustomerAccount(CustomerNo, GPPAccountNumber);
			System.out.println("Installment Months from ERP: " + InstallmentMonths);

			if (InstallmentMonths < 6) {
				System.out.println("Installment months < 6 for account: " + GPPAccountNumber + ". Trying next customer...");
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue;
			}

			Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(GPPAccountNumber);
			SkuPurityReturn = (String) GPPMaturityValues.get("Purity");
			TotalRgWeightReturn = (String) GPPMaturityValues.get("TotalQty");
			SkuGoldRateReturn = (String) GPPMaturityValues.get("AvgRate");
			SkuGoldRateReturn = SkuGoldRateReturn.replace(",", "");
			GppAmount = GPPMaturityValues.get("Amount").toString().replaceAll(",", "");
			double TestSchemeAmount=Double.parseDouble(GppAmount); 
			System.out.println("Valid GPP account found: " + GPPAccountNumber);
			ValidAccountFound = true;
			break;
		}
		if (!ValidAccountFound) {
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.VoidTransaction();
		double BenefitPercent = 0; // Initialize

		if (InstallmentMonths >= 6 && InstallmentMonths <= 8) {
			BenefitPercent = 7;  // For 6, 7, 8 months
		} 
		else if (InstallmentMonths >= 9 && InstallmentMonths <= 10) {
			BenefitPercent = 10; // For 9, 10 months
		} 
		else if (InstallmentMonths == 11) {
			BenefitPercent = 14; // For 11 months
		} 
		else {
			BenefitPercent = 0;  // Default if not in above ranges
		}
		System.out.println("Installment Months: " + InstallmentMonths + " → Benefit Percent: " + BenefitPercent);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3:Select customer
		//Step 4:Click on add to estimation button
		Thread.sleep(3000);
		appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5:Click on customer adjustment button
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6:Click on Auto Refund Benefit 
		base.buttonClick(LoginPageObj.Edt_AlertText("Auto Refund Benefit"));

		//Step 7:Click on GPP Refund Benefit
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Refund Benefit"));

		//Step 8:Enter scheme id and click on redeem button
		//Expected: Verify whether the entered GG scheme gets added 
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), 4);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"),3);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String ActualSchemeAmount = base.GetText(InvoiceWithAdvanceSchemeAndOGObj.Ele_Scheme("tillLayout-TotalWithoutTaxField textRight","h5",1)).replaceAll("[^\\d.]", "").trim(); 
		asrt.assertEquals(ActualSchemeAmount, GPPSchemeAmount,"Scheme amount mismatch: Expected is "+GPPSchemeAmount+" but got "+ActualSchemeAmount+" in billing page");


		//Step 9.Enter the OTP
		//Step 10.Scan multiple category SKU
		//Expected :Check calculation
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		List<String> ProductTotalList         = new ArrayList<>();
		ProductNames.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_SchemeName);
		ProductQtyList.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_Weight);
		ProductTotalList.add(ActualSchemeAmount);
		double SchemeAmountNow=Double.parseDouble(GppAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate=Double.parseDouble(SkuGoldRateReturn);
		double BenefitQty=SchemeAmountNow/SchemeGoldRate;
		System.out.println("TotalRgWeightReturn"+TotalRgWeightReturn);
		int SkuCounter = 1;

		List<String> RequiredGoldSku = appUtils.SelectMultipleSKUs(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_SkuCount,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_TransferType,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_FromCounter,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");

		List<String> RequiredUncutSku = appUtils.SelectMultipleSKUs(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_SkuCount,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_TransferType,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_FromUncutCounter,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_MetalType);

		List<String> CombinedSkus = new ArrayList<>();
		CombinedSkus.addAll(RequiredGoldSku);
		CombinedSkus.addAll(RequiredUncutSku);
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");
		List<Double> ActualRgRateInItemDetailsPge = new ArrayList<>();
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : CombinedSkus) {
			Thread.sleep(5000);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			ActualRgRateInItemDetailsPge.add(Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG")));
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,String.valueOf(ErpGoldRate24k));
			SkuCounter++;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Step 11: Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		// Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(10000); 
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		String TotalBonus = mathUtils.ToGetGppBonus(ScannedSkuDataMap,CombinedSkus.size(),BenefitPercent,BenefitQty,ErpGoldRate24k); 
		System.out.println(TotalBonus);

		asrt.assertEquals(ActualBonus, Double.parseDouble(TotalBonus),1,"Bonus mismatch: "+Double.parseDouble(TotalBonus)+" but found "+ActualBonus+" in billing page");

		ProductNames.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_GPPBonus);  
		ProductQtyList.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_GPPBonusQty);
		ProductTotalList.add(Double.toString(ActualBonus)); 
		System.out.println(ProductQtyList);
		System.out.println(ProductNames);
		System.out.println(	ProductTotalList);

		Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(CombinedSkus, ScannedSkuDataMap);
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

		//Step 12: Save estimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC301_ProformaInvoice");
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

		//Step 13: Recall estimate from cashier screen
		//Step 14: Select estimate and click on proceed button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"");

		//Step 15: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_TransactionType);

		//Step 16: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 17: Choose a sales representative[Step removed due to new update]
		//Note:Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(CombinedSkus, ScannedSkuDataMap);
		String DiscountInCashierScreen 				= TransactionDataLineInCashierScreen.get("Discount");
		String ActualLinesCountInCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in Cashier Screen");		

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

		asrt.assertEquals( Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in Cashier Screen");

		double ActualTaxFromCashierScreen      = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTaxFromCashierScreen    = (Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.]", "").trim()) + Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "").trim()))*0.03;
		asrt.assertEquals( ActualTaxFromCashierScreen ,ExpectedTaxFromCashierScreen ,1.0,"Tax amount mismatch: "+ExpectedTaxFromCashierScreen +" but found "+ActualTaxFromCashierScreen +" in Cashier Screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInCashierScreen   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals( ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in Cashier Screen");

		//Step 18: Click on the Amount
		//Step 19: Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 20: Post the Invoice
		//Expected Result:  Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice       = pdfUtils.DownloadAndRenameLatestPDF("TC301_TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath     = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		double GrossAmountForSale = Double.parseDouble(ProductTotalList.get(1)) + Double.parseDouble(ProductTotalList.get(2));
		String GrossAmount        = String.format("%.2f", GrossAmountForSale);
		String TotalDiscount      = DiscountInCashierScreen;
		String TotalQtyPcs        = Integer.toString(CombinedSkus.size());
		String PaymentHDFCCard    = PaymentAmount;
		String TotalValue         = String.format("%.2f", (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)))+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim()));
		String NetValue           = PaymentAmount;
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
				"",
				GppDiscount,
				TotalAdjustment,
				NetValue);
		String TotalValueInTaxInvoice = PdfDetails.get("TotalValue");
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}
	//<summary>
	// Test Case Title : Settle refunded GB scheme with multiple category item
	// Automation ID : TC302
	// Author : Benson 
	// </summary>
	public void TC302_CheckGPPRefundBenefitWithMultipleCategoryItem() throws Exception{

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//PreRequisite
		//1. Customer must be having a matured GB scheme and amount should be refunded
		// 2. 6-11 months installment must be completed
		// ERP
		//1. Search Retail deposit details
		//2. filter isrefunded as "Yes"
		// 3.Isrefund benifit as "No"
		Thread.sleep(3000);
		Map<String, Object> RefundedGPPMaturedAccountDetails = erpUtils.RefundedGPPMaturedAccountDetails(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_HwManyGppAccNeeded,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_SchemeCode,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_IsRefunded ,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_IsRefundBenefit);  

		// Step : Pre-Requisite for taking BoardRate
		Double ErpGoldRate22k =appUtils.FetchCurrentBoardRate("22k");

		// Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");


		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) RefundedGPPMaturedAccountDetails.get("MatchedAccounts");
		String CustomerNo="";
		String GPPAccountNumber="";
		String GPPAmount=CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_RefundedGBGPPAmount;  
		boolean ValidAccountFound = false;
		double Quantity =0.00;
		String MetalRateFetch="";

		String SkuPurityReturn		="";
		String TotalRgWeightReturn	="";
		String SkuGoldRateReturn	="";
		double InstallmentMonths=0.00;
		String Amount="";

		//for (int idx = 0; idx < MatchedAccounts.size(); idx++)
		for(int idx = MatchedAccounts.size() -1 ; idx >=0; idx--){
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerNo = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");

			System.out.println("\nChecking PAN for Customer: " + CustomerNo + " | GPP Account: " + GPPAccountNumber);
			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);    
			try{appUtils.AddOrVerifyPan(); } // Use your existing method

			catch (Exception e) {
				if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("OTP verification")) ||
						base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!"))){
					base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					System.out.println("Otp required for this Customer"+CustomerNo);
					continue; 
				}
			}
			appUtils.HamBurgerButtonClick("iconShop");
			appUtils.VoidTransaction();

			// Select customer
			//Click on add to estimation button
			Thread.sleep(3000);
			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);

			//Click on customer adjustment button
			base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

			//Click on Auto Refund Benefit 
			base.buttonClick(LoginPageObj.Edt_AlertText("Auto Refund Benefit"));

			//Click on GPP Refund Benefit
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Refund Benefit"));

			// Enter scheme id
			//Click on redeem button
			Thread.sleep(3000);
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
				
				/* 
				//Extra Code To Unlock Adjustments              
                appUtils.HamBurgerButtonClick("iconHome");
 
                //Click on Unlock Adjustments
                //Thread.sleep(3000); 
                base.WaitforElementToBePresentWithTimeout(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","Unlock Adjustments"), 4);
                for(int i=0;i<=5;i++)
                {
                    base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","Unlock Adjustments"));
                    //                  try {
                    //                      if(HamBurgerWait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Payement("Unlock adjustment"))) != null)
                    //                      {
                    //                          break;
                    //                      }
                    //                  }catch(Exception e) {}
                    if(base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Payement("Unlock adjustment"), 20))
                    {
                        break;
                    }
                    Thread.sleep(2000);
                }
 
                //Select deposit type as GPP
                base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrDepositType"), CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC301_DepositType);
 
                //Enter GPP Account number
                base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("TransactionId"), GPPAccountNumber);
 
                //Click on Unlock Button
                base.ClickCondition(LoginPageObj.Btn_SignInButton("Unlock"));
 
                //Click on OK button
                base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_Alert("Unlocked successfully !"), 15);
                base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
                appUtils.HamBurgerButtonClick("iconShop");
 
                //Click on GPP Refund Benefit
                base.buttonClick(LoginPageObj.Edt_AlertText("GPP Refund Benefit"));
 
                // Enter scheme id again after unlock adjustment 
                //Click on redeem button
                //Thread.sleep(3000); 
                base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), 4);
                base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
                base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
                Thread.sleep(2000);
                base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
                //Thread.sleep(2000); 
                base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"), 3);
                base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
                // Check if "No data" is displayed
                if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data"))) {
                    System.out.println("No data for account: " + GPPAccountNumber+ ". Retrying with next customer...");
                    base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
                }
                */

				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue; // go to next MatchedAccounts entry
			}

			// ******** NEW CHECK — Installment Months ********
			InstallmentMonths = erpUtils.GetInstallmentMonthsByCustomerAccount(CustomerNo, GPPAccountNumber);
			System.out.println("Installment Months from ERP: " + InstallmentMonths);

			if (InstallmentMonths < 6 || InstallmentMonths > 10 ){
				System.out.println("Installment months < 6 for account: " + GPPAccountNumber + ". Trying next customer...");
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue;
			}

			Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(GPPAccountNumber);
			SkuPurityReturn = (String) GPPMaturityValues.get("Purity");
			TotalRgWeightReturn = (String) GPPMaturityValues.get("TotalQty");
			SkuGoldRateReturn = (String) GPPMaturityValues.get("AvgRate");
			SkuGoldRateReturn = SkuGoldRateReturn.replace(",", "");
			Amount = GPPMaturityValues.get("Amount").toString().replaceAll(",", "");
			double TestSchemeAmount=Double.parseDouble(Amount); 
			System.out.println("Valid GPP account found: " + GPPAccountNumber);
			ValidAccountFound = true;
			break;
		}
		if (!ValidAccountFound) {
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}

		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.VoidTransaction();

		//InstallmentMonths = erpUtils.GetInstallmentMonthsByCustomerAccount(CustomerNo,GPPAccountNumber);

		// STEP 3: Print or use the result
		System.out.println("Installment Months from ERP: " + InstallmentMonths);

		// Determine Benefit Percent based on Installment Months
		double BenefitPercent = 0;
		if (InstallmentMonths >= 6 && InstallmentMonths <= 8) {
			BenefitPercent = 9;  // For 6, 7, 8 months
		} 
		else if (InstallmentMonths >= 9 && InstallmentMonths <= 10) {
			BenefitPercent = 14; // For 9, 10 months
		} 
		else if (InstallmentMonths == 11) {
			BenefitPercent = 18; // For 11 months
		} 
		else {
			BenefitPercent = 0;  // Default if not in above ranges
		}

		System.out.println("Installment Months: " + InstallmentMonths + " → Benefit Percent: " + BenefitPercent);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3:Select customer
		//Step 4:Click on add to estimation button
		Thread.sleep(3000);
		appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5:Click on customer adjustment button
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6:Click on Auto Refund Benefit 
		base.buttonClick(LoginPageObj.Edt_AlertText("Auto Refund Benefit"));

		//Step 7:Click on GPP Refund Benefit
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Refund Benefit"));

		//Step 8:Enter scheme id and click on redeem button
		//Expected: Verify whether the entered GB scheme gets added 
		Thread.sleep(3000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		String ActualSchemeAmount = base.GetText(InvoiceWithAdvanceSchemeAndOGObj.Ele_Scheme("tillLayout-TotalWithoutTaxField textRight","h5",1)).replaceAll("[^\\d.]", "").trim(); 
		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		//Step 9: Enter OTP (disabled)
		//Step 10: Scan multiple category SKU
		//Expected: Check calculation
		//Adding 1st item as gold sku
		List<String> RequiredGoldSku = appUtils.SelectMultipleSKUs(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_SkuCount,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_TransferType,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_FromCounter,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");

		//Adding 2nd item as uncut sku
		List<String> RequiredUncutSku = appUtils.SelectMultipleSKUs(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_SkuCount,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_TransferType,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_FromUncutCounter,CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_MetalType);

		List<String> CombinedSkus = new ArrayList<>();
		CombinedSkus.addAll(RequiredGoldSku);
		CombinedSkus.addAll(RequiredUncutSku);
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		double ActualRgRateInItemDetailsPge =0.0;
		ProductNames.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_SchemeName);
		ProductQtyList.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_Weight);
		ProductTotalList.add(GPPAmount);
		int SkuCounter = 1;	
		for (String Sku : CombinedSkus) {  
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter); 
			SkuCounter++;
		}

		//Step: 11. Click on gpp discount button
		//Expected: Verify the GPP Bonus amount
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(14000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","5"));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		System.out.println("ActualBonus: "+ActualBonus);
		Map<String, Object> GBMaturityDetails = erpUtils.GPPMaturityValues(GPPAccountNumber);
		Amount = GBMaturityDetails.get("Amount").toString().replaceAll(",", "");
		double SchemeAmount=Double.parseDouble(Amount); 
		double BenefitWeight = SchemeAmount / ErpGoldRate22k; 
		System.out.println("BenefitWeight = " + BenefitWeight);
		double ExpectedBonus = Double.parseDouble(mathUtils.ToGetGppBonus(
				ScannedSkuDataMap,
				CombinedSkus.size(),
				BenefitPercent,
				BenefitWeight,
				ErpGoldRate22k,
				CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_CurrentGoldPurity));
		System.out.println(ExpectedBonus);
		ProductNames.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_GPPBonus);  
		ProductQtyList.add(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_GPPBonusQty);
		ProductTotalList.add(Double.toString(ActualBonus));  

		//Validation for Billing Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
		//Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(CombinedSkus, ScannedSkuDataMap);
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String NetTotal = TransactionDataLine.get("NetTotal");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 12: Save Estimate
		//Expected:Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(4000);
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// PDF Verification Step
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC302_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		double GoldSkuAmount = Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", ""));
		double UncutSkuAmount = Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.-]", ""));
		double TaxableValueSum = GoldSkuAmount + UncutSkuAmount;
		String TaxableValue = String.format("%.2f", TaxableValueSum);
		double InvoiceTotalValue = (
				TaxableValueSum 
				+ Double.parseDouble(Tax.replaceAll("[^\\d.-]", "")) );
		String InvoiceTotal = String.format("%.2f", InvoiceTotalValue);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,   
				""   
				);

		//Step 13: Recall estimate from cashier screen
		//Step 14: Select estimate and click on Recall estimation button
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

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		//Step 15: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldandSilverObj.Sel_BankProofType("Transaction type"),CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_TransactionType);

		//Step 16: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldandSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 17: Choose a Sales Representative(disabled)
		//Expected: Cashier Screen after recall Estimate*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		base.setZoom(driver, 60);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		if(base.isPresent(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldandSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();

		String ProductsDiscount = base.GetText(NormalSaleGoldandSilverObj.Ele_Lines("DiscountsField", "h4"));
		String PaymentAmountHDFC = base.GetText(NormalSaleGoldandSilverObj.Sel_AmountDue("Amount due"));
		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(CombinedSkus, ScannedSkuDataMap);
		String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
		String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");
		String TotalCashierPageGrossWeight=SkuBillingScrnTbleData.get("GrossWeight");

		Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(CombinedSkus, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashier, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		// Step 18: Click on amount
		// Step 19: Select payment method(cash/card)
		appUtils.PaymentAfterRecallEstimate(CheckGPPRefundBenefitWithMultipleCategoryItemTestData.TC302_PaymentMethod); 

		//Step 20: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC302TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TaxableValue;
		String TotalDiscount=ProductsDiscount;   
		String TotalQtyPcs= Integer.toString(CombinedSkus.size());
		double HdfcValue = Double.parseDouble(PaymentAmountHDFC.replaceAll("[^\\d.-]", "").trim());
		long RoundedValue = Math.round(HdfcValue);
		String PaymentHDFCCard = String.format("%.2f", (double) RoundedValue);
		String TotalValue = String.format("%.2f", Double.parseDouble(TaxableValue) + Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim()));
		String GppDiscount = Double.toString(ActualBonus);
		String AdjustmentTotal = GppDiscount;
		String TotalAdjustment=AdjustmentTotal;
		TotalGrossWeight   = String.format("%.3f", (Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2))));
		String NetValue = String.valueOf(Double.parseDouble(TotalValue) - Double.parseDouble(TotalAdjustment));
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
				"",
				GppDiscount,
				TotalAdjustment,
				NetValue);

		Thread.sleep(3000);
		System.out.println("Invoice Number from PDF: " +  PdfDetails.get("InvoiceNumber"));
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}
}

