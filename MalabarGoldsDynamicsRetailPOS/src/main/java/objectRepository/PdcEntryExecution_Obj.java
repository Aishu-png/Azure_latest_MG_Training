package objectRepository;

import org.openqa.selenium.By;

public class PdcEntryExecution_Obj {
	//Ele
	public By Ele_ErpPdc(String titleValue, int index) {return By.xpath("(//div[@title='" + titleValue + "'])[" + index + "]");}
	public By Ele_ChequePresentedCheckbox(String classValue) {return By.xpath("//div[contains(@class,'" + classValue + "')]//span");}
	public By Ele_GppAccountPdc(String TextValue, String SchemeId) {return By.xpath("//div[@class='" + TextValue + "' and normalize-space(text()='" + SchemeId + "')])");}
	public By Ele_OrderID(String error, String cls,String index) {return By.xpath("(//div[@class='"+error+"']//div[text()='"+cls+"'])["+index+"]");}
	public By Ele_PdcNormalAdvance(String TextValue, String Arialabel,String index) {return By.xpath("(//div[contains(@id,'"+TextValue+"')]//input[contains(@aria-label,'"+Arialabel+"')])["+index+"]");}
	public By Ele_ChequeAmount(String TextValue) {return By.xpath("//input[contains(@id,'"+TextValue+"')]");}	
	public By Ele_Payment(String textValue) {return By.xpath("//div[contains(normalize-space(text()), '" + textValue + "')]");}
}