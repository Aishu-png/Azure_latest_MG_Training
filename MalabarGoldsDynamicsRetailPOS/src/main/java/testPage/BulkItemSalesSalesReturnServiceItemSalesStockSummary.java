package testPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj;
import testData.BulkItemSalesSalesReturnServiceItemSalesStockSummary_TestData;
import testData.CommonData;
import testData.GPPSettlementAverageRateSingleSkuMultipleSku_TestData;
import testData.GPPSettlementDiscountPlan_TestData;
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
import utilPack.PdfUtilities.PdfInvoiceDetails;

//import basePackage.BasePge;

public class BulkItemSalesSalesReturnServiceItemSalesStockSummary extends BasePge {

	BasePge base;
	Assert asrt;
	AppUtilities appUtils;
	ErpUtilities erpUtils;
	PdfUtilities pdfUtils;
	MathUtilities mathUtils;

	NormalSaleGoldandSilver_Obj NormalSaleGoldAndSilverObj = new NormalSaleGoldandSilver_Obj();
	NormalSalesReturnGoldSilverReturnSaleCounter_Obj NormalSalesReturnGoldSilverReturnSaleCounterObj = new NormalSalesReturnGoldSilverReturnSaleCounter_Obj();
	Utility_TestData UtilityTestData = new Utility_TestData();
	OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj =new OldPurchaseExchangeOwnorOtherGoldorSilverSale_Obj();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_TestData BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData = new BulkItemSalesSalesReturnServiceItemSalesStockSummary_TestData();
	Login login = new Login(driver);
	LoginPage_Obj LoginPageObj=new LoginPage_Obj();
	BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj = new BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj();
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj=new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	public BulkItemSalesSalesReturnServiceItemSalesStockSummary(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		erpUtils = new ErpUtilities(base);
		pdfUtils = new PdfUtilities(base);
		mathUtils =  new MathUtilities(base);
	}

