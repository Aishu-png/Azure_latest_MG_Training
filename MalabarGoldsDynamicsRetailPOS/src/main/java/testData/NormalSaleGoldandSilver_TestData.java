

package testData;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalSaleGoldandSilver_TestData {

	//TC01
	public static String TC01_CustomerNo 	       = "7907501134";
	public static int    TC01_SkuGoldCount 	       = 2;
	public static String TC01_TransferType 	       = "Inter";
	public static String TC01_FromCounter 	       = "Gold";
	public static String TC01_MetalType 	   	   = "Gold";
	public static String TC01_ApprovalCode		   = "12345";
	public static String TC01_ApprovalCodeAgain    = "12345";
	public static String TC01_TransationType 	   = "Sales";
	public static List<String> TC01_ReturnProducts = Arrays.asList("Gold","Gold");

	//TC02
	public static String TC02_CustomerID        = "8943319676";
	public static int TC02_SKUCount             = 2;
	public static String TC02_TranferType       = "Inter";
	public static String TC02_FromCounterGold   = "Gold";
	public static String TC02_MetalTypeGold     = "Gold";
	public static String TC02_FromCounterSilver = "Silver";
	public static String TC02_MetalTypeSilver   = "Silver";
	public static List<String> TC02_ReturnProducts      = Arrays.asList("Gold","Gold","Silver","Silver");
	public static List<String> TC02_ReturnProductsSilver = Arrays.asList("Silver","Silver");

	//TC03
		public static String TC03_CustomerID          ="8137821298";	
		public static int SkuCountToAdd               = 2;
		public static String TransferType             ="Inter";
		public static String FromCounter1             ="Gold";
		public static String MetalType1               ="Gold";	
		public static String FromCounter2             ="Silver";
		public static String MetalType2               ="Silver";
		public static String Product                  ="Article";
		public static String ToCounter1               ="Gold";
		public static String ToCounter2               ="Silver";
		public static String TC03_PaymentMethod       = "HDFC (Credit Card)";
		public static String TC03_ApprCode            = "12345";
		public static String TC03_CardNo              = "12345";	
		public static List<String> TC03_ReturnProducts= Arrays.asList("Gold","Gold","Silver","Silver");
	
	//TC04
	public static String TC04_CustomerNo = "8943319676";
	public static String TC04_TransferType = "Inter";
	public static String TC04_FromCounterForGold = "Gold";
	public static String TC04_MetalTypeForGold = "Gold";
	public static String TC04_RequiredItemForGold = "Gold";
	public static String TC04_ProductForGoldPcs = "Finger Ring";
	public static int TC04_RequiredNoGold = 2;
	public static String TC04_TransactionType = "Sales";
	public static String TC04_PaymentMethod = "HDFC (Credit Card)";
	public static String TC04_ApprCode = "12345";
	public static String TC04_CardNo = "12345";
	public static List<String> ReturnProducts=Arrays.asList("Gold","Gold");

	//TC06_NormalSaleGoldandSilver
	public static String TC06_CustId = "9633479003";
	public static List<String> TC06_ReturnProducts=Arrays.asList("Silver","Silver");
	public static int TC06_SkuCount=1;
	public static String TC06_TransferType="Inter";
	public static String TC06_ToCounter = "Silver";
	public static String TC06_MetalType = "Silver";
	public static String TC06_Product="Article";
	public static String TC06_TransactionType = "Sales";
	public static String TC06_ApprCode = "12345";
	public static String TC06_CardNo = "12345";

	//TC05
	public static String TC05_CustomerID =  "7907501134";
	public static String TC05_TransferType = "Inter";
	public static String TC05_FromCounter = "Silver";
	public static String TC05_MetalType = "Silver";
	public static String TC05_ApprCode = "12345";
	public static String TC05_CardNo = "12345";
	public static String TC05_TransactionType = "Sales";
	public static List<String> TC05_ReturnProducts=Arrays.asList("Silver","Silver");
}


