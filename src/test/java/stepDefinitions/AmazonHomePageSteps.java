package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.AmazonHomePage;
import pageObjects.AmazonProductPage;

import java.util.Map;

public class AmazonHomePageSteps {

    AmazonHomePage homePage;
    AmazonProductPage amazonProductPage;

    @Given("User is on the Amazon homepage")
    public void user_is_on_the_amazon_homepage() {
        homePage = new AmazonHomePage(BaseClass.getDriver());
        homePage.acceptCookies();
    }
    @When("User select the Electronics & Computers from Department section")
    public void user_select_the_electronics_computers_from_department_section() {
            homePage.clickOnAllDepartmentLink();
            homePage.clickOnElectronicsAndComputersLink();

    }
    @Then("User select Phone & Accessories from Electronics section")
    public void user_select_phone_accessories_from_electronics_section() throws InterruptedException {
        homePage.clickOnPhoneAndAccessoriesLink();
    }

    @Then("User select Mobile Phones & Smartphones")
    public void user_select_mobile_phones_smartphones() {
        amazonProductPage = new AmazonProductPage(BaseClass.getDriver());
        amazonProductPage.clickOnMobilePhoneLink();
    }
    @Then("User selects the desired configuration as follows")
    public void user_selects_the_desired_configuration_as_follows(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class,String.class);
        amazonProductPage.filterBySamsungPhones(dataMap.get("PhoneBrand"));
        amazonProductPage.filterByMegaPixel(dataMap.get("CameraResolution"));
        amazonProductPage.filterByManufacturedYear(dataMap.get("ModelYear"));
        amazonProductPage.filterByPriceRange(dataMap.get("lowerPriceRange"),dataMap.get("higherPriceRange"));
    }
    @Then("user should able to verify the page after desired configuration")
    public void user_should_able_to_verify_the_page_after_desired_configuration() {
        Assert.assertTrue(amazonProductPage.verifyDisplayedMessage());
    }

}
