package sampleTest;

import main.util.DriverUtil;
import main.util.PropReaderUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageModel.CartPage;
import pageModel.CheckOutPage;
import pageModel.LoginPage;
import pageModel.ProductListPage;

import java.time.Duration;

public class simpleTest {
    public static void main(String[] args) {

        LoginPage loginPage = new LoginPage();
        ProductListPage productListPage = new ProductListPage();
        CartPage cartPage = new CartPage();
        CheckOutPage checkOutPage = new CheckOutPage();
        DriverUtil driverUtil = new DriverUtil();
        PropReaderUtil propReaderUtil = new PropReaderUtil();

        WebDriver driver = driverUtil.getDriver(propReaderUtil.getBrowser());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(propReaderUtil.
                getDefaultExplicitWaitInSeconds()));

        /* Navigate to The Website*/
        driver.get(propReaderUtil.getBaseURL());

        /* Login Page */
        loginPage.getUserEmail(driver).sendKeys(propReaderUtil.getUserName());
        loginPage.getPassword(driver).sendKeys(propReaderUtil.getPassword());
        loginPage.getLoginButton(driver).click();

        /* Product List Page */
        productListPage.clickProductButton(driver, propReaderUtil.getSelectedProductText());
        wait.until(ExpectedConditions.invisibilityOf(productListPage.getAnimationElement(driver)));
        wait.until(ExpectedConditions.visibilityOf(productListPage.getSuccessMessage(driver)));
        productListPage.getCartButton(driver).click();

        /* Add to Cart Page */
        cartPage.getCartItems(driver).forEach(webElement -> {
            if (webElement.getText().contains(propReaderUtil.getSelectedProductText())) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        });
        cartPage.getCartButton(driver).click();

        /* Checkout Page */
        checkOutPage.getCountryTextBox(driver).sendKeys(propReaderUtil.getOrderCountry());
        wait.until(ExpectedConditions.visibilityOf(checkOutPage.getCountryDropDown(driver)));
        checkOutPage.getExactCountyFromDropDown(driver).click();
        checkOutPage.getPlaceOrderButton(driver).click();
        Assert.assertEquals(checkOutPage.getSuccessMessage(driver).getText(),
                propReaderUtil.getOrderConfirmationText());

        /* Closing the browser */
        driverUtil.quitDriver(driver);
    }
}
