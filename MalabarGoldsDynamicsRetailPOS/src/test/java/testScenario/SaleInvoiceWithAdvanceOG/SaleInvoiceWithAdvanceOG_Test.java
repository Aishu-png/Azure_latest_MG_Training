package testScenario.SaleInvoiceWithAdvanceOG;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import testData.CommonData;
import testPage.Login;
import testPage.NormalSaleDiamondandPlatinum;
import testPage.NormalSaleGoldandSilver;
import testPage.SaleInvoiceWithAdvanceOG;
import utilPack.BaseTest;

public class SaleInvoiceWithAdvanceOG_Test extends BaseTest 
{	
	SaleInvoiceWithAdvanceOG saleInvoiceWithAdvanceOG;
	
	@Test
	public void TC155_SaleInvoiceWithAdvanceOGTest() throws Exception
	{
		saleInvoiceWithAdvanceOG=new SaleInvoiceWithAdvanceOG(driver);
		saleInvoiceWithAdvanceOG.TC155_SaleInvoiceWithAdvanceOG();
	}

	@Test
	public void TC158_SaleInvoiceWithAdvanceOGTest() throws Exception
	{
		saleInvoiceWithAdvanceOG=new SaleInvoiceWithAdvanceOG(driver);
		saleInvoiceWithAdvanceOG.TC158_SaleInvoiceWithAdvanceOG();
	}
	
	@Test
	public void TC157_SaleInvoiceWithAdvanceOGTest() throws Exception
	{
		saleInvoiceWithAdvanceOG=new SaleInvoiceWithAdvanceOG(driver);
		saleInvoiceWithAdvanceOG.TC157_SaleInvoiceWithAdvanceOG();
	}
	
	@Test
	public void TC159_SaleInvoiceWithAdvanceOGTest() throws Exception
	{
		saleInvoiceWithAdvanceOG=new SaleInvoiceWithAdvanceOG(driver);
		saleInvoiceWithAdvanceOG.TC159_SaleInvoiceWithAdvanceOG();
	}

	@Test
	public void TC156_SaleInvoiceWithAdvanceOGTest() throws Exception
	{
		saleInvoiceWithAdvanceOG=new SaleInvoiceWithAdvanceOG(driver);
		saleInvoiceWithAdvanceOG.TC156_SaleInvoiceWithAdvanceOG();
	}
	
	
	
}