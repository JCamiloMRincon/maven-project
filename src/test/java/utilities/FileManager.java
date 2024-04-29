package utilities;

import io.qameta.allure.Attachment;
import io.qameta.allure.FileSystemResultsWriter;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private final static String screenshotsPath = "src/test/resources/screenshots";
    private final static String pageStructurePath = "src/test/resources/pageStructure";

    public static void getScreenshot(String screenshotsName) {

        Logs.debug("Taking screenshot...");
        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s/%s.png", screenshotsPath, screenshotsName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch(IOException ioException) {
            Logs.error("Error after taking the screenshot: %s", ioException.getLocalizedMessage());
        }
    }

    public static void getPageSource(String fileName) {

        Logs.debug("Taking the page source...");
        final var path = String.format("%s/page-source-%s.html", pageStructurePath, fileName);

        try {
            final var file = new File(path);

            Logs.debug("Creating the parent directories if they do not exist");
            if(file.getParentFile().mkdirs()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = new WebDriverProvider().get().getPageSource();
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }
        } catch(IOException ioException) {
            Logs.error("Fail to get the page source: %s", ioException.getLocalizedMessage());
        }
    }

    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Deleting the previous evidence...");
            FileUtils.deleteDirectory(new File(screenshotsPath));
            FileUtils.deleteDirectory(new File(pageStructurePath));
        } catch(IOException ioException) {
            Logs.error("Fail to delete the previous evidence: %s", ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "failureScreenshot", type = "image/png")
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "pageSource", type = "text/html", fileExtension = "txt")
    public static String getPageSource() {
        return Jsoup.parse(new WebDriverProvider().get().getPageSource()).toString();
    }

}
