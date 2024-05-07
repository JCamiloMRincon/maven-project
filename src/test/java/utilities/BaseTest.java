package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners({ SuiteListeners.class, TestListeners.class })
public class BaseTest {

    protected final String regression = "regression";
    protected  final String smoke = "smoke";
    protected final String automation = "automation";
    protected final String sauceDemo = "sauce-demo-automation";
    protected final String login = "sauce-demo-login";
    protected final String shopping = "sauce-demo-shopping";
    protected final String itemDetails = "sauce-demo-item-details";
    protected final String footer = "sauce-demo-footer";
    protected final String burgerMenu = "sauce-demo-burger-menu";

    protected CommonFlows commonFlows = new CommonFlows();
    private final DriverManager driverManager = new DriverManager();
    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        driverManager.buildDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        driverManager.killDriver();
    }
}
