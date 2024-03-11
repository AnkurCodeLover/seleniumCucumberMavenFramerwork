package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

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

    @Before
    public void setup() throws IOException, InterruptedException {
        driver = BaseClass.initilizeBrowser();
        prop = BaseClass.getProperties();
        driver.get(prop.getProperty("appURL"));
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
