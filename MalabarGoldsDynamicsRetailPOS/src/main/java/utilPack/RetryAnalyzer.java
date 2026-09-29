package utilPack;
 
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
public class RetryAnalyzer implements IRetryAnalyzer {
 
    private int retryCount = 0;
    private static final int maxRetryCount = 3;
 
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("ðŸ”„â€�â€ž RETRYING TEST: " + result.getName() + " | Attempt " + retryCount);
            return true; 
        }
        System.out.println("â�Œ TEST FAILED AFTER " + maxRetryCount + " ATTEMPTS: " + result.getName());
        return false;
    }
}