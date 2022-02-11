package Tests.Login.ValidTests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utilites.Utilities.driver;
import static Utilites.Utilities.loginPage;

public class NavToForgetPassword {

    @Description("login valid test")
    @Test
    public static void LoginTest() {
        loginPage.clickOnForgetPassword();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qaautomationtests.sysaidit.com/ForgotPassword.jsp?accountid=qaautomationtests");
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
