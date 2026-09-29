package testScenario.RtgsPaymentToCustomer;

import org.testng.annotations.Test;

import testPage.RtgsPaymentToCustomer;
import utilPack.BaseTest;

public class RtgsPaymentToCustomer_Test extends BaseTest  {
	
	RtgsPaymentToCustomer rtgsPaymentToCustomer;
	@Test
	public void TC172_RtgsPaymentToCustomerTest() throws Exception {	
	    rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
	    rtgsPaymentToCustomer.TC172_RtgsPaymentToCustomer();
	}

	@Test
	public void TC178_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC178_RtgsPaymentToCustomer();
	}
	
	@Test
	public void TC174_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC174_RtgsPaymentToCustomer();
	}
	
	@Test
	public void TC173_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC173_RtgsPaymentToCustomer();
	}

	@Test
	public void TC175_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC175_RtgsPaymentToCustomer();
	}
	@Test
	public void TC177_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC177_RtgsPaymentToCustomer();
	}
	
	@Test
	public void TC176_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC176_RtgsPaymentToCustomer();
	}
	
	@Test
	public void TC179_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC179_RtgsPaymentToCustomer();
	}
	
	@Test
	public void TC180_RtgsPaymentToCustomerTest() throws Exception {	
		rtgsPaymentToCustomer = new RtgsPaymentToCustomer(getDriver());
		rtgsPaymentToCustomer.TC180_RtgsPaymentToCustomer();
	}

}
