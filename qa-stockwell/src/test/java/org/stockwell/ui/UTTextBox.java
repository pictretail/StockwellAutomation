package org.stockwell.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.stockwell.browser.WebDriverFactory;
import org.stockwell.reportsetup.UTReportFactory;
import org.stockwell.tests.TestBase;
import com.aventstack.extentreports.Status;


public class UTTextBox extends WebDriverFactory {
	

	public void enterText(By locator, String text) {
		try {
			getDriver().findElement(locator).clear();
			getDriver().findElement(locator).sendKeys(text);

			if (UTReportFactory.getInstance().getExtent() != null) {
				UTReportFactory.getInstance().getExtent().log(Status.INFO, "entered text " + text);
			}

		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}

	public String getTextFromInput(By locator) {
		String text = null;
		try {
			text = getDriver().findElement(locator).getAttribute("value");
			UTReportFactory.getInstance().getExtent().log(Status.INFO,
					"got the text [" + text + " ] from the input [" + locator + " ]");
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
		return text;
	}

	public void enterText(By locator, Keys key) {
		try {
			getDriver().findElement(locator).sendKeys(key);

			if (UTReportFactory.getInstance().getExtent() != null) {
				UTReportFactory.getInstance().getExtent().log(Status.INFO, "entered keys " + key);
			}

		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}

	}

}
