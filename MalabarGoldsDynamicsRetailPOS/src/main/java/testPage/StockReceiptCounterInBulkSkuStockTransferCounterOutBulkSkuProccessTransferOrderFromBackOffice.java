package testPage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import objectRepository.AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj;
import objectRepository.AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj;
import objectRepository.ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj;
import objectRepository.CustomerCreation_Obj;
import objectRepository.GPPSettlementAverageRateSingleSkuMultipleSku_Obj;
import objectRepository.InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj;
import objectRepository.LoginPage_Obj;
import objectRepository.NormalSaleGoldandSilver_Obj;
import objectRepository.NormalSalesReturnGoldSilverReturnSaleCounter_Obj;
import objectRepository.OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactions_Obj;
import objectRepository.OldItemStockOutAndShipment_Obj;
import objectRepository.StatementPosting_Obj;
import objectRepository.StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj;
import testData.CommonData;
import testData.InvoiceAdvanceWithPaymentMethodRtgsReceived_TestData;
import testData.RtgsPaymentToCustomer_TestData;
import testData.StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_TestData;
import testData.Utility_TestData;
import utilPack.AppUtilities;
import utilPack.BasePge;
import utilPack.ErpUtilities;
import utilPack.MathUtilities;
import utilPack.PdfUtilities;

public class StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice extends BasePge {

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
	GPPSettlementAverageRateSingleSkuMultipleSku_Obj GPPSettlementAverageRateSingleSkuMultipleSkuObj =new GPPSettlementAverageRateSingleSkuMultipleSku_Obj();
	AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj =new AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPos_Obj();
	StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_TestData StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData = new StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_TestData();
	StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj = new StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj();
	StatementPosting_Obj StatementPostingObj = new StatementPosting_Obj();
	AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj = new AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvance_Obj();
	OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactions_Obj OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactionsObj = new OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactions_Obj();
	OldItemStockOutAndShipment_Obj OldItemStockOutAndShipmentObj=new OldItemStockOutAndShipment_Obj();
	ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj=new ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj();
	WebDriverWait wait;

	public StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice(WebDriver driver) {
		super(driver);
		base = new BasePge(driver);
		appUtils = new AppUtilities(base); 
		mathUtils = new MathUtilities(base);
		pdfUtils = new PdfUtilities(base);
		erpUtils = new ErpUtilities(base);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}

	//<summary>
	// Test Case Title : Counter out SKU from POS
	// Automation ID : TC229
	// Author : Benson 
	// </summary>
	public void TC229_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//To extract a single Sku for step 7
		String SkuValue=appUtils.SelectMultipleSKUs(
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC229_SkuCount,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC229_TransferType,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC229_FromCounter,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC229_MetalType).get(0);

		//Step 3: Go to Purchase
		appUtils.HamBurgerButtonClick("iconShop");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Purchase"))).click();

		//Step 4: Select counter transfer
		base.ClickCondition(LoginPageObj.Edt_AlertText("Counter Transfer"));

