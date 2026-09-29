package objectRepository;

import org.openqa.selenium.By;

public class CheckAdvanceRefundBenefitWithGldMultipleCategoryItem_Obj {
	
	public By Ele_DepositType(String Role,String Id) { return By.xpath("//div[@role='"+Role+"']//div[contains(@id,'"+Id+"')]"); }
	public By Ele_SearchResult(String Cls,String Text) { return By.xpath("//span[@class='"+Cls+"' and contains(text(),'"+Text+"')]"); }
	public By Ele_Apply() { return By.xpath("//div[@data-dyn-lastvisible='true']/descendant::span[contains(@id,'ApplyFilters_label')]"); }
	public By Sel_AdvanceType(String AdvanceType) { return By.xpath("//label[text()='"+AdvanceType+"']/../select"); }
	public By Sel_AdvanceWeight(String Cls,String Aria,String Role) {return By.xpath("//div[@class='"+Cls+"']/descendant::div[contains(@aria-label,'"+Aria+"') and @role='"+Role+"']");}
	public By Btn_Back(String Cls,String Aria) {return By.xpath("//button[@aria-label='"+Cls+"' and @title='"+Cls+"']");}
	public By Ele_OfferAdvanceWeight(String Cls,String Aria,String AriaLabel,String Role) { return By.xpath("//div[@class='"+Cls+"' and @aria-selected='"+Aria+"']//div[contains(@aria-label,'"+AriaLabel+"') and @role='"+Role+"']");}
	public By Ele_TransactionField(String ariaLabel, String className, int index) {return By.xpath( "(//div[contains(@aria-label,'" + ariaLabel + "') and contains(@class,'" + className + "')])[" + index + "]");}
	public By Ele_GridSelected(String idName, String roleType) {return By.xpath("//div[contains(@id,'"+idName+"') and @role='"+roleType+"']//span");}

}
