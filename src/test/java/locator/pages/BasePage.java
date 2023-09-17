package locator.pages;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import junit.framework.Assert;
import test.data.BaseData;
import util.loggers.Log;

public class BasePage {
	/** Declare Class */
	public WebDriver driver;
	public BaseData baseData;
	public WebDriverWait wait;
	public Faker faker;
	public Actions actions;
	public JavascriptExecutor js;

	/** Constructor */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		baseData = new BaseData();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		faker = new Faker();
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	/** Web Elements */

	/** Methods */
	public void click(By by) {
		waitVisibility(by).click();
	}

	public void selectDropdownByIndex(WebElement element, int index) {
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}

	public void selectDropdownByVisibleText(WebElement element, String text) {
		Select dropdown = new Select(element);
		dropdown.deselectByVisibleText(text);
	}

	public void selectDropdownByValue(WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}

	public void sendText(By by, String text) {
		waitVisibility(by).sendKeys(text);
	}

	public void sendKeyboardActionsEnter(By by) {
		waitVisibility(by).sendKeys(Keys.ENTER);
	}

	public void verifyDownloadedFileMethod(int delay, String fileName) throws InterruptedException {
		Log.info("Files are being downloaded!");
		delay(delay);

		String FILES_DIRECTORY = "C:\\Download";
		File Folder = new File(FILES_DIRECTORY);
		File[] allFiles = new File(Folder.getPath()).listFiles();
		for (File file : allFiles) {
			String eachFile = file.getName();
			if (eachFile.contains(fileName))
				Log.info("*Verified* File : " + fileName + " has been download!");
			else
				continue;
		}
	}

	public String getText(By by) {
		return waitVisibility(by).getText();
	}

	public List<WebElement> getElementList(By by) {
		return driver.findElements(by);
	}

	public WebElement getWebElement(By by) {
		return driver.findElement(by);
	}

	public String getValue(By by) {
		return waitVisibility(by).getAttribute("value");
	}

	public String getHREF(By by) {
		return waitVisibility(by).getAttribute("href");
	}

	public String[] splitFieldValue(By by, String regex) {
		return getValue(by).split(regex);
	}

	public char getCharAtIndex(String[] string, int indexArray, int indexChar) {
		return string[indexArray].charAt(indexChar);
	}

	public String convertCharToString(char character) {
		return Character.toString(character);
	}

	public WebElement waitVisibility(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void delay(int second) throws InterruptedException {
		Thread.sleep(second);
	}

	public void printVerify(String text) {
		Log.info(text + " is present!");
	}

	public void printVerifyPage(String text) {
		Log.info("Page " + text + " is present!");
	}

	public void printVerifyPopUp(String text) {
		Log.info("Pop Up " + text + " is present!");
	}

	public void assertEqualsTo(String expected, String result) {
		Assert.assertEquals(expected, result);
	}

	public void verifyElementIsDisplayed(By by) {
		waitVisibility(by).isDisplayed();
	}

	public String generateFullName() {
		return faker.name().fullName();
	}

	public String generateFirstName() {
		return faker.name().firstName();
	}

	public String generateLastName() {
		return faker.name().lastName();
	}

	public String generatePhoneNumber() {
		return faker.number().digits(6);
	}

	public String generateMobilePhoneNumber() {
		return faker.number().digits(12);
	}

	public String generateCertificateNumber() {
		return "CER - " + faker.number().digits(6);
	}

	public String generateWebsite(String name) {
		String websiteName = name.replaceAll(" ", "-").toLowerCase();
		return "www." + websiteName + ".com";
	}

	public String generateEmailFromField(By by) {
		return getValue(by).replaceAll(" ", "_").replaceAll("\\.", "_").toLowerCase() + "@yopmail.com";
	}

	public String generateTaxNumber() {
		return faker.number().digits(15);
	}

	public String generateNumber(int length) {
		return faker.number().digits(length);
	}

	public String generateSentence(int length) {
		return faker.lorem().sentence(length);
	}

	public String generateWord() {
		return faker.lorem().word();
	}

	public String generateAddress() {
		return faker.address().fullAddress();
	}

	public String generateCity() {
		return faker.address().city();
	}

	public String generateDefaultPassword() {
		return "Abc123##";
	}

	public void openNewTab() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	public void switchTabByNumber(int number) {
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(number));
	}

	public void switchToFrame(String frame) {
		driver.switchTo().frame(frame);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void navigateToURL(String url) {
		driver.navigate().to(url);
		Log.info("Navigate To : " + url + ", Title : " + driver.getTitle());

	}

	public void getCurrentURL() {
		Log.info(driver.getCurrentUrl());
	}

	public void getTitleWebsite() {
		Log.info(driver.getTitle());
	}

	public void hoverToElement(WebElement element) {
		actions.moveToElement(element);
		actions.click().build().perform();
	}

	public void hoverToElementAndClick(WebElement element) {
		actions.moveToElement(element).click(element).build().perform();
	}

	public void scroll(String x, String y) {
		js.executeScript("window.scrollBy(" + x + "," + y + ")", "");
	}

	public void scrollWithKeyPageDown(int loop) throws InterruptedException {
		for (int i = 0; i < loop; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
	}

	public void scrollWithKeyPageUp() {
		actions.sendKeys(Keys.PAGE_UP).build().perform();
	}

	public void scrollToElement(WebElement element) throws InterruptedException {
		js.executeScript("arguments[0].scrollIntoView();", element);
		delay(1000);
	}

	public void getAllCookies() {
		driver.manage().getCookies();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

}
