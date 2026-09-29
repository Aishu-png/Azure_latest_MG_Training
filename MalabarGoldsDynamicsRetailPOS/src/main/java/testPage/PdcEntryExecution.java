package testPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import objectRepository.PdcEntryExecution_Obj;
import testData.CommonData;
import testData.PdcEntryExecution_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

public class PdcEntryExecution extends BasePge {

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
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	PdcEntryExecution_Obj PdcEntryExecutionObj = new PdcEntryExecution_Obj();

	public PdcEntryExecution(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
	}

	//<summary>
	// Test Case Title : PDC for Normal advance
	// Automation ID : TC244
	// Author : Benson 
	// </summary>
	public void TC244_PdcEntryExecution() throws Exception{

		//Step 1 : Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2 :  Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3 : Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4 : Click on PDC entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5 : Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC244_CustomerNo,UtilityTestData.MenuBarOptions[7]);     
		Thread.sleep(2000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));

		//Step 6 : Choose PDC type as normal advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC244_PdcType);

		//Step 7 : Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Step 8 : Enter the following
		//Step 8.1 : Issue bank 
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC244_IssueBank);

		//Step 8.2 : Branch name
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC244_Branch);

		//Step 8.3 : MICR number
		String MicrNumber=base.CreateRandomNumber(9); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);

		//Step 8.4 : Cheque number
		//Step 8.5 : Cheque date
		String ChequeNo=base.CreateRandomNumber(6); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNo);

		//Step 8.6 : Cheque amount
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUEAMOUNT"),PdcEntryExecutionTestData.TC244_ChequeAmount);

		//Step 9 : Click on add cheque and then click commit PDC
		base.buttonClick(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000);

		//Nominee details popup 
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), PdcEntryExecutionTestData.TC244_NomineeDobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));		
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));

		//Navigate to ERP
		erpUtils.PDCEntryDepositAndExecution(PdcId,PdcEntryExecutionTestData.TC244_CustomerNo );
	}
	//<summary>
	// Test Case Title : PDC for Normal advance
	// Automation ID : TC247
	// Author : Christy Reji
	// </summary>
	public void TC247_PdcEntryExecution() throws Exception{

		//Step 1 : Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2 :  Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3 : Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4 : Click on PDC entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5 : Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC247_CustomerNo,UtilityTestData.MenuBarOptions[7]);     
		Thread.sleep(2000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));

		//Step 6 : Choose PDC type as normal advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC247_PdcType);

		//Step 7 : Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Step 8 : Enter the following
		//Step 8.1 : Issue bank 
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC247_IssueBank);

		//Step 8.2 : Branch name
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC247_Branch);

		//Step 8.3 : MICR number
		String MicrNumber=base.CreateRandomNumber(9); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);

		//Step 8.4 : Cheque number
		//Step 8.5 : Cheque date
		String ChequeNo=base.CreateRandomNumber(6); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNo);

		//Step 8.6 : Cheque amount
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUEAMOUNT"),PdcEntryExecutionTestData.TC247_ChequeAmount);

		//Step 9 : Click on add cheque and then click commit PDC
		base.buttonClick(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000);

		//Nominee details popup 
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), PdcEntryExecutionTestData.TC247_NomineeDobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));		
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));

		//Navigate to ERP
		//1.Go to PDC entry
		//2.Select customer and click on confirm
		// 3.Go to PDC deposit
		// 4.Click new --> Select warehouse and click on fetch button
		// 5.In lines, choose the deposit bank and cheque presented date against the required customer
		// 6.Click on confirm button
		// 7.Go to PDC execution
		// 8.Click new --> Select warehouse and click on fetch button
		// 9.In lines, choose honoured or dishonoured.
		// 10.Choose the honoured date
		// 11.Click on confirm.
		//expected:
		//2.verify already created PDC entry listed
		//6. After confirmation the PDC execution code the Confirm field should be shown Tick icon(✓) .
		//11.After confirmation the PDC execution code the Confirm field should be shown Tick icon(✓) .
		Thread.sleep(7000);
		erpUtils.PDCEntryDepositAndExecution(PdcId,PdcEntryExecutionTestData.TC247_CustomerNo );
	}

	//summary>
	// Test Case Title : PDC colletion for existing scheme
	// Automation ID : TC242
	// Author:Sangeetha M S
	// </summary>
	public void TC242_PdcEntryExecution() throws Exception
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre-requsite : Customer must have an existing scheme
		Thread.sleep(2000);
		appUtils.HamBurgerButtonClick("iconShop");
		String IdNumber=base.CreateRandomNumber(12);
		String SchemeId = appUtils.OpenGppAndTakeCollection(
				PdcEntryExecutionTestData.TC242_CustomerNo,
				PdcEntryExecutionTestData.TC242_Scheme,
				PdcEntryExecutionTestData.TC242_InstallmentAmount,
				PdcEntryExecutionTestData.TC242_SchemeCode,
				PdcEntryExecutionTestData.TC242_IdType,
				IdNumber);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3.Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4.Click on PDC entry
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5.Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC242_CustomerNo,UtilityTestData.MenuBarOptions[7]);				
		Thread.sleep(3000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));

		//Step 6.Choose PDC type as GPP_Advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC242_PdcType);

		//Step 6.1 Click on search button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));

		//Step 6.2 Choose the required gpp id
		Thread.sleep(5000);
		boolean found   = false;

		for (int scroll = 0; scroll < 40; scroll++)  // up to 40 scroll attempts
		{
			List<WebElement> GPPAccountList = driver.findElements( LoginPageObj.Ele_ErrorMessage("h4 ellipsis cell"));
			for (int i = 0; i < GPPAccountList.size(); i++)
			{
				WebElement GPPSelected = GPPAccountList.get(i);
				String text            = GPPSelected.getText().trim();
				if (text.equalsIgnoreCase(SchemeId))
				{
					// Scroll to the target element  
					base.ScrollToWebElements(GPPSelected);
					base.ClickCondition( NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", SchemeId) );
					System.out.println("FOUND → Clicked on PDC Reference: " + SchemeId);
					found = true;
					break;
				}
			}
			if (found) break;
			// Scroll down for next batch
			base.ScrollToWebElements(GPPAccountList.get(GPPAccountList.size() - 1));
			Thread.sleep(1000);
		}

		if (!found) { System.out.println("TARGET NOT FOUND → " + SchemeId);}
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

		//Step 6.3 Click on OK button
		Thread.sleep(1000);
		//base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

		//Step 7.Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Step 8.Enter the following
		//Step 8.1 Issue bank
		//Step 8.2 Branch name
		//Step 8.3 MICR number
		//Step 8.4 Cheque number
		//Step 8.5 Cheque date
		//Step 8.6 Cheque amount
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC242_IssueBank);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC242_Branch);
		String MicrNumber=base.CreateRandomNumber(9); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);
		String ChequeNumber=base.CreateRandomNumber(6); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNumber);

		//Step 9.Click on add cheque and then click commit PDC
		base.ClickCondition(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000); 
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), PdcEntryExecutionTestData.TC242_NomineeDobYear);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));				
		}
		base.ClickCondition(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));

		//ERP
		//Step 10.Go to PDC entry
		//Step 11.Select customer and click on confirm
		//Step 12.Go to PDC deposit
		//Step 13.Click new --> Select warehouse and click on fetch button
		//Step 14.In lines, choose the deposit bank and cheque presented date against the required customer
		//Step 15.Click on confirm button
		//Step 16.Go to PDC execution
		//Step 17.Click new --> Select warehouse and click on fetch button
		//Step 18.In lines, choose honoured or dishonoured.
		//Step 19.Choose the honoured date
		//Step 20.Click on confir
		erpUtils.PDCEntryDepositAndExecution(PdcId,PdcEntryExecutionTestData.TC242_CustomerNo );
	}

	//<summary>
	// Test Case Title : PDC for add on advance
	// Automation ID : TC245
	// Author : Neethu
	// </summary>
	public void TC245_PdcEntryExecution() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2 :  Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Prerequisite: customer must have an existing parent offer advance
		Thread.sleep(3000);
		Map<String, String> OfferAdvance = appUtils.CreateWeightBookedOfferAdvance
				(PdcEntryExecutionTestData.TC245_CustomerNo,
						PdcEntryExecutionTestData.TC245_Pieces,
						PdcEntryExecutionTestData.TC245_OfferGrossWeight1,
						UtilityTestData.Company[0],
						UtilityTestData.Location[0],
						PdcEntryExecutionTestData.TC245_DepositPercent);
		String OrderId = OfferAdvance.get("OrderId");
		String AdvanceAmount = OfferAdvance.get("AdvanceAmount");
		String WillingToPayAmount = OfferAdvance.get("WillingToPayAmount");
		System.out.println("OrderId : "+OrderId);
		System.out.println("AdvanceAmount : "+AdvanceAmount);
		System.out.println("WillingToPayAmount : "+WillingToPayAmount);

		//Go to transactions, Search customer, Click on Add to sale
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC245_CustomerNo ,UtilityTestData.MenuBarOptions[0]);

		//Choose advance-->advance collection
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"));
		Thread.sleep(3000);
		if(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}

		//Choose type as order
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"), "Order");
		Thread.sleep(5000);

		//Select the transaction number from the drop down
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), OrderId);

		//Enter the deposit amount
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"), WillingToPayAmount);
		System.out.println(WillingToPayAmount +" WillingToPayAmount");
		System.out.println(AdvanceAmount +" AdvanceAmount");

		//Choose the sales person		
		Thread.sleep(3000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);	

		//Click on deposit button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));

		//Click on the Amount
		Thread.sleep(3000);
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));

		//Select a Payment method(Cash or Card)
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		String ExpectedAdvanceAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", "");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		Thread.sleep(5000);

		//Step 3 : Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4 : Click on PDC entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5 : Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC245_CustomerNo,UtilityTestData.MenuBarOptions[7]);     
		Thread.sleep(2000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));
		System.out.println("PdcId :"+PdcId);

		//Step 6 : Choose PDC type as offer advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC245_PdcType);

		//Step 6.1 Click on search button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));

		//Step 6.2 Choose the required advance id
		//		Thread.sleep(9000);
		//		List<WebElement> GPPAccountList = driver.findElements(LoginPageObj.Ele_ErrorMessage("h4 ellipsis cell"));
		//		for (int i = GPPAccountList.size() - 1; i >= 0; i--)
		//		{
		//			Thread.sleep(5000);
		//			WebElement GPPSelected = GPPAccountList.get(i);
		//			if (GPPSelected.getText().trim().equalsIgnoreCase(OrderId))
		//			{
		//				base.ScrollToWebElement(GPPSelected);
		//				GPPSelected.click();
		//				System.out.println("Clicked on PDC Reference (from bottom): " + OrderId);
		//				break;
		//			}
		//		}
		//					Thread.sleep(5000);
		//				base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));


		//			base.scrollToElement(PdcEntryExecutionObj.Ele_OrderID("grow tableContent dataListPlaceholder win-selectionstylefilled win-disposable win-listview win-element-resize", OrderId));
		//			Thread.sleep(10000);
		//			base.excuteJsClick(PdcEntryExecutionObj.Ele_OrderID("grow tableContent dataListPlaceholder win-selectionstylefilled win-disposable win-listview win-element-resize", OrderId));
		//			Thread.sleep(10000);

		//Step 6.2 Choose the required gpp id
		//Step 6.3 Click on OK button
		Thread.sleep(5000);
		boolean found   = false;

		for (int scroll = 0; scroll < 40; scroll++)  // up to 40 scroll attempts
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LoginPageObj.Ele_ErrorMessage("h4 ellipsis cell")));
			List<WebElement> GPPAccountList = driver.findElements( LoginPageObj.Ele_ErrorMessage("h4 ellipsis cell"));
			for (int i = 0; i < GPPAccountList.size(); i++)
			{
				WebElement GPPSelected = GPPAccountList.get(i);
				String text            = GPPSelected.getText().trim();
				if (text.equalsIgnoreCase(OrderId))
				{
					// Scroll to the target element  
					base.ScrollToWebElements(GPPSelected);
					base.ClickCondition( NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", OrderId) );
					System.out.println("FOUND → Clicked on PDC Reference: " + OrderId);
					found = true;
					break;
				}
			}
			if (found) break;
			// Scroll down for next batch
			base.ScrollToWebElements(GPPAccountList.get(GPPAccountList.size() - 1));
			Thread.sleep(1000);
		}

		if (!found) { System.out.println("TARGET NOT FOUND → " + OrderId);}
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

		//Step 6.3 Click on OK button
		Thread.sleep(5000);
		//base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

		//Step 7.Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Step 8.Enter the following
		//Step 8.1 Issue bank
		//Step 8.2 Branch name
		//Step 8.3 MICR number
		//Step 8.4 Cheque number
		//Step 8.5 Cheque date
		//Step 8.6 Cheque amount
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC245_IssueBank);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC245_Branch);
		String MicrNumber=base.CreateRandomNumber(9);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);
		String ChequeNumber=base.CreateRandomNumber(6);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNumber);

		//Step 9.Click on add cheque and then click commit PDC
		base.ClickCondition(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000);
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), PdcEntryExecutionTestData.TC245_NomineeDobYear);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));				
		}
		base.ClickCondition(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));

		//ERP
		//Step 10.Go to PDC entry
		//Step 11.Select customer and click on confirm
		//Step 12.Go to PDC deposit
		//Step 13.Click new --> Select warehouse and click on fetch button
		//Step 14.In lines, choose the deposit bank and cheque presented date against the required customer
		//Step 15.Click on confirm button
		//Expected: After confirmation the PDC Deposit code the Confirm field should be shown Tick icon(✓)
		//Step 16.Go to PDC execution
		//Step 17.Click new --> Select warehouse and click on fetch button
		//Step 18.In lines, choose honoured or dishonoured.
		//Step 19.Choose the honoured date
		//Step 20.Click on confirm
		//Expected: After confirmation the PDC execution code the Confirm field should be shown Tick icon(✓) .
		erpUtils.PDCEntryDepositAndExecution(PdcId,PdcEntryExecutionTestData.TC245_CustomerNo );

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}
	
	//summary>
	// Test Case Title : PDC for offer advance
	// Automation ID : TC243
	// Author:Christy Reji
	// </summary>
	public void TC243_PdcEntryExecution() throws Exception
	{
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre-Requisite:customer must have an existing offer advance
		Map<String, String> AdvanceDetails = appUtils.CreateWeightBookedOfferAdvance(
				PdcEntryExecutionTestData.TC243_CustomerID,
				PdcEntryExecutionTestData.TC243_Pieces,
				PdcEntryExecutionTestData.TC243_GrossWeight,
				UtilityTestData.Company[0],
				UtilityTestData.Location[0],
				PdcEntryExecutionTestData.TC243_DepositPercent);

		String OrderId            = AdvanceDetails.get("OrderId");

		System.out.println("OrderId : "+OrderId);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3.Click on gpp
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4.Click on PDC entry
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5.Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC243_CustomerID,UtilityTestData.MenuBarOptions[7]);				
		Thread.sleep(3000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));
		System.out.println("PdcId : "+PdcId);

		//Step 6.Choose PDC type as Offer advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC243_PdcType);

		//Step 6.1 Click on search button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));

		//Step 6.2 Choose the required offer advance  id		
		Thread.sleep(9000);
		boolean found   = false;

		for (int scroll = 0; scroll < 40; scroll++)  // up to 40 scroll attempts
		{   
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LoginPageObj.Ele_ErrorMessage("h4 ellipsis cell")));
			List<WebElement> GPPAccountList = driver.findElements( LoginPageObj.Ele_ErrorMessage("h4 ellipsis cell"));
			for (int i = 0; i < GPPAccountList.size(); i++)
			{
				WebElement GPPSelected = GPPAccountList.get(i);
				String text            = GPPSelected.getText().trim();
				if (text.equalsIgnoreCase(OrderId))
				{
					// Scroll to the target element  
					base.ScrollToWebElements(GPPSelected);
					base.ClickCondition( NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", OrderId) );
					System.out.println("FOUND → Clicked on PDC Reference: " + OrderId);
					found = true;
					break;
				}
			}
			if (found) break;
			// Scroll down for next batch
			base.ScrollToWebElements(GPPAccountList.get(GPPAccountList.size() - 1));
			Thread.sleep(1000);
		}

		if (!found) { System.out.println("TARGET NOT FOUND → " + OrderId);}
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

		//Step 7.Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);
		Thread.sleep(9000);

		//Step 8.Enter the following
		//Step 8.1 Issue bank
		//Step 8.2 Branch name
		//Step 8.3 MICR number
		//Step 8.4 Cheque number
		//Step 8.5 Cheque date
		//Step 8.6 Cheque amount
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC243_IssueBank);
		Thread.sleep(3000);
		base.ClickClearEnter(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC243_Branch);
		String MicrNumber  = base.CreateRandomNumber(9); 
		Thread.sleep(3000);
		base.ClickClearEnter(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);
		String ChequeNumber = base.CreateRandomNumber(6); 
		base.ClickClearEnter(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNumber);

		//Step 9.Click on add cheque and then click commit PDC
		Thread.sleep(6000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(6000); 	
		if (base.isExists(LoginPageObj.Ele_ErrorMessage("win-itembox"))) {
			Thread.sleep(3000);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));
		} else {
			Assert.fail("Offer advance not displayed in PDC entry page");
		}

		//ERP
		//Step 10.Go to PDC entry
		//Step 11.Select customer and click on confirm
		//Step 12.Go to PDC deposit
		//Step 13.Click new --> Select warehouse and click on fetch button
		//Step 14.In lines, choose the deposit bank and cheque presented date against the required customer
		//Step 15.Click on confirm button
		//Step 16.Go to PDC execution
		//Step 17.Click new --> Select warehouse and click on fetch button
		//Step 18.In lines, choose honoured or dishonoured.
		//Step 19.Choose the honoured date
		//Step 20.Click on confir
		//Expected
		//11.verify already created PDC entry listed
		// 15. After confirmation the PDC Deposit code the Confirm field should be shown Tick icon(✓) .
		// 20.After confirmation the PDC execution code the Confirm field should be shown Tick icon(✓) 
		erpUtils.PDCEntryDepositAndExecution(PdcId,PdcEntryExecutionTestData.TC243_CustomerID );
	}

	//<summary>
	// Test Case Title : Settlement of advance done with new pdc entry
	// Automation ID : TC248
	// @Author :Gokul.P
	// </summary>
	public void TC248_PdcEntryExecution() throws Exception
	{
		//Step 1 : Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2 :  Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");
		HashMap<String,String> NormalAdvanceID=  erpUtils.CreatePDCEntry(PdcEntryExecutionTestData.TC248_CustomerNo,PdcEntryExecutionTestData.TC248_PdcType);
		String AdvanceId=NormalAdvanceID.get("Advance ID");

		// 3. Select customer
		// 4. Click on add to estimation button
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC248_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		// 5. Click on customer adjustment button
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		// 6. click on normal advance settlement
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));

		// 7. Select advance and click on cart button
		// 8. Enter OTP
		// Expected:The balance amount and adjustment amount for the selected advances should be shown correctly
		Thread.sleep(4000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),AdvanceId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		String BalanceAmount = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Balance amount", "h4 ellipsis cell"));
		String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldandSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));
		String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(AdvanceRate, "22k");
		TimeUnit.MINUTES.sleep(6);
		double Adjustment     = Double.parseDouble(AdjustmentAmount);
		double Quantity22k    = Double.parseDouble(BalanceAmount)/ Double.parseDouble(AdvanceRate);
		System.out.println("Advance Quantity:"+Quantity22k);
		appUtils.HamBurgerButtonClick("iconShop");	

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount+ " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		// 9. Scan SKU
		// Note: Check average rate
		// Expected:Check calculation
		// Note:Billing Screen:No of Product lines,Displayed Item Name,Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX,	 Total Amount
		List<String> GoldSkuList      = appUtils.SelectMultipleSKUs(PdcEntryExecutionTestData.TC248_SkuCount,PdcEntryExecutionTestData.TC248_TransferType,PdcEntryExecutionTestData.TC248_FromCounter,PdcEntryExecutionTestData.TC248_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");		
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		List<String> ProductTotalList         = new ArrayList<>();
		ProductNames.add(PdcEntryExecutionTestData.TC248_DepositAdjustment);
		ProductQtyList.add(PdcEntryExecutionTestData.TC248_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		String ErpGoldRate24k = ErpGoldRateFor24K;
		int SkuCounter        = 1;
		Thread.sleep(1000);
		erpUtils.ResetRemainingReturnWeight();
		for (String Sku : GoldSkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuCounter,String.valueOf(Quantity22k),"22k",AdvanceRate,ErpGoldRate24k);
			SkuCounter++;
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(GoldSkuList, ScannedSkuDataMap);
		String SubTotal                 = TransactionDataLine.get("Subtotal");
		String NetTotal                 = TransactionDataLine.get("NetTotal");
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
			if (i == 1) {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();				
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();				
				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
			}
			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge    = Quantity.get(i).getText();
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
		double ExpectedTax             = (Double.parseDouble(ProductTotalList.get(1))) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		double Invoice                 = Double.parseDouble(ProductTotalList.get(1))+ExpectedTax;
		System.out.println("Invoice after adding tax: "+Invoice);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		double ExpectedTotalAmountInBillingPge = InvoicAmount+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, Double.parseDouble(String.valueOf(ExpectedTotalAmountInBillingPge).replace("-", "").trim()), 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		double NetTotalBillingScreen      = Invoice-Double.parseDouble(ProductTotalList.get(0));
		String TotalGrossWt               = String.format("%.3f", Double.parseDouble(ProductQtyList.get(1)));

		// 10. save estimate
		// Expected:Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative","Back" ));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);	
		String FirstInvoice        = pdfUtils.DownloadAndRenameLatestPDF("TC248_ProformaInvoice");
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
		// 12. Select estimate and click on recall estimation
		// Expected:Verify whether the recalled item is the same as the item in the cart.		
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 
		appUtils.ValidateBillingAndRecallScreenData(EstmnNumber,ExpectedLinesCount,ProductNames,ProductQtyList, SubTotal, Tax, NetTotal);

		// 13.Select the Transaction Type as Sales 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), PdcEntryExecutionTestData.TC248_TransactionTypeSales);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// 14.Click on Process Estimation 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
		base.setZoom(driver, 60);


		// 15.Choose a sales representative
		// Note:-15.Cashier Screen after recall:No of Product lines,Displayed Item Name	 ,Displayed Quantity ,Displayed TOTAL (without Tax) ,Displayed Subtotal ,TAX ,Total Amount
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));}		
		Map<String, String> TransactionDataLineCashierPage = appUtils.TransactionSKUsLinesVerify(GoldSkuList, ScannedSkuDataMap);
		String TaxCashierPage                      = TransactionDataLineCashierPage.get("Tax");
		String TotalAmountCashierPage              = TransactionDataLineCashierPage.get("TotalAmount");
		String ActualLinesCountCashierPage         = TransactionDataLineCashierPage.get("LinesCount");
		List<WebElement> AllProductRowsCashierPage = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));		
		String ExpectedLinesCountCashierPage       = Integer.toString(AllProductRowsCashierPage.size());
		asrt.assertEquals(ActualLinesCountCashierPage, ExpectedLinesCountCashierPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountCashierPage + " but found " + ActualLinesCountCashierPage + " in billing page");

		double InvoicAmountCashierPage = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName          = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierPage = ItemName.get(i).getText();
			if (i == 1) {
				String ExpectedItemNameInCashierPage = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();				
				asrt.assertEquals(ActualItemNameInCashierPage, ExpectedItemNameInCashierPage,"Item name mismatch: Expected is " + ExpectedItemNameInCashierPage + " but got "+ ActualItemNameInCashierPage + " in billing page");
			} else {
				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();				
				asrt.assertEquals(ActualItemNameInCashierPage, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInCashierPage + " in billing page");
			}
			List<WebElement> Quantity             = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInCashierPage    = Quantity.get(i).getText();
			String ExpectedQuantityInCashierPage  = ProductQtyList.get(i);
			double ActualQtyInCashierPage         = Double.parseDouble(ActualQuantityInCashierPage);
			double ExpectedQtyInCashierPage       = Double.parseDouble(ExpectedQuantityInCashierPage);
			asrt.assertEquals(ActualQtyInCashierPage, ExpectedQtyInCashierPage, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInCashierPage + " but got " + ActualQtyInCashierPage + " in billing page");

			List<WebElement> TotalWithoutTax            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierPage   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			InvoicAmountCashierPage                     = InvoicAmountCashierPage + Double.parseDouble(ActualTotalWithoutTaxInCashierPage);
			String ExpectedTotalWithoutTaxInCashierPage = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierPage.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierPage), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierPage + " but got "+ ActualTotalWithoutTaxInCashierPage + " in billing page");
		}
		String ExpectedSubTotalInCashierPage = String.format("%.2f", InvoicAmountCashierPage).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierPage   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierPage),Double.parseDouble(ExpectedSubTotalInCashierPage), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierPage + " but found " + ActualSubTotalInCashierPage+ " in billing page");

		String ActualTaxInCashierPage   = TaxCashierPage.replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxCashierPage   = (Double.parseDouble(ProductTotalList.get(1))) * 0.03;
		String ExpectedTaxInCashierPage = String.format("%.2f", ExpectedTaxCashierPage);
		double InvoiceCashierPage       = Double.parseDouble(ProductTotalList.get(1))+ExpectedTax;
		System.out.println("Invoice after adding tax: "+Invoice);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierPage), Double.parseDouble(ExpectedTaxInCashierPage), 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierPage + " but found " + ActualTaxInCashierPage+ " in billing page");

		double ExpectedTotalAmountInCashierPage = InvoicAmountCashierPage+ Double.parseDouble(ActualTaxInCashierPage);
		double ActualTotalAmountInCashierPage   = Double.parseDouble(TotalAmountCashierPage.replaceAll("[^\\d.]", "").trim());
		asrt.assertEquals(ActualTotalAmountInCashierPage, Double.parseDouble(String.valueOf(ExpectedTotalAmountInCashierPage).replace("-", "").trim()), 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierPage + " but found " + ActualTotalAmountInCashierPage + " in billing page");
		double NetTotalInCashierPage      = InvoiceCashierPage-Double.parseDouble(ProductTotalList.get(0));

		// 16.Click on the Amount
		Thread.sleep(2000);
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));

		// 17.Select a Payment method(Cash or Card)
		Thread.sleep(500);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		String ExpectedAdvanceAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", "");
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

		// 18.Post the Invoice
		// Expected:Check final invoice details
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String SalesInvoiceName  = pdfUtils.DownloadAndRenameLatestPDF("TC248_SalesInvoice");
		String SalesInvoicePath  = System.getProperty("user.dir") + "\\Invoices\\" + SalesInvoiceName;
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
				String.valueOf(NetTotalInCashierPage),
				ExpectedAdvanceAmount,
				String.valueOf(Adjustment),
				GoldSkuList,
				ScannedSkuDataMap
				) ;
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	
	}

	//summary>
	// Test Case Title : PDC collection for new scheme [Verify Bank branch name accepts special characters and max limit 100 characters]
	// Automation ID : TC241
	// Author: Chandana Babu
	// </summary>
	public void TC241_PdcEntryExecution() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		//Step 1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre-requsite : Customer must have a scheme(GB,GG,GS)		
		Thread.sleep(2000);
		appUtils.HamBurgerButtonClick("iconShop");
		String IdNumber=base.CreateRandomNumber(12);
		String SchemeId = appUtils.OpenANewGppScheme(
				PdcEntryExecutionTestData.TC241_CustomerNo,
				PdcEntryExecutionTestData.TC241_Scheme,
				PdcEntryExecutionTestData.TC241_InstallmentAmount,
				PdcEntryExecutionTestData.TC241_SchemeCode,
				PdcEntryExecutionTestData.TC241_IdType,
				IdNumber);

		//Step 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3.Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4.Click on PDC entry
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5.Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC241_CustomerNo,UtilityTestData.MenuBarOptions[7]);				
		Thread.sleep(3000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));

		//Step 6.Choose PDC type as GPP_Advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC241_PdcType);

		//Step 6.1 Click on search button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));

		//Step 6.2 Choose the required gpp id
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Payement("Search PDC reference")));		
		//		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", SchemeId));
		//		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", SchemeId));

		//Step 6.2 Choose the required gpp id
		//Step 6.3 Click on OK button
		Thread.sleep(5000);
		boolean found   = false;

		for (int scroll = 0; scroll < 40; scroll++)  // up to 40 scroll attempts
		{
			List<WebElement> GPPAccountList = driver.findElements( LoginPageObj.Ele_ErrorMessage("h4 ellipsis cell"));
			for (int i = 0; i < GPPAccountList.size(); i++)
			{
				WebElement GPPSelected = GPPAccountList.get(i);
				String text            = GPPSelected.getText().trim();
				if (text.equalsIgnoreCase(SchemeId))
				{
					// Scroll to the target element  
					base.ScrollToWebElements(GPPSelected);
					base.ClickCondition( NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", SchemeId) );
					System.out.println("FOUND → Clicked on PDC Reference: " + SchemeId);
					found = true;
					break;
				}
			}
			if (found) break;
			// Scroll down for next batch
			base.ScrollToWebElements(GPPAccountList.get(GPPAccountList.size() - 1));
			Thread.sleep(1000);
		}

		if (!found) { System.out.println("TARGET NOT FOUND → " + SchemeId);}
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));

		//		//Step 6.3 Click on OK button
		//		Thread.sleep(1000);
		//		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));

		//Step 7.Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Step 8.Enter the following
		//Step 8.1 Issue bank
		//Step 8.2 Branch name
		//Step 8.3 MICR number
		//Step 8.4 Cheque number
		//Step 8.5 Cheque date
		//Step 8.6 Cheque amount
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC241_IssueBank);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC241_SpecialCharacters);
		String ActualSpecialCharacters = base.GetValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"));
		asrt.assertEquals(ActualSpecialCharacters, PdcEntryExecutionTestData.TC241_SpecialCharacters,"Branch name field do not accepts special characters");

		base.clearData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"));
		String MaxLimitCharater = base.CreateRandomWithoutNumbers(100);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),MaxLimitCharater);
		String ActualMaxLimit = base.GetValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"));
		asrt.assertEquals(ActualMaxLimit, MaxLimitCharater,"Branch name field do not accepts max limit 100 characters");

		String MicrNumber=base.CreateRandomNumber(9); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);
		String ChequeNumber=base.CreateRandomNumber(6); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNumber);

		//Step 9.Click on add cheque and then click commit PDC
		base.ClickCondition(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000); 
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK")))
		{
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));				
		}
		base.ClickCondition(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));

		//ERP
		//Step 10.Go to PDC entry
		//Step 11.Select customer and click on confirm
		//Expected: verify already created PDC entry listed.
		//Step 12.Go to PDC deposit
		//Step 13.Click new --> Select warehouse and click on fetch button
		//Step 14.In lines, choose the deposit bank and cheque presented date against the required customer
		//Step 15.Click on confirm button
		//Expected: After confirmation the PDC Deposit code the Confirm field should be shown Tick icon(✓) .
		//Step 16.Go to PDC execution
		//Step 17.Click new --> Select warehouse and click on fetch button
		//Step 18.In lines, choose honoured or dishonoured.
		//Step 19.Choose the honoured date
		//Step 20.Click on confirm
		//Expected: After confirmation the PDC execution code the Confirm field should be shown Tick icon(✓) .
		erpUtils.PDCEntryDepositAndExecution(PdcId,PdcEntryExecutionTestData.TC241_CustomerNo );
	}


	//<summary>
	// Test Case Title : Attempt to repeat pdc entry with same combinations-cheque,micr,branch,gpp
	// Automation ID : TC246
	// Author : Vishnu Manoj K 
	// </summary>
	public void TC246_PdcEntryExecution() throws Exception{

		//Step 1 : Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre-requsite Step 1 :  Customer must have a normal advance PDC entry
		//Pre-requsite Step 2 :  Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Pre-requsite Step 3 : Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Pre-requsite Step 4 : Click on PDC entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Pre-requsite Step 5 : Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC246_CustomerNo,UtilityTestData.MenuBarOptions[7]);     
		Thread.sleep(2000);
		String PdcId= base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("PDCENTRYCODE"));

		//Pre-requsite Step 6 : Choose PDC type as normal advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC246_PdcType);

		//Pre-requsite Step 7 : Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Pre-requsite Step 8 : Enter the following
		//Pre-requsite Step 8.1 : Issue bank 
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC246_IssueBank);

		//Pre-requsite Step 8.2 : Branch name
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC246_Branch);

		//Pre-requsite Step 8.3 : MICR number
		String MicrNumber=base.CreateRandomNumber(9); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);

		//Pre-requsite Step 8.4 : Cheque number
		//Pre-requsite Step 8.5 : Cheque date
		String ChequeNo=base.CreateRandomNumber(6); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNo);

		//Pre-requsite Step 8.6 : Cheque amount
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUEAMOUNT"),PdcEntryExecutionTestData.TC246_ChequeAmount);

		//Pre-requsite Step 9 : Click on add cheque and then click commit PDC
		base.buttonClick(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000);

		//Nominee details popup 
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), PdcEntryExecutionTestData.TC246_NomineeDobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));		
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_PDCEntryView_commitBarCommandCommand"));

		//Step 2 :  Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3 : Click on gpp
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","GPP"));

		//Step 4 : Click on PDC entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("PDC Entry"));

		//Step 5 : Search customer and click on add to PDC entry
		appUtils.SearchByCustomerID(PdcEntryExecutionTestData.TC246_CustomerNo,UtilityTestData.MenuBarOptions[7]);     
		Thread.sleep(2000);

		//Step 6 : Choose PDC type as normal advance
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrPDCTYPE"),PdcEntryExecutionTestData.TC246_PdcType);

		//Step 7 : Choose the sales person
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrSALESMAN"), 1);

		//Step 8 : Enter the following(already existing)
		//Step 8.1 : Issue bank 
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("ArrISSUEBANK"),PdcEntryExecutionTestData.TC246_IssueBank);

		//Step 8.2 : Branch name
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("BRANCHCODE"),PdcEntryExecutionTestData.TC246_Branch);

		//Step 8.3 : MICR number
		//String MicrNumber=base.CreateRandomNumber(9); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("MICRCODE"),MicrNumber);

		//Step 8.4 : Cheque number
		//Step 8.5 : Cheque date
		//String ChequeNo=base.CreateRandomNumber(6); 
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUENO"),ChequeNo);

		//Step 8.6 : Cheque amount
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("CHEQUEAMOUNT"),PdcEntryExecutionTestData.TC246_ChequeAmount);

		//Step 9 : Click on add cheque and then click commit PDC
		//Expected :  Validation must be displayed saying "This cheque number is already exists in database ! "
		base.buttonClick(LoginPageObj.Btn_SingnIn("AddEditHandlerButton"));
		Thread.sleep(4000);

		asrt.assertTrue(base.isPresent(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("This cheque number is already exists in database")), "Cheque number duplicate validation message ' This cheque number is already exists in database' is not displayed");
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();	
	}
}
