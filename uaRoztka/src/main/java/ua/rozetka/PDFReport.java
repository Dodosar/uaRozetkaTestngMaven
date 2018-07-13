package ua.rozetka;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * PDFReport
 * 
 * @author janaudy at jyperion dot org
 */
public class PDFReport implements ITestListener {
	/**
	 * Document
	 */
	private Document document = null;
	static int b = 0;
	/**
	 * PdfPTables
	 */
	PdfPTable successTable = null, failTable = null;
	
	/**
	 * throwableMap
	 */
	private HashMap<Integer, Throwable> throwableMap = null;
	
	/**
	 * nbExceptions
	 */
	private int nbExceptions = 0;
	
	/**
	 * PDFReport
	 */
	public PDFReport() {
		log("PDFReport()");
		this.document = new Document();
		this.throwableMap = new HashMap<Integer, Throwable>();
	}
	
	/**
	 * @see com.beust.testng.ITestListener#onTestSuccess(com.beust.testng.ITestResult)
	 */
	public void onTestSuccess(ITestResult result) {
		log("onTestSuccess("+result+")");
		String file = "D://ProjectWebDriverRozetka/screenshot"+(new Random().nextInt())+ ".png";
		try {
			Page.takeSnapShot(Page.driver, file);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if (successTable == null) {
			this.successTable = new PdfPTable(new float[]{.3f, .3f, .1f, .3f});
			Paragraph p = new Paragraph("PASSED TESTS", new Font(FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(p);
			cell.setColspan(3);
			cell.setBackgroundColor(BaseColor.ORANGE);
			this.successTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Class"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.successTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Method"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.successTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Time (ms)"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.successTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Exception"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.successTable.addCell(cell);
		}
		
		PdfPCell cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(result.getMethod().toString()));
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + (result.getEndMillis()-result.getStartMillis())));
		this.successTable.addCell(cell);

		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
			this.nbExceptions++;
			Chunk imdb = new Chunk("[SREEN SHOT SUCCESS]", new Font(FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE));
			imdb.setAction(new PdfAction("file:///"+file));
			Paragraph excep = new Paragraph(
					new Chunk(throwable.toString(), 
							new Font(FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE)).
							setLocalGoto("" + throwable.hashCode()));
			cell = new PdfPCell(excep);
			this.successTable.addCell(cell);
		} else {
			this.successTable.addCell(new PdfPCell(new Paragraph("")));
		}
	}

	/**
	 * @see com.beust.testng.ITestListener#onTestFailure(com.beust.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult result) {
		log("onTestFailure("+result+")");
		String file = String.format("D://ProjectWebDriverRozetka/screenshot"+(b++)+ ".png");
		try {
			Page.takeSnapShot(Page.driver, file);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if (this.failTable == null) {
			this.failTable = new PdfPTable(new float[]{.3f, .3f, .1f, .3f});
			this.failTable.setTotalWidth(20f);
			Paragraph p = new Paragraph("FAILED TESTS", new Font(FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(p);
			cell.setColspan(4);
			cell.setBackgroundColor(BaseColor.RED);
			this.failTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Class"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.failTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Method"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.failTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Time (ms)"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.failTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Exception"));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			this.failTable.addCell(cell);
		}
		
		PdfPCell cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(result.getMethod().toString()));
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + (result.getEndMillis()-result.getStartMillis())));
		this.failTable.addCell(cell);
		//String exception = result.getThrowable() == null ? "" : result.getThrowable().toString();
		//cell = new PdfPCell(new Paragraph(exception));
		//this.failTable.addCell(cell);
		
		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
			this.nbExceptions++;
			Chunk imdb = new Chunk("[SREEN SHOT]", new Font(FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE));
			imdb.setAction(new PdfAction("file:///"+file));
			Paragraph excep = new Paragraph(
					new Chunk(throwable.toString(), 
							new Font(FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE)).
							setLocalGoto("" + throwable.hashCode()));
			cell = new PdfPCell(excep);
			this.failTable.addCell(cell);
		} else {
			this.failTable.addCell(new PdfPCell(new Paragraph("")));
		}
	}

	/**
	 * @see com.beust.testng.ITestListener#onTestSkipped(com.beust.testng.ITestResult)
	 */
	public void onTestSkipped(ITestResult result) {
		log("onTestSkipped("+result+")");
	}

	/**
	 * @see com.beust.testng.ITestListener#onStart(com.beust.testng.ITestContext)
	 */
	public void onStart(ITestContext context) {
		log("onStart("+context+")");
		try {
			PdfWriter.getInstance(this.document, new FileOutputStream(context.getName()+".pdf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.document.open();
		
		Paragraph p = new Paragraph(context.getName() + " TESTNG RESULTS",
				FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLUE));
		
		try {
			this.document.add(p);
			this.document.add(new Paragraph(new Date().toString()));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @see com.beust.testng.ITestListener#onFinish(com.beust.testng.ITestContext)
	 */
	public void onFinish(ITestContext context) {
		log("onFinish("+context+")");
		
		try {
			if (this.failTable != null) {
				log("Added fail table");
				this.failTable.setSpacingBefore(15f);
				this.document.add(this.failTable);
				this.failTable.setSpacingAfter(15f);
			}
			
			if (this.successTable != null) {
				log("Added success table");
				this.successTable.setSpacingBefore(15f);
				this.document.add(this.successTable);
				this.successTable.setSpacingBefore(15f);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		Paragraph p = new Paragraph("EXCEPTIONS SUMMARY",
				FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.BLUE));
		try {
			this.document.add(p);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		Set<Integer> keys = this.throwableMap.keySet();
		
		assert keys.size() == this.nbExceptions;
		
		for(Integer key : keys) {
			Throwable throwable = this.throwableMap.get(key);
			
			Chunk chunk = new Chunk(throwable.toString(),
					FontFactory.getFont(FontFactory.HELVETICA));
			chunk.setLocalDestination("" + key);
			Paragraph throwTitlePara = new Paragraph(chunk);
			try {
				this.document.add(throwTitlePara);
			} catch (DocumentException e3) {
				e3.printStackTrace();
			}
			
			StackTraceElement[] elems = throwable.getStackTrace();
			String exception = "";
			for(StackTraceElement ste : elems) {
				Paragraph throwParagraph = new Paragraph(ste.toString());
				try {
					this.document.add(throwParagraph);
				} catch (DocumentException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		this.document.close();
	}
	
	/**
	 * log
	 * @param o
	 */
	public static void log(Object o) {
		//System.out.println("[JyperionListener] " + o);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


}