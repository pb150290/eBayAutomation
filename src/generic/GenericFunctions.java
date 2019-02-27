package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidElement;

public class GenericFunctions extends BaseClass implements ConstantItems {
	
	public static Workbook wb;
	public static FileInputStream fileStream;
	public static FileOutputStream fileOutputStream;
	
	public static String getProperty(String CONFIG_PATH,String key) throws FileNotFoundException, IOException {
		
		String properties="";
		fileStream=new FileInputStream(CONFIG_PATH);
		Properties prop=new Properties();
		
		prop.load(fileStream);
		
		properties=prop.getProperty(key);
		
		return properties;
	}
	
	public static int getRowCount(String EXCEL_PATH, String sheet) throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
		
		int rowCount=0;
		//fileStream=new FileInputStream(EXCEL_PATH);
//		wb= new XSSFWorkbook(new File(EXCEL_PATH));
		wb= (XSSFWorkbook) WorkbookFactory.create(new File(EXCEL_PATH));
		rowCount=wb.getSheet(sheet).getLastRowNum();
		
		return rowCount;
	}
	
	public static String getCellValue(String EXCEL_PATH, String sheet, int row, int column) throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
		
		String value="";
		//fileStream=new FileInputStream(EXCEL_PATH);
//		wb= new XSSFWorkbook(new File(EXCEL_PATH));
		wb= (XSSFWorkbook)WorkbookFactory.create(new File(EXCEL_PATH));
		Sheet newsheet=wb.getSheet(sheet);
		//		Row rows=wb.getSheet(sheet).getRow(row);
		DataFormatter formatter = new DataFormatter();
		Row row1=newsheet.getRow(row);
		Cell cell = wb.getSheet(sheet).getRow(row).getCell(column);
		if (cell!=null && row1!=null) {
//			value=wb.getSheet(sheet).getRow(row).getCell(column).getStringCellValue();
			value=formatter.formatCellValue(newsheet.getRow(row).getCell(column));
		}
		
		
		return value;
		
	}

	
	public static void WaitExplicitly(WebElement itemVisible) {
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		
		WebElement element=wait.until(ExpectedConditions.visibilityOf(itemVisible));
		
		if(element.isDisplayed()==true) {
			
			System.out.println("Element is found");
			
		}else {
			System.out.println("Element is not found");
		}
	}
	
	public static void WaitExplicitly(Boolean value,WebElement itemVisible) {
		
		WebDriverWait wait=new WebDriverWait(driver, 100);
		
		Boolean element=wait.until(ExpectedConditions.elementToBeSelected(itemVisible));
		
		if(element) {
			
			System.out.println("Element is found");
			
		}else {
			System.out.println("Element is not found");
		}
	}
	
	public static void scrollDownWebpageToElement(WebElement element) {
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		int x= element.getLocation().getX();
		System.out.println("X value is: "+x);
		int y=element.getLocation().getY();
		System.out.println("Y value is: "+y);

		js.executeScript("window.scrollBy("+x+","+y+")");
	
	}
	
	
		public static boolean elementIsPresent(WebElement element) {
	    try {
	        element.isDisplayed();
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }

	    return true;
	}

}

