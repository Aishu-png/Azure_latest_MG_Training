package objectRepository;

import org.openqa.selenium.By;

public class AdvanceRepayment_Obj {

	//Txt
	public By Txt_BalanceAmount(String DataBind,String DepositAmount){return By.xpath("//div[@class='"+DataBind+"' and text()='"+DepositAmount+"' and contains(@aria-label,'Balance amount')]");}
	public By Txt_MetalRate(String BalanceAmount,String MetalType){return By.xpath("//div[contains(@aria-label,'"+BalanceAmount+"')]//div[@class='h4 ellipsis cell' and contains(@aria-label,'"+MetalType+"')]");}

	public By Ele_Row(String h4, String MetalType) {return By.xpath("//div[@class='"+h4+"' and contains(@aria-label,'"+MetalType+"')]");}
	public By Ele_Scroll(String Cls) {return By.cssSelector(Cls);}
}