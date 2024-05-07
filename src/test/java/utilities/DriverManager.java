package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private final boolean runServer = System.getenv("JOB_NAME") != null;

    public void buildDriver() {
        if (runServer) {
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    private void buildRemoteDriver() {
        // TODO
    }

    private void buildLocalDriver() {
        Logs.debug("Init driver...");
        final var driver = new ChromeDriver();

        Logs.debug("Maximizing window...");
        driver.manage().window().maximize();

        Logs.debug("Clearing cookies...");
        driver.manage().deleteAllCookies();

        new WebDriverProvider().set(driver);
    }

    public void killDriver() {
        Logs.debug("Killing driver...");
        new WebDriverProvider().get().quit();
    }
}
