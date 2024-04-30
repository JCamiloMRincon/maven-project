package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class TutorialsPointTests extends BaseTest {

    @Test
    public void tabTest() {
        final var url = "https://www.tutorialspoint.com/selenium/practice/browser-windows.php";

        Logs.info("Navigating to the main page");
        driver.get(url);

        Logs.debug("Getting the id of the current tab for recognizing it later");
        final var tabId = driver.getWindowHandle();
        Logs.debug("tabId: %s", tabId);

        Logs.info("Clicking on the new tab button");
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();

        final var windowHandlesSet = driver.getWindowHandles();
        Logs.debug("Window handles set: %s", windowHandlesSet);

        Logs.info("Going to the new tab");
        for(var windowHandle : windowHandlesSet) {
            if(!windowHandle.equals(tabId)) { // If it is not the original tabId, it is the new tab
                driver.switchTo().window(windowHandle); // Going to the new tab
            }
        }

        Logs.info("Verifying the text");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed());

        Logs.info("Closing the current tab");
        driver.close();

        Logs.debug("Going back to the original window");
        driver.switchTo().window(tabId);

        Logs.info("Verifying that it has been returned to the original window");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed());
    }

    @Test
    public void windowTest() {
        final var url = "https://www.tutorialspoint.com/selenium/practice/browser-windows.php";

        Logs.info("Navigating to the main page");
        driver.get(url);

        Logs.debug("Getting the id of the current window for recognizing it later");
        final var windowId = driver.getWindowHandle();
        Logs.debug("windowId: %s", windowId);

        Logs.info("Clicking on the new window button");
        driver.findElement(By.xpath("//button[text()='New Window']")).click();

        final var windowHandlesSet = driver.getWindowHandles();
        Logs.debug("Window handles set: %s", windowHandlesSet);

        Logs.info("Going to the new window");
        for(var windowHandle : windowHandlesSet) {
            if(!windowHandle.equals(windowId)) { // If it is not the original tabId, it is the new tab
                driver.switchTo().window(windowHandle); // Going to the new tab
            }
        }

        Logs.info("Verifying the text");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Window']")).isDisplayed());

        Logs.info("Closing the current tab");
        driver.close();

        Logs.debug("Going back to the original window");
        driver.switchTo().window(windowId);

        Logs.info("Verifying that it has been returned to the original window");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed());
    }

    @Test
    public void iframeTest() {
        final var url = "https://www.tutorialspoint.com/selenium/practice/nestedframes.php";

        Logs.info("Navigating to the main page");
        driver.get(url);

        Logs.info("Going to the iframe");
        driver.switchTo().frame("frame1"); // id

        Logs.info("Verifying the iframe title");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed());
        
        Logs.info("Returning to the main page");
        driver.switchTo().defaultContent();

        Logs.info("Verifying the page title");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Nested Frames']")).isDisplayed());
    }
}