		//Step 5: Choose transfer type as stock out
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC229_StockTransferType);

		//Step 6: Select From counter and metal type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC229_FromCounter);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC229_MetalType);

		//Step 7: Enter SKU
		base.setData(CustomerCreationObj.Ele_Name("Product"), SkuValue);   
		base.pressKey(CustomerCreationObj.Ele_Name("Product"), "ENTER");

		//Step 8: Click on commit button
		//Expected: Verify if validation appears saying SKU received successfully
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconAccept")); 
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton", "YES"));
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")),"User is not able to see the validation message :'SKU received successfully' ");

	}

	//<summary>
	// Test Case Title : Ship SKU from POS
	// Automation ID : TC230
	// Author : Benson 
	// </summary>
	public void TC230_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception{

		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Prerequisite
		String SkuValue=appUtils.CounterOutSkuInPos(
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_SkuCount,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_TransferType,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_FromCounter,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_MetalType,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_StockTransferType); 

		//Step 2: Click on Transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Go to Purchase
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Purchase"))).click();

		//Step 4: Select transfer shipment
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Transfer Order Shipment"));

		//Step 5: Choose transfer type as SKU
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_ShipTransferType );

		//Step 6: Select to warehouse
		//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("Warehouse"), UtilityTestData.TC230_Warehouse);
		Select select=new Select(driver.findElement((NormalSaleGoldAndSilverObj.Sel_DepositType("Warehouse"))));
		Thread.sleep(5000);
		List<WebElement> WarehouseOptions = select.getOptions();
		for (WebElement Option : WarehouseOptions) {
			if (Option.getText().toLowerCase().contains(UtilityTestData.Location[0].toLowerCase())) {   
				Option.click();
				break;
			}
		}

		//Step 7: Click on search button
		//Step 8: Select the transfer id
		//Step 9 Click on Ok Button
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
		Thread.sleep(5000);
		List<WebElement> TransferIdList= driver.findElements(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine", "Transfer id"));
		for (int i = TransferIdList.size() - 1; i >= 0; i--) {
			base.ScrollToWebElement(TransferIdList.get(TransferIdList.size() - 1));
			WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebDriverWait SkuWait = new WebDriverWait(driver, Duration.ofSeconds(50));

			if (TransferIdList.get(i).getText().contains(UtilityTestData.Terminal)) {
				Wait.until(ExpectedConditions.elementToBeClickable(TransferIdList.get(TransferIdList.size() - 1)));
				TransferIdList.get(i).click();
				base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton", "OK"));	
				Thread.sleep(3000);
				SkuWait.until(ExpectedConditions.presenceOfElementLocated(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine","SKU NUMBER")));
				String SkuTransferValue=base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine","SKU NUMBER")).trim();
				if(SkuValue.equals(SkuTransferValue)) {
					break;
				}     
			}

			System.out.println("No match yet. Clicking search again...");
			//base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
			Thread.sleep(6000);
			TransferIdList = driver.findElements(
					AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj
					.Ele_OrderBalanceAmt("dataListLine", "Transfer id"));	
		}

		String StockOutId=base.GetValue(CustomerCreationObj.Ele_Name("TransferOrderId"));

		//Enter Mandatory fields: Way bill number,Enter Airway bill number and Remarks
		base.setData(CustomerCreationObj.Ele_Name("WayBillNumber"), StockOutId);
		base.setData(CustomerCreationObj.Ele_Name("AirwayBillNumber"), StockOutId);
		base.setData(CustomerCreationObj.Ele_StreetName("Remarks"),StockOutId);

		//Step 10: Click on Ship Button
		//Expected:Verify if validation appears saying SKU shipped successfully
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_commitBarCommandCommand"));
		asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")),"User is not able to see the validation message :'SKU shipped successfully'");

	}

	// <summary>
	// Test Case Title : do Inter counter transfer and check the counter
	// Automation ID : TC234
	//@Author: Gokul.P
	// </summary>
	public void TC234_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice()throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		// 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Pre-requisite
		appUtils.HamBurgerButtonClick("iconShop");
		List<String> GoldSkuList   = appUtils.SelectMultipleSKUs(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_SkuCount,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_TransferType,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_FromCounter,
				StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_MetalType);

		// 2. Click on transaction button
		appUtils.HamBurgerButtonClick("iconShop");

		// 3. Click on purchase
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		// 4. Click on counter transfer
		base.buttonClick(LoginPageObj.Edt_AlertText("Counter Transfer"));

		// 5. Select transfer type as "Inter"
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_TransferType);

		// 6. Select from counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_FromCounter);	

		// 7. Select to counter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("ToCounter"));		
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("ToCounter"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_ToCounter);

		// 8. Select metal type
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_MetalType);

		// 9. Enter SKU
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("Product"), GoldSkuList.get(0));
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("Product"), "ENTER");
		Thread.sleep(2000);

		// 10. Click on ship button
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.buttonClick(LoginPageObj.Edt_Alert("Ship"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		Thread.sleep(2000);

		// 11. Again select tansfer type as inter
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_TransferType);

		// 12. Click on search button
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		Thread.sleep(2000);

		// 13. Click on inter reciept search
		base.buttonClick(LoginPageObj.Edt_AlertText("Inter receipt search"));

		// 14 . Select item
		List<WebElement> AllReceipts = base.GetElement(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_RecipetNumber());	
		int SkuCount=0;
		for (WebElement Receipt : AllReceipts)
		{  
			String ReceiptNumber = Receipt.getText();
			if (ReceiptNumber.contains(UtilityTestData.Terminal)) {
				Receipt.click();
				Thread.sleep(2000);
				String SkuText=base.GetText(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("wrapTextWithoutHyphen"));
				if(SkuText.contains(GoldSkuList.get(SkuCount)))
				{
					String[] ReceiptDetails = SkuText.split("\\r?\\n");
					for (int i = 0; i < ReceiptDetails.length; i++)
					{
						if (ReceiptDetails[i].contains("SKU details") && i + 1 < ReceiptDetails.length) {
							String ExpectedSku = ReceiptDetails[i + 1].trim();
							String AcutalSKUInCounterTransferPage=GoldSkuList.get(SkuCount);
							asrt.assertEquals(ExpectedSku, AcutalSKUInCounterTransferPage,"The already shipped SKU in the Counter Transfer page is not shipped is not correctly: Expected: "+ExpectedSku+" Actual "+ AcutalSKUInCounterTransferPage+"");
							break;
						}						
					}						
					base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));
					break;
				}
				else{base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_ReciptClose());}
			}
		}

		// 15. Click on receive button
		// Expected:After receiving change the from counter and check whether the SKU is present or not
		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Receive("Receive"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		Thread.sleep(2000);	
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_TransferType);
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("FromCounter"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_ToCounter);	
		base.buttonClick(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"));
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("MetalType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC234_MetalType);
		Thread.sleep(1000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Estimation("win-disposable win-command AppBarCommand iconSearch"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 padLeft12", "Product search"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_SearchBox("Enter search keyword","SearchTerm"));
		Thread.sleep(8000);
		base.setData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_SearchBox("Enter search keyword","SearchTerm"),GoldSkuList.get(0));
		Thread.sleep(8000);
		base.pressKey(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_SearchBox("Enter search keyword","SearchTerm"), "ENTER");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(8000);

		String SkuCounterPge = base.GetText(NormalSaleGoldAndSilverObj.Ele_SKUNumber("column1", "h4 ellipsis cell", String.valueOf(1)));
		asrt.assertEquals(GoldSkuList.get(0), SkuCounterPge,"The Sku is not present in the changed counter as expected: Expected:"+GoldSkuList.get(0)+" Actual :"+SkuCounterPge+"");
	}

	// <summary>
	// Test Case Title : Create transfer order for SKU
	// Automation ID : TC224
	// Author: Chandana Babu
	// </summary>
	public String TC224_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception {	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));		
		//Step 1: Go to ERP of required store .
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		Map<String, Map<String, String>> SkuList = erpUtils.ToGetSkuForTransferOrder(1);
		String Sku = "";
		String ProductName ="";
		String Purity = "";
		for (Map.Entry<String, Map<String, String>> entry : SkuList.entrySet()) {
			Sku = entry.getKey();
			Map<String, String> SkuDetails = entry.getValue();
			ProductName = SkuDetails.getOrDefault("productName", "");
			Purity = SkuDetails.getOrDefault("purity", "");
		}

		//Step 2: Search for Transfer Order and click on New
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_TransferOrder);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		if(!base.isPresent(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"))) {
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_TransferOrder);
			Thread.sleep(5000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
			Thread.sleep(10000);
			base.ClickCondition(LoginPageObj.Edt_Alert("New"));			
		}

		//Step 2: Select From warehouse and To warehouse
		//Expected: Check if POS warehouse is not listed
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), UtilityTestData.WarehouseId);
		Thread.sleep(2000);
		List<WebElement>WareHouses = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Warehouse"));
		List<String>WareHousesList = new ArrayList<>();
		for(WebElement WareHouse : WareHouses ) {
			String WareHouseValue = WareHouse.getAttribute("value");
			WareHousesList.add(WareHouseValue);
		}
		asrt.assertFalse(WareHouses.contains(UtilityTestData.WarehouseId), "POS warehouse is listed in from warehouse list");
		base.clearData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), UtilityTestData.WarehouseId+"BO");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), "ENTER");
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdTo"), UtilityTestData.WarehouseId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdTo"), "ENTER");
		Thread.sleep(1000);

		//Step 3: Select Transfer type as "SKU"
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDTransferType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_TransferType);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDTransferType"), "ENTER");
		Thread.sleep(1000);

		//Step 4: Select Accounted under as "Gold"
		String AccountedUnder = "";
		if (ProductName.startsWith("PL")||ProductName.startsWith("pl")||ProductName.startsWith("Pl")) {
			AccountedUnder = "Platinum";
		}
		else if (ProductName.startsWith("S")||ProductName.startsWith("s")) {
			AccountedUnder = "Silver";		
		}
		else {
			AccountedUnder = "Gold";		
		}
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDMetalType"), AccountedUnder);

		//Step 5: Select Purpose code as "Stock Transfer"[00001]
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDPurposeCode"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_StockTransfer);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDPurposeCode"), "ENTER");
		Thread.sleep(1000);

		//Step 6: Click on save
		base.ClickCondition(LoginPageObj.Edt_Alert("Save"));

		//Step 7: Enter SKU number and click on enter button 
		Thread.sleep(1000);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SkuNumber"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SkuNumber"), Sku);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("SkuNumber"), "ENTER");

		//Step 8: Click on Calculate line
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_ERPInvoice(Sku)));
		base.ClickCondition(LoginPageObj.Edt_Alert("Calculate line"));

		//Step 9: Click on ship
		wait.until(ExpectedConditions.visibilityOfElementLocated(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Check("InventTransferLine_MGDCostCalculation", "Yes")));		
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("ShipmentTab_button"));
		Thread.sleep(1000);

		//Step 10: Click on Generate picking list
		base.ClickCondition(LoginPageObj.Edt_Alert("Generate picking list"));

		//Step 11: Change "ship now" to "All"
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("dialog-popup-content editMode")));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("combobox-button"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Ele_ERPNo("All")));
		base.ClickCondition(LoginPageObj.Ele_ERPNo("All"));
		Thread.sleep(1000);
		base.ClickCondition(StatementPostingObj.Btn_Add("OK"));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoginPageObj.Edt_Alert("Processing operation - Transfer order - Picking list")));

		//Step 12: Go to Ship transfer order
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("ShipmentTab_button"));
		base.ClickCondition(LoginPageObj.Edt_Alert("Ship transfer order"));

		//Step 13: Change the update field value from " ship" to "All"
		wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("dialog-popup-content editMode")));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Txt_Sku("combobox-button"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Ele_ERPNo("All")));
		base.ClickCondition(LoginPageObj.Ele_ERPNo("All"));
		Thread.sleep(1000);

		//Step 14: Click on ok button
		//Expected: Check the status of the order has been changed to shipped
		base.ClickCondition(StatementPostingObj.Btn_Add("OK"));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoginPageObj.Edt_Alert("Processing operation - Transfer order - Shipment")));
		Thread.sleep(2000);
		String Status = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EntityStatus_TransferStatus"), "title");
		asrt.assertEquals(Status, StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_Status,"Status displayed mismatched: Expected is "+StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_Status+" but got "+Status+" in transfer order page");
		String TransferOrder = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_MetalRateBlankMsg("Transfer order:"));
		String TransferOrderNumber = TransferOrder.split(":")[1].trim();
		return TransferOrderNumber;
	}
	// <summary>
	// Test Case Title : Receive from POS
	// Automation ID : TC225
	// Author: Chandana Babu
	// </summary>
	public String TC225_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice(String TransferOrder) throws Exception {	

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));	
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transactions
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Click on Purchase
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on Transfer order Receive
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Transfer Order Recieve"));

		//Step 5: Select Transfer type as SKu
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC225_TransferType);;

		//Step 6: Enter Transfer order id
		//Expected: Check the required transfer order is present
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: ReceiptNumber"), TransferOrder);

		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Btn_Transaction("h4 ellipsis cell", TransferOrder)), "Required transfer order is not present");

		base.ClickCondition(LoginPageObj.Ele_ErrorMessage("win-itembox"));
		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		String SkuNumber = base.GetText(AdvanceCollectionNormalAdvanceOfferAdvanceConvertToAdvanceObj.Ele_TotalAmount("SKU NUMBER", "h4 ellipsis cell"));

		//Step 7: Click on Receive
		base.ClickCondition(LoginPageObj.Btn_SingnIn("CartView_commitBarCommandCommand"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));
		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		//Thread.sleep(5000);
		return SkuNumber;
	}


	// <summary>
	// Test Case Title : Check the back office for transfer order and receive it in back office
	// Automation ID : TC231
	// Author: Neethu
	// </summary>
	public void TC231_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception{
		{
			//Step 1: Login to POS
			login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
			base.setZoom(driver, 60);

			//Prerequisite
			String SkuValue=appUtils.CounterOutSkuInPos(
					StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_SkuCount,
					StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_TransferType,
					StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_FromCounter,
					StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_MetalType,
					StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_StockTransferType); 

			//Step 2: Click on Transaction button
			appUtils.HamBurgerButtonClick("iconShop");
			System.out.println("SkuValue :"+SkuValue);

			//Step 3: Go to Purchase
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
			wait.until(ExpectedConditions.elementToBeClickable(LoginPageObj.Edt_AlertText("Purchase"))).click();

			//Step 4: Select transfer shipment
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Transfer Order Shipment"));

			//Step 5: Choose transfer type as SKU
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC230_ShipTransferType );

			//Step 6: Select to warehouse
			//base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("Warehouse"), UtilityTestData.TC230_Warehouse);
			Select select=new Select(driver.findElement((NormalSaleGoldAndSilverObj.Sel_DepositType("Warehouse"))));
			Thread.sleep(5000);
			List<WebElement> WarehouseOptions = select.getOptions();
			for (WebElement Option : WarehouseOptions) {
				if (Option.getText().toLowerCase().contains(UtilityTestData.Location[0].toLowerCase())) {   
					Option.click();
					break;
				}
			}

			//Step 7: Click on search button
			//Step 8: Select the transfer id
			//Step 9 Click on Ok Button
			base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
			Thread.sleep(5000);
			List<WebElement> TransferIdList= driver.findElements(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine", "Transfer id"));
			for (int i = TransferIdList.size() - 1; i >= 0; i--) {
				base.ScrollToWebElement(TransferIdList.get(TransferIdList.size() - 1));
				WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				WebDriverWait SkuWait = new WebDriverWait(driver, Duration.ofSeconds(50));

				if (TransferIdList.get(i).getText().contains(UtilityTestData.Terminal)) {
					Wait.until(ExpectedConditions.elementToBeClickable(TransferIdList.get(TransferIdList.size() - 1)));
					TransferIdList.get(i).click();
					base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton", "OK"));	
					Thread.sleep(3000);
					SkuWait.until(ExpectedConditions.presenceOfElementLocated(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine","SKU NUMBER")));
					String SkuTransferValue=base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine","SKU NUMBER")).trim();
					if(SkuValue.equals(SkuTransferValue)) {
						break;
					}     
				}

				System.out.println("No match yet. Clicking search again...");
				Thread.sleep(6000);
				TransferIdList = driver.findElements(
						AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj
						.Ele_OrderBalanceAmt("dataListLine", "Transfer id"));	
			}

			String StockOutId=base.GetValue(CustomerCreationObj.Ele_Name("TransferOrderId"));
			System.out.println("StockOutId: "+StockOutId);


			//Enter Mandatory fields: Way bill number,Enter Airway bill number and Remarks
			base.setData(CustomerCreationObj.Ele_Name("WayBillNumber"), StockOutId);
			Thread.sleep(3000);
			base.setData(CustomerCreationObj.Ele_Name("AirwayBillNumber"), StockOutId);
			Thread.sleep(3000);
			base.setData(CustomerCreationObj.Ele_StreetName("Remarks"),StockOutId);

			//Step 10: Click on Ship Button
			//Expected:Verify if validation appears saying SKU shipped successfully
			base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_commitBarCommandCommand"));
			Thread.sleep(3000);
			asrt.assertTrue(base.isExists(LoginPageObj.Btn_SingnIn("Button1Close")),"User is not able to see the validation message :'SKU shipped successfully'");
			base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
			Thread.sleep(3000);

			//ERP
			base.newWindow(1);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
			driver.get(UtilityTestData.ERPURL);
			wait.until( ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
			base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 50);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchProcessTransferOrderShipmentAtHO);
			base.setZoom(driver, 85);
			Thread.sleep(1000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
			Thread.sleep(20000);

			//				if(base.isExists(LoginPageObj.Edt_Alert("Yes")));
			//				{
			//					Thread.sleep(5000);
			//					base.ClickCondition(LoginPageObj.Edt_Alert("OK"));
			//					//	base.buttonClick(LoginPageObj.Edt_Alert("OK"));
			//					Thread.sleep(10000);
			//				}
			//
			//				if(base.isExists(OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactionsObj.Sel_BeginSKU("group_content layout-container layout-vertical","No","1")));
			//				{
			//					base.ClickCondition(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Sel_TransferOrder("field", "toggle-box", "1"));
			//					base.ClickCondition(LoginPageObj.Edt_Alert("OK"));
			//					//base.buttonClick(LoginPageObj.Edt_Alert("OK"));
			//					Thread.sleep(10000);
			//				}


			if(base.isExists(LoginPageObj.Edt_Alert("Yes")))
			{
				Thread.sleep(5000);
				base.ClickCondition(LoginPageObj.Edt_Alert("OK"));
				//	base.buttonClick(LoginPageObj.Edt_Alert("OK"));
				Thread.sleep(10000);
			}

			else {
				if(base.isExists(OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactionsObj.Sel_BeginSKU("group_content layout-container layout-vertical","No","1")));
				{
					base.ClickCondition(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Sel_TransferOrder("field", "toggle-box", "1"));
					base.ClickCondition(LoginPageObj.Edt_Alert("OK"));
					//base.buttonClick(LoginPageObj.Edt_Alert("OK"));
					Thread.sleep(10000);
				}
			}

			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());

			driver.switchTo().window(Tabs.get(1));
			driver.navigate().to(UtilityTestData.ERPURL);

			boolean jobEnded = false;
			int maxAttempts = 8; // 1 normal + 1 retry


			for (int attempt = 1; attempt <= maxAttempts; attempt++) {
				try {
					// Step 26: Search My Batch Job
					//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("button-commandRing Find-symbol"));
					wait.until(ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
					base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 50);
					base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
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
					break; // âœ… success, break the loop

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

			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> Tabs1 = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(Tabs1.get(3));
			driver.navigate().to(UtilityTestData.ERPURL);
			

			//In ERP,
			//Step 1. Search transfer Order.
			wait.until( ExpectedConditions.visibilityOfElementLocated(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input")));
			base.waitForClickable(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), 50);
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchTransferOrder);
			base.setZoom(driver, 85);
			Thread.sleep(8000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
			Thread.sleep(8000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("dyn-headerCellLabel _11w1prk","Ship date"));
			Thread.sleep(4000);
			base.setData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_ShipDate("textbox field displayoption viewMarker alignmentLeft","__FilterField_MainGrid_ShipDate_ShipDate_Input_0"), UtilityTestData.ShipDate);
			Thread.sleep(2000);
			base.setData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_ShipDate("textbox field displayoption viewMarker alignmentLeft","__FilterField_MainGrid_ShipDate_ShipDate_Input_1"), UtilityTestData.ShipDate);
			Thread.sleep(2000);
			base.buttonClick(StatementPostingObj.Btn_Add("MainGrid_ShipDate_ApplyFilters"));
			Thread.sleep(8000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("dyn-headerCellLabel _1rm4qln","Transfer status"));
			Thread.sleep(2000);
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("lookupButton", "3"));
			base.buttonClick(LoginPageObj.Ele_ERPNo("Shipped"));
			Thread.sleep(2000);
			base.buttonClick(StatementPostingObj.Btn_Add("MainGrid_TransferStatus_ApplyFilters"));
			Thread.sleep(8000);

			//Step 2.Find and Open Transfer order id.
			base.scrollToElement(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Transfer number", 1));
			Thread.sleep(8000);
			base.ClickCondition(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Transfer number", 1));
			Thread.sleep(10000);

			//Step 3.Click on receive button (Header)
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("appBarTab-headerLabel allowFlyoutClickPropagation", "Receive"));

			//Step 4. Click on receive button
			base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label", "Receive"));
			Thread.sleep(5000);

			//Step 5. Click on OK button
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label", "OK"));
			Thread.sleep(10000);

			//Step 6. Select update as"All"
			base.buttonClick(LoginPageObj.Ele_ErrorMessage("dyn-container _w611ys dyn-svg-symbol dyn-combobox-button"));
			Thread.sleep(3000);
			base.buttonClick(LoginPageObj.Ele_ERPNo("All"));
			Thread.sleep(5000);

			//Step 7. Click on OK button
			//Expected: Check if the status changed to received
			base.ClickCondition(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label", "OK"));
			Thread.sleep(30000);
			asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_OGProduct("Transfer status: Received")),"User is not able to view status changed to received");

		}
	}
	// <summary>
	// Test Case Title : Counter in the SKU and Check the counter(counter search)
	// Automation ID : TC226
	// Author: Chandana Babu
	// </summary>
	public void TC226_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice(String SkuNumber) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));	
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		//Step 2: Click on Transactions
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Click on Purchase
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on Counter transfer
		base.ClickCondition(LoginPageObj.Edt_AlertText("Counter Transfer"));

		//Step 5: Select Transfer type as stock in
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC226_TransferType);

		//Step 6: Select counter
		//Step 7: Enter the SKU and click enter
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("Product"), SkuNumber);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("Product"), "ENTER");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell", "CATEGORY")));
		String Category = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_AdvanceAdjustTrnsNo("h4 ellipsis cell", "CATEGORY"));
		String ToCounter = Category.split(" ")[0];
		if(ToCounter=="Precious") {
			ToCounter = "Precia";
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("ToCounter"), ToCounter);
		}
		else {
			base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("ToCounter"), ToCounter);
		}

		//Step 8: Click on Commit
		//Expected: Verify if validation appears saying "Stock transfer committed successfully !"
		base.ClickCondition((NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis")));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_return("Commit"));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageObj.Btn_SignInButton("OK")));

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Stock transfer committed successfully !")), "Validation message 'Stock transfer committed successfully !' is not displayed.");

		base.ClickCondition(LoginPageObj.Btn_SignInButton("OK"));
		appUtils.HamBurgerButtonClick("iconShop");       
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 button-content-abc primaryFontColor"));
		base.setData(NormalSaleGoldAndSilverObj.Edt_Name("Search or enter quantity"), SkuNumber);
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_AddtoCart("h1 marginBottom0 iconReturnKey button-content-return"));

		asrt.assertTrue(base.isExists(LoginPageObj.Edt_AlertText("Item details")),"Item details page is not displayed");
	}

	//<summary>
	// Test Case Title : Stock out bulk from POS and check transfer order from ERP
	// Automation ID : TC232
	// Author : Gokul.P 
	// </summary>
	public void TC232_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));		
		//Step 1: Login to POS
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconHome");
		for(int i=0;i<5;i++)
		{
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","POS Reports"));
			Thread.sleep(2000);
			try {
				if(wait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Btn_SignInButton("Stock Summary"))) != null) {
					break;
				}
			}catch(Exception e) {}
		}
		base.buttonClick(LoginPageObj.Btn_SignInButton("Stock Summary"));		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(2000);		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryBeforeStockOut = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary");
		String PdfPathBeforeStockOut = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryBeforeStockOut;
		String BulkWeightBeforeStockOut=pdfUtils.StockSummarySale1(PdfPathBeforeStockOut, "Back Chain");
		System.out.println("The Bulk Weight before StockOut:"+BulkWeightBeforeStockOut);

		// 2. Click on transaction button
		appUtils.HamBurgerButtonClick("iconShop");       

		// 3 Click on purchase		
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));

		// 4. Click on "Bulk item stock out" button
		base.ClickCondition(LoginPageObj.Edt_AlertText("Bulk Item Stock Out"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		// 5. Check date select as yesterday//238
		base.buttonClick(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_YesterdayDate("Week days drop down"));
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

		// 6.Search BC in the search field
		// 7.Choose BC
		// 8. Select the configuration
		// 9. Click on Add to bulk item stock out
		Thread.sleep(2000);
		appUtils.SearchBCProduct("BC", "22k");

		// 10. Enter gross weight
		base.clearData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_BulkWt("BulkItemStockOutView","GROSSWT"));
		base.setData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_BulkWt("BulkItemStockOutView","GROSSWT"),"1");

		// 11. Enter net weight
		base.clearData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_BulkWt("BulkItemStockOutView","NETWT"));
		base.setData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_BulkWt("BulkItemStockOutView","NETWT"),"1");

		// 12. Click on add item button
		base.actionClick(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_BulkAddItem("BulkItemStockOutView", "addItemButton"));

		// 13. Click on "Stock out" button
		Thread.sleep(2000);
		base.buttonClick(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_ViewMoreBulk("View more","menuitem"));
		base.actionClick(LoginPageObj.Edt_Alert("Stock out"));
		Thread.sleep(1000);
		asrt.assertTrue(base.isPresent(NormalSaleGoldAndSilverObj.Ele_CustomerAdjustment("Bulk item stock out saved successfully !")),"The Bulk item stock out saved successfully popup is not displayed correctly");

		base.buttonClick(LoginPageObj.Btn_SingnIn("Button1Close"));

		// Verify stock summary
		// 14. Click on home button
		Thread.sleep(5000);
		appUtils.HamBurgerButtonClick("iconHome");

		// 15. Click on POS report
		for(int i=0;i<5;i++)
		{
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","POS Reports"));
			Thread.sleep(2000);
			try {
				if(wait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Btn_SignInButton("Stock Summary"))) != null) 
				{
					break;
				}
			}catch(Exception e) {}
		}

		// 16. Click on stock summary	
		// Expected: Verify stock stock summary
		base.buttonClick(LoginPageObj.Btn_SignInButton("Stock Summary"));		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.buttonClick(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(2000);		
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryPdfName = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary");
		String PdfPath= System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryPdfName;
		String ActualBulkWeightAfterStockOut=pdfUtils.StockSummarySale1(PdfPath, "Back Chain");
		System.out.println("The Bulk Weight after Stock Out:"+ActualBulkWeightAfterStockOut);
		double ExpectedBulkWeightAfterStockOut=Double.parseDouble(BulkWeightBeforeStockOut)-1;
		asrt.assertEquals(Double.parseDouble(ActualBulkWeightAfterStockOut),ExpectedBulkWeightAfterStockOut,"The Bulk Weight after Stock out is not equal as expected: Expected: "+ExpectedBulkWeightAfterStockOut+" Actual: "+ActualBulkWeightAfterStockOut+"");

		//Transfer order
		// 18. Go to purchase
		appUtils.HamBurgerButtonClick("iconShop");   
		base.ClickCondition(LoginPageObj.Edt_AlertText("Purchase"));

		// 19.Select transfer shipment
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_CustomerAdjustment("Transfer Order Shipment"));

		// 20.Choose transfer type as Bulk
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC232_ShipTransferType );

		// 21.Select to warehouse
		Select select=new Select(driver.findElement((NormalSaleGoldAndSilverObj.Sel_DepositType("Warehouse"))));
		Thread.sleep(5000);
		List<WebElement> WarehouseOptions = select.getOptions();
		for (WebElement Option : WarehouseOptions) {
			if (Option.getText().toLowerCase().contains(UtilityTestData.WarehouseId+"BO".toLowerCase())) {   
				Option.click();
				break;
			}
		}

		// 22.Click on search button
		// 23.Select the transfer id
		// 24.Click on ok button
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
		Thread.sleep(5000);
		base.ClickClearEnter(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Txt_SearchBox("ReceiptNumber"),Utility_TestData.Terminal);
