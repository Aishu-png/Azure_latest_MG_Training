package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldItemStockOutAndShipment_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
import testData.GppOpeningAndCollectionMultiplePlans_TestData;
import testData.OldItemStockOutAndShipment_TestData;
import testData.PANCardLimitValidation_TestData;
import testData.UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData;
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

public class OldItemStockOutAndShipment extends BasePge{

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	CustomerCreation_Obj CustomerCreationObj = new CustomerCreation_Obj();
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	GPPSettlementDiscountPlan_TestData GPPSettlementDiscountPlanTestData =new GPPSettlementDiscountPlan_TestData();
	UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTableTestData=new UrdPurchaseGoldPreciaUncutStoreTransactionOldSkuTable_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj = new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	OldItemStockOutAndShipment_TestData OldItemStockOutAndShipmentTestData=new OldItemStockOutAndShipment_TestData();
	OldItemStockOutAndShipment_Obj OldItemStockOutAndShipmentObj=new OldItemStockOutAndShipment_Obj();

	public OldItemStockOutAndShipment(WebDriver driver) 
	{
		super(driver);
		base      = new BasePge(driver);
		appUtils  = new AppUtilities(base); 
		erpUtils  = new ErpUtilities(base);
		pdfUtils  = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	//summary>
	// Test Case Title : Stock Out OG and OGT
	// Automation ID : TC238
	// Author:Sangeetha M S
	// </summary>
	public void TC238_OldItemStockOutAndShipment() throws Exception
	{
		//Step 1: Login to POS
		Thread.sleep(1000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on the Transaction button
		Thread.sleep(1000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite :OG Purchase
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(OldItemStockOutAndShipmentTestData.TC238_CustomerId, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"OG");
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));	      
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"22k");
//		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK")));
//		{
//			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
//		}
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");
		if (base.isElementPresent(driver, CartChangedAlert)) {		
			try {
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e) {
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(2000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);		
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), UtilityTestData.GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), UtilityTestData.GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//Step 3: Click on Purchase
		Thread.sleep(1000);
		appUtils.HamBurgerButtonClick("iconShop");
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on the Bulk Item Stock Out button
		base.ClickCondition(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));

		//Step 5: Check that the date is selected as Yesterday
		DateTimeFormatter Format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate TodayDate = LocalDate.now();
		LocalDate TargetDate = TodayDate.minusDays(1);
		String ExpectedDate = TargetDate.format(Format);
		System.out.println("ExpectedDate : " + ExpectedDate);

