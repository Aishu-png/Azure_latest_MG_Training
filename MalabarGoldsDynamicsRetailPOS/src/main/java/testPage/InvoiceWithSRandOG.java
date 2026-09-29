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

import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.InvoiceWithSRandOG_TestData;
import testData.Utility_TestData;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities;
import utilPack.AppUtilities.ReturnDetails;

public class InvoiceWithSRandOG extends BasePge {

	AppUtilities appUtils;
	PdfUtilities pdfUtils;
	ErpUtilities erpUtils;
	BasePge base;
	Assert asrt;

	InvoiceWithSRandOG_TestData InvoiceWithSRandOGTestData = new InvoiceWithSRandOG_TestData();
	Utility_TestData UtilityTestData  = new Utility_TestData();
	LoginPage_Obj LoginPageObj = new  LoginPage_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj  OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();

	public InvoiceWithSRandOG(WebDriver driver){
		super(driver);
		base = new BasePge(driver);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
		appUtils = new AppUtilities(base);
	}

	// <summary>
	// Test Case Title : Invoice With SR and OG
	// Automation ID : TC291
	// Author: Bharath
	// </summary>
	public void TC291_InvoiceWithSRandOG() throws Exception {

		//Step 1: Login to POS
		//Step 2: Click on Transaction button
		//Pre-Requesites: Take the bill number (receipt number) that has previously been sold.
		Map<String, Object> InvoiceDetails = erpUtils.GetInvoiceDetailsAfterNormalSale(InvoiceWithSRandOGTestData.TC291_CustomerNo, InvoiceWithSRandOGTestData.TC291_Products);
		String ActualCustomerName = pdfUtils.ExtractCustomerNameFromSaleInvoice();

		String InvoiceNo            = (String) InvoiceDetails.get("InvoiceNo");
		String SkuPurityReturn      = (String) InvoiceDetails.get("FirstSkuPurity");
		String SkuGoldRateReturn    = (String) InvoiceDetails.get("FirstSkuGoldRate");
		String TotalRgWeightReturn  = (String) InvoiceDetails.get("TotalRgWeight");

		// Extract SKU-wise map
		Map<String, String> SkuWiseData = (Map<String, String>) InvoiceDetails.get("SKUWiseDetails");

		//  Print only SKU__NetWeight and SKU__GrossWeight
		String SkuNetWeight   = SkuWiseData.getOrDefault("SKU_1_NetWeight", "N/A");
		String SkuGrossWeight = SkuWiseData.getOrDefault("SKU_1_GrossWeight", "N/A");
		String SkuStoneWeight = SkuWiseData.getOrDefault("SKU_1_StoneWeight", "N/A");
		String SkuTotal	   = SkuWiseData.getOrDefault("SKU_1_Total", "N/A");
		System.out.println(SkuTotal);

		String ErpGoldRateFor24K= erpUtils.SetMetalRateInERP(SkuGoldRateReturn, SkuPurityReturn);
		//Thread.sleep(600000);
		TimeUnit.MINUTES.sleep(6);


		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(InvoiceWithSRandOGTestData.TC291_CustomerNo, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		//Step 6: Click on return transaction button at the billing screen
		//Step 7: Enter receipt number
		//Step 8: Select return product ( multiple gold )and click on return button
		//Expected: The product meant to be returned should be previously bought by the same customer.
		ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, InvoiceWithSRandOGTestData.TC291_ProductsToReturn);
		asrt.assertEquals(InvoiceWithSRandOGTestData.TC291_CustomerName.toUpperCase().trim(),ActualCustomerName.toUpperCase().trim(),"Customer name for sale is not the same as the customer name from return. " +"ExpectedCustomerNameFrom MethodInvoicePdf: " + ActualCustomerName + ", but found: " + InvoiceWithSRandOGTestData.TC291_CustomerName);

		List<String> ReturnProductNames        = Details.productNames;
		List<String> ReturnProductQtyList      = Details.productQty;
		List<String> ReturnProductTotalList    = Details.productPrice;

		//Step 9: Scan a SKU and add to cart 
		//Note: Check calculation and No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Subtotal,TAX and Total Amount
		//Step 10: Check average rate
		List<String> SaleProductNames = new ArrayList<>();
		List<String> SaleProductQtyList = new ArrayList<>();
		List<String> SaleProductTotalList = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter = 1;

		List<String> SkuList = appUtils.SelectMultipleSKUs(
				InvoiceWithSRandOGTestData.TC291_SaleProductCount,
				InvoiceWithSRandOGTestData.TC291_TransferType,
				InvoiceWithSRandOGTestData.TC291_FromCounter,
				InvoiceWithSRandOGTestData.TC291_MetalType);
		appUtils.HamBurgerButtonClick("iconShop"); 
		String ErpGoldRate24k = ErpGoldRateFor24K;
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			SaleProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			SaleProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			SaleProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,ErpGoldRate24k);
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String Discount 			= TransactionDataLine.get("Discount");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.addAll(ReturnProductNames);
		ProductNames.addAll(SaleProductNames);
		ProductQtyList.addAll(ReturnProductQtyList);
		ProductQtyList.addAll(SaleProductQtyList);
		ProductTotalList.addAll(ReturnProductTotalList); 
		ProductTotalList.addAll(SaleProductTotalList);

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==1) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
			}
			else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
			}

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replace("\u20B9", "").replace(",", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replace("\u20B9", "").replace(",", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")), Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge.replace("-", "")),1.00,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("\u20B9", "").replace(",", "").trim();
		asrt.assertEquals(ActualSubTotalInBillingPge, ExpectedSubTotalInBillingPge,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replace("\u20B9", "").replace(",", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = SubTotalSum*0.03;
		//String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ActualTax, ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replace("\u20B9", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		//Step 11: Select OG
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),InvoiceWithSRandOGTestData.TC291_OldProduct);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));
		Thread.sleep(2000);

		//Step 12:Click on OG own and Choose Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));	

		//Step 13: Select configuration
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),InvoiceWithSRandOGTestData.TC291_Configuration);

		//Step 14: Click on Add item button
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(10000);
		List<WebElement>ErrorMessage = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));
		if (!ErrorMessage.isEmpty()) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
		}

		//Step 15: Select QC person
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);

		//Step 16: Select Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);

		//Step 17: Enter piece value
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), InvoiceWithSRandOGTestData.TC291_Pieces);

		//Step 18: Enter gross weight
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), InvoiceWithSRandOGTestData.TC291_GrossWeight);

		//Step 19 Click on calculate button
		//Step 20: Click on Add to cart button
		//Expected: Check Old Gold Calculation
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(4000);
		double ActualCurrentGoldRate22kInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
		double CurrentERPGoldRate24k = Double.parseDouble(ErpGoldRateFor24K);
		double ExpectedCurrentGoldRate22kFromERP = (CurrentERPGoldRate24k*22)/24;
		asrt.assertEquals(ActualCurrentGoldRate22kInItemDetailsPge, ExpectedCurrentGoldRate22kFromERP,1.0,"Gold Rate mismatch: Expected is "+ExpectedCurrentGoldRate22kFromERP+" but found "+ActualCurrentGoldRate22kInItemDetailsPge+"in item details page");

		String ExpectedNetWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
		double NetWeightInItemDetailsPge = Double.parseDouble(ExpectedNetWeightInItemDetailsPge);
		double ExpectedGoldAmountInItemDetailsPge = Math.round(NetWeightInItemDetailsPge * ActualCurrentGoldRate22kInItemDetailsPge * 100.0) / 100.0;
		String GoldAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt"));
		double ActualGoldAmountInItemDetailsPge = Double.parseDouble(GoldAmountInItemDetailsPge);
		asrt.assertEquals(ActualGoldAmountInItemDetailsPge, ExpectedGoldAmountInItemDetailsPge,1.0,"GoldAmount mismatch: "+ExpectedGoldAmountInItemDetailsPge+" but got "+ActualGoldAmountInItemDetailsPge+" in item details page");

		String ExpectedGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		String TotalAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));
		double ActualTotalAmountInItemDetailsPge = Double.parseDouble(TotalAmountInItemDetailsPge);
		double ExpectedTotalAmountInItemDetailsPge = ActualGoldAmountInItemDetailsPge;
		asrt.assertEquals(ActualTotalAmountInItemDetailsPge, ExpectedTotalAmountInItemDetailsPge,1.0, "Total amount mismatch: Expected is "+ExpectedTotalAmountInItemDetailsPge+" but got "+ActualTotalAmountInItemDetailsPge+" in item details page");

		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		if(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Cart has changed"))) {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}

		Map<String, String> BillingPageItems = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String BillingPageSubTotal		 		= BillingPageItems.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String BillingPageTax 					= BillingPageItems.get("Tax").replaceAll("[^\\d.-]", "").trim();
		String BillingPageTotalAmount 			= BillingPageItems.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String BillingPageActualLinesCount		= BillingPageItems.get("LinesCount");
		List<String> BillingPageItemNames = new ArrayList<String>();
		List<String> BillingPageItemQtyList = new ArrayList<String>();


		List<WebElement> BillingProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		for(int i=1;i<=BillingProductRows.size();i++)
		{
			BillingPageItemNames.add(BillingPageItems.get("Item"+i+"Name"));
			BillingPageItemQtyList.add(BillingPageItems.get("Item"+i+"Quantity").replace("-",""));
		}


		//Step 21:  Click on Save to estimation button
		//Step 22:  Save estimate
		//Expected: Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		double SubTotalInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)-ActualTotalAmountInItemDetailsPge;
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC291_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = ActualTaxInBillingPge.replace("-", "");
		String TaxableValue		   = ActualSubTotalInBillingPge.replace("-", "");
		String InvoiceTotal        = String.format("%.2f",SubTotalInBillingPge+ActualTax).replace("-", "");
		String TotalAmnt           = String.format("%.2f",SubTotalInBillingPge+ActualTax).replace("-", "");
		String NetTotal            = String.format("%.2f",SubTotalInBillingPge+ActualTax).replace("-", "");
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmnt
				);

		//Step 23:Recall estimate from cashier screen
		//Step 24: Select estimate and click on Recall estimation button
		// Expected Result: Verify whether the recalled item is the same as the item in the cart.(Estimation No Check)
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ReturnEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ReturnEstmnNumber, ActualEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ReturnEstmnNumber+"");
		appUtils.RecallEstimateItemValidation(BillingPageItemNames, BillingPageItemQtyList, BillingPageItemNames, BillingPageItemQtyList, Double.parseDouble(BillingPageSubTotal), Double.parseDouble(BillingPageTax), Double.parseDouble(BillingPageTotalAmount), Integer.parseInt(BillingPageActualLinesCount), "in Recall page");

		//Step 25: Select the Transaction Type as Sales return
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithSRandOGTestData.TC291_TransactionTypeReturn);

		//Step 26:Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 27: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 28: Click on the Amount
		//Step 29: Post sale return invoice
		//Expected: Verify Return Invoice details
		String AmountDueForSaleReturnFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleReturnInFromBillingPge = Double.parseDouble(AmountDueForSaleReturnFromBillingPge);
		String ExpectedAmountForSaleReturnInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		double TaxForSaleReturnProducts = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("\u20B9", "").replace(",", "").replace("-", ""));
		if (AmountDueForSaleReturnInFromBillingPge<0.00) {
			appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
					UtilityTestData.SalePersonNumber,
					UtilityTestData.DueYear,
					UtilityTestData.DobYear,
					UtilityTestData.NomineeName,
					UtilityTestData.NomineeRelation);

			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");

			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(InvoiceWithSRandOG_TestData.TC291_PaymentMode, ActualPaymentMode,"Payment mode mismatch: Expected is "+InvoiceWithSRandOG_TestData.TC291_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			Map<String, String> CreditNoteDetails = pdfUtils.SalesReturnPDFCredit();
			String ActualGrossWt = CreditNoteDetails.get("Product1.GrossWt");
			String ActualStoneWt = CreditNoteDetails.get("Product1.StoneWt");
			String ActualNetWt = CreditNoteDetails.get("Product1.NetWt");
			String ActualAmount = CreditNoteDetails.get("Product1.Amount");
			String ActualTaxableValue   = CreditNoteDetails.get("TaxableValue");
			String ActualTotalNetValue  = CreditNoteDetails.get("TotalNetValue");
			String ActualPaymentDetails = CreditNoteDetails.get("PaymentDetails");
			double ExpectedTaxableValue = Double.parseDouble(SkuTotal);
			double TotalAmountInBillingPge = ExpectedTaxableValue+TaxForSaleReturnProducts;

			asrt.assertEquals(SkuGrossWeight, ActualGrossWt,"Gross Weight mismatch: Expected is "+SkuGrossWeight+" but found "+ActualGrossWt+" in credit note");
			asrt.assertEquals(SkuStoneWeight, ActualStoneWt,"StoneWt mismatch: Expected is"+SkuStoneWeight+" but found "+ActualStoneWt+" in credit note");
			asrt.assertEquals(SkuNetWeight, ActualNetWt,"NetWt mismatch: Expected is"+SkuNetWeight+" but found "+ActualNetWt+" in credit note");
			asrt.assertEquals(Double.parseDouble(SkuTotal), Double.parseDouble(ActualAmount),1.0,"Amount mismatch: Expected is"+SkuTotal+" but found "+ActualAmount+" in credit note");
			asrt.assertEquals(ExpectedTaxableValue, Double.parseDouble(ActualTaxableValue),1.0,"Taxable Value mismatch: Expected is"+ExpectedTaxableValue+" but found "+ActualTaxableValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualTotalNetValue),1.0,"Total Net Value mismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualTotalNetValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualPaymentDetails),1.0,"Payment Detailsmismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualPaymentDetails+" in credit note");
		}

		//Step 30: Again recall estimate and select transaction type as exchange
		//Step 31: Select estimate and click on recall estimation
		//Expected: Verify whether the recalled item is the same as the item in the cart. 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));				
		Thread.sleep(5000);

		BillingPageItemNames.remove(0);
		String ExchangeEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		List<String> ItemNamesFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME")).stream()
				.map(WebElement::getText).map(String::trim)
				.collect(Collectors.toList());

		Assert.assertEquals(ExchangeEstmnNumber, ActualEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ReturnEstmnNumber+"in recall page");
		Assert.assertEquals(ItemNamesFromRecall, BillingPageItemNames,"Mismatch in Item Names Expected:"+BillingPageItemNames+"but got"+ItemNamesFromRecall+"in recall page");

		//Step 32: Select the Transaction Type as Exchange
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithSRandOG_TestData.TC291_TransactionTypeExchange);

		//Step 33: Click on process estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 34: Choose a sales representative
		//Step 35: Click on the Amount
		//Step 36: Post the sales return Invoice
		//Expected: Check Return Invoice details
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		String AmountDueForExchangeFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForExchangeInFromBillingPge = Double.parseDouble(AmountDueForExchangeFromBillingPge);			
		String ExpectedAmountForExchangeInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		if (AmountDueForExchangeInFromBillingPge<0.00) {
			appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
					UtilityTestData.SalePersonNumber,
					UtilityTestData.DueYear,
					UtilityTestData.DobYear,
					UtilityTestData.NomineeName,
					UtilityTestData.NomineeRelation);

			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");

			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(InvoiceWithSRandOG_TestData.TC291_PaymentMode, ActualPaymentMode,"Payment Mode mismatch: Expected is "+InvoiceWithSRandOG_TestData.TC291_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			double GrossWeight = Double.parseDouble(ExpectedGrossWeightInItemDetailsPge);
			String ExpectedGrossWeightFromBillingPge = String.format("%.3f", GrossWeight);  
			String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f",ActualCurrentGoldRate22kInItemDetailsPge);
			double TotalAmountForOG = Double.parseDouble(TotalAmountInItemDetailsPge);
			String ExpectedTotalAmountForOG = String.format("%.2f", TotalAmountForOG);  			
			Map<String, String> OgPurchaseBill = pdfUtils.OGPdfValidation("Adjustment");
			String GrossWeightFromInvoice = OgPurchaseBill.get("GrossWt1");
			String NetWeightFromInvoice = OgPurchaseBill.get("NetWt1");
			String GoldRateFromInvoice = OgPurchaseBill.get("Rate1");
			String TotalAmountFromInvoice = OgPurchaseBill.get("TotalAmount1");
			String PaymentDetailsFromInvoice = OgPurchaseBill.get("PaymentDetails");

			asrt.assertEquals(ExpectedGrossWeightFromBillingPge, GrossWeightFromInvoice,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+GrossWeightFromInvoice+" in purchase bill");		
			asrt.assertEquals(ExpectedNetWeightInItemDetailsPge, NetWeightFromInvoice,"Net Weight mismatch: "+ExpectedNetWeightInItemDetailsPge+" but got "+NetWeightFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedOGPurchaseRateFromItemDetailsPge, GoldRateFromInvoice,"Gold rate mismatch: "+ExpectedOGPurchaseRateFromItemDetailsPge+" but got "+ GoldRateFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, TotalAmountFromInvoice,"Total amount mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+TotalAmountFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, PaymentDetailsFromInvoice,"Payment details mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+PaymentDetailsFromInvoice+" in purchase bill");
		}

		//Step 38: Again Recall estimate from cashier screen
		//Step 39: Select estimate and click on recall estimation
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		BillingPageItemNames.remove(1);
		ItemNamesFromRecall.clear();
		String SalesEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		ItemNamesFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME")).stream()
				.map(WebElement::getText).map(String::trim)
				.collect(Collectors.toList());

		Assert.assertEquals(SalesEstmnNumber, ActualEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ReturnEstmnNumber+"in recall page");
		Assert.assertEquals(ItemNamesFromRecall, BillingPageItemNames,"Mismatch in Item Names Expected:"+BillingPageItemNames+"but got"+ItemNamesFromRecall+"in recall page");

		//Step 40: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithSRandOG_TestData.TC291_TransactionTypeSales);

		//Step 41: Click on process estimation button
		//Expected: Cashier Screen after recall Estimate
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		
		Map<String, String> TransactionRecallDataLineSale = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		erpUtils.ErpValidateTransactionLineCalculation(TransactionRecallDataLineSale, ScannedSkuDataMap);

		//Step 42: Choose a sales representative
		//Step 43: Click on the Amount
		//Step 44: Select a Payment method(Cash or Card)
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		String GrossAmountForSale= base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"));
		String TaxableValueForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"));
		String TotalValueForSale=base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		String TotalNetValueForSale=base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		String TotalQtyPcsForSale = String.valueOf(SkuList.size());
		String TotalDiscountForSale = Discount;
		String TotalNetWeightForSale = TotalNetWeight;
		String TotalGrossWeightForSale= SaleProductQtyList.get(0);
		String AdjustmentForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("PaymentsField", "h4"));;
		String AmountDueForSaleFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleInFromBillingPge = Double.parseDouble(AmountDueForSaleFromBillingPge);
		String PaymentAmountForSale ="";
		if (AmountDueForSaleInFromBillingPge==0.00) {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			PaymentAmountForSale = "";
		}
		else {
			PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");
		}

		//Step 45:Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC291_TaxInvoice");		
		String TaxInvoice =System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String TotalDiamond = "";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				TaxInvoice,
				GrossAmountForSale,
				TotalDiscountForSale,
				TotalDiamond,
				TotalQtyPcsForSale,
				TotalNetWeightForSale,
				TotalGrossWeightForSale,
				TaxableValueForSale,
				TotalNetValueForSale,
				PaymentAmountForSale,
				AdjustmentForSale,
				SkuList,
				ScannedSkuDataMap
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
	
	// <summary>
	// Test Case Title :Invoice with multiple SR with multiple OG
	// Automation ID : TC292
	// Author: Nandagopan
	// </summary>
	public void TC292_InvoiceWithSRandOG() throws Exception {

		//Step 1: Login to POS
		//Step 2: Click on Transaction button
		//Pre-Requesites: Take the bill number (receipt number) that has previously been sold.
		Map<String, Object> InvoiceDetails = erpUtils.GetInvoiceDetailsAfterNormalSale(InvoiceWithSRandOGTestData.TC292_CustomerNo, InvoiceWithSRandOGTestData.TC292_Products);
		String ActualCustomerName = pdfUtils.ExtractCustomerNameFromSaleInvoice();
		String InvoiceNo            = (String) InvoiceDetails.get("InvoiceNo");
		String TotalNetWeightReturn = (String) InvoiceDetails.get("TotalNetWeight");
		String SkuPurityReturn      = (String) InvoiceDetails.get("FirstSkuPurity");
		String SkuGoldRateReturn    = (String) InvoiceDetails.get("FirstSkuGoldRate");
		String TotalRgWeightReturn  = (String) InvoiceDetails.get("TotalRgWeight");

		// Extract SKU-wise map
		Map<String, String> SkuWiseData = (Map<String, String>) InvoiceDetails.get("SKUWiseDetails");

		//  Print only SKU__NetWeight and SKU__GrossWeight
		// SKU 1
		String Sku1NetWeight   = SkuWiseData.getOrDefault("SKU_1_NetWeight", "N/A");
		String Sku1GrossWeight = SkuWiseData.getOrDefault("SKU_1_GrossWeight", "N/A");
		String Sku1StoneWeight = SkuWiseData.getOrDefault("SKU_1_StoneWeight", "N/A");
		String Sku1Total	   = SkuWiseData.getOrDefault("SKU_1_Total", "N/A");
		System.out.println(Sku1Total);

		// SKU 2
		String Sku2NetWeight   = SkuWiseData.getOrDefault("SKU_2_NetWeight", "N/A");
		String Sku2GrossWeight = SkuWiseData.getOrDefault("SKU_2_GrossWeight", "N/A");
		String Sku2StoneWeight = SkuWiseData.getOrDefault("SKU_2_StoneWeight", "N/A");
		String Sku2Total       = SkuWiseData.getOrDefault("SKU_2_Total", "N/A");

		System.out.println("SKU1 Total: " + Sku1Total);
		System.out.println("SKU2 Total: " + Sku2Total);
		String TotalSku =Double.toString(Double.parseDouble(Sku1Total)+Double.parseDouble(Sku2Total));
		String TotalNetWgt= Double.toString(Double.parseDouble(Sku1NetWeight)+Double.parseDouble(Sku2NetWeight));
		String TotalGrossWgt= Double.toString(Double.parseDouble(Sku1GrossWeight)+Double.parseDouble(Sku2GrossWeight));
		String TotalStoneWgt= Double.toString(Double.parseDouble(Sku1StoneWeight)+Double.parseDouble(Sku2StoneWeight));
		Map<String, String> ScannedSkuDataMapSale = (Map<String, String>) InvoiceDetails.get("ScannedSKUDataMap");

		String ErpGoldRateFor24K= erpUtils.SetMetalRateInERP(SkuGoldRateReturn, SkuPurityReturn);
		double Current22kRate = (Double.parseDouble(ErpGoldRateFor24K) * 22) / 24;
		TimeUnit.MINUTES.sleep(6);


		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(InvoiceWithSRandOGTestData.TC292_CustomerNo, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		//Step 6: Click on return transaction button at the billing screen
		//Step 7: Enter receipt number
		//Step 8: Select return product ( multiple gold )and click on return button
		//Expected: The product meant to be returned should be previously bought by the same customer.
		ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, InvoiceWithSRandOGTestData.TC292_ProductsToReturn);
		asrt.assertEquals(InvoiceWithSRandOGTestData.TC292_CustomerName.toUpperCase().trim(),ActualCustomerName.toUpperCase().trim(),"Customer name for sale is not the same as the customer name from return. " +"ExpectedCustomerNameFrom MethodInvoicePdf: " + ActualCustomerName + ", but found: " + InvoiceWithSRandOGTestData.TC292_CustomerName);

		List<String> ReturnProductNames        = Details.productNames;
		List<String> ReturnProductQtyList      = Details.productQty;
		List<String> ReturnProductTotalList    = Details.productPrice;

		//Step 9: Scan a SKU and add to cart 
		//Step10: Select estimate and click on add to sale button
		//Expected: Check calculation and No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Subtotal,TAX and Total Amount
		//Note: Check average rate
		List<String> SaleProductNames = new ArrayList<>();
		List<String> SaleProductQtyList = new ArrayList<>();
		List<String> SaleProductTotalList = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		String ExpectedGrossWeightInItemDetailsPge="";
		String TotalAmountInItemDetailsPge ="";
		double ExpectedTotalOgGwgt = 0.00;
		double ExpectedTotaOgAmt = 0.00;
		int SkuCounter = 1;
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				InvoiceWithSRandOGTestData.TC292_SaleProductCount,
				InvoiceWithSRandOGTestData.TC292_TransferType,
				InvoiceWithSRandOGTestData.TC292_FromCounter,
				InvoiceWithSRandOGTestData.TC292_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();

		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			SaleProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			SaleProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			SaleProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalRgWeightReturn,SkuPurityReturn,SkuGoldRateReturn,ErpGoldRateFor24K);
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLineBillingScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLineBillingScreen.get("Subtotal");
		String Discount 			= TransactionDataLineBillingScreen.get("Discount");
		String TotalNetWeight   	= TransactionDataLineBillingScreen.get("NetWeight");
		String Tax 					= TransactionDataLineBillingScreen.get("Tax");
		String TotalAmount 			= TransactionDataLineBillingScreen.get("TotalAmount");
		String NetTotal         = TransactionDataLineBillingScreen.get("NetTotal");
		String ActualLinesCount		= TransactionDataLineBillingScreen.get("LinesCount");

		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.addAll(ReturnProductNames);
		ProductNames.addAll(SaleProductNames);
		ProductQtyList.addAll(ReturnProductQtyList);
		ProductQtyList.addAll(SaleProductQtyList);
		ProductTotalList.addAll(ReturnProductTotalList); 
		ProductTotalList.addAll(SaleProductTotalList);

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");
		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==2) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
			}
			else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");
			}

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replace("\u20B9", "").replace(",", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replace("\u20B9", "").replace(",", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")), Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge.replace("-", "")),1.00,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}

		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("\u20B9", "").replace(",", "").trim();
		asrt.assertEquals(ActualSubTotalInBillingPge, ExpectedSubTotalInBillingPge,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replace("\u20B9", "").replace(",", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax = SubTotalSum*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(ActualTax, ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replace("\u20B9", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		// Step 11: Select OG
		// Step 12:Click on OG own and Choose exchange
		// Step 13: Select configuration
		// Step 14: Click on Add item button
		// Step 15: Select QC person
		// Step 16: Select Smith person
		// Step 17: Enter piece value
		// Step 17: Enter gross weight
		// Step 19: click on calculate button
		// Step 20: Click on Add to cart button
		// Expected:Check Old Gold Calculation
		int OgAddCount = InvoiceWithSRandOGTestData.TC292_OGCount;
		for (int i = 1; i <= OgAddCount; i++) {

			base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "OG");
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine", UtilityTestData.Product));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
			Thread.sleep(10000);
			List<WebElement> ErrorMessage = driver.findElements(
					NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));

			if (!ErrorMessage.isEmpty()) {
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart( "buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
			}
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), "1");
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), "2");
			base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			Thread.sleep(4000);

			double ActualCurrentGoldRate22kInItemDetailsPge = Double.parseDouble(base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate")));
			double CurrentERPGoldRate24k = Double.parseDouble(ErpGoldRateFor24K);
			double ExpectedCurrentGoldRate22kFromERP = (CurrentERPGoldRate24k*22)/24;
			asrt.assertEquals(ActualCurrentGoldRate22kInItemDetailsPge, ExpectedCurrentGoldRate22kFromERP,1.0,"Gold Rate mismatch: Expected is "+ExpectedCurrentGoldRate22kFromERP+" but found "+ActualCurrentGoldRate22kInItemDetailsPge+"in item details page");

			String ExpectedNetWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));		
			double NetWeightInItemDetailsPge = Double.parseDouble(ExpectedNetWeightInItemDetailsPge);
			double ExpectedGoldAmountInItemDetailsPge = Math.round(NetWeightInItemDetailsPge * ActualCurrentGoldRate22kInItemDetailsPge * 100.0) / 100.0;
			String GoldAmountInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetamt"));
			double ActualGoldAmountInItemDetailsPge = Double.parseDouble(GoldAmountInItemDetailsPge);
			asrt.assertEquals(ActualGoldAmountInItemDetailsPge, ExpectedGoldAmountInItemDetailsPge,1.0,"GoldAmount mismatch: "+ExpectedGoldAmountInItemDetailsPge+" but got "+ActualGoldAmountInItemDetailsPge+" in item details page");

			String ExpectedGrossWeightInItemDetailsPage = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
			String TotalAmountInItemDetailsPage = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));
			double ActualTotalAmountInItemDetailsPge = Double.parseDouble(TotalAmountInItemDetailsPage);
			double ExpectedTotalAmountInItemDetailsPge = ActualGoldAmountInItemDetailsPge;
			asrt.assertEquals(ActualTotalAmountInItemDetailsPge, ExpectedTotalAmountInItemDetailsPge,1.0, "Total amount mismatch: Expected is "+ExpectedTotalAmountInItemDetailsPge+" but got "+ActualTotalAmountInItemDetailsPge+" in item details page");

			ExpectedGrossWeightInItemDetailsPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
			ExpectedTotalOgGwgt += Double.parseDouble(ExpectedGrossWeightInItemDetailsPge);

			TotalAmountInItemDetailsPge =base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"));
			ExpectedTotaOgAmt += Double.parseDouble(TotalAmountInItemDetailsPge);
			ProductNames.add("OG");
			ProductQtyList.add(ExpectedGrossWeightInItemDetailsPge);
			ProductTotalList.add(TotalAmountInItemDetailsPge);
			base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots( "win-commandingsurface-actionarea win-appbar-actionarea"));
			base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));

			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Cart has changed")))
			{
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			}

			System.out.println("OG item added → iteration " + i);
		}

		Map<String, String> TransactionDataLineBillingScrn = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		List<String> BillingPageItemNames = new ArrayList<String>();
		List<String> BillingPageItemQtyList = new ArrayList<String>();
		List<WebElement> BillingProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		for(int i=1;i<=BillingProductRows.size();i++)
		{
			BillingPageItemNames.add(TransactionDataLineBillingScrn.get("Item"+i+"Name"));
		}

		//Step 21: Save to estimation Button
		//Step 22:	save esimate
		//Expected: Verify the Invoice details
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(10000);
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(8000);

		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC292_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		Thread.sleep(3000);
		String Gst = Tax;
		String TaxableValue = String.format("%.2f",
				- Double.parseDouble(ProductTotalList.get(0).replaceAll("[^\\d.-]", "")) -
				Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "")) +
				Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.-]", ""))
				);
		String InvoiceTotal = String.format("%.2f",
				-Double.parseDouble(ProductTotalList.get(0).replaceAll("[^\\d.-]", "")) -
				Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", "")) +
				Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.-]", "")) -
				Double.parseDouble(ProductTotalList.get(3).replaceAll("[^\\d.-]", "")) -
				Double.parseDouble(ProductTotalList.get(4).replaceAll("[^\\d.-]", "")) -
				Double.parseDouble(Gst.replaceAll("[^\\d.-]", ""))
				);

		String TotalAdjustmentProForma ="";
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustmentProForma,
				NetTotal,
				TotalAmt
				);

		//Step 23:Recall estimate from cashier screen
		//Step 24: Select estimate and click on Recall estimation button
		//Expected Result: Verify whether the recalled item is the same as the item in the cart.(Estimation No Check)
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ReturnEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ReturnEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ExpectedEstmnNumber+" and Expected Estimation number"+ReturnEstmnNumber+"");

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(BillingPageItemNames.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 25: Select the Transaction Type as Sales return
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithSRandOGTestData.TC292_TransactionTypeReturn);

		//Step 26:Click on Process Estimation 
		//Expected:Cashier Screen after recall Estimate,No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Subtotal,TAX,Total Amount
		//Step 27: Post sale return invoice
		//Expected: Verify Return Invoice details
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);

		Map<String, String> TransactionRecallDataLineSale = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMapSale);
		erpUtils.ErpValidateTransactionLineCalculation(TransactionRecallDataLineSale, ScannedSkuDataMapSale);

		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		String AmountDueForSaleReturnFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleReturnInFromBillingPge = Double.parseDouble(AmountDueForSaleReturnFromBillingPge);
		String ExpectedAmountForSaleReturnInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		double TaxForSaleReturnProducts = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("\u20B9", "").replace(",", "").replace("-", ""));
		if (AmountDueForSaleReturnInFromBillingPge<0.00) {
			appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
					UtilityTestData.SalePersonNumber,
					UtilityTestData.DueYear,
					UtilityTestData.DobYear,
					UtilityTestData.NomineeName,
					UtilityTestData.NomineeRelation);

			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");

			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(InvoiceWithSRandOG_TestData.TC292_PaymentMode, ActualPaymentMode,"Payment mode mismatch: Expected is "+InvoiceWithSRandOG_TestData.TC292_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			Map<String, String> CreditNoteDetails = pdfUtils.SalesReturnPDFCredit();
			String ActualGrossWt = CreditNoteDetails.get("Product1.GrossWt");
			String ActualStoneWt = CreditNoteDetails.get("Product1.StoneWt");
			String ActualNetWt = CreditNoteDetails.get("Product1.NetWt");
			String ActualAmount = CreditNoteDetails.get("Product1.Amount");
			String ActualTaxableValue   = CreditNoteDetails.get("TaxableValue");
			String ActualTotalNetValue  = CreditNoteDetails.get("TotalNetValue");
			String ActualPaymentDetails = CreditNoteDetails.get("PaymentDetails");
			double ExpectedTaxableValue = Double.parseDouble(TotalSku);
			double TotalAmountInBillingPge = ExpectedTaxableValue+TaxForSaleReturnProducts;

			asrt.assertEquals(TotalGrossWgt, ActualGrossWt,"Gross Weight mismatch: Expected is "+TotalGrossWgt+" but found "+ActualGrossWt+" in credit note");
			asrt.assertEquals(TotalStoneWgt, ActualStoneWt,"StoneWt mismatch: Expected is"+TotalStoneWgt+" but found "+ActualStoneWt+" in credit note");
			asrt.assertEquals(TotalNetWgt, ActualNetWt,"NetWt mismatch: Expected is"+TotalNetWgt+" but found "+ActualNetWt+" in credit note");
			asrt.assertEquals(Double.parseDouble(TotalSku), Double.parseDouble(ActualAmount),1.0,"Amount mismatch: Expected is"+TotalSku+" but found "+ActualAmount+" in credit note");
			asrt.assertEquals(ExpectedTaxableValue, Double.parseDouble(ActualTaxableValue),1.0,"Taxable Value mismatch: Expected is"+ExpectedTaxableValue+" but found "+ActualTaxableValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualTotalNetValue),1.0,"Total Net Value mismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualTotalNetValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualPaymentDetails),1.0,"Payment Detailsmismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualPaymentDetails+" in credit note");
		}

		//Step 28: Again recall estimate and select transaction type as exchange
		//Step 29: Click Process Estimation
		//Step 30 : Post the Exchange Invoice
		//Expected: Check Return Invoice Details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));				
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithSRandOG_TestData.TC292_TransactionTypeExchange);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		String AmountDueForExchangeFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForExchangeInFromBillingPge = Double.parseDouble(AmountDueForExchangeFromBillingPge);			
		String ExpectedAmountForExchangeInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		if (AmountDueForExchangeInFromBillingPge<0.00) {
			appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
					UtilityTestData.SalePersonNumber,
					UtilityTestData.DueYear,
					UtilityTestData.DobYear,
					UtilityTestData.NomineeName,
					UtilityTestData.NomineeRelation);

			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Thread.sleep(5000);
			Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
			Thread.sleep(2000);
			String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
			String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
			String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
			String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");

			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
			asrt.assertEquals(InvoiceWithSRandOG_TestData.TC292_PaymentMode, ActualPaymentMode,"Payment Mode mismatch: Expected is "+InvoiceWithSRandOG_TestData.TC292_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForExchangeInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForExchangeInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			double GrossWeight = (ExpectedTotalOgGwgt);
			String ExpectedGrossWeightFromBillingPge = String.format("%.3f", GrossWeight);  
			String ExpectedOGPurchaseRateFromItemDetailsPge = String.format("%.2f", Current22kRate);
			double TotalAmountForOG =(ExpectedTotaOgAmt);
			String ExpectedTotalAmountForOG = String.format("%.2f", TotalAmountForOG);  			
			Map<String, String> OgPurchaseBill = pdfUtils.OGPdfValidation("Adjustment");
			//OG1
			String GrossWeightFromInvoice = OgPurchaseBill.get("GrossWt1");
			String NetWeightFromInvoice = OgPurchaseBill.get("NetWt1");
			String GoldRateFromInvoice = OgPurchaseBill.get("Rate1");
			String TotalAmountFromInvoice = OgPurchaseBill.get("TotalAmount1");
			String PaymentDetailsFromInvoice = OgPurchaseBill.get("PaymentDetails");
			String ExpectedOGRate = GoldRateFromInvoice;

			// OG 2
			String GrossWeight2 = OgPurchaseBill.get("GrossWt2");
			String NetWeight2   = OgPurchaseBill.get("NetWt2");
			String Rate2        = OgPurchaseBill.get("Rate2");
			String Amount2      = OgPurchaseBill.get("TotalAmount2");

			//Total Values
			String TotalGwgtInvoice = String.format("%.3f",Double.parseDouble(GrossWeightFromInvoice) + Double.parseDouble(GrossWeight2));
			String TotalAmtInvoice  = String.format("%.2f", Double.parseDouble(TotalAmountFromInvoice) + Double.parseDouble(Amount2));
			String TotalNetwgtInvoice = String.format("%.3f", Double.parseDouble(NetWeightFromInvoice) + Double.parseDouble(NetWeight2));

			asrt.assertEquals(ExpectedGrossWeightFromBillingPge, TotalGwgtInvoice,"Gross Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+TotalGwgtInvoice+" in purchase bill");		
			asrt.assertEquals(ExpectedGrossWeightFromBillingPge, TotalNetwgtInvoice,"Net Weight mismatch: "+ExpectedGrossWeightFromBillingPge+" but got "+TotalNetwgtInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedOGRate, GoldRateFromInvoice,"Gold rate mismatch: "+ExpectedOGRate+" but got "+ GoldRateFromInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, TotalAmtInvoice,"Total amount mismatch: "+ExpectedTotalAmountForOG+" but got "+TotalAmtInvoice+" in purchase bill");	
			asrt.assertEquals(ExpectedTotalAmountForOG, PaymentDetailsFromInvoice,"Payment details mismatch: "+ExpectedTotalAmountInBillingPge+" but got "+PaymentDetailsFromInvoice+" in purchase bill");
		}

		//Step 31: Again Recall estimate and select Transaction Type Sales
		//Step 32: Click Process Estimation
		//Step 33: Click on the Amount.
		//Step 34: Select a Payment Method (Cash or Card).
		//Step 35 :Post the Invoice.
		//Expected:Check the final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithSRandOG_TestData.TC292_TransactionTypeSales);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		String GrossAmountForSale= base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"));
		String TaxableValueForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"));
		String TotalValueForSale=base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		String TotalNetValueForSale=base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		String TotalQtyPcsForSale = String.valueOf(SkuList.size());
		String TotalDiscountForSale = Discount;
		String TotalNetWeightForSale = TotalNetWeight;
		String TotalGrossWeightForSale= SaleProductQtyList.get(0);
		String AdjustmentForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("PaymentsField", "h4"));;
		String AmountDueForSaleFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleInFromBillingPge = Double.parseDouble(AmountDueForSaleFromBillingPge);
		String PaymentAmountForSale ="";
		if (AmountDueForSaleInFromBillingPge==0.00) {
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			PaymentAmountForSale = "";
		}
		else {
			PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");
		}

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC292_TaxInvoice");		
		String TaxInvoice =System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String TotalDiamond = "";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidation(
				TaxInvoice,
				GrossAmountForSale,
				TotalDiscountForSale,
				TotalDiamond,
				TotalQtyPcsForSale,
				TotalNetWeightForSale,
				TotalGrossWeightForSale,
				TaxableValueForSale,
				TotalNetValueForSale,
				PaymentAmountForSale,
				AdjustmentForSale,
				SkuList,
				ScannedSkuDataMap
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

}

