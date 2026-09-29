package testPage;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.InvoiceWithAdvanceSchemeAndOG_Obj;
import objectRepository.InvoiceWithDifferentTypeAdvances_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import testData.CommonData;
import testData.InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData;
import testData.InvoiceWithAdvanceSchemeSR_TestData;
import testData.InvoiceWithSRandOG_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities.ReturnDetails;
import utilPack.AppUtilities.SKUResult;
import utilPack.AppUtilities.SKUTypeResult;

public class InvoiceWithAdvanceSchemeSR extends BasePge{

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	MathUtilities mathUtils;
	PdfUtilities pdfUtils;
	ErpUtilities erpUtils;

	Login login = new Login(driver); 
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	LoginPage_Obj LoginPageObj = new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	RtgsPaymentToCustomer_TestData RtgsPaymentToCustomerTestData = new RtgsPaymentToCustomer_TestData();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData = new InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData();
	InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj InvoiceAdvanceWithPaymentMethodRTGSReceivedObj =new InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	WebDriverWait wait;
	InvoiceWithAdvanceSchemeAndOG_Obj InvoiceWithAdvanceSchemeAndOGObj = new InvoiceWithAdvanceSchemeAndOG_Obj();
	InvoiceWithAdvanceSchemeSR_TestData InvoiceWithAdvanceSchemeSRTestData=new InvoiceWithAdvanceSchemeSR_TestData();
	InvoiceWithDifferentTypeAdvances_Obj InvoiceWithDifferentTypeAdvancesObj = new InvoiceWithDifferentTypeAdvances_Obj();

	public InvoiceWithAdvanceSchemeSR(WebDriver driver) 
	{
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}

	//summary>
	// Test Case Title : Invoice with SR and advance
	// Automation ID : TC286
	// Author:Sangeetha M S
	// </summary>
	public void TC286_InvoiceWithAdvanceSchemeSR() throws Exception
	{
		//Step 1.Login to POS
		//Prerequisite 1.Take the bill number (receipt number) that has previously been sold.
		List<String> ItemList = InvoiceWithAdvanceSchemeSRTestData.TC286_ReturnProducts;
		Map<String, Object> InvoiceDetails = erpUtils.GetInvoiceDetailsAfterNormalSale(InvoiceWithAdvanceSchemeSRTestData.TC286_CustomerId, ItemList);
		String ActualCustomerName = pdfUtils.ExtractCustomerNameFromSaleInvoice();

		String InvoiceNo            = (String) InvoiceDetails.get("InvoiceNo");
		String SkuPurityReturn      = (String) InvoiceDetails.get("FirstSkuPurity");
		String SkuGoldRateReturn    = (String) InvoiceDetails.get("FirstSkuGoldRate");
		String TotalRgWeightReturn  = (String) InvoiceDetails.get("TotalRgWeight");
		String Rate = BigDecimal.valueOf(Double.parseDouble(SkuGoldRateReturn)).stripTrailingZeros().toPlainString();

		// Extract SKU-wise map
		Map<String, String> SkuWiseData = (Map<String, String>) InvoiceDetails.get("SKUWiseDetails");

		//  Print only SKU__NetWeight and SKU__GrossWeight
		String Sku1NetWeight   = SkuWiseData.getOrDefault("SKU_1_NetWeight", "N/A");	
		String Sku1GrossWeight = SkuWiseData.getOrDefault("SKU_1_GrossWeight", "N/A");
		String Sku1StoneWeight = SkuWiseData.getOrDefault("SKU_1_StoneWeight", "N/A");
		String Sku1Total	   = SkuWiseData.getOrDefault("SKU_1_Total", "N/A");	
		String Sku2NetWeight   = SkuWiseData.getOrDefault("SKU_2_NetWeight", "N/A");
		String Sku2GrossWeight = SkuWiseData.getOrDefault("SKU_2_GrossWeight", "N/A");
		String Sku2StoneWeight = SkuWiseData.getOrDefault("SKU_2_StoneWeight", "N/A");
		String Sku2Total       = SkuWiseData.getOrDefault("SKU_2_Total", "N/A");

		//Prerequisite 2.A valid advance booked
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				InvoiceWithAdvanceSchemeSRTestData.TC286_CustomerId,
				InvoiceWithAdvanceSchemeSRTestData.TC286_AdvanceAmount,
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		String ErpGoldRateFor24K= erpUtils.SetMetalRateInERP(SkuGoldRateReturn, SkuPurityReturn);
		TimeUnit.MINUTES.sleep(2);

		//Step 2. Click on Transaction button
		Thread.sleep(2000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3. Select customer
		//Step 4. Click on add to estimation button
		appUtils.SearchByCustomerID(InvoiceWithAdvanceSchemeSRTestData.TC286_CustomerId, UtilityTestData.MenuBarOptions[5]);
		Thread.sleep(1000);

		//Step 5. Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6. click on normal advance settlement
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		//Step 7. Select advance and click on cart button
		//Step 8. Enter OTP(Currently disabled)
		Thread.sleep(1000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),InvoiceWithAdvanceSchemeSRTestData.TC286_AdvanceAmount); 
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(2000);
		System.out.println("Rate   "+Rate);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",Rate));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		List<WebElement> DepositItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		List<WebElement> DepositAmount =  base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		double DepositAmt = Double.parseDouble(DepositAmount.get(0).getText().replace("\u20B9", "").replace(",", "").trim());
		double AdvanceQty22k = Double.parseDouble(InvoiceWithAdvanceSchemeSRTestData.TC286_AdvanceAmount) / Double.parseDouble(SkuGoldRateReturn) ;
		double TotalQty = AdvanceQty22k + Double.parseDouble(TotalRgWeightReturn);
		String TotalQty22k = Double.toString(TotalQty) ; 
		List<String> DepositProductNames = new ArrayList<>();
		List<String> DepositProductQtyList = new ArrayList<>();
		List<String> DepositProductTotalList = new ArrayList<>();
		DepositProductNames.add("Deposit Adjustment");
		DepositProductQtyList.add("1");
		DepositProductTotalList.add(Double.toString(DepositAmt));

		//Step 9. Click on customer adjustment button
		//Step 10. Click on return transaction button at the billing screen
		//Step 11. Enter receipt number
		//Step 12. Select return product (multiple gold)and click on return button
		//Expected : The product meant to be returned should be previously bought by the same customer.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		List<String> ToReturnProducts = Arrays.asList("Gold","Gold");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, ToReturnProducts);
		List<String> ReturnProductNames        = Details.productNames;
		List<String> ReturnProductQtyList      = Details.productQty;
		List<String> ReturnProductTotalList    = Details.productPrice;

		asrt.assertEquals(InvoiceWithAdvanceSchemeSRTestData.TC286_CustomerName.toUpperCase().trim(),ActualCustomerName.toUpperCase().trim(),"Customer name for sale is not the same as the customer name from return. " +"ExpectedCustomerNameFrom MethodInvoicePdf: " + ActualCustomerName + ", but found: " + InvoiceWithAdvanceSchemeSRTestData.TC286_CustomerName);

		//Step 13. Click on add to cart button
		//Step 14. Scan multiple category product SKU
		//Expected : Check calculation(Average rate) 
		//Note:Billing Screen *No of Product lines, *Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax), *Displayed Subtotal, *TAX ,*Total Amount
		// Note: Check average rate
		Map<String, String> SkuDetails = new LinkedHashMap<>();
		List<String> SkuListGold = new ArrayList<>();
		double WeightRequired=Double.parseDouble(Sku1GrossWeight);
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithAdvanceSchemeSRTestData.TC286_TransferType,
				InvoiceWithAdvanceSchemeSRTestData.TC286_FromCounter,
				InvoiceWithAdvanceSchemeSRTestData.TC286_FromCounter,
				WeightRequired,
				InvoiceWithAdvanceSchemeSRTestData.TC286_SkuCount);
		List<String> SkuList = SkuGoldSaleItem.PcsRateSKU;
		SkuListGold.addAll(SkuList);
		System.out.println("Gold Selected SKU: " + SkuListGold);
		appUtils.HamBurgerButtonClick("iconShop"); 
		Thread.sleep(1000);

		SKUResult SkuGoldSaleItemSkuListPrecia = appUtils.GetOGSaleSku(
				InvoiceWithAdvanceSchemeSRTestData.TC286_TransferType,
				InvoiceWithAdvanceSchemeSRTestData.TC286_UncutFromCounter,
				InvoiceWithAdvanceSchemeSRTestData.TC286_MetalType,
				WeightRequired,
				InvoiceWithAdvanceSchemeSRTestData.TC286_SkuCount);
		List<String> SkuListPrecia = SkuGoldSaleItemSkuListPrecia.PcsRateSKU;
		SkuListGold.addAll(SkuListPrecia);
		System.out.println("Precia Selected SKU: " + SkuListPrecia);
		appUtils.HamBurgerButtonClick("iconShop"); 
		Thread.sleep(1000);	

		List<String> SaleProductNames = new ArrayList<>();
		List<String> SaleProductQtyList = new ArrayList<>();
		List<String> SaleProductTotalList = new ArrayList<>();
		int SkuCounter = 1;

		appUtils.HamBurgerButtonClick("iconShop"); 
		String ErpGoldRate24k = ErpGoldRateFor24K;
		erpUtils.ResetRemainingReturnWeight();

		for (String Sku : SkuListGold) 
		{
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, SkuDetails);
			Map<String, String> currentSkuData = appUtils.ExtractDataForSku(SkuDetails, SkuCounter);
			SaleProductNames.add(SkuDetails.get("SKU_" + SkuCounter + "_HeaderName"));
			SaleProductQtyList.add(SkuDetails.get("SKU_" + SkuCounter + "_GrossWeight"));
			SaleProductTotalList.add(SkuDetails.get("SKU_" + SkuCounter + "_Total"));
			DepositProductNames.add(SkuDetails.get("SKU_" + SkuCounter + "_HeaderName"));
			DepositProductQtyList.add(SkuDetails.get("SKU_" + SkuCounter + "_GrossWeight"));
			DepositProductTotalList.add(SkuDetails.get("SKU_" + SkuCounter + "_Total"));

