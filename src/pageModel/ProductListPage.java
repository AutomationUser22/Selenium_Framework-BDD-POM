package pageModel;

import main.util.WebElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductListPage {

    private final WebElementUtil webElementUtil = new WebElementUtil();

    public void clickProductButton(WebDriver driver, String productName) {
        getProductList(driver).forEach(webElement -> {
            if (webElementUtil.getElement("b", webElement).getText().contains(productName)) {
                webElementUtil.getElement(".card-body button:last-of-type", driver).click();
            }
        });
    }

    private List<WebElement> getProductList(WebDriver driver) {
        return webElementUtil.getElements(".mb-3", driver);
    }

    public WebElement getAnimationElement(WebDriver driver) {
        return webElementUtil.getElement(".ng-animating", driver);
    }

    public WebElement getSuccessMessage(WebDriver driver) {
        return webElementUtil.getElement("#toast-container", driver);
    }

    public WebElement getCartButton(WebDriver driver) {
        return webElementUtil.getElement("[routerlink*='cart']", driver);
    }

}
