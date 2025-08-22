package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

		

		public String toReadDataFromExcelFile(String sheetname, int Rownum, int cellNum) throws IOException {
			
			FileInputStream fis = new FileInputStream("C:\\Users\\sipun\\OneDrive\\Desktop\\PropertiesFile.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			String data = wb.getSheet(sheetname).getRow(Rownum).getCell(cellNum).getStringCellValue();
			wb.close();
			return data;
	}
		public int togetLastRowNum(String Sheetname) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\sipun\\OneDrive\\Desktop\\PropertiesFile.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rownum = wb.getSheet(Sheetname).getLastRowNum();
		wb.close();
		return rownum;
		
		}

}
