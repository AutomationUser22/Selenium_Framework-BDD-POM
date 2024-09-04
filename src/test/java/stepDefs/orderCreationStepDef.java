package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckOutPage;
import pages.ProductListPage;
import util.DriverUtil;
import util.PropReaderUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class orderCreationStepDef {

    LoginPage loginPage = new LoginPage();
    ProductListPage productListPage = new ProductListPage();
    CartPage cartPage = new CartPage();
    CheckOutPage checkOutPage = new CheckOutPage();

    DriverUtil driverUtil = new DriverUtil();
    PropReaderUtil propReaderUtil = new PropReaderUtil();
    WebDriver driver = driverUtil.driverSetUp(propReaderUtil.getBrowser());
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(propReaderUtil.
            getDefaultExplicitWaitInSeconds()));

    @Given("Login Page is open")
    public void login_page_is_open() {
        driver.get(propReaderUtil.getBaseURL());
    }
    @When("User provider valid credentials")
    public void userProviderValidCredentials() {
        loginPage.getUserEmail(driver).sendKeys(propReaderUtil.getUserName());
        loginPage.getPassword(driver).sendKeys(propReaderUtil.getPassword());
        loginPage.getLoginButton(driver).click();
    }

    @And("User adds ZARA product to the Cart")
    public void userAddsZARAProductToTheCart() {
        productListPage.clickProductButton(driver, propReaderUtil.getSelectedProductText());
        wait.until(ExpectedConditions.visibilityOf(productListPage.getSuccessMessage(driver)));
        wait.until(ExpectedConditions.invisibilityOf(productListPage.getAnimationElement(driver)));
        productListPage.getCartButton(driver).click();
    }

    @And("User adds details on Cart Page")
    public void userAddsDetailsOnCartPage() {
        cartPage.getCartItems(driver).forEach(webElement -> {
            if (webElement.getText().contains(propReaderUtil.getSelectedProductText())) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        });
        cartPage.getCartButton(driver).click();
    }

    @And("User adds details on CheckOut Page")
    public void userAddsDetailsOnCheckOutPage() {
        checkOutPage.getCountryTextBox(driver).sendKeys(propReaderUtil.getOrderCountry());
        wait.until(ExpectedConditions.visibilityOf(checkOutPage.getCountryDropDown(driver)));
        checkOutPage.getExactCountyFromDropDown(driver).click();
        checkOutPage.getPlaceOrderButton(driver).click();
        Assert.assertEquals(checkOutPage.getSuccessMessage(driver).getText(),
                propReaderUtil.getOrderConfirmationText());
    }

    @Then("Browser get closed")
    public void browserGetClosed() {
        driverUtil.tearDown(driver);
    }
}
