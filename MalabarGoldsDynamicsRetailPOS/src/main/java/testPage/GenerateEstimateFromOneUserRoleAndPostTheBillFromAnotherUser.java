package testPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import testData.CommonData;
import testData.GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

public class GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	MathUtilities mathUtils;
	PdfUtilities  pdfUtils;

	LoginPage_Obj LoginPageObj = new LoginPage_Obj();
	GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_TestData GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserTestData = new GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_TestData();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSaleGoldandSilver_Obj  NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj = new GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj  = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();


	Login login = new Login(driver);
	public GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser(WebDriver driver) {

		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base);
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);

	}

	// <summary>
	// Test Case Title : Generate estimate from one user role and Post the bill from another user
	// Automation ID : TC293
	// Author: Bharath
	// </summary>
	public String TC293_GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser() throws Exception {

		//Step 1: Login to POS
		//Step 2: Click on Transaction button
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		//Step 3: Select customer
		//Step 4: Click on add to estmate button
		appUtils.SearchByCustomerID(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserTestData.TC293_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan SKU and click on Add to cart button
		//Step 6: Select any one of the sales agent
		//Expected: Check calculation
		//*No of Product lines, *Displayed Item Name,*Displayed Quantity,*Displayed TOTAL (without Tax),Displayed Subtotal,*TAX,*Total Amount
		//Displayed Subtotal,*TAX,*Total Amount
		List<String> SkuList = appUtils.SelectMultipleSKUs(
				GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserTestData.TC293_SkuGoldCount,
				GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserTestData.TC293_TransferType,
				GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserTestData.TC293_FromCounter,
				GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserTestData.TC293_MetalType);

		List<String> AllSkuList = new ArrayList<String>();
		AllSkuList.addAll(SkuList);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		int SkuCounter =1;
		appUtils.SKUIngridentTableValues(SkuList.get(0), SkuCounter, ScannedSkuDataMap);
		Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
		mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
		ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
		ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal         = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String Discount         = TransactionDataLine.get("Discount");
		String TotalGrossWeight = TransactionDataLine.get("GrossWeight");
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String Tax               = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount      = TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String NetTotal         = TransactionDataLine.get("NetTotal").replaceAll("[^\\d.]", "").trim();
		String LinesCount       = TransactionDataLine.get("LinesCount");	
		mathUtils.ValidateTransactionLineCalculation(TransactionDataLine, ScannedSkuDataMap);

		//Step 7: Click on Save to estimation button
		//Step 8:  Save esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		//Logging in a new terminal as a new user
		driver.switchTo().newWindow(WindowType.TAB);
		List<String> WindowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(WindowHandles.getLast());
		driver.navigate().to(UtilityTestData.Secondary_Terminal);

		//Reset a Terminal
		Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Btn_Estimation("primaryButton")));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("primaryButton"));
		base.ClickCondition(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("headerSplitViewToggleButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
		Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Btn_Transaction("h5 textLeft centerY", "Settings")));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h5 textLeft centerY", "Settings"));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("resetApplicationButton"));
		Wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Btn_SingnIn("yesButtonClick")));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("yesButtonClick"));
		login.loginToApplication(CommonData.UserName2, CommonData.PassWord2);
		Thread.sleep(2000);
		//Selecting a new Shift
		//if(base.isExists(NormalSaleGoldAndSilverObj.Ele_OGProduct("Resume an existing shift")))
		if(base.WaitforElementToBeInteractable(NormalSaleGoldAndSilverObj.Ele_OGProduct("Resume an existing shift"), 60))
		{
			System.out.println("Found the element:Resume an existing shift");
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_OGProduct("Resume an existing shift"));
			Thread.sleep(2000);
			base.ClickCondition(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Ele_Shifts("OPERATOR ID","gridcell"));
			Wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Btn_SingnIn("okAppBar")));
			base.ClickCondition(LoginPageObj.Btn_SingnIn("okAppBar"));
		}

		//Step 9: Recall estimate from cashier screen
		appUtils.HamBurgerButtonClick("iconShop");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		Wait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_AlertText("Recall Estimate")));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ExpectedEstmnNumber);
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		String ActualEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));

		asrt.assertEquals(ActualEstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+ActualEstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+"in recall estimation page"); 
		List<String> ProductGrossWt = new ArrayList<>();
		ProductGrossWt.add(TotalGrossWeight);

		//Step 10: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserTestData.TC293_TransationType);

		//Step 11: Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Note: Note: Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		//Step 12: Choose a sales representative
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 13: Click on the Amount 
		//Step 14: Select a Payment method(Cash or Card )
		String PaymentAmountHDFC = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));

		// Step 15:  Post the Invoice
		// Expected Result: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC292_TaxInvoice");	
		String TaxInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";	
		String GrossAmount    = SubTotal;
		String TaxableValue   = SubTotal;
		String TotalQtyPcs    = LinesCount;
		String TotalDiscount  = Discount;
		String NetValue       = PaymentAmountHDFC;
		String PdfInvoiceNo   = pdfUtils.SaleInvoicePdfValidation(
				TaxInvoice,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				PaymentAmountHDFC,
				AllSkuList,
				ScannedSkuDataMap
				);			

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		System.out.println(PdfInvoiceNo);
		return PdfInvoiceNo;

	}
}