//		List<WebElement> TransferIdList= driver.findElements(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_OrderBalanceAmt("dataListLine", "Transfer id"));
//
//		for (int i = TransferIdList.size() - 1; i >= 0; i--) {
//			base.ScrollToWebElement(TransferIdList.get(TransferIdList.size() - 1));
//			WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//			if (TransferIdList.get(i).getText().contains(UtilityTestData.Terminal)) {
//				Wait.until(ExpectedConditions.elementToBeClickable(TransferIdList.get(TransferIdList.size() - 1)));
//				TransferIdList.get(i).click();
//				base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton", "OK"));	
//				Wait.until(ExpectedConditions.presenceOfElementLocated(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Product(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC232_Product, "gridcell")));
//				String ProductName= base.GetText(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Product(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC232_Product, "gridcell"));
//				if(ProductName.equals(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC232_Product))
//				{break;}else continue;
//			}
//		}
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.win-viewport.win-vertical")));
		WebElement ScrollContainer = driver.findElement(By.cssSelector("div.win-viewport.win-vertical"));
		JavascriptExecutor JsExecutor = (JavascriptExecutor) driver;
		Set<String> AllIdsCollected = new HashSet<>();
		System.out.println("Scrolling to collect all IDs...");
		while (true) {
		    List<WebElement> CurrentVisible = driver.findElements(By.cssSelector("div.h4.ellipsis.cell"));
		    for (WebElement El : CurrentVisible) {
		        String Txt = El.getText().trim();
		        if (Txt.contains("AG")) {
		            AllIdsCollected.add(Txt);
		        }
		    }
		    long BeforeScrollTop = ((Number) JsExecutor.executeScript("return arguments[0].scrollTop;", ScrollContainer)).longValue();
		    long ScrollHeight = ((Number) JsExecutor.executeScript("return arguments[0].scrollHeight;", ScrollContainer)).longValue();
		    long ClientHeight = ((Number) JsExecutor.executeScript("return arguments[0].clientHeight;", ScrollContainer)).longValue();
		    if (BeforeScrollTop + ClientHeight >= ScrollHeight - 10) break;
		    JsExecutor.executeScript("arguments[0].scrollTo(0, arguments[0].scrollTop + arguments[0].clientHeight);", ScrollContainer);
		    Thread.sleep(1200); 
		    long AfterScrollTop = ((Number) JsExecutor.executeScript("return arguments[0].scrollTop;", ScrollContainer)).longValue();
		    if (BeforeScrollTop == AfterScrollTop) {
		        JsExecutor.executeScript("arguments[0].scrollIntoView();", CurrentVisible.get(CurrentVisible.size() - 1));
		        Thread.sleep(800);
		        long RetryScrollTop = ((Number) JsExecutor.executeScript("return arguments[0].scrollTop;", ScrollContainer)).longValue();
		        if (BeforeScrollTop == RetryScrollTop) break; 
		    }
		}
		String TargetId = "";
		long HighestNumber = -1;
		for (String Id : AllIdsCollected) {
		    try {
		        String NumericPart = Id.substring(Id.lastIndexOf("AG") + 2);
		        long Num = Long.parseLong(NumericPart);
		        if (Num > HighestNumber) {
		            HighestNumber = Num;
		            TargetId = Id;
		        }
		    } catch (Exception E) { /* Skip malformed IDs */ }
		}
		System.out.println("Highest ID found: " + TargetId);
		JsExecutor.executeScript("arguments[0].scrollTop = 0;", ScrollContainer);
		boolean Clicked = false;
		while (!Clicked) {
		    List<WebElement> CurrentVisible = driver.findElements(By.cssSelector("div.h4.ellipsis.cell"));
		    for (WebElement El : CurrentVisible) {
		        if (El.getText().trim().equals(TargetId)) {
		            JsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", El);
		            Thread.sleep(500);
		            El.click();
		            Clicked = true;
		            System.out.println("Successfully clicked: " + TargetId);
		            break;
		        }
		    }           
		    if (!Clicked) {
		        long BeforeScroll = ((Number) JsExecutor.executeScript("return arguments[0].scrollTop;", ScrollContainer)).longValue();
		        JsExecutor.executeScript("arguments[0].scrollTop += 400;", ScrollContainer);
		        Thread.sleep(1000);
		        long AfterScroll = ((Number) JsExecutor.executeScript("return arguments[0].scrollTop;", ScrollContainer)).longValue();
		        if (BeforeScroll == AfterScroll) break; 
		    }
		}
		
		base.buttonClick(GPPSettlementAverageRateSingleSkuMultipleSkuObj.Ele_GPPMature("primaryButton", "OK"));
		wait.until(ExpectedConditions.presenceOfElementLocated(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Product(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC232_Product, "gridcell")));
		String ProductName= base.GetText(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Product(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC232_Product, "gridcell"));

		String StockOutId=base.GetValue(CustomerCreationObj.Ele_Name("TransferOrderId"));

		//Enter Mandatory fields: Way bill number,Enter Airway bill number and Remarks
		base.setData(CustomerCreationObj.Ele_Name("WayBillNumber"), StockOutId);
		base.setData(CustomerCreationObj.Ele_Name("AirwayBillNumber"), StockOutId);
		base.setData(CustomerCreationObj.Ele_StreetName("Remarks"),StockOutId);

		// 25.Click on ship button
		// Expected: Verify if validation appears saying SKU shipped successfully
		base.buttonClick(LoginPageObj.Btn_SingnIn("CartView_commitBarCommandCommand"));
		Thread.sleep(5000);
		asrt.assertTrue(base.isPresent(NormalSalesReturnGoldSilverReturnSaleCounterObj.Ele_SpecifyPrice("Success")),"User is not able to see the validation message :'SKU shipped successfully'");

		//ERP
		base.newWindow(1);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.get(UtilityTestData.ERPURL);
		Thread.sleep(5000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchProcessTransferOrderShipmentAtHO);
		base.setZoom(driver, 85);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(8000);
		if(base.isExists(LoginPageObj.Edt_Alert("Yes")));
		{
			base.buttonClick(LoginPageObj.Edt_Alert("OK"));
			Thread.sleep(10000);
		}
		if(base.isExists(OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactionsObj.Sel_BeginSKU("group_content layout-container layout-vertical","No","1")));
		{
			base.buttonClick(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Sel_TransferOrder("field", "toggle-box", "1"));
			base.buttonClick(LoginPageObj.Edt_Alert("OK"));
			Thread.sleep(10000);
		}
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> Tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(Tabs.get(1));
		driver.navigate().to(UtilityTestData.ERPURL);
		boolean jobEnded = false;
		int maxAttempts = 8; // 1 normal + 1 retry
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
				break; // âœ… success, break the loop

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
		driver.switchTo().window(Tabs.get(0));			
		// 1.Search transfer Order.
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), UtilityTestData.SearchTransferOrder);
		base.setZoom(driver, 85);
		Thread.sleep(8000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(8000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("dyn-headerCellLabel _11w1prk","Ship date"));
		Thread.sleep(4000);
		base.setData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_ShipDate("textbox field displayoption viewMarker alignmentLeft","__FilterField_MainGrid_ShipDate_ShipDate_Input_0"), UtilityTestData.ShipDate);
		Thread.sleep(2000);
		base.setData(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_ShipDate("textbox field displayoption viewMarker alignmentLeft","__FilterField_MainGrid_ShipDate_ShipDate_Input_1"), UtilityTestData.ShipDate);
		Thread.sleep(2000);
		base.buttonClick(StatementPostingObj.Btn_Add("MainGrid_ShipDate_ApplyFilters"));
		Thread.sleep(8000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Transaction("dyn-headerCellLabel _1rm4qln","Transfer status"));
		Thread.sleep(2000);
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_SKUNumber("lookupButton", "3"));
		base.buttonClick(LoginPageObj.Ele_ERPNo("Shipped"));
		Thread.sleep(2000);
		base.buttonClick(StatementPostingObj.Btn_Add("MainGrid_TransferStatus_ApplyFilters"));
		Thread.sleep(8000);

		//Step 2.Find and Open Transfer order id.
		base.buttonClick(InvoiceAdvanceWithPaymentMethodRTGSReceivedObj.Ele_ErpRtgs("Transfer number", 1));
		Thread.sleep(10000);

		//Step 3.Click on receive button (Header)
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("appBarTab-headerLabel allowFlyoutClickPropagation", "Receive"));

		//Step 4. Click on receive button
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label", "Receive"));

		//Step 5. Click on OK button
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label", "OK"));
		Thread.sleep(10000);

		//Step 6. Select update as"All"
		base.buttonClick(LoginPageObj.Ele_ErrorMessage("dyn-container _w611ys dyn-svg-symbol dyn-combobox-button"));
		Thread.sleep(3000);
		base.buttonClick(LoginPageObj.Ele_ERPNo("All"));

		//Step 7. Click on OK button
		//Expected: Check if the status changed to received
		base.buttonClick(NormalSaleGoldAndSilverObj.Ele_ERPCustAccnt("button-label", "OK"));
		Thread.sleep(30000);
		asrt.assertTrue(base.isExists(NormalSaleGoldAndSilverObj.Ele_OGProduct("Transfer status: Received")),"User is not able to view status changed to received");
	}


	// <summary>
	// Test Case Title : Create transfer order for bulk[Verify: *There is no POS warehouse listed in the dropdown*Receive button is disabled]
	// Automation ID : TC227
	// Author: Vishnu Manoj K
	// </summary>
	public String TC227_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception {	

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));		

		//Step 1: Login to POS 
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);
		String ParentWindow = driver.getWindowHandle(); 

		//Step 1: Go to ERP of required store .
		//PreRequisite Step 1: Search On hand list
		base.newWindow(1);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_Onhandlist);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//PreRequisite Step 2: Enter warehouse& Desig code(Eg:5205)
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventSum"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_Product);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventSum"), "ENTER");
		//Warehouse Selection
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventDim_InventLocationId"),UtilityTestData.WarehouseId+"BO");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("SystemDefinedFilterPane_FilterDisplay_InventDim_InventLocationId"), "ENTER");

		//PreRequisite Step 3: Click on Apply button
		base.excuteJsClick(CustomerCreationObj.Ele_BankAccounts("ApplyFilters_label"));

		//PreRequisite Step 4: Click on dimensions
		base.buttonClick(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Dimensions"));

		//PreRequisite Step 5:Click on checkbox
		//PreRequisite Step 5.1: Configuration
		base.buttonClick(CustomerCreationObj.Ele_BankAccounts("ProductDimensions_ConfigIdFlag_check"));

		//PreRequisite Step 5.2://Site
		base.buttonClick(CustomerCreationObj.Ele_BankAccounts("StorageDimensions_InventSiteIdFlag_check"));

		//PreRequisite Step 5.3://Warehouse
		base.buttonClick(CustomerCreationObj.Ele_BankAccounts("StorageDimensions_InventLocationIdFlag_check"));

		//PreRequisite Step 5.4://Batch Number
		base.buttonClick(CustomerCreationObj.Ele_BankAccounts("TrackingDimensions_InventBatchIdFlag_check"));

		//PreRequisite Step 6://Click on OK
		base.ScrollIntoView(CustomerCreationObj.Ele_BankAccounts("Ok_label"));
		Thread.sleep(500);
		base.buttonClick(CustomerCreationObj.Ele_BankAccounts("Ok_label"));

		// ---------------- Scroll horizontally-------------
		WebElement Scrollbar = driver.findElement(By.cssSelector(".ScrollbarLayout_faceHorizontal"));
		Actions ActionsObj = new Actions(driver);
		ActionsObj.clickAndHold(Scrollbar).moveByOffset(650, 0).release().perform();
		Thread.sleep(15000);

		// Get all AvailPhysical input fields
		List<WebElement> AvailInputs =base.GetElement(NormalSaleGoldAndSilverObj.Sel_AvailPhysicalList("AvailPhysical", "_input"));
		String BatchId="";
		String Purity="";
		for (WebElement AvailInput : AvailInputs) {
			String AvailValue = AvailInput.getAttribute("value");
			// Skip rows where AvailPhysical is empty
			if (AvailValue == null || AvailValue.trim().isEmpty()) {continue;}
			WebElement Row = AvailInput.findElement(By.xpath("./ancestor::div[contains(@class,'fixedDataTableRowLayout_rowWrapper')]"));
			// ----------- Fetch BatchId from same row --------------
			WebElement BatchInput = Row.findElement(By.xpath(".//input[contains(@id,'InventoryDimensionsGrid_inventBatchId') and contains(@id,'_input')]"));
			BatchId = BatchInput.getAttribute("value");
			// ----------- Fetch Purity (configId) from same row -----
			WebElement PurityInput = Row.findElement(By.xpath(".//input[contains(@id,'InventoryDimensionsGrid_configId') and contains(@id,'_input')]"));
			Purity = PurityInput.getAttribute("value");
			System.out.println("BatchId: " + BatchId +" | Purity: " + Purity +" | AvailPhysical: " + AvailValue);
		}

		//Step 2: Search Transfer Order
		base.newWindow(2);
		driver.get(UtilityTestData.ERPURL);
		base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_TransferOrder);
		Thread.sleep(5000);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
		Thread.sleep(10000);

		//Step 3: Click on New
		base.ClickCondition(LoginPageObj.Edt_Alert("New"));
		if(!base.isPresent(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"))) {
			base.clearData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"));
			base.setData(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_TransferOrder);
			Thread.sleep(5000);
			base.pressKey(NormalSaleGoldAndSilverObj.Ele_Name("NavigationSearchBox_searchBoxInput_input"), "ENTER");
			Thread.sleep(10000);
			base.ClickCondition(LoginPageObj.Edt_Alert("New"));			
		}

		//Step 4: Select From warehouse and To warehouse
		//Expected: Check if POS warehouse is not listed
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), UtilityTestData.WarehouseId);
		Thread.sleep(2000);
		List<WebElement>WareHouses = base.GetElement(NormalSaleGoldAndSilverObj.Ele_ERPReceiveAmt("Warehouse"));
		List<String>WareHousesList = new ArrayList<>();
		for(WebElement WareHouse : WareHouses ) {
			String WareHouseValue = WareHouse.getAttribute("value");
			WareHousesList.add(WareHouseValue);
		}

		asrt.assertFalse(WareHouses.contains(UtilityTestData.WarehouseId), "POS warehouse is listed in from warehouse list");

		base.clearData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), UtilityTestData.WarehouseId+"BO");
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdFrom"), "ENTER");
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdTo"), UtilityTestData.WarehouseId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("LineViewHeader_InventLocationIdTo"), "ENTER");
		Thread.sleep(1000);

		//Step 5: Select Transfer type as "Bulk"
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ErpInputArrowClick("Transfer type","InventTransferTable","lookupDock-buttonContainer layout-ignoreArrange","lookupButton"));
		base.excuteJsClick(LoginPageObj.Ele_ERPYes("Bulk"));
		Thread.sleep(1000);

		//Step 6: Select Accounted under as "Gold"
		base.clearData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDMetalType"));
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDMetalType"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_AccountedUnder);

		//Step 7: Select Purpose code as "Stock Transfer"[00001]
		base.setData(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDPurposeCode"), StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_StockTransfer);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_WarehouseName("InventTransferTable_MGDPurposeCode"), "ENTER");
		Thread.sleep(1000);

		//Step 8.In Tranfer order lines ,Click on "Add"
		base.buttonClick(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Add"));

		//Step9.Select Design Code as BC
		By DesignCodeField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Design Code", 1);
		base.buttonClick(DesignCodeField);
		Thread.sleep(1000);
		WebElement DesignCodeFieldEl = driver.findElement(DesignCodeField);
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						DesignCodeFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						DesignCodeFieldEl,StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_Product
				);
		base.pressKey(DesignCodeField, "ENTER");
		Thread.sleep(1000);

		//10.In Line details, Add the Quantity
		base.scrollToElementtoCenter(NormalSaleGoldAndSilverObj.Btn_Deposit("Line details"));
		appUtils.ClickWithRetry(NormalSaleGoldAndSilverObj.Btn_Deposit("Line details"), ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Dimensions"), 5, 2);
		//base.buttonClick(NormalSaleGoldAndSilverObj.Btn_Deposit("Line details"));
		Thread.sleep(12000);
		base.scrollToElementtoCenter(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Dimensions"));
		base.buttonClick(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Dimensions"));
		//Purity
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_configId"),Purity);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_configId"), "ENTER");
		//Batch Number
		base.setData(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_inventBatchId"),BatchId);
		base.pressKey(NormalSaleGoldAndSilverObj.Ele_ERPCustAccntisExactly("InventoryDimensionsLine_inventBatchId"), "ENTER");
		//Ingredients
		base.buttonClick(ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscountObj.Ele_SpanText("Ingredient"));
		Thread.sleep(12000);
		By QuantityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Quantity", 1);
		base.buttonClick(QuantityField);
		Thread.sleep(1000);
		WebElement QuantityFieldEl = driver.findElement(QuantityField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						QuantityFieldEl, StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_AddLineDetailQty
				);
		base.pressKey(QuantityField, "ENTER");
		Thread.sleep(1000);

		//Step 11: In Transfer order lines, Add Transfer quantity
		By TransferQuantityField = NormalSaleGoldAndSilverObj.Ele_ErpRtgs("Transfer quantity", 1);
		base.buttonClick(TransferQuantityField);
		Thread.sleep(1000);
		WebElement TransferQuantityFieldEl = driver.findElement(TransferQuantityField);
		Js.executeScript(
				"const el = arguments[0]; el.focus(); el.value='';" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						TransferQuantityFieldEl
				);
		Js.executeScript(
				"const el = arguments[0], val = arguments[1];" +
						"const setter = Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set;" +
						"setter.call(el, val);" +
						"el.dispatchEvent(new Event('input', {bubbles:true}));" +
						"el.dispatchEvent(new Event('change', {bubbles:true}));",
						TransferQuantityFieldEl, StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_TransferOrderLineQty
				);
		base.pressKey(TransferQuantityField, "ENTER");
		Thread.sleep(1000);

		//Step 12: Click on Calculate line
		base.ClickCondition(LoginPageObj.Edt_Alert("Calculate line"));

		//Step 13: Click on ship
		wait.until(ExpectedConditions.visibilityOfElementLocated(StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeObj.Ele_Check("InventTransferLine_MGDCostCalculation", "Yes")));		
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("ShipmentTab_button"));
		Thread.sleep(1000);

		//Step 14: Click on Generate picking list
		base.ClickCondition(LoginPageObj.Edt_Alert("Generate picking list"));

		//Step 15: Select "All" in Update dropdown and click on OK
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ErpArrowClick("pickUpdateQty","symbol"));
		base.buttonClick(LoginPageObj.Ele_ERPNo("All"));	
		Thread.sleep(2000);
		base.excuteJsClick(StatementPostingObj.Btn_Add("OK"));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoginPageObj.Edt_Alert("Processing operation - Transfer order - Picking list")));

		//Step 16: Click on "Ship transfer order"
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("ShipmentTab_button"));
		base.ClickCondition(LoginPageObj.Edt_Alert("Ship transfer order"));

		//Step 17: Select "All" in Update dropdown and click on OK
		//Expected: Check the status of the order has been changed to shipped
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_ErpArrowClick("ShipUpdateQty","symbol"));
		base.buttonClick(LoginPageObj.Ele_ERPNo("All"));
		Thread.sleep(1000);
		base.excuteJsClick(StatementPostingObj.Btn_Add("OK"));
		Thread.sleep(4000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoginPageObj.Edt_Alert("Processing operation - Transfer order - Shipment")));
		Thread.sleep(2000);
		String Status = base.GetAttribte(NormalSaleGoldAndSilverObj.Ele_WarehouseName("EntityStatus_TransferStatus"), "title");

		asrt.assertEquals(Status, StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_Status,"Status displayed mismatched: Expected is "+StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC224_Status+" but got "+Status+" in ERP transfer order page");

		String TransferOrder = base.GetText(AdvanceSettlementNormalAdvanceOfferAdvanceConvertToAdvanceAddOnAdvanceLegacyPosObj.Ele_MetalRateBlankMsg("Transfer order:"));
		String TransferOrderNumber = TransferOrder.split(":")[1].trim();	
		driver.switchTo().window(ParentWindow);

		//Step 2: Click on Transactions
		appUtils.HamBurgerButtonClick("iconShop");

		//Step 3: Click on Purchase
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		//Step 4: Click on Transfer order Receive
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Transfer Order Recieve"));

		//Step 5: Select Transfer type as Bulk
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_TransferType);

		//Step 6: Enter Transfer order id
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: ReceiptNumber"), TransferOrderNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_TransferIdSearch("win-itembox",TransferOrderNumber,"h4 ellipsis cell"));
		base.excuteJsClick(LoginPageObj.Btn_SignInButton("OK"));

		//Step 7: Click on Receive
		Thread.sleep(5000);
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("CartView_commitBarCommandCommand"));
		return TransferOrderNumber;
	}

	// <summary>
	// Test Case Title : Check Stock summary and stock details before and after the stock movements
	// Automation ID : TC233
	// Author: Christy Reji
	// </summary>
	public void TC233_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception {	

		//Step 1: Login to POS 
		login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
		base.setZoom(driver, 60);

		// Step 2. Click on home button
		appUtils.HamBurgerButtonClick("iconHome");

		//Step 3:Click on POS report
		base.ClickCondition(LoginPageObj.Edt_AlertText("POS Reports"));
		Thread.sleep(2000);

		// Step 4:Click on stock summary Report
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(5000);

		//Step 5: Click on View Report
		//Note: check the values
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryBeforeSalePath = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_Before_StockMovement");
		String StockSummaryBeforeSalePDF  = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryBeforeSalePath;
		String BackChainGrossWeightBefore = pdfUtils.StockSummarySale1(StockSummaryBeforeSalePDF, "Back Chain");

		//Creating a bulk tranfer
		String TransferOrderNumber = erpUtils.CreateTranferOrderBulk();

		//Receiving the bulk in POS
		//Click on Transactions,Purchase
		appUtils.HamBurgerButtonClick("iconShop");
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

		//Click on Transfer order Receive
		Thread.sleep(1000);
		base.buttonClick(LoginPageObj.Edt_AlertText("Transfer Order Recieve"));

		//Step 5: Select Transfer type as Bulk
		base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_TransferType);

		//Step 6: Enter Transfer order id
		base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
		Thread.sleep(1000);
		base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: ReceiptNumber"), TransferOrderNumber);
		base.buttonClick(NormalSaleGoldAndSilverObj.Btn_TransferIdSearch("win-itembox",TransferOrderNumber,"h4 ellipsis cell"));
		base.excuteJsClick(LoginPageObj.Btn_SignInButton("OK"));

		//Step 7: Click on Receive
		Thread.sleep(5000);
		base.excuteJsClick(LoginPageObj.Btn_SingnIn("CartView_commitBarCommandCommand"));
		base.buttonClick(LoginPageObj.Btn_SignInButton("OK"));
		System.out.println("=====Bulk received from POS; checking Stock summary.=====");

		//Step 2.1:Click on POS report
		WebDriverWait HamBurgerWait = new WebDriverWait(driver, Duration.ofSeconds(5));	
		appUtils.HamBurgerButtonClick("iconHome");

		//Step3.1 Click on POS report
		Thread.sleep(3000);
		for(int i=0;i<=5;i++)
		{
			base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Btn_Pos("buttonGridShift","POS Reports"));
			try {
				if(HamBurgerWait.until(ExpectedConditions.presenceOfElementLocated(LoginPageObj.Btn_SignInButton("Stock Summary"))) != null)
				{break;}
			}catch(Exception e) {}
		}

		// Step 4.1:Click on stock summary Report
		base.ClickCondition(LoginPageObj.Btn_SignInButton("Stock Summary"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		base.ClickCondition(NormalSalesReturnGoldSilverReturnSaleCounterObj.Cb_ItemName("Bulk"));
		Thread.sleep(5000);

		//Step 5.1: Click on View Report
		//Note: check the values
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
		base.ClickCondition(NormalSaleGoldAndSilverObj.Btn_MenuBarOptions("saveBarCommandCommand"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
		String StockSummaryAfterSalePath = PdfUtilities.DownloadAndRenameLatestPDF("Stock Summary_BackChainGrossWeightAfter_StockMovement");
		String StockSummaryAfterSalePDF  = System.getProperty("user.dir") + "\\Invoices\\" + StockSummaryAfterSalePath;
		String BackChainGrossWeightAfter = pdfUtils.StockSummarySale1(StockSummaryAfterSalePDF, "Back Chain");
		System.out.println("Wt mentioned in summary report under bulk before and after  stock movements");
		System.out.println("BackChain  | BC  | GrossWt : "+BackChainGrossWeightBefore+" | Before StockMovement");
		System.out.println("BackChain  | BC  | GrossWt : "+BackChainGrossWeightAfter+" | After StockMovement");

		//Validation
		Assert.assertNotEquals(BackChainGrossWeightBefore, BackChainGrossWeightAfter,"Match in BC GrossWt : Expected '" + BackChainGrossWeightBefore + "', and found '" + BackChainGrossWeightAfter + "' in the Stock summary PDF under Bulk");
        pdfUtils.DeleteAllPDFFilesInInvoiceFolder();
	}
	
	        // <summary>
			// Test Case Title : Receive bulk from POS
			// Automation ID : TC228
			// Author: Vishnu RR
			// </summary>
	
			public String TC228_StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice() throws Exception {
				
				//Step 1: Login to POS 
				login.loginToApplication(CommonData.UserName1, CommonData.PassWord1);
				base.setZoom(driver, 60);
				String ParentWindow = driver.getWindowHandle(); 
				
				//Prerequisite - Creating a Bulk Transfer
				String TransferOrderNumber = erpUtils.CreateTranferOrderBulk();
				
				Thread.sleep(5000);
				driver.switchTo().window(ParentWindow);
				
				//Step 2: Click on Transactions
				appUtils.HamBurgerButtonClick("iconShop");

				//Step 3: Click on Purchase
				base.buttonClick(LoginPageObj.Edt_AlertText("Purchase"));

				//Step 4: Click on Transfer order Receive
				Thread.sleep(1000);
				base.buttonClick(LoginPageObj.Edt_AlertText("Transfer Order Recieve"));

				//Step 5: Select Transfer type as Bulk
				base.selectorByVisibleText(NormalSaleGoldAndSilverObj.Sel_DepositType("TransferType"),StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOfficeTestData.TC227_TransferType);

				//Step 6: Enter Transfer order id
				//Expected: Check the required transfer order is present
				base.excuteJsClick(NormalSaleGoldAndSilverObj.Btn_ViewMore("win-commandingsurface-ellipsis win-appbar-ellipsis"));
				base.excuteJsClick(LoginPageObj.Btn_SingnIn("CartView_searchBarCommandCommand"));
				Thread.sleep(1000);
				base.setData(NormalSaleGoldAndSilverObj.Ele_SameAsAbove("value: ReceiptNumber"), TransferOrderNumber);

				base.buttonClick(NormalSaleGoldAndSilverObj.Btn_TransferIdSearch("win-itembox",TransferOrderNumber,"h4 ellipsis cell"));
				base.excuteJsClick(LoginPageObj.Btn_SignInButton("OK"));
				Thread.sleep(3000);
				
				asrt.assertTrue(base.isExists(CustomerCreationObj.Ele_Name("TransferOrderId")), "Required transfer order is not present");

				//Step 7: Click on Receive
				Thread.sleep(5000);
				base.excuteJsClick(LoginPageObj.Btn_SingnIn("CartView_commitBarCommandCommand"));
				return TransferOrderNumber;
	
			}
		
}