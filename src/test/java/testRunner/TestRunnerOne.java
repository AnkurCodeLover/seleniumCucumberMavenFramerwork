package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features= {"src/test/resources/Features/"},
        glue="stepDefinitions",
        tags="@sanity",
        plugin= {"pretty", "html:reports/cucumber_report.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/rerun.txt"})

public class TestRunnerOne extends AbstractTestNGCucumberTests {
}
