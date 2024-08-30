package sampleTest;

import main.util.DriverUtil;
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

        WebDriver driver = driverUtil.getDriver("chrome");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        /* Navigate to The Website*/
        driver.get("https://rahulshettyacademy.com/client");

        /* Login Page */
        loginPage.getUserEmail(driver).sendKeys("test199191@test.com");
        loginPage.getPassword(driver).sendKeys("Test@123");
        loginPage.getLoginButton(driver).click();

        /* Product List Page */
        productListPage.clickProductButton(driver, "ZARA");
        wait.until(ExpectedConditions.invisibilityOf(productListPage.getAnimationElement(driver)));
        wait.until(ExpectedConditions.visibilityOf(productListPage.getSuccessMessage(driver)));
        productListPage.getCartButton(driver).click();

        /* Add to Cart Page */
        cartPage.getCartItems(driver).forEach(webElement -> {
            if (webElement.getText().contains("ZARA")) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        });
        cartPage.getCartButton(driver).click();

        /* Checkout Page */
        checkOutPage.getCountryTextBox(driver).sendKeys("india");
        wait.until(ExpectedConditions.visibilityOf(checkOutPage.getCountryDropDown(driver)));
        checkOutPage.getExactCountyFromDropDown(driver).click();
        checkOutPage.getPlaceOrderButton(driver).click();
        Assert.assertEquals(checkOutPage.getSuccessMessage(driver).getText(), "THANKYOU FOR THE ORDER.");

        /* Closing the browser */
        driverUtil.quitDriver(driver);
    }
}
