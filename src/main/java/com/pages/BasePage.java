package com.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private static WebDriver driver = null;
	private static String browser = null;

	public static void setDriver(String browser) {
		setBrowser(browser);
		if (browser.equals("chrome")) {
			System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

	public static void homePage(String url) {
		driver.get(url);
	}

	public void wait(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	static final int POLLING_INTERVAL_IN_MILLIS = 500;

	public void waitUntilElementContainsText(WebDriver driver, WebElement element, String text, int timeout) {
// Waiting for an element to be present on the page, checking for its
// presence once every second.
		final FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(POLLING_INTERVAL_IN_MILLIS, TimeUnit.MILLISECONDS).ignoring(TimeoutException.class);

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				try {
					System.out.println(element.getText());
					return element.getText().contains(text);
				} catch (final StaleElementReferenceException e) {
					return false;
				} catch (final NoSuchElementException e) {
					return false;
				}
			}
		});
	}

	public WebElement getElement(String element, By locator, int timeout) {
		try {
			return new WebDriverWait(driver, timeout)
					.until(ExpectedConditions.visibilityOf(getDriver().findElement(locator)));
		} catch (Exception e) {
			System.out.println("Unable to locate element " + element + ". Exception:" + e.getLocalizedMessage());
			throw e;
		}
	}

	public void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void click(WebElement element) {
		element.click();
	}

	public void selectVisibleText(String element, By locator, int timeout, String value) {
		Select select = new Select(getElement(element, locator, timeout));
		select.selectByVisibleText(value);
	}

	public void selectFromList(String screenItem, By locator, int timeout, String value) {
		if (getElement(screenItem, locator, timeout) != null) {
			List<WebElement> elements = getDriver().findElements(locator);
			for (WebElement element : elements) {
				if (element.getText().equals(value)) {
					element.click();
				}
				;
			}
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		BasePage.driver = driver;
	}

	public File takeScreenShot(String file) throws IOException {
		TakesScreenshot screenShot = ((TakesScreenshot) getDriver());
		File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(file);
		FileUtils.copyFile(srcFile, destFile);
		return srcFile;
	}

	public static String getBrowser() {
		return browser;
	}

	public static void setBrowser(String browser) {
		BasePage.browser = browser;
	}

	public static void TakeDown() {
		getDriver().quit();
	}
	/*
	 * ImplicitlyWait Command
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 *   
	 * FluentWait Command
	 	Wait wait = new FluentWait(driver)
    		.withTimeout(30, SECONDS)
    		.pollingEvery(5, SECONDS)
    		.ignoring(NoSuchElementException.class);
    		
    	WebElement foo = wait.until(new Function() {

    	public WebElement apply(WebDriver driver) {

    	return driver.findElement(By.id("foo"));

	 *   
	 * ExpectedConditions Command
	 
	 	WebDriverWait wait = new WebDriverWait(driver, 10); 
 		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(>someid>)));
 		
	 *   
	 * PageLoadTimeout Command
	 	driver.manage().timeouts().pageLoadTimeout(100, SECONDS);
	 */
}
