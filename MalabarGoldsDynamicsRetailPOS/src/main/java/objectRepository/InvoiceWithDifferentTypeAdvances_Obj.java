package objectRepository;

import org.openqa.selenium.By;

public class InvoiceWithDifferentTypeAdvances_Obj {

	//Ele
	public By Ele_Offer(String Class, String OrderId, String Arialabel) {return By.xpath("//div[@class='"+Class+"' and text()='"+OrderId+"'] /ancestor::div[1] /following-sibling::div//div[@aria-label='"+Arialabel+"']");}
	public By Ele_OfferAdvanceRate(String ArialabelGoldQty, String MetalRate, String Class) {return By.xpath("//div[@aria-label='"+ArialabelGoldQty+"']/parent::*/preceding-sibling::*//div[contains(@aria-label,'"+MetalRate+"') and @class='"+Class+"']");}
	public By Ele_NormalQtyFetch(String Boolean, String Prod, String Role) {return By.xpath("//div[@aria-selected='"+Boolean+"']//div[contains(@aria-label,'"+Prod+"') and @role='"+Role+"']");}
	public By Ele_CustomerOrderNoDropDown(String CustomerOrder) {return By.xpath("//label[text()='"+CustomerOrder+"']/following::select");}	
	public By Ele_OrderOptions(String DataBindOption ) {return By.xpath("//select[contains(@data-bind,'"+DataBindOption+"')]//option");}

}
