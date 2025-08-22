package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./src\\test\\resources\\PropertiesFile.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("createProduct");
		Row rw=sh.getRow(1);
		Cell cell = rw.createCell(5);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Typass");
		FileOutputStream fos=new FileOutputStream("./src\\\\test\\\\resources\\\\PropertiesFile.xlsx");
		wb.write(fos);
		wb.close();
		

	}

}
