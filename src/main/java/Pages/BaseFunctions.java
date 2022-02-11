package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static Utilites.Utilities.*;

public class BaseFunctions {

    public WebElement elementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement elementToBeVisible(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    public void elementToBeInVisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public WebElement elementToBeClickAble(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectFromDropDown(WebElement element, By dropDown, String value) {
        click(element);
        List<WebElement> list = numberOfElementMoreThen(dropDown, 0);
        for (WebElement ele : list) {
            if (ele.getAttribute("innerHTML").toLowerCase().equals(value.toLowerCase())) {
                regular_click(ele);
                break;
            }
        }
    }

    public void switchToNewWindow() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void switchToDefaultWindow() {
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
    }

    public void click(WebElement element) {
        elementToBeVisible(element);
        elementToBeClickAble(element).click();
    }

    public void regular_click(WebElement element) {
        elementToBeVisible(element).click();
    }

    @Step
    public void sendKeys(WebElement element, String value, Boolean clear) {
        elementToBeVisible(element);
        if (clear) {
            element.clear();
        }
        element.sendKeys(value);
    }

    public List<WebElement> numberOfElementMoreThen(By locator, int minNumber) {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, minNumber));
    }

    public void changeTimeOut(int timeout) {
        wait.withTimeout(timeout, TimeUnit.SECONDS);
    }

    public void changeToDefaultTimeOut() {
        wait.withTimeout(10, TimeUnit.SECONDS);
    }

    public boolean contains(String val1, String val2) {
        val1 = val1.toLowerCase().replace("-", " ");
        val2 = val2.toLowerCase();
        if (val1.contains(val2)) {
            return true;
        } else {
            return false;
        }
    }


    public List<WebElement> numberOfElementLessThen(By locator, int maxNumber) {
        return wait.until(ExpectedConditions.numberOfElementsToBeLessThan(locator, maxNumber));
    }

    public List<WebElement> numberOfElementToBe(By locator, int number) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollDown() {
        js.executeScript("window.scrollBy(0,250)");
    }

    public void switchToFrame(WebElement frame) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void selectByText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public WebElement elementByName(String name) {
        return driver.findElement(By.partialLinkText(name));
    }


}
