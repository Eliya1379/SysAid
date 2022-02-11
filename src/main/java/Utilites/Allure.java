package Utilites;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static Utilites.Utilities.driver;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static org.testng.Reporter.log;

public class Allure {

    @Attachment(value = "Screenshot", type = "image/png")
    public static void screenShot(String value) {
        io.qameta.allure.Allure.addAttachment(value, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void setAllureEnvironment(String browser, String url) {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browser)
                        .put("URL", url)
                        .build());
    }

    @Step("{0}")
    public static void logToReport(String Title , String message) {
        io.qameta.allure.Allure.addAttachment(message , "sdf");
        log(message);
    }
}
