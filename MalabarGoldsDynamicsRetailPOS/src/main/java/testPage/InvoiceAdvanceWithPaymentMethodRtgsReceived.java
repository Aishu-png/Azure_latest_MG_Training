package testPage;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceRepayment_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import testData.AdvanceRepayment_TestData;
import testData.CommonData;
import testData.InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;
import utilPack.AppUtilities.SKUResult;
import utilPack.AppUtilities.SKUTypeResult;

public class InvoiceAdvanceWithPaymentMethodRtgsReceived extends BasePge{

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
	AdvanceRepayment_Obj AdvanceRepaymentObj = new AdvanceRepayment_Obj();
	AdvanceRepayment_TestData  AdvanceRepaymentTestData = new AdvanceRepayment_TestData();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj = new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	WebDriverWait wait;

	public InvoiceAdvanceWithPaymentMethodRtgsReceived(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}

	//<summary>
	// Test Case Title : Invoice with RTGS Received
	// Automation ID : TC251
	// Author : Benson 
	// </summary>
	public void TC251_InvoiceAdvanceWithPaymentMethodRtgsReceived() throws Exception{


		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre requisite                                                    
		//To create a new RTGS account:
		//1.Go to ERP of specific legal entity and search RTGS payment receipt
		//2.Click on New
		//3.Enter Entry date
		//4.Enter UTR number
		//5.Enter Amount 
		//6.Provide Bank Account
		//7.Enter Remarks
		// 8.Enter Cost center
		// 9.Enter Company ID
		//10.Enter Customer Account
		//11.Select Credit date
		//12.Enter User ID
		//13.Click on Post

		//RTGS ACCOUNT CREATION IN ERP 
		Map<String, String> RtgsDetails= erpUtils.RtgsAccountCreation(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_StringAmountToDeposit, InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_CustomerId);	

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_CustomerId, UtilityTestData.MenuBarOptions[5]);
		//warning message click ok 
		//base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));

		//Step 5: Scan Multiple SKU's and click on Add to cart button
		//Expected Result: Check calculation
		// Step 6 : Select any one of the sales agent
		List<String> SkuList = appUtils.SelectMultipleSKUs(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_SkuGoldCount,InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_TransferType,InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_FromCounter,InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_MetalType);
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			// Step 1: Extract & store table values
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			// Step 2: Filter for this specific SKU
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			// Step 3: Validate calculated values
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		//TranscationPageVerifyLines
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


