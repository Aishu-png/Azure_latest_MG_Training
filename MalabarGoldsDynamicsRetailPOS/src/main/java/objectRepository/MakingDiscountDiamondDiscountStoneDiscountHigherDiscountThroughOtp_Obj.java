
package objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MakingDiscountDiamondDiscountStoneDiscountHigherDiscountThroughOtp_Obj {

	//Btn
	public By Btn_Discount(String title, String ariadescribedby ) {return By.xpath("//button[@title='"+title+"' and contains(@aria-describedby,'"+ariadescribedby+"')]");}
	public By Btn_ApplyDiscount(String databind, String text ) {return By.xpath("//div[@data-bind='"+databind+"']//button[text()='"+text+"']");}
	public By Btn_ApplyRoundOffDiscount(String databind,String text ) {return By.xpath("//button[@data-bind='"+databind+"' and text()='"+text+"']");}
}


