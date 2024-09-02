package main.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverUtil {

    private final PropReaderUtil propReaderUtil = new PropReaderUtil();

    public WebDriver getDriver(String browser) {
        WebDriver driver;
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(new ChromeOptions().addArguments(propReaderUtil.getChromeOptions()));
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(propReaderUtil.getDefaultImplicitWaitInSeconds()));
        return driver;
    }

    public void quitDriver(WebDriver driver) {
        driver.quit();
    }
}