		// Step 7: Click on Save to estimation button
		// Step 8: Save Esimate
		// Expected Result:Verify the invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = SubTotal;
		String Gst                 = Tax;
		String InvoiceTotal        = TotalAmount;
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC251_ProFormaInvoice");
		String ProFormaInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoice,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight)
				);

		//Step 9 : Recall estimate from cashier screen
		//Step 10 : Select estimate and click on recall estimate button
		//Expected : Verify whether the recalled item is the same as the item in the cart.
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

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page"); 

		// Step 11 : Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_TransactionType);

		// Step 12 : Click on Process Estimation
		// Step 13 : Select any Sales Representative
		// Expected Result:Cashier Screen after recall *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		base.setZoom(driver, 60);
		Thread.sleep(3000);

		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(1000);
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		// Step 14 :  Click on the Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// Step 15 :  Select a Payment method(RTGS/Received)
		base.buttonClick(LoginPageObj.Edt_AlertText(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_PaymentMethod));

		//Step15.1 Enter the RTGS amount
		String AmountDue=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("text","AmountDue"));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"), AmountDue);
		String RtgsAmount=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"));

		double RtgsValue = Double.parseDouble(RtgsAmount);
		// Round off to 2 decimal places
		double RoundedRtgsValue = Math.round(RtgsValue);
		// Convert back to String with ".00" format
		String RtgsFinalAmount = String.format("%.2f", RoundedRtgsValue);  //FOR STEP 16 
		double RtgsAmountReceived = Double.parseDouble(RtgsAmount);   //SAME FOR STEP 16 SOMETIMES FOR KALPETTA IF ROUND OFF NOT NEEDED THERE
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1")));

		//15.2.Enter the UTR number
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"),RtgsDetails.get("UtrNumber"));
		Thread.sleep(4000);
		String BalanceBeforePayments=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","RTGSBalance"));
		double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayments);//Expected Balance before payment in POS
		String ActualBalanceBeforePaymentInErp=RtgsDetails.get("RtgsBalanceAmntBeforePayment").replace(",",""); //Expected Balance Before Payment in ERP
		double ActualBalanceBeforePayment=Double.parseDouble(ActualBalanceBeforePaymentInErp);
		double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - RtgsAmountReceived;
		ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0; //For rounding off to 2 decimal places

		//15.3.Click on "Apply" then "Ok"
		base.buttonClick(LoginPageObj.Btn_SingnIn("applybtn"));  //click on apply
		wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1"));   //click on ok

		// Step 16:  Post the Invoice
		// Expected Result: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")); 
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC251_TaxInvoice");
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName + "";
		String GrossAmount      = SubTotal;
		String TotalValue       = InvoiceTotal;
		String TotalQtyPcs      = LinesCount;
		String TotalDiscount    = Discount;
		String NetValue = String.format("%.2f", Double.parseDouble(NetTotal.trim().replaceAll("[^\\d.]", "")));

		// Round off after that
		double rounded = Math.round(Double.parseDouble(NetValue));
		String RoundedNetValue = String.format("%.2f", rounded); //FOR VIJAYAWADA MAINLY SOMETIMES WE CAN USE NETVALUE FOR KALPETTA

		//Note Sometimes in Kalpetta rounded values not required for assertion  in.., vijayawada terminal rounded values are needed
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationWithRtgsReceived(
				TaxInvoicePath,
				GrossAmount,
				TotalDiscount,    
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				RtgsAmount,
				SkuList,
				ScannedSkuDataMap );

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

		//ERP:
		//1.Go to ERP and search RTGS payment receipt details
		//2.Filter with UTR number/Customer account number
		//3.Check the 'Balance Amount' Before and after Payment
		erpUtils.ValidateRTGSPaymentBalance(
				RtgsDetails.get("UtrNumber"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_CustomerId,
				ExpectedBalanceBeforePayment,
				ActualBalanceBeforePayment,
				ExpectedBalanceAfterPayment
				);
	}

	//<summary>
		// Test Case Title : Normal advance with RTGS received
		// Automation ID : TC249
		// Author : Hasna E K
		// </summary>
		public void TC249_InvoiceAdvanceWithPaymentMethodRtgsReceived() throws Exception {

			//Step 1: Login to POS
			login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
			base.setZoom(driver, 60);

			//Step 2: Click on Transaction button
			appUtils.HamBurgerButtonClick("iconShop");
			// for creating normal advance
			String AdvanceAmount = appUtils.CreateNormalAdvance(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_CustomerNo, 
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_AdvanceAmount,
					UtilityTestData.DobYear, 
					UtilityTestData.DueYear);

			// For fetching BoardRate
			Thread.sleep(3000);
			base.isPresent(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			Thread.sleep(5000);
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
			base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));
			Thread.sleep(1000);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_Purity);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Thread.sleep(3000);		
			if(base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
			base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
			Thread.sleep(3000);
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
			Thread.sleep(3000);
			base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_GrossPieces);
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_GrossWeight);
			base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
			Thread.sleep(3000);
			String GoldRate22k = GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
			double ExpectedGoldRate22k = Double.parseDouble(GoldRate22k);
			System.out.println("GoldRate22k"+ExpectedGoldRate22k);
			String Rate = BigDecimal.valueOf(ExpectedGoldRate22k).stripTrailingZeros().toPlainString();

			// Pre-Requisite: Change metal rate.
			String SkuPurity = InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_Purity;
			String ErpGoldRateFor24K = erpUtils.SetMetalRateInERP(GoldRate22k, SkuPurity);
			System.out.println("ErpGoldRateFor24K " + ErpGoldRateFor24K);
			TimeUnit.MINUTES.sleep(4);

			// Pre-Requisite:2. RTGS account creation
			Map<String, String> RtgsAccountDetails = erpUtils.RtgsAccountCreation(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_AmountToDeposit, 
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_CustomerId
					);
			String RtgsBalanceAmnt = RtgsAccountDetails.get("RtgsBalanceAmntBeforePayment").replace(",", "");
			String UtrNumber = RtgsAccountDetails.get("UtrNumber");
			if(base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			// Step 3: Select customer
			// Step 4: Click on add to estimation
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
			appUtils.SearchByCustomerID(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_CustomerNo, UtilityTestData.MenuBarOptions[5]);

			//Step 5: Click on customer adjustment button
			Thread.sleep(5000);		
			base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

			//Step 6: Click on normal advance settlement
			//Step 7: Select advance and Click on redeem button
			//Expected Result: The balance amount and adjustment amount for the selected advance is shown correctly.

			WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			base.waitForClickable(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"), 30);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"));
			Thread.sleep(1000);
			String MetalRateFetch = "";
			double TotalBalance = 0.0;
			double Adjustment = 0.0;

			By TableContainer = AdvanceRepaymentObj.Ele_Scroll(".win-vertical");
			JavascriptExecutor JsExecutor = (JavascriptExecutor) driver;
			WebElement Container = driver.findElement(TableContainer);
			JsExecutor.executeScript("arguments[0].scrollTop = 0;", Container);
			Thread.sleep(500);

			boolean Found = false;
			int LastCount = -1;
			String CleanAmt = AdvanceAmount.replaceAll(",", "").replaceAll("\\.00$", "");

			// Combined XPath for Rate + Deposit
			//	String CombinedXPath ="//div[contains(@class,'h4 ellipsis cell') and normalize-space(text())='" +Current22k.trim() +"']/ancestor::div[contains(@aria-label,'" + CleanAmt + "')]";
			By CombinedXPath = AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.NormalAdvance(GoldRate22k,CleanAmt);
			while (!Found) {
				List<WebElement> MatchingRows = driver.findElements(CombinedXPath);
				for (int Index = 0; Index < MatchingRows.size(); Index++) {
					try {
						WebElement DepositBtn = MatchingRows.get(Index);

						// Scroll element into center of container
						JsExecutor.executeScript(
								"arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
								DepositBtn
								);
						Thread.sleep(300);
						try {
							DepositBtn.click();
						} catch (Exception Exception) {
							JsExecutor.executeScript("arguments[0].click();", DepositBtn);
						}
						Found = true;
						break;

					} catch (StaleElementReferenceException Exception) {
						MatchingRows = driver.findElements(CombinedXPath);
						Index--; 
					} catch (Exception Exception) {

					}
				}

				if (Found) break;
				JsExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].clientHeight;",Container);
				Thread.sleep(600);
				//int CurrentCount = driver.findElements(By.xpath("//div[contains(@class,'h4 ellipsis cell')]")).size();
				int CurrentCount = driver.findElements(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("h4 ellipsis cell")).size();
				if (CurrentCount == LastCount) {
					break;
				}
				LastCount = CurrentCount;
			}

			Thread.sleep(3000);
			String BalanceAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
			String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));
			Thread.sleep(5000);		       
			TotalBalance = Double.parseDouble(BalanceAmount);
			Adjustment = Double.parseDouble(AdjustmentAmount);
			double Quantity = Double.parseDouble(driver.findElement(By.xpath("//div[@class='win-itembox win-selected']/descendant::div[contains(@aria-label,'Gold booked') and @class='h4 ellipsis cell']")).getText());
	//-----------------------------------------------------------------------------------------------------------------		
			
			asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

			//Step 8: Enter OTP(Disabled)
			//Step 9: Scan SKU and add to cart
			// Note: Check average rate
			//Expected result: .Check calculation, Note:Billing Screen,*No of Product lines, *Displayed Item Name, *Displayed Quantity, *Displayed TOTAL (without Tax), *Displayed Subtotal,*TAX, *Total Amount
			SKUResult SkuOGSale = appUtils.GetOGSaleSku(
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_TransferType,
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_FromCounter,
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_MetalType,
					Quantity,
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount);

			List<String> SkuList = SkuOGSale.PcsRateSKU;
			appUtils.HamBurgerButtonClick("iconShop");
			erpUtils.ResetRemainingReturnWeight();
			Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
			List<String> ProductNames = new ArrayList<>();
			List<String> ProductQtyList = new ArrayList<>();
			List<String> ProductTotalList = new ArrayList<>();
			ProductNames.add(InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData.TC249_DepositAdjustment);
			ProductQtyList.add(InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData.TC249_DepositAdjustmentQty);
			ProductTotalList.add(AdjustmentAmount);

			appUtils.SKUIngridentTableValues(SkuList.get(0),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount,ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount,String.valueOf(Quantity),"22k",GoldRate22k,ErpGoldRateFor24K);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount + "_Total"));
			String RateKey = "SKU"+ InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount+ "_ITEM"+ InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount+ "_RATE";
			String BoardRateFromSkuTble = ScannedSkuDataMap.get(RateKey);
			Double SkuBoardRate = Double.parseDouble(BoardRateFromSkuTble);
			String Purity = ScannedSkuDataMap.get("SKU_"+ InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount+ "_Purity");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
			String SubTotal = TransactionDataLine.get("Subtotal");
			String NetTotal = TransactionDataLine.get("NetTotal");
			String Discount = TransactionDataLine.get("Discount");
			String TotalNetWeight = TransactionDataLine.get("NetWeight");
			String Tax = TransactionDataLine.get("Tax");
			String TotalAmount = TransactionDataLine.get("TotalAmount");
			String ActualLinesCount = TransactionDataLine.get("LinesCount");
			List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
			String ExpectedLinesCount = Integer.toString(AllProductRows.size());
			asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

			double SubTotalSum = 0.00;
			for (int i = 0; i < AllProductRows.size(); i++) {
				List<WebElement> ItemName = base
						.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
				String ActualItemNameInBillingPge = ItemName.get(i).getText();
				//			if (i == 1) {
				//				String ExpectedItemNameInBillingPge = ProductNames.get(i)
				//						.substring(ProductNames.get(i).indexOf("-") + 1).trim();
				//				asrt.assertEquals(ActualItemNameInBillingPge, ExpectedItemNameInBillingPge,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");
				//			} else 

				String ExpectedItemNameInBillingPge = ProductNames.get(i).trim();
				asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is " + ExpectedItemNameInBillingPge + " but got "+ ActualItemNameInBillingPge + " in billing page");

				List<WebElement> QuantityInBillingPage = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
				String ActualQuantityInBillingPge = QuantityInBillingPage.get(i).getText().replace("-", "");
				String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
				double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
				double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
				asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.00, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

				List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
				String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
				SubTotalSum = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
				String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
				asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
			}

			String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();;
			String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
			asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

			String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
			double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
			double ExpectedTax = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
			String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
			asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

			double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
			double ActualTotalAmountInBillingPge = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
			asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

			// Step 10:  Click on Save to estimation button
			// Expected Result: Verify Invoice details
			base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));

			String EstimationNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
			base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
			String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC249_ProformaInvoice");
			Thread.sleep(4000);
			String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice + "";
			String TaxableValue = ProductTotalList.get(1);
			String InvoiceTotal = Double.toString(Double.parseDouble(TaxableValue.replaceAll("[^\\d.]", "").trim())+ Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim()));

			String TotalAdjustment = Double.toString(Adjustment);
			String Gst = Tax;
			String TotalAmnt = "";
			Thread.sleep(6000);
			pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(ProformaInvoicePath,
					TaxableValue,
					Gst,
					InvoiceTotal,
					TotalAdjustment,
					NetTotal,
					TotalAmnt);

			//Step 11:Recall estimate from cashier
			//Step 12: Select estimate and click on Recall estimation button
			//Step 13: Select the Transaction Type as Sales 
			//Step 14:Click on Process Estimation 
			//Expected Result:  Verify whether the recalled item is the same as the item in the cart.
			//Expected Result: Cashier Screen after recall
			//	 No of Product lines
			//	 Displayed Item Name
			//	 Displayed Quantity
			//	Displayed TOTAL (without Tax)
			//	Displayed Subtotal
			//	TAX
			//	Total Amount

			appUtils.RecallEstimateProcess(EstimationNumber, InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_TransactionType);

			// Step 15: Select any Sales Representative
			base.setZoom(driver, 60);
			//base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
			Thread.sleep(1000);
			appUtils.HandleSpecifyRateModalInRecall();
			Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList,ScannedSkuDataMap);
			String TaxInCashierScreen = TransactionDataLineInCashierScreen.get("Tax");
			String TotalAmountInCashierScreen = TransactionDataLineInCashierScreen.get("TotalAmount");
			String ActualLinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

			List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
			String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRows.size());
			asrt.assertEquals(ExpectedLinesCountInCashierScreen, ActualLinesCountInCashierScreen,"Lines count mismatch: Expected is " + ExpectedLinesCountInCashierScreen + " but found "+ ActualLinesCountInCashierScreen + " in Cashier Screen");

			double SubTotalSumInCashierScreen = 0.00;
			for (int i = 0; i < AllProductRows.size(); i++) {
				List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
				String ActualItemNameInCashierScreen = ItemName.get(i).getText();
				//			if (i == 1) {
				//				String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				//				asrt.assertEquals(ExpectedItemNameInCashierScreen, ActualItemNameInCashierScreen,"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in Cashier Screen");
				//			} else 
				String ExpectedItemNameInCashierScreen = ProductNames.get(i).trim();
				asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen + " in Cashier Screen");

				List<WebElement> QuantityInRecallPage = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
				String ActualQuantityInCashierScreen = QuantityInRecallPage.get(i).getText().replace("-", "");
				String ExpectedQuantityInCashierScreen = ProductQtyList.get(i);
				double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen);
				double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
				asrt.assertEquals(ExpectedQtyInCashierScreen, ActualQtyInCashierScreen, 1.00,"Quantity mismatch: Expected is " + ExpectedQtyInCashierScreen + " but got "+ ActualQtyInCashierScreen + " in Cashier Screen");

				List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
				String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
				SubTotalSumInCashierScreen = SubTotalSumInCashierScreen+ Double.parseDouble(ActualTotalWithoutTaxInCashierScreen);
				String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f",
						Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
				asrt.assertEquals(Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierScreen + " but got "+ ActualTotalWithoutTaxInCashierScreen + " in Cashier Screen");
			}
			String ExpectedSubTotalInCashierScreen = String.format("%.2f", SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();;
			String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
			asrt.assertEquals(Double.parseDouble(ExpectedSubTotalInCashierScreen),Double.parseDouble(ActualSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in Cashier Screen");

			String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
			double ExpectedTaxCalculated = Double.parseDouble(ProductTotalList.get(1)) * 0.03;
			String ExpectedTaxInCashierScreen = String.format("%.2f", ExpectedTaxCalculated);
			asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen),1.0, "Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in Cashier Screen");

			double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ Double.parseDouble(ActualTaxInCashierScreen);
			double ActualTotalAmountInCashierScreen = Double.parseDouble(TotalAmountInCashierScreen.replaceAll("[^\\d.-]", "").trim());
			asrt.assertEquals(ExpectedTotalAmountInCashierScreen, ActualTotalAmountInCashierScreen, 1.0,"Total Amount mismatch: " + ExpectedTotalAmountInCashierScreen + " but found "+ ActualTotalAmountInCashierScreen + " in Cashier Screen");


			//Step 16: Click on the Amount
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String AmntDueInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));

			// Step 17: Select a Payment method(RTGS/Received)
			base.buttonClick(LoginPageObj.Edt_AlertText(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_PaymentMethod));

			//Step17.1 Enter the RTGS amount
			// Verify the balance after entering the UTR number
			// *Check balance amount in RTGS payment receipt details 
			Thread.sleep(5000);
			base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"),AmntDueInBillingPge);

			// Convert String to double
			//		Thread.sleep(7000);
			//		double RtgsValue = Double.parseDouble(AmntDueInBillingPge);
			//		double RoundedRtgsValue = Math.round(RtgsValue);
			//		String RtgsFinalAmount = String.format("%.2f", RoundedRtgsValue);

			//step 17.2: Enter the UTR number
			Thread.sleep(2000);
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"),UtrNumber);
			String BalanceBeforePayment= String.format("%.2f",Double.parseDouble(base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","RTGSBalance"))));
			double RtgsAmountValue = Double.parseDouble(AmntDueInBillingPge);
			double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayment);
			double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - RtgsAmountValue;
			String ActualBalanceBeforePaymentInErp=RtgsAccountDetails.get("RtgsBalanceAmntBeforePayment").replace(",","");
			double ActualBalanceBeforePayment=Double.parseDouble(ActualBalanceBeforePaymentInErp);
			ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0;
			Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
			String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
			String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");

			System.out.println("BalanceBeforePayment " + ExpectedBalanceBeforePayment);
			System.out.println("RtgsAmount " + AmntDueInBillingPge);
			System.out.println("BalanceAfterPayment " + ExpectedBalanceAfterPayment);

			asrt.assertEquals(BalanceBeforePayment, RtgsBalanceAmnt,"RTGS Balance mismatch: Expected "+RtgsBalanceAmnt+"but got"+BalanceBeforePayment+"in RTGS Receipt");

			//17.3: Click on "Apply" then "Ok"
			base.ClickCondition(LoginPageObj.Btn_SingnIn("applybtn"));  
			Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
			Thread.sleep(5000);
			base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));  


			// Step 18: Post exchange invoice(sale)
			// Expected Result: Check final invoice details,* save receipt id for future reference
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")); 
			Thread.sleep(3000);
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Close")); 
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));			
			String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC249_SaleInvoice");
			Thread.sleep(3000);
			String SaleInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
			String GrossAmount = ProductTotalList.get(1);
			String TotalDiscount = Discount;
			String TotalQtyPcs = SkuLinesCount;
			String TotalDiamond = InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_TotalDiamond;
			String RTGSReceived = AmntDueInBillingPge;
			String SalesPdfPath = SaleInvoicePath;
			String AdvanceAmountFinalInvoice = AdjustmentAmount;
			String TotalGrossWeight = ProductQtyList.get(1);
			String NetValue = String.format("%.2f", Double.parseDouble(SkuNetTotal.trim().replaceAll("[^\\d.]", "")));
			String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidationRtgsReceived(
					SalesPdfPath,
					GrossAmount,
					TotalDiscount,
					TotalDiamond,
					TotalQtyPcs,
					TotalNetWeight,
					TotalGrossWeight,
					TaxableValue,
					NetValue,
					RTGSReceived,
					AdjustmentAmount,
					AdvanceAmountFinalInvoice,
					SkuList,
					ScannedSkuDataMap
					);

			System.out.println("PDF INVOICE NO:-" + PdfInvoiceNo);
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
			pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

			// ERP:
			// 1.Go to ERP and search RTGS payment receipt details
			// 2.Filter with UTR number/Customer account number
			// 3.Check the 'Balance Amount' Before and after Payment

			erpUtils.ValidateRTGSPaymentBalance(
					RtgsAccountDetails.get("UtrNumber"),
					InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_CustomerId ,
					ExpectedBalanceBeforePayment,
					ActualBalanceBeforePayment,
					ExpectedBalanceAfterPayment);

		}


	//<summary>
	// Test Case Title : Offer advance with RTGS received
	// Automation ID : TC250
	// Author : Vishnu RR
	// </summary>
	public void TC250_InvoiceAdvanceWithPaymentMethodRtgsReceived() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// Pre-Requisite: creating Offer Advance
		Map<String, String> FirstOfferAdvance = appUtils.CreateWeightBookedOfferAdvance
				(InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData.TC250_CustomerID,
						InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData.TC250_Pieces, 
						InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData.TC250_OfferGrossWeight1, 
						UtilityTestData.Company[0], 
						UtilityTestData.Location[0],
						InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData.TC250_DepositPercent);

		// For fetching BoardRate
		Thread.sleep(3000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product));		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData.TC250_Purity);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// To Handle the Unexpected Error
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");

