package automation;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SeleniumDevTests extends BaseTest {

    @Test
    public void randomScrollTest() {
        final var url = "https://www.selenium.dev/selenium/web/scroll.html";

        Logs.info("Navigating to the page");
        driver.get(url);

        Logs.debug("Generating a random number between 5 and 9");
        final var faker = new Faker();
        final var randomNumber = faker.number().numberBetween(5, 9);
        Logs.debug("randomNumber: %d", randomNumber); // Print the number in the logs file

        final var dynamicId = String.format("line%d", randomNumber); // line5, line6, ...
        final var lineN = driver.findElement(By.id(dynamicId));

        Logs.info("Scrolling down until the id: %s", dynamicId);
        new Actions(driver)
                .scrollToElement(lineN) // Scroll down until the element
                .pause(1000)            // Wait for 1 second
                .perform();             // To do all the actions

        Logs.info("Clicking on lineN");
        lineN.click();

        Logs.info("Verifying that the text is %s", dynamicId);
        Assert.assertEquals(driver.findElement(By.id("clicked")).getText(), dynamicId);
    }

    @Test
    public void scrollToFrameTest() {
        final var url = "https://www.selenium.dev/selenium/web/scrolling_tests/page_with_frame_out_of_view.html";

        Logs.info("Navigating to the page");
        driver.get(url);

        final var iframe = driver.findElement(By.name("frame"));

        Logs.info("Scrolling down until the iframe");
        new Actions(driver)
                .scrollToElement(iframe) // Scroll down until the iframe
                .pause(1000)             // Wait for 1 second
                .perform();              // To do all the actions

        Logs.debug("Changing the context to the iframe");
        driver.switchTo().frame(iframe); // Using the web element as parameter

        final var checkbox = driver.findElement(By.name("checkbox"));

        Logs.info("Clicking on the checkbox");
        checkbox.click();

        Logs.info("Verifying that the checkbox is selected");
        Assert.assertTrue(checkbox.isSelected());
    }
}
