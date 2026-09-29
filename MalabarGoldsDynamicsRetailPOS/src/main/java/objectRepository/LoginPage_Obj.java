package objectRepository;

import org.openqa.selenium.By;

public class LoginPage_Obj {
	//Btn	
	public By Btn_Login(String text) { return By.xpath("//a[text()='"+text+"']"); }
	public By Btn_LoginButton(String login) {return By.xpath("//a[@class='"+login+"']");}
	public By Btn_SignInButton(String text) { return By.xpath("//button[text()='"+text+"']"); }
	public By Btn_Captcha(String check) {return By.xpath("//span[@role='"+check+"']");}
	public By Btn_SingnIn(String id) {return By.xpath("//button[@id='"+id+"']");}
	public By Btn_SigninClick(String onclick) { return By.xpath("//button[@onclick='"+onclick+"']"); }
	public By Btn_SignInERP(String id) { return By.xpath("//button[contains(@id,'" + id + "')]");}
	public By Btn_Logout(String id, String text) { return By.xpath("//div[@id='"+id+"']//button[@class='"+text+"']");}

	//Edt
	public By Edt_LoginCredentialFields(String id) { return By.xpath("//*[@id='"+id+"']"); }
	public By Edt_LoginEmail(String email) { return By.xpath("//input[@type='"+email+"']"); }
	public By Edt_Alert(String text) { return By.xpath("//span[text()='"+text+"']"); }
	public By Edt_AlertText(String text) { return By.xpath("//div[text()='"+text+"']"); }
	public By Edt_PosReport(String text) { return By.xpath("//*[@id=\"WelcomeScreenButtonGrid-0\"]//div[@class='buttonsContainer positionRelative']//button[@title='"+text+"']"); }
	public By Mult_Text(String text,String ltext) { return By.xpath("//div[normalize-space()='"+text+"' or normalize-space()='"+ltext+"']");}
	
	//Ele
	public By Ele_ErrorMessage(String error) {return By.xpath("//div[@class='"+error+"']");}
	public By Ele_SnipBackHomePageLogo(String id) {return By.xpath("//img[@id='"+id+"']");}
	public By Ele_LogoutOptions(String title) { return By.xpath("//li[contains(@class,'org-wrap') and @title='"+title+"']");}

	//ERPQuery
	public By Ele_ERPQuery(String text) {return By.xpath("//textarea[contains(@id,'"+text+"')]");}
	public By Ele_ERPAdjust(String list) {return By.xpath("//input[@aria-autocomplete='"+list+"']");}
	public By Ele_ERPYes(String Yes) {return By.xpath("//li[normalize-space(text())='"+Yes+"']");}
	public By Ele_ERPNo(String No) {return By.xpath("//li[text()='"+No+"']");}
	public By Ele_TransactionID(String ID) {return By.xpath("//div[@data-dyn-controlname='"+ID+"']/child::div/input");}
}