	// <summary>
	// Test Case Title : Service item sale
	// Automation ID : TC149
	// Author: Chandana Babu
	// </summary>
	public void TC149_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {
		//Step1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step3. Select customer
		//Step4. Click on add to estimation button
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step5.Search any service item
		Thread.sleep(5000);	
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Item);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		//Step6.Select required item(eg. ear punching)-->click add item
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("addItemsToCartCommand"));

		//Step7.Enter 
		//Step7.1 Pieces
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesInput"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Pieces);

		//Step 7.2 Rate
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Rate);

		//Step 7.3 Click enter button
		//Expected: Check the calculation
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"), "ENTER");
		double CalculatedCvalue = Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Pieces)*Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Rate);
		String ExpectedCvalue = String.format("%.2f", CalculatedCvalue);;
		Thread.sleep(2000);
		String ItemNameInItemDetailsge = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ActualCvalue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		Thread.sleep(2000);
		String ActualTotal = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));

		asrt.assertEquals(ActualCvalue, ExpectedCvalue,"Cvalue mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");
		asrt.assertEquals(ActualTotal, ExpectedCvalue,"Total value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualTotal+" in item details page");

		//Step 8: Click on add to cart
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(1000);	
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			if(AlertWait.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(500); // Short delay for modal to close
			}
		} catch (Exception e) {
		}

		//Step 9. save esimate
		//Expected: Check calculation
		//Note: Billing Screen No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInBillingPage = Integer.toString(AllProductRows.size());
		String ActualLinesCountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInBillingPage, ExpectedLinesCountInBillingPage, "Lines count mismatch: Expected is "+ ExpectedLinesCountInBillingPage + " but found " + ActualLinesCountInBillingPage + " in billing page");

		List<String> ItemName = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInBillingPage = ItemName.get(0);
		String ExpectedItemNameInBillingPage = ItemNameInItemDetailsge.split(" - ")[1].trim();
		asrt.assertEquals(ActualItemNameInBillingPage, ExpectedItemNameInBillingPage,"Item name mismatch: Expected is " + ExpectedItemNameInBillingPage + " but got "+ ActualItemNameInBillingPage + " in billing page");

		List<String> Quantity = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ActualQuantityInBillingPage = Quantity.get(0);
		String ExpectedQuantityInBillingPage = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Pieces;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInBillingPage), Double.parseDouble(ExpectedQuantityInBillingPage), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPage + " but got " + ActualQuantityInBillingPage + " in billing page");

		List<String> TotalWithoutTax = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String ActualTotalWithoutTaxInBillingPage = TotalWithoutTax.get(0).replaceAll("[^\\d.]", "").trim();
		String ExpectedTotalWithoutTaxInBillingPage = ActualTotal;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPage),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in billing page");

		String ExpectedSubTotalInBillingPage = ActualTotalWithoutTaxInBillingPage.replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPage),Double.parseDouble(ExpectedSubTotalInBillingPage), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPage + " but found " + ActualSubTotalInBillingPage+ " in billing page");

		String TaxInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInBillingPage = Double.parseDouble(TaxInBillingPage);
		double ExpectedTaxInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)*0.18;
		asrt.assertEquals(ActualTaxInBillingPage,ExpectedTaxInBillingPage, 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPage + " but found " + ActualTaxInBillingPage+ " in billing page");

		double ExpectedTotalAmountInBillingPage = Double.parseDouble(ActualSubTotalInBillingPage)+ ActualTaxInBillingPage;
		String ActualTotalAmountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInBillingPage), ExpectedTotalAmountInBillingPage, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPage + " but found " + ActualTotalAmountInBillingPage + " in billing page");

		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String TaxInEstimationPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalTax"));

		asrt.assertEquals(TaxInEstimationPge, TaxInBillingPage, "Tax value mismatch: Expected is "+TaxInBillingPage+" but got "+TaxInEstimationPge+" in estimation page");

		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC149_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= TaxInBillingPage;
		String NetTotal = ActualTotalAmountInBillingPage;
		String TaxableValue=ActualSubTotalInBillingPage;
		String InvoiceTotal=ActualTotalAmountInBillingPage;
		String TotalAmount = ActualTotalAmountInBillingPage;
		String LinesCount = ActualLinesCountInBillingPage;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmount
				);

		//Step10.Recall estimate from cashier screen
		//Step11. Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
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
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Step12.Select the Transaction Type as Sales
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_TransactionType);

		//Step13.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step14.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		WebDriverWait AlertWait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			if(AlertWait1.until(ExpectedConditions.presenceOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price")))!= null){
				base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
				base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
				Thread.sleep(500); // Short delay for modal to close
			}
		} catch (Exception e) {
		}

		//Step15.Click on the Amount
		//Note:-Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and  Total Amount
		//Step16.Select a Payment method(Cash or Card)
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen  = Integer.toString(AllProductRowsInCashierScreen.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in Cashier Screen ");

		List<String> ItemNameInCashierScreen = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(0);
		String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsge.split(" - ")[1].trim();
		asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen  + " in Cashier Screen ");

		List<String> QuantityInCashierScreen = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(0);
		String ExpectedQuantityInCashierScreen = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Pieces;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in Cashier Screen ");

		List<String> TotalWithoutTaxInCashierScreen = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(0).replaceAll("[^\\d.]", "").trim();
		String ExpectedTotalWithoutTaxInCashierScreen = ActualTotal;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPage.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPage),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPage+" but got "+ActualTotalWithoutTaxInBillingPage+ " in Cashier Screen ");

		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInBillingPage.replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in Cashier Screen ");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.18;
		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in Cashier Screen ");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ ActualTaxInCashierScreen;
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in Cashier Screen ");

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step17.Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC149SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ActualSubTotalInCashierScreen;
		String TotalDiscount=BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Discount;
		String TotalQtyPcs=BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC149_Pieces;
		String NetValue=NetTotal;
		String Adjustment = "";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationForServiceItem(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TaxableValue,
				NetValue,
				PaymentAmount,
				Adjustment);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}


	// <summary>
	// Test Case Title : Sales return with service item sale
	// Automation ID : TC150
	// Author: Gokul.P
	// </summary>
	public void TC150_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception 
	{		
		//Step1.Login to POS
		//Pre-requisite
		//Take the bill number (receipt number) that has previously been sold.			
		String InvoiceNo = appUtils.GetInvoiceNoAfterNormalSale(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_CustomerNo, BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_SkuList);

		//Step 2.Click on Transaction button		
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to sale button
		//Step 5. Click on customer adjustment button
		//Step 6. Click on return transaction button at the billing screen
		//Step 7. Enter reciept number
		//Step 8. Select return product and click on return button
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_CustomerNo ,UtilityTestData.MenuBarOptions[5]);
		double TotalTaxInBillingScreenPage = 0.0;	
		double ActualTotalNoTax            = 0.0;
		ReturnDetails Details              = appUtils.ReturnMultipleProductsWithDetails(InvoiceNo, BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.ReturnProducts);
		List<String> BillScrnProdTotal     = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("row tillLayout-SelectedLinesFields", "tillLayout-TotalWithoutTaxField textRight"));
		for(int i=0;i<BillScrnProdTotal.size();i++)
		{
			ActualTotalNoTax = Double.parseDouble(BillScrnProdTotal.get(i).replaceAll("[^\\d.-]", ""));
		}            
		TotalTaxInBillingScreenPage=ActualTotalNoTax*0.03;	
		String TransTax               = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SubTot("TaxField","row pad12 padBottom0","h4"));
		double TransactionTax         = Double.parseDouble(TransTax.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(TransactionTax, TotalTaxInBillingScreenPage,1,"In Transaction Line Page, Mismatch in Actual Tax "+TransactionTax+" and Expected "+TotalTaxInBillingScreenPage+" Tax in Billing screen");

		//9.Search Ear Punch charges
		Thread.sleep(5000);	
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Item);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		//10.Click on add item button
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("addItemsToCartCommand"));

		// 11.Enter 
		// 11.1 Pieces
		// 11.2 Rate
		// 11.3 Click enter button
		// Expected: 11.3 Check calculation
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("piecesInput"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Pieces);
		Thread.sleep(2000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Rate);    		
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"), "ENTER");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		double EarPunchTax= Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Rate)*0.18;
		double CalculatedCvalue = Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Pieces)*Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Rate);
		String ExpectedCvalue   = String.format("%.2f", CalculatedCvalue);
		Thread.sleep(2000);
		String ItemNameInItemDetailsge = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ActualCvalue            = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		Thread.sleep(2000);
		String ActualTotal = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));    		
		asrt.assertEquals(ActualCvalue, ExpectedCvalue,"Cvalue mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");
		asrt.assertEquals(ActualTotal, ExpectedCvalue,"Total value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualTotal+" in item details page");

		// 12.Click on add to cart
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(1000);	
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		appUtils.HandleSpecifyRateModalInRecall();
		TotalTaxInBillingScreenPage=TotalTaxInBillingScreenPage+EarPunchTax;

		// 13. Save Estimate
		// Expected :  Verify performa invoice
		String ActualSubTotalInBillingPage    = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualTotalAmountInBillingPage = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualLinesCountInBillingPage  = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));		
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String TaxInEstimationPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_TotWithoutTax("text: viewModel.TotalTax"));

		asrt.assertEquals(Double.parseDouble(TaxInEstimationPge), TotalTaxInBillingScreenPage,1, "Tax value mismatch: Expected is "+TotalTaxInBillingScreenPage+" but got "+TaxInEstimationPge+" in estimation page");

		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC150_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = String.valueOf(TotalTaxInBillingScreenPage);
		String NetTotal            = ActualTotalAmountInBillingPage;
		String TaxableValue        = ActualSubTotalInBillingPage;
		String InvoiceTotal        = ActualTotalAmountInBillingPage;
		String TotalAmount         = ActualTotalAmountInBillingPage;
		String LinesCount          = ActualLinesCountInBillingPage;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmount
				);

		//14.Recall estimate from cashier screen
		//15.Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
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
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//16.Select the Transaction Type as Sales return
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_TransactionSalesReturn);

		//17.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//18.Choose a sales representative
		//19.Click on the Amount
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//20. Post sales return invoice
		//Expected: Verify sales return invoice
		String ExpectedAmountForSaleReturnInBillingPge = base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink")).replace("-", "").replace("\u20B9", "").replace(",", "").trim();
		appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
				UtilityTestData.SalePersonNumber,
				UtilityTestData.DueYear,
				UtilityTestData.DobYear,
				UtilityTestData.NomineeName,
				UtilityTestData.NomineeRelation);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "NORMAL ADV. INVOICE"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Map<String, String> AdvanceReceiptVoucherDetails = pdfUtils.NormalAdvancePdfValidation();
		String ActualAdvanceReceived = AdvanceReceiptVoucherDetails.get("AdvanceReceived");
		String ActualNetReceived = AdvanceReceiptVoucherDetails.get("NetAdvance");
		String ActualPaymentMode = AdvanceReceiptVoucherDetails.get("PaymentMode");
		String PaymentAmount = AdvanceReceiptVoucherDetails.get("PaymentAmount");
		String ApproxWeight = AdvanceReceiptVoucherDetails.get("ApproxWeight");
		String ActualFixedGoldRate = AdvanceReceiptVoucherDetails.get("FixedGoldRate");
		String ActualDepositType = AdvanceReceiptVoucherDetails.get("DepositType");
		asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualAdvanceReceived,"Advance value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualAdvanceReceived+" in Advance receipt");
		asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, ActualNetReceived,"Net value mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+ActualNetReceived+" in Advance receipt");
		asrt.assertEquals(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_PaymentMode, ActualPaymentMode,"Payment mode mismatch: Expected is "+BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_PaymentMode+" but found "+ActualPaymentMode+" in Advance receipt");
		asrt.assertEquals(ExpectedAmountForSaleReturnInBillingPge, PaymentAmount,"Payment Amount mismatch: Expected is "+ExpectedAmountForSaleReturnInBillingPge+" but found "+PaymentAmount+" in Advance receipt");
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//21. Again recall estimate from cashier screen
		//22. Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
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
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//23.Select the Transaction Type as Sales 
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_TransactionSales);

		//24.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//25.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();

		// 26.Click on the Amount
		// 27.Select a Payment method(Cash or Card)
		// Expected:26..Cashier Screen after recall
		// No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Sub-total,TAX,Total Amount
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen  = Integer.toString(AllProductRowsInCashierScreen.size());
		String ActualLinesCountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("LinesField", "h4"));
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is "+ ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in Cashier Screen ");

		List<String> ItemNameInCashierScreen = base.GetElementTexts(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
		String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(0);
		String ExpectedItemNameInCashierScreen = ItemNameInItemDetailsge.split(" - ")[1].trim();
		asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen,"Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got "+ ActualItemNameInCashierScreen  + " in Cashier Screen ");

		List<String> QuantityInCashierScreen = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
		String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(0);
		String ExpectedQuantityInCashierScreen = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Pieces;
		asrt.assertEquals(Double.parseDouble(ActualQuantityInCashierScreen), Double.parseDouble(ExpectedQuantityInCashierScreen), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInCashierScreen + " but got " + ActualQuantityInCashierScreen + " in Cashier Screen ");

		List<String> TotalWithoutTaxInCashierScreen = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(0).replaceAll("[^\\d.]", "").trim();
		String ExpectedTotalWithoutTaxInCashierScreen = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Rate;
		asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInCashierScreen+" but got "+ActualTotalWithoutTaxInCashierScreen+ " in Cashier Screen ");

		String ExpectedSubTotalInCashierScreen = ActualTotalWithoutTaxInCashierScreen.replaceAll("[^\\d.]", "").trim();
		String TaxableValueServiceTax=ExpectedSubTotalInCashierScreen;
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in Cashier Screen ");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)*0.18;
		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in Cashier Screen ");
		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen)+ ActualTaxInCashierScreen;		
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//28.Post the Invoice
		// Expected:Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC150_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = ActualSubTotalInCashierScreen;
		String TotalDiscount=BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Discount;
		String TotalQtyPcs=BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC150_Pieces;
		String NetValue=String.valueOf(ExpectedTotalAmountInCashierScreen);
		String Payment="";
		String Adjustment =String.valueOf(ExpectedTotalAmountInCashierScreen);
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationForServiceItem(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				TaxableValueServiceTax,
				NetValue,
				Payment,
				Adjustment);		
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Sale any item and apply gift coin
	// Automation ID : TC153
	// Author: Aiswarya
	// </summary>
	public void TC153_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		//Step1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		double CurrentGoldRate22k = appUtils.FetchCurrentBoardRate("22k");
		double RequiredSaleQty1 = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_SaleAmount1 / CurrentGoldRate22k;
		double RequiredSaleQty2 = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_SaleAmount2 / CurrentGoldRate22k;
		System.out.println("Required Sale Quantity"+RequiredSaleQty1);
		System.out.println("Required Sale Quantity"+RequiredSaleQty2);


		//Step3. Select customer
		//Step4. Click on add to estimation button
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step4. Scan SKU and click on Add to cart button
		RequiredSKUResult SkuGoldSaleItem = appUtils.GetRequiredQuantitySKUInRange(
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_TransferType,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_FromCounter,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_MetalType,
				RequiredSaleQty1,
				RequiredSaleQty2,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_SkuGoldCount);
		List<String> SkuList = SkuGoldSaleItem.RequiredQtySKUs;
		appUtils.HamBurgerButtonClick("iconShop");
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		for (int i = 0; i < SkuList.size(); i++) {
			int SkuNumber = i + 1;
			String Sku = SkuList.get(i);
			appUtils.SKUIngridentTableValues(Sku, SkuNumber, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuNumber + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuNumber);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuNumber);
		}

		String SkuAmount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();  
		double SkuTotalAmount = Double.parseDouble(SkuAmount);
		double CoinQuantity = 0.0;		
		if      (SkuTotalAmount >= 50000   && SkuTotalAmount <= 100000)   CoinQuantity = 0.10;
		//		else if (SkuTotalAmount >= 90001   && SkuTotalAmount <= 190000)  CoinQuantity = 0.20;
		//		else if (SkuTotalAmount >= 190001  && SkuTotalAmount <= 290000)  CoinQuantity = 0.30;
		//		else if (SkuTotalAmount >= 290001  && SkuTotalAmount <= 390000)  CoinQuantity = 0.40;

		//Step5. Search cgc or silver coin
		//Step6. Choose the required item
		//Step7. Click on add item after adding the purity
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_GiftCoin);
		Thread.sleep(3000);
		base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
		Thread.sleep(3000);
		List<String> ProductNumberList = base.GetElementTexts(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_ProductList("dataListLine","CGC_")); 
		for (int i = 0; i < ProductNumberList.size(); i++) {
			int ProductNumber = i + 1;
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
			Thread.sleep(3000);
			base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),ProductNumberList.get(i));
			Thread.sleep(3000);
			base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
			Thread.sleep(3000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",ProductNumberList.get(i)));
			Thread.sleep(5000);
			if (base.isPresent(LoginPageObj.Ele_ErrorMessage("messageText h5"))) 
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			}
			Thread.sleep(3000);
			base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
			Thread.sleep(7000);
			if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
			{
				base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
				continue;
			}
			break;
		}

		//Step8. Enter the weight (rate will be autocalculated )
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),Double.toString(CoinQuantity));
		Thread.sleep(3000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),"Enter");
		Thread.sleep(3000);
		String CGCQty = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value");
		String CGCTotal = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value");
		String CGCItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		ProductNames.add(CGCItemName);
		ProductQtyList.add(CGCQty);
		ProductTotalList.add(CGCTotal);

		//Step9. Click on add to cart
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));
		// Check for "Specify Price" modal
		if (base.isPresent(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price"))) {			
			base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
			Thread.sleep(500); 
		} else {
			Thread.sleep(500); 
		}

		String SubTotalBeforeDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();

		//Step10. Go to discounts
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Discounts"));

		//Step11. Click on apply silver gift coin in case of silver or apply gift coin discount in case of gold
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Apply Gift Coin Discount"));
		Thread.sleep(2000); 
		// Check for "Specify Price" modal
		if (base.isElementPresent(driver, NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Specify price"))) {			
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_KeyOne("numpad-control-buttons flexGrow100 flexRow", "1", "1"));
			base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_abc("flexGrow50 flexRow row-enter", "Enter"));
			Thread.sleep(500); 
		} else {
			Thread.sleep(500); 
		}
		String CGCDiscountItemName = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_CGCItemName ;
		String CGCDiscountQty = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_CGCQty;
		String CGCDiscountTotal = "-" + CGCTotal;
		ProductNames.add(CGCDiscountItemName);
		ProductQtyList.add(CGCDiscountQty);
		ProductTotalList.add(CGCDiscountTotal);

		//Expected Result: In billing screen, Note: *No of Product lines *Displayed Item Name *Displayed Quantity *Discount value *Displayed TOTAL (without Tax) *Displayed Subtotal *TAX *Total Amount
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String Discount 			= TransactionDataLine.get("Discount");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		asrt.assertEquals(ActualLinesCount,ExpectedLinesCount,"Lines count mismatch: Expected is "+ExpectedLinesCount+" but found "+ActualLinesCount+" in billing page");		

		Thread.sleep(5000);;
		String ActualSubTotalAfterDiscount = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replace("₹", "").replace(",", "").trim();
		double ExpectedSubtotal = Double.parseDouble(SubTotalBeforeDiscount) + Double.parseDouble(ProductTotalList.get(2));
		asrt.assertEquals(Double.parseDouble(ActualSubTotalAfterDiscount),ExpectedSubtotal,1.00,"Subtotal after applying coin discount mismatch: Expected is "+ExpectedSubtotal+" but found "+ActualSubTotalAfterDiscount+" in billing page");		

		double SubTotalSum = 0.00;
		for(int i =0; i < AllProductRows.size() ;i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();
			String ExpectedItemNameInBillingPge = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//asrt.assertEquals(ActualItemNameInBillingPge,ExpectedItemNameInBillingPge, "Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");			
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected is "+ExpectedItemNameInBillingPge+" but got "+ActualItemNameInBillingPge+" in billing page");

			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight","h5"));
			String ActualQuantityInBillingPge = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInBillingPge = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge,ExpectedQtyInBillingPge,1.000,"Quantity mismatch: Expected is "+ExpectedQtyInBillingPge+" but got "+ActualQtyInBillingPge+" in billing page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight","h5"));
			String ActualTotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String TotalWithoutTaxInBillingPge = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();	
			SubTotalSum = SubTotalSum + Double.parseDouble(TotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTaxInBillingPge+" but got "+ActualTotalWithoutTaxInBillingPge+" in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f",SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge),1.0,"Subtotal mismatch: "+ExpectedSubTotalInBillingPge+" but found "+ActualSubTotalInBillingPge+" in billing page");

		String ActualTaxInBillingPge = Tax.replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(ActualTaxInBillingPge);
		String TaxableValue = Double.toString(Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1)));
		double ExpectedTax = (Double.parseDouble(ProductTotalList.get(0))+Double.parseDouble(ProductTotalList.get(1)))*0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f",ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge),1.0,"Tax amount mismatch: "+ExpectedTaxInBillingPge+" but found "+ActualTaxInBillingPge+" in billing page");

		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge = Double.parseDouble( base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge,ExpectedTotalAmountInBillingPge,1.0,"Total Amount mismatch: "+ExpectedTotalAmountInBillingPge+" but found "+ActualTotalAmountInBillingPge+" in billing page");
		String TotalGrossWt = String.format("%.3f", Double.parseDouble(ProductQtyList.get(0)) + Double.parseDouble(ProductQtyList.get(1)));


		//Step12. Click on Save to estimation button
		//Step13. save esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		Thread.sleep(5000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Save To Estimate"));
		} 
		String EstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_AppBarView_appBarCommandCommand"));
		Thread.sleep(4000);

		//Expected Result: Check values in estimate print
		String FirstInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC153_ProformaInvoice");
		Thread.sleep(4000);
		String ProformaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + FirstInvoice;
		String Gst                 = Double.toString(ActualTax);
		String InvoiceTotal = Double.toString(Double.parseDouble(TaxableValue)+(ActualTax));
		String AdjustmentAmount = ProductTotalList.get(1);
		String NetTotalPdf 		   = Double.toString(Double.parseDouble(InvoiceTotal) - Double.parseDouble(AdjustmentAmount));
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(
				ProformaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				AdjustmentAmount,
				NetTotalPdf,
				"");

		//Step14. Recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"),EstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");

		//Step15. Select estimate and click on recall estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell",EstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		String ExpectedEstmnNumber= base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		Assert.assertEquals(EstmnNumber, ExpectedEstmnNumber,"Mismatch in Estimation number Actual is "+EstmnNumber+" and Expected Estimation number"+ExpectedEstmnNumber+ "in Recall Page"); 

		//Step16. Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_TransactionTypeSales);

		//Step17. Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		appUtils.HandleSpecifyRateModalInRecall();

		//Step18. Choose a sales representative
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Expected Result:Note:Cashier Screen after recall *No of Product lines *Displayed Item Name *Displayed Quantity *Displayed TOTAL (without Tax) *Displayed Subtotal *Discounts *TAX *Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalInCashierScreen = TransactionDataLineInCashierScreen.get("Subtotal"); 
		String NetTotalInCashierScreen = TransactionDataLineInCashierScreen.get("NetTotal"); 
		String DiscountInCashierScreen = TransactionDataLineInCashierScreen.get("Discount"); 
		String TotalNetWeightInCashierScreen = TransactionDataLineInCashierScreen.get("NetWeight"); 
		String TaxInCashierScreen = TransactionDataLineInCashierScreen.get("Tax"); 
		String TotalAmountInCashierScreen = TransactionDataLineInCashierScreen.get("TotalAmount"); 
		String ActualLinesCountInCashierScreen = TransactionDataLineInCashierScreen.get("LinesCount");

		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCountInCashierScreen = Integer.toString(AllProductRowsInCashierScreen.size());
		asrt.assertEquals(ActualLinesCountInCashierScreen, ExpectedLinesCountInCashierScreen, "Lines count mismatch: Expected is " + ExpectedLinesCountInCashierScreen + " but found " + ActualLinesCountInCashierScreen + " in cashier screen");

		double SubTotalSumInCashierScreen = 0.00;
		for (int i = 0; i < AllProductRowsInCashierScreen.size(); i++) {
			List<WebElement> ItemNameInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInCashierScreen = ItemNameInCashierScreen.get(i).getText(); String ExpectedItemNameInCashierScreen = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
			//asrt.assertEquals(ActualItemNameInCashierScreen, ExpectedItemNameInCashierScreen, "Item name mismatch: Expected is " + ExpectedItemNameInCashierScreen + " but got " + ActualItemNameInCashierScreen + " in cashier screen");
			asrt.assertTrue(ExpectedItemNameInCashierScreen.contains(ActualItemNameInCashierScreen),"Item name mismatch: Expected is "+ExpectedItemNameInCashierScreen+" but got "+ActualItemNameInCashierScreen+" in cashier screen");

			List<WebElement> QuantityInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInCashierScreen = QuantityInCashierScreen.get(i).getText().replace("-", ""); String ExpectedQuantityInCashierScreen = ProductQtyList.get(i).replace("-", "");
			double ActualQtyInCashierScreen = Double.parseDouble(ActualQuantityInCashierScreen); double ExpectedQtyInCashierScreen = Double.parseDouble(ExpectedQuantityInCashierScreen);
			asrt.assertEquals(ActualQtyInCashierScreen, ExpectedQtyInCashierScreen, 1.000, "Quantity mismatch: Expected is " + ExpectedQtyInCashierScreen + " but got " + ActualQtyInCashierScreen + " in cashier screen");

			List<WebElement> TotalWithoutTaxInCashierScreen = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim(); String TotalWithoutTaxValueInCashierScreen = TotalWithoutTaxInCashierScreen.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSumInCashierScreen += Double.parseDouble(TotalWithoutTaxValueInCashierScreen);
			String ExpectedTotalWithoutTaxInCashierScreen = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInCashierScreen), Double.parseDouble(ExpectedTotalWithoutTaxInCashierScreen), 1.0, "TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInCashierScreen + " but got " + ActualTotalWithoutTaxInCashierScreen + " in cashier screen");
		}

		String ExpectedSubTotalInCashierScreen = String.format("%.2f", SubTotalSumInCashierScreen).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen), Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0, "Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen + " in cashier screen");

		String ActualTaxInCashierScreen = TaxInCashierScreen.replaceAll("[^\\d.]", "").trim(); double ActualTaxValueInCashierScreen = Double.parseDouble(ActualTaxInCashierScreen);
		double ExpectedTaxValueInCashierScreen = (Double.parseDouble(ProductTotalList.get(0)) + Double.parseDouble(ProductTotalList.get(1))) * 0.03; String ExpectedTaxInCashierScreen = String.format("%.2f", ExpectedTaxValueInCashierScreen);
		asrt.assertEquals(Double.parseDouble(ActualTaxInCashierScreen), Double.parseDouble(ExpectedTaxInCashierScreen), 1.0, "Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen + " in cashier screen");

		double ExpectedTotalAmountInCashierScreen = Double.parseDouble(ActualSubTotalInCashierScreen) + Double.parseDouble(ActualTaxInCashierScreen);
		double ActualTotalAmountValueInCashierScreen = Double.parseDouble(base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replace("₹", "").replace(",", "").trim());
		asrt.assertEquals(ActualTotalAmountValueInCashierScreen, ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: " + ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountValueInCashierScreen + " in cashier screen");


		//Step19. Click on the Amount
		//Step20. Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step21. Post the Invoice
		//Expected Result: Check final invoice details * save receipt id for future reference
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC153_SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String TotalNetWt = TotalGrossWt;
		pdfUtils.SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
				InvoicePdfPath,
				TaxableValue,
				Discount,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC153_TotalQtyPcs,
				TotalNetWt,
				TotalGrossWt,
				TaxableValue,
				NetTotalPdf,
				PaymentAmount,
				AdjustmentAmount,
				SkuList,
				ScannedSkuDataMap
				);

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder(); 	


	}


	// <summary>
	// Test Case Title : Sales return with service item sale
	// Automation ID : TC152
	// Author: Nandagopan
	// </summary>
	public void TC152_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		//Step 1 :Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		WebDriverWait wait                               = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebDriverWait HamBurgerWait                      = new WebDriverWait(driver, Duration.ofSeconds(5));	
		appUtils.HamBurgerButtonClick("iconHome");

		//Step:2 Click on POS report
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

		// Step 3:Click on stock summary Report
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(5000);

		//Step 4: Click on View Report
		//Step 5:Check the gross weight for bulk (BC/HK)
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryBeforeSalePath = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_Before_Sale");
		String StockSummaryBeforeSalePDF = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryBeforeSalePath;
		String BackChainGrossWeight = pdfUtils.StockSummarySale1(StockSummaryBeforeSalePDF, "Back Chain");
		String HookGrossWeight = pdfUtils.StockSummarySale1(StockSummaryBeforeSalePDF, "Hook");

		//Step 6: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 7. Select customer and Click on add to estimation button		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC152_CustomerNo,UtilityTestData.MenuBarOptions[5]);
		Thread.sleep(8000);

		//Step 8.Search HK, select Hook
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"HK");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		//Step 9.Select configuration, and click on Add item
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.HKProduct));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);				
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
		Thread.sleep(3000);

		//	9.1.Provide Gross weight
		// 9.2.Click Enter
		//9.3.Click on Add to cart
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC152_Grosswgt);		
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Edt_Alert("Add to Cart"));	
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(2000);

		//Step 10: Click on Save to estimation button
		//Step 11:save esimate
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ExpectedEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);

		//Step 12: Recall estimate from cashier Screen.
		//Step 13: Select estimate and click on recall button	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
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
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ExpectedEstmnNumber));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Step 14: Select transaction type as "sales"
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),"Sales");

		//Step 15: Click on "Process estimation" button.
		//Step 16:Choose a sales representative
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		Thread.sleep(5000);
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 17: Click on the Amount
		//Step 18: Select a Payment method(Cash or Card)
		Thread.sleep(2000);
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 19: Post the Invoice				
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC152SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		PdfInvoiceDetails Details = pdfUtils.SaleInvoicePdfGrossWeightAndInvoiceNo(InvoicePdfPath,"Hook");
		String InvoiceNo = Details.InvoiceNo;
		String GrossWeightBeforeSRTaxInvoice = Details.GWeight;
		System.out.println("Invoice No of Sale → " + InvoiceNo);
		System.out.println("Gross Weight After Sale → " + GrossWeightBeforeSRTaxInvoice);
		Thread.sleep(5000);
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("primaryButton", "Close"));
		Thread.sleep(5000);

		//Step 20:Repeat steps 2-5
		//Expected: 20. Verify Stock summary details
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

		String StockSummaryAfterSalePath = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_After_Sale");
		String StockSummaryAfterSalePDF = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryAfterSalePath;

		// After sale values
		String BackChainGrossWeightSale = pdfUtils.StockSummarySale1(StockSummaryAfterSalePDF, "Back Chain");
		String HookGrossWeightSale = pdfUtils.StockSummarySale1(StockSummaryAfterSalePDF, "Hook");
		System.out.println("HookGrossWeight → " + HookGrossWeightSale);
		System.out.println("BackChainGross Weight → " + BackChainGrossWeightSale);
		double ExpectedGrossWgtAfterSale = Double.parseDouble(HookGrossWeight)-Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC152_Grosswgt);
		System.out.println("Expetced Grossweight after sale: " + ExpectedGrossWgtAfterSale);

		asrt.assertEquals(Double.parseDouble(HookGrossWeightSale),ExpectedGrossWgtAfterSale, " Grossweight mismatch: Expected is " + ExpectedGrossWgtAfterSale + " but got " + HookGrossWeightSale + "in Stock summary details after sale");
		Thread.sleep(3000);

		//					Step 21.Search customer and add to estimation
		//					Step 22. Click on customer adjustment button
		//					Step 23. Click on return transaction button at the billing screen
		//					Step 24. Enter reciept number
		//					Step 25.Select return product and click on return button
		//					Step 26. Click on Save to estimation button
		//					Step 27. save esimate
		//					Step 28.Recall estimate from cashier screen
		//					Step 29. Select estimate and click on recall estimation
		//					Step 30.Select the Transaction Type as Sales
		//					Step 31.Click on Process Estimation
		//					Step 32.Choose a sales representative
		//					Step 33.Click on the Amount
		//					Step 34.Select convert to Advance
		//					Step 35.Post the Invoice
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SalesReturnFlowBulkItem(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC152_CustomerNo, InvoiceNo,UtilityTestData.HKProduct );

		//Step 36:Repeat steps 2-5
		//Expected:Verify Stock summary details	
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
		String StockSummaryAfterSaleReturnPath = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_After_Return");
		String StockSummaryAfterSaleReturnPDF = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryAfterSaleReturnPath;

		// After Sale Return values
		String BackChainGrossWeightReturn = pdfUtils.StockSummarySale1(StockSummaryAfterSaleReturnPDF, "Back Chain");
		String HookGrossWeightReturn = pdfUtils.StockSummarySale1(StockSummaryAfterSaleReturnPDF, "Hook");				
		System.out.println("HookGrossWeight after Sale Return → " + HookGrossWeightReturn);
		System.out.println("BackChainGross Weight after Sales Return → " + BackChainGrossWeightReturn);
		double ExpectedGrossweightAfterSR= Double.parseDouble(HookGrossWeightSale)+Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC152_Grosswgt);
		System.out.println("ExpectedGrossweight after Sale Return " + ExpectedGrossweightAfterSR);

		asrt.assertEquals(Double.parseDouble(HookGrossWeightReturn),ExpectedGrossweightAfterSR, " Grossweight mismatch: Expected is " + ExpectedGrossweightAfterSR + " but got " + HookGrossWeightReturn + "in Stock summary details after Sale Return");
		Thread.sleep(3000);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}



	// <summary>
	// Test Case Title : Sales return CGC coin and check the summary
	// Automation ID : TC154
	// Author: Neethu
	// </summary>

	public void TC154_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		//Step 1 :Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		WebDriverWait wait                               = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebDriverWait HamBurgerWait                      = new WebDriverWait(driver, Duration.ofSeconds(5));	
		appUtils.HamBurgerButtonClick("iconHome");

		//Pre-Condition: Take the bill number (receipt number) that has previously been sold.
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC154_CustomerNo, UtilityTestData.MenuBarOptions[0]);
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC154_CGC);
		Thread.sleep(3000);
		base.excuteJsClick(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_CustomerSearch("Products"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ConvtAdv("column3","Complement Gold Coin 0.100g"));
		Thread.sleep(10000);
		double CoinQuantity = Double.parseDouble(UtilityTestData.CGCQuantity);

		if (base.isPresent(LoginPageObj.Ele_ErrorMessage("messageText h5"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}

		base.excuteJsClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(7000);
		if (base.isPresent(LoginPageObj.Btn_SignInButton("OK"))) 
		{
			base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		}

		//Enter the weight (rate will be autocalculated )
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),Double.toString(CoinQuantity));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),"Enter");
		Thread.sleep(3000);
		String CGCQty = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "value");
		String CGCTotal = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"), "value");
		String CGCItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));

		//Click on add to cart
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(1000);
		base.excuteJsClick(LoginPageObj.Edt_Alert("Add to Cart"));

		if (base.isExists(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.setZoom(driver, 40);
			wait.until(ExpectedConditions.elementToBeClickable(
					NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220","1"))).click();
		} else {
			System.out.println("SaleRep not required; continuing execution");
		}

		//Click on the Amount and Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");		
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		String TaxInvoiceName = PdfUtilities.DownloadAndRenameLatestPDF("Method_TaxInvoice");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		String TaxInvoice =System.getProperty("user.dir") + "\\Invoices\\"+TaxInvoiceName+"";	
		String InvoiceNo = pdfUtils.ExtractReceiptNoFrmSaleInvoice(TaxInvoice);
		System.out.println("The Invoice No: "+InvoiceNo);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));


		//Step:2 Click on POS report
		Thread.sleep(3000);
		appUtils.HamBurgerButtonClick("iconHome");

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

		// Step 3:Click on stock summary Report
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(10000);

		//Step 4: Click on View Report button
		//Step 5:Check the gross weight for Gold Coin(CO)
		//Note down the initial and final value in report
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryAfterSalePath = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_Before_Sale");
		String StockSummaryAfterSalePDF = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryAfterSalePath;
		String CGCIntialGrossWt = pdfUtils.StockSummarySaleReturn(StockSummaryAfterSalePDF, "Complement Gold Coin 0.100g");
		String CGCFinalGrossWt = pdfUtils.StockSummarySaleReturn(StockSummaryAfterSalePDF, "Complement Gold Coin 0.100g");
		System.out.println("CGCIntialGrossWt after sales: "+CGCIntialGrossWt);
		System.out.println("CGCIntialGrossWt after sales: "+CGCFinalGrossWt);


		//Step 6: Go to transactions
		//Step 7: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 8: Select customer 
		//Step 9: Click on add to Sale button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC154_CustomerNo, UtilityTestData.MenuBarOptions[0]);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Step 10: Click on customer adjustment button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Customer Adjustments"));

		//Step 11: Click on return transaction button at the billing screen
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Return transaction"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Btn_abc("numpad-control alphanumeric-mode flexCol", "Enable text editing"))).click();

		//Step 12: Enter reciept number
		base.setData(NormalSaleGoldAndSilverObj.Edt_TextField("numpad-control alphanumeric-mode flexCol", "text"), InvoiceNo);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_IconReturnKey("numpad-control-buttons flexGrow100 flexRow", "content-return"));

		//Step 13: Select return product (CGC coin)and click on return button
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

		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_Transaction("row", "Complement Gold Coin 0.100g"));
		Thread.sleep(5000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_return("Return"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(5000);

		//Step 14: Click amount
		//Step 15: select payment method as Convert to Advance
		appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
				UtilityTestData.SalePersonNumber,
				UtilityTestData.DueYear,
				UtilityTestData.DobYear,
				UtilityTestData.NomineeName,
				UtilityTestData.NomineeRelation);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("cancelButton primaryButton","Close"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));

		//Step 16: post the invoice
		Map<String, String> Result = pdfUtils.NormalAdvancePdfValidation();
		Thread.sleep(10000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		Thread.sleep(5000);

		//Step 17: Click on POS reports
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
			}catch(Exception e) {

			}	

		}	

		//Step 18: Click on stock summary report
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(5000);

		//Step 19: Click on view report button
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

		//Step 20: Check the gross weight for Gold coin.
		//Expected: Verify stock summery
		String StockSummaryAfterSaleReturn = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_After_Sale");
		String StockSummaryAfterSaleReturnPDF = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryAfterSaleReturn;

		// After sale return values
		String CGCGrossWeightSale = pdfUtils.StockSummarySaleReturn(StockSummaryAfterSaleReturnPDF, "Complement Gold Coin 0.100g");
		System.out.println("BackChainGross Weight after sales return " + CGCGrossWeightSale);
		double ExpectedGrossWgtAfterSaleReturn = Double.parseDouble(CGCIntialGrossWt)+CoinQuantity;
		double ExpectedFinalGrossWgtAfterSaleReturn = Double.parseDouble(CGCFinalGrossWt)+CoinQuantity;

		System.out.println("Expetced Grossweight after sale: " + ExpectedGrossWgtAfterSaleReturn);
		System.out.println("Expetced FinalGrossweight after sale: " + ExpectedFinalGrossWgtAfterSaleReturn);

		asrt.assertEquals(Double.parseDouble(CGCGrossWeightSale),ExpectedGrossWgtAfterSaleReturn, " Grossweight mismatch: Expected is " + ExpectedGrossWgtAfterSaleReturn + " but got " + CGCGrossWeightSale + "in Stock summary details after Sales Return");
		asrt.assertEquals(Double.parseDouble(CGCGrossWeightSale),ExpectedFinalGrossWgtAfterSaleReturn, " Grossweight mismatch: Expected is " + ExpectedFinalGrossWgtAfterSaleReturn + " but got " + CGCGrossWeightSale + "in Stock summary details after Sales Return");

		Thread.sleep(5000);	
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();


	}

	// <summary>
	// Test Case Title : Bulk item sale with SKU
	// Automation ID : TC147
	// Author: Bharath
	// </summary>
	public void TC147_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Select customer
		//Step 4: Click on add to estimate button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_CustomerNo, UtilityTestData.MenuBarOptions[5]);
		base.WaitforElementToBePresentWithTimeout(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_TransactionPage("splitViewTitle","Transaction"), 60);

		//Step 5: Search HK, select Hook			
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Edt_Name("Search"), 60);
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"HK");
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"), 60);
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		//Step 6: Select configuration, and click on Add item
		Thread.sleep(5000);
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.HKProduct));
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), 40);
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		if(base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("OK"),10))
		{
			base.UnifiedClick(LoginPageObj.Btn_SignInButton("OK"));
		}
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);
		Thread.sleep(3000);
		if(base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("OK"),50))
		{
			base.UnifiedClick(LoginPageObj.Btn_SignInButton("OK"));
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(3000);

		//Step 6.1: Provide Gross weight
		// Step 6.2: Click Enter
		//Step 6.3: Click on Add to cart
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();

		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), 50);
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_HKGrosswgt);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"), 20);
		String HkItemName = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		int Retry=0;
		String HkTotalAmount="";
		while(Retry<5) {
			Thread.sleep(5000);
			HkTotalAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
			if(HkTotalAmount.isBlank()||HkTotalAmount.isEmpty()||HkTotalAmount.equals("0.00"))
			{
				Retry++;
				continue;
			}else {break;}
		}
		ProductNames.add(HkItemName);
		ProductTotalList.add(HkTotalAmount);
		ProductQtyList.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_HKGrosswgt);		
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(2000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Add to Cart"));	
		Thread.sleep(2000);
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"), 50);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(2000);

		//			Step 7: Scan an SKU and click on Add to cart button
		//			Expected: Check calculation
		//			Expected:  Note:Billing Screen
		//			 *No of Product lines
		//			 *Displayed Item Name
		//			 *Displayed Quantity
		//			 *Displayed TOTAL (without Tax)
		//			 *Displayed Subtotal
		//			 *TAX
		//			 *Total Amount
		int SkuCounter = 1;
		List<String> SkuList  = appUtils.SelectMultipleSKUs(
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_SkuGoldCount,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_TransferType,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_FromCounter,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_MetalType				
				);
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
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

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLine, ProductNames, ProductQtyList, ProductTotalList, ActualLinesCount);

		//Step 8: Select any one of the sales agent, Click on Save to estimation button, Save esimate.
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));			
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC149_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= Tax;
		String NetTotal = TotalAmount;
		String TaxableValue=SubTotal;
		String InvoiceTotal=TotalAmount;
		String LinesCount = ActualLinesCount;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmount
				);

		//Step 9: Recall estimate from cashier screen
		//Step10: Select estimate and click on save to estimate button
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

		//Step 11: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_TransactionType);

		//Step 12: Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) {
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		appUtils.HandleSpecifyRateModalInRecall();  

		//Step 14: Click on the Amount
		//Note: Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, AX, Total Amount
		//Step 15: Select a Payment method(Cash or Card)
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalCashierScreen		 		= TransactionDataLineInCashierScreen.get("Subtotal");
		String DiscountCashierScreen 			= TransactionDataLineInCashierScreen.get("Discount");
		String TotalNetWeightCashierScreen   	= TransactionDataLineInCashierScreen.get("NetWeight");
		String TaxCashierScreen 					= TransactionDataLineInCashierScreen.get("Tax");
		String TotalAmountCashierScreen 			= TransactionDataLineInCashierScreen.get("TotalAmount");
		String ActualLinesCountCashierScreen		= TransactionDataLineInCashierScreen.get("LinesCount");

		appUtils.ValidateBillingPgeAndCasierScreen(TransactionDataLineInCashierScreen, ProductNames, ProductQtyList, ProductTotalList, "Cashier");

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step 16: Post the Invoice
		//Expected: Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC147SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = SubTotalCashierScreen;
		String TotalDiscount=BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_Discount;
		String TotalQtyPcs=Integer.toString(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_SkuGoldCount);
		String NetValue=TotalAmountCashierScreen;
		String Adjustment = "";
		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationForServiceItem(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				SubTotal,
				NetValue,
				PaymentAmount,
				Adjustment);			
		System.out.println(PdfInvoiceNo+"And Estimation Number"+ActualEstmnNumber);
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlowBulkItem(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC147_CustomerNo, PdfInvoiceNo,UtilityTestData.HKProduct);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}	

	// <summary>
	// Test Case Title : Sale with bulk item,service item and SKU
	// Automation ID : TC151
	// Author: Vishnu RR
	// </summary>

	public void TC151_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		//Step1.Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre-Requisite for taking BoardRate
		double ErpGoldRate22k = appUtils.FetchCurrentBoardRate(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Purity);

		//Step2. Click on Transaction button
		//Step3. Select customer
		//Step4. Click on add to estimation button
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step5.Search any service item
		Thread.sleep(5000);	
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Item);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		//Error Popup Handing
		if (base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("Ok"), 2)) {
			base.buttonClick(LoginPageObj.Btn_SignInButton("Ok"));
		}

		//Step6.Select required item(eg. ear punching)-->click add item
		base.WaitforElementToBePresentWithTimeout(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"), 10);
		base.UnifiedClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("addItemsToCartCommand"));

		//Step7.Enter 
		//Step7.1 Pieces
		Thread.sleep(2000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_Name("piecesInput"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Pieces);

		//Step 7.2 Rate
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"));
		base.clearData((NormalSaleGoldAndSilverObj.Ele_Name("rateInput")));
		Thread.sleep(3000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Rate);

		//Step 7.3 Click enter button
		//Expected: Check Ear Punch Charge calculation
		double CalculatedCvalue = Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Pieces)*Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Rate);
		String ExpectedCvalue = String.format("%.2f", CalculatedCvalue);
		Thread.sleep(2000);
		String ItemNameInItemDetailsge = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ActualCvalue = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"));
		Thread.sleep(2000);
		String ActualTotal = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));

		asrt.assertEquals(ActualCvalue, ExpectedCvalue,"Cvalue mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");
		asrt.assertEquals(ActualTotal, ExpectedCvalue,"Total value mismatch: Expected is "+ExpectedCvalue+" but got "+ActualTotal+" in item details page");

		//Step 8: Click on add to cart
		base.ClickCondition(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		Thread.sleep(1000);	
		WebDriverWait AlertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		appUtils.HandleSpecifyRateModalInRecall();

		// 9.Search bulk item and add to cart(eg.hook)
		// 10. Search BC (Back chain)
		Thread.sleep(5000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Item1);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");

		if (base.WaitforElementToBePresentWithTimeout(LoginPageObj.Btn_SignInButton("Ok"), 2)) {
			base.ClickCondition(LoginPageObj.Btn_SignInButton("Ok"));
		}

		// 11. Select configuration & Click on add item button
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", "BC"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),"22k");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebDriverWait Waits = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn = LoginPageObj.Btn_SignInButton("OK");

		if (base.isElementPresent(driver, CartChangedAlert)) {		
			//			WebElement OkButton = Waits.until(ExpectedConditions.elementToBeClickable(OkBtn));
			//			OkButton.click();
			try {
				WebElement OkButton = Waits.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}
			catch (TimeoutException e) {
			}
		}

		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));

		// 12. Enter gross weight
		// 13. Click on enter
		// Expected Result: Check BC Calculation  
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_GrossWeight);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"));
		Thread.sleep(2000);
		double CalculatedCvalue1 = Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_GrossWeight)*ErpGoldRate22k;
		String ExpectedCvalue1 = String.format("%.2f", CalculatedCvalue1);;
		Thread.sleep(2000);
		String ItemNameInItemDetailsge1 = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		String ActualCvalue1 = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"));
		Thread.sleep(2000);
		String Actualtotal =   base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));  
		String WastageAmount =   base.GetValue(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Ele_MakingDiscountAmount("makingDiscAmt", "WsDiscAmt"));
		String ExpectedTotal = String.format("%.2f", Double.parseDouble(WastageAmount)+(CalculatedCvalue1));
		Thread.sleep(2000);

		asrt.assertEquals(ActualCvalue1, ExpectedCvalue1,"Cvalue mismatch: Expected is "+ExpectedCvalue+" but got "+ActualCvalue+" in item details page");
		asrt.assertEquals(Actualtotal, ExpectedTotal,"Total value mismatch: Expected is "+ExpectedTotal+" but got "+Actualtotal+" in item details page");

		// 14. Click on add to cart button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		Thread.sleep(3000);

		//15.Scan any SKU and Add to Cart
		//Expected Result: Check Calculation
		SKUResult SkuGoldSaleItem = appUtils.GetOGSaleSku(
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_TransferType,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_FromCounterGold,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_MetalTypeGold,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151RequiredWeight,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_SaleProductCount);
		List<String> SkuList =  SkuGoldSaleItem.PcsRateSKU;
		base.setZoom(driver, 60);
		appUtils.HamBurgerButtonClick("iconShop");

		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames = new ArrayList<>();
		List<String> ProductQtyList = new ArrayList<>();
		List<String> ProductTotalList = new ArrayList<>();
		ProductNames.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_RequiredItem1);
		ProductQtyList.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Pieces);
		ProductTotalList.add(ActualTotal);
		ProductNames.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_RequiredItem2);
		ProductQtyList.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_GrossWeight);
		ProductTotalList.add((ExpectedTotal));

		int SkuCounter = 1;	
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 		= TransactionDataLine.get("Subtotal");
		String NetTotal		 		= TransactionDataLine.get("NetTotal");
		String TotalNetWeight   	= TransactionDataLine.get("NetWeight");
		String Tax 					= TransactionDataLine.get("Tax");
		String TotalAmount 			= TransactionDataLine.get("TotalAmount");
		String ActualLinesCount		= TransactionDataLine.get("LinesCount");

		// Expected Result :In billing screen,Note:No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax) ,Displayed Subtotal ,TAX,Total Amount
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCount = Integer.toString(AllProductRows.size());
		String ActualLinesCounts = TransactionDataLine.get("LinesCount");
		asrt.assertEquals(ActualLinesCounts, ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in Billing page");

		double SubTotalSum = 0.00;
		double ProductTotalAmount = 0.0;
		for (int i = 0; i < AllProductRows.size(); i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemName = ItemName.get(i).getText();
			if (ProductNames.get(i).contains(ActualItemName)&& ActualItemName.length()<ProductNames.get(i).length()) {
				String ExpectedItemName = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();
				asrt.assertTrue(ExpectedItemName.contains(ActualItemName), "Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Billing page");			
				ProductTotalAmount = ProductTotalAmount+Double.parseDouble(ProductTotalList.get(i));
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
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTax.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTax),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTax+" but got "+ActualTotalWithoutTax+ " in Billing  page");
		}
		String ExpectedSubTotal = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotal = TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotal),Double.parseDouble(ExpectedSubTotal), 1.0,"Subtotal mismatch: " + ExpectedSubTotal + " but found " + ActualSubTotal+ " in Billing page");
		String TaxInPge = TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		double ActualTax = Double.parseDouble(TaxInPge);

		String Taxs = String.format("%.2f",
				(Double.parseDouble(ProductTotalList.get(0).replaceAll("[^\\d.-]", ""))*0.18 +
						Double.parseDouble(ProductTotalList.get(1).replaceAll("[^\\d.-]", ""))*0.03 +
						Double.parseDouble(ProductTotalList.get(2).replaceAll("[^\\d.]", ""))*0.03));

		double ExpectedTax = Double.parseDouble(Taxs);
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(ActualTax,ExpectedTax, 1.0,"Tax amount mismatch: " + ExpectedTax + " but found " + ActualTax+ " in Billing page");

		double ExpectedTotalAmount = SubTotalSum+ ActualTax;
		double ActualTotalAmount = Double.parseDouble(TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmount, ExpectedTotalAmount, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmount + " but found " + ActualTotalAmount + " in Billing page");		

		//Step 16: Save Estimate
		//Expected: Verify the Invoice details
		base.ClickCondition(LoginPageObj.Edt_AlertText("Billing"));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Save To Estimate"));
		Thread.sleep(2500);
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC151_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst= String.valueOf(Taxs);
		double TaxableValue= Double.parseDouble(ActualSubTotal);
		String InvoiceTotal = Double.toString(TaxableValue + ExpectedTax);
		String NetTotals = InvoiceTotal;
		String TotalAmounts = TotalAmount.replaceAll("[₹,]", "").trim();

		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				Double.toString(TaxableValue),
				Gst,
				InvoiceTotal,
				NetTotals,
				TotalAmounts
				);

		//Step17.Recall estimate from cashier screen
		//Step18. Select estimate and click on recall estimation
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2", "text semilight primaryFontColor"));
		base.ClickCondition(LoginPageObj.Edt_AlertText("Recall Estimate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.setData(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), ActualEstmnNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Filter"), "ENTER");
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", ActualEstmnNumber));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconGo"));

		//Step19.Select the Transaction Type as Sales
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_TransactionType);

		//Step20.Click on Process Estimation
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Step21.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}
		WebDriverWait AlertWait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		appUtils.HandleSpecifyRateModalInRecall();

		//Step22.Click on the Amount
		//Note:-Cashier Screen after recall No of Product lines, Displayed Item Name, Displayed Quantity, Displayed TOTAL (without Tax), Displayed Subtotal, TAX and  Total Amount
		//Step23.Select a Payment method(Cash or Card)
		List<WebElement> AllProductRowsInCashierScreen = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));
		String ExpectedLinesCounts = Integer.toString(AllProductRowsInCashierScreen.size());
		String ActualLinesCountinCashier = TransactionDataLine.get("LinesCount");
		asrt.assertEquals(ActualLinesCountinCashier, ExpectedLinesCounts, "Lines count mismatch: Expected is "+ ExpectedLinesCounts + " but found " + ActualLinesCountinCashier + " in Cashier page");

		double SubTotalSums = 0.00;
		double ProductTotalAmounts = 0.0;
		for (int i = 0; i < AllProductRowsInCashierScreen.size(); i++) {
			List<WebElement> ItemName = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemName = ItemName.get(i).getText();
			if (ProductNames.get(i).contains(ActualItemName)&& ActualItemName.length()<ProductNames.get(i).length()) {
				String ExpectedItemName = ProductNames.get(i).substring(ProductNames.get(i).indexOf("-") + 1).trim();

				asrt.assertTrue(ExpectedItemName.contains(ActualItemName), "Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Cashier page");			
				ProductTotalAmount = ProductTotalAmount+Double.parseDouble(ProductTotalList.get(i));
			} else {
				String ExpectedItemName = ProductNames.get(i).trim();
				asrt.assertEquals(ActualItemName, ExpectedItemName,"Item name mismatch: Expected is " + ExpectedItemName + " but got "+ ActualItemName + " in Cashier page");
			}
			List<WebElement> Quantity = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantity = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantity = ProductQtyList.get(i);
			double ActualQty = Double.parseDouble(ActualQuantity);
			double ExpectedQty = Double.parseDouble(ExpectedQuantity);
			asrt.assertEquals(ActualQty, ExpectedQty, 1.000, "Quantity mismatch: Expected is "+ ExpectedQty + " but got " + ActualQty + " in Cashier page");

			List<WebElement> TotalWithoutTax = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String TotalAmountWithoutTax = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			String ActualTotalWithoutTax = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.]", "").trim();
			SubTotalSum = SubTotalSum+Double.parseDouble(TotalAmountWithoutTax);
			String ExpectedTotalWithoutTax = String.format("%.2f", Double.parseDouble(ProductTotalList.get(i).replaceAll("[^\\d.]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTax.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTax),1.0,"TotalWithoutTax mismatch: Expected is "+ExpectedTotalWithoutTax+" but got "+ActualTotalWithoutTax+ " in Cashier  page");
		}

		String ExpectedSubTotalInCashierScreen = ActualSubTotal.replaceAll("[^\\d.]", "").trim();;
		String ActualSubTotalInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInCashierScreen),Double.parseDouble(ExpectedSubTotalInCashierScreen), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInCashierScreen + " but found " + ActualSubTotalInCashierScreen+ " in Cashier Screen ");

		String TaxInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		double ActualTaxInCashierScreen = Double.parseDouble(TaxInCashierScreen);
		double ExpectedTaxInCashierScreen = Double.parseDouble(Taxs);
		asrt.assertEquals(ActualTaxInCashierScreen,ExpectedTaxInCashierScreen, 1.0,"Tax amount mismatch: " + ExpectedTaxInCashierScreen + " but found " + ActualTaxInCashierScreen+ " in Cashier Screen ");

		double ExpectedTotalAmountInCashierScreen = (ActualTotalAmount);
		String ActualTotalAmountInCashierScreen = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.-]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualTotalAmountInCashierScreen), ExpectedTotalAmountInCashierScreen, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInCashierScreen + " but found " + ActualTotalAmountInCashierScreen + " in Cashier Screen ");

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		//Step24.Post the Invoice
		//Expected: Check final invoice details
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC151SaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount = Double.toString(TaxableValue);
		String TotalDiscount=BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Discount;
		String TotalQtyPcs=BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC151_Piecespdf;
		String NetValue= PaymentAmount;
		String Taxablevalue=Double.toString(TaxableValue);
		String Adjustment = "";

		String PdfInvoiceNo = pdfUtils.SaleInvoicePdfValidationForServiceItem(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				Taxablevalue,
				NetValue,
				PaymentAmount,
				Adjustment);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();

	}



	/// <summary>
	/// Test Case Title : Bulk item sale HK and BC
	/// Automation ID : TC145
	/// Author: Gokul.P
	/// </summary>
	public void TC145_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		//Step 1 :Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// 2. Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// 3. Select customer		
		// 4.Click on add to estimate button
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_CustomerNo, UtilityTestData.MenuBarOptions[5]);

		// 5.Search HK, select Hook
		Thread.sleep(5000);
		base.ClickClearEnter(NormalSaleGoldAndSilverObj.Edt_Name("Search"),"HK");
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_SearchList("Products"));

		// 6.Select configuration, and click on Add item		
		//	 6.1.Provide Gross weight
		//	 6.2.Click Enter
		//	 6.3.Click on "Add to cart"
		// Expected:Check C-value and total
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_OP("dataListLine",UtilityTestData.HKProduct));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.waitForClickable(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"), 10);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_Configuration("col grow"),UtilityTestData.Purity22);				
		WebDriverWait Wait  = new WebDriverWait(driver, Duration.ofSeconds(8));
		By CartChangedAlert = LoginPageObj.Btn_SignInButton(" Please try again later.");
		By OkBtn            = LoginPageObj.Btn_SignInButton("OK");
		if (base.isElementPresent(driver, CartChangedAlert)) {		
			try 
			{
				WebElement OkButton = Wait.until(ExpectedConditions.elementToBeClickable(OkBtn));
				OkButton.click();
			}catch (TimeoutException e) {}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(LoginPageObj.Btn_SignInButton("Add item"));
		Thread.sleep(3000);		
		base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt);		
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		Thread.sleep(2000);

		//String HkRate         = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("rateInput"));
		String HkRate = base.GetText(NormalSaleGoldAndSilverObj.Ele_ItemRecallEst("gridcell","Rate"));
		double ExpectedCValue = Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt)*Double.parseDouble(HkRate);
		String ActualCvalue   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("valueInput"));

		asrt.assertEquals(ExpectedCValue, Double.parseDouble(ActualCvalue),1.0,"The CValue for HK is not as Expected: "+ExpectedCValue+" but got "+ActualCvalue+" in Item details page");
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String WastageAmountHk     = base.GetValue(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Txt_WastageAmount("makingDiscAmt","WsDiscAmt"));
		double ExpectedTotalAmount = Double.parseDouble(WastageAmountHk)+ExpectedCValue;
		String ActualTotalAmount   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));

		asrt.assertEquals(ExpectedTotalAmount, Double.parseDouble(ActualTotalAmount),1.0,"The Total Amount for HK is not as Expected: "+ExpectedTotalAmount+" but got "+ActualTotalAmount+" in Item details page");

		Thread.sleep(1000);
		base.ClickCondition(LoginPageObj.Edt_Alert("Add to Cart"));	
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));}
		Thread.sleep(5000);

		// 7.Search BC, select Back chain
		base.ClickCondition(NormalSaleGoldAndSilverObj.Edt_Name("Search"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search"), BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_BC);
		base.pressKey(NormalSaleGoldAndSilverObj.Edt_Name("Search"), "ENTER");
		Thread.sleep(2000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Transaction("dataListLine", BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_BC));
		Thread.sleep(6000);

		// 8.Select configuration, and click on Add item
		//   8.1.Provide Gross weight
		//	 8.2.Click Enter				
		//	 8.3.Click on "Add to cart"
		// Expected: 8.2.Check C-value and total
		//           8.3.Check calculation
		base.selectorByVisibleText(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Sel_GPPAccountNo("options: productDimensionValues"), UtilityTestData.Purity22);
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Add item"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt);					
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("grossWeightInput"), "ENTER");
		Thread.sleep(5000);			
		String BcItemName    = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUHeader("text: SectionHeader"));
		base.scrollToElement(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		String BcTotalAmount = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));
		String CvalueRG      = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("dataListLine","column5 textRight"));
		String BcRate        = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("dataListLine","column4 textRight"));
		base.buttonClick(OldPurchaseExchangeOwnorOtherGoldorSilverSaleObj.Btn_ThreeDots("win-commandingsurface-actionarea win-appbar-actionarea"));
		double ExpectedBcCvalue = Double.parseDouble(BcRate) * Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt);

		asrt.assertEquals(Double.parseDouble(CvalueRG), ExpectedBcCvalue,1,"Cvalue mismatch: Expected is " + ExpectedBcCvalue + " but got "+ CvalueRG + "in Item details page");

		String WastageAmountBC       = base.GetValue(BulkItemSalesSalesReturnServiceItemSalesStockSummaryObj.Txt_WastageAmount("makingDiscAmt","WsDiscAmt"));
		double ExpectedTotalAmountBC = Double.parseDouble(WastageAmountHk)+ExpectedCValue;
		String ActualTotalAmountBC   = base.GetValue(NormalSaleGoldAndSilverObj.Ele_Name("totalInput"));

		asrt.assertEquals(ExpectedTotalAmountBC, Double.parseDouble(ActualTotalAmountBC),1.0,"The Total Amount for BC is not as Expected: "+ExpectedTotalAmount+" but got "+ActualTotalAmount+" in Item details page");

		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Add to Cart"));
		if(base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1")))
		{base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));}
		Thread.sleep(2000);

		// 9. Click on Save to estimation button, Save estimate,Recall estimate from cashier screen
		// Expected: Note:Billing Screen:No of Product lines,Displayed Item Name,Displayed Quantity,Displayed TOTAL (without Tax),Displayed Sub-total,TAX,Total Amount
		List<String> ProductName     = new ArrayList<>();
		List<String> ProductQuantity = new ArrayList<>();
		List<String> ProductTotal    = new ArrayList<>();
		ProductName.add(UtilityTestData.HKProduct);
		ProductName.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_BC);
		ProductQuantity.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt);
		ProductQuantity.add(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt);
		ProductTotal.add(ActualTotalAmount);
		ProductTotal.add(ActualTotalAmountBC);			
		List<WebElement> AllProductRows = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));		
		String ExpectedLinesCount       = Integer.toString(AllProductRows.size());
		int  ActualLinesCount           = ProductName.size();
		asrt.assertEquals(String.valueOf(ActualLinesCount), ExpectedLinesCount, "Lines count mismatch: Expected is "+ ExpectedLinesCount + " but found " + ActualLinesCount + " in billing page");

		double SubTotalSum = 0.00;
		for (int i = 0; i < AllProductRows.size(); i++) 
		{
			List<WebElement> ItemName         = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameInBillingPge = ItemName.get(i).getText();

			String ExpectedItemNameInBillingPge = ProductName.get(i).trim();
			asrt.assertTrue(ExpectedItemNameInBillingPge.contains(ActualItemNameInBillingPge),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge + "' to contain actual '" + ActualItemNameInBillingPge + "'");

			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityInBillingPge    = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityInBillingPge  = ProductQuantity.get(i);
			double ActualQtyInBillingPge         = Double.parseDouble(ActualQuantityInBillingPge);
			double ExpectedQtyInBillingPge       = Double.parseDouble(ExpectedQuantityInBillingPge);
			asrt.assertEquals(ActualQtyInBillingPge, ExpectedQtyInBillingPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyInBillingPge + " but got " + ActualQtyInBillingPge + " in billing page");

			List<WebElement> TotalWithoutTax           = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxInBillingPge   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSum                                = SubTotalSum + Double.parseDouble(ActualTotalWithoutTaxInBillingPge);
			String ExpectedTotalWithoutTaxInBillingPge = String.format("%.2f",Double.parseDouble(ProductTotal.get(i).replaceAll("[^\\d.-]", "").trim()));
			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxInBillingPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxInBillingPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxInBillingPge + " but got "+ ActualTotalWithoutTaxInBillingPge + " in billing page");
		}
		String ExpectedSubTotalInBillingPge = String.format("%.2f", SubTotalSum).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge),Double.parseDouble(ExpectedSubTotalInBillingPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalInBillingPge + " but found " + ActualSubTotalInBillingPge+ " in billing page");

		String  Tax= base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualTaxInBillingPge   = String.valueOf(Tax).replaceAll("[^\\d.]", "").trim();
		double ExpectedTax             = (Double.parseDouble(ProductTotal.get(0))+ Double.parseDouble(ProductTotal.get(1))) * 0.03;
		String ExpectedTaxInBillingPge = String.format("%.2f", ExpectedTax);
		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + ActualTaxInBillingPge+ " in billing page");

		String TotalAmount                     = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		double ExpectedTotalAmountInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountInBillingPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountInBillingPge, ExpectedTotalAmountInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountInBillingPge + " but found " + ActualTotalAmountInBillingPge + " in billing page");

		// 10. Select estimate and click on save to estimate button
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));			
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));
		Thread.sleep(2000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC145_ProformaInvoice");
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = Tax;
		String NetTotal            = TotalAmount;
		String TaxableValue        = String.valueOf(SubTotalSum);
		String InvoiceTotal        = TotalAmount;
		String LinesCount          = String.valueOf(ActualLinesCount);
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmount
				);

		// 11.Select the Transaction Type as Sales 
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

		//Step 11: Select the Transaction Type as Sales 
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"),BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_TransactionType);

		// 12.Click on Process Estimation 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 13: Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));}

		// 14.Click on the Amount
		List<WebElement> AllProductRowsCashierPge = base.GetElement(LoginPageObj.Ele_ErrorMessage("listViewLine expandable"));		
		String ExpectedLinesCountCashierPge       = Integer.toString(AllProductRows.size());
		int  ActualLinesCountCashierPge           = ProductName.size();
		asrt.assertEquals(String.valueOf(ActualLinesCountCashierPge), ExpectedLinesCountCashierPge, "Lines count mismatch: Expected is "+ ExpectedLinesCountCashierPge + " but found " + ActualLinesCountCashierPge + " in billing page");

		double SubTotalSumCashierPge = 0.00;
		for (int i = 0; i < AllProductRowsCashierPge.size(); i++) {
			List<WebElement> ItemNameCashierPge = base.GetElement(LoginPageObj.Ele_ErrorMessage("tillLayout-ProductNameField wrapText"));
			String ActualItemNameCashierPge     = ItemNameCashierPge.get(i).getText();
			String ExpectedItemNameCashierPge = ProductName.get(i).trim();			
			asrt.assertTrue( ExpectedItemNameCashierPge.contains(ActualItemNameCashierPge),"Item name mismatch: Expected '" + ExpectedItemNameCashierPge + "' to contain actual '" + ActualItemNameCashierPge + "'");

			List<WebElement> Quantity            = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5"));
			String ActualQuantityCashierPge      = Quantity.get(i).getText().replace("-", "");
			String ExpectedQuantityCashierPge    = ProductQuantity.get(i);
			double ActualQtyCashierPge           = Double.parseDouble(ActualQuantityCashierPge);
			double ExpectedQtyCashierPge         = Double.parseDouble(ExpectedQuantityCashierPge);
			asrt.assertEquals(ActualQtyCashierPge, ExpectedQtyCashierPge, 1.000, "Quantity mismatch: Expected is "+ ExpectedQtyCashierPge + " but got " + ActualQtyCashierPge + " in billing page");

			List<WebElement> TotalWithoutTax         = base.GetElement(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
			String ActualTotalWithoutTaxCashierPge   = TotalWithoutTax.get(i).getText().replaceAll("[^\\d.-]", "").trim();
			SubTotalSumCashierPge                    = SubTotalSumCashierPge + Double.parseDouble(ActualTotalWithoutTaxCashierPge);
			String ExpectedTotalWithoutTaxCashierPge = String.format("%.2f",Double.parseDouble(ProductTotal.get(i).replaceAll("[^\\d.-]", "").trim()));

			asrt.assertEquals(Double.parseDouble(ActualTotalWithoutTaxCashierPge.replace("-", "")),Double.parseDouble(ExpectedTotalWithoutTaxCashierPge), 1.0,"TotalWithoutTax mismatch: Expected is " + ExpectedTotalWithoutTaxCashierPge + " but got "+ ActualTotalWithoutTaxCashierPge + " in billing page");
		}
		String ExpectedSubTotalCashierPge = String.format("%.2f", SubTotalSumCashierPge).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalCashierPge   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();

		asrt.assertEquals(Double.parseDouble(ActualSubTotalCashierPge),Double.parseDouble(ExpectedSubTotalCashierPge), 1.0,"Subtotal mismatch: " + ExpectedSubTotalCashierPge + " but found " + ActualSubTotalCashierPge+ " in billing page");

		String  TaxCashierPge= base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		String ActualTaxCashierPge     = String.valueOf(TaxCashierPge).replaceAll("[^\\d.]", "").trim();
		double ExpectedTaxCashierPge   = ( Double.parseDouble(ProductTotal.get(0))+ Double.parseDouble(ProductTotal.get(1))) * 0.03;
		String ExpectedTaxInCashierPge = String.format("%.2f", ExpectedTax);

		asrt.assertEquals(Double.parseDouble(ActualTaxInBillingPge), Double.parseDouble(ExpectedTaxInCashierPge), 1.0,"Tax amount mismatch: " + ExpectedTaxCashierPge + " but found " + ActualTaxCashierPge+ " in billing page");
		String TotalAmountCashierPge         = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4"));
		double ExpectedTotalAmountCashierPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(ActualTaxInBillingPge);
		double ActualTotalAmountCashierPge   = Double.parseDouble(TotalAmount.replaceAll("[^\\d.-]", "").trim());
		asrt.assertEquals(ActualTotalAmountCashierPge, ExpectedTotalAmountCashierPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmountCashierPge + " but found " + ActualTotalAmountCashierPge + " in billing page");
		double TotalPieces                      = Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt)+ Double.parseDouble(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt);
		//int TotalPieces                      = Integer.parseInt(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt)+Integer.parseInt(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Grosswgt);

		// 15.Select a Payment method(Cash or Card)
		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC");

		// 16.Post the Invoice
		// Expected: Check final invoice details: save receipt id for future reference			
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(2000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC145_SaleInvoice");
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount   = String.valueOf(SubTotalSumCashierPge);
		String TotalDiscount = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_Discount;
		String TotalQtyPcs   = 	Double.toString(TotalPieces);
		//String TotalQtyPcs   = Integer.toString(TotalPieces);
		String NetValue      = String.valueOf(ActualTotalAmountCashierPge);
		String Adjustment    = "";
		String PdfInvoiceNo  = pdfUtils.SaleInvoicePdfValidationForServiceItem(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				String.valueOf(SubTotalSumCashierPge),
				NetValue,
				PaymentAmount,
				Adjustment);			
		System.out.println(PdfInvoiceNo+"And Estimation Number"+ActualEstmnNumber);
		base.UnifiedClick(LoginPageObj.Btn_SignInButton("Close"));
		appUtils.SalesReturnFlowBulkItem(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_CustomerNo, PdfInvoiceNo,Utility_TestData.HKProduct);
		appUtils.HamBurgerButtonClick("iconShop");
		appUtils.SalesReturnFlowBulkItem(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_CustomerNo, PdfInvoiceNo,BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC145_BC);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}	

	/// <summary>
	/// Test Case Title : Bulk item sales return
	/// Automation ID : TC146
	/// Author: Christy Reji
	/// </summary>
	public void TC146_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		//Prerequisite : Take the bill number (receipt number) that has previously been sold.
		//Step 1: Login to POS
		//Step 2: Click on transaction button
		String BulkSalveInvoiceNo = appUtils.GetInvoiceNoBulkSale(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC146_CustomerNo,Utility_TestData.HKProduct,"0.1");

		//Step 3 : Select Customer
		//Step 4: Click on add to sale button
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC146_CustomerNo,UtilityTestData.MenuBarOptions[0]);

		//Step 5: Click on customer adjustment button
		//Step 6: Click on return transaction button at the billing screen
		//Step 7: Enter reciept number
		//Step 8:Select return product 
		//Step 9: click on return button
		ReturnDetails Details = appUtils.ReturnSingleProductWithDetails(BulkSalveInvoiceNo, Utility_TestData.HKProduct);

		//Step 10:Click on the Amount
		//Note:Billing Screen *No of Product lines *Displayed Item Name  *Displayed Quantity *Displayed TOTAL (without Tax)
		// *Displayed Subtotal *TAX *Total Amount
		List<String> BulkItemNameList        = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5")).stream().map(text -> text.replaceAll("[\u20B9,=:\\-]", "").replaceAll("\\s{2,}", " ").trim()).collect(Collectors.toList());
		List<String> BulkItemQtyList         = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")).stream().map(text -> text.replaceAll("[\u20B9,=:\\-]", "").replaceAll("\\s{2,}", " ").trim()).collect(Collectors.toList());
		List<String> BulkItemGrossList       = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).stream().map(text -> text.replaceAll("[^\\d.]", "").trim()).collect(Collectors.toList());
		List<String> TotalWithoutTax         = base.GetElementTexts(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5"));
		String ActualSubTotalInBillingPge    = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		String  Tax                          = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		String TotalAmount                   = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		String ExpectedItemNameInBillingPge  = Utility_TestData.HKProduct;
		String ExpectedQuantityInBillingPge  = UtilityTestData.GrossPieces;
		String ExpectedSubtotal              = TotalWithoutTax.get(0).replaceAll("[^\\d.]", "").trim();
		double ExpectedTax                   = (Double.parseDouble(ActualSubTotalInBillingPge)) * 0.03;
		String ExpectedTaxInBillingPge       = String.format("%.2f", ExpectedTax);
		double ExpectedTotalAmntInBillingPge = Double.parseDouble(ActualSubTotalInBillingPge)+ Double.parseDouble(Tax);

		//BillingPage validation
		asrt.assertTrue(ExpectedItemNameInBillingPge.contains(BulkItemNameList.get(0)),"Item name mismatch: Expected '" + ExpectedItemNameInBillingPge + "' to contain actual '" + BulkItemNameList.get(0) + "'");
		asrt.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmntInBillingPge, 1.0, "Total Amount mismatch: "+ ExpectedTotalAmntInBillingPge + " but found " + TotalAmount + " in billing page");
		asrt.assertEquals(Double.parseDouble(Tax), Double.parseDouble(ExpectedTaxInBillingPge), 1.0,"Tax amount mismatch: " + ExpectedTaxInBillingPge + " but found " + Tax+ " in billing page");
		asrt.assertEquals(Double.parseDouble(BulkItemQtyList.get(0)), Double.parseDouble(ExpectedQuantityInBillingPge), 1.000, "Quantity mismatch: Expected is "+ ExpectedQuantityInBillingPge + " but got " + BulkItemQtyList.get(0) + " in billing page");
		asrt.assertEquals(Double.parseDouble(ActualSubTotalInBillingPge), Double.parseDouble(ExpectedSubtotal), 1.000, "Subtotal mismatch: Expected is "+ ActualSubTotalInBillingPge + " but got " + ExpectedSubtotal + " in billing page");
		asrt.assertEquals(BulkItemNameList.size(), 1, "Total lines mismatch: Expected is "+ ExpectedQuantityInBillingPge + " but got " + BulkItemQtyList.get(0) + " in billing page");

		// Step 11.Select payment method as convert advance
		//Step 12.select deposite type as normal
		// Step 13.Enter deposite amount
		//Step 14.Select sales person
		//Step  15. Select due date
		//Step 16. Enter remarks
		//Step  17. Add nominee details
		//17.1 Enter nominee name
		//17.2 Select nominee relation
		// 17.3 Click on check box(Same as customer address)
		// 17.4 Click on OK button
		//17. Click on deposite button
		appUtils.PaymentModeForDiffTransactions(UtilityTestData.PaymentMode, "", "",
				UtilityTestData.SalePersonNumber,
				UtilityTestData.DueYear,
				UtilityTestData.DobYear,
				UtilityTestData.NomineeName,
				UtilityTestData.NomineeRelation);

		// 18. Click on amount
		// 19. select a Payment method(Cash or Card)
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_Amount("cartView_amountDueLink"));	
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

		// 20.Post the Invoice
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_NormalAdvInvoice("win-itembox","win-item","DOCUMENT NORMAL ADV. INVOICE"));
		Thread.sleep(10000);
		base.excuteJsClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Close("btnblue","PRINT"));
		Thread.sleep(1000);
		Map<String, String> Result = pdfUtils.NormalAdvancePdfValidation();	
		String AdvanceReceived     = Result.get("AdvanceReceived").replaceAll("[^\\d.]", "").trim();
		String NetAdvance          = Result.get("NetAdvance").replaceAll("[^\\d.]", "").trim();
		String DepositType         = Result.get("DepositType");
		String PaymentMode         = Result.get("PaymentMode");
		String PaymentAmount       = Result.get("PaymentAmount").replaceAll("[^\\d.]", "").trim();
		String ExpectedDepositType = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC146_DepositType;
		String ExpectedPaymentMode = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC146_PaymentMode;

		//Validation
		asrt.assertEquals(AdvanceReceived, TotalAmount, "Advance received mismatch: Expected is "+ TotalAmount + " but got " + AdvanceReceived + " in NormalAdvance PDF");
		asrt.assertEquals(NetAdvance, TotalAmount, "NetAdvance mismatch: Expected is "+ TotalAmount + " but got " + NetAdvance + " in NormalAdvance PDF");
		asrt.assertEquals(PaymentAmount, TotalAmount, "PaymentAmount mismatch: Expected is "+ TotalAmount + " but got " + AdvanceReceived + " in NormalAdvance PDF");
		asrt.assertEquals(DepositType, ExpectedDepositType, "DepositType mismatch: Expected is "+ ExpectedDepositType + " but got " + DepositType + " in NormalAdvance PDF");
		asrt.assertEquals(PaymentMode, ExpectedPaymentMode, "PaymentMode mismatch: Expected is "+ ExpectedPaymentMode + " but got " + PaymentMode + " in NormalAdvance PDF");

		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}

	// <summary>
	// Test Case Title : Bulk item sale return with SKU
	// Automation ID : TC148
	// Author:Christy Reji
	// </summary>
	public void TC148_BulkItemSalesSalesReturnServiceItemSalesStockSummary() throws Exception {

		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
		//Pre-requisite: Take the bill number(Receipt number) that has previosly been sold-Takin BC
		//1 a.Search BC, select Back chain
		//b.Select configuration, and click on Add item
		//c.Provide Gross weight
		//d.Click Enter
		//e.Click on "Add to cart"
		//Note: Sell any one of the item (HK or BC)
		//2. Click on Save to estimation button, Save esimate,Recall estimate from cashier screen
		//3. Select estimate and click on save to estimate button
		//4.Select the Transaction Type as Sales 
		//5.Click on Process Estimation 
		//6.Choose a sales representative
		//7.Click on the Amount
		//8.Select a Payment method(Cash or Card)
		String BulkSalveInvoiceNo = appUtils.GetInvoiceNoBulkSale(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_CustomerNo,BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_BC,"1");

		//Step 3 : Select Customer
		//Step 4: Click on add to estimation button
		appUtils.SearchByCustomerID(BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_CustomerNo,UtilityTestData.MenuBarOptions[5]);

		//Step 5: Click on customer adjustment button
		//Step 6: Click on return transaction button at the billing screen
		//Step 7: Enter reciept number
		//Step 8:Select return product and click on return button

		ReturnDetails Details = appUtils.ReturnSingleProductWithDetails(BulkSalveInvoiceNo, BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_BC);

		//Note:-8. *Displayed Quantity  *Displayed TOTAL (without Tax)*Displayed Subtotal  *TAX 	 *Total Amount
		String BulkItemName               = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-ProductNameField wrapText", "h5"));
		String BulkItemQty                = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-QuantityField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String BulkItemGrossWt            = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String TotalWithoutTax            = base.GetText(NormalSaleGoldAndSilverObj.Btn_CartMoney("tillLayout-TotalWithoutTaxField textRight", "h5")).replaceAll("[^\\d.]", "").trim();
		String ActualSubTotalInBillingPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("SubtotalField", "h4")).replaceAll("[^\\d.]", "").trim();
		String Tax                        = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TaxField", "h4")).replaceAll("[^\\d.]", "").trim();
		String TotalAmountBulk            = base.GetText(NormalSaleGoldAndSilverObj.Ele_Lines("TotalAmountField", "h4")).replaceAll("[^\\d.]", "").trim();

		// Step 9: Scan an SKU
		//Step 10: Click on add to cart button
		Map<String, String> ScannedSkuDataMap = new LinkedHashMap<>();
		List<String> ProductNames             = new ArrayList<>();
		List<String> ProductQtyList           = new ArrayList<>();
		List<String> ProductTotalList         = new ArrayList<>();
		ProductNames.add(BulkItemName);
		ProductQtyList.add(BulkItemQty);
		ProductTotalList.add(TotalWithoutTax);

		int SkuCounter = 1;
		List<String> SkuList  = appUtils.SelectMultipleSKUs(
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_SkuGoldCount,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_TransferType,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_FromCounter,
				BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_MetalType				
				);
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconShop");
		for (String Sku : SkuList) {
			appUtils.SKUIngridentTableValues(Sku, SkuCounter, ScannedSkuDataMap);
			ProductNames.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_HeaderName"));
			ProductQtyList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_GrossWeight"));
			ProductTotalList.add(ScannedSkuDataMap.get("SKU_" + SkuCounter + "_Total"));
			Map<String, String> CurrentSkuData = appUtils.ExtractDataForSku(ScannedSkuDataMap, SkuCounter);
			mathUtils.SkuIngredientItemCalculation(CurrentSkuData, SkuCounter);
			SkuCounter++;
		}

		//TranscationPageVerifyLines
		Map<String, String> TransactionDataLine = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotal		 	= TransactionDataLine.get("Subtotal").replaceAll("[^\\d.]", "").trim();
		String TotalNetWeight   = TransactionDataLine.get("NetWeight");
		String TaxWithSKU 		= TransactionDataLine.get("Tax").replaceAll("[^\\d.]", "").trim();
		String TotalAmount 		= TransactionDataLine.get("TotalAmount").replaceAll("[^\\d.]", "").trim();
		String ActualLinesCount	= TransactionDataLine.get("LinesCount");	

		mathUtils.ValidateTransactionLineCalculationsReturnProd(TransactionDataLine, ScannedSkuDataMap);

		double TotalWithoutscheme= Double.parseDouble(ProductTotalList.get(0));
		double TotalWithoutTaxSku= Double.parseDouble(ProductTotalList.get(1));
		//double ExpectedSubTotal  = TotalWithoutTaxSku-TotalWithoutscheme;
		double ExpectedSubTotal = Math.abs(TotalWithoutTaxSku - TotalWithoutscheme);
		//double ExpectedTax       = Math.round((ExpectedSubTotal * 0.03) * 100.0) / 100.0;
		double ExpectedTax = Math.round((Math.abs(ExpectedSubTotal) * 0.03) * 100.0) / 100.0;
		double ExpectedTotalAmnt = ExpectedSubTotal + ExpectedTax;

		Assert.assertEquals(Double.parseDouble(SubTotal), ExpectedSubTotal, 1,"Mismatch in Subtotal. Actual is: " + SubTotal + " but got ExpectedSubTotal: " + ExpectedSubTotal + " after calculation in billing page");
		Assert.assertEquals(Double.parseDouble(TotalAmount), ExpectedTotalAmnt, 1, "Mismatch in Total amount. Actual is: " + TotalAmount + " but got ExpectedTotalAmnt: " + ExpectedTotalAmnt + " after calculation in billing page");
		Assert.assertEquals(Double.parseDouble(TaxWithSKU), ExpectedTax, 1, "Mismatch in Tax. Actual is: " + TaxWithSKU + " but got ExpectedTotalAmnt: " + ExpectedTax + " after calculation in billing page");

		//Step 11. Click on Save to estimation button
		//Step 12. save esimate
		//Expected : 11.Verify values in estimate print
		base.buttonClick(LoginPageObj.Edt_AlertText("Billing"));	
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Back"));
		base.buttonClick(LoginPageObj.Edt_AlertText("Save To Estimate"));
		String ActualEstmnNumber = base.GetValue(NormalSaleGoldAndSilverObj.Ele_EstmnNumber("Estimation no"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAdd"));

		Thread.sleep(3000);
		String ProFormaInvoiceName = pdfUtils.DownloadAndRenameLatestPDF("TC148_ProformaInvoice");
		Thread.sleep(2000);
		String ProFormaInvoicePath = System.getProperty("user.dir") + "\\Invoices\\" + ProFormaInvoiceName;
		String Gst                 = TaxWithSKU;
		String NetTotal            = TotalAmount;
		String TaxableValue        = SubTotal;
		String InvoiceTotal        = TotalAmount;
		String LinesCount          = ActualLinesCount;
		pdfUtils.ProFormaInvoicePdfWithOldGoldNegativeValue(
				ProFormaInvoicePath,
				TaxableValue,
				Gst,
				InvoiceTotal,
				NetTotal,
				TotalAmount
				);

		// Step 13.Recall estimate from cashier screen
		//Step 14. Select estimate and click on save to estimate button
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

		//Step 15.Select the Transaction Type as Sales return
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), "Sales Return");

		//Step 16.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		// Step 17:Click on the Amount
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_AmountDue("Change due"));
		Thread.sleep(2000);
		base.buttonClick(LoginPageObj.Btn_SignInButton("Close"));

		//Step 18: Post sale return invoice
		//Expected:  18. Verify sales return invoice
		Thread.sleep(3000);
		Map<String, String> SalesReturnPdf = pdfUtils.SalesReturnPDFCredit();
		String ActualGrossWt                  = SalesReturnPdf.get("Product1.GrossWt");	
		String ActualNetWt                    = SalesReturnPdf.get("Product1.NetWt");
		String ActualAmount                   = SalesReturnPdf.get("Product1.Amount");
		String ActualTaxableValue             = SalesReturnPdf.get("TaxableValue");
		String ActualTotalNetValue            = SalesReturnPdf.get("TotalNetValue");
		String ActualPaymentDetails           = SalesReturnPdf.get("PaymentDetails");

		asrt.assertEquals(Double.parseDouble(ActualGrossWt), Double.parseDouble(BulkItemQty),0.001,"Gross Weight mismatch: Expected is "+BulkItemGrossWt+" but found "+ActualGrossWt+" in credit note");
		asrt.assertEquals(Double.parseDouble(ActualNetWt), Double.parseDouble(BulkItemQty),0.001,"NetWt mismatch: Expected is"+BulkItemGrossWt+" but found "+ActualNetWt+" in credit note");
		asrt.assertEquals(ActualAmount, TotalWithoutTax,"Amount without tax mismatch: Expected is"+TotalWithoutTax+" but found "+ActualAmount+" in credit note");
		asrt.assertEquals(ActualTaxableValue, ActualSubTotalInBillingPge,"Taxable value mismatch: Expected is"+ActualSubTotalInBillingPge+" but found "+ActualTaxableValue+" in credit note");
		asrt.assertEquals(ActualTotalNetValue, TotalAmountBulk,"NetValue mismatch: Expected is"+TotalAmountBulk+" but found "+ActualTotalNetValue+" in credit note");
		asrt.assertEquals(ActualPaymentDetails, TotalAmountBulk,"Adjustment amount mismatch: Expected is"+TotalAmountBulk+" but found "+ActualPaymentDetails+" in credit note");

		//Step 19. Again recall estimate from cashier screen
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Cash("tab-button-ButtonGrid2","text semilight primaryFontColor"));
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

		//Step 20.Select the Transaction Type as "sale "
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_BankProofType("Transaction type"), "Sales");

		//Step 21.Click on Process Estimation
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept"));

		//Step 22.Choose a sales representative
		if (base.isPresent(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"))) 
		{
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("margin0 h5 ellipsis maxWidth220", "1"));
		}

		//Step 23.Click on the Amount
		//Expected :  23.Cashier Screen after recall*No of Product lines  *Displayed Item Name  
		//*Displayed Quantity*Displayed TOTAL (without Tax) *Displayed Subtotal  *TAX *Total Amount
		Map<String, String> TransactionDataLineInCashierScreen = appUtils.TransactionSKUsLinesVerify(SkuList, ScannedSkuDataMap);
		String SubTotalCashierScreen		= TransactionDataLineInCashierScreen.get("Subtotal");
		String TotalAmountCashierScreen 	= TransactionDataLineInCashierScreen.get("TotalAmount");

		appUtils.ValidateBillingPgeAndCasierScreen(
				TransactionDataLineInCashierScreen,
				Arrays.asList(ProductNames.get(1)),
				Arrays.asList(ProductQtyList.get(1)),
				Arrays.asList(ProductTotalList.get(1)),
				"Cashier"
				);

		String PaymentAmount = appUtils.PaymentAfterRecallEstimate("HDFC").replaceAll("[^\\d.]", "").trim();

		//Step 24.Post the invoice
		//Expected:  24. Check final invoice details
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", "Original For Recipient"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("PRINT"));
		Thread.sleep(3000);
		String FinalInvoice = pdfUtils.DownloadAndRenameLatestPDF("TC148_FinalSaleInvoice");
		Thread.sleep(2000);
		String InvoicePdfPath = System.getProperty("user.dir") + "\\Invoices\\" + FinalInvoice;
		String GrossAmount    = ProductTotalList.get(1);
		String TotalDiscount  = BulkItemSalesSalesReturnServiceItemSalesStockSummaryTestData.TC148_Discount;
		String TotalQtyPcs    = String.valueOf(SkuList.size());		
		String Taxablevalue   = SubTotalCashierScreen;
		String NetValue       = TotalAmountCashierScreen;
		String Adjustment     = "";
		String PdfInvoiceNo   = pdfUtils.SaleInvoicePdfValidationForServiceItem(
				InvoicePdfPath,
				GrossAmount,
				TotalDiscount,
				TotalQtyPcs,
				Taxablevalue,
				NetValue,
				PaymentAmount,
				Adjustment);
		pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}


}