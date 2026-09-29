
package utilPack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj;
import objectRepository.GppOpeningAndCollectionMultiplePlans_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleDiamondandPlatinum_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import objectRepository.StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj;
import testData.CommonData;
import testData.Utility_TestData;
import testPage.Login;
import objectRepository.CustomerCreation_Obj;



public class AppUtilities  {

	//	protected WebDriver driver;
	//	Assert asrt;
	//	JavascriptExecutor js;
	protected WebDriver driver;
	protected BasePge base;
	protected PdfUtilities PdfUtilities;
	protected Login login;
	Assert asrt;
	JavascriptExecutor js;

	LoginPage_Obj LoginPageObj= new LoginPage_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj=new NormalSalesReturnGoldSilverReturnSaleCounter_Obj(); 
	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSaleDiamondandPlatinum_Obj NormalSaleDiamondandPlatinumObj =new NormalSaleDiamondandPlatinum_Obj();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj = new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	GppOpeningAndCollectionMultiplePlans_Obj GppOpeningAndCollectionMultiplePlansObj = new GppOpeningAndCollectionMultiplePlans_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj  AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj=new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj ();
	CustomerCreation_Obj CustomerCreationObj=new CustomerCreation_Obj();
	StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj = new StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj();
	GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj = new GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj();

	public AppUtilities(BasePge base) {
		this.base   = base;
		this.driver = base.driver;
		this.login  = new Login(this.driver);
		this.PdfUtilities = new PdfUtilities(this.base);
	}


	/// <summary>
	/// To Get Multiple random SKU Numbers and Excluding the Piece Skus
	/// Author : Neethu & Vishnu
	/// </summary>
	public List<String> SelectMultipleSKUs(
			int skuCount,
			String transferType,
			String fromCounter,
			String metalType) throws Exception {

		List<String> selectedSkus = new ArrayList<>();
		Set<String> seenSkus = new HashSet<>();

		base.setZoom(driver, 60);

		this.ClickWithRetry(
				LoginPageObj.Edt_AlertText("Purchase"),
				LoginPageObj.Edt_AlertText("Counter Transfer"),
				5, 2
				);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Counter Transfer"));

		base.selectorByVisibleText(
				NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),
				transferType);

		base.selectorByVisibleText(
				NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"),
				fromCounter);

		base.selectorByVisibleText(
				NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"),
				metalType);

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation(
				"win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction(
				"h4 padLeft12", "Product search"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Excluded SKUs
		Set<String> excludedSkus = new HashSet<>();
		excludedSkus.addAll(Utility_TestData.GoldPieceRateSkus);
		excludedSkus.addAll(Utility_TestData.DiamondPieceRateSkus);
		excludedSkus.addAll(Utility_TestData.SilverPieceRateSkus);
		excludedSkus.addAll(Utility_TestData.RestrictedSkus);
		excludedSkus.addAll(Utility_TestData.UncutVirazSkus);
		excludedSkus.addAll(Utility_TestData.KeralSkus);
		excludedSkus.addAll(Utility_TestData.GoldCoinSkus);

		int triesWithoutNew = 0;
		int maxTriesWithoutNew = 10;

		while (selectedSkus.size() < skuCount && triesWithoutNew < maxTriesWithoutNew) {

			List<WebElement> rows = driver.findElements(By.cssSelector(".win-container"));
			int newFound = 0;

			for (WebElement row : rows) {

				String sku;
				try {
					sku = row.findElement(By.cssSelector(".column1 .cell")).getText().trim();
				} catch (Exception e) {
					continue;
				}

				if (sku.isEmpty() || seenSkus.contains(sku))
					continue;

				seenSkus.add(sku);

				// ✅ NUMERIC SKU ONLY
				if (sku.matches("\\d+") && !excludedSkus.contains(sku)) {
					selectedSkus.add(sku);
					System.out.println("✔ Selected numeric SKU : " + sku);
					newFound++;
				} else {
					System.out.println("✘ Skipped SKU : " + sku);
				}

				if (selectedSkus.size() == skuCount)
					break;
			}

			if (newFound == 0) {
				triesWithoutNew++;
			} else {
				triesWithoutNew = 0;
			}

			// 🔽 REQUIRED WinJS scroll
			js.executeScript(
					"let v = document.querySelector('.win-viewport');" +
							"let i = document.querySelector('.win-itemscontainer');" +
							"if(v && i) v.scrollTop = i.scrollHeight;"
					);

			Thread.sleep(600);
		}

		if (selectedSkus.size() < skuCount) {
			throw new Exception(
					"Only " + selectedSkus.size() +
					" numeric SKUs found. Required: " + skuCount);
		}

		System.out.println("FINAL SELECTED NUMERIC SKUs: " + selectedSkus);
		return selectedSkus;
	}

