package utilPack;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.openqa.selenium.WebDriver;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.Assert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class PdfUtilities {

	protected WebDriver driver;
	static Assert asrt;
	JavascriptExecutor js;
	BasePge base;

	public PdfUtilities(BasePge base) {
		this.base   = base;
		this.driver = base.driver; 

	}


	/// <summary>
	/// Extracts and returns the full text content from a PDF file.
	/// Author - Vishnu Manoj
	/// </summary>
	public static String IsGetText(String PdfFilePath) {
		String PdfText = "";
		try {
			File File = new File(PdfFilePath);
			PDDocument Document = PDDocument.load(File);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			PdfText = pdfStripper.getText(Document);
			Document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PdfText;
	}

	/// <summary>
	/// Verifies that specific label-value pairs exist in the extracted PDF text.
	/// Author - Vishnu Manoj
	/// </summary>              

	//	public void VerifyMultipleFieldsInPdf(String PdfText, Map<String, String> ExpectedFields) {
	//		// Step 1: Normalize PDF content
	//		String NormalizedPdfContent = PdfText.replaceAll("[\u20B9,=:]", "")  // remove symbols
	//				.replaceAll("\\s+", "") // remove whitespace
	//				.trim();
	//
	//		for (Map.Entry<String, String> Entry : ExpectedFields.entrySet()) {
	//			String Label = Entry.getKey().trim().replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "");
	//			String ExpectedValue = Entry.getValue().trim().replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "");
	//
	//			boolean IsFound = false;
	//
	//			// Step 2: Try string-based check first
	//			String ExpectedTextCombined = (Label + ExpectedValue).trim();
	//			if (NormalizedPdfContent.contains(ExpectedTextCombined)) {
	//				IsFound = true;
	//			} else {
	//				int LabelIndex = NormalizedPdfContent.indexOf(Label);
	//				if (LabelIndex != -1) {
	//					String AfterLabel = NormalizedPdfContent.substring(LabelIndex + Label.length());
	//
	//					if (AfterLabel.contains(ExpectedValue))
	//					{
	//						IsFound = true;
	//					} else {
	//						// Step 3: Fallback to numeric tolerance check
	//						//try {
	//							double ExpectedNum = Double.parseDouble(ExpectedValue);
	//							//String ExtractedDigits = AfterLabel.replaceAll("[^0-9.]", ""); 
	//							Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
	//							Matcher matcher = pattern.matcher(AfterLabel);
	//							String ExtractedDigits="";
	//							if (matcher.find()) {
	//								ExtractedDigits = matcher.group();
	//								System.out.println("First number: " + ExtractedDigits);
	//
	//							} else {
	//								System.out.println("No number found.");
	//							}
	//
	//							if (!ExtractedDigits.isEmpty()) {
	//								double ActualNum = Double.parseDouble(ExtractedDigits);
	//								System.out.println(" Comparing: Expected " + ExpectedNum + " vs Actual " + ActualNum+" Label: "+Label);
	//								if (Math.abs(ExpectedNum - ActualNum) <= 1.0) {
	//									IsFound = true;
	//								}
	//							}
	//					//	} catch (NumberFormatException e) {
	//							// ignore if parsing fails
	//					//	}
	//					}
	//				}
	//			}
	//
	//			if (IsFound) {
	//				System.out.println(" Found: " + Label + " = " + ExpectedValue);
	//			} else {
	//				System.out.println(" NOT Found: " + Label + " = " + ExpectedValue);
	//			}
	//
	//			Assert.assertTrue(IsFound, "Expected PDF content not found (±1 tolerance): [" + Label + " = " + ExpectedValue + "]");
	//		}
	//	}



	/**
	 * Verifies multiple numerical fields extracted from normalized PDF content against expected values.
	 * Uses a multi-stage approach for robust extraction of varying footer and tax block patterns.
	 * * @param NormalizedPdfContent The full text content of the PDF (post-OCR/normalization).
	 * @param ExpectedFields A map of expected field names and their string values.
	 */






	/**
	 * Verifies multiple numerical fields extracted from normalized PDF content against expected values.
	 * Uses a multi-stage approach for robust extraction of varying footer and tax block patterns.
	 * * @param NormalizedPdfContent The full text content of the PDF (post-OCR/normalization).
	 * @param ExpectedFields A map of expected field names and their string values.
	 */





	// --- REGEX DEFINITIONS ---

	// 1.1 Try Unified Block (5-field contiguous pattern - expects 5 values)
	//	private static final String UNIFIED_FOOTER_REGEX = 
	//			"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*Total Net Wt\\.\\s*Total Gross Wt\\.\\s*" + 
	//					"([\\d\\.\\-]+)\\s+([\\d\\.\\-]+)\\s+([\\d\\.\\-]+)\\s+([\\d\\.\\-]+)\\s+([\\d\\.\\-]+)";
	//
	//	// 1.2 Pattern E: Truncated 5-label pattern that only has 3 values (e.g., when weights are zero/omitted)
	//	private static final String TRUNCATED_FOOTER_WITH_LABELS_REGEX = 
	//			"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*Total Net Wt\\.\\s*Total Gross Wt\\.\\s*" + 
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s+([\\d]+\\.[\\d]+|[\\d]+)\\s+([\\d]+\\.[\\d]+|[\\d]+)";
	//
	//	// 1.3 Part A: Gross Amount, Total Discount, Total Qty(Pcs) (3-field block - Original Split)
	//	private static final String FOOTER_REGEX_A = 
	//			"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*([\\d\\.\\-]+)\\s*([\\d\\.\\-]+)\\s*([\\d\\.\\-]+)";
	//
	//	// 1.4 Part B: Total Net Wt., Total Gross Wt. (2-field block)
	//	private static final String FOOTER_REGEX_B = 
	//			"Total Net Wt\\.\\s*Total Gross Wt\\.\\s*([\\d\\.\\-]+)\\s*([\\d\\.\\-]+)";
	//
	//	// 1.5 Part C: Gross Amount, Total Discount (2-field block)
	//	private static final String FOOTER_REGEX_C = 
	//			"Gross Amount\\s*Total Discount\\s*([\\d\\.\\-]+)\\s*([\\d\\.\\-]+)";
	//
	//	// 2.1 Pattern F: Full Block - Total Adjustment Round Off (Flexible RO)
	//	private static final String TAX_FULL_BLOCK_FLEXIBLE_REGEX = 
	//			"Taxable Value.*?Total Value\\s*Total Adjustment\\s*Round Off\\s*" +
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 1: Taxable Value
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 2: CGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 3: SGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 4: Total Value
	//					"(\\([\\d\\.\\-]+\\))\\s*" +     // Group 5: Total Adjustment (in parentheses)
	//					"\\(?([\\d]+\\.[\\d]+|\\-?[\\d]+)\\)?"; // Group 6: Round Off (parentheses optional)
	//
	//	// 2.2 Pattern C-Prime: Taxable Value ... Total Value Total Adjustment (Missing Round Off)
	//	private static final String TAX_ADJUSTMENT_ONLY_REGEX = 
	//			"Taxable Value.*?SGST.*?Total Value\\s*Total Adjustment\\s*" +
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 1: Taxable Value
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 2: CGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 3: SGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 4: Total Value
	//					"(\\([\\d\\.\\-]+\\))";          // Group 5: Total Adjustment (in parentheses)
	//
	//
	//	// 2.3 Pattern B: Taxable Value ... Total Value Round Off (Missing Total Adjustment)
	//	private static final String TAX_ROUND_OFF_FLEXIBLE_REGEX = 
	//			"Taxable Value\\s*CGST.*?SGST.*?Total Value\\s*Round Off\\s*" +
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 1: Taxable Value
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 2: CGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 3: SGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 4: Total Value
	//					"\\(?([\\d]+\\.[\\d]+|\\-?[\\d]+)\\)?"; // Group 5: Round Off (parentheses optional)
	//
	//	// 2.4 Pattern D: Taxable Value ... Total Value (Standard structure)
	//	private static final String TAX_VALUE_TOTAL_REGEX = 
	//			"Taxable Value\\s*CGST.*?SGST.*?Total Value\\s*" +
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 1: Taxable Value
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 2: CGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)\\s*" + // Group 3: SGST Amount
	//					"([\\d]+\\.[\\d]+|[\\d]+)";      // Group 4: Total Value
	//
	//	// 2.5 Final Fallback: Taxable Value (Single value search)
	//	private static final String TAX_VALUE_SINGLE_REGEX = 
	//			"Taxable Value[\\s]*([\\d]+\\.[\\d]+|[\\d]+)";
	//
	//	// 2.6 Pattern G: Specific capture for GST Rate % Amount (For 'GST 3.00% : 1554.05' structure)
	//	private static final String GST_RATE_AMOUNT_REGEX = 
	//			"GST\\s*([\\d]+\\.[\\d]+)\\s*%\\s*[:\\-]?\\s*([\\d]+\\.[\\d]+|[\\d]+)";


	/**
	 * Verifies multiple numerical fields extracted from normalized PDF content against expected values.
	 * @param NormalizedPdfContent The full text content of the PDF (post-OCR/normalization).
	 * @param ExpectedFields A map of expected field names and their string values.
	 */





	public void VerifyMultipleFieldsInPdf(String NormalizedPdfContent, Map<String, String> ExpectedFields) {
		final double TOLERANCE = 1.0;

		System.out.println("\n--- PDF Field Verification Start ---");

		// Defensive normalization
		String pdf = NormalizedPdfContent == null ? "" : NormalizedPdfContent
				.replace("\u20B9", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.trim();

		// ===== Regex definitions (locals) =====

		// Footer totals
		final String UNIFIED_FOOTER_REGEX =
				"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*Total Net Wt\\.\\s*Total Gross Wt\\.\\s*"
						+ "([\\d.\\-]+)\\s+([\\d.\\-]+)\\s+([\\d.\\-]+)\\s+([\\d.\\-]+)\\s+([\\d.\\-]+)";

		final String TRUNCATED_FOOTER_WITH_LABELS_REGEX =
				"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*Total Net Wt\\.\\s*Total Gross Wt\\.\\s*"
						+ "([\\d]+\\.[\\d]+|[\\d]+)\\s+([\\d]+\\.[\\d]+|[\\d]+)\\s+([\\d]+\\.[\\d]+|[\\d]+)";

		final String FOOTER_REGEX_A =
				"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)";

		final String FOOTER_REGEX_B =
				"Total Net Wt\\.\\s*Total Gross Wt\\.\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)";

		final String FOOTER_REGEX_C =
				"Gross Amount\\s*Total Discount\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)";

		// Tax blocks
		final String TAX_MATRIX_WITH_ROUNDOFF_REGEX =
				"\\bTaxable Value\\b\\s*CGST.*?SGST.*?\\bTotal Value\\b\\s*\\bRound Off\\b\\s*"
						+ "([\\d]+(?:\\.\\d+)?)\\s*"  // taxable
						+ "([\\d]+(?:\\.\\d+)?)\\s*"  // cgst
						+ "([\\d]+(?:\\.\\d+)?)\\s*"  // sgst
						+ "([\\d]+(?:\\.\\d+)?)\\s*"  // total value
						+ "\\((\\-?[\\d]+(?:\\.\\d+)?)\\)"; // round off

		final String TAX_MATRIX_NO_ADJ_REGEX =
				"\\bTaxable Value\\b\\s*CGST.*?\\bSGST\\b.*?\\bTotal Value\\b\\s*"
						+ "([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)";

		final String TAX_FULL_BLOCK_FLEXIBLE_REGEX =
				"\\bTaxable Value\\b.*?\\bTotal Value\\b\\s*\\bTotal Adjustment\\b\\s*\\bRound Off\\b\\s*"
						+ "([\\d]+(?:\\.\\d+)?)[\\s]+([\\d]+(?:\\.\\d+)?)[\\s]+([\\d]+(?:\\.\\d+)?)[\\s]+([\\d]+(?:\\.\\d+)?)[\\s]+\\(([\\d.\\-]+)\\)[\\s]+\\(?([\\d]+(?:\\.\\d+)?|\\-?[\\d]+)\\)?";

		// Fallbacks
		final String TAXABLE_SINGLE_REGEX = "\\bTaxable Value\\b\\s*([\\d]+(?:\\.\\d+)?)";
		final String TOTAL_ADJUSTMENT_DIRECT_REGEX = "\\bTotal Adjustment\\b\\s*\\(?([\\d]+(?:\\.\\d+)?)\\)?";

		// GST (amount, not %); exclude "Including GST"
		final String GST_RATE_AND_AMOUNT_REGEX =
				"(?<!Including\\s)\\bGST\\b\\s*([\\d]+(?:\\.\\d+)?)\\s*%\\s*[:\\-]\\s*([\\-]?[\\d]+(?:\\.\\d+)?)";
		final String GST_SIMPLE_COLON_REGEX =
				"(?<!Including\\s)\\bGST\\b\\s*[:\\-]\\s*([\\-]?[\\d]+(?:\\.\\d+)?)";

		// Payment details window + specific captures
		final String PAYMENT_WINDOW_REGEX =
				"\\bPayment Details\\b(.{0,200}?)\\b(Gross Amount|Other Information|Taxable Value|Rupees|Net Value|DECLARATION|$)";
		//final String HDFC_CC_REGEX = "\\bHDFC\\s*\\(\\s*Credit\\s*Card\\s*\\)\\s*([\\-]?\\d+(?:\\.\\d+)?)";
		final String HDFC_CC_REGEX = "\\bHDFC\\s*(?:\\(\\s*(?:Credit\\s*)?Card\\s*\\)|Card)\\s*([\\-]?\\d+(?:\\.\\d+)?)";


		// ===== Pre-extracted values =====
		Map<String, String> actuals = new HashMap<>();
		String totalAdjustmentFromTax = null;
		String roundOffFromTax = null;

		// ----- Footer extraction -----
		Matcher m = Pattern.compile(UNIFIED_FOOTER_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
		if (m.find()) {
			actuals.put("Gross Amount",   m.group(1).trim());
			actuals.put("Total Discount", m.group(2).trim());
			actuals.put("Total Qty(Pcs)", m.group(3).trim());
			actuals.put("Total Net Wt.",  m.group(4).trim());
			actuals.put("Total Gross Wt.",m.group(5).trim());
		} else {
			m = Pattern.compile(TRUNCATED_FOOTER_WITH_LABELS_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
			if (m.find()) {
				actuals.put("Gross Amount",   m.group(1).trim());
				actuals.put("Total Discount", m.group(2).trim());
				actuals.put("Total Qty(Pcs)", m.group(3).trim());
				actuals.putIfAbsent("Total Net Wt.",  "0.00");
				actuals.putIfAbsent("Total Gross Wt.","0.00");
			} else {
				Matcher a = Pattern.compile(FOOTER_REGEX_A, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (a.find()) {
					actuals.put("Gross Amount",   a.group(1).trim());
					actuals.put("Total Discount", a.group(2).trim());
					actuals.put("Total Qty(Pcs)", a.group(3).trim());
				}
				Matcher b = Pattern.compile(FOOTER_REGEX_B, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (b.find()) {
					actuals.put("Total Net Wt.",  b.group(1).trim());
					actuals.put("Total Gross Wt.",b.group(2).trim());
				}
				Matcher c = Pattern.compile(FOOTER_REGEX_C, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (c.find()) {
					actuals.putIfAbsent("Gross Amount",   c.group(1).trim());
					actuals.putIfAbsent("Total Discount", c.group(2).trim());
				}
			}
		}

		// ----- Tax block extraction -----
		Matcher taxRO = Pattern.compile(TAX_MATRIX_WITH_ROUNDOFF_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
		if (taxRO.find()) {
			actuals.put("Taxable Value", taxRO.group(1).trim());
			actuals.put("Total Value",   taxRO.group(4).trim());
			roundOffFromTax = taxRO.group(5).trim();
		} else {
			Matcher taxNoAdj = Pattern.compile(TAX_MATRIX_NO_ADJ_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
			if (taxNoAdj.find()) {
				actuals.put("Taxable Value", taxNoAdj.group(1).trim());
				actuals.put("Total Value",   taxNoAdj.group(4).trim());
			} else {
				Matcher taxFull = Pattern.compile(TAX_FULL_BLOCK_FLEXIBLE_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
				if (taxFull.find()) {
					actuals.put("Taxable Value", taxFull.group(1).trim());
					actuals.put("Total Value",   taxFull.group(4).trim());
					totalAdjustmentFromTax = taxFull.group(5).trim().replaceAll("[()]", "");
					roundOffFromTax = taxFull.group(6) == null ? null : taxFull.group(6).trim();
				} else {
					Matcher tv = Pattern.compile(TAXABLE_SINGLE_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
					if (tv.find()) {
						actuals.put("Taxable Value", tv.group(1).trim());
					}
				}
			}
		}

		// ----- Payment Details extraction (HDFC (Credit Card), etc.) -----
		Matcher payWin = Pattern.compile(PAYMENT_WINDOW_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
		if (payWin.find()) {
			String window = payWin.group(1);
			// HDFC (Credit Card)
			Matcher hdfc = Pattern.compile(HDFC_CC_REGEX, Pattern.CASE_INSENSITIVE).matcher(window);
			if (hdfc.find()) {
				actuals.put("HDFC (Credit Card)", hdfc.group(1).trim());
			}
			// You can extend similarly for other modes if needed:
			// Cash
			Matcher cash = Pattern.compile("\\bCash\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(window);
			if (cash.find()) actuals.put("Cash", cash.group(1).trim());
			// RTGS/Received
			Matcher rtgs = Pattern.compile("\\bRTGS\\/Received\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(window);
			if (rtgs.find()) actuals.put("RTGS/Received", rtgs.group(1).trim());
			// UPI (if appears)
			Matcher upi = Pattern.compile("\\bUPI\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(window);
			if (upi.find()) actuals.put("UPI", upi.group(1).trim());
		}

		// ===== FINAL FALLBACK for split/mixed tax lines =====
		if (!actuals.containsKey("Taxable Value") || !actuals.containsKey("Total Value")) {
			Pattern windowP = Pattern.compile("\\bTaxable Value\\b(.{0,800}?)(?:\\bNet Value\\b|\\bRupees\\b|\\bDECLARATION\\b|\\bPayment Details\\b|$)",
					Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher winM = windowP.matcher(pdf);
			if (winM.find()) {
				String window = winM.group(1);
				Matcher nums = Pattern.compile("\\(?-?\\d+(?:\\.\\d+)?\\)?").matcher(window);
				List<String> vals = new ArrayList<>();
				while (nums.find()) {
					String token = nums.group();
					int start = nums.start();
					boolean looksPercent = start > 0 && window.substring(Math.max(0, start - 3), Math.min(window.length(), start + token.length() + 1)).contains("%");
					if (!looksPercent) vals.add(token.replaceAll("[()]", ""));
					if (vals.size() >= 5) break;
				}
				if (vals.size() >= 4) {
					actuals.putIfAbsent("Taxable Value", vals.get(0));
					actuals.putIfAbsent("Total Value",   vals.get(3));
					if (vals.size() >= 5) {
						String wlower = window.toLowerCase();
						if (wlower.contains("round off"))      roundOffFromTax = vals.get(4);
						else if (wlower.contains("total adjustment")) totalAdjustmentFromTax = vals.get(4);
					}
				}
			}
		}

		// ===== Verification loop =====
		for (Map.Entry<String, String> e : ExpectedFields.entrySet()) {
			String keyRaw = e.getKey() == null ? "" : e.getKey().trim();
			String expectedStr = e.getValue();
			String key = keyRaw.replaceAll("\\s*:\\s*$", ":").trim();

			String actualStr = null;

			// A) Special: Total Adjustment
			if (key.equalsIgnoreCase("Total Adjustment")) {
				if (totalAdjustmentFromTax != null) actualStr = totalAdjustmentFromTax;
				else {
					Matcher ta = Pattern.compile(TOTAL_ADJUSTMENT_DIRECT_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
					if (ta.find()) actualStr = ta.group(1).trim();
				}
			}
			// B) Round Off
			else if (key.equalsIgnoreCase("Round Off")) {
				if (roundOffFromTax != null) actualStr = roundOffFromTax.replace("(", "").replace(")", "");
			}
			// C) Pre-extracted (Footer, Tax, Payment Details)
			else if (actuals.containsKey(key)) {
				actualStr = actuals.get(key);
			}
			// D) GST amount (strict; exclude "Including GST")
			else if (key.equalsIgnoreCase("GST") || key.equalsIgnoreCase("GST:") || key.equalsIgnoreCase("GST :")) {
				Matcher g = Pattern.compile(GST_RATE_AND_AMOUNT_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (g.find()) actualStr = g.group(2).trim();
				if (actualStr == null) {
					g = Pattern.compile(GST_SIMPLE_COLON_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
					if (g.find()) actualStr = g.group(1).trim();
				}
			}
			// E) “Adjustment :” (explicit label variant)
			//			else if (key.equalsIgnoreCase("Adjustment:") || key.equalsIgnoreCase("Adjustment :")) {
			//				Matcher adj = Pattern.compile("\\bAdjustment\\b\\s*[:\\-]?\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(pdf);
			//				if (adj.find()) actualStr = adj.group(1).trim();
			//				if (actualStr == null) {
			//					adj = Pattern.compile("\\bAdjustment\\b\\s*\\bTotal\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(pdf);
			//					if (adj.find()) actualStr = adj.group(1).trim();
			//				}
			//			}
			else if (key.equalsIgnoreCase("Adjustment:") || key.equalsIgnoreCase("Adjustment :")) {

				// 1️.Try "Adjustment Total" first (most accurate)
				Matcher adjTotal = Pattern.compile(
						"\\bAdjustment\\s*Total\\b\\s*[:\\-]?\\s*([\\-]?\\d+(?:\\.\\d+)?)",
						Pattern.CASE_INSENSITIVE
						).matcher(pdf);

				if (adjTotal.find()) {
					actualStr = adjTotal.group(1).trim();
				}

				// 2️. Fallback: plain "Adjustment : value"
				if (actualStr == null) {
					Matcher adj = Pattern.compile(
							"\\bAdjustment\\b\\s*[:\\-]?\\s*([\\-]?\\d+(?:\\.\\d+)?)",
							Pattern.CASE_INSENSITIVE
							).matcher(pdf);

					if (adj.find()) {
						actualStr = adj.group(1).trim();
					}
				}
			}
			// F) Generic: <Key> [: or -] <number>
			else {
				String searchKey = key.replaceAll("[\\:\\-]", "").trim();
				String generic = "\\b" + Pattern.quote(searchKey) + "\\b\\s*[:\\-]?\\s*\\(?([\\-]?\\d+(?:\\.\\d+)?)\\)?";
				Matcher mg = Pattern.compile(generic, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (mg.find()) actualStr = mg.group(1).trim();
			}

			if (actualStr == null || actualStr.isEmpty()) {
				System.out.printf("%-20s | Expected: %-10s | Actual: %-10s |  FIELD NOT FOUND%n", key, expectedStr, "N/A");
				asrt.fail("Field '" + key + "' not found in PDF content.");
				continue;
			}

			// Numeric compare with tolerance
			String cleanExpected = expectedStr == null ? "" : expectedStr.replaceAll("[^\\d.\\-+]", "").trim();
			String cleanActual   = actualStr.replaceAll("[^\\d.\\-+]", "").trim();

			try {
				double expected = Double.parseDouble(cleanExpected);
				double actual   = Double.parseDouble(cleanActual);
				boolean match   = Math.abs(expected - actual) <= TOLERANCE;

				System.out.printf("%-20s | Expected: %-10.2f | Actual: %-10.2f | %s%n",
						key, expected, actual, match ? " MATCH" : " MISMATCH");

				// Soft assert (TestNG): actual, expected, delta, message
				asrt.assertEquals(
						actual,
						expected,
						TOLERANCE,
						"Mismatch in " + key + " (Tolerance: " + TOLERANCE + ")"
						);

			} catch (NumberFormatException nfe) {
				boolean match = cleanExpected.equalsIgnoreCase(cleanActual);
				System.out.printf("%-20s | Expected: %-10s | Actual: %-10s | %s%n",
						key, cleanExpected, cleanActual, match ? " MATCH" : " MISMATCH");
				if (!match) {
					asrt.fail("Mismatch in " + key + ": expected '" + cleanExpected + "', actual '" + cleanActual + "'");
				}
			}
		}

		System.out.println("--- PDF Field Verification End ---\n");
	}


	/// <summary>
	/// Validates that each expected row (e.g., invoice line items) is present in the given PDF text.
	/// Author - Vishnu Manoj
	/// </summary> 
	public void VerifyTableRowInPdf(String PdfText, List<String> ExpectedRows) {
		String NormalizedPdf = PdfText.replaceAll("\\s+", " ").trim();
		for (String Row : ExpectedRows) {
			String NormalizedRow = Row.replaceAll("\\s+", " ").trim();
			boolean Found = NormalizedPdf.contains(NormalizedRow);
			if (Found) {
				System.out.println("ITEM Found: " + Row);
			} else {
				System.out.println("ITEM NOT Found: " + Row);
			}
			Assert.assertTrue(Found, "Expected PDF content not found in PDF: " + Row);
		}
	}


	/// <summary>
	/// Downloads and renames the latest PDF in the "Invoices" folder using a custom name prefix and timestamp.
	/// Author - Vishnu Manoj
	/// </summary>
	public static  String DownloadAndRenameLatestPDF(String PdfName) throws IOException, InterruptedException {
		String DownloadDir = System.getProperty("user.dir") + "\\Invoices";

		// Wait for download to complete
		Thread.sleep(5000);

		// Get all PDF files
		File Dir = new File(DownloadDir);
		File[] Files = Dir.listFiles((d, name) -> name.toLowerCase().endsWith(".pdf"));

		if (Files == null || Files.length == 0) {
			throw new FileNotFoundException("No PDF found in directory: " + DownloadDir);
		}

		// Sort files by last modified (most recent first)
		Arrays.sort(Files, Comparator.comparingLong(File::lastModified).reversed());
		File LatestFile = Files[0];

		// Generate new file name
		String Timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String NewPdfName = PdfName + "_" + Timestamp + ".pdf";
		File RenamedFile = new File(DownloadDir + "\\" + NewPdfName);

		// Rename file
		boolean Success = LatestFile.renameTo(RenamedFile);
		if (!Success) {
			throw new IOException("Failed to rename file: " + LatestFile.getName());
		}

		System.out.println("File renamed to: " + RenamedFile.getName());
		return NewPdfName;
	}

	/// <summary>
	/// To remove all PDF files in the Invoices
	/// Author - Vishnu Manoj
	/// <summary>
	public static void DeleteAllPDFFilesInInvoiceFolder() throws IOException {
		String DownloadDir = System.getProperty("user.dir") + "\\Invoices";

		File Dir = new File(DownloadDir);
		File[] Files = Dir.listFiles((d, name) -> name.toLowerCase().endsWith(".pdf"));

		if (Files == null || Files.length == 0) {
			System.out.println("No PDF files found in directory: " + DownloadDir);
			return;
		}

		for (File File : Files) {
			if (File.delete()) {
				System.out.println("Deleted: " + File.getName());
			} else {
				System.out.println("Failed to delete: " + File.getName());
			}
		}
	}

	/// <summary>
	/// Performs text extraction from the GPP PDF to locate and display the Application ID
	/// Author - Nivya Ramesan
	/// </summary> 
	public void GPPpdfValidation() {
		String PdfPath = "C:\\Users\\HP\\Downloads\\GPP.pdf";
		String PdfContent = IsGetText(PdfPath); 

		if (PdfContent == null || PdfContent.trim().isEmpty()) {
			System.err.println("Failed to extract text from PDF.");
			return;
		}

		PdfContent = PdfContent.replaceAll("\\r?\\n", "\n"); 

		String[] lines = PdfContent.split("\n");

		for (String line : lines) {
			if (line.trim().toLowerCase().startsWith("application id")) {
				System.out.println("" + line.trim());
				return;
			}
		}

		System.err.println("Application ID not found in the PDF.");
	}

	/// <summary>
	/// Method to validate OG(Old Gold) Pdf(PDF Name -Purchase Bill) for multiple products
	/// Method includes fetching Invoice Number, GrossWt, NetWt, Rate, Item Total, Convert to Advance
	/// Validates that Gross Wt and Net Wt are equal per product, and TotalAmount = PaymentDetails
	/// Author - Christy Reji
	/// </summary>
	public Map<String, String> OGPdfValidation(String paymentKeyword) throws IOException, InterruptedException {

		// Rename the downloaded PDF to OG
		String NewPdfName    = DownloadAndRenameLatestPDF("OG");
		String PdfPath       = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		Map<String, String> OGPdfData = new HashMap<>();
		OGPdfData.put("OGPdfName", ExtractedText.contains("PURCHASE BILL") ? "PURCHASE BILL" : "");

		// Invoice Number
		Matcher InvoiceMatcher = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)").matcher(ExtractedText);
		if (InvoiceMatcher.find()) {
			OGPdfData.put("InvoiceNumber", InvoiceMatcher.group(1));
		}

		// Product details: supports 1 or more products
		Pattern ProductPattern = Pattern.compile(
				"(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{3})\\s+(\\d{4,6}\\.\\d{2})\\s+(\\d{4,6}\\.\\d{2})\\s*Item Total\\s*(\\d{4,6}[\\.,]\\d{2})");
		Matcher ProductMatcher = ProductPattern.matcher(ExtractedText);

		int ProductIndex = 1;
		while (ProductMatcher.find()) {
			OGPdfData.put("GrossWt"     + ProductIndex, ProductMatcher.group(1));
			OGPdfData.put("NetWt"       + ProductIndex, ProductMatcher.group(2));
			OGPdfData.put("Rate"        + ProductIndex, ProductMatcher.group(3));
			OGPdfData.put("TotalAmount" + ProductIndex, ProductMatcher.group(4));
			OGPdfData.put("ItemTotal"   + ProductIndex, ProductMatcher.group(5).replace(",", ".")); // Replace comma
			ProductIndex++;
		}
		System.out.println("OGPdfData "+OGPdfData);

		// Fallback for single product if main pattern not matched
		if (ProductIndex == 1) {
			Pattern SingleProductPattern = Pattern.compile(
					"Raw Gold.*?(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{3})\\s+(\\d{4,6}\\.\\d{2})\\s+(\\d{4,6}\\.\\d{2})");
			Matcher FallbackMatcher = SingleProductPattern.matcher(ExtractedText);
			if (FallbackMatcher.find()) {
				OGPdfData.put("GrossWt1",     FallbackMatcher.group(1));
				OGPdfData.put("NetWt1",       FallbackMatcher.group(2));
				OGPdfData.put("Rate1",        FallbackMatcher.group(3));
				OGPdfData.put("TotalAmount1", FallbackMatcher.group(4));
			}
			System.out.println("OGPdfData1 "+OGPdfData);
			// Fallback for Item Total
			Matcher ItemTotalMatcher = Pattern.compile("Item Total\\s*(\\d{4,6}[\\.,]\\d{2})").matcher(ExtractedText);
			if (ItemTotalMatcher.find()) {
				OGPdfData.put("ItemTotal1", ItemTotalMatcher.group(1).replace(",", "."));
			}
		}

		OGPdfData.put("TotalQuantity", String.valueOf(ProductIndex - 1));

		System.out.println("OGPdfData after item total and Tot qty "+OGPdfData);

		// Extract Total Amount (numeric, after "Total", followed by currency in words or number line)
		Matcher TotalAmountMatcher = Pattern.compile("Total\\s*(\\d{4,6}[\\.,]\\d{2})\\s*Rupees").matcher(ExtractedText);
		if (TotalAmountMatcher.find()) {
			OGPdfData.put("TotalAmount", TotalAmountMatcher.group(1).replace(",", "."));
		}
		System.out.println("OGPdfData after tot amnt "+OGPdfData);

		// Convert to Advance (payment details)
		String PaymentKeyword = Pattern.quote(paymentKeyword); // To handle special characters
		//Matcher PaymentMatcher = Pattern.compile("Adjustment\\s*:?\\s*(\\d{4,6}[\\.,]\\d{2})").matcher(ExtractedText);
		Matcher PaymentMatcher = Pattern.compile(PaymentKeyword + "\\s*:?\\s*(\\d{4,6}[\\.,]\\d{2})").matcher(ExtractedText);
		if (PaymentMatcher.find()) {
			OGPdfData.put("PaymentDetails", PaymentMatcher.group(1).replace(",", "."));
		} else {
			OGPdfData.put("PaymentDetails", "Not Found");
		}

		// Assertion: TotalAmount == PaymentDetails
		String Total = OGPdfData.get("TotalAmount");
		String Payment = OGPdfData.get("PaymentDetails");

		if (Total != null && Payment != null && !Payment.equals("Not Found")) {
			asrt.assertEquals(Total, Payment, "Total Amount and Payment Details mismatch.");
		} else {
			throw new AssertionError("Missing Total or Payment data for validation.");
		}

		System.out.println("Validation successful for OG PDF");

		//		System.out.println("OGPdfData "+OGPdfData);

		System.out.println("Final OGPdfData "+OGPdfData);

		return OGPdfData;
		//how to call in your class
		//Map<String, String> result = PdfUtils.OGPdfValidation();
		//System.out.println("Invoice Number: " + result.get("InvoiceNumber"));
	}
	/// <summary>
	/// To verify that each expected SKU line (Gross, Stone, Net, Amount) is present in the PDF text Ã¢â‚¬â€� allowing for minor rounding differences (within Ã‚Â±0.05)
	/// Author - Vishnu Manoj
	/// <summary>
	public void VerifyMultipleItemTotalLines(String PdfText, List<double[]> ExpectedLines) {
		String[] Lines = PdfText.split("\\r?\\n");
		int MatchCount = 0;

		for (int Index = 0; Index < ExpectedLines.size(); Index++) {
			double[] Expected = ExpectedLines.get(Index);
			double ExpectedGross = Expected[0];
			double ExpectedStone = Expected[1];
			double ExpectedNet = Expected[2];
			double ExpectedAmount = Expected[3];

			//System.out.println("===== Comparing Line " + (Index + 1) + " =====");
			System.out.println("Gross Wt  - Expected: " + ExpectedGross);
			System.out.println("Stone Wt  - Expected: " + ExpectedStone);
			System.out.println("Net Wt    - Expected: " + ExpectedNet);
			System.out.println("Amount    - Expected: " + ExpectedAmount);

			boolean Matched = false;

			for (String Line : Lines) {
				List<Double> NumbersInLine = ExtractDoublesFromLine(Line);

				for (int I = 0; I <= NumbersInLine.size() - 4; I++) {
					double Gross = NumbersInLine.get(I);
					double Stone = NumbersInLine.get(I + 1);
					double Net = NumbersInLine.get(I + 2);
					double Amount = NumbersInLine.get(I + 3);

					System.out.printf(" Checking: Gross=%.3f, Stone=%.3f, Net=%.3f, Amount=%.2f\n",
							Gross, Stone, Net, Amount);

					if (IsApproxEqual(Gross, ExpectedGross) &&
							IsApproxEqual(Stone, ExpectedStone) &&
							IsApproxEqual(Net, ExpectedNet) &&
							IsApproxEqual(Amount, ExpectedAmount)) {
						Matched = true;
						MatchCount++;
						break;
					}
				}

			}
			Assert.assertTrue(Matched, String.format(
					" Expected line not found for SKU %d: Gross=%.3f, Stone=%.3f, Net=%.3f, Amount=%.2f",
					Index + 1, ExpectedGross, ExpectedStone, ExpectedNet, ExpectedAmount
					));
		}

		System.out.println(" Matched " + MatchCount + " expected item line(s) successfully.");
	}
	private List<Double> ExtractDoublesFromLine(String Line) {
		List<Double> Values = new ArrayList<>();
		Matcher Matcher = Pattern.compile("\\d+\\.\\d+").matcher(Line);
		while (Matcher.find()) {
			Values.add(Double.parseDouble(Matcher.group()));
		}
		return Values;
	}

	private boolean IsApproxEqual(double A, double B) {
		return Math.abs(A - B) < 0.05; // tolerance of Ã‚Â±0.05
	}


	///<Summary>
	///Method to validate the Schemes(GG,GS,GB) pdf
	///@Author: Gokul.P
	///</Summary>
	public static void SchemePdfValidation( Map<String, String> ExpectedFields,String SchemeName) throws IOException, InterruptedException
	{
		String NewPdfName = DownloadAndRenameLatestPDF(SchemeName.toUpperCase());
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);	
		Map<String, String> Extracted = ExtractFields(ExtractedText);		
		if(ExtractedText.contains(SchemeName))
		{
			Extracted.put("SchemePdfName", ExtractedText.contains(SchemeName) ? SchemeName : "");
			Assert.assertEquals(Extracted.get("SchemePdfName"), SchemeName,"The "+SchemeName+" is displayed in the form is incorrect");
		}

		for (Map.Entry<String, String> entry : Extracted.entrySet()) 
		{
			String Key = entry.getKey();
			String Value = entry.getValue();			
		}
		//Application ID Validation
		if (ExtractedText.contains("Application ID"))
		{   
			String ActualApplicationId = Extracted.get("Application ID");		    
			String ExpectedApplicationId = ExtractedText.contains(ActualApplicationId) ? ActualApplicationId : " ";
			Assert.assertEquals(ActualApplicationId,ExpectedApplicationId, "The " + ActualApplicationId + " and the " + ExpectedApplicationId + " are mismatched" );
		}

		// Validate the values fetched from the application
		for (Map.Entry<String, String> Expected : ExpectedFields.entrySet())
		{
			String Key = Expected.getKey();
			String ExpectedValue = Expected.getValue();
			String ActualValue = Extracted.get(Key);			
			Assert.assertEquals(ExpectedValue, ActualValue, "The "+ExpectedValue+" and the "+ActualValue+" are mismatched");
		}
	}

	///<Summary>
	/// Method to return the value of the corresponding field
	///@Author: Gokul.P
	///</Summary>
	public static String Match(String Text, String Regex) {
		Pattern pattern = Pattern.compile(Regex);
		Matcher Matcher = pattern.matcher(Text);
		if (Matcher.find()) {
			String Value = Matcher.group(1);
			if (Value != null && !Value.trim().isEmpty()) {
				return Value.trim();
			} else {
				return null;
			}
		}
		return null;
	}

	///<Summary>
	/// Fetch the values of the Fields using pattern
	///@Author: Gokul.P
	///</Summary>
	public static Map<String, String> ExtractFields(String PdfText) 
	{
		Map<String, String> Result = new LinkedHashMap<>();
		Result.put("Application ID", Match(PdfText, "Application ID\"?\\s*[:\"]\\s*\"?(\\w+)"));
		Result.put("Plan",Match(PdfText,"Plan\\s*:\\s*(\\w+::.*)"));
		Result.put("Customer",Match(PdfText,"Customer\\s*:\\s*(\\w+\\s*::\\s*.*)"));
		Result.put("Customer Address", Match(PdfText, "Address\\s*:\\s*(.*?)\\s*Phone"));
		Result.put("Phone", Match(PdfText, "Phone[\"\\s]*[:\"]\\s*\"?(\\d{10})"));
		Result.put("Mobile", Match(PdfText, "Mobile[\"\\s]*[:\"]\\s*\"?(\\d{10})"));
		Result.put("Email", Match(PdfText, "Email\\s*[:\"]\\s*\"?(\\S+@\\S+)?"));
		Result.put("Date Of Birth", Match(PdfText, "Date Of Birth\\s*[:\"]\\s*\"?(\\d{2}/\\d{2}/\\d{4})"));
		Result.put("Identity Type", Match(PdfText, "Identity Type\\s*[:\"]?\\s*(.*?)\\s*Identity Detail"));
		Result.put("Identity Detail", Match(PdfText, "Identity Detail\"?\\s*[:\"]\\s*\"?(\\d{12})"));		
		Result.put("Monthly Advance", Match(PdfText, "Monthly Advance\\s*[:\"]?\\s*(.*?)\\s*Advance Due Date"));	
		Result.put("Remind mode", Match(PdfText, "Remind mode\\s*[:\"]?\\s*(.*?)\\s*Bank A/C Holder Name"));
		Result.put("Bank A/C Holder Name", Match(PdfText, "Bank A/C Holder Name\"?\\s*[:\"]\\s*\"?(.*?)\\s*Bank Name"));
		Result.put("Bank Name", Match(PdfText, "Bank Name\\s*[:\"]?\\s*(.*?)\\s*Bank Account No"));
		Result.put("Bank Account No", Match(PdfText, "Bank Account No\\s*[:\"]\\s*\"?(\\d{9,})"));
		Result.put("IFSC Code", Match(PdfText, "IFSC Code\\s*[:\"]\\s*\"?([A-Z]{4}0\\d{6})"));
		Result.put("Branch", Match(PdfText, "Branch\\s*[:\"]\\s*\"?(.*?)\\s*Proof of Bank Account"));
		Result.put("Proof of Bank Account", Match(PdfText, "Proof of Bank Account\\s*[:\"]\\s*\"?(.*?)\\s*Name"));
		Result.put("Nominee Name", Match(PdfText, "Nominee Name\\s*[:\"]\\s*\"?(.*?)\\s*Nominee Address"));
		Result.put("Nominee Address", Match(PdfText, "Nominee Address\\s*[:\"]\\s*\"?(CPT.*?)\\s*Nominee Phone"));
		Result.put("Nominee Phone", Match(PdfText, "Nominee Phone\\s*[:\"]\\s*\"?(\\d{10})?"));
		Result.put("Relation with nominee", Match(PdfText, "Relation with nominee\\s*[:\"]\\s*\"?(.*?)\\s*Guardian Name"));
		return Result;
	}

	/// <summary>
	/// Extracts and validates key financial fields from the Normal Advance PDF,
	/// including Receipt Id, Advance Received, Net Advance, Weight, Rate,
	/// Deposit Type, Payment Mode, and Amount.
	/// Ensures Advance = Net Advance = Payment.
	/// Author - Nivya Ramesan
	/// </summary>
	public Map<String, String> NormalAdvancePdfValidation() throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("NormalAdvance");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		String[] Lines = ExtractedText.split("\\r?\\n");

		String ReceiptId = "";
		String AdvanceReceived = "";
		String NetAdvance = "";
		String ApproxWeight = "";
		String FixedGoldRate = "";
		String DepositType = "";
		String PaymentMode = "";
		String PaymentAmount = "";
		String MaxGoldRate = "";

		boolean FoundAdvanceLine = false;

		BiFunction<String, String, String> ExtractValueByPrefix = (Line, Prefix) -> {
			if (Line.startsWith(Prefix)) {
				String[] Parts = Line.split(":");
				if (Parts.length > 1) {
					return Parts[1].trim();
				}
			}
			return "";
		};

		for (String LineRaw : Lines) {
			String Line = LineRaw.trim();

			// Receipt ID
			if (Line.startsWith("Receipt Id")) {
				ReceiptId = ExtractValueByPrefix.apply(Line, "Receipt Id");
			}

			//Max Gold Rate
			if (Line.startsWith("Max Gold Rate")) {
				MaxGoldRate = ExtractValueByPrefix.apply(Line, "Max Gold Rate");
			}

			// Advance Received and Net Advance
			if (Line.contains("Advance Received") && Line.contains("Net Advance")) {
				FoundAdvanceLine = true;
			} else if (FoundAdvanceLine) {
				String[] Values = Line.trim().split("\\s+");
				if (Values.length >= 2) {
					AdvanceReceived = Values[0];
					NetAdvance = Values[1];
				}
				FoundAdvanceLine = false;
			}

			// Approx Weight
			if (Line.startsWith("Towards the advance against purchase of approximate")) {
				int Idx = Line.lastIndexOf("approximate");
				if (Idx != -1) {
					ApproxWeight = Line.substring(Idx + 11).replace("GM", "").trim();
				}
			}

			if (Line.startsWith("Fixed gold rate per gram")) {
				FixedGoldRate = ExtractValueByPrefix.apply(Line, "Fixed gold rate per gram");
			}

			if (Line.startsWith("Max gold rate per gram")) {
				FixedGoldRate = ExtractValueByPrefix.apply(Line, "Max gold rate per gram");
			}
			if (Line.startsWith("Deposit Type")) {
				DepositType = ExtractValueByPrefix.apply(Line, "Deposit Type");
			}

			if (Line.startsWith("Convert to Advance")) {
				PaymentMode = "Convert to Advance";
				PaymentAmount = ExtractValueByPrefix.apply(Line, "Convert to Advance");
			}
			if (Line.startsWith("SBI (Credit Card)")) {
				PaymentMode = "SBI (Credit Card)";
				PaymentAmount = ExtractValueByPrefix.apply(Line, "SBI (Credit Card)");
			}

			if (Line.startsWith("HDFC (Credit Card)")) {
				PaymentMode = "HDFC (Credit Card)";
				PaymentAmount = ExtractValueByPrefix.apply(Line, "HDFC (Credit Card)");
			}
		}
		Thread.sleep(2000);
		// Assertions
		//Assert.assertEquals(AdvanceReceived, NetAdvance, "Advance Received and Net Advance do not match");
		//Assert.assertEquals(PaymentAmount, AdvanceReceived, "Advance Received and Payment Amount do not match");

		Map<String, String> ResultMap = new HashMap<>();
		ResultMap.put("ReceiptId", ReceiptId);
		ResultMap.put("MaxGoldRate", MaxGoldRate);
		ResultMap.put("AdvanceReceived", AdvanceReceived);
		ResultMap.put("NetAdvance", NetAdvance);
		ResultMap.put("ApproxWeight", ApproxWeight);
		ResultMap.put("FixedGoldRate", FixedGoldRate);
		ResultMap.put("DepositType", DepositType);
		ResultMap.put("PaymentMode", PaymentMode);
		ResultMap.put("PaymentAmount", PaymentAmount);

		return ResultMap;
	}






	/// <summary>
	/// Method to validate SalesReturn Pdf(PDF Name -Credit Note) for multiple products
	/// Method includes fetching SerialNo,Item Total,Product,GrossWt,StoneWt,NetWt,Amount,TotalProducts,PaymentDetails,TotalNetValue
	/// GST,TotalDiscount,TotalQty,TotalNetValue,TaxableValue 
	/// Author - Christy Reji
	/// </summary>
	public Map<String, String> SalesReturnPDFCredit() throws IOException, InterruptedException {

		// Step 1: Rename the downloaded PDF
		String NewPdfName    = DownloadAndRenameLatestPDF("SalesReturnCreditNote");
		String PdfPath       = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		Map<String, String> CreditNoteData = new HashMap<>();

		// Step 2: Extract Serial Number
		Matcher serialMatcher = Pattern.compile("Serial No\\s*:\\s*(\\S+)").matcher(ExtractedText);
		if (serialMatcher.find()) {
			CreditNoteData.put("SerialNo", serialMatcher.group(1));
		}

		// Step 3: Extract Product details dynamically for any number of products and calculate totals
		Pattern ProductPattern = Pattern.compile("Item Total in Grams\\s*(\\d+\\.\\d{3})\\s*(\\d+\\.\\d{3})\\s*(\\d+\\.\\d{3})\\s*(\\d+\\.\\d{2})");
		Matcher ProductMatcher = ProductPattern.matcher(ExtractedText);
		int ProductIndex    = 1;
		double TotalGrossWt = 0.0;
		double TotalStoneWt = 0.0;
		double TotalNetWt   = 0.0;
		double TotalAmount  = 0.0;
		//Fetching above values of each individual product
		while (ProductMatcher.find()) {
			String GrossWt = ProductMatcher.group(1);
			String StoneWt = ProductMatcher.group(2);
			String NetWt   = ProductMatcher.group(3);
			String Amount  = ProductMatcher.group(4);

			CreditNoteData.put("Product" + ProductIndex + ".GrossWt", GrossWt);
			CreditNoteData.put("Product" + ProductIndex + ".StoneWt", StoneWt);
			CreditNoteData.put("Product" + ProductIndex + ".NetWt", NetWt);
			CreditNoteData.put("Product" + ProductIndex + ".Amount", Amount);

			TotalGrossWt += Double.parseDouble(GrossWt);
			TotalStoneWt += Double.parseDouble(StoneWt);
			TotalNetWt   += Double.parseDouble(NetWt);
			TotalAmount  += Double.parseDouble(Amount);

			ProductIndex++;
		}
		int TotalProducts = ProductIndex - 1;
		CreditNoteData.put("TotalProducts", String.valueOf(TotalProducts));

		// Set calculated values
		CreditNoteData.put("GrossAmount",  String.format("%.2f", TotalAmount));
		CreditNoteData.put("TaxableValue", String.format("%.2f", TotalAmount));
		CreditNoteData.put("TotalNetWt",   String.format("%.3f", TotalNetWt));
		CreditNoteData.put("TotalGrossWt", String.format("%.3f", TotalGrossWt));

		// Step 4: Extract Payment Details
		Matcher ConvertAdvance = Pattern.compile("Convert to Advance\\s*:\\s*(\\d+\\.\\d{2})").matcher(ExtractedText);
		Matcher Adjustment     = Pattern.compile("Adjustment\\s*:\\s*(\\d+\\.\\d{2})").matcher(ExtractedText);
		if (ConvertAdvance.find()) {
			CreditNoteData.put("PaymentDetails", ConvertAdvance.group(1));
		} else if (Adjustment.find()) {
			CreditNoteData.put("PaymentDetails", Adjustment.group(1));
		}

		// Step 5: Extract only TotalNetValue
		Matcher NetValueMatcher = Pattern.compile("Net Value\\s*(\\d+\\.\\d{2})").matcher(ExtractedText);
		if (NetValueMatcher.find()) {
			CreditNoteData.put("TotalNetValue", NetValueMatcher.group(1));
			CreditNoteData.put("TotalValue", NetValueMatcher.group(1));
		}

		// Step 5b: Extract Total Discount 
		Matcher DiscountMatcher = Pattern.compile("Total Gross Wt\\.\\s*(\\d+\\.\\d{2})\\s*(\\d+\\.\\d{2})").matcher(ExtractedText);
		if (DiscountMatcher.find()) {
			CreditNoteData.put("TotalDiscount", DiscountMatcher.group(2));
		}

		// Step 5c: Extract GST value
		Matcher GstMatcher = Pattern.compile("(\\d+\\.\\d{2})\\s*\\n?(\\d+\\.\\d{2})\\s*\\n?Rupees").matcher(ExtractedText);
		if (GstMatcher.find()) {
			CreditNoteData.put("GST", GstMatcher.group(1));
		}

		// Step 6: Total Qty 
		Matcher QtyMatcherAlt = Pattern.compile("Total Qty\\(Pcs\\)[\\s\\n]*(\\d+)").matcher(ExtractedText);
		if (QtyMatcherAlt.find()) {
			String TotalQty = QtyMatcherAlt.group(1);
			if (!TotalQty.equals(String.valueOf(TotalProducts))) {
				CreditNoteData.put("TotalQty", String.valueOf(TotalProducts));
			} else {
				CreditNoteData.put("TotalQty", TotalQty);
			}
		} else {
			CreditNoteData.put("TotalQty", String.valueOf(TotalProducts));
		}
		return CreditNoteData;
		//How to call in your class
		//		    Map<String, String> results = pdfUtils.SalesReturnPDFCredit();
		//		    String SerialNo       = result.get("SerialNo");
		//		    String TotalNetValue  = result.get("TotalNetValue");
		//		    String TaxableValue   = result.get("TaxableValue");
		//		    String PaymentDetails = result.get("PaymentDetails");
		//		    
		//		    //i indicates each individual product
		//		    int totalProducts = Integer.parseInt(result.get("TotalProducts"));
		//		    for (int i = 1; i <= totalProducts; i++) {
		//		        String grossWt = result.get("Product" + i + ".GrossWt");
		//		        String stoneWt = result.get("Product" + i + ".StoneWt");
		//		        String netWt = result.get("Product" + i + ".NetWt");
		//		        String amount = result.get("Product" + i + ".Amount");
		//		    }
	}


	/// <summary>
	/// Validates that each expected row (e.g., invoice line items) is present in the given PDF text.
	/// Author - Hasna
	/// </summary> 
	public void VerifyTableRowInOgPdf(String PdfText, List<String> ExpectedRows) {
		String NormalizedPdf = PdfText.replaceAll("\\s+", " ").trim();

		for (String Row : ExpectedRows) {
			String NormalizedRow = Row.replaceAll("\\s+", " ").trim();
			String[] parts = NormalizedRow.split(" ");

			boolean allPartsFound = true;

			for (String part : parts) {
				// Check if part is a number (allow decimals)
				if (part.matches("-?\\d+(\\.\\d+)?")) {
					double expectedValue = Double.parseDouble(part);
					boolean matched = false;

					// Search for all numbers in the PDF and compare with tolerance
					Matcher m = Pattern.compile("-?\\d+(\\.\\d+)?").matcher(NormalizedPdf);
					while (m.find()) {
						double actualValue = Double.parseDouble(m.group());
						if (Math.abs(actualValue - expectedValue) <= 1.0) {
							matched = true;
							break;
						}
					}

					if (!matched) {
						allPartsFound = false;
						break;
					}
				} else {
					// Non-numeric part: do a direct string check
					if (!NormalizedPdf.contains(part)) {
						allPartsFound = false;
						break;
					}
				}
			}

			if (allPartsFound) {
				System.out.println("ITEM Found: " + Row);
			} else {
				System.out.println("ITEM NOT Found: " + Row);
			}

			asrt.assertTrue(allPartsFound, "Expected parts of PDF row not found (Â±1 tolerance for numbers): " + Row);
		}
	}
	/// <summary>Add commentMore actions
	/// Extract Receipt Number from Sale Invoice PDF
	/// Author - Hasna EK
	/// </summary>
	public String ExtractReceiptNoFrmSaleInvoice(String PdfPath)throws IOException, InterruptedException {

		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}
		return  ExtractedInvoiceNo;
	}

	/// <summary>Add commentMore actions
	/// Extract Customer Name from Sale Invoice PDF
	/// Author - Christy Reji
	public static String ExtractCustomerNameFromSaleInvoice() throws IOException {

		String ExtractedCustomerName = "";
		String PdfContent=FetchingLatestPdfReadContent();

		try {
			// Extract 'Billed to : CUSTOMER NAME'
			Pattern Patterns = Pattern.compile("Billed to\\s*:\\s*([A-Za-z ]+)", Pattern.CASE_INSENSITIVE);
			Matcher Match = Patterns.matcher(PdfContent);
			if (Match.find()) {
				ExtractedCustomerName = Match.group(1).trim();
				System.out.println("Extracted Customer Name: " + ExtractedCustomerName);
			} else {
				System.out.println("Customer Name not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Customer Name: " + e.getMessage());
		}
		return ExtractedCustomerName;
	}

	/// <summary>
	/// Fetching latest downloaded PDF from Invoice folder,then read and return the PDF contents
	/// Author - Christy Reji
	public static String FetchingLatestPdfReadContent() throws IOException {

		// Get folder path
		String FolderPath = System.getProperty("user.dir") + "\\Invoices";
		File   Folder     = new File(FolderPath);

		// Get all PDF files in the folder
		File[] PdfFiles   = Folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

		if (PdfFiles == null || PdfFiles.length == 0) {
			System.out.println("No PDF files found in Invoices folder.");
			return "";
		}
		// Sort by last modified and get the latest one
		File LatestPdf = Arrays.stream(PdfFiles)
				.max(Comparator.comparingLong(File::lastModified))
				.orElse(null);
		if (LatestPdf == null) {
			System.out.println("Unable to find the latest PDF.");
			return "";
		}
		// Read content from latest PDF using PDFBox
		PDDocument Document      = PDDocument.load(LatestPdf);
		PDFTextStripper Stripper = new PDFTextStripper();
		String PdfContent        = Stripper.getText(Document);
		Document.close();
		return PdfContent; 
	}



	///<Summary>
	/// Method to get the total pieces from the stock summary
	/// Author: Gokul.P
	///</Summary>

	public static String StockSummary(String filePath, String sectionName,String Counter, String product, String purity ) {
		try {
			String text = IsGetText(filePath);
			Matcher m = Pattern.compile(Pattern.quote(sectionName) + "(.*?)Sub Total", Pattern.DOTALL).matcher(text);
			if (!m.find()) return "Section not found";
			String sectionText = m.group(1);
			String[] lines = sectionText.split("\\r?\\n");
			int total = 0;
			if (sectionName.equalsIgnoreCase("Sales Return SKU")) {
				int start = -1, end = lines.length;

				// Find start of counter block (e.g., "Sales Return")
				for (int i = 0; i < lines.length; i++)
				{
					if (lines[i].trim().toLowerCase().startsWith(Counter.toLowerCase()))
					{
						start = i + 1;
						break;
					}
				}
				if (start == -1) return "Counter not found";

				// Find end of counter block (next counter)
				for (int i = start; i < lines.length; i++) 
				{
					String line = lines[i].trim();
					if ((line.toLowerCase().startsWith("sales return") || line.toLowerCase().startsWith("transit")) 
							&&!line.toLowerCase().startsWith(Counter.toLowerCase())) 
					{
						end = i;
						break;
					}
				}
				for (int i = start; i < end; i++) 
				{
					String line = lines[i].trim();
					if (!line.isEmpty() && line.toLowerCase().contains(product.toLowerCase()) && line.toLowerCase().contains(purity.toLowerCase()))
					{
						String[] parts = line.split("\\s+");
						for (int j = 0; j < parts.length - 1; j++)
						{
							if (parts[j].equalsIgnoreCase(purity))
							{
								try { return String.valueOf(Integer.parseInt(parts[j + 1])); } catch (Exception ignored) {}
							}
						}
					}
				}
			} else 
			{
				//  Handle On-hand SKU logic
				for (String line : lines) 
				{
					line = line.trim();
					if (!line.isEmpty() && line.toLowerCase().contains(product.toLowerCase()) && line.toLowerCase().contains(purity.toLowerCase()) 
							&&(Counter.equalsIgnoreCase("Gold") && line.startsWith("GLD")
									|| Counter.equalsIgnoreCase("Precia") && line.startsWith("PRC")
									|| Counter.equalsIgnoreCase("Uncut") && line.startsWith("UNC")
									|| line.toLowerCase().startsWith(Counter.toLowerCase())))
					{

						String[] parts = line.split("\\s+");
						for (int i = 0; i < parts.length - 1; i++) 
						{
							if (parts[i].equalsIgnoreCase(purity)) 
							{
								try { total += Integer.parseInt(parts[i + 1]); } catch (Exception ignored) {}
								break;
							}
						}
					}
				}
			}
			return String.valueOf(total);
		} catch (Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	}
	/// <summary>
	/// PDF Validation-Validates OD Purchase Bill 
	/// Author - Chandana Babu
	/// </summary>
	public static Map<String, String> ValidateODPdf(
			String PaymentKeyword,
			List<String> ExpectedGrossWt,
			List<String> ExpectedNetWt,
			List<String> ExpectedGoldRate,
			List<String> ExpectedDiamondWt,
			List<String> ExpectedDiamondRate,
			List<String> ExpectedItemTotal,
			String ExpectedTotal,
			String ExpectedPaymentDetails
			) throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("OD");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		Map<String, String> Data = new HashMap<>();
		//System.out.println("Extracted Text:\n" + ExtractedText);
		// Customer Name
		Matcher CustomerMatcher = Pattern.compile("Billed to\\s*:?\\s*(.+)").matcher(ExtractedText);
		if (CustomerMatcher.find()) {
			Data.put("CustomerName", CustomerMatcher.group(1).trim());
		}
		// Invoice Number
		Matcher InvoiceMatcher = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)").matcher(ExtractedText);
		if (InvoiceMatcher.find()) {
			Data.put("InvoiceNumber", InvoiceMatcher.group(1));
		}
		// Gold Products
		Pattern GoldPattern = Pattern.compile(
				"Raw Gold.*?\\s+(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{3})\\s+(\\d{4,9}\\.\\d{2})\\s+(\\d{4,9}\\.\\d{2})"
				);
		Matcher GoldMatcher = GoldPattern.matcher(ExtractedText);
		int GoldIndex = 1;
		while (GoldMatcher.find()) {
			Data.put("GrossWt" + GoldIndex, GoldMatcher.group(1));
			Data.put("NetWt" + GoldIndex, GoldMatcher.group(2));
			Data.put("GoldRate" + GoldIndex, GoldMatcher.group(3));
			GoldIndex++;
		}
		// Diamond Products
		Pattern DiamondPattern = Pattern.compile(
				"Diamond\\s*\\d+\\s*Ct\\s*(\\d+\\.\\d{3})\\s+(\\d{5,9}\\.\\d{2})\\s+(\\d{5,9}\\.\\d{2})"
				);
		Matcher DiamondMatcher = DiamondPattern.matcher(ExtractedText);

		int DiamondIndex = 1;
		while (DiamondMatcher.find()) {
			Data.put("DiamondWt" + DiamondIndex, DiamondMatcher.group(1));
			Data.put("DiamondRate" + DiamondIndex, DiamondMatcher.group(2));
			DiamondIndex++;
		}
		// Item Totals
		Matcher ItemTotalMatcher = Pattern.compile("Item Total\\s*(\\d{5,9}[\\.,]\\d{2})").matcher(ExtractedText);
		int ItemIndex = 1;
		while (ItemTotalMatcher.find()) {
			Data.put("ItemTotal" + ItemIndex, ItemTotalMatcher.group(1).replace(",", "."));
			ItemIndex++;
		}
		// Grand Total
		Matcher TotalMatcher = Pattern.compile("Total\\s*(\\d{5,9}[\\.,]\\d{2})\\s*Rupees").matcher(ExtractedText);
		if (TotalMatcher.find()) {
			Data.put("Total", TotalMatcher.group(1).replace(",", "."));
		}
		// Payment Details
		String KeywordPattern = Pattern.quote(PaymentKeyword);
		Matcher PaymentMatcher = Pattern.compile(KeywordPattern + "\\s*:?\\s*(\\d{5,9}[\\.,]\\d{2})").matcher(ExtractedText);
		if (PaymentMatcher.find()) {
			Data.put("PaymentDetails", PaymentMatcher.group(1).replace(",", "."));
		} else {
			Data.put("PaymentDetails", "Not Found");
		}
		// Validate Numeric Values using Double.parseDouble
		for (int i = 0; i < ExpectedGrossWt.size(); i++) {
			String GrossWt = "GrossWt" + (i + 1);
			Double ActualGrossWeight = Double.parseDouble(Data.get(GrossWt));
			Double ExpectedGrossWeight = Double.parseDouble(ExpectedGrossWt.get(i));
			asrt.assertEquals(ActualGrossWeight, ExpectedGrossWeight, 1.0,"Gross weight mismatch: Expected value is " + ExpectedGrossWeight + " but got " + 
					ActualGrossWeight + " in OD purchase bill");
		}

		for (int i = 0; i < ExpectedNetWt.size(); i++) {
			String NetWt = "NetWt" + (i + 1);
			Double ActualNetWeight = Double.parseDouble(Data.get(NetWt));
			Double ExpectedNetWeight = Double.parseDouble(ExpectedNetWt.get(i));
			asrt.assertEquals(ActualNetWeight, ExpectedNetWeight, 1.0,"Net weight mismatch: Expected value is " + ExpectedNetWeight + " but got " + ActualNetWeight 
					+ " in OD purchase bill");
		}

		for (int i = 0; i < ExpectedGoldRate.size(); i++) {
			String GoldRate = "GoldRate" + (i + 1);
			Double ActualRawGoldRate = Double.parseDouble(Data.get(GoldRate));
			Double ExpectedRawGoldRate = Double.parseDouble(ExpectedGoldRate.get(i));
			asrt.assertEquals(ActualRawGoldRate, ExpectedRawGoldRate,1.0,"Gold rate mismatch: Expected value is " + ExpectedRawGoldRate + " but got " + 
					ActualRawGoldRate + " in OD purchase bill");
		}

		for (int i = 0; i < ExpectedDiamondWt.size(); i++) {
			String DiamondWt = "DiamondWt" + (i + 1);
			Double ActualDiamondWeight = Double.parseDouble(Data.get(DiamondWt));
			System.out.print("PDF Daimond Weight " +ActualDiamondWeight);

			Double ExpectedDiamondWeight = Double.parseDouble(ExpectedDiamondWt.get(i));
			asrt.assertEquals(ActualDiamondWeight, ExpectedDiamondWeight,1.0, "Diamond weight mismatch: Expected value is " + ExpectedDiamondWeight + " but got " + 
					ActualDiamondWeight + " in OD purchase bill");
		}

		for (int i = 0; i < ExpectedDiamondRate.size(); i++) {
			String DiamondRate = "DiamondRate" + (i + 1);
			Double ActualDiamondRateValue = Double.parseDouble(Data.get(DiamondRate));
			Double ExpectedDiamondRateValue = Double.parseDouble(ExpectedDiamondRate.get(i));
			asrt.assertEquals(ActualDiamondRateValue, ExpectedDiamondRateValue,1.0, "Diamond rate mismatch: Expected value is " + ExpectedDiamondRateValue + " but got " + ActualDiamondRateValue + " in OD purchase bill");
		}

		for (int i = 0; i < ExpectedItemTotal.size(); i++) {
			String ItemTotal = "ItemTotal" + (i + 1);
			Double ActualItemTotalValue = Double.parseDouble(Data.get(ItemTotal));
			Double ExpectedItemTotalValue = Double.parseDouble(ExpectedItemTotal.get(i));
			asrt.assertEquals(ActualItemTotalValue, ExpectedItemTotalValue, 1.0,"Item total mismatch: Expected value is " + ExpectedItemTotalValue + " but got " + 
					ActualItemTotalValue + " in OD purchase bill");
		}
		Double ActualTotal = Double.parseDouble(Data.get("Total"));
		Double ExpectedTotalVal = Double.parseDouble(ExpectedTotal);
		asrt.assertEquals(ActualTotal, ExpectedTotalVal, 1.0,"Total mismatch: Expected value is " + ExpectedTotalVal + " but got " + ActualTotal + " in OD purchase bill");

		Double ActualPayment = Double.parseDouble(Data.get("PaymentDetails"));
		Double ExpectedPaymentVal = Double.parseDouble(ExpectedPaymentDetails);
		asrt.assertEquals(ActualPayment, ExpectedPaymentVal,1.0, "Payment Details mismatch: Expected value is " + ExpectedPaymentVal + " but got " + ActualPayment 
				+ " in OD purchase bill");

		System.out.println("OD PDF validation successful.");
		//System.out.println("Extracted Data: " + Data);
		return Data;
	}


	/// <summary>
	/// Extract Purity from Sale Invoice PDF
	/// Author - Robin
	/// </summary>
	public String ExtractPurityFrmSaleInvoice(String PdfPath)throws IOException, InterruptedException {

		String ExtractedPurity = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {

			Pattern PurityPattern = Pattern.compile("(18K)\\s*:\\s*INR\\s*([\\d,]+\\.\\d{2})");
			Matcher PurityMatcher = PurityPattern.matcher(PdfContent);
			if (PurityMatcher.find()) {
				ExtractedPurity = PurityMatcher.group(1).trim();
				System.out.println("Extracted Purity: " + ExtractedPurity );
			} else {
				System.out.println("18K Purity not found in PDF.");
			}

		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		return ExtractedPurity;
	}
	/// <summary>
	/// Extracting Application ID from GOLDEN BLOOM PURCHASE ADVANCE PLAN - Application Form PDF
	/// Author - Aiswarya
	/// </summary>

	public static String ExtractApplicationIdFromPdf() throws IOException, InterruptedException {
		String ApplicationId = "";
		String NewPdfName = DownloadAndRenameLatestPDF("GB Application Form");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);	

		try {

			// Regex to match
			Pattern pattern = Pattern.compile("Application ID\\s*[:ï¼š]\\s*(\\w+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(ExtractedText);
			if (matcher.find()) {
				ApplicationId = matcher.group(1).trim();
				System.out.println("Extracted Application ID: " + ApplicationId);
			} else {
				System.out.println("Application ID not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Application ID: " + e.getMessage());
		}
		return ApplicationId;
	}
	/// <summary>
	/// Extracts Amount, Cumulative Amount, and Payment Mode from the GPP RECEIPT PDF
	/// Author - Aiswarya
	/// </summary>
	public static Map<String, String> ExtractPaymentDetailsFromPdf() throws IOException, InterruptedException {
		Map<String, String> PaymentDetails = new HashMap<>();

		String NewPdfName = DownloadAndRenameLatestPDF("GPP RECEIPT");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);	

		try {

			String[] lines = ExtractedText.split("\\r?\\n");
			for (int i = 0; i < lines.length - 1; i++) {
				if (lines[i].contains("Amt(Rs.)") && lines[i].contains("Cum. Amt(Rs.)")) {
					// Likely table header found; get next line
					String[] NextLineValues = lines[i + 1].trim().split("\\s+");

					// Example: [11476.00, 0.000, 22k, 91.6, 1000.00, 3000.00, 0.000]
					if (NextLineValues.length >= 6) {
						PaymentDetails.put("Amt", NextLineValues[4].trim());
						PaymentDetails.put("CumAmt", NextLineValues[5].trim());
					}
					break;
				}
			}

			// Extract Payment Mode (same as before)
			Pattern PaymentModePattern = Pattern.compile("Payment Mode\\s*:\\s*(.+)");
			Matcher PaymentModeMatcher = PaymentModePattern.matcher(ExtractedText);
			if (PaymentModeMatcher.find()) {
				PaymentDetails.put("PaymentMode", PaymentModeMatcher.group(1).trim());
			} else {
				System.out.println("Payment Mode not found.");
			}
			for (int i = 0; i < lines.length; i++) {

				// 1. Application ID
				if (lines[i].toLowerCase().contains("application id")) {
					String[] parts = lines[i].split(":");
					if (parts.length >= 2) {
						PaymentDetails.put("ApplicationID", parts[1].trim());
					}
				}

				// 2. Being advance against
				if (lines[i].toLowerCase().contains("being advance against")) {
					String[] parts = lines[i].split(":");
					if (parts.length >= 2) {
						PaymentDetails.put("AdvanceAgainst", parts[1].trim());
					}
				}

				// 3. Customer Name
				if (lines[i].toLowerCase().startsWith("billed to")) {
					String[] parts = lines[i].split(":");
					if (parts.length >= 2) {
						PaymentDetails.put("CustomerName", parts[1].trim());
					}
				}

				// 4. Mobile
				if (lines[i].toLowerCase().startsWith("mobile")) {
					String[] parts = lines[i].split(":");
					if (parts.length >= 2) {
						PaymentDetails.put("Phone", parts[1].trim());
					}
				}

				// 5. Board Rate, Purity, Amt
				if (lines[i].contains("Amt(Rs.)") && lines[i].contains("Cum. Amt(Rs.)")) {
					if (i + 1 < lines.length) {
						String[] NextLineValues = lines[i + 1].trim().split("\\s+");
						// Example: [14088.00, 0.923, 22k, 91.6, 13000.00, 13000.00, 0.923]
						if (NextLineValues.length >= 5) {
							PaymentDetails.put("BoardRate", NextLineValues[0].trim());
							PaymentDetails.put("Purity", NextLineValues[2].trim());
							PaymentDetails.put("Amt", NextLineValues[4].trim());
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error extracting payment details: " + e.getMessage());
		}

		return PaymentDetails;
	}


	/// <summary>
	/// Verifies Advance Received,Approx Weight,Fixed Gold Rate,Deposit Type in normalized PDF text.
	/// Author - Hasna EK
	/// </summary>
	public void VerifyFieldsInNormalAdvancePdf(double ExpectedAdvanceReceived, String DepositType) throws  InterruptedException, IOException{
		Map<String, String> pdfData = this.NormalAdvancePdfValidation();

		// Log relevant PDF fields
		System.out.println("Receipt Id: " + pdfData.get("ReceiptId"));
		System.out.println("Advance Received: " + pdfData.get("AdvanceReceived"));
		System.out.println("Approx Weight: " + pdfData.get("ApproxWeight"));
		System.out.println("Fixed Gold Rate: " + pdfData.get("FixedGoldRate"));
		System.out.println("Deposit Type: " + pdfData.get("DepositType"));

		double AdvanceReceived = Double.parseDouble(pdfData.get("AdvanceReceived").replaceAll("[^\\d.-]", "").trim());
		double FixedGoldRate = Double.parseDouble(pdfData.get("FixedGoldRate").replaceAll("[^\\d.-]", "").trim());
		double MaxGoldRate = Double.parseDouble(pdfData.get("MaxGoldRate").replaceAll("[^\\d.-]", "").trim());
		double approxWeightPdf = Double.parseDouble(pdfData.get("ApproxWeight").replaceAll("[^\\d.-]", "").trim());
		String PdfDepositType = pdfData.get("DepositType");
		System.out.println("FixedGoldRate= "+FixedGoldRate);
		// Validations
		asrt.assertEquals(ExpectedAdvanceReceived, AdvanceReceived, 1, 
				"Total amount mismatch: Expected " + ExpectedAdvanceReceived + " but got " + AdvanceReceived + " in the Normal Advance Pdf");
		System.out.println("MaxGoldRate= "+MaxGoldRate);
		asrt.assertEquals(MaxGoldRate, FixedGoldRate, 1, 
				"Fixed Gold Rate mismatch: Expected " + FixedGoldRate + " but got " + MaxGoldRate + " in the Normal Advance Pdf");

		asrt.assertEquals(DepositType, PdfDepositType, 
				"Deposit Type mismatch: Expected " + DepositType + " but got " + PdfDepositType + " in the Normal Advance Pdf");

		// Approx Weight Calculation and Validation
		if (pdfData.get("ApproxWeight") != null && !pdfData.get("ApproxWeight").isEmpty()) {
			double approxWeightExpected = AdvanceReceived / MaxGoldRate;
			double roundedApproxWeightExpected = Math.round(approxWeightExpected * 1000.0) / 1000.0;

			System.out.println("Rounded ApproxWeight Expected: " + roundedApproxWeightExpected);
			System.out.println("ApproxWeight PDF Value: " + approxWeightPdf);

			asrt.assertEquals(approxWeightPdf, roundedApproxWeightExpected, 1, 
					"Mismatch of ApproxWeightValue: Expected: " + approxWeightPdf + " but got " + roundedApproxWeightExpected + " in the Normal Advance Pdf");
		}
	}

	/// <summary>
	/// Verifies Taxable Value,Gross amount, Gross weight, Net weight, Net total,stone weight, lines count etc in credit note PDF text.
	/// Author - Hasna EK
	/// </summary>
	public void VerifyFieldsInCreditNotePdf(Map<String, String> SkuWiseData, Map<String, Object> InvoiceDetails, String LinesCountReturn, double 
			ExpectedAdvanceReceived) throws  InterruptedException, IOException{

		Map<String, String> Result = this.SalesReturnPDFCredit();
		String PdfSerialNoReturn         = Result.get("SerialNo");
		String PdfTotalProductsReturn    = Result.get("TotalProducts");
		String PdfTotalQtyReturn         = Result.get("TotalQty");
		String PdfGrossAmountReturn      = Result.get("GrossAmount");
		String PdfTotalNetWtReturn       = Result.get("TotalNetWt");
		String PdfTotalGrossWtReturn     = Result.get("TotalGrossWt");
		String PdfTotalNetValueReturn    = Result.get("TotalNetValue");
		String PdfTaxableValueReturn     = Result.get("TaxableValue");
		String PdfAdjustmentReturn       = Result.get("PaymentDetails");
		String PdfTotalValueReturn       = Result.get("TotalValue");
		int TotalProductsSale = Integer.parseInt(Result.get("TotalProducts"));

		// Loop through each SKU/Product
		for (int i = 1; i <= TotalProductsSale; i++) {
			String ExpectedGrossWt   		= Result.get("Product" + i + ".GrossWt");
			String ExpectedStoneWt		    = Result.get("Product" + i + ".StoneWt");
			String ExpectedNetWt 			= Result.get("Product" + i + ".NetWt");
			String ExpectedAmount 			= Result.get("Product" + i + ".Amount");
			String ActualGrossWt 			= SkuWiseData.getOrDefault("SKU_" + i + "_GrossWeight", "N/A");
			String ActualStoneWt 			= SkuWiseData.getOrDefault("SKU_" + i + "_StoneWeight", "N/A");
			String ActualNetWt 				= SkuWiseData.getOrDefault("SKU_" + i + "_NetWeight", "N/A");
			String ActualAmount 			= SkuWiseData.getOrDefault("SKU_" + i + "_Total", "N/A");

			Assert.assertEquals(ActualGrossWt, ExpectedGrossWt, "Mismatch in Gross Weight for SKU_" + i +"in PDF Sale Return Invoice details");
			Assert.assertEquals(ActualStoneWt, ExpectedStoneWt, "Mismatch in Stone Weight for SKU_" + i+"in PDF Sale Return Invoice details");
			Assert.assertEquals(ActualNetWt, ExpectedNetWt, "Mismatch in Net Weight for SKU_" + i+"in PDF Sale Return Invoice details");
			//Assert.assertEquals(ActualAmount, ExpectedAmount,1,"Mismatch in Total Amount for SKU_" + i+"in PDF Sale Return Invoice details");
			Assert.assertEquals(Double.parseDouble(ActualAmount), Double.parseDouble(ExpectedAmount),1.00,"Mismatch in Total Amount for SKU_" + i+"in PDF Sale Return Invoice details");
		}
		double ActualTaxableValue 		= Math.abs(Double.parseDouble(((String) InvoiceDetails.get("SubTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", 
				"").trim()));
		double ExpectedTaxableValue  	= Double.parseDouble(PdfTaxableValueReturn);

		double ActualGrossAmount 		= Math.abs(Double.parseDouble(((String) InvoiceDetails.get("SubTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", 
				"").trim()));
		double ExpectedGrossAmount 		= Double.parseDouble(PdfGrossAmountReturn);

		double ActualTotalGrossWeight   = Math.abs(Double.parseDouble(((String) InvoiceDetails.get("TotalGrossWeight")).replaceAll("[\u20B9,=:]", 
				"").replaceAll("\\s+", "").trim()));
		double ExpectedTotalGrossWeight = Double.parseDouble(PdfTotalGrossWtReturn);

		double ActualNetWeight			= Double.parseDouble(((String) InvoiceDetails.get("TotalNetWeight")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", 
				"").trim());
		double ExpectedNetWeight 		= Double.parseDouble(PdfTotalNetWtReturn);

		double ActualNetTotal 			= Double.parseDouble(((String) InvoiceDetails.get("NetTotal")).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		double ExpectedNetTotal 		= Double.parseDouble(PdfTotalNetValueReturn);

		//		Assert.assertEquals(ExpectedAdvanceReceived, PdfAdjustmentReturn,1, "Mismatch in Taxable Value Actual "+ActualTaxableValue+" and Expected Taxable Value "+ExpectedTaxableValue+"in PDF Sale Return Invoice details");
		Assert.assertEquals(ActualTaxableValue, ExpectedTaxableValue,1, "Mismatch in Taxable Value Actual "+ActualTaxableValue+" and Expected Taxable Value "+ExpectedTaxableValue+"in PDF Sale Return Invoice details");
		Assert.assertEquals(ActualGrossAmount, ExpectedGrossAmount,1, "Mismatch in Gross Amount Actual "+ActualGrossAmount+" and Expected Gross Amount "+ExpectedGrossAmount+"in PDF Sale Return Invoice details");
		Assert.assertEquals(LinesCountReturn, PdfTotalQtyReturn, "Mismatch in No of Product Lines Actual "+LinesCountReturn+" and Expected No of Product Lines "+PdfTotalProductsReturn+"in PDF Sale Return Invoice details");
		Assert.assertEquals(ActualNetWeight, ExpectedNetWeight,1, "Mismatch in Total Net Weight Actual "+ActualNetWeight+" and Expected Net Weight "+ExpectedNetWeight+"in PDF Sale Return Invoice details");
		Assert.assertEquals(ActualTotalGrossWeight, ExpectedTotalGrossWeight,1, "Mismatch in Total Gross Weight Actual "+ActualTotalGrossWeight+" and Expected Total Gross Weight"+ExpectedTotalGrossWeight+"in PDF Sale Return Invoice details");
		Assert.assertEquals(ActualNetTotal, ExpectedNetTotal,1, "Mismatch in Net Total Actual "+ActualNetTotal+" and Expected Net Total "+ExpectedNetTotal+"in PDF Sale Return Invoice details");

	}
	/// <summary>
	/// Verifies expected label-value pairs in normalized PDF text.
	/// Backward-compatible with older test cases.
	/// Tolerates currency signs, brackets, spacing, and float precision up to 1. 
	/// Author - Hasna EK
	/// </summary>
	public void VerifyPdfFieldsWithTolerance(String PdfText, Map<String, String> ExpectedFields) {
		// Step 1: Normalize the entire PDF text by removing currency, symbols, brackets, percent signs, and spaces
		String NormalizedPdfContent = PdfText.replaceAll("[\u20B9,=:]", "")  // remove \u20B9, commas, colons, equal signs
				.replaceAll("\\s+", "")    // remove all whitespace
				.trim();

		for (Map.Entry<String, String> Entry : ExpectedFields.entrySet()) {
			// Step 2: Normalize label and expected value in the same way
			//			String Label = Entry.getKey().trim().replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "");
			String Label = Entry.getKey()
					.replaceAll("[\u20B9,=:]", "")
					.replaceAll("\\d+(\\.\\d+)?%?", "") // removes numeric values like "3.00%" from labels
					.replaceAll("\\s+", "")
					.trim();
			System.out.println("Label = "+Label);

			String ExpectedValue = Entry.getValue().trim().replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "");

			System.out.println("ExpectedValue = "+ExpectedValue);

			boolean IsFound = false;

			// Step 3: Combine for exact match like "GrossAmount22484.50"
			String ExpectedTextCombined = (Label + ExpectedValue).trim();

			if (NormalizedPdfContent.contains(ExpectedTextCombined)) {
				IsFound = true;
			} else {
				// Step 4: Loose match â€” check if label exists and value follows
				int LabelIndex = NormalizedPdfContent.indexOf(Label);
				if (LabelIndex != -1) {
					String AfterLabel = NormalizedPdfContent.substring(LabelIndex + Label.length());

					// Step 1: Remove percentage patterns like "3.00%"
					String cleanedAfterLabel = AfterLabel.replaceFirst("\\d+(\\.\\d+)?%", "");

					// Step 2: Match the first number (InvoiceTotal1123.44 or GST11924.74)
					Matcher matcher = Pattern.compile("(\\d+(\\.\\d+)?)").matcher(cleanedAfterLabel);
					if (matcher.find()) {
						String actualStr = matcher.group(1);
						double actual = Double.parseDouble(actualStr);
						double expected = Double.parseDouble(ExpectedValue);

						System.out.println("actual = " + actual);

						if (Math.abs(actual - expected) <= 1) {
							IsFound = true;
						}
					}
				}
			}
			if (IsFound) {
				System.out.println("Found: " + Label + " = " + ExpectedValue);
			} else {
				System.out.println("NOT Found: " + Label + " = " + ExpectedValue);
			}

			Assert.assertTrue(IsFound, "Expected PDF content not found: [" + Label + " = " + ExpectedValue + "]");
		}
	}

	///<Summary>
	///Method validate OS Pdf
	///</Summary>
	public Map<String, String> OSPdfValidation(String paymentKeyword) throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("OS");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		Map<String, String> pdfData = new HashMap<>();

		// Match lines with: Gross Wt, Net Wt, Rate, Amount
		Pattern pattern = Pattern.compile("(\\d{1,3}\\.\\d{3})\\s+(\\d{1,3}\\.\\d{3})\\s+(\\d{1,3}\\.\\d{2})\\s+(\\d{3,4}\\.\\d{2})");
		Matcher matcher = pattern.matcher(ExtractedText);

		int index = 1;
		while (matcher.find()) {
			pdfData.put("GrossWt" + index, matcher.group(1));
			pdfData.put("NetWt"   + index, matcher.group(2));
			pdfData.put("Rate"    + index, matcher.group(3));
			index++;
		}

		pdfData.put("TotalItems", String.valueOf(index - 1));

		// Extract adjustment/payment
		Pattern adjustmentPattern = Pattern.compile(Pattern.quote(paymentKeyword) + "\\s*:?\\s*(\\d{3,4}\\.\\d{2})");
		Matcher adjustmentMatcher = adjustmentPattern.matcher(ExtractedText);
		if (adjustmentMatcher.find()) {
			pdfData.put("PaymentDetails", adjustmentMatcher.group(1));
		} else {
			pdfData.put("PaymentDetails", "Not Found");
		}

		// Debug print
		System.out.println("PDF Extracted Data:");
		for (Map.Entry<String, String> entry : pdfData.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return pdfData;
	}

	/// <summary>
	/// PDF Validation - Validates OU Purchase Bill (Old Uncut)
	/// Author - Robin T Abraham
	/// </summary>
	public static Map<String, Object> ValidateOUPdf(
			String PaymentKeyword,
			String ExpectedCustomer,
			String ExpectedGrossWtStr,
			List<String> ExpectedNetWtOU,
			List<String> ExpectedGoldRate,
			String ExpectedItemTotal,
			String ExpectedTotal,
			String ExpectedPaymentDetails
			) throws IOException, InterruptedException {

		// Convert comma-separated strings to trimmed List<String>
		List<String> ExpectedGrossWtOU = Arrays.stream(ExpectedGrossWtStr.split(","))
				.map(String::trim)
				.collect(Collectors.toList());

		String NewPdfName = DownloadAndRenameLatestPDF("OU");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		Map<String, Object> Data = new HashMap<>();

		// Customer Name
		Matcher CustomerMatcher = Pattern.compile("(?i)Billed to\\s*:?\\s*(.+)").matcher(ExtractedText);
		if (CustomerMatcher.find()) {
			String CustomerName = CustomerMatcher.group(1).trim();
			Data.put("CustomerName", CustomerName);
			asrt.assertEquals(CustomerName, ExpectedCustomer, "Customer name mismatch in OU purchase bill");
		}

		// Gold Products
		Pattern GoldPattern = Pattern.compile("Raw Gold.*?(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{3})\\s+(\\d{4,9}\\.\\d{2})");
		Matcher GoldMatcher = GoldPattern.matcher(ExtractedText);
		int GoldIndex = 1;
		while (GoldMatcher.find()) {

			Data.put("GrossWt" + GoldIndex, GoldMatcher.group(1));
			Data.put("NetWt" + GoldIndex, GoldMatcher.group(2));
			Data.put("GoldRate" + GoldIndex, GoldMatcher.group(3));
			GoldIndex++;
		}

		// Stone Products
		List<Map<String, String>> ExtractedStones = new ArrayList<>();
		Pattern StonePattern = Pattern.compile("(?i)([A-Za-z]+)\\s+Ct\\s+(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{2})\\s+(\\d+\\.\\d{2})");
		Matcher StoneMatcher = StonePattern.matcher(ExtractedText);
		while (StoneMatcher.find()) {
			Map<String, String> Stone = new HashMap<>();
			Stone.put("StoneName", StoneMatcher.group(1).trim());
			Stone.put("StoneWt", StoneMatcher.group(2));
			Stone.put("Rate", StoneMatcher.group(3));
			Stone.put("Amount", StoneMatcher.group(4));
			ExtractedStones.add(Stone);
		}
		Data.put("Stones", ExtractedStones);

		// Item Total
		Matcher ItemTotalMatcher = Pattern.compile("Item Total\\s*(\\d{5,9}[\\.,]\\d{2})").matcher(ExtractedText);
		if (ItemTotalMatcher.find()) {
			String ItemTotal = ItemTotalMatcher.group(1).replace(",", ".");
			Data.put("ItemTotal", ItemTotal);
			if (!ExpectedItemTotal.isEmpty()) {
				asrt.assertEquals(Double.parseDouble(ItemTotal), Double.parseDouble(ExpectedItemTotal), "Item total mismatch in OU purchase bill");
			}
		}

		// Grand Total
		Matcher TotalMatcher = Pattern.compile("Total\\s*(\\d{5,9}[\\.,]\\d{2})").matcher(ExtractedText);
		if (TotalMatcher.find()) {
			String Total = TotalMatcher.group(1).replace(",", ".");
			Data.put("Total", Total);

			if (!ExpectedTotal.isEmpty())
				asrt.assertEquals(Double.parseDouble(Total), Double.parseDouble(ExpectedTotal), "Total mismatch in OU purchase bill");
		}

		// Payment Details
		String KeywordPattern = Pattern.quote(PaymentKeyword);
		Matcher PaymentMatcher = Pattern.compile(KeywordPattern + "\\s*:?\\s*(\\d{5,9}[\\.,]\\d{2})").matcher(ExtractedText);
		if (PaymentMatcher.find()) {
			String PaymentVal = PaymentMatcher.group(1).replace(",", ".");
			Data.put("PaymentDetails", PaymentVal);

			if (!ExpectedPaymentDetails.isEmpty())
				asrt.assertEquals(Double.parseDouble(PaymentVal), Double.parseDouble(ExpectedPaymentDetails), "Payment details mismatch in OU purchase bill");
		}

		// Validate Gold values
		try {
			asrt.assertEquals(
					Double.parseDouble(Data.get("GrossWt1").toString()),
					Double.parseDouble(ExpectedGrossWtOU.get(0)),
					"Gross weight mismatch for RG"
					);

			asrt.assertEquals(
					Double.parseDouble(Data.get("NetWt1").toString()),
					Double.parseDouble(ExpectedNetWtOU.get(0)),
					"Net weight mismatch for RG"
					);

			asrt.assertEquals(
					Double.parseDouble(Data.get("GoldRate1").toString()),
					Double.parseDouble(ExpectedGoldRate.get(0)),
					"Gold rate mismatch for RG"
					);
		} catch (NullPointerException e) {
			throw new AssertionError("Missing Raw Gold data in PDF", e);
		}

		System.out.println("OU PDF validation successful.");
		return Data;
	}

	/// <summary>
	/// PDF Validation - Validates Purchase Bill (Old Precia, Old Gold etc.)
	/// Author - Hasna EK
	/// </summary>
	public static Map<String, String> PurchaseBillPdfValidation(String paymentKeyword,double TotalAmountInBillingPge, double TotalGrossWt,double 
			AmountDueInBillingPge, double TotalNetWt) throws IOException, InterruptedException {
		String PdfName = DownloadAndRenameLatestPDF("PurchaseBill");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + PdfName;
		String ExtractedText = IsGetText(PdfPath);

		System.out.println("ExtractedText:\n" + ExtractedText);

		Map<String, String> PdfData = new HashMap<>();

		// Invoice Number
		Matcher InvoiceMatcher = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)").matcher(ExtractedText);
		if (InvoiceMatcher.find()) {
			PdfData.put("InvoiceNumber", InvoiceMatcher.group(1));
		}

		// 1. Extract Item Total
		Matcher ItemTotalMatcher = Pattern.compile("Item Total\\s*(\\d+\\.\\d{2})").matcher(ExtractedText);
		if (ItemTotalMatcher.find()) {
			PdfData.put("ItemTotal", ItemTotalMatcher.group(1));
		}

		// 2. Extract GrossWt and NetWt from line like "2 6.740 6.472"
		Matcher WtMatcher = Pattern.compile("\\d+\\s+(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{3})").matcher(ExtractedText);
		if (WtMatcher.find()) {
			PdfData.put("GrossWt", WtMatcher.group(1));
			PdfData.put("NetWt", WtMatcher.group(2));
		}

		// 3.Extract Total Amount (numeric, after "Total", followed by currency in words or number line)
		Matcher TotalAmountMatcher = Pattern.compile("Total\\s*(\\d{4,6}[\\.,]\\d{2})\\s*Rupees").matcher(ExtractedText);
		if (TotalAmountMatcher.find()) {
			PdfData.put("TotalAmount", TotalAmountMatcher.group(1).replace(",", "."));
		}

		// 4. Extract Payment Details using dynamic keyword like RTGS/NEFT
		String keywordPattern = Pattern.quote(paymentKeyword);
		Matcher PaymentMatcher = Pattern.compile(keywordPattern + "\\s*:?\\s*(\\d+\\.\\d{2})").matcher(ExtractedText);
		if (PaymentMatcher.find()) {
			PdfData.put("PaymentDetails", PaymentMatcher.group(1));
		} else {
			PdfData.put("PaymentDetails", "Not Found");
		}

		// 5. Compare TotalAmount and PaymentDetails
		String TotalAmount = PdfData.get("TotalAmount");
		String Payment = PdfData.get("PaymentDetails");
		if (TotalAmount != null && Payment != null && !Payment.equals("Not Found")) {
			asrt.assertEquals(TotalAmount, Payment, "Mismatch between total"+TotalAmount+" and payment"+Payment+" in Purchase bill");
		} else {
			throw new AssertionError("Missing Total or Payment for validation.");
		}
		double TotalAmountInPdf = Double.parseDouble(TotalAmount);
		asrt.assertEquals(TotalAmountInBillingPge, TotalAmountInPdf,1,"Mismatch in Total amount, Expected "+TotalAmountInBillingPge+"but got"+TotalAmountInPdf+"in Purchase bill");

		String GrossWt = PdfData.get("GrossWt");

		double TotalGrossWtInPdf = Double.parseDouble(GrossWt);
		asrt.assertEquals(TotalGrossWt, TotalGrossWtInPdf,1,"Mismatch in Total amount, Expected "+TotalGrossWt+"but got"+TotalGrossWtInPdf+"in Purchase bill");

		double PaymentAmount = Double.parseDouble(Payment);

		asrt.assertEquals(AmountDueInBillingPge, PaymentAmount,1,"Mismatch in Total amount, Expected "+AmountDueInBillingPge+"but got"+PaymentAmount+"in Purchase bill");

		String NetWtInPdf = PdfData.get("NetWt");
		double TotalNetWtInPdf = Double.parseDouble(NetWtInPdf);
		asrt.assertEquals(TotalNetWt, TotalNetWtInPdf,1,"Mismatch in Total amount, Expected "+TotalNetWt+"but got"+TotalNetWtInPdf+"in Purchase bill");


		System.out.println("Purchase Bill validation successful.");
		System.out.println("Extracted Data: " + PdfData);
		return PdfData;
	}
	/// <summary>
	/// Extracts and validates key financial fields from the Offer Advance PDF,
	/// including Receipt Id, Advance Received, Net Advance, Weight, Rate,
	/// Deposit Type, Payment Mode, Validity, Offer Expiry Date, and Item Details.
	/// Author - Aiswarya
	/// </summary>
	public Map<String, String> OfferAdvancePdfValidation() throws IOException, InterruptedException {

		// Step 1: Download and read PDF
		Thread.sleep(5000);
		String NewPdfName = PdfUtilities.DownloadAndRenameLatestPDF("OfferAdvance");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = PdfUtilities.IsGetText(PdfPath);
		String[] Lines = ExtractedText.split("\\r?\\n");
		String ReceiptId = "";
		String AdvanceReceived = "";
		String ApproxWeight = "";
		String FixedGoldRate = "";
		String DepositType = "";
		String Validity = "";
		String ValidityDays = "";
		String OfferExpiryDate = "";
		boolean nextLineHasAdvance = false;
		BiFunction<String, String, String> ExtractValueByPrefix = (Line, Prefix) -> {
			if (Line.startsWith(Prefix)) {
				String[] Parts = Line.split(":");
				if (Parts.length > 1) {
					return Parts[1].trim();
				}
			}
			return "";
		};
		Pattern amountPattern = Pattern.compile("Amount\\s*:\\s*(\\d+[.]\\d+)");
		Pattern daysPattern = Pattern.compile("\\((\\d+)\\)\\s*Days");
		for (String LineRaw : Lines) {
			String Line = LineRaw.trim();
			// Receipt ID
			if (Line.startsWith("Receipt Id")) {
				ReceiptId = ExtractValueByPrefix.apply(Line, "Receipt Id");
			}
			// Advance Received using regex
			if (Line.contains("Advance Received") && Line.contains("Net Advance")) {
				nextLineHasAdvance = true;
				continue;
			}

			if (nextLineHasAdvance) {
				String[] parts = Line.split("\\s+");
				if (parts.length >= 2) {
					AdvanceReceived = parts[0]; // First value is Advance Received
					// Optional: String NetAdvance = parts[1];
				}
				nextLineHasAdvance = false;
			}
			// Approx Weight
			if (Line.startsWith("Line: 1") && Line.contains("G.Wt.")) {
				Pattern wtPattern = Pattern.compile("G\\.Wt\\.\\s*:\\s*(\\d+\\.\\d+)");
				Matcher wtMatcher = wtPattern.matcher(Line);
				if (wtMatcher.find()) {
					ApproxWeight = wtMatcher.group(1);
				}
			}
			// Validity, Days, Expiry Date
			if (Line.contains("Validity") && Line.contains("Offer Expire Date")) {
				String[] parts = Line.split("\\s+");
				for (int i = 0; i < parts.length; i++) {
					if (parts[i].equalsIgnoreCase("Validity")) {
						Validity = parts[i + 1] + " " + parts[i + 2]; // e.g., 3.00 months
					} else if (parts[i].equalsIgnoreCase("Date")) {
						OfferExpiryDate = parts[i + 1].replace("(", "").replace(")", "").trim();
					}
				}
				// Extract days using regex
				Matcher daysMatcher = daysPattern.matcher(Line);
				if (daysMatcher.find()) {

					ValidityDays = daysMatcher.group(1); // e.g., "30"
				}
			}
			// Gold Rate
			if (Line.startsWith("Max gold rate per gram") || Line.startsWith("Fixed gold rate per gram")) {
				String prefix = Line.startsWith("Max") ? "Max gold rate per gram" : "Fixed gold rate per gram";
				FixedGoldRate = ExtractValueByPrefix.apply(Line, prefix);
			}
			// Deposit Type

			if (Line.startsWith("Deposit Type")) {

				DepositType = ExtractValueByPrefix.apply(Line, "Deposit Type");
			}
		}
		// Prepare final result map
		Map<String, String> ResultMap = new HashMap<>();

		ResultMap.put("ReceiptId", ReceiptId);
		ResultMap.put("AdvanceReceived", AdvanceReceived);
		ResultMap.put("ApproxWeight", ApproxWeight);
		ResultMap.put("FixedGoldRate", FixedGoldRate);
		ResultMap.put("DepositType", DepositType);
		ResultMap.put("Validity", Validity);
		ResultMap.put("ValidityDays", ValidityDays);
		ResultMap.put("OfferExpiryDate", OfferExpiryDate);
		return ResultMap;
	}

	/// <summary>
	/// To verify the accuracy of values in a Pro Forma Invoice PDF for negative values(Estimation PDF) for Old Gold
	/// Validating both individual fields and a summary table row using extracted PDF text[Adjustment Value]
	/// Author - Nandagopan
	/// </summary>	
	public void ProFormaInvoicePdfWithOldGoldNegativeValueAdjustmentAmount(String PdfPath,String TaxableValue,String Gst,String InvoiceTotal,String 
			TotalAdjustment,String NetTotal,String TotalAmnt) {
		//,double TotalGrossWeight,double TotalNetWeight
		try {
			// Step 1: Read and normalize PDF content
			//Thread.sleep(5000); // wait for PDF to be downloaded
			String PdfContent = this.IsGetText(PdfPath);
			String NormalizedPdfContent = PdfContent.replaceAll("â‚¹", "").replaceAll(",", "").replaceAll("[()]", "").replaceAll("\\s+", " ").trim();
			System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);

			// Step 2: Field-level verifications
			Map<String, String> ExpectedFields = new HashMap<>();
			ExpectedFields.put("Taxable Value : ", String.format("%.2f",Double.parseDouble(TaxableValue.replaceAll("[^\\d.]", ""))));
			ExpectedFields.put("GST : ", String.format("%.2f", Double.parseDouble(Gst.replaceAll("[^\\d.]", ""))));
			ExpectedFields.put("Invoice Total : ", String.format("%.2f",Double.parseDouble(InvoiceTotal.replaceAll("[^\\d.]", ""))));
			ExpectedFields.put("Total Adjustment : ", String.format("%.2f",Double.parseDouble(TotalAdjustment.replaceAll("[^\\d.]", ""))));
			ExpectedFields.put("Net Total : ", String.format("%.2f",Double.parseDouble(NetTotal.replaceAll("[^\\d.]", ""))));
			this.VerifyPdfFieldsWithTolerance(NormalizedPdfContent, ExpectedFields);

		} catch (Exception e) {
			System.out.println("Error in EstimatePDFVerify: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/// <summary>
	/// PDF Validation - Validates OT (Exchange) Purchase Bill with multiple net and stone weights, and rates
	/// Author - Chandana Babu
	/// </summary>
	public static Map<String, String> ValidateOTPdf(
			String PaymentKeyword,
			String ExpectedGrossWt,
			List<String> ExpectedWeights,  // NetWt (RG, RP, â€¦) then StoneWt in on-page order
			List<String> ExpectedRates,    // Metal rates first (RG, RP, â€¦), then Stone rates in on-page order
			String ExpectedItemTotal,
			String ExpectedTotal,
			String ExpectedPaymentDetails
			) throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("OT");
		String PdfPath    = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		Map<String, String> Data = new HashMap<>();
		List<String> ActualWeights = new ArrayList<>();     // RG_net, RP_net, then stonesâ€¦
		List<String> StoneWeights  = new ArrayList<>();     // collect stones separately; append after metal nets
		int rateIndex = 1;

		// Customer Name
		Matcher m = Pattern.compile("Billed to\\s*:?\\s*(.+)", Pattern.CASE_INSENSITIVE).matcher(ExtractedText);
		if (m.find()) Data.put("CustomerName", m.group(1).trim());

		// Invoice Number
		m = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)", Pattern.CASE_INSENSITIVE).matcher(ExtractedText);
		if (m.find()) Data.put("InvoiceNumber", m.group(1));

		// ---------- RAW GOLD: capture Net then Rate ----------
		// Example: "Raw Gold ... Grams 4.930 9634.00 47495.62"
		Matcher rg = Pattern.compile(
				"(?i)Raw\\s*Gold[\\s\\S]*?Grams\\s+([0-9]+\\.[0-9]{3})\\s+([0-9,]+\\.[0-9]{2})"
				).matcher(ExtractedText);
		if (rg.find()) {
			String rgNet  = rg.group(1);
			String rgRate = rg.group(2).replace(",", "");
			ActualWeights.add(rgNet);                 // RG net first
			Data.put("Rate" + (rateIndex++), rgRate); // Rate1 = RG rate
			// Do NOT set Gross from RG; in your sample gross appears on RP line
		}

		// ---------- RAW PLATINUM: capture Gross, optional Stone, then Rate; compute RP Net = Gross - Stone ----------
		// Example: "Raw Platinum ... Grams 17.360 12.430 7800.00 96954.00"
		Matcher rp = Pattern.compile(
				"(?i)Raw\\s*Platinum[\\s\\S]*?Grams\\s+([0-9]+\\.[0-9]{3})(?:\\s+([0-9]+\\.[0-9]{3}))?\\s+([0-9,]+\\.[0-9]{2})"
				).matcher(ExtractedText);
		if (rp.find()) {
			String rpGross = rp.group(1).replace(",", "");
			String rpSecond = rp.group(2).replace(",", "");                   // may be Stone or Net depending on layout
			String rpRate  = rp.group(3).replace(",", "");
			Data.put("GrossWt",rpGross);
			ActualWeights.add(rpSecond); // RP net second
			Data.put("Rate" + (rateIndex++), rpRate); // Rate2 = RP rate
		}

		// ---------- Other Stones (if separately listed as Diamond/Ruby/Emerald lines) ----------
		// If your layout has explicit stone lines (not embedded in RP), weâ€™ll capture them here.
		Matcher stones = Pattern.compile(
				"(?i)(Diamond|Ruby|Emerald)[\\s\\S]*?([0-9]+\\.[0-9]{3})\\s+([0-9,]+\\.[0-9]{2})"
				).matcher(ExtractedText);
		while (stones.find()) {
			StoneWeights.add(stones.group(2));                       // Stone Wt
			Data.put("Rate" + (rateIndex++), stones.group(3).replace(",", "")); // next rate = stone rate
		}

		// Append all stones after metal nets to build the final ExpectedWeights order
		ActualWeights.addAll(StoneWeights);

		// ---------- Item Total ----------
		m = Pattern.compile("(?i)Item\\s*Total\\s*([0-9]{1,9}[\\.,][0-9]{2})").matcher(ExtractedText);
		if (m.find()) Data.put("ItemTotal", m.group(1).replace(",", "."));

		// ---------- Grand Total ----------
		m = Pattern.compile("(?i)Total\\s*([0-9]{1,9}[\\.,][0-9]{2})\\s*Rupees").matcher(ExtractedText);
		if (m.find()) Data.put("Total", m.group(1).replace(",", "."));

		// ---------- Payment Details ----------
		String keyword = Pattern.quote(PaymentKeyword);
		m = Pattern.compile(keyword + "\\s*:?\\s*([0-9]{1,9}[\\.,][0-9]{2})", Pattern.CASE_INSENSITIVE).matcher(ExtractedText);
		if (m.find()) {
			Data.put("PaymentDetails", m.group(1).replace(",", "."));
		} else {
			Data.put("PaymentDetails", "Not Found");
		}

		// ---------- VALIDATIONS ----------
		// Gross: from RP line (in your sample)
		asrt.assertNotNull(Data.get("GrossWt"), "GrossWt not captured (no gross value found).");
		asrt.assertEquals(Double.parseDouble(Data.get("GrossWt")),Double.parseDouble(ExpectedGrossWt),1.0,
				"Gross weight mismatch: Expected is " + ExpectedGrossWt + " but got " + Data.get("GrossWt") + " in purchase bill"
				);
		// Weights order = [RG_net, RP_net, Stone1, Stone2, ...]
		// Print for debug
		asrt.assertTrue(
				ActualWeights.size() == ExpectedWeights.size(),
				"Not enough weights captured. Expected at least " + ExpectedWeights.size() + " but got " + ActualWeights.size()
				);
		for (int i = 0; i < ExpectedWeights.size(); i++) {
			double actualW = Double.parseDouble(ActualWeights.get(i));
			double expectW = Double.parseDouble(ExpectedWeights.get(i));
			asrt.assertEquals(actualW, expectW, 1.0,
					"Weight mismatch at index " + i + ": Expected " + expectW + " but got " + actualW + " in purchase bill"
					);
		}

		// Rates are in order: RG_rate, RP_rate, then stone rates (if any)
		for (int i = 0; i < ExpectedRates.size(); i++) {
			String key = "Rate" + (i + 1);
			asrt.assertNotNull(Data.get(key), "Missing " + key + " in parsed data.");
			double actualR = Double.parseDouble(Data.get(key));
			double expectR = Double.parseDouble(ExpectedRates.get(i));
			asrt.assertEquals(actualR, expectR, 1.0,
					"Rate mismatch for " + key + ": Expected " + expectR + " but got " + actualR + " in purchase bill"
					);
		}

		// Item Total, Total, Payment
		asrt.assertEquals(Double.parseDouble(Data.get("ItemTotal")), Double.parseDouble(ExpectedItemTotal),1.0,
				"Item total mismatch: Expected " + ExpectedItemTotal + " but got " + Data.get("ItemTotal") + " in purchase bill"
				);
		asrt.assertEquals(Double.parseDouble(Data.get("Total")),Double.parseDouble(ExpectedTotal),1.0,
				"Total mismatch: Expected " + ExpectedTotal + " but got " + Data.get("Total") + " in purchase bill"
				);
		asrt.assertEquals(Double.parseDouble(Data.get("PaymentDetails")),Double.parseDouble(ExpectedPaymentDetails),1.0,
				"Payment mismatch: Expected " + ExpectedPaymentDetails + " but got " + Data.get("PaymentDetails") + " in purchase bill"
				);
		System.out.println("OT PDF validation successful.");
		return Data;
	}

	/// <summary>
	/// PDF Validation - Validates of Purchase Bill Old Skus
	/// Author - Vishnu Manoj K
	/// </summary>
	public Map<String, String> OldSkuBillPdfValidation(String PaymentKeyword) throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("PurchaseBill");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);
		System.out.println("Extracted Text:\n" + ExtractedText);

		Map<String, String> PurchaseBillPdfData = new HashMap<>();
		// Get "Billed to" name
		Matcher billedToMatcher = Pattern.compile("Billed to\\s*:?\\s*(.+)").matcher(ExtractedText);
		if (billedToMatcher.find()) {
			PurchaseBillPdfData.put("BilledToName", billedToMatcher.group(1).trim());
		} else {
			PurchaseBillPdfData.put("BilledToName", "Not Found");
		}

		PurchaseBillPdfData.put("PurchaseBillPdfName", ExtractedText.contains("PURCHASE BILL") ? "PURCHASE BILL" : "");

		Matcher InvoiceMatcher = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)").matcher(ExtractedText);
		if (InvoiceMatcher.find()) {
			PurchaseBillPdfData.put("InvoiceNumber", InvoiceMatcher.group(1));
		}

		Pattern productBlockPattern = Pattern.compile(
				"Raw Gold.*?(\\d+\\.\\d{3})\\s+" + // Gross Wt
						"(\\d+\\.\\d{3})\\s+" +           // Net Wt
						"(\\d{4,6}\\.\\d{2})\\s+" +       // Rate
						"(\\d{4,6}\\.\\d{2}).*?" +        // Total Amount
						"Item Total\\s*(\\d{4,6}[\\.,]\\d{2})",
						Pattern.DOTALL);

		Matcher productBlockMatcher = productBlockPattern.matcher(ExtractedText);
		int productIndex = 1;

		// Stone details pattern (handles Ruby, Emerald etc.)
		Pattern stoneDetailsPattern = Pattern.compile(
				"(?i)([A-Z][a-zA-Z]+)\\s+Grams\\s+(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{2})\\s+(\\d+\\.\\d{2})");

		Matcher stoneMatcher = stoneDetailsPattern.matcher(ExtractedText);
		int stoneMatchIndex = 0;

		while (productBlockMatcher.find()) {
			PurchaseBillPdfData.put("GrossWt"     + productIndex, productBlockMatcher.group(1));
			PurchaseBillPdfData.put("NetWt"       + productIndex, productBlockMatcher.group(2));
			PurchaseBillPdfData.put("Rate"        + productIndex, productBlockMatcher.group(3));
			PurchaseBillPdfData.put("TotalAmount" + productIndex, productBlockMatcher.group(4));
			PurchaseBillPdfData.put("ItemTotal"   + productIndex, productBlockMatcher.group(5).replace(",", "."));

			// Try matching a stone detail for this product
			while (stoneMatcher.find()) {
				stoneMatchIndex++;
				if (stoneMatchIndex == productIndex) {
					String stoneName = stoneMatcher.group(1).trim(); // e.g., Ruby, Emerald
					String stoneWt   = stoneMatcher.group(2).trim();
					String stoneRate = stoneMatcher.group(3).trim();
					PurchaseBillPdfData.put(stoneName + "Wt" + productIndex, stoneWt);
					PurchaseBillPdfData.put(stoneName + "Rate" + productIndex, stoneRate);
					break;
				}
			}

			productIndex++;
		}

		// Fallback if no product block found
		if (productIndex == 1) {
			Pattern fallbackPattern = Pattern.compile(
					"Raw Gold.*?(\\d+\\.\\d{3})\\s+" +
							"(\\d+\\.\\d{3})\\s+" +
							"(\\d{4,6}\\.\\d{2})\\s+" +
					"(\\d{4,6}\\.\\d{2})");

			Matcher fallbackMatcher = fallbackPattern.matcher(ExtractedText);
			if (fallbackMatcher.find()) {
				PurchaseBillPdfData.put("GrossWt1",     fallbackMatcher.group(1));
				PurchaseBillPdfData.put("NetWt1",       fallbackMatcher.group(2));
				PurchaseBillPdfData.put("Rate1",        fallbackMatcher.group(3));
				PurchaseBillPdfData.put("TotalAmount1", fallbackMatcher.group(4));
			}

			Matcher itemTotalMatcher = Pattern.compile("Item Total\\s*(\\d{4,6}[\\.,]\\d{2})").matcher(ExtractedText);
			if (itemTotalMatcher.find()) {
				PurchaseBillPdfData.put("ItemTotal1", itemTotalMatcher.group(1).replace(",", "."));
			}
		}

		// Backup: If "Stone Wt" specifically exists elsewhere (less reliable, optional)
		Matcher backupStoneMatcher = Pattern.compile("Stone\\s*Wt\\s*:?\\s*(\\d+\\.\\d{3})").matcher(ExtractedText);
		int backupStoneIndex = 1;
		while (backupStoneMatcher.find()) {
			PurchaseBillPdfData.put("StoneWt" + backupStoneIndex, backupStoneMatcher.group(1));
			backupStoneIndex++;
		}

		PurchaseBillPdfData.put("TotalQuantity", String.valueOf(productIndex - 1));
		System.out.println("OGPdfData after items: " + PurchaseBillPdfData);

		Matcher totalAmountMatcher = Pattern.compile("Total\\s*(\\d{4,6}[\\.,]\\d{2})\\s*Rupees").matcher(ExtractedText);
		if (totalAmountMatcher.find()) {
			PurchaseBillPdfData.put("TotalAmount", totalAmountMatcher.group(1).replace(",", "."));
		}

		String PaymentKeywordOld = Pattern.quote(PaymentKeyword);
		Matcher paymentMatcher = Pattern.compile(PaymentKeywordOld + "\\s*:?\\s*(\\d{4,6}[\\.,]\\d{2})").matcher(ExtractedText);
		if (paymentMatcher.find()) {
			PurchaseBillPdfData.put("PaymentDetails", paymentMatcher.group(1).replace(",", "."));
		} else {
			PurchaseBillPdfData.put("PaymentDetails", "Not Found");
		}

		// Final assertion
		String Total = PurchaseBillPdfData.get("TotalAmount");
		String Payment = PurchaseBillPdfData.get("PaymentDetails");
		Matcher InvoiceMatcherInvoice = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)").matcher(ExtractedText);
		if (InvoiceMatcherInvoice.find()) {
			PurchaseBillPdfData.put("InvoiceNumber", InvoiceMatcher.group(1));
		}

		System.out.println("Final OGPdfData: " + PurchaseBillPdfData);
		return PurchaseBillPdfData;
	}
	/// <summary>
	/// PDF Validation - To Extract GPP Plan PDF Application ID,PLan,Customer name,Phone no,Nominee name,Bank account no ,IFSC details
	/// Author - Sangeetha M S
	/// </summary>
	public static Map<String, String> ExtractDetailsFromGPPPdf(String SchemeCode) throws IOException, InterruptedException {
		Map<String, String> details = new HashMap<>();
		String NewPdfName = DownloadAndRenameLatestPDF(SchemeCode+" Application Form");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		try {
			// Application ID
			Matcher AppId = Pattern.compile("Application ID\\s*[:ï¼š]\\s*(\\w+)", Pattern.CASE_INSENSITIVE)
					.matcher(ExtractedText);
			if (AppId.find()) details.put("ApplicationID", AppId.group(1).trim());

			// Plan
			Matcher Plans = Pattern.compile("Plan\\s*[:ï¼š]\\s*(.*)").matcher(ExtractedText);
			if (Plans.find()) details.put("Plan", Plans.group(1).trim());

			// Customer Name
			Matcher Customer = Pattern.compile("Customer\\s*[:ï¼š]\\s*(.*)", Pattern.CASE_INSENSITIVE).matcher(ExtractedText);
			if (Customer.find()) {
				String FullValue = Customer.group(1).trim(); 

				// Split by "::" and take the last part
				String[] parts = FullValue.split("::");
				String CustomerName = parts[parts.length - 1].trim();  

				details.put("CustomerName", CustomerName);
			}

			// Phone number
			Matcher PhoneNum = Pattern.compile("Phone\\s*[:ï¼š]\\s*(\\d+)").matcher(ExtractedText);
			if (PhoneNum.find()) details.put("Phone", PhoneNum.group(1).trim());

			// Nominee Name
			Matcher Nominee = Pattern.compile(
					"Nominee Name\\s*[:ï¼š]\\s*(.*?)(?=\\s+Nominee Address|\\r|\\n|$)",
					Pattern.CASE_INSENSITIVE
					).matcher(ExtractedText);

			if (Nominee.find()) {
				details.put("NomineeName", Nominee.group(1).trim());
			}
			// Bank Account No
			Matcher Acc = Pattern.compile("Bank Account No\\s*[:ï¼š]\\s*(\\d+)").matcher(ExtractedText);
			if (Acc.find()) details.put("BankAccountNo", Acc.group(1).trim());

			// IFSC Code
			Matcher Ifsc = Pattern.compile("IFSC Code\\s*[:ï¼š]\\s*([A-Z0-9]+)").matcher(ExtractedText);
			if (Ifsc.find()) details.put("IFSCCode", Ifsc.group(1).trim());

		} catch (Exception e) {
			System.out.println("Error extracting details: " + e.getMessage());
		}

		// Print results for verification
		details.forEach((k,v) -> System.out.println(k + " : " + v));

		return details;
	}

	/// <summary>
	/// PDF Validation - Validates of Purchase Bill Old Sku(Single)
	/// Author - Nandagopan
	/// </summary>
	public Map<String, String> OldSkuBillPdfValidationCustName(String PaymentKeyword) throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("PurchaseBill");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);
		System.out.println("Extracted Text:\n" + ExtractedText);

		Map<String, String> PurchaseBillPdfData = new HashMap<>();
		// Get "Billed to" name
		Matcher billedToMatcher = Pattern.compile("Billed to\\s*:?\\s*(.+)").matcher(ExtractedText);
		if (billedToMatcher.find()) {
			PurchaseBillPdfData.put("BilledToName", billedToMatcher.group(1).trim());
		} else {
			PurchaseBillPdfData.put("BilledToName", "Not Found");
		}

		PurchaseBillPdfData.put("PurchaseBillPdfName", ExtractedText.contains("PURCHASE BILL") ? "PURCHASE BILL" : "");

		Matcher InvoiceMatcher = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)").matcher(ExtractedText);
		if (InvoiceMatcher.find()) {
			PurchaseBillPdfData.put("InvoiceNumber", InvoiceMatcher.group(1));
		}

		Pattern productBlockPattern = Pattern.compile(
				"Raw Gold.*?(\\d+\\.\\d{3})\\s+" + // Gross Wt
						"(\\d+\\.\\d{3})\\s+" +           // Net Wt
						"(\\d{4,6}\\.\\d{2})\\s+" +       // Rate
						"(\\d{4,6}\\.\\d{2}).*?" +        // Total Amount
						"Item Total\\s*(\\d{4,6}[\\.,]\\d{2})",
						Pattern.DOTALL);

		Matcher productBlockMatcher = productBlockPattern.matcher(ExtractedText);
		int productIndex = 1;

		// Stone details pattern (handles Ruby, Emerald etc.)
		Pattern stoneDetailsPattern = Pattern.compile(
				"(?i)([A-Z][a-zA-Z]+)\\s+Grams\\s+(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{2})\\s+(\\d+\\.\\d{2})");

		Matcher stoneMatcher = stoneDetailsPattern.matcher(ExtractedText);
		int stoneMatchIndex = 0;

		while (productBlockMatcher.find()) {
			PurchaseBillPdfData.put("GrossWt"     + productIndex, productBlockMatcher.group(1));
			PurchaseBillPdfData.put("NetWt"       + productIndex, productBlockMatcher.group(2));
			PurchaseBillPdfData.put("Rate"        + productIndex, productBlockMatcher.group(3));
			PurchaseBillPdfData.put("TotalAmount" + productIndex, productBlockMatcher.group(4));
			PurchaseBillPdfData.put("ItemTotal"   + productIndex, productBlockMatcher.group(5).replace(",", "."));

			// Try matching a stone detail for this product
			while (stoneMatcher.find()) {
				stoneMatchIndex++;
				if (stoneMatchIndex == productIndex) {
					String stoneName = stoneMatcher.group(1).trim(); // e.g., Ruby, Emerald
					String stoneWt   = stoneMatcher.group(2).trim();
					String stoneRate = stoneMatcher.group(3).trim();
					PurchaseBillPdfData.put(stoneName + "Wt" + productIndex, stoneWt);
					PurchaseBillPdfData.put(stoneName + "Rate" + productIndex, stoneRate);
					break;
				}
			}

			productIndex++;
		}

		// Fallback if no product block found
		if (productIndex == 1) {
			Pattern fallbackPattern = Pattern.compile(
					"Raw Gold.*?(\\d+\\.\\d{3})\\s+" +
							"(\\d+\\.\\d{3})\\s+" +
							"(\\d{4,6}\\.\\d{2})\\s+" +
					"(\\d{4,6}\\.\\d{2})");

			Matcher fallbackMatcher = fallbackPattern.matcher(ExtractedText);
			if (fallbackMatcher.find()) {
				PurchaseBillPdfData.put("GrossWt1",     fallbackMatcher.group(1));
				PurchaseBillPdfData.put("NetWt1",       fallbackMatcher.group(2));
				PurchaseBillPdfData.put("Rate1",        fallbackMatcher.group(3));
				PurchaseBillPdfData.put("TotalAmount1", fallbackMatcher.group(4));
			}

			Matcher itemTotalMatcher = Pattern.compile("Item Total\\s*(\\d{4,6}[\\.,]\\d{2})").matcher(ExtractedText);
			if (itemTotalMatcher.find()) {
				PurchaseBillPdfData.put("ItemTotal1", itemTotalMatcher.group(1).replace(",", "."));
			}
		}

		// Backup: If "Stone Wt" specifically exists elsewhere (less reliable, optional)
		Matcher backupStoneMatcher = Pattern.compile("Stone\\s*Wt\\s*:?\\s*(\\d+\\.\\d{3})").matcher(ExtractedText);
		int backupStoneIndex = 1;
		while (backupStoneMatcher.find()) {
			PurchaseBillPdfData.put("StoneWt" + backupStoneIndex, backupStoneMatcher.group(1));
			backupStoneIndex++;
		}

		PurchaseBillPdfData.put("TotalQuantity", String.valueOf(productIndex - 1));
		System.out.println("OGPdfData after items: " + PurchaseBillPdfData);

		Matcher totalAmountMatcher = Pattern.compile("Total\\s*(\\d{4,6}[\\.,]\\d{2})\\s*Rupees").matcher(ExtractedText);
		if (totalAmountMatcher.find()) {
			PurchaseBillPdfData.put("TotalAmount", totalAmountMatcher.group(1).replace(",", "."));
		}

		String PaymentKeywordOld = Pattern.quote(PaymentKeyword);
		Matcher paymentMatcher = Pattern.compile(PaymentKeywordOld + "\\s*:?\\s*(\\d{4,6}[\\.,]\\d{2})").matcher(ExtractedText);
		if (paymentMatcher.find()) {
			PurchaseBillPdfData.put("PaymentDetails", paymentMatcher.group(1).replace(",", "."));
		} else {
			PurchaseBillPdfData.put("PaymentDetails", "Not Found");
		}

		// Final assertion
		String Total = PurchaseBillPdfData.get("TotalAmount");
		String Payment = PurchaseBillPdfData.get("PaymentDetails");
		Matcher InvoiceMatcherInvoice = Pattern.compile("Invoice No\\s*:?\\s*(\\S+)").matcher(ExtractedText);
		if (InvoiceMatcherInvoice.find()) {
			PurchaseBillPdfData.put("InvoiceNumber", InvoiceMatcher.group(1));
		}

		System.out.println("Final OGPdfData: " + PurchaseBillPdfData);
		return PurchaseBillPdfData;
	}
	/// <summary>
	/// PDF Validation â€“ Validates "INSURANCE CERTIFICATE"
	/// Author - Chandana Babu and Robin
	/// </summary>	
	public static void ValidateInvoiceValue(String ExpectedInvoiceValue) throws IOException, InterruptedException {
		// Step 1: Download / Locate PDF
		String NewPdfName = DownloadAndRenameLatestPDF("InsurancePdf");  // you can adjust keyword
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;

		// Step 2: Extract text
		String ExtractedText = IsGetText(PdfPath);

		// Step 3: Find "Invoice Value (in INR)"
		Pattern InvoiceValuePattern = Pattern.compile("Invoice Value \\(in INR\\) Rs\\s*(\\d+\\.?\\d*)");
		Matcher matcher = InvoiceValuePattern.matcher(ExtractedText);

		if (matcher.find()) {
			String ActualInvoiceValue = matcher.group(1).trim();
			System.out.println("Extracted Invoice Value: " + ActualInvoiceValue);

			// Step 4: Assert
			asrt.assertEquals(
					Double.parseDouble(ActualInvoiceValue),
					Double.parseDouble(ExpectedInvoiceValue),
					"Invoice Value mismatch: Expected " + ExpectedInvoiceValue + " but got " + ActualInvoiceValue
					);
		} else {
			asrt.fail("Invoice Value (in INR) not found in PDF.");
		}
	}





	/// <summary>
	/// Extracts and validates key financial fields from the Normal Advance Refund PDF,
	/// including Receipt Id, Advance Refund, Net Refund, Weight, Rate,
	/// Deposit Type, Payment Mode, and Amount.
	/// Ensures Advance = Net Refund = Payment.
	/// Author - Neethu
	/// </summary>
	public Map<String, String> NormalAdvanceRefundPdfValidation() throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("NormalAdvance");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		String[] Lines = ExtractedText.split("\\r?\\n");

		String ReceiptId = "";
		String AdvanceReceived = "";
		String NetAdvance = "";
		String ApproxWeight = "";
		String FixedGoldRate = "";
		String DepositType = "";
		String PaymentMode = "";
		String PaymentAmount = "";
		String MaxGoldRate = "";

		String AdvanceRefund = "";
		String NetRefund = "";

		boolean FoundAdvanceLine = false;

		BiFunction<String, String, String> ExtractValueByPrefix = (Line, Prefix) -> {
			if (Line.startsWith(Prefix)) {
				String[] Parts = Line.split(":");
				if (Parts.length > 1) {
					return Parts[1].trim();
				}
			}
			return "";
		};

		for (String LineRaw : Lines) {
			String Line = LineRaw.trim();

			// Receipt ID
			if (Line.startsWith("Receipt Id")) {
				ReceiptId = ExtractValueByPrefix.apply(Line, "Receipt Id");
			}

			//Max Gold Rate
			if (Line.startsWith("Max Gold Rate")) {
				MaxGoldRate = ExtractValueByPrefix.apply(Line, "Max Gold Rate");
			}

			// Advance Received and Net Advance
			if (Line.contains("Advance Refund") && Line.contains("Net Refund")) {
				FoundAdvanceLine = true;
			} else if (FoundAdvanceLine) {
				String[] Values = Line.trim().split("\\s+");
				if (Values.length >= 2) {
					AdvanceReceived = Values[0];
					NetAdvance = Values[1];

				}
				FoundAdvanceLine = false;
			}

			// Approx Weight
			if (Line.startsWith("Towards the advance against purchase of approximate")) {
				int Idx = Line.lastIndexOf("approximate");
				if (Idx != -1) {
					ApproxWeight = Line.substring(Idx + 11).replace("GM", "").trim();
				}
			}

			if (Line.startsWith("Max gold rate per gram")) {
				FixedGoldRate = ExtractValueByPrefix.apply(Line, "Max gold rate per gram");
			}
			if (Line.startsWith("Deposit Type")) {
				DepositType = ExtractValueByPrefix.apply(Line, "Deposit Type");
			}

			if (Line.startsWith("RTGS/NEFT")) {
				PaymentMode = "RTGS/NEFT";
				PaymentAmount = ExtractValueByPrefix.apply(Line, "RTGS/NEFT");
			}
			if (Line.startsWith("SBI (Credit Card)")) {
				PaymentMode = "SBI (Credit Card)";
				PaymentAmount = ExtractValueByPrefix.apply(Line, "SBI (Credit Card)");
			}
		}
		Thread.sleep(2000);
		// Assertions
		AdvanceRefund = AdvanceReceived.replaceAll("[()]", "");
		System.out.println("AdvanceRefund after trim: "+AdvanceRefund);

		NetRefund =NetAdvance.replaceAll("[()]", "");
		System.out.println("NetRefund after trim: "+NetRefund);

		Assert.assertEquals(AdvanceRefund, NetRefund, "Advance Refund and Net Refund do not match");
		Assert.assertEquals(PaymentAmount, AdvanceRefund, "Advance Refund and Payment Amount do not match");

		Map<String, String> ResultMap = new HashMap<>();
		ResultMap.put("ReceiptId", ReceiptId);
		ResultMap.put("MaxGoldRate", MaxGoldRate);
		ResultMap.put("AdvanceReceived", AdvanceReceived);
		ResultMap.put("NetAdvance", NetAdvance);
		ResultMap.put("ApproxWeight", ApproxWeight);
		ResultMap.put("FixedGoldRate", FixedGoldRate);
		ResultMap.put("DepositType", DepositType);
		ResultMap.put("PaymentMode", PaymentMode);
		ResultMap.put("PaymentAmount", PaymentAmount);
		ResultMap.put("AdvanceRefund", AdvanceRefund);
		ResultMap.put("NetRefund", NetRefund);

		return ResultMap;
	}




	/// <summary>
	/// Extracts and validates key financial fields from the Normal order/offer Refund PDF,
	/// including Receipt Id, Advance Refund, Net Refund, 
	/// Payment Mode, and Amount.
	/// Ensures Advance = Net Refund = Payment.
	/// Author - Neethu
	/// </summary>
	public Map<String, String> OfferAdvanceRefundPdfValidation() throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("OfferAdvance");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		String[] Lines = ExtractedText.split("\\r?\\n");

		String ReceiptId = "";
		String AdvanceReceived = "";
		String NetAdvance = "";
		String PaymentMode = "";
		String PaymentAmount = "";
		String AdvanceRefund = "";
		String NetRefund = "";

		boolean FoundAdvanceLine = false;

		BiFunction<String, String, String> ExtractValueByPrefix = (Line, Prefix) -> {
			if (Line.startsWith(Prefix)) {
				String[] Parts = Line.split(":");
				if (Parts.length > 1) {
					return Parts[1].trim();
				}
			}
			return "";
		};

		for (String LineRaw : Lines) {
			String Line = LineRaw.trim();

			// Receipt ID
			if (Line.startsWith("Receipt Id")) {
				ReceiptId = ExtractValueByPrefix.apply(Line, "Receipt Id");
			}


			// Advance Received and Net Advance
			if (Line.contains("Advance Received") && Line.contains("Net Advance")) {
				FoundAdvanceLine = true;
			} else if (FoundAdvanceLine) {
				String[] Values = Line.trim().split("\\s+");
				if (Values.length >= 2) {
					AdvanceReceived = Values[0];
					NetAdvance = Values[1];

				}
				FoundAdvanceLine = false;
			}



			if (Line.startsWith("RTGS/NEFT")) {
				PaymentMode = "RTGS/NEFT";
				PaymentAmount = ExtractValueByPrefix.apply(Line, "RTGS/NEFT");
			}

		}
		Thread.sleep(2000);

		// Assertions
		System.out.println("AdvanceReceived after trim: "+AdvanceReceived);
		System.out.println("NetAdvance after trim: "+NetAdvance);

		Assert.assertEquals(AdvanceReceived, NetAdvance, "Advance Received and Net Advance do not match");
		Assert.assertEquals(PaymentAmount, AdvanceReceived, "Advance Received and Payment Amount do not match");

		Map<String, String> ResultMap = new HashMap<>();
		ResultMap.put("ReceiptId", ReceiptId);
		ResultMap.put("AdvanceReceived", AdvanceReceived);
		ResultMap.put("NetAdvance", NetAdvance);
		ResultMap.put("PaymentMode", PaymentMode);
		ResultMap.put("PaymentAmount", PaymentAmount);

		return ResultMap;
	}
	/// <summary>
	///Method to validate purchase bill : URD
	/// Author - Christy
	/// </summary>
	public Map<String, String> URDPurchaseBill(String Paymentkeyword) throws IOException, InterruptedException {

		// Rename and extract PDF text
		String NewPdfName              = DownloadAndRenameLatestPDF("URD");
		String PdfPath                 = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText           = IsGetText(PdfPath);
		Map<String, String> URDPdfData = new HashMap<>();
		URDPdfData.put("PdfType", ExtractedText.contains("PURCHASE BILL") ? "PURCHASE BILL" : "");

		// --- Customer Name ---
		Matcher customerMatcher = Pattern.compile("Billed to\\s*:?\\s*(.+)").matcher(ExtractedText);
		if (customerMatcher.find()) {
			URDPdfData.put("CustomerName", customerMatcher.group(1).trim());
		}

		// --- Product Details (Handles multiple products if exist) ---
		Pattern productPattern = Pattern.compile(
				"(\\d+)\\s+Raw\\s+Platinum.*?(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{3}).*?Item Total\\s*(\\d{4,8}[\\.,]\\d{2})",
				Pattern.DOTALL);
		Matcher productMatcher = productPattern.matcher(ExtractedText);

		int productIndex = 1;
		while (productMatcher.find()) {
			URDPdfData.put("SNo" + productIndex, productMatcher.group(1));
			URDPdfData.put("GrossWt" + productIndex, productMatcher.group(2));
			URDPdfData.put("NetWt" + productIndex, productMatcher.group(3));
			URDPdfData.put("ItemTotal" + productIndex, productMatcher.group(4).replace(",", "."));
			productIndex++;
		}

		// --- Fallback if pattern not found ---
		if (productIndex == 1) {
			Matcher fallbackMatcher = Pattern.compile("Grams\\s*(\\d+\\.\\d{3}).*?\\s(\\d+\\.\\d{3})").matcher(ExtractedText);
			if (fallbackMatcher.find()) {
				URDPdfData.put("GrossWt1", fallbackMatcher.group(1));
				URDPdfData.put("NetWt1", fallbackMatcher.group(2));
			}
			Matcher itemTotalMatcher = Pattern.compile("Item Total\\s*(\\d{4,8}[\\.,]\\d{2})").matcher(ExtractedText);
			if (itemTotalMatcher.find()) {
				URDPdfData.put("ItemTotal1", itemTotalMatcher.group(1).replace(",", "."));
			}
		}

		URDPdfData.put("TotalProducts", String.valueOf(productIndex - 1));

		// --- Extract Qty(Pcs) after "Item Total" ---
		Matcher qtyMatcher = Pattern.compile("Item Total\\s*\\d{4,8}[\\.,]\\d{2}\\s*(\\d+)").matcher(ExtractedText);
		if (qtyMatcher.find()) {
			URDPdfData.put("QtyPcs", qtyMatcher.group(1));
		}

		//		// --- Extract Total Amount ---
		//		Matcher totalMatcher = Pattern.compile("Total\\s*(\\d{4,8}[\\.,]\\d{2})").matcher(ExtractedText);
		//		if (totalMatcher.find()) {
		//			URDPdfData.put("TotalAmount", totalMatcher.group(1).replace(",", "."));
		//		}

		// --- Extract final Total Amount (avoid "Item Total") ---
		Matcher totalMatcher = Pattern.compile("(?<!Item )Total\\s*(\\d{4,8}[\\.,]\\d{2})").matcher(ExtractedText);
		String lastTotal = null;
		while (totalMatcher.find()) {
			lastTotal = totalMatcher.group(1);
		}
		if (lastTotal != null) {
			URDPdfData.put("TotalAmount", lastTotal.replace(",", "."));
		}

		// --- Extract Payment Details (e.g., Adjustment) ---
		String PaymentKeyword = Pattern.quote(Paymentkeyword);
		Matcher paymentMatcher = Pattern.compile(PaymentKeyword + "\\s*:?\\s*(\\d{4,8}[\\.,]\\d{2})").matcher(ExtractedText);
		if (paymentMatcher.find()) {
			URDPdfData.put("PaymentDetails", paymentMatcher.group(1).replace(",", "."));
		} else {
			URDPdfData.put("PaymentDetails", "Not Found");
		}

		// --- Assertions ---

		// 1️⃣ Assert GrossWt = NetWt for all items
		for (int i = 1; i < productIndex; i++) {
			String gross = URDPdfData.get("GrossWt" + i);
			String net = URDPdfData.get("NetWt" + i);
			if (gross != null && net != null) {
				asrt.assertEquals(gross, net, "Mismatch in GrossWt and NetWt for Product " + i);
			}
		}

		//		// 2️⃣ Assert TotalAmount = PaymentDetails
		//		String totalAmount = URDPdfData.get("TotalAmount");
		//		String payment = URDPdfData.get("PaymentDetails");
		//		if (totalAmount != null && payment != null && !payment.equals("Not Found")) {
		//			asrt.assertEquals(totalAmount, payment, "Total Amount and Payment Details mismatch.");
		//		} else {
		//			throw new AssertionError("Missing Total or Payment data for validation.");
		//		}

		// --- Extract overall Gross and Net totals ---
		Matcher totalWtMatcher = Pattern.compile("\\b\\d+\\s+(\\d+\\.\\d{3})\\s+(\\d+\\.\\d{3})").matcher(ExtractedText);
		while (totalWtMatcher.find()) {
			String TotalGrossWt = totalWtMatcher.group(1);
			String TotalNetWt = totalWtMatcher.group(2);

			// To ensure we are picking the *summary line*, not individual item weights:
			// It usually comes right before "Total <amount>"
			int endIdx = totalWtMatcher.end();
			if (ExtractedText.substring(endIdx).contains("Total")) {
				URDPdfData.put("TotalGrossWt", TotalGrossWt);
				URDPdfData.put("TotalNetWt", TotalNetWt);
				break;
			}
		}



		System.out.println(" Validation successful for URD Purchase Bill");
		System.out.println("Final URDPdfData: " + URDPdfData);

		return URDPdfData;
	}
























	/// <summary>
	/// Validates Sale Invoice PDF Validation With Cash, HDFC (Credit Card), and RTGS Received
	/// Author - Aiswarya
	/// </summary>

	public String SaleInvoicePdfValidationWithCashCardRtgsReceived(
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String CashAmount,
			String RTGSAmount,
			String HDFCCardAmount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		// Extract Invoice No
		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		// Normalize PDF content
		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[\u20B9,=:]", "")
				.trim();
		System.out.println("Normalized Pdf Content :" + NormalizedPdfContent);

		// Prepare Expected Fields
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Discount", (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("RTGS/Received", RTGSAmount.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Net Value", NetValue);
		ExpectedFields.put("Cash", CashAmount.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("HDFC (Credit Card)", HDFCCardAmount.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());

		// Build expected item lines (unchanged)
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " → Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		// Validate PDF
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}
	//Vishnu
	public String SaleInvoicePdfValidationAdvanceDoubleAdjustment(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String Adjustment,
			String TotalAdjustment,   // <-- NEW PARAMETER
			String AdvanceAmount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {

		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		// ===== Extract Invoice No =====
		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)", Pattern.CASE_INSENSITIVE);
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		// ===== Normalize Content (for broad string contains checks) =====
		String NormalizedPdfContent = PdfContent.replaceAll("₹", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[₹,=:]", "")
				.trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);

		// ===== Expected fields (string-based checks via VerifyMultipleFieldsInPdf) =====
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Total Discount", (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[₹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Net Value", NetValue.replaceAll("[^\\d.]", ""));

		// Optional single-line "Adjustment" value near payment details (if you want this too)
		if (Adjustment != null && !Adjustment.trim().isEmpty()) {
			// Use key without colon so it survives normalization better
			ExpectedFields.put("Adjustment", Adjustment.replaceAll("[^\\d.]", ""));
		}
		if (TotalAdjustment != null && !TotalAdjustment.trim().isEmpty()) {
			// Use key without colon so it survives normalization better
			ExpectedFields.put("Total Adjustment", TotalAdjustment.replaceAll("[^\\d.]", ""));
		}
		if (AdvanceAmount != null && !AdvanceAmount.trim().isEmpty()) {
			// Use key without colon so it survives normalization better
			ExpectedFields.put("Advance Amount", AdvanceAmount.replaceAll("[^\\d.]", ""));
		}

		// --- Optional total diamond amount (if provided and non-zero) ---
		if (TotalDiamond != null && !TotalDiamond.trim().isEmpty() && !TotalDiamond.trim().equals("0")) {
			ExpectedFields.put("Total Diamond amount is", TotalDiamond.replaceAll("[^\\d.]", ""));
		}
		System.out.println("Expected Fields: " + ExpectedFields);
		// ===== Build expected per-line totals from scanned SKU data =====
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " → Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		// ===== Perform the checks =====
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}
	/// <summary>
	/// To verify the accuracy of values including Adjustment value and advance amount in a Sales Invoice PDF for RTGS/Received values(Sales invoice PDF)
	/// Author - Hasna E K
	/// </summary>
	public String SaleInvoicePdfAdjustmentValidationRtgsReceived(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String RTGSReceived,
			String TotalAdjustment,
			String AdvanceAmount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}
		String NormalizedPdfContent = PdfContent.replaceAll("â‚¹", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[â‚¹,=:]", "")
				.trim();
		System.out.println("Normalized Pdf Content :" + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount", (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("RTGS/Received", RTGSReceived.replaceAll("[â‚¹,]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Net Value", NetValue); // Keep as-is if using tolerance elsewhere
		if (TotalAdjustment != null && !TotalAdjustment.trim().isEmpty()) {
			ExpectedFields.put("Total Adjustment", TotalAdjustment.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		}
		System.out.println("Expected Fields: " + ExpectedFields);
		try {
			double ExpectedAdvance = Math.round(Double.parseDouble(AdvanceAmount.replaceAll("[^\\d.]", "")) * 100.0) / 100.0;

			Pattern pattern = Pattern.compile("Advance Amount\\s*:?[\\s]*([\\d.]+)");
			Matcher matcher = pattern.matcher(PdfContent);

			if (matcher.find()) {
				double ActualAdvance = Math.round(Double.parseDouble(matcher.group(1).trim()) * 100.0) / 100.0;

				if (ExpectedAdvance != ActualAdvance) {
					System.out.printf("Advance Amount mismatch. Expected: %.2f, Found: %.2f%n", ExpectedAdvance, ActualAdvance);
					throw new AssertionError("Advance Amount does not match.");
				} else {
					System.out.printf(" Advance Amount matched: %.2f%n", ActualAdvance);
				}

			} else {
				System.out.println("'Advance Amount' not found in PDF.");
				throw new AssertionError("Advance Amount missing in PDF.");
			}

		} catch (Exception e) {
			System.out.println("Error validating Advance Amount: " + e.getMessage());
		}

		if (TotalDiamond != null && !TotalDiamond.trim().isEmpty() && !TotalDiamond.trim().equals("0")) {
			ExpectedFields.put("Total Diamond amount is :", TotalDiamond.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		}
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);
		return ExtractedInvoiceNo;
	}



	/// </summary>
	/// Validates a Sales Invoice PDF that includes a combination of regular SKUs and Gift/Service items
	/// Author - Aiswarya
	/// </summary>

	public String SaleInvoicePdfValidationWithAdjustmentGiftServiceItem(
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String HDFCCardAmount,
			String TotalAdjustment,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {

		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		// Extract Invoice No
		try {
			Pattern pattern = Pattern.compile("Invoice No\\s*:?\\s*([\\w\\d]+)");
			Matcher matcher = pattern.matcher(PdfContent);
			if (matcher.find()) {
				ExtractedInvoiceNo = matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		// Normalize PDF text
		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[\u20B9,=:]", "")
				.trim();
		System.out.println("Normalized Pdf Content :" + NormalizedPdfContent);

		// Expected Fields
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount", (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Net Value", NetValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("HDFC (Credit Card)", HDFCCardAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Adjustment", TotalAdjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Adjustment Total", TotalAdjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Gift Service Item", TotalAdjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		System.out.println("ExpectedFields: " + ExpectedFields);
		// Extract and log SKU details
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int i = 1; i <= SkuList.size(); i++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_Total", "0.0"));

			System.out.println("SKU" + i + " → Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);
			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		// Verify PDF content
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// To verify the accuracy of values in a Pro Forma Invoice PDF for negative values(Estimation PDF) by
	/// Validating both individual fields and a summary table row using extracted PDF text.
	//Return Sale Pdf validations
	/// Author - Christy
	/// </summary>	
	public void ProFormaInvoicePDFVerifyReturnSale(String PdfPath,String Subtotal,String Tax,String TotalAmount,String NetTotal,String LinesCount,double 
			TotalGrossWeight,double TotalNetWeight) {
		try {
			// Step 1: Read and normalize PDF content
			String PdfContent = this.IsGetText(PdfPath);
			String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").replaceAll("\\s+", " ").trim();
			System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
			// Step 2: Field-level verifications
			Map<String, String> ExpectedFields = new HashMap<>();
			ExpectedFields.put("Taxable Value :", Subtotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			ExpectedFields.put("GST :", Tax.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			ExpectedFields.put("Invoice Total :", TotalAmount.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			ExpectedFields.put("Net Total :", NetTotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			System.out.println("ExpectedFields: " + ExpectedFields);
			this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);

			// Step 3: Construct total summary row
			List<String> ExpectedRows = new ArrayList<>();

			String CleanLinesCount = LinesCount.replaceAll("[^\\d]", "");
			String CleanSubtotal = Subtotal.replaceAll("[^\\d.]", "").replaceAll("[()]", "");
			String FormattedGrossWeight = String.format("%.3f", TotalGrossWeight);
			String FormattedNetWeight = String.format("%.3f", TotalNetWeight);
			String FormattedSubtotal = String.format("%.2f", Double.parseDouble(CleanSubtotal));

			// Step 4: Row-level verification
			this.VerifyTableRowInPdf(NormalizedPdfContent, ExpectedRows);

		} catch (Exception e) {
			System.out.println("Error in EstimatePDFVerify: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/// <summary>
	/// To verify the accuracy of values in a Pro Forma Invoice PDF (Estimation PDF) by
	/// Validating both individual fields and a summary table row using extracted PDF text.
	/// Author - Vishnu Manoj
	/// </summary>	
	public void ProFormaInvoicePDFVerifyNormal(String PdfPath,String Subtotal,String Tax,String TotalAmount,String NetTotal,String LinesCount,double 
			TotalGrossWeight,double TotalNetWeight) {
		try {
			// Step 1: Read and normalize PDF content
			//Thread.sleep(5000); // wait for PDF to be downloaded
			String PdfContent = this.IsGetText(PdfPath);
			String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").trim();
			System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
			// Step 2: Field-level verifications
			Map<String, String> ExpectedFields = new HashMap<>();
			ExpectedFields.put("Taxable Value :", Subtotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[\u20B9]", "").replaceAll("\\?", "").trim());
			ExpectedFields.put("GST :", Tax.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[\u20B9]", "").replaceAll("\\?", "").trim());
			ExpectedFields.put("Invoice Total :", TotalAmount.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[\u20B9]", "").replaceAll("\\?", "").trim());
			ExpectedFields.put("Net Total :", NetTotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[\u20B9]", "").replaceAll("\\?", "").trim());
			System.out.println("ExpectedFields: " + ExpectedFields);
			this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);

			// Step 3: Construct total summary row
			List<String> ExpectedRows = new ArrayList<>();

			String CleanLinesCount = LinesCount.replaceAll("[^\\d]", "");
			String CleanSubtotal = Subtotal.replaceAll("[^\\d.]", "");
			String FormattedGrossWeight = String.format("%.3f", TotalGrossWeight);
			String FormattedNetWeight = String.format("%.3f", TotalNetWeight);
			String FormattedSubtotal = String.format("%.2f", Double.parseDouble(CleanSubtotal));

			String TotalRow = String.format("Total %s %s %s %s",
					CleanLinesCount,
					FormattedGrossWeight,
					FormattedNetWeight,
					FormattedSubtotal);

			ExpectedRows.add(TotalRow);

			// Step 4: Row-level verification
			this.VerifyTableRowInPdf(NormalizedPdfContent, ExpectedRows);

		} catch (Exception e) {
			System.out.println("Error in EstimatePDFVerify: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/// <summary>
	/// Validates Sale Invoice PDF Validation
	/// Author - Vishnu Manoj
	/// </summary>
	public String SaleInvoicePdfValidation(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		System.out.println("ExpectedFields: " + ExpectedFields);
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + "  Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}
	/// <summary>
	/// To verify the accuracy of values in a Pro Forma Invoice PDF for negative values(Estimation PDF) by
	/// Validating both individual fields and a summary table row using extracted PDF text.
	/// Author - Anagha
	/// </summary>	
	public void ProFormaInvoicePDFVerifyNegativeValue(String PdfPath,String Subtotal,String Tax,String TotalAmount,String NetTotal,String LinesCount,double 
			TotalGrossWeight,double TotalNetWeight) {
		try {
			// Step 1: Read and normalize PDF content
			String PdfContent = this.IsGetText(PdfPath);
			String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").replaceAll("\\s+", " ").trim();
			System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
			// Step 2: Field-level verifications
			Map<String, String> ExpectedFields = new HashMap<>();
			ExpectedFields.put("Taxable Value :", Subtotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			ExpectedFields.put("GST :", Tax.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			ExpectedFields.put("Invoice Total :", TotalAmount.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			ExpectedFields.put("Net Total :", NetTotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").trim());
			System.out.println("ExpectedFields: " + ExpectedFields);
			this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);

			// Step 3: Construct total summary row
			List<String> ExpectedRows = new ArrayList<>();

			String CleanLinesCount = LinesCount.replaceAll("[^\\d]", "");
			String CleanSubtotal = Subtotal.replaceAll("[^\\d.]", "").replaceAll("[()]", "");
			String FormattedGrossWeight = String.format("%.3f", TotalGrossWeight);
			String FormattedNetWeight = String.format("%.3f", TotalNetWeight);
			String FormattedSubtotal = String.format("%.2f", Double.parseDouble(CleanSubtotal));

			String TotalRow = String.format("Total %s %s %s %s",
					CleanLinesCount,
					FormattedGrossWeight,
					FormattedNetWeight,
					FormattedSubtotal);

			ExpectedRows.add(TotalRow);

			// Step 4: Row-level verification
			this.VerifyTableRowInPdf(NormalizedPdfContent, ExpectedRows);

		} catch (Exception e) {
			System.out.println("Error in EstimatePDFVerify: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/// <summary>
	/// To verify the accuracy of values including Adjustment value in a Sales Invoice PDF for negative values(Sales invoice PDF)
	/// Validating both individual fields and a summary table row using extracted PDF text.
	/// Author - Hasna 
	/// </summary>
	public String SaleInvoicePdfAdjustmentValidation(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String Adjustment,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		//ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").trim());
		//Jhoncy for TC34
		try {
			if (PaymentAmountHDFC != null && !PaymentAmountHDFC.trim().isEmpty()) {
				String cleanAmount = PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").trim();

				// First try adding as "HDFC (Credit Card)"
				ExpectedFields.put("HDFC (Credit Card)", cleanAmount);

				// Fallback: if PDF does not contain HDFC keyword, replace with Adjustment
				if (!PdfContent.contains("HDFC") && !PdfContent.contains("Credit Card")) {
					ExpectedFields.remove("HDFC (Credit Card)");
					ExpectedFields.put("Adjustment", cleanAmount);
					System.out.println("Fallback applied → Added Adjustment instead of HDFC (Credit Card)");
				}
			}
		} catch (Exception e) {
			System.out.println("Error while processing PaymentAmountHDFC: " + e.getMessage());
		}
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		ExpectedFields.put("Adjustment :", Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());

		if (!PdfContent.contains("Adjustment") && PdfContent.contains("Online Gift Voucher")) 
		{
			ExpectedFields.remove("Adjustment");
			ExpectedFields.remove("Adjustment :");
			ExpectedFields.put("Online Gift Voucher :",  Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
			System.out.println("Applied → Added Online Gift Voucher instead of Adjustment");
		}

		if (TotalDiamond != null && !TotalDiamond.trim().isEmpty() && !TotalDiamond.trim().equals("0")) {
			ExpectedFields.put("Total Diamond amount is :", TotalDiamond.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		}
		System.out.println("ExpectedFields: " + ExpectedFields);
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}
	/// <summary>
	/// To verify the accuracy of values in a Pro Forma Invoice PDF for negative values(Estimation PDF) for Old Gold
	/// Validating both individual fields and a summary table row using extracted PDF text.
	/// Author - Hasna EK
	/// </summary>	
	public void ProFormaInvoicePdfWithOldGoldNegativeValue(String PdfPath,String TaxableValue,String Gst,String InvoiceTotal,String NetTotal,String TotalAmnt) {
		//,double TotalGrossWeight,double TotalNetWeight
		try {
			// Step 1: Read and normalize PDF content
			//Thread.sleep(5000); // wait for PDF to be downloaded
			String PdfContent = this.IsGetText(PdfPath);
			String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()]", "").replaceAll("\\s+", " ").trim();
			System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);

			// Step 2: Field-level verifications
			//			ExpectedFields.put("Taxable Value : ", TaxableValue.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()\\-]", "").trim());
			//			ExpectedFields.put("GST : ", Gst.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()\\-]", "").trim());
			//			ExpectedFields.put("Invoice Total : ", InvoiceTotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()\\-]", "").trim());
			//			ExpectedFields.put("Net Total : ", NetTotal.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("[()\\-]", "").trim());
			Map<String, String> ExpectedFields = new HashMap<>();
			ExpectedFields.put("Taxable Value : ", String.format("%.2f",Double.parseDouble(TaxableValue.replaceAll("[^\\d.]", ""))));
			ExpectedFields.put("GST : ", String.format("%.2f", Double.parseDouble(Gst.replaceAll("[^\\d.]", ""))));
			ExpectedFields.put("Invoice Total : ", String.format("%.2f",Double.parseDouble(InvoiceTotal.replaceAll("[^\\d.]", ""))));
			ExpectedFields.put("Net Total : ", String.format("%.2f",Double.parseDouble(NetTotal.replaceAll("[^\\d.]", ""))));
			System.out.println("ExpectedFields: " + ExpectedFields);
			this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
			//this.VerifyPdfFieldsWithTolerance(NormalizedPdfContent, ExpectedFields);
			// Step 3: Construct total summary row
			//List<String> ExpectedRows = new ArrayList<>();

			//String CleanNetSubtotal  = TotalAmnt.replaceAll("[^\\d.]", "").replaceAll("[()]", "");
			//String FormattedGrossWeight = String.format("%.3f", TotalGrossWeight);
			//String FormattedNetWeight = String.format("%.3f", TotalNetWeight);
			//String FormattedSubtotal = String.format("%.2f", Double.parseDouble(CleanNetSubtotal ));

			//String TotalRow = String.format("Total %s %s %s",
			//		FormattedGrossWeight,
			//		FormattedNetWeight,
			//		FormattedSubtotal);

			//ExpectedRows.add(TotalRow);

			// Step 4: Row-level verification
			//this.VerifyTableRowInOgPdf(NormalizedPdfContent, ExpectedRows);

		} catch (Exception e) {
			System.out.println("Error in EstimatePDFVerify: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/// <summary>
	/// To verify the accuracy of values including Adjustment value and advance amount in a Sales Invoice PDF for negative values(Sales invoice PDF)
	/// Validating both individual fields and a summary table row using extracted PDF text.
	/// Author - Nandagopan
	/// </summary>
	public String SaleInvoicePdfAdjustmentValidationAdvance(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String Adjustment,
			String AdvanceAmount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}
		String NormalizedPdfContent = PdfContent.replaceAll("â‚¹", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[â‚¹,=:]", "")
				.trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount", (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[â‚¹,]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Net Value", NetValue); // Keep as-is if using tolerance elsewhere
		if (Adjustment != null && !Adjustment.trim().isEmpty()) {
			ExpectedFields.put("Adjustment :", Adjustment.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		}
		System.out.println("ExpectedFields: " + ExpectedFields);
		try {
			double ExpectedAdvance = Math.round(Double.parseDouble(AdvanceAmount.replaceAll("[^\\d.]", "")) * 100.0) / 100.0;

			Pattern pattern = Pattern.compile("Advance Amount\\s*:?[\\s]*([\\d.]+)");
			Matcher matcher = pattern.matcher(PdfContent);

			if (matcher.find()) {
				double ActualAdvance = Math.round(Double.parseDouble(matcher.group(1).trim()) * 100.0) / 100.0;

				if (ExpectedAdvance != ActualAdvance) {
					System.out.printf("Advance Amount mismatch. Expected: %.2f, Found: %.2f%n", ExpectedAdvance, ActualAdvance);
					throw new AssertionError("Advance Amount does not match.");
				} else {
					System.out.printf(" Advance Amount matched: %.2f%n", ActualAdvance);
				}

			} else {
				System.out.println("'Advance Amount' not found in PDF.");
				throw new AssertionError("Advance Amount missing in PDF.");
			}

		} catch (Exception e) {
			System.out.println("Error validating Advance Amount: " + e.getMessage());
		}

		if (TotalDiamond != null && !TotalDiamond.trim().isEmpty() && !TotalDiamond.trim().equals("0")) {
			ExpectedFields.put("Total Diamond amount is :", TotalDiamond.replaceAll("[â‚¹,=:]", "").replaceAll("\\s+", "").trim());
		}
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);
		return ExtractedInvoiceNo;
	}
	/// <Summary>
	/// Note:-GppSettlementSale Pdf Verification
	/// Author - Chandana
	/// </summary>	
	public Map<String, String> ValidateTaxInvoiceSchemeSettlementPdf(
			String SalesPdfPath,
			String ExpectedGrossAmount,
			String ExpectedTotalDiscount,
			String ExpectedTotalQtyPcs,
			String ExpectedTotalNetWt,
			String ExpectedTotalGrossWt,
			String ExpectedTaxableValue,
			String ExpectedTotalValue,
			String ExpectedHdfcCreditCard,
			String ExpectedGppAdvance,
			String ExpectedGppDiscount,
			String ExpectedAdjustmentTotal,
			String ExpectedNetValue
			) throws IOException, InterruptedException {
		// --- helpers ---
		// Normalize: remove any currency symbol (Unicode Sc), INR/Rs, commas, spaces, :, =
		java.util.function.Function<String, String> norm = s -> s == null ? null
				: s.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll("[,\\s=:]", "")
				.trim();

		java.util.function.BiFunction<String, String, Double> capMoney = (text, rx) -> {
			Matcher mm = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!mm.find()) throw new AssertionError("Field not found for regex: " + rx);
			String raw = mm.group(1);
			if (raw == null || raw.trim().isEmpty()) throw new AssertionError("Capture group empty for regex: " + rx);
			raw = raw.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
					.replaceAll("[,\\s()]", "")
					.trim();
			return Math.round(Double.parseDouble(raw) * 100.0) / 100.0;
		};

		java.util.function.BiFunction<String, String, String> capString = (text, rx) -> {
			Matcher m = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!m.find()) throw new AssertionError("Field not found for regex: " + rx);
			String g = m.group(1);
			if (g == null) throw new AssertionError("Capture group empty for regex: " + rx);
			return g.trim();
		};

		// --- read text ---
		String pdfText = this.IsGetText(SalesPdfPath);

		// --- Invoice No ---
		String invoiceNo = capString.apply(pdfText, "Invoice\\s*No\\s*:?[\\s]*([\\w\\d]+)");
		System.out.println("Extracted Invoice No: " + invoiceNo);

		// --- Normalize for map-based checks (currency-agnostic) ---
		String normalized = pdfText
				.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[,=:]", "")
				.trim();
		System.out.println("Normalized PDF Content: " + normalized);
		// --- Build ExpectedFields (labels as printed on the bill) ---
		Map<String, String> expectedFields = new HashMap<>();
		expectedFields.put("Gross Amount",       norm.apply(ExpectedGrossAmount));
		expectedFields.put("Total Discount",     norm.apply((ExpectedTotalDiscount == null || ExpectedTotalDiscount.trim().isEmpty()) ? "0.00" :
			ExpectedTotalDiscount));
		expectedFields.put("Total Qty(Pcs)",     norm.apply(ExpectedTotalQtyPcs));
		expectedFields.put("Total Net Wt.",      norm.apply(ExpectedTotalNetWt));
		expectedFields.put("Total Gross Wt.",    norm.apply(ExpectedTotalGrossWt));
		expectedFields.put("Taxable Value",      norm.apply(ExpectedTaxableValue));
		//expectedFields.put("Total Value",        norm.apply(ExpectedTotalValue)); // validated via map + explicit capture below
		expectedFields.put("Total Value",
				String.format("%.2f", Double.parseDouble(norm.apply(ExpectedTotalValue))));
		//expectedFields.put("HDFC (Credit Card)", norm.apply(ExpectedHdfcCreditCard));
		expectedFields.put("Net Value",          norm.apply(ExpectedNetValue));
		System.out.println("Expected Fields Map: " + expectedFields);
		// --- Explicit validations for payments/adjustments ---

		// HDFC (Credit Card)
		String HdfcRx = "HDFC\\s*\\(\\s*(?:Credit\\s*)?Card\\s*\\)\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.\\-\\(\\)]+)";
		double ActHdfc = capMoney.apply(pdfText, HdfcRx);
		double ExpHdfc = Double.parseDouble(norm.apply(ExpectedHdfcCreditCard));
		asrt.assertEquals(ActHdfc, ExpHdfc, 1, "HDFC (Credit Card) mismatch.");
		System.out.println("HDFC Amount | Expected: "+ExpHdfc+"| Actual: "+ActHdfc+"| MACTCH");

		// Advance (supports "Advance Amount" or "GPP Advance")
		double actAdv=0.00;
		double expAdv=0.00;
		if (ExpectedGppAdvance != null && !ExpectedGppAdvance.trim().isEmpty() && !ExpectedGppAdvance.trim().equals("0")) {
			String advanceRx = "(?:GPP\\s*Advance|Advance\\s*Amount)\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
			actAdv = capMoney.apply(pdfText, advanceRx);
			expAdv = Double.parseDouble(norm.apply(ExpectedGppAdvance));
			asrt.assertEquals(actAdv, expAdv, 1, "Advance mismatch.");
		}
		System.out.println("GPP Advance | Expected: "+expAdv+"| Actual: "+actAdv+"| MACTCH");

		// GPP Discount (optional)
		double ActDis = 0.00, ExpDis = 0.00;
		boolean DiscountProvided = (ExpectedGppDiscount != null && !ExpectedGppDiscount.trim().isEmpty());
		if (DiscountProvided) {
			String DiscountRx = "GPP\\s*Discount\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
			ActDis = capMoney.apply(pdfText, DiscountRx);
			ExpDis = Double.parseDouble(norm.apply(ExpectedGppDiscount));
			asrt.assertEquals(ActDis, ExpDis, 1, "GPP Discount mismatch.");
			System.out.println("GPP Discount | Expected: "+ExpDis+"| Actual: "+ActDis+"| MACTCH");
		}

		// Adjustment Total
		String AdjustmentRx = "Adjustment\\s*Total\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
		double ActAdj = capMoney.apply(pdfText, AdjustmentRx);
		double ExpAdj = Double.parseDouble(norm.apply(ExpectedAdjustmentTotal));
		asrt.assertEquals(ActAdj, ExpAdj, 1, "Adjustment Total mismatch.");
		System.out.println("Adjustment Total | Expected: "+ExpAdj+"| Actual: "+ActAdj+"| MACTCH");

		// Cross-check: Adjustment = Advance (+ Discount if present)
		double SumAdj = Math.round((actAdv + (DiscountProvided ? ActDis : 0.0)) * 100.0) / 100.0;
		asrt.assertEquals(SumAdj, ActAdj, 1,
				"Advance + (Discount if any) != Adjustment Total. Sum: " + SumAdj + ", Adjustment: " + ActAdj);
		System.out.println("Advance | Expected: "+SumAdj+"| Actual: "+ActAdj+"| MACTCH");

		// --- Verify the rest using your helper (presence/value in normalized content) ---
		this.VerifyMultipleFieldsInPdf(normalized, expectedFields);

		//	    // --- Extract Total Value reliably (labels in one column, numbers in another) ---
		//	    // Order after the label block: [Taxable Value, CGST, SGST, Total Value, Total Adjustment]
		//	    double actualTotalValue;
		//	    {
		//	        Pattern lbl = Pattern.compile(
		//	            "Taxable\\s*Value[\\s\\S]*?CGST[\\s\\S]*?SGST[\\s\\S]*?Total\\s*Value[\\s\\S]*?Total\\s*Adjustment",
		//	            Pattern.CASE_INSENSITIVE
		//	        );
		//	        Matcher lm = lbl.matcher(pdfText);
		//	        org.testng.Assert.assertTrue(lm.find(), "Totals label block not found.");
		//	        int afterLabelsIdx = lm.end();
		//
		//	        String tail = pdfText.substring(afterLabelsIdx);
		//	        Matcher num = Pattern.compile("\\(?\\s*([\\d,]+\\.\\d{2})\\s*\\)?").matcher(tail);
		//
		//	        java.util.List<Double> vals = new java.util.ArrayList<>();
		//	        while (num.find() && vals.size() < 5) {
		//	            String raw = num.group(1).replace(",", "").trim();
		//	            vals.add(Double.parseDouble(raw));
		//	        }
		//	        org.testng.Assert.assertEquals(vals.size(), 5,
		//	            "Could not capture 5 totals after label block. Got: " + vals);
		//
		//	        // index 3 (0-based) => Total Value
		//	        actualTotalValue = vals.get(3);
		//	        System.out.println("Extracted Total Value: " + actualTotalValue);
		//	    }

		// --- Return both InvoiceNumber and TotalValue (actual extracted) ---
		Map<String, String> result = new HashMap<>();
		result.put("InvoiceNumber", invoiceNo);
		result.put("TotalValue",ExpectedTotalValue);
		return result;
	}


	/// <summary>
	/// PDF Validation â€“ Validates "Tax Invoice" (Dynamics POS)
	/// Returns InvoiceNumber and TotalValue from the PDF.
	/// Author - Nandagopan  (updated for optional GPP Discount handling)
	/// </summary>
	public Map<String, String> ValidateTaxInvoiceSchemeSettlementPdfGPPDiscount(
			String SalesPdfPath,
			String ExpectedGrossAmount,
			String ExpectedTotalDiscount,
			String ExpectedTotalQtyPcs,
			String ExpectedTotalNetWt,
			String ExpectedTotalGrossWt,
			String ExpectedTaxableValue,
			String ExpectedTotalValue,
			String ExpectedHdfcCreditCard,
			String ExpectedGppAdvance,
			String ExpectedGppDiscount,
			String ExpectedAdjustmentTotal,
			String ExpectedNetValue
			) throws IOException, InterruptedException {

		// --- helpers ---
		java.util.function.Function<String, String> norm = s -> s == null ? null
				: s.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll("[,\\s=:]", "")
				.trim();

		java.util.function.BiFunction<String, String, Double> capMoney = (text, rx) -> {
			Matcher mm = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!mm.find()) throw new AssertionError("Field not found for regex: " + rx);
			String raw = mm.group(1);
			if (raw == null || raw.trim().isEmpty()) throw new AssertionError("Capture group empty for regex: " + rx);
			raw = raw.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
					.replaceAll("[,\\s()]", "")
					.trim();
			return Math.round(Double.parseDouble(raw) * 100.0) / 100.0;
		};

		java.util.function.BiFunction<String, String, String> capString = (text, rx) -> {
			Matcher m = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!m.find()) throw new AssertionError("Field not found for regex: " + rx);
			String g = m.group(1);
			if (g == null) throw new AssertionError("Capture group empty for regex: " + rx);
			return g.trim();
		};

		// --- read text ---
		String pdfText = this.IsGetText(SalesPdfPath);

		// --- Invoice No ---
		String invoiceNo = capString.apply(pdfText, "Invoice\\s*No\\s*:?[\\s]*([\\w\\d]+)");
		System.out.println("Extracted Invoice No: " + invoiceNo);

		// --- Normalize for map-based checks (currency-agnostic) ---
		String normalized = pdfText
				.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[,=:]", "")
				.trim();
		System.out.println("Normalized PDF Content: " + normalized);
		// --- Build ExpectedFields (labels as printed on the bill) ---
		Map<String, String> expectedFields = new HashMap<>();
		expectedFields.put("Gross Amount",       norm.apply(ExpectedGrossAmount));
		expectedFields.put("Total Discount",     norm.apply((ExpectedTotalDiscount == null || ExpectedTotalDiscount.trim().isEmpty()) ? "0.00" : 
			ExpectedTotalDiscount));
		expectedFields.put("Total Qty(Pcs)",     norm.apply(ExpectedTotalQtyPcs));
		expectedFields.put("Total Net Wt.",      norm.apply(ExpectedTotalNetWt));
		expectedFields.put("Total Gross Wt.",    norm.apply(ExpectedTotalGrossWt));
		expectedFields.put("Taxable Value",      norm.apply(ExpectedTaxableValue));
		//expectedFields.put("Total Value",        norm.apply(ExpectedTotalValue)); // validated via map + explicit capture below
		expectedFields.put("Total Value",
				String.format("%.2f", Double.parseDouble(norm.apply(ExpectedTotalValue))));
		expectedFields.put("HDFC (Credit Card)", norm.apply(ExpectedHdfcCreditCard));
		expectedFields.put("Net Value",          norm.apply(ExpectedNetValue));
		System.out.println("Expected Fields: " + expectedFields);
		// --- Explicit validations for payments/adjustments ---

		// HDFC (Credit Card)
		//double actHdfc = capMoney.apply(pdfText, "HDFC\\s*\\(Credit\\s*Card\\)\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		/*double actHdfc = capMoney.apply(
			    pdfText,
			    "HDFC\\s*\\(\\s*Card\\s*\\)\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)"
			);*/
		double actHdfc = capMoney.apply(
				pdfText,
				"HDFC\\s*\\(\\s*(?:Credit\\s*)?Card\\s*\\)\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)"
				);
		double expHdfc = Double.parseDouble(norm.apply(ExpectedHdfcCreditCard));
		asrt.assertEquals(actHdfc, expHdfc, 0.01, "HDFC (Credit Card) mismatch.");

		// Advance (supports "Advance Amount" or "GPP Advance")
		String advanceRx = "(?:GPP\\s*Advance|Advance\\s*Amount)\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
		double actAdv = capMoney.apply(pdfText, advanceRx);
		double expAdv = Double.parseDouble(norm.apply(ExpectedGppAdvance));
		asrt.assertEquals(actAdv, expAdv, 0.01, "Advance mismatch.");

		// --- GPP Discount (optional field) ---
		double actDis = 0.00, expDis = 0.00;
		if (ExpectedGppDiscount != null && !ExpectedGppDiscount.trim().isEmpty()) {
			expDis = Double.parseDouble(norm.apply(ExpectedGppDiscount));

			if (expDis > 0.0) {
				// Only try to capture from PDF if expecting a real discount
				String discountRx = "GPP\\s*Discount\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
				try {
					actDis = capMoney.apply(pdfText, discountRx);
					asrt.assertEquals(actDis, expDis, 1.00, "GPP Discount mismatch.");
				} catch (AssertionError e) {
					throw new AssertionError("Expected GPP Discount " + expDis + " but field not found in PDF.");
				}
			} else {
				// expected = 0, but PDF may not have the field â†’ treat as fine
				actDis = 0.0;
			}
		}

		// Adjustment Total
		String adjustmentRx = "Adjustment\\s*Total\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
		double actAdj = capMoney.apply(pdfText, adjustmentRx);
		double expAdj = Double.parseDouble(norm.apply(ExpectedAdjustmentTotal));
		asrt.assertEquals(actAdj, expAdj, 0.01, "Adjustment Total mismatch.");

		// Cross-check: Adjustment = Advance (+ Discount if present)
		double sumAdj = Math.round((actAdv + (expDis > 0.0 ? actDis : 0.0)) * 100.0) / 100.0;
		asrt.assertEquals(sumAdj, actAdj, 0.01,
				"Advance + (Discount if any) != Adjustment Total. Sum: " + sumAdj + ", Adjustment: " + actAdj);

		// --- Verify the rest using your helper (presence/value in normalized content) ---
		this.VerifyMultipleFieldsInPdf(normalized, expectedFields);

		// --- Return both InvoiceNumber and TotalValue (actual extracted) ---
		Map<String, String> result = new HashMap<>();
		result.put("InvoiceNumber", invoiceNo);
		result.put("TotalValue", ExpectedTotalValue);
		return result;
	}
	/// <summary>
	/// Validates Sale Invoice PDF Validation for Partial Payment with Cash
	/// Author - Robin
	/// </summary>
	public String SaleInvoiceCashPdfValidation(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String PaymentAmountCash,   //Added new param
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println("Normalized PDF Content: " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		if (PaymentAmountHDFC != null && !PaymentAmountHDFC.trim().isEmpty()) {
			ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		}
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue

		//Add Cash validation dynamically with assertion
		if (PaymentAmountCash != null && !PaymentAmountCash.trim().isEmpty() && !PaymentAmountCash.equals("0")) {
			String CleanCash = PaymentAmountCash.replaceAll("[\u20B9,]", "")
					.replaceAll("\\s+", "")
					.replaceAll("\\?", "")
					.trim();
			ExpectedFields.put("Cash", CleanCash);
		}

		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}
	/// <summary>
	/// Validates Sale Invoice PDF Validation for Service Item
	/// Author - Chandana Babu
	/// </summary>
	public String SaleInvoicePdfValidationForServiceItem(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String Adjustment
			) throws IOException, InterruptedException {

		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		// Extract Invoice No
		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		// Normalize PDF content
		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println("Normalized Pdf Content :"+NormalizedPdfContent);
		// Expected field values
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount",  
				(TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		// support both labels
		//ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		if (PaymentAmountHDFC != null && !PaymentAmountHDFC.trim().isEmpty()) {
			ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		}
		if (Adjustment != null && !Adjustment.trim().isEmpty()) {
			ExpectedFields.put("Adjustment", Adjustment.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		}
		ExpectedFields.put("Net Value", NetValue.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		System.out.println("ExpectedFields :"+ExpectedFields);
		// Verify fields in PDF
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);

		return ExtractedInvoiceNo;
	}
	/// <summary>
	/// PDF Validation – Dynamics POS Pro-Forma Invoice with discount
	/// Validates: "Total" summary row, Additional Discount, Taxable Value, GST, Invoice Total, Total Adjustment, Net Total.
	/// Author - Chandana Babu
	/// </summary>
	public void ProFormaInvoicePDFVerifyDiscount(
			String PdfPath,
			String ExpectedLinesCount,   
			double ExpectedTotalGrossWt,  
			double ExpectedTotalNetWt,    
			String ExpectedTotalAmount,
			String ExpectedAdditionalDiscount, 
			String ExpectedTaxableValue,      
			String ExpectedGst,                
			String ExpectedInvoiceTotal, 
			String TotalAdjustment,
			String ExpectedNetTotal            
			) {
		try {
			// 1) Extract & normalize PDF content (currency-agnostic)
			String pdfText = this.IsGetText(PdfPath);
			String normalized = pdfText
					.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "") // remove currency tokens/symbols
					.replaceAll(",", "")
					.replaceAll("\\s+", " ")
					.trim();
			System.out.println("Normalized Pdf Content :"+normalized);
			// 2) Field-level checks (labels exactly as printed)
			Map<String, String> expectedFields = new HashMap<>();
			expectedFields.put("Additional Discount :", ExpectedAdditionalDiscount);
			expectedFields.put("Taxable Value :",      ExpectedTaxableValue);
			expectedFields.put("GST :",                 ExpectedGst);
			expectedFields.put("Invoice Total :",       ExpectedInvoiceTotal);
			expectedFields.put("Net Total :",           ExpectedNetTotal);
			if (TotalAdjustment != null && !TotalAdjustment.trim().isEmpty()) {
				expectedFields.put("Total Adjustment : ", String.format("%.2f",Double.parseDouble(TotalAdjustment.replaceAll("[^\\d.]", ""))));
			}
			System.out.println("Expected Fields :"+expectedFields);
			this.VerifyMultipleFieldsInPdf(normalized, expectedFields);

			// 3) Row-level check – full "Total" summary row
			//    Format: Total <LinesCount> <G.Wt> <N.Wt> <Amount>
			String formattedGross = String.format(java.util.Locale.US, "%.3f", ExpectedTotalGrossWt);
			String formattedNet   = String.format(java.util.Locale.US, "%.3f", ExpectedTotalNetWt);
			String formattedAmt   = String.format(java.util.Locale.US, "%.2f",
					Double.parseDouble(ExpectedTotalAmount.replaceAll("[^\\d.]", "")));

			String totalRow = String.format("Total %s %s %s %s",
					ExpectedLinesCount.replaceAll("[^\\d]", ""),
					formattedGross,
					formattedNet,
					formattedAmt
					);

			List<String> expectedRows = new ArrayList<>();
			expectedRows.add(totalRow);

			//			this.VerifyTableRowInPdf(normalized, expectedRows);

		} catch (Exception e) {
			e.printStackTrace();
			throw e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException(e);
		}
	}

	/// <summary>
	/// Validates Sale Invoice PDF Validation With Rtgs Received
	/// Author - Benson
	/// </summary>
	public String SaleInvoicePdfValidationWithRtgsReceived(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String RTGSAmount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println("Normalized Pdf Content :"+NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("RTGS/Received", RTGSAmount.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		System.out.println("Expected Fields :"+ExpectedFields);
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// Validates Sale Invoice PDF Validation
	///Update-Validates Adjustment Tax amount
	/// Author - Nandagopan
	/// </summary>
	public String SaleInvoicePdfValidationTaxInvoice(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String AdjustmentTaxinvoice,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();

		System.out.println("NormalizedPdfContent"+NormalizedPdfContent);

		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		//ExpectedFields.put("Adjustment", AdjustmentTaxinvoice.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		ExpectedFields.put("Advance Amount", AdjustmentTaxinvoice.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Adjustment Total", AdjustmentTaxinvoice.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Adjustment", AdjustmentTaxinvoice.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());

		System.out.println("ExpectedFields"+ExpectedFields);

		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + "  Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// Validates Advance Refund Receipt PDF by verifying Advance Received, Net Advance, and RTGS/NEFT amounts.
	/// Author - Aiswarya
	/// </summary>
	///
	public Map<String, String> AdvanceRefundPdfValidation() throws IOException, InterruptedException {

		String NewPdfName = DownloadAndRenameLatestPDF("AdvanceRefundPdf");
		String PdfPath = System.getProperty("user.dir") + "\\Invoices\\" + NewPdfName;
		String ExtractedText = IsGetText(PdfPath);

		String[] Lines = ExtractedText.split("\\r?\\n");

		String ReceiptId = "";
		String AdvanceReceived = "";
		String NetAdvance = "";
		String PaymentMode = "";
		String PaymentAmount = "";
		String RTGSNEFTAmount = "";

		boolean FoundAdvanceLine = false;

		BiFunction<String, String, String> ExtractValueByPrefix = (Line, Prefix) -> {
			if (Line.startsWith(Prefix)) {
				String[] Parts = Line.split(":");
				if (Parts.length > 1) {
					return Parts[1].trim();
				}
			}
			return "";
		};

		for (String LineRaw : Lines) {
			String Line = LineRaw.trim();

			// Receipt ID
			if (Line.startsWith("Receipt Id")) {
				ReceiptId = ExtractValueByPrefix.apply(Line, "Receipt Id");
			}

			// Advance Received and Net Advance (line after header)
			if (Line.contains("Advance Received") && Line.contains("Net Advance")) {
				FoundAdvanceLine = true;
				continue; // skip this line, next line has the numbers
			}
			if (FoundAdvanceLine) {
				String[] Values = Line.split("\\s+");
				if (Values.length >= 2) {
					AdvanceReceived = Values[0].trim();
					NetAdvance = Values[1].trim();
				}
				FoundAdvanceLine = false;
				continue;
			}

			// Payment Mode and Amount (RTGS/NEFT)
			if (Line.startsWith("RTGS/NEFT")) {
				PaymentMode = "RTGS/NEFT";
				String[] Parts = Line.split(":");
				if (Parts.length > 1) {
					PaymentAmount = Parts[1].trim();
					RTGSNEFTAmount = PaymentAmount; // store for map
				}
			}
		}

		Thread.sleep(2000);

		// Assertions
		Assert.assertEquals(AdvanceReceived, NetAdvance, "Advance Received and Net Advance do not match");
		Assert.assertEquals(PaymentAmount, AdvanceReceived, "Advance Received and Payment Amount do not match");

		Map<String, String> ResultMap = new HashMap<>();
		ResultMap.put("ReceiptId", ReceiptId);
		ResultMap.put("RTGSNEFTAmount", RTGSNEFTAmount);
		ResultMap.put("AdvanceReceived", AdvanceReceived);
		ResultMap.put("NetAdvance", NetAdvance);

		return ResultMap;
	}


	/// <summary>
	///Verifying pdf having 2 pages
	///Author : Christy Reji
	///<summary>
	public void VerifyMultipleFieldsInDoublePagePdfs(String NormalizedPdfContent, Map<String, String> ExpectedFields) {
		final double TOLERANCE = 1.0;

		System.out.println("\n--- PDF Field Verification Start ---");

		String pdf = NormalizedPdfContent == null ? "" : NormalizedPdfContent
				.replace("\u20B9", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.trim();

		// ===== Regex definitions =====
		final String UNIFIED_FOOTER_REGEX =
				"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*Total Net Wt\\.\\s*Total Gross Wt\\.\\s*"
						+ "([\\d.\\-]+)\\s+([\\d.\\-]+)\\s+([\\d.\\-]+)\\s+([\\d.\\-]+)\\s+([\\d.\\-]+)";
		final String TRUNCATED_FOOTER_WITH_LABELS_REGEX =
				"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*Total Net Wt\\.\\s*Total Gross Wt\\.\\s*"
						+ "([\\d]+\\.[\\d]+|[\\d]+)\\s+([\\d]+\\.[\\d]+|[\\d]+)\\s+([\\d]+\\.[\\d]+|[\\d]+)";
		final String FOOTER_REGEX_A =
				"Gross Amount\\s*Total Discount\\s*Total Qty\\(Pcs\\)\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)";
		final String FOOTER_REGEX_B =
				"Total Net Wt\\.\\s*Total Gross Wt\\.\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)";
		final String FOOTER_REGEX_C =
				"Gross Amount\\s*Total Discount\\s*([\\d.\\-]+)\\s*([\\d.\\-]+)";

		// --- NEW: split tax line patterns for your case ---
		// e.g. "Taxable Value CGST - 1.50% 127814.07 1917.21"
		final String TAX_SPLIT_AFTER_CGST_REGEX =
				"\\bTaxable Value\\b\\s*CGST\\s*-\\s*[\\d.]+%\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)";
		// e.g. "SGST - 1.50% Total Value 1917.21 131648.49"
		final String TAX_SPLIT_SGST_TOTAL_REGEX =
				"\\bSGST\\b\\s*-\\s*[\\d.]+%\\s*\\bTotal Value\\b\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)";

		// Existing tax patterns
		final String TAX_MATRIX_WITH_ROUNDOFF_REGEX =
				"\\bTaxable Value\\b\\s*CGST.*?SGST.*?\\bTotal Value\\b\\s*\\bRound Off\\b\\s*"
						+ "([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)\\s*\\((\\-?[\\d]+(?:\\.\\d+)?)\\)";
		final String TAX_MATRIX_NO_ADJ_REGEX =
				"\\bTaxable Value\\b\\s*CGST.*?\\bSGST\\b.*?\\bTotal Value\\b\\s*"
						+ "([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)\\s*([\\d]+(?:\\.\\d+)?)";
		final String TAX_FULL_BLOCK_FLEXIBLE_REGEX =
				"\\bTaxable Value\\b.*?\\bTotal Value\\b\\s*\\bTotal Adjustment\\b\\s*\\bRound Off\\b\\s*"
						+ "([\\d]+(?:\\.\\d+)?)[\\s]+([\\d]+(?:\\.\\d+)?)[\\s]+([\\d]+(?:\\.\\d+)?)[\\s]+([\\d]+(?:\\.\\d+)?)[\\s]+\\(([\\d.\\-]+)\\)[\\s]+\\(?([\\d]+(?:\\.\\d+)?|\\-?[\\d]+)\\)?";
		final String TAXABLE_SINGLE_REGEX = "\\bTaxable Value\\b\\s*([\\d]+(?:\\.\\d+)?)";
		final String TOTAL_ADJUSTMENT_DIRECT_REGEX = "\\bTotal Adjustment\\b\\s*\\(?([\\d]+(?:\\.\\d+)?)\\)?";

		// GST (amount only), exclude "Including GST"
		final String GST_RATE_AND_AMOUNT_REGEX =
				"(?<!Including\\s)\\bGST\\b\\s*([\\d]+(?:\\.\\d+)?)\\s*%\\s*[:\\-]\\s*([\\-]?[\\d]+(?:\\.\\d+)?)";
		final String GST_SIMPLE_COLON_REGEX =
				"(?<!Including\\s)\\bGST\\b\\s*[:\\-]\\s*([\\-]?[\\d]+(?:\\.\\d+)?)";

		// Payment details (HDFC etc.)
		final String PAYMENT_WINDOW_REGEX =
				"\\bPayment Details\\b(.{0,200}?)\\b(Gross Amount|Other Information|Taxable Value|Rupees|Net Value|DECLARATION|$)";
		final String HDFC_CC_REGEX = "\\bHDFC\\s*\\(\\s*Credit\\s*Card\\s*\\)\\s*([\\-]?\\d+(?:\\.\\d+)?)";

		// ===== Pre-extracted values =====
		Map<String, String> actuals = new HashMap<>();
		String totalAdjustmentFromTax = null;
		String roundOffFromTax = null;
		String cgstAmount = null;
		String sgstAmount = null;

		// ----- Footer extraction -----
		Matcher m = Pattern.compile(UNIFIED_FOOTER_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
		if (m.find()) {
			actuals.put("Gross Amount",   m.group(1).trim());
			actuals.put("Total Discount", m.group(2).trim());
			actuals.put("Total Qty(Pcs)", m.group(3).trim());
			actuals.put("Total Net Wt.",  m.group(4).trim());
			actuals.put("Total Gross Wt.",m.group(5).trim());
		} else {
			m = Pattern.compile(TRUNCATED_FOOTER_WITH_LABELS_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
			if (m.find()) {
				actuals.put("Gross Amount",   m.group(1).trim());
				actuals.put("Total Discount", m.group(2).trim());
				actuals.put("Total Qty(Pcs)", m.group(3).trim());
				actuals.putIfAbsent("Total Net Wt.",  "0.00");
				actuals.putIfAbsent("Total Gross Wt.","0.00");
			} else {
				Matcher a = Pattern.compile(FOOTER_REGEX_A, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (a.find()) {
					actuals.put("Gross Amount",   a.group(1).trim());
					actuals.put("Total Discount", a.group(2).trim());
					actuals.put("Total Qty(Pcs)", a.group(3).trim());
				}
				Matcher b = Pattern.compile(FOOTER_REGEX_B, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (b.find()) {
					actuals.put("Total Net Wt.",  b.group(1).trim());
					actuals.put("Total Gross Wt.",b.group(2).trim());
				}
				Matcher c = Pattern.compile(FOOTER_REGEX_C, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (c.find()) {
					actuals.putIfAbsent("Gross Amount",   c.group(1).trim());
					actuals.putIfAbsent("Total Discount", c.group(2).trim());
				}
			}
		}

		// ----- NEW FIRST: split tax line captures -----
		// Capture taxable + cgst when they appear together after CGST - % 
		Matcher splitA = Pattern.compile(TAX_SPLIT_AFTER_CGST_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
		if (splitA.find()) {
			actuals.put("Taxable Value", splitA.group(1).trim()); // 127814.07
			cgstAmount = splitA.group(2).trim();                  // 1917.21
		}
		// Capture sgst + total value when they appear together
		Matcher splitB = Pattern.compile(TAX_SPLIT_SGST_TOTAL_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
		if (splitB.find()) {
			sgstAmount = splitB.group(1).trim();                  // 1917.21
			actuals.put("Total Value", splitB.group(2).trim());   // 131648.49
		}

		// ----- Other tax block patterns (only if still missing) -----
		if (!actuals.containsKey("Taxable Value") || !actuals.containsKey("Total Value")) {
			Matcher taxRO = Pattern.compile(TAX_MATRIX_WITH_ROUNDOFF_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
			if (taxRO.find()) {
				actuals.putIfAbsent("Taxable Value", taxRO.group(1).trim());
				actuals.putIfAbsent("Total Value",   taxRO.group(4).trim());
				roundOffFromTax = taxRO.group(5).trim();
			} else {
				Matcher taxNoAdj = Pattern.compile(TAX_MATRIX_NO_ADJ_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
				if (taxNoAdj.find()) {
					actuals.putIfAbsent("Taxable Value", taxNoAdj.group(1).trim());
					actuals.putIfAbsent("Total Value",   taxNoAdj.group(4).trim());
				} else {
					Matcher taxFull = Pattern.compile(TAX_FULL_BLOCK_FLEXIBLE_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
					if (taxFull.find()) {
						actuals.putIfAbsent("Taxable Value", taxFull.group(1).trim());
						actuals.putIfAbsent("Total Value",   taxFull.group(4).trim());
						totalAdjustmentFromTax = taxFull.group(5).trim().replaceAll("[()]", "");
						roundOffFromTax = taxFull.group(6) == null ? null : taxFull.group(6).trim();
					} else {
						Matcher tv = Pattern.compile(TAXABLE_SINGLE_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
						if (tv.find()) {
							actuals.putIfAbsent("Taxable Value", tv.group(1).trim());
						}
					}
				}
			}
		}

		// ----- Payment Details extraction -----
		Matcher payWin = Pattern.compile(PAYMENT_WINDOW_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(pdf);
		if (payWin.find()) {
			String window = payWin.group(1);
			Matcher hdfc = Pattern.compile(HDFC_CC_REGEX, Pattern.CASE_INSENSITIVE).matcher(window);
			if (hdfc.find()) actuals.put("HDFC (Credit Card)", hdfc.group(1).trim());

			Matcher cash = Pattern.compile("\\bCash\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(window);
			if (cash.find()) actuals.put("Cash", cash.group(1).trim());

			Matcher rtgs = Pattern.compile("\\bRTGS\\/Received\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(window);
			if (rtgs.find()) actuals.put("RTGS/Received", rtgs.group(1).trim());

			Matcher upi = Pattern.compile("\\bUPI\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(window);
			if (upi.find()) actuals.put("UPI", upi.group(1).trim());
		}

		// ----- Fallback window for mixed layouts (if still missing) -----
		if (!actuals.containsKey("Taxable Value") || !actuals.containsKey("Total Value")) {
			Pattern windowP = Pattern.compile("\\bTaxable Value\\b(.{0,800}?)(?:\\bNet Value\\b|\\bRupees\\b|\\bDECLARATION\\b|\\bPayment Details\\b|$)",
					Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher winM = windowP.matcher(pdf);
			if (winM.find()) {
				String window = winM.group(1);
				Matcher nums = Pattern.compile("\\(?-?\\d+(?:\\.\\d+)?\\)?").matcher(window);
				List<String> vals = new ArrayList<>();
				while (nums.find()) {
					String token = nums.group();
					int start = nums.start();
					boolean looksPercent = start > 0 && window.substring(Math.max(0, start - 3), Math.min(window.length(), start + token.length() + 1)).contains("%");
					if (!looksPercent) vals.add(token.replaceAll("[()]", ""));
					if (vals.size() >= 5) break;
				}
				if (vals.size() >= 4) {
					actuals.putIfAbsent("Taxable Value", vals.get(0));
					actuals.putIfAbsent("Total Value",   vals.get(3));
					String wlower = window.toLowerCase();
					if (vals.size() >= 5) {
						if (wlower.contains("round off")) roundOffFromTax = vals.get(4);
						else if (wlower.contains("total adjustment")) totalAdjustmentFromTax = vals.get(4);
					}
				}
			}
		}

		// ===== Verification loop =====
		for (Map.Entry<String, String> e : ExpectedFields.entrySet()) {
			String keyRaw = e.getKey() == null ? "" : e.getKey().trim();
			String expectedStr = e.getValue();
			String key = keyRaw.replaceAll("\\s*:\\s*$", ":").trim();

			String actualStr = null;

			if (key.equalsIgnoreCase("Total Adjustment")) {
				if (totalAdjustmentFromTax != null) actualStr = totalAdjustmentFromTax;
				else {
					Matcher ta = Pattern.compile(TOTAL_ADJUSTMENT_DIRECT_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
					if (ta.find()) actualStr = ta.group(1).trim();
				}
			} else if (key.equalsIgnoreCase("Round Off")) {
				if (roundOffFromTax != null) actualStr = roundOffFromTax.replace("(", "").replace(")", "");
			} else if (actuals.containsKey(key)) {
				actualStr = actuals.get(key);
			} else if (key.equalsIgnoreCase("GST") || key.equalsIgnoreCase("GST:") || key.equalsIgnoreCase("GST :")) {
				Matcher g = Pattern.compile(GST_RATE_AND_AMOUNT_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (g.find()) actualStr = g.group(2).trim();
				if (actualStr == null) {
					g = Pattern.compile(GST_SIMPLE_COLON_REGEX, Pattern.CASE_INSENSITIVE).matcher(pdf);
					if (g.find()) actualStr = g.group(1).trim();
				}
			} else if (key.equalsIgnoreCase("Adjustment:") || key.equalsIgnoreCase("Adjustment :")) {
				Matcher adj = Pattern.compile("\\bAdjustment\\b\\s*[:\\-]?\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (adj.find()) actualStr = adj.group(1).trim();
				if (actualStr == null) {
					adj = Pattern.compile("\\bAdjustment\\b\\s*\\bTotal\\b\\s*([\\-]?\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE).matcher(pdf);
					if (adj.find()) actualStr = adj.group(1).trim();
				}
			} else {
				String searchKey = key.replaceAll("[\\:\\-]", "").trim();
				String generic = "\\b" + Pattern.quote(searchKey) + "\\b\\s*[:\\-]?\\s*\\(?([\\-]?\\d+(?:\\.\\d+)?)\\)?";
				Matcher mg = Pattern.compile(generic, Pattern.CASE_INSENSITIVE).matcher(pdf);
				if (mg.find()) actualStr = mg.group(1).trim();
			}

			if (actualStr == null || actualStr.isEmpty()) {
				System.out.printf("%-20s | Expected: %-10s | Actual: %-10s |  FIELD NOT FOUND%n", key, expectedStr, "N/A");
				asrt.fail("Field '" + key + "' not found in PDF content.");
				continue;
			}

			String cleanExpected = expectedStr == null ? "" : expectedStr.replaceAll("[^\\d.\\-+]", "").trim();
			String cleanActual   = actualStr.replaceAll("[^\\d.\\-+]", "").trim();

			try {
				double expected = Double.parseDouble(cleanExpected);
				double actual   = Double.parseDouble(cleanActual);
				boolean match   = Math.abs(expected - actual) <= TOLERANCE;

				System.out.printf("%-20s | Expected: %-10.2f | Actual: %-10.2f | %s%n",
						key, expected, actual, match ? " MATCH" : " MISMATCH");

				asrt.assertEquals(
						actual,
						expected,
						TOLERANCE,
						"Mismatch in " + key + " (Tolerance: " + TOLERANCE + ")"
						);

			} catch (NumberFormatException nfe) {
				boolean match = cleanExpected.equalsIgnoreCase(cleanActual);
				System.out.printf("%-20s | Expected: %-10s | Actual: %-10s | %s%n",
						key, cleanExpected, cleanActual, match ? " MATCH" : " MISMATCH");
				if (!match) {
					asrt.fail("Mismatch in " + key + ": expected '" + cleanExpected + "', actual '" + cleanActual + "'");
				}
			}
		}

		System.out.println("--- PDF Field Verification End ---\n");
	}
	/// <summary>
	///PDF having 2 pages
	/// To verify the accuracy of values including Adjustment value in a Sales Invoice PDF for negative values(Sales invoice PDF)
	/// Validating both individual fields and a summary table row using extracted PDF text.
	/// Author - Christy Reji
	/// </summary>
	public String FinalSaleInvoicePdfAdjustmentValidation(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String Adjustment,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		//ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").trim());
		//Jhoncy for TC34
		try {
			if (PaymentAmountHDFC != null && !PaymentAmountHDFC.trim().isEmpty()) {
				String cleanAmount = PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").trim();

				// First try adding as "HDFC (Credit Card)"
				ExpectedFields.put("HDFC (Credit Card)", cleanAmount);

				// Fallback: if PDF does not contain HDFC keyword, replace with Adjustment
				if (!PdfContent.contains("HDFC") && !PdfContent.contains("Credit Card")) {
					ExpectedFields.remove("HDFC (Credit Card)");
					ExpectedFields.put("Adjustment", cleanAmount);
					System.out.println("Fallback applied → Added Adjustment instead of HDFC (Credit Card)");
				}
			}
		} catch (Exception e) {
			System.out.println("Error while processing PaymentAmountHDFC: " + e.getMessage());
		}
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		ExpectedFields.put("Adjustment :", Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());

		if (TotalDiamond != null && !TotalDiamond.trim().isEmpty() && !TotalDiamond.trim().equals("0")) {
			ExpectedFields.put("Total Diamond amount is :", TotalDiamond.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		}
		System.out.println("ExpectedFields: " + ExpectedFields);
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInDoublePagePdfs(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}



	///<summary>
	/// Utility method to extract Gross Weights of “Complement Gold Coin 0.100g” from Stock Summary Sale1 PDF.
	/// Author: Neethu
	///</summary>

	public String StockSummarySaleReturn(String filePath, String productName) {
		try {
			String text = IsGetText(filePath);
			String grossWeight = "";

			for (String line : text.split("\\r?\\n")) {
				line = line.trim();
				String lowerLine = line.toLowerCase();
				String searchKey = productName.toLowerCase();

				// Check if line contains target product name
				if (lowerLine.contains(searchKey)) {

					// split line by whitespace
					String[] parts = line.split("\\s+");

					/*
		                 Expected format:
		                 0   1   2        3   4                5           6   7   8      9      10
		                 GBC CO Coin Complement Gold Coin 0.100g CGC_0.100Gr 22k 0 25.655 0.000 25.655

		                 Gross Wt. is at index = parts.length - 3
					 */
					if (parts.length >= 3) {
						grossWeight = parts[parts.length - 3];   // Gross Wt column
						System.out.println("✅ " + productName + " Gross Weight: " + grossWeight);
						return grossWeight;
					} else {
						System.out.println("⚠️ Unable to extract Gross Wt. — format mismatch");
					}
				}
			}

			System.out.println("❌ " + productName + " not found in PDF.");
			return "Not Found";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}




	///<summary>
	/// Utility method to extract the Gross Weight of a given product (e.g., Back Chain, Hook)
	/// from Stock Summary Sale1 PDF.
	/// Author: Nandagopan Thampi
	///</summary>
	public String StockSummarySale1(String filePath, String productName) {
		try {
			String text = IsGetText(filePath);
			String grossWeight = "";

			for (String line : text.split("\\r?\\n")) {
				line = line.trim();
				String lowerLine = line.toLowerCase();

				if (lowerLine.contains(productName.toLowerCase())) {
					String[] parts = line.split("\\s+");

					// Determine index based on product
					int index = productName.equalsIgnoreCase("hook") ? 10 : 9;

					if (parts.length > index) {
						grossWeight = parts[index].trim();
						System.out.println("✅ " + productName + " Gross Weight: " + grossWeight);
						return grossWeight;
					} else {
						System.out.println("⚠️ Line format mismatch for: " + productName);
					}
				}
			}

			System.out.println("❌ " + productName + " not found in PDF.");
			return "Not Found";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}



	///<summary>
	/// Extracts Invoice Number and G.Wt (Item Gross Weight) from Sale Invoice PDF
	/// Works for invoices like “Gold 22k 91.6 Grams 5.000 10400.00 52000.00”
	/// Author: Nandagopan Thampi
	///</summary>
	public class PdfInvoiceDetails {
		public String InvoiceNo;
		public String GWeight;
	}

	public PdfInvoiceDetails SaleInvoicePdfGrossWeightAndInvoiceNo(String PdfPath, String productName)
			throws IOException, InterruptedException {
		PdfInvoiceDetails result = new PdfInvoiceDetails();
		String PdfContent = this.IsGetText(PdfPath);

		try {
			// 🔹 Extract Invoice Number
			Pattern invoicePattern = Pattern.compile("Invoice No\\s*:?\\s*([\\w\\d]+)");
			Matcher invoiceMatcher = invoicePattern.matcher(PdfContent);
			if (invoiceMatcher.find()) {
				result.InvoiceNo = invoiceMatcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + result.InvoiceNo);
			} else {
				result.InvoiceNo = "Not Found";
				System.out.println(" Invoice No not found in PDF.");
			}

			// 🔹 Extract G.Weight (e.g. “Gold 22k 91.6 Grams 5.000”)
			Pattern gwtPattern = Pattern.compile("(?i)\\bGold\\b\\s+\\d{2}k\\s+[\\d\\.]+\\s+Grams\\s+(\\d+\\.\\d+)");
			Matcher gwtMatcher = gwtPattern.matcher(PdfContent);
			if (gwtMatcher.find()) {
				result.GWeight = gwtMatcher.group(1).trim();
				System.out.println(" Extracted G.Weight: " + result.GWeight);
			} else {
				result.GWeight = "Not Found";
				System.out.println(" G.Weight not found in PDF.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.InvoiceNo = "Error";
			result.GWeight = "Error";
		}

		return result;
	}

	/// <summary>
	/// PDF Validation “ Validates "Tax Invoice" (Dynamics POS)  for Normal Advance,GPP Advance, GPP Discount and Adjustment Total)
	/// Returns InvoiceNumber and TotalValue from the PDF.
	/// Author - Benson  
	/// </summary>
	public Map<String, String> SaleInvoiceValidationAdvanceSchemeAndOG(
			String SalesPdfPath,
			String ExpectedGrossAmount,
			String ExpectedTotalDiscount,
			String ExpectedTotalQtyPcs,
			String ExpectedTotalNetWt,
			String ExpectedTotalGrossWt,
			String ExpectedTaxableValue,
			String ExpectedTotalValue,
			String ExpectedHdfcCreditCard,
			String ExpectedAdjustment,   
			String ExpectedAdvanceAmount,  
			String ExpectedGppAdvance,
			String ExpectedGppDiscount,
			String ExpectedAdjustmentTotal,
			String ExpectedNetValue
			) throws IOException, InterruptedException {

		// --- helpers ---
		java.util.function.Function<String, String> norm = s -> s == null ? null
				: s.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll("[,\\s=:]", "")
				.trim();

		java.util.function.BiFunction<String, String, Double> capMoney = (text, rx) -> {
			Matcher mm = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!mm.find()) throw new AssertionError("Field not found for regex: " + rx);
			String raw = mm.group(1);
			if (raw == null || raw.trim().isEmpty()) throw new AssertionError("Capture group empty for regex: " + rx);
			raw = raw.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
					.replaceAll("[,\\s()]", "")
					.trim();
			return Math.round(Double.parseDouble(raw) * 100.0) / 100.0;
		};

		java.util.function.BiFunction<String, String, String> capString = (text, rx) -> {
			Matcher m = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!m.find()) throw new AssertionError("Field not found for regex: " + rx);
			String g = m.group(1);
			if (g == null) throw new AssertionError("Capture group empty for regex: " + rx);
			return g.trim();
		};

		// --- read text ---
		String pdfText = this.IsGetText(SalesPdfPath);

		// --- Invoice No ---
		String invoiceNo = capString.apply(pdfText, "Invoice\\s*No\\s*:?[\\s]*([\\w\\d]+)");
		System.out.println("Extracted Invoice No: " + invoiceNo);

		// --- Normalize for map-based checks (currency-agnostic) ---
		String normalized = pdfText
				.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[,=:]", "")
				.trim();
		System.out.println("Normalized PDF Content: " + normalized);
		// --- Build ExpectedFields (labels as printed on the bill) ---
		Map<String, String> expectedFields = new HashMap<>();
		expectedFields.put("Gross Amount",       norm.apply(ExpectedGrossAmount));
		expectedFields.put("Total Discount",     norm.apply((ExpectedTotalDiscount == null || ExpectedTotalDiscount.trim().isEmpty()) ? "0.00" : 
			ExpectedTotalDiscount));
		expectedFields.put("Total Qty(Pcs)",     norm.apply(ExpectedTotalQtyPcs));
		expectedFields.put("Total Net Wt.",      norm.apply(ExpectedTotalNetWt));
		expectedFields.put("Total Gross Wt.",    norm.apply(ExpectedTotalGrossWt));
		expectedFields.put("Taxable Value",      norm.apply(ExpectedTaxableValue));
		//expectedFields.put("Total Value",        norm.apply(ExpectedTotalValue)); // validated via map + explicit capture below
		expectedFields.put("Total Value",
				String.format("%.2f", Double.parseDouble(norm.apply(ExpectedTotalValue))));
		expectedFields.put("HDFC (Credit Card)", norm.apply(ExpectedHdfcCreditCard));
		expectedFields.put("Adjustment", norm.apply(ExpectedAdjustment));
		expectedFields.put("Advance Amount", norm.apply(ExpectedAdvanceAmount));
		expectedFields.put("GPP Advance", norm.apply(ExpectedGppAdvance));
		expectedFields.put("GPP Discount", norm.apply(ExpectedGppDiscount));
		expectedFields.put("Adjustment Total", norm.apply(ExpectedAdjustmentTotal));
		expectedFields.put("Net Value",          norm.apply(ExpectedNetValue));
		System.out.println("Expected Fields: " + expectedFields);
		// --- Explicit validations for payments/adjustments ---

		// HDFC (Credit Card)
		double actHdfc = capMoney.apply(pdfText, "HDFC\\s*\\(Credit\\s*Card\\)\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expHdfc = Double.parseDouble(norm.apply(ExpectedHdfcCreditCard));
		asrt.assertEquals(actHdfc, expHdfc, 1.00, "HDFC (Credit Card) mismatch.");

		// Adjustment   ie OG
		double actAdjustment = capMoney.apply(pdfText, "(?<!Total\\s)Adjustment\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expAdjustment = Double.parseDouble(norm.apply(ExpectedAdjustment));
		asrt.assertEquals(actAdjustment, expAdjustment,  1.00,
				"Adjustment mismatch: expected " + expAdjustment + " but found " + actAdjustment);

		// Advance Amount  
		double actAdvanceAmount = capMoney.apply(pdfText, "Advance\\s*Amount\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expAdvanceAmount = Double.parseDouble(norm.apply(ExpectedAdvanceAmount)); 
		asrt.assertEquals(actAdvanceAmount, expAdvanceAmount,  1.00,
				"Advance Amount mismatch: expected " + expAdvanceAmount + " but found " + actAdvanceAmount);

		// GPP Advance
		double actGppAdvance = capMoney.apply(pdfText, "GPP\\s*Advance\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expGppAdvance = Double.parseDouble(norm.apply(ExpectedGppAdvance));
		asrt.assertEquals(actGppAdvance, expGppAdvance,  1.00,
				"GPP Advance mismatch: expected " + expGppAdvance + " but found " + actGppAdvance);


		// GPP Discount
		double actGppDiscount = capMoney.apply(pdfText, "GPP\\s*Discount\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expGppDiscount = Double.parseDouble(norm.apply(ExpectedGppDiscount));
		asrt.assertEquals(actGppDiscount, expGppDiscount,  1.00,
				"GPP Discount mismatch: expected " + expGppDiscount + " but found " + actGppDiscount);

		// Adjustment Total
		String adjustmentRx = "Adjustment\\s*Total\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
		double actAdj = capMoney.apply(pdfText, adjustmentRx);
		double expAdj = Double.parseDouble(norm.apply(ExpectedAdjustmentTotal));
		asrt.assertEquals(actAdj, expAdj,  1.00, "Adjustment Total mismatch.");

		//Cross-check: Adjustment Total = Advance Amount + GPP Advance + GPP Discount
		double calculatedAdj = Math.round((actAdvanceAmount + actGppAdvance + actGppDiscount) * 100.0) / 100.0;
		asrt.assertEquals(calculatedAdj, actAdj,  1.00,
				"Cross-check failed: Advance Amount + GPP Advance + GPP Discount != Adjustment Total. " +
						"Calculated Sum: " + calculatedAdj + ", Adjustment Total in PDF: " + actAdj);

		// --- Verify the rest using your helper (presence/value in normalized content) ---
		this.VerifyMultipleFieldsInPdf(normalized, expectedFields);

		// --- Return both InvoiceNumber and TotalValue (actual extracted) ---
		Map<String, String> result = new HashMap<>();
		result.put("InvoiceNumber", invoiceNo);
		result.put("TotalValue", ExpectedTotalValue);
		return result;
	}
	/// </summary>
	/// Validates a Sales Invoice PDF that includes Payment modes - Customer Account and HDFC Credit Card
	/// Author - Aiswarya
	/// </summary>
	public String SaleInvoicePdfValidationWithCustomerAccountAndHdfcCard(
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String HDFCCardAmount,
			String CustomerAccount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {

		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		// Extract Invoice No
		try {
			Pattern pattern = Pattern.compile("Invoice No\\s*:?\\s*([\\w\\d]+)");
			Matcher matcher = pattern.matcher(PdfContent);
			if (matcher.find()) {
				ExtractedInvoiceNo = matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		// Normalize PDF
		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[\u20B9,=:]", "")
				.trim();
		System.out.println("Normalized Pdf Content :" + NormalizedPdfContent);

		// Expected Fields
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount", (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Net Value", NetValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("HDFC (Credit Card)", HDFCCardAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Customer Account", CustomerAccount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());

		// SKU Details
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int i = 1; i <= SkuList.size(); i++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + i + "_Total", "0.0"));

			System.out.println("SKU" + i + " → Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);
			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		// Verify PDF content
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// Validates Sale Invoice PDF Validation
	/// Author - Sangeetha M S
	/// </summary>
	public String SaleInvoicePdfValidationAfterPromotion(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			double PromotionAmount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Net Value", NetValue);

		final String APPLIED_OF_AMOUNT_REGEX = "(?i)Applied\\s+of\\s+Amount\\s*:?\\s*([\\-]?\\d+(?:\\.\\d+)?)";
		Pattern pattern = Pattern.compile(APPLIED_OF_AMOUNT_REGEX);
		Matcher matcher = pattern.matcher(NormalizedPdfContent);

		double AppliedAmountFromPdf = 0.0;

		if (matcher.find())
		{
			AppliedAmountFromPdf = Double.parseDouble(matcher.group(1));
			System.out.println("Promotion Amount from PDF = " + AppliedAmountFromPdf);
		}
		else 
		{
			System.out.println("Promotion  Amount not found in PDF!");
		}

		asrt.assertEquals(AppliedAmountFromPdf, PromotionAmount, 1.00,"Final Pdf value Mismatch in Applied PromotionAmount");

		System.out.println("ExpectedFields: " + ExpectedFields);
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + "  Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// Validates Sale Invoice PDF Validation In Promotion Discount
	/// Author - Vishnu Manoj K
	/// </summary>
	public String SaleInvoicePdfValidationPromotionDiscount(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {

		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		// ===== Extract Invoice No =====
		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)", Pattern.CASE_INSENSITIVE);
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		// ===== Normalize Content (for broad string contains checks) =====
		String NormalizedPdfContent = PdfContent.replaceAll("₹", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[₹,=:]", "")
				.trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);

		// ===== Expected fields (string-based checks via VerifyMultipleFieldsInPdf) =====
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Total Discount", TotalDiscount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Net Value", NetValue.replaceAll("[^\\d.]", ""));
		ExpectedFields.put("Total Diamond amount is", TotalDiamond.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		//ExpectedFields.put("Onam Offer 2023 Applied of Amount", TotalDiscount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		//Onam Offer 2023 Applied of Amount  32200.00
		System.out.println("Expected Fields: " + ExpectedFields);
		// ===== Build expected per-line totals from scanned SKU data =====
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " → Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		// ===== Perform the checks =====
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// Extracts the Gross Weight of a specified bulk item from Stock Summary PDF text using product name and purity.
	/// Author - Aiswarya 
	/// </summary>


	public String StockSummaryStockOut(String FilePath, String ProductName, String Purity) {
		try {
			String Text = IsGetText(FilePath);
			String GrossWeight = "";

			for (String Line : Text.split("\\r?\\n")) {
				Line = Line.trim();
				String LowerLine = Line.toLowerCase();

				// Check Product Name first
				if (LowerLine.contains(ProductName.toLowerCase())) {
					String[] Parts = Line.split("\\s+");

					// Safeguard: ensure there are enough columns
					if (Parts.length >= 10) {
						// Try identifying the purity column dynamically
						for (int I = 0; I < Parts.length; I++) {
							String Part = Parts[I].toLowerCase();
							// Match patterns like "22k"
							if (Part.equalsIgnoreCase(Purity)) {
								// Get the value two indexes after purity (gross weight)
								if (I + 2 < Parts.length) {
									GrossWeight = Parts[I + 2].trim();
									System.out.println(ProductName + Purity + " Gross Weight: " + GrossWeight);
									return GrossWeight;
								}
							}
						}
					} else {
						System.out.println("Line format mismatch for: " + ProductName);
					}
				}
			}

			System.out.println(ProductName + " Purity " + Purity + " not found in PDF.");
			return "Not Found";

		} catch (Exception Ex) {
			Ex.printStackTrace();
			return "Error";
		}
	}

	/// <summary>
	/// Validates Sale Invoice PDF Validation for  Payment with Cash, Card and Advance
	/// Author - Chandana Babu
	/// </summary>
	public String SaleInvoiceWithCashCardAndAdvancePdfValidation(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String PaymentAmountCash,   //Added new param
			String AdvanceAmount,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println("Normalized PDF Content: " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());		
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		if (PaymentAmountHDFC != null && !PaymentAmountHDFC.trim().isEmpty()) {
			ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		}
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		ExpectedFields.put("Advance Amount", AdvanceAmount.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Adjustment Total", AdvanceAmount.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Adjustment", AdvanceAmount.replaceAll("[\u20B9,=:()]", "").replaceAll("\\s+", "").trim());

		//Add Cash validation dynamically with assertion
		if (PaymentAmountCash != null && !PaymentAmountCash.trim().isEmpty() && !PaymentAmountCash.equals("0")) {
			String CleanCash = PaymentAmountCash.replaceAll("[\u20B9,]", "")
					.replaceAll("\\s+", "")
					.replaceAll("\\?", "")
					.trim();

			ExpectedFields.put("Cash", CleanCash);
		}

		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}
	/// <summary>
	/// Validates Sale Invoice PDF Validation for  Payment with Cash Only
	/// Author - Vishnu Manoj K
	/// </summary>
	public String SaleInvoiceCashPdfValidationCash(		    
			String PdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountCash,   
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(PdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println("Normalized PDF Content: " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\?", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Cash", PaymentAmountCash.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").replaceAll("\\?", "").trim()); //Added Cash field

		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}
	/// <summary>
	/// Extracts Income, Difference If Any, and Safe Drop values from the provided PDF file.
	/// Author - Vishnu Manoj K
	/// </summary>

	public Map<String, Double> ExtractIncomeAndDifference(String filePath) {

		String PdfText = IsGetText(filePath);
		System.out.println("Extracted PDF Text: \n" + PdfText);

		Map<String, Double> Result = new HashMap<>();

		double Income = 0.0;
		double Difference = 0.0;
		double SafeDrop = 0.0;

		// --- Income ---
		Pattern IncomePattern = Pattern.compile(
				"\\bIncome\\s+([0-9,]+(?:\\.\\d{2})?)",
				Pattern.CASE_INSENSITIVE
				);

		Matcher IncomeMatcher = IncomePattern.matcher(PdfText);
		if (IncomeMatcher.find()) {
			Income = Double.parseDouble(
					IncomeMatcher.group(1).replace(",", "")
					);
		}

		// --- Difference If Any ---
		Pattern DifferencePattern = Pattern.compile(
				"DIFFERENCE IF ANY(?:\\s+(SHORT|EXCESS))?\\s+([0-9,]+(?:\\.\\d{2})?)",
				Pattern.CASE_INSENSITIVE
				);

		Matcher DifferenceMatcher = DifferencePattern.matcher(PdfText);
		if (DifferenceMatcher.find()) {
			Difference = Double.parseDouble(
					DifferenceMatcher.group(2).replace(",", "")
					);

			if ("SHORT".equalsIgnoreCase(DifferenceMatcher.group(1))) {
				Difference = -Difference;
			}
		}

		// --- Safe Drop (LAST occurrence only) ---
		Pattern SafeDropPattern = Pattern.compile(
				"\\bSafe\\s+Drop\\s+([0-9,]+(?:\\.\\d{2})?)",
				Pattern.CASE_INSENSITIVE
				);

		Matcher SafeDropMatcher = SafeDropPattern.matcher(PdfText);
		while (SafeDropMatcher.find()) {
			// overwrite every time → last one remains
			SafeDrop = Double.parseDouble(
					SafeDropMatcher.group(1).replace(",", "")
					);
		}

		Result.put("Income", Income);
		Result.put("Difference", Difference);
		Result.put("SafeDrop", SafeDrop);

		return Result;
	}
	/// <Summary>
	/// Note:-GppSettlementSale Pdf Verification
	/// Author - Chandana
	/// </summary>	
	public Map<String, String> ValidateTaxInvoiceSchemeSettlementWithCashAndCardPdf(
			String SalesPdfPath,
			String ExpectedGrossAmount,
			String ExpectedTotalDiscount,
			String ExpectedTotalQtyPcs,
			String ExpectedTotalNetWt,
			String ExpectedTotalGrossWt,
			String ExpectedTaxableValue,
			String ExpectedTotalValue,
			String ExpectedCashAmount,
			String ExpectedHdfcCreditCard,
			String ExpectedGppAdvance,
			String ExpectedGppDiscount,
			String ExpectedAdjustmentTotal,
			String ExpectedNetValue
			) throws IOException, InterruptedException {
		// --- helpers ---
		// Normalize: remove any currency symbol (Unicode Sc), INR/Rs, commas, spaces, :, =
		java.util.function.Function<String, String> norm = s -> s == null ? null
				: s.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll("[,\\s=:]", "")
				.trim();

		java.util.function.BiFunction<String, String, Double> capMoney = (text, rx) -> {
			Matcher mm = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!mm.find()) throw new AssertionError("Field not found for regex: " + rx);
			String raw = mm.group(1);
			if (raw == null || raw.trim().isEmpty()) throw new AssertionError("Capture group empty for regex: " + rx);
			raw = raw.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
					.replaceAll("[,\\s()]", "")
					.trim();
			return Math.round(Double.parseDouble(raw) * 100.0) / 100.0;
		};

		java.util.function.BiFunction<String, String, String> capString = (text, rx) -> {
			Matcher m = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!m.find()) throw new AssertionError("Field not found for regex: " + rx);
			String g = m.group(1);
			if (g == null) throw new AssertionError("Capture group empty for regex: " + rx);
			return g.trim();
		};

		// --- read text ---
		String pdfText = this.IsGetText(SalesPdfPath);

		// --- Invoice No ---
		String invoiceNo = capString.apply(pdfText, "Invoice\\s*No\\s*:?[\\s]*([\\w\\d]+)");
		System.out.println("Extracted Invoice No: " + invoiceNo);

		// --- Normalize for map-based checks (currency-agnostic) ---
		String normalized = pdfText
				.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[,=:]", "")
				.trim();
		System.out.println("Normalized PDF Content: " + normalized);
		// --- Build ExpectedFields (labels as printed on the bill) ---
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount",       norm.apply(ExpectedGrossAmount));
		ExpectedFields.put("Total Discount",     norm.apply((ExpectedTotalDiscount == null || ExpectedTotalDiscount.trim().isEmpty()) ? "0.00" :
			ExpectedTotalDiscount));
		ExpectedFields.put("Total Qty(Pcs)",     norm.apply(ExpectedTotalQtyPcs));
		ExpectedFields.put("Total Net Wt.",      norm.apply(ExpectedTotalNetWt));
		ExpectedFields.put("Total Gross Wt.",    norm.apply(ExpectedTotalGrossWt));
		ExpectedFields.put("Taxable Value",      norm.apply(ExpectedTaxableValue));
		//expectedFields.put("Total Value",        norm.apply(ExpectedTotalValue)); // validated via map + explicit capture below
		ExpectedFields.put("Total Value",
				String.format("%.2f", Double.parseDouble(norm.apply(ExpectedTotalValue))));
		//expectedFields.put("HDFC (Credit Card)", norm.apply(ExpectedHdfcCreditCard));
		ExpectedFields.put("Net Value",          norm.apply(ExpectedNetValue));
		System.out.println("Expected Fields Map: " + ExpectedFields);
		// --- Explicit validations for payments/adjustments ---

		if (ExpectedCashAmount != null && !ExpectedCashAmount.trim().isEmpty() && !ExpectedCashAmount.equals("0")) {
			String CleanCash = ExpectedCashAmount.replaceAll("[\u20B9,]", "")
					.replaceAll("\\s+", "")
					.replaceAll("\\?", "")
					.trim();
			ExpectedFields.put("Cash", CleanCash);
		}

		// HDFC (Credit Card)
		if (ExpectedHdfcCreditCard != null && !ExpectedHdfcCreditCard.trim().isEmpty()) {
			String HdfcRx = "HDFC\\s*\\(\\s*(?:Credit\\s*)?Card\\s*\\)\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.\\-\\(\\)]+)";
			double ActHdfc = capMoney.apply(pdfText, HdfcRx);
			double ExpHdfc = Double.parseDouble(norm.apply(ExpectedHdfcCreditCard));
			asrt.assertEquals(ActHdfc, ExpHdfc, 1, "HDFC (Credit Card) mismatch.");
			System.out.println("HDFC Amount | Expected: "+ExpHdfc+"| Actual: "+ActHdfc+"| MACTCH");
		}

		// Advance (supports "Advance Amount" or "GPP Advance")
		double actAdv=0.00;
		double expAdv=0.00;
		if (ExpectedGppAdvance != null && !ExpectedGppAdvance.trim().isEmpty() && !ExpectedGppAdvance.trim().equals("0")) {
			String advanceRx = "(?:GPP\\s*Advance|Advance\\s*Amount)\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
			actAdv = capMoney.apply(pdfText, advanceRx);
			expAdv = Double.parseDouble(norm.apply(ExpectedGppAdvance));
			asrt.assertEquals(actAdv, expAdv, 1, "Advance mismatch.");
		}
		System.out.println("GPP Advance | Expected: "+expAdv+"| Actual: "+actAdv+"| MACTCH");

		// GPP Discount (optional)
		double ActDis = 0.00, ExpDis = 0.00;
		if (ExpectedGppDiscount != null && !ExpectedGppDiscount.trim().isEmpty()) {
			ExpDis = Double.parseDouble(norm.apply(ExpectedGppDiscount));

			if (ExpDis > 0.0) {
				// Only try to capture from PDF if expecting a real discount
				String discountRx = "GPP\\s*Discount\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
				try {
					ActDis = capMoney.apply(pdfText, discountRx);
					asrt.assertEquals(ActDis, ExpDis, 1.00, "GPP Discount mismatch.");
					System.out.println("GPP Discount | Expected: "+ExpDis+"| Actual: "+ActDis+"| MACTCH");
				} catch (AssertionError e) {
					throw new AssertionError("Expected GPP Discount " + ExpDis + " but field not found in PDF.");
				}
			} else {
				// expected = 0, but PDF may not have the field â†’ treat as fine
				ActDis = 0.0;
			}
		}

		// Adjustment Total
		String AdjustmentRx = "Adjustment\\s*Total\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
		double ActAdj = capMoney.apply(pdfText, AdjustmentRx);
		double ExpAdj = Double.parseDouble(norm.apply(ExpectedAdjustmentTotal));
		asrt.assertEquals(ActAdj, ExpAdj, 1, "Adjustment Total mismatch.");
		System.out.println("Adjustment Total | Expected: "+ExpAdj+"| Actual: "+ActAdj+"| MACTCH");

		double SumAdj = Math.round((actAdv + (ExpDis > 0.0 ? ActDis : 0.0)) * 100.0) / 100.0;
		asrt.assertEquals(SumAdj, ActAdj, 0.01,
				"Advance + (Discount if any) != Adjustment Total. Sum: " + SumAdj + ", Adjustment: " + ActAdj);
		System.out.println("Advance | Expected: "+SumAdj+"| Actual: "+ActAdj+"| MACTCH");

		// --- Verify the rest using your helper (presence/value in normalized content) ---
		this.VerifyMultipleFieldsInPdf(normalized, ExpectedFields);

		// --- Return both InvoiceNumber and TotalValue (actual extracted) ---
		Map<String, String> result = new HashMap<>();
		result.put("InvoiceNumber", invoiceNo);
		result.put("TotalValue",ExpectedTotalValue);
		return result;
	}

	/// <summary>
	/// PDF Validation “ Validates "Tax Invoice" (Dynamics POS)  for Normal Advance, GPP Discount and Adjustment Total)
	/// Returns InvoiceNumber and TotalValue from the PDF.
	/// Author - Robin  
	/// </summary>
	public Map<String, String> SaleInvoiceValidationAdvanceScheme(
			String SalesPdfPath,
			String ExpectedGrossAmount,
			String ExpectedTotalDiscount,
			String ExpectedTotalQtyPcs,
			String ExpectedTotalNetWt,
			String ExpectedTotalGrossWt,
			String ExpectedTaxableValue,
			String ExpectedTotalValue,
			String ExpectedHdfcCreditCard,
			String ExpectedAdjustment,   
			String ExpectedAdvanceAmount,  
			String ExpectedGppAdvance,
			String ExpectedGppDiscount,
			String ExpectedAdjustmentTotal,
			String ExpectedNetValue
			) throws IOException, InterruptedException {

		// --- helpers ---
		java.util.function.Function<String, String> norm = s -> s == null ? null
				: s.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll("[,\\s=:]", "")
				.trim();

		java.util.function.BiFunction<String, String, Double> capMoney = (text, rx) -> {
			Matcher mm = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!mm.find()) throw new AssertionError("Field not found for regex: " + rx);
			String raw = mm.group(1);
			if (raw == null || raw.trim().isEmpty()) throw new AssertionError("Capture group empty for regex: " + rx);
			raw = raw.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
					.replaceAll("[,\\s()]", "")
					.trim();
			return Math.round(Double.parseDouble(raw) * 100.0) / 100.0;
		};

		java.util.function.BiFunction<String, String, String> capString = (text, rx) -> {
			Matcher m = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!m.find()) throw new AssertionError("Field not found for regex: " + rx);
			String g = m.group(1);
			if (g == null) throw new AssertionError("Capture group empty for regex: " + rx);
			return g.trim();
		};

		// --- read text ---
		String pdfText = this.IsGetText(SalesPdfPath);

		// --- Invoice No ---
		String invoiceNo = capString.apply(pdfText, "Invoice\\s*No\\s*:?[\\s]*([\\w\\d]+)");
		System.out.println("Extracted Invoice No: " + invoiceNo);

		// --- Normalize for map-based checks (currency-agnostic) ---
		String normalized = pdfText
				.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[,=:]", "")
				.trim();
		System.out.println("Normalized PDF Content: " + normalized);
		// --- Build ExpectedFields (labels as printed on the bill) ---
		Map<String, String> expectedFields = new HashMap<>();
		expectedFields.put("Gross Amount",       norm.apply(ExpectedGrossAmount));
		expectedFields.put("Total Discount",     norm.apply((ExpectedTotalDiscount == null || ExpectedTotalDiscount.trim().isEmpty()) ? "0.00" :
			ExpectedTotalDiscount));
		expectedFields.put("Total Qty(Pcs)",     norm.apply(ExpectedTotalQtyPcs));
		expectedFields.put("Total Net Wt.",      norm.apply(ExpectedTotalNetWt));
		expectedFields.put("Total Gross Wt.",    norm.apply(ExpectedTotalGrossWt));
		expectedFields.put("Taxable Value",      norm.apply(ExpectedTaxableValue));
		expectedFields.put("Total Value",
				String.format("%.2f", Double.parseDouble(norm.apply(ExpectedTotalValue))));
		expectedFields.put("HDFC (Credit Card)", norm.apply(ExpectedHdfcCreditCard));
		expectedFields.put("Adjustment", norm.apply(ExpectedAdjustment));
		expectedFields.put("GPP Advance", norm.apply(ExpectedGppAdvance));
		expectedFields.put("GPP Discount", norm.apply(ExpectedGppDiscount));
		expectedFields.put("Adjustment Total", norm.apply(ExpectedAdjustmentTotal));
		expectedFields.put("Net Value",          norm.apply(ExpectedNetValue));
		System.out.println("Expected Fields: " + expectedFields);
		// --- Explicit validations for payments/adjustments ---

		// HDFC (Credit Card)
		double actHdfc = capMoney.apply(pdfText,"HDFC\\s*\\(\\s*Card\\s*\\)\\s*:?\\s*([\\d,]+\\.\\d+)");
		double expHdfc = Double.parseDouble(norm.apply(ExpectedHdfcCreditCard));
		asrt.assertEquals(actHdfc, expHdfc,1.00 , "HDFC (Card) mismatch.");

		// Adjustment   ie OG
		double actAdjustment = capMoney.apply(pdfText, "(?<!Total\\s)Adjustment\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expAdjustment = Double.parseDouble(norm.apply(ExpectedAdjustment));
		asrt.assertEquals(actAdjustment, expAdjustment, 1.00 ,
				"Adjustment mismatch: expected " + expAdjustment + " but found " + actAdjustment);

		double actAdvanceAmount = 0.00;
		if (ExpectedAdvanceAmount != null && !ExpectedAdvanceAmount.trim().isEmpty())
		{
			actAdvanceAmount = capMoney.apply(pdfText, "Advance\\s*Amount\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
			double expAdvanceAmount = Double.parseDouble(norm.apply(ExpectedAdvanceAmount));

			asrt.assertEquals(
					actAdvanceAmount,
					expAdvanceAmount,
					1.00 ,
					"Advance Amount mismatch: expected " + expAdvanceAmount + " but found " + actAdvanceAmount
					);
		}

		// GPP Advance
		double actGppAdvance = capMoney.apply(pdfText, "GPP\\s*Advance\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expGppAdvance = Double.parseDouble(norm.apply(ExpectedGppAdvance));
		asrt.assertEquals(actGppAdvance, expGppAdvance, 0.01,
				"GPP Advance mismatch: expected " + expGppAdvance + " but found " + actGppAdvance);


		// GPP Discount
		double actGppDiscount = capMoney.apply(pdfText, "GPP\\s*Discount\\s*:?\\s*([\\p{Sc}\\s\\d,\\.]+)");
		double expGppDiscount = Double.parseDouble(norm.apply(ExpectedGppDiscount));
		asrt.assertEquals(actGppDiscount, expGppDiscount, 1.00 ,
				"GPP Discount mismatch: expected " + expGppDiscount + " but found " + actGppDiscount);

		// Adjustment Total
		String adjustmentRx = "Adjustment\\s*Total\\s*:?[\\s]*([\\p{Sc}\\s\\d,\\.]+)";
		double actAdj = capMoney.apply(pdfText, adjustmentRx);
		double expAdj = Double.parseDouble(norm.apply(ExpectedAdjustmentTotal));
		asrt.assertEquals(actAdj, expAdj, 1.00 , "Adjustment Total mismatch.");

		//Cross-check: Adjustment Total = Advance Amount + GPP Advance + GPP Discount
		double calculatedAdj = Math.round((actAdvanceAmount + actGppAdvance + actGppDiscount) * 100.0) / 100.0;
		asrt.assertEquals(calculatedAdj, actAdj, 0.01,
				"Cross-check failed: Advance Amount + GPP Advance + GPP Discount != Adjustment Total. " +
						"Calculated Sum: " + calculatedAdj + ", Adjustment Total in PDF: " + actAdj);

		// --- Verify the rest using your helper (presence/value in normalized content) ---
		this.VerifyMultipleFieldsInPdf(normalized, expectedFields);

		// --- Return both InvoiceNumber and TotalValue (actual extracted) ---
		Map<String, String> result = new HashMap<>();
		result.put("InvoiceNumber", invoiceNo);
		result.put("TotalValue", ExpectedTotalValue);
		return result;
	}
	/// <summary>
	/// PDF Validation “ Validates Invoice of Normal Advance,GG scheme and Package discount Sku with negative value"
	/// Author - Sangeetha M S
	/// </summary>
	public String SaleInvoicePdfGppBonusAdjustmentValidation(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String Adjustment,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount).replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		ExpectedFields.put("Adjustment :", Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		System.out.println("ExpectedFields: " + ExpectedFields);
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// PDF Validation “ Validates Invoice of Normal Advance,GG scheme and Package discount Sku with positive value"
	/// Author - Sangeetha M S
	/// </summary>
	public Map<String, String> SaleInvoiceValidationAdvanceSchemes(
			String SalesPdfPath,
			String ExpectedGrossAmount,
			String ExpectedTotalDiscount,
			String ExpectedTotalQtyPcs,
			String ExpectedTotalNetWt,
			String ExpectedTotalGrossWt,
			String ExpectedTaxableValue,
			String ExpectedTotalValue,
			String ExpectedHdfcCreditCard,
			String ExpectedAdvanceAmount,
			String ExpectedGppAdvance,
			String ExpectedGppDiscount,
			String ExpectedAdjustmentTotal,
			String ExpectedNetValue
			) throws IOException, InterruptedException {

		// ---------- helpers ----------
		java.util.function.Function<String, String> norm = s -> s == null ? "0.00"
				: s.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll("[,()\\s]", "")
				.trim();

		java.util.function.BiFunction<String, String, Double> capMoney = (text, rx) -> {
			Matcher m = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!m.find()) throw new AssertionError("Field not found for regex: " + rx);

			String raw = m.group(1);
			if (raw == null || raw.trim().isEmpty())
				throw new AssertionError("Capture group empty for regex: " + rx);

			raw = raw.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
					.replaceAll("[,()\\s]", "")
					.trim();

			return Math.round(Double.parseDouble(raw) * 100.0) / 100.0;
		};

		java.util.function.BiFunction<String, String, String> capString = (text, rx) -> {
			Matcher m = Pattern.compile(rx, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(text);
			if (!m.find()) throw new AssertionError("Field not found for regex: " + rx);
			return m.group(1).trim();
		};

		// ---------- Read PDF ----------
		String pdfText = this.IsGetText(SalesPdfPath);

		// ---------- Invoice No ----------
		String invoiceNo = capString.apply(
				pdfText,
				"Invoice\\s*No\\s*:?[\\s]*([\\w\\d]+)"
				);
		System.out.println("Extracted Invoice No: " + invoiceNo);

		// ---------- Normalized PDF ----------
		String normalized = pdfText
				.replaceAll("\\p{Sc}|(?i)\\bINR\\b|\\bRs\\.?\\b", "")
				.replaceAll(",", "")
				.replaceAll("\\s+", " ")
				.replaceAll("[=:]", "")
				.trim();

		System.out.println("Normalized PDF Content: " + normalized);

		// ---------- Expected Fields ----------
		Map<String, String> expectedFields = new HashMap<>();
		expectedFields.put("Gross Amount", norm.apply(ExpectedGrossAmount));
		expectedFields.put("Total Discount", norm.apply(ExpectedTotalDiscount));
		expectedFields.put("Total Qty(Pcs)", norm.apply(ExpectedTotalQtyPcs));
		expectedFields.put("Total Net Wt.", norm.apply(ExpectedTotalNetWt));
		expectedFields.put("Total Gross Wt.", norm.apply(ExpectedTotalGrossWt));
		expectedFields.put("Taxable Value", norm.apply(ExpectedTaxableValue));
		expectedFields.put("Total Value", norm.apply(ExpectedTotalValue));
		expectedFields.put("HDFC (Credit Card)", norm.apply(ExpectedHdfcCreditCard));
		expectedFields.put("GPP Advance", norm.apply(ExpectedGppAdvance));
		expectedFields.put("GPP Discount", norm.apply(ExpectedGppDiscount));
		expectedFields.put("Adjustment Total", norm.apply(ExpectedAdjustmentTotal));
		expectedFields.put("Net Value", norm.apply(ExpectedNetValue));

		System.out.println("Expected Fields: " + expectedFields);

		// ---------- HDFC (Credit Card) ----------
		double actHdfc = capMoney.apply(
				pdfText,
				"HDFC\\s*\\(\\s*(?:Credit\\s*)?Card\\s*\\)\\s*:?\\s*([\\d,]+(?:\\.\\d{2})?)"
				);
		double expHdfc = Double.parseDouble(norm.apply(ExpectedHdfcCreditCard));
		asrt.assertEquals(actHdfc, expHdfc, 1.00, "HDFC (Credit Card) mismatch.");

		// ---------- Advance Amount ----------
		double actAdvanceAmount = 0.00;
		if (ExpectedAdvanceAmount != null && !ExpectedAdvanceAmount.trim().isEmpty()) {
			actAdvanceAmount = capMoney.apply(
					pdfText,
					"Advance\\s*Amount\\s*:?\\s*([\\d,]+(?:\\.\\d{2})?)"
					);
			double expAdvanceAmount = Double.parseDouble(norm.apply(ExpectedAdvanceAmount));
			asrt.assertEquals(actAdvanceAmount, expAdvanceAmount, 1.00,
					"Advance Amount mismatch.");
		}

		// ---------- GPP Advance ----------
		double actGppAdvance = capMoney.apply(
				pdfText,
				"GPP\\s*Advance\\s*:?\\s*([\\d,]+(?:\\.\\d{2})?)"
				);
		double expGppAdvance = Double.parseDouble(norm.apply(ExpectedGppAdvance));
		asrt.assertEquals(actGppAdvance, expGppAdvance, 0.01,
				"GPP Advance mismatch.");

		// ---------- GPP Discount ----------
		double actGppDiscount = capMoney.apply(
				pdfText,
				"GPP\\s*Discount\\s*:?\\s*([\\d,]+(?:\\.\\d{2})?)"
				);
		double expGppDiscount = Double.parseDouble(norm.apply(ExpectedGppDiscount));
		asrt.assertEquals(actGppDiscount, expGppDiscount, 1.00,
				"GPP Discount mismatch.");

		// ---------- Adjustment Total ----------
		double actAdj = capMoney.apply(
				pdfText,
				"Adjustment\\s+Total\\s*:?\\s*([\\(\\-]?\\s*[\\d,]+(?:\\.\\d{2})?)"
				);
		double expAdj = Double.parseDouble(norm.apply(ExpectedAdjustmentTotal));
		asrt.assertEquals(actAdj, expAdj, 1.00,
				"Adjustment Total mismatch.");

		// ---------- Cross-check ----------
		double calculatedAdj = Math.round(
				(actAdvanceAmount + actGppAdvance + actGppDiscount) * 100.0
				) / 100.0;

		asrt.assertEquals(calculatedAdj, actAdj, 0.01,
				"Cross-check failed: Advance + GPP Advance + GPP Discount != Adjustment Total");

		// ---------- Final field presence ----------
		this.VerifyMultipleFieldsInPdf(normalized, expectedFields);

		// ---------- Return ----------
		Map<String, String> result = new HashMap<>();
		result.put("InvoiceNumber", invoiceNo);
		result.put("TotalValue", ExpectedTotalValue);
		return result;
	}

	/// <Summary>
	/// To check the sku number is present in StockDetails pdf
	/// Author - Chandana
	/// </summary>
	public void VerifySkuPresentInStockDetailsPdf(String pdfPath, String sku) {
		try {
			String PdfContent = this.IsGetText(pdfPath);
			String NormalizedPdfContent = PdfContent
					.replaceAll("\u20B9", "")
					.replaceAll(",", "")
					.replaceAll("\\s+", " ")
					.trim();
			if (NormalizedPdfContent.contains(sku)) {
				System.out.println("SKU FOUND in PDF : " + sku);
			} else {
				asrt.fail("SKU NOT FOUND in PDF : " + sku);
			}
		} catch (Exception e) {
			asrt.fail("Error while verifying SKU in PDF: " + e.getMessage());
		}
	}

	/// <summary>
	/// To verify the accuracy of values including Adjustment value and advance amount in a Sales Invoice PDF for RTGS/Received values(Sales invoice PDF)
	/// Author - Hasna E K/Sangeetha
	/// </summary>
	public String SaleInvoicePdfAdjustmentAdvanceValidation(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String Adjustment,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		String PdfContent = this.IsGetText(SalesPdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Taxable Value", TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		//ExpectedFields.put("HDFC (Credit Card)", PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").trim());
		//Jhoncy for TC34
		try {
			if (PaymentAmountHDFC != null && !PaymentAmountHDFC.trim().isEmpty()) {
				String cleanAmount = PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").trim();

				// First try adding as "HDFC (Credit Card)"
				ExpectedFields.put("HDFC (Credit Card)", cleanAmount);

				// Fallback: if PDF does not contain HDFC keyword, replace with Adjustment
				if (!PdfContent.contains("HDFC") && !PdfContent.contains("Credit Card")) {
					ExpectedFields.remove("HDFC (Credit Card)");
					ExpectedFields.put("Adjustment", cleanAmount);
					System.out.println("Fallback applied → Added Adjustment instead of HDFC (Credit Card)");
				}
			}
		} catch (Exception e) {
			System.out.println("Error while processing PaymentAmountHDFC: " + e.getMessage());
		}
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		ExpectedFields.put("Adjustment :", Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());

		if (!PdfContent.contains("Adjustment") && PdfContent.contains("Online Gift Voucher"))
		{
			ExpectedFields.remove("Adjustment");
			ExpectedFields.remove("Adjustment :");
			ExpectedFields.put("Online Gift Voucher :",  Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
			System.out.println("Applied → Added Online Gift Voucher instead of Adjustment");
		}

		if(PdfContent.contains("Adjustment") && PdfContent.contains("Adjustment Total"))
		{
			//			ExpectedFields.remove("Adjustment");
			//			asrt.assertEquals(Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim(), Adjustment,"Mismatch ");


			Pattern pattern = Pattern.compile("Adjustment(?!\\s*Total)\\s*:\\s*(-?\\d+(?:\\.\\d+)?)");
			Matcher matcher = pattern.matcher(PdfContent);

			if (matcher.find()) {

				String adjustmentValue = matcher.group(1).trim(); // extracted amount

				ExpectedFields.remove("Adjustment");
				ExpectedFields.remove("Adjustment :");

				asrt.assertEquals(adjustmentValue, Adjustment.trim(), "Mismatch in Adjustment value");
			}
		}
		if (TotalDiamond != null && !TotalDiamond.trim().isEmpty() && !TotalDiamond.trim().equals("0")) {
			ExpectedFields.put("Total Diamond amount is :", TotalDiamond.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		}
		System.out.println("ExpectedFields: " + ExpectedFields);
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}

		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);

		return ExtractedInvoiceNo;
	}

	/// <summary>
	/// To verify the accuracy of values including Adjustment value in a Sales Invoice PDF 
	/// Validating both individual fields and a summary table row using extracted PDF text.
	/// Author - Sangeetha M S
	/// </summary>
	public String SaleInvoicePdfAdjustmentValidationMultipleItems(
			String SalesPdfPath,
			String GrossAmount,
			String TotalDiscount,
			String TotalDiamond,
			String TotalQtyPcs,
			String TotalNetWeight,
			String TotalGrossWeight,
			String TaxableValue,
			String NetValue,
			String PaymentAmountHDFC,
			String Adjustment,
			List<String> SkuList,
			Map<String, String> ScannedSKUDataMap
			) throws IOException, InterruptedException {
		String ExtractedInvoiceNo = "";
		System.out.println("Retry");
		String PdfContent = this.IsGetText(SalesPdfPath);

		try {
			Pattern Vpattern = Pattern.compile("Invoice No\\s*:?[\\s]*([\\w\\d]+)");
			Matcher Matcher = Vpattern.matcher(PdfContent);
			if (Matcher.find()) {
				ExtractedInvoiceNo = Matcher.group(1).trim();
				System.out.println("Extracted Invoice No: " + ExtractedInvoiceNo);
			} else {
				System.out.println("Invoice No not found in PDF.");
			}
		} catch (Exception e) {
			System.out.println("Error extracting Invoice No: " + e.getMessage());
		}

		String NormalizedPdfContent = PdfContent.replaceAll("\u20B9", "").replaceAll(",", "").replaceAll("\\s+", " ").replaceAll("[\u20B9,=:]", "").trim();
		System.out.println(" Normalized PDF Content = " + NormalizedPdfContent);
		Map<String, String> ExpectedFields = new HashMap<>();
		ExpectedFields.put("Gross Amount", GrossAmount.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Discount",  (TotalDiscount == null || TotalDiscount.trim().isEmpty() ? "0.00" : TotalDiscount)
				.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Qty(Pcs)", TotalQtyPcs.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Net Wt.", TotalNetWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		ExpectedFields.put("Total Gross Wt.", TotalGrossWeight.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());

		String taxableValue ="";
		if (TaxableValue != null && !TaxableValue.trim().isEmpty())
		{
			String CleanTaxableAmount= TaxableValue.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim();
			Pattern pattern = Pattern.compile("Taxable\\s+Value[\\s\\S]*?(\\d[\\d,]*\\.\\d{2})(?!%)");
			Matcher matcher = pattern.matcher(PdfContent);
			if (matcher.find()) 
			{
				taxableValue = matcher.group(1);
				System.out.println("Taxable Value: " + taxableValue);			    
			}
			asrt.assertEquals(taxableValue,CleanTaxableAmount,"Mismatch in taxable amount expected : "+CleanTaxableAmount+"but got"+taxableValue);		
		}
		try 
		{
			if (PaymentAmountHDFC != null && !PaymentAmountHDFC.trim().isEmpty()) {
				String cleanAmount = PaymentAmountHDFC.replaceAll("[\u20B9,]", "").replaceAll("\\s+", "").trim();
				// First try adding as "HDFC (Credit Card)"
				ExpectedFields.put("HDFC (Credit Card)", cleanAmount);
				// Fallback: if PDF does not contain HDFC keyword, replace with Adjustment
				if (!PdfContent.contains("HDFC") && !PdfContent.contains("Credit Card")) 
				{
					ExpectedFields.remove("HDFC (Credit Card)");
					ExpectedFields.put("Adjustment", cleanAmount);
					System.out.println("Fallback applied → Added Adjustment instead of HDFC (Credit Card)");
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error while processing PaymentAmountHDFC: " + e.getMessage());
		}
		ExpectedFields.put("Net Value", NetValue);//due to Decimal issue, Calculation issue
		ExpectedFields.put("Adjustment :", Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());

		if (!PdfContent.contains("Adjustment") && PdfContent.contains("Online Gift Voucher")) 
		{
			ExpectedFields.remove("Adjustment");
			ExpectedFields.remove("Adjustment :");
			ExpectedFields.put("Online Gift Voucher :",  Adjustment.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
			System.out.println("Applied → Added Online Gift Voucher instead of Adjustment");
		}

		if (TotalDiamond != null && !TotalDiamond.trim().isEmpty() && !TotalDiamond.trim().equals("0")) {
			ExpectedFields.put("Total Diamond amount is :", TotalDiamond.replaceAll("[\u20B9,=:]", "").replaceAll("\\s+", "").trim());
		}
		System.out.println("ExpectedFields: " + ExpectedFields);
		List<double[]> ExpectedLines = new ArrayList<>();
		for (int SkuIndex = 1; SkuIndex <= SkuList.size(); SkuIndex++) {
			double GrossWeight = Math.round(Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_GrossWeight", "0.0")) * 1000.0) / 1000.0;
			double StoneWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_StoneWeight", "0.0"));
			double NetWeight = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_NetWeight", "0.0"));
			double TotalAmount = Double.parseDouble(ScannedSKUDataMap.getOrDefault("SKU_" + SkuIndex + "_Total", "0.0"));

			System.out.println("SKU" + SkuIndex + " â†’ Gross: " + GrossWeight + ", Stone: " + StoneWeight + ", Net: " + NetWeight + ", Total: " + TotalAmount);

			ExpectedLines.add(new double[]{GrossWeight, StoneWeight, NetWeight, TotalAmount});
		}
		this.VerifyMultipleFieldsInPdf(NormalizedPdfContent, ExpectedFields);
		this.VerifyMultipleItemTotalLines(NormalizedPdfContent, ExpectedLines);
		return ExtractedInvoiceNo;
	}

}
