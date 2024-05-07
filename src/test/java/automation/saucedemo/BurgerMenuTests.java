package automation.saucedemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.BurgerMenuPage;
import pages.saucedemo.LoginPage;
import utilities.BaseTest;

public class BurgerMenuTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final BurgerMenuPage burgerMenuPage = new BurgerMenuPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.openBurgerMenu();
    }

    @Test(groups = { sauceDemo, burgerMenu })
    public void logoutTest() {
        burgerMenuPage.clickLogout();
        loginPage.waitPageToLoad();
        loginPage.verifyPage();
    }

    @Test(groups = { sauceDemo, burgerMenu })
    public void aboutButtonTest() {
        burgerMenuPage.verifyAboutOption("https://saucelabs.com/");
    }
}