		String DayText = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate("FromDatePicker", "Week days"));
		Thread.sleep(2000);
		String MonthText = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate("FromDatePicker", "Months"));
		Thread.sleep(2000);
		String YearText = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate("FromDatePicker", "Years"));	
		int correctedMonth = Integer.parseInt(MonthText) + 1;
		String ActualDate = String.format("%02d/%02d/%s", Integer.parseInt(DayText),correctedMonth,YearText);
		System.out.println("ActualDate : " + ActualDate);

		asrt.assertEquals( ActualDate, ExpectedDate," The date is not selected as Yesterday in Bulk item Stock out Page");

		//Step 6: Select Metal Type as Gold
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC238_MetalType);

		//Step 7: Select Item Type as OG
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC238_ItemType);

		//Step 8: Click on the Fetch button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));

		// Step 9: Click on the Stock Out button
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(2000);

		if (base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")))
		{
			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");
			System.out.println("Stock out completed successfully!");
			Thread.sleep(3000);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}
		else if(base.isPresent(LoginPageObj.Edt_AlertText("There is no items in the list, please add line !")))
		{
			Thread.sleep(2000);
			WebElement DropDown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].removeAttribute('disabled');", DropDown);
			js.executeScript("arguments[0].disabled = false;", DropDown);
			LocalDate Today = LocalDate.now();
			String CurrentDay = String.valueOf(Today.getDayOfMonth());
			System.out.println("Current selected (day): " + CurrentDay);
			Select SelectDay = new Select(DropDown);
			SelectDay.selectByVisibleText(CurrentDay);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(2000);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));

			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");			
		}
	}

	// <summary>
	// Test Case Title : Stock out OD
	// Automation ID : TC236
	// Author: Aiswarya
	// </summary>
	public void TC236_OldItemStockOutAndShipment() throws Exception {

		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on the Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");	

		//OD purchase
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(OldItemStockOutAndShipmentTestData.TC236_CustomerID, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), UtilityTestData.ODProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine", UtilityTestData.OldDiamondProduct)); 
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.OldDiamondProduct),NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),5,2);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),  "22k");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK")));
//		{
//			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
//		}
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");
		if (base.isElementPresent(driver, CartChangedAlert)) {		
			try {
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e) {
			}
		}
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(3000);
		//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		//		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), UtilityTestData.GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), UtilityTestData.GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));	

		//Step 3: Click on Purchase
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on the Bulk Item Stock Out button
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));

		//Step 5: Check that the date is selected as Yesterday
		Thread.sleep(3000);
		LocalDate CurrentDate = LocalDate.now();
		LocalDate ExpectedDate = CurrentDate.minusDays(1);
		String ActualDay = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Week days"));
		String ActualMonth =Integer.toString(Integer.parseInt(base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Months")))+1);
		String ActualYear = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Years"));
		String CombinedDate = ActualYear + "-" + ActualMonth + "-" + ActualDay;
		LocalDate ActualDate = LocalDate.parse(CombinedDate, DateTimeFormatter.ofPattern("yyyy-M-d"));

		asrt.assertEquals(ActualDate,ExpectedDate,"Date mismatch: "+ExpectedDate+" but found "+ActualDate+" in Bulk Item Stock Out Page.");

		//Step 6: Select Metal Type as Gold
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC236_MetalType);

		//Step 7: Select Item Type as OD
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC236_ItemType);

		//Step 8: Click on the Fetch button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));

		//Step 9: Click on the Stock Out button
		//Expected: Verify if validation appears "stock out is done successfully"
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		if (base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")))
		{
			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");
			System.out.println("Stock out completed successfully!");
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		else if(base.isPresent(LoginPageObj.Edt_AlertText("There is no items in the list, please add line !")))
		{
			Thread.sleep(3000);
			WebElement Dropdown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
			JavascriptExecutor Js = (JavascriptExecutor) driver;
			Js.executeScript("arguments[0].removeAttribute('disabled');", Dropdown);
			Js.executeScript("arguments[0].disabled = false;", Dropdown);
			boolean Enabled = Dropdown.isEnabled();
			System.out.println("Dropdown enabled status: " + Enabled);
			LocalDate Today = LocalDate.now();
			String CurrentDay = String.valueOf(Today.getDayOfMonth());
			System.out.println("Today's date (day): " + CurrentDay);
			Select select = new Select(Dropdown);
			select.selectByVisibleText(CurrentDay);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(3000);

			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");			
		}
	}

	//<summary>
	// Test Case Title : Stock Out OG
	// Automation ID : TC235
	// Author:Vishnu RR
	// </summary>
	public void TC235_OldItemStockOutAndShipment() throws Exception
	{
		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on the Transaction button
		Thread.sleep(2000);
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite :OG Purchase
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(OldItemStockOutAndShipmentTestData.TC235_CustomerId, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"OG");
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));	      
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"22k");
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");
		if (base.isElementPresent(driver, CartChangedAlert)) {		
			try {
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e) {
			}
		}
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(2000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);		
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), UtilityTestData.GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), UtilityTestData.GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//Step 3: Click on Purchase
		Thread.sleep(1000);
		appUtils.HamBurgerButtonClick("iconShop");
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on the Bulk Item Stock Out button
		base.ClickCondition(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));

		//Step 5: Check that the date is selected as Yesterday
		DateTimeFormatter Format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate TodayDate = LocalDate.now();
		LocalDate TargetDate = TodayDate.minusDays(1);
		String ExpectedDate = TargetDate.format(Format);
		System.out.println("ExpectedDate : " + ExpectedDate);

		String DayText = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate("FromDatePicker", "Week days"));
		Thread.sleep(2000);
		String MonthText = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate("FromDatePicker", "Months"));
		Thread.sleep(2000);
		String YearText = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate("FromDatePicker", "Years"));	
		int correctedMonth = Integer.parseInt(MonthText) + 1;
		String ActualDate = String.format("%02d/%02d/%s", Integer.parseInt(DayText),correctedMonth,YearText);
		System.out.println("ActualDate : " + ActualDate);

		asrt.assertEquals( ActualDate, ExpectedDate," The date is not selected as Yesterday in Bulk item Stock out Page");

		//Step 6: Select Metal Type as Gold
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC235_MetalType);

		//Step 7: Select Item Type as OG
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC235_ItemType);

		//Step 8: Click on the Fetch button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));

		// Step 9: Click on the Stock Out button
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);

		if (base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")))
		{
			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");
			System.out.println("Stock out completed successfully!");
			Thread.sleep(5000);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}
		else if(base.isPresent(LoginPageObj.Edt_AlertText("There is no items in the list, please add line !")))
		{
			Thread.sleep(3000);
			WebElement DropDown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].removeAttribute('disabled');", DropDown);
			js.executeScript("arguments[0].disabled = false;", DropDown);
			LocalDate Today = LocalDate.now();
			String CurrentDay = String.valueOf(Today.getDayOfMonth());
			System.out.println("Current selected (day): " + CurrentDay);
			Select SelectDay = new Select(DropDown);
			SelectDay.selectByVisibleText(CurrentDay);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(5000);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));

			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");			
		}
	}

	// <summary>
	// Test Case Title : Stock Out OG,OGT,OP,OU,OT,OD
	// Automation ID : TC239
	// Author: Robin T. Abraham
	// </summary>
	public void TC239_OldItemStockOutAndShipment() throws Exception {

		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on the Transaction button
		Thread.sleep(4000);
		appUtils.HamBurgerButtonClick("iconShop");	
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(OldItemStockOutAndShipmentTestData.TC239_CustomerID, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(3000);

		//Prerequisite: Adding all stocks (OG,OGT,OP,OU,OT,OD) to prevent stock-out error
		Map<String, String> ProductMap = new LinkedHashMap<>();
		ProductMap.put("OGProduct", UtilityTestData.OGProduct);
		ProductMap.put("OGTProduct", UtilityTestData.OGTProduct);
		ProductMap.put("OPProduct", UtilityTestData.OPProduct);
		ProductMap.put("OUProduct", UtilityTestData.OUProduct);
		ProductMap.put("OTProduct", UtilityTestData.OTProduct);
		ProductMap.put("ODProduct", UtilityTestData.ODProduct);

		//Add each product to cart
		for (Map.Entry<String, String> Entry : ProductMap.entrySet()) {
			String ProductType = Entry.getKey();
			String CurrentProduct = Entry.getValue();
			System.out.println("Product type: " + ProductType);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			Thread.sleep(5000);
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), CurrentProduct);
			base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine", CurrentProduct));
			Thread.sleep(4000);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));

			// Select configuration based on product type
			Thread.sleep(2000);
			List<WebElement> ErrorMessage = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));
			if (!ErrorMessage.isEmpty()) {
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart(
						"buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
			}

			if (ProductType.equals("OTProduct")) {
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), "950");
				System.out.println("Selected 950 for OTProduct");
			} else {
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), "22k");
				System.out.println("Selected 22k for " + ProductType);
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

			base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
			Thread.sleep(10000);

			List<WebElement> ErrorMessages = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("messageText h5"));
			if (!ErrorMessages.isEmpty()) {base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("buttonContainer col no-shrink blockWidth leftmostDialogButton rightmostDialogButton marginTop20"));
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
			Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
			Thread.sleep(3000);

			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);

			base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);

			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), UtilityTestData.GrossPieces);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), UtilityTestData.GrossWeight);

			if (ProductType.equals("OGTProduct")) {
				System.out.println("OGT product - performing XRF steps");

				// Click XRF button
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("XRF"));

				// Enter Purity Reading 1				
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("xrfval1"), OldItemStockOutAndShipmentTestData.TC239_XRFPurity1);

				// Enter Purity Reading 2
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("xrfval2"), OldItemStockOutAndShipmentTestData.TC239_XRFPurity2);

				// Enter Purity Reading 3
				base.setData(NormalSaleGoldAndSilverObj.Ele_Name("xrfval3"), OldItemStockOutAndShipmentTestData.TC239_XRFPurity3);

				// Click OK
				base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

				System.out.println("XRF steps completed for OGT");
			}

			base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			Thread.sleep(10000);

			base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
			base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

			if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
				base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			}
			Thread.sleep(3000);
			System.out.println("Completed Product type: " + ProductType);
		}

		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		System.out.println("Payment completed for all types");

		//Step 3: Click on Purchase
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on the Bulk Item Stock Out button
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));

		//Step 5: Check that the date is selected as Yesterday
		Thread.sleep(3000);
		LocalDate CurrentDate = LocalDate.now();
		LocalDate ExpectedDate = CurrentDate.minusDays(1);
		String ActualDay = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Week days"));
		String ActualMonth =Integer.toString(Integer.parseInt(base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Months")))+1);
		String ActualYear = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Years"));
		String CombinedDate = ActualYear + "-" + ActualMonth + "-" + ActualDay;
		LocalDate ActualDate = LocalDate.parse(CombinedDate, DateTimeFormatter.ofPattern("yyyy-M-d"));

		asrt.assertEquals(ActualDate,ExpectedDate,"Date mismatch: "+ExpectedDate+" but found "+ActualDate+" in Bulk Item Stock Out Page.");

		//Step 6: Select Metal Type as Gold
		//Stocks Out
		//OG: Gold, OG
		//OGT: Gold, OG
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC239_GoldMetalType);

		//Step 7: Select Item Type as OG
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC239_OGItemType);

		//Step 8: Click on the Fetch button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));

		//Step 9: Click on the Stock Out button
		//Expected: Verify if validation appears "stock out is done successfully"
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		if (base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")))
		{
			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");
			System.out.println("Stock out completed successfully!");
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		else if(base.isPresent(LoginPageObj.Edt_AlertText("There is no items in the list, please add line !")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			Thread.sleep(3000);
			WebElement Dropdown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
			JavascriptExecutor Js = (JavascriptExecutor) driver;
			Js.executeScript("arguments[0].removeAttribute('disabled');", Dropdown);
			Js.executeScript("arguments[0].disabled = false;", Dropdown);
			boolean Enabled = Dropdown.isEnabled();
			System.out.println("Dropdown enabled status: " + Enabled);
			LocalDate Today = LocalDate.now();
			String CurrentDay = String.valueOf(Today.getDayOfMonth());
			System.out.println("Today's date (day): " + CurrentDay);
			Select select = new Select(Dropdown);
			select.selectByVisibleText(CurrentDay);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(3000);

			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' is not displayed in Bulk Item StockOut Page");			
		}

		//Click on the Transaction button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");	

		//Click on Purchase
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		//Click on the Bulk Item Stock Out button
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));

		//Step 6: Select Metal Type as Gold
		//Stocks Out
		//OP: Gold, OD
		//OU: Gold, OD
		//OD: Gold, OD
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC239_GoldMetalType);

		//Step 7: Select Item Type as OD
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC239_ODItemType);

		//Step 8: Click on the Fetch button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));

		//Step 9: Click on the Stock Out button
		//Expected: Verify if validation appears "stock out is done successfully"
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		if (base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")))
		{
			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' is not displayed in Bulk Item StockOut Page");
			System.out.println("Stock out completed successfully!");
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		else if(base.isPresent(LoginPageObj.Edt_AlertText("There is no items in the list, please add line !")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			Thread.sleep(3000);
			WebElement Dropdown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
			JavascriptExecutor Js = (JavascriptExecutor) driver;
			Js.executeScript("arguments[0].removeAttribute('disabled');", Dropdown);
			Js.executeScript("arguments[0].disabled = false;", Dropdown);
			boolean Enabled = Dropdown.isEnabled();
			System.out.println("Dropdown enabled status: " + Enabled);
			LocalDate Today = LocalDate.now();
			String CurrentDay = String.valueOf(Today.getDayOfMonth());
			System.out.println("Today's date (day): " + CurrentDay);
			Select select = new Select(Dropdown);
			select.selectByVisibleText(CurrentDay);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(3000);

			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");

		}	

		//Click on the Transaction button
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");	

		//Click on Purchase
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		//Click on the Bulk Item Stock Out button
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));

		//Step 6: Select Metal Type as Platinum
		//Stocks Out
		//OT: Platinum, OD
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC239_PlatinumMetalType);

		//Step 7: Select Item Type as OD
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC239_ODItemType);

		//Step 8: Click on the Fetch button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));

		//Step 9: Click on the Stock Out button
		//Expected: Verify if validation appears "stock out is done successfully"
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		if (base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")))
		{
			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' is not displayed in Bulk Item StockOut Page");
			System.out.println("Stock out completed successfully!");
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		else if(base.isPresent(LoginPageObj.Edt_AlertText("There is no items in the list, please add line !")))
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			Thread.sleep(3000);
			WebElement Dropdown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
			JavascriptExecutor Js = (JavascriptExecutor) driver;
			Js.executeScript("arguments[0].removeAttribute('disabled');", Dropdown);
			Js.executeScript("arguments[0].disabled = false;", Dropdown);
			boolean Enabled = Dropdown.isEnabled();
			System.out.println("Dropdown enabled status: " + Enabled);
			LocalDate Today = LocalDate.now();
			String CurrentDay = String.valueOf(Today.getDayOfMonth());
			System.out.println("Today's date (day): " + CurrentDay);
			Select select = new Select(Dropdown);
			select.selectByVisibleText(CurrentDay);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
			Thread.sleep(3000);

			asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");			
		}
	}

	// <summary>
	// Test Case Title : Stock out OG ,OD,OT & OU
	// Automation ID : TC237
	// Author: Chandana Babu
	// </summary>
	public void TC237_OldItemStockOutAndShipment() throws Exception {

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofMinutes(10));

		//Step 1: Login to POS
		Thread.sleep(3000);
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on the Transaction button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");	

		//OG,OD,OT,OU purchase
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(OldItemStockOutAndShipmentTestData.TC237_CustomerID, UtilityTestData.MenuBarOptions[0]);
		int i = 0;
		for (String Product: OldItemStockOutAndShipmentTestData.TC237_PurchaseProducts) {
			Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
			Wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Edt_Name("Search")));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), Product);
			base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
			Thread.sleep(5000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine", OldItemStockOutAndShipmentTestData.TC237_PurchaseOldProducts.get(i)));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),  OldItemStockOutAndShipmentTestData.TC237_ProductsPurity.get(i));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
			{
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			}
			Thread.sleep(3000);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));
			Thread.sleep(3000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
			Thread.sleep(1000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), UtilityTestData.GrossPieces);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), UtilityTestData.GrossWeight);
			base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			Thread.sleep(5000);
			for (int k = 0; k<5; k++) {
				String NetWeight  =  base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetwt"));
				if (NetWeight.equals("0.000")|| NetWeight.isEmpty()||NetWeight.isBlank()) {
					base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));	
					Thread.sleep(5000);
				}
				else {
					break;
				}
			}
			base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
			base.ClickCondition(LoginPageObj.Edt_Alert("Add to Cart"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
			{
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			}
			i++;
		}
		Thread.sleep(3000);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));	

		//Step 3: Click on Purchase
		Wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Purchase")));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on the Bulk Item Stock Out button
		Wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Bulk Item Stock Out")));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));

		//Step 5: Check that the date is selected as Yesterday
		Thread.sleep(3000);
		LocalDate CurrentDate = LocalDate.now();
		LocalDate ExpectedDate = CurrentDate.minusDays(1);
		String ActualDay = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Week days"));
		String ActualMonth =Integer.toString(Integer.parseInt(base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Months")))+1);
		String ActualYear = base.GetValue(OldItemStockOutAndShipmentObj.Ele_FromDate( "FromDatePicker" ,"Years"));
		String CombinedDate = ActualYear + "-" + ActualMonth + "-" + ActualDay;
		LocalDate ActualDate = LocalDate.parse(CombinedDate, DateTimeFormatter.ofPattern("yyyy-M-d"));
		asrt.assertEquals(ActualDate,ExpectedDate,"Date mismatch: "+ExpectedDate+" but found "+ActualDate+" in Bulk Item Stock Out Page.");

		int j =0;
		for(String Product: OldItemStockOutAndShipmentTestData.TC237_StockOutProducts) {

			//Step 6: Select Metal Type as Gold,Select metal type as " Gold",Select metal type as " Platinum"
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"),  OldItemStockOutAndShipmentTestData.TC237_MetalTypes.get(j));

			//Step 7: Select Item Type as OG, Select item type as " OD", Select item type as " OD"
			base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), Product);

			//Step 8: Click on the Fetch button
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
			Thread.sleep(5000);	

			//Step 9: Click on the Stock Out button
			//Expected: Verify if validation appears "stock out is done successfully"
			if (base.isPresent(LoginPageObj.Ele_ErrorMessage("win-itembox"))) {
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
				Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")));
				asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");
				System.out.println("Stock out completed successfully!");
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			else {
				Thread.sleep(3000);
				WebElement Dropdown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
				JavascriptExecutor Js = (JavascriptExecutor) driver;
				Js.executeScript("arguments[0].removeAttribute('disabled');", Dropdown);
				Js.executeScript("arguments[0].disabled = false;", Dropdown);
				boolean Enabled = Dropdown.isEnabled();
				System.out.println("Dropdown enabled status: " + Enabled);
				LocalDate Today = LocalDate.now();
				String CurrentDay = String.valueOf(Today.getDayOfMonth());
				System.out.println("Today's date (day): " + CurrentDay);
				Select select = new Select(Dropdown);
				select.selectByVisibleText(CurrentDay);
				base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
				Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Ele_ErrorMessage("win-itembox")));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
				Wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")));
				asrt.assertTrue(base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")),"Validation message 'Bulk item stock out saved successfully !' not displayed in Bulk Item StockOut Page");			
				System.out.println("Stock out completed successfully!");
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			j++;
		}
	}

	// <summary>
	// Test Case Title : check Stock summary
	// Automation ID : TC240
	// Author: Aiswarya
	// </summary>
	public void TC240_OldItemStockOutAndShipment() throws Exception {

		//Step 1 :Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		WebDriverWait wait                               = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebDriverWait HamBurgerWait                      = new WebDriverWait(driver, Duration.ofSeconds(5));	

		//Prerequisite : Checking Stock Summary Before Bulk Item Stock Out
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));
		Thread.sleep(3000);
		WebElement Dropdown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		Js.executeScript("arguments[0].removeAttribute('disabled');", Dropdown);
		Js.executeScript("arguments[0].disabled = false;", Dropdown);
		boolean Enabled = Dropdown.isEnabled();
		System.out.println("Dropdown enabled status: " + Enabled);
		LocalDate Today = LocalDate.now();
		String CurrentDay = String.valueOf(Today.getDayOfMonth());
		Select DateStockOut = new Select(Dropdown);
		DateStockOut.selectByVisibleText(CurrentDay);
		Thread.sleep(1000);
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC240_MetalType);
		Thread.sleep(1000);
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC240_ItemType);
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
		Thread.sleep(1000);
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK")))		
		{
			System.out.println("Stock out completed successfully!");
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconHome");
		Thread.sleep(3000);
		for(int i=0;i<=5;i++)
		{
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","POS Reports"));
			try {
				if(HamBurgerWait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Btn_SignInButton("Stock Summary"))) != null)
				{
					break;
				}
			}catch(Exception e) {}
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryBeforeStockOutPDF = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_Before_StockOut");
		String StockSummaryBeforeStockOutPath = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryBeforeStockOutPDF;
		String OGGrossWeightBeforePurchase = pdfUtils.StockSummaryStockOut(StockSummaryBeforeStockOutPath, "Old Gold Own", "22K"); // write new method to get 22k  from report

		//OG purchase
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		appUtils.SearchByCustomerID(OldItemStockOutAndShipmentTestData.TC240_CustomerID, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(5000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), UtilityTestData.OGProduct);
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine", UtilityTestData.Product));        
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),  "22k"); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");
		if (base.isElementPresent(driver, CartChangedAlert)) {		
			try {
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e) {
			}
		}
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), UtilityTestData.GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), UtilityTestData.GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("RTGS/NEFT"));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment","iconReturnKey"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));	
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconHome");
		Thread.sleep(3000);
		for(int i=0;i<=5;i++)
		{
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","POS Reports"));
			try {
				if(HamBurgerWait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Btn_SignInButton("Stock Summary"))) != null)
				{
					break;
				}
			}catch(Exception e) {}
		}
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryAfterOGPDF = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_Before_StockOut");
		String StockSummaryAfterOGPath = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryAfterOGPDF;
		String OGGrossWeightAfterOG = String.format("%.2f", Double.parseDouble(pdfUtils.StockSummaryStockOut(StockSummaryAfterOGPath, "Old Gold Own", "22K")));
		String ExpectedOGGrossWtAfterPurchase = String.format("%.2f", (Double.parseDouble(OGGrossWeightBeforePurchase) + Double.parseDouble(UtilityTestData.GrossWeight)));

		asrt.assertEquals(Double.parseDouble(OGGrossWeightAfterOG), Double.parseDouble(ExpectedOGGrossWtAfterPurchase),1, "Grossweight mismatch: Expected is " + ExpectedOGGrossWtAfterPurchase + " but got " + OGGrossWeightAfterOG + " in Stock summary details after OG Purchase.");

		appUtils.HamBurgerButtonClick("iconShop"); 
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));
		Thread.sleep(3000);
		WebElement DropDown = driver.findElement(OldItemStockOutAndShipmentObj.Ele_FromDate("ToDatePicker", "Week days"));
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("arguments[0].removeAttribute('disabled');", DropDown);
		JS.executeScript("arguments[0].disabled = false;", DropDown);
		boolean IsEnabled = DropDown.isEnabled();
		System.out.println("Dropdown enabled status: " + IsEnabled);
		LocalDate TodayDate = LocalDate.now();
		String CurrentDate = String.valueOf(TodayDate.getDayOfMonth());
		Select DayStockOut = new Select(DropDown);
		DayStockOut.selectByVisibleText(CurrentDate);
		Thread.sleep(1000);
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrMetalType"), OldItemStockOutAndShipmentTestData.TC240_MetalType);
		Thread.sleep(1000);
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrFetchType"), OldItemStockOutAndShipmentTestData.TC240_ItemType);
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Fetch"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(3000);
		if (base.isPresent(LoginPageObj.Edt_AlertText("Bulk item stock out saved successfully !")))
		{
			System.out.println("Stock out completed successfully!");
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}

		// Step 2: Click on Home button
		// Step 3: Click on POS Report
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconHome");
		Thread.sleep(3000);
		for(int i=0;i<=5;i++)
		{
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","POS Reports"));
			try {
				if(HamBurgerWait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Btn_SignInButton("Stock Summary"))) != null)
				{
					break;
				}
			}catch(Exception e) {}
		}		

		// Step 4: Click on Stock Summary
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(5000);

		// Step 5: Click on View Report
		// Note: Check the values
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryAfterStockOutPath = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_After_StockOut");
		String StockSummaryAfterStockOutPDF = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryAfterStockOutPath;

		// After stock out
		//Expected : Verify if the respective weight is reduced in stock summary report
		String OGGrossWeightAfterStockOut = String.format("%.2f",Double.parseDouble(pdfUtils.StockSummaryStockOut(StockSummaryAfterStockOutPDF, "Old Gold Own", "22K")));
		String ExpectedOGGrossWeight = String.format("%.2f",(Double.parseDouble(OGGrossWeightAfterOG) - Double.parseDouble(UtilityTestData.GrossWeight)));
	
		asrt.assertEquals(Double.parseDouble(OGGrossWeightAfterStockOut),Double.parseDouble(ExpectedOGGrossWeight),1, " Grossweight mismatch: Expected is " + ExpectedOGGrossWeight + " but got " + OGGrossWeightAfterStockOut + "in Stock summary details after sale");

		Thread.sleep(3000); 
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}


}
