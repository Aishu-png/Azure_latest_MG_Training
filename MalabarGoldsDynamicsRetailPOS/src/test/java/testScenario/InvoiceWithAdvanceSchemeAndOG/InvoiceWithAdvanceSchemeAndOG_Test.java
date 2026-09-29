package testScenario.InvoiceWithAdvanceSchemeAndOG;

import org.testng.annotations.Test;
import testPage.InvoiceWithAdvanceSchemeAndOG;
import utilPack.BaseTest;

public class InvoiceWithAdvanceSchemeAndOG_Test extends BaseTest {

    InvoiceWithAdvanceSchemeAndOG invoiceWithAdvanceSchemeAndOG;

    @Test
    public void TC290_InvoiceWithAdvanceSchemeAndOGTest() throws Exception {
        invoiceWithAdvanceSchemeAndOG = new InvoiceWithAdvanceSchemeAndOG(getDriver());
        invoiceWithAdvanceSchemeAndOG.TC290_InvoiceWithAdvanceSchemeAndOG();
    }
    
    @Test
    public void TC289_InvoiceWithAdvanceSchemeAndOGTest() throws Exception {
        invoiceWithAdvanceSchemeAndOG = new InvoiceWithAdvanceSchemeAndOG(getDriver());
        invoiceWithAdvanceSchemeAndOG.TC289_InvoiceWithAdvanceSchemeAndOG();
    }
}

