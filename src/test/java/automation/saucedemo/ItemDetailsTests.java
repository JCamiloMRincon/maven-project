package automation.saucedemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.ItemDetailsPage;
import pages.saucedemo.ShoppingPage;
import utilities.BaseTest;

public class ItemDetailsTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final ItemDetailsPage itemDetailsPage = new ItemDetailsPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToItemDetail("Sauce Labs Fleece Jacket");
    }

    @Test(groups = { sauceDemo, itemDetails })
    public void itemDetailsTest() {
        itemDetailsPage.verifyPage();
    }

    @Test(groups = { sauceDemo, itemDetails })
    public void backToProductsTest() {
        itemDetailsPage.clickBackToProducts();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();
    }
}
