package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonProductPage extends BasePage {

    public AmazonProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//b[text()='Smartphones']")
    WebElement pageTitle;

    @FindBy(linkText = "Mobile Phones & Smartphones")
    WebElement mobilePhonesFilter;

    @FindBy(xpath = "//input[@id='low-price']")
    WebElement lowerPriceRange;

    @FindBy(xpath = "//input[@id='high-price']")
    WebElement higherPriceRange;
    @FindBy(xpath = "//li[@class='a-spacing-micro']")
    public WebElement parentElement;

    @FindBy(xpath = "//input[@class='a-button-input']")
    public WebElement goButton;

    @FindBy(xpath = "//span[@class='a-size-medium-plus a-color-base']")
    public WebElement blankPageMessage;

    public WebElement getDynamicEmement(String SystemName) {
        return parentElement.findElement(By.xpath("//span[@class='a-size-base a-color-base'][text()='" + SystemName + "']"));
    }

    public void clickOnMobilePhoneLink() {
        mobilePhonesFilter.click();
    }

    public boolean isProductPageDisplayed(){
        return isTitleDisplayed(pageTitle);
    }
    public void filterBySamsungPhones(String phoneBrand) {
        waitForElementToBeVisible(parentElement, 5);
        hoveOverElementAndClick(getDynamicEmement(phoneBrand));
    }

    public void filterByMegaPixel(String cameraMegaPixel) {
        hoveOverElementAndClick(getDynamicEmement(cameraMegaPixel));
    }

    public void filterByManufacturedYear(String manufacturedYear) {
        hoveOverElementAndClick(getDynamicEmement(manufacturedYear));
    }

    public void filterByPriceRange(String lowerRange, String higherRange) {
        hoveOverElementAndClick(lowerPriceRange);
        lowerPriceRange.sendKeys(lowerRange);
        higherPriceRange.sendKeys(higherRange);
        goButton.click();
    }

    public boolean verifyDisplayedMessage() {
        try {
            return blankPageMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
