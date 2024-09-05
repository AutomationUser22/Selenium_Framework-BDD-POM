package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.asserts.SoftAssert;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;

public class DriverUtil {

    private final PropReaderUtil propReaderUtil = new PropReaderUtil();

    public WebDriver driverSetUp(String browser) {

        WebDriver driver;
        DesiredCapabilities desired_capabilities = new DesiredCapabilities();
        URL testGridUrl = null;
        SoftAssert softAssert = new SoftAssert();
        String projectARN = propReaderUtil.getProjectARN();

        if(propReaderUtil.getLocalOrCloud().equals("cloud")) {
            try(DeviceFarmClient deviceFarmClient = DeviceFarmClient.builder().region(Region.US_WEST_2).build()){
                CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder()
                        .expiresInSeconds(300)
                        .projectArn(projectARN)
                        .build();
                CreateTestGridUrlResponse response = deviceFarmClient.createTestGridUrl(request);
                testGridUrl = new URL(response.url());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (browser.equals("firefox")) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                softAssert.assertTrue(testGridUrl != null, "Test Grid URL is null");
                driver = new RemoteWebDriver(testGridUrl, firefoxOptions);
            } else {
                ChromeOptions chromeOptions = new ChromeOptions().addArguments(propReaderUtil.getChromeOptions());
                softAssert.assertTrue(testGridUrl != null, "Test Grid URL is null");
                driver = new RemoteWebDriver(testGridUrl, chromeOptions);
            }
        }
        else {
            if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().addArguments(propReaderUtil.getChromeOptions()));
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(propReaderUtil.getDefaultImplicitWaitInSeconds()));
        softAssert.assertAll();
        return driver;
    }

    public void tearDown(WebDriver driver) {
        driver.quit();
    }
}
