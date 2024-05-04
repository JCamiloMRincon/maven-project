package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class BurgerMenuPage extends BasePage {

    private final By logoutButton = By.id("logout_sidebar_link");

    @Override
    @Step("Waiting for the menu burger to be loaded")
    public void waitPageToLoad() {
        waitPage(logoutButton, this.getClass().getSimpleName());

        Logs.info("Waiting for the logout button to be clickable because it has an animation");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
    }

    @Override
    @Step("Verifying the burger menu")
    public void verifyPage() {
        Logs.info("Verifying the burger menu");
        softAssert.assertTrue(find(logoutButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Click on the logout option")
    public void clickLogout() {
        Logs.info("Click on the logout option");
        find(logoutButton).click();
    }
}
