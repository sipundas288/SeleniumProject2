package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingDataFromMultipleRow {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src\\test\\resources\\PropertiesFile.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("Sheet1");
		int rowcount = sh.getLastRowNum();
		for(int row=1; row<=rowcount;row++)
		{
			String productcategory = sh.getRow(row).getCell(0).getStringCellValue();
			String productname = sh.getRow(row).getCell(1).getStringCellValue();
			System.out.println(productcategory+"=="+productname);
		}
	}

}
