package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AutomationUtils;
import utilities.BaseTest;
import utilities.Logs;

public class SauceDemoTests extends BaseTest {

    @Test
    public void invalidUserTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        AutomationUtils.sleep(3000); // I wait for 3 seconds

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        AutomationUtils.sleep(2000); // I wait for 2 seconds

        Logs.info("Verifying the error message");
        final var errorLabel = driver.findElement(By.cssSelector("h3[data-test='error']"));
        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(), "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test
    public void validUserTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        AutomationUtils.sleep(3000); // I wait for 3 seconds

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        AutomationUtils.sleep(2000); // I wait for 2 seconds

        Logs.info("verifying that the inventory list is visible");
        final var inventoryList = driver.findElement(By.className("inventory_list"));
        Assert.assertTrue(inventoryList.isDisplayed());
    }

    @Test
    public void itemDetailTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        AutomationUtils.sleep(3000); // I wait for 3 seconds

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        AutomationUtils.sleep(2000); // I wait for 2 seconds

        final var imagesList = driver.findElements(By.cssSelector("img.inventory_item_img"));
        Logs.info("Clicking on the image 1");
        imagesList.get(0).click(); // get(0) brings the first element, and it will be clicked

        AutomationUtils.sleep(1000); // I wait for 1 second

        Logs.info("verifying the details of the item");
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.xpath("//button[text()='Add to cart']")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_img")).isDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void selectDescendingTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        AutomationUtils.sleep(3000); // I wait for 3 seconds

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        AutomationUtils.sleep(3000); // I wait for 3 seconds

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

    @Test
    public void selectPriceTest() {
        Logs.info("Navigating to the url");
        driver.get("https://www.saucedemo.com/");

        AutomationUtils.sleep(3000); // I wait for 3 seconds

        Logs.info("Writing the username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Writing the password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Click on the login button");
        driver.findElement(By.id("login-button")).click();

        AutomationUtils.sleep(3000); // I wait for 3 seconds

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
}
