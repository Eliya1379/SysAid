package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static Utilites.Allure.screenShot;

public class HelpDeskPage extends BasePage {

    @FindBy(className = "breadcrumbs-wrapper-new-button")
    WebElement newButton;

    @FindBy(css = "[class = 'newListSelected problem_type_CustomSelect'] span")
    WebElement selectCategory;

    By dropDownCategoryLocator = By.cssSelector("#addScroll_problem_type_CustomSelect .newList li");

    @FindBy(css = "[class = 'newListSelected subcategory_CustomSelect'] span")
    WebElement selectSubCategory;

    By dropDownSubCategoryLocator = By.cssSelector("#addScroll_subcategory_CustomSelect .newList li");

    @FindBy(css = "[class = 'newListSelected thirdLevelCategory_CustomSelect'] span")
    WebElement selectThirdCategory;

    By dropDownThirdCategoryLocator = By.cssSelector("#addScroll_thirdLevelCategory_CustomSelect .newList li");

    @FindBy(id = "formLoadingIndicator")
    WebElement loadPage;

    @FindBy(id = "title")
    WebElement title;

    @FindBy(id = "desc")
    WebElement description;

    @FindBy(css = "[class = 'newListSelected requestUser_CustomSelect'] span")
    WebElement selectUserRequest;

    By dropDownUserCategoryLocator = By.cssSelector("#addScroll_requestUser_CustomSelect li");

    @FindBy(id = "sr-save-btn")
    WebElement saveButton;

    @FindBy(id = "searchField")
    WebElement searchRecord;

    @FindBy(id = "search_button")
    WebElement searchButton;

    By listOfRecordLocator = By.cssSelector("#t [sr_type = '1']");
    By listOfDetailsRecordLocator = By.className("UI_GridCellInner");
    By checkBoxOfRecord = By.className("GridCheckbox");

    public void addNewRecord() {
        click(newButton);
    }

    public void selectFromCategory(String value) {
        selectFromDropDown(selectCategory, dropDownCategoryLocator, value);
        elementToBeInVisible(loadPage);
    }

    public void selectFromSubCategory(String value) {
        selectFromDropDown(selectSubCategory, dropDownSubCategoryLocator, value);
        elementToBeInVisible(loadPage);
    }

    public void selectFromThirdCategory(String value) {
        selectFromDropDown(selectThirdCategory, dropDownThirdCategoryLocator, value);
        elementToBeInVisible(loadPage);
    }

    public void selectFromRequest(String value) {
        scrollToElement(selectUserRequest);
        selectFromDropDown(selectUserRequest, dropDownUserCategoryLocator, value);
        elementToBeInVisible(loadPage);
    }

    public void setTile(String value) {
        sendKeys(title, value, true);
    }

    public void setDescription(String value) {
        sendKeys(description, value + "\n", false);
    }

    public void clickOnSave() {
        click(saveButton);
    }

    public void searchTheRecord(String value) {
        sendKeys(searchRecord, value, true);
        click(searchButton);
    }

    public int getNumberOfLastRecord() {
        try {
            List<WebElement> records = numberOfElementMoreThen(listOfRecordLocator, 0);
            WebElement lastRecords = records.get(0);
            String numberRecordStr = lastRecords.findElements(listOfDetailsRecordLocator).get(0).getAttribute("innerText");
            int numberRecord = Integer.parseInt(lastRecords.findElements(listOfDetailsRecordLocator).get(0).getAttribute("innerText"));
            return numberRecord;
        } catch (Exception e) {
            // Not have any record
            return 0;
        }
    }

    // check if new record is add if record have the correct title and correct requestUser
    public boolean checkIfRecordIsFound(String title, String requestUser) {
        try {
            List<WebElement> recordsListElements = numberOfElementMoreThen(listOfRecordLocator, 0);
            boolean isCorrect = false;
            // check if details of new record is correct
            if (checkRecord(recordsListElements.get(0), title, requestUser)) {
                screenShot("new record is added title : " + title + " request user : " + requestUser);
                isCorrect = true;
            }
            if (isCorrect == false) {
                screenShot("new record not added the new Record title : " + title + " request user : " + requestUser);
                return isCorrect;
            } else {
                return isCorrect;
            }
            // not found any record
        } catch (Exception e) {
            screenShot("new record not added the new Record title : " + title + " request user : " + requestUser);
            return false;
        }
    }

    // check if record is have the correct title and correct requestUser
    public boolean checkRecord(WebElement record, String title, String requestUser) {
        List<WebElement> listOfDetailsRecordElements = record.findElements(listOfDetailsRecordLocator);
        if (listOfDetailsRecordElements.get(4).getAttribute("innerText").toLowerCase().equals(title.toLowerCase()) &&
                listOfDetailsRecordElements.get(6).getAttribute("innerText").toLowerCase().equals(requestUser.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    public void chooseRecord() {
        List<WebElement> recordsListElements = numberOfElementMoreThen(listOfRecordLocator, 0);
        recordsListElements.get(0).findElement(checkBoxOfRecord).click();
    }

    public boolean checkIfRecordSelected() {
        List<WebElement> recordsListElements = numberOfElementMoreThen(listOfRecordLocator, 0);
        if (recordsListElements.get(0).getAttribute("class").equals("RowSelected")) {
            screenShot("The record is selected");
            return true;
        } else {
            screenShot("The record is not selected");
            return false;
        }
    }
}
