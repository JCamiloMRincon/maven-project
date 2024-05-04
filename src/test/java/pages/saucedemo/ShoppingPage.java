package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ShoppingPage extends BasePage {

    private final By inventoryList = By.className("inventory_list");
    private final By productsTitle = By.xpath("//span[text()='Products']");
    private final By selectItem = By.cssSelector("select[data-test='product-sort-container']");

    @Override
    @Step("Waiting for the shopping page to be loaded")
    public void waitPageToLoad() {
        waitPage(inventoryList, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verifying the shopping page")
    public void verifyPage() {
        Logs.info("Verifying the shopping page");
        softAssert.assertTrue(find(inventoryList).isDisplayed());
        softAssert.assertTrue(find(productsTitle).isDisplayed());
        softAssert.assertTrue(find(selectItem).isDisplayed());
        softAssert.assertAll();
    }
}
