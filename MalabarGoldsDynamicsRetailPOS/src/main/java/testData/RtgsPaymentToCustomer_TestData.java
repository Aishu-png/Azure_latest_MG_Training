package testData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RtgsPaymentToCustomer_TestData {

	//TC172
	public static String TC172_CustomerID = "8848876398";
	public static String TC172_TransferType= "Inter";
	public static String TC172_FromCounter = "Gold";
	public static String TC172_MetalType= "Gold";
	public static int TC172_SkuCount= 1;
	public static double TC172_SaleQty= 9.00;
	public static String TC172_Configuration= "22k";
	public static String TC172_PurchaseOrExchange= "NExchange";
	//	public static String TC172_QCPerson="Santhosh M";
	//	public static String TC172_SmithPerson = "Santhosh M";
	public static String TC172_GrossPieces= "1";
	public static String TC172_GrossWeight= "1";
	public static String TC172_TransactionType1 = "Exchange";
	public static String TC172_TransactionTypeSales="Sales";
	public static String TC172_ApprCode = "12345";
	public static String TC172_CardNo = "12345";

	//TC178
	public static String TC178_CustomerID="8137821298";
	public static List<String> CounterTransfer= Arrays.asList("1","Gold","Gold");
	public static List<String> TC178_ReturnProducts= Arrays.asList("Gold");
	public static int TC178_SkuCount=1;
	public static String TC178_ToCounter="Gold";
	public static String TC178_TransferType="Inter";
	public static String TC178_MetalType="Gold";
	public static String TC178_TransationType = "Sales Return";

	//TC174
	public static String TC174_CustomerID = "7012330026";
	public static String TC174_TransferType= "Inter";
	public static String TC174_FromCounter = "Gold";
	public static String TC174_MetalType= "Gold";
	public static int TC174_SkuCount= 1;
	public static double TC174_SaleQty= 1.00;
	public static String TC174_Configuration= "22k";
	public static String TC174_PurchaseOrExchange= "NExchange";
	public static String TC174_GrossPieces= "1";
	public static String TC174_GrossWeight= "50";
	public static String TC174_TransactionType = "Exchange";


	//TC173
	public static String TC173_CustomerID = "8089172463";
	public static String TC173_SalesPerson = "AKASH T V";
	public static String TC173_GrossWeight = "10";
	public static String TC173_PiecesByPurity = "1";

	public static String TC173_OGTax = "0.00";
	public static String TC173_Purity = "22k";
	//for kalpetta
	//			public static String TC173_QCPersonK = "Santhosh M";
	//			public static String TC173_SmithPersonk = "Santhosh M";
	//for Vijayawada
	//			public static String TC173_QCPersonV = "Pattem";
	//			public static String TC173_SmithPersonV = "Pattem";

	public static String TC173_GrossWeightByPurity = String.format("%.2f",1.5 + new Random().nextDouble() * ((Double.parseDouble(TC173_GrossWeight) - 5) + 0.001));

	public static String TC173_PaymentDetails = "RTGS/NEFT";

	//TC175
		public static String TC175_Purity = "24k";
		public static String TC175_GrossPieces = "1";
		public static String TC175_GrossWeight = "1";
		public static String TC175_SchemeName = "Deposit Adjustment" ;
		public static String TC175_SchemeWeight = "1";
		public static int TC175_SkuGoldCount= 1;	
		public static String TC175_TransferType="Inter";
		public static String TC175_GoldFromCounter="Gold";
		public static String TC175_MetalType="Gold";
		public static String TC175_TransactionTypeExchnge="Advance";	
		public static String TC175_PaymentMode = "RTGS/NEFT";
		public static String TC175_TransactionTypeSales="Sales";	
		public static String TC175_CustomerID = "7012330026";
		public static String TC175_Pieces = "1";
		public static String TC175_OfferGrossWeight2 = "3";
		public static String TC175_Company = "RJAL";
		public static String TC175_Location = "MGD-Kalpetta";
		public static double TC175_DepositPercent = 0.05;

	
	//TC77
	public static String TC177_Purity               = "24k";
	public static double TC177_RequiredSettlement   = 5;
	public static String TC177_SchemeCode           = "GG";
	public static int TC177_AccountCount            = 25;
	public static String TC177_Matured              = "Yes";
	public static String TC177_Adjusted             = "No";
	public static String TC177_TransferType         = "Inter";
	public static String TC177_FromCounterGold      = "Gold";
	public static String TC177_MetalTypeGold        = "Gold";
	public static int TC177_SaleProductCount        = 1;
	public static String TC177_TransactionTypeSales = "Sales";
	public static String TC177_TransactionTypeAdvance = "Advance";
	public static String TC177_GrossPieces          = "1";
	public static String TC177_GrossWeight          = "1";
	public static String TC177_SchemeName           = "GPP Golden Glow";
	public static String TC177_SchemeQty            = "1";
	public static double TC177_Weight               = 2;
	public static String TC177_BonusName            = "GPP Bonus";
	public static double TC177_CurrentGoldPurity    = 22;
	public static double TC177_Installment          = 1.0;
	public static int TC177_MaxxGppAccounts         = 25;
	public static String TC177_MaturedScheme        = "Yes";
	public static String TC177_AdjustedScheme       = "No";
	public static String TC177_SchemeWeight 	    = "1";
	
	//TC176
	public static String TC176_CustomerID = "7012330026";
	public static String TC176_TransferType= "Inter";
	public static String TC176_FromCounter = "Gold";
	public static String TC176_MetalType= "Gold";
	public static int TC176_SkuCount= 1;
	public static String TC176_AdvanceAmount= "10000000";
	public static String TC176_AdvanceName= "Deposit Adjustment";
	public static String TC176_AdvanceQty= "-1";
	public static String TC176_AdvanceTotal= "-10000000";
	public static String TC176_TransactionTypeAdvance="Advance";	
	public static String TC176_TransactionTypeSales="Sales";
	public static String TC176_Purity= "22k";
	
	
	
	//TC179
	public static String TC179_CustomerID = "8129144884";
	public static String TC179_Purity= "22k";
	public static String TC179_GrossPieces  = "1";
	public static String TC179_GrossWeight = "10";


	//TC180
    public static String TC180_CustomerId= "33CA00270408";  //For Perf
    //public static String TC180_CustomerId= "33CA00282731";  //For OmniPerf
	public static int    TC180_SkuGoldCount = 2;
	public static String TC180_TransferType = "Inter";
	public static String TC180_FromCounter = "Gold";
	public static String TC180_MetalType = "Gold";
	public static String TC180_TransactionType = "Sales";
	public static String TC180_PaymentMethod = "RTGS/Received";
	public static String TC180_StringAmountToDeposit="1000000";


		
		
		
		
}
