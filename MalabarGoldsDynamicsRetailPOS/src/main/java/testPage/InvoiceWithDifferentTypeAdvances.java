package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceRepayment_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceWithDifferentTypeAdvances_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
import testData.GppOpeningAndCollectionMultiplePlans_TestData;
import testData.InvoiceWithDifferentTypeAdvances_TestData;
import testData.PANCardLimitValidation_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.RequiredSKUResult;
import utilPack.AppUtilities.ReturnDetails;
import utilPack.AppUtilities.SKUResult;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.ErpUtilities.GPPAccountDetails;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

//import basePackage.BasePge;

public class InvoiceWithDifferentTypeAdvances extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	GPPSettlementDiscountPlan_TestData GPPSettlementDiscountPlanTestData =new GPPSettlementDiscountPlan_TestData();
	PANCardLimitValidation_TestData PANCardLimitValidationTestData = new PANCardLimitValidation_TestData();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	InvoiceWithDifferentTypeAdvances_TestData InvoiceWithDifferentTypeAdvancesTestData = new InvoiceWithDifferentTypeAdvances_TestData();
	InvoiceWithDifferentTypeAdvances_Obj InvoiceWithDifferentTypeAdvancesObj = new InvoiceWithDifferentTypeAdvances_Obj();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj = new BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj();
	AdvanceRepayment_Obj AdvanceRepaymentObj=new AdvanceRepayment_Obj();

	public InvoiceWithDifferentTypeAdvances(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	// <summary>
	// Test Case Title : Invoice with multiple normal advance and offer advance
	// Automation ID : TC193
	// Author: Chandana Babu
	// </summary>
	public void TC193_InvoiceWithDifferentTypeAdvances() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, Object> Result = erpUtils.ToFindCustomerWithMultipleNormalOfferAdvance(
				InvoiceWithDifferentTypeAdvancesTestData.TC193_NormalAdvanceCount, 
				InvoiceWithDifferentTypeAdvancesTestData.TC193_Normal, 
				InvoiceWithDifferentTypeAdvancesTestData.TC193_OfferAdvanceCount, 
				InvoiceWithDifferentTypeAdvancesTestData.TC193_Offer,
				InvoiceWithDifferentTypeAdvancesTestData.TC193_RequiredCustomerAccounts);
		List<String> Accounts = (List<String>) Result.get("Accounts");
		Accounts.addFirst(InvoiceWithDifferentTypeAdvancesTestData.TC193_CustomerAccount);
		List<String> OfferId = new ArrayList<>();
		List<String> NormalTransactionNumber = new ArrayList<>();
		int CheckedCustomer = 0;

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		for (int i = 0; i<Accounts.size();i++) {
			appUtils.SearchByCustomerID(Accounts.get(i),UtilityTestData.MenuBarOptions[5]);
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
			Thread.sleep(4000);
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),
					UtilityTestData.WarehouseId);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
			Thread.sleep(3000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) {
				List<WebElement>NormalAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
				int NormalAdvanceRowSize = NormalAdvanceRows.size();
				if(NormalAdvanceRowSize <= 1) {
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					continue;			
				}
				else {
					int Normalcount = 0;
					List<WebElement>NormalAdvanceBalanceGoldQty = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance gold qty", "h4 ellipsis cell"));
					for (int j = 0; j<NormalAdvanceRowSize;j++) {
						String TransactionNumber = NormalAdvanceRows.get(j).getText();
						double BalanceGoldQty = Double.parseDouble(NormalAdvanceBalanceGoldQty.get(j).getText());
						if (TransactionNumber.matches("^\\d{4}-\\d{4}[A-Za-z]\\d{2}-\\d{13}$") &&  BalanceGoldQty < 10) {
							NormalTransactionNumber.add(TransactionNumber);
							Normalcount++;
						}
						if (Normalcount == 2) {
							break;
						}
					}
					if( Normalcount != 2) {
						appUtils.HamBurgerButtonClick("iconShop");
						appUtils.VoidTransaction();
						continue;
					}
				}
			}
			appUtils.HamBurgerButtonClick("iconShop");
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
			Thread.sleep(7000);
			WebElement Dropdown = driver.findElement(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
			Select Select = new Select(Dropdown);
			List<WebElement> AllOptions = Select.getOptions();
			List<String> OptionTexts = new ArrayList<>();
			for (WebElement Option : AllOptions) {
				OptionTexts.add(Option.getText());
			}
			int Count = 0;
			for(int j=1; j<OptionTexts.size();j++) {
				base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
				base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"),OptionTexts.get(j));
				Thread.sleep(2000);
				if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) {
					List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
					int OfferAdvanceRowSize = OfferAdvanceRows.size();
					if(OfferAdvanceRowSize >= 1) {
						OfferId.add(OptionTexts.get(j));
						Count++;		
					}
					if(Count==2) {
						break;				
					}
				}
			}
			if (Count <= 1) {
				appUtils.HamBurgerButtonClick("iconShop");
				appUtils.VoidTransaction();
				CheckedCustomer++;
			}
			else {
				appUtils.HamBurgerButtonClick("iconShop");
				break;
			}
		}
		if(CheckedCustomer== Accounts.size()) {
			System.out.println("No data found");
		}

		//Step 5: Click on customer adjustment button
		//Step 6: click on normal advance settlement
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		//Step 7: Select advance and click on redeem button
		//Step 8: Enter OTP
		//Note: Again add another normal & offer advance (continue step 5-8)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));;
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> AdvanceRates = new ArrayList<>();
		List<Double> AdvanceQty = new ArrayList<>();
		List<String> AdvanceTotal = new ArrayList<>();
		double TotalQuantity = 0.0;
		double TotalAdvanceAmount=0.0;
		double Quantity = 0.0;
		for(int i =0; i< InvoiceWithDifferentTypeAdvancesTestData.TC193_NormalAdvanceCount;i++) {
			Thread.sleep(1000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
			Thread.sleep(4000);
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),NormalTransactionNumber.get(i));
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
			Thread.sleep(1000);
			base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
			String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
			String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
			String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			Thread.sleep(1000);
			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Normal advance amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			Quantity= Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		}
		Map<String, List<String>> ValidId = erpUtils.ToFindOfferPercentageForOfferAdvance(OfferId);
		for(int j =0; j<OfferId.size();j++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"),OfferId.get(j));
			String BalanceAmount = "";
			String AdvanceRate = "";
			String AdjustmentAmount="";
			List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
			int OfferAdvanceRowSize = OfferAdvanceRows.size();
			if (OfferAdvanceRowSize==1) {
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",OfferId.get(j)));
				BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
				AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			}
			else {				
				base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_Offer("h4 ellipsis cell", OfferId.get(j), "Gold booked qty 0"));
				BalanceAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
				AdvanceRate = base.GetText(InvoiceWithDifferentTypeAdvancesObj.Ele_OfferAdvanceRate("Gold booked qty 0", "Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));

			}
			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Offer advance amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			if(ValidId.get("OfferID"+j).get(1)=="false")
			{
				Quantity = (Double.parseDouble(AdjustmentAmount))/Double.parseDouble(AdvanceRate);
			}
			else {
				Quantity = ((Double.parseDouble(AdjustmentAmount)) *100)/(Double.parseDouble(AdvanceRate)*Double.parseDouble(ValidId.get("OfferID"+j).get(0)));
			}
			//Quantity = Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		}

		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 120);
		double CurrentRate24K = appUtils.FetchCurrentBoardRate("24k");
		double CurrentRate22K =  Math.round((CurrentRate24K*22)/24);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 9: Scan multiple product SKU
		//Expected: Check calculation
		//Note: Check average rate
		//Note: Billing Screen No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Subtotal,TAX,Total Amount
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC193_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC193_FromCounterGold,
				InvoiceWithDifferentTypeAdvancesTestData.TC193_MetalType,
				TotalQuantity,
				InvoiceWithDifferentTypeAdvancesTestData.TC193_ProductCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");	
		List<String> SkuListPrecia = appUtils.SelectMultipleSKUs(
				InvoiceWithDifferentTypeAdvancesTestData.TC193_ProductCount,
				InvoiceWithDifferentTypeAdvancesTestData.TC193_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC193_FromCounterPrecia,
				InvoiceWithDifferentTypeAdvancesTestData.TC193_MetalType);
		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListGold);
		SkuList.addAll(SkuListPrecia);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i <InvoiceWithDifferentTypeAdvancesTestData.TC193_AdvanceCount ; i++) {
			ProductNames.add(InvoiceWithDifferentTypeAdvancesTestData.TC193_AdvanceName);
			ProductQtyList.add(InvoiceWithDifferentTypeAdvancesTestData.TC193_AdvanceQuantity);
		}
		int SkuCounter = 1;
		double TotalTaxableAmount=0.0;
		double GrossWeight=0.0;
		String TotalAdvanceQuantity = String.format("%.2f",TotalQuantity);
		double RemainingQty = 0.0;
		boolean ExpectedCondition=false;
		for (int i = 0; i < SkuList.size(); i++) {
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalTaxableAmount = TotalTaxableAmount+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			GrossWeight = GrossWeight+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			if (TotalQuantity==0) {
				mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			}
			double Total = 0.0;                
			double ExpectedRgRate = 0.0;       
			double SaleQuantity = RGQuantity;  
			int FullyConsumedFromHead = 0;     

			if (TotalQuantity != 0) {
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++) {
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));
					double EffectiveRate = Math.min(Rate, CurrentRate22K);
					if (Qty <= 0) continue;
					double UseQty = Math.min(Qty, SaleQuantity);   
					Total += UseQty * EffectiveRate;
					SaleQuantity -= UseQty;
					TotalQuantity -= UseQty;
					double RemainingInRow = Qty - UseQty;
					AdvanceQty.set(j, RemainingInRow);
					if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead) {
						FullyConsumedFromHead++;
					}
				}
				if (SaleQuantity > 0) {
					Total += SaleQuantity * CurrentRate22K;
					SaleQuantity = 0;
				}
				ExpectedRgRate = Total / RGQuantity;
				for (int k = 0; k < FullyConsumedFromHead; k++) {
					AdvanceQty.removeFirst();
					AdvanceTotal.removeFirst();
					AdvanceRates.removeFirst();
				}
				asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0,"Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");
				mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, "22k", Double.toString(CurrentRate24K));
			}
			SkuCounter++;		
		}
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10: save esimate
		//EXpected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC193_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = String.format("%.2f",TotalTaxableAmount);
		String InvoiceTotal = String.format("%.2f",(TotalTaxableAmount+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String TotalAdjustment = Double.toString(TotalAdvanceAmount);
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment,
				NetTotal,
				TotalAmt);

		//Step 11: Recall estimate from cashier screen		
		//Step 12: Select estimate and click on recall estimation
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 13: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithDifferentTypeAdvancesTestData.TC193_TransactionType);

		//Step 14: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 16: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, AX, Total Amount
		//Step 17: Select a Payment method(Cash or Card)
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");
		String PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 18: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC193TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TaxableValue;
		String TotalDiscount = Discount;
		String TotalQtyPcs = Integer.toString(SkuList.size());
		String NetValue = NetTotal;
		String PaymentHDFCCard = PaymentAmountForSale;
		String Adjustment = "";
		String ActualAdvanceAmount = Double.toString(TotalAdvanceAmount);
		String TotalGrossWeight = Double.toString(GrossWeight);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationTaxInvoice(
				InvoicePdfPath, 
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs, 
				TotalNetWeight, 
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentHDFCCard, 
				ActualAdvanceAmount, 
				SkuList, 
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Invoice with multiple normal advance and converted advance
	// Automation ID : TC194
	//Prerequisite : Cust
	// Author: Sangeetha M S
	// </summary>
	public void TC194_InvoiceWithDifferentTypeAdvances() throws Exception 
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step : Pre-Requisite for taking BoardRate
		Thread.sleep(1000);
		double CurrentRate24K = appUtils.FetchCurrentBoardRate("24k");
		double CurrentRate22K =  Math.round((CurrentRate24K*22)/24);

		//Step 3. Select customer
		//Step 4. Click on add to estimation button
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(InvoiceWithDifferentTypeAdvancesTestData.TC194_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5. Click on customer adjustment button
		//Step 6. click on normal advance settlement
		//Expected : The balance amount and adjustment amount for the selected advances should be shown correctly
		//Step 7. Select advance and click on redeem button		
		//Step 8. Enter OTP(Not Available)
		// Note: Again add another normal & converted advance
		// (continue step 5-8)
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> AdvanceRates = new ArrayList<>();
		List<Double> AdvanceQty = new ArrayList<>();
		List<String> AdvanceTotal = new ArrayList<>();
		List<String> TransactionNumber = new ArrayList<>();

		double TotalQuantity = 0.0;
		double TotalAdvanceAmount=0.0;
		double Quantity = 0.0;
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));	

		//For taking Converted Advance 
		for(int i =0;  i < 2; i++)
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			Thread.sleep(3000);
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),"CAN");
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
			Thread.sleep(1000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) 
			{
				List<WebElement>ConvertedAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
				int ConvertedAdvanceRowSize = ConvertedAdvanceRows.size();
				if(ConvertedAdvanceRowSize <= 1) 
				{
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					System.out.println("No Converted advance found for customer id"+InvoiceWithDifferentTypeAdvancesTestData.TC194_CustomerNo);								
				}
				else
				{
					int Count = 0;
					List<WebElement>NormalAdvanceBalanceGoldQty = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance gold qty", "h4 ellipsis cell"));
					for (int j = 0; j<ConvertedAdvanceRowSize;j++) 
					{
						String ConvertedTransactionNumber = ConvertedAdvanceRows.get(j).getText();
						double BalanceGoldQty = Double.parseDouble(NormalAdvanceBalanceGoldQty.get(j).getText());
						if (ConvertedTransactionNumber.matches("^\\d{2}CAN\\d{9}$") &&  BalanceGoldQty < 10)
						{
							TransactionNumber.add(ConvertedTransactionNumber);
							Count++;
						}
						if (Count == 2)
						{
							break;
						}
					}
				}
			}
			base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
			String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
			String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
			String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			Thread.sleep(1000);

			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Normal advance amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			Quantity= Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));				
		}

		//For taking two normal advance
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));	
		for (int i = 0;  i < 2; i++)
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
			Thread.sleep(3000);
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.WarehouseId);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
			Thread.sleep(3000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) 
			{
				List<WebElement>NormalAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
				int NormalAdvanceRowSize = NormalAdvanceRows.size();
				if(NormalAdvanceRowSize <= 1) 
				{
					appUtils.HamBurgerButtonClick("iconShop");
					appUtils.VoidTransaction();
					System.out.println("No Normal advance found for customer id"+InvoiceWithDifferentTypeAdvancesTestData.TC194_CustomerNo);				
				}
				else
				{
					int Normalcount = 0;
					List<WebElement>NormalAdvanceBalanceGoldQty = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance gold qty", "h4 ellipsis cell"));
					for (int j = 0; j<NormalAdvanceRowSize;j++) 
					{
						String NormalTransactionNumber = NormalAdvanceRows.get(j).getText();
						double BalanceGoldQty = Double.parseDouble(NormalAdvanceBalanceGoldQty.get(j).getText());
						if (NormalTransactionNumber.matches("^\\d{4}-\\d{4}[A-Za-z]\\d{2}-\\d{13}$") &&  BalanceGoldQty < 10)
						{
							TransactionNumber.add(NormalTransactionNumber);
							Normalcount++;
						}
						if (Normalcount == 2)
						{
							break;
						}
					}
				}
			}
			Thread.sleep(1000);
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),TransactionNumber.get(i));
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
			Thread.sleep(1000);
			base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
			String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
			String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
			String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			Thread.sleep(1000);

			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Converted advance amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			Quantity= Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		}
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));

		//Step 9. Scan multiple product SKU
		//Step Note: Check average rate
		//Expected : Check calculation Billing Screen *No of Product lines,*Displayed Item Name, *Displayed Quantity,*Displayed TOTAL (without Tax),*Displayed Subtotal
		//           *TAX,*Total Amount
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC194_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC194_FromCounterGold,
				InvoiceWithDifferentTypeAdvancesTestData.TC194_MetalType,
				TotalQuantity,
				InvoiceWithDifferentTypeAdvancesTestData.TC194_ProductCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");	
		List<String> SkuListPrecia = appUtils.SelectMultipleSKUs(
				InvoiceWithDifferentTypeAdvancesTestData.TC194_ProductCount,
				InvoiceWithDifferentTypeAdvancesTestData.TC194_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC194_FromCounterPrecia,
				InvoiceWithDifferentTypeAdvancesTestData.TC194_MetalType);
		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListGold);
		SkuList.addAll(SkuListPrecia);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i <InvoiceWithDifferentTypeAdvancesTestData.TC194_AdvanceCount ; i++) 
		{
			ProductNames.add(InvoiceWithDifferentTypeAdvancesTestData.TC193_AdvanceName);
			ProductQtyList.add(InvoiceWithDifferentTypeAdvancesTestData.TC194_AdvanceQuantity);
		}
		int SkuCounter = 1;
		double TotalTaxableAmount=0.0;
		double GrossWeight=0.0;

		for (int i = 0; i < SkuList.size(); i++) 
		{
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalTaxableAmount = TotalTaxableAmount+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			GrossWeight = GrossWeight+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			if (TotalQuantity==0)
			{
				mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			}
			double Total = 0.0;                
			double ExpectedRgRate = 0.0;       
			double SaleQuantity = RGQuantity;  
			int FullyConsumedFromHead = 0;     
			List<String> AdvanceType = new ArrayList<>( Arrays.asList("Converted", "Converted", "Normal", "Normal"));
			if (TotalQuantity != 0)
			{
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++)
				{
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));

					if (Qty <= 0) continue;

					String AdvType = AdvanceType.get(j);  

					double EffectiveRate;

					if (AdvType.equalsIgnoreCase("Converted"))
					{
						EffectiveRate = Rate;  
					}
					else if (AdvType.equalsIgnoreCase("Normal"))
					{
						EffectiveRate = Math.min(Rate, CurrentRate22K);
					}
					else
					{
						EffectiveRate = Math.min(Rate, CurrentRate22K);
					}
					double UseQty = Math.min(Qty, SaleQuantity);//Variable Advanceqty
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
					Total += SaleQuantity * CurrentRate22K;
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
			mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, InvoiceWithDifferentTypeAdvancesTestData.TC194_Purity , Double.toString(CurrentRate24K));
			SkuCounter++;		
		}
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10. save estimate
		//Expected : Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC194_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = String.format("%.2f",TotalTaxableAmount);
		String InvoiceTotal = String.format("%.2f",(TotalTaxableAmount+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String TotalAdjustment = Double.toString(TotalAdvanceAmount);
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment,
				NetTotal,
				TotalAmt);

		//Step 11.Recall estimate from cashier screen
		//Step 12. Select estimate and click on Recall estimation button
		//Expected : Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 13.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithDifferentTypeAdvancesTestData.TC193_TransactionType);

		//Step 14.Click on Process Estimation 
		//Step 15.Choose a sales representative(Not available after Data refresh)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 16.Click on the Amount
		//Step 17.Select a Payment method(Cash or Card)
		//Expected : Cashier Screen after recall  *No of Product lines,*Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax)
		//           *Displayed Subtotal,*TAX,*Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");
		String PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 18.Post the Invoice
		//Expected : Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC194TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TaxableValue;
		String TotalDiscount = Discount;
		String TotalQtyPcs = Integer.toString(SkuList.size());
		String NetValue = NetTotal;
		String PaymentHDFCCard = PaymentAmountForSale;
		String Adjustment = "";
		String ActualAdvanceAmount = Double.toString(TotalAdvanceAmount);
		String TotalGrossWeight = Double.toString(GrossWeight);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationTaxInvoice(
				InvoicePdfPath, 
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs, 
				TotalNetWeight, 
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentHDFCCard, 
				ActualAdvanceAmount, 
				SkuList, 
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Invoice with multiple offer advance and add on advance
	// Automation ID : TC196
	// Author: Benson
	// </summary>
	public void TC196_InvoiceWithDifferentTypeAdvances() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(InvoiceWithDifferentTypeAdvancesTestData.TC196_CustomerNo, UtilityTestData.MenuBarOptions[5]);
		List<String> OfferId = new ArrayList<>();
		List<String> AddonId=new ArrayList<>();
		List<String> TotalAdvanceId=new ArrayList<>();  
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
		Thread.sleep(7000);

		WebElement Dropdown = driver.findElement(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
		Select Select = new Select(Dropdown);
		List<WebElement> AllOptions = Select.getOptions();
		List<String> OptionTexts = new ArrayList<>();
		for (WebElement Option : AllOptions) {
			OptionTexts.add(Option.getText());
		}
		int OfferCount = 0;
		int AddOnCount=0;
		for (int j = OptionTexts.size() - 1; j >= 1; j--) { //reverse for loop
			//for(int j=1; j<OptionTexts.size();j++) {
			base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
			Thread.sleep(7000);
			driver.findElement(InvoiceWithDifferentTypeAdvancesObj.Ele_CustomerOrderNoDropDown("Customer order no")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated( InvoiceWithDifferentTypeAdvancesObj.Ele_OrderOptions("options")));
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"),OptionTexts.get(j));
			Thread.sleep(2000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) {
				List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
				int OfferAdvanceRowSize = OfferAdvanceRows.size();
				// ADD-ON type (>1 rows)
				if(OfferAdvanceRowSize > 1 && AddOnCount < 2) {
					AddonId.add(OptionTexts.get(j));
					AddOnCount++;
				}

				// OFFER type (==1 row)
				if(OfferAdvanceRowSize == 1 && OfferCount < 2) {
					OfferId.add(OptionTexts.get(j));
					OfferCount++;
				}

				// Stop only when BOTH conditions satisfied
				if(AddOnCount == 2 && OfferCount == 2) {
					break;
				}
			}

		}

		if (OfferCount < 2 || AddOnCount < 2) {
			System.out.println(
					"Insufficient advance records. OfferCount=" + OfferCount +
					", AddOnCount=" + AddOnCount
					);
			throw new RuntimeException("Insufficient offer/add-on advance records.");
		}
		TotalAdvanceId.addAll(OfferId);
		TotalAdvanceId.addAll(AddonId);

		Map<String, List<String>> ValidId = erpUtils.ToFindOfferPercentageForOfferAdvance(TotalAdvanceId);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 5: Click on customer adjustment button
		//Step 6: click on offer advance settlement
		//Step 7: Select advance and click on redeem button
		//Step 8: Enter OTP
		//Note: Again add another add on advance
		//(continue step 5-8)
		//Expected:The balance amount and adjustment amount for the selected advances should be shown correctly
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));;
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> AdvanceRates = new ArrayList<>();
		List<Double> AdvanceQty = new ArrayList<>();
		List<String> AdvanceTotal = new ArrayList<>();
		double TotalQuantity = 0.0;
		double TotalAdvanceAmount=0.0;
		double Quantity = 0.0;

		//for (int j = TotalAdvanceId.size() - 1; j >= 0; j--) {   //reverse for loop
		for(int j =0; j<TotalAdvanceId.size();j++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
			base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
			Thread.sleep(7000);
			driver.findElement(InvoiceWithDifferentTypeAdvancesObj.Ele_CustomerOrderNoDropDown("Customer order no")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated( InvoiceWithDifferentTypeAdvancesObj.Ele_OrderOptions("options")));
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"),TotalAdvanceId.get(j));
			String BalanceAmount = "";
			String AdvanceRate = "";
			String AdjustmentAmount="";
			List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
			int OfferAdvanceRowSize = OfferAdvanceRows.size();
			if (OfferAdvanceRowSize==1) {
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",TotalAdvanceId.get(j)));
				BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
				AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			}
			else {				
				base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_Offer("h4 ellipsis cell", TotalAdvanceId.get(j), "Gold booked qty 0"));
				BalanceAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
				AdvanceRate = base.GetText(InvoiceWithDifferentTypeAdvancesObj.Ele_OfferAdvanceRate("Gold booked qty 0", "Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));

			}
			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Offer advance amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			if(ValidId.get("OfferID"+j).get(1)=="false") {
				Quantity = Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
			}
			else {
				Quantity = (Double.parseDouble(AdjustmentAmount) *100)/(Double.parseDouble(AdvanceRate)*Double.parseDouble(ValidId.get("OfferID"+j).get(0)));
			}
			//Quantity = Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		}

		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 120);
		double CurrentRate24K = appUtils.FetchCurrentBoardRate("24k");
		double CurrentRate22K =  Math.round((CurrentRate24K*22)/24);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 9: Scan multiple product SKU
		//Expected: Check calculation
		//Note: Check average rate
		//Note: Billing Screen No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Subtotal,TAX,Total Amount
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC196_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC196_FromCounterGold,
				InvoiceWithDifferentTypeAdvancesTestData.TC196_MetalType,
				TotalQuantity,
				InvoiceWithDifferentTypeAdvancesTestData.TC196_ProductCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");	
		List<String> SkuListPrecia = appUtils.SelectMultipleSKUs(
				InvoiceWithDifferentTypeAdvancesTestData.TC196_ProductCount,
				InvoiceWithDifferentTypeAdvancesTestData.TC196_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC196_FromCounterPrecia,
				InvoiceWithDifferentTypeAdvancesTestData.TC196_MetalType);
		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListGold);
		SkuList.addAll(SkuListPrecia);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i <InvoiceWithDifferentTypeAdvancesTestData.TC196_AdvanceCount ; i++) {
			ProductNames.add(InvoiceWithDifferentTypeAdvancesTestData.TC196_AdvanceName);
			ProductQtyList.add(InvoiceWithDifferentTypeAdvancesTestData.TC196_AdvanceQuantity);
		}
		int SkuCounter = 1;
		double TotalTaxableAmount=0.0;
		double GrossWeight=0.0;
		String TotalAdvanceQuantity = String.format("%.2f",TotalQuantity);
		//double RemainingQty = 0.0;
		//boolean ExpectedCondition=false;
		for (int i = 0; i < SkuList.size(); i++) {
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalTaxableAmount = TotalTaxableAmount+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			GrossWeight = GrossWeight+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			TotalQuantity =  Math.round(TotalQuantity * 1000.0) / 1000.0;
			double Total = 0.0;                
			double ExpectedRgRate = 0.0;       
			double SaleQuantity = RGQuantity;  
			int FullyConsumedFromHead = 0;     

			if (TotalQuantity != 0) {
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++) {
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));
					double EffectiveRate = Math.min(Rate, CurrentRate22K);
					if (Qty <= 0) continue;
					double UseQty = Math.min(Qty, SaleQuantity);   
					Total += UseQty * EffectiveRate;
					SaleQuantity -= UseQty;
					TotalQuantity -= UseQty;
					double RemainingInRow = Qty - UseQty;
					AdvanceQty.set(j, RemainingInRow);
					if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead) {
						FullyConsumedFromHead++;
					}
				}
				if (SaleQuantity > 0) {
					Total += SaleQuantity * CurrentRate22K;
					SaleQuantity = 0;
				}
				ExpectedRgRate = Total / RGQuantity;
				for (int k = 0; k < FullyConsumedFromHead; k++) {
					AdvanceQty.removeFirst();
					AdvanceTotal.removeFirst();
					AdvanceRates.removeFirst();
				}
				System.out.println(ActualRgRate);
				System.out.println(ExpectedRgRate);
				asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0,"Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");
				mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, "22k", Double.toString(CurrentRate24K));
			}
			SkuCounter++;		
		}
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10: save estimate
		//EXpected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC196_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = String.format("%.2f",TotalTaxableAmount);
		String InvoiceTotal = String.format("%.2f",(TotalTaxableAmount+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String TotalAdjustment = Double.toString(TotalAdvanceAmount);
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment,
				NetTotal,
				TotalAmt);

		//Step 11: Recall estimate from cashier screen		
		//Step 12: Select estimate and click on recall estimation
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 13: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithDifferentTypeAdvancesTestData.TC196_TransactionType);

		//Step 14: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 16: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, AX, Total Amount
		//Step 17: Select a Payment method(Cash or Card)
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");
		String PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 18: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC196TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TaxableValue;
		String TotalDiscount = Discount;
		String TotalQtyPcs = Integer.toString(SkuList.size());
		String NetValue = NetTotal;
		String PaymentHDFCCard = PaymentAmountForSale;
		String ActualAdvanceAmount = Double.toString(TotalAdvanceAmount);
		String TotalGrossWeight = Double.toString(GrossWeight);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationTaxInvoice(
				InvoicePdfPath, 
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs, 
				TotalNetWeight, 
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentHDFCCard, 
				ActualAdvanceAmount, 
				SkuList, 
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}

	// <summary>
	// Test Case Title : Invoice with multiple offer advance and converted advance
	// Automation ID : TC195
	// Author : Robin T. Abraham
	// </summary>
	public void TC195_InvoiceWithDifferentTypeAdvances() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(InvoiceWithDifferentTypeAdvancesTestData.TC195_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 5: Click on customer adjustment button
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//pre checking offer
		List<String> OfferId = new ArrayList<>();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
		Thread.sleep(7000);
		base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_CustomerOrderNoDropDown("Customer order no"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated( InvoiceWithDifferentTypeAdvancesObj.Ele_OrderOptions("options")));
		WebElement Dropdown = driver.findElement(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
		Select Select = new Select(Dropdown);
		List<WebElement> AllOptions = Select.getOptions();
		List<String> OptionTexts = new ArrayList<>();
		for (WebElement Option : AllOptions) {
			OptionTexts.add(Option.getText());
		}
		int Count = 0;
		//for(int j=1; j<OptionTexts.size();j++) {
		for(int j=OptionTexts.size()-1;j>=0;j--) {
			base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"),OptionTexts.get(j));
			Thread.sleep(2000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) {
				OfferId.add(OptionTexts.get(j));
				Count++;		
			}
			if(Count==2) {
				break;				
			}
		}

		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> AdvanceRates     = new ArrayList<>();
		List<Double> AdvanceQty       = new ArrayList<>();
		List<String> AdvanceTotal     = new ArrayList<>();
		double TotalQuantity = 0.0;
		double TotalAdvanceAmount=0.0;
		double Quantity = 0.0;

		//Step 6: Click on Offer advance settlement
		//Step 7: Select advance and click on Add to cart button
		//Step 8: Enter OTP
		//Note: Again add another Offer advance
		//(continue step 5-8)
		//Step 9: Click on customer adjustment button
		//Step 10: Click on Normal advance settlement
		//Step 11: Select advance and click on Add to cart button
		//Step 12: Enter OTP
		//Note: Again add another converted advance
		//(continue step 9-12)

		//Converted Advance
		appUtils.HamBurgerButtonClick("iconShop");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));;
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		for(int i =0; i< InvoiceWithDifferentTypeAdvancesTestData.TC195_NormalAdvanceCount; i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
			Thread.sleep(4000);
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),"CAN");
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
			Thread.sleep(1000);
			base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
			String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
			String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
			String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			Thread.sleep(1000);
			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Normal advance amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			Quantity = Double.parseDouble( String.format("%.3f", Double.parseDouble(AdjustmentAmount) / Double.parseDouble(AdvanceRate)));
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		}
		//Offer Advance
		System.out.println("OfferId: "+OfferId.size());

		Map<String, List<String>> ValidId = erpUtils.ToFindOfferPercentageForOfferAdvance(OfferId);


		//for(int j =0; j<OfferId.size();j++) {
		for(int j=OfferId.size()-1;j>=0;j--) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
			Thread.sleep(3000);
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"),OfferId.get(j));
			String BalanceAmount = "";
			String AdvanceRate = "";
			String AdjustmentAmount="";
			List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
			int OfferAdvanceRowSize = OfferAdvanceRows.size();
			if (OfferAdvanceRowSize==1) {
				Thread.sleep(3000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",OfferId.get(j)));
				BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
				AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			}
			else {				
				base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_Offer("h4 ellipsis cell", OfferId.get(j), "Gold booked qty 0"));
				BalanceAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
				AdvanceRate = base.GetText(InvoiceWithDifferentTypeAdvancesObj.Ele_OfferAdvanceRate("Gold booked qty 0", "Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			}
			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Offer advance amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			//Quantity = Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
			if(ValidId.get("OfferID"+j).get(1)=="false") {
				Quantity = Double.parseDouble( String.format("%.3f", Double.parseDouble(AdjustmentAmount) / Double.parseDouble(AdvanceRate)));
				System.out.println("Normal: "+Quantity);
			} else {
				Quantity = Double.parseDouble( String.format("%.3f", Double.parseDouble(AdjustmentAmount)*100 / (Double.parseDouble(AdvanceRate)*Double.parseDouble(ValidId.get("OfferID"+j).get(0)))));
				System.out.println("Offer: "+Quantity);
			}
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		}

		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 120);
		double CurrentRate24K = appUtils.FetchCurrentBoardRate("24k");
		double CurrentRate22K =  Math.round((CurrentRate24K*22)/24);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 13: Scan multiple product SKU
		//Note: Check average rate
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC195_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC195_FromCounterGold,
				InvoiceWithDifferentTypeAdvancesTestData.TC195_MetalType,
				TotalQuantity/2,
				InvoiceWithDifferentTypeAdvancesTestData.TC195_ProductCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");	
		
//		List<String> SkuListPrecia = appUtils.SelectMultipleSKUs(
//				InvoiceWithDifferentTypeAdvancesTestData.TC195_ProductCount,
//				InvoiceWithDifferentTypeAdvancesTestData.TC195_TransferType,
//				InvoiceWithDifferentTypeAdvancesTestData.TC195_FromCounterPrecia,
//				InvoiceWithDifferentTypeAdvancesTestData.TC195_MetalType);
		
		SKUResult SkuPreciaSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC195_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC195_FromCounterPrecia,
				InvoiceWithDifferentTypeAdvancesTestData.TC195_MetalType,
				TotalQuantity/2,
				InvoiceWithDifferentTypeAdvancesTestData.TC195_ProductCount);
		List<String> SkuListPrecia = SkuPreciaSaleItem.PcsRateSKU;
		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListGold);
		SkuList.addAll(SkuListPrecia);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i <InvoiceWithDifferentTypeAdvancesTestData.TC195_AdvanceCount ; i++) {
			ProductNames.add(InvoiceWithDifferentTypeAdvancesTestData.TC195_AdvanceName);
			ProductQtyList.add(InvoiceWithDifferentTypeAdvancesTestData.TC195_AdvanceQuantity);
		}
		int SkuCounter = 1;
		double TotalTaxableAmount=0.0;
		double GrossWeight=0.0;
		String TotalAdvanceQuantity = String.format("%.2f",TotalQuantity);
		double RemainingQty = 0.0;
		boolean ExpectedCondition=false;
		LinkedList<String> AdvanceType =new LinkedList<>(Arrays.asList("Converted", "Converted", "Offer", "Offer"));
		for (int i = 0; i < SkuList.size(); i++) {
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalTaxableAmount = TotalTaxableAmount+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			GrossWeight = GrossWeight+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			if (TotalQuantity==0) {
				mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			}

			double Total = 0.0;
			double ExpectedRgRate = 0.0;
			double SaleQuantity = RGQuantity;
			int FullyConsumedFromHead = 0;

			System.out.println("===== DEBUG START =====");
			System.out.println("Initial RGQuantity: " + RGQuantity);
			System.out.println("Initial SaleQuantity: " + SaleQuantity);
			System.out.println("AdvanceQty: " + AdvanceQty);
			System.out.println("AdvanceRates: " + AdvanceRates);
			System.out.println("AdvanceType: " + AdvanceType);
			System.out.println("CurrentRate22K: " + CurrentRate22K);
			System.out.println("-----------------------");

			if (TotalQuantity != 0) 
			{
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++) 
				{
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));

					if (Qty <= 0) continue;

					String AdvType = AdvanceType.get(j);

					System.out.println("\n---- ROW " + j + " ----");
					System.out.println("Qty: " + Qty + " | Rate: " + Rate + " | Type: " + AdvType);
					System.out.println("SaleQuantity before using: " + SaleQuantity);

					double EffectiveRate;
					if (AdvType.equalsIgnoreCase("Converted")) 
					{
						EffectiveRate = Rate;
					}
					else if (AdvType.equalsIgnoreCase("Normal")) 
					{
						EffectiveRate = Math.min(Rate, CurrentRate22K);
					}
					else 
					{
						EffectiveRate = Math.min(Rate, CurrentRate22K);
					}

					System.out.println("EffectiveRate used: " + EffectiveRate);

					double UseQty = Math.min(Qty, SaleQuantity);

					System.out.println("UseQty: " + UseQty);

					Total += UseQty * EffectiveRate;
					System.out.println("Total after adding: " + Total);

					SaleQuantity -= UseQty;
					TotalQuantity -= UseQty;

					System.out.println("SaleQuantity remaining: " + SaleQuantity);
					System.out.println("TotalQuantity remaining: " + TotalQuantity);

					double RemainingInRow = Qty - UseQty;
					AdvanceQty.set(j, RemainingInRow);

					System.out.println("RemainingInRow in AdvanceQty: " + RemainingInRow);

					if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead)
					{
						FullyConsumedFromHead++;
						System.out.println("FullyConsumedFromHead increased → " + FullyConsumedFromHead);
					}
				}

				if (SaleQuantity > 0)
				{
					System.out.println("\nAdvance exhausted → Using current rate for remaining SaleQuantity: " + SaleQuantity);
					Total += SaleQuantity * CurrentRate22K;
					SaleQuantity = 0;
				}

				ExpectedRgRate = Total / RGQuantity;

				System.out.println("\nFinal Total: " + Total);
				System.out.println("RGQuantity: " + RGQuantity);
				System.out.println("ExpectedRgRate: " + ExpectedRgRate);
				System.out.println("ActualRgRate: " + ActualRgRate);
				System.out.println("FullyConsumedFromHead before removal: " + FullyConsumedFromHead);

				for (int k = 0; k < FullyConsumedFromHead; k++)
				{
					System.out.println("Removing row index 0 from lists");
					AdvanceQty.removeFirst();
					AdvanceTotal.removeFirst();
					AdvanceRates.removeFirst();
					AdvanceType.removeFirst();
				}

				System.out.println("AdvanceQty after removal: " + AdvanceQty);
				System.out.println("AdvanceRates after removal: " + AdvanceRates);
				System.out.println("AdvanceType after removal: " + AdvanceType);
				System.out.println("===== DEBUG END =====");

				asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0, "Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");
			}

			mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, "22k", Double.toString(CurrentRate24K));
			SkuCounter++;
		}

		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal");
		String NetTotal         = TransactionDataLine.get("NetTotal");
		String Discount         = TransactionDataLine.get("Discount");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax              = TransactionDataLine.get("Tax");
		String TotalAmount      = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 14: Save esimate
		//EXpected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC195_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = String.format("%.2f",TotalTaxableAmount);
		String InvoiceTotal = String.format("%.2f",(TotalTaxableAmount+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String TotalAdjustment = Double.toString(TotalAdvanceAmount);
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment,
				NetTotal,
				TotalAmt);

		//Step 15: Recall estimate from cashier screen
		//Step 16: Select estimate and click on save to estimate button
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 17: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithDifferentTypeAdvancesTestData.TC195_TransactionType);

		//Step 18: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 19: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 20: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, AX, Total Amount
		//Step 21: Select a Payment method(Cash or Card)
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");
		String PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 22: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC195TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount         = TaxableValue;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = Integer.toString(SkuList.size());
		String NetValue            = NetTotal;
		String PaymentHDFCCard     = PaymentAmountForSale;
		String Adjustment          = "";
		String ActualAdvanceAmount = Double.toString(TotalAdvanceAmount);
		String TotalGrossWeight    = Double.toString(GrossWeight);

		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationTaxInvoice(
				InvoicePdfPath, 
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs, 
				TotalNetWeight, 
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentHDFCCard, 
				ActualAdvanceAmount, 
				SkuList, 
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Invoice with multiple normal advance and offer advance
	// Automation ID : TC197
	// Author: Bharath
	// </summary>
	public void TC197_InvoiceWithDifferentTypeAdvances() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(InvoiceWithDifferentTypeAdvancesTestData.TC197_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		//Step 6: click on Offer advance settlement
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		//Step 7: Select advance and click on redeem button
		//Step 8: Enter OTP
		//Note: Again add another offer advance (continue step 5-8)
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> AdvanceRates = new ArrayList<>();
		List<Double> AdvanceQty = new ArrayList<>();
		List<String> AdvanceTotal = new ArrayList<>();
		String TransactionNumber ="";
		double TotalQuantity = 0.0;
		double TotalAdvanceAmount=0.0;
		double Quantity = 0.0;
		List<String> OfferId = new ArrayList<>();		
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
		Thread.sleep(7000);
		base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_CustomerOrderNoDropDown("Customer order no"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated( InvoiceWithDifferentTypeAdvancesObj.Ele_OrderOptions("options")));
		WebElement Dropdown = driver.findElement(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
		Select Select = new Select(Dropdown);
		List<WebElement> AllOptions = Select.getOptions();
		List<String> OptionTexts = new ArrayList<>();
		for (WebElement Option : AllOptions) {
			OptionTexts.add(Option.getText());
		}
		int Count = 0;
		//for(int j=0; j<OptionTexts.size();j++) {
		for(int j=OptionTexts.size()-1;j>=0;j--)
		{
			base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"),OptionTexts.get(j));
			Thread.sleep(2000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) {
				List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
				int OfferAdvanceRowSize = OfferAdvanceRows.size();
				if(OfferAdvanceRowSize >= 1) {
					OfferId.add(OptionTexts.get(j));
					Count++;		
				}
				if(Count==2) {
					break;				
				}
			}
		}
		if (Count <= 1) {
			System.out.println("Insufficient offer advance count available for the selected customer.");
			throw new RuntimeException("Insufficient offer advance records.");
		}

		Map<String, List<String>> ValidId = erpUtils.ToFindOfferPercentageForOfferAdvance(OfferId);
		for(int i =0; i<=OfferId.size()-1;i++) {
			Thread.sleep(2000);
			base.waitForClickable(InvoiceWithDifferentTypeAdvancesObj.Ele_CustomerOrderNoDropDown("Customer order no"), 10);
			base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_CustomerOrderNoDropDown("Customer order no"));
			wait.until(ExpectedConditions.visibilityOfElementLocated( InvoiceWithDifferentTypeAdvancesObj.Ele_OrderOptions("options")));
			Thread.sleep(3000);
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"),OfferId.get(i));
			String BalanceAmount = "";
			String AdvanceRate = "";
			String AdjustmentAmount="";
			List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
			int OfferAdvanceRowSize = OfferAdvanceRows.size();
			if (OfferAdvanceRowSize==1) {
				Thread.sleep(3000);
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",OfferId.get(i)));
				BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
				AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			}
			else {				
				base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_Offer("h4 ellipsis cell", OfferId.get(i), "Gold booked qty 0"));
				BalanceAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
				AdvanceRate = base.GetText(InvoiceWithDifferentTypeAdvancesObj.Ele_OfferAdvanceRate("Gold booked qty 0", "Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			}

			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Offer advance amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"), Double.toString(Double.parseDouble(AdjustmentAmount)/2));
			Thread.sleep(3000);
			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(Double.toString(Double.parseDouble(AdjustmentAmount)/2));
			AdvanceTotal.add(Double.toString(Double.parseDouble(AdjustmentAmount)/2));
			TotalAdvanceAmount = TotalAdvanceAmount +(Double.parseDouble(AdjustmentAmount)/2);
			if(ValidId.get("OfferID"+i).get(1)=="false")
			{
				Quantity = (Double.parseDouble(AdjustmentAmount)/2)/Double.parseDouble(AdvanceRate);
			}
			else {
				Quantity = ((Double.parseDouble(AdjustmentAmount)/2) *100)/(Double.parseDouble(AdvanceRate)*Double.parseDouble(ValidId.get("OfferID"+i).get(0)));
			}
			AdvanceQty.add(Quantity);
			TotalQuantity = TotalQuantity+Quantity;
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			Thread.sleep(3000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
			base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 120);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));

		}

		//Step 9: Click on customer adjustment button
		//Step 10: Click on normal advance settlement
		//Step 11: Select advance and Click on cart button, Enter OTP
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		appUtils.HamBurgerButtonClick("iconShop");
		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 120);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));			
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),
				UtilityTestData.WarehouseId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(3000);
		List<WebElement>NormalAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
		List<WebElement>NormalAdvanceBalanceGoldQty = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance gold qty", "h4 ellipsis cell"));
		for (int i = 0; i<NormalAdvanceRows.size();i++) {
			TransactionNumber = NormalAdvanceRows.get(i).getText();
			double BalanceGoldQty = Double.parseDouble(NormalAdvanceBalanceGoldQty.get(i).getText());
			if (TransactionNumber.matches("^\\d{4}-\\d{4}[A-Za-z]\\d{2}-\\d{13}$") &&  BalanceGoldQty < 10) {
				break;
			}
		}
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.clearData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),TransactionNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(1000);

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Normal advance amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		AdvanceRates.add(AdvanceRate);
		ProductTotalList.add(BalanceAmount);
		AdvanceTotal.add(BalanceAmount);
		Quantity= Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate);
		Quantity= Math.round(Quantity * 1000.0) / 1000.0;
		AdvanceQty.add(Quantity);
		TotalQuantity = TotalQuantity+Quantity;
		TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		System.out.print(AdvanceRates);
		System.out.println(AdvanceQty);

		//Step 12: Scan SKU
		//Note: Check average rate
		//Expected: Check Calculation
		//Note: Billing Screen: No. of Product Lines, Displayed Item Name, Displayed Quantity, Displayed Total (Without Tax), Displayed Subtotal, Tax, Total Amount
		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 120);
		double CurrentRate24K = appUtils.FetchCurrentBoardRate("24k");
		double CurrentRate22K =  Math.round((CurrentRate24K*22)/24);
		appUtils.HamBurgerButtonClick("iconShop");
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC197_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC197_FromCounterGold,
				InvoiceWithDifferentTypeAdvancesTestData.TC197_MetalType,
				TotalQuantity,
				InvoiceWithDifferentTypeAdvancesTestData.TC197_ProductCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");				
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i <InvoiceWithDifferentTypeAdvancesTestData.TC197_AdvanceCount ; i++) {
			ProductNames.add(InvoiceWithDifferentTypeAdvancesTestData.TC197_AdvanceName);
			ProductQtyList.add(InvoiceWithDifferentTypeAdvancesTestData.TC197_AdvanceQuantity);
		}
		int SkuCounter = 1;
		double TotalTaxableAmount=0.0;
		double GrossWeight=0.0;			
		for (int i = 0; i < SkuListGold.size(); i++) {
			String Sku = SkuListGold.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalTaxableAmount = TotalTaxableAmount+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			GrossWeight = GrossWeight+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			double RGQuantity = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			TotalQuantity =  Math.round(TotalQuantity * 1000.0) / 1000.0;
			if (TotalQuantity==0) {
				mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			}
			double Total = 0.0;                
			double ExpectedRgRate = 0.0;       
			double SaleQuantity = RGQuantity;  
			int FullyConsumedFromHead = 0;     
			if (TotalQuantity != 0) {
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++) {
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));
					double EffectiveRate = Math.min(Rate, CurrentRate22K);
					if (Qty <= 0) continue;
					double UseQty = Math.min(Qty, SaleQuantity);   
					Total += UseQty * EffectiveRate;
					SaleQuantity -= UseQty;
					TotalQuantity -= UseQty;						
					double RemainingInRow = Qty - UseQty;
					AdvanceQty.set(j, RemainingInRow);
					if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead) {
						FullyConsumedFromHead++;
					}
				}
				if (SaleQuantity > 0) {
					Total += SaleQuantity * CurrentRate22K;
					SaleQuantity = 0;
				}
				ExpectedRgRate = Total / RGQuantity;
				for (int k = 0; k < FullyConsumedFromHead; k++) {
					AdvanceQty.removeFirst();
					AdvanceTotal.removeFirst();
					AdvanceRates.removeFirst();
				}

				asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0,"Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");			
				mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, "22k", Double.toString(CurrentRate24K));
			}
			SkuCounter++;		
		}
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMap);			
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");		

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 13: save estimate
		//EXpected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC197_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst = Tax;
		String TaxableValue = String.format("%.2f",TotalTaxableAmount);
		String InvoiceTotal = String.format("%.2f",(TotalTaxableAmount+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String TotalAdjustment = Double.toString(TotalAdvanceAmount);
		String TotalAmt = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment,
				NetTotal,
				TotalAmt);

		//Step 14: Recall estimate from cashier screen		
		//Step 15: Select estimate and click on recall estimation
		//Expected: Verify whether the recalled item is the same as the item in the cart.
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 16: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithDifferentTypeAdvancesTestData.TC193_TransactionType);

		//Step 17: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 18: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 19: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, AX, Total Amount
		//Step 20: Select a Payment method(Cash or Card)
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuListGold, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");
		String PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 21: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC197TaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = TaxableValue;
		String TotalDiscount = Discount;
		String TotalQtyPcs = Integer.toString(SkuListGold.size());
		String NetValue = NetTotal;
		String PaymentHDFCCard = PaymentAmountForSale;
		String Adjustment = "";
		String ActualAdvanceAmount = Double.toString(TotalAdvanceAmount);
		String TotalGrossWeight = Double.toString(GrossWeight);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationTaxInvoice(
				InvoicePdfPath, 
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs, 
				TotalNetWeight, 
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentHDFCCard, 
				ActualAdvanceAmount, 
				SkuListGold, 
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}	

	// <summary>
	// Test Case Title :Invoice with advances like normal advance,offer advance,converted advance and add on advance
	// Automation ID : TC198
	// Author: Christy Reji
	// </summary>
	public void TC198_InvoiceWithDifferentTypeAdvances() throws Exception 
	{

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(InvoiceWithDifferentTypeAdvancesTestData.TC198_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		// Data holders
		List<String> NormalTransactionNumber = new ArrayList<>();
		List<String> OfferId                 = new ArrayList<>();
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
		Thread.sleep(4000);

		//Fetching normal and converted advance
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),"ENTER");
		Thread.sleep(3000);
		if (base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) {
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),UtilityTestData.WarehouseId);
			String NormalAdvance = base.GetText(AdvanceRepaymentObj.Ele_Row("h4 ellipsis cell", "Transaction no"));
			System.out.println("NormalAdvance : "+NormalAdvance);
			Thread.sleep(3000);
			base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),"CAN");
			String CovertedAdvance = base.GetText(AdvanceRepaymentObj.Ele_Row("h4 ellipsis cell", "Transaction no"));
			System.out.println("NormalAdvance : "+CovertedAdvance);
			NormalTransactionNumber.add(CovertedAdvance);
			NormalTransactionNumber.add(NormalAdvance);	
		}
		System.out.println("Final Normaladvance List: " + NormalTransactionNumber);

		//Fetching Offer and AddOn Advance
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
		Thread.sleep(7000);
		WebElement dropdown         = driver.findElement(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
		Select select               = new Select(dropdown);
		List<WebElement> allOptions = select.getOptions();
		int count                   = 0;
		boolean gotSizeOne          = false;
		boolean gotSizeMoreThanOne = false;

		//for (int j = 1; j < allOptions.size(); j++) {
		for (int j = allOptions.size()-1; j>=0; j--) {

			// Stop ONLY when both required datas are collected
			if (gotSizeOne && gotSizeMoreThanOne) {
				break;
			}

			String optionText = allOptions.get(j).getText();
			base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"));
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("options: viewModel.availableTranscodeOptions"),	optionText);
			Thread.sleep(2000);
			if (base.isPresent(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-itembox"))) {
				List<WebElement> offerAdvanceRows = base.GetElement(
						AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj
						.Ele_TotalAmount("Transaction no", "h4 ellipsis cell")
						);
				int offerSize = offerAdvanceRows.size();

				// Collect ONE where size == 1
				if (offerSize == 1 &&  offerSize>=1 &&!gotSizeOne) {
					OfferId.add(optionText);
					gotSizeOne = true;
					System.out.println("OfferAdvance (size == 1): " + optionText);
				}
				// Collect ONE where size > 1
				else if (offerSize > 1 && !gotSizeMoreThanOne) {
					OfferId.add(optionText);
					gotSizeMoreThanOne = true;
					System.out.println("AddOnAdvance (size > 1): " + optionText);
				}
			}
		}

		// Final validation
		if (gotSizeOne && gotSizeMoreThanOne) {
			System.out.println("Final OfferId List: " + OfferId);
		} else {
			System.out.println("Required offer types not found completely");
		}
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 5: Click on customer adjustment button
		//Step 6: click on normal advance settlement
		//Expected: The balance amount and adjustment amount for the selected advances should be shown correctly
		//Step 7: Select advance and click on redeem button
		//Step 8: Enter OTP
		//Note: Again add another normal & offer advance (continue step 5-8)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));;
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		List<String> AdvanceRates     = new ArrayList<>();
		List<Double> AdvanceQty       = new ArrayList<>();
		List<String> AdvanceTotal     = new ArrayList<>();
		double TotalQuantity          = 0.0;
		double TotalAdvanceAmount     = 0.0;
		double Quantity               = 0.0;
		for(int i =0; i< InvoiceWithDifferentTypeAdvancesTestData.TC193_NormalAdvanceCount;i++) {
			Thread.sleep(1000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
			Thread.sleep(4000);
			base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
			base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),NormalTransactionNumber.get(i));
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
			Thread.sleep(1000);
			base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
			String BalanceAmount    = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
			String AdvanceRate      = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
			String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			String NormalQty=base.GetText(InvoiceWithDifferentTypeAdvancesObj.Ele_NormalQtyFetch("true", "Balance gold", "gridcell"));			

			Thread.sleep(1000);

			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Normal advance amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			Quantity           = Double.parseDouble(NormalQty);
			System.out.println("Normal Qty=="+Quantity);
			AdvanceQty.add(Quantity);
			TotalQuantity      = TotalQuantity+Quantity;
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			Thread.sleep(3000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		}
		//ERP
		Map<String, List<String>> ValidOfferId = erpUtils.ToFindOfferPercentageForOfferAdvance(OfferId);
		System.out.println("ValidOfferId=="+ValidOfferId);
		for(int j =0; j<OfferId.size();j++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"));
			Thread.sleep(3000);
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"),OfferId.get(j));
			String BalanceAmount    = "";
			String AdvanceRate      = "";
			String AdjustmentAmount = "";
			List<WebElement>OfferAdvanceRows = base.GetElement(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Transaction no", "h4 ellipsis cell"));
			int OfferAdvanceRowSize = OfferAdvanceRows.size();
			if (OfferAdvanceRowSize==1) {
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",OfferId.get(j)));
				BalanceAmount    = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
				AdvanceRate      = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			}
			else {				
				base.ClickCondition(InvoiceWithDifferentTypeAdvancesObj.Ele_Offer("h4 ellipsis cell", OfferId.get(j), "Gold booked qty 0"));
				BalanceAmount    = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
				AdvanceRate      = base.GetText(InvoiceWithDifferentTypeAdvancesObj.Ele_OfferAdvanceRate("Gold booked qty 0", "Metal Rate", "h4 ellipsis cell"));
				AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));

			}
			//Balance amount validation
			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Offer advance amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

			AdvanceRates.add(AdvanceRate);
			ProductTotalList.add(BalanceAmount);
			AdvanceTotal.add(BalanceAmount);
			TotalAdvanceAmount = TotalAdvanceAmount +Double.parseDouble(BalanceAmount);
			Quantity           = Math.round(Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate));	
			if(ValidOfferId.get("OfferID"+j).get(1)=="false")
			{
				Quantity           = Math.round(Double.parseDouble(AdjustmentAmount)/Double.parseDouble(AdvanceRate));	
			}else {
				Quantity = (Double.parseDouble(AdjustmentAmount) *100)/(Double.parseDouble(AdvanceRate)*Double.parseDouble(ValidOfferId.get("OfferID"+j).get(0)));
			}

			AdvanceQty.add(Quantity);
			TotalQuantity      = TotalQuantity+Quantity;
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		}

		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 120);
		double CurrentRate24K = appUtils.FetchCurrentBoardRate("24k");
		double CurrentRate22K =  Math.round((CurrentRate24K*22)/24);
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 9: Scan multiple product SKU
		//Expected: Check calculation
		//Note: Check average rate
		//Note: Billing Screen No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Subtotal,TAX,Total Amount
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC198_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC198_FromCounterGold,
				InvoiceWithDifferentTypeAdvancesTestData.TC198_MetalType,
				TotalQuantity/2,
				InvoiceWithDifferentTypeAdvancesTestData.TC198_ProductCount);
		List<String> SkuListGold = SkuGoldSaleItem.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");	
		SKUResult SkuPreciaSaleItem = appUtils.GetOGSaleSku(
				InvoiceWithDifferentTypeAdvancesTestData.TC198_TransferType,
				InvoiceWithDifferentTypeAdvancesTestData.TC198_FromCounterPrecia,
				InvoiceWithDifferentTypeAdvancesTestData.TC198_MetalType,
				TotalQuantity/2,
				InvoiceWithDifferentTypeAdvancesTestData.TC198_ProductCount);

		List<String> SkuListPrecia = SkuPreciaSaleItem.PcsRateSKU;
		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(SkuListGold);
		SkuList.addAll(SkuListPrecia);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i <InvoiceWithDifferentTypeAdvancesTestData.TC198_AdvanceCount ; i++) {
			ProductNames.add(InvoiceWithDifferentTypeAdvancesTestData.TC198_AdvanceName);
			ProductQtyList.add(InvoiceWithDifferentTypeAdvancesTestData.TC198_AdvanceQuantity);
		}
		int SkuCounter              = 1;
		double TotalTaxableAmount   = 0.0;
		double GrossWeight          = 0.0;
		String TotalAdvanceQuantity = String.format("%.2f",TotalQuantity);
		double RemainingQty         = 0.0;
		boolean ExpectedCondition   = false;
		for (int i = 0; i < SkuList.size(); i++) {
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			TotalTaxableAmount  = TotalTaxableAmount+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			GrossWeight         = GrossWeight+Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			double RGQuantity   = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_TotalRGWeight"));
			double ActualRgRate = Double.parseDouble(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_RateRG"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			if (TotalQuantity==0) {
				mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			}
			double Total              = 0.0;                
			double ExpectedRgRate     = 0.0;       
			double SaleQuantity       = RGQuantity;  
			int FullyConsumedFromHead = 0;     

			if (TotalQuantity != 0) {
				for (int j = 0; j < AdvanceQty.size() && SaleQuantity > 0; j++) {
					double Qty  = AdvanceQty.get(j);
					double Rate = Double.parseDouble(AdvanceRates.get(j));
					double EffectiveRate = Math.min(Rate, CurrentRate22K);
					if (Qty <= 0) continue;
					double UseQty         = Math.min(Qty, SaleQuantity);   
					Total                += UseQty * EffectiveRate;
					SaleQuantity         -= UseQty;
					TotalQuantity        -= UseQty;
					double RemainingInRow = Qty - UseQty;
					AdvanceQty.set(j, RemainingInRow);
					if (RemainingInRow <= 1e-9 && j == FullyConsumedFromHead) {
						FullyConsumedFromHead++;
					}
				}
				if (SaleQuantity > 0) {
					Total       += SaleQuantity * CurrentRate22K;
					SaleQuantity = 0;
				}
				ExpectedRgRate = Total / RGQuantity;
				for (int k = 0; k < FullyConsumedFromHead; k++) {
					AdvanceQty.removeFirst();
					AdvanceTotal.removeFirst();
					AdvanceRates.removeFirst();
				}
				//average rate calculation
				asrt.assertEquals(ActualRgRate, ExpectedRgRate, 1.0,"Average rate mismatch: Expected is " + ExpectedRgRate + " but got " + ActualRgRate + " in item details page");
				mathUtils.SkuIngredientItemCalculationWithoutRate(CurrentSkuData, SkuCounter, "22k", Double.toString(CurrentRate24K));
			}
			SkuCounter++;		
		}
		List<String> ItemNamesInBillingPage = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal");
		String NetTotal         = TransactionDataLine.get("NetTotal");
		String Discount         = TransactionDataLine.get("Discount");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax              = TransactionDataLine.get("Tax");
		String TotalAmount      = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		//Step 10: save esimate
		//EXpected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC198_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String TaxableValue        = String.format("%.2f",TotalTaxableAmount);
		String InvoiceTotal        = String.format("%.2f",(TotalTaxableAmount+Double.parseDouble(Tax.replaceAll("[^\\d.-]", "").trim())));
		String TotalAdjustment     = Double.toString(TotalAdvanceAmount);
		String TotalAmt            = "";
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProFormaInvoicePath, 
				TaxableValue, 
				Gst,
				InvoiceTotal, 
				TotalAdjustment,
				NetTotal,
				TotalAmt);

		//Step 11: Recall estimate from cashier screen		
		//Step 12: Select estimate and click on recall estimation
		//Expected: Verify whether the recalled item is the same as the item in the cart.
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is " + ActualEstmnNumber + " and Expected Estimation number"+ ExpectedEstmnNumber + " in recall estimation page");

		//Recallpage validation
		List<String> ItemNamesFromRecallPage = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		asrt.assertTrue(ItemNamesInBillingPage.containsAll(ItemNamesFromRecallPage), "Recalled item is not the same as the item in the cart");

		//Step 13: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),InvoiceWithDifferentTypeAdvancesTestData.TC198_TransactionType);
		Thread.sleep(4000);

		//Step 14: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 15: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();

		//Step 16: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, AX, Total Amount
		//Step 17: Select a Payment method(Cash or Card)
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");
		String PaymentAmountForSale = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 18: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice        = pdfUtils.DownloadAndRenameLatestPDF("TC198_FinalTaxInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath      = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount         = TaxableValue;
		String TotalDiscount       = Discount;
		String TotalQtyPcs         = Integer.toString(SkuList.size());
		String NetValue            = NetTotal;
		String PaymentHDFCCard     = PaymentAmountForSale;
		String Adjustment          = "";
		String ActualAdvanceAmount = Double.toString(TotalAdvanceAmount);
		String TotalGrossWeight    = Double.toString(GrossWeight);
		String PdfInvoiceNo        = pdfUtils.SaleInvoicePdfValidationTaxInvoice(
				InvoicePdfPath, 
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs, 
				TotalNetWeight, 
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentHDFCCard, 
				ActualAdvanceAmount, 
				SkuList, 
				ScannedSkuDataMap);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
}