package com.crm.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;


import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	/*
	 * Xls_Reader reader=new
	 * Xls_Reader("C:\\Users\\sunee\\eclipse-workspace\\FreeCRMTest" +
	 * "\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM_Testdata.xlsx");
	 */
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
	public static String TESTDATA_SHEET_PATH="C:\\Users\\sunee\\eclipse-workspace\\FreeCRMTest\\src\\"
			+ "main\\java\\com\\crm\\qa\\testdata\\FreeCRM_Testdata.xlsx";
	 static Workbook book;
	 static Sheet sheet;
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum());
		System.out.println(sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		return data;
	}
	public static void takeScreenshotAtEndOfTest() throws IOException{
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("C:\\Users\\sunee\\eclipse-workspace\\FreeCRMTest");
		FileUtils.copyFile(src, new File(currentDir+"\\screenshots\\"+System.currentTimeMillis()+".png"));
	}
}