			erpUtils.ErpSkuIngredientItemCalculation(currentSkuData, SkuCounter,TotalQty22k,SkuPurityReturn,SkuGoldRateReturn,ErpGoldRate24k);
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuListGold, SkuDetails);
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
		List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
		List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
		String ActualTotalWithoutTaxInBillingPge ="";
		double ExpectedTotalWithoutTaxInBillingPge =0.00;
		double TaxableValue=0.00;
		for(int i =0; i < AllProductRows.size() ;i++) 
		{			
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			if (i==0)
			{
				String DepositName = DepositItemName.get(i).getText();
				String ExpectedItemNameInBillingPge = DepositName.substring(DepositName.indexOf("-") + 1).trim();

				asrt.assertTrue(ActualItemNameInBillingPge.contains(ExpectedItemNameInBillingPge),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

				ActualTotalWithoutTaxInBillingPge =  TotalWithoutTax.get(i).getText().replaceAll("[^0-9.-]", "") .trim();
				SubTotalSum = Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
				if (!DepositAmount.isEmpty()) 
				{
					String AmountDeposited = DepositAmount.get(0).getText().replace("\u20B9", "").replace(",", "").trim();
					if (!AmountDeposited.isEmpty())
					{
						ExpectedTotalWithoutTaxInBillingPge = Double.parseDouble(AmountDeposited);
					}
				}
				asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge),ExpectedTotalWithoutTaxInBillingPge,1.00,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got " + ActualTotalWithoutTaxInBillingPge + " in billing page");
			}
			else 
			{
				String ExpectedItemNameInBillingPge ="";
				if (i==1 || i==2)
				{					
					asrt.assertTrue(ProductNames.get(i-1).trim().contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ProductNames.get(i-1).trim()+" but got "+ActualItemNameInBillingPge+" in billing page");

					String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
					String ExpectedQuantityInBillingPge = ProductQtyList.get(i-1);
                 	double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
					double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
					
					asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

					ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replace("\u20B9", "").replace(",", "").trim();
					SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
					String ExpectedTotalWithoutTax = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i-1).replace("\u20B9", "").replace(",", "").trim()));
					TaxableValue=TaxableValue+Double.parseDouble(TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim());
					
					asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")), Double.parseDouble(ExpectedTotalWithoutTax.replace("-", "")),1.00,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTax+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
				}
				else
				{
					Thread.sleep(1000);
					asrt.assertTrue(ProductNames.get(i-1).trim().contains(ActualItemNameInBillingPge), "Item name mismatch: Expected is " + ProductNames.get(i-1).trim() + " but got " + ActualItemNameInBillingPge + " in billing page");

					String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");	
					String ExpectedQuantityInBillingPge = ProductQtyList.get(i-1);//y
					double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
					double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);

					asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");				

					ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replace("\u20B9", "").replace(",", "").trim();
					SubTotalSum = SubTotalSum+Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
					String ExpectedTotalWithoutTax = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i-1).replace("\u20B9", "").replace(",", "").trim()));
					TaxableValue=TaxableValue+Double.parseDouble(ExpectedTotalWithoutTax);
					
					asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")), Double.parseDouble(ExpectedTotalWithoutTax.replace("-", "")),1.00,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTax+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
				}				
			}	
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum);
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("\u20B9", "").replace(",", "").trim();

		asrt.assertEquals(ActualSubTotalInBillingPge, ExpectedSubTotalInBillingPge,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replace("\u20B9", "").replace(",", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax =  TaxableValue*0.03;
		System.out.println("TaxableValue   pro"+TaxableValue);

		asrt.assertEquals(ActualTax, ExpectedTax,1.0,"Tax amount mismatch: "+ExpectedTax+" but found "+ActualTax+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replace("\u20B9", "").replace(",", "").trim());

		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");

		Map<String, String> BillingPageItems = appUtils.TransactionSKUsLinesVerify(SkuListGold, SkuDetails);
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

		//Step 15. save estimate
		//Expected : Verify performa invoice
		//Step 16.Recall estimate from cashier screen
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));

		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(1000);
		double DepositedValue = Math.abs(DepositAmt); 	
		double SubTotalInBillingPge =Double.parseDouble(ActualSubTotalInBillingPge);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC286_ProformaInvoice");
		Thread.sleep(1000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = ActualTaxInBillingPge.replace("-", "");
		String TaxableAmount		   = ActualSubTotalInBillingPge.replace("-", "")+DepositedValue;
		String InvoiceTotal        = String.format("%.2f",TaxableValue+ActualTax).replace("-", "");
		String TotalAmnt           =  String.format("%.2f",TaxableValue+ActualTax).replace("-", "");
		double InvoiceValue = Double.parseDouble(InvoiceTotal); 
		double CalculatedNetTotal = InvoiceValue - DepositedValue; ;
		String NetTotal =  String.format("%.2f", CalculatedNetTotal); 

		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath,
				TaxableAmount,
				Gst,
				InvoiceTotal,
				InvoiceWithAdvanceSchemeSRTestData.TC286_AdvanceAmount,
				NetTotal,
				TotalAmnt
				);

		//Step 17. Select estimate and click on recall estimation
		//Expected : Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ReturnEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ReturnEstmnNumber, EstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ReturnEstmnNumber+"");
		appUtils.RecallEstimateItemValidation(BillingPageItemNames, BillingPageItemQtyList, BillingPageItemNames, BillingPageItemQtyList, Double.parseDouble(BillingPageSubTotal), Double.parseDouble(BillingPageTax), Double.parseDouble(BillingPageTotalAmount), Integer.parseInt(BillingPageActualLinesCount), "in Recall page");

		//Step 18.Select the Transaction Type as sales return
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAdvanceSchemeSRTestData.TC286_TransationType);

		//Step 19.Click on Process Estimation 
		//Step 20.Choose a sales representative(Removed after data updation)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 21.Click on the Amount
		//Step 22.Post return invoice
		//Expected : Verify return invoice
		String AmountDueForSaleReturnFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleReturnInFromBillingPge = Double.parseDouble(AmountDueForSaleReturnFromBillingPge);
		String ExpectedAmountForSaleReturnInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		double TaxForSaleReturnProducts = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replace("\u20B9", "").replace(",", "").replace("-", ""));
		if (AmountDueForSaleReturnInFromBillingPge<0.00)
		{
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
			asrt.assertEquals(InvoiceWithAdvanceSchemeSRTestData.TC286_PaymentMode, ActualPaymentMode,"Payment mode mismatch: Expected is "+InvoiceWithAdvanceSchemeSRTestData.TC286_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
			asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		}
		else 
		{
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			Map<String, String> CreditNoteDetails = pdfUtils.SalesReturnPDFCredit();
			String ActualGrossWt = CreditNoteDetails.get("Product1.GrossWt");
			String ActualNetWt = CreditNoteDetails.get("Product1.NetWt");
			String ActualAmount = CreditNoteDetails.get("Product1.Amount");
			String Actual2GrossWt = CreditNoteDetails.get("Product2.GrossWt");
			String Actual2NetWt = CreditNoteDetails.get("Product2.NetWt");
			String Actual2Amount = CreditNoteDetails.get("Product2.Amount");

			String ActualTaxableValue   = CreditNoteDetails.get("TaxableValue");
			String ActualTotalNetValue  = CreditNoteDetails.get("TotalNetValue");
			String ActualPaymentDetails = CreditNoteDetails.get("PaymentDetails");
			double ExpectedTaxableValue = Double.parseDouble(Sku1Total)+ Double.parseDouble(Sku2Total);
			double TotalAmountInBillingPge = ExpectedTaxableValue+TaxForSaleReturnProducts;

			asrt.assertEquals(Sku1GrossWeight, ActualGrossWt,"Gross Weight mismatch: Expected is "+Sku1GrossWeight+" but found "+ActualGrossWt+" in credit note");
			asrt.assertEquals(Sku1NetWeight, ActualNetWt,"NetWt mismatch: Expected is"+Sku1NetWeight+" but found "+ActualNetWt+" in credit note");
			asrt.assertEquals(Double.parseDouble(Sku1Total), Double.parseDouble(ActualAmount),1.0,"Amount mismatch: Expected is"+Sku1Total+" but found "+ActualAmount+" in credit note");
			asrt.assertEquals(Sku2GrossWeight, Actual2GrossWt,"Gross Weight mismatch: Expected is "+Sku2GrossWeight+" but found "+Actual2GrossWt+" in credit note");
			asrt.assertEquals(Sku2NetWeight, Actual2NetWt,"NetWt mismatch: Expected is"+Sku2NetWeight+" but found "+Actual2NetWt+" in credit note");
			asrt.assertEquals(Double.parseDouble(Sku2Total), Double.parseDouble(Actual2Amount),1.0,"Amount mismatch: Expected is"+Sku2Total+" but found "+Actual2Amount+" in credit note");
			asrt.assertEquals(ExpectedTaxableValue, Double.parseDouble(ActualTaxableValue),1.0,"Taxable Value mismatch: Expected is"+ExpectedTaxableValue+" but found "+ActualTaxableValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualTotalNetValue),1.0,"Total Net Value mismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualTotalNetValue+" in credit note");
			asrt.assertEquals(TotalAmountInBillingPge, Double.parseDouble(ActualPaymentDetails),1.0,"Payment Detailsmismatch: Expected is"+TotalAmountInBillingPge+" but found "+ActualPaymentDetails+" in credit note");
		}

		//Step 23.Recall estimate from cashier screen
		//Step 24. Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		Thread.sleep(3000);
		BillingPageItemNames.remove(0);

		//Step 25.Select the Transaction Type as sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAdvanceSchemeSRTestData.TC286_TransationType2);

		//Step 26.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 27.Choose a sales representative[Step removed: New update]	
		//Expected : Cashier Screen after recall Estimate  *No of Product lines,*Displayed Item Name,*Displayed Quantity, *Displayed TOTAL (without Tax), *Displayed Subtotal, *TAX,*Total Amount
		Thread.sleep(2000);	
		Map<String, String> TransactionRecallDataLineSale = appUtils.TransactionSKUsLinesVerify(SkuListGold, SkuDetails);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionRecallDataLineSale, DepositProductNames, DepositProductQtyList, DepositProductTotalList, "Cashier");

		String GrossAmountForSale= base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();;
		String TaxableValueForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();;
		String TotalNetValueForSale=base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim();;
		String TotalQtyPcsForSale = String.valueOf(SkuListGold.size());
		String TotalDiscountForSale = Discount;
		String TotalNetWeightForSale = TotalNetWeight;
		//String TotalGrossWeightForSale= Double.toString(Double.parseDouble(SaleProductQtyList.get(0))+Double.parseDouble(SaleProductQtyList.get(1)));
		String AdjustmentForSale = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("PaymentsField", "h4")).replace("₹", "").replace(",", "").trim();
		String AmountDueForSaleFromBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		double AmountDueForSaleInFromBillingPge = Double.parseDouble(AmountDueForSaleFromBillingPge);
		String PaymentAmountForSale ="";
		Thread.sleep(1000);
		if (AmountDueForSaleInFromBillingPge==0.00) 
		{
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
			PaymentAmountForSale = "";
		}
		else
		{
			Thread.sleep(1000);
			PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");
		}

		//Step 29.Post the Invoice
		//Expected :Check final invoice details
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(1000);
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC286_TaxInvoice");		
		String TaxInvoice =System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String TotalDiamond = "";
		String GrossAmountPdf = Double.toString(Double.parseDouble(GrossAmountForSale)  + Double.parseDouble(InvoiceWithAdvanceSchemeSRTestData.TC286_AdvanceAmount)) ;
		String TotalGrossWeightForSale= Double.toString(Double.parseDouble(SaleProductQtyList.get(0)) +Double.parseDouble(SaleProductQtyList.get(1)));
		String TaxableValuePdf = Double.toString(Double.parseDouble(TaxableValueForSale)  + Double.parseDouble(InvoiceWithAdvanceSchemeSRTestData.TC286_AdvanceAmount)) ;
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentAdvanceValidation(
				TaxInvoice,
				GrossAmountPdf,
				TotalDiscountForSale,
				TotalDiamond,
				TotalQtyPcsForSale,
				TotalNetWeightForSale,
				TotalGrossWeightForSale,
				TaxableValuePdf,
				TotalNetValueForSale,
				PaymentAmountForSale,
				AdjustmentForSale,
				SkuListGold,
				SkuDetails
				);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	//<summary>
	// Test Case Title : Invoice with SR ,advance and scheme
	// Automation ID : TC288
	// Author : Aiswarya
	// </summary>
	public void TC288_InvoiceWithAdvanceSchemeSR() throws Exception{

		// Step 1: Login to POS
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//PreRequisite
		//1.Customer must be having a matured scheme 
		Thread.sleep(3000);
		Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
				InvoiceWithAdvanceSchemeSRTestData.TC288_InstallmentCount,
				InvoiceWithAdvanceSchemeSRTestData.TC288_HwManyGppAccNeeded,
				InvoiceWithAdvanceSchemeSRTestData.TC288_SchemeCode,
				InvoiceWithAdvanceSchemeSRTestData.TC288_MaturedGGScheme,
				InvoiceWithAdvanceSchemeSRTestData.TC288_AdjustMaturedGGScheme);  
		Thread.sleep(15000);  

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(3000);
		double ErpGoldRate24k = appUtils.FetchCurrentBoardRate(InvoiceWithAdvanceSchemeSRTestData.TC288_Purity);
		double ErpGoldRate22k = Math.round((ErpGoldRate24k * 22) / 24);
		System.out.println("BoardRate24k "+ ErpGoldRate24k);
		System.out.println("ErpGoldRate22k "+ ErpGoldRate22k);

		//Step 2 : Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");     
		String CustomerNo="";
		String GPPAccountNumber="";
		String GPPAmount="";
		boolean ValidAccountFound = false;
		String AdjustmentAmount="";
		String TotalRgWeightGG	="";
		String NormalAdvanceAmount="";
		String MetalRateFetch="";
		String SkuGoldRateGG ="";
		String SkuPurityGG ="";

		for (int idx = 0; idx < MatchedAccounts.size(); idx++)
		{
			Map<String, String> AccountMap = MatchedAccounts.get(idx);
			CustomerNo = AccountMap.get("CustomerAccountNo");
			GPPAccountNumber = AccountMap.get("GppAccountNo");
			GPPAmount = (String) AccountMap.get("GppAmount"); 
		
			//To check Customer has pan number or not 
			System.out.println("\nChecking PAN for Customer: " + CustomerNo + " | GPP Account: " + GPPAccountNumber);
			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);    
			try
			{
				appUtils.AddOrVerifyPan(); 
			} 
			catch (Exception e) 
			{
				if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("OTP verification")))
				{
					base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					System.out.println("Otp required for this Customer"+CustomerNo);
					continue; 
				}
			}  
			appUtils.HamBurgerButtonClick("iconShop");
			appUtils.VoidTransaction();

			//Select customer
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
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
			Thread.sleep(2000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

			// Check if "No data" is displayed
			if (base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("No data")))
			{
				System.out.println("No data for account: " + GPPAccountNumber+ ". Retrying with next...");
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				continue; 
			}
			Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(GPPAccountNumber);
			SkuPurityGG = (String) GPPMaturityValues.get("Purity");
			SkuGoldRateGG = (String) GPPMaturityValues.get("AvgRate");
			SkuGoldRateGG = SkuGoldRateGG.replace(",", "");
			System.out.println("Valid GPP account found: " + GPPAccountNumber);
			ValidAccountFound = true;
			break;
		}
		if (!ValidAccountFound) 
		{
			throw new RuntimeException(" All GPP Accounts resulted in 'No data'. Test stopped.");
		}
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.VoidTransaction();

		//PreRequisite
		List<String> ItemList = InvoiceWithAdvanceSchemeSRTestData.TC288_ReturnProducts;
		Map<String, Object> InvoiceDetails = erpUtils.GetInvoiceDetailsAfterNormalSaleWt(CustomerNo, ItemList );  
		String ExpectedCustomerName = pdfUtils.ExtractCustomerNameFromSaleInvoice();
		String InvoiceNo            = (String) InvoiceDetails.get("InvoiceNo");
		String SkuPurityReturn      = (String) InvoiceDetails.get("FirstSkuPurity");
		String SkuGoldRateReturn    = (String) InvoiceDetails.get("FirstSkuGoldRate");
		String TotalRgWeightReturn  = (String) InvoiceDetails.get("TotalRgWeight");

		// Extract SKU-wise map
		Map<String, String> SkuWiseData = (Map<String, String>) InvoiceDetails.get("SKUWiseDetails");

		//SKU_1_NetWeight and SKU_1_GrossWeight
		String Sku1NetWeight   = SkuWiseData.getOrDefault("SKU_1_NetWeight", "N/A");
		String Sku1GrossWeight = SkuWiseData.getOrDefault("SKU_1_GrossWeight", "N/A");
		String Sku1StoneWeight = SkuWiseData.getOrDefault("SKU_1_StoneWeight", "N/A");
		String Sku1Total	   = SkuWiseData.getOrDefault("SKU_1_Total", "N/A");
		String Sku2NetWeight   = SkuWiseData.getOrDefault("SKU_2_NetWeight", "N/A");
		String Sku2GrossWeight = SkuWiseData.getOrDefault("SKU_2_GrossWeight", "N/A");
		String Sku2StoneWeight = SkuWiseData.getOrDefault("SKU_2_StoneWeight", "N/A");
		String Sku2Total       = SkuWiseData.getOrDefault("SKU_2_Total", "N/A");
		Map<String, String> ScannedSKUDataMapSale = (Map<String, String>) InvoiceDetails.get("ScannedSKUDataMap");
		Map<String, String> TransactionDataLineSale = (Map<String, String>) InvoiceDetails.get("TransactionDataLine");
		String LinesCountSale = TransactionDataLineSale.get("LinesCount");

		//2.Customer must be having a advance(normal/offer)
		NormalAdvanceAmount = appUtils.CreateNormalAdvance(    
				CustomerNo,
				InvoiceWithAdvanceSchemeSRTestData.TC288_NormalAdvanceAmount, 
				UtilityTestData.SalePersonName, UtilityTestData.DueYear);

		//Step 3 : Select customer
		//Step 4 : Click on add to estimation button
		Thread.sleep(3000);
		appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5 : Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6 : Click on normal advance settlement
		// Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(7000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.Terminal);
		Thread.sleep(3000);
		base.ClickCondition(InvoiceWithAdvanceSchemeAndOGObj.Ele_NormalAdv("h4 ellipsis cell", ErpGoldRate22k));
		Thread.sleep(4000);
		String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		Thread.sleep(4000);
		AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);
		MetalRateFetch = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustMetalRate("option", "Metal Rate", "gridcell", 0));
		double AdvanceQty22k = Double.parseDouble(AdjustmentAmount) / Double.parseDouble(MetalRateFetch) ;
		String AdvQuantity = Double.toString(AdvanceQty22k);

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount	+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		//Step 7 : Select advance and click on cart button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		//Step 10 : Enter OTP  (Disabled)
		//Note: Check average rate
		//Step 11 : Click on customer adjustment button
		//Step 12 : Click on GPP advance settlement
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

		//Step 13 : Enter scheme ID and click on redeem button
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
		Thread.sleep(2000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(5000);
		String ActualSchemeAmount = base.GetText(InvoiceWithAdvanceSchemeAndOGObj.Ele_Scheme("tillLayout-TotalWithoutTaxField textRight","h5",2)).replaceAll("[^\\d.]", "").trim(); 

		asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

		double SchemeAmountNow=Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
		double SchemeGoldRate=Double.parseDouble(SkuGoldRateGG);
		TotalRgWeightGG=String.valueOf(SchemeAmountNow/SchemeGoldRate);
		System.out.println("TotalRgWeightReturn"+TotalRgWeightGG);

		//Step 8 : Click on customer adjustment button
		//Step 7 : Click on return transaction button at the billing screen
		//Step 8 : Enter receipt number
		//Step 9: Select return product (multiple gold) and click on return button
		//Expected : The product meant to be returned should be previously bought by the same customer.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		List<String> ToReturnProducts = Arrays.asList("Gold","Gold");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, ToReturnProducts);
		List<String> ReturnProductNames        = Details.productNames;
		List<String> ReturnProductQtyList      = Details.productQty;
		List<String> ReturnProductTotalList    = Details.productPrice;
		ReturnProductTotalList.replaceAll(value -> value.replaceAll("[^\\d.]", ""));
		System.out.println("ReturnProductTotalList:::" + ReturnProductTotalList);
		String ActualCustomerName  = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Customer"));
		Thread.sleep(1000);

		asrt.assertEquals(ActualCustomerName.toUpperCase().trim(),ExpectedCustomerName.toUpperCase().trim(),"Customer name for sale is not the same as the customer name from return. " +"ExpectedCustomerNameFrom MethodInvoicePdf: " + ExpectedCustomerName + ", but found: " + ActualCustomerName);

		double TotalQuantity = AdvanceQty22k + Double.parseDouble(TotalRgWeightReturn) + Double.parseDouble(TotalRgWeightGG) ;
		double RequiredSkuQty = Math.round(TotalQuantity / 3.0) ;
		String TotalQty22k = Double.toString(TotalQuantity) ; 
		
		String ErpGoldRateFor24K= erpUtils.SetMetalRateInERP(Double.toString(ErpGoldRate22k), "22k"); 
		TimeUnit.MINUTES.sleep(2);
		System.out.println("ErpGoldRateFor24K1:::" + ErpGoldRateFor24K);
		double ErpGoldRateFor22k = Math.round((Double.parseDouble(ErpGoldRateFor24K) * 22) / 24);

		//Step 14 : Enter OTP (Disabled)
		//Step 15 : Scan SKU (Gold, Precia, Diamond) & Click on GPP discount
		List<String> SkuList = new ArrayList<>();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		int SkuCounter = 1;

		// Gold SKUs
		SKUResult GoldSkus = appUtils.GetOGSaleSku(
				InvoiceWithAdvanceSchemeSRTestData.TC288_TransferType,
				InvoiceWithAdvanceSchemeSRTestData.TC288_FromCounter,
				InvoiceWithAdvanceSchemeSRTestData.TC288_MetalType,
				RequiredSkuQty,
				InvoiceWithAdvanceSchemeSRTestData.TC288_SkuGoldCount);
		List<String> SkuListGold = GoldSkus.PcsRateSKU;
		SkuList.addAll(SkuListGold);

		//Step 16 : Click on Add to cart button
		//Expected : Check calculation
		appUtils.HamBurgerButtonClick("iconShop"); 
		erpUtils.ResetRemainingReturnWeight();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<Double> AdvanceQty = new ArrayList<>();
		List<String> AdvanceRates = new ArrayList<>();
		List<String> AdvanceTotal = new ArrayList<>();
		double ActualRgRateInItemDetailsPge =0.0;

		AdvanceQty.add(Double.parseDouble(Sku1NetWeight));
		AdvanceQty.add(Double.parseDouble(Sku2NetWeight));
		AdvanceQty.add(Double.parseDouble(TotalRgWeightGG));
		AdvanceQty.add(AdvanceQty22k);

		AdvanceRates.add(SkuGoldRateReturn);
		AdvanceRates.add(SkuGoldRateReturn);
		AdvanceRates.add(Double.toString(SchemeGoldRate));
		AdvanceRates.add(MetalRateFetch);

		AdvanceTotal.add(Sku1Total);
		AdvanceTotal.add(Sku2Total);
		AdvanceTotal.add(ActualSchemeAmount);
		AdvanceTotal.add(AdjustmentAmount);

		ProductNames.add(InvoiceWithAdvanceSchemeSRTestData.TC288_DepositAdjustment);  
		ProductQtyList.add(InvoiceWithAdvanceSchemeSRTestData.TC288_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);

		ProductNames.add(InvoiceWithAdvanceSchemeSRTestData.TC288_SchemeName);
		ProductQtyList.add(InvoiceWithAdvanceSchemeSRTestData.TC288_SchemeWeight);
		ProductTotalList.add(GPPAmount);

		ProductNames.addAll(ReturnProductNames);
		ProductQtyList.addAll(ReturnProductQtyList);
		ProductTotalList.addAll(ReturnProductTotalList); 

		for (String Sku : SkuList)
		{
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			String SkuPurity = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Purity");
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			if (TotalQuantity==0)
			{
				mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			}
			double Total = 0.0;                
			double ExpectedRgRate = 0.0;       
			double SaleQuantity = RGQuantity;  
			int FullyConsumedFromHead = 0;     
			List<String> AdvanceType = new ArrayList<>( Arrays.asList("SR", "SR","GG", "Normal"));
			if (TotalQuantity != 0)
			{
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++)
				{
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));
					if (Qty <= 0) continue;
					String AdvType = AdvanceType.get(j);  
					double EffectiveRate;
					if (AdvType.equalsIgnoreCase("SR"))
					{
						EffectiveRate = Rate;  
					}
					else if (AdvType.equalsIgnoreCase("Normal"))
					{
						EffectiveRate = Math.min(Rate, ErpGoldRate22k);
					}
					else
					{
						EffectiveRate = Rate; 
					}
					double UseQty = Math.min(Qty, SaleQuantity);
					Total += UseQty * EffectiveRate;
					SaleQuantity -= UseQty;
					TotalQuantity -= UseQty;
					double RemainingInRow = Qty - UseQty;
					AdvanceQty.set(j, RemainingInRow);
					if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead)
					{
						FullyConsumedFromHead++;
					}
				}
				if (SaleQuantity > 0)
				{
					Total += SaleQuantity * ErpGoldRateFor22k;
					SaleQuantity = 0;
				}
				ExpectedRgRate = Total / RGQuantity;
				for (int k = 0; k < FullyConsumedFromHead; k++)
				{
					AdvanceQty.removeFirst();
					AdvanceTotal.removeFirst();
					AdvanceRates.removeFirst();
					AdvanceType.removeFirst();  
				}
				asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0,"Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");
			}
			mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, InvoiceWithAdvanceSchemeSRTestData.TC288_Purity22k , ErpGoldRateFor24K);
			SkuCounter++;		
		}
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
		base.setZoom(driver, 60);
		Thread.sleep(14000);
		String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","9"));
		double BenefitWeight = Double.parseDouble(ActualSchemeAmount) / ActualRgRateInItemDetailsPge; 
		double ExpectedBonus = Double.parseDouble(mathUtils.ToGetGppBonus(ScannedSkuDataMap,SkuList.size(),InvoiceWithAdvanceSchemeSRTestData.TC288_BenefitPercentage,Double.parseDouble(TotalRgWeightGG) ,Double.parseDouble(ErpGoldRateFor24K)));
		double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
		System.out.println("ActualBonus: "+ActualBonus);

		ProductNames.add(InvoiceWithAdvanceSchemeSRTestData.TC288_GPPBonus);  
		ProductQtyList.add(InvoiceWithAdvanceSchemeSRTestData.TC288_GPPBonusQty);
		ProductTotalList.add(Double.toString(ExpectedBonus));

		String TotalAdjustment = Double.toString((Double.parseDouble(ActualSchemeAmount)) + Double.parseDouble(InvoiceWithAdvanceSchemeSRTestData.TC288_NormalAdvanceAmount) + ActualBonus);		
		System.out.println("TotalAjustment: "+TotalAdjustment);

		//Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax) *Displayed Subtotal*TAX*Total Amount*Verify the GPP Bonus amount	
		//TranscationPageVerifyLines
		asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ActualBonus+" but got "+ExpectedBonus+" in billing page");

		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String NetTotal = TransactionDataLine.get("NetTotal");
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		String ActualLinesCount = TransactionDataLine.get("LinesCount");
		
		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in Billing page");

		double SubTotalSum = 0.00;
		double ProductTotalAmount = 0.0;
		double	TotalGrosswt = 0.0;
		for (int i = 0; i < AllProductRows.size(); i++) 
		{
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemName = ItemName.get(i).getText();
			if (ProductNames.get(i).contains(ActualItemName)&& ActualItemName.length()<ProductNames.get(i).length()) 
			{
				String ExpectedItemName = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();

				asrt.assertTrue(ExpectedItemName.contains(ActualItemName), "Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Billing page");			
				ProductTotalAmount = ProductTotalAmount+Double.parseDouble(ProductTotalList.get(i));
				TotalGrosswt =  TotalGrosswt +  Double.parseDouble (ProductQtyList.get(i));
			}
			else 
			{
				String ExpectedItemName = ProductNames.get(i).trim();
	
				asrt.assertEquals(ActualItemName, ExpectedItemName,"Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Billing page");
			}
			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantity = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantity = ProductQtyList.get(i);
			double ActualQty = Double.parseDouble(ActualQuantity);
			double ExpectedQty = Double.parseDouble(ExpectedQuantity);
			
			asrt.assertEquals(ActualQty, ExpectedQty, 1.000, "Quantity mismatch: Expected is "+ ExpectedQty + " but got " + ActualQty + " in Billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String TotalAmountWithoutTax = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTax = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalAmountWithoutTax);
			String ExpectedTotalWithoutTax = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTax.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTax),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTax+" but got "+ActualTotalWithoutTax+ " in Billing page");
		}
		String ExpectedSubTotal = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotal = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		
		asrt.assertEquals(Double.parseDouble(ActualSubTotal),Double.parseDouble(ExpectedSubTotal), 1.0,"Subtotal mismatch: " + ExpectedSubTotal + " but found " + ActualSubTotal+ " in Billing page");
		
		String TaxInPge = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(TaxInPge);
        double ProductTotalAmnt = ProductTotalAmount -(Double.parseDouble(ProductTotalList.get(3)) + Double.parseDouble(ProductTotalList.get(2)));
		double ExpectedTax = ProductTotalAmnt*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		
		asrt.assertEquals(ActualTax,ExpectedTax, 1.0,"Tax amount mismatch: " + ExpectedTax + " but found " + ActualTax+ " in Billing page");

		double ExpectedTotalAmount = SubTotalSum+ ActualTax;
		double ActualTotalAmount = Double.parseDouble(TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.-]", "").trim());
		
		asrt.assertEquals(ActualTotalAmount, ExpectedTotalAmount, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmount + " but found " + ActualTotalAmount + " in Billing page");

		//Step 17 : Save estimate
		// Expected : Verify the invoice details (not specified)
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));		 
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(4000);
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// PDF Verification Step
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC288_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst           = Double.toString(ExpectedTax);
		String TaxableValue = Double.toString(ProductTotalAmnt)  ;
		double InvoiceTotalValue = ProductTotalAmnt + ExpectedTax ;
		String InvoiceTotal = String.format("%.2f", InvoiceTotalValue);
		String NetTotalPdf = String.format("%.2f", ActualTotalAmount);
		String TotalAmnt = String.format("%.2f", ActualTotalAmount);

		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment,
				NetTotalPdf,
				TotalAmnt);
		
		//Step 18 : Recall estimate from cashier screen
		//Step 19 : Select estimate and click on Recall estimation button
		//Expected : Verify whether the recalled item is the same as the item in the cart
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
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
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		Thread.sleep(5000);
		List<String> ItemNamesFromRecallPageBeforeExchange = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));

		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPageBeforeExchange), "Recalled item is not same as the item in the Cart");

		//Step 20 : Select the Transaction Type as (Sales return, sale)
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAdvanceSchemeSRTestData.TC288_TransactionTypeSR);

		//Step 21 : Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 22 : Choose a sales representative
		// Expected Result: Cashier Screen after recall*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		Map<String, String> TransactionRecallDataLineReturn = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalReturn 			= TransactionRecallDataLineReturn.get("Subtotal");
		String DiscountReturn 			= TransactionRecallDataLineReturn.get("Discount");
		String TotalGrossWeightReturn   = TransactionRecallDataLineReturn.get("GrossWeight");
		String TotalNewNetWeightReturn  = TransactionRecallDataLineReturn.get("NetWeight");
		String TaxReturn 				= TransactionRecallDataLineReturn.get("Tax");
		String TotalAmountReturn	    = TransactionRecallDataLineReturn.get("TotalAmount").replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim();
		String NetTotalReturn 			= TransactionRecallDataLineReturn.get("NetTotal").replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim();
		String LinesCountReturn 		= TransactionRecallDataLineReturn.get("LinesCount");

		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLineReturn, ScannedSKUDataMapSale);

		// Step 23:  Click on the Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

		// Step 24: post sales return bill
		// Expected Result: Check Return Invoice details
		pdfUtils.DownloadAndRenameLatestPDF("TC288SaleReturnInvoice");

		Map<String, String> result = pdfUtils.SalesReturnPDFCredit();
		String ExpSerialNoReturnSale         = result.get("SerialNo");
		String ExpTotalProductsReturnSale    = result.get("TotalProducts");
		String ExpTotalQtyReturnSale         = result.get("TotalQty");
		String ExpGrossAmountReturnSale      = result.get("GrossAmount");
		String ExpTotalNetWtReturnSale       = result.get("TotalNetWt");
		String ExpTotalGrossWtReturnSale     = result.get("TotalGrossWt");
		String ExpTotalNetValueReturnSale    = result.get("TotalNetValue");
		String ExpTaxableValueReturnSale     = result.get("TaxableValue");
		String ExpAdjustmentReturnSale       = result.get("PaymentDetails");
		String ExpTotalValueReturnSale       = result.get("TotalValue");
		int TotalProductsSale = Integer.parseInt(result.get("TotalProducts"));

		// Loop through each SKU/Product
		for (int i = 1; i <= TotalProductsSale; i++)
		{
			double expectedGrossWt = Double.parseDouble(result.get("Product" + i + ".GrossWt"));
			double expectedStoneWt = Double.parseDouble(result.get("Product" + i + ".StoneWt"));
			double expectedNetWt   = Double.parseDouble(result.get("Product" + i + ".NetWt"));
			double expectedAmount  = Double.parseDouble(result.get("Product" + i + ".Amount"));
			double actualGrossWt = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_GrossWeight", "0"));
			double actualStoneWt = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_StoneWeight", "0"));
			double actualNetWt   = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_NetWeight", "0"));
			double actualAmount  = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_Total", "0"));

			asrt.assertEquals(actualGrossWt, expectedGrossWt, 1.0, "Mismatch in Gross Weight for SKU_" + i + " in PDF Sale Return Invoice details");
			asrt.assertEquals(actualStoneWt, expectedStoneWt, 1.0, "Mismatch in Stone Weight for SKU_" + i + " in PDF Sale Return Invoice details");
			asrt.assertEquals(actualNetWt, expectedNetWt, 1.0, "Mismatch in Net Weight for SKU_" + i + " in PDF Sale Return Invoice details");
			asrt.assertEquals(actualAmount, expectedAmount, 1.0, "Mismatch in Total Amount for SKU_" + i + " in PDF Sale Return Invoice details");
		}
		double ActualTaxableValue 		= Math.abs(Double.parseDouble(((String) InvoiceDetails.get("SubTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim()));
		double ExpectedTaxableValue  	= Double.parseDouble(ExpTaxableValueReturnSale);
		double ActualGrossAmount 		= Math.abs(Double.parseDouble(((String) InvoiceDetails.get("SubTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim()));
		double ExpectedGrossAmount 		= Double.parseDouble(ExpGrossAmountReturnSale);
		double ActualTotalGrossWeight   = Math.abs(Double.parseDouble(((String) InvoiceDetails.get("TotalGrossWeight")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim()));
		double ExpectedTotalGrossWeight = Double.parseDouble(ExpTotalGrossWtReturnSale);
		double ActualNetWeight			= Double.parseDouble(((String) InvoiceDetails.get("TotalNetWeight")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		double ExpectedNetWeight 		= Double.parseDouble(ExpTotalNetWtReturnSale);
		double ActualNetTotal 			= Double.parseDouble(((String) InvoiceDetails.get("NetTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		double ExpectedNetTotal 		= Double.parseDouble(ExpTotalNetValueReturnSale);

		asrt.assertEquals(ActualTaxableValue, ExpectedTaxableValue,1, "Mismatch in Taxable Value Actual "+ActualTaxableValue+" and Expected Taxable Value "+ExpectedTaxableValue+"in PDF Sale Return Invoice details");
		asrt.assertEquals(ActualGrossAmount, ExpectedGrossAmount,1, "Mismatch in Gross Amount Actual "+ActualGrossAmount+" and Expected Gross Amount "+ExpectedGrossAmount+"in PDF Sale Return Invoice details");
		asrt.assertEquals(LinesCountReturn, ExpTotalQtyReturnSale, "Mismatch in No of Product Lines Actual "+LinesCountReturn+" and Expected No of Product Lines "+ExpTotalProductsReturnSale+"in PDF Sale Return Invoice details");
		asrt.assertEquals(ActualNetWeight, ExpectedNetWeight,1, "Mismatch in Total Net Weight Actual "+ActualNetWeight+" and Expected Net Weight "+ExpectedNetWeight+"in PDF Sale Return Invoice details");
		asrt.assertEquals(ActualTotalGrossWeight, ExpectedTotalGrossWeight,1, "Mismatch in Total Gross Weight Actual "+ActualTotalGrossWeight+" and Expected Total Gross Weight"+ExpectedTotalGrossWeight+"in PDF Sale Return Invoice details");
		asrt.assertEquals(ActualNetTotal, ExpectedNetTotal,1, "Mismatch in Net Total Actual "+ActualNetTotal+" and Expected Net Total "+ExpectedNetTotal+"in PDF Sale Return Invoice details");

		Thread.sleep(3000);
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

		//Step 25 : Select the Transaction Type as (Sales return, sale)
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAdvanceSchemeSRTestData.TC288_TransactionTypeSale);

		//Step 26 : Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 27 : Choose a sales representative
		base.setZoom(driver, 60);
		Thread.sleep(3000);
		appUtils.HandleSpecifyRateModalInRecall();
		String ProductsDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4"));
		String PaymentAmountHDFC = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap); //need to remove
		String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
		String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");
		ProductNames.remove(3);
		ProductNames.remove(2);
		ProductQtyList.remove(3);
		ProductQtyList.remove(2);
		ProductTotalList.remove(3);
		ProductTotalList.remove(2);

		Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String TotalGrossWeightCashier   = TransactionDataLineCashier.get("GrossWeight");
		String TotalNewNetWeightCashier  = TransactionDataLineCashier.get("NetWeight");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashier, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		//Step 28 : Click on the Amount
		//Step 29 : Select a Payment method (Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 30 : Post the Invoices
		//Expected: Check final invoice details
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(5000);
		String FinalTaxInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC288_FinalTaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalTaxInvoice;
		String GrossAmount = Double.toString(ProductTotalAmount);
		String TaxablePdf = GrossAmount;
		String TotalDiscount=ProductsDiscount;   
		String TotalQtyPcs= Integer.toString(SkuList.size());
		String PaymentHDFCCard = PaymentAmount;
		String TotalValue = String.format("%.2f",
				Double.parseDouble(TaxablePdf.replaceAll("[^\\d.-]", "").trim()) +
				Math.round(Double.parseDouble(TaxablePdf.replaceAll("[^\\d.-]", "").trim()) * 0.03 * 100.0) / 100.0);
		String NetValue = Double.toString(Double.parseDouble(TotalValue) - Double.parseDouble(TotalAdjustment));
		String GppAdvance = ActualSchemeAmount;
		String GppDiscount = Double.toString(ActualBonus);
		String Adjustment = Double.toString(Double.parseDouble(NetValue) - Double.parseDouble(PaymentHDFCCard)); 
		String AdvanceAmount=InvoiceWithAdvanceSchemeSRTestData.TC288_NormalAdvanceAmount;
		String AdjustmentTotal = String.format("%.2f",
				Double.parseDouble(AdvanceAmount.replace(",", "")) +
				Double.parseDouble(GppAdvance.replace(",", "")) +
				Double.parseDouble(GppDiscount.replace(",", ""))
				);

		Map<String, String> PdfDetails = pdfUtils.SaleInvoiceValidationAdvanceSchemeAndOG(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNewNetWeightCashier,
				Double.toString(TotalGrosswt),  
				TaxablePdf,
				TotalValue,
				PaymentHDFCCard,
				Adjustment,
				AdvanceAmount,
				GppAdvance,
				GppDiscount,
				AdjustmentTotal,
				NetValue);

		Thread.sleep(3000);
		String invoiceNumber = PdfDetails.get("InvoiceNumber");
		System.out.println("Invoice Number from PDF: " + invoiceNumber);
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();  
	}  

	//summary>
		//Test Case Title: Invoice with SR and advance
		//Automation ID: TC287
		//Author: Robin T. Abraham
		//</summary>
		public void TC287_InvoiceWithAdvanceSchemeSR() throws Exception{

			// Step 1: Login to POS
			pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
			login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
			base.setZoom(driver, 60);

			//PreRequisite
			//1.Customer must be having a matured scheme 
			Thread.sleep(3000);
			Map<String, Object> GPPMaturedAccountDetails = erpUtils.GPPMaturedAccountDetails(
					InvoiceWithAdvanceSchemeSRTestData.TC287_InstallmentCount,
					InvoiceWithAdvanceSchemeSRTestData.TC287_HwManyGppAccNeeded,
					InvoiceWithAdvanceSchemeSRTestData.TC287_SchemeCode,
					InvoiceWithAdvanceSchemeSRTestData.TC287_MaturedGGScheme,
					InvoiceWithAdvanceSchemeSRTestData.TC287_AdjustMaturedGGScheme);

			// Step : Pre-Requisite for taking BoardRate
			Thread.sleep(3000);
			double ErpGoldRate24k = appUtils.FetchCurrentBoardRate(InvoiceWithAdvanceSchemeSRTestData.TC287_Purity);
			double ErpGoldRate22k = Math.round((ErpGoldRate24k * 22) / 24);
			System.out.println("BoardRate24k "+ ErpGoldRate24k);
			System.out.println("ErpGoldRate22k "+ ErpGoldRate22k);

			//Step 2 : Click on Transaction button
			appUtils.HamBurgerButtonClick("iconShop");

			List<Map<String, String>> MatchedAccounts = (List<Map<String, String>>) GPPMaturedAccountDetails.get("MatchedAccounts");     
			String CustomerNo          = "";
			String GPPAccountNumber    = "";
			String GPPAmount           = "";
			boolean ValidAccountFound  = false;
			String AdjustmentAmount    = "";
			String TotalRgWeightGG	   = "";
			String NormalAdvanceAmount = "";
			String MetalRateFetch      = "";
			String SkuGoldRateGG       = "";
			String SkuPurityGG         = "";

			for (int idx = 0; idx < MatchedAccounts.size(); idx++) {
				Map<String, String> AccountMap = MatchedAccounts.get(idx);
				CustomerNo = AccountMap.get("CustomerAccountNo");
				GPPAccountNumber = AccountMap.get("GppAccountNo");
				GPPAmount = (String) AccountMap.get("GppAmount"); 

				//EXTRA CODE To check Customer has pan number or not 
				System.out.println("\nChecking PAN for Customer: " + CustomerNo + " | GPP Account: " + GPPAccountNumber);

				appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);    
				try{appUtils.AddOrVerifyPan(); }

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

				//Select customer
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

				Map<String, Object> GPPMaturityValues = erpUtils.GPPMaturityValues(GPPAccountNumber);
				SkuPurityGG = (String) GPPMaturityValues.get("Purity");
				//TotalRgWeightReturn = (String) GPPMaturityValues.get("TotalQty");
				SkuGoldRateGG = (String) GPPMaturityValues.get("AvgRate");
				SkuGoldRateGG = SkuGoldRateGG.replace(",", "");

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
			List<String> ItemList = InvoiceWithAdvanceSchemeSRTestData.TC287_ReturnProducts;
			Map<String, Object> InvoiceDetails = erpUtils.GetInvoiceDetailsAfterNormalSaleWt(CustomerNo, ItemList );  
			String ExpectedCustomerName = pdfUtils.ExtractCustomerNameFromSaleInvoice().replaceAll("[^a-zA-Z0-9 ]","").toUpperCase().trim();

			String InvoiceNo            = (String) InvoiceDetails.get("InvoiceNo");
			String SkuPurityReturn      = (String) InvoiceDetails.get("FirstSkuPurity");
			String SkuGoldRateReturn    = (String) InvoiceDetails.get("FirstSkuGoldRate");
			String TotalRgWeightReturn  = (String) InvoiceDetails.get("TotalRgWeight");

			// Extract SKU-wise map
			Map<String, String> SkuWiseData = (Map<String, String>) InvoiceDetails.get("SKUWiseDetails");

			//  Print only SKU_1_NetWeight and SKU_1_GrossWeight
			String Sku1NetWeight   = SkuWiseData.getOrDefault("SKU_1_NetWeight", "N/A");
			String Sku1GrossWeight = SkuWiseData.getOrDefault("SKU_1_GrossWeight", "N/A");
			String Sku1StoneWeight = SkuWiseData.getOrDefault("SKU_1_StoneWeight", "N/A");
			String Sku1Total	   = SkuWiseData.getOrDefault("SKU_1_Total", "N/A");
			String Sku2NetWeight   = SkuWiseData.getOrDefault("SKU_2_NetWeight", "N/A");
			String Sku2GrossWeight = SkuWiseData.getOrDefault("SKU_2_GrossWeight", "N/A");
			String Sku2StoneWeight = SkuWiseData.getOrDefault("SKU_2_StoneWeight", "N/A");
			String Sku2Total       = SkuWiseData.getOrDefault("SKU_2_Total", "N/A");
			Map<String, String> ScannedSKUDataMapSale = (Map<String, String>) InvoiceDetails.get("ScannedSKUDataMap");
			Map<String, String> TransactionDataLineSale = (Map<String, String>) InvoiceDetails.get("TransactionDataLine");
			String LinesCountSale = TransactionDataLineSale.get("LinesCount");

			//Step 3 : Select customer
			//Step 4 : Click on add to estimation button
			Thread.sleep(3000);
			appUtils.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[5]);


			//Step 9 : Click on customer adjustment button
			//Step 10 : Click on GPP advance settlement
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
			Thread.sleep(2000);
			base.buttonClick(LoginPageObj.Edt_AlertText("GPP Advance Adjustment"));

			//Step 11 : Enter scheme ID (GG)
			//Step 12 : Click on redeem button
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), GPPAccountNumber);
			Thread.sleep(2000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("CustOrdrInput"), "ENTER");
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
			Thread.sleep(15000);

			String ActualSchemeAmount = base.GetText(InvoiceWithAdvanceSchemeAndOGObj.Ele_Scheme("tillLayout-TotalWithoutTaxField textRight","h5",1)).replaceAll("[^\\d.]", "").trim(); 
			asrt.assertEquals(ActualSchemeAmount, GPPAmount,"Scheme amount mismatch: Expected is "+GPPAmount+" but got "+ActualSchemeAmount+" in billing page");

			double SchemeAmountNow=Double.parseDouble(ActualSchemeAmount.replaceAll("[^\\d.]", "").trim());
			double SchemeGoldRate=Double.parseDouble(SkuGoldRateGG);
			TotalRgWeightGG=String.valueOf(SchemeAmountNow/SchemeGoldRate);
			System.out.println("TotalRgWeightReturn"+TotalRgWeightGG);

			//Step 5 : Click on customer adjustment button
			//Step 6 : Click on return transaction button at the billing screen
			//Step 7 : Enter receipt number
			//Step 8 : Select return product (multiple gold) and click on return button
			//Expected : The product meant to be returned should be previously bought by the same customer.
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			List<String> ToReturnProducts = Arrays.asList("Gold","Gold");
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
			ReturnDetails Details = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, ToReturnProducts);
			List<String> ReturnProductNames        = Details.productNames;
			List<String> ReturnProductQtyList      = Details.productQty;
			List<String> ReturnProductTotalList    = Details.productPrice;
			ReturnProductTotalList.replaceAll(value -> value.replaceAll("[^\\d.]", ""));
			System.out.println("ReturnProductTotalList:::" + ReturnProductTotalList);
			String ActualCustomerName  = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Customer")).replaceAll("[^a-zA-Z0-9 ]","").toUpperCase().trim();
			Thread.sleep(1000);

			asrt.assertEquals( ActualCustomerName,ExpectedCustomerName,"Customer name for sale is not the same as the customer name from return." + "ExpectedCustomerNameFrom MethodInvoicePdf: " + ExpectedCustomerName+ ", but found: " + ActualCustomerName);


			double TotalQuantity =  Double.parseDouble(TotalRgWeightReturn) + Double.parseDouble(TotalRgWeightGG) ;
			double RequiredSkuQty = Math.round(TotalQuantity / 3.0) ;
			String TotalQty22k = Double.toString(TotalQuantity) ; 
			String ErpGoldRateFor24K= erpUtils.SetMetalRateInERP(Double.toString(ErpGoldRate22k), "22k"); //ErpGoldRateFor24K
			TimeUnit.MINUTES.sleep(2); //Wait needed for ERP Gold Rate to Change
			System.out.println("ErpGoldRateFor24K1:::" + ErpGoldRateFor24K);
			double ErpGoldRateFor22k = Math.round((Double.parseDouble(ErpGoldRateFor24K) * 22) / 24);

			//Step 13 : Enter OTP (Disabled)
			//Step 14 : Scan multiple Gold SKU
			List<String> SkuList = new ArrayList<>();
			Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
			int SkuCounter = 1;

			// Gold SKUs
			SKUResult GoldSkus = appUtils.GetOGSaleSku(
					InvoiceWithAdvanceSchemeSRTestData.TC287_TransferType,
					InvoiceWithAdvanceSchemeSRTestData.TC287_FromCounter,
					InvoiceWithAdvanceSchemeSRTestData.TC287_MetalType,
					RequiredSkuQty,
					InvoiceWithAdvanceSchemeSRTestData.TC287_SkuGoldCount);

			List<String> SkuListGold = GoldSkus.PcsRateSKU;
			SkuList.addAll(SkuListGold);

			appUtils.HamBurgerButtonClick("iconShop"); 
			erpUtils.ResetRemainingReturnWeight();
			List<String> ProductNames = new ArrayList<>();
			List<String> ProductQtyList = new ArrayList<>();
			List<String> ProductTotalList = new ArrayList<>();
			List<Double> AdvanceQty = new ArrayList<>();
			List<String> AdvanceRates = new ArrayList<>();
			List<String> AdvanceTotal = new ArrayList<>();
			double ActualRgRateInItemDetailsPge =0.0;

			AdvanceQty.add(Double.parseDouble(Sku1NetWeight));
			AdvanceQty.add(Double.parseDouble(Sku2NetWeight));
			AdvanceQty.add(Double.parseDouble(TotalRgWeightGG));

			AdvanceRates.add(SkuGoldRateReturn);
			AdvanceRates.add(SkuGoldRateReturn);
			AdvanceRates.add(Double.toString(SchemeGoldRate));

			AdvanceTotal.add(Sku1Total);
			AdvanceTotal.add(Sku2Total);
			AdvanceTotal.add(ActualSchemeAmount);

			ProductNames.add(InvoiceWithAdvanceSchemeSRTestData.TC287_SchemeName);
			ProductQtyList.add(InvoiceWithAdvanceSchemeSRTestData.TC287_SchemeWeight);
			ProductTotalList.add(GPPAmount);

			ProductNames.addAll(ReturnProductNames);
			ProductQtyList.addAll(ReturnProductQtyList);
			ProductTotalList.addAll(ReturnProductTotalList); 


			//Expected : Check calculation
			for (String Sku : SkuList) {
				appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
				ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
				ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
				ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
				ActualRgRateInItemDetailsPge  = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
				double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
				double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
				String SkuPurity = ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Purity");
				Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
				if (TotalQuantity==0)
				{
					mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
				}

				//Note: Check average rate
				double Total = 0.0;                
				double ExpectedRgRate = 0.0;       
				double SaleQuantity = RGQuantity;  
				int FullyConsumedFromHead = 0;     
				List<String> AdvanceType = new ArrayList<>( Arrays.asList("SR", "SR","GG"));
				if (TotalQuantity != 0)
				{
					for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++)
					{
						double Qty  = AdvanceQty.get(j);
						double Rate = Double.parseDouble(AdvanceRates.get(j));

						if (Qty <= 0) continue;

						String AdvType = AdvanceType.get(j);  

						double EffectiveRate;

						if (AdvType.equalsIgnoreCase("SR"))
						{
							EffectiveRate = Rate;  
						}
						else
						{
							EffectiveRate = Rate; 
						}
						double UseQty = Math.min(Qty, SaleQuantity);
						Total += UseQty * EffectiveRate;
						SaleQuantity -= UseQty;
						TotalQuantity -= UseQty;
						double RemainingInRow = Qty - UseQty;
						AdvanceQty.set(j, RemainingInRow);
						if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead)
						{
							FullyConsumedFromHead++;
						}
					}
					// If advance qty finishes → use current rate
					if (SaleQuantity > 0)
					{
						Total += SaleQuantity * ErpGoldRateFor22k;
						SaleQuantity = 0;
					}
					ExpectedRgRate = Total / RGQuantity;
					// Remove consumed rows
					for (int k = 0; k < FullyConsumedFromHead; k++)
					{
						AdvanceQty.removeFirst();
						AdvanceTotal.removeFirst();
						AdvanceRates.removeFirst();
						AdvanceType.removeFirst();  
					}
					asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0,"Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");
				}
				mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, InvoiceWithAdvanceSchemeSRTestData.TC287_Purity22k , ErpGoldRateFor24K);
				SkuCounter++;		
			}

			//Step 15 : Click on GPP discount button
			//Note: Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax) *Displayed Subtotal*TAX*Total Amount*Verify the GPP Bonus amount	
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
			base.excuteJsClick(LoginPageObj.Edt_AlertText("Customer Adjustments"));
			base.excuteJsClick(LoginPageObj.Edt_AlertText("GPP Discount"));
			base.setZoom(driver, 60);
			Thread.sleep(14000);
			String Bonus = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-TotalWithoutTaxField textRight","8"));
			double BenefitWeight = Double.parseDouble(ActualSchemeAmount) / ActualRgRateInItemDetailsPge; 
			double ExpectedBonus = Double.parseDouble(mathUtils.ToGetGppBonus(ScannedSkuDataMap,SkuList.size(),InvoiceWithAdvanceSchemeSRTestData.TC287_BenefitPercentage,Double.parseDouble(TotalRgWeightGG) ,Double.parseDouble(ErpGoldRateFor24K)));
			double ActualBonus = Double.parseDouble(Bonus.replaceAll("[^\\d.]", "").trim());
			System.out.println("ActualBonus: "+ActualBonus);

			asrt.assertEquals(ActualBonus, ExpectedBonus,1,"Bonus Mismatch: Expected is "+ActualBonus+" but got "+ExpectedBonus+" in billing page");

			ProductNames.add(InvoiceWithAdvanceSchemeSRTestData.TC287_GPPBonus);  
			ProductQtyList.add(InvoiceWithAdvanceSchemeSRTestData.TC287_GPPBonusQty);
			ProductTotalList.add(Double.toString(ExpectedBonus));

			String TotalAdjustment = Double.toString((Double.parseDouble(ActualSchemeAmount)) + ActualBonus);		
			System.out.println("TotalAjustment: "+TotalAdjustment);

			//TranscationPageVerifyLines
			Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
			String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
			String TotalNetWeight = TransactionDataLine.get("NetWeight");
			String Tax = TransactionDataLine.get("Tax");
			String NetTotal = TransactionDataLine.get("NetTotal");

			List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
			String ExpectedLinesCount = Integer.toString(AllProductRows.size());
			String ActualLinesCount = TransactionDataLine.get("LinesCount");
			asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in Billing page");

			double SubTotalSum = 0.00;
			double ProductTotalAmount = 0.0;
			double	TotalGrosswt = 0.0;
			for (int i = 0; i < AllProductRows.size(); i++) {
				List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
				String ActualItemName = ItemName.get(i).getText();
				if (ProductNames.get(i).contains(ActualItemName)&& ActualItemName.length()<ProductNames.get(i).length()) {
					String ExpectedItemName = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
					//asrt.assertEquals(ActualItemName, ExpectedItemName,"Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Billing page");
					asrt.assertTrue(ExpectedItemName.contains(ActualItemName), "Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Billing page");			
					ProductTotalAmount = ProductTotalAmount+Double.parseDouble(ProductTotalList.get(i));
					TotalGrosswt =  TotalGrosswt +  Double.parseDouble (ProductQtyList.get(i));
					System.out.println("ProductTotalAmountif: "+ProductTotalAmount);
					System.out.println("TotalGrosswtif: "+TotalGrosswt);

				} else {
					String ExpectedItemName = ProductNames.get(i).trim();
					asrt.assertEquals(ActualItemName, ExpectedItemName,"Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Billing page");
				}
				List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
				String ActualQuantity = Quantity.get(i).getText().replace("-", "");
				String ExpectedQuantity = ProductQtyList.get(i);
				double ActualQty = Double.parseDouble(ActualQuantity);
				double ExpectedQty = Double.parseDouble(ExpectedQuantity);
				asrt.assertEquals(ActualQty, ExpectedQty, 1.000, "Quantity mismatch: Expected is "+ ExpectedQty + " but got " + ActualQty + " in Billing page");

				List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
				String TotalAmountWithoutTax = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
				String ActualTotalWithoutTax = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
				SubTotalSum = SubTotalSum+Double.parseDouble(TotalAmountWithoutTax);
				String ExpectedTotalWithoutTax = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
				asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTax.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTax),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTax+" but got "+ActualTotalWithoutTax+ " in Billing page");
			}
			String ExpectedSubTotal = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();;
			String ActualSubTotal = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
			asrt.assertEquals(Double.parseDouble(ActualSubTotal),Double.parseDouble(ExpectedSubTotal), 1.0,"Subtotal mismatch: " + ExpectedSubTotal + " but found " + ActualSubTotal+ " in Billing page");
			String TaxInPge = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
			double ActualTax = Double.parseDouble(TaxInPge);

			double ProductTotalAmnt = ProductTotalAmount -(Double.parseDouble(ProductTotalList.get(1)) + Double.parseDouble(ProductTotalList.get(2)));
			System.out.println("ProductTotalAmountlast: "+ProductTotalAmnt);
			double ExpectedTax = ProductTotalAmnt*0.03;
			String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
			asrt.assertEquals(ActualTax,ExpectedTax, 1.0,"Tax amount mismatch: " + ExpectedTax + " but found " + ActualTax+ " in Billing page");

			double ExpectedTotalAmount = SubTotalSum+ ActualTax;
			double ActualTotalAmount = Double.parseDouble(TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.-]", "").trim());
			asrt.assertEquals(ActualTotalAmount, ExpectedTotalAmount, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmount + " but found " + ActualTotalAmount + " in Billing page");

			//Step 16 : Save estimate
			//Step 17 : Recall estimate from cashier screen
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
			String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC287_ProformaInvoice");
			Thread.sleep(2000);
			String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
			System.out.println("ProFormaInvoicePath:::"+ ProFormaInvoicePath);
			String Gst               = Double.toString(ExpectedTax);
			String TaxableValue      = Double.toString(ProductTotalAmnt)  ;
			double InvoiceTotalValue = ProductTotalAmnt + ExpectedTax ;
			String InvoiceTotal      = String.format("%.2f", InvoiceTotalValue);
			String NetTotalPdf       = String.format("%.2f", ActualTotalAmount);
			String TotalAmnt         = String.format("%.2f", ActualTotalAmount);

			pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
					ProFormaInvoicePath, 
					TaxableValue, 
					Gst,
					InvoiceTotal, 
					TotalAdjustment,
					NetTotalPdf,
					TotalAmnt);

			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
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
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
			String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

			asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

			Thread.sleep(5000);
			List<String> ItemNamesFromRecallPageBeforeExchange = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
			System.out.println(ItemNamesFromRecallPageBeforeExchange);
			System.out.println(ItemNamesInBillingPage);

			asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPageBeforeExchange), "Recalled item is not same as the item in the Cart");

			//Step 19 : Select the Transaction Type as (Sales return, sale)
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAdvanceSchemeSRTestData.TC287_TransactionTypeSR);

			//Step 20 : Click on Process Estimation
			//Step 21 : Choose a sales representative
			// Expected Result: Cashier Screen after recall*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount

			//SalesReturn
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			base.setZoom(driver, 60);
			Thread.sleep(3000);
			Map<String, String> TransactionRecallDataLineReturn = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
			String SubTotalReturn 		   = TransactionRecallDataLineReturn.get("Subtotal");
			String DiscountReturn 		   = TransactionRecallDataLineReturn.get("Discount");
			String TotalGrossWeightReturn  = TransactionRecallDataLineReturn.get("GrossWeight");
			String TotalNewNetWeightReturn = TransactionRecallDataLineReturn.get("NetWeight");
			String TaxReturn 			   = TransactionRecallDataLineReturn.get("Tax");
			String TotalAmountReturn	   = TransactionRecallDataLineReturn.get("TotalAmount").replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim();
			String NetTotalReturn 		   = TransactionRecallDataLineReturn.get("NetTotal").replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim();
			String LinesCountReturn 	   = TransactionRecallDataLineReturn.get("LinesCount");

			mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLineReturn, ScannedSKUDataMapSale);

			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

			// Step 18: post sales return bill
			// Expected Result: Check Return Invoice details
			pdfUtils.DownloadAndRenameLatestPDF("TC287SaleReturnInvoice");

			Map<String, String> result = pdfUtils.SalesReturnPDFCredit();
			String ExpSerialNoReturnSale      = result.get("SerialNo");
			String ExpTotalProductsReturnSale = result.get("TotalProducts");
			String ExpTotalQtyReturnSale      = result.get("TotalQty");
			String ExpGrossAmountReturnSale   = result.get("GrossAmount");
			String ExpTotalNetWtReturnSale    = result.get("TotalNetWt");
			String ExpTotalGrossWtReturnSale  = result.get("TotalGrossWt");
			String ExpTotalNetValueReturnSale = result.get("TotalNetValue");
			String ExpTaxableValueReturnSale  = result.get("TaxableValue");
			String ExpAdjustmentReturnSale    = result.get("PaymentDetails");
			String ExpTotalValueReturnSale    = result.get("TotalValue");
			int TotalProductsSale             = Integer.parseInt(result.get("TotalProducts"));

			// Loop through each SKU/Product
			for (int i = 1; i <= TotalProductsSale; i++) {
				double expectedGrossWt = Double.parseDouble(result.get("Product" + i + ".GrossWt"));
				double expectedStoneWt = Double.parseDouble(result.get("Product" + i + ".StoneWt"));
				double expectedNetWt   = Double.parseDouble(result.get("Product" + i + ".NetWt"));
				double expectedAmount  = Double.parseDouble(result.get("Product" + i + ".Amount"));

				double actualGrossWt = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_GrossWeight", "0"));
				double actualStoneWt = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_StoneWeight", "0"));
				double actualNetWt   = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_NetWeight", "0"));
				double actualAmount  = Double.parseDouble(SkuWiseData.getOrDefault("SKU_" + i + "_Total", "0"));

				Assert.assertEquals(actualGrossWt, expectedGrossWt, 1.0, "Mismatch in Gross Weight for SKU_" + i + " in PDF Sale Return Invoice details");
				Assert.assertEquals(actualStoneWt, expectedStoneWt, 1.0, "Mismatch in Stone Weight for SKU_" + i + " in PDF Sale Return Invoice details");
				Assert.assertEquals(actualNetWt, expectedNetWt, 1.0, "Mismatch in Net Weight for SKU_" + i + " in PDF Sale Return Invoice details");
				Assert.assertEquals(actualAmount, expectedAmount, 1.0, "Mismatch in Total Amount for SKU_" + i + " in PDF Sale Return Invoice details");

			}
			double ActualTaxableValue 		= Math.abs(Double.parseDouble(((String) InvoiceDetails.get("SubTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim()));
			double ExpectedTaxableValue  	= Double.parseDouble(ExpTaxableValueReturnSale);

			double ActualGrossAmount 		= Math.abs(Double.parseDouble(((String) InvoiceDetails.get("SubTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim()));
			double ExpectedGrossAmount 		= Double.parseDouble(ExpGrossAmountReturnSale);

			double ActualTotalGrossWeight   = Math.abs(Double.parseDouble(((String) InvoiceDetails.get("TotalGrossWeight")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim()));
			double ExpectedTotalGrossWeight = Double.parseDouble(ExpTotalGrossWtReturnSale);

			double ActualNetWeight			= Double.parseDouble(((String) InvoiceDetails.get("TotalNetWeight")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
			double ExpectedNetWeight 		= Double.parseDouble(ExpTotalNetWtReturnSale);

			double ActualNetTotal 			= Double.parseDouble(((String) InvoiceDetails.get("NetTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
			double ExpectedNetTotal 		= Double.parseDouble(ExpTotalNetValueReturnSale);

			Assert.assertEquals(ActualTaxableValue, ExpectedTaxableValue,1, "Mismatch in Taxable Value Actual "+ActualTaxableValue+" and Expected Taxable Value "+ExpectedTaxableValue+"in PDF Sale Return Invoice details");
			Assert.assertEquals(ActualGrossAmount, ExpectedGrossAmount,1, "Mismatch in Gross Amount Actual "+ActualGrossAmount+" and Expected Gross Amount "+ExpectedGrossAmount+"in PDF Sale Return Invoice details");
			Assert.assertEquals(LinesCountReturn, ExpTotalQtyReturnSale, "Mismatch in No of Product Lines Actual "+LinesCountReturn+" and Expected No of Product Lines "+ExpTotalProductsReturnSale+"in PDF Sale Return Invoice details");
			Assert.assertEquals(ActualNetWeight, ExpectedNetWeight,1, "Mismatch in Total Net Weight Actual "+ActualNetWeight+" and Expected Net Weight "+ExpectedNetWeight+"in PDF Sale Return Invoice details");
			Assert.assertEquals(ActualTotalGrossWeight, ExpectedTotalGrossWeight,1, "Mismatch in Total Gross Weight Actual "+ActualTotalGrossWeight+" and Expected Total Gross Weight"+ExpectedTotalGrossWeight+"in PDF Sale Return Invoice details");
			Assert.assertEquals(ActualNetTotal, ExpectedNetTotal,1, "Mismatch in Net Total Actual "+ActualNetTotal+" and Expected Net Total "+ExpectedNetTotal+"in PDF Sale Return Invoice details");

			//Sales
			Thread.sleep(3000);
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

			//Step 19 : Select the Transaction Type as (Sales return, sale)
			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceWithAdvanceSchemeSRTestData.TC287_TransactionTypeSale);

			//Step 20 : Click on Process Estimation
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

			//Step 21 : Choose a sales representative
			base.setZoom(driver, 60);
			Thread.sleep(3000);
			appUtils.HandleSpecifyRateModalInRecall();

			String ProductsDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4"));
			String PaymentAmountHDFC = base.GetText(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
			Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap); //need to remove
			String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
			String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");

			//removes the SR products
			ProductNames.remove(2);
			ProductNames.remove(1);

			ProductQtyList.remove(2);
			ProductQtyList.remove(1);

			ProductTotalList.remove(2);
			ProductTotalList.remove(1);

			Map<String, String> TransactionDataLineCashier = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
			String TotalGrossWeightCashier   = TransactionDataLineCashier.get("GrossWeight");
			String TotalNewNetWeightCashier  = TransactionDataLineCashier.get("NetWeight");
			System.out.println("TotalGrossWeightCashier:::"+ TotalGrossWeightCashier);
			System.out.println("TotalNewNetWeightCashier:::"+ TotalNewNetWeightCashier);

			appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineCashier, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

			//Step 22 : Click on the Amount
			//Step 23 : Select a Payment method (Cash or Card)
			String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

			//Step 24 : Post the Invoices
			//Expected: Check final invoice details
			pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			Thread.sleep(5000);
			String FinalTaxInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC287_FinalTaxInvoice");
			Thread.sleep(2000);
			String InvoicePdfPath  = System.getProperty("user.dir") + "\\Invoices\\" + FinalTaxInvoice;
			String GrossAmount     = Double.toString(ProductTotalAmount);
			String TaxablePdf      = GrossAmount;
			String TotalDiscount   = ProductsDiscount;   
			String TotalQtyPcs     = Integer.toString(SkuList.size());
			String PaymentHDFCCard = PaymentAmount;
			String TotalValue      = String.format("%.2f",
					Double.parseDouble(TaxablePdf.replaceAll("[^\\d.-]", "").trim()) +
					Math.round(Double.parseDouble(TaxablePdf.replaceAll("[^\\d.-]", "").trim()) * 0.03 * 100.0) / 100.0);
			String NetValue        = Double.toString(Double.parseDouble(TotalValue) - Double.parseDouble(TotalAdjustment));
			String GppAdvance      = ActualSchemeAmount;
			String GppDiscount     = Double.toString(ActualBonus);
			String Adjustment      = Double.toString(Double.parseDouble(NetValue) - Double.parseDouble(PaymentHDFCCard)); 
			String AdvanceAmount   = "";
			String AdjustmentTotal = String.format("%.2f",
					Double.parseDouble(GppAdvance.replace(",", "")) +
					Double.parseDouble(GppDiscount.replace(",", "")));

			Map<String, String> PdfDetails = pdfUtils.SaleInvoiceValidationAdvanceScheme(
					InvoicePdfPath,
					GrossAmount,
					TotalDiscount,
					TotalQtyPcs,
					TotalNewNetWeightCashier,
					Double.toString(TotalGrosswt),  
					TaxablePdf,
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
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));

			// Cleanup PDF files
			pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		} 
}
