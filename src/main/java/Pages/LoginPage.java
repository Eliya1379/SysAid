package Pages;

import com.asprise.ocr.Ocr;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.net.URL;


public class LoginPage extends BaseFunctions {

    @FindBy(css = "input[name = 'userName']")
    WebElement userName;

    @FindBy(css = "input[name = 'password']")
    WebElement password;

    @FindBy(id = "loginBtn")
    WebElement loginButton;

    @FindBy(css = "#PSlinks a")
    WebElement forgetPasswordButton;

    @FindBy(css = "#loginFooter a")
    WebElement helpDescButton;

    @FindBy(css = "input[name ='rememberMeForDisplay']")
    WebElement rememberButton;

    @FindBy(id = "error_message")
    WebElement errorMessage;

    @FindBy(id = "topstrip_userPhoto")
    WebElement userNamePhoto;

    @FindBy(css = "[href='#/function/logout']")
    WebElement logOutButton;

    @FindBy(id = "inCaptchaChars")
    WebElement captcha;

    @FindBy(id = "cap")
    WebElement captchaImage;

    public void login(String userNameValue, String passwordValue) {
        sendKeys(userName, userNameValue, true);
        sendKeys(password, passwordValue, true);
        click(loginButton);
        try {
            if (captchaImage.isDisplayed()) {
                String path = captchaImage.getAttribute("src");
                URL url = new URL(path);
                Image image = ImageIO.read(url);
                System.out.println("image 'SRC' is = " + image);
                Ocr ocr = new Ocr();
                String s = ocr.recognize(new File[]{new File(String.valueOf(image))},
                        Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
                sendKeys(captcha, s, true);
                System.out.println("Text From Image : \n" + s);
                System.out.println("Length of total text : \n" + s.length());
            }
        } catch (Exception e) {
            // capta not visible
        }


    }

    public void logOut() {
        click(userNamePhoto);
        click(logOutButton);
    }

    public void clickOnRememberMe() {
        click(rememberButton);
    }

    public void clickOnForgetPassword() {
        click(forgetPasswordButton);
        switchToNewWindow();
    }

    public void clickOnHelpDesc() {
        click(helpDescButton);
        switchToNewWindow();
    }

    public String getErrorMessage() {
        String message = null;
        changeTimeOut(3);
        try {
            message = errorMessage.getText();
        } catch (Exception e) {
            message = "Not have error message";
        } finally {
            changeToDefaultTimeOut();
            return message;
        }
    }

    public boolean checkIfUserOnline() {
        changeTimeOut(4);
        try {
            elementToBeVisible(userNamePhoto);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            changeToDefaultTimeOut();
        }
    }
}
