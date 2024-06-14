package org.stockwell.ui;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.stockwell.browser.Factory;
import org.stockwell.reportsetup.ExtFactory;
import org.stockwell.tests.TestInfra;

import com.aventstack.extentreports.Status;


public class TextBox extends Factory {
	private Foundation foundation = new Foundation();

	public void enterText(By object, String text) {
		try {
			getDriver().findElement(object).clear();
			getDriver().findElement(object).sendKeys(text);

			if (ExtFactory.getInstance().getExtent() != null) {
				ExtFactory.getInstance().getExtent().log(Status.INFO, "entered text " + text);
			}

		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}

	public String getTextFromInput(By object) {
		String text = null;
		try {
			text = getDriver().findElement(object).getAttribute("value");
			ExtFactory.getInstance().getExtent().log(Status.INFO,
					"got the text [" + text + " ] from the input [" + object + " ]");
		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
		return text;
	}

	public void enterText(By object, Keys key) {
		try {
			getDriver().findElement(object).sendKeys(key);

			if (ExtFactory.getInstance().getExtent() != null) {
				ExtFactory.getInstance().getExtent().log(Status.INFO, "entered keys " + key);
			}

		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}

	}

}
