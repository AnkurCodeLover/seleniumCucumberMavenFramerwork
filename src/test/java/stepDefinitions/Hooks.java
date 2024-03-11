package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {

    WebDriver driver;
    Properties prop;

    private Logger log;

    @Before
    public void setup() throws IOException, InterruptedException {
        log = LogManager.getLogger(this.getClass().getName());
        driver = BaseClass.initilizeBrowser();
        log.info("Browser Initialized in Before Method ");
        prop = BaseClass.getProperties();
        driver.get(prop.getProperty("appURL"));
        log.info("Application URL from .properties file is"+prop.getProperty("appURL"));
        driver.manage().window().maximize();

    }


    @AfterStep
    public void addScreenshot(Scenario scenario) {

        if(scenario.isFailed()) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        final byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
        }

    }

    @After
    public void tearDown(Scenario scenario) {

        driver.quit();

    }

}
