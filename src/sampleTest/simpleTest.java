package sampleTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.util.WebElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageModel.LoginPage;

import java.time.Duration;
import java.util.List;

public class simpleTest {
    public static void main(String[] args) {

        LoginPage loginPage = new LoginPage();

        WebElementUtil webElementUtil = new WebElementUtil();


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--disable-search-engine-choice-screen"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/client");

        loginPage.getUserEmail(driver).sendKeys("test199191@test.com");
        loginPage.getPassword(driver).sendKeys("Test@123");
        loginPage.getLoginButton(driver).click();

        List<WebElement> arrayList = webElementUtil.getElements(".mb-3", driver);

        arrayList.forEach(webElement -> {
            if(webElementUtil.getElement("b", webElement).getText().contains("ZARA")){
                webElementUtil.getElement(".card-body button:last-of-type", driver).click();
            }
        });

        wait.until(ExpectedConditions.invisibilityOf(webElementUtil.getElement(".ng-animating", driver)));
        wait.until(ExpectedConditions.visibilityOf(webElementUtil.getElement("#toast-container", driver)));

        webElementUtil.getElement("[routerlink*='cart']", driver).click();

        List<WebElement> list = webElementUtil.getElements(".cartSection h3", driver);
        list.forEach(webElement -> {
            if(webElement.getText().contains("ZARA")){
                Assert.assertTrue(true);
            }
            else {
                Assert.fail();
            }
        });
        webElementUtil.getElement(".totalRow button", driver).click();

        webElementUtil.getElement("[placeholder='Select Country']", driver).sendKeys("india");
        wait.until(ExpectedConditions.visibilityOf(webElementUtil.getElement(".ta-results", driver)));
        webElementUtil.getElement(".ta-item:nth-of-type(2)", driver).click();
        webElementUtil.getElement(".action__submit", driver).click();
        Assert.assertEquals(webElementUtil.getElement(".hero-primary", driver).getText(), "THANKYOU FOR THE ORDER.");
        driver.quit();
    }
}
