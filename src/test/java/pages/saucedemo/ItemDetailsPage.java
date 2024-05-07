package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ItemDetailsPage extends BasePage {

    private final By itemName = By.className("inventory_details_name");
    private final By itemPrice = By.className("inventory_details_price");
    private final By itemImage = By.className("inventory_details_img");
    private final By itemDescription = By.className("inventory_details_desc");
    private final By addToCartButton = By.xpath("//button[text()='Add to cart']");
    private final By backToProductsButton = By.id("back-to-products");

    @Override
    @Step("Waiting for the item details page to load completely")
    public void waitPageToLoad() {
        waitPage(itemName, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verifying the item details page")
    public void verifyPage() {
        Logs.info("Verifying the item details page");
        softAssert.assertTrue(find(itemName).isDisplayed());
        softAssert.assertTrue(find(itemPrice).isDisplayed());
        softAssert.assertTrue(find(itemImage).isDisplayed());
        softAssert.assertTrue(find(itemDescription).isDisplayed());
        softAssert.assertTrue(find(addToCartButton).isDisplayed());
        softAssert.assertTrue(find(backToProductsButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Clicking on 'Back to products'")
    public void clickBackToProducts() {
        Logs.info("Clicking on 'Back to products'");
        find(backToProductsButton).click();
    }
}
