package test.cases;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;

import io.qameta.allure.Step;
import junit.framework.Assert;

import locator.pages.BasePage;
import locator.pages.HomePage;
import locator.pages.LoginPage;
import test.data.BaseData;
import util.loggers.Log;

public class BaseTest {

	// Declare Class
	public WebDriver driver;
	public Faker faker;
	public BaseData baseData;

	// Declare Pages
	public BasePage basePg;
	public LoginPage loginPg;
	public HomePage homePg;

	// Get WebDriver Method
	public WebDriver getDriver() {
		return driver;
	}

	@BeforeClass
	@Step("Automation test are starting !")
	public void configEnvironment() {

		// Webdriver Declaration
		Log.info("Automation test are starting !");
		System.setProperty("webdriver.chrome.driver", "drivers" + File.separator + "chromedriver.exe");
		driver = new ChromeDriver();

		// Implicitly Wait Timeouts
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@BeforeMethod
	public void initClass() {
		// Class
		faker = new Faker();
		baseData = new BaseData();

		// Pages
		basePg = new BasePage(driver);
		loginPg = new LoginPage(driver);
		homePg = new HomePage(driver);
	}

	@BeforeMethod
	@Step("Go To Apps and Verify")
	public void goToApps() {
		// Go To Apps
		String baseURL = "https://www.saucedemo.com/";
		driver.get(baseURL);

		// Verify Current URL & Pages Title
		String currentURL = driver.getCurrentUrl();
		String pagesTitle = driver.getTitle();
		Log.info(currentURL);
		Assert.assertEquals(currentURL, baseURL);
		Log.info(pagesTitle);
		Assert.assertEquals(pagesTitle, "Swag Labs");

		// Maximize current window
		driver.manage().window().maximize();
	}

	@AfterMethod
	@Step("Delete All Cookies After Method")
	public void deleteAllCookiesAfterMethod() {
		if (driver.manage().getCookies() != null) {
			basePg.deleteAllCookies();
		}
	}

	@AfterClass
	@Step("Automation test are ending !")
	public void tearDown() {
		Log.info("Automation test are ending !");
		driver.quit();
	}
}
