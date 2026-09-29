package objectRepository;

import org.openqa.selenium.By;

public class ApplyGiftCoinDiscountPromotionDiscountSilverCoinDiscount_Obj 
{
    //Btn
	public By Btn_DateClick(String Title, String AriaDescribedBy) {
	    return By.xpath("//div[@data-dyn-controlname='" + Title + "']//div[contains(@class,'" + AriaDescribedBy + "')]");}	
	//Ele
	public By Ele_SpanText(String text) {return By.xpath("//span[normalize-space()='" + text + "']");}
	public By Ele_Date(String DayValue) {return By.xpath("//td[normalize-space()='" + DayValue + "']");}	
	public By Ele_FullDate(String fullDate, String DayValue) {return By.xpath("//td[@data-date='" + fullDate + "' and normalize-space()='" + DayValue + "']");}
	public By Ele_ActiveStatus(String ariaLabel, String titleValue) { return By.xpath("(//div[@aria-label='" + ariaLabel + "']//div[@title='" + titleValue + "'])[1]");}
	public By Ele_ERPStatus(String Custid, int index) { return By.xpath("(//input[@aria-label='" + Custid + "'])[" + index + "]");}
	public By Ele_Delete(String title) { return By.xpath("//div[@title='"+title+"']");}
	public By Ele_InputFieldByLabel(String labelText) {return By.xpath("//label[normalize-space()='" + labelText + "']/following::input[1]");}
}