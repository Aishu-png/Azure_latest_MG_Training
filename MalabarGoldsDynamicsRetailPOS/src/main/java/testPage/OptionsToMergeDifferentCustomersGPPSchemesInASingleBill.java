package testPage;

import java.time.Duration;
import java.util.ArrayList;
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
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceWithAdvanceSchemeAndOG_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.GPPSettlementDiscountPlan_TestData;
import testData.InvoiceWithAdvanceSchemeAndOG_TestData;
import testData.OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData;
import testData.PdcEntryExecution_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.SKUResult;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

public class OptionsToMergeDifferentCustomersGPPSchemesInASingleBill extends BasePge {

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
	GPPSettlementDiscountPlan_TestData GPPSettlementDiscountPlanTestData = new GPPSettlementDiscountPlan_TestData();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData OptionsToMergeDifferentCustomersGPPSchemesInASingleBillTestData = new OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData();


	public OptionsToMergeDifferentCustomersGPPSchemesInASingleBill(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
	}

	


			// <summary>
			// Test Case Title : GB with convert advance with current board rate lesser
			// Automation ID : TC271
			// Author: Vishnu RR
			// </summary>
			public void TC271_OptionsToMergeDifferentCustomersGPPSchemesInASingleBill() throws Exception {


				//Step 1: Login to POS
				login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
				base.setZoom(driver, 60);


				//Prerequisite Customer must have a matured GB scheme
				Thread.sleep(3000);
				Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBillTestData.TC271_RequiredSettlement,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBillTestData.TC271_AccountCount,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBillTestData.TC271_SchemeCode,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBillTestData.TC271_Matured,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBillTestData.TC271_Adjusted);

				
				//Step 2: Click on Transaction button
//				appUtils.HamBurgerButtonClick("iconshop");

