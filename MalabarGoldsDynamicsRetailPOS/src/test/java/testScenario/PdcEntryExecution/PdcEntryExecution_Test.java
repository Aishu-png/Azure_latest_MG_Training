package testScenario.PdcEntryExecution;

import org.testng.annotations.Test;

import testPage.PdcEntryExecution;
import utilPack.BaseTest;

public class PdcEntryExecution_Test extends BaseTest {
	
    PdcEntryExecution pdcEntryExecution;
    
    @Test
    public void TC244_PdcEntryExecutionTest() throws Exception {
        pdcEntryExecution = new PdcEntryExecution(getDriver());
        pdcEntryExecution.TC244_PdcEntryExecution();
    }
    
    @Test
    public void TC247_PdcEntryExecutionTest() throws Exception {
        pdcEntryExecution = new PdcEntryExecution(getDriver());
        pdcEntryExecution.TC247_PdcEntryExecution();
    }
    
    @Test
    public void TC242_PdcEntryExecutionTest() throws Exception {
        pdcEntryExecution = new PdcEntryExecution(getDriver());
        pdcEntryExecution.TC242_PdcEntryExecution();
    }
    
    @Test
    public void TC245_PdcEntryExecutionTest() throws Exception {
        pdcEntryExecution = new PdcEntryExecution(getDriver());
        pdcEntryExecution.TC245_PdcEntryExecution();
    }
    @Test
    public void TC243_PdcEntryExecutionTest() throws Exception {
        pdcEntryExecution = new PdcEntryExecution(getDriver());
        pdcEntryExecution.TC243_PdcEntryExecution();
    }
    @Test
    public void TC248_PdcEntryExecutionTest() throws Exception {
    	pdcEntryExecution = new PdcEntryExecution(getDriver());
    	pdcEntryExecution.TC248_PdcEntryExecution();
    }
    @Test
    public void TC241_PdcEntryExecutionTest() throws Exception {
    	pdcEntryExecution = new PdcEntryExecution(getDriver());
    	pdcEntryExecution.TC241_PdcEntryExecution();
    }
    @Test
    public void TC246_PdcEntryExecutionTest() throws Exception {
    	pdcEntryExecution = new PdcEntryExecution(getDriver());
    	pdcEntryExecution.TC246_PdcEntryExecution();
    }

}