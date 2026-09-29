package objectRepository;

import org.openqa.selenium.By;

public class OldExchangeOwnDiamondPlatinumPreciaUncutStoreTransactions_Obj {

	//Ele
	public By Sel_SKUNuber(String text, String Class) {return By.xpath("//div[@id='"+text+"']//span[@class='"+Class+"']");}
	public By Sel_BeginSKU(String Class, String text, String index) {return By.xpath("(//div[@class='"+Class+"']//span[text()='"+text+"'])["+index+"]");}
	public By Sel_SKU(String text, String index) {return By.xpath("(//input[@aria-label='"+text+"'])["+index+"]");}
	public static By Sel_ODCValue(String ele) { return By.xpath("//div[starts-with(@aria-label, '"+ele+"')]");}
	public static By Sel_Amt (String formName, String lastVisible) {
	    return By.xpath("//div[@data-dyn-form-name='" + formName + "' and @data-dyn-lastvisible='" + lastVisible + "']");}
	public static By RateField(String AriaLabel, String Class, String Field) { return By.xpath("//div[contains(@aria-label,'"+AriaLabel+"')]//div[@class='"+Class+"' and contains(@aria-label,'"+Field+"')]");}
	public By Ele_InputInsideVisibleDiv(String parentClass1,
            String parentClass2,
            String inputId) {return By.xpath("//div[contains(@class,'" + parentClass1 + "') " +
"and contains(@class,'" + parentClass2 + "') " +
"and not(contains(@style,'visibility: hidden'))]" +
"//input[@id='" + inputId + "']");
}
}
