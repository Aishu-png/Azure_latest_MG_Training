package objectRepository;

import org.openqa.selenium.By;

public class InvoiceWithAdvanceSchemeAndOG_Obj {
	
	public By Ele_Scheme(String cls, String cls2, int index) {
	    return By.xpath("(//div[@class='" + cls + "']//div[@class='" + cls2 + "'])[" + index + "]");
	}
	
	public By Ele_NormalAdv(String className, double value) {
	    return By.xpath("//div[@class='" + className + "' and number(normalize-space(text()))=" + value + "]");
	}

}