package locator.pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import util.loggers.Log;

public class HomePage extends BasePage {
	/** Constructor */
	public HomePage(WebDriver driver) {
		super(driver);
	}

	/** Web Elements */
	By homePageTitle = By.className("app_logo");
	By addToCartButton = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']");
	By checkoutButton = By.className("shopping_cart_link");
	By removeFromCartButton = By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory']");
	By itemNameTitle = By.cssSelector("div[class='inventory_item_name']");
	By itemDescriptionTitle = By.cssSelector("div[class='inventory_item_desc']");
	By itemPriceTitle = By.cssSelector("div[class='inventory_item_price']");

	/** Page Methods */
	@Step("Verify Home Page")
	public void verifyHomePage() throws InterruptedException {
		delay(2000);
		String sHomeText = getText(homePageTitle);
		assertEqualsTo(sHomeText, "Swag Labs");
		printVerifyPage(sHomeText);
	}

	@Step("Get All Data Products")
	public void getAllDataProducts() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Products");

		ArrayList<Object[]> productsData = new ArrayList<Object[]>();
		productsData.add(new Object[] { "No", "Item Name", "Item Description", "Item Price" });

		for (int i = 0; i < driver.findElements(itemNameTitle).size(); i++) {
			productsData.add(new Object[] { 1 + i, driver.findElements(itemNameTitle).get(i).getText(),
					driver.findElements(itemDescriptionTitle).get(i).getText(),
					driver.findElements(itemPriceTitle).get(i).getText() });
		}

		// CREATE XLS USING FOREACH LOOP
		int rowNumber = 0;
		for (Object[] cmp : productsData) {
			XSSFRow row = sheet.createRow(rowNumber++);
			int cellNumber = 0;
			for (Object value : cmp) {
				XSSFCell cell = row.createCell(cellNumber++);
				if (value instanceof String)
					cell.setCellValue((String) value);
				if (value instanceof Integer)
					cell.setCellValue((Integer) value);
				if (value instanceof Boolean)
					cell.setCellValue((Boolean) value);
			}
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();

		String filePath = ".\\poi-output\\Products-SauceDemoApps[" + dtf.format(now) + "].xlsx";
		FileOutputStream outstream = new FileOutputStream(filePath);
		workbook.write(outstream);
		outstream.close();
		Log.info("Data all products written successfully! at path : " + filePath);
	}
}
