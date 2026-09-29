package testScenario.InvoiceAdvanceWithPaymentMethodRtgsReceived;
import org.testng.annotations.Test;
import testPage.InvoiceAdvanceWithPaymentMethodRtgsReceived;
import utilPack.BaseTest;
public class InvoiceAdvanceWithPaymentMethodRtgsReceived_Test extends BaseTest {
	
	InvoiceAdvanceWithPaymentMethodRtgsReceived invoiceAdvanceWithPaymentMethodRtgsReceived;
	@Test
	public void TC253_InvoiceAdvanceWithPaymentMethodRtgsReceivedTest() throws Exception {	
		invoiceAdvanceWithPaymentMethodRtgsReceived = new InvoiceAdvanceWithPaymentMethodRtgsReceived(getDriver());
		invoiceAdvanceWithPaymentMethodRtgsReceived.TC253_InvoiceAdvanceWithPaymentMethodRtgsReceived();
	}
	@Test
	public void TC249_InvoiceAdvanceWithPaymentMethodRtgsReceivedTest() throws Exception {	
		invoiceAdvanceWithPaymentMethodRtgsReceived = new InvoiceAdvanceWithPaymentMethodRtgsReceived(getDriver());
		invoiceAdvanceWithPaymentMethodRtgsReceived.TC249_InvoiceAdvanceWithPaymentMethodRtgsReceived();
	}
	
	@Test
	public void TC251_InvoiceAdvanceWithPaymentMethodRtgsReceivedTest() throws Exception {	
		invoiceAdvanceWithPaymentMethodRtgsReceived = new InvoiceAdvanceWithPaymentMethodRtgsReceived(getDriver());
		invoiceAdvanceWithPaymentMethodRtgsReceived.TC251_InvoiceAdvanceWithPaymentMethodRtgsReceived();
	}
	
	@Test
	public void TC250_InvoiceAdvanceWithPaymentMethodRtgsReceivedTest() throws Exception {	
		invoiceAdvanceWithPaymentMethodRtgsReceived = new InvoiceAdvanceWithPaymentMethodRtgsReceived(getDriver());
		invoiceAdvanceWithPaymentMethodRtgsReceived.TC250_InvoiceAdvanceWithPaymentMethodRtgsReceived();
	}
	
	
	@Test
	public void TC252_InvoiceAdvanceWithPaymentMethodRtgsReceivedTest() throws Exception {	
		invoiceAdvanceWithPaymentMethodRtgsReceived = new InvoiceAdvanceWithPaymentMethodRtgsReceived(getDriver());
		invoiceAdvanceWithPaymentMethodRtgsReceived.TC252_InvoiceAdvanceWithPaymentMethodRtgsReceived();
	}

	
}