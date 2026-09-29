package testPage;

import utilPack.BasePge;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import testData.CommonData;
import testData.InvoiceWithPaymentMethodB2BVoucher_TestData;
import testData.NormalSalesReturnGoldSilverReturnSaleCounter_TestData;
import testData.NormalSalesReturnDiamondPlatinumReturnSaleCounter_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities.ReturnDetails;

public class InvoiceWithPaymentMethodB2BVoucher extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	PdfUtilities pdfUtils;
	ErpUtilities erpUtils;
	MathUtilities mathUtils;

	Login login = new Login(driver); 
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_TestData NormalSalesReturnGoldSilverReturnSaleCounterTestData = new NormalSalesReturnGoldSilverReturnSaleCounter_TestData();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	NormalSalesReturnDiamondPlatinumReturnSaleCounter_TestData NormalSalesReturndiamondPlatinumReturnSaleCounterTestData= new NormalSalesReturnDiamondPlatinumReturnSaleCounter_TestData();
	InvoiceWithPaymentMethodB2BVoucher_TestData InvoiceWithPaymentMethodB2BVoucherTestData=new InvoiceWithPaymentMethodB2BVoucher_TestData();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj=new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();

	public InvoiceWithPaymentMethodB2BVoucher(WebDriver driver) {
		super(driver);
		base     = new BasePge(driver);
		appUtils = new AppUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
		mathUtils = new MathUtilities(base);
	}

	// <summary>
	// Test Case Title : B2B voucher with multiple category items
	// Automation ID : TC201
	// Author : Sangeetha M S
	// </summary>
	public void TC201_InvoiceWithPaymentMethodB2BVoucher() throws Exception {

		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 70);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer
		//Step 4.Click on add to estimate button
		appUtils.SearchByCustomerID(InvoiceWithPaymentMethodB2BVoucherTestData.TC201_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 5. Scan multiple category SKUs and click on Add to cart button
		//Step 6. Select any one of the sales agent
		List<String> SkuList = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ExpectedItemNames = new ArrayList<>();
		List<String> TotalForEachItems = new ArrayList<>();
		List<String> ItemQuantity=new ArrayList<>();
		double DiamondValue=0.00;

		// Diamond SKUs
		List<String> DiamondSkus = appUtils.SelectMultipleSKUs(
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_SkuGoldCount,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_TransferType,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_FromCounter4,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_MetalType);
		SkuList.addAll(DiamondSkus);
		appUtils.HamBurgerButtonClick("iconShop"); 

		// Gold SKUs
		List<String> GoldSkus = appUtils.SelectMultipleSKUs(
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_SkuGoldCount,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_TransferType,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_FromCounter,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_MetalType);
		SkuList.addAll(GoldSkus);
		appUtils.HamBurgerButtonClick("iconShop"); 

		/*
		// Precia SKUs
		List<String> PreciaSkus = appUtils.SelectMultipleSKUs(
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_SkuGoldCount,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_TransferType,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_FromCounter3,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_MetalType);
		SkuList.addAll(PreciaSkus);
		appUtils.HamBurgerButtonClick("iconShop"); 

		// Uncut SKUs
        //List<String> UncutSkus = Arrays.asList("570000672676");
		List<String> UncutSkus =
		appUtils.SelectMultipleSKUs(
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_SkuGoldCount,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_TransferType,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_FromCounter2,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC201_MetalType);
		SkuList.addAll(UncutSkus);
		appUtils.HamBurgerButtonClick("iconShop"); */

		for (int i = 0; i < SkuList.size(); i++) 
		{    		
			appUtils.SKUIngridentTableValues(SkuList.get(i), i + 1,ScannedSkuDataMap);
			ExpectedItemNames.add(ScannedSkuDataMap.get("SKU_" + (i+1) + "_HeaderName"));
			TotalForEachItems.add(ScannedSkuDataMap.get("SKU_" + (i+1) + "_Total"));
			ItemQuantity.add(ScannedSkuDataMap.get("SKU_" + (i+1) + "_GrossWeight"));
			DiamondValue=DiamondValue+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + (i+1) + "_CvalueDIAMOND"));
		}

		Map<String, String> TransactionPageItems = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionPageItems.get("Subtotal");
		String Discount = TransactionPageItems.get("Discount");
		String TotalGrossWeight = TransactionPageItems.get("GrossWeight");
		String TotalNetWeight = TransactionPageItems.get("NetWeight");
		String Tax = TransactionPageItems.get("Tax");
		String TotalAmount = TransactionPageItems.get("TotalAmount");
		String NetTotal = TransactionPageItems.get("NetTotal");
		String LinesCount = TransactionPageItems.get("LinesCount");
		mathUtils.ValidateTransactionLineCalculation(TransactionPageItems, ScannedSkuDataMap);

		//Step 7. Click on Save to estimation button
		//Step 8. save estimate
		//Expected : Verify values in estimate print
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(2000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC201_ProformaInvoice");
		String ProFormaInvoicePath =System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
		String Gst= Tax;
		String TaxableValue=SubTotal;
		String InvoiceTotal=TotalAmount;

		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step 9.Recall estimate from cashier screen
		//Step 10. Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Step 11.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithPaymentMethodB2BVoucherTestData.TC201_TransactionType);

		//Step 12.Click on Process Estimation 
		//Step 13.Choose a sales representative(Not available after Data refresh)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));  

		//Step 14.Click on the Amount
		//Expected : Cashier Screen after recall *No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax)
		//             *Displayed Subtotal,*TAX, *Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);	
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		//Step 15.Select a Payment method as online gift voucher
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Online Gift Voucher"));

		//Step 16.Enter the voucher id and PIN
		//Expected : Validate the PIN number
		String CardCode="";
		String Pin ="";
		String CardBalanceAmount="";

		// Flag to track if a valid B2B voucher is found
		boolean ValidB2BVoucher = false;
		// Counter to limit the vouchers
		int count = 0;
		
		for (String CardId : UtilityTestData.B2BVoucherMap.keySet()) 
		{
			// Stop after checking 5 vouchers
		    if (count >= 5) {
		        break;
		    }
		    count++;
			CardCode = UtilityTestData.B2BVoucherMap.get(CardId).get("CardCode");
			Pin = UtilityTestData.B2BVoucherMap.get(CardId).get("Pin");
			System.out.println("Card ID: " + CardId);
			System.out.println("Card Code: " + CardCode);
			System.out.println("PIN: " + Pin);
			Thread.sleep(1000);
			base.clearData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"));
			base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"),CardCode);
			base.pressKey(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"),"ENTER");
			Thread.sleep(1000);
			if (base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_AlertText("Insufficient balance !"),30)|| base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_AlertText("Gift Card status is Used"),30)) 
			{
				System.out.println("Card failed : " + CardId);
				base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
				continue;
			} 
			else if(base.WaitforElementToBePresentWithTimeout(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: CardBalance"),50))
			{
				System.out.println("Valid card found: " + CardId);
				ValidB2BVoucher = true;  // Set flag to true when a valid card is found
				break;
			}
			else {
				System.out.println("Unknown error for Card: " + CardId);
				base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
				continue;
			}
		}
		// Check after the loop if no valid voucher was found
		if (!ValidB2BVoucher) {
			throw new RuntimeException("All B2B vouchers have been used. No B2B Voucher available.");
		}
		Thread.sleep(1000);
		CardBalanceAmount=base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: CardBalance"));
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherPin, valueUpdate"),Pin);  
		String ActualPinNo=base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherPin, valueUpdate"));

		asrt.assertEquals(ActualPinNo,Pin,"Mismatch in Pin Numbers entered Expected "+Pin+" but got "+ActualPinNo+" in Pay gift card pop up in Transaction page");

		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		double FinalTotal=Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "")) - Double.parseDouble(CardBalanceAmount.replaceAll("[^\\d.]", ""));
		System.out.println("FinalTotal  : " + FinalTotal);

		//Step 17.Click on the Amount     
		Thread.sleep(3000);
		String PaymentAmountHDFC = appUtils.PaymentAfterRecallEstimateVoucher("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 18.Select a Payment method(Card)
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC201_SaleInvoice");
		Thread.sleep(2000);
		String SaleInvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotal;
		String TotalDiscount=Discount;
		String TotalQtyPcs=LinesCount;
		String NetValue=PaymentAmountHDFC;
		String TotalDiamond              = Double.toString(DiamondValue);
		System.out.println("TotalDiamond"+TotalDiamond);

		//Step 19. Post the invoice
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SaleInvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalDiamond,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmountHDFC,
				CardBalanceAmount,
				SkuList,
				ScannedSkuDataMap);

		System.out.println("PDF INVOICE NO:-"+PdfInvoiceNo);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 
	}

	// <summary>
	// Test Case Title : B2B voucher with single gold item
	// Automation ID : TC199
	// Author : Benson
	// </summary>
	public void TC199_InvoiceWithPaymentMethodB2BVoucher() throws Exception {

		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 70);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer
		//Step 4.Click on add to estimate button
		appUtils.SearchByCustomerID(InvoiceWithPaymentMethodB2BVoucherTestData.TC199_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 5. Scan a gold SKU and click on Add to cart button
		//Step 6. Select any one of the sales agent
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				InvoiceWithPaymentMethodB2BVoucherTestData.TC199_SkuGoldCount,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC199_TransferType,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC199_FromCounter,
				InvoiceWithPaymentMethodB2BVoucherTestData.TC199_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//TranscationPageVerifyLines
		//In billing screen,Note:*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
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

		//Step 7. Click on Save to estimation button
		//Step 8. save estimate
		//Expected : Verify values in estimate print
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(2000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC199_ProformaInvoice");
		String ProFormaInvoicePath =System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
		String Gst= Tax;
		String TaxableValue=SubTotal;
		String InvoiceTotal=TotalAmount;
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight));

		//Step 9.Recall estimate from cashier screen
		//Step 10. Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Step 11.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithPaymentMethodB2BVoucherTestData.TC199_TransactionType); 

		//Step 12.Click on Process Estimation 
		//Step 13.Choose a sales representative(Not available after Data refresh)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));  

		//Step 14.Click on the Amount
		//Expected : Cashier Screen after recall *No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax)
		//*Displayed Subtotal,*TAX, *Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);	
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		//Step 15.Select a Payment method as online gift voucher
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Online Gift Voucher"));

		//Step 16.Enter the voucher id and PIN
		//Expected : Validate the PIN number
		String CardCode="";
		String Pin ="";
		String CardBalanceAmount="";

		// Flag to track if a valid B2B voucher is found
		boolean ValidB2BVoucher = false;
		
		// Counter to limit the vouchers
		int count = 0;
		
		for (String CardId : UtilityTestData.B2BVoucherMap.keySet()) 
		{
			// Stop after checking 5 vouchers
		    if (count >= 5) {
		        break;
		    }
		    count++;
			CardCode = UtilityTestData.B2BVoucherMap.get(CardId).get("CardCode");
			Pin = UtilityTestData.B2BVoucherMap.get(CardId).get("Pin");
			System.out.println("Card ID: " + CardId);
			System.out.println("Card Code: " + CardCode);
			System.out.println("PIN: " + Pin);
			Thread.sleep(1000);
			base.clearData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"));
			base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"),CardCode);
			base.pressKey(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"),"ENTER");
			Thread.sleep(1000);
			if (base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_AlertText("Insufficient balance !"),30)|| base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_AlertText("Gift Card status is Used"),30)) 
			{
				System.out.println("Card failed : " + CardId);
				base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
				continue;
			} 
			else if(base.WaitforElementToBePresentWithTimeout(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: CardBalance"),50))
			{
				System.out.println("Valid card found: " + CardId);
				ValidB2BVoucher = true;  // Set flag to true when a valid card is found
				break;
			}
			else {
				System.out.println("Unknown error for Card: " + CardId);
				base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
				continue;
			}
		}
		// Check after the loop if no valid voucher was found
		if (!ValidB2BVoucher) {
			throw new RuntimeException("All B2B vouchers have been used. No B2B Voucher available.");
		}
		Thread.sleep(1000);
		CardBalanceAmount=base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: CardBalance"));
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherPin, valueUpdate"),Pin);  
		String ActualPinNo=base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherPin, valueUpdate"));

		asrt.assertEquals(ActualPinNo,Pin,"Mismatch in Pin Numbers entered Expected "+Pin+" but got "+ActualPinNo+" in Pay gift card pop up in Transaction page");

		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		double FinalTotal=Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "")) - Double.parseDouble(CardBalanceAmount.replaceAll("[^\\d.]", ""));
		System.out.println("FinalTotal  : " + FinalTotal);

		//Step 17.Click on the Amount     
		Thread.sleep(3000);
		String PaymentAmountHDFC = appUtils.PaymentAfterRecallEstimateVoucher("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 18.Select a Payment method(Card)
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC199_SaleInvoice");
		Thread.sleep(2000);
		String SaleInvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotal;
		String TotalDiscount=Discount;
		String TotalQtyPcs=LinesCount;
		String NetValue = String.valueOf(Double.parseDouble(PaymentAmountHDFC) + Double.parseDouble(CardBalanceAmount));

		//Step 19. Post the invoice
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				SaleInvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				"",
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmountHDFC,
				CardBalanceAmount,
				SkuList,
				ScannedSkuDataMap);

		System.out.println("PDF INVOICE NO:-"+PdfInvoiceNo);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
			// Test Case Title : B2B voucher with multiple gold item
			// Automation ID : TC200
			// Author : Vishnu RR
			// </summary>
			public void TC200_InvoiceWithPaymentMethodB2BVoucher() throws Exception {

				//Step 1.Login to POS
				login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
				base.setZoom(driver, 70);

				//Step 2. Click on Transaction button
				appUtils.HamBurgerButtonClick("iconShop");

				//Step 3. Select customer
				//Step 4.Click on add to estimate button
				appUtils.SearchByCustomerID(InvoiceWithPaymentMethodB2BVoucherTestData.TC200_CustomerID, UtilityTestData.MenuBarOptions[5]);

				//Step 5. Scan a gold SKU and click on Add to cart button
				//Step 6. Select any one of the sales agent
				List<String> SkuList = appUtils.SelectMultipleSKUs(
						InvoiceWithPaymentMethodB2BVoucherTestData.TC200_SkuGoldCount,
						InvoiceWithPaymentMethodB2BVoucherTestData.TC200_TransferType,
						InvoiceWithPaymentMethodB2BVoucherTestData.TC200_FromCounter,
						InvoiceWithPaymentMethodB2BVoucherTestData.TC200_MetalType);
				appUtils.HamBurgerButtonClick("iconShop");
				Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
				for (int i = 0; i < SkuList.size(); i++) {
					int SkuNumber = i + 1;
					String Sku = SkuList.get(i);
					appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
					Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
					mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
				}

				//TranscationPageVerifyLines
				//In billing screen,Note:*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
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

				//Step 7. Click on Save to estimation button
				//Step 8. save estimate
				//Expected : Verify values in estimate print
				base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
				base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
				String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
				Thread.sleep(2000);
				String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC200_ProformaInvoice");
				String ProFormaInvoicePath =System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
				String Gst= Tax;
				String TaxableValue=SubTotal;
				String InvoiceTotal=TotalAmount;
				pdfUtils.ProFormaInvoicePDFVerifyNormal(
						ProFormaInvoicePath,
						TaxableValue,
						Gst,
						InvoiceTotal,
						NetTotal,
						LinesCount,
						Double.parseDouble(TotalGrossWeight),
						Double.parseDouble(TotalNetWeight));

				//Step 9.Recall estimate from cashier screen
				//Step 10. Select estimate and click on recall estimation
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
				base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				Thread.sleep(2000);
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
				Thread.sleep(2000);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

				//Step 11.Select the Transaction Type as Sales 
				base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithPaymentMethodB2BVoucherTestData.TC200_TransactionType); 

				//Step 12.Click on Process Estimation 
				//Step 13.Choose a sales representative(Not available after Data refresh)
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));  

				//Step 14.Click on the Amount
				//Expected : Cashier Screen after recall *No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax)
				//*Displayed Subtotal,*TAX, *Total Amount
				Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);	
				mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

				//Step 15.Select a Payment method as online gift voucher
				Thread.sleep(5000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Online Gift Voucher"));

				//Step 16.Enter the voucher id and PIN
				//Expected : Validate the PIN number
				String CardCode="";
				String Pin ="";
				String CardBalanceAmount="";

				// Flag to track if a valid B2B voucher is found
				boolean ValidB2BVoucher = false;
				// Counter to limit the vouchers
				int count = 0;
				
				for (String CardId : UtilityTestData.B2BVoucherMap.keySet()) 
				{
					// Stop after checking 5 vouchers
				    if (count >= 5) {
				        break;
				    }
				    count++;
					CardCode = UtilityTestData.B2BVoucherMap.get(CardId).get("CardCode");
					Pin = UtilityTestData.B2BVoucherMap.get(CardId).get("Pin");
					System.out.println("Card ID: " + CardId);
					System.out.println("Card Code: " + CardCode);
					System.out.println("PIN: " + Pin);
					Thread.sleep(1000);
					base.clearData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"));
					base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"),CardCode);
					base.pressKey(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherNo, valueUpdate"),"ENTER");
					Thread.sleep(1000);
					if (base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_AlertText("Insufficient balance !"),30)|| base.WaitforElementToBePresentWithTimeout(LoginPageObj.Edt_AlertText("Gift Card status is Used"),30)) 
					{
						System.out.println("Card failed : " + CardId);
						base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
						continue;
					} 
					else if(base.WaitforElementToBePresentWithTimeout(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: CardBalance"),50))
					{
						System.out.println("Valid card found: " + CardId);
						ValidB2BVoucher = true;  // Set flag to true when a valid card is found
						break;
					}
					else {
						System.out.println("Unknown error for Card: " + CardId);
						base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
						continue;
					}
				}
				// Check after the loop if no valid voucher was found
				if (!ValidB2BVoucher) {
					throw new RuntimeException("All B2B vouchers have been used. No B2B Voucher available.");
				}
				Thread.sleep(1000);
				CardBalanceAmount=base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: CardBalance"));
				base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherPin, valueUpdate"),Pin);  
				String ActualPinNo=base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("value: VoucherPin, valueUpdate"));

				asrt.assertEquals(ActualPinNo,Pin,"Mismatch in Pin Numbers entered Expected "+Pin+" but got "+ActualPinNo+" in Pay gift card pop up in Transaction page");

				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				double FinalTotal=Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "")) - Double.parseDouble(CardBalanceAmount.replaceAll("[^\\d.]", ""));
				System.out.println("FinalTotal  : " + FinalTotal);

				//Step 17.Click on the Amount     
				Thread.sleep(3000);
				String PaymentAmountHDFC = appUtils.PaymentAfterRecallEstimateVoucher("HDFC").replaceAll("[^\\d.]", "").trim();

				//Step 18.Select a Payment method(Card)
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC200_SaleInvoice");
				Thread.sleep(2000);
				String SaleInvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
				String GrossAmount = SubTotal;
				String TotalDiscount=Discount;
				String TotalQtyPcs=LinesCount;
				String NetValue = String.valueOf(Double.parseDouble(PaymentAmountHDFC) + Double.parseDouble(CardBalanceAmount));

				//Step 19. Post the invoice
				String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
						SaleInvoicePdfPath,
						GrossAmount,
						TotalDiscount,
						"",
						TotalQtyPcs,
						TotalNetWeight,
						TotalGrossWeight,
						TaxableValue,
						NetValue,
						PaymentAmountHDFC,
						CardBalanceAmount,
						SkuList,
						ScannedSkuDataMap);

				System.out.println("PDF INVOICE NO:-"+PdfInvoiceNo);
				pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
			}
	
}
