package objectRepository;

import org.openqa.selenium.By;

public class OldItemStockOutAndShipment_Obj {

	//Ele
	public By Ele_FromDate(String id ,String Path) { return By.xpath("//div[@id='"+id+"']//select[contains(@aria-label,'"+Path+"')]");}
}
