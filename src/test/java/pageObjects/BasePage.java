package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	Actions actions;

	//int explicitWaitDuration= 10;
	    
	   public BasePage(WebDriver driver)
	     {
		     this.driver=driver;
			 this.actions = new Actions(driver);
			 PageFactory.initElements(driver,this);
	     }

	public void refreshPage() {
		driver.navigate().refresh();
	}

	//Method to click on element using Javascript
	public void javaScriptClick(WebElement element){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public boolean isTitleDisplayed( WebElement element)   // MyAccount Page heading display status
	{
		try {
			return (waitForElementToBeVisible(element,10).isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}

	public void drawBorder(WebElement element){
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	// Method to wait for element to be clickable
	public WebElement waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		wait = new WebDriverWait(driver,Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Method to wait for element to be visible
	public WebElement waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Method to perform mouse hover action
	public void hoverOverElement(WebElement element) {
		actions.moveToElement(element).perform();
	}

	public void hoveOverElementAndClick(WebElement element) {
		actions.moveToElement(element).click().perform();
	}

	// Method to perform drag and drop action
	public void dragAndDrop(WebElement source, WebElement target) {
		actions.dragAndDrop(source, target).perform();
	}

	// Method to perform double-click action
	public void doubleClick(WebElement element) {
		actions.doubleClick(element).perform();
	}
	   	   
}





