package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class AmazonHomePage extends BasePage{

        public AmazonHomePage(WebDriver driver) {
            super(driver);
        }

    @FindBy(xpath = "//h4[contains(text(),'Select your cookie ')]")
    WebElement cookieBanner;

    @FindBy(xpath = "//input[@id='sp-cc-accept']")
    WebElement cookieAcceptButton;

    @FindBy(css = ".hm-icon.nav-sprite")
    WebElement allDepartmentButton;

    @FindBy(xpath = "//div[normalize-space()='Electronics & Computers']")
    WebElement electronicsAndComputers;

    @FindBy(xpath = "(//a[normalize-space()='Phones & Accessories'])[1]")
    WebElement phonesAndAccessories;


    public void acceptCookies(){
        if (waitForElementToBeVisible(cookieBanner,10).isDisplayed()) {
            cookieAcceptButton.click();
        }
    }

    public void clickOnAllDepartmentLink() {
        allDepartmentButton.click();
    }

    public void clickOnElectronicsAndComputersLink(){
        electronicsAndComputers.click();
    }

    public void clickOnPhoneAndAccessoriesLink() throws InterruptedException {
        Thread.sleep(2000);
        javaScriptClick(phonesAndAccessories);
    }
}
