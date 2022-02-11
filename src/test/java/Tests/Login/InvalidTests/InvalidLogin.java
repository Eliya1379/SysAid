package Tests.Login.InvalidTests;

import Tests.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static Utilites.Excel.getExcelData;
import static Utilites.Utilities.loginPage;

public class InvalidLogin{
    @Description("login valid test")
    @Test(dataProvider = "new account")
    public static void LoginTest(String userName, String password){
        loginPage.login(userName, password);
        Assert.assertFalse(loginPage.checkIfUserOnline());
    }

    @BeforeClass
    public void checkIfUserOnline() {
        if (loginPage.checkIfUserOnline()) {
            loginPage.logOut();
        }
    }

    @DataProvider(name = "new account")
    public Object[][] loginData() {
        Object[][] arrayObject = getExcelData("C:\\Users\\tkhvn\\IdeaProjects\\apiTesting10\\SysAidProjectAutomation\\src\\main\\resources\\UsersInvalid.xls", "Sheet1");
        return arrayObject;
    }
}
