package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private Logger log;
    List<HashMap<String, String>> excelDataMapper;


    @Given("User is on the Amazon homepage")
    public void user_is_on_the_amazon_homepage() throws InterruptedException {
        try {
        log = LogManager.getLogger(AmazonHomePageSteps.class);
        homePage = new AmazonHomePage(BaseClass.getDriver());
            homePage.acceptCookies();
            log.info("Accepting cookies of Amazon");
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Element No Found"));
        }
    }

    @When("User select the Electronics & Computers from Department section")
    public void user_select_the_electronics_computers_from_department_section() {
        try {
        Assert.assertTrue("HomePageTitle is not displayed",homePage.isHomePageTitleDisplayed());
        Assert.assertTrue("AllDepartment is not displayed",homePage.isAllDepartmentDisplayed());
            homePage.clickOnAllDepartmentLink();
            log.info("Clicking on the All Department Link");
            homePage.clickOnElectronicsAndComputersLink();
            Assert.assertTrue("ElectronicsAndComputers is not displayed", homePage.isElectronicsAndComputersDisplayed());
            log.info("Click On ElectronicsAndComputers Link");
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Element No Found"));
        }

    }

    @Then("User select Phone & Accessories from Electronics section")
    public void user_select_phone_accessories_from_electronics_section() throws InterruptedException {
        try {
        Assert.assertTrue("PhonesAndAccessories Title is not displayed",homePage.isPhonesAndAccessoriesDisplayed());
        homePage.clickOnPhoneAndAccessoriesLink();
            log.info("Click On the PhoneAndAccessories Link");
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Element No Found"));
        }
    }

    @Then("User select Mobile Phones & Smartphones")
    public void user_select_mobile_phones_smartphones() {
        try {
        amazonProductPage = new AmazonProductPage(BaseClass.getDriver());
            amazonProductPage.clickOnMobilePhoneLink();
            log.info("Click On the OnMobilePhone Link");
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Element No Found"));
        }
    }

    @Then("User selects the desired configuration as follows")
    public void user_selects_the_desired_configuration_as_follows(io.cucumber.datatable.DataTable dataTable) {
        try {
        Assert.assertTrue("ProductPage Title is not displayed",amazonProductPage.isProductPageDisplayed());
        log.info("Click On the OnMobilePhone Link");
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        amazonProductPage.filterBySamsungPhones(dataMap.get("PhoneBrand"));
        amazonProductPage.filterByMegaPixel(dataMap.get("CameraResolution"));
        amazonProductPage.filterByManufacturedYear(dataMap.get("ModelYear"));
        amazonProductPage.filterByPriceRange(dataMap.get("lowerPriceRange"), dataMap.get("higherPriceRange"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Element No Found"));
        }

    }

    @Then("user should able to verify the page after desired configuration")
    public void user_should_able_to_verify_the_page_after_desired_configuration() {
        try {
            Assert.assertTrue("Blank Page message is not displayed", amazonProductPage.verifyDisplayedMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Element No Found"));
        }

    }

    @Then("user select the {string},{string},{string},{string} and {string} as desired configuration")
    public void user_select_the_and(String PhoneBrand, String CameraResolution, String ModelYear, String lowerPriceRange, String higherPriceRange) {
        try {
        Assert.assertTrue("ProductPage Title is not displayed",amazonProductPage.isProductPageDisplayed());
        amazonProductPage.filterBySamsungPhones(PhoneBrand);
        amazonProductPage.filterByMegaPixel(CameraResolution);
        amazonProductPage.filterByManufacturedYear(ModelYear);
        amazonProductPage.filterByPriceRange(lowerPriceRange, higherPriceRange);
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Element No Found"));
        }
    }

    @Then("User selects the desired configuration")
    public void user_selects_the_desired_configuration() {
        try {
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
                            amazonProductPage.filterByPriceRange(lowRange, highRange);
                            break;
                    }
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Exception occurred",new Exception("Exception While reading the data from excel"));
        }
    }
}


