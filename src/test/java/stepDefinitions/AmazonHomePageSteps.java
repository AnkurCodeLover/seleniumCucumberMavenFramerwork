package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.AmazonHomePage;
import pageObjects.AmazonProductPage;
import utilities.DataReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonHomePageSteps {

    AmazonHomePage homePage;
    AmazonProductPage amazonProductPage;

    List<HashMap<String, String>> excelDataMapper;

    @Given("User is on the Amazon homepage")
    public void user_is_on_the_amazon_homepage() throws InterruptedException {
        homePage = new AmazonHomePage(BaseClass.getDriver());
        homePage.acceptCookies();
    }

    @When("User select the Electronics & Computers from Department section")
    public void user_select_the_electronics_computers_from_department_section() {
        Assert.assertTrue("HomePageTitle is not displayed",homePage.isHomePageTitleDisplayed());
        Assert.assertTrue("AllDepartment is not displayed",homePage.isAllDepartmentDisplayed());
        homePage.clickOnAllDepartmentLink();
        Assert.assertTrue("ElectronicsAndComputers is not displayed",homePage.isElectronicsAndComputersDisplayed());
        homePage.clickOnElectronicsAndComputersLink();

    }

    @Then("User select Phone & Accessories from Electronics section")
    public void user_select_phone_accessories_from_electronics_section() throws InterruptedException {
        Assert.assertTrue("PhonesAndAccessories Title is not displayed",homePage.isPhonesAndAccessoriesDisplayed());
        homePage.clickOnPhoneAndAccessoriesLink();
    }

    @Then("User select Mobile Phones & Smartphones")
    public void user_select_mobile_phones_smartphones() {
        amazonProductPage = new AmazonProductPage(BaseClass.getDriver());
        amazonProductPage.clickOnMobilePhoneLink();
    }

    @Then("User selects the desired configuration as follows")
    public void user_selects_the_desired_configuration_as_follows(io.cucumber.datatable.DataTable dataTable) {
        Assert.assertTrue("ProductPage Title is not displayed",amazonProductPage.isProductPageDisplayed());
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        amazonProductPage.filterBySamsungPhones(dataMap.get("PhoneBrand"));
        amazonProductPage.filterByMegaPixel(dataMap.get("CameraResolution"));
        amazonProductPage.filterByManufacturedYear(dataMap.get("ModelYear"));
        amazonProductPage.filterByPriceRange(dataMap.get("lowerPriceRange"), dataMap.get("higherPriceRange"));
    }

    @Then("user should able to verify the page after desired configuration")
    public void user_should_able_to_verify_the_page_after_desired_configuration() {
        Assert.assertTrue("Blank Page message is not displayed",amazonProductPage.verifyDisplayedMessage());
    }

    @Then("user select the {string},{string},{string},{string} and {string} as desired configuration")
    public void user_select_the_and(String PhoneBrand, String CameraResolution, String ModelYear, String lowerPriceRange, String higherPriceRange) {
        Assert.assertTrue("ProductPage Title is not displayed",amazonProductPage.isProductPageDisplayed());
        amazonProductPage.filterBySamsungPhones(PhoneBrand);
        amazonProductPage.filterByMegaPixel(CameraResolution);
        amazonProductPage.filterByManufacturedYear(ModelYear);
        amazonProductPage.filterByPriceRange(lowerPriceRange, higherPriceRange);
    }

    @Then("User selects the desired configuration")
    public void user_selects_the_desired_configuration() {
        Assert.assertTrue("ProductPage Title is not displayed",amazonProductPage.isProductPageDisplayed());
        excelDataMapper = DataReader.data(System.getProperty("user.dir") + "\\testData\\TestData.xlsx", "AmazonData");
        String lowRange = null, highRange = null;
        for (Map<String, String> map : excelDataMapper) {
            // Iterate over each HashMap
            for (Map.Entry<String, String> entry : map.entrySet()) {
                // Access key-value pairs
                String key = entry.getKey();
                switch (entry.getKey()) {
                    case "PhoneBrand":
                        amazonProductPage.filterBySamsungPhones(entry.getValue().trim());
                        break;
                    case "CameraResolution":
                        amazonProductPage.filterByMegaPixel(entry.getValue().trim());
                        break;
                    case "ModelYear":
                        amazonProductPage.filterByManufacturedYear(entry.getValue().trim());
                        break;
                    case "lowerPriceRange":
                        lowRange = entry.getValue().trim();
                        break;
                    case "higherPriceRange":
                        highRange = entry.getValue().trim();
                        amazonProductPage.filterByPriceRange(lowRange,highRange);
                        break;
                }
            }

        }
    }
}


