package Tests.HelpDesk;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utilites.Utilities.*;

public class addNewRecordTest {
    @Description("sure user is login")
    @BeforeClass
    public void Login() {
        if (!loginPage.checkIfUserOnline()) {
            loginPage.login("myAdmin" , "A12345^y");
        }
    }

    @Description("Add new record")
    @Test
    public void AddRecord() {
        helpDeskPage.chooseCategoryMenu("Service Desk", "Incidents");
        helpDeskPage.searchTheRecord("eu");
        int lastRecord = helpDeskPage.getNumberOfLastRecord();
        Assert.assertEquals(helpDeskPage.getPageNameVisible(), "List");
        helpDeskPage.addNewRecord();
        helpDeskPage.selectFromCategory("ERP");
        helpDeskPage.selectFromSubCategory("HR");
        helpDeskPage.selectFromThirdCategory("Other");
        helpDeskPage.setTile("Problem with my laptop");
        helpDeskPage.setDescription("check");
        helpDeskPage.setDescription("from");
        helpDeskPage.setDescription("2");
        helpDeskPage.selectFromRequest("eu");
        helpDeskPage.clickOnSave();
        Assert.assertEquals(helpDeskPage.getPageNameVisible(), "List");
        helpDeskPage.searchTheRecord(String.valueOf(lastRecord + 1));
        Assert.assertTrue(helpDeskPage.checkIfRecordIsFound("Problem with my laptop", "eu"));
        helpDeskPage.chooseRecord();
        softAssert.assertTrue(helpDeskPage.checkIfRecordSelected());
        softAssert.assertAll();
    }
}

