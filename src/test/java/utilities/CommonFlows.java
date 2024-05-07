package utilities;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.saucedemo.BurgerMenuPage;
import pages.saucedemo.ItemDetailsPage;
import pages.saucedemo.LoginPage;
import pages.saucedemo.ShoppingPage;
import pages.saucedemo.TopBarPage;

public class CommonFlows {

    private WebDriver getDriver() {
        return new WebDriverProvider().get();
    }

    private void assignLoginCookie() {
        Logs.debug("Assigning the login cookie");
        getDriver().get("https://www.saucedemo.com/404");
        final var loginCookie = new Cookie("session-username", "standard_user");
        getDriver().manage().addCookie(loginCookie);
    }

    public void goToLoginPage() {
        getDriver().get("https://www.saucedemo.com/");
        new LoginPage().waitPageToLoad();
    }

    public void goToShoppingPage() {
        assignLoginCookie();
        getDriver().get("https://www.saucedemo.com/inventory.html");
        new ShoppingPage().waitPageToLoad();
    }

    public void openBurgerMenu() {
        goToShoppingPage();
        new TopBarPage().openBurgerMenu();
        new BurgerMenuPage().waitPageToLoad();
    }

    public void goToItemDetail(String itemName) {
        goToShoppingPage();
        new ShoppingPage().goToItemDetails(itemName);
        new ItemDetailsPage().waitPageToLoad();
    }
}