//		if (base.isElementPresent(driver, CartChangedAlert)) {		
//			base.buttonClick(OkBtn);
//			System.out.println("Attempted to click OK button for error alert");
//		}
		if(base.WaitForElementToBePresentWithTimeouts(LoginPageObj.Btn_SignInButton("OK"),10)) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		if(base.WaitForElementToBePresentWithTimeouts(LoginPageObj.Btn_SignInButton("OK"),10)) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(1000);
		String GoldRate22k = GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		double ExpectedGoldRate22k = Double.parseDouble(GoldRate22k);
		System.out.println("GoldRate22k"+ExpectedGoldRate22k);

		// Pre-Requisite:1. RTGS account creation
		Map<String, String> RtgsAccountDetails = erpUtils.RtgsAccountCreation(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_AmountToDeposit, 
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_CustomerId
				);
		String RtgsBalanceAmnt = RtgsAccountDetails.get("RtgsBalanceAmntBeforePayment").replace(",", "");
		String UtrNumber = RtgsAccountDetails.get("UtrNumber");


		// Step 3: Select customer
		// Step 4: Click on add to estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		appUtils.SearchByCustomerID(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_CustomerID, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));			
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));
		WebDriverWait Waits = new WebDriverWait(driver, Duration.ofSeconds(10));

		//Step 6: Click on Offer advance adjustment button
		Wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Order Advance Adjustment"))).click();

		double AdvanceBalance = 0;
		String AdvanceBalanceAmount = "";
		String MetalRateFetch = "";
		base.selectorByVisibleText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Sel_OrderNumber("availableTranscodeOptions"), FirstOfferAdvance.get("OrderId")); 
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("option", FirstOfferAdvance.get("OrderId")));
		AdvanceBalance = Double.parseDouble(base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount")));
		System.out.println("Balance Amount from the box " + AdvanceBalance);

		// Balance Amount in the Offer advance page 
		Thread.sleep(2000);
		AdvanceBalance = Double.parseDouble(base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount")));
		System.out.println("Balance Amount from the box " + AdvanceBalance);
		MetalRateFetch = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_MetalRate("column3", "h4 ellipsis cell", "Metal Rate"));
		System.out.println("Metal Rate = " + MetalRateFetch);

		//Step: 7 Select advance and Click on redeem button
		//Step: 8. Enter OTP
		//Expected Result : The balance amount and adjustment amount for the selected advance is shown correctly.
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("column1", "h4 ellipsis cell", "1"));
		String BalanceAmtInAdvanceAdj = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Balance amount"));
		String DefaultAdjAmt = base.GetValue(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		double Adjustment = AdvanceBalance * 0.1;
		String AdvanceRate = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("Metal Rate", "h4 ellipsis cell"));
		base.clearData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"));
		base.setData(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAmountInput("Adjustment amount"), Double.toString(Adjustment));
		String AdjustmentAmount = base.GetAttribte(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_BalanceAndAdjustmentAmnt("InputAmount"),"value");

		// Click on cart button
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));

		double Adjustment22K = Double.parseDouble(AdjustmentAmount);
		double Quantity22k   = (Adjustment22K/Double.parseDouble(AdvanceRate))/InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_DepositPercent;
		double MetalRate18k = (Double.parseDouble(AdvanceRate)*18)/22;
		double Quantity18k  = (Adjustment22K/MetalRate18k)/InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_DepositPercent;

		asrt.assertEquals(BalanceAmtInAdvanceAdj, DefaultAdjAmt,"Mismatch in balance amount " +BalanceAmtInAdvanceAdj+" and adjusted amount " +DefaultAdjAmt+" displayed in advance adjustment page"); 

		//Expected Result:Average Calculation  
		String ErpGoldRateFor24K= erpUtils.SetMetalRateInERP(AdvanceRate, "22k"); //ErpGoldRateFor24K
		Thread.sleep(600000); //Wait needed for ERP Gold Rate to Change
		System.out.println("ErpGoldRateFor24K1:::" + ErpGoldRateFor24K);

		double Current22kRate = (Double.parseDouble(ErpGoldRateFor24K)*22)/24;
		double Current18kRate = (Double.parseDouble(ErpGoldRateFor24K)*18)/24;

		List<String> SelectedGoldSkuList = new ArrayList<>();
		int SkuCounter = 1;

		//Step: 9 Scan SKU and add to cart
		//Expected Result : Checking the calculation 
		//Expected Result : Note:Billling Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAXTotal Amount

		SKUResult SkuOGSale = appUtils.GetOGSaleSku(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_TransferType,
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_FromCounter,
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_MetalType,
				Quantity22k,
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_SkuCount);

		List<String> SkuList = SkuOGSale.PcsRateSKU;

		appUtils.HamBurgerButtonClick("iconShop");
		erpUtils.ResetRemainingReturnWeight();
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		ProductNames.add(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_AdvanceName);
		ProductQtyList.add(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_DepositAdjustmentQty);
		ProductTotalList.add(Double.toString(Adjustment));

		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);

			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);

			//Expected Result : Checking the calculation 
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount,String.valueOf(Quantity22k),"22k",MetalRateFetch,ErpGoldRateFor24K);
		}
		ProductNames.add(ScannedSkuDataMap.get("SKU_" + InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount + "_HeaderName"));
		ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount + "_GrossWeight"));
		ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount + "_Total"));
		String RateKey = "SKU"+ InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount+ "_ITEM"+ InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount+ "_RATE";
		String BoardRateFromSkuTble = ScannedSkuDataMap.get(RateKey);
		Double SkuBoardRate = Double.parseDouble(BoardRateFromSkuTble);
		String Purity = ScannedSkuDataMap.get("SKU_"+ InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_SkuCount+ "_Purity");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Expected Result : Note:Billling Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAXTotal Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal = TransactionDataLine.get("Subtotal");
		String NetTotal = TransactionDataLine.get("NetTotal");
		String Discount = TransactionDataLine.get("Discount");
		String TotalNetWeight = TransactionDataLine.get("NetWeight");
		String Tax = TransactionDataLine.get("Tax");
		String TotalAmount = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount = TransactionDataLine.get("LinesCount");
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		String Linecount = String.valueOf(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_SkuCount);
		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, "Billing");

		// Step 10:  Click on Save to estimation button
		// Expected Result: Verify Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		String EstimationNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC249_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice + "";
		String TaxableValue = ProductTotalList.get(1);
		String InvoiceTotal = Double.toString(Double.parseDouble(TaxableValue.replaceAll("[^\\d.]", "").trim())+ Double.parseDouble(Tax.replaceAll("[^\\d.]", "").trim()));
		String TotalAdjustment = Double.toString(Adjustment);
		String Gst = Tax;
		String TotalAmnt = "";
		Thread.sleep(3000);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				TotalAdjustment,
				NetTotal,
				TotalAmnt);

		//Step 11:Recall estimate from cashier
		//Step 12: Select estimate and click on Recall estimation button
		//Step 13: Select the Transaction Type as Sales 
		//Step 14:Click on Process Estimation 
		//Expected Result:  Verify whether the recalled item is the same as the item in the cart.
		//Expected Result : Note:Cashier Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAXTotal Amount

		appUtils.RecallEstimateProcess(EstimationNumber, InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_TransactionType);

		// Step 15: Select any Sales Representative
		base.setZoom(driver, 60);
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(1000);
		appUtils.HandleSpecifyRateModalInRecall();

		//Expected Result : Note:Cashier Screen, No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAXTotal Amount
		Map<String, String> RecallTransactionDataLines = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		appUtils.ValidateBillingPgeAndCasierScreen(RecallTransactionDataLines, ProductNames, ProductQtyList, ProductTotalList, "Cashier Screen");

		//Step 16: Click on the Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String AmntDueInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));

		// Step 17: Select a Payment method(RTGS/Received)
		base.buttonClick(LoginPageObj.Edt_AlertText(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_PaymentMethod));

		//Step17.1 Enter the RTGS amount
		// Verify the balance after entering the UTR number
		// Check balance amount in RTGS payment receipt details 
		Thread.sleep(2000);	
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"),AmntDueInBillingPge);

		//step 17.2: Enter the UTR number
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"),UtrNumber);
		String BalanceBeforePayment= String.format("%.2f",Double.parseDouble(base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","RTGSBalance"))));
		double RtgsAmountValue = Double.parseDouble(AmntDueInBillingPge);
		double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayment);
		double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - RtgsAmountValue;
		String ActualBalanceBeforePaymentInErp=RtgsAccountDetails.get("RtgsBalanceAmntBeforePayment").replace(",","");
		double ActualBalanceBeforePayment=Double.parseDouble(ActualBalanceBeforePaymentInErp);
		ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0;
		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
		String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");
		System.out.println("BalanceBeforePayment " + ExpectedBalanceBeforePayment);
		System.out.println("RtgsAmount " + AmntDueInBillingPge);
		System.out.println("BalanceAfterPayment " + ExpectedBalanceAfterPayment);

		//Expected Result :Check balance amount in RTGS payment receipt details 
		asrt.assertEquals(BalanceBeforePayment, RtgsBalanceAmnt,"RTGS Balance mismatch: Expected "+RtgsBalanceAmnt+"but got"+BalanceBeforePayment+"in RTGS Receipt");

		//17.3: Click on "Apply" then "Ok"
		base.ClickCondition(LoginPageObj.Btn_SingnIn("applybtn"));  
		Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));  

		// Step 18: Post exchange invoice(sale)
		// Expected Result: Check final invoice details,* save receipt id for future reference
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")); 
		Thread.sleep(3000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));			
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC249_SaleInvoice");
		Thread.sleep(3000);
		String SaleInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
		String GrossAmount = ProductTotalList.get(1);
		String TotalDiscount = Discount;
		String TotalQtyPcs = String.valueOf(Integer.parseInt(SkuLinesCount) - 1);
		String TotalDiamond = InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC249_TotalDiamond;
		String RTGSReceived = AmntDueInBillingPge;
		String SalesPdfPath = SaleInvoicePath;
		String AdvanceAmountFinalInvoice = AdjustmentAmount;
		String TotalGrossWeight = ProductQtyList.get(1);
		String NetValue = String.format("%.2f", Double.parseDouble(SkuNetTotal.trim().replaceAll("[^\\d.]", "")));
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidationRtgsReceived(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				TotalDiamond,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				RTGSReceived,
				AdjustmentAmount,
				AdvanceAmountFinalInvoice,
				SkuList,
				ScannedSkuDataMap
				);

		System.out.println("PDF INVOICE NO:-" + PdfInvoiceNo);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		Thread.sleep(12000);

		// ERP:
		// 1.Go to ERP and search RTGS payment receipt details
		// 2.Filter with UTR number/Customer account number
		// 3.Check the 'Balance Amount' Before and after Payment
		erpUtils.ValidateRTGSPaymentBalance(
				RtgsAccountDetails.get("UtrNumber"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC250_CustomerId ,
				ExpectedBalanceBeforePayment,
				ActualBalanceBeforePayment,
				ExpectedBalanceAfterPayment);
	}



	//<summary>
	// Test Case Title : Invoice with partial RTGS Received
	// Automation ID : TC252
	// Author : Neethu 
	// </summary>
	public void TC252_InvoiceAdvanceWithPaymentMethodRtgsReceived() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre requisite                                                    
		//To create a new RTGS account:
		//1.Go to ERP of specific legal entity and search RTGS payment receipt
		//2.Click on New
		//3.Enter Entry date
		//4.Enter UTR number
		//5.Enter Amount 
		//6.Provide Bank Account
		//7.Enter Remarks
		// 8.Enter Cost center
		// 9.Enter Company ID
		//10.Enter Customer Account
		//11.Select Credit date
		//12.Enter User ID
		//13.Click on Post

		//RTGS ACCOUNT CREATION IN ERP 
		Map<String, String> RtgsDetails= erpUtils.RtgsAccountCreation(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_StringAmountToDeposit, InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_CustomerId);	
		String RtgsBalanceAmt = RtgsDetails.get("RtgsBalanceAmntBeforePayment").replace(",", "");
		String UtrNumber = RtgsDetails.get("UtrNumber");
		System.out.println("RtgsBalanceAmnt: "+RtgsBalanceAmt);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimation button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_CustomerId, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Scan Multiple uncut and precia SKU's and click on Add to cart button
		//Expected Result: Check calculation
		// Step 6 : Select any one of the sales agent
		List<String> MultipleUncutSkus      = appUtils.SelectMultipleSKUs( InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_SKUCount,InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_TransferType,
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_ToCounterUncut,InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_MetalTypeUncut);

		appUtils.HamBurgerButtonClick("iconShop");

		List<String> MultiplePreciaSkus = appUtils.SelectMultipleSKUs( InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_SKUCount,InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_TransferType,
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_ToCounterPrecia,InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_MetalTypePrecia);
		Thread.sleep(1000);

		List<String> SkuList = new ArrayList<>();
		SkuList.addAll(MultipleUncutSkus);
		SkuList.addAll(MultiplePreciaSkus);

		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		//Note:Billing Screen*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		//TranscationPageVerifyLines
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

		// Step 7: Click on Save to estimation button
		// Step 8: Save Esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String EstmnNumber=base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		String TaxableValue        = SubTotal;
		String Gst                 = Tax;
		String InvoiceTotal        = TotalAmount;
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC251_ProFormaInvoice");
		String ProFormaInvoice     = System.getProperty("user.dir") + "\\Invoices\\"+ProFormaInvoiceName+"";
		pdfUtils.ProFormaInvoicePDFVerifyNormal(
				ProFormaInvoice,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				LinesCount,
				Double.parseDouble(TotalGrossWeight),
				Double.parseDouble(TotalNetWeight)
				);

		//Step 9 : Recall estimate from cashier screen
		//Step 10 : Select estimate and click on recall estimate button
		//Expected : Verify whether the recalled item is the same as the item in the cart.
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

		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+" in recall estimation page"); 

		// Step 11 : Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_TransactionType);

		// Step 12 : Click on Process Estimation
		// Step 13 : Select any Sales Representative
		// Expected Result:Cashier Screen after recall *No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX*Total Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		base.setZoom(driver, 60);
		Thread.sleep(3000);

		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		Thread.sleep(1000);
		appUtils.HandleSpecifyRateModalInRecall();
		Map<String, String> TransactionRecallDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		mathUtils.ValidateTransactionLineCalculation(TransactionRecallDataLine, ScannedSkuDataMap);

		// Step 14 :  Click on the Amount
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Amnt("h1"));

		// Step 15 :  Select a Payment method(RTGS/Received)
		base.buttonClick(LoginPageObj.Edt_AlertText(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC251_PaymentMethod));

		//Step15.1 Enter the RTGS amount
		// Verify the balance after entering the UTR number
		// Check balance amount in RTGS payment receipt details 
		String AmountDue=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("text","AmountDue"));
		double PartialAmtDue = Double.parseDouble(AmountDue)/2;
		double RoundedPartialAmtDue = Math.round(PartialAmtDue);
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"));
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"), String.valueOf(RoundedPartialAmtDue));
		String RtgsAmount=base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"));
		double ExpectedRtgsAmt =Double.parseDouble(RtgsBalanceAmt)-RoundedPartialAmtDue;
		double RtgsValue = Double.parseDouble(RtgsAmount);
		double RoundedRtgsValue = Math.round(RtgsValue);
		String RtgsFinalAmount = String.format("%.2f", RoundedRtgsValue);
		System.out.println("RtgsFinalAmount :"+RtgsFinalAmount);
		double RtgsAmountReceived = Double.parseDouble(RtgsAmount);  
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1")));
		Thread.sleep(2000);

		//15.2.Enter the UTR number
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Ele_SalesPerson("1"),RtgsDetails.get("UtrNumber"));
		String BalanceBeforePayment= String.format("%.2f",Double.parseDouble(base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","RTGSBalance"))));
		double RtgsAmountValue = PartialAmtDue;
		double RtgsAmountValuerounded = Math.round(RtgsAmountValue);
		String RoundedRtgsAmountValue = String.format("%.2f", RtgsAmountValuerounded); 
		double ExpectedBalanceBeforePayment = Double.parseDouble(BalanceBeforePayment);
		double ExpectedBalanceAfterPayment = ExpectedBalanceBeforePayment - RtgsAmountValuerounded;
		String ActualBalanceBeforePaymentInErp=RtgsDetails.get("RtgsBalanceAmntBeforePayment").replace(",","");
		double ActualBalanceBeforePayment=Double.parseDouble(ActualBalanceBeforePaymentInErp);
		System.out.println("ActualBalanceBeforePayment " + ActualBalanceBeforePayment);
		ExpectedBalanceAfterPayment = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0;
		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SkuNetTotal = SkuBillingScrnTbleData.get("TotalAmount");
		String SkuLinesCount = SkuBillingScrnTbleData.get("LinesCount");
		System.out.println("BalanceBeforePayment " + ExpectedBalanceBeforePayment);
		System.out.println("RtgsAmountValue " + RoundedRtgsAmountValue);
		System.out.println("BalanceAfterPayment " + ExpectedBalanceAfterPayment);

		//Expected Result :Check balance amount in RTGS payment receipt details 
		asrt.assertEquals(BalanceBeforePayment, RtgsBalanceAmt,"RTGS Balance mismatch: Expected "+RtgsBalanceAmt+"but got"+BalanceBeforePayment+" in RTGS Receipt");


		//15.3.Click on "Apply" then "Ok"
		base.buttonClick(LoginPageObj.Btn_SingnIn("applybtn"));  //click on apply
		wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
		Thread.sleep(5000);
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1"));   //click on ok

		// Step 16:  Post the Invoice
		// Expected Result: Check final invoice details * save receipt id for future reference
		String PaymentAmountHDFC = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();
		System.out.println("PaymentAmountHDFC : "+PaymentAmountHDFC);
		Thread.sleep(3000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));		
		String TaxInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC252_SaleInvoice");
		Thread.sleep(3000);
		String TaxInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + TaxInvoiceName + "";
		String GrossAmount = SubTotal;
		String TotalDiscount = Discount;
		String TotalQtyPcs = LinesCount;
		String TotalDiamond = InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_TotalDiamond;
		String RTGSReceived = String.valueOf(RtgsAmountValuerounded);
		String NetValue = String.format("%.2f", Double.parseDouble(SkuNetTotal.trim().replaceAll("[^\\d.]", "")));
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationWithRtgsReceived(
				TaxInvoicePath,
				GrossAmount,
				TotalDiscount,    
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				RtgsAmount,
				SkuList,
				ScannedSkuDataMap );
		Thread.sleep(4000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close")); 
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

		//ERP:
		//1.Go to ERP and search RTGS payment receipt details
		//2.Filter with UTR number/Customer account number
		//3.Check the 'Balance Amount' Before and after Payment
		erpUtils.ValidateRTGSPaymentBalance(
				RtgsDetails.get("UtrNumber"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC252_CustomerId,
				ExpectedBalanceBeforePayment,
				ActualBalanceBeforePayment,
				ExpectedRtgsAmt
				);

	}

	//<summary>
	// Test Case Title : RTGS received for Advance and invoice
	// Automation ID : TC253
	// Author : Vishnu Manoj K
	// </summary>
	public void TC253_InvoiceAdvanceWithPaymentMethodRtgsReceived() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(3000);

		//Pre-Requisite: Customer must have an advance
		String AdvanceAmount = appUtils.CreateNormalAdvance(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_CustomerNo, 
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_AdvanceAmount,
				UtilityTestData.DobYear, 
				UtilityTestData.DueYear);

		//Pre-Requisite: GoldRate Fetching from POS 
		Thread.sleep(3000);
		base.isPresent(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),UtilityTestData.OGProduct);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(5000);
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.Product),
				NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),5,2);
		Thread.sleep(1000);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_Purity);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(3000);	
		//Error pop-up handling
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_Name("NExchange"));
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("qcInput"),1);
		Thread.sleep(3000);
		base.selectorByIndex(NormalSaleGoldAndSilverObj.Sel_DepositType("smInput"),1);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesogp"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_GrossPieces);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightogp"),InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_GrossWeight);
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Calculate"));
		Thread.sleep(3000);
		String GoldRate22k         = GetValue(NormalSaleGoldAndSilverObj.Ele_Name("ogpnetrate"));
		String Rate                = GoldRate22k.replace(",", "").replace(".00", "");
		double ExpectedGoldRate22k = Double.parseDouble(GoldRate22k);
		System.out.println("GoldRate22k"+ExpectedGoldRate22k);

		// Pre-Requisite: Change metal rate.
		String SkuPurity           = InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_Purity;
		String ErpGoldRateFor24K   = erpUtils.SetMetalRateInERP(GoldRate22k, SkuPurity);
		System.out.println("ErpGoldRateFor24K " + ErpGoldRateFor24K);
		TimeUnit.MINUTES.sleep(2);

		// Pre-Requisite:2. Customer must have enough balance amount in RTGS account
		Map<String, String> RtgsAccountDetails = erpUtils.RtgsAccountCreation(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_AmountToDeposit, 
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_CustomerId
				);
		String RtgsBalanceAmnt = RtgsAccountDetails.get("RtgsBalanceAmntBeforePayment").replace(",", "");
		String UtrNumber       = RtgsAccountDetails.get("UtrNumber");
		System.out.println("RtgsBalanceAmnt before payment " + RtgsBalanceAmnt);
		System.out.println("UtrNumber " + UtrNumber);

		//Error pop-up handling
		if(base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}

		// Pre-Requisite: To fetch Transaction no(Advance Adjustment) in ERP - Filter using Customer ID and Recent date. 
		String OriginalWindow = driver.getWindowHandle();

		// Step 1: Go to ERP and search Retail deposit details
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		// 1. Search Retail deposit details
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "Retail deposit details");
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(5000);

		// ----------------Is locked--------------
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Is locked"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsLocked_ApplyFilters_label", "Apply"),5,2);
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CartMoney("lookupDock-dockContainer lookupDock-comboBox displayoption hasFocusedChild","lookupButton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_DistrSched("__FilterField_MGDRetailDepositTable_IsLocked_IsLocked_Input_0_0_list_item0"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsLocked_ApplyFilters_label", "Apply"));

		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(250, 0).release().perform();
		Thread.sleep(5000);

		// ---------------- CustomerAccount ----------------
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Customer account"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_CustAccount_ApplyFilters_label", "Apply"),5,2);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("CustAccount"), InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_CustomerId);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_CustAccount_ApplyFilters_label", "Apply"));
		Thread.sleep(5000);

		// ---------------- Date ----------------
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Date"),
				NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_TransDate_TransDate"),5,2);
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_TransDate_TransDate"), appUtils.getCurrentDate("MM/dd/yyyy"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_TransDate_ApplyFilters_label", "Apply"));
		Thread.sleep(5000);

		// ---------------- Scroll horizontally---------
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(250,0).release().perform();
		Thread.sleep(5000);

		// ----------------Adjusted Field --------------
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Is adjusted"),
				GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsAdjusted_ApplyFilters_label", "Apply"),5,2);
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CartMoney("lookupDock-dockContainer lookupDock-comboBox displayoption hasFocusedChild","lookupButton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_DistrSched("__FilterField_MGDRetailDepositTable_IsAdjusted_IsAdjusted_Input_0_0_list_item0"));
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_IsAdjusted_ApplyFilters_label", "Apply"));

		// ---------------- Metal Rate ----------------
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Ele_Transaction("public_fixedDataTableCell_cellContent", "Metal rate"),
				LoginPageObj.Edt_Alert("Sort largest to smallest"),5,2);
		Thread.sleep(1000);
		Rate = Rate.replace(",", "").replace(".00", "");
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("FilterField_MGDRetailDepositTable_MetalRate_MetalRate_Input"), Rate);
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPApply("__MGDRetailDepositTable_MetalRate_ApplyFilters_label", "Apply"));
		System.out.println("Rate"+Rate);

		// --------------Scroll fully LEFT-------------
		for (int i = 0; i < 10; i++) {
			ActionsObj.clickAndHold(Scrollbar)
			.moveByOffset(-50, 0)   
			.release()
			.perform();
			Thread.sleep(200);
		}
		String TransNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Transaction number", 1));
		System.out.println("TransNumber"+TransNumber);
		driver.switchTo().window(OriginalWindow);

		// Step 3: Select customer
		// Step 4: Click on add to estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		appUtils.SearchByCustomerID(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_CustomerNo, UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		Thread.sleep(5000);		
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 6: Click on normal advance settlement
		//Step 7: Select advance and Click on redeem button
		//Expected Result: The balance amount and adjustment amount for the selected advance is shown correctly.
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.elementToBeClickable(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Normal Advance Adjustment"))).click();
		double TotalBalance = 0.0;
		double Adjustment = 0.0;
		Thread.sleep(2000);
		//Error pop-up handling
		if(base.isExists(LoginPageObj.Btn_SignInButton("OK"))) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.actionClick(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"));
		base.actionSetData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"),TransNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.SearchTerm"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",Rate));		
		String Quantity=base.GetText(NormalSaleGoldAndSilverObj.Ele_ErpRtgsWithClass("Gold booked qty","h4 ellipsis cell",1));
		System.out.println("Quantity"+Quantity);
		Thread.sleep(2000);
		String BalanceAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.BalanceAmount"));
		String AdjustmentAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: viewModel.InputAmount"));	
		TotalBalance = Double.parseDouble(BalanceAmount);
		Adjustment = Double.parseDouble(AdjustmentAmount);

		asrt.assertEquals(BalanceAmount, AdjustmentAmount, "Amount mismatch: Balance amount is " + BalanceAmount + " and adjustment amount is " + AdjustmentAmount + " in advance adjustment page");

		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-commandingsurface-overflowbutton win-appbar-overflowbutton"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Add to cart"));

		//Step 8: Enter OTP(Disabled)
		//Step 9: Scan SKU and add to cart
		// Note: Check average rate
		//Expected result: Check calculation
		SKUResult SkuOGSale = appUtils.GetOGSaleSku(
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_TransferType,
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_FromCounter,
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_MetalType,
				Double.parseDouble(Quantity),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_SkuCount);

		List<String> SkuList = SkuOGSale.PcsRateSKU;
		appUtils.HamBurgerButtonClick("iconShop");  

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames     = new ArrayList<>();
		List<String> ProductQtyList   = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_DepositAdjustment);
		ProductQtyList.add(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_DepositAdjustmentQty);
		ProductTotalList.add(AdjustmentAmount);
		String SkuPurityReturn		="22K";
		erpUtils.ResetRemainingReturnWeight();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			erpUtils.ErpSkuIngredientItemCalculation(CurrentSkuData, SkuNumber,Quantity,SkuPurityReturn,Rate,String.valueOf(ErpGoldRateFor24K));
		}

		// Note:Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal                         = TransactionDataLine.get("Subtotal");
		String NetTotal                         = TransactionDataLine.get("NetTotal");
		String Discount                         = TransactionDataLine.get("Discount");
		String TotalNetWeight                   = TransactionDataLine.get("NetWeight");
		String Tax                              = TransactionDataLine.get("Tax");
		String TotalAmount                      = TransactionDataLine.get("TotalAmount");
		String ActualLinesCount                 = TransactionDataLine.get("LinesCount");

		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount       = Integer.toString(AllProductRows.size());

		asrt.assertEquals(ActualLinesCount, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

		double SubTotalSum = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName  = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String Actual              = ItemName.get(i).getText().trim();
			String Expected            = ProductNames.get(i).trim();

			asrt.assertTrue(Expected.contains(Actual),"Item name mismatch: Expected '" + Expected + "' to contain actual '" + Actual + "in billing page");

			List<WebElement> Quantity1          = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge   = Quantity1.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i);
			double ActualQtyInBillingPge        = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge      = Double.parseDouble(ExpectedQuantityInBillingPge);

			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax           = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSum                                = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));

			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

		String ActualTaxInBillingPge   = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax               = Double.parseDouble(ActualTaxInBillingPge);
		double ExpectedTax             = (Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2))) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);

		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());

		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		List<String> TotalWithoutTax = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));

		// Step 10: save estimate
		//Expected: Verify the Invoice details
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("buttonsContainer positionRelative", "Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC253_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		for (String Ele : TotalWithoutTax) {
			System.out.println(Ele);
		}
		String Gst = Double.toString((Double.parseDouble(TotalWithoutTax.get(1).replaceAll("[^\\d.]", "").trim()) + Double.parseDouble(TotalWithoutTax.get(2).replaceAll("[^\\d.]", "").trim()) )* 0.03);
		String TaxableValue    = Double.toString(Double.parseDouble(TotalWithoutTax.get(1).replaceAll("[^\\d.]", "").trim())+Double.parseDouble(TotalWithoutTax.get(2).replaceAll("[^\\d.]", "").trim()));
		String InvoiceTotal    = Double.toString(Double.parseDouble(TotalWithoutTax.get(1).replaceAll("[^\\d.-]", "").trim())+ Double.parseDouble(Gst)+ Double.parseDouble(TotalWithoutTax.get(2).replaceAll("[^\\d.-]", "").trim())  );
		String TotalAdjustment = TotalWithoutTax.get(0).replaceAll("[^\\d.]", "").trim();
		String TotalAmt        = "";
		NetTotal=Double.toString(Double.parseDouble(InvoiceTotal)-Double.parseDouble(TotalAdjustment));
		System.out.println(Gst + " gst :" + TaxableValue + " taxab :" + InvoiceTotal + " invoice " + TotalAdjustment + " NetTotal " + NetTotal);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(ProFormaInvoicePath, 
				TaxableValue, 
				Gst, 
				InvoiceTotal, 
				TotalAdjustment, 
				NetTotal, 
				TotalAmt);

		// Step 11: Recall estimate from cashier screen
		// Step 12: Select estimate and click on recall estimate button
		// Expected: Verify whether the recalled item is the same as the item in the cart.
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
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

		//Step 13: Select transaction type as sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_TransactionType);

		//Step 14: Click on process estimation button
		//Step 15: Choose a sales representative
		//Expected: Note:-Cashier Screen after recall*No of Product lines*Displayed Item Name*Displayed Quantity*Displayed TOTAL (without Tax)*Displayed Subtotal*TAX *Total Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Map<String, String> CashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		System.out.println("CashierScreen: " + CashierScreen);
		for (Map.Entry<String, String> Entry : TransactionDataLine.entrySet()) {
			String Key           = Entry.getKey();
			String ExpectedValue = Entry.getValue();
			String ActualValue   = CashierScreen.get(Key);
			System.out.println("Verifying Field: " + Key + " | Expected: " + ExpectedValue + " | Actual: " + ActualValue);

			asrt.assertEquals( ActualValue,ExpectedValue,"Mismatch at Field: " + Key + "  Expected: " + ExpectedValue + ", but got: " + ActualValue);

		}
		appUtils.HandleSpecifyRateModalInRecall();

		//Step 16: Click on the Amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String AmntDueInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replaceAll("[^\\d.]", "").trim();
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));

		// Step 17: Select a Payment method(RTGS/Received)
		base.buttonClick(LoginPageObj.Edt_AlertText(InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_PaymentMethod));

		//Step17.1 Enter the RTGS amount
		Thread.sleep(2000);	
		base.setData(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","Amount"),AmntDueInBillingPge);

		//step 17.2: Enter the UTR number
		Thread.sleep(2000);
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("padd"),UtrNumber);
		String BalanceBeforePayment                = String.format("%.2f",Double.parseDouble(base.GetValue(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_RtgsBalance("number","RTGSBalance"))));
		double RtgsAmountValue                     = Double.parseDouble(AmntDueInBillingPge);
		double ExpectedBalanceBeforePayment        = Double.parseDouble(BalanceBeforePayment);
		double ExpectedBalanceAfterPayment         = ExpectedBalanceBeforePayment - RtgsAmountValue;
		String ActualBalanceBeforePaymentInErp     = RtgsAccountDetails.get("RtgsBalanceAmntBeforePayment").replace(",","");
		double ActualBalanceBeforePayment          = Double.parseDouble(ActualBalanceBeforePaymentInErp);
		ExpectedBalanceAfterPayment                = Math.round(ExpectedBalanceAfterPayment * 100.0) / 100.0;
		Map<String, String> SkuBillingScrnTbleData = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SkuNetTotal                         = SkuBillingScrnTbleData.get("TotalAmount");
		String SkuLinesCount                       = SkuBillingScrnTbleData.get("LinesCount");
		System.out.println("BalanceBeforePayment " + ExpectedBalanceBeforePayment);
		System.out.println("RtgsAmount " + AmntDueInBillingPge);
		System.out.println("BalanceAfterPayment " + ExpectedBalanceAfterPayment);

		//Expected Result : Verify the balance after entering the UTR number Check balance amount in RTGS payment receipt details 
		asrt.assertEquals(BalanceBeforePayment, RtgsBalanceAmnt,"RTGS Balance mismatch: Expected "+RtgsBalanceAmnt+"but got"+BalanceBeforePayment+"in RTGS Receipt");

		//17.3: Click on "Apply" then "Ok"
		base.ClickCondition(LoginPageObj.Btn_SingnIn("applybtn"));  
		Wait.until(ExpectedConditions.presenceOfElementLocated(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text:Message")));
		Thread.sleep(5000);
		base.ClickCondition(LoginPageObj.Btn_SingnIn("Button1"));  

		// Step 18: Post exchange invoice(sale)
		// Expected Result: Check final invoice details,* save receipt id for future reference
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Amnt("h1")); 
		Thread.sleep(3000);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Close")); 
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));			
		String FinalInvoice              = pdfUtils.DownloadAndRenameLatestPDF("TC253_SaleInvoice");
		Thread.sleep(3000);
		String SaleInvoicePath           = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice + "";
		String GrossAmount               = Double.toString(Double.parseDouble(ProductTotalList.get(1))+Double.parseDouble(ProductTotalList.get(2)));
		String TotalDiscount             = Discount;
		String TotalQtyPcs               = Integer.toString(SkuList.size());
		String TotalDiamond              = InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_TotalDiamond;
		String RTGSReceived              = AmntDueInBillingPge;
		String SalesPdfPath              = SaleInvoicePath;
		String AdvanceAmountFinalInvoice = AdjustmentAmount;
		String TotalGrossWeight = Double.toString(Double.parseDouble(ProductQtyList.get(1))+Double.parseDouble(ProductQtyList.get(2)));
		String NetValue = String.format("%.2f", Double.parseDouble(SkuNetTotal.trim().replaceAll("[^\\d.]", "")));
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfAdjustmentValidationRtgsReceived(
				SalesPdfPath,
				GrossAmount,
				TotalDiscount,
				TotalDiamond,
				TotalQtyPcs,
				TotalNetWeight,
				TotalGrossWeight,
				TaxableValue,
				NetValue,
				RTGSReceived,
				AdjustmentAmount,
				AdvanceAmountFinalInvoice,
				SkuList,
				ScannedSkuDataMap
				);

		System.out.println("PDF INVOICE NO:-" + PdfInvoiceNo);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		Thread.sleep(5000);

		// ERP:
		// 1.Go to ERP and search RTGS payment receipt details
		// 2.Filter with UTR number/Customer account number
		// 3.Check the 'Balance Amount' Before and after Payment
		erpUtils.ValidateRTGSPaymentBalance(
				RtgsAccountDetails.get("UtrNumber"),
				InvoiceAdvanceWithPaymentMethodRtgsReceivedTestData.TC253_CustomerId ,
				ExpectedBalanceBeforePayment,
				ActualBalanceBeforePayment,
				ExpectedBalanceAfterPayment);
	}
}


