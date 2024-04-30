package automation;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class DemoQATests extends BaseTest {

    @Test
    public void keyboardNameTest() {
        final var url = "https://demoqa.com/text-box";

        Logs.info("Navigating to the main page");
        driver.get(url);

        final var faker = new Faker();
        final var fullName = faker.name().fullName();
        Logs.debug("fullName: %s", fullName);

        final var usernameInput = driver.findElement(By.id("userName"));

        Logs.info("Pressing SHIFT and writing in capital letters");
        new Actions(driver)
                .click(usernameInput) // Clicking on the input
                .keyDown(Keys.SHIFT)  // Press SHIFT
                .sendKeys(fullName)   // Write the full name
                .keyUp(Keys.SHIFT)    // Stop pressing SHIFT
                .perform();           // To do all the actions

        Logs.info("Verifying that the input is in capital letters");
        Assert.assertEquals(usernameInput.getAttribute("value"), fullName.toUpperCase());
    }

    @Test
    public void keyboardAddressTest() {
        final var url = "https://demoqa.com/text-box";

        Logs.info("Navigating to the main page");
        driver.get(url);

        final var faker = new Faker();
        final var address = faker.address().fullAddress();
        Logs.debug("address: %s", address);

        final var currentAddressInput = driver.findElement(By.id("currentAddress"));

        Logs.info("Writing the address, selecting and copying the content");
        new Actions(driver)
                .click(currentAddressInput) // Clicking on the input
                .sendKeys(address)          // Writing the address
                .keyDown(Keys.CONTROL)      // Press CTRL
                .sendKeys("a")              // Press A because CTRL + A select the input
                .sendKeys("c")              // Press C because CTRL + C copies the selected input
                .keyUp(Keys.CONTROL)        // Stop pressing CTRL
                .perform();                 // To do all the actions

        final var permanentAddressInput = driver.findElement(By.id("permanentAddress"));

        Logs.info("Giving focus and pasting the content");
        new Actions(driver)
                .click(permanentAddressInput) // Clicking on the input
                .keyDown(Keys.CONTROL)        // Press CTRL
                .sendKeys("v")                // Press V because CTRL + V pastes the content
                .keyUp(Keys.CONTROL)          // Stop pressing CTRL
                .perform();                   // To do all the actions

        Logs.info("Verifying that the text is the same");
        Assert.assertEquals(permanentAddressInput.getAttribute("value"), currentAddressInput.getAttribute("value"));
    }

    @Test
    public void dragAndDropTest() {
        final var url = "https://demoqa.com/droppable";

        Logs.info("Navigating to the main page");
        driver.get(url);

        final var originFigure = driver.findElement(By.id("draggable"));
        final var destinyFigure = driver.findElement(By.id("droppable"));

        Logs.info("Moving the origin figure to the destiny figure");
        new Actions(driver)
                .dragAndDrop(originFigure, destinyFigure) // Move from origin to destiny
                .perform();                               // To do all the actions

        Logs.info("Verifying that the 'dropped' label is visible");
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Dropped!']")).isDisplayed());
    }

    @Test
    public void hoverTest() {
        final var url = "https://demoqa.com/tool-tips";

        Logs.info("Navigating to the main page");
        driver.get(url);

        final var greenButton = driver.findElement(By.id("toolTipButton"));

        Logs.info("Hovering over the green button");

        new Actions(driver)
                .moveToElement(greenButton) // Move the pointer to the green button
                .pause(1500)                // Wait for 1.5 seconds
                .perform();                 // To do all the actions

        Logs.info("Verifying the text after hovering");
        Assert.assertEquals(greenButton.getAttribute("aria-describedby"), "buttonToolTip");
    }
}
