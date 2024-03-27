package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {"src/test/resources/Features/"},
        glue="stepDefinitions",
        tags="@sanity1",
        plugin= {"pretty", "html:reports/cucumber_report.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/rerun.txt"})


/*
@RunWith(Cucumber.class)
@CucumberOptions(

        features= {".//Features/AmazonDeviceSearch.feature"},
        glue="stepDefinitions",
        plugin= {"pretty", "html:reports/cucumber_report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"},
        dryRun=false,
        monochrome=true, // to avoid junk characters in output
        publish=true,
        tags="@sanity"

)
*/
public class TestRunnerTwo extends AbstractTestNGCucumberTests {
}
