package at.framework.ui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import at.framework.browser.Factory;
import at.framework.generic.DateAndTime;
import at.framework.reportsetup.ExtFactory;
import at.smartshop.keys.Constants;
import at.smartshop.keys.FilePath;
import at.smartshop.tests.TestInfra;

public class Foundation extends Factory {
	DateAndTime dateAndTime = new DateAndTime();
	public String parent;

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

	public void fluentWait(String object, int waitTime) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(waitTime))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver getDriver) {
					return getDriver.findElement(By.id(object));
				}
			});
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