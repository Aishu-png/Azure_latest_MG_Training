package testData;
import java.util.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;
import utilPack.BasePge;

public class CashCollectionRepaymentLimitValidation_TestData
{
	//TC170
	public static String TC170_CustomerPhoneNo = "9633479003";
	public static String TC170_Purity="22k";
	public static String TC170_Pieces="1";
	public static String TC170_GrossWeight="6";
	public static String TC170_OGTax = "0.00";
	public static String TC170_PaymentDetails           = "RTGS/NEFT";

	//171
	public static String TC171_GrossPieces      = "1";
	public static String TC171_GrossWeight      = "2";
	public static int TC171_SaleAmount          = 200000;
	public static String TC171_TransferType     = "Inter";
	public static String TC171_FromCounterUncut = "Uncut";
	public static String TC171_MetalTypeUncut   = "Gold";
	public static int TC171_SkuCount            = 1;
	public static String TC171_TransationType   = "Sales";
	public static String TC171_CashAmount       = "199999";

	//TC321
	public static String TC321_AdvanceAmount   	 	= "1000";
	public static int    TC321_ProductCount 		= 1;
	public static String TC321_TransferType 		= "Inter";
	public static String TC321_FromCounter 			= "Gold";
	public static String TC321_MetalType 		 	= "Gold";
	public static String TC321_DepositAdjustment    = "Deposit Adjustment";
	public static String TC321_DepositAdjustmentQty = "1";
	public static String TC321_TransactionType 		= "Sales";
	public static double TC321_CashMaxAmount 		= 199999.00;
	public static String TC321_PaymentMethod    	= "Cash";	
	public static int    TC321_SkuGoldCount 	    = 2;
	public static String TC321_TransationType 	    = "Sales";


	//TC323
	public static double TC323_RequiredSettlement = 11.0;
	public static double TC323_BenefitPercent = 20;
	public static String TC323_SchemeCode = "GB";
	public static int TC323_AccountCount = 10;
	public static String TC323_Matured = "Yes";
	public static String TC323_Adjusted = "No";
	public static String TC323_TransferType = "Inter";
	public static String TC323_FromCounterDiamond = "Diamond";
	public static String TC323_MetalTypeGold = "Gold";
	public static int TC323_SaleProductCount = 2;
	public static String TC323_TransactionTypeSales = "Sales";
	public static String TC323_GrossPieces = "1";
	public static String TC323_GrossWeight = "2";
	public static String TC323_SchemeName = "GPP Golden Bloom";
	public static String TC323_Weight = "1";
	public static String TC323_BonusName = "GPP Bonus";
	public static double TC323_CurrentGoldPurity = 22;
	public static double TC323_CashMaxAmount = 199999.00;
	public static String TC323_CashPaymentMethod    = "Cash";
	public static String TC323_Scheme = "GS::Golden Bliss Wedding Advance Plan";
	public static String TC323_NewSchemeCode = "GS";
	public static String TC323_InstallmentAmount = "1000";
	//public static String TC323_InstallmentAmount = "199999";
	public static String TC323_DefaultInstallmentAmount = "1";
	public static String TC323_SalesPerson =  "12807: Ajith R";
	public static String TC323_IdProofType = "Aadhar";
	public static String TC323_IdProofNo = "111122223333";
	public static String TC323_BankProof = "Passbook";
	public static String TC323_DepositType = "GPP";
	public static String TC323_PhoneNo = "7012034013";
	public static String TC323_CardPaymentMethod = "HDFC";

	//TC324
	public static String TC324_Pieces = "1";
	public static String TC324_Deposit = "Order";
	public static String TC324_GrossWeight = "2";
	public static double TC324_DepositPercent = 0.05;
	public static String TC324_DepositType = "Order Advance";

	//TC322
	public static String TC322_AdvanceAmount        = "1000";
	public static int TC322_ProductCount            = 1;
	public static String TC322_TransferType         = "Inter";
	public static String TC322_FromCounter          = "Gold";
	public static String TC322_MetalType            = "Gold";
	public static String TC322_DepositAdjustment    = "Deposit Adjustment";
	public static String TC322_DepositAdjustmentQty = "1";
	public static String TC322_TransactionType      = "Sales";
	public static double TC322_CashMaxAmount        = 199999.00;
	public static String TC322_PaymentMethod        = "Cash";	
	public static int    TC322_SkuGoldCount 	    = 2;
	public static String TC322_TransationType 	    = "Sales";

}
