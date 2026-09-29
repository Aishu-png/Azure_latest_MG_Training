package objectRepository;

import org.openqa.selenium.By;

public class BulkItemSalesSalesReturnServiceItemSalesStockSummary_Obj {
	
	public By Ele_ProductList(String Cls, String Txt){return By.xpath("//div[@class='"+Cls+"']//div[contains(text(),'"+Txt+"')]");}

	public By Ele_CustomerSearch(String text) {return By.xpath("//h5[text()='"+text+"' and contains(@class,'nowrap whitespaceNoWrap')]");}
	public By Ele_TransactionPage(String Cls,String Text ) { return By.xpath("//div[contains(@class,'"+Cls+"')]//div[text()='"+Text+"']");}

	public By Ele_ErpCreditNo(String Value, int index) { return By.xpath("(//input[@aria-label='" + Value + "' and @title and @value])[" + index + "]");}

	public By Ele_MakingDiscountAmount(String ElementId, String DataBindValue) {
	    return By.xpath("//input[@id='" + ElementId + "' and contains(@data-bind, '" + DataBindValue + "')]");	}
	
	public By Txt_WastageAmount(String Cls,String Text ) { return By.xpath("//input[@id='"+Cls+"' and contains(@data-bind,'"+Text+"')]");}

}