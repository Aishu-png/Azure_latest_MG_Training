package objectRepository;

import org.openqa.selenium.By;

public class StockReceiptCounterInBulkSkuStockTransferCounterOutBulkSkuProccessTransferOrderFromBackOffice_Obj {
	public By Ele_SearchBox(String Placeholder,String db) {return By.xpath("//input[contains(@placeholder,'"+Placeholder+"') and @id='"+db+"']");}
	public By Ele_Check(String Id,String Title) {return By.xpath("//div[contains(@id,'"+Id+"')]//div[@title='"+Title+"']");}
	public By Ele_ShipDate(String Class, String Field) {return By.xpath("//input[@class='"+Class+"' and contains(@aria-describedby,'"+Field+"')]");}
	public By Ele_YesterdayDate(String arialabel) {return By.xpath("//select[@aria-label='"+arialabel+"' and not(@disabled)]");}
	public By Ele_Product(String ProductName, String role) {return By.xpath("//div[text()='"+ProductName+"' and @role='"+role+"']");}
	public By Ele_BulkWt(String action, String id) {return By.xpath("//div[contains(@action,'"+action+"') and not(contains(@style,'visibility: hidden'))]//input[@id='"+id+"']");}
	public By Ele_BulkAddItem(String action, String id) {return By.xpath("//div[contains(@action,'"+action+"') and not(contains(@style,'visibility: hidden'))]//button[@id='"+id+"']");}
	public By Txt_SearchBox(String Value) {return By.xpath("//input[contains(@data-bind,'value: "+Value+"')]");}
	public By Ele_ViewMoreBulk(String action, String id) {return By.xpath("//button[@aria-label='"+action+"' and not(@role='"+id+"')]");}

	//Select
	public By Sel_TransferOrder(String Class, String text, String index) {return By.xpath("(//div[@class='"+Class+"']//span[@class='"+text+"'])["+index+"]");}
}
