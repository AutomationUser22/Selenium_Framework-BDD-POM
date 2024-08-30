package pageModel;

import main.util.WebElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final WebElementUtil webElementUtil = new WebElementUtil();

    public List<WebElement> getCartItems(WebDriver driver) {
        return webElementUtil.getElements(".cartSection h3", driver);
    }

    public WebElement getCartButton(WebDriver driver) {
        return webElementUtil.getElement(".totalRow button", driver);
    }
}
