package org.stockwell.ui;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stockwell.browser.WebDriverFactory;
import org.stockwell.generic.DateAndTime;
import org.stockwell.reportsetup.UTReportFactory;
import org.stockwell.tests.TestBase;
import com.aventstack.extentreports.Status;


public class UTBase extends WebDriverFactory {
	DateAndTime dateAndTime = new DateAndTime();
	
	public boolean isDisplayed(By locator) {
		boolean isElementDisplayed = false;
		try {
			isElementDisplayed = getDriver().findElement(locator).isDisplayed();
			if (UTReportFactory.getInstance().getExtent() != null) {
				UTReportFactory.getInstance().getExtent().log(Status.INFO, locator + " is displayed");
			}
		} catch (NoSuchElementException exc) {
			isElementDisplayed = false;
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
		return isElementDisplayed;
	}

	public String getText(By locator) {
		String text = null;
		try {
			text = getDriver().findElement(locator).getText();
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
		return (text);
	}

	public void objectFocus(By locator) {
		Actions action = new Actions(getDriver());
		Action seriesOfActions = action.moveToElement(getDriver().findElement(locator)).build();
		seriesOfActions.perform();
		if (UTReportFactory.getInstance().getExtent() != null)
			UTReportFactory.getInstance().getExtent().log(Status.INFO, "the object [" + locator + " ] is focused");
	}

	public void click(By locator) {
		try {
			objectFocus(locator);
			getDriver().findElement(locator).click();
			if (UTReportFactory.getInstance().getExtent() != null) {
				UTReportFactory.getInstance().getExtent().log(Status.INFO, "clicked on [ " + locator + " ]");
			}
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}

	public WebElement waitforElement(By locator, int waitTime) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTime));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if (UTReportFactory.getInstance().getExtent() != null)
				UTReportFactory.getInstance().getExtent().log(Status.INFO,
						"waited for element [ " + locator + " ] and the object is visible");
		} catch (TimeoutException exc) {
			// Continue
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
		return element;
	}

	public WebElement waitforElementToBeVisible(By locator, int waitTime) {
		WebElement element = null;
		int waitTimeInSec = waitTime;
		boolean displayed = false;
		long startTime = System.currentTimeMillis();
		do {
			try {
				WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTimeInSec));
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				displayed = getDriver().findElement(locator).isDisplayed();
				UTReportFactory.getInstance().getExtent().log(Status.INFO,
						"waited for element [ " + locator + "] and the object is visible ");
			} catch (TimeoutException exc) {
				// Continue
			} catch (Exception exc) {
				TestBase.captureScreenshot(exc.toString());
			}
		} while ((!displayed) && (System.currentTimeMillis() - startTime) < waitTime * 1000);
		return element;
	}

}