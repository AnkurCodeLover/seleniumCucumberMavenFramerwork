package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features= {".//Features/AmazonDeviceSearch.feature"},
        //features= {"@target/rerun.txt"},
        glue="stepDefinitions",
        plugin= {"pretty", "html:reports/myreport.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"},

        dryRun=false,
        monochrome=true, // to avoid junk characters in output
        publish=true,
        tags="@sanity"
        //tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
