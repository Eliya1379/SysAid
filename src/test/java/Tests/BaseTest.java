package Tests;

import Utilites.Allure;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static Utilites.Utilities.*;

public class BaseTest {

    @Parameters({"platform", "url"})
    @BeforeTest()
    public void StartTest(String platform, String url) {
        Allure.setAllureEnvironment(platform, url);
        startDriver(platform);
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor) driver;
        startPageFactory();
        driver.get(url);
        driver.manage().window().maximize();

    }

//    @AfterTest
//    public void finishTest() {
//        driver.close();
//        driver.quit();
//    }

    public void startDriver(String platform) {
        switch (platform) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Explorer":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "FireFox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("sdfdf");
                Allure.logToReport("platform not exist", "The platform : " + platform);
                break;
        }
    }

}
