package automation.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class LoginTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad();
    }

    @Test(groups = { sauceDemo, login })
    public void invalidLoginTest() {
        loginPage.fillLogin("locked_out_user", "secret_sauce");
        loginPage.verifyErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(groups = { sauceDemo, login })
    public void verifyLoginPageTest() {
        loginPage.verifyPage();
    }
}
