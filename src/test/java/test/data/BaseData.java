package test.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseData {
	public String readUserData(int getRow, int getCell) throws IOException {
		String filePath = ".\\poi-input\\List_User_SauceDemoApps.xlsx";
		FileInputStream inputStream = new FileInputStream(filePath);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);

		// READ EXCEL WITH ITERATOR
		Iterator iterator = sheet.iterator();

		while (iterator.hasNext()) {
			XSSFRow row = (XSSFRow) iterator.next();
			Iterator cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				XSSFCell cell = (XSSFCell) cellIterator.next();

				switch (cell.getCellType()) {
				case STRING:
					break;
				case NUMERIC:
					break;
				case BOOLEAN:
					break;
				}
			}
		}
		return sheet.getRow(getRow).getCell(getCell).toString();
	}
}
