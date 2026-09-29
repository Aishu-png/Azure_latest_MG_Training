package objectRepository;

import org.openqa.selenium.By;

public class CheckGPPRefundBenefitWithMultipleCategoryItem_Obj {
	
	public By Ele_CustomerAccountInput(String className, String ariaLabel) {
	    return By.xpath("//div[@class='" + className + "']//input[@aria-label='" + ariaLabel + "']");
	}
	
	public By Ele_Refunded(String text, String idPart) {
	    return By.xpath("//li[text()='" + text + "' and contains(@id, '" + idPart + "')]");
	}
	
}