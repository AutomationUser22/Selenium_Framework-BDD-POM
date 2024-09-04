package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.DriverUtil;
import util.PropReaderUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class loginPageStepDef {
    LoginPage loginPage = new LoginPage();
    DriverUtil driverUtil = new DriverUtil();
    PropReaderUtil propReaderUtil = new PropReaderUtil();
    WebDriver driver = driverUtil.driverSetUp(propReaderUtil.getBrowser());
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(propReaderUtil.
            getDefaultExplicitWaitInSeconds()));

    @Given("Login Page is open")
    public void login_page_is_open() {
        driver.get(propReaderUtil.getBaseURL());
    }
    @When("User enter correct UserName")
    public void user_enter_correct_UserName() {
        loginPage.getUserEmail(driver).sendKeys(propReaderUtil.getUserName());
    }
    @When("User enter correct Password")
    public void user_enter_correct_Password() {
        loginPage.getPassword(driver).sendKeys(propReaderUtil.getPassword());
    }
    @When("User clicks on Login button")
    public void user_clicks_on_Login_button() {
        loginPage.getLoginButton(driver).click();
    }
    @Then("Product list Page should open")
    public void product_list_page_should_open() {
        System.out.println("Product list Page should open");
    }
}
