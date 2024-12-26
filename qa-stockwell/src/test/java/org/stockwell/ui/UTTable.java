package org.stockwell.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.stockwell.browser.WebDriverFactory;
import org.stockwell.tests.TestBase;


public class UTTable extends WebDriverFactory{
  
	public List<String> getTblHeaders(By locator) {
		List<String> headerValues = new ArrayList<>();
		try {
			
			List<WebElement> columnHeaders = getDriver().findElements(locator);
			for (WebElement columnHeader : columnHeaders) {
				headerValues.add(columnHeader.getText());
				
			}
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
		return headerValues;
	}
}
