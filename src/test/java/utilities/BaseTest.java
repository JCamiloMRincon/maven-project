package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners({ SuiteListeners.class, TestListeners.class })
public class BaseTest {

    protected SoftAssert softAssert;
    protected WebDriver driver;
    protected final String regression = "regression";
    protected  final String smoke = "smoke";
    protected final String automation = "automation";
    protected final String sauceDemo = "sauce-demo-automation";

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();

        Logs.debug("Init driver...");
        driver = new ChromeDriver();
        Logs.debug("Maximizing window...");
        driver.manage().window().maximize();
        Logs.debug("Clearing cookies...");
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        new WebDriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        Logs.debug("Killing driver...");
        driver.quit();
    }
}
