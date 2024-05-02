package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AutomationUtils;
import utilities.BaseTest;
import utilities.Logs;

public class BooksPwakitTests extends BaseTest {

    @Test
    public void shadowDomTest() {
        final var url = "https://books-pwakit.appspot.com/";

        Logs.info("Navigating to the page");
        driver.get(url);

        Logs.debug("Getting the shadow root");
        final var shadowRoot = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();

        Logs.debug("Getting the footer through the shadow root");
        final var footer = shadowRoot.findElement(
                By.cssSelector("footer > p") // p is the descendant of footer in CSS
        );

        Logs.info("Verifying that the text is right");
        Assert.assertEquals(footer.getText(), "Made with <3 by the Polymer team.");
    }

    @Test
    public void shadowDomSearchTextTest() {
        final var url = "https://books-pwakit.appspot.com/";

        Logs.info("Navigating to the page");
        driver.get(url);

        Logs.debug("Getting the shadow root");
        final var shadowRoot = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();

        Logs.debug("Getting the input through the shadow root");
        final var input = shadowRoot.findElement(
                By.id("input")
        );

        Logs.info("Writing 'hello world' in the input");
        new Actions(driver)
                .click(input)            // Focus
                .sendKeys("hello world") // Write 'hello world'
                .sendKeys(Keys.ENTER)    // Press enter for searching the results
                .perform();              // To do all the actions

        // We wait with Thread.Sleep because the implicit has issues with the shadow DOM
        AutomationUtils.sleep(2000);

        Logs.debug("Getting the inner shadow root");
        final var innerShadowRoot = shadowRoot.findElement(
                By.cssSelector("book-explore")
        ).getShadowRoot();

        final var booksList = innerShadowRoot.findElement(By.cssSelector("ul.books")); // tag.class

        Logs.info("Verifying that the list of books is visible");
        Assert.assertTrue(booksList.isDisplayed());
    }
}
