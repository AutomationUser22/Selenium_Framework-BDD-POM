package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/featureFiles"}
        ,glue={"stepDefs"}
        ,plugin = {"pretty",
        "html:target/cucumber.html",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
        ,monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
