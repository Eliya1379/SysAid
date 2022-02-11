package Utilites;

import Pages.HelpDeskPage;
import Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Utilities {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;
    public static SoftAssert softAssert = new SoftAssert();

    public static LoginPage loginPage;
    public static HelpDeskPage helpDeskPage;

    public static void startPageFactory() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        helpDeskPage = PageFactory.initElements(driver, HelpDeskPage.class);
    }

}
