package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AmazonHomePage extends BasePage {

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h4[contains(text(),'Select your cookie ')]")
    WebElement cookieBanner;

    @FindBy(xpath = "//input[@id='sp-cc-accept']")
    WebElement cookieAcceptButton;

    @FindBy(id = "nav-logo-sprites")
    WebElement amazonHomePageTitle;

    @FindBy(linkText = "All")
    WebElement allDepartmentButton;

    @FindBy(xpath = "//div[normalize-space()='Electronics & Computers']")
    WebElement electronicsAndComputers;

    @FindBy(xpath = "(//a[normalize-space()='Phones & Accessories'])[1]")
    WebElement phonesAndAccessories;

    @FindBy(xpath = "//h4[text()='Enter the characters you see below']")
    WebElement captchaScreenMessage;

    int CaptchaRefresh =5;
    public void acceptCookies() throws InterruptedException {

        if(isTitleDisplayed(captchaScreenMessage)){
                Thread.sleep(5000);
                driver.navigate().refresh();
        }

        if(!isTitleDisplayed(cookieBanner)){
            driver.navigate().refresh();
        }
        if (waitForElementToBeVisible(cookieBanner, 10).isDisplayed()) {
            //drawBorder(cookieAcceptButton);
            cookieAcceptButton.click();
        }
    }

    public boolean isHomePageTitleDisplayed(){
        return isTitleDisplayed(amazonHomePageTitle);
    }
    public boolean isAllDepartmentDisplayed(){
        return isTitleDisplayed(allDepartmentButton);
    }

    public boolean isElectronicsAndComputersDisplayed(){
        return isTitleDisplayed(electronicsAndComputers);
    }

    public boolean isPhonesAndAccessoriesDisplayed(){
        return isTitleDisplayed(phonesAndAccessories);
    }
    public void clickOnAllDepartmentLink() {
        waitForElementToBeVisible(allDepartmentButton,10).click();
    }

    public void clickOnElectronicsAndComputersLink() throws InterruptedException {
        Thread.sleep(2000);
        waitForElementToBeClickable(electronicsAndComputers,10);
        hoveOverElementAndClick(electronicsAndComputers);
    }

    public void clickOnPhoneAndAccessoriesLink() throws InterruptedException {
        Thread.sleep(2000);
        javaScriptClick(phonesAndAccessories);
    }
}
