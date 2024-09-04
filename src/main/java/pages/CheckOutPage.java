package pages;

import main.util.WebElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutPage {
    private final WebElementUtil webElementUtil = new WebElementUtil();

    public WebElement getCountryTextBox(WebDriver driver) {
        return webElementUtil.getElement("[placeholder='Select Country']", driver);
    }

    public WebElement getCountryDropDown(WebDriver driver) {
        return webElementUtil.getElement(".ta-results", driver);
    }

    public WebElement getExactCountyFromDropDown(WebDriver driver) {
        return webElementUtil.getElement(".ta-item:nth-of-type(2)", driver);
    }

    public WebElement getPlaceOrderButton(WebDriver driver) {
        return webElementUtil.getElement(".action__submit", driver);
    }

    public WebElement getSuccessMessage(WebDriver driver) {
        return webElementUtil.getElement(".hero-primary", driver);
    }

}
