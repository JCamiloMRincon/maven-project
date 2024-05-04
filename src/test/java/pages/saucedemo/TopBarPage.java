package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class TopBarPage extends BasePage {

    private final By title = By.xpath("//div[text()='Swag Labs']");
    private final By burgerMenu = By.id("react-burger-menu-btn");

    @Override
    public void waitPageToLoad() { }

    @Override
    @Step("Verifying the top bar")
    public void verifyPage() {
        softAssert.assertTrue(find(title).isDisplayed());
        softAssert.assertTrue(find(burgerMenu).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Opening the burger menu")
    public void openBurgerMenu() {
        Logs.info("Opening the burger menu");
        find(burgerMenu).click();
    }
}
