package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    @Override
    @Step("Waiting for the login page to be loaded")
    public void waitPageToLoad() {
        waitPage(usernameInput, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verifying the login page")
    public void verifyPage() {
        Logs.info("Verifying the login page");
        softAssert.assertTrue(find(usernameInput).isDisplayed());
        softAssert.assertTrue(find(passwordInput).isDisplayed());
        softAssert.assertTrue(find(loginButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Filling the login form")
    public void fillLogin(String username, String password) {
        Logs.info("Writing the username");
        find(usernameInput).sendKeys(username);

        Logs.info("Writing the password");
        find(passwordInput).sendKeys(password);

        Logs.info("Click on the login button");
        find(loginButton).click();
    }

    @Step("Verifying the error message")
    public void verifyErrorMessage(String errorText) {
        final var errorLabel = find(errorMessage);

        Logs.info("Verifying the error message");
        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(), errorText);
        softAssert.assertAll();
    }
}
