package automation.saucedemo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saucedemo.FooterPage;
import utilities.BaseTest;

public class FooterTests extends BaseTest {

    private final FooterPage footerPage = new FooterPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToShoppingPage();
    }

    @Test(groups = { sauceDemo, footer })
    public void socialMediaLinksTest() {
        footerPage.verifySocialMediaLinks("https://twitter.com/saucelabs",
                                 "https://www.linkedin.com/company/sauce-labs/",
                                "https://www.facebook.com/saucelabs");
    }
}
