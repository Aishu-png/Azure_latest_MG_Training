package testData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utilPack.BasePge;
public class OldItemStockOutAndShipment_TestData 
{
	//TC238
	public static String TC238_MetalType  ="Gold";
	public static String TC238_ItemType   ="OG";
	public static String TC238_CardCode   = "24354";
	public static String TC238_ApprCode   ="90876";
	public static String TC238_CustomerId ="9633479003";

	//TC236
	public static String TC236_CustomerID  = "7012330026";
	public static String TC236_MetalType  = "Gold";
	public static String TC236_ItemType  = "OD";

	//235
	public static String TC235_MetalType  ="Gold";
	public static String TC235_ItemType   ="OG";
	public static String TC235_CardCode   = "24354";
	public static String TC235_ApprCode   ="90876";
	public static String TC235_CustomerId ="8129144884";

	//TC239
	public static String TC239_CustomerID        = "7561066863";
	public static String TC239_GoldMetalType     = "Gold";
	public static String TC239_PlatinumMetalType = "Platinum";
	public static String TC239_OGItemType 		 = "OG";
	public static String TC239_ODItemType 		 = "OD";
	public static String TC239_XRFPurity1 		 = "0.6";
	public static String TC239_XRFPurity2 		 = "0.7";
	public static String TC239_XRFPurity3 		 = "0.8";

	//TC237
	public static String TC237_CustomerID  = "8086143322";
	public static List<String> TC237_PurchaseProducts= new ArrayList<>(Arrays.asList("OG","OD","OT","OU"));
	public static List<String> TC237_PurchaseOldProducts= new ArrayList<>(Arrays.asList("Old Gold Own","Old Diamond Own","Old Platinum Own","Old Uncut Own"));
	public static List<String> TC237_ProductsPurity= new ArrayList<>(Arrays.asList("22k","22k","950","22k"));
	public static List<String> TC237_StockOutProducts= new ArrayList<>(Arrays.asList("OG","OD","OD"));
	public static List<String> TC237_MetalTypes= new ArrayList<>(Arrays.asList("Gold","Gold","Platinum"));

	//TC240
	public static String TC240_CustomerID  = "7012330026";
	public static String TC240_MetalType  = "Gold";
	public static String TC240_ItemType  = "OG";


}
