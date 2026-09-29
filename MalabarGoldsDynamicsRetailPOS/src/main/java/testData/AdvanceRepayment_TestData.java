package testData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AdvanceRepayment_TestData {

	//TC182
	public static String TC182_CustomerNo     = "7558852527";
	public static String TC182_AdvanceAmount  = "500000";
	public static String TC182_PaymentMode    = "RTGS/NEFT";
	public static String TC182_ApprCode       = "11111";
	public static String TC182_Cardnumber     = "12345";
	//TC183
	public static String TC183_CustomerNo     = "7907501134";
	public static String TC183_AdvanceAmount  = "500000";
	public static String TC183_PaymentMode    = "RTGS/NEFT";

	//TC184
	public static String TC184_CustomerID     = "8943319676";
	public static String TC184_Product        = "CST";
	public static String TC184_Pieces         = "1";
	public static String TC184_GrossWeight    = "10";
	public static String TC184_Remarks        = "Remarks";
	public static double TC184_DepositPercent = 0.05;
	public static String TC184_AdjAmnt        = "1000";
	public static int TC184_ProductCount      = 1;
	public static String TC184_WarningMsg     ="Partial adjustment can not be refund !";

	//TC185
	public static String TC185_CustomerID               = "8137821298";
	public static String TC185_AdvanceAmount            = "1000000";
	public static int TC185_SkuCount                    = 1;
	public static String TC185_TransferType             = "Inter";
	public static String TC185_FromCounter              = "Gold";
	public static String TC185_MetalType                = "Gold";
	public static String TC185_DepositAdjustment        = "Deposit Adjustment";
	public static String TC185_DepositAdjustmentQty     = "1";	
	public static String TC185_TransactionypeAdvance    = "Advance";	
	public static String TC185_TransactionypeSales      = "Sales";	
}