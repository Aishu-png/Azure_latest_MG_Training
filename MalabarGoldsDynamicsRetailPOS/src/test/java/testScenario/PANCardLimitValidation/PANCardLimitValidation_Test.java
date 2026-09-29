package testScenario.PANCardLimitValidation;

import org.testng.annotations.Test;

import testPage.PANCardLimitValidation;
import utilPack.BaseTest;

public class PANCardLimitValidation_Test extends BaseTest {

	PANCardLimitValidation PanCardLimitValidation;
	
	@Test()
	public void TC167_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC167_PANCardLimitValidation();
	}
	@Test()
	public void TC166_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC166_PANCardLimitValidation();
	}
	@Test()
	public void TC163_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC163_PANCardLimitValidation();
	}
	
	@Test()
	public void TC165_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC165_PANCardLimitValidation();
	}
	@Test()
	public void TC169_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC169_PANCardLimitValidation();
	}
	@Test()
	public void TC168_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC168_PANCardLimitValidation();
	}
	
	@Test()
	public void TC164_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC164_PANCardLimitValidation();
	}
	
	@Test()
	public void TC320_PANCardLimitValidationTest() throws Exception 
	{	
		PanCardLimitValidation = new PANCardLimitValidation(getDriver());
		PanCardLimitValidation.TC320_PANCardLimitValidation();
	}
}
