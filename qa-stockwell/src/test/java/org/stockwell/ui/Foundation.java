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
import org.stockwell.browser.Factory;
import org.stockwell.generic.DateAndTime;
import org.stockwell.reportsetup.ExtFactory;
import org.stockwell.tests.TestInfra;
import com.aventstack.extentreports.Status;


public class Foundation extends Factory {
	DateAndTime dateAndTime = new DateAndTime();
	
	public boolean isDisplayed(By object) {
		boolean isElementDisplayed = false;
		try {
			isElementDisplayed = getDriver().findElement(object).isDisplayed();
			if (ExtFactory.getInstance().getExtent() != null) {
				ExtFactory.getInstance().getExtent().log(Status.INFO, object + " is displayed");
			}
		} catch (NoSuchElementException exc) {
			isElementDisplayed = false;
		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
		return isElementDisplayed;
	}

	public String getText(By object) {
		String text = null;
		try {
			text = getDriver().findElement(object).getText();
		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
		return (text);
	}

	public void objectFocus(By element) {
		Actions action = new Actions(getDriver());
		Action seriesOfActions = action.moveToElement(getDriver().findElement(element)).build();
		seriesOfActions.perform();
		if (ExtFactory.getInstance().getExtent() != null)
			ExtFactory.getInstance().getExtent().log(Status.INFO, "the object [" + element + " ] is focused");
	}

	public void click(By object) {
		try {
			objectFocus(object);
			getDriver().findElement(object).click();
			if (ExtFactory.getInstance().getExtent() != null) {
				ExtFactory.getInstance().getExtent().log(Status.INFO, "clicked on [ " + object + " ]");
			}
		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}

	public WebElement waitforElement(By object, int waitTime) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTime));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(object));
			if (ExtFactory.getInstance().getExtent() != null)
				ExtFactory.getInstance().getExtent().log(Status.INFO,
						"waited for element [ " + object + " ] and the object is visible");
		} catch (TimeoutException exc) {
			// Continue
		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
		return element;
	}

	public WebElement waitforElementToBeVisible(By object, int waitTime) {
		WebElement element = null;
		int waitTimeInSec = waitTime;
		boolean displayed = false;
		long startTime = System.currentTimeMillis();
		do {
			try {
				WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTimeInSec));
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(object));
				displayed = getDriver().findElement(object).isDisplayed();
				ExtFactory.getInstance().getExtent().log(Status.INFO,
						"waited for element [ " + object + "] and the object is visible ");
			} catch (TimeoutException exc) {
				// Continue
			} catch (Exception exc) {
				TestInfra.failWithScreenShot(exc.toString());
			}
		} while ((!displayed) && (System.currentTimeMillis() - startTime) < waitTime * 1000);
		return element;
	}

}