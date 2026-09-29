package objectRepository;

import org.openqa.selenium.By;

public class InvoiceAdvanceWithPaymentMethodRtgsReceived_Obj {
	
	//Ele
	public By Ele_RtgsBalance(String inputType, String dataBindValue) {return By.xpath("//input[@type='" + inputType + "' and contains(@data-bind,'value: " + dataBindValue + "')]");}
	public By Ele_CustomerAccount(String columnName) {return By.xpath("//div[@data-dyn-columnname='" + columnName + "']");}
	public By Ele_CustomerAccountText(String columnName) {return By.xpath("//div[@data-dyn-columnname='" + columnName + "']/div");}
	public By Ele_CustomerAccountFilter(String columnName) {return By.xpath("//div[@data-dyn-form-name='" + columnName + "' and @data-dyn-lastvisible='true']");}
	public By Ele_GridAmount(String id) {return By.xpath("//input[contains(@id,'" + id + "')]");}
	public By Ele_ErpRtgs(String ariaLabelValue, int index) { return By.xpath("(//input[@aria-label='" + ariaLabelValue + "'])[" + index + "]");}
	public By Ele_AdjustmentLastEle(String cls) { return By.xpath("(//div[@class='"+cls+"'])[last()]") ; }
	public By Ele_MetalRate(String cls,String aria,String role) { return By.xpath("(//div[@class='"+cls+"'])[last()]//div[contains(@aria-label,'"+aria+"') and @role='"+role+"']"); }
	
}
