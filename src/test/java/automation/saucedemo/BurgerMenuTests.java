package automation.saucedemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.BurgerMenuPage;
import pages.saucedemo.LoginPage;
import pages.saucedemo.ShoppingPage;
import pages.saucedemo.TopBarPage;
import utilities.BaseTest;
import utilities.Logs;

public class BurgerMenuTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final TopBarPage topBarPage = new TopBarPage();
    private final BurgerMenuPage burgerMenuPage = new BurgerMenuPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad();
        loginPage.fillLogin("standard_user", "secret_sauce");

        shoppingPage.waitPageToLoad();
        topBarPage.openBurgerMenu();
        burgerMenuPage.waitPageToLoad();
    }

    @Test
    public void logoutTest() {
        burgerMenuPage.clickLogout();
        loginPage.waitPageToLoad();
        loginPage.verifyPage();
    }
}
