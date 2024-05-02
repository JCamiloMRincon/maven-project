package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SauceDemoTests extends BaseTest {

    @Test(groups = { sauceDemo })
    public void invalidUserTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Verifying the error message");
        final var errorLabel = driver.findElement(By.cssSelector("h3[data-test='error']"));
        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(), "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test(groups = { sauceDemo })
    public void validUserTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        Logs.info("verifying that the inventory list is visible");
        final var inventoryList = driver.findElement(By.className("inventory_list"));
        Assert.assertTrue(inventoryList.isDisplayed());
    }

    @Test(groups = { sauceDemo })
    public void itemDetailTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        final var imagesList = driver.findElements(By.cssSelector("img.inventory_item_img"));
        Logs.info("Clicking on the image 1");
        imagesList.get(0).click(); // get(0) brings the first element, and it will be clicked


        Logs.info("verifying the details of the item");
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.xpath("//button[text()='Add to cart']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_img")).isDisplayed());
        softAssert.assertAll();
    }

    @Test(groups = { sauceDemo })
    public void selectDescendingTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        final var selectWebElement = driver.findElement(By.cssSelector("select[data-test='product-sort-container']"));
        final var select = new Select(selectWebElement); // Cast to select

        Logs.info("Selecting the items alphabetically descending");
        select.selectByValue("za"); // Select Z-A

        final var itemNamesList = driver.findElements(By.className("inventory_item_name"));

        Logs.info("Getting the first item text");
        final var firstItem = itemNamesList.get(0).getText();
        Logs.info("Getting the last item text");
        final var lastItem = itemNamesList.get(itemNamesList.size() - 1).getText();

        Logs.info("Verifying the names");
        softAssert.assertEquals(firstItem, "Test.allTheThings() T-Shirt (Red)");
        softAssert.assertEquals(lastItem, "Sauce Labs Backpack");
        softAssert.assertAll();
    }

    @Test(groups = { sauceDemo })
    public void selectPriceTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        final var selectWebElement = driver.findElement(By.cssSelector("select[data-test='product-sort-container']"));
        final var select = new Select(selectWebElement); // Cast to select

        Logs.info("Selecting the items from low to high price");
        select.selectByValue("lohi"); // Select low to high price

        final var itemPricesList = driver.findElements(By.className("inventory_item_price"));

        Logs.info("Getting the first item price");
        // Get the price, delete the symbol '$' and convert it to double
        final var firstItem = Double.parseDouble(itemPricesList.get(0).getText().substring(1));
        Logs.info("Getting the last item price");
        // Get the price, delete the symbol '$' and convert it to double
        final var lastItem = Double.parseDouble(itemPricesList.get(itemPricesList.size() - 1).getText().substring(1));

        Logs.info("Verifying the prices");
        softAssert.assertEquals(firstItem, 7.99);
        softAssert.assertEquals(lastItem, 49.99);
        softAssert.assertAll();
    }

    @Test(groups = { sauceDemo })
    public void facebookLinkTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        final var facebookLabel = driver.findElement(By.xpath("//a[text()='Facebook']"));

        Logs.info("Verifying that the Facebook hyperlink is right");
        softAssert.assertTrue(facebookLabel.isDisplayed());
        softAssert.assertTrue(facebookLabel.isEnabled());
        softAssert.assertEquals(facebookLabel.getAttribute("href"), "https://www.facebook.com/saucelabs");
        softAssert.assertAll();
    }

    @Test(groups = { sauceDemo })
    public void linkedinLinkTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        final var linkedinLabel = driver.findElement(By.xpath("//a[text()='LinkedIn']"));

        Logs.info("Verifying that the Linkedin hyperlink is right");
        softAssert.assertTrue(linkedinLabel.isDisplayed());
        softAssert.assertTrue(linkedinLabel.isEnabled());
        softAssert.assertEquals(linkedinLabel.getAttribute("href"), "https://www.linkedin.com/company/sauce-labs/");
        softAssert.assertAll();
    }

    @Test(groups = { sauceDemo })
    public void aboutLinkTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Opening the burger menu");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        final var aboutLink = driver.findElement(By.id("about_sidebar_link"));

        Logs.info("Verifying the about hyperlink");
        softAssert.assertTrue(aboutLink.isDisplayed());
        softAssert.assertTrue(aboutLink.isEnabled());
        softAssert.assertEquals(aboutLink.getAttribute("href"), "https://saucelabs.com/");
        softAssert.assertAll();
    }

    @Test(groups = { sauceDemo })
    public void logoutTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        final var userNameLocator = By.id("user-name");

        Logs.info("Writing the username");
        driver.findElement(userNameLocator).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Opening the burger menu");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        Logs.info("Click on the logout option");
        driver.findElement(By.id("logout_sidebar_link")).click();

        Logs.info("Verifying that it would be returned the login page");
        Assert.assertTrue(driver.findElement(userNameLocator).isDisplayed());
    }

    @Test
    public void deleteCookiesTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        final var userNameLocator = By.id("user-name");

        Logs.info("Writing the username");
        driver.findElement(userNameLocator).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Get the set of cookies");
        var cookiesSet = driver.manage().getCookies();

        Logs.info("Verifying that the number of cookies is 1");
        Assert.assertEquals(cookiesSet.size(), 1);

        Logs.info("Deleting the cookies");
        driver.manage().deleteAllCookies();

        Logs.info("Getting the set of cookies again");
        cookiesSet = driver.manage().getCookies();

        Logs.info("Verifying that the number of cookies is 0");
        Assert.assertEquals(cookiesSet.size(), 0);
    }

    @Test
    public void getCookieTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        final var userNameLocator = By.id("user-name");

        Logs.info("Writing the username");
        driver.findElement(userNameLocator).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Getting the info of the login cookie");
        final var cookieLogin = driver.manage().getCookieNamed("session-username");

        Logs.info("Verifying that its value is standard_user");
        Assert.assertEquals(cookieLogin.getValue(), "standard_user");
    }

    @Test
    public void relativeLocatorPriceTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        final var userNameLocator = By.id("user-name");

        Logs.info("Writing the username");
        driver.findElement(userNameLocator).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        /*
           I get the locator relatively, this has a tag button.
           Besides, it is located below the text 'Sauce Labs Bolt T-Shirt'
        */

        final var locator = (By) RelativeLocator
                .with(By.className("inventory_item_price"))                               // Item harder to find
                .below(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']")); // Item easier to find

        // I find the price: delete the dollar symbol '$' -> change to double
        final var price = Double.parseDouble(driver.findElement(locator).getText().substring(1));

        Logs.info("Verifying that the price is right");
        Assert.assertEquals(price, 15.99);
    }

    @Test
    public void relativeLocatorAddToCardTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        final var userNameLocator = By.id("user-name");

        Logs.info("Writing the username");
        driver.findElement(userNameLocator).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        /*
           I get the locator relatively, this has a tag button.
           Besides, it is located below the text 'Sauce Labs Fleece Jacket'
        */

        final var locator = (By) RelativeLocator
                .with(By.tagName("button"))                                                // Item harder to find
                .below(By.xpath("//div[text()='Sauce Labs Fleece Jacket']")); // Item easier to find

        var cartButton = driver.findElement(locator);

        Logs.info("Verifying that its text is 'Add to cart'");
        Assert.assertEquals(cartButton.getText(), "Add to cart");

        Logs.info("Clicking on the button 'Add to cart' of the item 'Sauce Labs Fleece Jacket'");
        cartButton.click();

        cartButton = driver.findElement(locator);

        Logs.info("Verifying that its text is 'Removed'");
        Assert.assertEquals(cartButton.getText(), "Remove");
    }
}
