package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasePage extends BaseFunctions {
    By menuLocator = By.cssSelector("[class ='main-items'] > span");
    By categoryMenuLocator = By.cssSelector("[class='megamenu_fullwidth service-desk'] span");

    By pageVisible = By.className("breadcrumbs-wrapper-title-2");

    @FindBy(id = "contentFrame")
    WebElement iframeElement;

    public void chooseCategoryMenu(String menuValue, String categoryValue) {
        // choose from menu
        List<WebElement> menuList = numberOfElementMoreThen(menuLocator, 0);
        for (WebElement menuElement : menuList) {
            if (contains(menuElement.getAttribute("class"), menuValue)) {
                click(menuElement);
                break;
            }
        }

        // choose from drop down category
        List<WebElement> categoryMenu = numberOfElementMoreThen(categoryMenuLocator, 0);
        for (WebElement categoryElement : categoryMenu) {
            if (contains(categoryElement.getAttribute("innerHTML"), categoryValue)) {
                click(categoryElement);
                break;
            }
        }
        switchToFrame(iframeElement);
    }

    public String getPageNameVisible() {
        // get the name of the page is visible
        List<WebElement> page = numberOfElementMoreThen(pageVisible, 0);
        return page.get(page.size() - 1).getText();
    }
}
