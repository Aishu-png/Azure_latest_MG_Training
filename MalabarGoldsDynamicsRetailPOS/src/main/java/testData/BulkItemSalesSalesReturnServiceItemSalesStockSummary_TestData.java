package testData;

import java.util.*;

import utilPack.BasePge;
public class BulkItemSalesSalesReturnServiceItemSalesStockSummary_TestData {

	//TC149
	public static String TC149_CustomerNo = "8086143322";
	public static String TC149_Item = "EPCHARGES";
	public static String TC149_Pieces = "1";
	public static String TC149_Rate = "200.00";
	public static String TC149_Discount = "0.00";
	public static String TC149_TransactionType = "Sales";

	//TC150
	public static String TC150_CustomerNo              = "8137821298";
	public static String TC150_Item                    = "EPCHARGES";
	public static String TC150_Pieces                  = "1";
	public static String TC150_Rate                    = "200.00";
	public static String TC150_Discount                = "0.00";
	public static String TC150_TransactionType         = "Sales";
	public static List<String> TC150_SkuList           =Arrays.asList("1","Gold","Gold");
	public static List<String> ReturnProducts          =Arrays.asList("Gold");
	public static String TC150_TransactionSalesReturn  = "Sales Return";
	public static String TC150_TransactionSales        = "Sales";
	public static String TC150_PaymentMode             = "Convert to Advance";			
	public static String TC150_Diamond                 = "0";	

	//TC153
	public static String TC153_CustomerNo   = "7012330026";
	public static int    TC153_SkuGoldCount = 1;
	public static String TC153_TransferType = "Inter";
	public static String TC153_FromCounter 	= "Gold";
	public static String TC153_MetalType 	= "Gold";
	public static String TC153_GiftCoin 	= "CGC_";
	public static String TC153_TransactionTypeSales="Sales";
	public static String TC153_CGCItemName = "Complement Gold Coin";
	public static String TC153_CGCQty = "-1";
	public static String TC153_TotalQtyPcs = "1";
	public static int    TC153_SaleAmount1   = 50000;
	public static int    TC153_SaleAmount2   = 100000;



	//TC152
	public static List<String> TC152_ReturnProductsHK          =Arrays.asList("Hook");
	public static String TC152_Grosswgt = "0.01";
	public static String TC152_CustomerNo   = "8089172463";
	


	//TC154
	public static String TC154_CustomerNo   = "7558852527";
	public static String TC154_CGC 	= "CGC_";

	//TC147
	public static String TC147_CustomerNo   = "9747504401";
	public static int    TC147_SkuGoldCount = 1;
	public static String TC147_TransferType = "Inter";
	public static String TC147_FromCounter 	= "Gold";
	public static String TC147_MetalType 	= "Gold";
	public static String TC147_HKGrosswgt = "0.1";
	public static String TC147_TransactionType = "Sales";
	public static String TC147_Discount = "0.00";

	//TC151
	public static String TC151_CustomerNo   = "8129144884";
	public static String TC151_Item = "EPCHARGES";
	public static String TC151_Pieces = "1";
	public static String TC151_Rate = "2000.00";
	public static String TC151_Item1 = "Back chain";
	public static String TC151_GrossWeight = "1";
	public static String TC151_Purity = "22k";
	public static String TC151_TransferType = "Inter";
	public static String TC151_FromCounterGold = "Gold";
	public static String TC151_MetalTypeGold = "Gold";
	public static int TC151_SaleProductCount = 1;
	public static double TC151RequiredWeight = 12;
	public static String TC151_RequiredItem1 = "Ear Punch Charges" ;
	public static String TC151_RequiredItem2 = "BC";
	public static String TC151_TransactionType = "Sales";
	public static String TC151_Piecespdf = "2";
	public static String TC151_Discount = "0.00";

	//TC145
	public static List<String> TC145_ReturnProductsHK = Arrays.asList("Hook","Back Chain");
	public static String TC145_Grosswgt               = "0.1";
	public static String TC145_CustomerNo             = "8137821298";
	public static String TC145_BC                     = "BC";
	public static String TC145_Discount               = "0.00";
	public static String TC145_TransactionType        = "Sales";

	//TC146
	public static String TC146_CustomerNo             = "8943319676";
	public static String TC146_DepositType            ="Normal Advance";
	public static String TC146_PaymentMode            = "Convert to Advance";
	
	//TC148
	public static String TC148_CustomerNo             = "8943319676";
	public static String TC148_BC                     = "BC";
	public static String TC148_Discount               = "0.00";
	public static int    TC148_SkuGoldCount           = 1;
	public static String TC148_TransferType           = "Inter";
	public static String TC148_FromCounter 	          = "Gold";
	public static String TC148_MetalType 	          = "Gold";
}
