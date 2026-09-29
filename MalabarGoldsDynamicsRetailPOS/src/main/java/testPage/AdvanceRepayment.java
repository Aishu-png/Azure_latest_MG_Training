package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceRepayment_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleDiamondandPlatinum_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceSkuReserve_TestData;
import testData.AdvanceRepayment_TestData;
import testData.CommonData;
import testData.OldPurchaseExchangeOwnorOtherGoldorSilverSale_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.ReturnDetails;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

//import basePackage.BasePge;

public class AdvanceRepayment extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	MathUtilities mathUtils;
	PdfUtilities pdfUtils;
	ErpUtilities erpUtils;
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();	
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceSkuReserve_TestData AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceSkuReserveTestData = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceSkuReserve_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	NormalSaleDiamondandPlatinum_Obj NormalSaleDiamondandPlatinumObj =new NormalSaleDiamondandPlatinum_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj=new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj ();
	AdvanceRepayment_TestData AdvanceRepaymentTestData = new AdvanceRepayment_TestData();
	AdvanceRepayment_Obj AdvanceRepaymentObj=new AdvanceRepayment_Obj();

	public AdvanceRepayment(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils=new PdfUtilities(base);
		erpUtils=new ErpUtilities(base);
	}

	// <summary>
	// Test Case Title : Advance full repayment to customer
	// Automation ID : TC182
	// Author : Neethu
	// </summary>
	public void TC182_AdvanceRepayment() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: Customer must be having an advance(Normal/Offer)
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				AdvanceRepaymentTestData.TC182_CustomerNo,
				AdvanceRepaymentTestData.TC182_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//Step 3: Select customer
		//Step 4: Click on add to sale button
		Thread.sleep(5000);
		appUtils.SearchByCustomerID(AdvanceRepaymentTestData.TC182_CustomerNo, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(4000);

		//Step 5: Click on customer adjustment button at the billing screen
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6: Click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(4000);

		//Step 7: Select any one of the normal advance
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),
				UtilityTestData.Terminal);		 
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		Thread.sleep(3000);

		//Step 8: Click on "is refund" checkbox
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_TextField("stretch","checkbox"));

		//Step 9: Click on add to cart button
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		//Step 10: Click on the Amount
		String ExpectedAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		System.out.println("ExpectedAmount :"+ExpectedAmount);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		double ExpectedAdvanceAmount = Double.parseDouble(ExpectedAmount.replaceAll("[^\\d.]", "").trim());
		System.out.println("ExpectedAdvanceAmount :"+ExpectedAdvanceAmount);

		//Step 11: Click on payment method as RTGS/NEFT
		base.buttonClick(LoginPageObj.Edt_AlertText("RTGS/NEFT"));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_CartMoney("flexGrow50 flexRow row-enter","h1 marginBottom0 iconReturnKey button-content-return"));
		Thread.sleep(10000);

		if(base.isExists(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"))) 

		{
			base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),UtilityTestData.ApprCode);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
			Thread.sleep(8000);
			base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),UtilityTestData.CardNumber);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
			Thread.sleep(7000);
		}
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

		//Step 12: POST invoice
		//Expected: Check final invoice details
		Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvanceRefundPdfValidation();

		String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceRefund");
		String FixedGoldRate = AdvanceReceiptVoucherDetails.get("FixedGoldRate");
		String MaxGoldRate = AdvanceReceiptVoucherDetails.get("MaxGoldRate");
		String ApproxWeight = AdvanceReceiptVoucherDetails.get("ApproxWeight");
		String DepositType = AdvanceReceiptVoucherDetails.get("DepositType");
		String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetRefund");
		String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
		String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");
		String ActualDepositType = AdvanceReceiptVoucherDetails.get("DepositType");

		double ExpectedAdvanceRefund = Double.parseDouble(ActualAdvanceReceived.replaceAll("[^\\d.]", "").trim());
		double ExpectedNetReceived = Double.parseDouble(ActualNetReceived.replaceAll("[^\\d.]", "").trim());

		asrt.assertEquals(ExpectedAdvanceAmount, ExpectedAdvanceRefund,"Advance Refund mismatch: Expected is "+ExpectedAdvanceAmount+" but found "+ActualAdvanceReceived+" in Advance refundreceipt");
		asrt.assertEquals(ExpectedAdvanceAmount, ExpectedNetReceived,"Net refund value mismatch: Expected is "+ExpectedAdvanceAmount+" but found "+ActualNetReceived+" in Advance refund receipt");
		asrt.assertEquals(AdvanceRepaymentTestData.TC182_PaymentMode, ActualPaymentMode,"Payment mode mismatch: Expected is "+AdvanceRepaymentTestData.TC182_PaymentMode+" but found "+ActualPaymentMode+" in Advance refund receipt");
		asrt.assertEquals(ActualAdvanceReceived, PaymentAmount,"Payment Amount mismatch: Expected is "+ActualAdvanceReceived+" but found "+PaymentAmount+" in Advance refund receipt");
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Advance adjusted balance repayment to customer
	// Automation ID : TC184
	// Author : Christy Reji
	// </summary>
	public void TC184_AdvanceRepayment() throws Exception {

		//Prerequisite: Customer must be having an advance(Normal/Offer)
		// Step 1: Login to POS
		// Step 2: Click on Transaction button
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		//Creating OfferAdvance
		Map<String, String> AdvanceDetails = appUtils.CreateWeightBookedOfferAdvance(
				AdvanceRepaymentTestData.TC184_CustomerID,
				AdvanceRepaymentTestData.TC184_Pieces,
				AdvanceRepaymentTestData.TC184_GrossWeight,
				UtilityTestData.Company[0],
				UtilityTestData.Location[0],
				AdvanceRepaymentTestData.TC184_DepositPercent);

		String OrderId            = AdvanceDetails.get("OrderId");

		// Step 3: Select customer
		// Step 4: Click on add to sale
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(AdvanceRepaymentTestData.TC184_CustomerID,UtilityTestData.MenuBarOptions[0]);

		// Step 5: Click on customer adjustment button at the billing screen
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"))).click();

		// Step 6:Click on customer offer advance adjustment button
		//Step 7: Select any one of the offer advance
		wait.until(ExpectedConditions.presenceOfElementLocated(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions")));
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"),OrderId);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", OrderId));

		//Step 8: Enter adjustment amount
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("InputAmount"), AdvanceRepaymentTestData.TC184_AdjAmnt);

		//Step 9 :Click on is refund checkbox
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_TextField("stretch","checkbox"));

		// Step 10: Click on add to cart
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Step 11:Click on the Amount
		//Step 12:Click on RTGS payment mode
		// Expected:Note: Showing validation message Partial adjustment can not be refund !
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		//Error-handling , if present
		try
		{
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		}
		catch (Exception e){}
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		Thread.sleep(3000);
		Assert.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Warning!")), "A message with :-Partial adjustment can not be refund !-displayed");
		String WarningMsg=AdvanceRepaymentTestData.TC184_WarningMsg;
		String ActualMsg= base.GetText(CustomerCreationObj.Ele_CustomerIDLabel("text: content"));
		Assert.assertEquals(ActualMsg, WarningMsg, "The Expected msg in warning msg : "+WarningMsg+"but got Actual: "+ActualMsg+"message from warning Popup in Billing page");
	}

	// <summary>
	// Test Case Title : Advance added against to sale and refund to customer.
	// Automation ID : TC185
	// Author : Gokul
	// </summary>
	public void TC185_AdvanceRepayment() throws Exception {

		//Prerequisite: Customer must be having an advance(Normal/Offer)
		// Step 1: Login to POS
		// Step 2: Click on Transaction button
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite: Customer must be have a advance(normal,offer)
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: Customer must be having an advance(Normal/Offer)			
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				AdvanceRepaymentTestData.TC185_CustomerID,
				AdvanceRepaymentTestData.TC185_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//3. Select customer
		//4. Click on add to estimation button
		appUtils.SearchByCustomerID(AdvanceRepaymentTestData.TC185_CustomerID,UtilityTestData.MenuBarOptions[5]);

		//5. Click on customer adjustment button
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//6. click on normal advance settlement
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		//7. Select advance and Click on redeem button
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),AdvanceRepaymentTestData.TC185_AdvanceAmount);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(3000);
		base.ClickCondition(AdvanceRepaymentObj.Txt_BalanceAmount("h4 ellipsis cell",AdvanceRepaymentTestData.TC185_AdvanceAmount));
		String BalanceAmount    = base.GetText(AdvanceRepaymentObj.Txt_BalanceAmount("h4 ellipsis cell", AdvanceRepaymentTestData.TC185_AdvanceAmount));
		String AdvanceRate      = base.GetText(AdvanceRepaymentObj.Txt_MetalRate(AdvanceRepaymentTestData.TC185_AdvanceAmount, "Metal Rate"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRate, "22k");
		Thread.sleep(600000);     
		double Adjustment     = Double.parseDouble(AdjustmentAmount);
		double Quantity22k    = Double.parseDouble(BalanceAmount)/ Double.parseDouble(AdvanceRate);
		System.out.println("Advance Quantity:"+Quantity22k);
		double Current22kRate = (Double.parseDouble(ErpGoldRateFor24K) * 22) / 24;
		appUtils.HamBurgerButtonClick("iconShop");	

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");
		Thread.sleep(4000);

		// 8. Enter OTP
		// 9. Scan SKU
		// Note: Check average rate
		// Expected: Check Calculation.
		List<String> GoldSkuList      = appUtils.SelectMultipleSKUs(AdvanceRepaymentTestData.TC185_SkuCount,AdvanceRepaymentTestData.TC185_TransferType,AdvanceRepaymentTestData.TC185_FromCounter,AdvanceRepaymentTestData.TC185_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");		
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(AdvanceRepaymentTestData.TC185_DepositAdjustment);
		ProductQtyList.add(AdvanceRepaymentTestData.TC185_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		String ErpGoldRate24k = ErpGoldRateFor24K;
		int SkuCounter        = 1;
		erpUtils.ResetRemainingReturnWeight();
		Thread.sleep(2000);
		for (String Sku : GoldSkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			Thread.sleep(500);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,String.valueOf(Quantity22k),"22k",AdvanceRate,ErpGoldRate24k);
			SkuCounter++;
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(GoldSkuList, ScannedSkuDataMap);
		String SubTotal                 = TransactionDataLine.get("Subtotal");
		String NetTotal                 = TransactionDataLine.get("NetTotal");
		String Discount                 = TransactionDataLine.get("Discount");
		String TotalNetWeight           = TransactionDataLine.get("NetWeight");
		String Tax                      = TransactionDataLine.get("Tax");
		String TotalAmount              = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount         = TransactionDataLine.get("LinesCount");
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));		
		String ExpectedLinesCount       = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

		double InvoicAmount = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName         = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
//			if (i == 1) {
//				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();				
//				asrt.assertEquals(ActualItemNameInBillingPge, ,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
//				asrt.assertEquals(ActualItemNameInBillingPge, ,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
//			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();				
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			
			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge    = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge  = ProductQtyList.get(i);
			double ActualQtyInBillingPge         = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge       = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax           = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			InvoicAmount                               = InvoicAmount + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f", InvoicAmount).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

		String ActualTaxInBillingPge   = Tax.replaceAll("[^\\d.]", "").trim();
		double ExpectedTax             =( Double.parseDouble(ProductTotalList.get(1))) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		double Invoice=Double.parseDouble(ProductTotalList.get(1))+ExpectedTax;
		System.out.println("Invoice after adding tax: "+Invoice);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		double ExpectedTotalAmountInBillingPge = InvoicAmount+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, Double.parseDouble(String.valueOf(ExpectedTotalAmountInBillingPge).replace("-", "").trim()), 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");
		double NetTotalBillingScreen      = Invoice-Double.parseDouble(ProductTotalList.get(0));
		String TotalGrossWt               = String.format("%.3f", Double.parseDouble(ProductQtyList.get(1)));

		// 10. save estimate
		// Expected: Verify Proforma invoice
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);	
		String FirstInvoice        = pdfUtils.DownloadAndRenameLatestPDF("TC185_ProformaInvoice");
		Thread.sleep(4000);		
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\"+FirstInvoice+"";
		String TaxableValue        = String.valueOf(ProductTotalList.get(1));
		String InvoiceTotal        = String.valueOf(Invoice);
		String Gst                 = Tax;
		String TotalAmnt =String.valueOf(ActualTotalAmountInBillingPge);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				String.valueOf(NetTotalBillingScreen),
				TotalAmnt				
				);

		// 11.Recall estimate from cashier screen
		// 12. Select estimate and click on Recall estimation button
		// Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//Select estimate and click on Recall estimation button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		appUtils.ValidateBillingAndRecallScreenData(EstmnNumber,ExpectedLinesCount,ProductNames,ProductQtyList, SubTotal, Tax, NetTotal);

		// 13.Select the Transaction Type as advance
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), AdvanceRepaymentTestData.TC185_TransactionypeAdvance);

		// 14.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		// 15.Choose a sales representative
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));}

		// 16.Click on the Amount
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// 17.Select a Payment method (RTGS/NEFT)
		// Expected: Verify advance invoice.
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		String PaymentAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", "");
		Thread.sleep(5000);
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Map<String, String> Result = pdfUtils.AdvanceRefundPdfValidation();	
		String AdvanceReceived     = Result.get("AdvanceReceived");
		String NetAdvance          = Result.get("NetAdvance");
		String RTGSNEFTAmount      = Result.get("RTGSNEFTAmount");
		double PdfAdvanceReceived  = Double.parseDouble(AdvanceReceived.replaceAll("[^\\d.]", "").trim());
		double PdfNetAdvance       = Double.parseDouble(NetAdvance.replaceAll("[^\\d.]", "").trim());
		double PdfRTGSNEFTAmount   = Double.parseDouble(RTGSNEFTAmount.replaceAll("[^\\d.]", "").trim());

		asrt.assertEquals(PdfAdvanceReceived,Double.parseDouble(String.valueOf(NetTotalBillingScreen).replace("-", "").trim()),1, "Mismatch in Advance Received actual "+PdfAdvanceReceived+" and Expected "+NetTotalBillingScreen+"in AdvanceRefund Pdf ");
		asrt.assertEquals(PdfNetAdvance,Double.parseDouble(String.valueOf(NetTotalBillingScreen).replace("-", "").trim()),1, "Mismatch in NetAdvance actual "+PdfNetAdvance+" and Expected "+NetTotalBillingScreen+" in AdvanceRefund Pdf ");
		asrt.assertEquals(PdfRTGSNEFTAmount,Double.parseDouble(String.valueOf(NetTotalBillingScreen).replace("-", "").trim()), 1,"Mismatch in RTGSNEFTAmount Actual "+PdfRTGSNEFTAmount+" and Expected "+NetTotalBillingScreen+ " in AdvanceRefund Pdf ");

		// 18.Select the Transaction Type as sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), AdvanceRepaymentTestData.TC185_TransactionypeSales);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// 19.Click on Process Estimation 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
		base.setZoom(driver, 60);
		Thread.sleep(3000);

		// 20.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));}

		// 21.Click on the Amount
		// Expected: Note:Cashier Screen after recall:No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Subtotal,TAX,Total Amount.
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(GoldSkuList, ScannedSkuDataMap);
		String TaxInCashierScreen 					   = TransactionDataLineInCashierScreen.get("Tax");
		String ActualLinesCountInCashierScreen	       = TransactionDataLineInCashierScreen.get("LinesCount");
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen       = Integer.toString(AllProductRowsInCashierScreen.size());

		asrt.assertEquals(ActualLinesCountInCashierScreen,ExpectedLinesCountInCashierScreen,"Lines count mismatch: Expected is "+ExpectedLinesCountInCashierScreen+" but found "+ActualLinesCountInCashierScreen+" in CashierScreen");		

		List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInCashierScreen     = ItemNameInCashierScreen.get(0).getText();
		String ExpectedItemNameInCashierScreen   = ProductNames.get(1).substring(ProductNames.get(1).indexOf("-") + 1).trim();

		asrt.assertEquals(ActualItemNameInCashierScreen,ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in CashierScreen");			

		List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
		String ActualQuantityInCashierScreen     = QuantityInCashierScreen.get(0).getText().replace("-", "");
		String ExpectedQuantityInCashierScreen   = ProductQtyList.get(1).replace("-", "");
		double ActualQtyInCashierScreen          = Double.parseDouble(ActualQuantityInCashierScreen);
		double ExpectedQtyInCashierScreen        = Double.parseDouble(ExpectedQuantityInCashierScreen);
		asrt.assertEquals(ActualQtyInCashierScreen,ExpectedQtyInCashierScreen,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInCashierScreen+" but got "+ActualQtyInCashierScreen+" in CashierScreen");

		List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		String ActualTotalWithoutTaxInCashierScreen     = TotalWithoutTaxInCashierScreen.get(0).getText().replaceAll("[^\\d.-]", "").trim();
		double SubTotalSumInCashierScreen               = Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
		String ExpectedTotalWithoutTaxInCashierScreen   = String.format("%.2f", Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "").trim())); 
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+" in CashierScreen");

		String ExpectedSubTotalInCashierScreen = String.format("%.2f",SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen),1.0,"Subtotal mismatch: "+ExpectedSubTotalInCashierScreen+" but found "+ActualSubTotalInCashierScreen+" in CashierScreen");

		String ActualTaxInCashierScreen        = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxValueInCashierScreen = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
		String ExpectedTaxInCashierScreen      = String.format("%.2f",ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0,"Tax amount mismatch: "+ExpectedTaxInCashierScreen+" but found "+ActualTaxInCashierScreen+" in CashierScreen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountInCashierScreen   = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierScreen,ExpectedTotalAmountInCashierScreen,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInCashierScreen+" but found "+ActualTotalAmountInCashierScreen+" in CashierScreen");

		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// 22.Post the Invoice	
		// Expected: Verify Invoice
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String SalesInvoiceName  = pdfUtils.DownloadAndRenameLatestPDF("TC185_SalesInvoice");
		Thread.sleep(2000);
		String SalesInvoicePath  = System.getProperty("user.dir") + "\\Invoices\\" + SalesInvoiceName;
		String AdjustmentCashier = InvoiceTotal;
		String TotalNetWt        = TotalNetWeight.replaceAll("[^\\d.]", "");
		String GrossAmount       = TaxableValue;
		pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SalesInvoicePath,
				GrossAmount,
				"",
				"",
				ActualLinesCount,
				TotalNetWt,
				TotalGrossWt,
				TaxableValue,
				InvoiceTotal,
				"",
				AdjustmentCashier,
				GoldSkuList,
				ScannedSkuDataMap
				) ;
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	
	}


	// <summary>
	// Test Case Title : Advance partial repayment to customer
	// Automation ID : TC183
	// Author : Vishnu Manoj K
	// </summary>
	public void TC183_AdvanceRepayment() throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: Customer must be having an advance(Normal/Offer)
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				AdvanceRepaymentTestData.TC183_CustomerNo,
				AdvanceRepaymentTestData.TC183_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//Step 3: Select customer
		//Step 4: Click on add to sale button
		Thread.sleep(5000);
		appUtils.SearchByCustomerID(AdvanceRepaymentTestData.TC183_CustomerNo, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(4000);

		//Step 5: Click on customer adjustment button at the billing screen
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6: Click on normal advance adjustment button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(4000);

		//Step 7: Select any one of the normal advance
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),
				UtilityTestData.Terminal);		 
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		Thread.sleep(13000);
		String AdjustAmount=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Adjustment amount"));
		System.out.println("AdjustAmount"+AdjustAmount);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Adjustment amount"), Double.toString((Double.parseDouble(AdjustAmount))/2));

		//Step 8: Click on "is refund" checkbox
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_TextField("stretch","checkbox"));

		//Step 9: Click on add to cart button
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		//Step 10: Click on the Amount
		String ExpectedAmount = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		System.out.println("ExpectedAmount :"+ExpectedAmount);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		double ExpectedAdvanceAmount = Double.parseDouble(ExpectedAmount.replaceAll("[^\\d.]", "").trim());
		System.out.println("ExpectedAdvanceAmount :"+ExpectedAdvanceAmount);

		//Step 11: Click on payment method as RTGS/NEFT
		//Expected: Showing validation message Partial adjustment can not be refund !
		base.buttonClick(LoginPageObj.Edt_AlertText("RTGS/NEFT"));
		Thread.sleep(2000);
		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("Partial adjustment can not be refund")),"Partial adjustment can not be refund ! Warning Message not Found");
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	


	}
}
