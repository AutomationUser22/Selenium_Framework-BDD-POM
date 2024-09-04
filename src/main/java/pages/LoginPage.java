package pages;

import util.WebElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private final WebElementUtil webElementUtil = new WebElementUtil();

    public WebElement getUserEmail(WebDriver driver) {
        return webElementUtil.getElement("#userEmail", driver);
    }

    public WebElement getPassword(WebDriver driver) {
        return webElementUtil.getElement("#userPassword", driver);
    }

    public WebElement getLoginButton(WebDriver driver) {
        return webElementUtil.getElement("#login", driver);
    }
}
