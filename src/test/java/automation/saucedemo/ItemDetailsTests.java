package automation.saucedemo;

import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.ItemDetailsPage;
import pages.saucedemo.ShoppingPage;
import utilities.BaseTest;

public class ItemDetailsTests extends BaseTest {

    private static final String[] productsArray = new String[] {
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie",
            "Test.allTheThings() T-Shirt (Red)"
    };
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final ItemDetailsPage itemDetailsPage = new ItemDetailsPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Faker faker = new Faker();
        var randomIndex = faker.number().numberBetween(0, productsArray.length - 1);
        var productName = productsArray[randomIndex];
        commonFlows.goToItemDetail(productName);
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