	/// <summary>
	/// Method to search by customer ID and selecting option from menu bar
	///Author-Christy & Gokul
	/// </summary>	
	//	public void SearchByCustomerID(String CustomerID,String Option) throws InterruptedException 
	//	{
	//		// Wait for loader or overlay to go away
	//		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//		Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
	//
	//		// Wait for field to be clickable, then click via JS (if necessary)
	//		By SearchBox = NormalSaleGoldAndSilverObj.Edt_Name("Search");
	//
	//		Wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
	//		base.ClickCondition(SearchBox);
	//		Thread.sleep(4000);
	//		// Wait again for it to be interactable
	//		WebElement element = Wait.until(ExpectedConditions.presenceOfElementLocated(SearchBox));
	//		Thread.sleep(2000);
	//		element.clear();  
	//		element.sendKeys(CustomerID);
	//
	//		Thread.sleep(3000);
	//
	//		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	//
	//		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_SearchList("CusHamBurgerButtonClicktomers"));
	//		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Customers"));
	//		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SignInButton("Customers")),"The user is not able to navigate to the customer page");
	//
	//		//base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
	//		//			for (String id : Utility_TestData.MenuBarOptions) 
	//		//			{
	//		//				asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions(id)), "The "+id+"  option is not exist in the menu bar");
	//		//			}
	//
	//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions(Option));
	//
	//		//asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_OGProduct("Main content")), "The corresponding "+Option+" is not displayed to the user on clicking options from the menubar");
	//	}
	public void SearchByCustomerID(String CustomerID,String Option) throws InterruptedException 
	{

		this.ClickWithRetry(NormalSaleGoldAndSilverObj.Edt_Name("Search"), NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("splitViewSearchPane active"),5,2);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), CustomerID);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Customers"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions(Option));
	}
	/// <summary>
	/// Method to validate the selected payment after recalling an estimate.Method includes validations for approval code, card number, and the 'Change Due' subpage.
	/////Author-Vishnu RR & Benson
	/// </summary>
	public String PaymentAfterRecallEstimateVoucher(String PaymentMethod) throws InterruptedException {

		for(int i=0;i<5;i++) {
			base.actionClick(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
			Thread.sleep(5000);
			if(base.isPresent(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PaymentMethod))) {
				break;
			}
		}
		Thread.sleep(5000);
		for(int i=0;i<5;i++) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PaymentMethod));
			Thread.sleep(5000);
			if(base.isPresent(CustomerCreationObj.Ele_ERPCustid("Payment amount"))) {
				break;
			}
		}
		Thread.sleep(3000);

		String PaymentAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
		Thread.sleep(3000);

		if (PaymentMethod.equals("HDFC (Credit Card)") || PaymentMethod.equals("ICICI")||PaymentMethod.equals("HDFC")) {
			// ApprCode-validation
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

			// CardNumber-validation
			Thread.sleep(5000);		
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			ChangeDueSubpageValidation();
		} else if (PaymentMethod.equals("Cash")) {
			ChangeDueSubpageValidation();
		} else {
			asrt.fail("User is unable to select the payment method after recalling an estimate.");
		}

		return PaymentAmount;
	}
	/// <summary>
	/// Method to validate change due subpage after succesfull Approval code and Card Number validation in recall estimation
	/// Author-Christy Reji
	/// </summary>
	//	public void ChangeDueSubpageValidation()throws InterruptedException
	//	{	
	//		Thread.sleep(2000);
	//		//		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due")),"'Change due' subpage is not displayed to user after enter card number details");
	//		//		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_ChangeDueAmount("0.00")),"'Change due' amount is not changed to zero even after appr, card number validations");
	//		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
	//		//	asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Receipts")),"'Receipt' subpage is not displayed to user after clicking close button from 'Change due' subpage");
	//	}
	public void ChangeDueSubpageValidation()throws InterruptedException
	{	
		Thread.sleep(5000);
		By okLocator = LoginPageObj.Btn_SignInButton("OK");
		if (!driver.findElements(okLocator).isEmpty()) {
			driver.findElement(okLocator).click();
			driver.findElement( NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).click();
		} else {
			driver.findElement(LoginPageObj.Btn_SignInButton("Close")).click();
		}
	}

	/// <summary>
	/// Method to select the Payment Mode(Sales, Sales Return, Exchange, Purchase) based on the AmountDue.
	/// This Method include sub scenario:
	/// 1. If AmountDue is greater than Zero(like Sales transaction) User can select payment method and can create Receipt.
	/// 2. If AmountDue is less than Zero(like Sales Return transaction) User can select payment method such as Convert to Advance and can create Receipt.
	/// Author : Neethu & Nivya
	/// </summary>
	public void PaymentModeForDiffTransactions(String PaymentMethod,String ApprCode,String Cardnumber, String SalesPersonNo,String DueDate,String DOBYear, String NomineeName, String NomineeRelation) throws Exception
	{	
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		String AmountDue = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		if (AmountDue != null && !AmountDue.trim().isEmpty()) {
			AmountDue = AmountDue.replaceAll("[^0-9.-]", "").trim();
			double numericAmount = Double.parseDouble(AmountDue);
			System.out.println("The Amount: "+numericAmount);

			if (numericAmount > 0.00) {

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

				asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("HDFC (Credit Card)")),"The user is not able to view the payment methods");

				base.buttonClick(LoginPageObj.Edt_AlertText(PaymentMethod));
				Thread.sleep(2000);
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_CartMoney("flexGrow50 flexRow row-enter","h1 marginBottom0 iconReturnKey button-content-return"));
				Thread.sleep(2000);
				base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),ApprCode);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
				base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),Cardnumber);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

				asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Receipts")),"'Receipt' subpage is not displayed to user after clicking close button from 'Change due' subpage");
			}

			else if (numericAmount < 0.00){

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
				Thread.sleep(2000);

				asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Convert to Advance")),"The user is not able to view the payment methods");

				Thread.sleep(2000);
				base.buttonClick(LoginPageObj.Edt_AlertText(PaymentMethod));	
				Thread.sleep(4000);
				base.buttonClick(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"),2);
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), DueDate);
				base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Remarks("6"));
				base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"), UtilityTestData.Remarks);
				base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
				Thread.sleep(1000);				
				base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"),NomineeName);
				base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"),1);
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), DOBYear);
				//base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee address"),NomineeAddress);
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));}
			Thread.sleep(3000);
			base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
			//wait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")));
			if(base.WaitforElementToBePresentWithTimeout(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"), 30)) {
				Thread.sleep(2000);	
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));	
			}
			else {
				Thread.sleep(2000);	
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Payement("Change due"));
			}
			Thread.sleep(3000);	

			//asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Change due")),"'Change due' subpage is not displayed to user after enter card number details");

		}
		else {
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
			Thread.sleep(3000);
			//change due close button after clicking the amount
			if (base.isElementPresent(driver, By.cssSelector("button[data-ax-bubble='messageDialog_closeButtonClick'][class='cancelButton primaryButton']"))) {
				try {
					base.excuteJsClick(By.cssSelector("button[data-ax-bubble='messageDialog_closeButtonClick'][class='cancelButton primaryButton']"));
					System.out.println("Close button clicked successfully.");
				} catch (Exception e) {
					System.out.println("Failed to click the Close button: " + e.getMessage());
				}
			} else {
				System.out.println("Close button not found, skipping the action.");
			}

			//close button(from Reciept) after the click close button on change due
			if (base.isElementPresent(driver, By.cssSelector("button[data-ax-bubble='messageDialog_ExtensionTemplatedDialogButton1'][class='primaryButton']"))) {
				try {
					base.excuteJsClick(By.cssSelector("button[data-ax-bubble='messageDialog_ExtensionTemplatedDialogButton1'][class='primaryButton']"));
					System.out.println("Close button clicked successfully in reciept");
				} catch (Exception e) {
					System.out.println("Failed to click the Close button: " + e.getMessage());
				}
			} else {
				System.out.println("Close button not found, skipping the action.");
			}
		}
	}	

	/// <summary>
	/// Method to Void transaction from Cash
	/// </summary>
	///@Author:Gokul.P
	public void VoidTransaction() throws InterruptedException
	{
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_VoidMethod("ButtonGrid2","Void"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_VoidMethod("ButtonGrid2","Void transaction"));
		String ValidationMessage=base.GetText(LoginPageObj.Ele_ErrorMessage("messageText h5"));
		asrt.assertEquals(base.GetText(LoginPageObj.Ele_ErrorMessage("messageText h5")),ValidationMessage,"The warning message "+ValidationMessage+" is not displayed to the user");
		base.buttonClick(LoginPageObj.Btn_SignInButton("Yes"));
		Thread.sleep(1000);
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("addCustomerButton")),"The customer is not removed from the transaction page");
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("addProductButton")),"The product listed in the cart in the transaction page is not removed");
	}	

	/// <summary>
	/// Method to Void Product in the Cart
	/// </summary>
	///@Author:Gokul.P
	public void VoidProduct(List Option) throws InterruptedException
	{
		int count=0;
		List<WebElement> products= driver.findElements(NormalSaleGoldAndSilverObj.Txt_ProductValidation());	 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_VoidMethod("ButtonGrid2","Void"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		WebElement hiddenElement=driver.findElement(NormalSaleGoldAndSilverObj.Txt_Quantity("1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String getSKU = (String) js.executeScript("return arguments[0].textContent;", hiddenElement);      
		for(int j=0;j<Option.size();j++)
		{			
			if(getSKU.equals(Option.get(j)))
			{
				String quantity=products.get(j).getText();
				base.Hover(LoginPageObj.Edt_AlertText(quantity));
				base.buttonClick(NormalSaleGoldAndSilverObj.Fld_Product(quantity));
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_VoidMethod("ButtonGrid2","Void product"));			    	
				break;
			}
		}
		Thread.sleep(2000);
		for(int i=0;i<products.size();i++)
		{
			if(products.get(i).isDisplayed())
			{
				count++;					
			}	
		}				
		String totalProduct= base.GetText(NormalSaleGoldAndSilverObj.Txt_TotalProduct());
		asrt.assertEquals(Integer.parseInt(totalProduct), count,"The total number of products displayed in the cart is not equal to the lines"); 		
	}	

	/// <summary>
	/// Extracts structured data (rows and columns) from a dynamic HTML table on the SKU Ingredient page
	/// </summary>	
	public List<Map<String, String>> ExtractTableData(WebDriver driver){
		// Step 1: Create a list to hold each row's data (each row is a Map of columnName -> cellValue)
		List<Map<String, String>> TableData = new ArrayList<>();
		// Step 2: Find all column header elements (assumed to be inside divs with class containing 'column')
		List<WebElement> Headers = driver.findElements(By.cssSelector("div.dataListSubheader > div[class*='column']"));
		// Step 3: Extract and clean the text of each header
		List<String> HeaderNames = Headers.stream()
				.map(h -> h.findElement(By.cssSelector("div.h6.ellipsis")).getText().trim())  // Get trimmed text from nested div
				.collect(Collectors.toList());  // Collect header names into a list
		// Step 4: Find all table row elements (each with class 'dataListLine')
		List<WebElement> Rows = driver.findElements(By.cssSelector("div.dataListLine"));
		// Step 5: Loop through each row element to extract cell values
		for (WebElement Row : Rows) {
			// Step 5a: Get all cell elements inside the current row
			List<WebElement> Cells = Row.findElements(By.cssSelector("div[class*='column'] > div.cell"));
			// Step 5b: Create a map to store columnName -> cellValue for this row
			Map<String, String> RowData = new LinkedHashMap<>();
			// Step 5c: For each cell, map it to its corresponding header (up to the size of the smallest list)
			for (int i = 0; i < Math.min(HeaderNames.size(), Cells.size()); i++) {
				RowData.put(HeaderNames.get(i), Cells.get(i).getText().trim());  // Add entry: HeaderName -> CellText
			}
			// Step 5d: Add this row's data to the main table data list
			TableData.add(RowData);
		}

		// Step 6: Return the fully parsed table data
		return TableData;
	}

	/// <summary>
	/// Fetch and store all values in SKU Ingredient Page (Optimized version with reduced wait time)
	/// </summary>
	public void SKUIngridentTableValues(String Sku, int SkuNumber, Map<String, String> DataMap) throws Exception {

		// Step 1: Click "Add to Cart" to activate the SKU input field
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Thread.sleep(3000);
		// Step 2: Wait and enter the SKU string into the input field (do not clear existing data)
		//wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity")));
		base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), Sku);

		// Step 3: Press return key button to trigger SKU search
		wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"))).click();

		// Step 4: Adjust zoom level to 40%
		base.setZoom(driver, 40);
		Thread.sleep(10000);
		// Step 5: Wait until the data table with SKU details appears on the screen
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.dataListLine")));

		// Step 6: Fetch and store item-level details in DataMap
		DataMap.put("SKU_" + SkuNumber + "_GrossWeight", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_Purity", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("Purity"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_TotalCValue", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_MakingRate", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_MakingValue", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_MakingType", base.getTheSelectedTextInDropDown(NormalSaleGoldAndSilverObj.Sel_DepositType("makingTypeOptions")));
		DataMap.put("SKU_" + SkuNumber + "_WastageQty", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Wastage Qty"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_WastageAmount", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Wastage amount"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_ItemName", base.GetText(By.xpath("//h3[@data-bind='text: SectionHeader']")));
		Thread.sleep(5000);
		DataMap.put("SKU_" + SkuNumber + "_Total", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_HeaderName", base.GetText(By.xpath("//h3[@data-bind='text: SectionHeader']")));
		DataMap.put("SKU_" + SkuNumber + "_CvalueTextField", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_RateTextField", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"), "value"));

		// Step 7: Adjust zoom level to 30% for better visibility
		base.setZoom(driver, 30);

		// Step 8: Extract and flatten table data into DataMap
		List<Map<String, String>> SkuData = ExtractTableData(driver);
		for (int ItemIndex = 0; ItemIndex < SkuData.size(); ItemIndex++) {
			Map<String, String> Row = SkuData.get(ItemIndex);
			for (Map.Entry<String, String> Entry : Row.entrySet()) {
				String Key = "SKU" + SkuNumber + "_ITEM" + (ItemIndex + 1) + "_" + Entry.getKey().toUpperCase().replaceAll("\\s+", "");
				DataMap.put(Key, Entry.getValue());
			}
		}

		Thread.sleep(7000);
		// Step 9: Click the "Estimation" button to add the SKU estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		// Step 10: Calculate and store weights (StoneWeight, NetWeight, RGWeight)
		double StoneWeight = 0.0, NetWeight = 0.0, TotalRGWeight = 0.0;
		String Rate_RP = "";
		String Rate_RG = "";
		String Cvalue_RG = "";
		double Cvalue_DIAMOND = 0.0 ;

		double Cvalue_STONES = 0.0;

		for (int ItemIndex = 0; ItemIndex < SkuData.size(); ItemIndex++) {
			String prefix = "SKU" + SkuNumber + "_ITEM" + (ItemIndex + 1);
			String ItemId = DataMap.getOrDefault(prefix + "_ITEMID", "").trim().toUpperCase();
			String QtyStr = DataMap.getOrDefault(prefix + "_QTY", "0").replaceAll("[^\\d.]", "");
			String Unit = DataMap.getOrDefault(prefix + "_UNIT", "").trim().toLowerCase();
			String Rate = DataMap.getOrDefault(prefix + "_RATE", "0").replaceAll("[^\\d.]", "");
			String Cvalue = DataMap.getOrDefault(prefix + "_CVALUE", "0").replaceAll("[^\\d.]", "");

			if (!Rate.isEmpty()) {			
				if (ItemId.equals("RP")) {
					Rate_RP = Rate;
				}
				if (ItemId.equals("RG")) {
					Rate_RG = Rate;
					Cvalue_RG = Cvalue;
				}
				if (ItemId.equalsIgnoreCase("DIAMOND")) { 
					Cvalue_DIAMOND = Cvalue_DIAMOND + Double.parseDouble(Cvalue);
				}


				// Add Cvalue to others if not RG or DIAMOND
				if (!ItemId.equals("RG") && !ItemId.equalsIgnoreCase("DIAMOND")) {
					Cvalue_STONES = Cvalue_STONES+Double.parseDouble(Cvalue);
				}

			}
			if (!QtyStr.isEmpty()) {
				double Qty = Math.round(Double.parseDouble(QtyStr) * 1000.0) / 1000.0;

				if (ItemId.equals("RG") || ItemId.equals("RP") || ItemId.equals("RS")) {
					NetWeight += Qty;
					if (ItemId.equals("RG")) {
						double QtyInGrams = Unit.contains("ct") ? Qty * 0.2 : Qty;
						TotalRGWeight += QtyInGrams;
					}
				} else {
					StoneWeight += Unit.contains("ct") ? Qty * 0.2 : Qty;
				}
			}
		}

		// Step 11: Round and store the final computed weight values
		DataMap.put("SKU_" + SkuNumber + "_StoneWeight", String.format("%.3f", StoneWeight));
		DataMap.put("SKU_" + SkuNumber + "_NetWeight", String.format("%.3f", NetWeight));
		DataMap.put("SKU_" + SkuNumber + "_TotalRGWeight", String.format("%.3f", TotalRGWeight));

		DataMap.put("SKU_" + SkuNumber + "_RateRP", Rate_RP);
		DataMap.put("SKU_" + SkuNumber + "_RateRG", Rate_RG);
		DataMap.put("SKU_" + SkuNumber + "_CvalueRG", Cvalue_RG);
		DataMap.put("SKU_" + SkuNumber + "_CvalueDIAMOND", Double.toString(Cvalue_DIAMOND));

		DataMap.put("SKU_" + SkuNumber + "_CvalueSTONES", Double.toString(Cvalue_STONES));


		// Step 12: Store the SKU header name
		DataMap.put("SKU_" + SkuNumber + "_SKUName", base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader")));

		// Step 13: Click the "Estimation" button again (final confirmation)
		Thread.sleep(2000);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"))).click();
		}catch (Exception e) {
			System.out.println("Step 13:(final confirmation) not required, continuing execution");
		}
		// Step 14: If first SKU, reset zoom and focus on first SKU box
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.setZoom(driver, 40);
			wait.until(ExpectedConditions.elementToBeClickable(
					NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220","1"))).click();
		} else {
			System.out.println("SaleRep not required; continuing execution");
		}
		// Extra: Handle "Specify price" modal if it appears
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//		Thread.sleep(1000);
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(500); // Short delay for modal to close
			}
		} catch (Exception e) {
		}

	} 
	/// <summary>
	/// Fetch and store all values in Transaction Lines Page
	/// </summary>	
	public Map<String, String> TransactionSKUsLinesVerify(List<String> SkuList, Map<String, String> ScannedSKUDataMap) throws InterruptedException {
		Map<String, String> Results = new HashMap<>();

		// 1. Get Lines Count
		Thread.sleep(5000);
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		String LinesCount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		//System.out.println("Total LinesCountQty: " + LinesCount);
		Results.put("LinesCount", LinesCount);

		// 2. Gross Weight (Sum of all visible weights)
		List<WebElement> AllSKUElements = driver.findElements(
				NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")  );

		int SkuCount = AllSKUElements.size();
		double TotalGrossWeight = 0.0;

		for (int i = 1; i <= SkuCount; i++) {
			By SkuLocator = NormalSaleGoldAndSilverObj.Ele_SKUNumber("tillLayout-QuantityField textRight", "h5", String.valueOf(i));
			String ValueStr = base.GetText(SkuLocator);
			ValueStr = ValueStr.replaceAll("[^\\d.-]", "");
			double Grosswt = Double.parseDouble(ValueStr);
			Results.put("GrossWeight" + i, String.format("%.3f", Grosswt));
			if (ValueStr != null && !ValueStr.isEmpty()) {

				try {

					TotalGrossWeight += Double.parseDouble(ValueStr);

				} catch (NumberFormatException e) {
					System.out.println("Invalid number at index " + i + ": " + ValueStr);
				}
			}
		}

		//System.out.println("Total Gross Weight: " + TotalGrossWeight);
		Results.put("GrossWeight", String.format("%.3f", TotalGrossWeight));

		// 3. Net Weight (Only for RG/RP items)
		double TotalNetWeight = 0.0;
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			int ItemNumber = 1;
			while (true) {
				String BaseKey = "SKU" + SkuIndex + "_ITEM" + ItemNumber;
				String ItemId = ScannedSKUDataMap.get(BaseKey + "_ITEMID");

				if (ItemId == null) break;

				if (ItemId.equals("RG") || ItemId.equals("RP") || ItemId.equals("RS")) {
					String QtyStr = ScannedSKUDataMap.getOrDefault(BaseKey + "_QTY", "0");
					QtyStr = QtyStr.replaceAll("[^\\d.]", "");

					try {
						TotalNetWeight += Double.parseDouble(QtyStr);
					} catch (NumberFormatException e) {
						//System.out.println("Invalid QTY at " + BaseKey + ": " + QtyStr);
					}
				}
				ItemNumber++;
			}
		}

		//System.out.println("Total Net Weight (N.Wt. for RG/RP): " + String.format("%.3f", TotalNetWeight));
		Results.put("NetWeight", String.format("%.3f", TotalNetWeight));

		// 4. Subtotal
		String Subtotal = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4"));
		//System.out.println("Total Subtotal: " + Subtotal);
		Results.put("Subtotal", Subtotal);

		// 5. Tax
		String Tax = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4"));
		//System.out.println("Total Tax: " + Tax);
		Results.put("Tax", Tax);

		// 6. TotalAmount and NetTotal
		String TotalAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		//System.out.println("Total TotalAmount: " + TotalAmount);
		Results.put("TotalAmount", TotalAmount);

		// 7. Net Total = Total Amount= AmountDue
		String AmountDue = base.GetText(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Results.put("NetTotal", AmountDue);

		//8. Payment
		String Payments = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("PaymentsField", "h4"));
		Results.put("Payments", Payments);

		// 9. Per-item details (Name, Quantity, TotalWithoutTax, SalesRep â€¦)
		// Collect each column separately
		List<WebElement> AllNameElements = base.GetElement(NormalSaleGoldAndSilverObj.Sel_TheListOfName("tillLayout-ProductNameField","h5"));
		List<WebElement> AllQuantityElements = base.GetElement(NormalSaleGoldAndSilverObj.Sel_TheListOfName("tillLayout-QuantityField","h5"));
		List<WebElement> AllTotalWithoutTaxElements = base.GetElement(NormalSaleGoldAndSilverObj.Sel_TheListOfName("tillLayout-TotalWithoutTaxField","h5"));
		//List<WebElement> AllSalesRepElements =  base.GetElement(NormalSaleGoldAndSilverObj.Sel_TheListOfName("tillLayout-SalesRepresentativeField","h5"));

		//System.out.println("ItemContainers found = " + ItemContainers.size());
		System.out.println("AllNameElements found = " + AllNameElements.size());
		System.out.println("AllQuantityElements found = " + AllQuantityElements.size());
		System.out.println("AllTotalWithoutTaxElements found = " + AllTotalWithoutTaxElements.size());
		//System.out.println("AllSalesRepElements found = " + AllSalesRepElements.size());

		// Loop by index, zip the lists together
		for (int ItemIndex = 0; ItemIndex < AllNameElements.size(); ItemIndex++) {
			String Name = (ItemIndex < AllNameElements.size()) ? AllNameElements.get(ItemIndex).getText().trim() : "";
			String Quantity = (ItemIndex < AllQuantityElements.size()) ? AllQuantityElements.get(ItemIndex).getText().trim() : "";
			String TotalWithoutTax = (ItemIndex < AllTotalWithoutTaxElements.size()) ? AllTotalWithoutTaxElements.get(ItemIndex).getText().trim() : "";
			// String SalesRep = (ItemIndex < AllSalesRepElements.size()) ? AllSalesRepElements.get(ItemIndex).getText().trim() : "";

			Results.put("Item" + (ItemIndex+1) + "Name", Name);
			Results.put("Item" + (ItemIndex+1) + "Quantity", Quantity);
			Results.put("Item" + (ItemIndex+1) + "TotalWithoutTax", TotalWithoutTax);
			// Results.put("Item" + (ItemIndex+1) + "SalesRep", SalesRep);


			//10. Discounts
			String Discounts = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("DiscountsField", "h4"));
			Results.put("Discounts", Discounts);
		}

		return Results;
	}

	/// <summary>
	/// Method to Purchase Old Gold.
	/// This method describes the user can Purchase or Exchange the Old Gold products and can add those products to the cart.
	/// Step for Bug: 'Unexpected Error' Pop up displayed in the Old Gold Details page
	/// Author : Neethu
	/// </summary>	
	public void PurchaseOldGold(String SelectConfiguration, String PurchaseORExchange, String QCPerson, String SmithPerson, String GrossPieces, String GrossWeight) throws Exception
	{
		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Transaction("Transaction")), "The user is not able to navigate to Transaction page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"OG");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		Thread.sleep(2000);
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SignInButton("Products")),"The User is not able to view OG Product in Product Page");
		Thread.sleep(2000);
		driver.findElement(LoginPageObj.Edt_AlertText(UtilityTestData.Product)).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),SelectConfiguration);

		//Step for Bug: 'Unexpected Error' Pop up displayed in the Old Gold Details page
		WebDriverWait Alertwait = new WebDriverWait(driver, Duration.ofSeconds(7));
		try {
			//			if (base.isElementPresent(driver, By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"))) {
			if (Alertwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"))) != null) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

				base.excuteJsClick(By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"));
				System.out.println("Popup appeared and OK button was clicked.");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			}
		}catch (Exception e) {
			System.out.println("Error Popup not found, skipping the close button action.");
		}

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("SimpleProductDetailsView_addProductToSale","win-commandimage"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(1000);
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_Name(PurchaseORExchange));
		//QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		//	Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

	}


	/// <summary>
	/// Creates a GPP account and extracts the Application ID from the generated PDF.
	/// Confirms successful account creation and retrieves the ID for further use.
	/// Author : Nivya
	/// </summary>
	public void GppAccountOpening(String CustomerPhNo, String SchemaCode, String BankProofType, String NomineeName, String NomineeRelation) throws Exception
	{
		base.buttonClick(LoginPageObj.Edt_AlertText("GPP"));
		base.buttonClick(LoginPageObj.Edt_AlertText("New GPP Account"));

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("GPP account opening")),"The user is not able to navigate the GPP account opening page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),CustomerPhNo);
		Thread.sleep(2000);

		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Customers"));

		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SignInButton("Customers")),"Customers list is not visible to the user");

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconBuy"));

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("GPP account opening")), "The user is not able to navigate the GPP account opening page");

		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("SchmTypeOptions"), SchemaCode);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Bank proof type"), BankProofType);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"),NomineeName);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"), NomineeRelation);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("IsSameAsAbove"));	

		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("SearchView_AppBarView_appBarCommandCommand")),"The User is not able to view Ok button to add Gpp Account for customer");

		base.buttonClick(LoginPageObj.Btn_SingnIn("SearchView_AppBarView_appBarCommandCommand"));
		PdfUtilities.GPPpdfValidation();
	}

	/// <summary>
	/// Method to return single or multiple product with details
	/// Author : Anagha
	/// </summary>
	public ReturnDetails ReturnMultipleProductsWithDetails(String ReceiptNo, List<String> RequiredReturnProductList) throws InterruptedException {
		List<String> returnedProductNames = new ArrayList<>();
		List<String> returnedProductQty = new ArrayList<>();
		List<String> returnedProductPrice = new ArrayList<>();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		for (String ItemCategory : RequiredReturnProductList) {
			if (ItemCategory.equalsIgnoreCase("Gold") ||
					ItemCategory.equalsIgnoreCase("Silver") ||
					ItemCategory.equalsIgnoreCase("Diamond") ||
					ItemCategory.equalsIgnoreCase("Uncut") ||
					ItemCategory.equalsIgnoreCase("Platinum") ||
					ItemCategory.equalsIgnoreCase("Precia")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Return transaction"))).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Btn_abc("numpad-control alphanumeric-mode flexCol", "Enable text editing"))).click();
				base.setData(NormalSaleGoldAndSilverObj.Edt_TextField("numpad-control alphanumeric-mode flexCol", "text"), ReceiptNo);
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("numpad-control-buttons flexGrow100 flexRow", "content-return"));

				if (base.isElementPresent(driver, NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames"))) {
					try {
						WebElement storeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames")));
						storeDropdown.click();
						base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames"), 1);

						WebElement regDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionRegisterNumbers")));
						regDropdown.click();
						base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionRegisterNumbers"), 1);

						wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Ele_ErrorMessage("buttonContainer col no-shrink halfWidth leftmostDialogButton marginTop20"))).click();
					} catch (Exception e) {
						// Log or ignore
					}
				}

				// popup handling
				Thread.sleep(5000);
				if (base.isExists(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames"))) {
					Thread.sleep(5000);
					base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames"),  UtilityTestData.WarehouseId );
					Thread.sleep(5000);
					base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionRegisterNumbers"),  UtilityTestData.Terminal );
					base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
				} else {
					System.out.println("Popup not displayed, continuing transaction");
				}

				List<WebElement> ReturnableProductList = base.GetElement(NormalSaleGoldAndSilverObj.Ele_Lines("salesOrderLinesView", "enhancedGridDynamicRowTemplate ratio5"));
				List<WebElement> ReturnableProductQty = base.GetElement(NormalSaleGoldAndSilverObj.Ele_Lines("salesOrderLinesView", "enhancedGridDynamicRowTemplate ratio1 textRight"));
				List<WebElement> ReturnableProductPrice = base.GetElement(NormalSaleGoldAndSilverObj.Ele_Lines("salesOrderLinesView", "enhancedGridDynamicRowTemplate ratio2 textRight"));

				for (int i = 0; i < ReturnableProductList.size(); i++) {
					String ProdName = ReturnableProductList.get(i).getText();
					String ProQty = ReturnableProductQty.get(i).getText();
					String ProdPrice = ReturnableProductPrice.get(i).getText();

					if ((ItemCategory.equalsIgnoreCase("Gold") && ProdName.startsWith("G")) ||
							(ItemCategory.equalsIgnoreCase("Silver") && ProdName.startsWith("S")) ||
							(ItemCategory.equalsIgnoreCase("Diamond") && ProdName.startsWith("D")) ||
							(ItemCategory.equalsIgnoreCase("Uncut") && ProdName.startsWith("U")) ||
							(ItemCategory.equalsIgnoreCase("Platinum") && (ProdName.startsWith("PL") || ProdName.startsWith("Pl") || ProdName.startsWith("pl") || ProdName.startsWith("PG"))) ||
							(ItemCategory.equalsIgnoreCase("Precia") && ProdName.toLowerCase().startsWith("pr") || ProdName.startsWith("PR") || ProdName.startsWith("Pr"))) {

						ReturnableProductList.get(i).click();
						base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Return"));
						base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
						returnedProductNames.add(ProdName);
						returnedProductQty.add(ProQty);
						returnedProductPrice.add(ProdPrice);
						break;

					}
				}
			}
		}

		return new ReturnDetails(returnedProductNames, returnedProductQty, returnedProductPrice);
	}
	public class ReturnDetails {
		public List<String> productNames;
		public List<String> productQty;
		public List<String> productPrice;

		public ReturnDetails(List<String> productNames, List<String> productQty, List<String> productPrice) {
			this.productNames = productNames;
			this.productQty = productQty;
			this.productPrice = productPrice;
		}

		public ReturnDetails(String returnedProduct, String returnedQty, String returnedPrice) {
			// TODO Auto-generated constructor stub
		}
	}

	///<summary>
	/// Method to get Making Rate and Piece rate items from product search page
	///Author : Anagha, Chandana Babu
	/// </summary>	
	public SKUTypeResult GetMakingAndPcsRateItems(String TransferType, String FromCounter, String MetalType, String Item, String Product) throws InterruptedException 
	{
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("SKU counter stock transfer")),
				"The user is not able to navigate to the SKU Counter stock Transfer page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Product search")),
				"The user is not able to navigate to the Product Search page");


		List<String> SeenSKUs = new  ArrayList<>();
		List<String> AllProductNames =new ArrayList<>();
		List<String> Quantities =new ArrayList<>();

		List<WebElement> AllSKUs = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int SameCount = 0;
		int ScrollPauseTime = 400;

		int PreviousTotalCount = 0;

		while (true) {
			List<WebElement> CurrentRows = driver.findElements(By.xpath("//div[@class='win-container']"));
			int NewItems = 0;

			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;

				String[] Parts = RowText.split("\\n");
				if (Parts.length < 6) continue;

				String SKU = Parts[0].trim();
				String ProductName = Parts[4].trim();
				String Quantity = Parts[6].trim();

				if (!SeenSKUs.contains(SKU)) {
					SeenSKUs.add(SKU);
					AllProductNames.add(ProductName);
					Quantities.add(Quantity);
					AllSKUs.add(Row);
					NewItems++;
				}
			}

			if (NewItems == 0) {
				SameCount++;
			} else {
				SameCount = 0;
			}

			if (SameCount >= 5) {
				break;
			}

			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(ScrollPauseTime);
		}
		List<String> PcsRateSKUs = new ArrayList<>();
		List<String> MakingRateSKUs = new ArrayList<>();
		for (int i = 0; i < SeenSKUs.size(); i++) {
			if (Item.equals("Gold")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				String ProductName = AllProductNames.get(i);
				if (QtyNum <= 0.5 && ProductName.equals(Product)) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > 0.5 && ProductName.equals(Product)) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
			if (Item.equals("Silver")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				String ProductName = AllProductNames.get(i);
				if (QtyNum <= 0.5 && ProductName.equals(Product)) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > 0.5 && ProductName.equals(Product)) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
			if (Item.equals("Diamond")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				String ProductName = AllProductNames.get(i);
				if (QtyNum <= 0.5 && ProductName.equals(Product)) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > 0.5 && ProductName.equals(Product)) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
			if (Item.equals("Platinum")) {
				String Qty = Quantities.get(i);
				double QtyNum = Double.parseDouble(Qty);
				if (QtyNum <= 1.5 && Product.equals("Null")) {
					PcsRateSKUs.add(SeenSKUs.get(i));
				}
				if (QtyNum > 1.5 && Product.equals("Null")) {
					MakingRateSKUs.add(SeenSKUs.get(i));
				}
			}
		}
		return new SKUTypeResult(PcsRateSKUs, MakingRateSKUs);
	}

	public class SKUTypeResult 
	{
		public List<String> PcsRateSKU;
		public List<String> MakingRateSKU;

		public SKUTypeResult(List<String> pcsRateSKUs, List<String> makingRateSKUs) 
		{
			this.PcsRateSKU = pcsRateSKUs;
			this.MakingRateSKU = makingRateSKUs;
		}
	}

	///<summary>
	/// Method to get required pcs rate items from list
	///Author : Chandana Babu
	/// </summary>
	public List<String> GetRequiredPcsRateItems(int count,List<String> SKUs ) throws InterruptedException
	{
		List<String> RequiredPcsRateSKUs = new ArrayList<>();
		if (SKUs.isEmpty()) {
			System.out.println("SKU List is empty - exiting test early");
		} else {

			for (int i = 0; i < SKUs.size(); i++) {

				this.HamBurgerButtonClick("iconShop");

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
				Thread.sleep(3000);
				base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SKUs.get(i));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
				String TextMakingRate = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"));
				double MakingRate = Double.parseDouble(TextMakingRate);
				String TextMakingValue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"));
				double MakingValue = Double.parseDouble(TextMakingValue);
				String TextMakingType = base.getTheSelectedTextInDropDown(NormalSaleGoldAndSilverObj.Sel_DepositType("makingTypeOptions"));
				if (TextMakingType.equals("Total")||TextMakingType.equals("Pcs") && MakingRate==MakingValue) {
					RequiredPcsRateSKUs.add(SKUs.get(i));
				}
				if (RequiredPcsRateSKUs.size()== count) {
					this.HamBurgerButtonClick("iconShop");
					break;
				}
			}
		}
		return RequiredPcsRateSKUs;
	}

	///<summary>
	/// Method to get required Making Rate Items from list
	///Author : Anagha
	/// </summary>
	public List<String> GetRequiredMakingRateItems(int count,List<String> SKUs ) throws InterruptedException
	{
		List<String> RequiredMakingRateSKUs = new ArrayList<>();
		if (SKUs.isEmpty()) {
			System.out.println("SKU List is empty - exiting test early");
		} else {

			for (int i = 0; i < SKUs.size(); i++) {

				this.HamBurgerButtonClick("iconShop");

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
				Thread.sleep(3000);
				base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SKUs.get(i));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
				String TextMakingType = base.getTheSelectedTextInDropDown(NormalSaleGoldAndSilverObj.Sel_DepositType("makingTypeOptions"));
				if (TextMakingType.equals("Total")||TextMakingType.equals("Net")){
					RequiredMakingRateSKUs.add(SKUs.get(i));
				}
				if (RequiredMakingRateSKUs.size()== count) {
					this.HamBurgerButtonClick("iconShop");
					break;
				}
			}
		}
		return RequiredMakingRateSKUs;
	}	

	/// <summary>
	/// Filters out only the entries belonging to a specific SKU (skuNumber) from the big Data map.
	/// Author: VISHNU MANOJ K
	/// <summary>
	public Map<String, String> ExtractDataForSku(Map<String, String> FullMap, int SkuNumber) {
		Map<String, String> SingleSkuMap = new LinkedHashMap<>();
		String SkuPrefix = "SKU_" + SkuNumber + "_";
		String ItemPattern = "^SKU" + SkuNumber + "_ITEM\\d+_.*";

		for (Map.Entry<String, String> Entry : FullMap.entrySet()) {
			String Key = Entry.getKey();
			if (Key.startsWith(SkuPrefix) || Key.matches(ItemPattern)) {
				SingleSkuMap.put(Key, Entry.getValue());
			}
		}
		return SingleSkuMap;
	}

	///<summary>
	/// Method to get invoice no after normal sale
	///Author : Chandana
	/// </summary>
	public String GetInvoiceNoAfterNormalSale(String CustomerNo, List<String> CounterTransfer) throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Navigate to the Transaction page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		Thread.sleep(9000);
		this.HamBurgerButtonClick("iconShop");

		// Step 3 : Select customer and click in add to sale button
		this.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);

		// Step 4 : Scan Multiple SKU's, click on Add to cart button and Select any one of the sales agent
		List<String> AllSkuList = new ArrayList<>();
		for(int j = 0; j + 2 < CounterTransfer.size(); j += 3) {
			List<String> RequiredSkuList = this.SelectMultipleSKUs(Integer.parseInt(CounterTransfer.get(j)),"Inter",CounterTransfer.get(j+1),CounterTransfer.get(j+2));
			AllSkuList.addAll(RequiredSkuList);
			this.HamBurgerButtonClick("iconShop");
		}

		Map<String, String> ScannedSKUDataMap = new LinkedHashMap<>();
		for (int i = 0; i < AllSkuList.size(); i++)
		{    		
			this.SKUIngridentTableValues(AllSkuList.get(i), i + 1, ScannedSKUDataMap);
		}	

		//Map<String, String> TransactionFieldValues = this.TransactionSKUsLinesVerify(AllSkuList, ScannedSKUDataMap);		

		//Step 5: Click on the Amount and Select a Payment method(Cash or Card)
		String PaymentAmount = this.PaymentAfterRecallEstimate("HDFC");		

		//Step 6 : Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(6000);
		String TaxInvoiceName = PdfUtilities.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		String TaxInvoice =System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String InvoiceNo = PdfUtilities.ExtractReceiptNoFrmSaleInvoice(TaxInvoice);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		return InvoiceNo;
		//How to call in your class
		//  String InvoiceNo = appUtils.GetInvoiceNoAfterNormalSale(NormalSalesReturnGoldSilverReturnSaleCounterTestData.TC17_CustomerID, NormalSalesReturnGoldSilverReturnSaleCounterTestData.SkuValue);
	}

	///<summary>
	/// Method for Sales Return flow
	///Author : Gokul,Christy,Nivya
	/// </summary>
	public void  SalesReturnFlow(String CustomerID,String InvoiceNo,List<String>Product) throws Exception
	{
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		SearchByCustomerID(CustomerID,UtilityTestData.MenuBarOptions[0]);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Click on customer adjustment,return transaction,Receipt Number,Return Product,Return Button
		ReturnDetails Details = ReturnMultipleProductsWithDetails(InvoiceNo, Product); //Product taken from Your own testdata

		//Click on the Amount,Payment method,transaction type,due date, Nominee details,Deposit button	
		PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
				UtilityTestData.SalePersonNumber,
				UtilityTestData.DueYear,
				UtilityTestData.DobYear,
				UtilityTestData.NomineeName,
				UtilityTestData.NomineeRelation);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		Thread.sleep(1000);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));
		Thread.sleep(10000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Cart has changed"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		for (int i =0; i<2;i++) {
			//			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Error"))) {
			//				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			//			}
			if(base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Payement("Error"),30)) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}

		}
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		Thread.sleep(10000);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));
		Thread.sleep(10000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(5000);	
		//How to call in your class
		//	appUtils.SalesReturnFlow(NormalSalesReturnGoldSilverReturnSaleCounterTestData.TC17_CustomerID, InvoiceNo, NormalSalesReturnGoldSilverReturnSaleCounterTestData.ReturnProducts);
	}


	//	  ///<summary>
	//
	//	/// Method for Counter In Flow
	//	///Author :Chandana,Christy,Nivya,Gokul
	//	/// </summary>
	public void CounterFlow(String ToCounter, String MetalType,List<String> SKUs, String Terminal) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Error"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		Thread.sleep(3000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));
		for(int SkuCount =0; SkuCount < SKUs.size();SkuCount++)
		{
			//Click in Transfer Type,FromCounter,ToCounter,MetalType,Ship,InterReceiptSearch,Receive
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), "Inter");// Hard Code
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
			Thread.sleep(4000);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), "Sales Return");// Hard Code
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("ToCounter"));
			Thread.sleep(3000);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("ToCounter"), ToCounter);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
			Thread.sleep(3000);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);
			Thread.sleep(3000);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("Product"), SKUs.get(SkuCount));
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("Product"), "ENTER");
			Thread.sleep(2000);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
			base.ClickCondition(LoginPageObj.Edt_Alert("Ship"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			Thread.sleep(2000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), "Inter");
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
			Thread.sleep(2000);
			base.ClickCondition(LoginPageObj.Edt_AlertText("Inter receipt search"));

			// Fetch and Store the Recepit list based on the users terminal
			//			List<WebElement> AllElements = new ArrayList<>();			
			//			AllElements.addAll(base.GetElement(LoginPageObj.Ele_ErrorMessage("win-container win-container-even")));
			//
			//			List<WebElement> oddElements = base.GetElement(LoginPageObj.Ele_ErrorMessage("win-container win-container-odd"));
			//			if (oddElements != null) {
			//				AllElements.addAll(oddElements);
			//			}
			Thread.sleep(6000);
			List<WebElement> AllElements = base.GetElement(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_RecipetNumber());
			// Get the SKU number that the user is using.
			//for (WebElement Key : AllElements)
			for(int i =AllElements.size()-1; i>=0;i--)
			{

				base.ScrollToWebElement(AllElements.get(i));
				String ReceiptNumber = AllElements.get(i).getText();
				if (ReceiptNumber.contains(Terminal)) {
					Thread.sleep(10000);
					AllElements.get(i).click();
					Thread.sleep(10000);
					String SkuText=base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("wrapTextWithoutHyphen"));
					if(SkuText.contains(SKUs.get(SkuCount)))
					{
						base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
						break;
					}
					else{base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_ReciptClose());}
				}
			}

			//Receiving the SKU
			base.buttonClick(LoginPageObj.Btn_SingnIn("Button1"));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Receive("Receive"));
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			Thread.sleep(2000);
			//How to call in your class
			//appUtils.CounterFlow("Gold","Gold",NormalSalesReturnGoldSilverReturnSaleCounterTestData.SkuValue , "5011T02");
		}
	}


	///<summary>
	/// Method to fetch the the current rate of 22k gold
	///Author : Hasna EK
	/// </summary>
	public String Fetch22kCurrentRate(String SkuTestData)throws Exception {
		//Step 1: Click "Add to Cart" to activate the SKU input field
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));

		// Step 2: Set up WebDriverWait for dynamic waits (e.g., table loading)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Step 3: Enter the SKU string into the input field (do not clear existing data)
		Thread.sleep(3000);
		base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SkuTestData);

		// Step 4: Press return key button to trigger SKU search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));

		base.setZoom(driver, 40);
		Thread.sleep(5000);
		// Step 5: Wait until the data table with SKU details appears on the screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.dataListLine")));

		// Step 6: Adjust zoom for better visibility if needed
		base.setZoom(driver, 30);
		List<Map<String, String>> SkuData = this.ExtractTableData(driver);
		//
		//		 	String Rate = null;
		//		 	if (!skuData.isEmpty()) {
		//		 	    Map<String, String> firstRow = skuData.get(0); // Only the first row
		//		 	    Rate = firstRow.get("Rate"); // Column header must match exactly

		Map<String, String> Row = SkuData.get(0);
		System.out.println("Extracted Table Row: " + Row);
		System.out.println("Available Keys: " + Row.keySet());
		String RateValue = Row.get("RATE"); 

		System.out.println(" 22k Purity Rate: " + RateValue);
		return RateValue;


	}
	///<summary>
	/// Method to validate the data between Billing screen and Recall Estimate screen
	///Author : Hasna EK
	/// </summary>
	public void ValidateBillingAndRecallScreenData(String EstimationNo, String LinesCount,List<String> ItemNamesBillngScrn,List<String>QtyBillingScrn,String SubTotal,String Tax,String NetTotal)throws Exception {

		System.out.println("====== Validation between Billing screen and Recall estimate screen has started ======  ");
		//To validate Estimation Number
		String EstmnNumberRecallPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(EstimationNo,EstmnNumberRecallPge,"Mismatch in Estimation number, expected: "+EstimationNo+" but got "+EstmnNumberRecallPge+" in Recall Estimation page ");
		//To validate lines count
		int LinesCountInBilling = Integer.parseInt(LinesCount);
		List<String> ItemNamesFromRecall = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		int LinesCountInRecall = ItemNamesFromRecall.size();
		asrt.assertEquals(LinesCountInBilling, LinesCountInRecall,"Mismatch in Lines count, expected: "+LinesCountInBilling+" but got "+LinesCountInRecall+" in Recall Estimation page");

		//		//To validate Item name
		//		for (int i =0; i<ItemNamesFromRecall.size();i++) 
		//		{			
		//			String ItemNameRecallPge = ItemNamesFromRecall.get(i).trim();
		//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//			if(ItemNamesBillngScrn.get(i).contains("-")) 
		//			{
		//				String[] ItemName = ItemNamesBillngScrn.get(i).split("-");
		//				String ItemNameFromBillingPge= ItemName[1].trim();
		//				asrt.assertEquals(ItemNameRecallPge,ItemNameFromBillingPge,"Mismatch in Item name, expected: "+ItemNameFromBillingPge+" but got "+ItemNameRecallPge+" in Recall estimation page");
		//			}else {
		//				String ItemNameFromBillingPge = ItemNamesBillngScrn.get(i);
		//				asrt.assertEquals(ItemNameRecallPge,ItemNameFromBillingPge,"Mismatch in Item name, expected: "+ItemNameFromBillingPge+" but got "+ItemNameRecallPge+" in Recall estimation page");}
		//		}
		for (int i =0; i<ItemNamesFromRecall.size();i++)
		{String ItemNameRecallPge = ItemNamesFromRecall.get(i).trim();
		String ItemNameFromBillingPge = ItemNamesBillngScrn.get(i).trim();
		asrt.assertTrue( ItemNameFromBillingPge.contains(ItemNameRecallPge),"Item name mismatch: Expected '" + ItemNameRecallPge + "' to contain actual '" + ItemNameFromBillingPge + "'");
		}

		List<String> QuantityFromRecall = base.GetElementTexts(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "QUANTITY"));
		for (int i = 0; i < QuantityFromRecall.size(); i++)
		{
			String DisplayedRecallQty = QuantityFromRecall.get(i).trim().replaceAll("[^\\d.]", "");
			double QtyFromRecallPge = Double.parseDouble(DisplayedRecallQty);
			String DisplayedQtyBilling = QtyBillingScrn.get(i).trim().replaceAll("[^\\d.]", "");			
			double QtyFromBillingPge = Double.parseDouble(DisplayedQtyBilling);

			asrt.assertEquals(QtyFromRecallPge,QtyFromBillingPge,"Mismatch in Quantity Value, expected " + QtyFromBillingPge + " but got " + QtyFromRecallPge+ " in Recall Estimation page");
		}

		//To validate Total without tax
		double SubTotalFromBillingPge = Double.parseDouble(SubTotal.trim().replaceAll("[^\\d.]", ""));
		double TotalWithoutTaxRecallPge = Math.abs(Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalWithoutTax"))));
		asrt.assertEquals(SubTotalFromBillingPge,TotalWithoutTaxRecallPge,"Mismatch in Total Amount value, expected: "+SubTotalFromBillingPge+" but got "+TotalWithoutTaxRecallPge+" in Recall estimation page");

		//To validate tax
		double TaxFromBillingPge = Double.parseDouble(Tax.trim().replaceAll("[^\\d.]", ""));
		double TaxFromRecallPge = Math.abs(Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalTax"))));
		asrt.assertEquals(TaxFromBillingPge,TaxFromRecallPge,"Mismatch in Tax amount, expected " + TaxFromBillingPge+" but got "+TaxFromRecallPge+" in Recall Estimation page");

		//To validate sub total
		double TotalAmntBillingPge = Double.parseDouble(NetTotal.trim().replaceAll("[^\\d.]", ""));
		double SubTotalFromRecallPge = Math.abs(Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.SubTotal"))));
		asrt.assertEquals(TotalAmntBillingPge,SubTotalFromRecallPge,"Mismatch in Subtotal,expected " +TotalAmntBillingPge+" but got "+SubTotalFromRecallPge+" in Recall estimation page");

		System.out.println("====== Validation between the Billing screen and the Recall Estimate screen is completed ======  ");

	}

	///<summary>
	/// Method to Fetch the Uncut Viraz Sku
	/// Author: Hasna EK
	///</summary>
	public List<String> GetVirazSkus(List<String> productList, int count) throws InterruptedException {

		List<String> RequiredPiece = new ArrayList<>();
		List<String> AvailableProducts = new ArrayList<>(productList);

		while (RequiredPiece.size() < count && !AvailableProducts.isEmpty()) {
			List<String> Piece = getRandomProducts(AvailableProducts, 1);

			String sku = Piece.get(0);

			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			Thread.sleep(3000);
			base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), sku);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));

			base.setZoom(driver, 60);
			Thread.sleep(3000);

			if (base.isExists(LoginPageObj.Edt_AlertText("Item details"))) {
				if (!RequiredPiece.contains(sku)) {	
					Thread.sleep(3000);
					//To handle item-specific 'warning' messages displayed on the Item Details page when the "Add to Cart" button is clicked.
					base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
					if (base.isExists( NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("Sale below cost price is not valid"))) {
						Thread.sleep(3000);
						base.buttonClick(LoginPageObj.Btn_SingnIn("DefaultMessageDialogButton"));
						AvailableProducts.remove(sku);
					}
					else if(base.isElementPresent(driver,NormalSaleGoldAndSilverObj.Ele_SKUSalesRep("margin0","1"))){
						base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Close"));
						base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));
						RequiredPiece.add(sku);
						AvailableProducts.remove(sku);
					}
					this.HamBurgerButtonClick("iconShop");
				}
			} else {
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
				AvailableProducts.remove(sku);
			}
		}
		System.out.println("RequiredPiece "+RequiredPiece);
		return RequiredPiece;
	}

	///<summary>
	/// Method to Fetch Random Item from list
	///Author : Chandana,Nivya
	/// </summary>
	public  List<String> getRandomProducts(List<String> productList, int count) {
		if (count > productList.size()) {
			throw new IllegalArgumentException("Requested more items than available in the list.");
		}

		Random random = new Random();
		Set<Integer> pickedIndices = new HashSet<>();

		while (pickedIndices.size() < count) {
			int randomIndex = random.nextInt(productList.size());
			pickedIndices.add(randomIndex);
		}

		List<String> result = new ArrayList<>();
		for (int index : pickedIndices) {
			result.add(productList.get(index));
		}

		return result;
	}
	//<summary>
	/// Method to Fetch the Piece Rate Item
	/// Author: Chandana, Nivya
	///</summary>
	public List<String> FetchPieceRateItem(List<String> productList, int count) throws InterruptedException {

		List<String> RequiredPiece = new ArrayList<>();
		List<String> AvailableProducts = new ArrayList<>(productList);

		while (RequiredPiece.size() < count && !AvailableProducts.isEmpty()) {
			List<String> Piece = getRandomProducts(AvailableProducts, 1);

			String sku = Piece.get(0);

			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			Thread.sleep(3000);
			base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), sku);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));

			base.setZoom(driver, 40);
			Thread.sleep(3000);

			if (base.isExists(LoginPageObj.Edt_AlertText("Item details"))) {
				if (!RequiredPiece.contains(sku)) {
					RequiredPiece.add(sku);
					AvailableProducts.remove(sku);

					this.HamBurgerButtonClick("iconShop");
				}
			} else {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				AvailableProducts.remove(sku);
			}
		}
		if(RequiredPiece.size()==0) {
			System.out.println("Valid Sku Items not available in Utility List");
			asrt.fail("Valid Sku Items not available in Utility List");
		}
		return RequiredPiece;
	}

	/// <summary>
	/// This method can be used to fetch skus which have gross weight greater than weight of OG during exchange
	/// Author : Sangeetha
	/// </summary>

	public SKUResult GetOGSaleSku(String TransferType, String FromCounter, String MetalType, double QtySale, int skuCount) throws InterruptedException {		
		// Navigate to SKU counter transfer page
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);

		// Select From Counter
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);

		// Select Metal Type
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		// Navigate to Product Search
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Combine all SKUs to exclude
		Set<String> ExcludedSkus = new HashSet<>();		
		ExcludedSkus.addAll(Utility_TestData.GoldPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.DiamondPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.SilverPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.RestrictedSkus);
		ExcludedSkus.addAll(Utility_TestData.UncutVirazSkus);
		ExcludedSkus.addAll(Utility_TestData.KeralSkus);
		ExcludedSkus.addAll(Utility_TestData.GoldCoinSkus);

		// Init data collections
		List<String> SeenSKUs = new ArrayList<>();
		List<String> Quantities = new ArrayList<>();
		List<WebElement> AllSKUs = new ArrayList<>();
		List<String> PcsRateSKUs = new ArrayList<>();	
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		int scrollPauseTime = 400;
		int maxTriesWithoutNew = 7;
		int triesWithoutNew = 0;

		while (PcsRateSKUs.size() < skuCount && triesWithoutNew < maxTriesWithoutNew) 
		{
			List<WebElement> CurrentRows = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-container"));
			int NewItems = 0;	
			for (WebElement Row : CurrentRows) 
			{
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;	
				String[] Parts = RowText.split("\\n");
				if (Parts.length < 7) continue;

				String SKU = Parts[0].trim();
				String Quantity = Parts[6].trim();	
				if (!SeenSKUs.contains(SKU) && !ExcludedSkus.contains(SKU))
				{
					SeenSKUs.add(SKU);
					Quantities.add(Quantity);
					AllSKUs.add(Row);	
					double QtyNum = 0;
					try 
					{
						QtyNum = Double.parseDouble(Quantity);
					}
					catch (NumberFormatException e)
					{
						continue;
					}
					if (QtyNum >= QtySale) 
					{
						PcsRateSKUs.add(SKU);
						if (PcsRateSKUs.size() >= skuCount) break;
					}	
					NewItems++;
				}
			}
			if (NewItems == 0) triesWithoutNew++;
			else triesWithoutNew = 0;
			if (PcsRateSKUs.size() >= skuCount) break;	
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(scrollPauseTime);
		}	
		return new SKUResult(PcsRateSKUs);
	}

	public static class SKUResult 
	{
		public List<String> PcsRateSKU;	
		public SKUResult(List<String> pcsRateSKUs) 
		{
			this.PcsRateSKU = pcsRateSKUs;
		}
	}
	///<summary>
	/// Method to Fetch Sku and Invoice Number from Normal Sale
	/// Author: Nivya
	///</summary>
	public List<String> GetInvoiceNoAfterNormalSaleFetch(String CustomerNo, List<String> CounterTransfer) throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 80);

		// Step 2: Navigate to the Transaction page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		this.HamBurgerButtonClick("iconShop");

		// Step 3 : Select customer
		this.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);

		// Step 4 : Scan Multiple SKUs
		List<String> AllSkuList = new ArrayList<>();
		for (int j = 0; j + 2 < CounterTransfer.size(); j += 3) {
			List<String> RequiredSkuList = this.SelectMultipleSKUs(
					Integer.parseInt(CounterTransfer.get(j)),
					"Inter",
					CounterTransfer.get(j + 1),
					CounterTransfer.get(j + 2)
					);
			AllSkuList.addAll(RequiredSkuList);
			this.HamBurgerButtonClick("iconShop");
		}
		Map<String, String> ItemData  = new HashMap<>();
		for (int i = 0; i < AllSkuList.size(); i++)
		{   Thread.sleep(2000);
		this.SKUIngridentTableValues(AllSkuList.get(i), i + 1,ItemData);

		}

		// Step 5: Payment
		String PaymentAmount = this.PaymentAfterRecallEstimate("HDFC");

		// Step 6 : Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		String TaxInvoiceName = PdfUtilities.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		String TaxInvoice = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName;
		String InvoiceNo = PdfUtilities.ExtractReceiptNoFrmSaleInvoice(TaxInvoice);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Combine invoice number and SKU list to return
		List<String> result = new ArrayList<>();
		result.add(InvoiceNo);
		result.addAll(AllSkuList);

		return result;
	}

	/// <summary>
	/// This method can be used to fetch Platinum skus which have Item Ids as specified in the code
	/// Author : Hasna EK
	/// </summary>
	public  List<String> ScanMultiplePlatinumSkus(String TransferType, String FromCounter, String MetalType) throws InterruptedException {
		// Navigate to SKU counter transfer page
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase")); 
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TransferType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);

		// Select From Counter 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='FromCounter']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='MetalType']")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search")); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Init data collections 
		List<String> SeenSkus = new ArrayList<>(); 
		List<String> MatchingSkus = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;    
		int scrollPauseTime = 400;
		int maxTriesWithoutNew = 3;
		int triesWithoutNew = 0;

		while (triesWithoutNew < maxTriesWithoutNew) {
			List<WebElement> CurrentRows = driver.findElements(By.xpath("//div[@class='win-container']"));
			int NewItems = 0;    
			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;    
				String[] Parts = RowText.split("\\n"); 
				if (Parts.length < 7) continue;

				String SKU = Parts[0].trim(); 
				String ItemName = Parts[2].trim(); 

				if (!SeenSkus.contains(SKU)) { 
					SeenSkus.add(SKU);
					String cleanedItemName = ItemName.trim();
					System.out.println(ItemName);
					MatchingSkus.add(SKU);
					NewItems++;
				} 
			}

			if (NewItems == 0) triesWithoutNew++;
			else triesWithoutNew = 0;
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);"); 
			Thread.sleep(scrollPauseTime);
		}
		return MatchingSkus;
	}
	/// <summary>
	/// This method can be used to fetch Platinum skus based on ingredients(Normal Platinum, Dual Tone Platinum and Platinum with diamond)
	/// Author : Hasna EK
	/// </summary>
	public Map<String, List<String>> FetchPlatinumSkuWithDiffIngredients(List<String> MatchingSkus, int skuCountPerCategory) throws InterruptedException {

		List<String> AvailableProducts = new ArrayList<>(MatchingSkus);
		List<String> DiamondWithPlatinumSku = new ArrayList<>();
		List<String> DualTonePlatinumSku = new ArrayList<>();
		List<String> NormalPlatinumSku = new ArrayList<>();

		while (!AvailableProducts.isEmpty()) {
			List<String> Piece = getRandomProducts(AvailableProducts, 1);
			String Sku = Piece.get(0);
			boolean SkuCategorized = false;

			System.out.println("Selected sku " + Sku);

			this.HamBurgerButtonClick("iconShop");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			Thread.sleep(3000);
			base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), Sku);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
			base.setZoom(driver, 60);
			Thread.sleep(3000);

			if (base.isExists(LoginPageObj.Edt_AlertText("Item details"))) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.dataListLine")));
				base.setZoom(driver, 60);
				List<Map<String, String>> SkuData = this.ExtractTableData(driver);
				List<String> Ingredients = new ArrayList<>();
				for (Map<String, String> row : SkuData) {
					String ingredient = row.get("ITEM ID");

					if (ingredient == null || ingredient.trim().isEmpty()) continue;
					Ingredients.add(ingredient.trim().toUpperCase());
				}

				boolean IncludesRp = Ingredients.contains("RP");
				boolean IncludesRg = Ingredients.contains("RG");
				boolean IncludesDiamond = Ingredients.contains("DIAMOND");
				boolean IncludesOtherStones = Ingredients.contains("C-OTHERSTONES");

				if (IncludesRp && IncludesRg && IncludesDiamond && DiamondWithPlatinumSku.size() < skuCountPerCategory) {
					DiamondWithPlatinumSku.add(Sku);
					SkuCategorized = true;
				} else if (IncludesRp && IncludesRg && !IncludesDiamond && !IncludesOtherStones && DualTonePlatinumSku.size() < skuCountPerCategory) {
					DualTonePlatinumSku.add(Sku);
					SkuCategorized = true;
				} else if (IncludesRp && !IncludesRg && !IncludesDiamond && !IncludesOtherStones && NormalPlatinumSku.size() < skuCountPerCategory) {
					NormalPlatinumSku.add(Sku);
					SkuCategorized = true;
				}

			} else {
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			}

			AvailableProducts.remove(Sku);

			if (!SkuCategorized) {
				System.out.println("SKU not matched with any category or limit reached: " + Sku);
			}

			this.HamBurgerButtonClick("iconShop");

			if (DiamondWithPlatinumSku.size() >= skuCountPerCategory &&
					DualTonePlatinumSku.size() >= skuCountPerCategory &&
					NormalPlatinumSku.size() >= skuCountPerCategory) {
				System.out.println("All " + skuCountPerCategory + " SKUs per type collected.");
				break;
			}


		}
		// Final categorized map
		Map<String, List<String>> ResultMap = new LinkedHashMap<>();
		ResultMap.put("NORMAL", NormalPlatinumSku);
		ResultMap.put("DUAL", DualTonePlatinumSku);
		ResultMap.put("DIAMOND", DiamondWithPlatinumSku);

		System.out.println("ResultMap "+ResultMap);
		return ResultMap;
	}
	/// <summary>
	/// This method handles the "Specify Price" modal in Recall flow until Transaction page is reached
	/// Author : Hasna EK
	/// </summary>
	public void HandleSpecifyRateModalInRecall() throws InterruptedException {

		int maxAttempts = 10;  // Optional safety limit to avoid infinite loop
		int attempt = 0;

		do {
			try {
				attempt++;
				System.out.println("Attempt " + attempt + ": Checking if on Transaction page...");
				if (base.isExists(By.xpath("//div[@class='h5 marginLeft08 padRight28']//div[text()='Transaction']"))) {
					System.out.println("Transaction page reached.");
					break;
				}
				// Check for "Specify Price" modal
				if (base.isElementPresent(driver, NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price"))) {
					// Click "1"
					base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne(
							"numpad-control-buttons flexGrow100 flexRow", "1", "1"));
					// Click "Enter"
					base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
					Thread.sleep(500); 
				} else {
					Thread.sleep(500); // Brief wait before re-checking
				}

			} catch (Exception e) {
			}
		} while (attempt < maxAttempts);
	}


	///<summary>
	/// Method to get invoice no and Sku Purity and Product after normal sale
	///Author : Gokul.P
	/// </summary>
	public Map<String, Object> GetInvoiceNoSkuPurityAndProductAfterNormalSale(String CustomerNo, List<String> CounterTransfer) throws Exception {

		// Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2: Navigate to the Transaction page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		this.HamBurgerButtonClick("iconShop");

		// Step 3 : Select customer and click in add to sale button
		this.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);

		// Step 4 : Scan Multiple SKU's, click on Add to cart button and Select any one of the sales agent
		List<String> AllSkuList = new ArrayList<>();
		for(int j = 0; j + 2 < CounterTransfer.size(); j += 3) {
			Thread.sleep(3000);
			List<String> RequiredSkuList = this.SelectMultipleSKUs(Integer.parseInt(CounterTransfer.get(j)),"Inter",CounterTransfer.get(j+1),CounterTransfer.get(j+2));
			AllSkuList.addAll(RequiredSkuList);
			this.HamBurgerButtonClick("iconShop");
		}

		String Purity=null;
		String ProductName=null;
		String ProductCode=null;

		Map<String, String> ScannedSKUDataMap = new LinkedHashMap<>();
		for (int i = 0; i < AllSkuList.size(); i++)
		{    	Thread.sleep(3000);	
		this.SKUIngridentTableValues(AllSkuList.get(i), i + 1, ScannedSKUDataMap);
		Purity=ScannedSKUDataMap.get("SKU_" + (i+1) + "_Purity");
		ProductName=ScannedSKUDataMap.get("SKU_" +(i+1)+ "_SKUName");
		}	

		if (ProductName != null && ProductName.contains(" - ")) {
			String skuCode = ProductName.split(" - ")[0];
			ProductCode = skuCode.substring(0, 2);	     
		}  

		//Step 5: Click on the Amount and Select a Payment method(Cash or Card)
		String PaymentAmount = this.PaymentAfterRecallEstimate("HDFC");		

		//Step 6 : Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		String TaxInvoiceName = PdfUtilities.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		String TaxInvoice =System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";
		String InvoiceNo = PdfUtilities.ExtractReceiptNoFrmSaleInvoice(TaxInvoice);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Map<String,Object> InvoiceaSkuPurityAndProduct=new HashMap<>();
		InvoiceaSkuPurityAndProduct.put("SKU",AllSkuList);
		InvoiceaSkuPurityAndProduct.put("Invoice",InvoiceNo);		
		InvoiceaSkuPurityAndProduct.put("Purity",Purity);
		InvoiceaSkuPurityAndProduct.put("ProductCode",ProductCode);
		return InvoiceaSkuPurityAndProduct;
	}

	/// <summary>
	/// Fetches Dual Tone Platinum SKUs from the given list.
	/// Adds SKUs to POS, checks their ingredients, and selects those containing both RP and RG,
	/// excluding Diamond and Other Stones. Stops when the required count is reached.
	/// Author : Robin T Abraham
	/// </summary>
	public List<String> FetchDualTonePlatinumSku(List<String> MatchingSkus, int skuCountPerCategory) throws Exception {

		List<String> AvailableProducts = new ArrayList<>(MatchingSkus);
		List<String> DualTonePlatinumSku = new ArrayList<>();
		WebDriverWait HamBurgerWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		while (!AvailableProducts.isEmpty()) {
			List<String> Piece = getRandomProducts(AvailableProducts, 1);
			String Sku = Piece.get(0);
			boolean SkuCategorized = false;

			System.out.println("Selected sku " + Sku);

			this.HamBurgerButtonClick("iconShop");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			Thread.sleep(3000);
			base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), Sku);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
			base.setZoom(driver, 60);
			Thread.sleep(3000);

			if (base.isExists(LoginPageObj.Edt_AlertText("Item details"))) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.dataListLine")));
				base.setZoom(driver, 60);
				List<Map<String, String>> SkuData = this.ExtractTableData(driver);
				List<String> Ingredients = new ArrayList<>();
				for (Map<String, String> row : SkuData) {
					String ingredient = row.get("ITEM ID");

					if (ingredient == null || ingredient.trim().isEmpty()) continue;
					Ingredients.add(ingredient.trim().toUpperCase());
				}

				boolean IncludesRp          = Ingredients.contains("RP");
				boolean IncludesRg          = Ingredients.contains("RG");
				boolean IncludesDiamond     = Ingredients.contains("DIAMOND");
				boolean IncludesOtherStones = Ingredients.contains("C-OTHERSTONES");

				if (IncludesRp && IncludesRg && !IncludesDiamond /*&& !IncludesOtherStones */&& DualTonePlatinumSku.size() < skuCountPerCategory) {
					DualTonePlatinumSku.add(Sku);
					SkuCategorized = true;
				}

			} else {
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			}

			AvailableProducts.remove(Sku);

			if (!SkuCategorized) {
				System.out.println("SKU not matched with any category or limit reached: " + Sku);
			}

			if (DualTonePlatinumSku.size() >= skuCountPerCategory ) {
				System.out.println("All " + skuCountPerCategory + " SKUs per type collected.");
				break;
			}

			try {
				if (HamBurgerWait.until(ExpectedConditions.visibilityOfElementLocated(
						LoginPageObj.Ele_ErrorMessage("width48 height48 buttonIcon center iconShop"))) != null) {
					Thread.sleep(3000);
					base.excuteJsClick(LoginPageObj.Ele_ErrorMessage("width48 height48 buttonIcon center iconHome"));
				}
			} catch (Exception e) {

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation(
						"headerSplitViewToggleButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
				Thread.sleep(3000);
				this.HamBurgerButtonClick("iconShop");
			}
		}

		if (DualTonePlatinumSku == null || DualTonePlatinumSku.isEmpty()) {
			throw new Exception("SKU not found: DualTonePlatinumSku list is empty");
		}

		return DualTonePlatinumSku;
	}


	///Summary
	///Method to validate the items in Recall page having OG, OD,OGT-before performing exchange,sales
	///Author-Christy Reji
	///Summary
	public void ValidateRecallPageOGItem(List<String> ExpectedItemNames, List<String> ItemQuantity, 
			String ExpectedItemNameInOgItemDetailsPge, String ExpectedGrossWeightInItemDetailsPge,
			double ExpectedSubtotal, double ExpectedTax, double ExpectedTotal, int TotalLinesBillingPage,String OGName) {

		List<WebElement> ItemNamesFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		List<WebElement> QuantityFromRecall = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "QUANTITY"));
		int TotalLinesRecallPage = ItemNamesFromRecall.size();

		for (int k = 0; k < ItemNamesFromRecall.size(); k++) {
			String ActualItemRaw = ItemNamesFromRecall.get(k).getText().trim();

			if (ActualItemRaw.equalsIgnoreCase(ExpectedItemNameInOgItemDetailsPge)) {
				// OGT Item Validation
				asrt.assertEquals(ActualItemRaw, ExpectedItemNameInOgItemDetailsPge,"OG Item Name mismatch: Expected " + ExpectedItemNameInOgItemDetailsPge + " but got " + ActualItemRaw + " in Recall page");

				String QtyStr       = QuantityFromRecall.get(k).getText().trim().replaceAll("[^\\d.]", "");
				double ActualQty    = Double.parseDouble(QtyStr);
				double ExpectedQty  = Double.parseDouble(ExpectedGrossWeightInItemDetailsPge.trim().replaceAll("[^\\d.]", ""));
				asrt.assertEquals(ExpectedQty, ActualQty,"OG Quantity mismatch: Expected " + ExpectedQty + " but got " + ActualQty + " in Recall page");

				System.out.println("The"+OGName+" Item Validation successful from recall page");
			} else {
				String ExpectedItemRaw = ExpectedItemNames.get(k).trim();
				String ActualItemName = ActualItemRaw.contains("-")
						? ActualItemRaw.substring(ActualItemRaw.lastIndexOf("-") + 1).trim()
								: ActualItemRaw.trim();

				String ExpectedItemName = ExpectedItemRaw.contains("-")
						? ExpectedItemRaw.substring(ExpectedItemRaw.lastIndexOf("-") + 1).trim()
								: ExpectedItemRaw.trim();

				System.out.println("The names: " + ExpectedItemNames);
				System.out.println("Expected: " + ExpectedItemRaw + "\nActual: " + ActualItemRaw);

				asrt.assertEquals(ActualItemName, ExpectedItemName,
						"Item Name mismatch: Expected " + ExpectedItemName + " but got " + ActualItemName + " in Recall page");


				String QtyStr           = QuantityFromRecall.get(k).getText().trim().replaceAll("[^\\d.]", "");
				double ActualQty        = Double.parseDouble(QtyStr);
				String ExpectedQtyStr   = ItemQuantity.get(k).trim().replaceAll("[^\\d.]", "");
				double ExpectedQty      = Double.parseDouble(ExpectedQtyStr);
				asrt.assertEquals(ExpectedQty, ActualQty,"Quantity mismatch: Expected " + ExpectedQty + " but got " + ActualQty + " in Recall page");
				System.out.println("The Non"+OGName+" Item Validation successful from recall page");
			}
		}

		double ActualTotalWithoutTax = Math.abs(Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalWithoutTax"))));
		asrt.assertEquals(ExpectedSubtotal, ActualTotalWithoutTax,"Total (Without Tax) mismatch: Expected " + ExpectedSubtotal + " but got " + ActualTotalWithoutTax + " in Recall page");

		double ActualTax = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalTax")));
		asrt.assertEquals(ExpectedTax, ActualTax,"Tax Value mismatch: Expected " + ExpectedTax + " but got " + ActualTax + " in Recall page");

		double ActualSubTotal = Math.abs(Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.SubTotal"))));
		asrt.assertEquals(ExpectedTotal, ActualSubTotal,"SubTotal mismatch: Expected " + ExpectedTotal + " but got " + ActualSubTotal + " in Recall page");
		asrt.assertEquals(TotalLinesBillingPage, TotalLinesRecallPage,"Total line count mismatch: Expected " + TotalLinesBillingPage + " but got " + TotalLinesRecallPage + " in Recall page");
		System.out.println("======== Validation completed from recall page ==================");
	}


	/// <summary>
	/// Method to Purchase or Exchange Old Silver. 
	/// This method describes the user can Purchase or Exchange the Old Silver products and can add those products to the cart.
	/// Step for Bug: 'Unexpected Error' Pop up displayed in the Old Silver Details page
	/// Author : Neethu
	/// </summary>	
	public void PurchaseOldSilver(String SelectConfiguration, String PurchaseORExchange, String QCPerson, String SmithPerson, String GrossPieces, String GrossWeight) throws Exception
	{
		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_Transaction("Transaction")), "The user is not able to navigate to Transaction page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"OS");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		Thread.sleep(2000);
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SignInButton("Products")),"The User is not able to view OG Product in Product Page");
		Thread.sleep(2000);
		driver.findElement(LoginPageObj.Edt_AlertText(UtilityTestData.SilverProduct)).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),SelectConfiguration);

		//Step for Bug: 'Unexpected Error' Pop up displayed in the Old Silver Details page
		if (base.isElementPresent(driver, By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"))) {
			try {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

				base.excuteJsClick(By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"));
				System.out.println("Popup appeared and OK button was clicked.");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			} catch (Exception e) {
				System.out.println("Failed to click the OK button: " + e.getMessage());
			}
		} else {

			System.out.println("Popup not found, skipping the close button action.");
		}
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart1("SimpleProductDetailsView_addProductToSale","win-commandimage"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(1000);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name(PurchaseORExchange));

		//QC person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		//	Smith person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

	}
	/// <summary>
	/// Fetch and store all values in SKU Ingredient Page(For OD/OG/OS) (Optimized version with reduced wait time)
	/// </summary>
	public void ItemDetailsTableValues(int SkuNumber, Map<String, String> DataMap) throws Exception {
		String ItemNameForOD = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ExpectedItemNameInItemDetailsPge = ItemNameForOD.substring(ItemNameForOD.indexOf("-") + 2);
		System.out.println("ExpectedItemNameInItemDetailsPge========="+ExpectedItemNameInItemDetailsPge);
		DataMap.put("SKU_" + SkuNumber + "_SKUName", ExpectedItemNameInItemDetailsPge);
		DataMap.put("SKU_" + SkuNumber + "_GrossWeight", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), "value"));
		DataMap.put("SKU_" + SkuNumber + "_Total", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("ogptotalamt"), "value"));
		// Step 8: Extract and flatten table data into DataMap
		base.setZoom(driver, 20);
		List<Map<String, String>> SkuData = ExtractTableData(driver);
		for (int ItemIndex = 0; ItemIndex < SkuData.size(); ItemIndex++) {
			Map<String, String> Row = SkuData.get(ItemIndex);
			for (Map.Entry<String, String> Entry : Row.entrySet()) {
				String Key = "SKU" + SkuNumber + "_ITEM" + (ItemIndex + 1) + "_" + Entry.getKey().toUpperCase().replaceAll("\\s+", "");
				DataMap.put(Key, Entry.getValue());
				System.out.println(Key + " = " + Entry.getValue());
			}
		}

		// Step 10: Calculate and store weights (StoneWeight, NetWeight, RGWeight)
		double StoneWeight = 0.0, NetWeight = 0.0, TotalRGWeight = 0.0;
		for (int ItemIndex = 0; ItemIndex < SkuData.size(); ItemIndex++) {
			String prefix = "SKU" + SkuNumber + "_ITEM" + (ItemIndex + 1);
			String ItemId = DataMap.getOrDefault(prefix + "_ITEMID", "").trim().toUpperCase();
			String QtyStr = DataMap.getOrDefault(prefix + "_QTY", "0").replaceAll("[^\\d.]", "");
			String Unit = DataMap.getOrDefault(prefix + "_UNIT", "").trim().toLowerCase();

			if (!QtyStr.isEmpty()) {
				double Qty = Math.round(Double.parseDouble(QtyStr) * 1000.0) / 1000.0;

				if (ItemId.equals("RG") || ItemId.equals("RP") || ItemId.equals("RS")) {
					NetWeight += Qty;
					if (ItemId.equals("RG")) {
						double QtyInGrams = Unit.contains("ct") ? Qty * 0.2 : Qty;
						TotalRGWeight += QtyInGrams;
					}
				} else {
					StoneWeight += Unit.contains("ct") ? Qty * 0.2 : Qty;
				}
			}
		}

		// Step 11: Round and store the final computed weight values
		DataMap.put("SKU_" + SkuNumber + "_StoneWeight", String.format("%.3f", StoneWeight));
		DataMap.put("SKU_" + SkuNumber + "_NetWeight", String.format("%.3f", NetWeight));
		DataMap.put("SKU_" + SkuNumber + "_TotalRGWeight", String.format("%.3f", TotalRGWeight));
	}

	///<summary>
	/// Method to execute the Recall Estimate and process estimation steps
	///Author : Hasna EK
	/// </summary>
	public void RecallEstimateProcess(String EstimationNo, String TransactionType) throws Exception {

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstimationNo);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), EstimationNo);
		Thread.sleep(1000);

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstimationNo));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//Verify whether the recalled item is the same as the item in the cart.
		String EstmnNumberRecallPge = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		asrt.assertEquals(EstimationNo,EstmnNumberRecallPge,"Mismatch in Estimation number, expected: "+EstimationNo+" but got "+EstmnNumberRecallPge+" in Recall Estimation page ");

		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), TransactionType);

		// Click on Process Estimation  
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
	}

	///<Summary>
	///Method to click on the Hamburger Icon
	///@Author: Gokul.P
	///</Summary>

	//		public void HamBurgerButtonClick(String NavigationIcons) throws InterruptedException
	//		{
	//			Thread.sleep(5000);			
	//			  if(base.isPresent(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("headerHighContrastBorderThin flexRow"))) {
	//			this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"), 5, 2);
	//			  }
	//			  else
	//			  {
	//				  base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("headerSplitViewToggleButton iconGlobalNavButton win-splitviewpanetoggle"));
	//				  this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"), 5, 2);
	//	 
	//			  }
	//		}  

	public void HamBurgerButtonClick(String NavigationIcons) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

		try {
			Thread.sleep(3000);		
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

		}catch(Exception e) 
		{
			if(base.isExists(NormalSaleGoldAndSilverObj.Btn_Estimation("headerSplitViewToggleButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"))) {
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("headerSplitViewToggleButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
			}
			else
			{
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("headerSplitViewToggleButton expandedNavButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
			}
			Thread.sleep(3000);
			base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons));
		}		
	}

	/*public void HamBurgerButtonClick(String NavigationIcons) throws InterruptedException
	{

		this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"), 5, 2);
	}
	public void HamBurgerButtonClick(String NavigationIcons) throws InterruptedException
	{

		this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("h2","Unlock Adjustments"), 5, 2);
	}*/

	/// <summary>
	/// Method to search by customer ID and selecting option from menu bar for GPP Customer
	///Author-Jhoncy
	/// </summary>	
	public void SearchByCustomerIDForGPP(String CustomerID,String Option) throws InterruptedException
	{
		// Wait for loader or overlay to go away
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

		// Wait for field to be clickable, then click via JS (if necessary)
		By SearchBox = NormalSaleGoldAndSilverObj.Edt_Name("Search");

		Wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		base.excuteJsClick(SearchBox); // JS click to focus the field

		// Wait again for it to be interactable
		WebElement element = Wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		element.clear();  
		element.sendKeys(CustomerID);

		Thread.sleep(3000);
		base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Customers"),20);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Customers"));
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SignInButton("Customers")),"The user is not able to navigate to the customer page");

		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions(Option));
	}
	/// <summary>
	/// Method to Create customer offer advance
	/// Author: Chandana Babu
	/// </summary>	
	public Map<String, String> CreateCustomerOfferAdvance(String CustomerID,String Pieces, String GrossWeight, double DepositPercent,String Company, String Location) throws InterruptedException
	{
		Map<String, String> Details = new HashMap<>();
		//Click on advance-->customer order
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Customer Order"));

		//Search customer and click on add to customer order
		this.SearchByCustomerIDForGPP(CustomerID, UtilityTestData.AddToCustomerOrder);

		//Search CST and click on the CST_Order
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.ProductOfferAdvance);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dataListLine"));

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {
			Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dataListLine")));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("dataListLine"));
			System.out.println("Clicked again on CST orderprod");
		}
		catch(TimeoutException e) {
			System.out.println("Not again Clicked on CST orderprod");
		}

		try {
			if (Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Payement("Error")))!=null) {
				base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			}
		}catch(TimeoutException e) {
			System.out.println("Error Message not found");
		}

		//Click on "Add to order line"
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.buttonClick(LoginPageObj.Edt_Alert("Add to order line"));

		//Select the 'Is offer order' checkbox
		base.buttonClick(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_CheckBox("checkbox", "IsofferChkBox"));

		//Choose the company and invent location
		base.selectorByVisibleText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_IsOrder("col", "viewModel.IsOrderEntry"), Company);
		base.selectorByVisibleText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_IsOrder("col stretch", "viewModel.IsOrderEntry"), Location);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_BankProofType("Sales person"),1);

		//Select the order line
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));

		//Select the ingredient line
		base.scrollToElement(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));

		//Click on edit ingredient line
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("SimpleProductDetailsView_EditSlDtlappBarCommandCommand"));

		//Enter pieces and weight
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: Pieces"),Pieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: Weight"),GrossWeight);

		//Click ok button
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		double TotalAmount = Double.parseDouble(base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Total Amount", "h4 ellipsis cell")));
		String AdvanceAmount = Double.toString(TotalAmount);
		double GoldRate = TotalAmount/Double.parseDouble(GrossWeight);
		String FixedGoldRate = String.format("%.2f",GoldRate);

		//Click on willing to pay button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("SimpleProductDetailsView_appWillToPayCommandCommand"));

		//Enter the amount
		String WillingToPayAmount = Double.toString(TotalAmount*DepositPercent);

		System.out.println("WillingToPayAmount  " + WillingToPayAmount);

		Thread.sleep(1000);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Amount("value: WILLAMT", "number"), WillingToPayAmount);
		Thread.sleep(1000);
		String OfferExpiryDate= base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: SDATE"));
		String ValidityDays = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: SDAYS")).replaceAll("\\D+", "");;

		//Click on apply button
		base.buttonClick(LoginPageObj.Btn_SignInButton("APPLY"));

		//Step 17: Click on edit line
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("win-structuralnodes win-selectionmode"));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_OrderView_EditSlappBarCommandCommand"));

		//Enter the line delivery date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ExpiryDate = LocalDate.parse(OfferExpiryDate, formatter);
		LocalDate targetDate = ExpiryDate.minusDays(1);
		String Day = Integer.toString(targetDate.getDayOfMonth());
		String Month = Integer.toString(targetDate.getMonthValue() - 1);
		String Year = Integer.toString(targetDate.getYear());		
		base.selectorByValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Date("content ExtensionTemplateDialog", "win-datepicker-date"), Day);
		base.selectorByValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Date("content ExtensionTemplateDialog", "win-datepicker-month"), Month);
		base.selectorByValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Date("content ExtensionTemplateDialog", "win-datepicker-year"), Year);

		//Enter remarks
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Remarks("value: Remarks"), UtilityTestData.Remarks);

		//Click on OK button
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		//Add nominee details, Select a sales person
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_OrderView_NomineeDetailsAppBarCommandCommand"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeName"),UtilityTestData.NomineeName);
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: NomineeRelationArr"),1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));	
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year win-dropdown win-order2 displayOrder3"),UtilityTestData.DobYear);
		String Address = base.GetText(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeAddress"));
		if(Address == "") {
			base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeAddress"),UtilityTestData.NomineeAddress);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}
		else {
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}

		//Click on save order button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_OrderView_appBarCommandCommand"));
		Thread.sleep(1000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("YES")))
		{
			base.buttonClick(LoginPageObj.Btn_SignInButton("YES"));
		}
		String Message = base.GetText(NormalSaleDiamondandPlatinumObj.Ele_GrossWeight("text: content"));
		String OrderId = Message.split(" - ")[1].split(" ")[0];
		//System.out.println(OrderId);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		Details.put("OrderId",OrderId);
		Details.put("AdvanceAmount",AdvanceAmount);
		Details.put("WillingToPayAmount",WillingToPayAmount);
		Details.put("ValidityDays", ValidityDays);
		Details.put("OfferExpiryDate", OfferExpiryDate);
		Details.put("FixedGoldRate", FixedGoldRate);

		return Details;
	}

	/// <summary>
	/// Method to Create Normal Advance
	/// Author: Chandana Babu
	/// </summary>
	public String CreateNormalAdvance(String CustomerNo, String AdvanceAmount, String SalesPerson, String DueYear) throws Exception {
		//Select customer and  click on add to sale button
		Thread.sleep(1000);
		this.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(3000);
		//Click on advance button at the transaction screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"));
		//Click on advance collection button
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance Collection"));
		Thread.sleep(5000);
		if(base.isPresent(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}
		Thread.sleep(3000);
		//select deposit type as normal
		//Enter deposit amount
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),AdvanceAmount);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Select sales person
		base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"),1);
		//Select due date
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), DueYear);
		//Enter remarks
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"),UtilityTestData.Remarks);
		//Add nominee details
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		//Enter nominee name
		//Select nominee relation
		//Click on check box(Same as customer address)					
		//Click on OK button
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"), UtilityTestData.NomineeName);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"), UtilityTestData.NomineeRelation);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"),UtilityTestData.DobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		//Click on deposit button
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		Thread.sleep(3000);
		//Click on amount
		//select a Payment method(Cash or Card)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		String AdvanceAmountPaid = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_CartMoney("flexGrow50 flexRow row-enter","h1 marginBottom0 iconReturnKey button-content-return"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),UtilityTestData.ApprCode);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		Thread.sleep(4000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),UtilityTestData.CardNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		return AdvanceAmountPaid;
	}


	/// <summary>
	/// Method to Create Normal Advance for Goldcoin
	/// Author: Neethu
	/// </summary>
	public String CreateNormalAdvanceGoldCoin(String CustomerNo, String AdvanceAmount, String SalesPerson, String DueYear,double MetalRate) throws Exception {
		//Select customer and  click on add to sale button
		Thread.sleep(1000);
		String GoldcoinWeight =Double.toString(Double.parseDouble(AdvanceAmount)/(MetalRate*(99.9/100))) ;
		this.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);
		System.out.println(GoldcoinWeight+"Coin weigth");
		Thread.sleep(3000);
		//Click on advance button at the transaction screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"));
		//Click on advance collection button
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance Collection"));
		Thread.sleep(5000);
		if(base.isPresent(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}
		Thread.sleep(3000);
		//select deposit type as normal
		//Enter deposit amount
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),AdvanceAmount);
		//Check IsGoldCoinAdvance:
		base.ClickCondition(CustomerCreationObj.Ele_Name("IsGldCoininp"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(CustomerCreationObj.Ele_Name("GldCoinWt"),GoldcoinWeight);
		//Select sales person
		base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"),1);
		//Select due date
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), DueYear);
		//Enter remarks
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"),UtilityTestData.Remarks);
		//Add nominee details
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		//Enter nominee name
		//Select nominee relation
		//Click on check box(Same as customer address)					
		//Click on OK button
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"), UtilityTestData.NomineeName);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"), UtilityTestData.NomineeRelation);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"),UtilityTestData.DobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		//Click on deposit button
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		Thread.sleep(3000);
		//Click on amount
		//select a Payment method(Cash or Card)
		//		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		//		base.ClickCondition(LoginPageObj.Edt_AlertText("HDFC (Credit Card)"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		String AdvanceAmountPaid = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_CartMoney("flexGrow50 flexRow row-enter","h1 marginBottom0 iconReturnKey button-content-return"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),UtilityTestData.ApprCode);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		Thread.sleep(4000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_ApprCode("textInputArea"),UtilityTestData.CardNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("OK"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		return AdvanceAmountPaid;
	}

	/// <summary>
	/// Find Board Rate Using Skus RG/ RP /RS
	/// Author: Vishnu Manoj K
	/// </summary>
	public void BoardRateFromSkuRporRgorRs(String Sku, int SkuNumber, Map<String, String> DataMap) throws Exception {

		// Step 1: Click "Add to Cart" to activate the SKU input field
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Thread.sleep(3000);
		// Step 2: Wait and enter the SKU string into the input field (do not clear existing data)
		//wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity")));
		base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), Sku);

		// Step 3: Press return key button to trigger SKU search
		wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"))).click();

		// Step 4: Adjust zoom level to 40%
		base.setZoom(driver, 40);

		// Step 5: Wait until the data table with SKU details appears on the screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.dataListLine")));

		// Step 6: Fetch and store item-level details in DataMap
		DataMap.put("SKU_" + SkuNumber + "_Purity", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("Purity"), "value"));

		// Step 7: Adjust zoom level to 30% for better visibility
		base.setZoom(driver, 30);

		// Step 8: Extract and flatten table data into DataMap
		List<Map<String, String>> SkuData = ExtractTableData(driver);
		for (int ItemIndex = 0; ItemIndex < SkuData.size(); ItemIndex++) {
			Map<String, String> Row = SkuData.get(ItemIndex);
			for (Map.Entry<String, String> Entry : Row.entrySet()) {
				String Key = "SKU" + SkuNumber + "_ITEM" + (ItemIndex + 1) + "_" + Entry.getKey().toUpperCase().replaceAll("\\s+", "");
				DataMap.put(Key, Entry.getValue());
			}
		}

		// Step 9: Calculate RG/RS/RP
		double StoneWeight = 0.0, NetWeight = 0.0, TotalRGWeight = 0.0;
		String Rate_RP = "";
		String Rate_RG = "";
		String Rate_RS = "";
		for (int ItemIndex = 0; ItemIndex < SkuData.size(); ItemIndex++) {
			String prefix = "SKU" + SkuNumber + "_ITEM" + (ItemIndex + 1);
			String ItemId = DataMap.getOrDefault(prefix + "_ITEMID", "").trim().toUpperCase();
			String QtyStr = DataMap.getOrDefault(prefix + "_QTY", "0").replaceAll("[^\\d.]", "");
			String Unit = DataMap.getOrDefault(prefix + "_UNIT", "").trim().toLowerCase();
			String Rate = DataMap.getOrDefault(prefix + "_RATE", "0").replaceAll("[^\\d.]", "");

			if (!Rate.isEmpty()) {			
				if (ItemId.equals("RP")) {
					Rate_RP = Rate;
				}
				if (ItemId.equals("RG")) {
					Rate_RG = Rate;
				}
				if (ItemId.equals("RS")) {
					Rate_RS = Rate;
				}
			}
			if (!QtyStr.isEmpty()) {
				double Qty = Math.round(Double.parseDouble(QtyStr) * 1000.0) / 1000.0;

				if (ItemId.equals("RG") || ItemId.equals("RP") || ItemId.equals("RS")) {
					NetWeight += Qty;
					if (ItemId.equals("RG")) {
						double QtyInGrams = Unit.contains("ct") ? Qty * 0.2 : Qty;
						TotalRGWeight += QtyInGrams;
					}
				} else {
					StoneWeight += Unit.contains("ct") ? Qty * 0.2 : Qty;
				}
			}
		}

		DataMap.put("SKU_" + SkuNumber + "_RateRP", Rate_RP);
		DataMap.put("SKU_" + SkuNumber + "_RateRG", Rate_RG);
		DataMap.put("SKU_" + SkuNumber + "_RateRS", Rate_RS);
	}



	/// <summary>			/// Method to Add random SKU from the Test Data  list 
	/// Author: Vishnu RR
	/// </summary>	


	private static List<String> unusedSKUs = new ArrayList<>();

	public static String getRandomSKU(List<String> skuList) {
		if (unusedSKUs.isEmpty()) {
			unusedSKUs = new ArrayList<>(skuList); // Reset when all are used
		}
		Random rand = new Random();
		String sku = unusedSKUs.remove(rand.nextInt(unusedSKUs.size())); // Pick & remove from this round
		return sku;
	}

	////// <summary>
	/// Method to Create customer offer advance with weight booked
	/// Method to Create customer offer advance
	/// Author: Hasna EK
	/// </summary>
	public Map<String, String> CreateWeightBookedOfferAdvance(String CustomerId, String Pieces, String GrossWeight, 
			String Company, String Location, double DepositePercentage) throws Exception {

		Map<String, String> Details = new HashMap<>();

		// Step 1: Navigate to the Transaction page
		this.HamBurgerButtonClick("iconShop");
		Details = this.CreateCustomerOfferAdvance(CustomerId, Pieces, GrossWeight, DepositePercentage, Company, Location);

		// Step 2: Go to transactions, Search customer, Click on Add to sale
		this.HamBurgerButtonClick("iconShop");
		this.SearchByCustomerID(CustomerId, UtilityTestData.MenuBarOptions[0]);

		// Step 3: Choose advance --> advance collection
		base.waitForClickable(LoginPageObj.Edt_AlertText("Advance"),15);
		base.ClickCondition(LoginPageObj.Edt_AlertText("Advance"));

		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"),15);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"));
		if (base.isExists(LoginPageObj.Btn_SingnIn("Button1Close"))) 
		{
			base.waitForClickable(LoginPageObj.Btn_SingnIn("Button1Close"),15);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}

		// Step 4: Choose type as Order
		base.waitForVisible(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"), "Order");

		// Step 5: Select the transaction number from the drop down
		String OrderId = Details.get("OrderId");
		base.waitForVisible(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"));
		Thread.sleep(3000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), OrderId);

		// Step 6: Enter the deposit amount
		String AdvanceAmount = Details.get("AdvanceAmount");
		String WillingToPayAmount = Details.get("WillingToPayAmount");
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"), WillingToPayAmount);
		System.out.println(WillingToPayAmount + " WillingToPayAmount");
		System.out.println(AdvanceAmount + " AdvanceAmount");

		// Step 7: Choose the sales person
		base.waitForClickable(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"),15);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);

		// Step 8: Click on deposit button
		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"),15);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));

		// Step 9: Click on the Amount
		base.waitForClickable(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"),15);
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"), 40);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Thread.sleep(5000);

		// Step 10: Select a Payment method (Cash or Card)	
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"),15);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		String ExpectedAdvanceAmount = base.GetAttribte( CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", "");
		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"),15);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));

		// ---- APPROVAL CODE ENTRY ----
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);

		base.waitForClickable(LoginPageObj.Btn_SignInButton("OK"),15);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		// ---- CARD NUMBER ENTRY ----
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);

		base.waitForClickable(LoginPageObj.Btn_SignInButton("OK"),15);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		// Close deposit popup
		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"),15);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

		// Step 11: Post the Invoice
		String NewPdfName = PdfUtilities.DownloadAndRenameLatestPDF("OfferAdvance");

		Map<String, String> IntialAdvanceReceiptPdf = PdfUtilities.OfferAdvancePdfValidation();
		Details.put("AdvanceReceived", IntialAdvanceReceiptPdf.get("AdvanceReceived"));

		return Details;
	}

	/// <summary>
	/// Method for multiple OG Converted to Normal Advance
	/// Author-Jhoncy
	/// </summary>	
	public String MultipleOGConvertedToNormalAdvance(String CustomerID) throws Exception
	{
		this.HamBurgerButtonClick("iconShop");
		//Select customer and click on add to sale button
		Thread.sleep(2000);
		this.SearchByCustomerID(CustomerID,UtilityTestData.MenuBarOptions[0]);

		//Select OG
		Thread.sleep(3000);
		base.waitForClickable(NormalSaleGoldAndSilverObj.Edt_Name("Search"), 30);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Click on OG own and choose purchase
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));

		//Select configuration
		Thread.sleep(3000);
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), 30);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);

		try {
			base.waitForClickable(LoginPageObj.Btn_SignInButton("OK"),15);
			base.UnifiedClick(LoginPageObj.Btn_SignInButton("OK"));
		} catch (Exception e) {
			// Ignore if no popup
		}

		//Click on add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		//To Handle the Unexpected Error
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

		//Note:OGPurchase
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));

		//Select QC Person
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Select Smith person
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Enter piece value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),UtilityTestData.GrossPieces);

		//Enter gross weight
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),UtilityTestData.GrossWeight);

		//Click on Calculate button
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

		//Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Select payment method as convert advance
		//Choose normal advance
		//Select sales person
		//Select the due date and Enter remarks
		//Click on nominee details
		//Enter nominee name, nominee relation, Click on checkbox(same as customer address)Click on ok button and deposit button
		//Click on deposit button
		Thread.sleep(3000);
		String PaymentAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		String OGAmountDue = PaymentAmount.replaceAll("[^\\d.]", "");
		System.out.println(OGAmountDue);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(2000);

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Convert to Advance")),"The user is not able to view the payment methods");

		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_AlertText(UtilityTestData.PaymentMode));	
		Thread.sleep(4000);
		base.ClickCondition(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));
		Thread.sleep(2000);
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);


		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), UtilityTestData.DueYear);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Remarks("6"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"), UtilityTestData.Remarks);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"),UtilityTestData.NomineeName);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"), UtilityTestData.NomineeRelation);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), "1989");
		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));	
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);	

		//Warning handling if present
		try {
			WebDriverWait ShortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
			WebElement Warning = ShortWait.until( ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Payement("Warning")) );
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		} catch (Exception e) {
			// Warning not found
		}
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));

		return OGAmountDue;
	}

	public void RecallEstimateItemValidation(
			List<String> ExpectedItemNames,          // All expected item names (Billing page)
			List<String> ItemQuantity,               // All expected item quantities (Billing page)
			List<String> ExpectedOGItemNames,        // OG item names to validate (in same order as their weights)
			List<String> ExpectedGrossWeights,       // OG item weights to validate (same order as OG names)
			double ExpectedSubtotal,
			double ExpectedTax,
			double ExpectedTotal,
			int TotalLinesBillingPage,
			String OGName) throws InterruptedException {

		List<WebElement> ItemNamesFromRecall =base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "ITEM NAME"));
		List<WebElement> QuantityFromRecall =base.GetElement(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell", "QUANTITY"));

		int TotalLinesRecallPage = ItemNamesFromRecall.size();
		int OgIndex = 0;

		for (int k = 0; k < ItemNamesFromRecall.size(); k++) {

			String ActualItemRaw = ItemNamesFromRecall.get(k).getText().trim();

			if (k > 4) {
				base.ScrollToWebElements(ItemNamesFromRecall.get(k));
				Thread.sleep(4000);
			}

			// ---------- CASE 1: OG item validation ----------
			if (ExpectedOGItemNames.contains(ActualItemRaw)) {

				String ExpectedOGName      = ExpectedOGItemNames.get(OgIndex);
				String ExpectedOGWeightStr = ExpectedGrossWeights.get(OgIndex);

				asrt.assertTrue(ActualItemRaw.contains(ExpectedOGName), "The " + OGName + " Item Name mismatch: Expected item containing "+ ExpectedOGName + " but got " + ActualItemRaw + " in Recall page");
				String QtyStr = QuantityFromRecall.get(k).getText().trim().replaceAll("[^\\d.]", "");
				double ActualQty   = Double.parseDouble(QtyStr);
				double ExpectedQty = Double.parseDouble(ExpectedOGWeightStr);

				asrt.assertEquals(ActualQty,ExpectedQty,1,"OG Quantity mismatch: Expected " + ExpectedQty + " but got " + ActualQty + " in Recall page");
				System.out.println("The " + OGName + " Item Validation successful from recall page for item: " + ExpectedOGName);
				OgIndex++;
			}

			// ---------- CASE 2: Non-OG item validation ----------
			else {

				String ExpectedItemRaw = ExpectedItemNames.get(k).trim();

				String ActualItemName = ActualItemRaw.contains("-")
						? ActualItemRaw.substring(ActualItemRaw.lastIndexOf("-") + 1).trim()
								: ActualItemRaw.trim();

				String ExpectedItemName = ExpectedItemRaw.contains("-")
						? ExpectedItemRaw.substring(ExpectedItemRaw.lastIndexOf("-") + 1).trim()
								: ExpectedItemRaw.trim();

				asrt.assertTrue(ActualItemName.contains(ExpectedItemName),"Item Name mismatch: Expected item containing "+ ExpectedItemName + " but got " + ActualItemName + " in Recall page");
				String QtyStr = QuantityFromRecall.get(k).getText().trim().replaceAll("[^\\d.]", "");
				double ActualQty = Double.parseDouble(QtyStr);

				String ExpectedQtyStr = ItemQuantity.get(k).trim().replaceAll("[^\\d.]", "");
				double ExpectedQty = Double.parseDouble(ExpectedQtyStr);

				asrt.assertEquals(ExpectedQty,ActualQty,1,	"Quantity mismatch: Expected " + ExpectedQty + " but got " + ActualQty + " in Recall page");
				System.out.println("The Non-" + OGName + " Item Validation successful from recall page");
			}
		}

		// ---------- Totals validation ----------
		double actualTotalWithoutTax = Math.abs(Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalWithoutTax"))));
		double actualTax = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalTax")));
		double actualSubTotal = Math.abs(Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.SubTotal"))));

		asrt.assertEquals(ExpectedTax,actualTax,1,"Tax Value mismatch: Expected " + ExpectedTax + " but got " + actualTax + " in Recall page");
		asrt.assertEquals(ExpectedSubtotal,actualTotalWithoutTax,1,"Total (Without Tax) mismatch: Expected " + ExpectedSubtotal + " but got "	+ actualTotalWithoutTax + " in Recall page");		
		asrt.assertEquals(ExpectedTotal,actualSubTotal,1,"SubTotal mismatch: Expected " + ExpectedTotal + " but got " + actualSubTotal + " in Recall page");
		asrt.assertEquals(TotalLinesBillingPage,TotalLinesRecallPage,1,"Total line count mismatch: Expected " + TotalLinesBillingPage+ " but got " + TotalLinesRecallPage + " in Recall page");
		System.out.println("======== Validation completed from recall page ==================");
	}
	/// <summary>
	/// Method to validate the items in Recall page having OG, OD, OGT - before performing exchange or sales
	/// Author - Christy Reji
	/// Modified - Added support for multiple OG items with correct weight mapping
	/// </summary>

	// <summary>
	// Method to Open any GPP scheme and take first collection
	// Author: Chandana Babu
	// </summary>
	public String OpenGppAndTakeCollection(String CustomerNo, String Scheme, String InstallmentAmount,String SchemeCode,String IdProof, String IdProofNo) throws Exception {
		//Go to GPP
		base.ClickCondition(LoginPageObj.Edt_AlertText("GPP"));
		//Select New GPP Account Opening
		WebDriverWait Wait =new WebDriverWait(driver, Duration.ofSeconds(50));
		Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Btn_return("New GPP Account")));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_return("New GPP Account"));
		//Search customer phone number
		//Add to GPP ac open
		this.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[6]);
		//Select scheme code
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("SchmTypeOptions"),Scheme);
		//Enter installment amount
		Thread.sleep(2000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("InstlamtInput"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("InstlamtInput"), InstallmentAmount);
		//Select sales person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"), 1);
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"),UtilityTestData.SalePersonNameWithNo);
		Thread.sleep(3000);
		String IdProofNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("Idproofno1"));
		if (IdProofNumber == null || IdProofNumber.trim().isEmpty()) {
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("IdTypeOptions"), IdProof);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("Idproofno1"), IdProofNo);
		}

		//Select bank proof type
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("BankProof"),UtilityTestData.BankProof);
		//Enter nominee name
		base.setData(GppOpeningAndCollectionMultiplePlansObj.Sel_GPPNomineeName("NomineeName"),UtilityTestData.NomineeName);
		//Select nominee relation
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("NomineeRelation"),UtilityTestData.NomineeRelation);
		//Click on check box( same as above)
		base.ClickCondition(GppOpeningAndCollectionMultiplePlansObj.Sel_GPPNomineeName("IsSameAsAbove"));

		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);

		//Click on save button
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_return("View more"));
		Thread.sleep(2000);
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("SearchView_AppBarView_appBarCommandCommand"));	
		Thread.sleep(2000);
		Map<String, String> PdfDetails = PdfUtilities.ExtractDetailsFromGPPPdf(SchemeCode);
		Thread.sleep(2000);
		String ApplicationIdPdf   = PdfDetails.get("ApplicationID");
		//Select customer
		//Click on Add to sale button
		this.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[0]);
		//Click on GPP
		base.ClickCondition(LoginPageObj.Edt_AlertText("GPP"));
		//GPP collection entry
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("GPP Collection Entry"));
		//Choose deposit type
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"),UtilityTestData.GPPDepositType);
		//Enter transaction number
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), ApplicationIdPdf);
		//Select sales person

		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);
		//base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"),UtilityTestData.SalePersonName);

		//Click on deposit
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		//Click on the Amount
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		//Select a Payment method(Cash or Card)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC"));
		String ExpectedAdvanceAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value").replace(",", "");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));
		return ApplicationIdPdf;
	}

	/// <summary>
	/// Method for multiple SR Converted to Normal Advance
	/// Author - nivya
	/// </summary>
	public Map<String, String> MultipleSRConvertedToNormalAdvance(
			String CustomerID,
			List<String> CounterTransfer,
			List<String> ReturnProducts) throws Exception {

		Map<String, String> Result = new HashMap<>();

		this.HamBurgerButtonClick("iconShop");

		// Step 1: Select customer and click on add to sale button
		this.SearchByCustomerID(CustomerID, UtilityTestData.MenuBarOptions[0]);

		// Step 2: Scan Multiple SKU's, click on Add to cart button and Select any one of the sales agent
		List<String> AllSkuList = new ArrayList<>();
		for (int j = 0; j + 2 < CounterTransfer.size(); j += 3) {
			List<String> RequiredSkuList = this.SelectMultipleSKUs(
					Integer.parseInt(CounterTransfer.get(j)),
					"Inter",
					CounterTransfer.get(j + 1),
					CounterTransfer.get(j + 2)
					);
			AllSkuList.addAll(RequiredSkuList);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation(
					"headerSplitViewToggleButton expandedNavButton iconGlobalNavButton win-splitviewpanetoggle win-disposable"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h5 textLeft centerY", "Transaction"));
		}

		Map<String, String> ScannedSKUDataMap = new LinkedHashMap<>();
		for (int i = 0; i < AllSkuList.size(); i++) {
			this.SKUIngridentTableValues(AllSkuList.get(i), i + 1, ScannedSKUDataMap);
		}

		// Step 3: Click on the Amount and Select a Payment method (Cash or Card)
		String PaymentAmount = this.PaymentAfterRecallEstimate("HDFC");

		// Step 4: Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		String TaxInvoiceName = PdfUtilities.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		String TaxInvoice = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName + "";
		String InvoiceNo = PdfUtilities.ExtractReceiptNoFrmSaleInvoice(TaxInvoice);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		System.out.println("Generated Invoice No: " + InvoiceNo);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		SearchByCustomerID(CustomerID, UtilityTestData.MenuBarOptions[0]);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Click on customer adjustment, return transaction, Receipt Number, Return Product, Return Button
		ReturnDetails Details = ReturnMultipleProductsWithDetails(InvoiceNo, ReturnProducts);

		// Click on the Amount, Payment method, transaction type, due date, Nominee details, Deposit button
		String PaymentAmountSR = base.GetText(
				NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")
				).replace("\u20B9", "").replace(",", "").trim();

		String SRAmountDue = PaymentAmountSR.replaceAll("[^\\d.]", "");
		System.out.println(SRAmountDue);

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(2000);

		asrt.assertTrue(
				base.isExists(LoginPageObj.Edt_AlertText("Convert to Advance")),
				"The user is not able to view the payment methods"
				);

		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText(UtilityTestData.PaymentMode));
		Thread.sleep(4000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SalesPerson(UtilityTestData.SalePersonNumber));
		Thread.sleep(4000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Ele_SalesPerson(UtilityTestData.SalePersonNumber),1);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), UtilityTestData.DueYear);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Remarks("6"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"), UtilityTestData.Remarks);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"), UtilityTestData.NomineeName);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"),1);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));

		//Nominee DOB Details
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);

		// Warning handling if present
		try {
			WebDriverWait ShortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
			WebElement Warning = ShortWait.until(
					ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Payement("Warning"))
					);
			base.excuteJsClick(LoginPageObj.Btn_SignInButton("OK"));
		} catch (Exception e) {
			// Warning not found
		}

		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton", "Close"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox", "win-item", "DOCUMENT NORMAL ADV. INVOICE"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue", "PRINT"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		// Store both values in result map
		Result.put("InvoiceNo", InvoiceNo);
		Result.put("PaymentAmountSR", PaymentAmountSR);

		return Result;
	}

	// <summary>
	// Method to Create offer advance with Reserve Sku and take first collection
	// Author: Sangeetha M S
	// </summary>
	public Map<String, String> OfferAdvanceReserveSkuAndTakeCollection(String CustomerPhoneNo, int SkuCount, String TransferType,String FromCounterGold,String MetalTypeGold, double DepositPercent) throws Exception
	{
		Map<String, String> OfferAdvanceDataMap = new HashMap<>();

		//Select SKU Number
		List<String> GoldSku =SelectMultipleSKUs(SkuCount,TransferType,FromCounterGold,MetalTypeGold);
		List<String> SkuList=new ArrayList<>();
		SkuList.addAll(GoldSku);
		String SkuNumber = SkuList.get(0);		
		Thread.sleep(3000);
		HamBurgerButtonClick("iconShop");	
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
		base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SkuNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
		base.setZoom(driver, 40);
		double ExpectedTotalCValueInItemDetailsPage = Double.parseDouble(base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"), "value"));
		double ExpectedGrossWeightInItemDetailspage =  Double.parseDouble(base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value"));		
		double ExpectedTotalAmountInItemDetailPage = Double.parseDouble(base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value"));
		Thread.sleep(3000);
		HamBurgerButtonClick("iconShop");	

		//Click on advance
		//Select customer order
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Customer Order"));

		//Search customer and click on add to customer order
		Thread.sleep(3000);
		SearchByCustomerIDForGPP(CustomerPhoneNo, UtilityTestData.MenuBarOptions[7]);

		//Enter the SKU number in the field and click enter
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("AddSKUAutomatic "), SkuNumber);
		base.pressKey(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_ScanSku("AddSKUAutomatic "), "ENTER");
		Thread.sleep(2000);
		String ActualGrossWeightInCustomerOrderPge=base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column3", "h4 ellipsis cell"));
		double ActualGrossWeightInCustomerOrderPage = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column3", "h4 ellipsis cell")));
		double ActualCValueInCustomerOrderPage = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column4", "h4 ellipsis cell")));
		String TotalInCustomerOrderPage=base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("column7", "h4 ellipsis cell"));
		double ActualTotalAmountInCustomerOrderPage = Double.parseDouble(base.GetValue(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("TotAmtInput")));
		OfferAdvanceDataMap.put("TotalInCustomerOrderPage", TotalInCustomerOrderPage);
		OfferAdvanceDataMap.put("ActualGrossWeightInCustomerOrderPge", ActualGrossWeightInCustomerOrderPge);

		//Verify while nominee detail getting removed if we update any other details after updating the nominee details.
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_OrderView_NomineeDetailsAppBarCommandCommand"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeName"),UtilityTestData.NomineeName);
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: NomineeRelationArr"),1 );
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));	
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year win-dropdown win-order2 displayOrder3"),UtilityTestData.DobYear);
		String Address = base.GetText(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeAddress"));
		if(Address == "")
		{
			base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeAddress"),UtilityTestData.NomineeAddress);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}
		else
		{
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}			

		//Select the 'Is offer order' checkbox
		base.buttonClick(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_CheckBox("checkbox", "IsofferChkBox"));

		//Add nominee details
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_OrderView_NomineeDetailsAppBarCommandCommand"));
		Thread.sleep(1000);
		WebElement NomineeNameField = driver.findElement(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPCustAccnt("NmInput"));
		String NomineeName = NomineeNameField.getAttribute("value");
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("secondaryButton"));

		//Choose the company and invent location
		base.selectorByVisibleText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_IsOrder("col", "viewModel.IsOrderEntry"), UtilityTestData.Company[0]);
		base.selectorByVisibleText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_IsOrder("col stretch", "viewModel.IsOrderEntry"), UtilityTestData.Location[0]);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_BankProofType("Sales person"), 1);

		//Select the order line
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		Thread.sleep(2000);
		String ExpectedFixedGoldRate= base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj
				.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Rate"));
		String PurityOrderLine=base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj
				.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell","Config"));

		double Rate = Double.parseDouble(ExpectedFixedGoldRate);
		double ConvertedRate=0.0;
		if (PurityOrderLine.equalsIgnoreCase("18k"))
		{		
			ConvertedRate = (Rate * 18.0) / 22.0;
			ExpectedFixedGoldRate = String.valueOf(ConvertedRate);		
		}
		else
		{
			ExpectedFixedGoldRate = String.valueOf(Rate);
		}
		OfferAdvanceDataMap.put("ExpectedFixedGoldRate", ExpectedFixedGoldRate);
		System.out.println("ExpectedFixedGoldRate"+ExpectedFixedGoldRate);

		//Select the ingredient line
		base.scrollToElement(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		base.ClickCondition(LoginPageObj.Ele_ErrorMessage("win-itembox"));

		//Click on willing to pay button
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("MGDRetail_MGDRetail_Extensions_OrderView_appWillToPayCommandCommand"));

		//Enter the amount
		Thread.sleep(2000);
		String WillingToPayAmount = String.format("%.2f",ActualTotalAmountInCustomerOrderPage*DepositPercent);
		Thread.sleep(1000);
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Amount("value: WILLAMT", "number"), WillingToPayAmount);
		Thread.sleep(1000);
		String ExpectedOfferExpiryDate= base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: SDATE"));
		String ExpectedValidityDays = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: SDAYS")).replaceAll("\\D+", "");;
		OfferAdvanceDataMap.put("ExpectedOfferExpiryDate", ExpectedOfferExpiryDate);
		OfferAdvanceDataMap.put("ExpectedValidityDays", ExpectedValidityDays);

		//Click on apply button
		base.ClickCondition(LoginPageObj.Btn_SignInButton("APPLY"));

		//Click on edit line
		Thread.sleep(2000);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("win-structuralnodes win-selectionmode"));
		Thread.sleep(2000);
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("MGDRetail_MGDRetail_Extensions_OrderView_EditSlappBarCommandCommand"));

		//Enter the line delivery date
		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ExpiryDate = LocalDate.parse(ExpectedOfferExpiryDate, Formatter);
		LocalDate TargetDate = ExpiryDate.minusDays(1);
		String Day = Integer.toString(TargetDate.getDayOfMonth());
		String Month = Integer.toString(TargetDate.getMonthValue() - 1);
		String Year = Integer.toString(TargetDate.getYear());	
		base.selectorByValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Date("content ExtensionTemplateDialog", "win-datepicker-date"), Day);
		base.selectorByValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Date("content ExtensionTemplateDialog", "win-datepicker-month"), Month);
		base.selectorByValue(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Date("content ExtensionTemplateDialog", "win-datepicker-year"), Year);

		//Enter remarks
		base.setData(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_Remarks("value: Remarks"),UtilityTestData.Remarks);

		//Click on OK button
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		//Add nominee details, Select a sales person
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_OrderView_NomineeDetailsAppBarCommandCommand"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeName"),UtilityTestData.NomineeName);
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: NomineeRelationArr"), 1);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));	
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year win-dropdown win-order2 displayOrder3"),UtilityTestData.DobYear);
		String Address1 = base.GetText(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeAddress"));
		Thread.sleep(2000);
		if(Address1 == "")
		{
			base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: NomineeAddress"),UtilityTestData.NomineeAddress);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}
		else
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}

		//Click on save button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("MGDRetail_MGDRetail_Extensions_OrderView_appBarCommandCommand"));
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("YES"));
		String Remark = base.GetText(NormalSaleDiamondandPlatinumObj.Ele_GrossWeight("text: content"));
		String OrderId = Remark.split(" - ")[1].split(" ")[0];
		OfferAdvanceDataMap.put("OrderId", OrderId);
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

		//Go to transactions
		//Step Search customer
		//Step Click on add to sale button
		Thread.sleep(3000);
		HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		SearchByCustomerID(CustomerPhoneNo ,UtilityTestData.MenuBarOptions[0]);

		//Choose advance-->advance collection
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance"));
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"));
		Thread.sleep(5000);
		if(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));	
		}

		//Choose type as order
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"), "Order");

		//Select the transaction number from the drop down
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), OrderId);

		//Enter the deposit amount
		Thread.sleep(4000);
		WillingToPayAmount = String.format("%.2f", Double.parseDouble(WillingToPayAmount) + 15.00);
		OfferAdvanceDataMap.put("WillingToPayAmount", WillingToPayAmount);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),WillingToPayAmount);	
		Thread.sleep(2000);
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"),1);
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		Thread.sleep(5000);
		if(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}
		//		if(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")))
		//		{
		//			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		//			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),WillingToPayAmount);	
		//			base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);
		//			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		//		}
		if(base.isExists(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit")))
		{	
			//Choose type as order
			Thread.sleep(2000);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"), "Order");

			//Select the transaction number from the drop down
			Thread.sleep(1000);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), OrderId);

			//Enter the deposit amount
			Thread.sleep(5000);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),WillingToPayAmount);	
			Thread.sleep(1000);
			base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);
			Thread.sleep(1000);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		}

		//Click on the Amount
		Thread.sleep(3000);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("HDFC (Credit Card)"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

		//Post the Invoice			
		Map<String, String> FirstAdvanceReceiptPdf = PdfUtilities.OfferAdvancePdfValidation();
		String FirstAdvanceReceived = FirstAdvanceReceiptPdf.get("AdvanceReceived");
		OfferAdvanceDataMap.put("FirstAdvanceReceived", FirstAdvanceReceived);

		return OfferAdvanceDataMap;
	}

	// <summary>
	// Method for multiple OG Converted to Offer Advance
	// Author-Sangeetha M S
	// </summary>	
	public Map<String, String> MultipleOGConvertedToOfferAdvance(String CustomerID,String PiecesByPurity,String GrossWeight,double WillingToPayPercentage,String OgGrossWeight) throws Exception
	{
		Map<String, String> OfferAdvanceDataMap = new HashMap<>();

		//Create a offer Advance
		Map<String, String> OfferDetails=CreateCustomerOfferAdvance(CustomerID,PiecesByPurity,GrossWeight,WillingToPayPercentage,UtilityTestData.Company[0],UtilityTestData.Location[0]);
		String OfferAdvanceId              = OfferDetails.get("OrderId");
		OfferAdvanceDataMap.put("OfferAdvanceId", OfferAdvanceId);

		//OG Purchase
		//Select customer and click on add to sale button
		Thread.sleep(2000);
		HamBurgerButtonClick("iconShop");
		Thread.sleep(3000);
		SearchByCustomerID(CustomerID,UtilityTestData.MenuBarOptions[0]);

		//Select OG
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"OG");
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		//Click on OG own and choose purchase
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));

		//Select configuration
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);

		//Click on add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//To Handle the Unexpected Error
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");

		if (base.isElementPresent(driver, CartChangedAlert)) 
		{		
			try 
			{
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e)
			{
			}
		}

		//Select OG Purchase Option
		Thread.sleep(2000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NPurchase"));

		//Select QC Person
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),UtilityTestData.QCAndSmithPerson);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Select Smith person
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),UtilityTestData.QCAndSmithPerson); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);

		//Enter piece value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),UtilityTestData.GrossPieces);

		//Enter gross weight
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),OgGrossWeight);

		//Click on Calculate button
		Thread.sleep(1000);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

		//Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Click on the Amount
		Thread.sleep(5000);
		String PaymentAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		String OGAmountDue = PaymentAmount.replaceAll("[^\\d.]", "");
		System.out.println("OGAmountDue"+OGAmountDue);
		System.out.println("PaymentAmount"+PaymentAmount);
		OfferAdvanceDataMap.put("PaymentAmount", PaymentAmount);
		OfferAdvanceDataMap.put("OGAmountDue", OGAmountDue);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		//Select converted to advance button
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Convert to Advance"));

		//Choose deposit type as order 
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"),"Order");
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), OfferAdvanceId);
		Thread.sleep(3000); 

		//Select sales agent
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("3"),UtilityTestData.SalePersonName);
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"),1);
		Thread.sleep(2000);

		//Click on deposit button
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		Thread.sleep(6000);

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(5000);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT ORDER ADV. INVOICE"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));

		//Post Invoice
		Map<String, String> AdvanceReceiptDetailsFromPdf   = PdfUtilities.OfferAdvancePdfValidation();
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));	
		return OfferAdvanceDataMap;
	}

	// <summary>
	// To RetryCicking Until u find that element to be displayed
	// Author-Vishnu Manoj K
	// </summary>
	public void ClickWithRetry(By mainLocator, By retryLocator, int maxRetries, int waitInSeconds) {

		for (int attempt = 1; attempt <= maxRetries; attempt++) {
			System.out.println("Attempt " + attempt + " of " + maxRetries);

			// 1. Normal Click
			try {
				base.buttonClick(mainLocator);
				System.out.println("ButtonClick the element.");
				Thread.sleep(500); // wait for any UI changes
				if (base.isExists(retryLocator)) {
					System.out.println("Retry element found — stopping retries.");
					return;  // success
				}
			} catch (Exception e) {
				System.out.println("Button click failed: " + e.getMessage());
			}

			// 2. Action Click
			try {
				base.actionClick(mainLocator);
				System.out.println("ActionClick the element.");
				Thread.sleep(500); // wait for any UI changes
				if (base.isExists(retryLocator)) {
					System.out.println("Retry element found — stopping retries.");
					return;  // success
				}
			} catch (Exception e) {
				System.out.println("Action click failed: " + e.getMessage());
			}

			// 3. JS Click
			try {
				base.excuteJsClick(mainLocator);
				System.out.println("JsClicked the element.");
				Thread.sleep(500); // wait for any UI changes
				if (base.isExists(retryLocator)) {
					System.out.println("Retry element found — stopping retries.");
					return;  // success
				}
			} catch (Exception e) {
				System.out.println("JS click failed: " + e.getMessage());
			}

			System.out.println("Retry element NOT found — continuing retries.");

			// Optional wait between retries
			try { Thread.sleep(waitInSeconds * 1000L); } catch (InterruptedException ignored) {}
		}

		throw new RuntimeException("Failed to click main element after " + maxRetries + " retries.");
	}
	/*
	public void ClickWithRetry(By MainLocator, By RetryLocator, int MaxRetries, int WaitInSeconds) {
		int Attempts = 0;
		while (Attempts < MaxRetries) {
			try {
				base.excuteJsClick(MainLocator); // Try clicking main element
				System.out.println("Clicked on main element successfully.");
				return; // Exit if success
			} catch (Exception e) {
				System.out.println("Main element not clickable, retrying...");
			}

			try {
				base.excuteJsClick(RetryLocator); // Click retry element
				System.out.println("Clicked retry element to refresh UI.");
			} catch (Exception e) {
				System.out.println("Retry element not found.");
			}

			try {
				Thread.sleep(WaitInSeconds * 1000L); // Wait before retry
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Attempts++;
		}
		throw new RuntimeException("Failed to click main element after " + MaxRetries + " retries.");
	}
	 */

	/// <summary>
	/// Method for multiple OG Exchange Converted to Normal Advance With different Grossweight
	/// Author-Aiswarya
	/// </summary>	
	public String MultipleOGConvertedToNormalAdvancewithDiffGrossWeight(String CustomerID , String GrossWeight) throws Exception
	{
		this.HamBurgerButtonClick("iconShop");
		//Select customer and click on add to sale button
		Thread.sleep(2000);
		this.SearchByCustomerID(CustomerID,UtilityTestData.MenuBarOptions[0]);

		//Select OG
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_CustomerSearch("Products"));

		//Click on OG own and choose purchase
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));
		Thread.sleep(5000);

		//Select configuration
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), 40);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);

		//Click on add item button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));

		//To Handle the Unexpected Error
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");

		if (base.isElementPresent(driver, CartChangedAlert)) 
		{		
			try
			{
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e) {}
		}

		//Select QC Person
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);

		//Select Smith person
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1); 

		//Enter piece value
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),UtilityTestData.GrossPieces);

		//Enter gross weight
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),GrossWeight);

		//Click on Calculate button
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

		//Click on Add to cart button
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));
		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

		//Select payment method as convert advance
		//Choose normal advance
		//Select sales person
		//Select the due date and Enter remarks
		//Click on nominee details
		//Enter nominee name, nominee relation, Click on checkbox(same as customer address)Click on ok button and deposit button
		//Click on deposit button
		Thread.sleep(3000);
		String PaymentAmount = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("\u20B9", "").replace(",", "").trim();
		String OGAmountDue = PaymentAmount.replaceAll("[^\\d.]", "");
		System.out.println(OGAmountDue);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(2000);

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Convert to Advance")),"The user is not able to view the payment methods");

		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_AlertText(UtilityTestData.PaymentMode));	
		Thread.sleep(4000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Ele_SalesPerson(UtilityTestData.SalePersonNumber), 1);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), UtilityTestData.DueYear);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Remarks("6"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"), UtilityTestData.Remarks);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"),UtilityTestData.NomineeName);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"), 1);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));	
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(3000);	

		try 
		{
			WebDriverWait ShortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
			WebElement Warning = ShortWait.until( ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Payement("Warning")) );
			base.excuteJsClick(LoginPageObj.Btn_SignInButton("OK"));
		} 
		catch (Exception e) 
		{}
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		return OGAmountDue;
	}

	/// <summary>
	/// To Generate Pan Card Number
	/// Author-Vishnu Manoj K
	/// </summary>	
	public static String GenerateUniquePAN() {
		Set<String> GeneratedPANs = new HashSet<>();
		String Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random RandomGenerator = new Random();
		String Pan;
		do {
			StringBuilder PanBuilder = new StringBuilder();
			// First 5 letters
			for (int I = 0; I < 5; I++) {
				PanBuilder.append(Letters.charAt(RandomGenerator.nextInt(Letters.length())));
			}
			// Next 4 digits
			for (int I = 0; I < 4; I++) {
				PanBuilder.append(RandomGenerator.nextInt(10));
			}
			// Last letter
			PanBuilder.append(Letters.charAt(RandomGenerator.nextInt(Letters.length())));
			Pan = PanBuilder.toString();
		} while (GeneratedPANs.contains(Pan));
		GeneratedPANs.add(Pan);
		return Pan;
	}
	/// <summary>
	/// To Validate Whether Pan Card no Available, If Not add the Pan Card No
	/// Author-Vishnu Manoj K
	/// </summary>	
	public void AddOrVerifyPan() throws InterruptedException {
		// Open Edit Customer
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Purchase("customerName"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("customerDetailsView_cmdEditCustomer"));

		// Wait and scroll to ID proof type
		Thread.sleep(10000);
		base.ScrollToElementLocatorPresence(NormalSaleGoldAndSilverObj.Sel_DepositType("IDProofType"));

		// Get existing PAN
		String existingPAN = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("IDProofNumber"));

		// If empty, generate and enter new PAN
		String ContactActualNo ="";
		if (existingPAN == null || existingPAN.trim().isEmpty()) {
			String uniquePAN = this.GenerateUniquePAN();
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("IDProofNumber"), uniquePAN);
			base.buttonClick(LoginPageObj.Btn_SingnIn("cmdSaveCustomerDetails"));
			Thread.sleep(3000);
			if(base.isPresent(LoginPageObj.Edt_AlertText("This mobile number already exists with another customer!"))) {
				this.RetryGenerateNewPhoneNumber(ContactActualNo);
			}
			System.out.println("New PAN entered: " + uniquePAN);
		} else {
			System.out.println("PAN already exists: " + existingPAN);
		}

		// Navigate back
		Thread.sleep(3000);
		this.HamBurgerButtonClick("iconShop");
		Thread.sleep(8000);
	}
	/// <summary>
	/// Method for creating new customer with Or Without pan card
	/// Author-Sangeetha M S
	/// </summary>	
	public String CreateANewCustomerWithPan(String PanCardStatus) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		HamBurgerButtonClick("iconShop");

		//Click on add customer
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("addCustomerButton"));

		//Click on create customer
		Thread.sleep(3000);
		base.excuteJsClick(LoginPageObj.Btn_Login("Create new customer"));

		//Add all customer details
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("customerTypeInput"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("customerTypeInput"),UtilityTestData.CustomerType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("customerTitleOptions"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("customerTitleOptions"),UtilityTestData.CustomerTitleOptions);

		// Customer - FirstName - Last - Contact details 
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("customerFirstNameInput"),UtilityTestData.CustomerFirstName );
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("customerLastNameInput"),UtilityTestData.CustomerLastName );

		//Email Address 
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("customerContactEmailInput"),UtilityTestData.Email );

		//Contact Number 
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"),"70"+BasePge.CreateRandomNumber(8));
		String ContactActualNo = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Sel_DepositType("customerLanguageOptions"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("customerLanguageOptions"),UtilityTestData.CustomerLanguageOptions);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Sel_DepositType("RegionCode"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("RegionCode"),UtilityTestData.CustomerReligion);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PreferredLanguage"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("PreferredLanguage"),UtilityTestData.PreferredLanguage);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("ProfessionCode"),UtilityTestData.ProfessionCode);		
		base.scrollToElement(NormalSaleGoldAndSilverObj.Sel_DepositType("PreferredLanguage")); 
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("PreferredLanguage"),UtilityTestData.PreferredLanguage);

		// DOB
		base.scrollToElement(CustomerCreationObj.Ele_Dob("Date of birth", "win-datepicker-date win-dropdown win-order0 displayOrder1"));
		base.selectorByVisibleText(CustomerCreationObj.Ele_Dob("Date of birth", "win-datepicker-date win-dropdown win-order0 displayOrder1"),UtilityTestData.DobDate);
		base.selectorByVisibleText(CustomerCreationObj.Ele_Dob("Date of birth", "win-datepicker-month win-dropdown win-order1 displayOrder2"),UtilityTestData.DobMonth);		
		base.selectorByVisibleText(CustomerCreationObj.Ele_Dob("Date of birth", "win-datepicker-year win-dropdown win-order2 displayOrder3"),UtilityTestData.DobYear);

		//Pan Details
		if(PanCardStatus=="Yes")
		{
			String PANNumber = this.GenerateUniquePAN();
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("IDProofType"),UtilityTestData.IDProofType);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("IDProofNumber"),PANNumber);
			System.out.println("PAN NO"  +PANNumber);
		}

		//Actual AddressProof  
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("AddressProofType"),UtilityTestData.AddressProofType );
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("AddressProofNumber"),UtilityTestData.AadharNo );

		//Bank Details 
		base.scrollToElement(NormalSaleGoldAndSilverObj.Sel_DepositType("BankCode"));
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("BankCode"),UtilityTestData.BankName1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("BankAccNo"),UtilityTestData.BankAccNo );
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("IFSCCode"),UtilityTestData.IFSCCode );
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("Branch"),UtilityTestData.BranchName );

		// Click Add an address
		base.scrollToElement(LoginPageObj.Edt_AlertText("Add an address"));
		base.excuteJsClick(LoginPageObj.Edt_AlertText("Add an address"));
		Thread.sleep(1000);

		// Enter Zip code
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("zipTabRead"),UtilityTestData.PlaceZipCode);
		driver.findElement(By.id("zipTabRead")).sendKeys(Keys.ENTER);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Address());	
		Thread.sleep(1000);
		base.scrollToElement(LoginPageObj.Btn_SingnIn("cmdSaveCustomerDetails"));

		//Click the Save Button 
		Thread.sleep(5000);	
		base.ClickCondition(LoginPageObj.Btn_SingnIn("cmdSaveCustomerDetails"));
		Thread.sleep(3000);	
		String CustomerId=base.GetText(CustomerCreationObj.Ele_CustomerIDLabel("text: customerAccount"));
		System.out.println("Contact  No "+ContactActualNo);		
		return ContactActualNo;
	}


	/// <summary>
	/// To Generate new phone number if the phone number already exists during customer creation
	/// Author- Aiswarya
	/// </summary>	
	public String RetryGenerateNewPhoneNumber(String PhoneNumber) throws InterruptedException {
		String RandomPhoneNumber = "";
		String CustomerPhoneNumber = PhoneNumber;  
		Thread.sleep(3000);
		int attempt = 0;

		for (; attempt <= 5; attempt++) {
			try {
				if (base.isExists(LoginPageObj.Ele_ErrorMessage("h4 wrapTextWithoutHyphen wrapTextWithLineBreaks"))) {
					base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
					RandomPhoneNumber = "1" + (System.currentTimeMillis() % 1000000000L);
					Thread.sleep(2000);
					base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"));
					Thread.sleep(2000);
					base.setData(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"), RandomPhoneNumber);
					Thread.sleep(2000);
					CustomerPhoneNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("customerPhoneNumberInput"));
					base.excuteJsClick(LoginPageObj.Btn_SingnIn("cmdSaveCustomerDetails"));
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("New PhoneNumber Cannot be Saved. ");
			}
		}
		if (attempt > 5) {
			throw new RuntimeException("New Phone number not generated");
		}
		return CustomerPhoneNumber;
	}

	/// <summary>
	/// Method to get current date in MM/dd/yyyy format
	/// Author-Hasna EK & Benson
	/// </summary>	
	public static String getCurrentDate(String Format) {
		LocalDate date = LocalDate.now();
		String FormattedDate = date.format(DateTimeFormatter.ofPattern(Format));
		return FormattedDate;
	}

	/// <summary>
	///Method to Fetch current board rate
	/// Author- Christy Reji
	////// <summary>
	public double FetchCurrentBoardRate(String Purity) throws InterruptedException {

		// Search product
		Thread.sleep(5000);
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Edt_Name("Search"), 10);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		//base.waitForClickable((NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product)),40);
		this.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product),
				NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),5,2);
		Thread.sleep(1000);

		// Select purity / configuration
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),15);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), Purity);

		// Handle popup if any
		try {
			base.waitForClickable(LoginPageObj.Btn_SignInButton("OK"),15);
			base.UnifiedClick(LoginPageObj.Btn_SignInButton("OK"));
		} catch (Exception e) {
			// Ignore if no popup
		}

		// Add item
		base.waitForClickable(LoginPageObj.Btn_SignInButton("Add item"),15);
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Add item"));

		// Select N-Exchange
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));

		// Select QC Input
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),20);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"));
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"), 1);

		// Select SM Input
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),20);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"));
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"), 1);

		// Enter pieces and weight
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"), UtilityTestData.GrossPieces);

		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"), UtilityTestData.GrossWeight);

		// Click Calculate twice (POS UI behaviour)
		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"),15);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"),15);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));

		// Get Current Board Rate
		Thread.sleep(5000);
		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		Thread.sleep(5000);
		double CurrentGoldRate22k = Double.parseDouble(
				base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"))
				);

		System.out.println("CurrentGoldRate22k: " + CurrentGoldRate22k);

		// Handle invalid weight popup
		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_Payement("Invalid net weight value found"))) {
			base.waitForClickable(LoginPageObj.Btn_SignInButton("OK"),15);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		}

		return CurrentGoldRate22k;
	}


	/// <summary>
	/// This method can be used to fetch SKUs with gross weight less than a specific value,
	/// excluding restricted or predefined SKU lists.
	/// Author : Chandana Babu
	/// </summary>
	public RequiredSKUResult GetRequiredQuantitySKU(String TransferType, String FromCounter, String MetalType, double FirstQtySale1, int skuCount) throws InterruptedException {		 
		// Navigate to SKU counter transfer page
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase")); 
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);

		// Select From Counter 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search")); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//  Combine all SKUs to exclude (same as SelectMultipleSKUs)
		Set<String> ExcludedSkus = new HashSet<>();
		ExcludedSkus.addAll(Utility_TestData.GoldPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.DiamondPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.SilverPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.RestrictedSkus);
		ExcludedSkus.addAll(Utility_TestData.UncutVirazSkus);
		ExcludedSkus.addAll(Utility_TestData.KeralSkus);

		// Init data collections 
		List<String> SeenSKUs = new ArrayList<>(); 
		List<String> Quantities = new ArrayList<>();
		List<WebElement> AllSKUs = new ArrayList<>();
		List<String> RequiredQtySKUs = new ArrayList<>();	
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		int scrollPauseTime = 400;
		int maxTriesWithoutNew = 3;
		int triesWithoutNew = 0;

		while (RequiredQtySKUs.size() < skuCount && triesWithoutNew < maxTriesWithoutNew) {
			List<WebElement> CurrentRows = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-container"));
			int NewItems = 0;	
			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;	
				String[] Parts = RowText.split("\\n"); 
				if (Parts.length < 7) continue;

				String SKU = Parts[0].trim(); 
				String Quantity = Parts[6].trim();	

				//  Skip already seen or excluded SKUs
				if (SeenSKUs.contains(SKU) || ExcludedSkus.contains(SKU)) continue;

				SeenSKUs.add(SKU); 
				Quantities.add(Quantity); 
				AllSKUs.add(Row);	

				double QtyNum = 0;
				try { 
					QtyNum = Double.parseDouble(Quantity);
				} catch (NumberFormatException e) {
					continue;
				}

				// Include only SKUs where quantity < specified threshold
				if (QtyNum < FirstQtySale1) {
					RequiredQtySKUs.add(SKU);
					if (RequiredQtySKUs.size() >= skuCount) break;
				}	
				NewItems++;
			}

			if (NewItems == 0) triesWithoutNew++;
			else triesWithoutNew = 0;

			if (RequiredQtySKUs.size() >= skuCount) break;	
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);"); 
			Thread.sleep(scrollPauseTime);
		}	

		return new RequiredSKUResult(RequiredQtySKUs);
	}

	/// <summary>
	/// Class to hold SKU results
	/// </summary>
	public static class RequiredSKUResult {
		public List<String> RequiredQtySKUs;	
		public RequiredSKUResult(List<String> RequiredQtySKUs) {
			this.RequiredQtySKUs = RequiredQtySKUs;
		}
	}


	/// <summary>
	/// Method to counter out SKU in POS
	/// Author-Benson
	/// </summary>
	public String CounterOutSkuInPos(int SkuCount,String TransferType,String FromCounter,String MetalType,String StockTransferType) throws Exception {

		//Step 1: Click on Transaction button
		this.HamBurgerButtonClick("iconShop");

		//To extract a single Sku 
		String SkuValue=this.SelectMultipleSKUs(SkuCount,TransferType, FromCounter,MetalType).get(0);

		//Step 2: Go to Purchase
		// Navigate to SKU counter transfer page
		this.HamBurgerButtonClick("iconShop");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Purchase"))).click();

		//Step 3: Select counter transfer
		base.ClickCondition(LoginPageObj.Edt_AlertText("Counter Transfer"));

		//Step 4: Choose transfer type as stock out
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockTransferType);

		//Step 5: Select From counter and metal type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"),FromCounter);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		//Step 6: Enter SKU
		base.setData((CustomerCreationObj.Ele_Name("Product")), SkuValue);
		base.pressKey((CustomerCreationObj.Ele_Name("Product")), "ENTER");

		//Step 7: Click on commit button
		//Expected: Verify if validation appears saying SKU received successfully 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton", "YES")); 
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")),"User is not able to see the validation message: 'SKU received successfully' ");
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));
		return SkuValue;

	}

	// <summary>
	// To validate billing page and cashier screen
	// Author: Chandana Babu
	// </summary>
	public void ValidateBillingPgeAndCasierScreen(
			Map<String, String> TransactionDataLine,
			List<String> ProductNames,
			List<String> ProductQtyList,
			List<String> ProductTotalList,
			String PageName) throws Exception {

		System.out.println("\n--- Billing Page Verification Start (" + PageName + ") ---");

		List<WebElement> AllProductRows =
				base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));

		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		String ActualLinesCount = TransactionDataLine.get("LinesCount");

		System.out.printf(
				"%-22s | Expected: %-12s | Actual: %-12s | %s%n",
				"Lines Count",
				ExpectedLinesCount,
				ActualLinesCount,
				ExpectedLinesCount.equals(ActualLinesCount) ? " MATCH" : " MISMATCH"
				);

		asrt.assertEquals(
				ActualLinesCount,
				ExpectedLinesCount,
				"Lines count mismatch: Expected is " + ExpectedLinesCount +
				" but found " + ActualLinesCount + " in " + PageName + " page"
				);

		double SubTotalSum = 0.00;
		double ProductTotalAmount = 0.0;

		for (int i = 0; i < AllProductRows.size(); i++) {

			// ---------- ITEM NAME ----------
			List<WebElement> ItemName =
					base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));

			String ActualItemName = ItemName.get(i).getText();
			String ExpectedItemName;

			if (ProductNames.get(i).contains(ActualItemName)
					&& ActualItemName.length() < ProductNames.get(i).length()) {

				ExpectedItemName =
						ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();

				System.out.printf(
						"%-22s | Expected: %-12s | Actual: %-12s | %s%n",
						"Item Name",
						ExpectedItemName,
						ActualItemName,
						ExpectedItemName.contains(ActualItemName) ? " MATCH" : " MISMATCH"
						);

				asrt.assertTrue(
						ExpectedItemName.contains(ActualItemName),
						"Item name mismatch: Expected is " + ExpectedItemName +
						" but got " + ActualItemName + " in " + PageName + " page"
						);

				ProductTotalAmount += Double.parseDouble(ProductTotalList.get(i));

			} else {

				ExpectedItemName = ProductNames.get(i).trim();

				System.out.printf(
						"%-22s | Expected: %-12s | Actual: %-12s | %s%n",
						"Item Name",
						ExpectedItemName,
						ActualItemName,
						ExpectedItemName.equals(ActualItemName) ? " MATCH" : " MISMATCH"
						);

				asrt.assertEquals(
						ActualItemName,
						ExpectedItemName,
						"Item name mismatch: Expected is " + ExpectedItemName +
						" but got " + ActualItemName + " in " + PageName + " page"
						);
			}

			// ---------- QUANTITY ----------
			List<WebElement> Quantity =
					base.GetElement(NormalSaleGoldAndSilverObj
							.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));

			String ActualQuantity = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantity = ProductQtyList.get(i);

			double ActualQty = Double.parseDouble(ActualQuantity);
			double ExpectedQty = Double.parseDouble(ExpectedQuantity);

			System.out.printf(
					"%-22s | Expected: %-12.2f | Actual: %-12.2f | %s%n",
					"Quantity",
					ExpectedQty,
					ActualQty,
					Math.abs(ExpectedQty - ActualQty) <= 1.0 ? " MATCH" : " MISMATCH"
					);

			asrt.assertEquals(
					ActualQty,
					ExpectedQty,
					1.000,
					"Quantity mismatch: Expected is " + ExpectedQty +
					" but got " + ActualQty + " in " + PageName + " page"
					);

			// ---------- TOTAL WITHOUT TAX ----------
			List<WebElement> TotalWithoutTax =
					base.GetElement(NormalSaleGoldAndSilverObj
							.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));

			String TotalAmountWithoutTax =
					TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();

			String ActualTotalWithoutTax =
					TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();

			SubTotalSum += Double.parseDouble(TotalAmountWithoutTax);

			String ExpectedTotalWithoutTax =
					String.format("%.2f",
							Double.parseDouble(ProductTotalList.get(i)
									.replaceAll("[^\\d.]", "").trim()));

			System.out.printf(
					"%-22s | Expected: %-12.2f | Actual: %-12.2f | %s%n",
					"Total Without Tax",
					Double.parseDouble(ExpectedTotalWithoutTax),
					Double.parseDouble(ActualTotalWithoutTax.replace("-", "")),
					Math.abs(Double.parseDouble(ExpectedTotalWithoutTax)
							- Double.parseDouble(ActualTotalWithoutTax.replace("-", ""))) <= 1.0
							? " MATCH" : " MISMATCH"
					);

			asrt.assertEquals(
					Double.parseDouble(ActualTotalWithoutTax.replace("-", "")),
					Double.parseDouble(ExpectedTotalWithoutTax),
					1.0,
					"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTax +
					" but got " + ActualTotalWithoutTax + " in " + PageName + " page"
					);
		}

		// ---------- SUBTOTAL ----------
		String ExpectedSubTotal =
				String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();

		String ActualSubTotal =
				TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();

		System.out.printf(
				"%-22s | Expected: %-12.2f | Actual: %-12.2f | %s%n",
				"Subtotal",
				Double.parseDouble(ExpectedSubTotal),
				Double.parseDouble(ActualSubTotal),
				Math.abs(Double.parseDouble(ExpectedSubTotal)
						- Double.parseDouble(ActualSubTotal)) <= 1.0
						? " MATCH" : " MISMATCH"
				);

		asrt.assertEquals(
				Double.parseDouble(ActualSubTotal),
				Double.parseDouble(ExpectedSubTotal),
				1.0,
				"Subtotal mismatch: " + ExpectedSubTotal +
				" but found " + ActualSubTotal + " in " + PageName + " page"
				);

		// ---------- TAX ----------
		String TaxInPge =
				TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();

		double ActualTax = Double.parseDouble(TaxInPge);
		double ExpectedTax = ProductTotalAmount * 0.03;

		System.out.printf(
				"%-22s | Expected: %-12.2f | Actual: %-12.2f | %s%n",
				"Tax",
				ExpectedTax,
				ActualTax,
				Math.abs(ExpectedTax - ActualTax) <= 1.0 ? " MATCH" : " MISMATCH"
				);

		asrt.assertEquals(
				ActualTax,
				ExpectedTax,
				1.0,
				"Tax amount mismatch: " + ExpectedTax +
				" but found " + ActualTax + " in " + PageName + " page"
				);

		// ---------- TOTAL AMOUNT ----------
		double ExpectedTotalAmount = SubTotalSum + ActualTax;

		double ActualTotalAmount =
				Double.parseDouble(TransactionDataLine
						.get("TotalAmount")
						.replaceAll("[^\\d.-]", "")
						.trim());

		System.out.printf(
				"%-22s | Expected: %-12.2f | Actual: %-12.2f | %s%n",
				"Total Amount",
				ExpectedTotalAmount,
				ActualTotalAmount,
				Math.abs(ExpectedTotalAmount - ActualTotalAmount) <= 1.0
				? " MATCH" : " MISMATCH"
				);

		asrt.assertEquals(
				ActualTotalAmount,
				ExpectedTotalAmount,
				1.0,
				"Total Amount mismatch: " + ExpectedTotalAmount +
				" but found " + ActualTotalAmount + " in " + PageName + " page"
				);

		System.out.println("--- Billing Page Verification End (" + PageName + ") ---");
	}



	///<summary>
	/// Method for Sales Return flow(For Bulk Item)
	///Author : Nandagopan
	/// </summary>
	public void  SalesReturnFlowBulkItem(String CustomerID,String InvoiceNo,String Product) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		SearchByCustomerID(CustomerID,UtilityTestData.MenuBarOptions[0]);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Click on customer adjustment,return transaction,Receipt Number,Return Product,Return Button
		ReturnDetails Details = ReturnSingleProductWithDetails(InvoiceNo, Product); //Product taken from Your own testdata

		//Click on the Amount,Payment method,transaction type,due date, Nominee details,Deposit button	
		PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
				UtilityTestData.SalePersonNumber,
				UtilityTestData.DueYear,
				UtilityTestData.DobYear,
				UtilityTestData.NomineeName,
				UtilityTestData.NomineeRelation);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		Thread.sleep(1000);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));
		Thread.sleep(10000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Cart has changed"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		for (int i =0; i<2;i++) {
			if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_Payement("Error"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
		}
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		Thread.sleep(10000);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));
		Thread.sleep(10000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(5000);	
	}


	/// <summary>
	/// Method to return a single specified product for Bulk Item (e.g., Hook / HK)
	/// Author: Nandagopan 
	/// </summary>
	public ReturnDetails ReturnSingleProductWithDetails(String ReceiptNo, String ProductToReturn) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Navigate to Customer Adjustments
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		// Start Return Transaction
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Return transaction"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Btn_abc("numpad-control alphanumeric-mode flexCol", "Enable text editing"))).click();
		base.setData(NormalSaleGoldAndSilverObj.Edt_TextField("numpad-control alphanumeric-mode flexCol", "text"), ReceiptNo);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("numpad-control-buttons flexGrow100 flexRow", "content-return"));

		// Handle multiple store popup if appears
		try {
			if (base.isElementPresent(driver, NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames"))) {
				WebElement storeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames")));
				storeDropdown.click();
				base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionStoreNames"), 1);

				WebElement regDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionRegisterNumbers")));
				regDropdown.click();
				base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("multipleTransactionRegisterNumbers"), 1);

				wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Ele_ErrorMessage("buttonContainer col no-shrink halfWidth leftmostDialogButton marginTop20"))).click();
			}
		} catch (Exception e) {
			System.out.println("Popup handling skipped: " + e.getMessage());
		}

		// Wait and fetch the product elements
		Thread.sleep(4000);
		List<WebElement> productList = base.GetElement(NormalSaleGoldAndSilverObj.Ele_Lines("salesOrderLinesView", "enhancedGridDynamicRowTemplate ratio5"));
		List<WebElement> qtyList = base.GetElement(NormalSaleGoldAndSilverObj.Ele_Lines("salesOrderLinesView", "enhancedGridDynamicRowTemplate ratio1 textRight"));
		List<WebElement> priceList = base.GetElement(NormalSaleGoldAndSilverObj.Ele_Lines("salesOrderLinesView", "enhancedGridDynamicRowTemplate ratio2 textRight"));

		String returnedProduct = "";
		String returnedQty = "";
		String returnedPrice = "";

		// Loop through and find your target product (e.g., HK)
		for (int i = 0; i < productList.size(); i++) {
			String prodName = productList.get(i).getText().trim();

			if (prodName.equalsIgnoreCase(ProductToReturn) || prodName.toLowerCase().contains(ProductToReturn.toLowerCase())) {
				returnedProduct = prodName;
				returnedQty = qtyList.get(i).getText();
				returnedPrice = priceList.get(i).getText();

				// Click and return
				productList.get(i).click();
				Thread.sleep(2000);
				if(base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("OK"),20))
				{
					base.UnifiedClick(LoginPageObj.Btn_SignInButton("OK"));
				}
				base.UnifiedClick(NormalSaleGoldAndSilverObj.Btn_return("Return"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));

				System.out.println("âœ… Returned Product: " + returnedProduct + " | Qty: " + returnedQty + " | Price: " + returnedPrice);
				break;
			}
		}

		//		    if (returnedProduct.isEmpty()) {
		System.out.println("â�Œ Product '" + ProductToReturn + "' not found in Returnable Products.");


		return new ReturnDetails(returnedProduct, returnedQty, returnedPrice);
	}

	// Small helper class
	public class ReturnDetails1 {
		public String productName;
		public String productQty;
		public String productPrice;

		public ReturnDetails1(String productName, String productQty, String productPrice) {
			this.productName = productName;
			this.productQty = productQty;
			this.productPrice = productPrice;
		}

	}

	/// <summary>
	/// Method to Create customer offer advance with advance amount
	/// Author: Neethu
	/// </summary>
	public Map<String, String> CreateWeightBookedOfferAdvanceAmount(String CustomerId,String Pieces, String GrossWeight, String Company, String Location, double DepositePercentage) throws Exception  {	
		Map<String, String> Details = new HashMap<>();

		//Step 1: Login to POS
		//		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		//		base.setZoom(driver, 60);

		// Step 2: Navigate to the Transaction page
		this.HamBurgerButtonClick("iconShop");

		Details = this.CreateCustomerOfferAdvance(CustomerId,Pieces, GrossWeight,DepositePercentage, Company, Location);

		//Step 23: Go to transactions, Search customer, Click on Add to sale
		this.HamBurgerButtonClick("iconShop");
		this.SearchByCustomerID(CustomerId ,UtilityTestData.MenuBarOptions[0]);

		//Step 24: Choose advance-->advance collection
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"));
		Thread.sleep(3000);
		if(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}

		//Step 25: Choose type as order
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"), "Order");
		Thread.sleep(5000);

		//Step 26: Select the transaction number from the drop down
		String OrderId = Details.get("OrderId");
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), OrderId);

		//Step 27: Enter the deposit amount
		String AdvanceAmount = Details.get("AdvanceAmount");
		String WillingToPayAmount = Details.get("WillingToPayAmount");
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"), AdvanceAmount);

		System.out.println(WillingToPayAmount +" WillingToPayAmount");
		System.out.println(AdvanceAmount +" AdvanceAmount");

		//Step 28: Choose the sales person		
		//	base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), UtilityTestData.SalePersonName);
		Thread.sleep(3000);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));
		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);	

		//Step 29: Click on deposit button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));

		//Step 30: Click on the Amount
		Thread.sleep(3000);
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));

		//Step 31: Select a Payment method(Cash or Card)
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

		//Step 32: Post the Invoice
		Thread.sleep(5000);
		String NewPdfName = PdfUtilities.DownloadAndRenameLatestPDF("OfferAdvance");
		Map<String, String> IntialAdvanceReceiptPdf   = PdfUtilities.OfferAdvancePdfValidation();
		Details.put("AdvanceReceived",IntialAdvanceReceiptPdf.get("AdvanceReceived"));
		return Details;
	}	
	// <summary>
	// To Collect Skus that have Piece/ WastageQty Only/ MC only/ Special Skus
	// Author: Vishnu Manoj K
	// </summary>	

	public List<String> CollectValidSkuByVerfiy(
			int RequiredValidSkuCount,String TransferType,
			String FromCounter,
			String MetalType,
			int TargetSkuLimit,
			List<String> ValidationFields // Example: Arrays.asList("WastageQty", "MakingType")        
			) throws Exception {

		// Navigate to Counter Transfer
		this.HamBurgerButtonClick("iconShop");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_AlertText("Purchase"))).click();
		Thread.sleep(8000);
		System.out.println("wait over for CounterTransfer");
		wait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Edt_AlertText("Counter Transfer"))).click();

		// Select transfer criteria
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);

		Thread.sleep(3000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);

		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Prepare list of SKUs to exclude
		Set<String> ExcludedSkus = new HashSet<>();
		ExcludedSkus.addAll(Utility_TestData.GoldPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.DiamondPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.SilverPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.RestrictedSkus);
		ExcludedSkus.addAll(Utility_TestData.UncutVirazSkus);
		ExcludedSkus.addAll(Utility_TestData.KeralSkus);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<String> SeenSKUs = new ArrayList<>();
		Map<String, String> AllSkusData = new LinkedHashMap<>();
		List<String> ValidSkus = new ArrayList<>();

		int SameCount = 0;
		int ScrollPauseTime = 1500;

		System.out.println(" Starting SKU collection... Target: " + TargetSkuLimit);

		// Step 1: Scroll and collect SKUs
		while (true) {
			List<WebElement> Rows = driver.findElements(By.xpath("//div[@class='win-container']"));
			int NewItems = 0;

			for (WebElement Row : Rows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;

				String[] Parts = RowText.split("\\n");
				if (Parts.length < 6) continue;

				String Sku = Parts[0].trim();
				String ProductName = Parts[4].trim();
				String Quantity = Parts[6].trim();

				if (ExcludedSkus.contains(Sku)) {
					System.out.println(" Skipping excluded SKU: " + Sku);
					continue;
				}

				if (!SeenSKUs.contains(Sku)) {
					SeenSKUs.add(Sku);
					NewItems++;
				}
			}

			if (NewItems == 0)
				SameCount++;
			else
				SameCount = 0;

			if (SameCount >= 5 || SeenSKUs.size() >= TargetSkuLimit)
				break;

			try {
				WebElement ScrollableDiv = driver.findElement(By.cssSelector(".win-viewport"));
				js.executeScript("arguments[0].scrollBy(0, 2000);", ScrollableDiv);
			} catch (Exception e) {
				System.out.println(" Could not scroll further: " + e.getMessage());
				break;
			}

			Thread.sleep(ScrollPauseTime);
		}

		System.out.println(" Total SKUs collected: " + SeenSKUs.size());

		// Step 2: Validate SKUs
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (int i = 0; i < SeenSKUs.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SeenSKUs.get(i);

			this.HamBurgerButtonClick("iconShop");
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
			Thread.sleep(3000);

			base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), Sku);

			Wait.until(ExpectedConditions.presenceOfElementLocated(
					NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return")
					)).click();

			base.setZoom(driver, 40);
			Thread.sleep(8000);

			// Collect all field data
			AllSkusData.put("SKU_" + SkuNumber + "_GrossWeight", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_Purity", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("Purity"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_TotalCValue", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_MakingRate", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("makingRateInput"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_MakingValue", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("makingValueInput"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_MakingType", base.getTheSelectedTextInDropDown(NormalSaleGoldAndSilverObj.Sel_DepositType("makingTypeOptions")));
			AllSkusData.put("SKU_" + SkuNumber + "_WastageQty", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Wastage Qty"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_WastageAmount", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Wastage amount"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_ItemName", base.GetText(By.xpath("//h3[@data-bind='text: SectionHeader']")));
			AllSkusData.put("SKU_" + SkuNumber + "_Total", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_CvalueTextField", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"), "value"));
			AllSkusData.put("SKU_" + SkuNumber + "_RateTextField", base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"), "value"));

			// Validate fields
			Map<String, Boolean> FieldValidationResults = new LinkedHashMap<>();

			for (String Field : ValidationFields) {
				String Key = "SKU_" + SkuNumber + "_" + Field;
				String FieldValue = AllSkusData.get(Key);

				System.out.println(" Checking Key: " + Key + " -> Value: " + FieldValue);

				if (FieldValue != null && !FieldValue.trim().isEmpty()
						&& !FieldValue.equals("0") && !FieldValue.equals("0.0")
						&& !FieldValue.equalsIgnoreCase("None")) {
					System.out.println(" SKU " + Sku + " valid for " + Field + " = " + FieldValue);
					FieldValidationResults.put(Field, true);
				} else {
					System.out.println(" SKU " + Sku + " invalid for " + Field + " (value: '" + FieldValue + "')");
					FieldValidationResults.put(Field, false);
				}
			}

			// Decide if SKU is valid (any field valid)
			boolean IsValid = FieldValidationResults.containsValue(true);
			if (IsValid) {
				ValidSkus.add(Sku);
			}

			if (ValidSkus.size() >= RequiredValidSkuCount) {
				System.out.println(" Collected required valid SKUs (" + ValidSkus.size() + "). Stopping.");
				break;
			}
		}

		// Final summary
		if (!ValidSkus.isEmpty()) {
			System.out.println(" Total valid SKUs collected: " + ValidSkus.size());
			System.out.println(" Valid SKUs: " + ValidSkus);
		} else {
			System.out.println(" No valid SKUs found for fields: " + ValidationFields);
		}

		return ValidSkus;
	}

	///<Summary>
	///Method to select the products for BC
	///</Summary>
	public void SearchBCProduct(String Product,String Purity) throws Exception
	{
		base.buttonClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),Product);
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		Thread.sleep(2000);
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SignInButton("Products")),"The User is not able to view "+Product+" Product in Product Page");
		Thread.sleep(2000);

		base.actionClick(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Product(Product,"gridcell"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), Purity);

		WebDriverWait Alertwait = new WebDriverWait(driver, Duration.ofSeconds(7));
		try {
			//			if (base.isElementPresent(driver, By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"))) {
			if (Alertwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"))) != null) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

				base.excuteJsClick(By.cssSelector("button[data-ax-bubble='messageDialog_TemplatedDialogButton1'].primaryButton"));
				System.out.println("Popup appeared and OK button was clicked.");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			}
		}catch (Exception e) {System.out.println("Error Popup not found, skipping the close button action.");}

		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAddTo"));
	}

	// <summary>
	// Method to Open any GPP scheme 
	// Author: Chandana Babu
	// </summary>
	public String OpenANewGppScheme(String CustomerNo, String Scheme, String InstallmentAmount,String SchemeCode,String IdProof, String IdProofNo) throws Exception {
		//Go to GPP
		base.ClickCondition(LoginPageObj.Edt_AlertText("GPP"));
		//Select New GPP Account Opening
		WebDriverWait Wait =new WebDriverWait(driver, Duration.ofSeconds(50));
		Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Btn_return("New GPP Account")));
		Thread.sleep(2000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_return("New GPP Account"));
		//Search customer phone number
		//Add to GPP ac open
		this.SearchByCustomerID(CustomerNo,UtilityTestData.MenuBarOptions[6]);
		//Select scheme code
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("SchmTypeOptions"),Scheme);
		//Enter installment amount
		Thread.sleep(2000);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("InstlamtInput"));
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("InstlamtInput"), InstallmentAmount);
		//Select sales person
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"));
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"), 1);
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("SalesPersonInput"),UtilityTestData.SalePersonNameWithNo);
		Thread.sleep(3000);
		String IdProofNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("Idproofno1"));
		if (IdProofNumber == null || IdProofNumber.trim().isEmpty()) {
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("IdTypeOptions"), IdProof);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("Idproofno1"), IdProofNo);
		}

		//Select bank proof type
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("BankProof"),UtilityTestData.BankProof);
		//Enter nominee name
		base.setData(GppOpeningAndCollectionMultiplePlansObj.Sel_GPPNomineeName("NomineeName"),UtilityTestData.NomineeName);
		//Select nominee relation
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("NomineeRelation"),UtilityTestData.NomineeRelation);
		//Click on check box( same as above)
		base.ClickCondition(GppOpeningAndCollectionMultiplePlansObj.Sel_GPPNomineeName("IsSameAsAbove"));

		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"), UtilityTestData.DobYear);

		//Click on save button
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_return("View more"));
		Thread.sleep(2000);
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("SearchView_AppBarView_appBarCommandCommand"));	
		Thread.sleep(5000);
		Map<String, String> PdfDetails = PdfUtilities.ExtractDetailsFromGPPPdf(SchemeCode);
		String ApplicationIdPdf   = PdfDetails.get("ApplicationID");
		return ApplicationIdPdf;
	}

	/// <summary>
	/// This method can be used to fetch SKUs with gross weight in between a specific value,
	/// excluding restricted or predefined SKU lists.
	/// Author : Robin
	/// </summary>
	public RequiredSKUResult GetRequiredQuantitySKUInRange(String TransferType, String FromCounter, String MetalType, double FirstQtySale1, double FirstQtySale2, int skuCount) throws InterruptedException {		
		// Navigate to SKU counter transfer page
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Select Transfer Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), TransferType);

		// Select From Counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), FromCounter);

		// Select Metal Type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType")));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), MetalType);

		// Navigate to Product Search
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//  Combine all SKUs to exclude (same as SelectMultipleSKUs)
		Set<String> ExcludedSkus = new HashSet<>();
		ExcludedSkus.addAll(Utility_TestData.GoldPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.DiamondPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.SilverPieceRateSkus);
		ExcludedSkus.addAll(Utility_TestData.RestrictedSkus);
		ExcludedSkus.addAll(Utility_TestData.UncutVirazSkus);
		ExcludedSkus.addAll(Utility_TestData.KeralSkus);

		// Init data collections
		List<String> SeenSKUs = new ArrayList<>();
		List<String> Quantities = new ArrayList<>();
		List<WebElement> AllSKUs = new ArrayList<>();
		List<String> RequiredQtySKUs = new ArrayList<>();	
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		int scrollPauseTime = 400;
		int maxTriesWithoutNew = 3;
		int triesWithoutNew = 0;

		while (RequiredQtySKUs.size() < skuCount && triesWithoutNew < maxTriesWithoutNew) {
			List<WebElement> CurrentRows = driver.findElements(NormalSaleGoldAndSilverObj.Btn_AddtoCart("win-container"));
			int NewItems = 0;	
			for (WebElement Row : CurrentRows) {
				String RowText = Row.getText().trim();
				if (RowText.isEmpty()) continue;	
				String[] Parts = RowText.split("\\n");
				if (Parts.length < 7) continue;

				String SKU = Parts[0].trim();
				String Quantity = Parts[6].trim();	

				//  Skip already seen or excluded SKUs
				if (SeenSKUs.contains(SKU) || ExcludedSkus.contains(SKU)) continue;

				SeenSKUs.add(SKU);
				Quantities.add(Quantity);
				AllSKUs.add(Row);	

				double QtyNum = 0;
				try {
					QtyNum = Double.parseDouble(Quantity);
				} catch (NumberFormatException e) {
					continue;
				}

				// Include only SKUs where quantity < specified threshold
				if (QtyNum > FirstQtySale1 && QtyNum < FirstQtySale2 ) {
					RequiredQtySKUs.add(SKU);
					if (RequiredQtySKUs.size() >= skuCount) break;
				}	
				NewItems++;
			}

			if (NewItems == 0) triesWithoutNew++;
			else triesWithoutNew = 0;

			if (RequiredQtySKUs.size() >= skuCount) break;	
			js.executeScript("document.querySelector('.win-viewport').scrollBy(0, 2500);");
			Thread.sleep(scrollPauseTime);
		}	

		return new RequiredSKUResult(RequiredQtySKUs);
	}

	///<summary>
	/// Method to get required pcs rate items from list
	///Author : Chandana Babu
	/// </summary>
	public List<String> GetSpecificSkuPurityFromList(List<String> SKUs,String Purity,int count) throws InterruptedException
	{
		List<String> RequiredPcsRateSKUs = new ArrayList<>();
		if (SKUs.isEmpty()) {
			System.out.println("SKU List is empty - exiting test early");
		} else {

			//for (int i = 0; i < SKUs.size(); i++) {
			for (int i = SKUs.size()-1; i>=0; i--) {
				this.HamBurgerButtonClick("iconShop");

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
				Thread.sleep(3000);
				base.setDataWithoutClear(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SKUs.get(i));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));
				if(base.WaitForElementToBePresentWithTimeouts(LoginPageObj.Btn_SignInButton("OK"), 7)) {
					base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
					continue;
				}				
				String ActualPurity = base.GetValue(CustomerCreationObj.Ele_Name("Purity"));					
				System.out.println(ActualPurity);
				if (ActualPurity.equals(Purity)) {
					RequiredPcsRateSKUs.add(SKUs.get(i));
				}
				if (RequiredPcsRateSKUs.size()== count) {
					this.HamBurgerButtonClick("iconShop");
					break;
				}
			}
		}
		return RequiredPcsRateSKUs;
	}

	// <summary>
	// Method to create a BULK Item HK/BC and return invoice number 
	// Author: Christy Reji
	// </summary>
	public String GetInvoiceNoBulkSale(String CustomerNo,String BulkProduct,String GrossWt) throws Exception {
		//Step 1 :Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// 2. Click on Transaction button
		HamBurgerButtonClick("iconShop");

		// 3. Select customer		
		// 4.Click on add to estimate button
		SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[5]);

		// 5.Search HK, select Hook
		Thread.sleep(5000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Edt_Name("Search"),BulkProduct);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		// 6.Select configuration, and click on Add item		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",BulkProduct));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);				
		WebDriverWait Wait  = new WebDriverWait(driver, Duration.ofSeconds(8));
		// Handle popup if any
		try {
			base.waitForClickable(LoginPageObj.Btn_SignInButton("OK"),15);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		} catch (Exception e) {
			// Ignore if no popup
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(3000);		
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),GrossWt);		
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(2000);
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Add to Cart"));	

		//Sale rep
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(5000);

		// 10. Select estimate and click on save to estimate button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));			
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(2000);
		String ProFormaInvoiceName = PdfUtilities.DownloadAndRenameLatestPDF("Bulk_ProformaInvoice");

		// 11.Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),"Sales");

		// 12.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		String PaymentAmount = PaymentAfterRecallEstimate("HDFC");

		// 16.Post the Invoice
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(2000);

		String FinalInvoice   = PdfUtilities.DownloadAndRenameLatestPDF("Bulk_FinalSaleInvoice");
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String InvoiceNo      = PdfUtilities.ExtractReceiptNoFrmSaleInvoice(InvoicePdfPath);

		System.out.println("BulkSale completed with invoice number : "+InvoiceNo);
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));

		return InvoiceNo;
	}

	/// <summary>
	/// Method to validate the selected payment after recalling an estimate.Method includes validations for approval code, card number, and the 'Change Due' subpage.
	/////Author-Christy Reji 
	/// </summary>
	public String PaymentAfterRecallEstimate(String PaymentMethod) throws InterruptedException {
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Thread.sleep(5000);
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PaymentMethod), 10);		
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod(PaymentMethod));
		Thread.sleep(3000);

		String PaymentAmount = base.GetAttribte(CustomerCreationObj.Ele_ERPCustid("Payment amount"),"value");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("paymentView fragment", "iconReturnKey"));
		Thread.sleep(3000);

		if (PaymentMethod.equals("HDFC (Credit Card)") || PaymentMethod.equals("ICICI")||PaymentMethod.equals("HDFC")) {
			// ApprCode-validation
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.ApprCode);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));

			// CardNumber-validation
			Thread.sleep(5000);     
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("textInputDialogContent"), UtilityTestData.CardNumber);
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			if(base.isPresent(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("email"))){
				base.ClickCondition(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("cancelButton"));
			}
			ChangeDueSubpageValidation();
		} else if (PaymentMethod.equals("Cash")) {
			ChangeDueSubpageValidation();
		} else {
			asrt.fail("User is unable to select the payment method after recalling an estimate.");
		}

		return PaymentAmount;
	}

	/// <summary>
	/// Method to Create Normal Advance Cash Mop
	/// Author: Chandana Babu
	/// </summary>
	public String CreateNormalAdvanceCashMop(String CustomerNo, String AdvanceAmount, String SalesPerson, String DueYear) throws Exception {


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));

		//Select customer and  click on add to sale button
		Thread.sleep(1000);
		this.SearchByCustomerID(CustomerNo, UtilityTestData.MenuBarOptions[0]);
		Thread.sleep(3000);
		//Click on advance button at the transaction screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"));
		//Click on advance collection button
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance Collection"));
		Thread.sleep(3000);
		if(base.isPresent(LoginPageObj.Btn_SingnIn("Button1Close")))
		{
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}
		Thread.sleep(3000);
		//select deposit type as normal
		//Enter deposit amount
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"),AdvanceAmount);
		//Select sales person
		base.ClickCondition(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByIndex(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("value: SALESMAN"),1);
		//Select due date
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_DrpdwnYear("win-datepicker-year win-dropdown win-order2 displayOrder3"), DueYear);
		//Enter remarks
		base.setData(NormalSaleGoldAndSilverObj.Ele_Remarks("6"),UtilityTestData.Remarks);
		//Add nominee details
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Add Nominee Details"));
		Thread.sleep(1000);
		//Enter nominee name
		//Select nominee relation
		//Click on check box(Same as customer address)					
		//Click on OK button
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Nominee name"), UtilityTestData.NomineeName);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Nominee relation"), UtilityTestData.NomineeRelation);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NomineeChkBox"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_NomineeDOBYear("win-datepicker-year"),UtilityTestData.DobYear);
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		//Click on deposit button
		base.scrollToElement(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));
		Thread.sleep(3000);
		//Click on amount
		//select a Payment method(Cash or Card)
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("Close")));
		if(base.isPresent(LoginPageObj.Btn_SignInButton("Close"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close"));
		}
		return AdvanceAmount;
	}

	///<summary>
	/// Method to Create customer offer advance with weight booked
	/// Take collection as Cashmop
	/// Author: Aiswarya S
	/// </summary>
	public Map<String, String> OfferAdvanceParentCashmop(String CustomerId, String Pieces, String GrossWeight, 
			String Company, String Location, double DepositePercentage) throws Exception {

		Map<String, String> Details = new HashMap<>();

		// Step 1: Navigate to the Transaction page
		this.HamBurgerButtonClick("iconShop");

		Details = this.CreateCustomerOfferAdvance(CustomerId, Pieces, GrossWeight, DepositePercentage, Company, Location);


		// Step 23: Go to transactions, Search customer, Click on Add to sale
		this.HamBurgerButtonClick("iconShop");
		this.SearchByCustomerID(CustomerId, UtilityTestData.MenuBarOptions[0]);

		// Step 24: Choose advance --> advance collection
		base.waitForClickable(LoginPageObj.Edt_AlertText("Advance"),15);
		base.buttonClick(LoginPageObj.Edt_AlertText("Advance"));

		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"),15);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Advance Collection"));

		if (base.isExists(LoginPageObj.Btn_SingnIn("Button1Close"))) {
			base.waitForClickable(LoginPageObj.Btn_SingnIn("Button1Close"),15);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1Close"));
		}

		// Step 25: Choose type as Order
		base.waitForVisible(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("OrderTypeOptions"), "Order");

		// Step 26: Select the transaction number from the drop down
		String OrderId = Details.get("OrderId");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"));
		base.waitForVisible(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"));
		Thread.sleep(5000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransTypeOptions"), OrderId);

		// Step 27: Enter the deposit amount
		String AdvanceAmount = Details.get("AdvanceAmount");
		String WillingToPayAmount = Details.get("WillingToPayAmount");

		base.waitForVisible(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("DepAmount"), WillingToPayAmount);

		System.out.println(WillingToPayAmount + " WillingToPayAmount");
		System.out.println(AdvanceAmount + " AdvanceAmount");

		// Step 28: Choose the sales person
		base.waitForClickable(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"),15);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"));

		base.selectorByIndex(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: ArrSALESMAN"), 1);

		// Step 29: Click on deposit button
		base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"),15);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Deposit"));

		// Step 30: Click on the Amount
		base.waitForClickable(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"),15);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_AmountDue("Amount due"));
		Thread.sleep(5000);

		// PaymentMethod  Cash
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_PaymentMethod("Cash"));
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ClearButton("Clear"));
		base.actionSetData(CustomerCreationObj.Ele_ERPCustid("Payment amount"),String.valueOf(WillingToPayAmount));
		base.actionClick(NormalSaleGoldAndSilverObj.Btn_Estimation("flexGrow100 accentBackground enter"));
		Thread.sleep(1000);

		// Close deposit popup
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Close"));

		// Step 32: Post the Invoice
		String NewPdfName = PdfUtilities.DownloadAndRenameLatestPDF("OfferAdvanceParentCollection");

		Map<String, String> IntialAdvanceReceiptPdf = PdfUtilities.OfferAdvancePdfValidation();
		Details.put("AdvanceReceived", IntialAdvanceReceiptPdf.get("AdvanceReceived"));

		return Details;
	}



	//		public void HamBurgerButtonClickShop(String NavigationIcons) throws InterruptedException
	//		{
	//			System.out.println("Clicking on Hamburger Shop Menu");
	//			//base.setZoom(driver, 30);
	//			Thread.sleep(45000);
	//			  if(base.isPresent(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("headerHighContrastBorderThin flexRow"))) {
	//			this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"), 5, 2);
	//			  }
	//			  else
	//			  {
	//				  base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("headerSplitViewToggleButton iconGlobalNavButton win-splitviewpanetoggle"));
	//				  this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("text semilight primaryFontColor","Advance"), 5, 2);
	//
	//			  }
	//		}
	//		public void HamBurgerButtonClickHome(String NavigationIcons) throws InterruptedException
	//		{
	//			System.out.println("Clicking on Hamburger Home Menu");
	////			if(base.isPresent(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("headerHighContrastBorderThin flexRow"))) {
	////			this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("h2","Unlock Adjustments"), 5, 2);
	////			}
	////			  else
	////			  {
	////				  base.buttonClick(GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUserObj.Btn_HamburgerReset("headerSplitViewToggleButton iconGlobalNavButton win-splitviewpanetoggle"));
	////				  this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("h2","Unlock Adjustments"), 5, 2);
	////					
	////			  }
	//			this.ClickWithRetry(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku(NavigationIcons), NormalSaleGoldAndSilverObj.Btn_Transaction("h2","Unlock Adjustments"), 5, 2);
	//		}

	// <summary>
	// Utility class for calculating denomination quantities
	// Author: Vishnu Manoj K
	// </summary>
	public Map<Integer, Integer> DenominationUtil(int Amount) {

		Map<Integer, Integer> DenominationMap = new LinkedHashMap<>();

		int RemainingAmount = Amount;

		int[] Denominations = {500, 200, 100, 50, 20, 10, 5, 2, 1};

		for (int Denomination : Denominations) {
			int Quantity = RemainingAmount / Denomination;
			RemainingAmount = RemainingAmount % Denomination;
			DenominationMap.put(Denomination, Quantity);
		}

		return DenominationMap;
	}
	// <summary>
	// Utility method to get adjusted amount based on last three digits
	// Author: Vishnu Manoj K
	// </summary>
	public double GetAdjustedAmount(double Amount) {

		// 🔹 Rule 1: If input is 0, return 0
		if (Amount == 0) {
			return 0;
		}

		long IntegerPart = (long) Amount;
		double DecimalPart = Amount - IntegerPart;

		long LastThreeDigits = IntegerPart % 1000;

		// 🔹 Normal case
		if (LastThreeDigits > 0) {
			return LastThreeDigits + DecimalPart;
		}

		// 🔹 Fallback when last three digits are 0
		if (IntegerPart >= 2000) {
			return 500 + DecimalPart;
		} else if (IntegerPart >= 1000) {
			return 100 + DecimalPart;
		} else if (IntegerPart >= 100) {
			return 10 + DecimalPart;
		} else {
			return 1 + DecimalPart;
		}
	}
}

