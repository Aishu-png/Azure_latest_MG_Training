
package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.PdcEntryExecution_Obj;
import testData.BelowCostSaleValidation_TestData;
import testData.CommonData;
import testData.NormalSaleGoldandSilver_TestData;
import testData.NormalSalesReturnGoldSilverReturnSaleCounter_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.AppUtilities.SKUTypeResult;
import utilPack.BasePge;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;


public class BelowCostSaleValidation extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	MathUtilities mathUtils;
	PdfUtilities pdfUtils;

	Login Login = new Login(driver); 
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	Utility_TestData UtilityTestData=new Utility_TestData();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();	
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj= new MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	PdcEntryExecution_Obj PdcEntryExecutionObj=new PdcEntryExecution_Obj();
	BelowCostSaleValidation_TestData BelowCostSaleValidationTestData= new BelowCostSaleValidation_TestData();

	public BelowCostSaleValidation(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base);
		mathUtils=new MathUtilities(base);
		pdfUtils=new PdfUtilities(base);
	}



	// <summary>
	// Test Case Title : Create sale invoice with SKU having MC 4% and apply the discount -check both in mtouch and POS
	// Automation ID : TC188
	// <Author : Vishnu Manoj K
	// </summary>
	public void TC188_BelowCostSaleValidation() throws Exception {

		// Step 1: Login to POS
		Login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Click on Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");

		// Step 3: Select customer
		// Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(BelowCostSaleValidationTestData.TC188_CustomerNo,Utility_TestData.MenuBarOptions[5]);

		// Step 5: Scan gold SKU and click on Add to cart button
		List<String> SkuList = appUtils.CollectValidSkuByVerfiy( BelowCostSaleValidationTestData.TC188_SkuGoldCount,BelowCostSaleValidationTestData.TC188_TransferType,BelowCostSaleValidationTestData.TC188_FromCounter,BelowCostSaleValidationTestData.TC188_MetalType,
				BelowCostSaleValidationTestData.TC188_AllSkuFetchNo, Arrays.asList("WastageQty"));
		System.out.println("Final Valid SKUs: " + SkuList);	   

		appUtils.HamBurgerButtonClick("iconShop");		
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		String TotalGold=ScannedSkuDataMap.get("SKU_1_Total").replace('\u00A0', ' ') .trim().replaceAll("[^0-9.\\-]", "");
		System.out.println("TotalGold "+TotalGold); 
		String BoardRateGold=ScannedSkuDataMap.get("SKU_1_RateRG").replace('\u00A0', ' ') .trim().replaceAll("[^0-9.\\-]", "");
		String CvalueGold=ScannedSkuDataMap.get("SKU_1_CvalueRG").replace('\u00A0', ' ') .trim().replaceAll("[^0-9.\\-]", "");
		System.out.println("CvalueGold "+CvalueGold); 
		String RgQtyGold=ScannedSkuDataMap.get("SKU_1_TotalRGWeight").replace('\u00A0', ' ') .trim().replaceAll("[^0-9.\\-]", "");
		double TotalMinCvalue = Double.parseDouble(TotalGold) - Double.parseDouble(CvalueGold);
		String SkuName = ScannedSkuDataMap.get("SKU_1_HeaderName");
		//String DesignCode = SkuName.split("\\s*-\\s*")[0].trim();
		//System.out.println(DesignCode);  

		String ParentWindow = driver.getWindowHandle(); 

		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Sku Inquiry");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		// Select Scheme Code
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "SKU number"));
		System.out.println("SkuList "+SkuList);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SKUNumber_Input"),SkuList.get(0).trim());
		base.buttonClick(CustomerCreationObj.Ele_BankAccounts("ApplyFilters_label"));

		//select Details
		Thread.sleep(5000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Details"));
		String ActualCostErp=(base.GetValue(NormalSaleGoldAndSilverObj.Ele_CostPriceErp("Actual cost price","CostPrice")).replace('\u00A0', ' ') .trim().replaceAll("[^0-9.\\-]", ""));
		String GoldValueErp=(base.GetValue(NormalSaleGoldAndSilverObj.Ele_CostPriceErp("Gold value","GoldValue")).replace('\u00A0', ' ') .trim().replaceAll("[^0-9.\\-]", ""));
		String DesignCode=base.GetText(NormalSaleGoldAndSilverObj.Ele_DesigCodeErp("Design Code","TablePosted"));
		System.out.println("DesignCode :"+DesignCode);  
		double ActualCost = Double.parseDouble(ActualCostErp);
		System.out.println("ActualCostErp   :"+ActualCostErp);
		double GoldValue = Double.parseDouble(GoldValueErp);
		System.out.println("GoldValueErp   :"+GoldValueErp);
		double ActualCostMinGoldValue = ActualCost - GoldValue;
		System.out.println("ActualCostErp   "+ActualCostErp);
		double DifferenceInValue= TotalMinCvalue - ActualCostMinGoldValue;
		System.out.println("DifferenceInValue "+DifferenceInValue);
		double DividePercentage=DifferenceInValue/Double.parseDouble(RgQtyGold);
		System.out.println("DividePercentage "+DividePercentage);
		double BelowCostPerGram=(DividePercentage/ Double.parseDouble(BoardRateGold))*100;
		System.out.println("BelowCostPerGram "+BelowCostPerGram);

		double DecimalPart = BelowCostPerGram - Math.floor(BelowCostPerGram);
		double BelowCostPerGramRoundOff;
		if (DecimalPart < 0.5)
			BelowCostPerGramRoundOff = Math.floor(BelowCostPerGram);
		else if (DecimalPart > 0.5)
			BelowCostPerGramRoundOff = Math.ceil(BelowCostPerGram);
		else
			BelowCostPerGramRoundOff = Math.floor(BelowCostPerGram) + 0.5;

		System.out.println("BelowCostPerGram after round off" + BelowCostPerGramRoundOff);

		base.newWindow(2);
		driver.get(UtilityTestData.ERPURL);

		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Making and stone rate discount");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);


		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"), UtilityTestData.WarehouseId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RetailStoreId_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(15000);

		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Design Code"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_ItemId_ItemId"), DesignCode);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_ItemId_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(15000);

		//Activate
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Activate"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Activate"),"Yes");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_Activate"),"ENTER");
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_Activate_ApplyFilters_label", "Apply"));

		//Select Row
		Thread.sleep(6000);
		if(base.isExists(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1)))
		{
			base.buttonClick(PdcEntryExecutionObj.Ele_ErpPdc("Select or unselect row", 1));

			if(base.isExists(NormalSaleGoldAndSilverObj.Ele_CheckDelete("Delete","disabled")))
			{
				base.excuteJsClick(LoginPageObj.Edt_Alert("Activate"));
				Thread.sleep(5000);
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
				base.actionClick(LoginPageObj.Edt_Alert("Yes"));
			}
			else
			{
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_CheckDeleteEnable("Delete"));
			}
		}

		base.newWindow(3);
		driver.get(UtilityTestData.ERPURL);

		// 1. Search On hand list
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Making and stone rate discount");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);


		//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Store number"));
		//		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_Grid_RetailStoreId_RetailStoreId"), UtilityTestData.WarehouseId);
		//		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__Grid_RetailStoreId_ApplyFilters_label", "Apply"));

		//Click on New
		base.excuteJsClick(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Edt_Alert("New"));
		Thread.sleep(5000);
		//Click From Date
		LocalDate CurrentDate = LocalDate.now();
		String FormattedDate = CurrentDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		System.out.println(FormattedDate);  
		// ---------------- From Date ----------------
		By FromDateField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("From date", 1);
		base.buttonClick(FromDateField);
		Thread.sleep(1000);
		WebElement FromDateEl = driver.findElement(FromDateField);
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		// Clear the field via JS
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						FromDateEl
				);

		// Set new formatted date value (e.g., M/d/yyyy)
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						FromDateEl, FormattedDate
				);

		// Commit entry
		base.pressKey(FromDateField, "ENTER");
		Thread.sleep(1000);

		// ---------------- To Date ----------------
		By ToDateField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("To date", 1);
		base.buttonClick(ToDateField);
		Thread.sleep(1000);
		WebElement ToDateEl = driver.findElement(ToDateField);

		// Clear existing value
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						ToDateEl
				);

		// Set new date
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						ToDateEl, FormattedDate
				);

		// Commit entry
		base.pressKey(ToDateField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Store Number ----------------
		By StoreNumberField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Store number", 1);
		base.buttonClick(StoreNumberField);
		Thread.sleep(1000);
		WebElement StoreNumberEl = driver.findElement(StoreNumberField);

		// Clear existing text
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						StoreNumberEl
				);

		// Set WarehouseId
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						StoreNumberEl, UtilityTestData.WarehouseId
				);

		// Commit entry
		base.pressKey(StoreNumberField, "ENTER");
		Thread.sleep(5000);

		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar2 = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj2 = new Actions(driver);
		ActionsObj2.clickAndHold(Scrollbar2).moveByOffset(650, 0).release().perform();
		Thread.sleep(15000);

		// ---------------- Design Code ----------------
		By DesignCodeField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Design Code", 1);
		base.buttonClick(DesignCodeField);
		Thread.sleep(1000);
		WebElement DesignCodeEl = driver.findElement(DesignCodeField);

		// Clear field
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						DesignCodeEl
				);

		// Set Design Code value
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						DesignCodeEl, DesignCode
				);

		// Commit the value
		base.pressKey(DesignCodeField, "ENTER");
		Thread.sleep(1000);

		// ---------------- From Qty ----------------
		By FromQtyField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("From qty", 1);
		base.buttonClick(FromQtyField);
		Thread.sleep(1000);
		WebElement FromQtyEl = driver.findElement(FromQtyField);

		// Clear existing text
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						FromQtyEl
				);

		// Set value “0.01”
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						FromQtyEl, "0.01"
				);

		// Commit
		base.pressKey(FromQtyField, "ENTER");
		Thread.sleep(1000);

		// ---------------- To Qty ----------------
		By ToQtyField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("To qty", 1);
		base.buttonClick(ToQtyField);
		Thread.sleep(1000);
		WebElement ToQtyEl = driver.findElement(ToQtyField);

		// Clear field
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						ToQtyEl
				);

		// Set value “1000”
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						ToQtyEl, "1000"
				);

		// Commit
		base.pressKey(ToQtyField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Scroll horizontally-------------
		//		ActionsObj2.clickAndHold(Scrollbar2).moveByOffset(650, 0).release().perform();
		ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
		Thread.sleep(15000);
		// ---------------- Discount Type ----------------
		By DiscountTypeField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Discount type", 1);
		base.buttonClick(DiscountTypeField);
		Thread.sleep(1000);

		WebElement DiscountTypeEl = driver.findElement(DiscountTypeField);

		// Clear field first
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						DiscountTypeEl
				);

		// Set new value “Reduce making percentage”
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						DiscountTypeEl, "Reduce making percentage"
				);

		// Commit entry (press ENTER)
		base.pressKey(DiscountTypeField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Calculate Discount ----------------
		// Create a formatter that keeps up to 2 decimals and removes trailing zeros
		DecimalFormat DecimalFormatObj = new DecimalFormat("0.##");

		// Calculate formatted values
		String CashierDiscount     = DecimalFormatObj.format(BelowCostPerGramRoundOff + 2);
		String SalesPersonDiscount = DecimalFormatObj.format(BelowCostPerGramRoundOff + 4);
		String SupervisorDiscount  = DecimalFormatObj.format(BelowCostPerGramRoundOff + 6);
		String ManagerDiscount     = DecimalFormatObj.format(BelowCostPerGramRoundOff + 8);

		// Cashier Discount Percentage field
		By CashierDiscountField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Cashier discount perentage", 1);
		String CashierDiscountValue = CashierDiscount; // already calculated earlier

		// Click the field to focus
		base.buttonClick(CashierDiscountField);
		Thread.sleep(1000);

		// Get element
		WebElement CashierDiscountElement = driver.findElement(CashierDiscountField);


		// 1️ Force clear field value (even if masked)
		Js.executeScript(
				"const el = arguments[0];" +
						"el.focus();" +
						"el.value = '';" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						CashierDiscountElement
				);
		Thread.sleep(500);

		// 2 Set new value directly in DOM using native JS setter
		Js.executeScript(
				"const el = arguments[0];" +
						"const val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', { bubbles: true }));" +
						"el.dispatchEvent(new Event('change', { bubbles: true }));",
						CashierDiscountElement, CashierDiscountValue
				);

		// 3️Optional – press ENTER to commit masked format
		base.pressKey(CashierDiscountField, "ENTER");


		// ---------------- Sales Person Discount ----------------
		By SalesPersonField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Sales person discount percentage", 1);
		base.buttonClick(SalesPersonField);
		Thread.sleep(500);
		WebElement SalesPersonEl = driver.findElement(SalesPersonField);

		// Clear + set via JS
		Js.executeScript(
				"const el=arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true}));",
						SalesPersonEl
				);
		Js.executeScript(
				"const el=arguments[0], val=arguments[1];" +
						"const setter=Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true}));",
						SalesPersonEl, SalesPersonDiscount
				);
		base.pressKey(SalesPersonField, "ENTER");

		// ---------------- Supervisor Discount ----------------
		By SupervisorField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Supervisor discount percentage", 1);
		base.buttonClick(SupervisorField);
		Thread.sleep(500);
		WebElement SupervisorEl = driver.findElement(SupervisorField);

		Js.executeScript(
				"const el=arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true}));",
						SupervisorEl
				);
		Js.executeScript(
				"const el=arguments[0], val=arguments[1];" +
						"const setter=Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true}));",
						SupervisorEl, SupervisorDiscount
				);
		base.pressKey(SupervisorField, "ENTER");

		// ---------------- Manager Discount ----------------
		By ManagerField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Manager discount percentage", 1);
		base.buttonClick(ManagerField);
		Thread.sleep(500);
		WebElement ManagerEl = driver.findElement(ManagerField);

		Js.executeScript(
				"const el=arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true}));",
						ManagerEl
				);
		Js.executeScript(
				"const el=arguments[0], val=arguments[1];" +
						"const setter=Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true}));",
						ManagerEl, ManagerDiscount
				);
		base.pressKey(ManagerField, "ENTER");

		// ---------------- Scroll horizontally-------------
		ActionsObj2.scrollFromOrigin(org.openqa.selenium.interactions.WheelInput.ScrollOrigin.fromElement(Scrollbar2), 650, 0).perform();
		Thread.sleep(15000);

		// ---------------- OverwriteAppend ----------------
		By OverwriteAppendField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("OverwriteAppend", 1);
		base.buttonClick(OverwriteAppendField);
		Thread.sleep(1000);
		WebElement OverwriteAppendEl = driver.findElement(OverwriteAppendField);

		// Clear field via JS
		Js.executeScript(
				"const el=arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						OverwriteAppendEl
				);

		// Set new value “Overwrite”
		Js.executeScript(
				"const el=arguments[0], val=arguments[1];" +
						"const setter=Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						OverwriteAppendEl, "Overwrite"
				);

		// Commit entry
		base.pressKey(OverwriteAppendField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Auto-promotion/Manual ----------------
		By AutoPromotionField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Auto-promotion/Manual", 1);
		base.buttonClick(AutoPromotionField);
		Thread.sleep(1000);
		WebElement AutoPromotionEl = driver.findElement(AutoPromotionField);

		// Clear field via JS
		Js.executeScript(
				"const el=arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						AutoPromotionEl
				);

		// Set new value “Manual”
		Js.executeScript(
				"const el=arguments[0], val=arguments[1];" +
						"const setter=Object.getOwnPropertyDescriptor(HTMLInputElement.prototype,'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input',{bubbles:true}));" +
						"el.dispatchEvent(new Event('change',{bubbles:true}));",
						AutoPromotionEl, "Manual"
				);

		// Commit entry
		base.pressKey(AutoPromotionField, "ENTER");
		Thread.sleep(1000);

		// ---------------- Click on Activate ----------------
		base.excuteJsClick(LoginPageObj.Edt_Alert("Activate"));
		System.out.println("Activated successfully");

		//----------------Go to Distribution Schedule ----------------
		base.newWindow(4);
		driver.get(UtilityTestData.ERPURL);

		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchDistributionSchedule);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Filter the job ""N-AGREEMENT"" and click on ""Run now""
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "N-AGREEMENT");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("QuickFilterControl_Input"), "ENTER");
		Thread.sleep(10000);

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement runNowBtn = wait2.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_Alert("Run now")));
		base.excuteJsClick(LoginPageObj.Edt_Alert("Run now"));
		System.out.println("Activated successfully");

		base.buttonClick(LoginPageObj.Edt_Alert("OK"));

		//---------------- Batch Job ---------------- 
		base.newWindow(5);
		driver.get(UtilityTestData.ERPURL);

		boolean jobEnded = false;
		int maxAttempts = 8; 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			try {
				// Step 26: Search My Batch Job
				//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchMyBatchJobs);
				Thread.sleep(1000);
				base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");

				// Step 27: Sort by "Scheduled start date/time"
				Thread.sleep(2000);
				By sortColumn = NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Scheduled start date/time");
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(sortColumn)).click();
				base.buttonClick(LoginPageObj.Edt_Alert("Sort newest to oldest"));

				// Step 28: Wait for "Ended" job
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						NormalSaleGoldAndSilverObj.Ele_StatusERP("2", "Ended")));

				jobEnded = true;
				break; // success, break the loop

			} catch (StaleElementReferenceException stale) {
				System.out.println("Attempt " + attempt + ": Stale element. Retrying after refresh...");
				if (attempt < maxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Stale element not resolved after retries", stale);
				}
			} catch (TimeoutException timeout) {
				System.out.println("Attempt " + attempt + ": Timeout while waiting for 'Ended' job status.");
				if (attempt < maxAttempts) {
					driver.navigate().refresh();
					Thread.sleep(3000);
				} else {
					throw new RuntimeException("Job status 'Ended' not found even after retrying.", timeout);
				}
			}
		}

		driver.switchTo().window(ParentWindow);

		//Step 6: In Billing,Go to discount button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_Discount("Discounts", "Billing"));

		//Step 7: Click a normal discount.
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Discount"));

		//Step 7.1: Select making discount.
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrDiscountType"), "Making discount");

		//Step 7.2: Select making discount type as Reduce making percentage
		//Step 7.3: Discount type as Percentage
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MkDiscype"),"Reduce making percentage");

		//Step 7.4: Add a discount value greater than MC
		String DiscountValue     = DecimalFormatObj.format(BelowCostPerGramRoundOff +1 );
		Thread.sleep(40000);
		System.out.println("DiscountValue"+DiscountValue);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), DiscountValue);

		//Step 7.5: Select Category.
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrCategory"), "Gold Ornaments");
		Thread.sleep(20000);

		//Step 7.6: Click on apply discount.
		//Expected: Verify Below cost validation should appear
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));

		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("cost price validation failed")), "Cost price validation Failed Warning Message not Found");

		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		double BelowCostPerGramShort = Math.floor(BelowCostPerGram * 10) / 10;
		System.out.println(BelowCostPerGramShort); 
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: mkdiscamt"), Double.toString(BelowCostPerGramShort));
		Thread.sleep(5000);
		base.ClickCondition(MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtpObj.Btn_ApplyDiscount("visible:IsMaking", "Apply discount"));
		double ExpectedDiscount = (Double.parseDouble(CvalueGold)*BelowCostPerGramShort)/100;
		ExpectedDiscount = Math.round(ExpectedDiscount * 100.0) / 100.0;
		System.out.println("ExpectedDiscount"+ExpectedDiscount);
		Thread.sleep(20000);
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String Discount = TransactionDataLine.get("Discounts").trim().replaceAll("[^0-9.]", "");
		System.out.println("DiscountInBIllingPage"+Discount);

		asrt.assertEquals(Double.parseDouble(Discount),ExpectedDiscount,1, "Discount is not Applied or Cost price validation Message Still Remains while applying Discount ");

		// Step 8: Save Esimate
		// Step 9: Click on Save to estimation button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_BillingBack("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// Step 10: Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstmnNumber);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		// Step 11: Select estimate and click on recall button
		// Step 12: Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), BelowCostSaleValidationTestData.TC188_TransationType);

		// Step 13: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 14: Select any Sales Representative [Due to new Fix in the application this step is Removed] 
		appUtils.HandleSpecifyRateModalInRecall();

		// Step 15:  Click on the Amount
		/*
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// Step 16:  Select a Payment method(Cash or Card)
		base.buttonClick(LoginPageObj.Edt_AlertText("HDFC (Credit Card)"));
		String PaymentHDFCCard = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_CartMoney("flexGrow50 flexRow row-enter","h1 marginBottom0 iconReturnKey button-content-return"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),BelowCostSaleValidationTestData.TC188_ApprovalCode);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),BelowCostSaleValidationTestData.TC188_ApprovalCodeAgain);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		*/
		String PaymentHDFCCard = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		// Step 17:  Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}


}