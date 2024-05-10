package automation.saucedemo;

import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;
import utilities.BaseTest;

public class LoginTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToLoginPage();
    }

    @DataProvider(name = "login-data-provider")
    public Object[][] loginDataProvider() {
        Faker faker = new Faker();

        return new Object[][] {
                {
                    "locked_out_user",
                    "secret_sauce",
                    "Epic sadface: Sorry, this user has been locked out."
                },
                {
                    faker.name().username(),
                    faker.internet().password(),
                    "Epic sadface: Username and password do not match any user in this service"
                }
        };
    }

    @Test(groups = { sauceDemo, login })
    public void verifyLoginPageTest() {
        loginPage.verifyPage();
    }

    @Test(dataProvider = "login-data-provider", groups = { sauceDemo, login })
    public void invalidLoginTest(String username, String password, String errorMessage) {
        loginPage.fillLogin(username, password);
        loginPage.verifyErrorMessage(errorMessage);
    }
}