				List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");
				String CustomerAccount="";
				String GPPAccountNumber="";
				String GPPAmount="";
				boolean ValidAccountFound = false;
				double Quantity =0.00;
				//String AdjustmentAmount="";
				String NormalAdvanceAmount="";
				String MetalRateFetch="";
				for (int idx = 0; idx < MatchedAccounts.size(); idx++) {
					Map<String, String> AccountMap = MatchedAccounts.get(idx);
					CustomerAccount = AccountMap.get("CustomerAccountNo");
					GPPAccountNumber = AccountMap.get("GppAccountNo");
					GPPAmount = (String) AccountMap.get("GppAmount"); 
		//-------------------------------------------------------------------------------------------
					
					//EXTRA CODE To check Customer has pan number or not
					// Wait for loader or overlay to go away
					WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

					// Wait for field to be clickable, then click via JS (if necessary)
					By SearchBox = NormalSaleGoldAndSilverObj.Edt_Name("Search");

					Wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
					base.ClickCondition(SearchBox);
					Thread.sleep(4000);
					// Wait again for it to be interactable
					WebElement element = Wait.until(ExpectedConditions.presenceOfElementLocated(SearchBox));
					Thread.sleep(2000);
					element.clear();  
					element.sendKeys(CustomerAccount);
					Thread.sleep(3000);
					base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Customers"));
					
					if (!base.isExists(LoginPageObj.Ele_ErrorMessage("win-itembox win-selected"))) {
						System.out.println("No Customer Data available in POS: " + CustomerAccount+ ". Retrying with next...");
						base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("width48 height48 buttonIcon center iconBack"));
						continue; // go to next MatchedAccounts entry
					}
					base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("width48 height48 buttonIcon center iconBack"));
					appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);
	  		
					try{appUtils.AddOrVerifyPan(); } // Use your existing method

					catch (Exception e) {
						if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("OTP verification"))){
							base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
							appUtils.HamBurgerButtonClick("iconShop");
							appUtils.VoidTransaction();	
							continue; // move to next customer
						}
					}
					appUtils.VoidTransaction();

					// Select customer
					//Click on add to estimation button
					Thread.sleep(3000);
					appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

					//Click on customer adjustment button
					base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
					base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

					//Click on GPP advance settlement 
					base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

					// Enter scheme id
					//Click on redeem button
					//Expected: Verify whether the entered GB scheme gets added 
					//base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrderInput")); //Vijayawada id
					base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput")); // Somaguguda id 
					base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
					Thread.sleep(6000);
					base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
					Thread.sleep(6000);
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

				//-------------------------------------------------------------------------

				//			String CustomerAccount ="20CA00027754";
				//	     	String GPPAccountNumber="20GB000043157";  
				//			String GPPAmount="38500.00";
				//		
			
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
						try {
							base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
						}catch (Exception e) {}

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
						String GoldRate = String.valueOf(CurrentGoldRate22k);
						Thread.sleep(3000);
						System.out.println("CurrentGoldRate22k "+CurrentGoldRate22k);
						if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found")))
						{
							base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));	
						}

						String ErpGoldRateFor24K = erpUtils.ToUpdateMetalRateWithLowerValue(GoldRate, "22k");
						Thread.sleep(150000); // Wait needed for ERP Gold Rate to Change

				//Pre-Requisite : Normal Advances 
				String AdvanceAmount = appUtils.CreateNormalAdvance(
						CustomerAccount,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_AdvanceAmount,
						UtilityTestData.SalePersonName, UtilityTestData.DueYear);

				String AdvanceAmt = String.format("%.2f", Double.parseDouble(AdvanceAmount.replaceAll("[^\\d.]", "")));

				//Step 3: Select customer
				//Step 4: Click on add to estimation button
				appUtils.SearchByCustomerID(CustomerAccount,UtilityTestData.MenuBarOptions[5]);

				//Step 5: In billing, Click on customer adjustment button
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				Thread.sleep(2000);
				base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));

				//Step 6: Click on GPP Advance Adjustment
				base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

				//Step 7:  Enter scheme id(GB) and click on "Add to cart" button
				//Step 8:  Enter OTP
				//Expected: Verify whether the entered GB scheme gets added 
				base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
				Thread.sleep(3000);
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
				Thread.sleep(3000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
				Thread.sleep(6000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
				Thread.sleep(8000);

				String ActualSchemeAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5")).replaceAll("[^\\d.]", "").trim();;
				asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

				double SchemeAmount =Double.parseDouble(ActualSchemeAmount);		
				double BenefitWeightFor22k = SchemeAmount/CurrentGoldRate22k;
				double CurrentGoldRate18k =(CurrentGoldRate22k*18)/22;
				double BenefitWeightFor18k = SchemeAmount/CurrentGoldRate18k;
				double RequiredWeight =0.07;
				if(BenefitWeightFor22k>BenefitWeightFor18k) {
					RequiredWeight= BenefitWeightFor22k;
				}
				if(BenefitWeightFor22k<BenefitWeightFor18k) {
					RequiredWeight= BenefitWeightFor18k;
				}

				// Step 9: Click on customer adjustment button
				// Step 10: click on normal advance adjustment button
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
				base.buttonClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

				// Step 11: Select advance and click on cart button
				// Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
				Thread.sleep(4000);
				base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
				base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.Terminal);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
				Thread.sleep(1000);
				//	base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itempox"));
				base.ClickCondition(InvoiceWithAdvanceSchemeAndOGObj.Ele_NormalAdv("h4 ellipsis cell", CurrentGoldRate22k));
				String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
				String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
				String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
				Thread.sleep(1000);

				asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

				// Step 12: Click on Cart button and Enter OTP
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

				// Step13: Scan Multiple Gold SKU
				// Check Calculation
				SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_TransferType,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_FromCounterGold,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_MetalTypeGold,
						RequiredWeight,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_SaleProductCount);
				List<String> SkuList =  SkuGoldSaleItem.PcsRateSKU;
				base.setZoom(driver, 60);
				appUtils.HamBurgerButtonClick("iconShop");

				Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
				List<String> ProductNames = new ArrayList<>();
				List<String> ProductQtyList = new ArrayList<>();
				List<String> ProductTotalList = new ArrayList<>();
				double TotalDiamondValue = 0.0;
				double ActualRgRateInItemDetailsPge =0.0;
				ProductNames.add(OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_SchemeName);
				ProductQtyList.add(OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_Weight);
				ProductTotalList.add(GPPAmount);


				ProductNames.add(OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_DepositAdjustment);
				ProductQtyList.add(OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_DepositAdjustmentQty);
				ProductTotalList.add((AdjustmentAmount));


				int SkuCounter = 1;	
				for (String Sku : SkuList) {
					appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
					ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
					ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
					ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
					TotalDiamondValue = TotalDiamondValue +Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_CvalueDIAMOND"));
					ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
					Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
					mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
					++SkuCounter;
				}

				//Step 14: Click on GPP discount button
				//Expected: Verify the GPP Bonus amount
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
				base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
				base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
				base.setZoom(driver, 60);
				Thread.sleep(15000);
				String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","last()"));
				double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
				System.out.println("ActualBonus: "+ActualBonus);

				double BenefitWeight = SchemeAmount / ActualRgRateInItemDetailsPge;
				double ExpectedBonus = Double.parseDouble(mathUtils.ToGetGppBonus(
						ScannedSkuDataMap,
						SkuList.size(),
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_BenefitPercent,
						BenefitWeight,
						CurrentGoldRate22k,
						OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_CurrentGoldPurity));
				asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ActualBonus+" but got "+ExpectedBonus+" in billing page");


				ProductNames.add(OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_BonusName);
				ProductQtyList.add(OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_Weight);
				ProductTotalList.add(Bonus);

				// Expected Result:Validating  No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
				Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
				//String SubTotal		 		= TransactionDataLine.get("Subtotal");
				String NetTotal		 		= TransactionDataLine.get("NetTotal");
				String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
				String Tax 					= TransactionDataLine.get("Tax");
				String TotalAmount 			= TransactionDataLine.get("TotalAmount");
				String ActualLinesCount		= TransactionDataLine.get("LinesCount");
				appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

				double taxValue = Double.parseDouble(Tax.replaceAll("[^\\d.-]", ""));
				double NetTotalInvoice = Double.parseDouble(NetTotal.replaceAll("[^\\d.-]", ""));

				String Taxablevalueinvoice = String.format("%.2f", Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "")) + Double.parseDouble(ProductTotalList.get(3).replaceAll("[^\\d.]", "")) );
				String InvoiceTotalAmount= String.format("%.2f",
						(

								Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", "")) + 
								Double.parseDouble(Tax.replaceAll("[^\\d.-]", "")))-
						Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", ""))
						);

				double InvoiceTotalAmountBillingPage = Double.parseDouble(InvoiceTotalAmount.replaceAll("[^\\d.-]", ""));
				double AdjustmentAmountBillingPage = Double.parseDouble(AdjustmentAmount.replaceAll("[^\\d.-]", ""));
				double NetTotalCalulation = InvoiceTotalAmountBillingPage - AdjustmentAmountBillingPage;
				String NetTotalBillScrn = String.format("%.2f", NetTotalCalulation);

				//Step 15: Save Esimate
				//Expected: Verify the Invoice details
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
				base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
				String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

				Thread.sleep(3000);
				String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC271_ProformaInvoice");
				Thread.sleep(2000);
				String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
				String Gst= String.valueOf(taxValue);
				String TaxableValue= Taxablevalueinvoice;
				String InvoiceTotal= Double.toString(Double.parseDouble(Taxablevalueinvoice) + Double.parseDouble(Gst));
				String NetTotals = String.valueOf(NetTotalInvoice);
				//String TotalAdjustment = Double.toString(SchemeAmount+ActualBonus) + AdjustmentAmount;
				String TotalAdjustment = String.format("%.2f", Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim())
						+ Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim()) 
						+ Double.parseDouble(AdjustmentAmount.replaceAll("[^\\d.]", "").trim()));
				String TotalAmt  =""; 
				pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
						ProFormaInvoicePath,
						TaxableValue,
						Gst,
						InvoiceTotal,
						TotalAdjustment,
						NetTotals,
						TotalAmt);

				//Step:16 Recall the estimate from cashier screen
				//Expected Result: Verify whether the recalled item is the same as the item in the cart
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
				base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				base.clearData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
				String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

				Assert.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page"); 


				//Step 17: Select estimate and click on save to estimate button
				//Step 18:Select estimate and click on save to estimate button
				//Expected Result: Validation for Cashier Screen after recall,No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX, Total Amount
				base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_TransactionTypeSales);

				//Step 19: Click on Process Estimation 
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

				//Step 20: Choose a sales representative
				if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
					base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
				}
				String PaymentAmountHDFC = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
				String PaymentAmount = String.format("%.2f", Double.parseDouble(PaymentAmountHDFC.replaceAll("[^\\d.]", "")));

				Map<String, String> RecallTransactionDataLines = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
				appUtils.ValidateBillingPgeAndCasierScreen(RecallTransactionDataLines, ProductNames, ProductQtyList, ProductTotalList, "Cashier Screen");

				//Step 21: Click on amount
				//Step 22: Select payment method(cash/card)
				//Step 23: Post the Invoice
				Thread.sleep(2000);
				appUtils.PaymentAfterRecallEstimate(OptionsToMergeDifferentCustomersGPPSchemesInASingleBill_TestData.TC271_PaymentMethod);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
				Thread.sleep(3000);
				String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC271TaxInvoice");
				Thread.sleep(2000);
				String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
				String GrossAmount = Taxablevalueinvoice;
				String TotalDiscount="";
				String TotalQtyPcs= Integer.toString(SkuList.size());
				String PaymentHDFCCard = PaymentAmount;
				String TotalValue = String.format("%.2f", Double.parseDouble(InvoiceTotal.replaceAll("[^\\d.-]", "").trim()));
				String NetValue = PaymentAmount;
				String GppAdvance = ActualSchemeAmount;
				String GppDiscount = Double.toString(ActualBonus);
				String TotalGrossWeight = String.format("%.2f", 
						Double.parseDouble(ProductQtyList.get(2).replaceAll("[^\\d.-]", "")) + 
						Double.parseDouble(ProductQtyList.get(3).replaceAll("[^\\d.-]", "")));	
				String NetWeight = TotalGrossWeight;	

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
						"" ,
						AdvanceAmt,
						GppAdvance,
						GppDiscount,
						TotalAdjustment,
						NetValue);
			}
}
