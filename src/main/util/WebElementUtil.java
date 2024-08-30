package main.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class WebElementUtil {

    private final SoftAssert softAssert = new SoftAssert();
    private WebElement webElement = null;

    /**
     * @param locator Takes CSS selector as String
     * @param driver  Current webdriver element
     * @return WebElement if element found on page else throws NoSuchElementException
     * exception
     */
    public WebElement getElement(String locator, WebDriver driver) throws NoSuchElementException {
        try {
            webElement = driver.findElement(By.cssSelector(locator));
        } catch (NoSuchElementException e) {
            softAssert.fail(e.getMessage());
            throw new NoSuchElementException(locator);
        }
        softAssert.assertAll();
        return webElement;
    }

    /**
     * @param locator       Takes CSS selector as String
     * @param parentElement parent WebElement in which you are looking for element
     * @return WebElement if element found on page else throws NoSuchElementException
     * exception
     */
    public WebElement getElement(String locator, WebElement parentElement) throws NoSuchElementException {
        try {
            webElement = parentElement.findElement(By.cssSelector(locator));
        } catch (NoSuchElementException e) {
            softAssert.fail(e.getMessage());
            throw new NoSuchElementException(locator);
        }
        softAssert.assertAll();
        return webElement;
    }

    /**
     * @param locator Takes CSS selector as String
     * @param driver  Current webdriver element
     * @return WebElements list if found else throws NoSuchElementException
     * exception
     */
    public List<WebElement> getElements(String locator, WebDriver driver) throws NoSuchElementException {
        List<WebElement> webElements;
        try {
            webElements = driver.findElements(By.cssSelector(locator));
        } catch (NoSuchElementException e) {
            softAssert.fail(e.getMessage());
            throw new NoSuchElementException(locator);
        }
        softAssert.assertAll();
        return webElements;
    }
}
