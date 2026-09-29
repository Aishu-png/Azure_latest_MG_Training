package objectRepository;

import org.openqa.selenium.By;

public class GenerateEstimateFromOneUserRoleAndPostTheBillFromAnotherUser_Obj {
	
	public By Btn_HamburgerReset(String cls)  { return By.xpath("//button[contains(@class,'"+cls+"')]");}
	public By Ele_Shifts(String aria,String role) { return By.xpath("//div[contains(@aria-label,'"+aria+"') and @role='"+role+"']");}

}
