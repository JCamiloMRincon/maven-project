package automation.saucedemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;
import pages.saucedemo.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad();
        loginPage.fillLogin("standard_user", "secret_sauce");

        shoppingPage.waitPageToLoad();
    }

    @Test(groups = { sauceDemo, shopping })
    public void verifyShoppingPageTest() {
        shoppingPage.verifyPage();
    }
}
