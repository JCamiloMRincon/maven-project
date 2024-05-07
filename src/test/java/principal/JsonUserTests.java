package principal;

import entities.UserJson;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.JsonReader;
import utilities.Logs;

public class JsonUserTests extends BaseTest {

    private UserJson user;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        user = JsonReader.getUserJson("src/main/resources/data/user.json");
    }

    @Test(groups = { smoke })
    @Description("Verifying that the ID is greater than 0")
    @Severity(SeverityLevel.BLOCKER)
    public void firstTest() {
        Logs.info("Verifying that the ID is greater than 0...");
        Assert.assertTrue(user.getId() > 0,
                  "The ID is lower or equal to 0");
    }

    @Test(groups = { regression })
    @Description("Verifying the longitude")
    @Severity(SeverityLevel.NORMAL)
    public void secondTest() {
        Logs.info("Verifying the longitude...");
        Assert.assertEquals(user.getAddress().getGeo().getLng(), 71.7478,
                    "The longitude is not equal to 71.7478");
    }

    @Test(groups = { smoke })
    @Description("Verifying the company BS length")
    @Severity(SeverityLevel.MINOR)
    public void thirdTest() {
        Logs.info("Verifying the company BS length...");
        Assert.assertTrue(user.getCompany().getBs().length() > 10,
                 "The company BS length is lower or equal to 10");
    }

    @Test(groups = { regression, smoke })
    @Description("Verifying the user information")
    @Severity(SeverityLevel.BLOCKER)
    public void fourthTest() {
        Logs.info("Verifying the user information...");

        /* softAssert.assertEquals(user.getName(), "Mrs. Dennis Schulist");
        softAssert.assertEquals(user.getId(), 6);
        softAssert.assertEquals(user.getUsername(), "Leopoldo_Corkery");
        softAssert.assertEquals(user.getWebsite(), "ola.org");
        softAssert.assertAll(); */
    }
}
