package Tests.Login.ValidTests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utilites.Utilities.driver;
import static Utilites.Utilities.loginPage;

public class navToHelpDesc {

    @Description("login valid test")
    @Test
    public void LoginTest() {
        loginPage.clickOnHelpDesc();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.sysaid.com/");
    }

    @AfterClass
    public void returnToDefaultWindow() {
        loginPage.switchToDefaultWindow();
    }

    @BeforeClass
    public void checkIfUserOnline() {
        if (loginPage.checkIfUserOnline()) {
            loginPage.logOut();
        }
    }
}
