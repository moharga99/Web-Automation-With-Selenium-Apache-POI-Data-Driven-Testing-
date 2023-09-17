package test.cases;

import static util.extentreports.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Data Driven Test")
@Feature("Data Driven Test")

public class DataDrivenTest extends BaseTest {
	@Test(priority = 0, description = "Data Driven Test")
	@Severity(SeverityLevel.NORMAL)
	@Description("User login to apps using excel data and get all data products then export to excel")
	@Story("User can login to apps using excel data and get all data products then export to excel")
	@Flaky
	public void dataDrivenTest(Method method) throws InterruptedException, IOException {
		startTest(method.getName(), "Data Driven Test");
		// Variables

		// Steps

		// Login Page
		loginPg.verifyLoginPage();
		loginPg.inputUsername(baseData.readUserData(1, 1));
		loginPg.inputPassword(baseData.readUserData(1, 2));
		loginPg.clickLoginButton();

		// Home Page
		homePg.verifyHomePage();
		homePg.getAllDataProducts();
	}
}
