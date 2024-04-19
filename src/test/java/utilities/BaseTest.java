package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({ SuiteListeners.class, TestListeners.class })
public class BaseTest {

    protected SoftAssert softAssert;
    protected final String regression = "regression";
    protected  final String smoke = "smoke";

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();
    }
}
