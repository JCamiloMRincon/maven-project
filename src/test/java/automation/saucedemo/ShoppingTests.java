package automation.saucedemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.ShoppingPage;
import utilities.BaseTest;

public class ShoppingTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToShoppingPage();
    }

    @Test(groups = { sauceDemo, shopping })
    public void verifyShoppingPageTest() {
        shoppingPage.verifyPage();
    }
}
