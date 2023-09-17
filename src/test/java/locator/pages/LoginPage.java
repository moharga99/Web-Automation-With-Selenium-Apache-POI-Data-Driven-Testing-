package locator.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	/** Constructor */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/** Web Elements */
	By loginPageTitle = By.className("login_logo");
	By inputUsername = By.id("user-name");
	By inputPassword = By.id("password");
	By loginButton = By.id("login-button");
	By loginAlert = By.cssSelector("h3[data-test='error']");

	/** Page Methods */
	@Step("Verify Login Page")
	public void verifyLoginPage() throws InterruptedException {
		delay(2000);
		String sLoginText = getText(loginPageTitle);
		assertEqualsTo(sLoginText, "Swag Labs");
		printVerifyPage(sLoginText);
	}

	@Step("Input Username : {0}")
	public void inputUsername(String username) {
		sendText(inputUsername, username);
	}

	@Step("Input Password : {0}")
	public void inputPassword(String password) {
		sendText(inputPassword, password);
	}

	@Step("Click Login Button")
	public void clickLoginButton() {
		click(loginButton);
	}

	@Step("Verify Username and Password Not Match Alert")
	public void verifyUsernameAndPasswordNotMatchAlert() throws InterruptedException {
		delay(2000);
		String sAlertText = getText(loginAlert);
		assertEqualsTo(sAlertText, "Epic sadface: Username and password do not match any user in this service");
		printVerifyPage(sAlertText);
	}

	@Step("Verify Locked Account Alert")
	public void verifyLockedAccountAlert() throws InterruptedException {
		delay(2000);
		String sAlertText = getText(loginAlert);
		assertEqualsTo(sAlertText, "Epic sadface: Sorry, this user has been locked out.");
		printVerifyPage(sAlertText);
	}
}
