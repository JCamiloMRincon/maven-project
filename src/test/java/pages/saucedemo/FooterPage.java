package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class FooterPage extends BasePage {

    private final By twitterButton = By.xpath("//a[text()='Twitter']");
    private final By facebookButton = By.xpath("//a[text()='Facebook']");
    private final By linkedinButton = By.xpath("//a[text()='LinkedIn']");

    @Override
    public void waitPageToLoad() { }

    @Override
    public void verifyPage() { }

    @Step("Verifying the social media links")
    public void verifySocialMediaLinks(String twitterUrl, String linkedinUrl, String facebookUrl) {
        final var facebookLabel = find(facebookButton);
        final var twitterLabel = find(twitterButton);
        final var linkedinLabel = find(linkedinButton);

        Logs.info("Verifying the social media links");
        softAssert.assertTrue(facebookLabel.isDisplayed());
        softAssert.assertTrue(facebookLabel.isEnabled());
        softAssert.assertEquals(facebookLabel.getAttribute("href"), facebookUrl);
        softAssert.assertTrue(twitterLabel.isDisplayed());
        softAssert.assertTrue(twitterLabel.isEnabled());
        softAssert.assertEquals(twitterLabel.getAttribute("href"), twitterUrl);
        softAssert.assertTrue(linkedinLabel.isDisplayed());
        softAssert.assertTrue(linkedinLabel.isEnabled());
        softAssert.assertEquals(linkedinLabel.getAttribute("href"), linkedinUrl);
        softAssert.assertAll();
    }
}
