package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AutomationUtils;
import utilities.BaseTest;
import utilities.Logs;

public class NavigationTests extends BaseTest {

    @Test(groups = { automation })
    public void firstTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Navigating to %s", url);
        driver.get(url);

        AutomationUtils.sleep(2000); // I wait for 2 seconds

        Logs.info("Get the current url...");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verifying that the urls match");
        Assert.assertEquals(currentUrl, url);
    }

    @Test(groups = { automation })
    public void secondTest() {
        final var urlHeroku = "https://the-internet.herokuapp.com/";
        final var urlGithub = "https://github.com";

        Logs.info("Navigating to %s", urlHeroku);
        driver.get(urlHeroku);

        AutomationUtils.sleep(2000); // I wait for 2 seconds

        Logs.info("Navigating to %s", urlGithub);
        driver.get(urlGithub);

        AutomationUtils.sleep(3000); // I wait for 3 seconds

        Logs.info("Going back to the previous page");
        driver.navigate().back();

        AutomationUtils.sleep(1500); // I wait for 1.5 seconds

        Logs.info("Get the current url...");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verifying that the urls match");
        Assert.assertEquals(currentUrl, urlHeroku);
    }

    @Test(groups = { automation })
    public void alwaysFailTest() {
        final var url = "https://the-internet.herokuapp.com/";

        Logs.info("Navigating to %s", url);
        driver.get(url);

        Logs.info("Get the current url...");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verifying that the urls match");
        Assert.assertEquals(currentUrl, "Hello world");
    }
}
