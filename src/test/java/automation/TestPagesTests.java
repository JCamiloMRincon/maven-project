package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.io.File;

public class TestPagesTests extends BaseTest {

    @Test
    public void uploadFileTest() {
        final var url = "https://testpages.eviltester.com/styled/file-upload-test.html";

        Logs.info("Navigating to the page");
        driver.get(url);

        final var file = new File("src/test/resources/samples/sampleImage.jpg");

        Logs.info("Uploading the image");
        driver.findElement(By.id("fileinput")).sendKeys(file.getAbsolutePath()); // I write the absolute path

        Logs.info("Selecting the image option");
        driver.findElement(By.id("itsanimage")).click();

        Logs.info("Clicking on the green button");
        driver.findElement(By.name("upload")).click();

        Logs.info("Verifying that the image has been uploaded");
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='You uploaded this image:']")).isDisplayed());
    }
}
